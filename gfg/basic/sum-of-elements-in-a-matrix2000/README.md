# Sum of Matrix

![Difficulty](https://img.shields.io/badge/Difficulty-Basic-red)

## Problem

Given an integer matrix  **mat[][]**, compute the sum of all its elements.

 **Examples:** 

```
Input: mat[][] = [[1, 0, 1], [-8, 9, -2]]
Output: 1
Explanation: The sum of all elements of the matrix is: (1 + 0 + 1 - 8 + 9 - 2) = 1.
```

```
Input: mat[][] = [[1, 0, 1, 0, 1], [0, 1, 0, 1, 0], [-1, -1, -1, -1, -1]]
Output: 0
Explanation: The sum of all elements of the matrix is: (1 + 0 + 1 + 0 + 1 + 0 + 1 + 0 + 1 + 0 - 1 - 1 - 1 - 1 - 1) = 0.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-11-02T01:49:18.468Z  

```java
class Solution {
    long sumOfMatrix(int[][] mat) {
        long sum = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                sum += mat[i][j];
            }
        }

        return sum;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/sum-of-elements-in-a-matrix2000/1)