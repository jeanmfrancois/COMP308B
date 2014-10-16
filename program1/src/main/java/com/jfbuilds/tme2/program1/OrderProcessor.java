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
 * OrderProcessor (description of class)
 * <p>
 * (description of core fields)
 * <p>
 * (description of core methods)
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class OrderProcessor {

	GenericOrder<Product> elements;

	ComputerOrder<ComputerPart> compParts;

	ComputerOrder<Peripheral> peripherals;

	PartyTrayOrder<Cheese> cheeses;

	PartyTrayOrder<Fruit> fruits;

	ComputerOrder<Service> services;

	public OrderProcessor() {
		elements = new GenericOrder<>();
		compParts = new ComputerOrder<>();
		peripherals = new ComputerOrder<>();
		cheeses = new PartyTrayOrder<>();
		fruits = new PartyTrayOrder<>();
		services = new ComputerOrder<>();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// CREATE
		OrderProcessor processor = new OrderProcessor();
		ComputerOrder<Product> comp = new ComputerOrder<>();
		comp.add(new Monitor());
		comp.add(new Peripheral());
		comp.add(new DeliveryService());
		comp.add(new RAM());
		comp.add(new Drive());
		PartyTrayOrder<Fruit> fruits = new PartyTrayOrder<>();
		fruits.add(new Apple());
		fruits.add(new Orange());
		ComputerOrder<Peripheral> peripherals = new ComputerOrder<>();
		peripherals.add(new Printer());
		peripherals.add(new Monitor());
		// ACCEPT
		processor.accept(comp);
		processor.accept(fruits);
		processor.accept(peripherals);
		System.out.println(processor.elements);
		// PROCESS
		processor.process();
		// DISPATCH
		System.out.print("COMP: ");
		processor.dispatchComputerParts();
		System.out.print("PERI: ");
		processor.dispatchPeripherals();
		System.out.print("CHEE: ");
		processor.dispatchCheeses();
		System.out.print("FRUI: ");
		processor.dispatchFruits();
		System.out.print("SERV: ");
		processor.dispatchServices();
	}

	/**
	 * @param order
	 */
	public <T extends Product> void accept(GenericOrder<T> order) {
		System.out.println("accept a generic order of type " + order.type);
		elements.addAll(order);
	}

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
				System.out.println("There was an error processing elements, check OrderProcessor.process");
			}
		}
	}

	/**
	 * 
	 */
	public void dispatchComputerParts() {
		System.out.println(compParts);
	}

	/**
	 * 
	 */
	public void dispatchPeripherals() {
		System.out.println(peripherals);
	}

	/**
	 * 
	 */
	public void dispatchCheeses() {
		System.out.println(cheeses);
	}

	/**
	 * 
	 */
	public void dispatchFruits() {
		System.out.println(fruits);
	}

	/**
	 * 
	 */
	public void dispatchServices() {
		System.out.println(services);
	}
}
