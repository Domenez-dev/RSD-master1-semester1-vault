## Notation de Kendall
A/B/s/K/N/D où :
- A : Distribution des arrivées
- B : Distribution du service
- s : Nombre de serveurs
- K : Capacité du système (par défaut ∞)
- N : Taille de la population source (par défaut ∞)
- D : Discipline de service (par défaut FIFO)

## 1. M/M/1 - Le système de base
### Description
- Un seul serveur
- Arrivées poissonniennes
- Service exponentiel
- Capacité infinie

### Points clés
- Condition de stabilité : ρ = λ/μ < 1
- Calculs simples grâce aux propriétés markoviennes
- Formules explicites pour tous les paramètres

### Utilisation typique
- Guichet unique
- Service informatique simple
- Point de service avec flux régulier

### Conseils pour les exercices
- Toujours vérifier la stabilité en premier
- Utiliser ρ = λ/μ pour simplifier les calculs
- Les formules sont toutes liées à ρ

## 2. M/M/1/K - Système à capacité limitée
### Description
- Comme M/M/1 mais avec capacité K finie
- Les clients sont rejetés si système plein

### Points clés
- Toujours stable (pas besoin de ρ < 1)
- Important : calculer le taux de rejet
- Formules plus complexes mais finies

### Utilisation typique
- Systèmes avec buffer limité
- Services avec contraintes physiques
- Centres d'appels avec files limitées

### Conseils pour les exercices
- Ne pas oublier les clients rejetés dans les calculs de performance
- Utiliser la forme géométrique tronquée pour les probabilités
- Attention aux sommes finies vs infinies

## 3. M/M/c - Système multi-serveurs
### Description
- c serveurs identiques
- File unique
- Services exponentiels

### Points clés
- Stabilité : λ < cμ
- Plus complexe que M/M/1
- Formules d'Erlang importantes

### Utilisation typique
- Centres d'appels
- Guichets multiples
- Services parallèles

### Conseils pour les exercices
- Séparer l'analyse avant/après utilisation de tous les serveurs
- Utiliser les formules d'Erlang C
- Attention au calcul du temps d'attente moyen

## 4. M/M/c/K - Multi-serveurs avec capacité limitée
### Description
- c serveurs
- Capacité K finie
- Rejet si système plein

### Points clés
- Toujours stable
- Combine complexité de M/M/c et M/M/1/K
- Important pour dimensionnement

### Utilisation typique
- Centres d'appels réels
- Services avec ressources limitées
- Systèmes industriels

### Conseils pour les exercices
- Utiliser les formules d'Erlang B pour les pertes
- Bien distinguer K total vs K en attente
- Vérifier la cohérence des flux

## 5. M/G/1 - Service général
### Description
- Service de distribution quelconque
- Un seul serveur
- Arrivées poissonniennes

### Points clés
- Formule de Pollaczek-Khinchin essentielle
- Nécessite E[S] et E[S²]
- Plus réaliste que M/M/1

### Utilisation typique
- Services variables
- Processus industriels
- Systèmes informatiques

### Conseils pour les exercices
- Toujours calculer E[S²]
- Utiliser la formule P-K
- Attention aux différentes variances

## 6. G/G/1 - Système général
### Description
- Arrivées générales
- Service général
- Un serveur

### Points clés
- Approximations nécessaires
- Bornes de Kingman utiles
- Analyse complexe

### Utilisation typique
- Modélisation réaliste
- Systèmes complexes
- Analyse approximative

### Conseils pour les exercices
- Utiliser les bornes
- Se concentrer sur les moments
- Acceptable d'avoir des approximations

## Notes importantes pour tous les systèmes

### Vérifications systématiques
1. Stabilité du système (ρ < 1 si capacité infinie)
2. Conditions d'ergodicité
3. Cohérence des unités (temps, taux)

### Calculs prioritaires
1. Taux d'utilisation (ρ)
2. Probabilités d'état
3. Performances moyennes (N, X, R, W)

### Pièges courants
- Oublier les unités de temps
- Confondre taux et temps moyens
- Négliger les conditions de stabilité
- Mélanger formules de différents systèmes

### Bonnes pratiques
- Dessiner le diagramme des états
- Vérifier la cohérence des résultats
- Utiliser les relations de Little
- Valider les ordres de grandeur