# Word Search II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given an `m x n` `board` of characters and a list of strings `words`, return  *all words on the board*.

Each word must be constructed from letters of sequentially adjacent cells, where  **adjacent cells**  are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

 **Example 1:** 

```
Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]

```

 **Example 2:** 

```
Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []

```

 

 **Constraints:** 

- m == board.length
- n == board[i].length
- 1 <= m, n <= 12
- board[i][j] is a lowercase English letter.
- 1 <= words.length <= 3 * 104
- 1 <= words[i].length <= 10
- words[i] consists of lowercase English letters.
- All the strings of words are unique.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.1 MB  
**Submitted:** 2026-11-03T16:46:47.046Z  

```java

class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    TrieNode root = new TrieNode();

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        // Build Trie
        for (String word : words) {
            TrieNode node = root;

            for (char ch : word.toCharArray()) {
                int index = ch - 'a';

                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }

                node = node.children[index];
            }

            node.word = word;
        }

        // DFS on every cell
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int i, int j,
                     TrieNode node, List<String> result) {

        if (i < 0 || j < 0 || i >= board.length ||
            j >= board[0].length || board[i][j] == '#') {
            return;
        }

        char ch = board[i][j];
        TrieNode next = node.children[ch - 'a'];

        if (next == null) {
            return;
        }

        // Word found
        if (next.word != null) {
            result.add(next.word);
            next.word = null;
        }

        // Mark visited
        board[i][j] = '#';

        // Explore four directions
        dfs(board, i + 1, j, next, result);
        dfs(board, i - 1, j, next, result);
        dfs(board, i, j + 1, next, result);
        dfs(board, i, j - 1, next, result);

        // Restore original character
        board[i][j] = ch;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/word-search-ii/)