CREATE DATABASE BaseDeDatos;
USE BaseDeDatos;

CREATE TABLE usuarios (
id_usuario INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nombre VARCHAR(45) NOT NULL, 
apellido VARCHAR(45) NOT NULL,
correo VARCHAR(45) NOT NULL,
contraseña VARCHAR(20) NOT NULL,
usuario VARCHAR(15) NOT NULL UNIQUE,
imagen VARCHAR(100) NOT NULL,
nacimiento VARCHAR(25) NOT NULL,
fecha TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE usuarios (
id_usuario INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nombre VARCHAR(45) NOT NULL, 
apellido VARCHAR(45) NOT NULL,
correo VARCHAR(45) NOT NULL UNIQUE,
contraseña VARCHAR(20) NOT NULL,
usuario VARCHAR(15) NOT NULL UNIQUE,
nacimiento VARCHAR(25) NOT NULL
);



CREATE TABLE categorias (
id_categoria INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nombre VARCHAR(30) NOT NULL
);
INSERT INTO usuarios Select id_publicacion, id_usuario_publicacion, id_categoria_publicacion, fecha, titulo, descripcion, estado FROM publicacion WHERE descripcion LIKE "%descripcion%";

Select p.id_publicacion, u.nombre as id_usuario_publicacion, c.nombre as nombre_categoria_publicacion, c.id_categoria as id_cat, p.fecha, p.titulo, p.descripcion, p.estado  FROM publicacion as p inner join categorias as c inner join usuarios as u  WHERE c.id_categoria = p.id_categoria_publicacion AND u.id_usuario = p.id_usuario_publicacion AND p.estado = 'Activo' AND p.descripcion LIKE "%'descripcion'%" ORDER BY p.id_publicacion ASC;Select p.id_publicacion, u.nombre as id_usuario_publicacion, c.nombre as nombre_categoria_publicacion, c.id_categoria as id_cat, p.fecha, p.titulo, p.descripcion, p.estado  FROM publicacion as p inner join categorias as c inner join usuarios as u  WHERE c.id_categoria = p.id_categoria_publicacion AND u.id_usuario = p.id_usuario_publicacion AND p.estado = 'Activo'
AND p.descripcion LIKE "%descripcion%" ORDER BY p.id_publicacion ASC;


SELECT * from usuarios;
SELECT * from publicacion;
SELECT * from categorias;

CREATE TABLE publicacion (
id_publicacion INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
fecha TIMESTAMP NOT NULL DEFAULT NOW(),
titulo VARCHAR(45),
descripcion VARCHAR(500),
estado VARCHAR(15),
id_usuario_publicacion INT NOT NULL,
FOREIGN KEY (id_usuario_publicacion) REFERENCES usuario (id_usuario),
id_categoria_publicacion INT NOT NULL,
FOREIGN KEY (id_categoria_publicacion) REFERENCES categorias (id_categoria)
);
