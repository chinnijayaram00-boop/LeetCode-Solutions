class Solution {
    public int subarrayXor(int[] arr) {
        int N = arr.length;
        if (N % 2 == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < N; i += 2) {
            ans ^= arr[i];
        }
        return ans;
    }
}