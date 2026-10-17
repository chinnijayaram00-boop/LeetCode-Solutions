// Node is defined as:
// class Node{
//     int val;
//     Node next;
//     Node(int x){
//     	val = x; next = null;
//     }
// }
class Solution{
    static int solve(Node root){
        // If there are fewer than 3 nodes, no critical points can exist
        if (root == null || root.next == null || root.next.next == null) {
            return 0;
        }
        
        int criticalPoints = 0;
        Node prev = root;
        Node current = root.next;
        Node next = current.next;
        
        // Traverse the linked list
        while (next != null) {
            // Check if current node is a local maxima or local minima
            if ((current.val > prev.val && current.val > next.val) || 
                (current.val < prev.val && current.val < next.val)) {
                criticalPoints++;
            }
            
            // Slide the window of 3 pointers forward
            prev = current;
            current = next;
            next = next.next;
        }
        
        return criticalPoints;
    }
}