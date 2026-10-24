class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] < minK || nums[right] > maxK) {
                left = right + 1;
            }
            ans += right - left + 1;
        }
        return ans;
    }
}