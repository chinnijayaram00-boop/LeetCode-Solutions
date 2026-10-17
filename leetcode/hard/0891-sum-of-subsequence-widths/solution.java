class Solution {
    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);

        long mod = 1_000_000_007L;
        long ans = 0;
        long power = 1;

        for (int i = 0; i < nums.length; i++) {
            long contribution = (nums[i] * power) % mod;
            ans = (ans + contribution) % mod;

            long subtract = (nums[i] * power) % mod;
            ans = (ans - subtract + mod) % mod;

            power = (power * 2) % mod;
        }

        power = 1;
        ans = 0;

        for (int i = 0; i < nums.length; i++) {
            long maxContribution = (nums[i] * power) % mod;
            long minContribution = (nums[nums.length - 1 - i] * power) % mod;
            ans = (ans + maxContribution - minContribution + mod) % mod;
            power = (power * 2) % mod;
        }

        return (int) ans;
    }
}
