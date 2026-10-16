class Solution {
    private static final long MOD = 1_000_000_007L;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        long[] sum = new long[4 * n];
        long[] square = new long[4 * n];
        long[] lazy = new long[4 * n];

        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        int[] last = new int[maxVal + 1];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int left = last[nums[i]] + 1;
            last[nums[i]] = i;

            update(1, 0, n - 1, left, i,
                   sum, square, lazy);

            ans = (ans + square[1]) % MOD;
        }

        return (int) ans;
    }

    private void apply(int node, int l, int r,
                       long[] sum, long[] square,
                       long[] lazy, long val) {

        long len = r - l + 1L;

        square[node] = (
            square[node]
            + 2L * val % MOD * sum[node] % MOD
            + val * val % MOD * len % MOD
        ) % MOD;

        sum[node] = (sum[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void push(int node, int l, int r,
                      long[] sum, long[] square,
                      long[] lazy) {

        if (lazy[node] == 0 || l == r) {
            return;
        }

        int mid = l + (r - l) / 2;
        long val = lazy[node];

        apply(node * 2, l, mid, sum, square, lazy, val);

        apply(node * 2 + 1, mid + 1, r,
              sum, square, lazy, val);

        lazy[node] = 0;
    }

    private void update(int node, int l, int r,
                        int ql, int qr,
                        long[] sum, long[] square,
                        long[] lazy) {

        if (ql > r || qr < l) {
            return;
        }

        if (ql <= l && r <= qr) {
            apply(node, l, r, sum, square, lazy, 1L);
            return;
        }

        push(node, l, r, sum, square, lazy);

        int mid = l + (r - l) / 2;

        update(node * 2, l, mid, ql, qr,
               sum, square, lazy);

        update(node * 2 + 1, mid + 1, r, ql, qr,
               sum, square, lazy);

        sum[node] = (
            sum[node * 2] + sum[node * 2 + 1]
        ) % MOD;

        square[node] = (
            square[node * 2] + square[node * 2 + 1]
        ) % MOD;
    }
}
