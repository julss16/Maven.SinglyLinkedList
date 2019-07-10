package com.zipcodewilmington.singlylinkedlist;

import com.sun.tools.javac.util.GraphUtils.Node;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by leon on 1/10/18.
 *
 * add -- add an element to the lis t
 * remove -- remove an element (specified by numeric index) from the list
 * contains -- returns true if the element is in the list, false otherwise
 * find -- returns the element's index if it is in the list, -1 otherwise
 * size -- returns the current size of the list
 * get -- returns the element at the specified index
 * copy -- returns a new linked list containing the same values (look up deep versus shallow copy)
 * sort -- sorts the list using your algorithm of choice. You must perform the sorting yourself (no fair using someone else's library)
 */
public class SinglyLinkedList<T> implements Iterable<T>{

    private Node head;
    private Node last;


    public SinglyLinkedList() {
        this.head = null;
        this.last = null;

    }


    public void add(T element){

        if( head== null){
            head= new Node (null,  element, null);
            head= last;


        }else {
            Node<T> newNode = new Node(last, element, null);
            last.next = newNode;
            last = last.next;
        }
}

    public void remove(int index){

        Node node= getNode(index);
        if( node == head && node== last){
            head = null;
            last= null;

        }else if( node== head){
            head= node.getNext();
        }else if( node== last){
            last=node.getPrevious();
        }else{
            Node p = node.getPrevious();
            Node n = node.getNext();
            p.setNext(n);
            n.setPrevious(p);
        }



    }

    public int find(T element){

        Node current= head;
        T item;
        while( current !=null){
            item= (T) current.getElement();
            if( item.equals(element))

                return 0;

        }
            return 0;

    }

    public boolean contains(T element) {
        if (head == null) {
            Node current = head;
            while (current != null) {
                if (element.equals(current.getElement())) {
                    return true;

                }
                current = current.getNext();
            }
            return false;

        } else {

            return false;
        }


    }


    public int size(){
        int index= 0;
        Node current= head;
        while (current != null){
            index++;
            current= current.getNext();
        }

        return index;
    }

    public T getElement(int index){


        return (T) getNode(index).getElement();

    }

    public Node getNode(int index){

        try{
            Node current= head;
            for (int i=0; i <index; i++){
                current= current.getNext();
            }

            return current;
        }
        catch (NullPointerException e){
            return null;
        }
    }


    public SinglyLinkedList<T> copy(){
        SinglyLinkedList copy= new SinglyLinkedList();
        Node current= head;
        while(current != null){
            copy.add(current.getElement());
            current= current.getNext();
        }


        return copy;
    }

    public void switched(Node one, Node two){
        one.setNext(two.getNext());
        if (one.getNext() !=null){
            one.getNext().setPrevious(one);
        }else{
            this.last= one;
        }

        two.setPrevious(one.getPrevious());
        if(two.getPrevious() != null){
            two.getPrevious().setNext(two);
        }else {
           this.head= two;

        }
        one.setPrevious(two);
        two.setNext(one);
    }

    public void sort(){
        for (int i=0; i < this.size(); i++){
            for (int j=0; j <this.size()-i; j++){
                Comparable head= (Comparable)getNode(j-1).getElement();
                Comparable last= (Comparable) getNode(j).getElement();
                if(head.compareTo(last) >0){
                    switched(getNode(j-1), getNode(j));
                }
            }
        }


    }

    public Iterator<T> iterator() {
        return null;
    }


    public class Node<T> {

        private Node next;
        private Node previous;
        private T element;

        public Node( Node previous, T element, Node next) {
            this.element = element;
            this.previous = null;
            this.next = null;

        }

        public T getElement() {

            return element;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }
}

