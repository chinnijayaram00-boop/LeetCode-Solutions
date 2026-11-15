class Solution {
    public int countConsistent(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int andVal = Integer.MAX_VALUE;
            int minVal = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                andVal &= nums[j];
                minVal = Math.min(minVal, nums[j]);
                if (andVal == minVal) {
                    count++;
                }
            }
        }
        return count;
    }
}