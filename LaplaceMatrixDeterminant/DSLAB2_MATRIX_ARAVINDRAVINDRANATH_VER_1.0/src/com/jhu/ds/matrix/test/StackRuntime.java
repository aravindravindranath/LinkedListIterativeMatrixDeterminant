package com.jhu.ds.matrix.test;

/**
 * @author Aravind Ravindranath
 * @version 1.0
 * 
 * This class contains attributes and methods to store
 * measurements done of a method run in class LangParser.
 * 
 */
public class StackRuntime {

	private long runtime;
	private long size;
	
	StackRuntime( long size, long runtime){
		this.size = size;
		this.runtime = runtime;
	}
	
	public long getRuntime(){
		return this.runtime;
	}
	
	public long getSize(){
		return this.size;
	}
	
}
