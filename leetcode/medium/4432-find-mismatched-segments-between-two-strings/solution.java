class Solution {
    public List<List<String>> findMismatchedSegments(String s1, String s2) {
        List<List<String>> ans = new ArrayList<>();
        int n = s1.length();
        int i = 0;
        while (i < n) {
            if (s1.charAt(i) == s2.charAt(i)) {
                i++;
                continue;
            }
            int start = i;
            while (i < n  && s1.charAt(i) != s2.charAt(i)) {
                i++;
            }
            List<String> segment = new ArrayList<>();
            segment.add(s1.substring(start, i));
            segment.add(s2.substring(start, i));
            ans.add(segment);
        }
        return ans;
    }
}