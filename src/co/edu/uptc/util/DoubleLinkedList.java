package co.edu.uptc.util;

import java.lang.runtime.TemplateRuntime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
        if(size==Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        return size;
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
    if (head == null) {
        return false; // lista vacía
    }

    Node<T> current = head;

    while (current != null) {

        if ((o == null && current.getData() == null) ||
            (o != null && o.equals(current.getData()))) {

            if (current == head && current == tail) {
                head = null;
                tail = null;
            }
            else if (current == head) {
                head = head.getNext();
                head.setPrevius(null);
            }
            else if (current == tail) {
                tail = tail.getPrevius();
                tail.setNext(null);
            }
            else {
                current.getPrevius().setNext(current.getNext());
                current.getNext().setPrevius(current.getPrevius());
            }

            size--;
            return true;
        }

        current = current.getNext();
    }

    return false; // no encontrado
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
        int counter=0; 
        T oldData= get(index);
        Node<T> auxNode = head; 
         
        if((index < 0 || index >= size())){ 
            throw new IndexOutOfBoundsException(); 
        } while (counter<index) { 
            auxNode= auxNode.getNext(); counter++; 
        } 
        auxNode.setData(element); 
        return oldData;   
    }

@Override
    public void add(int index, T element) {
        Node <T> newNode = new Node (element);
        if(index< 0 || index > size){
            throw new IndexOutOfBoundsException();  
        }
        
        if(element == null){
            throw  new NullPointerException();
        }
        //SI ESTA VACIO
        if(size == 0 ){
            head = newNode;
            tail = newNode;
        }
        //INSERTAR AL PRINCIPIO
        if(index  == 0){
            newNode.setNext(head);
            head.setPrevius(newNode);
            head = newNode; 
            
            //INSERTAR AL FINAL
        }else if(index ==  size){
            newNode.setPrevius(tail);
            tail.setNext(newNode);
            tail = newNode;
            //INSERTAR EN MEDIO
        }else {
            Node <T> currentNode = head;
            for(int i = 0 ; i < index ; i++){
                currentNode= currentNode.getNext();
            }
            Node <T> previusNode =currentNode.getPrevius();
            previusNode.setNext(newNode);
            newNode.setPrevius(previusNode);
            newNode.setNext(currentNode);
            currentNode.setPrevius(newNode);
        }
        size++;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return new ListIterator<T>() {
            private Node<T> cursor = inicializarCursor();
            private int cursorIndex = index;
            private Node<T> inicializarCursor() {
                if (index == size) {
                    return null;
                }
                Node<T> temp = head;
                for (int i = 0; i < index; i++) {
                    temp = temp.getNext();
                }

                for (int i = 0; i < index; i++) {
                    temp = temp.getNext();
                }

                return temp;
            }
            @Override
            public boolean hasNext() {
                return cursor != null;
            }
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T value = cursor.getData();
                cursor = cursor.getNext();
                cursorIndex++;
                return value;
            }
            @Override
            public boolean hasPrevious() {
                return cursorIndex > 0;
            }
            @Override
            public T previous() {
                if (!hasPrevious()) {
                    throw new java.util.NoSuchElementException();
                }
                if (cursor == null) {
                    cursor = tail;
                } else {
                    cursor = cursor.getPrevius();
                }

                cursorIndex--;

                return cursor.getData();
            }
            @Override
            public int nextIndex() {
                return cursorIndex;
            }
            @Override
            public int previousIndex() {
                return cursorIndex - 1;
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
            @Override
            public void set(T e) {
                throw new UnsupportedOperationException();
            }
            @Override
            public void add(T e) {
                throw new UnsupportedOperationException();
            }
        };
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
        if (c == null) {
            throw new NullPointerException("The specified collection is null");
        }
        
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            
            if (element == null) {
                throw new NullPointerException("The collection contains null elements");
            }

            try {
                if (!contains(element)) {
                    return false;
                }
            } catch (ClassCastException e) {
                throw new ClassCastException("The element type is not compatible with this collection.");
            }
        }
        
        return true;
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        if ( c.isEmpty()) {
            modified = false; 
        }else{ 
                for (T dataCollection : c) {
                add(dataCollection); 
                modified = true;
            }
            }
        return modified; 
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if (c == null)
            throw new NullPointerException("Collection cannot be null");
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        if (c.isEmpty())
            return false;

       
        for (Object element : c) {
            if (element == null)
                throw new NullPointerException("Collection contains a null element");
            try {
                T ignored = (T) element;
            } catch (ClassCastException e) {
                throw new ClassCastException("Element type is incompatible with the list: " + e.getMessage());
            }
        }

        Node<T> next = null;
        Node<T> previous = null;
        if (index == size) {
            previous = tail;
        } else {
            next = head;
            for (int i = 0; i < index; i++) {
                next = next.getNext();
            }
            previous = next.getPrevius();
        }

        for (Object element : c) {
            Node<T> newNode = new Node<>((T) element);
            newNode.setPrevius(previous);
            newNode.setNext(next);
            if (previous == null) {
                head = newNode;
            } else {
                previous.setNext(newNode);
            }
            if (next == null) {
                tail = newNode;
            } else {
                next.setPrevius(newNode);
            }
            previous = newNode;
            size++;
        }

        return true;
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
