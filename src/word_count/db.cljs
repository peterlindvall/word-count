(ns word-count.db
  (:require [reagent.core :as reagent]))

(defonce db-atom (reagent/atom {:name "Peter"}))
