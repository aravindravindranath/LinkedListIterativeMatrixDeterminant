package com.jhu.ds.matrix.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jhu.ds.lab2.MatrixOperations.*;


public class TestMyMatrix {
	SquareMatrix cut = null;

	@Before
	public void setUp() throws Exception {
		int[][] test = { { 1, 2, 3, 4 }, 
						 { 5, 6, 7, 8 }, 
						 { 9, 10, 11, 12 }, 
						 { 13, 14, 15, 16 } };
		cut = new SquareMatrix(test);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testBuildMinorMatrix() {
		int[][] act = cut.getSubMatrix(1, 2).buildMinorMatrix();
		int[][] exp = { {1, 2,  4},
		                {9, 10, 12},
		                {13, 14, 16} };
		assertArrayEquals(exp, act);
		int[][] test = { { 1, 2, 3, 4 }, 
				 { 5, 6, 7, 8, 9 }, 
				 { 9, 10, 11, 12 }, 
				 { 13, 14, 15, 16 } };
		
		try {
			cut.buildMinorMatrix(test, 0, 0);
			assertTrue(false);
		} catch (NotASquareMatrix e) {
			assertTrue(true);
		}
		test = null;

		
		try {
			int[][] test1 = { { 1,  2,  3,  4 }, 
			 		          { 5,  6,  7,  8 }, 
			 		          { 9,  10, 11, 12 }, 
			 		          { 13, 14, 15, 16 } };
			int[][] exp1 = {{6, 7, 8},
						    {10, 11,12},
						    {14, 15, 16}};
			assertArrayEquals(exp1, cut.buildMinorMatrix(test1, 0, 0));
		} catch (NotASquareMatrix e) {
			e.printStackTrace();
		}

		try {
			int[][] test1 = { { 1,  2,  3,  4 }, 
			 		  		  { 5,  6,  7,  8 }, 
			 		          { 9,  10, 11, 12 }, 
			 		          { 13, 14, 15, 16 } };			
			int[][] exp1 = {{1, 2, 4},
						    {5, 6, 8},
						    {13, 14, 16}};
			assertArrayEquals(exp1, cut.buildMinorMatrix(test1, 2, 2));
		} catch (NotASquareMatrix e) {
			e.printStackTrace();
		}		
		
		try {
			int[][] test1 = { { 1,  2,  3,  4 }, 
			 		  		  { 5,  6,  7,  8 }, 
			 		          { 9,  10, 11, 12 }, 
			 		          { 13, 14, 15, 16 } };			
			int[][] exp1 = {{6, 8},
						    {14, 16}};
			assertArrayEquals(exp1, cut.buildMinorMatrix(
					cut.buildMinorMatrix(test1, 2, 2), 0, 0));
		} catch (NotASquareMatrix e) {
			e.printStackTrace();
		}	
		
		
	}

	
	@Test
	public final void testCalc_Determinant() {

		try {
			int[][] test = {{10}}; 
			assertEquals(10, cut.calcDeterminant(test) );
		} catch (NotASquareMatrix e) {
			e.printStackTrace();
		}
		
		
		try {
			int[][] test = {{6, 8},
				            {14, 16}}; 
			assertEquals(-16, cut.calcDeterminant(test) );
		} catch (NotASquareMatrix e) {
			e.printStackTrace();
		}
		
		try {
			int[][] test = {{10, 12, 9},
							{3,  6,  8 },
				            {2,  5,  -7}}; 
			int act = cut.calcDeterminant(test);
			assertEquals(-349, act);
		} catch (NotASquareMatrix e) {
			e.printStackTrace();
		}
		
		try {
			int[][] test = {
					{ 4,	9,	0,	1 },
					{ 11,	1,	-6,	0 },
					{ 5,	23,	0,	-4 },
					{ 11,	0,	10,	0}
					
			};
			int act = cut.calcDeterminant(test);
			assertEquals( -10174, act);
		} catch (NotASquareMatrix e) {
			e.printStackTrace();
		}
		
		try {
			int[][] test = {
					{ 11, -3,	8,	7,	-2},
					{ 2, 	4,	7,	9,	0},
					{ -17,	6,	3,	15,	21},
					{ 3,	0,	7,	12,	-5},
					{3,	4,	5,	14,	0}
			
			};
			int act = cut.calcDeterminant(test);
			assertEquals( 65515, act);
		} catch (NotASquareMatrix e) {
			e.printStackTrace();
		}

		try {
			int[][] test = {
					{1,	2,	3,	4,	5},
					{6,	7,	8,	9,	10},
					{10, 9,	8,	7,	6},
					{5,	4,	3,	2,	1},
					{-1, -2,	-3,	-4,	-5}
			
			};
			int act = cut.calcDeterminant(test);
			assertEquals( 0, act);
		} catch (NotASquareMatrix e) {
			e.printStackTrace();
		}
		try {
			int[][] test = {
					{10, 5,	4,	11,	21, 7},
					{9,	 6,	8,	12,	8, 2},
					{13, 7,	19,	3,	-7, -3},
					{21, 17, 6,	8,	-4, 15},
					{6,	-9,	19,	0,	6, 8},
					{4,	15,	7,	9,	-12, 7}

			
			};
			int act = cut.calcDeterminant(test);
			assertEquals( 9846755, act);
		} catch (NotASquareMatrix e) {
			e.printStackTrace();
		}
		
	}
}
