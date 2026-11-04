
import java.util.*;

class Solution {

    class Median {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;

        void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                left.merge(val, 1, Integer::sum);
                leftSize++;
                leftSum += val;
            } else {
                right.merge(val, 1, Integer::sum);
                rightSize++;
                rightSum += val;
            }
            balance();
        }

        void remove(int val) {
            if (left.containsKey(val)) {
                erase(left, val);
                leftSize--;
                leftSum -= val;
            } else {
                erase(right, val);
                rightSize--;
                rightSum -= val;
            }
            balance();
        }

        void erase(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) map.remove(val);
            else map.put(val, count - 1);
        }

        void balance() {
            int target = (leftSize + rightSize + 1) / 2;

            while (leftSize > target) {
                int val = left.lastKey();
                erase(left, val);
                leftSize--;
                leftSum -= val;

                right.merge(val, 1, Integer::sum);
                rightSize++;
                rightSum += val;
            }

            while (leftSize < target) {
                int val = right.firstKey();
                erase(right, val);
                rightSize--;
                rightSum -= val;

                left.merge(val, 1, Integer::sum);
                leftSize++;
                leftSum += val;
            }
        }

        long cost() {
            long median = left.lastKey();

            return median * leftSize - leftSum
                 + rightSum - median * rightSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int m = n - x + 1;

        long[] costs = new long[m];
        Median median = new Median();

        // Calculate cost of every window
        for (int i = 0; i < n; i++) {
            median.add(nums[i]);

            if (i >= x) {
                median.remove(nums[i - x]);
            }

            if (i >= x - 1) {
                costs[i - x + 1] = median.cost();
            }
        }

        // Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                // Skip current element
                curr[i] = curr[i - 1];

                // Select a window of size x
                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + costs[i - x]
                    );
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
