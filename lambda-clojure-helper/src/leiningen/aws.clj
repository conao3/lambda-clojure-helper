(ns leiningen.aws
  (:require [leinjacker.deps :as deps]
            [leinjacker.eval :as eval]))

(defn aws
  "aws"
  [project & args]
  (leiningen.core.main/info "Hello AWS!"))
