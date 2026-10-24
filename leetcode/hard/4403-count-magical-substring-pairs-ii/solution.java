/*
class Solution {
    public long countMagicalPairs(String s) {
        int n = s.length();
        int[][] leftCounts = new int[n][27];
        long ans = 0;
        for (int d = 1; d <= 26; d++) {
            int[] freq1 = new int[26];
            int[] freq2 = new int[26];
            int l1 = 0, l2 = 0;
            int distinct1 = 0, distinct2 = 0;
            for (int r = 0; r < n; r++) {
                int c = s.charAt(r) - 'a';
                if (freq1[c]++ == 0) distinct1++;
                if (freq2[c]++ == 0) distinct2++;
                while (distinct1 > d) {
                    if (--freq1[s.charAt(n - 1 - l1) - 'a'] == 0)
                        distinct1--;
                    l1++;
                }
                while (distinct2 > d - 1) {
                    if (--freq2[s.charAt(n - 1 - l2) - 'a'] == 0)
                        distinct2--;
                    l2++;
                }
                int start = n - 1 - r;
                int rightCount = l2 - l1;
                if (start > 0) {
                    ans += (long) leftCounts[start - 1][d] * rightCount;
                }
            }
        }
        return ans;
    }
}*/
class Solution {
    public long countMagicalPairs(String s) {
        int n = s.length();
        String rev = new StringBuilder(s).reverse().toString();
        long ans = 0;
        for (int d = 1; d <= 26; d++) {
            int[] left = exactEnding(s, d);
            int[] right = exactEnding(rev, d);
            for (int j = 0; j < n - 1; j++) {
                ans += (long) left[j] * right[n - j - 2];
            }
        }
        return ans;
    }
    private int[] exactEnding(String s, int k) {
        int n = s.length();
        int[] result = new int[n];
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        int l1 = 0, l2 = 0;
        int distinct1 = 0, distinct2 = 0;
        for (int r = 0; r < n; r++) {
            int c = s.charAt(r) - 'a';
            if (freq1[c]++ == 0) distinct1++;
            if (freq2[c]++ == 0) distinct2++;
            while (distinct1 > k) {
                if (--freq1[s.charAt(l1) - 'a'] == 0) {
                    distinct1--;
                }
                l1++;
            }
            while (distinct2 > k - 1) {
                if (--freq2[s.charAt(l2) - 'a'] == 0) {
                    distinct2--;
                }
                l2++;
            }
            result[r] = l2 - l1;
        }
        return result;
    }
}