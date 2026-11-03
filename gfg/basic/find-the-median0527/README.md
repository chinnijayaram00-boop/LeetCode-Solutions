# Median of Array

![Difficulty](https://img.shields.io/badge/Difficulty-Basic-red)

## Problem

Given an array  **arr[]**  of integers, calculate the median.

 **Examples:** 

```
Input: arr[] = [90, 100, 78, 89, 67]
Output: 89
Explanation: After sorting the array middle element is the median 

```

```
Input: arr[] = [56, 67, 30, 79]
Output: 61.5
Explanation: In case of even number of elements, average of two middle elements is the median. 

```

```
Input: arr[] = [1, 2]
Output: 1.5
Explanation: The average of both elements will result in 1.5.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-11-03T00:54:19.637Z  

```java

import java.util.Arrays;

class Solution {
    public double findMedian(int[] arr) {
        Arrays.sort(arr);

        int n = arr.length;

        if (n % 2 == 1) {
            return arr[n / 2];
        }

        return (arr[n / 2 - 1] + arr[n / 2]) / 2.0;
    }
}

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/find-the-median0527/1)