import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);
        moveToFront(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToFront(node);
            return;
        }

        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        addToFront(newNode);

        if (cache.size() > capacity) {
            Node leastRecentlyUsed = tail.prev;
            remove(leastRecentlyUsed);
            cache.remove(leastRecentlyUsed.key);
        }
    }

    private void moveToFront(Node node) {
        remove(node);
        addToFront(node);
    }

    private void remove(Node node) {
        Node previousNode = node.prev;
        Node nextNode = node.next;

        previousNode.next = nextNode;
        nextNode.prev = previousNode;
    }

    private void addToFront(Node node) {
        Node firstNode = head.next;

        node.prev = head;
        node.next = firstNode;

        head.next = node;
        firstNode.prev = node;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 100);
        cache.put(2, 200);

        System.out.println(cache.get(1)); // 100

        cache.put(3, 300);

        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 300
    }
}
