-- Insert Books
INSERT INTO books (id, title, author, isbn) VALUES
(RANDOM_UUID(), 'Spring Boot in Action', 'Craig Walls', '9781617292545'),
(RANDOM_UUID(), 'Spring Security in Action', 'Laurentiu Spilca', '9781617297731'),
(RANDOM_UUID(), 'Reactive Spring', 'Josh Long', '9781732910225'),
(RANDOM_UUID(), 'Native Image Definitive Guide', 'Oleg Šelajev', '9781492078531');

INSERT INTO speakers (id, name) VALUES
(RANDOM_UUID(), 'Brandon Sanderson'),
(RANDOM_UUID(), 'Uncle Bob'),
(RANDOM_UUID(), 'Ada Lovelace');

--Error: Failed to execute SQL script statement #3 of class path resource [data.sql]: INSERT INTO conferences (id, name, conferenceDate) VALUES (RANDOM_UUID(), 'Spring Boot 101', '2025-03-20')
-- possibly LocalDate issue
--INSERT INTO conferences (id, name, date) VALUES
--(RANDOM_UUID(), 'Spring Boot 101', '2025-03-20');
--(RANDOM_UUID(), 'Spring Boot Is Awesome', '2025-03-22');