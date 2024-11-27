1. **Bubble Sort**
    - **Description:** Repeatedly compares adjacent elements and swaps them if they are in the wrong order. Process repeats until no swaps are needed.
      
    - **Time Complexity:** O(n^2)
    - **Space Complexity:** O(1)

```plaintext
BubbleSort(A, n):
    Pour i de 0 à n-1:
        Pour j de 0 à n-i-2:
            Si A[j] > A[j+1]:
                Échanger A[j] et A[j+1]
```
    
2. **Selection Sort**
    - **Description:** Selects the smallest (or largest) element from the unsorted portion and swaps it with the first element of the unsorted portion.
      
    - **Time Complexity:** O(n^2)
    - **Space Complexity:** O(1)

```plaintext
SelectionSort(A, n):
    Pour i de 0 à n-1:
        min_index ← i
        Pour j de i+1 à n-1:
            Si A[j] < A[min_index]:
                min_index ← j
        Échanger A[i] et A[min_index]
```
    
3. **Insertion Sort**
    - **Description:** Builds the sorted array one element at a time by inserting elements into their correct position in the sorted portion.
      
    - **Time Complexity:** O(n^2)
    - **Space Complexity:** O(1)
      
```plaintext
InsertionSort(A, n):
    Pour i de 1 à n-1:
        clé ← A[i]
        j ← i - 1
        Tant que j >= 0 et A[j] > clé:
            A[j+1] ← A[j]
            j ← j - 1
        A[j+1] ← clé
```

4. **Merge Sort**
    - **Description:** Recursively divides the array into halves, sorts each half, and merges them back together.
      
    - **Time Complexity:** O(n log⁡ n)
    - **Space Complexity:** O(n)
      
```plaintext
MergeSort(A, gauche, droite):
    Si gauche < droite:
        milieu ← (gauche + droite) / 2
        MergeSort(A, gauche, milieu)
        MergeSort(A, milieu + 1, droite)
        Fusion(A, gauche, milieu, droite)

Fusion(A, gauche, milieu, droite):
    n1 ← milieu - gauche + 1
    n2 ← droite - milieu
    L ← tableau de taille n1
    R ← tableau de taille n2
    Pour i de 0 à n1-1:
        L[i] ← A[gauche + i]
    Pour j de 0 à n2-1:
        R[j] ← A[milieu + 1 + j]
    i ← 0, j ← 0, k ← gauche
    Tant que i < n1 et j < n2:
        Si L[i] <= R[j]:
            A[k] ← L[i]
            i ← i + 1
        Sinon:
            A[k] ← R[j]
            j ← j + 1
        k ← k + 1
    Copier les éléments restants de L (s’il y en a)
    Copier les éléments restants de R (s’il y en a)
```

5. **Quick Sort**
    - **Description:** Divides the array based on a pivot element such that elements less than the pivot are on the left and greater ones are on the right. Recursively applies the same process.
      
    - **Time Complexity:** O(n^2) 
    - **Space Complexity:** O(log⁡n)O(\log n) (in-place)

```plaintext
QuickSort(A, gauche, droite):
    Si gauche < droite:
        pivot_index ← Partition(A, gauche, droite)
        QuickSort(A, gauche, pivot_index - 1)
        QuickSort(A, pivot_index + 1, droite)

Partition(A, gauche, droite):
    pivot ← A[droite]
    i ← gauche - 1
    Pour j de gauche à droite - 1:
        Si A[j] <= pivot:
            i ← i + 1
            Échanger A[i] et A[j]
    Échanger A[i+1] et A[droite]
    Retourner i+1
```

6. **Heap Sort**
    - **Description:** Builds a max-heap (or min-heap) and repeatedly extracts the maximum (or minimum) element to sort the array.
      
    - **Time Complexity:** O(nlog⁡n)
    - **Space Complexity:** O(1)

```plaintext
HeapSort(A, n):
    Pour i de n/2 - 1 à 0:
        ConstruireHeap(A, n, i)
    Pour i de n-1 à 1:
        Échanger A[0] et A[i]
        ConstruireHeap(A, i, 0)

ConstruireHeap(A, n, i):
    largest ← i
    gauche ← 2 * i + 1
    droite ← 2 * i + 2
    Si gauche < n et A[gauche] > A[largest]:
        largest ← gauche
    Si droite < n et A[droite] > A[largest]:
        largest ← droite
    Si largest ≠ i:
        Échanger A[i] et A[largest]
        ConstruireHeap(A, n, largest)
```

7. **Shell Sort**
    - **Description:** Generalization of insertion sort, uses a gap to compare elements that are farther apart, reducing the gap over iterations.
      
    - **Time Complexity:** O(n^2)
    - **Space Complexity:** O(1)

```plaintext
ShellSort(A, n):
    gap ← n / 2
    Tant que gap > 0:
        Pour i de gap à n-1:
            clé ← A[i]
            j ← i
            Tant que j >= gap et A[j - gap] > clé:
                A[j] ← A[j - gap]
                j ← j - gap
            A[j] ← clé
        gap ← gap / 2
```

---

### **Summary of Key Concepts**

- **Bubble Sort** and **Selection Sort** use repeated passes over the array.
- **Insertion Sort** builds the sorted portion incrementally.
- **Merge Sort** and **Quick Sort** use divide-and-conquer.
- **Heap Sort** relies on a heap structure.
- **Shell Sort** optimizes insertion sort by comparing elements at larger intervals.