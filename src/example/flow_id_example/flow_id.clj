(ns example.flow-id-example.flow-id)

(def ^:dynamic ^:private *flow-id* "")

(def flow-id #'*flow-id*)

(defn to-header [flow-id-component]
  (if (var? flow-id-component)
    {:x-flow-id (var-get flow-id-component)}
    {}))

(defn middleware
  "Ring middleware that binds x-flow-id header value from
  the headers to be used for flow-id value and injected as a dependency
  where needed.

  Requires that headers have the key \"x-flow-id\" in order to get the
  value. In case there is another middleware that generates this value,
  that middleware has to be processed before this one."
  [handler]
  (fn [{:keys [headers] :as request}]
    (let [flow-id (get headers "x-flow-id")]
      (binding [*flow-id* flow-id]
        (handler request)))))
