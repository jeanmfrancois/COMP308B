/**
 * File Name: ComputerPartsOrder.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Oct 15, 2014
 */
package com.jfbuilds.tme2.program2;

import java.util.ArrayList;

/**
 * ComputerPartsOrder (description of class)
 * <p>
 * (description of core fields)
 * <p>
 * (description of core methods)
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @param <T>
 * @since 1.0
 */
public class ComputerPartyOrder<T extends Product> extends GenericOrder<T> {

	ArrayList<ComputerPart> computerParts;

	ArrayList<Peripheral> peripherals;

	ArrayList<Cheese> cheeses;

	ArrayList<Fruit> fruits;

	ArrayList<Service> services;

	/**
	 * 
	 */
	public ComputerPartyOrder() {
		elements = new ArrayList<>();
		computerParts = new ArrayList<>();
		peripherals = new ArrayList<>();
		cheeses = new ArrayList<>();
		fruits = new ArrayList<>();
		services = new ArrayList<>();
		id = "CP-Ord" + count;
	}

	@Override
	public boolean add(T e) {
		if (ComputerPart.class.isAssignableFrom(e.getClass())) {
			// System.out.println("Adding comp part: " + e.getClass());
			elements.add(e);
			computerParts.add((ComputerPart) e);
		} else if (Peripheral.class.isAssignableFrom(e.getClass())) {
			// System.out.println("Adding peripheral: " + e.getClass());
			elements.add(e);
			peripherals.add((Peripheral) e);
		} else if (Cheese.class.isAssignableFrom(e.getClass())) {
			// System.out.println("Adding cheese: " + e.getClass());
			elements.add(e);
			cheeses.add((Cheese) e);
		} else if (Fruit.class.isAssignableFrom(e.getClass())) {
			// System.out.println("Adding fruit: " + e.getClass());
			elements.add(e);
			fruits.add((Fruit) e);
		} else if (Service.class.isAssignableFrom(e.getClass())) {
			// System.out.println("Adding service: " + e.getClass());
			elements.add(e);
			services.add((Service) e);
		} else {
			System.out.println("Item was not added due to not meeting container requirements");
		}
		return true;
	}

	public static void main(String[] args) {
		ComputerPartyOrder<Product> compParts = new ComputerPartyOrder<>();
		compParts.add(new Motherboard());
		compParts.add(new Printer());
		compParts.add(new AssemblyService());
		compParts.add(new RAM());
		compParts.add(new Monitor());
		compParts.add(new DeliveryService());
		compParts.add(new Drive());
		compParts.add(new Apple());
		compParts.add(new Peripheral());
		System.out.println(compParts + "\n\n");
		System.out.println(compParts.computerParts + "\n");
		System.out.println(compParts.peripherals + "\n");
		System.out.println(compParts.services + "\n");
	}
}
