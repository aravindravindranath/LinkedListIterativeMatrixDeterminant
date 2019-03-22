package com.jhu.ds.matrix.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jhu.ds.lab3.MatrixOperations.Iterative.MyLinkedMatrix;
import com.jhu.ds.lab3.MatrixOperations.Iterative.NotRowReducedForm;

public class TestMyLinkedMatrixIterative {

	MyLinkedMatrix matrix = new MyLinkedMatrix();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// @Test
	public final void testBuildMinorMatrix() {
		// fail("Not yet implemented"); // TODO
		matrix.displayMatrix();
		MyLinkedMatrix minorMatrix = matrix.buildMinorMatrix(matrix, 0, 0);
		minorMatrix.displayMatrix();
		minorMatrix = matrix.buildMinorMatrix(matrix, 0, 1);
		minorMatrix.displayMatrix();
		minorMatrix = matrix.buildMinorMatrix(matrix, 0, 3);
		minorMatrix.displayMatrix();
	}

	@Test
	public final void testCalcDeterminant() {

		try {
			MyLinkedMatrix matrix1 = new MyLinkedMatrix();
			matrix1.setOrder(1);
			int[][] test = { { 10 } };
			matrix1.buildLinkedListMatrixfromArray(test);
			assertEquals(10, matrix1.calcDeterminantGaussian(matrix1));
		} catch (NullPointerException | NotRowReducedForm e) {
			System.out.println(e.toString());
		}

		try {
			MyLinkedMatrix matrix1 = new MyLinkedMatrix();
			matrix1.setOrder(2);
			int[][] test = { { 6, 8 }, 
							 { 14, 16 } };
			matrix1.buildLinkedListMatrixfromArray(test);
			assertEquals(-16, matrix1.calcDeterminantGaussian(matrix1));
		} catch (NullPointerException | NotRowReducedForm e) {
			System.out.println(e.toString());
		}

		try {
			MyLinkedMatrix matrix1 = new MyLinkedMatrix();
			matrix1.setOrder(3);
			int[][] test = { { 10, 12, 9 }, 
							 { 3, 6, 8 }, 
							 { 2, 5, -7 } };
			matrix1.buildLinkedListMatrixfromArray(test);
			assertEquals(-349, matrix1.calcDeterminantGaussian(matrix1));
		} catch (NullPointerException | NotRowReducedForm e) {
			System.out.println(e.toString());
		}

		
		try {
			MyLinkedMatrix matrix1 = new MyLinkedMatrix();
			matrix1.setOrder(4);
			int[][] test = {
					{ 4,	9,	0,	1 },
					{ 11,	1,	-6,	0 },
					{ 5,	23,	0,	-4 },
					{ 11,	0,	10,	0}
					
			};
			matrix1.buildLinkedListMatrixfromArray(test);
			assertEquals(-10174, matrix1.calcDeterminantGaussian(matrix1));
		} catch (NullPointerException | NotRowReducedForm e) {
			System.out.println(e.toString());
		}

		try {
			MyLinkedMatrix matrix1 = new MyLinkedMatrix();
			matrix1.setOrder(5);
			int[][] test = {
					{ 11, -3,	8,	7,	-2},
					{ 2, 	4,	7,	9,	0},
					{ -17,	6,	3,	15,	21},
					{ 3,	0,	7,	12,	-5},
					{3,	4,	5,	14,	0}
			
			};
			matrix1.buildLinkedListMatrixfromArray(test);
			assertEquals(65515, matrix1.calcDeterminantGaussian(matrix1));
		} catch (NullPointerException | NotRowReducedForm e) {
			System.out.println(e.toString());
		}
		
		try {
			MyLinkedMatrix matrix1 = new MyLinkedMatrix();
			matrix1.setOrder(5);
			int[][] test = {
					{1,	2,	3,	4,	5},
					{6,	7,	8,	9,	10},
					{10, 9,	8,	7,	6},
					{5,	4,	3,	2,	1},
					{-1, -2, -3, -4, -5}
			
			};
			matrix1.buildLinkedListMatrixfromArray(test);
			assertEquals(0, matrix1.calcDeterminantGaussian(matrix1));
		} catch (NullPointerException | NotRowReducedForm e) {
			System.out.println(e.toString());
		}
		
		try {
			MyLinkedMatrix matrix1 = new MyLinkedMatrix();
			matrix1.setOrder(6);
			int[][] test = {
					{10, 5,	4,	11,	21, 7},
					{9,	 6,	8,	12,	8, 2},
					{13, 7,	19,	3,	-7, -3},
					{21, 17, 6,	8,	-4, 15},
					{6,	-9,	19,	0,	6, 8},
					{4,	15,	7,	9,	-12, 7}

			
			};
			matrix1.buildLinkedListMatrixfromArray(test);
			assertEquals(9846755, matrix1.calcDeterminantGaussian(matrix1));
		} catch (NullPointerException | NotRowReducedForm e) {
			System.out.println(e.toString());
		}
		
		try {
			MyLinkedMatrix matrix1 = new MyLinkedMatrix();
			matrix1.setOrder(6);
			int[][] test = {
					{10, 5,	4,	11,	21, 7},
					{9,	 6,	8,	12,	8, 2},
					{13, 7,	19,	3,	-7, -3},
					{21, 17, 6,	8,	-4, 15},
					{6,	-9,	19,	0,	6, 8},
					{4,	15,	7,	9,	-12, 7}

			
			};
			matrix1.buildLinkedListMatrixfromArray(test);
			assertEquals(9846755, matrix1.calculateDeterminant(matrix1) );
		} catch (NullPointerException | NotRowReducedForm e) {
			System.out.println(e.toString());
		}
		
		
	}

}
