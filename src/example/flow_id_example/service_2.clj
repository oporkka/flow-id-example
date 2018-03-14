(ns example.flow-id-example.service-2
  (:require [example.flow-id-example.client-2 :as c]))

(def component {})

(defn exists
  [{:keys [client-2]} status]
  (let [body (if (= 200 (:status (c/get-status client-2 status)))
               {:exists true}
               {:exists false})]
    {:status 200 :body body}))
