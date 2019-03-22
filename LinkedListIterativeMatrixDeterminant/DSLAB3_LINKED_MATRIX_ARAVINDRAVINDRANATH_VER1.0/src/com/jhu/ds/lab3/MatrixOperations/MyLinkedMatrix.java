package com.jhu.ds.lab3.MatrixOperations;

import com.jhu.ds.lab3.MatrixReuse.MyAbsMatrixDeterminant;

/**
 * @author ARAVIND RAVINDRANATH
 * @version 1.0
 *
 *          The class contains the necessary attributes and methods to handle 
 *          square matrices. The class consists of methods to display the matrix and its 
 *          minor. There are methods to write the matrix into a string, to easily write to
 *          a file. The crux of the class is to enable building of a minor matrix of a given 
 *          element in the square matrix and also the calculation of the determinant.
 *          Right now the elements are designed to be of type int. The basic idea here is to build
 *          the matrix as a linked list, with a head node pointing to the element in the first row 
 *          and first column. A multi linked list is used here, where each node which represents an 
 *          element of the matrix has references to two other nodes, one to the right which is the 
 *          next element in the row and one to the bottom element in the same column.
 *          
 *          head
 *            |
 *            ---->A00---->A01
 *					 |							          		
 *					 |
 *					\|/				
 *					A10
 *
 *			The determinant calculation is performed with the first row as the pivot and the 
 *			traversal will be to the right and to the bottom most of the time. We just need the 
 *          pointer to the beginning of a row which can be achieved by navigating from the head 
 *          node 
 */

public class MyLinkedMatrix extends MyAbsMatrixDeterminant{
	
	/*
	 * The private class holds the order of the matrix represented in the main class
	 * and also contains a reference to the element in the first row and first column
	 * 
	 */
	private class Head{
		int order;          // Order of the square matrix
		ElementNode head;   // Reference to the first top and leftmost element in the matrix
		
		/*
		 * Method to set the order of the square matrix
		 * 
		 *@param order	An integer value defining the order of the matrix
		 */
		void setOrder(int order){
			this.order = order;
		}
		
		/*
		 * Method to set the reference to the element in the first row and column
		 * 
		 * @param head	An object of type ElementNode which holds the reference to a matrix
		 * 				 element	
		 */
		void sethead(ElementNode head){
			this.head = head;
		}
		
		/*
		 * Method which returns the order of the matrix
		 * 
		 */
		int getOrder(){
			return this.order;
		}
		
		/*
		 * Method which returns the reference to the element in first row and column
		 */
		ElementNode gethead(){
			return this.head;
		}
		
	};

	private Head head = new Head();  //head object used throughout the class
	
	
	/* 
	 * (non-Javadoc)
	 * @see com.jhu.ds.lab3.MatrixReuse.MyAbsMatrixDeterminant#setOrder(int)
	 */
	@Override
	public void setOrder(int order){
		this.head.setOrder(order);
	}
	
	
	/* 
	 * (non-Javadoc)
	 * @see com.jhu.ds.lab3.MatrixReuse.MyAbsMatrixDeterminant#buildEmptyMatrix()
	 */
	@Override
	public void buildEmptyMatrix(){
		ElementNode temp = null;
		ElementNode prevRow = null;
		for (int i = 0; i < this.head.getOrder(); i++) {
			//when building rows we need to connect each element from the preceding
			//row to the succeeding row. This is the pattern followed in this class.
			//Therefore we need a reference to the previous row's first element.
			prevRow = head.gethead();
			if( i > 1){
				for (int j = 0; j < i-1; j++) {
					prevRow = prevRow.getNextRow();
				}
			}
			for (int j = 0; j <this.head.getOrder(); j++) {
				ElementNode e = new ElementNode(0, null, null);
				if ( i==0 && j==0 ){  // first row and column, reference to head
					this.head.sethead(e);
					prevRow = head.gethead();
				}else{
					if (temp != null){
						//sets the reference from the previous column element 
						//to the current column element. 
						temp.setNextColumn(e);  
					}
					if(i > 0){
						prevRow.setNextRow(e);
						prevRow = prevRow.getNextColumn();
					}
				}
				temp = e;
			}
			temp = null;
		}
	}
	
	/*
	 * This method builds a square matrix based on an input array. The logic is similar to the 
	 * method buildEmptyMatrix(), but each element gets the value from the corresponding array
	 * element. This is used for testing purposes and not used productively.
	 * 
	 * @param array	A 2d integer array representing a square matrix
	 *
	 */
	
	public void buildLinkedListMatrixfromArray(int[][] array){
		ElementNode temp = null;
		ElementNode prevRow = null;
		for (int i = 0; i < this.head.getOrder(); i++) {
			prevRow = head.gethead();
			if( i > 1){
				for (int j = 0; j < i-1; j++) {
					prevRow = prevRow.getNextRow();
				}
			}
			for (int j = 0; j <this.head.getOrder(); j++) {
				ElementNode e = new ElementNode(array[i][j], null, null);
				if ( i==0 && j==0 ){
					this.head.sethead(e);
					prevRow = head.gethead();
				}else{
					if (temp != null){
						temp.setNextColumn(e);
					}
					if(i > 0){
						prevRow.setNextRow(e);
						prevRow = prevRow.getNextColumn();
					}
				}
				temp = e;
			}
			temp = null;
		}
	}
	
	/*
	 * Method to the display the matrix onto the console. Starting from the first row/column
	 * element, the traversal is made across the row and then the reference is brought back
	 * to the beginning of next row via the head node.
	 */
	
	public void displayMatrix(){
		
		for (int i = 0; i < head.getOrder(); i++) {
			ElementNode e = head.gethead();
			if( i > 0){
				for (int j = 0; j <= i-1; j++) {
					e = e.getNextRow();
				}
			}	
			for (int j = 0; j < head.getOrder(); j++) {
				System.out.print( e.getData() + " ");
				e = e.getNextColumn();
			}
			System.out.print("\n");
		}
	}
	
	/* 
	 * (non-Javadoc)
	 * @see com.jhu.ds.lab3.MatrixReuse.MyAbsMatrixDeterminant#printMatrix()
	 */
	@Override
	public StringBuilder printMatrix(){
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < head.getOrder(); i++) {
			ElementNode e = head.gethead();
			if( i > 0){
				for (int j = 0; j <= i-1; j++) {
					e = e.getNextRow();
				}
			}	
			for (int j = 0; j < head.getOrder(); j++) {
				sb.append(e.getData()).append(" ");
				e = e.getNextColumn();
			}
			sb.append("\n");
		}
		
		return sb;
	}
	
	/* 
	 * (non-Javadoc)
	 * @see com.jhu.ds.lab3.MatrixReuse.MyAbsMatrixDeterminant#fillValue(int, int, int)
	 */
	public void fillValue(int data, int rowPos, int colPos){
		
		boolean found = false;
		
		for (int i = 0; i < head.getOrder(); i++) {
			ElementNode e = head.gethead();
			if( i > 0){
				for (int j = 0; j <= i-1; j++) {
					e = e.getNextRow();
				}
			}	
			for (int j = 0; j < head.getOrder(); j++) {
				if (i == rowPos && j == colPos){
					e.setData(data);
					found = true;
					break;
				}
				e = e.getNextColumn();
			}
			if (found == true){
				break;
			}

		}
	}

	/*
	 * This method returns the minor of the matrix for an element at a certain row
	 * and column passed as parameters. This method is called within a recursive algorithm
	 * to calculate determinants. The logic is similar to the method buildEmptyMatrix(), where a
	 * minor matrix is built by traversing through the list and excluding the row and column for 
	 * determing the sub matrix.
	 * 
	 * @param	matrix	The matrix for which the minor matrix is built, a multi linked list
	 * 					of type MyLinkedMatrix
	 * 			rowPos  Row id of the element for which the minor needs to be determined
	 * 			colPos  Column id of the element for which the minor needs to be determined
	 */
	
	public MyLinkedMatrix buildMinorMatrix(MyLinkedMatrix matrix, 
					int rowPos, int colPos) {
		int x = 0;
		int y = 0;
		int minI = 0;
		ElementNode prevRow = null;
		ElementNode colPtr  = null;
		MyLinkedMatrix minor = new MyLinkedMatrix();
		minor.setOrder(matrix.head.order - 1);
		ElementNode e = matrix.head.gethead();
		while( e != null){
			prevRow = minor.head.gethead();
			if( minI > 1){
				for (int j = 0; j < minI-1; j++) {
					prevRow = prevRow.getNextRow();
				}
			}
			if ( x == rowPos){ 
				e = e.getNextRow();
				x++;
				continue;
			}
			ElementNode col = e;
			while( col != null){
				if( y == colPos ){
					col = col.getNextColumn();
					y++;
					continue;
				}
				ElementNode minorElement = new ElementNode(col.getData(), null, null);
				if (colPtr != null){
					colPtr.setNextColumn(minorElement);
				}
				if (minor.head.gethead() == null){
					minor.head.sethead(minorElement);
				}
				if(minI > 0){
					prevRow.setNextRow(minorElement);
					prevRow = prevRow.getNextColumn();
				}
				colPtr = minorElement;
				col = col.getNextColumn();
				y++;
				
			}
			e = e.getNextRow();
			x++;
			y = 0;
			minI++;
			colPtr = null;
		}

		return minor;
	}
	
	/*
	 * This method calculates the determinant of an nth order square matrix. This relies 
	 * on a recursive approach that to find the determinant of a matrix, we end up calculating
	 * the determinant of the minors based on an element. This further leads to finding the 
	 * determinants of minors of minors until a base case is encountered, which is the determinant 
	 * of a 1*1 matrix. This is the matrix itself. The helper method buildMinorMatrix assists in 
	 * finding the right minor for an element. In this method the first row acts as the pivot 
	 * and works well with the linked list design where each node has references to the right and
	 * to the bottom.
	 * 
	 * @param	matrix	Multi Linked List representing a square matrix
	 *                  whose determinant is calculated 
	 * 
	 */
	
	public int calcDeterminant(MyLinkedMatrix matrix) {
		int determinant = 0;
		if ( matrix.head.getOrder() == 1){ //base case
			return matrix.head.gethead().getData();
		}else{
			/*
			 * The co-factors ( determinants of the minor matrices ) of the first row
			 * elements are calculated multiplied by the corresponding element and these products 
			 * are summed with an alternating sign. The determinant of a co-factor leads to 
			 * a recursive call, which follows the same pattern taking its first row elements, 
			 * calculating its co-factors, multiplying them and then adding the product like its
			 * superior matrix until the base case of 1*1 minor matrix is reached.
			 * 
			 */
			ElementNode e = matrix.head.gethead();
			for (int i = 0; i < matrix.head.getOrder(); i++) {
				determinant += (int)Math.pow(-1, i) * e.getData() * 
						calcDeterminant(buildMinorMatrix(matrix, 0, i));
				e = e.getNextColumn();
			}
			return determinant;
		} 
	}


	/* 
	 * (non-Javadoc)
	 * @see com.jhu.ds.lab3.MatrixReuse.MyAbsMatrixDeterminant#calculateDeterminant(com.jhu.ds.lab3.MatrixReuse.MyAbsMatrixDeterminant)
	 */
	@Override
	public long calculateDeterminant(MyAbsMatrixDeterminant absMatrix) 
															throws Exception {
		long res = 0;
		MyLinkedMatrix matrix = (MyLinkedMatrix) absMatrix;
		res = calcDeterminant(matrix);
		return res;
	}
	
	
}
