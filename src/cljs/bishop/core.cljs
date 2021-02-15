(ns bishop.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [accountant.core :as accountant]
   [secretary.core :as secretary]
   [bishop.lib.fb :as fb]
   [bishop.events :as events]
   [bishop.routes :as routes]
   [bishop.views :as views]
   [bishop.config :as config]))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-page] root-el)))

(defn init []
  (routes/app-routes)
  (accountant/configure-navigation!
   {:nav-handler (fn [path] (secretary/dispatch! path))
    :path-exists? (fn [_] true)})
  (accountant/dispatch-current!)
  (fb/firebase-init)
  (fb/on-auth-state-changed)

  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
