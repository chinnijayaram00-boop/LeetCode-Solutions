
class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1000000000;

        // Step 1: Precompute cost of every substring
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {

                int best = INF;

                // Try all proper divisors
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;

                    // Check all d groups
                    for (int r = 0; r < d; r++) {

                        int left = r;
                        int right = r + ((len - 1 - r) / d) * d;

                        // Count mismatched pairs
                        while (left < right) {

                            if (s.charAt(l + left) !=
                                s.charAt(l + right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[l][l + len - 1] = best;
            }
        }

        // Step 2: Partition DP
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            java.util.Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        // Exactly p substrings
        for (int p = 1; p <= k; p++) {

            for (int i = 2 * p; i <= n; i++) {

                // Starting position of last substring
                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
