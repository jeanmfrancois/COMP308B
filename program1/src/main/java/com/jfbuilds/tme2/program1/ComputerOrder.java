/**
 * File Name: ComputerPartsOrder.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Oct 15, 2014
 */
package com.jfbuilds.tme2.program1;

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
public class ComputerOrder<T extends Product> extends GenericOrder<T> {

	ArrayList<ComputerPart> computerParts;

	ArrayList<Peripheral> peripherals;

	ArrayList<Service> services;

	/**
	 * 
	 */
	public ComputerOrder() {
		elements = new ArrayList<>();
		computerParts = new ArrayList<>();
		peripherals = new ArrayList<>();
		services = new ArrayList<>();
		id = "Com-Ord" + count;
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
		} else if (Service.class.isAssignableFrom(e.getClass())) {
			// System.out.println("Adding service: " + e.getClass());
			elements.add(e);
			services.add((Service) e);
		} else {
			System.out.println("Item " + e + ", of type " + e.getClass().getSimpleName()
					+ ", was not added due to not meeting container requirements of ComputerPartsOrder");
		}
		return true;
	}

	public static void main(String[] args) {
		ComputerOrder<Product> compParts = new ComputerOrder<>();
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
