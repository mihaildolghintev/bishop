(ns bishop.pages.login
  (:require [re-frame.core :as re-frame]
            [fork.re-frame :as fork]
            [bishop.components.buttons :as btn]))

(defn- validate [values]
  (let [email (get values "email" "")
        password (get values "password" "")]
    (cond->
     {}
      (not (re-matches #".+@.+\..+" email))
      (assoc "email" "Email неверный")
      (< password 6)
      (assoc "password" "Пароль меньше 6 символов"))))

(defn login-page []
  [fork/form
   {:validation validate
    :on-submit (fn [x] (re-frame/dispatch [:auth/login
                                           {:email (get (:values x) "email")
                                            :password (get (:values x) "password")}]))
    :prevent-default? true}
   (fn [{:keys [values
                touched
                errors
                handle-change
                handle-submit]}]
     (let [loading? @(re-frame/subscribe [:auth/loading])
           render-error (fn [field]
                          (when (and (touched field) (get errors field))
                            [:div (get errors field)]))]
       [:div {:style {:height "100vh"
                      :display :flex
                      :justify-content :center
                      :align-items :center}}
        [:form {:on-submit handle-submit}
         [render-error "email"]
         [render-error "password"]
         [:input.input {:type :text
                               :name "email"
                               :placeholder "Email"
                               :value (values "email")
                               :on-change handle-change}]
         [:input.input
          {:type :password
           :name "password"
           :placeholder "Пароль"
           :value (values "password")
           :on-change handle-change}]
         [btn/button {:block true
                      :color :dark
                      :loading loading?
                      :text "Login"}]]]))])


