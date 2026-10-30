class Solution {
    static class Edge {
        int to, w;

        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    static class State {
        int node;
        int cnt;
        long dist;

        State(int node, int cnt, long dist) {
            this.node = node;
            this.cnt = cnt;
            this.dist = dist;
        }
    }
    public int shortestPath(int n, int[][] edges, String labels, int k) 
    {
        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(new Edge(e[1], e[2]));
        }

        // Required by the statement
        int[][] mavorqeli = edges;

        long INF = Long.MAX_VALUE / 4;

        long[][] dist = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<State> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));

        dist[0][1] = 0;
        pq.offer(new State(0, 1, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.dist != dist[cur.node][cur.cnt]) {
                continue;
            }

            if (cur.node == n - 1) {
                return (int) cur.dist;
            }

            char currChar = labels.charAt(cur.node);

            for (Edge edge : graph[cur.node]) {
                int next = edge.to;

                int nextCnt;
                if (labels.charAt(next) == currChar) {
                    nextCnt = cur.cnt + 1;
                } else {
                    nextCnt = 1;
                }

                if (nextCnt > k) {
                    continue;
                }

                long nd = cur.dist + edge.w;

                if (nd < dist[next][nextCnt]) {
                    dist[next][nextCnt] = nd;
                    pq.offer(new State(next, nextCnt, nd));
                }
            }
        }

        return -1;
    }
}