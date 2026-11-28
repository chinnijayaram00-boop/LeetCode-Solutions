# Minimum Edges Between Two Vertices

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given an undirected graph represented by an adjacency list  **adj[][]** and two vertices  **u**  and  **v**, find the minimum number of edges in a path from vertex u to vertex v. If no path exists between the two vertices, return -1.

 **Examples:** 

```
Input: adj[][] = [[1], [0, 2], [1, 3], [2, 4, 5], [3, 5], [4, 3]], u = 2, v = 5

Output: 2
Explanation: The path between vertex 2 and vertex 5: 2 -> 3 -> 5, has 2 edges.

```

```
Input: adj[][] = [[2, 3], [2], [1, 0], [0], [5], [4]], u = 1, v = 5

Output: -1
Explanation: There is no path between the two vertices.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-11-28T19:19:42.653Z  

```java
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
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/minimum-number-of-edges-between-two-vertices/1)