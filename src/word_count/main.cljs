(ns ^:figwheel-hooks word-count.main
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [clojure.string :as str]
            [word-count.db :refer [db-atom]]
            [word-count.view :refer [app-component]]))


(defn render []
  (rdom/render [app-component] (js/document.getElementById "app")))

(render)

(defn on-reload
  {:after-load true}
  []
  (println "Reloading..."))