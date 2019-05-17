(ns ring-utils.middleware.not-found
  (:require [compojure.response :as compojure]
            [ring.util.response :as response]
            [clojure.tools.logging :as log]))

(defn log [request]
  (let [excludes #{"/apple-app-site-association"
                   "/apple-touch-icon-120x120-precomposed.png"
                   "/apple-touch-icon-120x120.png"
                   "/apple-touch-icon-152x152-precomposed.png"
                   "/apple-touch-icon-152x152.png"
                   "/apple-touch-icon-precomposed.png"
                   "/apple-touch-icon.png"
                   "/favicon.ico"
                   "/robots.txt"}]
    (when-not (contains? excludes (:uri request))
      (log/warn (:server-name request) (:request-method request) (:uri request)))))

(defn wrap-not-found
  [handler error-response]
  (fn [request]
    (or (handler request)
        (log request)
        (-> (compojure/render error-response request)
            (response/content-type "text/html")
            (response/status 404)))))


