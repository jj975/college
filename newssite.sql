-- Створення бази даних
CREATE DATABASE IF NOT EXISTS NewsDatabase;

-- Використання створеної бази даних
USE NewsDatabase;

-- Створення таблиці News
CREATE TABLE IF NOT EXISTS News (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(255)
);

-- Створення таблиці Subscriptions
CREATE TABLE IF NOT EXISTS Subscriptions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL
);

-- Додавання даних до таблиці News
INSERT INTO News (title, description, image_url) VALUES
('New version of Python programming language to be released in March', 'A new version of Python is scheduled for release in March with numerous new features and improvements.', 'https://ioflood.com/blog/wp-content/uploads/2023/08/Digital-image-showing-a-Python-environment-with-the-process-of-updating-Python-focusing-on-upgrading-the-Python-version-300x300.jpg.webp'),
('Release date announced for the new version of JavaScript', 'Developers have announced that the new version of JavaScript will be released in April this year with significant changes to the ECMAScript standard.', 'https://store-images.s-microsoft.com/image/apps.26747.14287819761523024.ce8fba94-2426-4aa0-8617-d034156fb5e2.1d384ea6-eb3c-4eea-b7ae-f206ea061b2f'),
('New features in Java programming language', 'Java 17 has been released with new features, including productivity enhancements and expanded functionality.', 'https://ioflood.com/blog/wp-content/uploads/2023/10/Streamlined-display-of-Java-programming-keywords-300x300.jpg.webp'),
('Announcement of the new version of Ruby programming language', 'Developers have announced an expedited development cycle for the new version of Ruby with many new features and improvements.', 'https://www.thoughtco.com/thmb/PZhLV9sAIDN_jt4j6FjhLHrSyWI=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/2000px-Ruby-logo-R.svg-56a811b75f9b58b7d0f05e83.jpg'),
('Development begins on Go 2.0 programming language', 'The development team has announced the start of a project to develop a new version of the Go programming language with significant changes.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Go_Logo_Blue.svg/1200px-Go_Logo_Blue.svg.png'),
('New version of PHP programming language released', 'PHP 8.1 has been released with a number of important changes and improvements, including support for new features and performance optimization.', 'https://securitygladiators.com/wp-content/uploads/40.PHP-Programming-Language-Image-3-300x300.jpg');
