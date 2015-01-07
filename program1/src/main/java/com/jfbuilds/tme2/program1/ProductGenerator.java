/**
 * File Name: ProductGenerator.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Oct 22, 2014
 */
package com.jfbuilds.tme2.program1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ProductGenerator is used to faciliate the generation of random objects and
 * their subclasses into collections for testing purposes.
 * <p>
 * (description of core fields)
 * <p>
 * (description of core methods)
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class ProductGenerator {

	/**
	 * A list collection containing all relevant product types for generation
	 */
	public static final List<Class<? extends Product>> allTypes = Collections.unmodifiableList(Arrays.asList(
			Product.class, ComputerPart.class, Motherboard.class, RAM.class, Drive.class, Peripheral.class,
			Printer.class, Monitor.class, Service.class, AssemblyService.class, DeliveryService.class, Cheese.class,
			Cheddar.class, Mozzarella.class, Fruit.class, Apple.class, Orange.class));

	/**
	 * Method to return subtypes of base class
	 * 
	 * @param type
	 *            of objects to be return for class type
	 * @return a List containing the random objects type options
	 */
	private static <T> List<Class<? extends Product>> getSubtypes(Class<T> classType) {
		System.out.println("Class type:" + classType);
		List<Class<? extends Product>> subTypes = new ArrayList<>();
		for (int i = 0; i < allTypes.size(); i++) {
			if (classType.isAssignableFrom(allTypes.get(i))) {
				subTypes.add(allTypes.get(i));
			}
		}
		return subTypes.subList(1, subTypes.size());
	}

	/**
	 * Creates an ArrayList of type Product for given size and containing
	 * selected base classes
	 * 
	 * @param size
	 *            the size of the collection to be returned
	 * @param baseClasses
	 *            the base class for random generation of subclasses
	 * @return an ArrayList of base class subclasses objects
	 */
	@SuppressWarnings({ "unchecked" })
	public static ArrayList<Product> createOrder(int size, Class... baseClasses) {
		int randomItemNum;
		ArrayList<Product> list = new ArrayList<>();
		List<Class<? extends Product>> baseClassOptions = new ArrayList<>();
		for (int i = 0; i < baseClasses.length; i++) {
			baseClassOptions.addAll(ProductGenerator.getSubtypes(baseClasses[i]));
		}
		for (int i = 0; i < size; i++) {
			randomItemNum = (int) Math.floor(Math.random() * (baseClassOptions.size()));
			addClassType(baseClassOptions.get(randomItemNum), list);
		}
		return list;
	}

	/**
	 * Based on given class type, method to add item to list
	 * 
	 * @param productClass
	 *            Class of given type
	 * @param list
	 *            containing possible Product types
	 */
	private static void addClassType(Class productClass, ArrayList<Product> list) {
		if (productClass == Motherboard.class) {
			list.add(Motherboard.generate());
		} else if (productClass == RAM.class) {
			list.add(RAM.generate());
		} else if (productClass == Drive.class) {
			list.add(Drive.generate());
		} else if (productClass == Printer.class) {
			list.add(Printer.generate());
		} else if (productClass == Monitor.class) {
			list.add(Monitor.generate());
		} else if (productClass == AssemblyService.class) {
			list.add(AssemblyService.generate());
		} else if (productClass == DeliveryService.class) {
			list.add(DeliveryService.generate());
		} else if (productClass == Cheddar.class) {
			list.add(Cheddar.generate());
		} else if (productClass == Mozzarella.class) {
			list.add(Mozzarella.generate());
		} else if (productClass == Apple.class) {
			list.add(Apple.generate());
		} else if (productClass == Orange.class) {
			list.add(Orange.generate());
		}
	}

	/**
	 * Method to create a random ComputerOrder
	 * 
	 * @param size
	 *            of the ComputerOrder
	 * @return the generated random collection of given size
	 */
	public static ArrayList<Product> createComputerOrder(int size) {
		return createOrder(size, ComputerPart.class, Peripheral.class, Service.class);
	}

	/**
	 * Method to create a random PartyOrder
	 * 
	 * @param size
	 *            of the PartyOrder
	 * @return the generated random collection of given size
	 */
	public static ArrayList<Product> createPartyOrder(int size) {
		return createOrder(size, Fruit.class, Cheese.class, Service.class);
	}

	/**
	 * Method to create a random ComputerPartyOrder
	 * 
	 * @param size
	 *            of the ComputerPartyOrder
	 * @return the generated random collection of given size
	 */
	public static ArrayList<Product> createComputerPartyOrder(int size) {
		return createOrder(size, ComputerPart.class, Peripheral.class, Service.class, Fruit.class, Cheese.class);
	}

	/**
	 * TODO TESTING TO BE REMOVED
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Computer Order = " + Arrays.deepToString(createComputerOrder(10).toArray()));
		System.out.println("Party Order = " + Arrays.deepToString(createPartyOrder(10).toArray()));
		System.out.println("Computer Party Order = " + Arrays.deepToString(createComputerPartyOrder(10).toArray()));
	}
}
