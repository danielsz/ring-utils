(ns ring-utils.middleware.admin)

(defn wrap-admin [handler uids]
  (fn [{session :session headers :headers :as request}]
    (if (contains? session :admin)
      (handler request)
      (handler (assoc request :session (assoc session :admin uids))))))
