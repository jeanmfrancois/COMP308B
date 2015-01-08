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
 * TestOrderProcessor is used to test the creation of empty GenericOrders, add
 * 50 elements of type ComputerOrder and PartyTrayOrder, which are generated
 * from a generator ProductGenerator, process the orders with OrderProcessor to
 * be accepted, processed and dispatched within a sorted manner for both.
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class TestOrderProcessor {

	public static void main(String[] args) {
		// Test for ComputerPartOrder collection processing
		System.out.println("*************** ComputerPartOrder Collection Processing *********************/\n");
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
		// Test for ComputerPartOrder collection processing
		System.out.println("\n\n/*************** PartyTrayOrder Collection Processing *********************/\n");
		// Create a GenericOrder for PartyOrder objects and initialize
		GenericOrder<Product> genOrd2 = new GenericOrder<>();
		// Add a PartyOrder with 50 random items
		genOrd2.addAll(ProductGenerator.createPartyOrder(50));
		// Create another OrderProcessor and initialize it
		OrderProcessor<Product> ordPro2 = new OrderProcessor<>();
		// Pass the GenericOrder subclass type ComputerOrder to the ordPro2
		ordPro2.accept(genOrd2);
		// Process the elements contained inside the OrderProcessor
		ordPro2.process();
		// Dispatch sorted elements to console
		ordPro2.dispatchComputerParts();
		ordPro2.dispatchPeripherals();
		ordPro2.dispatchFruits();
		ordPro2.dispatchCheeses();
		ordPro2.dispatchServices();
	}
}
