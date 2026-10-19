package com.lendinglibrary.libraryapp.models.enums;

public enum ReservationStatus {
    ACTIVE,      // Σε ισχύ (το έχει ο χρήστης)
    RETURNED,    // Επιστράφηκε επιτυχώς
    CANCELLED,   // Ακυρώθηκε
    EXPIRED      // Έληξε η προθεσμία χωρίς παραλαβή/επιστροφή
}