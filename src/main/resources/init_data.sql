INSERT INTO t_role_rol (rol_label) VALUES ('ROLE_CLIENT');
INSERT INTO t_role_rol (rol_label) VALUES ('ROLE_COMMERCIAL');
INSERT INTO t_role_rol (rol_label) VALUES ('ROLE_ADMIN');

INSERT INTO t_utilisateur_user (user_naissance, user_disable, user_email, user_nom, user_password, user_prenom, adresse_entity_adr_id, role_rol_id) VALUES
('1990-01-16', 0, 'leclient@waoo.fr', 'Dupont', '$2a$10$c3lHG01xMLTDG9RPfa2x/OcVDCNtpCRxcbDrvQT5hdVoXnRxqmr9S', 'Jean', NULL, 1),
('1990-02-24', 0, 'levendeur@waoo.fr', 'Doe', '$2a$10$PI0IzfIVuu81NSO8uOizlOolDt85K/TZJAxAlqtp8O7Pmo83ZpbmO', 'John', NULL, 2),
('1990-12-25', 0, 'ladmin@waoo.fr', 'Anderson', '$2a$10$MvWql1LRiIoW26boDnjKB.OPY.o3s.tuFEbeaOVeDyWTng/1MH2ii', 'Neo', NULL, 3);

INSERT INTO t_tag_tag (tag_label) VALUES ('gamer');
INSERT INTO t_tag_tag (tag_label) VALUES ('amd');
INSERT INTO t_tag_tag (tag_label) VALUES ('nvidia');
INSERT INTO t_tag_tag (tag_label) VALUES ('hexacore');
INSERT INTO t_tag_tag (tag_label) VALUES ('rtx3060');
INSERT INTO t_tag_tag (tag_label) VALUES ('pc');
INSERT INTO t_tag_tag (tag_label) VALUES ('windows');
INSERT INTO t_tag_tag (tag_label) VALUES ('intel');
INSERT INTO t_tag_tag (tag_label) VALUES ('windows11');
INSERT INTO t_tag_tag (tag_label) VALUES ('octocore');
INSERT INTO t_tag_tag (tag_label) VALUES ('ryzen7');
INSERT INTO t_tag_tag (tag_label) VALUES ('rtx3070ti');
INSERT INTO t_tag_tag (tag_label) VALUES ('quadcore');
INSERT INTO t_tag_tag (tag_label) VALUES ('rtx3050');
INSERT INTO t_tag_tag (tag_label) VALUES ('radeonvega7');
INSERT INTO t_tag_tag (tag_label) VALUES ('ryzen5');
INSERT INTO t_tag_tag (tag_label) VALUES ('miniitx');
INSERT INTO t_tag_tag (tag_label) VALUES ('atx');
INSERT INTO t_tag_tag (tag_label) VALUES ('usb3');
INSERT INTO t_tag_tag (tag_label) VALUES ('tour');
INSERT INTO t_tag_tag (tag_label) VALUES ('boitier');
INSERT INTO t_tag_tag (tag_label) VALUES ('matx');
INSERT INTO t_tag_tag (tag_label) VALUES ('ssd');
INSERT INTO t_tag_tag (tag_label) VALUES ('disquedur');
INSERT INTO t_tag_tag (tag_label) VALUES ('adaptateur');
INSERT INTO t_tag_tag (tag_label) VALUES ('pciexpress');
INSERT INTO t_tag_tag (tag_label) VALUES ('carte');
INSERT INTO t_tag_tag (tag_label) VALUES ('ethernet');
INSERT INTO t_tag_tag (tag_label) VALUES ('tplink');
INSERT INTO t_tag_tag (tag_label) VALUES ('corsair');
INSERT INTO t_tag_tag (tag_label) VALUES ('alimentation');
INSERT INTO t_tag_tag (tag_label) VALUES ('dissipateurthermique');
INSERT INTO t_tag_tag (tag_label) VALUES ('bequiet');
INSERT INTO t_tag_tag (tag_label) VALUES ('dvi');
INSERT INTO t_tag_tag (tag_label) VALUES ('displayport');
INSERT INTO t_tag_tag (tag_label) VALUES ('ecran');
INSERT INTO t_tag_tag (tag_label) VALUES ('asus');
INSERT INTO t_tag_tag (tag_label) VALUES ('fullhd');
INSERT INTO t_tag_tag (tag_label) VALUES ('moniteur');
INSERT INTO t_tag_tag (tag_label) VALUES ('adaptivesync');
INSERT INTO t_tag_tag (tag_label) VALUES ('hdmi');
INSERT INTO t_tag_tag (tag_label) VALUES ('chromecast');
INSERT INTO t_tag_tag (tag_label) VALUES ('google');
INSERT INTO t_tag_tag (tag_label) VALUES ('epson');
INSERT INTO t_tag_tag (tag_label) VALUES ('cartouche');
INSERT INTO t_tag_tag (tag_label) VALUES ('imprimante');
INSERT INTO t_tag_tag (tag_label) VALUES ('encre');
INSERT INTO t_tag_tag (tag_label) VALUES ('cable');
INSERT INTO t_tag_tag (tag_label) VALUES ('clavier');
INSERT INTO t_tag_tag (tag_label) VALUES ('cherry');
INSERT INTO t_tag_tag (tag_label) VALUES ('filaire');
INSERT INTO t_tag_tag (tag_label) VALUES ('usb');
INSERT INTO t_tag_tag (tag_label) VALUES ('bluetooth');
INSERT INTO t_tag_tag (tag_label) VALUES ('synology');
INSERT INTO t_tag_tag (tag_label) VALUES ('serveur');
INSERT INTO t_tag_tag (tag_label) VALUES ('nas');
INSERT INTO t_tag_tag (tag_label) VALUES ('realtek');
INSERT INTO t_tag_tag (tag_label) VALUES ('stockage');
INSERT INTO t_tag_tag (tag_label) VALUES ('wifi5');
INSERT INTO t_tag_tag (tag_label) VALUES ('dlink');
INSERT INTO t_tag_tag (tag_label) VALUES ('gigabit');
INSERT INTO t_tag_tag (tag_label) VALUES ('switch');

INSERT INTO t_typeproduit_tprd (tprd_label)
VALUES 									 ('Ordinateurs'),
                                           ('Composants'),
                                           ('Périphériques'),
                                           ('Réseaux');
INSERT INTO t_frais_frs (frs_label,frs_montant)
VALUES ('colis >45 Kg',45),
       ('montant > 100€',0.0),
       ('Distance > 100 Km',25.47),
       ('participation e-recyclage',1.25),
       ('garantie 5 ans',99.25);

INSERT INTO t_statuscommande_stcmd (stcmd_label)
VALUES ('commandé'),
       ('annulé'),
       ('envoyé'),
       ('en cours de livraison'),
       ('livré'),
       ('demande de retour'),
       ('demande de retour accepté'),
       ('demande de retour refusé'),
       ('retourné');



INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Intel Core i5-11600K Hexa Core (3.9 GHz) - 16 Go DDR4 - SSD NVMe 500 Go - 1 To - GeForce RTX 3060 - WiFi - Windows 11',0,'PC Gamer BATTLE ROYALE 2 - Avec Windows',1749.99,1);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Intel Core i5-11400 Hexa Core (2.6 GHz) - 16 Go DDR4 - SSD NVMe 500 Go - 1 To - GeForce RTX 3060 - WiFi - Windows 11',0,'PC Gamer BOHRIUM - Avec Windows',1649.99,1);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('AMD Ryzen 7 5800X Octo Core (3.8 GHz) - 32 Go DDR4 - SSD NVMe 1 To - 2 To - GeForce RTX 3070 Ti - WiFi',0,'PC Gamer HYDROGENE Ti Edition - Sans Windows',2499.99,1);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Intel Core i3-10105F Quad Core (3.7 GHz) - 16 Go DDR4 - SSD 240 Go - 1 To - GeForce RTX 3050 - WiFi - Windows 11',0,'PC Gamer MOBA RTX - Avec Windows',1099.99,1);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('AMD Ryzen 5 5600G Hexa Core (3.9 GHz) - 16 Go DDR4 - SSD 960 Go - Radeon Vega 7 - WiFi',0,'PC Gamer RHODIUM - Sans Windows',699.99,1);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Boitier PC Moyen Tour - ATX / mATX / Mini-ITX - USB 3.0',0,'Fractal Design Meshify C - Noir',82.49,2);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Grâce à cet adaptateur 2,5\" vers 3,5\" pour SSD et disques durs portables, vous pourrez installer votre solution de stockage compacte au niveau dun emplacement pour disque dur traditionnel 3,5\".',0,'Adaptateur 3.5\" pour 1 HDD/SSD 2.5\" - Noir',5.99,2);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Carte Ethernet 10 Gigabits - PCI-Express',0,'TP-Link TX401',99.99,2);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Alimentation PC Certifiée 80+ Bronze - Modulaire',0,'Corsair CX650F RGB (Noir) - 650W',69.99,2);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Dissipateur thermique pour SSD M.2 2280 - Caloduc intégré',0,'Be Quiet! MC1 Pro',17.99,2);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Moniteur 24\" TN 165 Hz - Full HD - 0.5 ms - DisplayPort / HDMI / DVI - Pied réglable + Rotation - HP intégrés - Bords fins - Compatible G-Sync',0,'Asus VG248QG Adaptive Sync',199.99,3);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Diffusez du contenu sur votre téléviseur à partir de votre Smartphone ou Tablette',0,'Google Chromecast V3',39.99,3);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Cartouche dencre haute capacité noire (500 pages à 5%)',0,'Epson Stylo à Plume 16 XL Noir',23.99,3);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Câble DisplayPort Mâle / Mâle - Verrouillable',0,'Câble DisplayPort 1.2 - 2 mètres - Nedis',9.99,3);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Clavier filaire',0,'Cherry KC 1000 - Noir (AZERTY)',15.99,3);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Adaptateur USB Bluetooth 5.0',0,'Asus USB-BT500',19.99,4);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Serveur de stockage NAS - 2 baies - Hot Swap - Realtek RTD1296 1.4 GHz Quad-Core - 2 Go - USB 3.0',0,'Synology DS218',273.99,4);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Wi-Fi 5',0,'TP-Link RE200',32.99,4);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Switch Gigabit - 5 ports',0,'D-Link DGS-105',22.99,4);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Disque dur 3.5\" - 7200 tpm - 256 Mo - SATA III - Bulk',0,'Synology HAT5300 12 To',499.99,4);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('La puce M1 confère au MacBook Pro une vitesse et une puissance vertigineuses. Le tout, avec une efficacité énergétique stupéfiante',0,'Apple MacBook Pro M1 13.3\" Silver 16GB/512GB (MYDC2FN/A-16GB)',1899.95,1);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('Le PC Gamer MSI MEG Aegis Ti5 est novateur sur bien des aspects. Il dispose également de composants ultra-performants',0,'ASUS ROG STRIX GeForce RTX 3070 Ti O8G GAMING',3599.95,1);
INSERT INTO t_produit_prd (prd_description,prd_disable,prd_nom,prd_prix,id_type_produit) VALUES ('La carte graphique ASUS GeForce ROG STRIX RTX 3070 Ti O8G GAMING embarque 8 Go de mémoire vidéo de nouvelle génération GDDR6X',0,'ASUS ROG STRIX GeForce RTX 3070 Ti O8G GAMING',1199.95,2);



INSERT INTO t_commande_cmd (cmd_id,cmd_dtcmd,cmd_dtenvoie,cmd_dtlivraison, status_de_commande_stcmd_id, user_user_id)
VALUES (1,'2022-02-05','2022-02-08','2022-02-13',1,1),
       (2,'2022-03-01','2022-03-03','2022-03-20',1,2);


INSERT INTO t_lignecommande_lgc (lgc_id,lgc_qte,produits_prd_id, lgc_prix_unitaire,commande_entity_cmd_id)
VALUES (1,24,1,45.25,1),
       (2,14,1,25.14,1),
       (3,11,2,25.85,2);

INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (1,1);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (1,3);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (1,4);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (1,5);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (1,6);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (1,7);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (1,8);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (1,9);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (2,1);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (2,3);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (2,4);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (2,5);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (2,6);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (2,7);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (2,8);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (2,9);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (3,1);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (3,2);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (3,6);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (3,10);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (3,11);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (3,12);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (4,1);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (4,6);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (4,7);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (4,8);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (4,9);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (4,13);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (4,14);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (5,1);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (5,2);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (5,4);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (5,6);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (5,15);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (5,16);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (6,17);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (6,18);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (6,19);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (6,20);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (6,21);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (6,22);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (7,23);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (7,24);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (7,25);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (8,26);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (8,27);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (8,28);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (8,29);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (9,30);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (9,31);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (10,23);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (10,32);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (10,33);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (11,34);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (11,35);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (11,36);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (11,37);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (11,38);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (11,39);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (11,40);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (11,41);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (12,42);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (12,43);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (13,44);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (13,45);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (13,46);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (13,47);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (14,35);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (14,48);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (15,49);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (15,50);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (15,51);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (16,25);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (16,37);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (16,52);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (16,53);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (17,19);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (17,24);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (17,54);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (17,55);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (17,56);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (17,57);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (17,58);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (18,29);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (18,59);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (19,60);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (19,61);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (19,62);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (20,24);
INSERT INTO t_tag_tag_list_produit (list_produit_prd_id, tag_id) VALUES (20,54);

INSERT INTO ecommerce.t_adresse_adr (adr_id,adr_codepostal,adr_num,adr_rue,adr_ville)
VALUES (1,'59100',25,'Luxembourg','roubaix'),
       (2,'59000',64,'Nationale','Lille'),
       (3,'59491',33,'Dupont','Villeneuve d''Ascq');
UPDATE ecommerce.t_utilisateur_user SET adresse_entity_adr_id=1 WHERE user_id=1;
UPDATE ecommerce.t_utilisateur_user SET adresse_entity_adr_id=2 WHERE user_id=2;
UPDATE ecommerce.t_utilisateur_user SET adresse_entity_adr_id=3 WHERE user_id=3;