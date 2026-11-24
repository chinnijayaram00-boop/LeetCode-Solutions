
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

