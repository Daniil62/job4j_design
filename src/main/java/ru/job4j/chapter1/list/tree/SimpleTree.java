package ru.job4j.chapter1.list.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {

    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> node = findBy(parent);
        boolean result = node.isPresent() && findBy(child).isEmpty();
        if (result) {
            node.get().children.add(new Node<>(child));
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(p -> p.value.equals(value));
    }

    @Override
    public boolean isBinary() {
        return findByPredicate(p -> p.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> e = data.poll();
            if (condition.test(e)) {
                result = Optional.of(e);
                break;
            }
            data.addAll(e.children);
        }
        return result;
    }
}
