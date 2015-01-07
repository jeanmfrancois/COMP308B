/**
 * File Name: TestOrderProcessor.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Oct 26, 2014
 */
package com.jfbuilds.tme2.program1;

/**
 * TestOrderProcessor is used to test the creation of an empty GenericOrder, add
 * 50 elements of type ComputerOrder, which are generated from a generator
 * ProductGenerator, process the order with OrderProcessor to be accepted,
 * processed and dispatched within a sorted manner.
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class TestOrderProcessor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericOrder<Product> genOrd = new GenericOrder<>();
		System.out.println(genOrd);
		genOrd.addAll(ProductGenerator.createComputerOrder(50));
		OrderProcessor<Product> ordPro = new OrderProcessor<>();
		System.out.println(ordPro);
		ordPro.accept(genOrd);
		System.out.println(genOrd);
		System.out.println(ordPro);
		ordPro.process();
		ordPro.dispatchComputerParts();
		ordPro.dispatchPeripherals();
		// //ordPro.dispatchFruits();
		// //ordPro.dispatchCheeses();
		ordPro.dispatchServices();
	}
}
