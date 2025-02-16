#### 1. Protocoles de Routage :

- RIP (Routing Information Protocol) :
  - RIPv1 : Protocole à vecteur de distance avec classe
  - RIPv2 : Version améliorée sans classe, utilisant le multicast et supportant VLSM
  - Limite de 15 sauts
  - Mises à jour toutes les 30 secondes

- OSPF (Open Shortest Path First) :
  - Protocole à état de liens
  - Organisation en zones (areas) avec une zone backbone (area 0)
  - Utilisation de LSA (Link State Advertisement)
  - Concepts de DR (Designated Router) et BDR (Backup Designated Router)
  - Rôles spécifiques : IR, BR, ABR, ASBR

#### 2. Adressage IP :

- Conception de plan d'adressage avec VLSM
- Adresses publiques vs privées
- Calcul de masques de sous-réseau
- Découpage en sous-réseaux
- Super-réseaux (agrégation de routes)

#### 3. Commutation et VLAN :

- Types de ports : accès et trunk
- Configuration des VLANs
- Protocole STP (Spanning Tree Protocol)
- Tables MAC et ARP

#### 4. Architecture TCP/IP :

- PDU (Protocol Data Unit) pour chaque couche :
  - Application : Données
  - Transport : Segments
  - Internet : Paquets/Datagrammes
  - Accès réseau : Trames

#### 5. Tables de routage :

- Routes directement connectées
- Routes statiques
- Routes dynamiques
- Route par défaut
- Métriques et distance administrative

Les examens mettent l'accent sur :
- La capacité à concevoir des plans d'adressage
- La compréhension des protocoles de routage
- La configuration des tables de routage
- L'analyse de topologies réseau
- La résolution de problèmes de routage

#RP