Starting repository for `Data Structures` COMP20280 2025-2026


Q5-Q8 Solutions from the Linked Lists practical: 


Q5:


Q6: In a singly linked list, the final node's next pointer is null; in a circular list, the final node points back to the first node.
    
    Singly linked lists have a definite start and end; circular lists can be traversed infinitely in a loop.
    
    Singly linked lists often trigger NullPointerException if you don't check for the end of the list, whereas circular lists require logic to stop the loop once you've returned to the start.

Q7: Linked lists are ideal when you need to perform frequent insertions or deletions, as they only require updating pointers rather    than shifting all elements like an array.
They are preferred when the total size of your data is unknown or changes constantly, as they grow dynamically without needing to reallocate large blocks of memory.
Additionally, they are useful when memory is fragmented, as nodes can be stored anywhere in memory rather than needing one large, continuous block.

Q8: In operating systems, a circular linked list is used to manage a list of processes that need CPU time. Since the tail points back to the head, the scheduler can continuously loop through the list, giving each process a fixed time slice without needing to reset the pointer or restart the search.

Circular lists are ideal for applications like music players or image sliders where the user expects the sequence to repeat. When the "next" button is clicked on the last item, the circular structure automatically moves back to the first item, creating a seamless loop for the user experience. 