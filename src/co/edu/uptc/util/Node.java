package co.edu.uptc.util;

public class Node<T> {
        private Node<T> next;
        private Node<T> previus;
        private T data;
public Node(T data){
    this.data = data;
    this.previus = null;
    this.next = null;
}
public Node<T> getNext() {
    return next;
}
public void setNext(Node<T> next) {
    this.next = next;
}
public T getData() {    
    return data;
}
public Node<T> getPrevius() {
    return previus;
}
public void setPrevius(Node<T> previus) {
    this.previus = previus;
}
public void setData(T data) {
    this.data = data;
}
}
