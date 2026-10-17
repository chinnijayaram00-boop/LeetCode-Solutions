# CRITLIST - Rating 1000

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

### Critical points in a Linked List

Given the head of a linked list, Find the number of critical points. (The starting and end are not considered critical points).

Local minima or maxima are called critical points.

A Node is called a local minima if both next and previous elements are greater than the current element.

A Node is called a local maxima if both next and previous elements are smaller than the current element.

### Constraints
- $1 \leq$ Number of elements in the linked list, $N$ $\leq 10^5$
- $1 \leq Node.data \leq 10^9$
### Sample 1:
Input
Output

```
8
1 2 3 3 3 5 1 3

```

```
2 
```

### Explanation:

1 is a minima and 5 is a maxima hence there are 2 critical points

### Sample 2:
Input
Output

```
7
1 2 3 2 1 3 2 
```

```
3
```

### Explanation:

3rd node, 5th node and 6th node are the critical nodes, hence the answer is 3

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-10-17T02:14:14.789Z  

```java
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
```

---

[View on CodeChef](https://www.codechef.com/problems/CRITLIST)