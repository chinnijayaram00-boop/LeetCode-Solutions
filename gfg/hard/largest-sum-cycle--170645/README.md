# Largest Sum Cycle

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given an array   edge[]   of size   n , there are   n   cells numbered from   0   to   n - 1 . Each cell has at most one outgoing edge.

For every cell i:

- edge[i] is the cell reached from i in one step.
- edge[i] = -1 means that cell i has no outgoing edge.

A cycle is a sequence of cells where, by following the outgoing edges, we eventually return to the starting cell. The sum of a cycle is the sum of the indices of all cells in that cycle.

Return the maximum cycle sum. If there is no cycle, return -1.

  Examples:  

```
Input: edge[] = [1, 2, 0, -1]
Output: 3
Explanation: There is only one cycle in the graph i.e. 0 -> 1 -> 2 -> 0. Sum of the cell index in that cycle = 0 + 1 + 2 = 3.

```

```
Input: edge[] = [2, 0, -1, 2]
Output: -1
Explanation: There is no cycle in the graph. The edges are 1 -> 0 -> 2 <- 3. Hence, 2 has no outgoing edge, so no cycle exists.

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-11-06T14:42:26.079Z  

```java
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
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/largest-sum-cycle--170645/1)