class Solution {
    public long maxDifference(int[] nums) {
        int n = nums.length;
        long next1 = 0;
        long next2 = 0;
        for (int i = n - 1; i >= 0; i--) {
            long takeOne = nums[i];
            if (takeOne % 3 == 0) {
                takeOne -= next1;
            } else {
                takeOne += next1;
            }
            long best = takeOne;
            if (i + 1 < n) {
                long sum = (long) nums[i] + nums[i + 1];
                long takeTwo;
                if (sum % 3 == 0) {
                    takeTwo = sum - next2;
                } else {
                    takeTwo = sum + next2;
                }
                best = Math.max(best, takeTwo);
            }
            next2 = next1;
            next1 = best;
        }
        return next1;
    }
}