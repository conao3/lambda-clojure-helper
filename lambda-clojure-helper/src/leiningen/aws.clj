(ns leiningen.aws
  (:require [leinjacker.deps :as deps]
            [leinjacker.eval :as eval]
            [lambda-clojure-helper.core :as helper]))

(defn aws
  "aws"
  [project & args]
  (leiningen.core.main/info "Hello AWS!")
  (apply helper/-main args))
