(ns redis-core-async.system
  (:require [redis-core-async.redis :refer [redis]]
            [com.stuartsierra.component :as component]))

(defn dev-system
  [{redis-config :redis}]
  (component/system-map
   :redis (redis redis-config)))
