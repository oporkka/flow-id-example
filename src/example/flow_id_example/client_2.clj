(ns example.flow-id-example.client-2
  (:require [clj-http.client :as http]
            [example.flow-id-example.flow-id :as fid]
            [org.zalando.stups.friboo.log :as log]))

(def component {:host "https://httpstatuses.com"})

(defn get-status
  [{:keys [host flow-id]} status-code]
  (let [headers (fid/to-header flow-id)
        params  {:method           :get
                 :url              (str host "/" status-code)
                 :headers          headers
                 :throw-exceptions false}]
    (log/info (str "X-Flow-Id is " (:x-flow-id headers)))
    (http/request params)))

