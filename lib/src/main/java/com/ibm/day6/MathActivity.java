package com.ibm.day6;
public class MathActivity {


	//Add two numbers
	public static float add (float a, float b) { 
		return a + b;
	}
	
	// Subtract two numbers
	public static float subtract(float a, float b) 
	{ return a - b;	}
	
	// Multiply two numbers
	public static float multiply(float a, float b)
	{ return a * b; }
	
	// Divide two numbers
	public static float divide(float a, float b)
	// prevent division by zero
	{ if ( b == 0) { throw new ArithmeticException("Cannot divide by zero");}
	return a / b;
	}
}
	
	