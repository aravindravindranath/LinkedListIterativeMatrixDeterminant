/**
 * 
 */
package com.jhu.ds.lab3.MatrixOperations.Iterative;

/**
 * @author  ARAVIND RAVINDRANATH
 * @version 1.0
 *
 * Class defining the structure of a data element or data node which will be stored in a matrix.
 * 
 * Consists of 3 attributes, one a double type which is the relevant information and 
 * a pointer to the next row and a pointer to the next column. In general this node structure
 * would be used in the matrix representation and would generally have a forward and downward link.
 * This means that from a particular element in the matrix, we can navigate to the next element to
 * its right and/or to the element below.
 * 
 * Also contains methods which provides read and write access to the attributes
 * 
 */

public class ElementNodeDouble {
	private double data;  //holds the double value which is the info needed
	private ElementNodeDouble nextColumn; //holds a reference to the next column
	private ElementNodeDouble nextRow;    //holds a reference to the next row
	
	
	/*
	 * Method which sets the double value in the data part of the node
	 * 
	 * @param i		The double value which is the actual relevant data 
	 */	
	public void setData(double d){
		this.data = d;
	}
	
	/*
	 * Method which sets the reference to the next column
	 * 
	 * @param node		Reference to the next column required to build a matrix 
	 */
	public void setNextColumn(ElementNodeDouble node){
		this.nextColumn = node;
	}
	
	/*
	 * Method which sets the reference to the next row
	 * 
	 * @param node		Reference to the next row required to build a matrix 
	 */
	public void setNextRow(ElementNodeDouble node){
		this.nextRow = node;
	}
	
	/*
	 * Constructor method to set the attributes
	 * 
	 * @param d			  The double value which is the actual relevant data
	 * @param nextColumn  Reference to the next column required to build a matrix
	 * @param nextRow	  Reference to the next row required to build a matrix		
	 */
	public ElementNodeDouble(double d, ElementNodeDouble nextColumn, ElementNodeDouble nextRow){
		this.data = d;
		this.nextColumn = nextColumn;
		this.nextRow = nextRow;
	}
	
	//method which returns the current instance of the class
	public ElementNodeDouble getNode(){
		return this;
	}

	//Method which provides the reference to the next Column
	public ElementNodeDouble getNextColumn(){
		return this.nextColumn;
	}
	
	//Method which provides the reference to the next Row
	public ElementNodeDouble getNextRow(){
		return this.nextRow;
	}
	
	//method which returns the data part of the node
	public double getData(){
		return this.data;
	}
		
	
}
