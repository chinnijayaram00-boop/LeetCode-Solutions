/*class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        Arrays.sort(items, (a, b) -> a[0] - b[0]);
        boolean[] taken = new boolean[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (budget < items[i][0]) 
                continue;
            budget -= items[i][0];
            Queue<Integer> q = new LinkedList<>();
            if (!taken[i]) {
                taken[i] = true;
                total++;
                q.offer(i);
            }
            while (!q.isEmpty()) {
                int curr = q.poll();
                int factor = items[curr][1];
                for (int j = 0; j < n; j++) {
                    if (!taken[j] && items[j][1] % factor == 0) {
                    taken[j] = true;
                    total++;
                    q.offer(j);
                    }
                }
            }
        }
        return total;
   }
}*/
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] samples = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    samples[i]++;
                }
            }
        }
        int[] dp = new int[budget + 1];
        java.util.Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int cost = items[i][1];
            int val = 1 + samples[i];
            int[] nextDp = dp.clone();   
            for (int w = cost; w <= budget; w++) {
                if (dp[w - cost] != -1) {
                    nextDp[w] = Math.max(nextDp[w], dp[w - cost] + val);
                }
                if (nextDp[w - cost] != -1) {
                    nextDp[w] = Math.max(nextDp[w], nextDp[w - cost] + 1);
                }
            }
            dp = nextDp;
        }
        int max = 0;
        for (int i = 0; i <= budget; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}