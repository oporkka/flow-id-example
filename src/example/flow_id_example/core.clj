(ns example.flow-id-example.core
  (:require [org.zalando.stups.friboo.config :as config]
            [org.zalando.stups.friboo.system :as system]
            [org.zalando.stups.friboo.system.http :as http]
            [org.zalando.stups.friboo.log :as log]
            [com.stuartsierra.component :as component]
            [example.flow-id-example.api :as api]
            [example.flow-id-example.client :as client]
            [example.flow-id-example.client-2 :as client-2]
            [example.flow-id-example.service :as service]
            [example.flow-id-example.service-2 :as service-2]
            [example.flow-id-example.flow-id :as fid]
            [io.sarnowski.swagger1st.core :as s1st])
  (:gen-class))

(def default-http-config
  {:http-port 8080})

(def middlewares
  "Extend default Friboo Ring middlewares with the flow-id middleware"
  (let [before-executor (:before-executor http/default-middlewares)]
    (merge http/default-middlewares
           {:before-executor (conj before-executor
                                   #(s1st/ring % fid/middleware))})))


(defn run
  "Initializes and starts the whole system."
  [args-config]
  (let [config (config/load-config
                 (merge default-http-config
                        args-config)
                 [:http :api])
        system (component/map->SystemMap
                 {:http      (component/using
                               (http/map->Http {:api-resource      "api.yaml"
                                                :configuration     (:http config)
                                                :middlewares       middlewares
                                                :security-handlers {} ; No security by default
                                                :s1st-options      http/default-s1st-options})
                               {:controller :api})
                  :service   (component/using
                               service/component
                               [:client])
                  :service-2 (component/using
                               service-2/component
                               [:client-2])
                  :client    (component/using
                               client/component
                               [])
                  :client-2  (component/using
                               client-2/component
                               [:flow-id])
                  :flow-id   fid/flow-id
                  :api       (component/using
                               (api/map->Controller {:configuration (:api config)})
                               [:service :service-2])})]
    (system/run config system)))

(defn -main
  "The actual main for our uberjar."
  [& args]
  (try
    (run {})
    (catch Exception e
      (log/error e "Could not start the system because of %s." (str e))
      (System/exit 1))))
