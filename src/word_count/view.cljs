(ns word-count.view
  (:require [word-count.db :refer [db-atom]]))

(defn app-component
  []
  (let [db (deref db-atom)]
    [:div
     [:h1 "Word count"]
     ;[:div (str db)]
     [:textarea {:style     {:width  "100%"
                             :height "400px"}
                 :value     (:value db)
                 :on-change (fn [e]
                              (let [value (.-value (.-target e))]
                                (swap! db-atom assoc :value value)))}]
     [:div (count (:value db))]]))
