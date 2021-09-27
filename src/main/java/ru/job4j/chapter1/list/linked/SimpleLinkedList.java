package ru.job4j.chapter1.list.linked;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    private transient Node<E> head;
    private transient Node<E> last;
    private transient int size, modCount = 0;

    public SimpleLinkedList(Collection<? extends E> collection) {
        for (E value : collection) {
            add(value);
        }
    }

    public SimpleLinkedList() {
    }

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
        ++size;
        ++modCount;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        if (index > size / 2) {
            return getElementByReverseIteration(index);
        } else {
            return getElementByDirectIteration(index);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private Node<E> node = head;
            private int expectedModeCount = modCount;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModeCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E result = node.value;
                node = node.next;
                return result;
            }
        };
    }

    private E getElementByReverseIteration(int index) {
        Node<E> node = last;
        for (int i = size - 1; i >= index; i--) {
            if (index == i) {
                break;
            }
            node = node.previous;
        }
        return node.value;
    }

    private E getElementByDirectIteration(int index) {
        Node<E> node = head;
        for (int i = 0; i <= index; i++) {
            if (index == i) {
                break;
            }
            node = node.next;
        }
        return node.value;
    }

    private static class Node<K> {

        K value;
        Node<K> previous;
        Node<K> next;

        public Node(Node<K> previous, K value, Node<K> next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
}
