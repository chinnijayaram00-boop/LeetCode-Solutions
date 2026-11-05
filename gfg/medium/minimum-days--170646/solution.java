class Solution {
    public int getMinimumDays(String s, int[] p) {
        int n = s.length();
        char[] strArray = s.toCharArray();
        int collisions = 0;
        for (int i = 0; i < n - 1; i++) {
            if (strArray[i] == strArray[i + 1]) {
                collisions++;
            }
        }
        if (collisions == 0) {
            return 0;
        }
        for (int day = 0; day < n; day++) {
            int idx = p[day];
            if (idx > 0 && strArray[idx] == strArray[idx - 1]) {
                collisions--;
            }
            if (idx < n - 1 && strArray[idx] == strArray[idx + 1]) {
                collisions--;
            }
            strArray[idx] = '?';
            if (collisions == 0) {
                return day + 1;
            }
        }
        return n;
    }
}