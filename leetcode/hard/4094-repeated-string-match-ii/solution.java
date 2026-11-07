class Solution {
    public boolean repeatedStringMatch(String a, String b, int x, int y) {
        long sourceLen = (long) a.length() * x;
        long targetLen = (long) b.length() * y;
        if (targetLen > sourceLen) {
            return false;
        }
        int m = a.length();
        int n = b.length();
        int g = gcd(m, n);
        int limit = (int) Math.min(targetLen, m + n - g);
        long starts = Math.min(m, sourceLen - targetLen + 1);
        for (int start = 0; start < starts; start++) {
            boolean match = true;
            for (int i = 0; i < limit; i++) {
                if (a.charAt((start + i) % m) != b.charAt(i % n)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }
        return false;
    }
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}