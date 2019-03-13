(ns sample-project.core
  (:require [clojure.data.json :as json])
  (:gen-class))

(defn hello-lambda [event]
  {'statusCode 200
   'headers {'content-type "text/plain"}
   'body "hello world"
   })

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
