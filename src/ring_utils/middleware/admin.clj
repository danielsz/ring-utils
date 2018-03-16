(ns ring-utils.middleware.admin)

(defn wrap-admin [handler uids]
  (fn [{session :session headers :headers :as request}]
    (if (and (not (contains? session :admin)) (some #{(:uid session)} uids))
      (handler (assoc request
                      :flash {:message (str "Welcome admin!") :level :success}
                      :session (assoc session :admin true)))
      (handler request))))
