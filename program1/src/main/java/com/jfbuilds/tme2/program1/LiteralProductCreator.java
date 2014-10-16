package com.jfbuilds.tme2.program1;

import java.util.*;

public class LiteralProductCreator extends ProductCreator {

	@SuppressWarnings("unchecked")
	public static final List<Class<? extends Product>> allTypes = Collections.unmodifiableList(Arrays.asList(
		Product.class, 
			ComputerPart.class, 
				Motherboard.class, RAM.class, Drive.class, 
			Peripheral.class, 
				Printer.class, Monitor.class, 
			Service.class,
				AssemblyService.class, DeliveryService.class,
			Cheese.class,
				Cheddar.class, Mozzarella.class,
			Fruit.class,
				Apple.class, Orange.class
		));

	private static final List<Class<? extends Product>> types = allTypes.subList(allTypes.indexOf(Fruit.class), allTypes.size());

	public List<Class<? extends Product>> types() {
		return types;
	}

	public static void main(String[] args) {
		System.out.println(types);
	}
}