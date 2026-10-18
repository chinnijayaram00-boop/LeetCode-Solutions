class Solution {
    public int findColoredRoot(int[][] edges, String colors) {
        int n = colors.length();
        int[] indegree = new int[n];
        int[] children = new int[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            char a = colors.charAt(u);
            char b = colors.charAt(v);
            if (nextColor(a) == b) {
                indegree[v]++;
                children[u]++;
            } else if (nextColor(b) == a) {
                indegree[u]++;
                children[v]++;
            } else {
                return -1;
            }
            if (indegree[u] > 1 || indegree[v] > 1 || children[u] > 2 || children[v] > 2) {
                return -1;
            }
        }
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                if (root != -1) {
                    return -1;
                }
                root = i;
            }
        }
        return root;
    }
    private char nextColor(char c) {
        if (c == 'R') return 'B';
        if (c == 'B') return 'W';
        return 'R';
    }
}