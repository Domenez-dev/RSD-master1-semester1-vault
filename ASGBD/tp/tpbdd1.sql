CREATE DATABASE TP1;
USE TP1;

CREATE TABLE Equipe (
    CodeEquipe VARCHAR(5) PRIMARY KEY,
    NomEquipe VARCHAR(40),
    DirecteurSportif VARCHAR(40),
    DateDeCreation DATETIME,
    SiteWeb VARCHAR(40)
);

CREATE TABLE Pays (
    CodePays VARCHAR(5) PRIMARY KEY,
    NomPays VARCHAR(40)
);

CREATE TABLE Coureur (
    NumeroCoureur INT PRIMARY KEY,
    NomCoureur VARCHAR(40),
    CodeEquipe VARCHAR(10),
    CodePays VARCHAR(5),
    FOREIGN KEY (CodePays) REFERENCES Pays(CodePays),
    FOREIGN KEY (CodeEquipe) REFERENCES Equipe(CodeEquipe)
);

CREATE TABLE TypeEtape (
    CodeType VARCHAR(5) PRIMARY KEY,
    LibelleType VARCHAR(40)
);

CREATE TABLE Etape (
    NumeroEtape INT PRIMARY KEY,
    DateEtape DATETIME,
    VilleDep VARCHAR(40),
    VilleArr VARCHAR(40),
    NbKm INT,
    CodeType VARCHAR(5),
    FOREIGN KEY (CodeType) REFERENCES TypeEtape(CodeType)
);

CREATE TABLE Participer (
    NumeroCoureur INT,
    NumeroEtape INT,
    TempRealise VARCHAR(15),
    PRIMARY KEY (NumeroCoureur, NumeroEtape),
    FOREIGN KEY (NumeroCoureur) REFERENCES Coureur(NumeroCoureur),
    FOREIGN KEY (NumeroEtape) REFERENCES Etape(NumeroEtape)
);

CREATE USER 'ZakiHamza'@'localhost' IDENTIFIED BY 'ABCD1234'
DEFAULT TABLESPACE ZakiHamza_tbs TEMPORARY TABLESPACE temp_tbs;

GRANT ALL PRIVILEGES ON *.* TO 'ZakiHamza'@'localhost';


-- create tablespace zaki_TBS
-- add datafile 'C:\Users\Zakkye\Desktop\zaki_TBS.dat' Size 2000M
-- default storage (initial 10m next 50m mineextents 1 maxextents 10)
-- online;

-- insert into Equipe values ('liq', 'credit agricole', 'roberto', 'teamliquigas.com');
-- insert into Equipe values ('cge', 'lequigas', 'jose miguel', 'cyclisme.com');
-- insert into Equipe values ('fes', 'festina', 'stephane auge', 'festina.com');

-- insert into Coureur values (1, 'Chris Boardman', 'liq', 'gbr', '08/01/2000');
-- insert into Coureur values (2, 'Mario Cipollini', 'fes', 'ita', '08/01/2000');
-- insert into Coureur values (3, 'Erik Zabel', 'cge', 'all', '08/01/2000');
-- insert into Coureur values (4, 'Nicola Minali', 'liq', 'ita', '08/01/2000');
-- insert into Coureur values (5, 'cedric vasseur', 'ca', 'fra', '08/01/2000');
-- insert into Coureur values (6, 'Lebron Blijle', 'liq', 'esp', '08/01/2000');
-- insert into Coureur values (7, 'Laurent Brochard', 'ca', 'fra', '08/01/2000');
-- insert into Coureur values (8, 'Jan Ulritch', 'cge', 'all', '08/01/2000');

-- insert into pays values ('gbr', 'grand bretain');
-- insert into pays values ('ita', 'italie');
-- insert into pays values ('all', 'allemagne');
-- insert into pays values ('esp', 'espagne');
-- insert into pays values ('fra', 'france');
