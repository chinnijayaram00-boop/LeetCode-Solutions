# Maximum Subarray Sum with One Square Allowed

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer array  **arr[]**, find the maximum subarray sum by squaring at most one element.

 **Examples:** 

```
Input: arr[] =  [-1, -2, 3, -2, 3]
Output: 10
Explanation: The subarray {3, -2, 3} with squaring -2 will give the maximum sum as 3 + 4 + 3 = 10.
```

```
Input: arr[] =  [-4, -4, -2, 5, 2, -1]
Output: 27
Explanation: The subarray {5, 2} with squaring 5 will give a maximum sum of 25 + 2 = 27.
```

## Solution

**Language:** JavaScript  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-11-06T15:06:16.507Z  

```js
/**
 * @param {number[]} arr
 * @return {number}
 */

class Solution {
    maximumSubarray(arr) {
        let noSquare = arr[0];
        let square = arr[0] * arr[0];
        let ans = Math.max(noSquare, square);

        for (let i = 1; i < arr.length; i++) {
            let x = arr[i];

            let newSquare = Math.max(
                square + x,
                noSquare + x * x,
                x * x
            );

            let newNoSquare = Math.max(noSquare + x, x);

            square = newSquare;
            noSquare = newNoSquare;

            ans = Math.max(ans, square, noSquare);
        }

        return ans;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/maximum-sum-subarray--143510/1)