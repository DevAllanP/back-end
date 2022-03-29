/*drop table if exists*/
drop table if exists t_utilisateurs_ut ;
drop table if exists t_tag_tag  ;
drop table if exists t_roles_rol ;
drop table if exists t_commande_cmd ;
drop table if exists t_adresse_adr ;
drop table if exists t_status_commande_stc  ;
drop table if exists t_frais_frais  ;
drop table if exists t_ligne_commande_lc  ;
drop table if exists t_produit_prd  ;
drop table if exists t_type_produit_tp  ;

/************************************************************************************************/
/*Table Role*/
create table if not exists t_roles_rol(
   rol_id int not null auto_increment,
   rol_label VARCHAR(50),
   rol_desactivated BOOLEAN,
   PRIMARY KEY(rol_id)   
);
insert into t_roles_rol (rol_label, rol_desactivated) values ('admin', false);
insert into t_roles_rol (rol_label, rol_desactivated) values ('commercial', false);
insert into t_roles_rol (rol_label, rol_desactivated) values ('client', false);

select * from t_roles_rol ;

/************************************************************************************************/
/*Table Adresse*/
create table if not exists t_adresse_adr(
   adr_id int not null auto_increment,
   adr_numero int,
   adr_rue VARCHAR(150),
   adr_ville VARCHAR(150),
   adr_cp VARCHAR(50),
   adr_desactivated BOOLEAN,
   PRIMARY KEY(adr_id)   
);
insert into t_adresse_adr (adr_numero,
adr_rue,
adr_ville,
adr_cp,
adr_desactivated)
values (255, 'Nationale','Lille','59000', false);
insert into t_adresse_adr (adr_numero,
adr_rue,
adr_ville,
adr_cp,
adr_desactivated)
values (14, 'lannoy','Fives','59000', false);
insert into t_adresse_adr (adr_numero,
adr_rue,
adr_ville,
adr_cp,
adr_desactivated)
values (23, 'Luxembourg','Roubaix','59100', false);
insert into t_adresse_adr (adr_numero,
adr_rue,
adr_ville,
adr_cp,
adr_desactivated)
values (33, 'trebert','Wasquehal','59290', false);
insert into t_adresse_adr (adr_numero,
adr_rue,
adr_ville,
adr_cp,
adr_desactivated)
values (17, 'Opera','Paris','75000', false);

select * from t_adresse_adr ;

/************************************************************************************************/
/*Table Utilisateur*/
CREATE TABLE IF NOT exists t_utilisateurs_ut(
   ut_id int not null auto_increment,
   ut_nom VARCHAR(50),
   ut_prenom VARCHAR(50),
   ut_email VARCHAR(100),
   ut_password VARCHAR(50),
   ut_date_naissance DATE,
   ut_desactivated BOOLEAN,
   ut_rol_id int,
   ut_adress_id int unique,/*relation1-1 entre utilisateur et adresse*/
   PRIMARY KEY(ut_id),
   foreign key(ut_rol_id) references t_roles_rol (rol_id),
   foreign key(ut_adress_id) references t_adresse_adr (adr_id)
);

insert into t_utilisateurs_ut (ut_nom, ut_prenom, ut_email, ut_password, ut_date_naissance, ut_desactivated,ut_rol_id, ut_adress_id ) 
values ('Rachid', 'SOUKANE', 'skn@gmail.com', 'asterix', '2022-10-05', false, 1,1);
insert into t_utilisateurs_ut (ut_nom, ut_prenom, ut_email, ut_password, ut_date_naissance, ut_desactivated, ut_rol_id, ut_adress_id) 
values ('Samir', 'Ardj', 'ardj@gmail.com', 'obelix', '1983-07-08', false, 2,2);
insert into t_utilisateurs_ut (ut_nom, ut_prenom, ut_email, ut_password, ut_date_naissance, ut_desactivated, ut_rol_id, ut_adress_id) 
values ('JoeStar', 'Zeppelin', 'jojo@gmail.com', 'theWorld', '1980-10-23', true, 3,3);
insert into t_utilisateurs_ut (ut_nom, ut_prenom, ut_email, ut_password, ut_date_naissance, ut_desactivated, ut_rol_id, ut_adress_id) 
values ('Charles', 'Boulanger', 'blgcharles07@gmail.com', 'motdepasse', '1999-12-20', false, 3,4);
insert into t_utilisateurs_ut (ut_nom, ut_prenom, ut_email, ut_password, ut_date_naissance, ut_desactivated, ut_rol_id, ut_adress_id) 
values ('Joe', 'Snowden', 'snowjoe@gmail.com', 'alcatraz', '1987-06-14', false, 3,5);

select * from t_utilisateurs_ut ;

/************************************************************************************************/
/*Table Frais*/
CREATE TABLE IF NOT exists t_frais_frais(
   frais_id int not null auto_increment,
   frais_label VARCHAR(50),
   frais_montant float(50),
   PRIMARY KEY(frais_id)
);
insert into t_frais_frais (frais_label, frais_montant) 
values ('point retrait', 2.50 );
insert into t_frais_frais (frais_label, frais_montant) 
values ('point presse', 0.00 );
insert into t_frais_frais (frais_label, frais_montant) 
values ('livraison gros colis', 45.25 );

select * from t_frais_frais ;

/************************************************************************************************/
/*Table Status de la commande*/
CREATE TABLE IF NOT exists t_status_commande_stc(
   stc_id int not null auto_increment,
   stc_libelle VARCHAR(50),
   PRIMARY KEY(stc_id)
);
insert into t_status_commande_stc (stc_libelle) values ('commande');
insert into t_status_commande_stc (stc_libelle) values ('en cours de livraison');
insert into t_status_commande_stc (stc_libelle) values ('livre');
insert into t_status_commande_stc (stc_libelle) values ('annule');
insert into t_status_commande_stc (stc_libelle) values ('litige');
insert into t_status_commande_stc (stc_libelle) values ('demande de remboursement');
insert into t_status_commande_stc (stc_libelle) values ('rembourse');

select * from t_status_commande_stc ;


/************************************************************************************************/
/*Table Type de produit*/
CREATE TABLE IF NOT exists t_type_produit_tp(
   tp_id int not null auto_increment,
   tp_label VARCHAR (50),
   PRIMARY KEY(tp_id)
);
insert into t_type_produit_tp (tp_label) values ('Disque Dur');
insert into t_type_produit_tp (tp_label) values ('PC fixe');
insert into t_type_produit_tp (tp_label) values ('Ordinateur Portable');
insert into t_type_produit_tp (tp_label) values ('Imprimante');

select * from t_type_produit_tp ;

/************************************************************************************************/
/*Table Produit*/
CREATE TABLE IF NOT exists t_produit_prd(
   prd_id int not null auto_increment,
   prd_nom VARCHAR (50),
   prd_description VARCHAR (50),
   prd_prix FLOAT (50),
   prd_tp_id int,
   PRIMARY KEY(prd_id),
   foreign key(prd_tp_id) references t_type_produit_tp (tp_id)
);
insert into t_produit_prd (prd_nom,prd_description, prd_prix, prd_tp_id ) 
values ('HP2355', 'Imprimante - scanner couleur', 69.45, 4);
insert into t_produit_prd (prd_nom,prd_description, prd_prix, prd_tp_id ) 
values ('Asus', 'Pc Gaming', 1569.499, 2);
insert into t_produit_prd (prd_nom,prd_description, prd_prix, prd_tp_id ) 
values ('HP Sprout', 'ordinateur de bureau tout en un', 2169.27, 3);
insert into t_produit_prd (prd_nom,prd_description, prd_prix, prd_tp_id ) 
values ('XL301', 'Cartouche XL pour imprimante Epson', 35.45, 4);

select * from t_produit_prd ;

/************************************************************************************************/
/*Table Ligne de commande*/
CREATE TABLE IF NOT exists t_ligne_commande_lc(
   lc_id int not null auto_increment, 
   lc_qte int(50),
   lc_prd_id int,
   PRIMARY KEY(lc_id),
   foreign key(lc_prd_id) references t_produit_prd (prd_id)
);
insert into t_ligne_commande_lc (lc_qte, lc_prd_id) values (15,2);
insert into t_ligne_commande_lc (lc_qte, lc_prd_id) values (1,1);
insert into t_ligne_commande_lc (lc_qte, lc_prd_id) values (5,3);
insert into t_ligne_commande_lc (lc_qte,lc_prd_id) values (5,4);

select * from t_ligne_commande_lc ;

/************************************************************************************************/
/*Table Tag*/
CREATE TABLE IF NOT exists t_tag_tag(
   tag_id int not null auto_increment,
   tag_label VARCHAR (50),
   PRIMARY KEY(tag_id)
);
insert into t_tag_tag (tag_label) values ('espace de stockage');
insert into t_tag_tag (tag_label) values ('gaming');
insert into t_tag_tag (tag_label) values ('bureautique');
insert into t_tag_tag (tag_label) values ('consommable');

select * from t_tag_tag ;



/************************************************************************************************/
/*Table Commande*/
CREATE TABLE IF NOT exists t_commande_cmd(
   cmd_id int not null auto_increment,
   cmd_date_commande DATE,
   cmd_date_envoi DATE,
   cmd_date_livraison DATE,
   cmd_adr_id int,
   cmd_frais_id int,
   cmd_stc_id int,
   cmd_lc_id int,   
   PRIMARY KEY(cmd_id),
   foreign key(cmd_adr_id) references t_adresse_adr (adr_id),
   foreign key(cmd_frais_id) references t_frais_frais (frais_id),
   foreign key(cmd_stc_id) references t_status_commande_stc (stc_id),
   foreign key(cmd_lc_id) references t_ligne_commande_lc (lc_id)
);
insert into t_commande_cmd (cmd_date_commande, cmd_date_envoi, cmd_date_livraison, cmd_adr_id, cmd_frais_id, cmd_stc_id, cmd_lc_id) 
values ('2021-01-15', '2021-01-17', '2021-01-22',1,3,3,1);
insert into t_commande_cmd (cmd_date_commande, cmd_date_envoi, cmd_date_livraison, cmd_adr_id, cmd_frais_id, cmd_stc_id, cmd_lc_id) 
values ('2021-01-15', '2021-01-18', '2021-01-24',1,3,3,2);
insert into t_commande_cmd (cmd_date_commande, cmd_date_envoi, cmd_date_livraison, cmd_adr_id, cmd_frais_id, cmd_stc_id, cmd_lc_id) 
values ('2021-01-24', '2021-01-28', '2021-02-24',2,1,3,2);


select * from t_commande_cmd ;

select * from t_roles_rol ;
select * from t_adresse_adr ;
select * from t_utilisateurs_ut ;
select * from t_frais_frais ;
select * from t_status_commande_stc ;
select * from t_type_produit_tp ;
select * from t_produit_prd ;
select * from t_ligne_commande_lc ;
select * from t_tag_tag ;
select * from t_commande_cmd ;

