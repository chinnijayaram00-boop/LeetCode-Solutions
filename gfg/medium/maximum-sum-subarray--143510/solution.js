/**
 * @param {number[]} arr
 * @return {number}
 */

class Solution {
    maximumSubarray(arr) {
        let noSquare = arr[0];
        let square = arr[0] * arr[0];
        let ans = Math.max(noSquare, square);

        for (let i = 1; i < arr.length; i++) {
            let x = arr[i];

            let newSquare = Math.max(
                square + x,
                noSquare + x * x,
                x * x
            );

            let newNoSquare = Math.max(noSquare + x, x);

            square = newSquare;
            noSquare = newNoSquare;

            ans = Math.max(ans, square, noSquare);
        }

        return ans;
    }
}