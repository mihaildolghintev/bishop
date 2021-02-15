(ns bishop.lib.fb
  (:require ["@firebase/app" :refer [firebase]]
            ["@firebase/auth"]
            ["@firebase/firestore"]
            [accountant.core :as accountant]
            [re-frame.core :as re-frame]))


(def firebase-config {:apiKey "AIzaSyAd43JUryT3oMCVuHEhUbLyLkmk-pT0u4c"
                      :authDomain "sandbox-8254e.firebaseapp.com"
                      :projectId "sandbox-8254e"
                      :storageBucket "sandbox-8254e.appspot.com"
                      :messagingSenderId "752836572489"
                      :appId "1:752836572489:web:405277c27aece6cbe85698"})

(defn firebase-init []
  (if (zero? (count (.-apps firebase)))
    (-> firebase
        (.initializeApp (clj->js firebase-config)))
    (.-app firebase)))

(defn firestore []
  (.firestore firebase))

(defn auth []
  (.auth firebase))


(defn on-auth-state-changed []
  (.onAuthStateChanged (auth)
                       (fn [user]
                         (if user
                           (re-frame/dispatch [:auth/set-user user])
                           (accountant/navigate! "/login")))))




