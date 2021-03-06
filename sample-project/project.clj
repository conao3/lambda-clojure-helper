(defproject sample-project "0.1.0"
  :description "lambda-clojure-helper sample project"
  :url "http://github.com/conao3/lambda-clojure-helper"
  :license {:name "Affero General Public License version 3 or lator"
            :url "https://www.gnu.org/licenses/agpl-3.0.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/data.json "0.2.6"]
                 [com.conao3/lambda-clojure-helper "0.0.2"]]
  :repl-options {:init-ns sample-project.core}
  :java-source-paths ["src/java"]
  :aot :all)
