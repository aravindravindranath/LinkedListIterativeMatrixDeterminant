package com.jhu.ds.lab3.MatrixReuse;

/**
 * @author ARAVIND RAVINDRANATH
 * @version 1.0
 *
 *          The abstract class contains abstract methods to
 *          abstract the call of a few methods used in input/output processing. 
 *          The sub classes can inherit these methods and we can abstract the 
 *          caller, which could calculate the determinant recursively, iteratively et al.
 *
 */

public abstract class MyAbsMatrixDeterminant {
	
	/*
	 * Method to calculate the determinant of a square matrix, returning a long value
	 */
	public abstract long calculateDeterminant (MyAbsMatrixDeterminant absMatrix) 
		throws Exception;
	
	/*
	 * Method to the set the order of the matrix 
	 * 
	 * @param order An integer value representing the order
	 */
	public abstract void setOrder(int order);
	
	/*
	 * Method to build an empty matrix for a given order. Provides a template for
	 * node traversal and connecting the nodes ( right and bottom ). This method is called
	 * to generate an empty linked list based matrix and later on values are passed to 
	 * populate the right nodes.
	 */
	public abstract void buildEmptyMatrix();
	
	
	/*
	 * This method is used to populate a value in a particular node/element of the 
	 * linked list. This method assumes that the matrix is already built and then based 
	 * on the row and column, the right node is determined and the value is set.
	 * 
	 * @param data	  Value of the element
	 * 		  rowPos  Row no. of the concerned element
	 * 		  colPos  Column no. of the concerned element
	 */
	public abstract void fillValue(int data, int rowPos, int colPos);
	
	/*
	 * Returns a StringBuilder Object holding the square matrix.
	 */
	public abstract StringBuilder printMatrix();
}
