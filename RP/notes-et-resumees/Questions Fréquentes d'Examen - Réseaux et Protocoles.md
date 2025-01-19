## Questions Très Fréquentes

### 1. Protocoles de Routage OSPF vs RIP
- **Fréquence :** 8 apparitions
- **Question type :** Comparez OSPF et RIP. Quels sont leurs avantages et inconvénients respectifs ?
- **Réponse :**
  - OSPF :
    - Protocole à état de liens
    - Convergence rapide
    - Support VLSM et CIDR
    - Organisation hiérarchique (zones)
    - Adapté aux grands réseaux
  - RIP :
    - Protocole à vecteur de distance
    - Configuration simple
    - Limité à 15 sauts
    - Convergence lente
    - Adapté aux petits réseaux

### 2. VLSM (Variable Length Subnet Mask)
- **Fréquence :** 7 apparitions
- **Question type :** Expliquez le VLSM et réalisez un exercice de sous-réseaux avec VLSM
- **Réponse :**
  - Permet des masques de sous-réseau de différentes tailles
  - Optimise l'utilisation des adresses IP
  - Formule pour calculer :
    - Nombre de sous-réseaux : 2^n (n = bits empruntés)
    - Nombre d'hôtes : 2^m - 2 (m = bits restants)
  - Toujours commencer par le plus grand sous-réseau

### 3. Types de Routeurs OSPF
- **Fréquence :** 6 apparitions
- **Question type :** Identifiez et expliquez les différents types de routeurs dans OSPF
- **Réponse :**
  - IR (Internal Router) : routeur interne à une zone
  - ABR (Area Border Router) : relie plusieurs zones
  - ASBR (AS Boundary Router) : connecte à d'autres systèmes autonomes
  - DR (Designated Router) : routeur principal sur réseau multi-accès
  - BDR (Backup Designated Router) : remplaçant du DR

## Questions Fréquentes

### 4. Distance Administrative
- **Fréquence :** 4 apparitions
- **Valeurs importantes :**
  - Route directement connectée : 0
  - Route statique : 1
  - EIGRP : 90
  - OSPF : 110
  - RIP : 120

### 5. Système Autonome
- **Fréquence :** 4 apparitions
- **Définition :** Ensemble de réseaux sous même administration utilisant une politique de routage commune
- **Caractéristiques :**
  - Identifié par un numéro unique
  - Utilise un IGP en interne
  - Communique avec d'autres AS via BGP

### 6. Routage Statique vs Dynamique
- **Fréquence :** 3 apparitions
- **Comparaison :**
  - Statique :
    - Configuration manuelle
    - Sécurisé
    - Pas d'overhead
    - Adapté aux petits réseaux
  - Dynamique :
    - Auto-adaptation
    - Configuration automatique
    - Overhead réseau
    - Adapté aux grands réseaux

## Questions Occasionnelles 

### 7. Tables de Routage
- **Composition :**
  - Réseau destination
  - Masque
  - Interface de sortie
  - Prochain saut
  - Métrique

### 8. Protocole BGP
- **Caractéristiques :**
  - Protocole de routage externe
  - Utilise TCP port 179
  - Path vector protocol
  - Utilisé pour l'Internet

### 9. RIPv2 vs RIPv1
- **Différences :**
  - Support VLSM
  - Utilisation multicast
  - Authentification
  - Support des réseaux discontinus

### 10. Convergence Réseau
- **Définition :** Temps nécessaire pour que tous les routeurs aient une vue cohérente du réseau
- **Facteurs :**
  - Taille du réseau
  - Type de protocole
  - Bande passante disponible

## Conseils pour l'Examen

1. Pratiquez les exercices de sous-réseaux VLSM
2. Maîtrisez les différences entre protocoles
3. Comprenez les types de routeurs OSPF
4. Sachez calculer les métriques
5. Connaissez les distances administratives