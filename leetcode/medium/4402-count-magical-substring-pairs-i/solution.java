class Solution {
    public int countMagicalPairs(String s) {
        int n = s.length();
        int ans = 0;
        for (int j = 0; j < n - 1; j++) {
            int[] left = new int[27];
            int[] right = new int[27];
            boolean[] seen = new boolean[26];
            int distinct = 0;
            for (int i = j; i >= 0; i--) {
                int c = s.charAt(i) - 'a';
                if (!seen[c]) {
                    seen[c] = true;
                    distinct++;
                }
                left[distinct]++;
            }
            seen = new boolean[26];
            distinct = 0;
            for (int k = j + 1; k < n; k++) {
                int c = s.charAt(k) - 'a';
                if (!seen[c]) {
                    seen[c] = true;
                    distinct++;
                }
                right[distinct]++;
            }
            for (int d = 1; d <= 26; d++) {
                ans += left[d] * right[d];
            }
        }
        return ans;
    }
}