(ns leiningen.lambda
  (:require [leinjacker.deps :as deps]
            [leinjacker.eval :as eval]
            [lambda-clojure-helper.core :as helper]))

(defn lambda
  "Deploy Clojure functions to AWS lambda."
  [project & args]
  ;; (leiningen.core.main/info "Hello AWS!")
  (apply helper/-main args))
