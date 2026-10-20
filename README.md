# Lending Library App 📚

Μια σύγχρονη εφαρμογή διαχείρισης βιβλιοθήκης (Lending Library) ανεπτυγμένη με **Spring Boot** και **Java**. Το project ακολουθεί αρχιτεκτονική πολλαπλών επιπέδων (layered architecture) και περιλαμβάνει ασφάλεια, DTOs, mappers και business logic.

---

## 🚀 Τεχνολογίες
* **Java**
* **Spring Boot**
* **Spring Data JPA / Repositories**
* **Spring Security** (Authentication & Authorization)
* **Maven** (Διαχείριση εξαρτήσεων)

---

## 🏗️ Αρχιτεκτονική & Δομή Project
Το project είναι οργανωμένο στα εξής βασικά packages:
* **`controller`**: Τα REST API endpoints (π.χ. για διαχείριση χρηστών και βιβλίων).
* **`service`**: Η επιχειρησιακή λογική της εφαρμογής (`UserService`, `BookService`).
* **`repository`**: Η επικοινωνία με τη βάση δεδομένων μέσω Spring Data JPA (`UserRepository`, `BookRepository`).
* **`dto`**: Data Transfer Objects για την ασφαλή και καθαρή μεταφορά δεδομένων.
* **`mapper`**: Μετατροπείς (Mappers) για τη μετάβαση από Entity σε DTO και αντίστροφα (`BookMapper`, `UserMapper`).
* **`exception`**: Custom διαχείριση σφαλμάτων της εφαρμογής.
* **`security`**: Ρυθμίσεις ασφαλείας (`SecurityConfig`).

---

## ✨ Κύρια Features (έως τώρα)
* Εγγραφή και διαχείριση χρηστών (`/api/users/register`, κ.λπ.)[cite: 1].
* Αρχικοποιημένη δομή βιβλίων και αποθηκών δεδομένων.
* Ασφάλεια βασισμένη στο Spring Security.
* Καθαρό και κατηγοριοποιημένο ιστορικό Git / GitHub με επαγγελματικά commits.

---

## ⚙️ Οδηγίες Εκκίνησης (Getting Started)

1. **Κλωνοποίηση του Repository:**
   ```bash
   git clone [https://github.com/jroumpekas/lending-library-app.git](https://github.com/jroumpekas/lending-library-app.git)