/**
 * 
 */
package com.jhu.ds.lab3.MatrixOperations;

/**
 * @author  ARAVIND RAVINDRANATH
 * @version 1.0
 *
 * Class defining the structure of a data element or data node which will be stored in a matrix.
 * 
 * Consists of 3 attributes, one an integer type which is the relevant information and 
 * a pointer to the next row and a pointer to the next column. In general this node structure
 * would be used in the matrix representation and would generally have a forward and downward link.
 * This means that from a particular element in the matrix, we can navigate to the next element to
 * its right and/or to the element below.
 * 
 * Also contains methods which provides read and write access to the attributes
 * 
 */

public class ElementNode {
	private int data;  //holds the integer which is the info needed
	private ElementNode nextColumn; //holds a reference to the next column
	private ElementNode nextRow;    //holds a reference to the next row
	
	
	/*
	 * Method which sets the integer value in the data part of the node
	 * 
	 * @param i		The integer value which is the actual relevant data 
	 */	
	public void setData(int i){
		this.data = i;
	}
	
	/*
	 * Method which sets the reference to the next column
	 * 
	 * @param node		Reference to the next column required to build a matrix 
	 */
	public void setNextColumn(ElementNode node){
		this.nextColumn = node;
	}
	
	/*
	 * Method which sets the reference to the next row
	 * 
	 * @param node		Reference to the next row required to build a matrix 
	 */
	public void setNextRow(ElementNode node){
		this.nextRow = node;
	}
	
	/*
	 * Constructor method to set the attributes
	 * 
	 * @param i			  The integer value which is the actual relevant data
	 * @param nextColumn  Reference to the next column required to build a matrix
	 * @param nextRow	  Reference to the next row required to build a matrix		
	 */
	public ElementNode(int i, ElementNode nextColumn, ElementNode nextRow){
		this.data = i;
		this.nextColumn = nextColumn;
		this.nextRow = nextRow;
	}
	
	//method which returns the current instance of the class
	public ElementNode getNode(){
		return this;
	}

	//Method which provides the reference to the next Column
	public ElementNode getNextColumn(){
		return this.nextColumn;
	}
	
	//Method which provides the reference to the next Row
	public ElementNode getNextRow(){
		return this.nextRow;
	}
	
	//method which returns the data part of the node
	public int getData(){
		return this.data;
	}
		
	
}
