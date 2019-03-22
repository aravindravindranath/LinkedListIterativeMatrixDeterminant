package com.jhu.ds.matrix.test;

import com.jhu.ds.lab3.MatrixOperations.Iterative.*;

/**
 * @author Aravind Ravindranath
 * @version 1.0
 * 
 * This class contains methods which run determination calculation method with
 * square matrices of different sizes and then take runtime measurements in 
 * nanoseconds.
 * 
 */

public class MeasureDeterminantCalcGaussianRun {

	private StackRuntime[] runtime = new StackRuntime[100];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MeasureDeterminantCalcGaussianRun mDCRun = new MeasureDeterminantCalcGaussianRun();
		mDCRun.measure_method_run(0, 2);
		mDCRun.measure_method_run(1, 4);
		mDCRun.measure_method_run(2, 6);
		mDCRun.measure_method_run(3, 8);
		mDCRun.measure_method_run(4, 10);
		mDCRun.measure_method_run(5, 16);
		mDCRun.measure_method_run(6, 20);
		mDCRun.measure_method_run(7, 30);
		mDCRun.measure_method_run(8, 40);
		mDCRun.measure_method_run(9, 100);
		mDCRun.display_metrics();
	}

	
	private void measure_method_run(int i, int order){
		long start;
		long end;
		long totalTime;
		MyLinkedMatrix matrix = build_matrix(order);
		start = System.nanoTime();
		try {
			matrix.calcDeterminantGaussian(matrix);
		} catch (NullPointerException | NotRowReducedForm e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		end = System.nanoTime();
		totalTime = end - start;
		runtime[i] = new StackRuntime(order, totalTime);
        
	}

	
	private MyLinkedMatrix build_matrix(int order){
		MyLinkedMatrix matrix = new MyLinkedMatrix();
		matrix.setOrder(order);
		matrix.buildEmptyMatrix();
		for(int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++) {
				matrix.fillValue((int) ( Math.random() * 100 ), i, j);
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
