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
 * ProductGenerator (description of class)
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
	 * 
	 */
	public static final List<Class<? extends Product>> allTypes = Collections.unmodifiableList(Arrays.asList(
			Product.class, ComputerPart.class, Motherboard.class, RAM.class, Drive.class, Peripheral.class,
			Printer.class, Monitor.class, Service.class, AssemblyService.class, DeliveryService.class, Cheese.class,
			Cheddar.class, Mozzarella.class, Fruit.class, Apple.class, Orange.class));

	/**
	 * @param type
	 * @return
	 */
	private static <T> List<Class<? extends Product>> getSubtypes(Class<T> classType) {
		System.out.println("Class type:" + classType);
		List<Class<? extends Product>> subTypes = new ArrayList<>();
		for (int i = 0; i < allTypes.size(); i++) {
			if (classType.isAssignableFrom(allTypes.get(i))) {
				// System.out.println("Instances: " + allTypes.get(i));
				subTypes.add(allTypes.get(i));
			}
		}
		return subTypes.subList(1, subTypes.size());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Computer Order = " + Arrays.deepToString(createComputerOrder(10).toArray()));
		System.out.println("Party Order = " + Arrays.deepToString(createPartyOrder(10).toArray()));
		System.out.println("Computer Party Order = " + Arrays.deepToString(createComputerPartyOrder(10).toArray()));
	}

	/**
	 * @param productType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T randomProduct(Class<T> productType) {
		// try {
		List<Class<? extends Product>> optionsArray;
		try {
			optionsArray = ProductGenerator.getSubtypes(productType.getClass());
			int randomItemNum = (int) Math.ceil(Math.random() * (optionsArray.size() - 1));
			T randomItem = (T) optionsArray.get(randomItemNum).newInstance();
			return randomItem;
		} catch (InstantiationException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * @param size
	 * @param baseClasses
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Product> createOrder(int size, Class... baseClasses) {
		int randomItemNum;
		ArrayList<Product> list = new ArrayList<>();
		try {
			List<Class<? extends Product>> baseClassOptions = new ArrayList<>();
			for (int i = 0; i < baseClasses.length; i++) {
				baseClassOptions.addAll(ProductGenerator.getSubtypes(baseClasses[i]));
			}
			for (int i = 0; i < size; i++) {
				randomItemNum = (int) Math.floor(Math.random() * (baseClassOptions.size()));
				list.add(baseClassOptions.get(randomItemNum).newInstance());
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @param size
	 * @return
	 */
	public static ArrayList<Product> createComputerOrder(int size) {
		return createOrder(size, ComputerPart.class, Peripheral.class, Service.class);
	}

	/**
	 * @param size
	 * @return
	 */
	public static ArrayList<Product> createPartyOrder(int size) {
		return createOrder(size, Fruit.class, Cheese.class, Service.class);
	}

	/**
	 * @param size
	 * @return
	 */
	public static ArrayList<Product> createComputerPartyOrder(int size) {
		return createOrder(size, ComputerPart.class, Peripheral.class, Service.class, Fruit.class, Cheese.class);
	}
}
