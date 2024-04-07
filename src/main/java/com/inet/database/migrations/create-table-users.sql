CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(60),
    email VARCHAR(150),
    password VARCHAR(430),
    type ENUM("client", "client-manager", "admin")
)