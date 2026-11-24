# Two Pairs with Same Sum

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an array  **arr[]**  of positive integers, find four indices  **a**,  **b**,  **c**, and  **d**  such that arr[a] + arr[b] = arr[c] + arr[d], where a, b, c, d are all distinct indices.

- If multiple such quadruplets exist, return the one that is lexicographically smallest when the indices are considered in the order a, b, c, d.
- If no such quadruplet exists, return {-1, -1, -1, -1}. 
- Ensure a < b and c < d, and the pair (a, b) is lexicographically smaller than the pair (c, d).

 **Examples:** 

```
Input: arr[] = [3, 4, 7, 1, 2, 9, 8]
Output: [0, 2, 3, 5]
Explanation: arr[0] + arr[2] = 3 + 7 = 10, arr[3] + arr[5] = 1 + 9 = 10. 
This is the lexicographically smallest such quadruplet of indices.
```

```
Input: arr[] = [1, 2, 3, 3]
Output: [-1, -1, -1, -1]
Explanation: No two distinct pairs of indices produce the same sum.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-11-24T05:26:28.107Z  

```java

import java.util.*;

class Solution {

    public ArrayList<Integer> findPairs(int[] arr) {

        int n = arr.length;

        HashMap<Long, ArrayList<int[]>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                long sum = (long) arr[i] + arr[j];

                map.computeIfAbsent(sum, k -> new ArrayList<>())
                   .add(new int[]{i, j});
            }
        }

        int[] best = null;

        for (ArrayList<int[]> pairs : map.values()) {

            for (int x = 0; x < pairs.size(); x++) {

                int[] p1 = pairs.get(x);

                for (int y = x + 1; y < pairs.size(); y++) {

                    int[] p2 = pairs.get(y);

                    if (p1[0] == p2[0] ||
                        p1[0] == p2[1] ||
                        p1[1] == p2[0] ||
                        p1[1] == p2[1]) {
                        continue;
                    }

                    int[] curr = {
                        p1[0], p1[1], p2[0], p2[1]
                    };

                    if (best == null || compare(curr, best) < 0) {
                        best = curr;
                    }

                    break;
                }
            }
        }

        if (best == null) {
            return new ArrayList<>(
                Arrays.asList(-1, -1, -1, -1)
            );
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for (int x : best) {
            ans.add(x);
        }

        return ans;
    }

    private int compare(int[] a, int[] b) {

        for (int i = 0; i < 4; i++) {
            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }

        return 0;
    }
}

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/satisfy-the-equation5847/1)