(ns ring-utils.core
  (:require [ring.util.response :as util :refer [response redirect content-type]]))

(defn logged-in? [{session :session}]
  (contains? session :uid))

(defn status [{session :session headers :headers cookies :cookies :as req}]
  (-> (str "Session: " session 
        "\nCookies: " cookies 
        "\nHeaders: " headers 
        "\nLogged in: " (logged-in? req))
    (response)
    (content-type "txt")))

(defn logout [{session :session}]
  (-> (redirect "/")
      (assoc :session {})))
