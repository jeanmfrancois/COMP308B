package com.jfbuilds.tme2.program1;

import java.util.*;

public abstract class ProductCreator {

	//private Random rand = new Random(47);

	public abstract List<Class<? extends Product>> types();

	public Product randomProduct() {
		//int n = rand.nextInt(types().size());
		int n = (int) (Math.ceil(Math.random()*(types().size()-1)));
		System.out.println("RAN NUM IS: " + n);
		try {
			return types().get(n).newInstance();
		} catch(InstantiationException e) {
			throw new RuntimeException(e);
		} catch(IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public Product[] createArray(int size) {
		Product[] result = new Product[size];
		for(int i = 0; i < size; i++) {
			result[i] = randomProduct();
		}
		return result;
	}

	public ArrayList<Product> arrayList(int size) {
		ArrayList<Product> result = new ArrayList<Product>();
		Collections.addAll(result, createArray(size));
		return result;
	}
}