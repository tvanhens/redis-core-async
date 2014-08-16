(ns user
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.namespace.repl :refer (refresh)]
            [redis-core-async.system :refer [dev-system]]))

(def system nil)

(defn init []
  (alter-var-root #'system
                  (constantly (dev-system {:redis {:host "localhost" :port 6379}}))))

(defn start []
  (alter-var-root #'system component/start))

(defn stop []
  (alter-var-root #'system
    (fn [s] (when s (component/stop s)))))

(defn go []
  (init)
  (start))

(defn reset []
  (stop)
  (refresh :after 'user/go))
