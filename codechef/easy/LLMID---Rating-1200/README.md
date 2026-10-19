# LLMID - Rating 1200

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

### Find Middle Element of Linked List

You are given the head of a singly linked list $A$ of length $N$. The values in the list are $A_1, A_2, \ldots, A_N$ respectively. You need to find the value of the middle element of the linked list.

The middle element of a linked list of length $N$ is the $(\lfloor \frac{N}{2} \rfloor + 1)$-th element from the head of the list.

### Input Format
- The first line of the input contains a single integer $T$ - the number of test cases. The description of $T$ test cases follows.
- The first line of each test case contains a single integer $N$.
- The second line of each test case contains $N$ space-separated integers $A_1, A_2, \ldots, A_N$.
### Output Format
- For each test case, the function you complete should return the value of the middle element of the list.

 **Note:**  You need to complete the function `getMiddleElement` to solve the problem.

### Constraints
- $1 \leq T \leq 100$
- $1 \leq N \leq 10^5$
- $1 \leq A_i \leq 10^9$ for each valid $i$
- the sum of $N$ over all test cases does not exceed $2 \cdot 10^5$
### Sample 1:
Input
Output

```
3
5
1 2 3 4 5
6
1 2 3 4 5 6
4
10 1 6 12
```

```
3
4
6
```

### Explanation:

 **Example case 1:**  The value of the middle element is $A_3=3$.

 **Example case 2:**  The value of the middle element is $A_4=4$.

 **Example case 3:**  The value of the middle element is $A_3=6$.

## Solution

**Language:** Python  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-10-19T18:59:35.704Z  

```py
def getMiddleElement(head):
    slow = head
    fast = head
    while fast is not None and fast.next is not None:
        slow = slow.next
        fast = fast.next.next
    return slow.data
```

---

[View on CodeChef](https://www.codechef.com/problems/LLMID)