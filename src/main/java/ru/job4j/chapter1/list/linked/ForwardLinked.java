package ru.job4j.chapter1.list.linked;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;
    private int size = 0;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
        } else {
            Node<T> last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = node;
        }
        ++size;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        ++size;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> newHead = head.next;
        T value = head.value;
        head.next = null;
        head = newHead;
        size--;
        return value;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {

        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
