package com.jhu.ds.lab3.MatrixOperations.Iterative;


/**
 * @author ARAVIND RAVINDRANATH
 * @version 1.0
 * 
 * This exception is thrown if the square matrix does not get reduced to an 
 * upper triangular form after performing the desired set of row operations.
 *
 */
public class NotRowReducedForm extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1699360249255322078L;
	
	public NotRowReducedForm(String msg){
		super(msg);
	}

}
