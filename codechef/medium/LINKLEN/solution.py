def getLength(head):
    # temp points to the first node
    temp = head

    count = 0

    # move through the linked list node by node
    while temp is not None:
        count += 1          # one node visited
        temp = temp.next    # go to the next node

    return count  