
import java.util.*;

class Solution {

    public ArrayList<Integer> findPairs(int[] arr) {

        int n = arr.length;

        HashMap<Long, ArrayList<int[]>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                long sum = (long) arr[i] + arr[j];

                map.computeIfAbsent(sum, k -> new ArrayList<>())
                   .add(new int[]{i, j});
            }
        }

        int[] best = null;

        for (ArrayList<int[]> pairs : map.values()) {

            for (int x = 0; x < pairs.size(); x++) {

                int[] p1 = pairs.get(x);

                for (int y = x + 1; y < pairs.size(); y++) {

                    int[] p2 = pairs.get(y);

                    if (p1[0] == p2[0] ||
                        p1[0] == p2[1] ||
                        p1[1] == p2[0] ||
                        p1[1] == p2[1]) {
                        continue;
                    }

                    int[] curr = {
                        p1[0], p1[1], p2[0], p2[1]
                    };

                    if (best == null || compare(curr, best) < 0) {
                        best = curr;
                    }

                    break;
                }
            }
        }

        if (best == null) {
            return new ArrayList<>(
                Arrays.asList(-1, -1, -1, -1)
            );
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for (int x : best) {
            ans.add(x);
        }

        return ans;
    }

    private int compare(int[] a, int[] b) {

        for (int i = 0; i < 4; i++) {
            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }

        return 0;
    }
}
