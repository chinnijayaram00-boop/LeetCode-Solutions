/*class Solution {
    public int sequence(int n) {
        /
        
    }
};*/
class Solution {
    static int sequence(int n) {
        long mod = 1000000007L;
        long sum = 0;
        long num = 1;

        for (int i = 1; i <= n; i++) {
            long product = 1;

            for (int j = 1; j <= i; j++) {
                product = (product * num) % mod;
                num++;
            }

            sum = (sum + product) % mod;
        }

        return (int) sum;
    }
}