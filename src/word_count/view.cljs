(ns word-count.view
  (:require [word-count.db :refer [db-atom]]))

(defn app-component
  []
  (let [db (deref db-atom)]
    [:div (:name db)]))
