# Power of 2

![Difficulty](https://img.shields.io/badge/Difficulty-Basic-red)

## Problem

Given a non-negative integer  **n**, return true if it is a power of  **2**. Otherwise, return false.  

**Examples:
**

```
Input: n = 8
Output: true
Explanation: 8 is equal to 2 raised to 3 (23 = 8).
```

```
Input: n = 98
Output: false
Explanation: 98 cannot be obtained by any power of 2.
```

```
Input: n = 1
Output: true
Explanation: 1 is equal to 2 raised to 0 (20 = 1).
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-10-24T19:55:21.405Z  

```java
class Solution {
    public boolean isPowerofTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/power-of-2-1587115620/1)