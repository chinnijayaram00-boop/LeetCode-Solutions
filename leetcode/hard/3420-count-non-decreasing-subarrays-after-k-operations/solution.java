
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
