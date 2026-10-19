-- 1. Πίνακας USERS
CREATE TABLE users (
    id BINARY(16) NOT NULL PRIMARY KEY, -- Αν είσαι σε Postgres βάλε: id UUID NOT NULL PRIMARY KEY
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- 2. Πίνακας BOOKS (Κατάλογος)
CREATE TABLE books (
    id BINARY(16) NOT NULL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    category VARCHAR(100),
    description TEXT
);

-- 3. Πίνακας BOOK_COPIES (Φυσικά Αντίτυπα)
CREATE TABLE book_copies (
    id BINARY(16) NOT NULL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    book_id BINARY(16) NOT NULL,
    barcode VARCHAR(50) NOT NULL UNIQUE,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_book_copy_book FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

-- 4. Πίνακας RESERVATIONS (Κρατήσεις / Δανεισμοί)
CREATE TABLE reservations (
    id BINARY(16) NOT NULL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    user_id BINARY(16) NOT NULL,
    book_copy_id BINARY(16) NOT NULL,
    reserved_at TIMESTAMP NOT NULL,
    due_date TIMESTAMP NOT NULL,
    returned_at TIMESTAMP NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_reservation_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_reservation_copy FOREIGN KEY (book_copy_id) REFERENCES book_copies(id) ON DELETE CASCADE
);