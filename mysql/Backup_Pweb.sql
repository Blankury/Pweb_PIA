CREATE DATABASE  IF NOT EXISTS `basededatos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `basededatos`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: basededatos
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Musica'),(2,'Tecnologia'),(3,'Gaming'),(4,'Entretenimiento'),(5,'Noticias');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publicacion`
--

DROP TABLE IF EXISTS `publicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publicacion` (
  `id_publicacion` int NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `titulo` varchar(45) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `estado` varchar(15) DEFAULT NULL,
  `id_usuario_publicacion` int NOT NULL,
  `id_categoria_publicacion` int NOT NULL,
  PRIMARY KEY (`id_publicacion`),
  KEY `id_usuario_publicacion` (`id_usuario_publicacion`),
  KEY `id_categoria_publicacion` (`id_categoria_publicacion`),
  CONSTRAINT `publicacion_ibfk_1` FOREIGN KEY (`id_usuario_publicacion`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `publicacion_ibfk_2` FOREIGN KEY (`id_categoria_publicacion`) REFERENCES `categorias` (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicacion`
--

LOCK TABLES `publicacion` WRITE;
/*!40000 ALTER TABLE `publicacion` DISABLE KEYS */;
INSERT INTO `publicacion` VALUES (18,'2022-05-02 07:57:37','PUBLICACION 1','DESCRIPCION ACTUALIZADA VER 2\n-> Inserte info de cosas tecnologicas xd','Activo',1,2),(19,'2022-05-02 07:58:06','Subí un  video sobre...','Vayan y lean la descripcion','Activo',1,4),(20,'2022-05-04 00:05:27','EJEMPLO 3','Me toco describir un...','Activo',20,2),(21,'2022-05-06 03:19:58','Lo ultimo que jugue...','Jugue genshin impact','Activo',1,3),(22,'2022-05-06 04:07:22','Musica aleatoria','LA OTRA VEZ PUSE LA REPRODUCCION AUTOMATICA Y SALIO UNA CANCION MUY PENOSA...','Eliminado',6,1),(23,'2022-05-06 04:12:14','Musica tranquila','Lo-fi tal vez,','Activo',6,1),(24,'2022-05-06 04:23:52','Programacion Web','Ta complicao','Activo',1,2),(25,'2022-05-06 04:27:08','Graficas computacionales 2','SI SE HIZOOOO XD','Activo',19,2),(26,'2022-05-06 04:27:33','La tecnologia en la vida actual','Internet','Eliminado',1,2),(27,'2022-05-06 04:27:53','Musica de los 80s','Gun\'s n roses','Activo',1,1),(28,'2022-05-06 04:30:03','Remixes, qué opinan de ellos? ','Pueden ser buenos o muy malos','Activo',20,1),(29,'2022-05-06 04:30:28','prueba','para llenar','Activo',1,1),(30,'2022-05-06 04:33:09','La musica actual','Los generos mas escuchados este ultimo año son: te quedaras con la duda, no estoy al tanto jsjsjds','Activo',1,1),(31,'2022-05-06 04:49:01','Soundtrack de videojeugos','Son god','Activo',1,1),(52,'2022-05-24 05:41:25','NOTICIA SOBRE LA PAGINA','Dicen que la proxima actualizacion de la pagina no será posible','Activo',6,5),(53,'2022-05-24 05:42:46','NOTICIA 2','Proximamente las publicaciones mostraran el icono de el usuario que publico','Activo',6,5),(54,'2022-05-25 05:42:06','Nuevo celular - inserte marca -','El nuevo celular cuenta con una camara... etc.','Activo',21,2),(55,'2022-05-25 08:28:10','Estaba escuchando sorry de CNBLUE','Muy buenaaaa','Activo',21,1),(56,'2022-05-26 16:38:12','Megaman x Dive','Odio el gachapon de x dive, cambia muy rapido.','Activo',22,3),(57,'2022-05-26 16:39:26','NOTICIA DE ULTIMA','AAAAAAA','Activo',22,5),(58,'2022-05-27 01:06:43','Publicacion prueba','Dscripcion','Eliminado',6,3);
/*!40000 ALTER TABLE `publicacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  `usuario` varchar(15) NOT NULL,
  `imagen` varchar(100) NOT NULL,
  `nacimiento` varchar(25) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `usuario` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Rodrigo','Rodriguez','rr@gmai.com','AAaa123!','rrz1','FotosImg/1653466546996.jpg','1999-10-02','2022-03-27 03:15:51'),(6,'Enrique','Lopez','a@gmail.com','AAaa123!','KIKE1','FotosImg/1653467489823.png','2002-03-08','2022-03-31 22:26:36'),(19,'Angel','Contreras','Guada@gmail.com','AAaa123!','GD1','FotosImg/1652142426843.png','2001-11-11','2022-05-02 05:36:14'),(20,'Cassandra','Pinia','Mari2@gmail.com','AAaa123!','Cass','FotosImg/1653609773005.jpg','1977-05-02','2022-05-10 00:27:06'),(21,'Carolina','Gutierrez','Car@gmail.com','AAaa123!','Caro1','FotosImg/1653582235408.png','2013-06-11','2022-05-24 08:20:23'),(22,'Gerardo','Montemayor','mMayor@gmail.com','AAaa123!','mMayor','FotosImg/1653582543154.png','1994-06-29','2022-05-26 16:29:03'),(23,'Antonio','Lopez  Trujillo','AL@gmail.com','AAaa123!','AL1','FotosImg/1653613503561.jfif','2002-02-12','2022-05-27 01:05:03');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'basededatos'
--

--
-- Dumping routines for database 'basededatos'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-27 10:36:57
