(ns ring-utils.endpoints
  (:require [ring-utils.core :as impl]))

(def status
  [["/status" {:get impl/status}]])

(def status-admin
  [["/status" {:get impl/status-admin}]])

(def logout
  [["/logout" {:get impl/logout}]])

