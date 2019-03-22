package com.jhu.ds.matrix.test;

import com.jhu.ds.lab2.MatrixOperations.*;

/**
 * @author Aravind Ravindranath
 * @version 1.0
 * 
 * This class contains methods which run determination calculation method with
 * square matrices of different sizes and then take runtime measurements in 
 * nanoseconds.
 * 
 */

public class MeasureDeterminantCalcRun {

	private StackRuntime[] runtime = new StackRuntime[100];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MeasureDeterminantCalcRun mDCRun = new MeasureDeterminantCalcRun();
		mDCRun.measure_method_run(0, 2);
		mDCRun.measure_method_run(1, 3);
		mDCRun.measure_method_run(2, 6);
		mDCRun.measure_method_run(3, 8);
		mDCRun.measure_method_run(4, 10);
		mDCRun.measure_method_run(5, 11);
		mDCRun.measure_method_run(6, 12);
		mDCRun.display_metrics();
	}

	
	private void measure_method_run(int i, int order){
		long start;
		long end;
		long totalTime;
		int[][] array = build_array(order);
		SquareMatrix matrix = new SquareMatrix(array);
		start = System.nanoTime();
		try {
			matrix.calcDeterminant(array);
		} catch (NotASquareMatrix e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		end = System.nanoTime();
		totalTime = end - start;
		runtime[i] = new StackRuntime(order, totalTime);
        
/*        for (int i = 0; i < 16; i++) {
        	measureRuntime(runtime, cut, , build_string((int) Math.pow(2, i+1)));
		}
		display_metrics(ltype, runtime, 16);
		clear_metrics(runtime);*/
	}

	
	private int[][] build_array(int order){
		int[][] matrix = new int[order][order];
		for(int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++) {
				matrix[i][j] = (int) ( Math.random() * 100 );
			}
		}
		return matrix;
	}
	
	private void display_metrics(){
		for (int i = 0; i < runtime.length; i++) {
			if (runtime[i] == null ){
				break;
			}
			System.out.println("Size: " + runtime[i].getSize() + 
						" Runtime: " + runtime[i].getRuntime()); ;
			
		}
		System.out.println("\n");
	}
	
	private void clear_metrics(){
		for (int i = 0; i < runtime.length; i++) {
			runtime[i] = null;
		}
	}
	
}
