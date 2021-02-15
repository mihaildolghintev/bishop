(ns bishop.views
  (:require
   [re-frame.core :as re-frame]
   [bishop.subs :as subs]
   [bishop.components.navigation :refer [navbar]]
   [bishop.pages.login :refer [login-page]]
   ))


;; home

(defn home-page []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 (str "Hello from " @name ". This is the Home Page.")]

     [:div
      [:a {:href "/about"}
       "go to About Page"]]
     ]))


;; about

(defn about-page []
    [:div
     [:h1 "This is the About Page."]

     [:div
      [:a {:href "/"}
       "go to Home Page"]]])


;; main

(defn- pages [page-name]
  (case page-name
    :home-page [home-page]
    :about-page [about-page]
    :login-page [login-page]
    [:div]))

(defn show-page [page-name]
  [pages page-name])

(defn main-page []
  (let [active-page (re-frame/subscribe [:router/active-page])]
    [:div
     [navbar]
     [show-page @active-page]]))
