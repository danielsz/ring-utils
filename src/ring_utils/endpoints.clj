(ns ring-utils.endpoints
  (:require [compojure.core :refer [routes GET]]
            [ring-utils.core :refer [status* status-admin* logout*]]))

(defn status [_]
  (routes
   (GET "/status" [] status*)))

(defn status-admin [_]
  (routes
   (GET "/status" [] status-admin*)))

(defn logout [_]
  (routes
   (GET "/logout" [] logout)))

