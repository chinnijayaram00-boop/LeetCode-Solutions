class Solution {
    public int minTime(int n, int s1, int s2) {
        long low = 1;
        long high = (long) n * Math.min(s1, s2);
        while (low < high) {
            long mid = low + (high - low) / 2;
            long total = mid / s1 + mid / s2;
            if (total >= n) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return (int) low;
    }
}