(ns bishop.components.navigation
  (:require [re-frame.core :as re-frame]))

(defn navbar []
  (let [current-page @(re-frame/subscribe [:router/active-page])]
    [:div.navbar.is-dark {:role "navigation"
                  :aria-label "main navigation"}
     [:div.navbar-brand
      [:a.navbar-item {:href "/"} "Bishop"]]
     [:div.navbar-start
      [:a.navbar-item {:href "/"
                       :class [(when (= current-page :home-page) "is-active")]} "Home"]
      [:a.navbar-item {:href "/about"
                       :class [(when (= current-page :about-page) "is-active")]} "About"]]]))