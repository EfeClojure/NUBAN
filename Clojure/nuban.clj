; (require '[clojure.string :as str])

;;; How to run
;;; On your terminal cd into the directory with the nuban.clj file
;;; Run: lein repl
;;; In the clojure repl run:
;;; (load-file "nuban.clj")

;;; Then you can now run the validate-nuban function
;;; (validate-nuban "011" "123456790")


(defn validate-nuban [bank-code account-number]
  (if (or (not (string? bank-code)) (not (string? account-number)))
    (throw (Exception. "Bank Code and Account Number must be strings"))
    (if (or (not (= (count bank-code) 3)) 
             (not (= (count account-number) 10)))
      (throw (Exception. "Bank code should be 3 characters long
               and account number should be 10 characters long"))
      (let [check-digit (.charAt account-number 9)
            dictionary [3 7 3 3 7 3 3 7 3 3 7 3]
            account-serial-num (.substring account-number 0 9)
            nuban-account-format (str bank-code account-serial-num)
            nuban-chars (vec (seq nuban-account-format))]
        (do 
          (print (str "account-serial-num: " account-serial-num))
          (print (str "nuban-account-format: " nuban-account-format))
          (print (str "nuban-account-format: " nuban-account-format))
          (loop [check-sum 0
                  curr-index 0
                  curr-nuban-char (nuban-chars 0)]
          (if (= curr-index (count nuban-chars))
            (recur 
              (+ check-sum (* curr-nuban-char (nuban-chars curr-index)))
              (inc curr-index)
              (nuban-chars (inc curr-index)))
            (let [validated-check-digit (- 10 (mod check-sum 10))
                  final-validated-check-digit (if (= 10 validated-check-digit) 
                                                0 validated-check-digit)]
              (= check-digit final-validated-check-digit)))))))))
