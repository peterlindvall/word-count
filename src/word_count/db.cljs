(ns word-count.db
  (:require [reagent.core :as reagent]))

(defonce db-atom (reagent/atom {:use-filter true
                                :target 2000}))
