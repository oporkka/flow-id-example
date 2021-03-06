(defproject example/flow-id-example "0.0.1-SNAPSHOT"
  :description "An example project that presents request scoped component dependencies."
  :url "https://github.com/oporkka/flow-id-example"
  :license {:name "The MIT License"
            :url  "https://opensource.org/licenses/MIT"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.zalando.stups/friboo "2.0.0-beta5"]]
  :main ^:skip-aot example.flow-id-example.core
  :uberjar-name "flow-id-example.jar"
  :target-path "target/%s"
  :manifest {"Implementation-Version" ~#(:version %)}
  :plugins [[lein-cloverage "1.0.9"]
            [lein-set-version "0.4.1"]]
  :aliases {"cloverage" ["with-profile" "test" "cloverage"]}
  :profiles {:uberjar {:aot :all}
             :dev     {:repl-options {:init-ns user}
                       :source-paths ["dev"]
                       :dependencies [[org.clojure/tools.namespace "0.2.11"]
                                      [org.clojure/java.classpath "0.2.3"]
                                      [midje "1.8.3"]]}})
