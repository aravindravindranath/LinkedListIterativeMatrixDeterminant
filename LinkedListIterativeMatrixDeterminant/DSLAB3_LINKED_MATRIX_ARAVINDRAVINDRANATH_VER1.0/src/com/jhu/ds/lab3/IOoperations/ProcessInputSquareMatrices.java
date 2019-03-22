/**
 * 
 */
package com.jhu.ds.lab3.IOoperations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.jhu.ds.lab3.MatrixReuse.MyAbsMatrixDeterminant;

/**
 * @author ARAVIND RAVINDRANATH
 * @version 1.0
 *
 *          The class contains logic to read a file placed in the Project
 *          Folder. The input file would have the square matrices of any order
 *          to be read. The output file will contain the square matrices along
 *          with its calculated determinant. This class will take the role of
 *          the driver.
 *
 */
public class ProcessInputSquareMatrices {

	/**
	 * This is the main method which will be called in this project. The input
	 * file which contains square matrices and an output file which contains the
	 * results are passed as arguments. These are passed into another method for
	 * the actual parsing of the input file data to derive the matrices, after
	 * which the determinants are calculated.
	 * 
	 * @param args
	 *            The normal dynamic arguments passed to the main. Two strings,
	 *            one representing the input file path along with the file name
	 *            and the other one the output file path along with the file
	 *            name
	 */
	public static void main(String[] args) {
        
		char runType = '\u0000';
		try {
			if( args.length <= 2){
				runType = 'R';  //default recursive calculation by Laplacian method
			}else{
				runType = args[2].charAt(0); //choice of the caller: Laplacian or iterative Gaussian
			}
			fileReader(args[0], args[1], runType);
			System.out.println("Processing Completed. Please Check the output file " + args[1]);
		} catch (IOException e) {
			System.out.println(e.toString());
			System.out.println("Please specify valid file names as arguments");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Please enter the input file and the output file as " + "the two arguments");
			System.exit(1);
		}

	}

	/**
	 * This is the method which will call File I/O JAVA library methods. The
	 * input file will be opened and using a buffered reader, the contents will
	 * be read line by line. A handle to output file will also be instantiated
	 * so that the results can be written into it as well. The string in each
	 * line will be traversed integer by integer and fed into a two dimensional
	 * array. The array is passed onto another class, where the determinant gets
	 * calculated.
	 * 
	 * @param InpFile
	 *            Input File which contains the string for analysis OutFile
	 *            Output file which contains the matrix and the determinant.
	 * 
	 */

	public static void fileReader(String InpFile, String OutFile, char runType) 
																throws IOException {
		String line;
		FileReader inputStream = new FileReader(InpFile); // input file
		FileWriter outputStream = new FileWriter(OutFile); // output file
		BufferedReader bReader = new BufferedReader(inputStream); // line reader

		StringBuilder sb = new StringBuilder();
		boolean bLoop = true;
		if (runType == 'I'){
			outputStream.write("Determinant calculated by iterative algorithm using Gauss-Jordan Elimination.\n");
			outputStream.write("--------------------------------------------------------------------------" +
								"\n\n");
		}else{
			outputStream.write("Determinant calculated by recursive algorithm.\n");
			outputStream.write("----------------------------------------------"+"\n\n");
		}
		while (bLoop) {
			sb.delete(0, sb.length());
			line = bReader.readLine();
			if (line == null) { // End of File
				break;
			} else {
				// Before start of each input matrix an order is specified
				int order = Integer.parseInt(line);
				MyAbsMatrixDeterminant absMatrix = null;
				switch (runType){
					case 'I':
						absMatrix = 
								new com.jhu.ds.lab3.MatrixOperations.Iterative.MyLinkedMatrix();
						break;
					default:
						absMatrix = 
								new com.jhu.ds.lab3.MatrixOperations.MyLinkedMatrix();
						break;
				}
				absMatrix.setOrder(order);
				absMatrix.buildEmptyMatrix();
				int i = 0;
				int j = 0;
				try {
					for (int k = 0; k < order; k++) {
						if ((line = bReader.readLine()) != null) {
							String str[] = line.split(" ");
							/* 
							* more or less elements in the row than expected.
							* It is expected to enter a 0 value for elements 
							* with initial value.
							*/ 							
							if (str.length != order) { 
								System.out.println("Encountered a non Square matrix");
								sb.append("Encountered a non Square matrix. Processing will stop\n");
								sb.append("Please enter only square matrices in the input file\n");
								outputStream.write(sb.toString());
								bLoop = !bLoop;
								break;
							}
							for (int l = 0; l < str.length; l++) {
								int num = Integer.parseInt(str[l]);
								if (j == order) {
									j = 0;
									i++;
								}
								if (i == order) {
									break;
								}
								try {
									absMatrix.fillValue(num, i, j);
								} catch (NullPointerException e) {
									// this condition should not happen as the
									// input data
									// is validated
									sb.append(e.getMessage());
								}

								j++;
							}

						}
					}
				} catch (NumberFormatException e) {
					System.out.println("Encountered a non Integer");
					sb.append("Encountered a non Integer. Processing will stop\n");
					sb.append("Please enter only integers in the input file\n");
					outputStream.write(sb.toString());
					bLoop = !bLoop;
					break;
				}
				if (!bLoop) {
					continue;
				}
				sb = absMatrix.printMatrix();
				outputStream.write("Order of Matrix: " + order + "\n");
				outputStream.write(sb.toString());
				try {
					outputStream.write("Determinant of the above Matrix is " + 
							absMatrix.calculateDeterminant(absMatrix) + "\n");
					outputStream.write("\n");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					outputStream.write(e.toString() + "\n");
				}

			}
		}// end while
		outputStream.close();
		bReader.close();

	}

}
