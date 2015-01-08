/**
 * File Name: TestOrderProcessor.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Oct 26, 2014
 */
package com.jfbuilds.tme2.program2;

/**
 * TestOrderProcessor is used to test the creation of an empty GenericOrder, add
 * 50 elements of type ComputerPartyOrder, which are generated from a generator
 * ProductGenerator, process the orders with OrderProcessor to be accepted,
 * processed and dispatched within a sorted manner.
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class TestOrderProcessor {

	public static void main(String[] args) {
		// Test for ComputerPartyOrder collection processing
		System.out.println("*************** ComputerPartyOrder Collection Processing *********************/\n");
		// Create a GenericOrder for ComputerPartsOrder objects and initialize
		GenericOrder<Product> genOrd = new GenericOrder<>();
		// Add a ComputerOrder with 50 random items
		genOrd.addAll(ProductGenerator.createComputerOrder(50));
		// Create an OrderProcessor and initialize it
		OrderProcessor<Product> ordPro = new OrderProcessor<>();
		// Pass the GenericOrder subclass type ComputerOrder to ordPro
		ordPro.accept(genOrd);
		// Process the elements contained inside the OrderProcessor ordPro
		ordPro.process();
		// Dispatch sorted elements to console
		ordPro.dispatchComputerParts();
		ordPro.dispatchPeripherals();
		ordPro.dispatchFruits();
		ordPro.dispatchCheeses();
		ordPro.dispatchServices();
	}
}
