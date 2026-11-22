
import java.util.*;

class Solution {
    public int longestSubarrayDivK(int[] arr, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, -1);

        long sum = 0;
        int maxLength = 0;

        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];

            int rem = (int)(((sum % k) + k) % k);

            if (map.containsKey(rem)) {

                int len = i - map.get(rem);

                maxLength = Math.max(maxLength, len);

            } else {
                map.put(rem, i);
            }
        }

        return maxLength;
    }
}
