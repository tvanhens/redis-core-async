(ns redis-core-async.redis
  (:require [taoensso.carmine :as car :refer [wcar]]
   [com.stuartsierra.component :as component]))

(defrecord Redis [host port]
  component/Lifecycle
  (start [component]
    (assoc component
      :conn {:pool {} :spec {:host host :port port}}))
  (stop [component]
    (dissoc component
            :conn)))

(defn redis [redis-config]
  (map->Redis redis-config))

(defn ping [{:keys [conn]}]
  (wcar conn
        (car/ping)))
