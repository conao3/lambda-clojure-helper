(ns com.conao3.lambda-clojure-helper.core
  (:require [clojure.edn :as edn]
            [clojure.string :as string]
            [clojure.data.json :as json]
            [clojure.tools.cli :refer [parse-opts]])
  (:use [clojure.string :only (join split)]
        [clojure.java.shell :only [sh]]
        [clansi.core :only [style]])
  (:gen-class))

(def user-config
  (edn/read-string (slurp "resources/lambda-clojure-helper-config.edn")))

(def cli-options
  ;; An option with a required argument
  [
   ;; ["-v" nil "Verbosity level"
   ;;  :id :verbosity
   ;;  :default 0
   ;;  :update-fn inc] ; Prior to 0.4.1, you would have to use:
   ;; ;; :assoc-fn (fn [m k _] (update-in m [k] inc))
   ;; A boolean option defaulting to nil
   ["-v" "--verbose" "Show debug messages" :default false]
   ["-h" "--help" "Show this help"]])

(defn usage [options-summary]
  (->> ["A helper to work Clojure on AWS Lambda"
        ""
        "Usage: lein aws <actions> [options]"
        ""
        "Actions:"
        "  echo            system command execution test"
        "  show-config     show user-config"
        "  dryrun          dryrun of deploy"
        "  deploy          deploy functions"
        ""
        "Options:"
        options-summary
        ""
        "Please refer to the readme for more information."]
       (string/join \newline)))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (string/join \newline errors)))

(defn validate-args
  "Validate command line arguments. Either return a map indicating the program
  should exit (with a error message, and optional ok status), or a map
  indicating the action the program should take and the options provided."
  [args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond
      (:help options) ; help => exit OK with usage summary
      {:exit-message (usage summary) :ok? true}

      errors ; errors => exit with description of errors
      {:exit-message (error-msg errors)}

      ;; custom validation on arguments
      (and (= 1 (count arguments))
           (#{"echo" "show-config" "dryrun" "deploy"} (first arguments)))
      {:action (first arguments) :options options}

      :else ; failed custom validation => exit with usage summary
      {:exit-message (usage summary)})))

(defn exit [status msg]
  (println msg)
  (System/exit status))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn shell-command [& args]
  (println (clojure.string/join " " (cons "$" args)))
  (apply sh args))

(defn shell-command-json [& args]
  (let [result (apply shell-command args)]
    (if (== (:exit result) 0)
      (json/read-str (:out result) :key-fn keyword)
      (exit (:exit result) (:err result)))))

(defn aws-shell-command [& args]
  (apply shell-command-json (cons "aws" args)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn create-cache []
  (let [config (edn/read-string (slurp "resources/config.edn"))]
    (spit "resources/.cache.edn"
          (pr-str config))
    (println (:out (sh "aws" "apigateway" "get-rest-apis")))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn action-echo [options]
  (println (:out (sh "echo" "Lambda-clojure-helper"))))

(defn action-show-config [options]
  (println user-config))

(defn action-dryrun [{:keys [verbose], :as options}]
  (when verbose
    (print (style "user-config: " :green))
    (action-show-config options))
  (println options))

(defn action-deploy [options]
  (create-cache))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; this project also run `lein run <subcommnad>'
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [{:keys [action options exit-message ok?]} (validate-args args)]
    (if exit-message
      (exit (if ok? 0 1) exit-message)
      (case action
        "echo"        (action-echo options)
        "show-config" (action-show-config options)
        "dryrun"      (action-dryrun options)
        "deploy"      (action-deploy options)))))
