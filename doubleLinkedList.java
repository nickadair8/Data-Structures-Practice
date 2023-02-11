import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class doubleLinkedList {

    static Node head = null; // head of list
    static Node tail = null; // tail of list
    public static int numNodes = 0; // keeps track of the num of nodes in the linked list
    public static HashMap<String, Integer> wordTable = new HashMap<String, Integer>();

    public static class Node {
        public int freq; // the amount of times the word shows up
        public String val; // word held in node
        public Node next, prev;

        Node(String v) {
            this.val = v;
            this.next = null;
            this.prev = null;
            this.freq = 0;
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
                node.freq = wordCount(node);
                findDups(node);
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
    public int wordCount(Node node) {
        Node temp = head;
        int count = 1;
        if (temp == null) {
            return 1;
        }
        while (temp != null) {
            if (temp.val.equals(node.val)) {
                count++;
            }
            temp = temp.next;
        }
        return count;
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

    public static void findDups(Node node) {
        if (node == null) {
            return;
        }
        if (wordTable.containsKey(node.val)) {
            wordTable.put(node.val, ++node.freq);
            deleteNode(node);
        } else {
            wordTable.put(node.val, node.freq);
        }

    }

    static void deleteNode(Node del) {
        if(head != null) {
    
            //1. if head in not null and next of head
            //   is null, release the head
            if(head.next == null) {
              head = null;
            } else {
              
              //2. Else, traverse to the second last 
              //   element of the list
              Node temp = new Node("");
              temp = head;
              while(temp.next.next != null)
                temp = temp.next;
              
              //3. Change the next of the second 
              //   last node to null and delete the
              //   last node
              Node lastNode = temp.next;
              temp.next = null; 
              lastNode = null;
            }
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
        Node leftList = new Node("");
        Node rightList = new Node("");
        Node temp = new Node("");
        if (top == null || top.next == null) { // base cases [checks to see if the list has any entries or if we got a single node]
            return top;
        }

        // split the list in half
        leftList = top;
        rightList = middleNode(top);
        temp = rightList.next;
        rightList.next = null;
        rightList = temp;

        // recursively sorts each seperated list
        leftList = mergeSort(leftList);
        rightList = mergeSort(rightList);

        return merge(leftList, rightList);
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

    public Node merge(Node left, Node right) {
        Node tail = new Node("");
        Node temp = tail;
        while (left != null && right != null) {
            if (left.freq < right.freq) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        if (left != null) {
            tail.next = left;
        }
        if (right != null) {
            tail.next = right;
        }
        return temp.next;
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