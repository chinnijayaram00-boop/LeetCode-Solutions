/*class Solution {
    public int minActivationCost(int[][] edges, int[] values) {
        int n = values.length;
        if (n < 2) return -1;
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int m = edges.length;
        int[] to = new int[2 * m];
        int[] next = new int[2 * m];
        int[] weight = new int[2 * m];
        int idx = 0;
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            to[idx] = v;
            weight[idx] = w;
            next[idx] = head[u];
            head[u] = idx++;
            to[idx] = u;
            weight[idx] = w;
            next[idx] = head[v];
            head[v] = idx++;
        }
        int[] parent = new int[n];
        int[] parentCost = new int[n];
        int[] order = new int[n];
        Arrays.fill(parent, -1);
        int count = 0;
        int[] stack = new int[n];
        int top = 0;
        stack[top++] = 0;
        parent[0] = 0;
        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                if (v == parent[u]) continue;
                parent[v] = u;
                parentCost[v] = weight[e];
                stack[top++] = v;
            }
        }
        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[n][2];
        long answer = INF;
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            long[] best = {INF, INF};
            int parity = Integer.bitCount(values[u]) & 1;
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                if (parent[v] != u) continue;
                long cost = weight[e];
                long d0 = dp[v][0] + cost;
                long d1 = dp[v][1] + cost;
                int p0 = parity;
                int p1 = parity ^ 1;
                long x0 = p0 == 0 ? d0 : d1;
                long x1 = p1 == 0 ? d0 : d1;
                if (x1 < INF && best[1] < INF) {
                    answer = Math.min(answer, x0 = best[1]);
                }
                if (x1 < INF && best[0] < INF) {
                    answer = Math.min(answer, x1 + best[0]);
                }
                best[0] = Math.min(best[0], x0);
                best[1] = Math.min(best[1], x1);
            }
            dp[u][0] = best[0];
            dp[u][1] = best[1];
        }
        return answer == INF ? -1 : (int) answer;
    }
}*/
class Solution {
    public int minActivationCost(int[][] edges, int[] values) {

        int n = values.length;

        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            graph[e[1]].add(new int[]{e[0], e[2]});
        }

        int[] parent = new int[n];
        int[] order = new int[n];
        int[] parentCost = new int[n];

        Arrays.fill(parent, -1);

        int size = 0;
        order[size++] = 0;
        parent[0] = 0;

        for (int i = 0; i < size; i++) {
            int u = order[i];

            for (int[] e : graph[u]) {
                int v = e[0];

                if (v == parent[u]) continue;

                parent[v] = u;
                parentCost[v] = e[1];
                order[size++] = v;
            }
        }

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n][2];

        long ans = INF;

        for (int i = n - 1; i >= 0; i--) {

            int u = order[i];

            int bit = Integer.bitCount(values[u]) & 1;

            long[] best = {INF, INF};

            best[bit] = 0;

            for (int[] e : graph[u]) {

                int v = e[0];

                if (parent[v] != u) continue;

                long[] current = {INF, INF};

                for (int p = 0; p < 2; p++) {

                    if (dp[v][p] == INF) continue;

                    int q = p ^ bit;

                    current[q] = Math.min(
                        current[q],
                        dp[v][p] + e[1]
                    );
                }

                for (int p = 0; p < 2; p++) {

                    int need = p ^ bit;

                    if (best[p] != INF &&
                        current[need] != INF) {

                        ans = Math.min(
                            ans,
                            best[p] + current[need]
                        );
                    }
                }

                for (int p = 0; p < 2; p++) {
                    best[p] = Math.min(best[p], current[p]);
                }
            }

            dp[u][0] = best[0];
            dp[u][1] = best[1];
        }

        return ans == INF ? -1 : (int) ans;
    }
}