package co.edu.uptc.util;

import java.lang.runtime.TemplateRuntime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class DoubleLinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoubleLinkedList() {
        size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public boolean add(T e) {

        Node<T> newNode = new Node(e);
        if (head != null) {
            tail.setNext(newNode);
            newNode.setPrevius(tail);
            tail = newNode;
        } else {
            head = newNode;
            tail = newNode;
        }
        size++;
        return tail.equals(newNode);
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        int current = 0;
        Node<T> aux = head;
        while (aux != null) {
            if (current == index) {
                return aux.getData();
            }
            aux = aux.getNext();
            current++;
        }
        return null;
    }

    @Override
    public T set(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    @Override
    public void add(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public T remove(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public int indexOf(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

    @Override
    public int lastIndexOf(Object o) {
        int output = size;
        boolean found = false;

        while (output > 0 && !found) {
            output--;
            if (Objects.equals(o, get(output))) {
                found = true;
            }
        }

        return found ? output : -1;

    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subList'");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        if (c == null) {
            throw new NullPointerException("La coleccion esta vacia");
        }

        Node<T> temporalNode = head;
        boolean isDeleted = false;
        
        while (temporalNode != null) {
            Node<T> nextNode = temporalNode.getNext();
            isDeleted = exists(temporalNode, c);
            temporalNode = nextNode;
        }
        return isDeleted;
    }

    private boolean exists(Node<T> temporalNode, Collection<?> c) {
        boolean isDeleted = false;
        if (c.contains(temporalNode.getData())) {
            deleteNode(temporalNode);
            isDeleted = true;
        }
        return isDeleted;
    }

    private void deleteNode(Node<T> temporalNode) {
        if (temporalNode.getPrevius() == null) {
            rewriteHead(temporalNode);
        } else if (temporalNode.getNext() == null) {
            tail = temporalNode.getPrevius();
            tail.setNext(null);
        } else {
            temporalNode.getPrevius().setNext(temporalNode.getNext());
            temporalNode.getNext().setPrevius(temporalNode.getPrevius());
        }
    }

    private void rewriteHead(Node<T> temporalNode) {
        head = temporalNode.getNext();
        if (head != null) {
            head.setPrevius(null);
        } else {
            tail = null;
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean hasChanged = false;
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            T element = it.next();
            if (!c.contains(element)) {
                it.remove();
                hasChanged = true;
            }
        }
        return hasChanged;
    }
}
