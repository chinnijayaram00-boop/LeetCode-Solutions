
class Solution {
    public String longestDupSubstring(String s) {
        int n = s.length();

        long mod = (1L << 31) - 1;
        long base = 26;
        
        long power = 1;

        for (int i = 0; i < n; i++) {
            power = (power * base) % mod;
        }

        int left = 1, right = n - 1;
        String ans = "";

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int index = search(s, mid, base, mod);

            if (index != -1) {
                ans = s.substring(index, index + mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private int search(String s, int len, long base, long mod) {
        int n = s.length();

        long hash = 0;
        long power = 1;

        for (int i = 0; i < len; i++) {
            hash = (hash * base + (s.charAt(i) - 'a')) % mod;
            power = (power * base) % mod;
        }

        java.util.HashMap<Long, java.util.ArrayList<Integer>> map =
            new java.util.HashMap<>();

        map.put(hash, new java.util.ArrayList<>());
        map.get(hash).add(0);

        for (int i = len; i < n; i++) {
            hash = (hash * base
                    - (s.charAt(i - len) - 'a') * power % mod
                    + mod) % mod;

            hash = (hash + (s.charAt(i) - 'a')) % mod;

            int start = i - len + 1;

            if (map.containsKey(hash)) {
                for (int prev : map.get(hash)) {
                    if (s.substring(prev, prev + len)
                          .equals(s.substring(start, start + len))) {
                        return start;
                    }
                }
            }

            map.computeIfAbsent(hash, k -> new java.util.ArrayList<>())
               .add(start);
        }

        return -1;
    }
}
