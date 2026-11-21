class Solution {
    int[] answer;
    int[] newParent;
    int[][] children;
    int[] count;
    int n;
    public int[] findSubtreeSizes(int[] parent, String s) {
        n = parent.length;
        answer = new int[n];
        newParent = new int[n];
        int[] degree = new int[n];
        for (int i = 1; i < n; i++) {
            degree[parent[i]]++;
        }
        children = new int[n][];
        for (int i = 0; i < n; i++) {
            children[i] = new int[degree[i]];
        }
        int[] index = new int[n];
        for (int i = 1; i < n; i++) {
            int p = parent[i];
            children[p][index[p]++] = i;
        }
        int[] last = new int[26];
        java.util.Arrays.fill(last, -1);
        dfsParent(0, parent, s, last);
        java.util.Arrays.fill(degree, 0);
        for (int i = 1; i < n; i++) {
            degree[newParent[i]]++;
        }
        children = new int[n][];
        for (int i = 0; i < n; i++) {
            children[i] = new int[degree[i]];
        }
        java.util.Arrays.fill(index, 0);
        for (int i = 1; i < n; i++) {
            int p = newParent[i];
            children[p][index[p]++] = i;
        }
        dfsSize(0);
        return answer;
    }
    private void dfsParent(int node, int[] parent, String s, int[] last) {
        int ch = s.charAt(node) - 'a';
        int previous = last[ch];
        newParent[node] = (previous == -1) ? parent[node] : previous;
        last[ch] = node;
        for (int child : children[node]) {
            dfsParent(child, parent, s, last);
        }
        last[ch] = previous;
    }
    private int dfsSize(int node) {
        int size = 1;
        for (int child : children[node]) {
            size += dfsSize(child);
        }
        answer[node] = size;
        return size;
    }
}