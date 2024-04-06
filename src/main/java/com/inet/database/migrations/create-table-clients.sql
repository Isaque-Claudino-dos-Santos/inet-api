CREATE TABLE IF NOT EXISTS clients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    systemName VARCHAR(20),
    systemArch VARCHAR(20),
    systemVersion VARCHAR(100)
)