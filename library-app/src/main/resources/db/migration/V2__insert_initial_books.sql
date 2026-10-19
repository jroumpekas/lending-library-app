-- Εισαγωγή αρχικών βιβλίων στη βάση
-- (Χρησιμοποιούμε UUIDs για τα IDs και timestamps για τα created_at / updated_at)

INSERT INTO books (id, created_at, updated_at, title, author, isbn, category, description)
VALUES
(UUID_TO_BIN(UUID()), NOW(), NOW(), 'The Lord of the Rings', 'J.R.R. Tolkien', '978-0618640157', 'Fantasy', 'An epic high-fantasy novel.'),
(UUID_TO_BIN(UUID()), NOW(), NOW(), 'Clean Code', 'Robert C. Martin', '978-0132350884', 'Programming', 'A Handbook of Agile Software Craftsmanship.'),
(UUID_TO_BIN(UUID()), NOW(), NOW(), '1984', 'George Orwell', '978-0451524935', 'Dystopian', 'A dystopian social science fiction novel.');