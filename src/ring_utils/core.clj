(ns ring-utils.core
  (:require [ring.util.response :as util :refer [response redirect content-type]]
            [clojure.string :as str]))

(defn logged-in? [{session :session}]
  (contains? session :uid))

(defn admin? [{session :session}]
  (contains? session :admin))

(defn status [{session :session headers :headers cookies :cookies flash :flash :as req}]
  (-> (str "Session: " session 
        "\nCookies: " cookies 
        "\nHeaders: " headers
        "\nFlash: " flash
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

(defn origin [{server-name :server-name server-port :server-port scheme :scheme :as request}]
  (if (> server-port 8000)
    (str "https://" server-name) ;production
    (str "http://" server-name ":" server-port) ;development
))

(defn subdomain [x]
  (let [host (if (map? x)
               (get x "host")
               x)]
    (first (str/split host #"\."))))
