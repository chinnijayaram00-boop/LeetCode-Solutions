class Solution {
    static boolean isPerfect(int N) {
        if (N == 1) return false;

        int sum = 1;

        for (int i = 2; i * i <= N; i++) {
            if (N % i == 0) {
                sum += i;

                if (i != N / i) {
                    sum += N / i;
                }
            }
        }

        return sum == N;
    }
}