# Car Laundry - Team 11

# Εισαγωγή

Η εφαρμογή αυτή αφορά την διαχείριση πωλήσεων ενός πλυντηρίου αυτοκινήτων. Υποστηρίζει τα καθήκοντα του υπαλλήλου υποδοχής (όπως είναι η καταχώριση των ραντεβού αποτρέποντας τις επικαλύψεις) και των καθαριστών (όπως είναι η ολοκλήρωση ενός καθαρισμού) της επιχείρησης. Το σύστημα, ακόμη, υπολογίζει  χρήσιμα για τη διεύθυνση στατιστικά στοιχεία.

## Επισκόπηση

Σε αυτό το έγγραφο περιλαμβάνονται η περιγραφή της εφαρμογής, το διάγραμμα περιπτώσεων χρήσης και σύντομη περιγραφή των περιπτώσεων χρήσης.

# Συνολική περιγραφή

## Επισκόπηση μοντέλου περιπτώσεων χρήσης

![Διάγραμμα περιπτώσεων χρήσης](requirements/use-case-diagram.png)

# Ειδικές Απαιτήσεις 

## Περιπτώσεις χρήσης

### Οι ενδιαφερόμενοι και οι ανάγκες τους

Οι ενδιαφερόμενοι είναι ο υπάλληλος υποδοχής, οι πελάτες, οι καθαριστές, αλλά και η διεύθυνση της επιχείρησης.

### Actors του συστήματος

* Χρήστης (Υπάλληλος υποδοχής, Καθαριστής)
* Υπάλληλος υποδοχής
* Καθαριστής

### Περιγραφές περιπτώσεων χρήσης

#### [ΠΧ1 Ταυτοποίηση](requirements/uc1-identification.md)
Πρωτεύον Actor: Χρήστης

Ο Χρήστης (υπάλληλος υποδοχής, καθαριστής) εάν δεν διαθέτει ήδη λογαριασμό, εγγράφεται εισάγοντας λεπτομερώς τα στοιχεία του, αλλιώς συνδέεται στο σύστημα με το όνομα χρήστη και τον κωδικό πρόσβασής του. Αν τα στοιχεία είναι σωστά, ο χρήστης συνδέεται στην αρχική σελίδα της εφαρμογής, διαφορετικά εμφανίζεται μήνυμα λάθους που τον προτρέπει να εισάγει ξανά τα στοιχεία σύνδεσης.

#### ΠΧ2 [Διαχείριση πελατών](requirements/uc2-client-management.md)
Πρωτεύον Actor: Υπάλληλος υποδοχής

Ο υπάλληλος υποδοχής έχει την δυνατότητα να πραγματοποιεί την εγγραφή ενός πελάτη όταν ο τελευταίος προσκομίσει τα κατάλληλα στοιχεία. Στην περίπτωση που η εγγραφή κριθεί επιτυχής, δημιουργείται μια εγγραφή για τον πελάτη στην βάση δεδομένων του συστήματος. Επίσης, ο υπάλληλος μπορεί κατόπιν να  επεξεργάζεται και να επικαιροποιεί αυτά τα στοιχεία ανακτώντας τα από τη βάση, αλλά και να διαγράφει το λογαριασμό κάποιου πελάτη, αν κρίνεται απαραίτητο.

#### ΠΧ3 [Διαχείριση καθαριστών](requirements/uc3-cleaning-stuff-management.md)
Πρωτεύον Actor: Υπάλληλος υποδοχής

Ο υπάλληλος υποδοχής έχει πρόσβαση στα στοιχεία των καθαριστών, όπως και στα στοιχεία των πελατών. Κατά τον ίδιο τρόπο θα μπορεί να προσθέτει καθαριστές στο αρχείο, να επεξεργάζεται τα στοιχεία τους και να διαγράφει κάποιον καθαριστή όταν χρειαστεί. Όλες αυτές οι ενέργειες συνοδεύονται με τα κατάλληλα μηνύματα λάθους ή επιτυχίας από το σύστημα. 

#### [ΠΧ4 Διαχείριση ραντεβού](requirements/uc4-appointment-management.md)
Πρωτεύον Actor: Υπάλληλος υποδοχής

Όταν ο πελάτης επικοινωνήσει με την επιχείρηση για τον προγραμματισμό ραντεβού καθαρισμού και δώσει τις λεπτομέρειες του ραντεβού στον υπάλληλο υποδοχής (είδος πλυσίματος, ώρα ραντεβού κλπ.), ο τελευταίος καταχωρεί τα στοιχεία αυτά στο σύστημα και το σύστημα τον ενημερώνει τον υπάλληλο για το αν η δήλωση του ραντεβού ήταν επιτυχής (δεν υπάρχουν επικαλύψεις), αλλά και τον καθαριστή ο οποίος θα αναλάβει τον καθαρισμό ότι το ραντεβού προστέθηκε στο πρόγραμμά του. Στη συνέχεια, ο υπάλληλος έχει τη δυνατόητα να επεξεργαστεί τις λεπτομέρειες ενός επερχόμενου ραντεβού, αλλά και να ακυρώσει κάποιο αν το ζητήσει ο αντίστοιχος πελάτης.

#### [ΠΧ5 Υπολογισμός στατιστικών ημέρας](requirements/uc5-statistics-calculation.md)
Actor: Υπάλληλος υποδοχής

Ο υπάλληλος υποδοχής έχει πρόσβαση σε διάφορα στατιστικά στοιχεία που το σύστημα υπολογίζει ανα ημέρα. Όταν επιθυμεί να τα προβάλλει, θα πρέπει το σύστημα να τα επικαιροποιήσει. Τα στοιχεία αυτά έχουν να κάνουν με την ικανοποίηση των πελατών, τον μέσο χρόνο εξυπηρέτησης, το είδος και τον αριθμό των οχημάτων που προσέρχονται στην επιχείρηση, αλλά και οικονομικά στοιχεία, όπως τα μηνιαία έσοδα. Τα στατιστικά αυτά προκύπτουν από ιχνηλάτηση των δεδομένων που υπάρχουν για τα ολοκληρωμένα ραντεβού.

#### [ΠΧ6 Ολοκλήρωση καθαρισμού](requirements/uc6-cleaning-completion.md)
Actor: Καθαριστής

Ο καθαριστής αφού τελειώσει με τον καθαρισμό του οχήματος, ενημερώνει το σύστημα για το γεγονός, και προσθέτει τυχόν σημειώσεις, βλάβες κ.ά. Το σύστημα μετά υπολογίζει το τελικό κόστος πλυσίματος και παράγεται η απόδειξη αγοράς, που εμφανίζεται στον υπάλληλο υποδοχής για να την δώσει στον πελάτη.

## Απαιτήσεις συστήματος

 **Το σύστημα πρέπει:**
		 
 - Να ταυτοποιεί τους χρήστες του.
   
 - Να αποθηκεύει τα δεδομένα που εισάγονται για τα ραντεβού (τύπος καθαρισμού, ώρα ραντεβού κλπ)  σε μία βάση δεδομένων.
   	  
 - Κατά την καταχώριση ραντεβού πρέπει να εξασφαλίζει την αποφυγή
   	   συγκρούσεων τους μεταξύ των πελατών.
 - Να υπολογίζει την τιμή και τον χρόνο στον οποίο θα είναι έτοιμο ένα
   	   όχημα, και να παράγει την σχετική απόδειξη.

 - Να εξάγει στατιστικά στοιχεία προς διεύθυνση επιχείρησης (μέσος χρόνος πλυσίματος, μηνιαία έξοδα κλπ).
	   
 - Να ελέγχει την διαθεσιμότητα των καθαριστών για μελλοντικό πλύσιμο μέσω ραντεβού.


**O υπάλληλος υποδοχής της επιχείρησης θα μπορεί:**
		 
	 
 - Να έχει πρόσβαση σε παλαιότερες συναλλαγές και στοιχεία του των πελατών μέσω του συστήματος.
 
 - Να καταχωρεί το είδος του πλυσίματος που επιθυμεί ο πελάτης και ένα χρονικό διάστημα αναμονής.
 
 - Να μπορεί να διαχειρίζεται το αρχείο καθαριστών.
 
**Ο πελάτης πρέπει:**
  
 - Να μπορεί να κλείσει ραντεβού μέσω της εφαρμογής.
 
 - Να μπορεί να κάνει συνδέεται στην εφαρμογή.
 
 - Να μπορεί να βλέπει παλαιότερες συναλλαγές του με την επιχείρηση.
 
**Ο καθαριστής πρέπει:**
  
 - Να μπορεί να ενημερώσει το σύστημα για την ολοκλήρωση των καθαρισμών που αναλαμβάνει.
