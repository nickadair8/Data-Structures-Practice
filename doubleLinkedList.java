import java.io.IOException;
import java.io.InputStream;
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


    public void createList(InputStream file) throws IOException{
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()){
                doubleLinkedList.addNode(scanner.nextLine());
            }
        }
    }
    

    //adds a new node to end of list / creates head if needed
    public static void addNode(String v) {  
        Node newNode = new Node(v);  
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
   

    public static void main(String[] args) {
        doubleLinkedList list = new doubleLinkedList();
        list.createList("t");
    }
}