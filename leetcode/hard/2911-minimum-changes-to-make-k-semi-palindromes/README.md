# Minimum Changes to Make K Semi-palindromes

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given a string `s` and an integer `k`, partition `s` into `k`  **substrings**  such that the letter changes needed to make each substring a  **semi-palindrome**  are minimized.

Return the  ***minimum**  number of letter changes *required*.*

A  **semi-palindrome**  is a special type of string that can be divided into  **palindromes**  based on a repeating pattern. To check if a string is a semi-palindrome:​

- Choose a positive divisor d of the string's length. d can range from 1 up to, but not including, the string's length. For a string of length 1, it does not have a valid divisor as per this definition, since the only divisor is its length, which is not allowed.
- For a given divisor d, divide the string into groups where each group contains characters from the string that follow a repeating pattern of length d. Specifically, the first group consists of characters at positions 1, 1 + d, 1 + 2d, and so on; the second group includes characters at positions 2, 2 + d, 2 + 2d, etc.
- The string is considered a semi-palindrome if each of these groups forms a palindrome.

Consider the string `"abcabc"`:

- The length of "abcabc" is 6. Valid divisors are 1, 2, and 3.
- For d = 1: The entire string "abcabc" forms one group. Not a palindrome.
- For d = 2: Group 1 (positions 1, 3, 5): "acb" Group 2 (positions 2, 4, 6): "bac" Neither group forms a palindrome.
- For d = 3: Group 1 (positions 1, 4): "aa" Group 2 (positions 2, 5): "bb" Group 3 (positions 3, 6): "cc" All groups form palindromes. Therefore, "abcabc" is a semi-palindrome.

 

Original
Java
 **Example 1:** 

 **Input:**  s = "abcac", k = 2

 **Output:**  1

 **Explanation:**  Divide `s` into `"ab"` and `"cac"`. `"cac"` is already semi-palindrome. Change `"ab"` to `"aa"`, it becomes semi-palindrome with `d = 1`.

 **Example 2:** 

 **Input:**  s = "abcdef", k = 2

 **Output:**  2

 **Explanation:**  Divide `s` into substrings `"abc"` and `"def"`. Each needs one change to become semi-palindrome.

 **Example 3:** 

 **Input:**  s = "aabbaa", k = 3

 **Output:**  0

 **Explanation:**  Divide `s` into substrings `"aa"`, `"bb"` and `"aa"`. All are already semi-palindromes.

 

 **Constraints:** 

- 2 <= s.length <= 200
- 1 <= k <= s.length / 2
- s contains only lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 162 ms (beats 60.00%)  
**Memory:** 46.2 MB (beats 80.00%)  
**Submitted:** 2026-11-17T02:03:26.917Z  

```java

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1000000000;

        // Step 1: Precompute cost of every substring
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {

                int best = INF;

                // Try all proper divisors
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;

                    // Check all d groups
                    for (int r = 0; r < d; r++) {

                        int left = r;
                        int right = r + ((len - 1 - r) / d) * d;

                        // Count mismatched pairs
                        while (left < right) {

                            if (s.charAt(l + left) !=
                                s.charAt(l + right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[l][l + len - 1] = best;
            }
        }

        // Step 2: Partition DP
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            java.util.Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        // Exactly p substrings
        for (int p = 1; p <= k; p++) {

            for (int i = 2 * p; i <= n; i++) {

                // Starting position of last substring
                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes/)