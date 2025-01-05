-- MySQL does not support sequences; use AUTO_INCREMENT instead.

-- Drop tables if they exist
DROP TABLE IF EXISTS `authors`;
DROP TABLE IF EXISTS `books`;

-- Create `authors` table
CREATE TABLE `authors` (
   `id` BIGINT NOT NULL AUTO_INCREMENT, -- Use AUTO_INCREMENT for MySQL
   `age` SMALLINT,
   `description` VARCHAR(512),
   `image` VARCHAR(512),
   `name` VARCHAR(512),
   PRIMARY KEY (`id`) -- MySQL primary key syntax
);

-- Create `books` table
CREATE TABLE `books` (
   `isbn` VARCHAR(19) NOT NULL, -- MySQL primary key for ISBN
   `description` VARCHAR(2048),
   `image` VARCHAR(512),
   `title` VARCHAR(512),
   `author_id` BIGINT,
   PRIMARY KEY (`isbn`),
   FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`) -- MySQL foreign key syntax
);
