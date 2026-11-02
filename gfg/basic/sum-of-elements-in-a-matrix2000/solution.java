class Solution {
    long sumOfMatrix(int[][] mat) {
        long sum = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                sum += mat[i][j];
            }
        }

        return sum;
    }
}