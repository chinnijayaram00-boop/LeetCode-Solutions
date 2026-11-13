# Count Non-Decreasing Subarrays After K Operations

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an array `nums` of `n` integers and an integer `k`.

For each subarray of `nums`, you can apply  **up to**  `k` operations on it. In each operation, you increment any element of the subarray by 1.

 **Note**  that each subarray is considered independently, meaning changes made to one subarray do not persist to another.

Return the number of subarrays that you can make  **non-decreasing**  ​​​​​after performing at most `k` operations.

An array is said to be  **non-decreasing**  if each element is greater than or equal to its previous element, if it exists.

 

Original
Java
 **Example 1:** 

 **Input:**  nums = [6,3,1,2,4,4], k = 7

 **Output:**  17

 **Explanation:** 

Out of all 21 possible subarrays of `nums`, only the subarrays `[6, 3, 1]`, `[6, 3, 1, 2]`, `[6, 3, 1, 2, 4]` and `[6, 3, 1, 2, 4, 4]` cannot be made non-decreasing after applying up to k = 7 operations. Thus, the number of non-decreasing subarrays is `21 - 4 = 17`.

 **Example 2:** 

 **Input:**  nums = [6,3,1,3,6], k = 4

 **Output:**  12

 **Explanation:** 

The subarray `[3, 1, 3, 6]` along with all subarrays of `nums` with three or fewer elements, except `[6, 3, 1]`, can be made non-decreasing after `k` operations. There are 5 subarrays of a single element, 4 subarrays of two elements, and 2 subarrays of three elements except `[6, 3, 1]`, so there are `1 + 5 + 4 + 2 = 12` subarrays that can be made non-decreasing.

 

 **Constraints:** 

- 1 <= nums.length <= 105
- 1 <= nums[i] <= 109
- 1 <= k <= 109

## Solution

**Language:** Java  
**Runtime:** 563 ms (beats 7.14%)  
**Memory:** 195.8 MB (beats 28.57%)  
**Submitted:** 2026-11-13T05:26:29.163Z  

```java

class Solution {

    private long getCost(
        int l, int r,
        int[] nums,
        long[] prefix,
        int[][] next,
        long[][] blockSum,
        int n,
        int LOG
    ) {
        int cur = l;
        long totalMax = 0;

        // Jump through complete prefix-maximum blocks
        for (int j = LOG - 1; j >= 0; j--) {

            int nxt = next[j][cur];

            if (nxt <= r) {
                totalMax += blockSum[j][cur];
                cur = nxt;
            }
        }

        // Add the final incomplete block
        totalMax += (long) nums[cur] * (r - cur + 1);

        // Required increments = prefix maximum sum - original sum
        return totalMax - (prefix[r + 1] - prefix[l]);
    }

    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        int LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        // Step 1: Find next strictly greater element
        int[] nge = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {

            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            nge[i] = (top == -1) ? n : stack[top];

            stack[++top] = i;
        }

        // Step 2: Build binary lifting tables
        int[][] next = new int[LOG][n];
        long[][] blockSum = new long[LOG][n];

        for (int i = 0; i < n; i++) {
            next[0][i] = nge[i];

            blockSum[0][i] =
                (long) nums[i] * (nge[i] - i);
        }

        for (int j = 1; j < LOG; j++) {

            for (int i = 0; i < n; i++) {

                int mid = next[j - 1][i];

                if (mid < n) {
                    next[j][i] = next[j - 1][mid];

                    blockSum[j][i] =
                        blockSum[j - 1][i]
                        + blockSum[j - 1][mid];

                } else {
                    next[j][i] = n;
                    blockSum[j][i] = blockSum[j - 1][i];
                }
            }
        }

        // Step 3: Build prefix sums
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Step 4: Binary search for every left endpoint
        long ans = 0;

        for (int l = 0; l < n; l++) {

            int low = l;
            int high = n - 1;
            int best = l - 1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                long cost = getCost(
                    l, mid, nums, prefix,
                    next, blockSum, n, LOG
                );

                if (cost <= (long) k) {
                    best = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            ans += best - l + 1;
        }

        return ans;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/count-non-decreasing-subarrays-after-k-operations/)