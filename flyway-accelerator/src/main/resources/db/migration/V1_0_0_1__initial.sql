CREATE TABLE users (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(50) unique not null,
    status varchar(50) not null default 'ACTIVE'
)