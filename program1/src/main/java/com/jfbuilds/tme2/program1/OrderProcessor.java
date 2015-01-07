/**
 * File Name: OrderProcessor.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Oct 15, 2014
 */
package com.jfbuilds.tme2.program1;

/**
 * OrderProcessor is used to process an Order of type GenericOrder or it's
 * subclasses
 * <p>
 * Contains fields for holding types GenericOrder of all elements, ComputerOrder
 * of ComputerPart objects, ComputerOrder of Peripheral objects, and
 * ComputerOrder of Service objects
 * <p>
 * Methods for accepting elements, processing current elements, and dispatching
 * the processed elements
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class OrderProcessor<T extends Product> {

	GenericOrder<T> elements;

	ComputerOrder<ComputerPart> compParts;

	ComputerOrder<Peripheral> peripherals;

	PartyTrayOrder<Cheese> cheeses;

	PartyTrayOrder<Fruit> fruits;

	ComputerOrder<Service> services;

	/**
	 * Default constructor which only initializes containing collection fields
	 */
	public OrderProcessor() {
		elements = new GenericOrder<>();
		compParts = new ComputerOrder<>();
		peripherals = new ComputerOrder<>();
		cheeses = new PartyTrayOrder<>();
		fruits = new PartyTrayOrder<>();
		services = new ComputerOrder<>();
	}

	/**
	 * Constructor which takes a GenericOrder of a specified type to be
	 * processed instead of current elements if defined
	 * 
	 * @param productCollection
	 *            to be used for processing
	 */
	public OrderProcessor(GenericOrder<T> productCollection) {
		elements = productCollection;
		compParts = new ComputerOrder<>();
		peripherals = new ComputerOrder<>();
		cheeses = new PartyTrayOrder<>();
		fruits = new PartyTrayOrder<>();
		services = new ComputerOrder<>();
	}

	/**
	 * accepts a GenericOrder of specified type to be added to an internal
	 * collection for future processing
	 * 
	 * @param order
	 *            to be added to OrderProcessor
	 */
	public void accept(GenericOrder<T> order) {
		System.out.println("accept a generic order");
		elements.addAll(order);
	}

	/**
	 * Process elements and sort them into corresponding containers of
	 * ComputerPart, Peripheral, or Service collections
	 */
	public void process() {
		for (Product p : elements) {
			if (ComputerPart.class.isAssignableFrom(p.getClass())) {
				compParts.add((ComputerPart) p);
			} else if (Peripheral.class.isAssignableFrom(p.getClass())) {
				peripherals.add((Peripheral) p);
			} else if (Cheese.class.isAssignableFrom(p.getClass())) {
				cheeses.add((Cheese) p);
			} else if (Fruit.class.isAssignableFrom(p.getClass())) {
				fruits.add((Fruit) p);
			} else if (Service.class.isAssignableFrom(p.getClass())) {
				services.add((Service) p);
			} else {
				System.out.println("There was an error processing elements, check OrderProcessor.process [" + p + "]");
			}
		}
	}

	/**
	 * Dispatch simulation of all objects of type ComputerPart
	 */
	public void dispatchComputerParts() {
		if (compParts.size() > 0) {
			System.out.print("COMPUTER PARTS: ");
			System.out.println(compParts);
		}
	}

	/**
	 * Dispatch simulation of all objects of type Peripherals
	 */
	public void dispatchPeripherals() {
		if (peripherals.size() > 0) {
			System.out.print("PERIPHERALS: ");
			System.out.println(peripherals);
		}
	}

	/**
	 * Dispatch simulation of all objects of type Cheese
	 */
	public void dispatchCheeses() {
		if (cheeses.size() > 0) {
			System.out.print("CHEESES: ");
			System.out.println(cheeses);
		}
	}

	/**
	 * Dispatch simulation of all objects of type Fruit
	 */
	public void dispatchFruits() {
		if (fruits.size() > 0) {
			System.out.print("FRUITS: ");
			System.out.println(fruits);
		}
	}

	/**
	 * Dispatch simulation of all objects of type Service
	 */
	public void dispatchServices() {
		if (services.size() > 0) {
			System.out.print("SERVICES: ");
			System.out.println(services);
		}
	}

	/**
	 * TODO TESTING CODE TO BE REMOVED
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// CREATE
		GenericOrder<Product> testCollection = new GenericOrder<>(ProductGenerator.createComputerPartyOrder(10));
		OrderProcessor<Product> processor = new OrderProcessor<>();
		// ACCEPT
		processor.accept(testCollection);
		// PROCESS
		processor.process();
		// DISPATCH
		System.out.println();
		processor.dispatchComputerParts();
		processor.dispatchPeripherals();
		processor.dispatchCheeses();
		processor.dispatchFruits();
		processor.dispatchServices();
	}
}
