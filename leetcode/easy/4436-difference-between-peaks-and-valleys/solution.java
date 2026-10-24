class Solution {
    public int peakValleyDifference(int[] nums) {
        int sum = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                sum += nums[i];
            } else if (nums[i] < nums[i - 1] && nums[i] < nums[i + 1]) {
                sum -= nums[i];
            }
        }
        return sum;
    }
}