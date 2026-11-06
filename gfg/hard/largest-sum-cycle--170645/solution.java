class Solution {
    public int largestSumCycle(int[] edge) {
        int n = edge.length;
        boolean[] visited = new boolean[n];
        int[] pathIndex = new int[n];
        Arrays.fill(pathIndex, -1);
        long maxSum = -1;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            int node = i;
            int index = 0;
            ArrayList<Integer> path = new ArrayList<>();
            while (node != -1 && !visited[node]) {
                visited[node] = true;
                pathIndex[node] = index++;
                path.add(node);
                node = edge[node];
            }
            if (node != -1 && pathIndex[node] != -1) {
                long sum = 0;
                for (int j = pathIndex[node]; j < path.size(); j++) {
                    sum += path.get(j);
                }
                maxSum = Math.max(maxSum, sum);
            }
            for (int v : path) {
                pathIndex[v] = -1;
            }
        }
        return (int) maxSum;
    }
}