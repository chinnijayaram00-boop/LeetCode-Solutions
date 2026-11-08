class Solution {
    public int[] countSuffix(int[] nums, int[] target) {
        int n = nums.length;
        int m = target.length;
        int size = m + n + 1;
        int[] a = new int[size];
        for (int i = 0; i < n; i++) {
            a[i] = nums[i];
        }
        a[n] = 0;
        for (int i = 0; i < m; i++) {
            a[n + 1 + i] = target[i];
        }
        int[] z = new int[size];
        int l = 0, r = 0;
        for (int i = 1; i < size; i++) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            while (i + z[i] < size && a[z[i]] == a[i + z[i]]) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        int[] prev = new int[m];
        int last = -1;
        for (int i = 0; i < m; i++) {
            if (i + 1 < m && target[i] < target[i + 1]) {
                last = i;
            }
            prev[i] = last;
        }
        int MAX = 100001;
        int[] freq = new int[MAX];
        for (int x : nums) {
            freq[x]++;
        }
        for (int i = 0; i < n; i++) {
            freq[target[i]]--;
        }
        int mismatch = 0;
        for (int x = 1; x < MAX; x++) {
            if (freq[x] != 0) {
                mismatch++;
            }
        }
        int[] ans = new int[m - n + 1];
        for (int k = 0; k <= m - n; k++) {
            if (mismatch == 0) {
                int lcp = Math.min(n, z[n + 1 + k]);
                int lower = 0;
                if (n > 1) {
                    int p = prev[k + n - 2];
                    if (p >= k) {
                        lower = p - k + 1;
                    }
                }
                int upper = Math.min(lcp, n - 1);
                if (upper >= lower) {
                    ans[k] = upper - lower + 1;
                }
            }
            if (k < m - n) {
                int x = target[k];
                if (freq[x] == 0) mismatch++;
                freq[x]++;
                if (freq[x] == 0) mismatch--;
                int y = target[k + n];
                if (freq[y] == 0) mismatch++;
                freq[y]--;
                if (freq[y] == 0) mismatch--;
            }
        }
        return ans;
    }
}