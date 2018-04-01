// CMPS 12B
// 1/27/2018
// Search.java
// Search for given words in given file and returns whether word was found, and if so, on which line.  

import java.util.Scanner; 
import java.io.*;

public class Search{
  public static void main(String[] args){
        Scanner in;
        if(args.length < 2 ){
        System.out.println("Usage: Search file target1 [target2 ...]");
        System.exit(1);
        }
        try{
        in = new Scanner(new File(args[0]));
        in.useDelimiter("\\Z"); // cuts off all spaces 
        String s = in.next(); // puts whole file into one 
        in.close();
        String[] word = s.split("\n");
        int lineCount = word.length;
        int[] lineNumber = new int[lineCount];
        for(int i = 0; i<lineCount; i++){
                lineNumber[i] = i+1;
                }
	mergeSort(word, lineNumber, 0, word.length-1);
	for(int i = 1; i<args.length; i++){
		binarySearch(word, lineNumber, 0, word.length-1, args[i]);
		}
	}
        catch(FileNotFoundException e){
                System.out.println(args[0] + "Not Found ");
                System.exit(1);}
        }
        static void mergeSort(String[] word, int[] lineNumber, int p , int r){
	     int q;
			if(p<r){
				q = (p+r)/2;
				mergeSort(word, lineNumber, p,q); 
				mergeSort(word, lineNumber, q+1,r);
				merge(word, lineNumber, p, q, r);
			}
	    }
	  static void merge(String[] word, int[] lineNumber, int p, int q, int r){
		int len1 = q-p+1; 
		int len2 = r - q;
			
		String[] L = new String[len1]; 
		String[] R = new String[len2];
		int[] Ln = new int[len1];
		int[] Rn = new int[len2];
				
		int x = 0;
		int y = 0;
		int z = 0;
				
		for(int i = 0; i<len1; i++){
			L[i] = word[p+i];
			Ln[i] = lineNumber[p+i];
		}
		for(int i = 0; i<len2; i++){
			R[i] = word[q+i+1];
			Rn[i] = lineNumber[q+i+1];
		}
				
		for(z = p; z<=r; z++){
			if(x<len1 && y<len2){
				if(L[x].compareTo(R[y]) < 0){
					word[z] = L[x];
					lineNumber[z] = Ln[x];
					x++;
				}else{
					word[z] = R[y];
					lineNumber[z] = Rn[y];
			        	y++;
			}   
			}else if(x<len1){
			 	word[z] = L[x];
				lineNumber[z] = Ln[x];
				x++;
			}else{
				word[z] = R[y];
				lineNumber[z] = Rn[y];
				y++;
				}
			}
		}
	 static void binarySearch(String word[], int[] lineNumber, int p, int r, String target){
		    int q; 
		    if(p>r){
		        System.out.println( target + " not found ");
		    }else{
		        q = (p+r)/2;
		        if(target.equals(word[q])){
		            System.out.println(target + " found on line " + lineNumber[q]);
		        }else if(target.compareTo(word[q]) < 0){
		            binarySearch(word,lineNumber, p, q-1,target);
		        }else{
		            binarySearch(word, lineNumber, q+1, r, target);
		        }
		        
		    }
		    
		}    
}	
