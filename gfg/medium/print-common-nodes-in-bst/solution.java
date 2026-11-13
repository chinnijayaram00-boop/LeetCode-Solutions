/* Structure of a Binary Search Tree node
class Node {
  public int data;
  public Node left;
  public Node right;

  public Node(int val) {
      data = val;
      left = right = null;
  }
};
*/

class Solution {

    public ArrayList<Integer> findCommon(Node r1, Node r2) {

        ArrayList<Integer> ans = new ArrayList<>();

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        while (r1 != null || r2 != null ||
               !s1.isEmpty() || !s2.isEmpty()) {

            // Push left nodes of first BST
            while (r1 != null) {
                s1.push(r1);
                r1 = r1.left;
            }

            // Push left nodes of second BST
            while (r2 != null) {
                s2.push(r2);
                r2 = r2.left;
            }

            if (s1.isEmpty() || s2.isEmpty()) {
                break;
            }

            Node a = s1.peek();
            Node b = s2.peek();

            if (a.data == b.data) {

                ans.add(a.data);

                r1 = s1.pop().right;
                r2 = s2.pop().right;

            } else if (a.data < b.data) {

                r1 = s1.pop().right;

            } else {

                r2 = s2.pop().right;
            }
        }

        return ans;
    }
}
