## Introduction

Les chaînes de Markov sont des modèles mathématiques qui permettent de décrire des systèmes comportant des phénomènes probabilistes en fonction du temps. Selon le document, elles font partie de la famille des processus stochastiques.

## Définition fondamentale

Une chaîne de Markov est caractérisée par la propriété markovienne qui stipule que :
$$
P[Xn = j|Xn-1 = in-1, Xn-2 = in-2, ..., X0 = i0] = P[Xn = j|Xn-1 = in-1]
$$
Cela signifie que l'état futur du système ne dépend que de l'état présent et non de l'historique des états passés.

## Types de chaînes de Markov

Selon le document, on distingue deux types principaux :
1. **Chaînes de Markov à temps discret (CMTD)**
   - L'ensemble des temps T ⊆ ℤ ou ℕ
   - L'espace d'états est discret (E ⊆ ℕ)

2. **Chaînes de Markov à temps continu (CMTC)**
   - L'ensemble des temps T ⊆ ℝ ou [0, +∞[
   - L'espace d'états reste discret

## Chaînes de Markov à temps discret (CMTD)

### Caractéristiques principales
1. **Probabilités de transition**
   - Notées $pij = P[Xn = j|Xn-1 = i]$
   - Représentent la probabilité de passer de l'état i à l'état j en une étape
   - ∑j∈E pij = 1

2. **Représentation**
   - Par un graphe orienté
   - Par une matrice de transition P = ||pij||

### Analyse transitoire
- Étude du vecteur des probabilités d'état π(n)
- π(n) = π(0).Pn où π(0) est la distribution initiale
- Utilisation des équations de Kolmogorov pour calculer les probabilités de transition en n étapes

### Classification des états
Selon le document, les états peuvent être classifiés selon plusieurs critères :

1. **Périodicité**
   - États périodiques
   - États apériodiques

2. **Nature des états**
   - États transitoires (fii < 1)
   - États récurrents (fii = 1)
     * Récurrents nuls (Mi = ∞)
     * Récurrents non nuls (Mi < ∞)

### Propriétés importantes
1. Tous les états d'une CMTD irréductible sont de même nature
2. Une CMTD finie ne peut comporter que des états transitoires ou récurrents non nuls
3. Tous les états d'une CMTD irréductible finie sont récurrents non nuls

## Analyse stationnaire
Le document précise que pour atteindre un régime stationnaire, la chaîne doit être :
1. Ergodique (apériodique et irréductible)
2. La distribution stationnaire π est solution du système :
$$
   π.P = π
   ∑i∈E πi = 1
$$

### Propriétés du régime stationnaire
1. Les probabilités stationnaires sont indépendantes de la distribution initiale
2. πj = 1/Mj où Mj est le temps moyen de retour en j
3. Pour une CMTD finie et irréductible, le régime stationnaire existe toujours

Je vais compléter le résumé avec la partie sur les Chaînes de Markov à Temps Continu (CMTC) à partir du document "Cours_Chaines de Markov.pdf".

## Chaînes de Markov à temps continu (CMTC)

### Définition
Une chaîne de Markov à temps continu est caractérisée par :
- Un espace d'états discret
- Un temps continu (T ⊆ [0, +∞[)
- La propriété markovienne :
$$
P[X(tn) = in|X(tn-1) = in-1, ..., X(t0) = i0] = P[X(tn) = in|X(tn-1) = in-1]
$$

### Caractéristiques principales
1. **Temps de séjour**
   - Le temps passé dans tout état i suit une loi exponentielle de taux μi
   - Les transitions entre états sont probabilistes

2. **Taux de transition**
   - μij = μi.pij où :
     * μij : taux de transition de l'état i vers j
     * μi : taux de sortie de l'état i
     * pij : probabilité de transition de i vers j

### Représentation
1. **Diagramme de transition d'état**
   - Graphe orienté et libellé
   - Sommets : états de la chaîne
   - Arcs : étiquetés par les taux de transition

2. **Générateur infinitésimal Q**
   - Matrice carrée avec éléments qij où :
     * $qij = μij$ pour $i≠j$
     * $qii = -∑(k≠i) μik$

### Chaîne de Markov incluse
- Lien entre CMTC et CMTD
- Les probabilités de transition sont données par :
$$
pij = μij/μi = μij/∑(k≠i) μik
$$
- La nature d'un état dans une CMTC est identique à celle du même état dans la CMTD incluse

### Analyse stationnaire

1. **Conditions d'existence**
   - La CMTC doit être irréductible
   - Une CMTC finie et irréductible est ergodique

2. **Calcul des probabilités stationnaires**
   - Le vecteur π des probabilités stationnaires est solution du système :
$$
   π.Q = 0
   ∑(i∈E) πi = 1
$$

3. **Propriété des flux**
   - À l'état stationnaire, pour tout état j :
   - Flux sortant de j = flux entrant dans j
   - Cette équation est appelée "équation d'état"

### Relations entre CMTC et CMTD

1. **Propriétés**
   - Une CMTC est irréductible si et seulement si la CMTD incluse est irréductible
   - La nature des états (transitoire, récurrent) est conservée entre la CMTC et sa CMTD incluse

2. **Avantages de la relation**
   - Permet d'utiliser les propriétés des CMTD pour analyser les CMTC
   - Simplifie l'étude de la nature des états