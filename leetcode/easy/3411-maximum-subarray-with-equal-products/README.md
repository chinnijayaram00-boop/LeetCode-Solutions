# Maximum Subarray With Equal Products

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

You are given an array of  **positive**  integers `nums`.

An array `arr` is called  **product equivalent**  if `prod(arr) == lcm(arr) * gcd(arr)`, where:

- prod(arr) is the product of all elements of arr.
- gcd(arr) is the GCD of all elements of arr.
- lcm(arr) is the LCM of all elements of arr.

Return the length of the  **longest**   **product equivalent**  subarray of `nums`.

 

Original
Java
 **Example 1:** 

 **Input:**  nums = [1,2,1,2,1,1,1]

 **Output:**  5

 **Explanation:**  

The longest product equivalent subarray is `[1, 2, 1, 1, 1]`, where `prod([1, 2, 1, 1, 1]) = 2`, `gcd([1, 2, 1, 1, 1]) = 1`, and `lcm([1, 2, 1, 1, 1]) = 2`.

 **Example 2:** 

 **Input:**  nums = [2,3,4,5,6]

 **Output:**  3

 **Explanation:**  

The longest product equivalent subarray is `[3, 4, 5].`

 **Example 3:** 

 **Input:**  nums = [1,2,3,1,4,5,1]

 **Output:**  5

 

 **Constraints:** 

- 2 <= nums.length <= 100
- 1 <= nums[i] <= 10

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 96.84%)  
**Memory:** 43.6 MB (beats 85.26%)  
**Submitted:** 2026-11-24T05:24:21.273Z  

```java

class Solution {

    public int maxLength(int[] nums) {

        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {

            long product = 1;
            int gcd = 0;
            int lcm = 1;

            for (int j = i; j < n; j++) {

                int x = nums[j];

                // Calculate product
                product *= x;

                // Calculate GCD
                gcd = findGCD(gcd, x);

                // Calculate LCM
                lcm = (lcm / findGCD(lcm, x)) * x;

                // Check product equivalent condition
                if (product == (long) gcd * lcm) {

                    ans = Math.max(ans, j - i + 1);
                }

                // Product can never become valid again
                if (product > 25200) {
                    break;
                }
            }
        }

        return ans;
    }

    // GCD using Euclidean algorithm
    private int findGCD(int a, int b) {

        while (b != 0) {

            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/maximum-subarray-with-equal-products/)