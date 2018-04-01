// CMPS 12B 
// 1/18/2018
// Recursion.java
// Finds the index of min and max through mergesorting and uses three different methods to reverse the order of given array.
  
 class Recursion {
	static int max_ind = 0;
	static int min_ind = 0;
	public static void main(String[] args){
		int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
		int[] B = new int[A.length];
		int[] C = new int[A.length];
		int minIndex = minArrayIndex(A, 0, A.length-1);
		int maxIndex = maxArrayIndex(A, 0, A.length-1);
		for(int x: A) System.out.print(x+" ");
		System.out.println();
		System.out.println( "minIndex = " + minIndex );
		System.out.println( "maxIndex = " + maxIndex );
		reverseArray1(A, A.length, B);
		for(int x: B) System.out.print(x+" ");
		System.out.println();
		reverseArray2(A, A.length, C);
		for(int x: C) System.out.print(x+" ");
		System.out.println();
		reverseArray3(A, 0, A.length-1);
		for(int x: A) System.out.print(x+" ");
		System.out.println();
		}
	static void reverseArray1(int[] X, int n, int[] Y){
		if(n>0){
			Y[X.length - n] = X[n-1];
			reverseArray1(X, n-1, Y);
			}
	}
	
	static void reverseArray2(int[] X, int n, int[] Y){
		if(n>0){
			reverseArray2(X, n-1, Y);
			Y[n-1] = X[X.length - n];
			}
	}
	static void reverseArray3(int[] X, int i, int j){
		if(X.length/2 != i){
			int temp = X[j];
			X[j] = X[i];
			X[i] = temp;
			reverseArray3(X, i+1, j-1);
		}
	}
	public static int maxArrayIndex(int[] A, int p, int r){
		int q;
		if(p<r){
			q = (p+r)/2;
			maxArrayIndex(A,p,q); 
			maxArrayIndex(A,q+1,r);
			if(A[p] > A[max_ind]){
				max_ind = p;
			}
			if( A[r] > A[max_ind]){
				max_ind = r; 
			}
 		}
		return max_ind;
	}
	public static int minArrayIndex(int[] A, int p, int r){
		int q;
		if(p<r){
			q = (p+r)/2;
			minArrayIndex(A,p,q); 
			minArrayIndex(A,q+1,r);
			if(A[p] < A[min_ind]){
				min_ind = p;
			}
			if( A[r] < A[min_ind]){
				min_ind = r; 
			}
 		}
		return min_ind;
	}
 }

