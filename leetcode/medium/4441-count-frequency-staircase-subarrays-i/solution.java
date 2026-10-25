class Solution {
    public int countStaircase(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            HashMap<Integer, Integer> rank = new HashMap<>();
            HashMap<Integer, Integer> freq = new HashMap<>();
            int distinct = 0;
            int bad = 0;
            for (int j = i; j < n; j++) {
                int x = nums[j];
                if (!rank.containsKey(x)) {
                    distinct++;
                    rank.put(x, distinct);
                    freq.put(x, 0);
                }
                int r = rank.get(x);
                int old = freq.get(x);
                if (old > 0 && old != r) {
                    bad--;
                }
                int now = old + 1;
                freq.put(x, now);
                if (now != r) {
                    bad++;
                }
                if (bad == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
}