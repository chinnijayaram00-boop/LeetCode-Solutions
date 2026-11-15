class Solution {
    public int maxScore(int[] parent, int[] op, int[] score) {
        int n = parent.length;
        int[] head = new int[n];
        int[] next = new int[n];
        Arrays.fill(head, -1);
        for (int i = 1; i < n; i++) {
            int p = parent[i];
            next[i] = head[p];
            head[p] = i;
        }
        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0;
        int size = 0;
        stack[top++] = 0;
        while (top > 0) {
            int u = stack[--top];
            order[size++] = u;
            for (int v = head[u]; v != -1; v = next[v]) {
                stack[top++] = v;
            }
        }
        long[] dp = new long[n];
        for (int i = size - 1; i >= 0; i--) {
            int u = order[i];
            long sum = 0;
            long minChild = Long.MAX_VALUE;
            for (int v = head[u]; v != -1; v = next[v]) {
                sum += dp[v];
                minChild = Math.min(minChild, dp[v]);
            }
            if (minChild == Long.MAX_VALUE) {
                dp[u] = score[u];
            } else if (op[u] == 1) {
                dp[u] = score[u] + sum - minChild;
            } else {
                dp[u] = score[u] + sum - Math.min(0L, minChild);
            }
        }
        return (int) dp[0];
        
    }
}