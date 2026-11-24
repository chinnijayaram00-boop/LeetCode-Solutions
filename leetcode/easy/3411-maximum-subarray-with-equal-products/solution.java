
class Solution {

    public int maxLength(int[] nums) {

        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {

            long product = 1;
            int gcd = 0;
            int lcm = 1;

            for (int j = i; j < n; j++) {

                int x = nums[j];

                // Calculate product
                product *= x;

                // Calculate GCD
                gcd = findGCD(gcd, x);

                // Calculate LCM
                lcm = (lcm / findGCD(lcm, x)) * x;

                // Check product equivalent condition
                if (product == (long) gcd * lcm) {

                    ans = Math.max(ans, j - i + 1);
                }

                // Product can never become valid again
                if (product > 25200) {
                    break;
                }
            }
        }

        return ans;
    }

    // GCD using Euclidean algorithm
    private int findGCD(int a, int b) {

        while (b != 0) {

            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}
