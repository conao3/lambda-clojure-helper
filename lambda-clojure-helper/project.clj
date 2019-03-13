(defproject com.conao3/lambda-clojure-helper "0.0.2"
  :description "A helper to work Clojure on AWS Lambda"
  :url "http://github.com/conao3/lambda-clojure-helper"
  :license {:name "Affero General Public License version 3 or lator"
            :url "https://www.gnu.org/licenses/agpl-3.0.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.4.1"]
                 [org.clojure/data.json "0.2.6"]
                 [myguidingstar/clansi "1.3.0"]
                 [com.cognitect.aws/api       "0.8.273"]
                 [com.cognitect.aws/endpoints "1.1.11.507"]
                 [com.cognitect.aws/s3        "697.2.391.0"]
                 [leinjacker "0.4.2" :exclusions [org.clojure/clojure]]]

  :repositories [["clojars" {:url "https://clojars.org/repo"
                             :creds :gpg}]]
  :deploy-repositories  [["releases" :clojars]
                         ["snapshots" :clojars]]
  :signing {:gpg-key "81903F6B7B02852F"}

  :main ^:skip-aot com.conao3.lambda-clojure-helper.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :eval-in-leiningen true)
