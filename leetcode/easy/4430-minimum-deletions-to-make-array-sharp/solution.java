class Solution {
    public int minDeletions(int[] nums) {
        int deletions = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                deletions++;
            }
        }
        return deletions;
    }
}