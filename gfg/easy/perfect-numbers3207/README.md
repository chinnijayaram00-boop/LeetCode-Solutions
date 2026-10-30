# Perfect Numbers

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given a number  **n,**  check if the number is  **perfect**  or not. A number is said to be perfect if  **sum**  of all its factors excluding the number itself is equal to the  **number**.

 **Examples:** 

```
Input: n = 6
Output: true 
Explanation: Factors of 6 are 1, 2, 3 and 6. Excluding 6 their sum is 6 which is equal to n itself. So, it's a Perfect Number.
```

```
Input: n = 10
Output: false
Explanation: Factors of 10 are 1, 2, 5 and 10. Excluding 10 their sum is 8 which is not equal to n itself. So, it's not a Perfect Number.

```

```
Input: n = 15
Output: false
Explanation: Factors of 15 are 1, 3, 5, 15. Excluding 15 their sum is 9 which is not equal to n itself. So, it's not a Perfect Number.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-10-30T01:43:03.579Z  

```java
class Solution {
    static boolean isPerfect(int N) {
        if (N == 1) return false;

        int sum = 1;

        for (int i = 2; i * i <= N; i++) {
            if (N % i == 0) {
                sum += i;

                if (i != N / i) {
                    sum += N / i;
                }
            }
        }

        return sum == N;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/perfect-numbers3207/1)