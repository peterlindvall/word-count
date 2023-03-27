(ns word-count.view
  (:require [word-count.db :refer [db-atom]]
            [word-count.logic :as logic]))

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

      [:p (str "LaTeX Wordcount " (logic/count-words (:value db)))]
      [:p (str "Wordscount " (logic/count-all-words (:value db)))]]))
