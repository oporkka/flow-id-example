(ns example.flow-id-example.client
  (:require [clj-http.client :as http]
            [example.flow-id-example.flow-id :as fid]
            [org.zalando.stups.friboo.log :as log]))

(def component {:host "https://httpstatuses.com"})

(defn get-status
  [{:keys [host]} status-code flow-id]
  (let [headers {"x-flow-id" flow-id}
        params  {:method           :get
                 :url              (str host "/" status-code)
                 :headers          headers
                 :throw-exceptions false}]
    (log/info (str "X-Flow-Id is " flow-id))
    (http/request params)))

