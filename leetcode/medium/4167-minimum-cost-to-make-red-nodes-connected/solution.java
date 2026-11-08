class Solution {
    public long minCost(int n, String colors, int[][] edges) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        int[] parent = new int[n];
        int[] order = new int[n];
        Arrays.fill(parent, -1);
        int idx = 0;
        order[idx++] = 0;
        for (int i = 0; i < idx; i++) {
            int u = order[i];
            for (int v : adj[u]) {
                if (v != parent[u]) {
                    parent[v] = u;
                    order[idx++] = v;
                }
            }
        }
        long[] size = new long[n];
        long[] dp = new long[n];
        long base = 0;
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;
            for (int v : adj[u]) {
                if (parent[v] == u) {
                    size[u] += size[v];
                }
            }
            long cost = size[u];
            if (colors.charAt(u) == 'R') {
                base += cost;
                dp[u] = -cost;
            } else {
                dp[u] = cost;
            }
            for (int v : adj[u]) {
                if (parent[v] == u) {
                    dp[u] += Math.min(0L, dp[v]);
                }
            }
        }
        long minDp = 0;
        for (int i = 0; i < n; i++) {
            minDp = Math.min(minDp, dp[i]);
        }
        return base + minDp;
    }
}