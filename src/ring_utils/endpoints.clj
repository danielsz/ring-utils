(ns ring-utils.endpoints
  (:require [compojure.core :refer [routes GET]]
            [ring-utils.core :as impl]))

(defn status [_]
  (routes
   (GET "/status" [] impl/status)))

(defn status-admin [_]
  (routes
   (GET "/status" [] impl/status-admin)))

(defn logout [_]
  (routes
   (GET "/logout" [] impl/logout)))

