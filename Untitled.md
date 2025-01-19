Je vais analyser le cas d'une file d'attente M/M/1/N où N est la capacité maximale du système (nombre maximal de clients admis).

D'après le cours (Cours_FA.pdf, section 1.8), il s'agit d'une file d'attente à capacité limitée. Si un client arrive quand la file contient déjà N clients, il est perdu.

La principale différence avec la file M/M/1/K précédente réside dans l'analyse des pertes.

Les paramètres sont les mêmes : λ = 4 étudiants/heure μ = 6 services/heure ρ = λ/μ = 2/3

1. Stabilité : Comme pour la M/M/1/K, une file M/M/1/N est toujours ergodique car elle a un espace d'états fini et est irréductible.
2. Distribution stationnaire : La formule est la même que pour M/M/1/K : π₀ = (1-ρ)/(1-ρᴺ⁺¹) πᵢ = ((1-ρ)/(1-ρᴺ⁺¹))×ρⁱ, pour 0 ≤ i ≤ N
3. Paramètres de performance supplémentaires pour M/M/1/N :

a) Taux de perte (TP) : D'après le cours (Cours_FA.pdf, section 1.11), le taux de perte est : TP = πₙ = ρᴺ × (1-ρ)/(1-ρᴺ⁺¹)

b) Débit moyen effectif (X) : D'après le cours, le débit effectif est : X = λ × (1-πₙ) = λ × (1-ρᴺ)/(1-ρᴺ⁺¹)

c) Nombre moyen de clients dans le système (N) : N = (ρ/(1-ρ)) × (1-(N+1)×ρᴺ+N×ρᴺ⁺¹)/(1-ρᴺ⁺¹)

d) Temps de réponse moyen (R) : R = N/X

e) Temps d'attente moyen (W) : W = R - 1/μ

f) Taux d'utilisation du serveur (U) : U = (ρ-ρᴺ⁺¹)/(1-ρᴺ⁺¹)