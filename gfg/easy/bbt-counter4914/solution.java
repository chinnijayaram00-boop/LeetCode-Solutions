class Solution {
    public int countBT(int h) {
        long mod = 1000000007L;
        if (h <= 1) {
            return 1;
        }
        long a = 1, b = 1, c = 0;
        for (int i = 2; i <= h; i++) {
            c = (b * ((2 * a + b) % mod)) % mod;
            a = b;
            b = c;
        }
        return (int) b;
    }
}
