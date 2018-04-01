// CMPS 12B
// 2/7/2018
// DictionaryTest.java
// Tests run as ADT operations were implemented

public class DictionaryTest {

	   public static void main(String[] args){
	      Dictionary B = new Dictionary();
//	      testing for isEmpty() and size()
//	      System.out.println(B.isEmpty());
//	      System.out.println(B.size());
	      
	      // testing for insert() and lookup()
	      
	      B.insert("1", "A");
	      B.insert("2", "B");
	      B.insert("3", "C");
	      B.insert("4", "D");
//	      System.out.println(B.lookup("1"));
//	      System.out.println(B.lookup("2"));
//	 //  B.insert("1", "A"); // *exception works*
//	      System.out.println(B.isEmpty());
//	      System.out.println(B.size());
//	      B.delete("4");
//	      B.delete("3");
//	      System.out.println(B.size());
	      
//	      B.makeEmpty();
//	      System.out.println(B.isEmpty());
//	      System.out.println(B.size());
	      
//	      B.delete("1");
//	      System.out.println(B.size());
	      System.out.println(B);
	      B.delete("1");
	      System.out.println(B);
	      B.delete("3");
	      System.out.println(B);
	   }
}
		   


