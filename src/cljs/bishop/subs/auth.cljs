(ns bishop.subs.auth
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :auth/current-user
 (fn [db _]
   (get-in db [:user :user])))

(re-frame/reg-sub
 :auth/loading
 (fn [db _]
   (get-in db [:user :loading])))
