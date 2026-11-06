class Solution {
    public ArrayList<String> generateUsernames(String[] arr) {
        HashSet<String> reserved = new HashSet<>();
        HashSet<String> used = new HashSet<>();
        HashMap<String, Integer> next = new HashMap<>();
        for (String s : arr) {
            reserved.add(s);
        }
        ArrayList<String> result = new ArrayList<>();
        for (String s : arr) {
            if (!used.contains(s)) {
                result.add(s);
                used.add(s);
            }
            else {
                int k = next.getOrDefault(s, 1);
                String candidate = s + k;
                while (reserved.contains(candidate) || used.contains(candidate)) {
                    k++;
                    candidate = s + k;
                }
                result.add(candidate);
                used.add(candidate);
                next.put(s, k + 1);
            }
        }
        return result;
    }
}