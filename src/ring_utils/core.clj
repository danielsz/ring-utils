(ns ring-utils.core
  (:require [ring.util.response :as util :refer [response redirect content-type]]))

(defn logged-in? [{session :session}]
  (contains? session :uid))

(defn admin? [{session :session}]
  (contains? session :admin))

(defn status [{session :session headers :headers cookies :cookies :as req}]
  (-> (str "Session: " session 
        "\nCookies: " cookies 
        "\nHeaders: " headers 
        "\nLogged in: " (logged-in? req))
    (response)
    (content-type "txt")))

(defn status-admin [req]
  (if (admin? req)
    (status req)
    (redirect "/")))

(defn logout [{session :session}]
  (-> (redirect "/")
      (assoc :session {})))
