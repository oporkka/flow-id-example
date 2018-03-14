(ns example.flow-id-example.service
  (:require [example.flow-id-example.client :as c]))

(def component {})

(defn exists
  [{:keys [client]} status flow-id]
  (let [body (if (= 200 (:status (c/get-status client status flow-id)))
               {:exists true}
               {:exists false})]
    {:status 200 :body body}))
