(defproject lambda-clojure-helper "0.0.1"
  :description "A helper to work Clojure on AWS Lambda"
  :url "http://github.com/conao3/lambda-clojure-helper"
  :license {:name "Affero General Public License version 3 or lator"
            :url "https://www.gnu.org/licenses/agpl-3.0.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/data.json "0.2.6"]]
  :main ^:skip-aot aws-lambda-clojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
