class Solution {
    public int minOperations(int[] nums, int[] locked, int k) {
        int n = nums.length;
        int maxPrefix = 0;
        int unlocks = 0;
        for (int i = 0; i < n; i++) {
            maxPrefix = Math.max(maxPrefix, nums[i]);
            boolean validBoundary = nums[i] == i + 1 && maxPrefix == i + 1;
            if (locked[i] == 1 && !validBoundary) {
                unlocks++;
            }
        }
        if (unlocks > k) {
            return -1;
        }
        int[] bit = new int[n + 1];
        int swaps = 0;
        for (int i = n - 1;i >= 0; i--) {
            int x = nums[i];
            int j = x - 1;
            while (j > 0) {
                swaps += bit[j];
                j -= j & -j;
            }
            j = x;
            while (j <= n) {
                bit[j]++;
                j += j & -j;
            }
        }
        return unlocks + swaps;
    }
}