/* Structure of a Binary Search Tree node
class Node {
    int data;
    Node left, right;

    public Node(int val) {
        data = val;
        left = right = null;
    }
}
*/

class Solution {
    int minDiff = Integer.MAX_VALUE;

    void solve(Node root, int k) {
        if (root == null) {
            return;
        }

        minDiff = Math.min(minDiff, Math.abs(root.data - k));

        if (root.data > k) {
            solve(root.left, k);
        } 
        else if (root.data < k) {
            solve(root.right, k);
        }
    }

    int minDiff(Node root, int k) {
        minDiff = Integer.MAX_VALUE;
        solve(root, k);
        return minDiff;
    }
}
