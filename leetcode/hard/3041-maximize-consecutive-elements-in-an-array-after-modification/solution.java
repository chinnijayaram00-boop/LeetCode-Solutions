
import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            int a = dp.getOrDefault(x - 1, 0);
            int b = dp.getOrDefault(x, 0);

            int next0 = Math.max(b, a + 1);
            int next1 = b + 1;

            dp.put(x, Math.max(b, next0));
            dp.put(x + 1, Math.max(dp.getOrDefault(x + 1, 0), next1));

            ans = Math.max(ans, Math.max(next0, next1));
        }

        return ans;
    }
}
