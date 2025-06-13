DROP USER IF EXISTS 'adminAkihabara'@'localhost';

CREATE USER 'adminAkihabara'@'localhost' IDENTIFIED BY 'campusfp';

DROP DATABASE IF EXISTS akihabara_db;
CREATE DATABASE akihabara_db;

GRANT ALL PRIVILEGES ON akihabara_db.* TO 'adminAkihabara'@'localhost';
FLUSH PRIVILEGES;

USE akihabara_db;

CREATE TABLE productos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(100),
    precio DECIMAL(10,2),
    stock INT
);

CREATE TABLE clientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    fecha_registro DATE DEFAULT (CURDATE())
);

INSERT INTO productos (nombre, categoria, precio, stock) VALUES
('Figura Goku', 'Figuras', 25.99, 10),
('Camiseta Naruto', 'Ropa', 15.50, 5),
('Manga One Piece Vol.1', 'Manga', 9.99, 20),
('Llavero Totoro', 'Llavero', 4.99, 50),
('Póster Attack on Titan', 'Póster', 6.75, 30),
('Videojuego Dragon Ball Z', 'Videojuego', 39.99, 8),
('Sudadera One Punch Man', 'Ropa', 29.95, 12),
('Figura Naruto', 'Figura', 27.50, 7),
('Manga Death Note Vol.1', 'Manga', 8.50, 15),
('Peluca de Gintoki', 'Otro', 18.00, 3);

INSERT INTO clientes (nombre, email, telefono) VALUES 
('Ana López', 'ana.lopez@example.com', '555-1234'),
('Carlos Gómez', 'carlos.gomez@example.com', '555-5678'),
('Lucía Martínez', 'lucia.martinez@example.com', '555-9012'),
('Pedro Fernández', 'pedro.fernandez@example.com', '555-3456'),
('Sofía Ramírez', 'sofia.ramirez@example.com', '555-7890');

SELECT * FROM productos;
SELECT * FROM clientes;

