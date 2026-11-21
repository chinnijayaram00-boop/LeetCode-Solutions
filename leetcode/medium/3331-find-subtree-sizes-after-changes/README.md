# Find Subtree Sizes After Changes

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a tree rooted at node 0 that consists of `n` nodes numbered from `0` to `n - 1`. The tree is represented by an array `parent` of size `n`, where `parent[i]` is the parent of node `i`. Since node 0 is the root, `parent[0] == -1`.

You are also given a string `s` of length `n`, where `s[i]` is the character assigned to node `i`.

We make the following changes on the tree  **one**  time  **simultaneously**  for all nodes `x` from `1` to `n - 1`:

- Find the closest node y to node x such that y is an ancestor of x, and s[x] == s[y].
- If node y does not exist, do nothing.
- Otherwise, remove the edge between x and its current parent and make node y the new parent of x by adding an edge between them.

Return an array `answer` of size `n` where `answer[i]` is the  **size**  of the subtree rooted at node `i` in the  **final**  tree.

 

Original
Java
 **Example 1:** 

 **Input:**  parent = [-1,0,0,1,1,1], s = "abaabc"

 **Output:**  [6,3,1,1,1,1]

 **Explanation:** 

The parent of node 3 will change from node 1 to node 0.

 **Example 2:** 

 **Input:**  parent = [-1,0,4,0,1], s = "abbba"

 **Output:**  [5,2,1,1,1]

 **Explanation:** 

The following changes will happen at the same time:

- The parent of node 4 will change from node 1 to node 0.
- The parent of node 2 will change from node 4 to node 1.

 

 **Constraints:** 

- n == parent.length == s.length
- 1 <= n <= 105
- 0 <= parent[i] <= n - 1 for all i >= 1.
- parent[0] == -1
- parent represents a valid tree.
- s consists only of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 88 ms (beats 88.37%)  
**Memory:** 188.2 MB (beats 93.02%)  
**Submitted:** 2026-11-21T03:38:25.406Z  

```java
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
```

---

[View on LeetCode](https://leetcode.com/problems/find-subtree-sizes-after-changes/)