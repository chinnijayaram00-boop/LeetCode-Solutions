class Solution {
    public long countConsistent(int[] nums) {
        int n = nums.length;
        int[] leftBad = new int[n];
        int[] rightBad = new int[n];
        int[] lastZero = new int[31];
        Arrays.fill(lastZero, -1);
        for (int i = 0; i < n; i++) {
            int bad = -1;
            for (int b = 0; b < 31; b++) {
                if (((nums[i] >> b) & 1) != 0) {
                    bad = Math.max(bad, lastZero[b]);
                } else {
                    lastZero[b] = i;
                }
            }
            leftBad[i] = bad;
        }
        int[] nextZero = new int[31];
        Arrays.fill(nextZero, n);
        for (int i = n - 1; i >= 0; i--) {
            int bad = n;
            for (int b = 0; b < 31; b++) {
                if (((nums[i] >> b) & 1) != 0) {
                    bad = Math.min(bad, nextZero[b]);
                } else {
                    nextZero[b] = i;
                }
            }
            rightBad[i] = bad;
        }
        int[] stack = new int[n];
        int top = 0;
        int[] prevLE = new int[n];
        int[] nextLess = new int[n];
        for (int i = 0; i < n; i++) {
            while (top > 0 && nums[stack[top - 1]] > nums[i]) {
                top--;
            }
            prevLE[i] = top > 0 ? stack[top - 1] : -1;
            stack[top++] = i;
        }
        top = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (top > 0 && nums[stack[top - 1]] >= nums[i]) {
                top--;
            }
            nextLess[i] = top > 0 ? stack[top - 1] : n;
            stack[top++] = i;
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int left = Math.max(prevLE[i], leftBad[i]);
            int right = Math.min(nextLess[i], rightBad[i]);
            ans += (long) (i - left) * (right - i);
        }
        return ans;
    }
}