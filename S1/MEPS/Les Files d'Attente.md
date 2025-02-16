## Introduction
La théorie des files d'attente, développée en 1917 par l'ingénieur danois ERLANG, permet de modéliser le phénomène de partage de ressources. Elle s'applique à diverses situations comme :
- L'attente des malades dans un cabinet médical
- La gestion des abonnés dans une centrale téléphonique
- Le traitement des pièces dans un atelier de fabrication
- La gestion des feux lumineux dans un réseau routier

## Processus de Poisson

### Définition
Le processus de Poisson est un processus stochastique à :
- Espace d'état discret
- Temps continu
- Variables aléatoires d'inter-arrivées suivant une loi exponentielle

### Propriétés principales
1. **Propriété des temps d'inter-arrivées**
   - Les variables Tn = An - An-1 sont exponentielles
   - Paramètre λ (taux d'arrivée)
   - Variables indépendantes et identiquement distribuées

2. **Propriété caractéristique**
   - Probabilité d'arrivée entre t et t + Δt ≈ λΔt
   - Probabilité de plus d'une arrivée négligeable

## Processus de Naissance et de Mort

### Caractéristiques
- Chaîne de Markov à temps continu
- Transitions uniquement entre états voisins (i-1 et i+1)
- Spécifié par :
  * Taux de naissance (λi)
  * Taux de mortalité (μi)

## Description du Modèle des Files d'Attente

### Composants principaux
1. **Structure physique**
   - Buffer (espace d'attente)
   - Station de service (mono ou multi-serveurs)

2. **Flux des clients**
   - Arrivée → Buffer → Service → Départ

### Caractéristiques
1. **Processus d'arrivée**
   - Généralement processus de Poisson

2. **Processus de service**
   - Distribution du temps de service
   - Souvent loi exponentielle

3. **Paramètres système**
   - Nombre de serveurs
   - Capacité maximale de la file
   - Taille de la source de clients
   - Discipline de service

### Disciplines de service
- FIFO (First In First Out)
- LIFO (Last In First Out)
- Random (aléatoire)
- Round Robin (cyclique)

### Notation de KENDALL
Format : A/B/s/K/N/D où :
- A : loi du processus d'arrivée
- B : loi de service
- s : nombre de serveurs
- K : capacité de la file
- N : taille de la source
- D : discipline de service

Types de lois possibles :
- M : exponentielle (markovienne)
- D : déterministe
- G : générale

## Système M/M/1

### Définition
- Processus d'arrivée : Poisson (taux λ)
- Service : exponentiel (taux μ)
- Un seul serveur
- Capacité infinie
- Source infinie
- Discipline FIFO

### Condition d'ergodicité
- λ < μ
- ρ = λ/μ < 1

### Distribution stationnaire
Probabilités d'état en régime permanent :
- π0 = 1 - ρ
- πi = (1 - ρ)ρi, ∀i ≥ 0

### Paramètres de performance
| Description                         | Formule                       |
|-------------------------------------|-------------------------------|
| Nombre moyen de clients (N)         | N = ρ/(1-ρ)                   |
| Débit moyen (X)                     | X = λ en régime stationnaire  |
| Temps de réponse moyen (R)          | R = 1/(μ(1-ρ))                |
| Temps d'attente moyen (W)           | W = R - 1/μ                   |
| Taux d'utilisation du serveur (U)   | U = ρ = λ/μ                   |
| Probabilité d'avoir k clients (Pk)  | Pk = ρ^k                      |