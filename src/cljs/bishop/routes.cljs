(ns bishop.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:require
   [secretary.core :as secretary]
   [re-frame.core :as re-frame]
   ))


(defn app-routes []
  (defroute "/" []
   (re-frame/dispatch [:router/set-active-page :home-page])))

    (defroute "/login" []
      (re-frame/dispatch [:router/set-active-page :login-page]))

    (defroute "/about" []
      (re-frame/dispatch [:router/set-active-page :about-page]))
