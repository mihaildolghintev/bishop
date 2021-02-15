(ns bishop.events.auth
  (:require
   [re-frame.core :as re-frame]
   [accountant.core :as accountant]
   [bishop.lib.fb :as fb]))



(re-frame/reg-event-db
 :auth/set-user
 (fn [db [_ user]]
   (-> db
       (assoc-in [:user :user] user)
       (assoc-in [:user :loading] false))))

(re-frame/reg-fx
 :auth/login-fx
 (fn [state]
   (let [email (:email state)
         password (:password state)
         on-success (:on-success state)
         on-failure (:on-failure state)]
     (-> (fb/auth)
         (.signInWithEmailAndPassword email password)
         (.then (fn [user] (re-frame/dispatch [on-success user])))
         (.catch (fn [error] (re-frame/dispatch [on-failure error])))))))

(re-frame/reg-event-db
 :auth/on-login-success
 (fn [db [_ user]]
   (-> db
       (assoc-in [:user :loading] false)
       (assoc-in [:user :user] user))
   (accountant/navigate! "/")))

(re-frame/reg-event-db
 :auth/on-login-failure
 (fn [db [_ error]]
   (-> db
       (assoc-in [:user :loading] false)
       (assoc-in [:user :error] error))))

(re-frame/reg-event-fx
 :auth/login
 (fn [{:keys [db]} [_ credentials]]
   {:db (assoc-in db [:user :loading] true)
    :auth/login-fx {:email (:email credentials)
                    :password (:password credentials)
                    :on-success :auth/on-login-success
                    :on-failure :auth/on-login-failure}}))
