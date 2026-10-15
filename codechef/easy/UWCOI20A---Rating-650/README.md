# UWCOI20A - Rating 650

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

### Find maximum in an Array

Given a list of $N$ integers, representing height of mountains. Find the height of the tallest mountain.

### Input:
- First line will contain $T$, number of testcases. Then the testcases follow.
- The first line in each testcase contains one integer, $N$.
- The following line contains $N$ space separated integers: the height of each mountains.
### Output:

For each testcase, output one line with one integer: the height of the tallest mountain for that test case.

### Constraints
- $1 \leq T \leq 10$
- $1 \leq N \leq 100000$
- $0 \leq$ height of each mountain $\leq 10^9$
### Sample 1:
Input
Output

```
1
5
4 7 6 3 1
```

```
7
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-10-15T14:09:33.418Z  

```java
import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner scanner = new Scanner(System.in);

        // Read the number of test cases
        int t = scanner.nextInt();

        // Process each test case
        while (t-- > 0) {
            // Read the size of the array
            int n = scanner.nextInt();

            // Create an array to hold the elements
            int[] arr = new int[n];

            // Read the array elements
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            // Find the maximum element
            int maximum = arr[0];
            for (int i = 1; i < n; i++) {
                if (arr[i] > maximum) {
                    maximum = arr[i];
                }
            }

            // Print the maximum element
            System.out.println(maximum);
        }
    }
}

```

---

[View on CodeChef](https://www.codechef.com/problems/UWCOI20A)