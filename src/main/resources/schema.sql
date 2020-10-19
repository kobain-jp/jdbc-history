CREATE TABLE IF NOT Exists book(book_id SERIAL, isbn Long, title NVARCHAR(50), author NVARCHAR(50), release_date DATE);

CREATE TABLE IF NOT Exists user(user_id SERIAL, user_name NVARCHAR(50), birthday Date);
