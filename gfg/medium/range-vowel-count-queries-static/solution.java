class Solution {
    public ArrayList<Integer> countVowels(String s, int[][] queries) {
        int n = s.length();
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (isVowel(s.charAt(i)) ? 1 : 0);
        }
        int q = queries.length;
       // int[] result = new int[q];
       ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            result.add(prefix[r + 1] - prefix[l]);
        }
        return result;
    }
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e'|| c == 'i' || c == 'o' || c == 'u';
    }
}