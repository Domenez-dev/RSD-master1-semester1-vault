1. **Counting Sort**
    - **Description:** Counts the frequency of each element and uses this count to place elements in the sorted array.
      
    - **Time Complexity:** O(n+k)
    - **Space Complexity:** O(k)
      
2. **Radix Sort**
    - **Description:** Sorts numbers digit by digit starting from the least significant digit, using a stable sorting algorithm (e.g., counting sort) at each step.
      
    - **Time Complexity:** O(d⋅(n+k))
    - **Space Complexity:** O(n+k)
      
3. **Bucket Sort**
    - **Description:** Divides elements into buckets, sorts each bucket individually, and concatenates the results.
      
    - **Time Complexity:** O(n^2)
    - **Space Complexity:** O(n+k)O(n + k)