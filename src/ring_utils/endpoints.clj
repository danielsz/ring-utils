(ns ring-utils.endpoints
  (:require [compojure.core :refer [routes GET]]
            [ring-utils.core :refer [status status-admin logout]]))

(defn utils [_]
  (routes
   (GET "/status" [] status-admin)
   (GET "/logout" [] logout)))
