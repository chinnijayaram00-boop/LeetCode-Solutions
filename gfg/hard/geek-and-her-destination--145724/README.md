# Geek's Destination with Special Jump

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Geek starts at position  **0**  on the positive X-axis and needs to reach or pass position  **d**  on the positive X-axis.

Geek normally follows a repeating movement pattern:

- Move forward by a units, taking a seconds.
- Move backward by b units, taking b seconds.

Geek also has a special jump that moves him exactly  **j**  units forward in 1 second. He can use it at any time, even in the middle of a forward or backward movement, and the journey ends immediately after the jump.

Given five integers  **a**,  **b**,  **d**,  **l**, and  **r**. For every integer j in the range [l, r], find the minimum time required for Geek to reach or pass position d. He may either use the special jump or reach the destination using normal movement alone, whichever takes less time.

Return the sum of the minimum times for all jump lengths from l to r. Since, the answer can be large, return modulo 109 + 7. 

 **Note:** Each jump length is considered independently.

 **Examples:** 

```
Input: a = 2, b = 1, d = 5, l = 3, r = 4
Output: 5
Explanation: 
For j = 3, Geek moves 2 units forward, then takes a jump of 3 units to reach the destination. The minimum time required is 3 seconds.
For j = 4, Geek moves 1 unit forward, then takes a jump of 4 units to reach the destination. The minimum time required is 2 seconds.
Total sum for the range [l, r] = 3 + 2 = 5.
```

```
Input: a = 4, b = 2, d = 8, l = 2, r = 4
Output: 26
Explanation:
For j = 2, Geek moves 4 units forward, then 2 units backward. She then moves 4 units forward again and takes a jump of 2 units to reach the destination. The minimum time required is 11 seconds.
For j = 3, Geek moves 4 units forward, then 2 units backward. She then moves 3 units forward and takes a jump of 3 units to reach the destination. The minimum time required is 10 seconds.
For j = 4, Geek moves 4 units forward, then takes a jump of 4 units to reach the destination. The minimum time required is 5 seconds.
Total sum for the range [l, r] = 11 + 10 + 5 = 26.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-11-24T20:23:15.787Z  

```java

class Solution {

    static final long MOD = 1000000007L;

    long timeAt(long q, long a, long b) {
        long diff = a - b;

        long k = 0;

        if (q > a) {
            k = (q - a + diff - 1) / diff;
        }

        return q + 2 * b * k;
    }

    long prefix(long n, long diff) {
        if (n <= 0) return 0;

        long m = n / diff;
        long s = n % diff;

        return diff * m * (m + 1) / 2
                + s * (m + 1);
    }

    long jumpSum(long L, long R, long a, long b) {
        if (L > R) return 0;

        long diff = a - b;

        long sumQ = (L + R) * (R - L + 1) / 2;

        long sumK = prefix(R - a, diff)
                  - prefix(L - a - 1, diff);

        return sumQ + 2 * b * sumK
                + (R - L + 1);
    }

    public int minTime(int a, int b, int d, int l, int r) {

        long normalTime = timeAt(d, a, b);

        long L = d - r;
        long R = d - l;

        long low = L;
        long high = R;

        long boundary = L - 1;

        while (low <= high) {

            long mid = low + (high - low) / 2;

            long jumpTime = timeAt(mid, a, b) + 1;

            if (jumpTime <= normalTime) {
                boundary = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        long ans = jumpSum(L, boundary, a, b);

        long count = R - boundary;

        ans += count * normalTime;

        return (int)(ans % MOD);
    }
}


```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/geek-and-her-destination--145724/1)