(ns sample-project.core)

(defn hello-lambda [event]
  {'statusCode 200
   'headers {'content-type "text/plain"}
   'body "hello world"
   })

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
