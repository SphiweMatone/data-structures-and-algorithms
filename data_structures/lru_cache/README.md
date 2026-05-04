## Key Insight

To achieve O(1) time complexity:
- A HashMap is used for fast lookups
- A Doubly Linked List maintains usage order

The least recently used item is always at the tail.

## Why Doubly Linked List?

It allows:
- O(1) removal of nodes
- O(1) insertion at the front
