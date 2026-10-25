class Solution {
    public int countStaircase(int[] nums) {
        int n = nums.length;
        int[] prev = new int[n];
        int[] occIndex = new int[n];
        Map<Integer, Integer> last = new HashMap<>();
        Map<Integer, ArrayList<Integer>> positions = new HashMap<>();
        for (int i = 0; i < n; i++) {
            prev[i] = last.getOrDefault(nums[i], -1);
            last.put(nums[i], i);
            ArrayList<Integer> list = positions.computeIfAbsent(nums[i], x -> new ArrayList<>());
            occIndex[i] = list.size();
            list.add(i);
        }
        MinSegmentTree seg = new MinSegmentTree(prev, n);
        int ans = 0;
        for (int left = 0; left < n; left++) {
            int p = left;
            int distinct = 0;
            int low = left;
            int upper = n - 1;
            while (p < n) {
                distinct++;
                ArrayList<Integer> list = positions.get(nums[p]);
                int idx = occIndex[p];
                int target = idx + distinct - 1;
                if (target >= list.size()) {
                    break;
                }
                low = Math.max(low, list.get(target));
                int nextTarget = idx + distinct;
                if (nextTarget < list.size()) {
                    upper = Math.min(upper, list.get(nextTarget) - 1);
                }
                if (low > upper) {
                    break;
                }
                int next = seg.findFirst(p + 1, left);
                int high = Math.min(upper, next - 1);
                if (low <= high) {
                    ans += high - low + 1;
                }
                p = next;
            }
        }
        return ans;
    }
    static class MinSegmentTree {
        int n;
        int[] tree;
        MinSegmentTree(int[] arr, int size) {
            n = size;
            tree = new int[4 * n];
            build(1, 0, n - 1, arr);
        }
        void build(int node, int l ,int r, int[] arr) {
            if (l == r) {
                tree[node] = arr[l];
                return;
            }
            int mid = (l + r) >>> 1;
            build(node * 2, l, mid, arr);
            build(node * 2 + 1, mid + 1, r, arr);
            tree[node] = Math.min(tree[node * 2],tree[node * 2 + 1]);
        }
        int findFirst(int start, int threshold) {
            if (start >= n) {
                return n;
            }
            return findFirst(1, 0, n - 1, start, threshold);
        }
        int findFirst(int node, int l, int r, int start, int threshold) {
            if (r < start || tree[node] >= threshold) {
                return n;
            }
            if (l == r) {
                return l;
            }
            int mid = (l + r) >>> 1;
            int result = findFirst(node * 2, l, mid, start, threshold);
            if (result != n) {
                return result;
            }
            return findFirst(node * 2 + 1, mid + 1, r, start, threshold);
        }
    }
}