(ns bishop.components.buttons)

(defn- button-color [color]
  (color {:primary "is-primary"
          :success "is-success"
          :danger "is-danger"
          :warning "is-warning"
          :light "is-light"
          :white "is-white"
          :dark "is-dark"
          :ghost "is-ghost"
          :black "is-black"}))



(defn button [{:keys [text on-click color block disabled class loading]
               :or {color :primary}}]
  [:button.mt-1.mb-1 {:class (apply conj
                                    ["button"
                                     (when block "is-fullwidth")
                                     (when loading "is-loading")
                                     (button-color color)]
                                    class)
                      :disabled disabled
                      :on-click on-click} text])



