# Système M/M/1

### Exercice 1 (Basé sur l'exemple du cours)
Dans une bibliothèque, le service de prêt des livres est géré par un seul employé. Les étudiants se présentent selon un processus de Poisson avec une moyenne de 4 étudiants par heure. La durée moyenne du service est de 10 minutes.

**Questions :**
1. Étudier la stabilité du système
2. Calculer la probabilité que le bibliothécaire soit inoccupé
3. Quel est le nombre moyen d'étudiants dans le système?
4. Calculer le temps moyen de réponse
5. Un étudiant arrivant à 9h30 pourra-t-il assister à son cours de 9h50 sachant que le trajet prend 3 minutes?

**Solution :**

1) Analyse des données :
   - λ = 4 étudiants/heure
   - Durée moyenne service = 10 min = 1/6 heure
   - Donc μ = 6 services/heure

2) Stabilité :
   - ρ = λ/μ = 4/6 = 2/3 < 1
   - Le système est stable

3) Probabilité d'inoccupation :
   - π0 = 1 - ρ = 1 - 2/3 = 1/3
   - Donc 33.33% du temps

4) Nombre moyen d'étudiants :
   - N = ρ/(1-ρ) = (2/3)/(1/3) = 2 étudiants

5) Temps de réponse :
   - R = 1/(μ-λ) = 1/(6-4) = 1/2 heure = 30 minutes
   - Temps total = 30 min + 3 min = 33 min
   - Arrivée à 9h30 + 33 min = 10h03
   - Il sera en retard de 13 minutes

# Système M/M/1/K

### Exercice 2
Un petit café dispose d'une capacité maximale de 10 places (incluant les personnes en train d'être servies). Les clients arrivent selon un processus de Poisson de taux λ = 20 clients/heure. Le temps de service moyen est de 4 minutes.

**Questions :**
1. Calculer le taux d'utilisation
2. Déterminer la distribution stationnaire
3. Calculer le taux de rejet
4. Quel est le débit effectif du système?
5. Calculer le nombre moyen de clients dans le système

**Solution :**

1) Analyse préliminaire :
   - λ = 20 clients/heure
   - Service = 4 min = 1/15 heure
   - Donc μ = 15 services/heure
   - K = 10 places
   - ρ = λ/μ = 20/15 = 4/3

2) Distribution stationnaire :
   - π0 = (1-ρ)/(1-ρK+1)
   - πi = [(1-ρ)/(1-ρK+1)]ρi, pour 0 ≤ i ≤ K
   - Calcul numérique avec K = 10

3) Taux de rejet :
   - TP = πK = ρK*(1-ρ)/(1-ρK+1)
   - ≈ 0.15 soit 15% des clients rejetés

4) Débit effectif :
   - X = λ(1-πK)
   - X = 20(1-0.15) = 17 clients/heure

5) Nombre moyen de clients :
   - N = [ρ/(1-ρ)]*[1-(K+1)ρK+KρK+1]/(1-ρK+1)
   - ≈ 6.8 clients

# Système M/M/c

### Exercice 3
Un centre d'appels dispose de 3 opérateurs. Les appels arrivent selon un processus de Poisson avec une moyenne de 45 appels par heure. Le temps moyen de traitement d'un appel est de 3 minutes.

**Questions :**
1. Vérifier la stabilité du système
2. Calculer la probabilité qu'un appel doive attendre
3. Déterminer le temps d'attente moyen
4. Quel est le taux d'occupation des opérateurs?
5. Combien d'opérateurs faudrait-il pour avoir moins de 10% d'appels en attente?

**Solution :**

1) Analyse des données :
   - c = 3 opérateurs
   - λ = 45 appels/heure
   - Service = 3 min = 1/20 heure
   - μ = 20 appels/heure/opérateur

2) Stabilité :
   - ρ = λ/(cμ) = 45/(3*20) = 0.75 < 1
   - Système stable

3) Probabilité d'attente :
   - Utilisation de la formule d'Erlang C
   - P(attente) ≈ 0.35 soit 35%

4) Temps d'attente moyen :
   - W = [P(attente)/(cμ-λ)] ≈ 1.4 minutes

5) Nombre d'opérateurs nécessaires :
   - Par itération, on trouve c = 5 opérateurs

# Système M/G/1

### Exercice 4
Dans un atelier, les pièces arrivent selon un processus de Poisson de taux 30 pièces/heure. Le temps de traitement suit une distribution uniforme entre 1 et 3 minutes.

**Questions :**
1. Vérifier la stabilité
2. Calculer E[S] et E[S²]
3. Déterminer le temps d'attente moyen (utiliser Pollaczek-Khinchin)
4. Calculer le nombre moyen de pièces dans le système
5. Comparer avec un M/M/1 équivalent

**Solution :**

1) Données :
   - λ = 30/heure
   - S ~ U[1,3] minutes = U[1/60,1/20] heure

2) Moments :
   - E[S] = 2/60 = 1/30 heure
   - E[S²] = [(1/60)² + (1/20)²]/3 + (1/30)²

3) Stabilité :
   - ρ = λE[S] = 1 < 1
   - Système stable à la limite

4) Temps d'attente (P-K) :
   - W = [λE[S²]]/[2(1-ρ)]
   - Calcul numérique

5) Comparaison M/M/1 :
   - Pour même E[S], le temps d'attente est différent
   - Effet de la variance du service

# Notes générales pour la résolution d'exercices

1. **Étapes systématiques**
   - Identifier le type de système
   - Convertir toutes les unités dans la même base
   - Vérifier la stabilité
   - Appliquer les formules appropriées

2. **Points d'attention**
   - Unités cohérentes
   - Conditions d'application des formules
   - Vraisemblance des résultats

3. **Vérifications**
   - ρ < 1 si nécessaire
   - Somme des probabilités = 1
   - Relations de Little