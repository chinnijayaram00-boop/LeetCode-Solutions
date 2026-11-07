class Solution {
    public long countValidSubstrings(String s) {
        int n = s.length();
        int lastA = -1;
        int latestAWithB = -1;
        int lastBad = -1;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                lastA = i;
            } else if (ch == 'b') {
                if (lastA != -1) {
                    latestAWithB = lastA;
                }
            }
            else if (ch == 'c') {
                if (latestAWithB != -1) {
                    lastBad = Math.max(lastBad, latestAWithB);
                }
            }
            ans += i - lastBad;
        }
        return ans;
    }
}