class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals[i][0];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int target = intervals[i][1];
            int low = 0, high = n - 1;
            int result = -1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (arr[mid][0] >= target) {
                    result = arr[mid][1];
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            ans[i] = result;
        }
        return ans;
    }
}