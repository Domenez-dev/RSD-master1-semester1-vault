## I. Introduction

L'architecture des réseaux constitue le fondement de toute communication informatique moderne. Ce chapitre couvre les concepts essentiels nécessaires à la compréhension des réseaux informatiques.

## II. Architecture des Réseaux

### Supports de Communication

| Type de Support  | Description                       | Avantages                        | Limitations                |
| ---------------- | --------------------------------- | -------------------------------- | -------------------------- |
| Fils métalliques | Câbles avec conducteurs en cuivre | Coût faible, installation simple | Distance limitée           |
| Fibres optiques  | Transmission par lumière          | Grande distance, haut débit      | Coût élevé                 |
| Sans fil         | Transmission par ondes radio      | Mobilité, flexibilité            | Sensible aux interférences |

### Critères de Choix d'un Support
- Distance maximale de transmission
- Environnement d'installation
- Quantité de données et débit requis
- Coût (installation et maintenance)

## III. Les Trois Grandes Architectures

### 1. Modèle OSI

| Coche | Nom          | Description                                               |
| ----- | ------------ | --------------------------------------------------------- |
| 7     | Application  | - Interface utilisateur<br>- Protocoles : FTP, SMTP, HTTP |
| 6     | Présentation | - Interface utilisateur<br>- Protocoles : FTP, SMTP, HTTP |
| 5     | Session      | - Gestion des sessions<br>- Synchronisation               |
| 4     | Transport    | - Fiabilité bout-en-bout<br>- Segmentation des données    |
| 3     | Réseau       | - Routage<br>- Adressage logique                          |
| 2     | Liaison      | - Adressage physique<br>- Contrôle d'erreurs              |
| 1     | Physique     | - Transmission des bits<br>- Caractéristiques physiques   |
### 2. Modèle TCP/IP

| Coche | Nom          | Correspondance avec OSI         | Unités de Données |
| ----- | ------------ | ------------------------------- | ----------------- |
| 4     | Application  | Équivalent aux couches 5-7 OSIs | Données           |
| 3     | Transport    | Équivalent à la couche 4 OSI    | Segments          |
| 2     | Internet     | Équivalent à la couche 3 OSI    | Paquets           |
| 1     | Accès Réseau | Équivalent aux couches 1-2 OSI  | Trames            |
### 3. Architecture ATM
- Structure en cellules fixes (53 octets)
- Optimisé pour le multimédia
- Organisation en 3 couches

## IV. Routage et Commutation

### Types de Connexions Réseaux
- LAN-LAN : Connexion entre réseaux locaux
- LAN-WAN : Connexion local-étendu
- WAN-WAN : Connexion entre réseaux étendus
- LAN-WAN-LAN : Connexion traversant un réseau étendu

### Équipements d'Interconnexion

| Équipement | Couche OSI | Fonction Principale |
|------------|------------|-------------------|
| Répéteur | 1 | Amplification signal |
| Pont/Switch | 2 | Filtrage trames |
| Routeur | 3 | Routage paquets |
| Passerelle | 4-7 | Conversion protocoles |

### V. Techniques de Transfert
1. **Commutation de circuits**
   - Établissement préalable du circuit
   - Ressources réservées

2. **Transfert de messages**
   - Transmission message complet
   - Stockage intermédiaire

3. **Transfert de paquets**
   - Fragmentation en paquets
   - Routage indépendant

4. **Commutation de trames**
   - Traitement niveau 2
   - Plus rapide que paquets

5. **Commutation de cellules**
   - Taille fixe (ATM)
   - Optimisé multimédia

#RP