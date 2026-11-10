# Palindrome Pairs

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a  **0-indexed**  array of  **unique**  strings `words`.

A  **palindrome pair**  is a pair of integers `(i, j)` such that:

- 0 <= i, j < words.length,
- i != j, and
- words[i] + words[j] (the concatenation of the two strings) is a palindrome.

Return  *an array of all the  **palindrome pairs**  of* `words`.

You must write an algorithm with `O(sum of words[i].length)` runtime complexity.

 

Original
Java
 **Example 1:** 

```
Input: words = ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]]
Explanation: The palindromes are ["abcddcba","dcbaabcd","slls","llssssll"]

```

 **Example 2:** 

```
Input: words = ["bat","tab","cat"]
Output: [[0,1],[1,0]]
Explanation: The palindromes are ["battab","tabbat"]

```

 **Example 3:** 

```
Input: words = ["a",""]
Output: [[0,1],[1,0]]
Explanation: The palindromes are ["a","a"]

```

 

 **Constraints:** 

- 1 <= words.length <= 5000
- 0 <= words[i].length <= 300
- words[i] consists of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 1358 ms (beats 36.57%)  
**Memory:** 60.3 MB (beats 88.81%)  
**Submitted:** 2026-11-10T13:50:47.301Z  

```java
import java.util.*;

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = word.length();

            for (int j = 0; j <= len; j++) {

                String left = word.substring(0, j);
                String right = word.substring(j);

                // Case 1: Left part is palindrome
                if (isPalindrome(left)) {
                    String reversed = new StringBuilder(right)
                            .reverse().toString();

                    Integer index = map.get(reversed);

                    if (index != null && index != i) {
                        result.add(Arrays.asList(index, i));
                    }
                }

                // Case 2: Right part is palindrome
                if (j < len && isPalindrome(right)) {
                    String reversed = new StringBuilder(left)
                            .reverse().toString();

                    Integer index = map.get(reversed);

                    if (index != null && index != i) {
                        result.add(Arrays.asList(i, index));
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/palindrome-pairs/)