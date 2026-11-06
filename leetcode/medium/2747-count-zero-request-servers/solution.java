
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        int m = logs.length;
        int q = queries.length;

        int[] ans = new int[q];
        int[] count = new int[n + 1];

        // Sort logs by time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Store query indices
        Integer[] idx = new Integer[q];

        for (int i = 0; i < q; i++) {
            idx[i] = i;
        }

        // Sort queries by time
        Arrays.sort(idx, (a, b) ->
            Integer.compare(queries[a], queries[b]));

        int left = 0;
        int right = 0;
        int active = 0;

        for (int i = 0; i < q; i++) {

            int current = queries[idx[i]];
            int start = current - x;

            // Add logs inside right boundary
            while (right < m && logs[right][1] <= current) {
                int server = logs[right][0];

                if (count[server] == 0) {
                    active++;
                }

                count[server]++;
                right++;
            }

            // Remove logs outside left boundary
            while (left < m && logs[left][1] < start) {
                int server = logs[left][0];

                count[server]--;

                if (count[server] == 0) {
                    active--;
                }

                left++;
            }

            // Servers with zero requests
            ans[idx[i]] = n - active;
        }

        return ans;
    }
}
