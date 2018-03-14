(ns example.flow-id-example.api
  (:require [org.zalando.stups.friboo.ring :refer :all]
            [org.zalando.stups.friboo.log :as log]
            [org.zalando.stups.friboo.config :refer [require-config]]
            [com.stuartsierra.component :as component]
            [example.flow-id-example.service :as service]
            [example.flow-id-example.service-2 :as service-2]
            [ring.util.response :as r]))

(defrecord Controller [configuration]
  component/Lifecycle
  (start [this]
    (log/info "Starting API Controller")
    this)
  (stop [this]
    (log/info "Stopping API Controller")
    this))

(defn get-status
  [{:keys [service] :as this} {:as params :keys [status x-flow-id]} request]
  (service/exists service status x-flow-id))

(defn get-status-with-flow-id-component
  [{:keys [service-2] :as this} {:as params :keys [status]} request]
  (service-2/exists service-2 status))


