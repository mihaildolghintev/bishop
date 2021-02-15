(ns bishop.events
  (:require
   [re-frame.core :as re-frame]
   [bishop.events.auth]
   [bishop.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 :router/set-active-page
 (fn [db [_ active-page]]
   (assoc db :active-page active-page)))
