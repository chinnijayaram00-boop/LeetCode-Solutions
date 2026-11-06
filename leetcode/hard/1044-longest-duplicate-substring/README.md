# Longest Duplicate Substring

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given a string `s`, consider all  *duplicated substrings* : (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.

Return  **any**  duplicated substring that has the longest possible length. If `s` does not have a duplicated substring, the answer is `""`.

 

 **Example 1:** 

```
Input: s = "banana"
Output: "ana"

```

 **Example 2:** 

```
Input: s = "abcd"
Output: ""

```

 

 **Constraints:** 

- 2 <= s.length <= 3 * 104
- s consists of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 256 ms (beats 47.12%)  
**Memory:** 156.2 MB (beats 22.29%)  
**Submitted:** 2026-11-06T19:38:11.350Z  

```java

class Solution {
    public String longestDupSubstring(String s) {
        int n = s.length();

        long mod = (1L << 31) - 1;
        long base = 26;
        
        long power = 1;

        for (int i = 0; i < n; i++) {
            power = (power * base) % mod;
        }

        int left = 1, right = n - 1;
        String ans = "";

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int index = search(s, mid, base, mod);

            if (index != -1) {
                ans = s.substring(index, index + mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private int search(String s, int len, long base, long mod) {
        int n = s.length();

        long hash = 0;
        long power = 1;

        for (int i = 0; i < len; i++) {
            hash = (hash * base + (s.charAt(i) - 'a')) % mod;
            power = (power * base) % mod;
        }

        java.util.HashMap<Long, java.util.ArrayList<Integer>> map =
            new java.util.HashMap<>();

        map.put(hash, new java.util.ArrayList<>());
        map.get(hash).add(0);

        for (int i = len; i < n; i++) {
            hash = (hash * base
                    - (s.charAt(i - len) - 'a') * power % mod
                    + mod) % mod;

            hash = (hash + (s.charAt(i) - 'a')) % mod;

            int start = i - len + 1;

            if (map.containsKey(hash)) {
                for (int prev : map.get(hash)) {
                    if (s.substring(prev, prev + len)
                          .equals(s.substring(start, start + len))) {
                        return start;
                    }
                }
            }

            map.computeIfAbsent(hash, k -> new java.util.ArrayList<>())
               .add(start);
        }

        return -1;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/longest-duplicate-substring/)