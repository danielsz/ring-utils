(ns ring-utils.endpoints
  (:require [compojure.core :refer [routes GET POST]]
            [ring-utils.core :refer [status logout]]))

(defn utils [_]
  (routes
   (GET "/status" [] status)
   (GET "/logout" [] logout)))
