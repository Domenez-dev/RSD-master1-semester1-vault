## Introduction

Les réseaux de Petri ont été développés dans les années 60-62 par Carl Adam Petri. Selon le document, ce modèle permet d'exprimer les caractéristiques des systèmes parallèles, notamment :
- La synchronisation
- La concurrence
- Le non-déterminisme
- L'allocation de ressources

## Définition formelle

Un réseau de Petri R est défini par le tuple (P, T, Pre, Post, M0), où :
- $P$ = {p1, p2, ..., pn} : ensemble fini de places
- $T$ = {t1, t2, ..., tm} : ensemble de transitions
- $Pre$ : P × T → ℕ : application d'incidence avant
- $Post$ : P × T → ℕ : application d'incidence arrière
- $M_0$ : P → ℕ : marquage initial

## Représentation

### Représentation graphique

<img title="RdP" alt="Représentation graphique d'un RDP" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSf6437KLQM9EEiwmh1klwkPpzey040WSjS1w&s">

- Places : représentées par des cercles
- Transitions : représentées par des traits
- Arcs orientés : relient les places aux transitions et vice-versa
- Poids des arcs : donnés par les fonctions Pre et Post
- Marques : représentées par des points ou nombres dans les places

### Représentation matricielle

1. **Matrices d'incidence**
   - C+ : matrice d'incidence arrière
   - C- : matrice d'incidence avant
   - C = C+ - C- : matrice d'incidence globale

2. **Propriétés des matrices**
   - Lignes indexées par les places
   - Colonnes indexées par les transitions
   - C(p,t) indique la modification du marquage après franchissement

## Évolution d'un RdP

### Règles d'évolution

1. **Sensibilisation**
   - Une transition t est sensibilisée si ∀p ∈ P : M(p) ≥ Pre(p,t)
   - Notation : M[t⟩

2. **Tir de transition**
   - Nouveau marquage : M'(p) = M(p) - Pre(p,t) + Post(p,t)
   - Notation : M[t⟩M'

3. **Séquence de franchissements**
   - Suite ordonnée de transitions
   - Notation : **$M_0[σ⟩M$**

### Accessibilité
1. **Marquage accessible**
   - Un marquage M est accessible s'il existe une séquence σ telle que M0[σ⟩M

2. **Ensemble d'accessibilité**
   - Noté A(R,M0)
   - Contient tous les marquages accessibles

3. **Graphe des marquages**
   - Nœuds : marquages accessibles
   - Arcs : transitions entre marquages

## Analyse comportementale

### Propriétés fondamentales

1. **Bornitude**
   - Un réseau est k-borné si ∀M ∈ A(R,M0), ∀p ∈ P : M(p) ≤ k
   - Réseau binaire si k = 1

2. **Vivacité**
   - **Pseudo-vivacité** : ∀M ∈ A(R,M0), ∃t ∈ T tel que M[t⟩
   - **Quasi-vivacité** : ∀t ∈ T, ∃M ∈ A(R,M0) tel que M[t⟩
   - **Vivacité complète** : possibilité de franchir toute transition depuis tout état accessible

3. **Blocage**
   - Un réseau est sans blocage si aucun marquage accessible n'est mort
   - Un marquage mort est un marquage sans transition franchissable

4. **État d'accueil**
   - Un marquage Ma est un état d'accueil si :
   - ∀M ∈ A(R,M0), ∃σ ∈ T* tel que M[σ⟩Ma
   - Important pour la réinitialisation du système

### Applications pratiques
Selon le document, ces propriétés permettent de vérifier :
- L'absence de blocage dans le système
- La possibilité de réinitialisation
- Le bon fonctionnement permanent du système
- La correction du modèle