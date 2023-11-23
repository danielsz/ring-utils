(ns ring-utils.endpoints
  (:require [ring-utils.core :as impl]))

(def server-names (atom ["localhost" "localtest.me"])) ; development

(def status
  [["/status" {:get (partial impl/status server-names)}]])

(def status-admin
  [["/status" {:get (partial impl/status-admin server-names)}]])

(def logout
  [["/logout" {:get impl/logout}]])

