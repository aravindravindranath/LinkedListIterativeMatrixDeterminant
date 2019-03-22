package com.jhu.ds.lab3.MatrixOperations.Iterative;

import com.jhu.ds.lab3.MatrixOperations.Iterative.ElementNodeDouble;
import com.jhu.ds.lab3.MatrixOperations.Iterative.NotRowReducedForm;
import com.jhu.ds.lab3.MatrixReuse.*;

/**
 * @author ARAVIND RAVINDRANATH
 * @version 1.0
 *
 *          The class contains the necessary attributes and methods to handle 
 *          square matrices. The class consists of methods to display the matrix and its 
 *          minor. There are methods to write the matrix into a string, to easily write to
 *          a file. The crux of the class is to enable building of a minor matrix of a given 
 *          element in the square matrix and also the calculation of the determinant.
 *          Right now the elements are designed to be of type double due to floating point operations. 
 *          The basic idea here is to build the matrix as a linked list, with a head node 
 *          pointing to the element in the first row and first column. 
 *          A multi linked list is used here, where each node which represents an 
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
		ElementNodeDouble head;   // Reference to the first top and leftmost element in the matrix
		
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
		void sethead(ElementNodeDouble head){
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
		ElementNodeDouble gethead(){
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
		ElementNodeDouble temp = null;
		ElementNodeDouble prevRow = null;
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
				ElementNodeDouble e = new ElementNodeDouble(0, null, null);
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
		ElementNodeDouble temp = null;
		ElementNodeDouble prevRow = null;
		for (int i = 0; i < this.head.getOrder(); i++) {
			prevRow = head.gethead();
			if( i > 1){
				for (int j = 0; j < i-1; j++) {
					prevRow = prevRow.getNextRow();
				}
			}
			for (int j = 0; j <this.head.getOrder(); j++) {
				ElementNodeDouble e = new ElementNodeDouble(array[i][j], null, null);
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
			ElementNodeDouble e = head.gethead();
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
			ElementNodeDouble e = head.gethead();
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
	@Override
	public void fillValue(int data, int rowPos, int colPos){
		
		boolean found = false;
		
		for (int i = 0; i < head.getOrder(); i++) {
			ElementNodeDouble e = head.gethead();
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
		ElementNodeDouble prevRow = null;
		ElementNodeDouble colPtr  = null;
		MyLinkedMatrix minor = new MyLinkedMatrix();
		minor.setOrder(matrix.head.order - 1);
		ElementNodeDouble e = matrix.head.gethead();
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
			ElementNodeDouble col = e;
			while( col != null){
				if( y == colPos ){
					col = col.getNextColumn();
					y++;
					continue;
				}
				ElementNodeDouble minorElement = new ElementNodeDouble(col.getData(), null, null);
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
	 * This method calculates the determinant of an nth order square matrix. The algorithm relies 
	 * on a few elementary row operations and reduces the matrix to an upper triangular matrix.
	 * Essentially all elements below the diagonal will end up with initial value of 0 after 
	 * all the necessary reductions. In this state the determinant will be the product of all 
	 * elements of the diagonal. The element in the first row and column is made the maximum in 
	 * the first column to ensure stability. No swapping of columns are done, to keep the algorithm
	 * straight forward. The algorithm implemented is a slight variant of Gauss-Jordan Elimination.
	 * 
	 * Each diagonal element is picked up and all the elements under it and to the left are set to
	 * 0 by calculating a factor which is applied to the rows below. If there are 3 rows, then we 
	 * find that the factor to make the second row's first element zero, leading to m, then we perform 
	 * the following row operation:
	 * R2 -> R2 - m.R1;  All elements of Row 2 are transformed, for the first element m.R1 = R2, 
	 * leading to an initial element.
	 * This is repeated for the third row, finding factor n to make the first element in the row 
	 * initial. R3 -> R3 - n.R1, for the first element m.R1 = R2
	 * This process is repeated now for the second diagonal element. In the meanwhile the first elements
	 * of row R2 and R3 are already 0, and further elementary row operations on them will have no
	 * impact. For the second diagonal, then we have to make the second element in 3rd row zero, leading
	 * to the following row operation:
	 * R3 -> R3 - p.R2, where for the 2nd element of R3, p.R2 = R3. This will not change the first 
	 * element, since the element above it is 0, and multiplication and additions on 0, does not have
	 * any consequences.
	 * Thinking of a higher order matrix, we do the row reductions like the way described above, we
	 * can build an upper triangular matrix, leading to an easy calculation of determinants.
	 * 
	 * @param	matrix	Multi Linked List representing a square matrix
	 *                  whose determinant is calculated 
	 * 
	 */
	
	public long calcDeterminantGaussian(MyLinkedMatrix matrix) throws NotRowReducedForm{
		int maxIndex = 0;
		double scalar = 1.0;
		int downCounter = 0;
		double factor = 0.0;
		double det = 1.0;
		ElementNodeDouble e = matrix.head.gethead();
		double maxValue = e.getData();
		
		//Get the maximum value in the first column.
		for (int i = 0; i < matrix.head.getOrder(); i++) {
			if (e.getData() > maxValue) {
				maxValue = e.getData();
				maxIndex = i;
			}
			e = e.getNextRow();
		}

		//Set the first row/first column element as the maximum value, by multiplying
		//the first row with the maximum value. Otherwise there could be some strange problems
		//during the reduction process. The maximum column value is used as the multiplier and this
		//then used as divisor before the final determinant is calculated. 
		if (maxIndex != 0) {
			e = matrix.head.gethead();
			while (e != null) {
				e.setData(e.getData() * maxValue);
				e = e.getNextColumn();
			}
			scalar = scalar / maxValue;
		}
		

		for (int i = 0; i < matrix.head.getOrder() - 1; i++) {
			downCounter = 0;
			e = matrix.head.gethead();
			//As we traverse through the diagonal, we need a reference to the diagonal as well
			//the beginning of the row on which the diagonal element resides. The beginning reference
			//is required for doing row operations starting from the first element.
			/*
			 * Remark: There is scope for optimization by not doing operations on elements to the left
			 * of the diagonal. Scope for improvement.
			 */
			ElementNodeDouble rowBegin = matrix.head.gethead();;
			for (int k = 0; k < i; k++) {
				e = e.getNextRow();
				rowBegin = rowBegin.getNextRow();
				e = e.getNextColumn();
			}
			double diagonal = e.getData();
			if (diagonal == 0.0){  
				continue;
			}
			
			ElementNodeDouble up = rowBegin;
			ElementNodeDouble down = e.getNextRow();

			//For each diagonal, perform row operations on all the rows below, to make the 
			//the elements in the column below the diagonal element zero. At this time, it is
			//assumed that all elements to the left of this column are already zero, based on the 
			//the operations done for the predecessor diagonal
			
			factor = down.getData() / diagonal;
			for (int j = i; j < matrix.head.getOrder() - 1; j++) {
				down = matrix.head.gethead().getNextRow();
				for (int k = 0; k < j; k++) {
					down = down.getNextRow();
				}
				downCounter = 0;
				while (down != null) {
					down.setData(down.getData() - up.getData() * factor);
					down = down.getNextColumn();
					up = up.getNextColumn();
				}
				up = rowBegin;
				down = e.getNextRow();
				while (downCounter <= j-i ) {
					down = down.getNextRow();
					downCounter++;
				}
				if( down != null){
					factor = down.getData() / diagonal;
				}
				
			}
		}
		
		
	    //Validate if the final matrix is upper triangular matrix or not.	
		for (int i = 0; i < matrix.head.getOrder(); i++) {
			e = matrix.head.gethead();
			for (int j = 0; j < i; j++) {
				e = e.getNextRow();
			}
			for (int j = 0; j < i; j++) {
				if (Math.round(e.getData()) != 0){
					throw new NotRowReducedForm("Not a upper triangular Matrix. " +
											   "Not row reduced.");	
				}
				e = e.getNextColumn();
			}
		}
		
		e = matrix.head.gethead();
		while( e != null){
			det *= e.getData();
			e = e.getNextRow();
			if ( e == null){
				break;
			}
			e = e.getNextColumn();
		}
		det = scalar * det;
		long res = Math.round(det);
		return res;
	}


	/* 
	 * (non-Javadoc)
	 * @see com.jhu.ds.lab3.MatrixReuse.MyAbsMatrixDeterminant#calculateDeterminant(com.jhu.ds.lab3.MatrixReuse.MyAbsMatrixDeterminant)
	 */
	@Override
	public long calculateDeterminant(MyAbsMatrixDeterminant absMatrix) 
														throws NotRowReducedForm{
		long res = 0;
		MyLinkedMatrix matrix = (MyLinkedMatrix) absMatrix;
		res = calcDeterminantGaussian(matrix);
		return res;
	}

	
	
}
