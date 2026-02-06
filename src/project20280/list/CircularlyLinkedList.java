package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private final Node<E> tail = null;
    private final int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node<E> curr = tail.getNext(); // Start from head
        for (int j = 0; j < i; j++) {
            curr = curr.getNext();
        }
        return curr.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (i == 0) {
            addFirst(e);
        } else {
            Node<E> prev = tail.getNext(); // Start from head
            for (int j = 0; j < i - 1; j++) {
                prev = prev.getNext();
            }
            Node<E> newest = new Node<>(e, prev.getNext());
            prev.setNext(newest);
            if (prev == tail) { // If we added at the end, update tail
                tail.setNext(newest);
            }
        }
    }

    @Override
    public E remove(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (i == 0) {
            return removeFirst();
        } else {
            Node<E> prev = tail.getNext(); // Start from head
            for (int j = 0; j < i - 1; j++) {
                prev = prev.getNext();
            }
            Node<E> toRemove = prev.getNext();
            prev.setNext(toRemove.getNext());
            if (toRemove == tail) { // If we removed the tail, update tail
                tail.setNext(prev);
            }
            return toRemove.getData();
        }
    }

    public void rotate() {
        if (tail != null) {
            tail.setNext(tail.getNext()); // Old head becomes new tail
        }
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if (tail == null) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        Node<E> head = tail.getNext();
        if (head == tail) { // Only one element
            tail.setNext(null);
        } else {
            tail.setNext(head.getNext());
        }
        return head.getData();
    }

    @Override
    public E removeLast() {
        if (tail == null) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        Node<E> head = tail.getNext();
        if (head == tail) { // Only one element
            tail.setNext(null);
            return head.getData();
        } else {
            Node<E> prev = head;
            while (prev.getNext() != tail) {
                prev = prev.getNext();
            }
            prev.setNext(head);
            E data = tail.getData();
            tail.setNext(null); // Help GC
            return data;
        }
    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, null);
        if (tail == null) {
            tail.setNext(newNode);
            tail.setNext(newNode); // Point to itself
        } else {
            newNode.setNext(tail.getNext());
            tail.setNext(newNode);
        }
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e, null);
        if (tail == null) {
            tail.setNext(newNode);
            tail.setNext(newNode); // Point to itself
        } else {
            newNode.setNext(tail.getNext());
            tail.setNext(newNode);
            tail.setNext(newNode); // Update tail to new node
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
