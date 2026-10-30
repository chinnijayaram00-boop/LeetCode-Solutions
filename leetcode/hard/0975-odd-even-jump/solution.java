
class Solution {
    public int oddEvenJumps(int[] arr) {
        int n = arr.length;

        boolean[] odd = new boolean[n];
        boolean[] even = new boolean[n];

        TreeMap<Integer, Integer> map = new TreeMap<>();

        odd[n - 1] = true;
        even[n - 1] = true;

        map.put(arr[n - 1], n - 1);

        int ans = 1;

        for (int i = n - 2; i >= 0; i--) {

            // Odd jump: smallest value >= arr[i]
            Integer higher = map.ceilingKey(arr[i]);

            if (higher != null) {
                odd[i] = even[map.get(higher)];
            }

            // Even jump: largest value <= arr[i]
            Integer lower = map.floorKey(arr[i]);

            if (lower != null) {
                even[i] = odd[map.get(lower)];
            }

            if (odd[i]) {
                ans++;
            }

            // Store current index
            map.put(arr[i], i);
        }

        return ans;
    }
}
