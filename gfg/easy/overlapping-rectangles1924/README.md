# Overlapping Rectangles

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given two rectangles, find if the given two rectangles overlap or not.

- A rectangle is denoted by providing the x and y coordinates of two points: the left top corner and the right bottom corner of the rectangle.
- Two rectangles sharing a side are considered overlapping. (l1[] and r1[] are the extreme points of the first rectangle and l2[] and r2[] are the extreme points of the second rectangle).

  Note:   It may be assumed that all sides of rectangles are parallel to the coordinate axis (2 sides parallel to X axis and other sides to Y axis).

 **Examples:** 

```
Input: l1[] = [0, 10], r1[] = [10, 0], l2[] = [5, 5], r2[] = [15, 0]
Output: true
Explanation: The rectangles overlap.
```

```
Input: l1[] = [0, 2], r1[] = [1, 1], l2[] = [-2, 0], r2[] = [0, -3]
Output: false
Explanation: The rectangles do not overlap.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-10-31T09:31:44.226Z  

```java
class Solution {
    static boolean doOverlap(int L1[], int R1[], int L2[], int R2[]) {

        // One rectangle is to the left of the other
        if (L1[0] > R2[0] || L2[0] > R1[0]) {
            return false;
        }

        // One rectangle is above the other
        if (R1[1] > L2[1] || R2[1] > L1[1]) {
            return false;
        }

        return true;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/overlapping-rectangles1924/1)