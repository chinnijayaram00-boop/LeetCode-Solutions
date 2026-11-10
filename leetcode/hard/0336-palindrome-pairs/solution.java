import java.util.*;

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = word.length();

            for (int j = 0; j <= len; j++) {

                String left = word.substring(0, j);
                String right = word.substring(j);

                // Case 1: Left part is palindrome
                if (isPalindrome(left)) {
                    String reversed = new StringBuilder(right)
                            .reverse().toString();

                    Integer index = map.get(reversed);

                    if (index != null && index != i) {
                        result.add(Arrays.asList(index, i));
                    }
                }

                // Case 2: Right part is palindrome
                if (j < len && isPalindrome(right)) {
                    String reversed = new StringBuilder(left)
                            .reverse().toString();

                    Integer index = map.get(reversed);

                    if (index != null && index != i) {
                        result.add(Arrays.asList(i, index));
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}