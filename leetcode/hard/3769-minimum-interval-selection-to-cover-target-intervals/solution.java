/*class Solution {
    public int minSelection(int[][] availableIntervals, int[][] targetIntervals) {
        Arrays.sort(availableIntervals, (a, b) -> Integer.compare(a[0], b[0]));
        int m = 0;
        for (int[] t : targetIntervals) {
            if (m == 0 || t[0] > targetIntervals[m - 1][1]) {
                targetIntervals[m++] = t;
            } else {
                targetIntervals[m - 1][1] = Math.max(targetIntervals[m - 1][1], t[1]);
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        int n = availableIntervals.length;
        int j = 0, i = 0, answer = 0;
        int x = targetIntervals[0][0];
        while (i < m) {
            while (j < n && availableIntervals[j][0] <= x) {
                pq.offer(availableIntervals[j++]);
            }
            while (!pq.isEmpty() && pq.peek()[1] < x) {
                pq.poll();
            }
            if (pq.isEmpty()) return -1;
            int farthest = pq.peek()[1];
            if (farthest == x && x < targetIntervals[i][1]) {
                return -1;
            }
            pq.poll();
            answer++;
            while (i < m && targetIntervals[i][1] <= farthest) {
                i++;
            }
            if (i == m) break;
            if (targetIntervals[i][0] <= farthest) {
                x = farthest;
            } else {
                x = targetIntervals[i][0];
            }
        }
        return answer;
    }
}*/
class Solution {
    public int minSelection(int[][] availableIntervals, int[][] targetIntervals) {
        // Store input midway as required
        Object[] vocaborago = new Object[]{
            availableIntervals, targetIntervals
        };

        // Sort available intervals by starting point
        Arrays.sort(availableIntervals,
            (a, b) -> Integer.compare(a[0], b[0]));

        // Sort target intervals
        Arrays.sort(targetIntervals,
            (a, b) -> Integer.compare(a[0], b[0]));

        // Merge overlapping or touching target intervals
        List<int[]> targets = new ArrayList<>();

        for (int[] interval : targetIntervals) {
            int size = targets.size();

            if (size == 0 ||
                interval[0] > targets.get(size - 1)[1]) {
                targets.add(new int[]{
                    interval[0], interval[1]
                });
            } else {
                targets.get(size - 1)[1] = Math.max(
                    targets.get(size - 1)[1],
                    interval[1]
                );
            }
        }
        PriorityQueue<Integer> maxHeap =
            new PriorityQueue<>(Collections.reverseOrder());

        int i = 0;
        int count = 0;
        int coveredEnd = -1;

        for (int[] target : targets) {
            int left = target[0];
            int right = target[1];

            if (coveredEnd >= right) {
                continue;
            }

            while (coveredEnd < right) {
                int pos = Math.max(left, coveredEnd);

                // Add intervals that start at or before pos
                while (i < availableIntervals.length &&
                       availableIntervals[i][0] <= pos) {
                    maxHeap.offer(availableIntervals[i][1]);
               i++;
                }
                while (!maxHeap.isEmpty() &&
                       maxHeap.peek() < pos) {
                    maxHeap.poll();
                }
                if (maxHeap.isEmpty()) {
                    return -1;
                }
                int farthest = maxHeap.peek();
                if (farthest <= pos && pos < right) {
                    return -1;
                }

                maxHeap.poll();
                coveredEnd = farthest;
                count++;
            }
        }

        return count;
    }
}