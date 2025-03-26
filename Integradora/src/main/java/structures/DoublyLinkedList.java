package structures;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import model.Driver;
import model.Identifiable;

public class DoublyLinkedList<T extends Comparable<T> & Identifiable>{
    private Node<T> first;
    private Node<T> last;
    private int size;

    public DoublyLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            first = last = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrev(last);
            last = newNode;
        }
        size++;
        return true;
    }

    public T search(T data) {
        Node<T> current = first;
        while (current != null) {
            if (current.getData().getIdentification().compareTo(data.getIdentification()) == 0) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean delete(T data) {
        if (isEmpty()) return false;

        Node<T> current = first;
        while (current != null && !current.getData().equals(data)) {
            current = current.getNext();
        }

        if (current == null) return false;

        if (current == first) {
            first = first.getNext();
            if (first != null) first.setPrev(null);
        } else if (current == last) {
            last = last.getPrev();
            if (last != null) last.setNext(null);
        } else {
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }
        size--;
        return true;
    }

    public T get(int index) {
        if (index < 0 || index >= size) return null;
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public void set(int index, T newObject) {
        Node<T> current = first;

        for (int i = 0; i<index; i++) {
            current = current.getNext();
        }

        current.setData(newObject);
    }


    public String getData() {
        String msg = "";

        if(!isEmpty()) {
            Node<T> current = first;

            for (int i = 0; i < size; i++) {
                msg += (i+1) + ". " + current.toString() + "\n";
                current = current.getNext();
            }
        }

        return msg;
    }

    public Node<T> getFirst() {
        return first;
    }



    public String sortList() {
        int n = size;

        for (int i = 1; i < n; i++) {
            T current = get(i);
            int j = i - 1;

            while (j >= 0 && current.compareTo( get(j) ) < 0 ){
                set(j+1,get(j));
                --j;
            }
            set(j+1, current);
        }

        return getData();
    }


    public String sortList(Comparator<T> comparator) {
        int n = size;

        for (int i = 1; i < n; i++) {
            T current = get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(current, get(j) ) < 0 ){
                set(j+1,get(j));
                --j;
            }
            set(j+1, current);
        }

        return getData();

    }
}
