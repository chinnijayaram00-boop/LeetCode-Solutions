# Sum of Natural Number Cubes

![Difficulty](https://img.shields.io/badge/Difficulty-Basic-red)

## Problem

Given an integer  **n,** calculate the sum of series  **13 + 23 + 33 + 43 + …**  till n-th term.

 **Examples:** 

```
Input: n = 5
Output: 225
Explanation: 13 + 23 + 33 + 43 + 53 = 225
```

```
Input: n = 7
Output: 784
Explanation: 13 + 23 + 33 + 43 + 53 + 63 + 73 = 784
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-10-23T02:22:42.376Z  

```java

class Solution {
    long sumOfSeries(int n) {
        return (long) n * (n + 1) / 2
                * n * (n + 1) / 2;
    }
}

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/sum-of-first-n-terms5843/1)