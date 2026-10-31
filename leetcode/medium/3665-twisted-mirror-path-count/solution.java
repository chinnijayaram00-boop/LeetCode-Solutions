class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        int[] down = new int[n];
        down[0] = 1;

        int right = 0;

        for (int i = 0; i < m; i++) {
            right = 0;

            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {
                    int temp = right;
                    right = down[j];
                    down[j] = temp;
                } else {
                    int ways = (right + down[j]) % MOD;

                    right = ways;
                    down[j] = ways;
                }
            }
        }

        return right;
    }
}

