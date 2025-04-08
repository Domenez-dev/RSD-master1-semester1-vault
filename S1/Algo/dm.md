# Devoir maison Algo et complexity
 etudiant: Bouzara Zakaria
matricule: 212138069681

---
### 1. Algorithme iteraif pour calculer le produit de deux matrices:
```
fonction produit_matrices_carres(E: A, B [n][n]entier, S: [n][n]entier){
	pour i=0 ; i<n ; i++ faire:
		pour j=0 ; j<n ; j++ faire:
			pour k=0 ; i<n ; k++  faire:
				C[i][j] += A[i][k] * B[k][i];
			fait;
		fait;
	fait;
	retourner C
fin
```
- **La complexity de cette algorithme:**
On a 3 boucles `pour` chacune itère de 0 à n (n est la taille de la matrice carrée A), alors $O(n^3)$.

- Pour cet algorithme de multiplication de matrices, la complexité temporelle $O(n^3)$ est obtenue dans tous les cas (pire, meilleur et cas moyen). car l'algorithme est fixe et dépend uniquement de la taille des matrices.

### 2. Modifiant l'algorithme pour diffrent tailles des matrices:
```
fonction produit_matrices_non_carres(E: A [m][n]entier, B [n][p]entier S: [m][p]entier){
	pour i=0 ; i<m ; i++ faire:					//m <- nbr de lignes de A 
		pour j=0 ; j<p ; j++ faire:				//p <- nbr de colomns de B
			pour k=0 ; k<n ; k++  faire:		//n <- nbr de lignes de B
				C[i][j] += A[i][k] * B[k][i];
			fait;
		fait;
	fait;
	retourner C
fin
```
- **La complexity de cette algorithme:**
on a 3 boucle `pour` qui itère de 0 a differents valeur m, p et m alors $O(n.m.p)$

### Algorithme  recursive pour calculer le produit
```
fonction produit_recursive(E: A [m][n]entier, B [n][p]entier C: [m][p], i, j, k entier){
	si i >= m:
		retourner C //condition d'arret, en retourne C comme resultat
	fsi;
	si j >= p:
		retourner produit_recursive(A, B, C, i+1, j, 0) //passer au prochaine colomn
	fsi;
	si k >= n:
		retourner produit_recursive(A, B, C, i, j+1, 0) //passer au prochaine ligne
	fsi;
	
	C[i][j] = A[i][k] * B[k][j]
	retourner produit_recursive(A, B, C, i, j, k+1)
fin
```
- **La complexity de cette algorithme:**

La fonction `produit_recursive` itère de `0` à `m` (condition d'arrêt), incrémentant `i`. À chaque fois, elle s'appelle avec une incrémentation de `j`, qui est responsable d'itérer sur les colonnes de `B`. Dans chaque appel, cette dernière appelle elle-même en incrémentant `k` jusqu'à `k = n`.

Pour chaque élément du résultat, la fonction effectue une série de multiplications et d'additions. Étant donné les trois niveaux de récursion, la complexité temporelle totale de cette fonction est $O ( m × n × p )$ .

