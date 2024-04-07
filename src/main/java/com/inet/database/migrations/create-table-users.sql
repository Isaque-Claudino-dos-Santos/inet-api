CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(60) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(430) NOT NULL,
    type ENUM("client", "client-manager", "admin")
)