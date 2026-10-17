class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = digitSum(nums[i]);
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        Map<Integer, Integer> position = new HashMap<>();
        for (int i = 0; i < n; i++) {
            position.put(nums[i], i);
        }
        int[] target = new int[n];
        for (int i = 0; i < n; i++) {
            target[position.get(arr[i][0])] = i;
        }
        boolean[] visited = new boolean[n];
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] || target[i] == i) {
                continue;
            }
            int cycle = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = target[j];
                cycle++;
            }
            swaps += cycle - 1;
        }
        return swaps;
    }
    private int digitSum(int num) {
        int digitSum = 0;
        while (num > 0) {
            digitSum += num % 10;
            num /= 10;
        }
        return digitSum;
    }
}