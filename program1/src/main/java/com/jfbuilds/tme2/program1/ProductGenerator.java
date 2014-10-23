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
public class ProductGenerator<T extends Product> implements Generator<T> {

	@SuppressWarnings("unchecked")
	public static final List<Class<? extends Product>> allTypes = Collections.unmodifiableList(Arrays.asList(
			Product.class, ComputerPart.class, Motherboard.class, RAM.class, Drive.class, Peripheral.class,
			Printer.class, Monitor.class, Service.class, AssemblyService.class, DeliveryService.class, Cheese.class,
			Cheddar.class, Mozzarella.class, Fruit.class, Apple.class, Orange.class));

	private final List<Class<? extends Product>> types;

	public Class type;

	/**
	 * @param type
	 */
	public ProductGenerator(T type) {
		this.type = type.getClass();
		types = getSubtypes(type.getClass());
	}

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
		ProductGenerator gen = new ProductGenerator(new ComputerPart());
		ArrayList<Product> cp = createComputerOrderArray(40);
		System.out.println("Created Array = " + Arrays.deepToString(cp.toArray()));
	}

	/**
	 * @see com.jfbuilds.tme2.program1.Generator#next()
	 */
	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param productType
	 * @return
	 */
	public static <T> T randomProduct(Class<T> productType) {
		// try {
		List<Class<? extends Product>> optionsArray;
		try {
			optionsArray = ProductGenerator.getSubtypes(productType.getClass());
			int randomItemNum = (int) Math.ceil(Math.random() * (optionsArray.size() - 1));
			// for (int i = 0; i < 20; i++) {
			// System.out.println("RN:" + (int) Math.ceil(Math.random() *
			// (optionsArray.size() - 1)));
			// }
			T randomItem = (T) optionsArray.get(randomItemNum).newInstance();
			return randomItem;
		} catch (InstantiationException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Product> createComputerOrderArray(int size) {
		ArrayList<Product> list = new ArrayList<>();
		try {
			List<Class<? extends Product>> baseClassOptions = new ArrayList<>();
			// System.out.println(Arrays.deepToString(ProductGenerator.getSubtypes(ComputerPart.class).toArray()));
			baseClassOptions.addAll(ProductGenerator.getSubtypes(ComputerPart.class));
			baseClassOptions.addAll(ProductGenerator.getSubtypes(Peripheral.class));
			baseClassOptions.addAll(ProductGenerator.getSubtypes(Service.class));
			// System.out.println(Arrays.deepToString(baseClassOptions.toArray()));
			int randomItemNum;
			for (int i = 0; i < size; i++) {
				randomItemNum = (int) Math.floor(Math.random() * (baseClassOptions.size()));
				list.add(baseClassOptions.get(randomItemNum).newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ComputerPart> ComputerParts(int size) {
		ArrayList<ComputerPart> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(randomProduct(ComputerPart.class));
		}
		return null;
	}

	public static ArrayList<Fruit> Fruits(int size) {
		ArrayList<Fruit> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(randomProduct(Fruit.class));
		}
		return null;
	}

	public static ArrayList<Cheese> Cheeses(int size) {
		ArrayList<Cheese> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(randomProduct(Cheese.class));
		}
		return null;
	}

	public static ArrayList<Peripheral> Peripherals(int size) {
		ArrayList<Peripheral> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(randomProduct(Peripheral.class));
		}
		return null;
	}

	public static ArrayList<Service> Services(int size) {
		ArrayList<Service> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(randomProduct(Service.class));
		}
		return null;
	}
}
