# Quadratic Equation Roots

![Difficulty](https://img.shields.io/badge/Difficulty-Basic-red)

## Problem

Given a quadratic equation  **ax2 + bx + c = 0**, find its roots. If the equation has real roots, then return floor value of each root in decreasing order, If the roots are imaginary return  **-1**, the driver code will print  **Imaginary**.

 **Examples:** 

```
Input: a = 1, b = -2, c = 1
Output: [1, 1]
Explanation: Roots of equation x2-2x+1 are 1 and 1.
```

```
Input: a = 1, b = -7, c = 12
Output: [4, 3]
Explanation: Roots of equation x2 - 7x + 12 are 4 and 3.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-10-20T19:15:45.579Z  

```java

class Solution {
    public ArrayList<Integer> quadraticRoots(int a, int b, int c) {
        ArrayList<Integer> ans = new ArrayList<>();

        int d = b * b - 4 * a * c;

        if (d < 0) {
            ans.add(-1);
            return ans;
        }

        double root1 = (-b + Math.sqrt(d)) / (2.0 * a);
        double root2 = (-b - Math.sqrt(d)) / (2.0 * a);

        int r1 = (int) Math.floor(root1);
        int r2 = (int) Math.floor(root2);

        ans.add(Math.max(r1, r2));
        ans.add(Math.min(r1, r2));

        return ans;
    }
}

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/quadratic-equation-roots/1)