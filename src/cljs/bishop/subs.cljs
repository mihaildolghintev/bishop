(ns bishop.subs
  (:require
   [re-frame.core :as re-frame]
   [bishop.subs.auth]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 :router/active-page
 (fn [db _]
   (:active-page db)))
