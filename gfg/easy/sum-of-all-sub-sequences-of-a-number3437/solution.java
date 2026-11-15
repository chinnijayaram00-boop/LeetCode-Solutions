
class Solution {
    public int subsequenceSum(String s) {
        int MOD = 1000000007;
        int n = s.length();

        long sum = 0;

        for (int i = 0; i < n; i++) {
            sum = (sum + (s.charAt(i) - '0')) % MOD;
        }

        long power = 1;

        for (int i = 1; i < n; i++) {
            power = (power * 2) % MOD;
        }

        return (int) ((sum * power) % MOD);
    }
}
