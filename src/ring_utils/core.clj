(ns ring-utils.core
  (:require [ring.util.response :as util :refer [response redirect content-type]]
            [clojure.string :as str]))

(defn logged-in? [{session :session}]
  (contains? session :uid))

(defn admin? [{session :session}]
  (some #{(:uid session)} (:admin session)))

(defn status [server-names req]
  (if (some #{(:server-name req)} @server-names)
    (-> (str/join "\n" (for [[key val] req] (str key " " val)))
       (response)
       (content-type "text/plain"))
    (content-type {:status  403
                   :body "Forbidden"} "text/plain")))

(defn status-admin [server-names req]
  (if (admin? req)
    (status server-names req)
    (content-type {:status  403
                   :body "Forbidden"} "text/plain")))

(defn logout [{session :session}]
  (-> (redirect "/")
      (assoc :session {})))

(defn origin [{server-name :server-name server-port :server-port scheme :scheme :as request}]
  (if (> server-port 8000)
    (str (name scheme) "://" server-name) ;production
    (str (name scheme) "://" server-name ":" server-port) ;development
))

(defn subdomain [x]
  (let [host (if (map? x)
               (get x "host")
               x)]
    (first (str/split host #"\."))))
