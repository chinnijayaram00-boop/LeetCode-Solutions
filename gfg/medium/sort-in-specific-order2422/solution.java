class Solution {
    public void sortIt(int[] arr) {
        int left = 0, right = arr.length - 1;
        int oddCount = 0;
        while (left <= right) {
            if (arr[left] % 2 != 0) {
                left++;
                oddCount++;
            } else if (arr[right] % 2 == 0) {
                right--;
            } else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
                oddCount++;
            }
        }
        Arrays.sort(arr, 0, oddCount);
        reverse(arr, 0, oddCount - 1);
        Arrays.sort(arr, oddCount, arr.length);
    }
    private void reverse (int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
