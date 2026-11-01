/*
class Solution {
    private int smallestPrimeFactor(int x) {
        if (x % 2 == 0) {
            return 2;
        }
        for (int i = 33; (long) i * i <= x; i += 2) {
            if (x % i == 0) {
                return i;
            }
        }
        return x;
    }
    public long minimumMaxValue(int n, int index, int value) {
        int right = n - 1 - index;
        if (value == 1 && (index > 0 || right > 0)) {
            return -1L;
        }
        int p = smallestPrimeFactor(value);
        if (index > 0) {
            if (value == p) {
                return -1L;
            }
            long smallest = (long) value - p - 2L * (index - 1);
            if (smallest <= 0) {
                return -1L;
            }
        }
        long ans = value;
        if (right > 0) {
            ans += p + 2L * (right - 1);
        }
        return ans;
    }
}*/
class Solution {
    public long minimumMaxValue(int n, int index, int value) {
        if (value == 1) {
            return -1L;
        }
        int p = value;
        if (value % 2 == 0) {
            p = 2;
        } else {
            for (int i = 3; i <= value / i; i += 2) {
                if (value % i == 0) {
                    p = i;
                    break;
                }
            }
        }
        if (index > 0) {
            if (p == value) {
                return -1L;
            }
            long first = (long) value - p - 2L * (index - 1);
            if (first <= 0) {
                return -1L;
            }
        }
        long right = n - 1L - index;
        return value + (right == 0 ? 0L : p + 2L * (right - 1));
    }
}