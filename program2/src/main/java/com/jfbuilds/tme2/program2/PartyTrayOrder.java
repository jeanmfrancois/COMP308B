/**
 * File Name: PartyTrayOrder.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Created: Oct 15, 2014
 */
package com.jfbuilds.tme2.program2;

import java.util.ArrayList;

/**
 * A container that acts as a collection of an arbitrary number of objects of
 * type Cheese, Fruit, and Service or their subclasses.
 * <p>
 * Field id gives each item in the collection a unique number.
 * <p>
 * Methods exist for common actions for a collection as well as an override of
 * the toString method to offer a readable representation of the item in the
 * collection.
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class PartyTrayOrder<T extends Product> extends GenericOrder<T> {

	ArrayList<Fruit> fruits;

	ArrayList<Cheese> cheeses;

	ArrayList<Service> services;

	public PartyTrayOrder() {
		elements = new ArrayList<>();
		fruits = new ArrayList<>();
		cheeses = new ArrayList<>();
		services = new ArrayList<>();
		id = "Par-Ord-" + idNum;
	}

	/**
	 * @see com.jfbuilds.tme2.program1.GenericOrder#add(com.jfbuilds.tme2.program1.Product)
	 */
	@Override
	public boolean add(T e) {
		if (Fruit.class.isAssignableFrom(e.getClass())) {
			elements.add(e);
			fruits.add((Fruit) e);
		} else if (Cheese.class.isAssignableFrom(e.getClass())) {
			elements.add(e);
			cheeses.add((Cheese) e);
		} else if (Service.class.isAssignableFrom(e.getClass())) {
			elements.add(e);
			services.add((Service) e);
		} else {
			System.out.println("Item " + e + ", of type " + e.getClass().getSimpleName()
					+ ", was not added due to not meeting container requirements of PartyTrayOrder");
		}
		return true;
	}

	/**
	 * TODO REMOVE TESTING
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PartyTrayOrder<Product> partyTray = new PartyTrayOrder<>();
		partyTray.add(new Mozzarella());
		partyTray.add(new Orange());
		partyTray.add(new Apple());
		partyTray.add(new AssemblyService());
		partyTray.add(new Cheddar());
		partyTray.add(new DeliveryService());
		partyTray.add(new Drive());
		partyTray.add(new Apple());
		partyTray.add(new ComputerPart());
		System.out.println(partyTray + "\n\n");
		System.out.println(partyTray.fruits + "\n");
		System.out.println(partyTray.cheeses + "\n");
		System.out.println(partyTray.services + "\n");
	}
}
