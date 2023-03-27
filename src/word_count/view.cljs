(ns word-count.view
  (:require [word-count.db :refer [db-atom]]
            [word-count.logic :as logic]))

(defn slider-value [db]
  (if (:use-filter db)
    (int (* 100 (/ (logic/count-words (:value db)) (:target db))))
    (int (* 100 (/ (logic/count-all-words (:value db)) (:target db))))))
(defn app-component
  []
  (let [db (deref db-atom)]
    [:div
     [:style "input[type=text] {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        box-sizing: border-box;}"]
     [:h1 "Word count"]
    ; [:div (str db)]
     [:textarea {:style     {:width  "100%"
                             :height "400px"}
                 :value     (:value db)
                 :on-change (fn [e]
                              (let [value (.-value (.-target e))]
                                (swap! db-atom assoc :value value)))}]
     [:div {:style {:width "100%" :backgroundColor (if (< 90 (slider-value db)) "MediumSeaGreen" "Inherit")}}
     [:input {:type  "range" :value (slider-value db) :min 0 :max 100
              :style {:width "98%"}}]]
     [:p
      [:label {:for "target"} "Målvärde "]
             [:input {:type      "text"
                      :id        "target"
                      :value (if (int? (:target db))
                               (:target db)
                               "")
                      :on-change (fn [e]
                                   (let [target (js/parseInt (.-value (.-target e)))]
                                     (swap! db-atom assoc :target target)))}]]
     [:p
      [:label {:for "wc"} "Word Count "]
      [:input {:type "checkbox"
               :id "filter"
               :checked (:use-filter db)
               :on-change (fn [e]
                            (let [use-filter (.-checked (.-target e))]
                              (swap! db-atom assoc :use-filter use-filter)))}]
      [:label {:for "filter"}
       "Use LaTeX filter"]

      [:input {:type  "text"
               :id    "wc"
               :read-only true
               :value (if (:use-filter db)
                        (logic/count-words (:value db))
                        (logic/count-all-words (:value db)))}]]
     ;[:div (str (slider-value db))]
     ]))

