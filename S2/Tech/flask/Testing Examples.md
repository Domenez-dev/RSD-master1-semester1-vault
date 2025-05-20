### **2. Jeu de la congestion – Choix d’une route réseau surchargée ou non**
**Tableau des gains** (format : (J1, J2)) :

||**A**|**B**|
|---|---|---|
|**A**|(1, 1)|(3, 2)|
|**B**|(2, 3)|(2, 2)|

- Interprétation : Si les deux choisissent A, ils se gênent (latence haute).
    
- Si un seul va sur A, il a un bon score, l’autre un score moyen.
    
- Si les deux prennent B, c’est médiocre mais stable.
    

**Analyse possible** :

- Équilibres de Nash : (B,A) et (A,B)
    
- Pas de stratégie strictement dominante
    

---

### **3. Accès au canal – Choix de transmission dans un canal partagé (ALOHA/CSMA)**
**Tableau des gains** :

||**E**|**A**|
|---|---|---|
|**E**|(0, 0)|(1, 0)|
|**A**|(0, 1)|(0, 0)|

- Interprétation : L’émission simultanée cause une perte.
    
- L’émission unique rapporte 1 point.
    
- Si les deux attendent, aucun ne gagne.
    

**Analyse possible** :

- Équilibres de Nash : (E,A) et (A,E)
    
- Comportement typique de type anti-coordination
    

---

### **4. Sécurité Réseau – Défenseur vs Attaquant**
**Tableau des gains** (Défenseur, Attaquant) :

||**A**|**B**|
|---|---|---|
|**A**|(1, 0)|(0, 1)|
|**B**|(0, 1)|(1, 0)|

- Interprétation : Si le défenseur protège la bonne cible, il gagne. Sinon, l’attaquant réussit.
    

**Analyse possible** :

- Pas d’équilibre pur (jeu de type "pierre-feuille-ciseaux")
    
- Jeu zéro-somme, nécessite une stratégie mixte pour équilibre de Nash
    
