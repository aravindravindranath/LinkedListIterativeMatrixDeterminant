package com.jhu.ds.lab2.MatrixOperations;

/**
 * @author ARAVIND RAVINDRANATH
 * @version 1.0
 *
 *          The class contains the necessary attributes and methods to handle 
 *          square matrices. The class consists of methods to display the matrix and its 
 *          minor. There are methods to write the matrix into a string, to easily write to
 *          a file. The crux of the class is to enable building of a minor matrix of a given 
 *          element in the square matrix and also the calculation of the determinant.
 *          Right now the elements are designed to be of type int. 
 *
 */

public class SquareMatrix {
	int[][] matrix = null;  //Two dimensional matrix undergoing determinant calculation
	int rowPos = 0;         //Row position of element used for minor matrix determination
	int colPos = 0;         //Column position of element used for minor matrix determination 
	int order = 0;          //Order of the matrix
	
	public SquareMatrix(int[][] array, int rowPos, int colPos, int order){
		this.matrix = array;
		this.rowPos = rowPos;
		this.colPos = colPos;
		this.order = order;
	}
	
	//overloaded constructor
	public SquareMatrix(int[][] array){
		this(array, 0, 0, array.length);
	}
	
	/*
	 * This method creates a new matrix from an existing 
	 * matrix. It serves the purpose of building a minor
	 * matrix for a given element defined by the coordinates. 
	 * This method is primarily used for unit testing
	 * 
	 * @param	x	The row id of the element for which, minor needs to be found
	 * 			y   The column id of the element for which, minor needs to be found
	 */
	public SquareMatrix getSubMatrix( int x, int y){
		return new SquareMatrix(matrix, x, y, order);
	}
	
	/*
	 * This method prints the instance attribute matrix onto the console.
	 * Could have been implemented by overriding toString() as an alternative
	 * 
	 */
	public void displayMatrix(){
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix.length; j++) {
				System.out.print(this.matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	/*
	 * Overloaded static method for display, meant as a utility
	 * Not used productively
	 * 
	 * @param	matrix	Two dimensional integer array whose contents are printed onto the console
	 */
	public static void displayMatrix(int[][] matrix){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	/*
	 * This static method returns a StringBuilder Object which contains
	 * the contents of the square matrix in a formatted way. Used for 
	 * streaming into an output file.
	 * 
	 * @param	matrix	Two dimensional integer array whose contents are stored in a StirngBuilder
	 *                  object.
	 */
	public static StringBuilder printMatrix(int[][] matrix){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < matrix.length; i++) {
			//sb.append("|");
			for (int j = 0; j < matrix.length; j++) {
				sb.append(matrix[i][j]).append(" ");
			}
			//sb.replace(sb.length()-1, sb.length(), "|");
			sb.append("\n");
		}
		
		return sb;
	}
	
	/*
	 * Similar to the method printMatrix but modeled as an instance method which calls 
	 * the static method. The matrix comes from the attribute. 
	 */
	public StringBuilder printMatrix(){
		return printMatrix(this.matrix);
	}
	
	
	/*
	 * This method returns the minor of the matrix for element defined by attributes
	 * rowPos and colPos.
	 */
	public int[][] buildMinorMatrix(){
		int x = 0;
		int y = 0;
		int[][] minor = new int[order-1][order-1];
		for (int i = 0; i < matrix.length; i++) {
			if ( i == this.rowPos){
				continue;
			}
			for (int j = 0; j < matrix.length; j++) {
				if( j == this.colPos ){
					continue;
				}
				minor[x][y] = matrix[i][j];
				y++;
			}
			x++;
			y=0;
		}
		return minor;
	}
	
	/*
	 * This method returns the minor of the matrix for an element at a certain row
	 * and column passed as parameters. This method is called within a recursive algorithm
	 * to calculate determinants. 
	 * 
	 * @param	matrix	The matrix for which the minor matrix is built, a 2d array
	 * 			rowPos  Row id of the element for which the minor needs to be determined
	 * 			colPos  Column id of the element for which the minor needs to be determined
	 */
	
	public int[][] buildMinorMatrix(int matrix[][], 
					int rowPos, int colPos) throws NotASquareMatrix{
		int x = 0;
		int y = 0;
		int size = 0;
		for (int i = 0; i < matrix.length; i++) {
			size += matrix[i].length;
		}
		if ( Math.pow(matrix.length, 2) != size ){
			System.out.println("Not a Square Matrix");
			throw new NotASquareMatrix("Not a Square Matrix");
		}
		int[][] minor = new int[matrix.length-1][matrix.length-1];
		for (int i = 0; i < matrix.length; i++) {
			if ( i == rowPos){
				continue;
			}
			for (int j = 0; j < matrix.length; j++) {
				if( j == colPos ){
					continue;
				}
				minor[x][y] = matrix[i][j];
				y++;
			}
			x++;
			y=0;
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
	 * albeit any row or even column could be used.
	 * 
	 * @param	matrix	2d int array whose determinant is calculated 
	 * 
	 */
	
	public int calcDeterminant(int[][] matrix) throws NotASquareMatrix{
		int determinant = 0;
		if ( matrix.length == 1){ //base case
			return matrix[0][0];
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
			for (int i = 0; i < matrix.length; i++) {
				determinant += (int)Math.pow(-1, i) * matrix[0][i] * 
						calcDeterminant(buildMinorMatrix(matrix, 0, i));
			}
			return determinant;
		} 
	}
	
	
	/*
	 * Method to display the minor matrix of an element. The element has 
	 * co-ordinates rowPos, colPos in the matrix
	 */
	public void displayMinorMatrix(){
		for (int i = 0; i < this.matrix.length; i++) {
			if ( i == this.rowPos){
				continue;
			}
			for (int j = 0; j < this.matrix.length; j++) {
				if( j == this.colPos ){
					continue;
				}
				System.out.print(this.matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	/*
	 * Method which returns a string builder containing the minor matrix of 
	 * an element, similar to the method displayMinorMatrix.
	 */
	
	public StringBuilder printMinorMatrix(int[][] matrix){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < matrix.length; i++) {
			sb.append("|");
			for (int j = 0; j < matrix.length; j++) {
				sb.append(matrix[i][j]).append(" ");
			}
			sb.replace(sb.length()-1, sb.length(), "|");
			sb.append("\n");
		}
		
		return sb;
	}
}
