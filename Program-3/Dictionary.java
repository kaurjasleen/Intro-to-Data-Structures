// CMPS 12B
// 2/7/2018
// Dictionary.java
// File implements a Dictionary ADT using linked list data structure in Java with seven ADT operations.

public class Dictionary implements DictionaryInterface{
	
	public Node head = null;
	public Node tail = null; 
	
	private class Node{
		public String key;
		public String value;
		public Node next;
		public Node(String x, String y){
			key = x;
			value = y; 
			next = null; 
		}
	}
	
	// isEmpty()
	   // pre: none
	   // returns true if this Dictionary is empty, false otherwise
	   public boolean isEmpty(){
		   if (head == null){
			   return true;
		   }
		   return false;
	   }

	   // size()
	   // pre: none
	   // returns the number of entries in this Dictionary
	   public int size(){
		   int total = 0; 
		   for(Node N = head; N != null; N=N.next){
			   total+=1;
		   }
		   return total;
	   }

	   // lookup()
	   // pre: none
	   // returns value associated key, or null reference if no such key exists
	   public String lookup(String key){
		   for(Node N = head; N != null; N=N.next){
			   if(N.key == key){
				   return N.value; 
			   }
		   }
		   return null;
	   }

	   // insert()
	   // inserts new (key,value) pair into this Dictionary
	   // pre: lookup(key)==null
	   public void insert(String key, String value) throws DuplicateKeyException{
		   if(lookup(key) != null){ 
			      throw new DuplicateKeyException("cannot insert duplicate keys");
		   } else{
			   Node n = new Node(key, value);
			   if(size() == 0){
				   head = n; 
				   tail = n;
			   }
			   else{
				   tail.next = n;
				   tail = n; 
			   }
		   }
	   }

	   // delete()
	   // deletes pair with the given key
	   // pre: lookup(key)!=null
	   public void delete(String key) throws KeyNotFoundException{
		   if(lookup(key) == null){
			   throw new KeyNotFoundException("cannot delete non-existent key");
		   } else{
			   if(size() == 1){
				   head = null;
				   tail = null;
			   } else if(head.key == key){
				   head = head.next;
			   } else{
				   for(Node N = head; N != null; N=N.next){
					   if(N.next.key == key){
						   if(tail.key == key){
							   tail = N;
							   N.next = null;
							   break;
						   }
						  // N.next = null;
						   N.next =  N.next.next;
						   break;
					   }
				   }
			   }
		   }
	   }

	   // makeEmpty()
	   // pre: none
	   public void makeEmpty(){
		   head = null;
		   tail = null;
	   }

	   // toString()
	   // returns a String representation of this Dictionary
	   // overrides Object's toString() method
	   // pre: none
	   public String toString(){
	   String dictionary = "";
           Node N = head;
           while(N != null){
               dictionary += N.key + " " + N.value + "\n";
               N = N.next;
           }
		   return dictionary;
	   }
		
}

