class Solution {
    public int countSuffix(int[] nums, int[] target) {
        int n = nums.length;
        int M = 100001;
        int[] freq = new int[M];
        int mismatch = 0;
        int firstMismatch = n;
        for (int i = 0; i < n; i++) {
            if (nums[i] != target[i]) {
                firstMismatch = Math.min(firstMismatch, i);
            }
        }
        int ans = 0;
        boolean sorted = true;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && target[i] < target[i + 1]) {
                sorted = false;
            }
            int a = nums[i];
            int b = target[i];
            if (freq[a] == 0) mismatch++;
            else if (freq[a] == -1) mismatch--;
            freq[a]++;
            if (freq[b] == 0) mismatch++;
            else if (freq[b] == 1) mismatch--;
            freq[b]--;
            if (i <= firstMismatch && mismatch == 0 && sorted) {
                ans++;
            }
        }
        return ans;
    }
}