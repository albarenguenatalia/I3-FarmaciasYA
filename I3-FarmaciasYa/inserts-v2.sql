SET FOREIGN_KEY_CHECKS=0;

INSERT INTO `farmaciasyadb`.`product` (`idProduct`, `Name`, `Description`) VALUES ('1', 'Aspirina', 'Para el dolor de cabeza');
INSERT INTO `farmaciasyadb`.`product` (`idProduct`, `Name`, `Description`) VALUES ('3', 'Perifar', 'Para cualquier dolor');
INSERT INTO `farmaciasyadb`.`product` (`idProduct`, `Name`, `Description`) VALUES ('2', 'Perfume', 'Rico aroma');
INSERT INTO `farmaciasyadb`.`product` (`idProduct`, `Name`, `Description`) VALUES ('4', 'Shampoo Sedal', 'Alizado perfecto');
INSERT INTO `farmaciasyadb`.`product` (`idProduct`, `Name`, `Description`) VALUES ('5', 'Shampoo Pantene', 'Brillo extremo');
INSERT INTO `farmaciasyadb`.`product` (`idProduct`, `Name`, `Description`) VALUES ('6', 'Actron', 'Alivio al dolor');
INSERT INTO `farmaciasyadb`.`product` (`idProduct`, `Name`, `Description`) VALUES ('7', 'Gasas', 'para heridas');
INSERT INTO `farmaciasyadb`.`product` (`idProduct`, `Name`, `Description`) VALUES ('8', 'Vendas', 'para articulaciones');
INSERT INTO `farmaciasyadb`.`product` (`idProduct`, `Name`, `Description`) VALUES ('9', 'Pantalla Solar', 'para el sol');
 
INSERT INTO `farmaciasyadb`.`drugstore` (`idDrugStore`, `Name`, `Address`, `Telephone`, `Email`) VALUES ('1', 'San Roque 1', 'Minas 2124, Montevideo', '4123 0995', 's1@s.com');
INSERT INTO `farmaciasyadb`.`drugstore` (`idDrugStore`, `Name`, `Address`, `Telephone`, `Email`) VALUES ('2', 'San Roque 2', 'Av. Rivera 1884, Montevideo', '4123 1234', 's2@s.com');
INSERT INTO `farmaciasyadb`.`drugstore` (`idDrugStore`, `Name`, `Address`, `Telephone`, `Email`) VALUES ('3', 'San Roque 3', 'Av. Italia 4333, Montevideo', '4123 5454', 's3@s.com');
INSERT INTO `farmaciasyadb`.`drugstore` (`idDrugStore`, `Name`, `Address`, `Telephone`, `Email`) VALUES ('4', 'San Roque 5', 'Av 18 de Julio 3333, Montevideo', '4123 9393', 's4@s.com');
INSERT INTO `farmaciasyadb`.`drugstore` (`idDrugStore`, `Name`, `Address`, `Telephone`, `Email`) VALUES ('5', 'FarmaShop 1', 'Ruta 8 km 30', '4113 9323', 'f1@f.com');
INSERT INTO `farmaciasyadb`.`drugstore` (`idDrugStore`, `Name`, `Address`, `Telephone`, `Email`) VALUES ('6', 'FarmaShop 2', 'Av. 8 de Octubre 3883, Montevideo', '4113 9393', 'f2@f.com');
INSERT INTO `farmaciasyadb`.`drugstore` (`idDrugStore`, `Name`, `Address`, `Telephone`, `Email`) VALUES ('7', 'San Nicolas', 'Garibaldi 2345, Montevideo', '2435 2314', 'sn@sn.com');
 
UPDATE `farmaciasyadb`.`drugstore` SET `Latitud`='-34.9027683', `Longitud`='-56.1810544' WHERE `idDrugStore`='1';
UPDATE `farmaciasyadb`.`drugstore` SET `Latitud`='-31.5696995', `Longitud`='-55.4708263' WHERE `idDrugStore`='2';
UPDATE `farmaciasyadb`.`drugstore` SET `Latitud`='-34.8870531', `Longitud`='-56.1162977' WHERE `idDrugStore`='3';
UPDATE `farmaciasyadb`.`drugstore` SET `Latitud`='-34.873686', `Longitud`='-56.1384051' WHERE `idDrugStore`='4';
UPDATE `farmaciasyadb`.`drugstore` SET `Latitud`='-34.8818052', `Longitud`='-56.1691785' WHERE `idDrugStore`='5';
UPDATE `farmaciasyadb`.`drugstore` SET `Latitud`='-34.9027683', `Longitud`='-55.4708263' WHERE `idDrugStore`='6';
UPDATE `farmaciasyadb`.`drugstore` SET `Latitud`='-34.8818052', `Longitud`='-55.4708263' WHERE `idDrugStore`='7';
 
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('11', '1', '1', '10');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('12', '1', '2', '20');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('13', '1', '3', '30');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('14', '1', '4', '40');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('15', '1', '5', '50');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('16', '1', '6', '60');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('17', '1', '7', '70');
 
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('21', '2', '1', '10');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('22', '2', '2', '20');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('23', '2', '3', '30');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('24', '2', '4', '40');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('25', '2', '5', '50');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('26', '2', '6', '60');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('27', '2', '7', '70');
 
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('31', '3', '1', '10');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('32', '3', '2', '20');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('33', '3', '3', '30');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('34', '3', '4', '40');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('35', '3', '5', '50');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('36', '3', '6', '60');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('37', '3', '7', '70');
 
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('41', '4', '1', '10');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('42', '4', '2', '20');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('43', '4', '3', '30');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('44', '4', '4', '40');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('45', '4', '5', '50');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('46', '4', '6', '60');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('47', '4', '7', '70');
 
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('51', '5', '1', '10');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('52', '5', '2', '20');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('53', '5', '3', '30');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('54', '5', '4', '40');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('55', '5', '5', '50');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('56', '5', '6', '60');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('57', '5', '7', '70');
 
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('61', '6', '1', '10');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('62', '6', '2', '20');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('63', '6', '3', '30');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('64', '6', '4', '40');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('65', '6', '5', '50');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('66', '6', '6', '60');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('67', '6', '7', '70');
 
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('71', '7', '1', '10');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('72', '7', '2', '20');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('73', '7', '3', '30');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('74', '7', '4', '40');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('75', '7', '5', '50');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('76', '7', '6', '60');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('77', '7', '7', '70');
 
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('81', '8', '1', '10');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('82', '8', '2', '20');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('83', '8', '3', '30');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('84', '8', '4', '40');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('85', '8', '5', '50');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('86', '8', '6', '60');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('87', '8', '7', '70');
 
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('91', '9', '1', '10');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('92', '9', '2', '20');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('93', '9', '3', '30');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('94', '9', '4', '40');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('95', '9', '5', '50');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('96', '9', '6', '60');
INSERT INTO `farmaciasyadb`.`product_drugstore` (`idProduct_DrugStore`, `idProduct`, `idDrugStore`, `Price`) VALUES ('97', '9', '7', '70');
 
 
 
INSERT INTO `farmaciasyadb`.`order` (`idOrder`, `Date`, `Status`, `Total`) VALUES ('1', '2015-10-31 12:35', '1', '1');
INSERT INTO `farmaciasyadb`.`order` (`idOrder`, `Date`, `Status`, `Total`) VALUES ('2', '2015-09-30 11:30', '1', '1');
INSERT INTO `farmaciasyadb`.`order` (`idOrder`, `Date`, `Status`, `Total`) VALUES ('3', '2015-08-19 17:00', '1', '1');
INSERT INTO `farmaciasyadb`.`order` (`idOrder`, `Date`, `Status`, `Total`) VALUES ('4', '2015-11-02 07:30', '1', '1');
INSERT INTO `farmaciasyadb`.`order` (`idOrder`, `Date`, `Status`, `Total`) VALUES ('5', '2015-10-30 14:45', '1', '1');
INSERT INTO `farmaciasyadb`.`order` (`idOrder`, `Date`, `Status`, `Total`) VALUES ('6', '2015-10-28 10:09', '1', '1');
INSERT INTO `farmaciasyadb`.`order` (`idOrder`, `Date`, `Status`, `Total`) VALUES ('7', '2015-10-26 09:00', '1', '1');
INSERT INTO `farmaciasyadb`.`order` (`idOrder`, `Date`, `Status`, `Total`) VALUES ('8', '2015-10-24 07:00', '1', '1');
INSERT INTO `farmaciasyadb`.`order` (`idOrder`, `Date`, `Status`, `Total`) VALUES ('9', '2015-09-30 12:35', '1', '1');
 
 
INSERT INTO `farmaciasyadb`.`order_rate` (`id`, `idOrder`, `idDrugStore`, `rate`) VALUES ('1', '1', '1', '1');
INSERT INTO `farmaciasyadb`.`order_rate` (`id`, `idOrder`, `idDrugStore`, `rate`) VALUES ('2', '2', '2', '2');
INSERT INTO `farmaciasyadb`.`order_rate` (`id`, `idOrder`, `idDrugStore`, `rate`) VALUES ('3', '3', '3', '1');
INSERT INTO `farmaciasyadb`.`order_rate` (`id`, `idOrder`, `idDrugStore`, `rate`) VALUES ('4', '4', '3', '4');
 
INSERT INTO `farmaciasyadb`.`order_rate` (`id`, `idOrder`, `idDrugStore`, `rate`) VALUES ('5', '5', '4', '3');
 
INSERT INTO `farmaciasyadb`.`order_rate` (`id`, `idOrder`, `idDrugStore`, `rate`) VALUES ('6', '6', '5', '3');
INSERT INTO `farmaciasyadb`.`order_rate` (`id`, `idOrder`, `idDrugStore`, `rate`) VALUES ('7', '7', '5', '4');
 
INSERT INTO `farmaciasyadb`.`order_rate` (`id`, `idOrder`, `idDrugStore`, `rate`) VALUES ('8', '8', '6', '4');
INSERT INTO `farmaciasyadb`.`order_rate` (`id`, `idOrder`, `idDrugStore`, `rate`) VALUES ('9', '9', '7', '5');
 
UPDATE `farmaciasyadb`.`order` SET `idUser`='1' WHERE `idOrder`='1';
UPDATE `farmaciasyadb`.`order` SET `idUser`='1' WHERE `idOrder`='2';
UPDATE `farmaciasyadb`.`order` SET `idUser`='1' WHERE `idOrder`='3';
UPDATE `farmaciasyadb`.`order` SET `idUser`='1' WHERE `idOrder`='4';
UPDATE `farmaciasyadb`.`order` SET `idUser`='1' WHERE `idOrder`='5';
UPDATE `farmaciasyadb`.`order` SET `idUser`='1' WHERE `idOrder`='6';
UPDATE `farmaciasyadb`.`order` SET `idUser`='1' WHERE `idOrder`='7';
UPDATE `farmaciasyadb`.`order` SET `idUser`='1' WHERE `idOrder`='8';
UPDATE `farmaciasyadb`.`order` SET `idUser`='1' WHERE `idOrder`='9';


SET FOREIGN_KEY_CHECKS=1;