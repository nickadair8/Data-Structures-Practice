import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class doubleLinkedList {

    static Node head = null; // head of list
    static Node tail = null; // tail of list
    public static int numNodes = 0; // keeps track of the num of nodes in the linked list

    public static class Node {
        public int freq; // the amount of times the word shows up
        public String val; // word held in node
        public Node next, prev;

        Node(String v) {
            this.val = v;
            this.next = null;
            this.prev = null;
            this.freq = 1;
        }
    }

    // creates the list
    // to build the list runs O(n), entire function runs O(n^2)
    public void createList(File text) throws IOException {
        try (Scanner scanner = new Scanner(text)) {
            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                Node node = new Node("");
                node.val = temp;
                if(numNodes == 0){
                    addNode(node);
                }
                wordCount(node);
                numNodes++;
            }
        }
            
    }

    public static Node getHead() {
        return head;
    }

  
    // runs O(n)
    // remod to run thru array instead of list, add to this list if the val does not
    // exists.
    public void wordCount(Node node) {
        Node temp = head;
        boolean flag = false;
        if (temp == null) {
            System.out.println("head is empty");
        }
        while (temp != null) {
            if (temp.val.equals(node.val)) {
                node.freq++;
                flag = true;
            }
            temp = temp.next;
        }
        if(flag == false){
          addNode(node);
        }

    }

    // adds a new node to end of list / creates head if needed
    // runs O(1)
    public static void addNode(Node node) {
        Node newNode = node;
        if (head == null) {
            head = tail = newNode;
            head.prev = null;
            tail.next = null;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            tail.next = null;
        }
    }

    // prints the list and the freq val
    // runs in O(n)
    public void printList() {
        Node tempNode = head; // set a temp to head so head isn't modified
        if (tempNode == null) {
            System.out.println("List empty");
        }
        while (tempNode != null) {
            System.out.println(tempNode.val + " " + tempNode.freq);
            tempNode = tempNode.next;
        }
    }

    // public Node splitList(Node head){

    // }

    public Node mergeSort(Node top) {
        Node temp = new Node("");
        if (top == null || top.next == null) { // base cases [checks to see if the list has any entries or if we got a single node]
            return top;
        }
        temp = middleNode(top);
        top = mergeSort(top);
        temp = mergeSort(temp);

        return merge(top, temp);
 
    }

    // helper to get the middle nodes, runs in O(n/2)
    public Node middleNode(Node head) {
        Node fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Node temp = slow.next;
        slow.next = null;
        return temp;
    }

    public Node merge(Node first, Node second) {
        // If first linked list is empty
        if (first == null) {
            return second;
        }
 
        // If second linked list is empty
        if (second == null) {
            return first;
        }
 
        // Pick the smaller value
        if (first.freq < second.freq) {
            first.next = merge(first.next, second);
            first.next.prev = first;
            first.prev = null;
            return first;
        }
        else {
            second.next = merge(first, second.next);
            second.next.prev = second;
            second.prev = null;
            return second;
        }
    }

    public static void main(String[] args) throws IOException {
        File text = new File("inputs/largerTest.txt");
        doubleLinkedList list = new doubleLinkedList();
        list.createList(text);
       // list.mergeSort(getHead());

        list.printList();
        // System.out.println(numNodes);
    }
}