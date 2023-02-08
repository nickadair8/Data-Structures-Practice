import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class doubleLinkedList {

    static Node head = null; //head of list
    static Node tail = null; //tail of list

    public static class Node{
        public int freq; //the amount of times the word shows up
        public String val; //word held in node
        public Node next, prev; 

        Node(String v){
            this.val = v; 
            this.next = null;
            this.prev = null;
            this.freq = 0;
        }
    }

    //creates the list
    //to build the list runs O(n)
    public void createList(File text) throws IOException{
        try (Scanner scanner = new Scanner(text)) {
            while(scanner.hasNextLine()){
                String temp = scanner.nextLine();
                Node node = new Node("");
                node.val = temp;
                node.freq = wordCount(node.val);
                doubleLinkedList.addNode(node);
            }
        }
    }

    //runs O(n)
    public int wordCount(String v){
        Node temp = head;
        int count = 1;
        if(temp == null){
            return 1;
        }
        while(temp != null){
            if(temp.val == v) count++;
            temp = temp.next;
        }
        return count;
    }
    

    //adds a new node to end of list / creates head if needed
    //runs O(1)
    public static void addNode(Node node) {  
        Node newNode = node;  
        if(head == null) {  
            head = tail = newNode;   
            head.prev = null;  
            tail.next = null;  
        }  
        else {   
            tail.next = newNode;  
            newNode.prev = tail;  
            tail = newNode;    
            tail.next = null;  
        }  
    }  

    public void printList(){
        Node tempNode = head; //set a temp to head so head isn't modified
        if(tempNode == null){
            System.out.println("List empty");
        }
        while(tempNode != null){
            System.out.println(tempNode.val + " " + tempNode.freq);
            tempNode = tempNode.next;
        }
    }
   

    public static void main(String[] args) throws IOException {
        File text = new File("inputs/largerTest.txt");
        doubleLinkedList list = new doubleLinkedList();
        list.createList(text);
        list.printList();
    }
}