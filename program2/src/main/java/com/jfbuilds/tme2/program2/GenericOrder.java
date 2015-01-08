/**
 * File Name: GenericOrder.java<br>
 * Jean-francois Nepton<br>
 * COMP 308 Java for Programmers<br>
 * Cordinator: Dr. Xiaokun Zhang<br>
 * Student ID# 2358976<br>
 * Assignment: TME 2<br>
 * Created: Sep 30, 2014
 */
package com.jfbuilds.tme2.program2;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * A generic container that acts as a collection of an arbitrary number of
 * objects of type Products or a subclass.
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
public class GenericOrder<T extends Product> extends AbstractList<T> {

	public String id;

	protected ArrayList<T> elements;

	public static int idNum = (int) (Math.random() * 5000);

	public GenericOrder() {
		elements = new ArrayList<>();
		idNum += 1;
		id = "Gen-Ord-" + idNum;
	}

	/**
	 * Create a GenericOrder from an ArrayList of supplied Product type
	 */
	public GenericOrder(ArrayList<T> suppliedProducts) {
		elements = suppliedProducts;
		idNum += 1;
		id = "Gen-Ord-" + idNum;
	}

	/**
	 * @see java.util.AbstractList#get(int)
	 */
	@Override
	public T get(int arg0) {
		return elements.get(arg0);
	}

	/**
	 * @see java.util.AbstractCollection#size()
	 */
	@Override
	public int size() {
		return elements.size();
	}

	/**
	 * @see java.util.AbstractCollection#toString()
	 */
	@Override
	public String toString() {
		String output = this.getClass().getSimpleName() + " - ID:" + id + "\n";
		for (Product p : elements) {
			output += p.toString() + "\n";
		}
		return output;
	}

	/**
	 * @see java.util.AbstractList#add(java.lang.Object)
	 */
	@Override
	public boolean add(T e) {
		elements.add(e);
		return true;
	}

	/**
	 * @param e
	 *            elements to add all from another GenericOrder of same type
	 * @return whether the operation was successful
	 */
	public boolean addAll(GenericOrder<T> e) {
		elements.addAll(e);
		return true;
	}

	/**
	 * TESTING CODE TO BE REMOVED...
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GenericOrder<Product> products = new GenericOrder<Product>(ProductGenerator.createComputerPartyOrder(50));
		System.out.println("Print items:");
		System.out.println(products);
	}
}
