class Solution {
    public long countValidSubstrings(String s) {
        long ans = 0;
        int lastBad = -1;
        for (int i = 0; i < s.length(); i++) {
            if (i >= 2 && s.charAt(i - 2) == 'a' && s.charAt(i - 1) == 'b' && s.charAt(i) == 'c') {
                lastBad = i - 2;
            }
            ans += i - lastBad;
        }
        return ans;
    }
}