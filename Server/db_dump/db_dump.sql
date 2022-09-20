/*
SQLyog Community v13.1.8 (64 bit)
MySQL - 8.0.22 : Database - butikdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`butikdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `butikdb`;

/*Table structure for table `artikl` */

DROP TABLE IF EXISTS `artikl`;

CREATE TABLE `artikl` (
  `SifraArtikla` bigint NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(50) NOT NULL,
  `Opis` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ProdajnaCena` decimal(10,0) NOT NULL,
  `ProdajnaVrednost` decimal(10,0) NOT NULL,
  `Velicina` varchar(20) NOT NULL,
  `Proizvodjac` varchar(50) NOT NULL,
  `KolicinaNaStanju` int NOT NULL,
  PRIMARY KEY (`SifraArtikla`),
  CONSTRAINT `KolicinaNaStanjuVecaIliJednakaNuli` CHECK ((`KolicinaNaStanju` >= 0)),
  CONSTRAINT `ProdajnaCenaVecaOdNule` CHECK ((`ProdajnaCena` > 0)),
  CONSTRAINT `ProdajnaVrednostVecaOdNule` CHECK ((`ProdajnaVrednost` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `artikl` */

insert  into `artikl`(`SifraArtikla`,`Naziv`,`Opis`,`ProdajnaCena`,`ProdajnaVrednost`,`Velicina`,`Proizvodjac`,`KolicinaNaStanju`) values 
(3,'Dzemper','Pantalone opis - test2',2500,1200,'BALARY-test','BALARY-test',6),
(4,'Suknja','Suknja opis',3200,2100,'M','PRS2',2),
(5,'Pantalone','Pantalone opis asdasdasdasdasdasd',2200,1000,'XL','Proizvodjac2',2),
(6,'TEST_ARTIKL','Opis test artikla',12900,9000,'XL','Proizvodjac T',122),
(7,'TEST artikl','Opis test aritkla...',2400,1500,'3','TEST',21),
(8,'Novi artikl','Opis novog artikla PROMENA...',3200,2000,'Test proizvodjac','Test proizvodjac',5),
(9,'Novi artikl','Description novog artiklaa sda asdasdasdads',3400,2000,'M','ProizovdjacX',12),
(10,'Test artikl 3','asdasdadsdassad',3200,1200,'XM','TEST',2);

/*Table structure for table `izvestaj` */

DROP TABLE IF EXISTS `izvestaj`;

CREATE TABLE `izvestaj` (
  `IzvestajID` bigint NOT NULL AUTO_INCREMENT,
  `DatumKreiranja` date NOT NULL,
  `DatumOd` date NOT NULL,
  `DatumDo` date NOT NULL,
  `UkupanPrihod` decimal(10,0) NOT NULL,
  `RadnikJMBG` varchar(13) NOT NULL,
  PRIMARY KEY (`IzvestajID`),
  KEY `RadnikJMBG` (`RadnikJMBG`),
  CONSTRAINT `izvestaj_ibfk_1` FOREIGN KEY (`RadnikJMBG`) REFERENCES `zaposleni` (`JMBG`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `izvestaj` */

insert  into `izvestaj`(`IzvestajID`,`DatumKreiranja`,`DatumOd`,`DatumDo`,`UkupanPrihod`,`RadnikJMBG`) values 
(2,'2016-09-20','2020-12-20','2016-09-20',30200,'2012999889977'),
(3,'2019-09-20','2020-12-20','2019-09-20',41000,'2012999889977'),
(4,'2019-09-20','2020-12-20','2019-09-20',41000,'2012999889977'),
(5,'2020-09-20','2020-12-20','2020-09-20',8800,'2012999889977'),
(6,'2020-09-20','2020-12-20','2020-09-20',41000,'2012999889977');

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `KlijentID` bigint NOT NULL AUTO_INCREMENT,
  `Ime` varchar(50) NOT NULL,
  `Prezime` varchar(50) NOT NULL,
  `Email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`KlijentID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `klijent` */

insert  into `klijent`(`KlijentID`,`Ime`,`Prezime`,`Email`) values 
(1,'Ivan','Ivanovic','ivan.ivanovic@gmail.com'),
(2,'Milan','Mitrovic','mico@gmail.com'),
(3,'Dejan','Nenadovic','deki.deki@gmail.com'),
(4,'Milos','Milovanovic','milos.milovanovic@gmail.com'),
(5,'Milan','Savic','save99@gmail.com'),
(6,'Miroslav','Milosavljevic','miki@gmail.com'),
(7,'Stefan','Stefanovic','stefo2020@gmail.com'),
(8,'Milica','Ivanovic','mica@gmail.com'),
(9,'Deki','Majstor','majstordeki@gmail.com'),
(10,'Milos','Mitrovic','mitra@gmail.com'),
(11,'Milan','Mitrovic','milimili@gmail.com');

/*Table structure for table `prodajnastavka` */

DROP TABLE IF EXISTS `prodajnastavka`;

CREATE TABLE `prodajnastavka` (
  `ProdajnaStavkaID` bigint NOT NULL AUTO_INCREMENT,
  `DatumProdaje` date NOT NULL,
  `Kolicina` int NOT NULL,
  `Iznos` decimal(10,0) NOT NULL,
  `KlijentID` bigint DEFAULT NULL,
  `SifraArtikla` bigint NOT NULL,
  `RadnikJMBG` varchar(13) NOT NULL,
  PRIMARY KEY (`ProdajnaStavkaID`),
  KEY `KlijentID` (`KlijentID`),
  KEY `RadnikJMBG` (`RadnikJMBG`),
  KEY `SifraArtikla` (`SifraArtikla`),
  CONSTRAINT `prodajnastavka_ibfk_2` FOREIGN KEY (`KlijentID`) REFERENCES `klijent` (`KlijentID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `prodajnastavka_ibfk_3` FOREIGN KEY (`RadnikJMBG`) REFERENCES `zaposleni` (`JMBG`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `prodajnastavka_ibfk_4` FOREIGN KEY (`SifraArtikla`) REFERENCES `artikl` (`SifraArtikla`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `IznosVeciOdNule` CHECK ((`Iznos` > 0)),
  CONSTRAINT `KolicinaVecaIliJednakaNuli` CHECK ((`Kolicina` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `prodajnastavka` */

insert  into `prodajnastavka`(`ProdajnaStavkaID`,`DatumProdaje`,`Kolicina`,`Iznos`,`KlijentID`,`SifraArtikla`,`RadnikJMBG`) values 
(1,'2020-12-20',2,4400,3,3,'2012999889977'),
(3,'2016-09-20',2,25800,3,6,'2012999889977'),
(4,'2015-10-20',2,6400,3,4,'2012999889977'),
(5,'2019-09-20',2,4400,2,3,'2012999889977');

/*Table structure for table `stavkaizvestaja` */

DROP TABLE IF EXISTS `stavkaizvestaja`;

CREATE TABLE `stavkaizvestaja` (
  `IzvestajID` bigint NOT NULL,
  `RB` int NOT NULL,
  `PrihodStavke` decimal(10,0) NOT NULL,
  `ProdajnaStavkaID` bigint NOT NULL,
  PRIMARY KEY (`IzvestajID`,`RB`),
  KEY `ProdajnaStavkaID` (`ProdajnaStavkaID`),
  CONSTRAINT `stavkaizvestaja_ibfk_1` FOREIGN KEY (`IzvestajID`) REFERENCES `izvestaj` (`IzvestajID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stavkaizvestaja_ibfk_2` FOREIGN KEY (`ProdajnaStavkaID`) REFERENCES `prodajnastavka` (`ProdajnaStavkaID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `PrihodStavkeVeciOdNula` CHECK ((`PrihodStavke` >= 0)),
  CONSTRAINT `RBIsUnsignedInt` CHECK ((`RB` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `stavkaizvestaja` */

insert  into `stavkaizvestaja`(`IzvestajID`,`RB`,`PrihodStavke`,`ProdajnaStavkaID`) values 
(2,0,4400,1),
(2,1,25800,3),
(3,0,4400,1),
(3,1,25800,3),
(3,2,6400,4),
(3,3,4400,5),
(4,0,4400,1),
(4,1,25800,3),
(4,2,6400,4),
(4,3,4400,5),
(5,0,4400,1),
(5,1,4400,5),
(6,0,4400,1),
(6,1,25800,3),
(6,2,6400,4),
(6,3,4400,5);

/*Table structure for table `zaposleni` */

DROP TABLE IF EXISTS `zaposleni`;

CREATE TABLE `zaposleni` (
  `JMBG` varchar(13) NOT NULL,
  `Ime` varchar(50) NOT NULL,
  `Prezime` varchar(50) NOT NULL,
  `DatumPocetkaRada` date NOT NULL,
  `Plata` decimal(10,0) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  PRIMARY KEY (`JMBG`),
  CONSTRAINT `PlataVecaOdNule` CHECK ((`Plata` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `zaposleni` */

insert  into `zaposleni`(`JMBG`,`Ime`,`Prezime`,`DatumPocetkaRada`,`Plata`,`Username`,`Password`) values 
('2012999889977','Ognjen','Simic','2022-06-07',65000,'ognjen','ognjen'),
('2507989556644','Milan','Mitrovic','2022-04-12',60000,'milan','milan');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
