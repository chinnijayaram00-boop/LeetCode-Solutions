# Character at Position in Infinite String

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a string  **s**  and an integer  **pos**, an infinite string is constructed by repeatedly applying the following operation. In the i-th step, append i consecutive $ characters, followed by the reverse of the current string, to the current string.

Formally, if the current string is s, update it as:

- s = s + ('$' repeated i times) + reverse(s)

The initial string is the given s, and the process continues indefinitely.

Find and return the character at the pos(1-based indexing) in the resulting infinite string.

 **Examples:** 

```
Input: s = "12345", pos = 3
Output: 3
Explanation: In "12345...", the character at pos 3 is 3.

```

```
Input: s = "12345", pos = 10
Output: 2
Explanation: In "12345$54321...", the character at pos 10 is 2.

```

```
Input: s = "abc", pos = 7
Output: a
Explanation: In "abc$cba...", the character at pos 7 is a.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-11-23T05:11:02.168Z  

```java

class Solution {

    public char posInfinite(String s, int pos) {

        long n = s.length();

        java.util.ArrayList<Long> len = new java.util.ArrayList<>();

        len.add(n);

        long i = 1;

        while (len.get(len.size() - 1) < pos) {
            long L = len.get(len.size() - 1);
            len.add(2 * L + i);
            i++;
        }

        for (int j = len.size() - 1; j >= 1; j--) {

            long L = len.get(j - 1);

            if (pos <= L) {
                continue;
            }
            else if (pos <= L + j) {
                return '$';
            }
            else {
                pos = (int)(2 * L + j - pos + 1);
            }
        }

        return s.charAt(pos - 1);
    }
}


```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/the-infinite-string1049/1)