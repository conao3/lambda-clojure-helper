(ns leiningen.aws
  (:require [leinjacker.deps :as deps]
            [leinjacker.eval :as eval]
            [lambda-clojure-helper.core :as helper]))

(defn aws
  "Deploy Clojure functions to AWS lambda."
  [project & args]
  ;; (leiningen.core.main/info "Hello AWS!")
  (apply helper/-main args))
