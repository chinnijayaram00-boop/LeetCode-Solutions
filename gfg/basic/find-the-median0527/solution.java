
import java.util.Arrays;

class Solution {
    public double findMedian(int[] arr) {
        Arrays.sort(arr);

        int n = arr.length;

        if (n % 2 == 1) {
            return arr[n / 2];
        }

        return (arr[n / 2 - 1] + arr[n / 2]) / 2.0;
    }
}
