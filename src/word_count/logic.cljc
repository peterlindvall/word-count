(ns word-count.logic
  (:require  [clojure.string :as str]))

(defn remove-lines [lines]
  (filter (fn [line]
            (and (not= \\ (first (str/trim line)))
                 (not= \% (first (str/trim line)))))
          lines))

(defn remove-words [strings]
  (filter (fn [string]
            (and (not= \\ (first (str/trim string)))
                 (not= \% (first (str/trim string)))))
          strings))
(defn remove-emphs [string]
  (str/replace string #"\\emph\{([a-zA-zÅåÄäÖö 0-9,?]+)\}" "$1")
  )
(defn text-as-string [text]
  (->> text
       (str/split-lines)
       (remove-lines)
       (str/join " ")
       (remove-emphs)))

(defn count-words [text]
  (let [words (-> text
                  (text-as-string)
                  (str/trim)
                  (str/split #"\s+")
                  (remove-words))]
    (if (and (= 1 (count words))
         (= 0 (count (first words))))
      0
      (count words))))

(defn count-all-words [text]
  (let [gross-string (->> text
                          (str/split-lines)
                          (str/join " "))
        words (-> gross-string
                       (str/trim)
                       (str/split #"\s+"))]
    (if (and (= 1 (count words))
             (= 0 (count (first words))))
      0
      (count words))))
(defn get-words [text]
  (let [gross-string (text-as-string text)
        words (remove-words (str/split (str/trim gross-string) #"\s+"))]
    words))
(comment
  (count-words "aaa aaa.\n %bbb bbb bbb\n\n \n\n\nccc ccc ccc\n\\documentclass")
  (remove-words ["asd" "\\autocite{wittg:23}" "\\emph{kursiverat}"])
  (str/replace "\\emph{adsf} \\emph{qwer}" #"\\emph\{(\w+)\}" "$1")
)

