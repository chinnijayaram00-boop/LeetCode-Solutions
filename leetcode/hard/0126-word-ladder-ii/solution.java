
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
