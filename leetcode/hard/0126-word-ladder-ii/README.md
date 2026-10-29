# Word Ladder II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

A  **transformation sequence**  from word `beginWord` to word `endWord` using a dictionary `wordList` is a sequence of words `beginWord -> s1 -> s2 ->... -> sk` such that:

- Every adjacent pair of words differs by a single letter.
- Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
- sk == endWord

Given two words, `beginWord` and `endWord`, and a dictionary `wordList`, return  *all the  **shortest transformation sequences**  from*  `beginWord`  *to*  `endWord` *, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words* `[beginWord, s1, s2,..., sk]`.

 

 **Example 1:** 

```
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"

```

 **Example 2:** 

```
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

```

 

 **Constraints:** 

- 1 <= beginWord.length <= 5
- endWord.length == beginWord.length
- 1 <= wordList.length <= 500
- wordList[i].length == beginWord.length
- beginWord, endWord, and wordList[i] consist of lowercase English letters.
- beginWord != endWord
- All the words in wordList are unique.
- The sum of all shortest transformation sequences does not exceed 105.

## Solution

**Language:** Java  
**Runtime:** 17 ms (beats 66.20%)  
**Memory:** 47.1 MB (beats 52.13%)  
**Submitted:** 2026-10-29T09:38:10.162Z  

```java

import java.util.*;

class Solution {
    public List<List<String>> findLadders(
        String beginWord, String endWord, List<String> wordList) {

        List<List<String>> ans = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) {
            return ans;
        }

        Map<String, List<String>> parents = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(beginWord);
        visited.add(beginWord);

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            Set<String> levelVisited = new HashSet<>();

            for (int x = 0; x < size; x++) {
                String word = queue.poll();
                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char original = chars[i];

                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == original) continue;

                        chars[i] = ch;
                        String next = new String(chars);

                        if (!dict.contains(next)) continue;

                        if (!visited.contains(next)) {
                            if (levelVisited.add(next)) {
                                queue.offer(next);
                            }

                            parents.computeIfAbsent(
                                next, k -> new ArrayList<>()
                            ).add(word);

                            if (next.equals(endWord)) {
                                found = true;
                            }
                        } else if (levelVisited.contains(next)) {
                            parents.computeIfAbsent(
                                next, k -> new ArrayList<>()
                            ).add(word);
                        }
                    }

                    chars[i] = original;
                }
            }

            visited.addAll(levelVisited);
        }

        if (found) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            dfs(endWord, beginWord, parents, path, ans);
        }

        return ans;
    }

    private void dfs(String word, String beginWord,
                     Map<String, List<String>> parents,
                     List<String> path, List<List<String>> ans) {

        if (word.equals(beginWord)) {
            List<String> result = new ArrayList<>(path);
            Collections.reverse(result);
            ans.add(result);
            return;
        }

        if (!parents.containsKey(word)) return;

        for (String parent : parents.get(word)) {
            path.add(parent);
            dfs(parent, beginWord, parents, path, ans);
            path.remove(path.size() - 1);
        }
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/word-ladder-ii/)