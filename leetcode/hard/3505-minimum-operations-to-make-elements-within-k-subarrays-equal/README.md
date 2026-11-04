# Minimum Operations to Make Elements Within K Subarrays Equal

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an integer array `nums` and two integers, `x` and `k`. You can perform the following operation any number of times (**including zero**):

- Increase or decrease any element of nums by 1.

Return the  **minimum**  number of operations needed to have  **at least**  `k`  *non-overlapping subarrays*  of size  **exactly**  `x` in `nums`, where all elements within each subarray are equal.

 

 **Example 1:** 

 **Input:**  nums = [5,-2,1,3,7,3,6,4,-1], x = 3, k = 2

 **Output:**  8

 **Explanation:** 

- Use 3 operations to add 3 to nums[1] and use 2 operations to subtract 2 from nums[3]. The resulting array is [5, 1, 1, 1, 7, 3, 6, 4, -1].
- Use 1 operation to add 1 to nums[5] and use 2 operations to subtract 2 from nums[6]. The resulting array is [5, 1, 1, 1, 7, 4, 4, 4, -1].
- Now, all elements within each subarray [1, 1, 1] (from indices 1 to 3) and [4, 4, 4] (from indices 5 to 7) are equal. Since 8 total operations were used, 8 is the output.

 **Example 2:** 

 **Input:**  nums = [9,-2,-2,-2,1,5], x = 2, k = 2

 **Output:**  3

 **Explanation:** 

- Use 3 operations to subtract 3 from nums[4]. The resulting array is [9, -2, -2, -2, -2, 5].
- Now, all elements within each subarray [-2, -2] (from indices 1 to 2) and [-2, -2] (from indices 3 to 4) are equal. Since 3 operations were used, 3 is the output.

 

 **Constraints:** 

- 2 <= nums.length <= 105
- -106 <= nums[i] <= 106
- 2 <= x <= nums.length
- 1 <= k <= 15
- 2 <= k * x <= nums.length

## Solution

**Language:** Java  
**Runtime:** 689 ms (beats 65.79%)  
**Memory:** 141.9 MB (beats 81.58%)  
**Submitted:** 2026-11-04T02:16:10.696Z  

```java

import java.util.*;

class Solution {

    class Median {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;

        void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                left.merge(val, 1, Integer::sum);
                leftSize++;
                leftSum += val;
            } else {
                right.merge(val, 1, Integer::sum);
                rightSize++;
                rightSum += val;
            }
            balance();
        }

        void remove(int val) {
            if (left.containsKey(val)) {
                erase(left, val);
                leftSize--;
                leftSum -= val;
            } else {
                erase(right, val);
                rightSize--;
                rightSum -= val;
            }
            balance();
        }

        void erase(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) map.remove(val);
            else map.put(val, count - 1);
        }

        void balance() {
            int target = (leftSize + rightSize + 1) / 2;

            while (leftSize > target) {
                int val = left.lastKey();
                erase(left, val);
                leftSize--;
                leftSum -= val;

                right.merge(val, 1, Integer::sum);
                rightSize++;
                rightSum += val;
            }

            while (leftSize < target) {
                int val = right.firstKey();
                erase(right, val);
                rightSize--;
                rightSum -= val;

                left.merge(val, 1, Integer::sum);
                leftSize++;
                leftSum += val;
            }
        }

        long cost() {
            long median = left.lastKey();

            return median * leftSize - leftSum
                 + rightSum - median * rightSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int m = n - x + 1;

        long[] costs = new long[m];
        Median median = new Median();

        // Calculate cost of every window
        for (int i = 0; i < n; i++) {
            median.add(nums[i]);

            if (i >= x) {
                median.remove(nums[i - x]);
            }

            if (i >= x - 1) {
                costs[i - x + 1] = median.cost();
            }
        }

        // Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                // Skip current element
                curr[i] = curr[i - 1];

                // Select a window of size x
                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + costs[i - x]
                    );
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/minimum-operations-to-make-elements-within-k-subarrays-equal/)