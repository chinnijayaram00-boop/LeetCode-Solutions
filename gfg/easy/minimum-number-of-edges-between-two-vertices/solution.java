class Solution {
    public int minEdges(ArrayList<ArrayList<Integer>> adj, int u, int v) {

        int V = adj.size();

        boolean[] visited = new boolean[V];
        int[] dist = new int[V];

        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();

        q.add(u);
        visited[u] = true;
        dist[u] = 0;

        while (!q.isEmpty()) {

            int node = q.poll();

            if (node == v) {
                return dist[node];
            }

            for (int neighbor : adj.get(node)) {

                if (!visited[neighbor]) {

                    visited[neighbor] = true;
                    dist[neighbor] = dist[node] + 1;

                    q.add(neighbor);
                }
            }
        }

        return -1;
    }
}