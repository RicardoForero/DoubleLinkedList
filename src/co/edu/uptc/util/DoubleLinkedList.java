package co.edu.uptc.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DoubleLinkedList<T> implements List<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoubleLinkedList(){
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
        if (head!=null) {
            tail.setNext(newNode);
            newNode.setPrevius(tail);
            tail = newNode;
        } else{
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
       if(index<0||index>=size()){
           throw new  IndexOutOfBoundsException();
       }
       int current=0;
       Node <T> aux=head;
       while(aux!=null){
           if(current==index){
               return aux.getData();
           }
           aux=aux.getNext();
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
                found =  true;
            }
        }

        return found ? output : -1; 


    }

    @Override
    public ListIterator<T> listIterator() {

        return new ListIterator<T>() {

            private Node<T> current = head;
            private Node<T> lastReturned = null;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                lastReturned = current;
                T data = current.getData();
                current = current.getNext();
                index++;
                return data;
            }

            @Override
            public boolean hasPrevious() {
                if (current == null) {
                    return tail != null;
                }
                return current.getPrevius() != null;
            }

            @Override
            public T previous() {
                if (!hasPrevious())
                    throw new NoSuchElementException();

                if (current == null) {
                    current = tail;
                } else {
                    current = current.getPrevius();
                }

                lastReturned = current;
                index--;
                return current.getData();
            }

            @Override
            public int nextIndex() {
                return index;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                if (lastReturned == null)
                    throw new IllegalStateException();

                Node<T> nextNode = lastReturned.getNext();
                Node<T> prevNode = lastReturned.getPrevius();

                if (prevNode != null) {
                    prevNode.setNext(nextNode);
                } else {
                    head = nextNode;
                }

                if (nextNode != null) {
                    nextNode.setPrevius(prevNode);
                } else {
                    tail = prevNode;
                }

                if (current == lastReturned) {
                    current = nextNode;
                } else {
                    index--;
                }

                lastReturned = null;
            }

            @Override
            public void set(T e) {
                if (lastReturned == null)
                    throw new IllegalStateException();

                lastReturned = new Node<>(e);
            }

            @Override
            public void add(T e) {

                Node<T> newNode = new Node<>(e);
                Node<T> prevNode;
                Node<T> nextNode = current;

                if (current != null) {
                    prevNode = current.getPrevius();
                } else {
                    prevNode = tail;
                }

                newNode.setNext(nextNode);
                newNode.setPrevius(prevNode);

                if (prevNode != null) {
                    prevNode.setNext(newNode);
                } else {
                    head = newNode;
                }

                if (nextNode != null) {
                    nextNode.setPrevius(newNode);
                } else {
                    tail = newNode;
                }

                index++;
                lastReturned = null;
            }
        };
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
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
