package com.jfbuilds.tme2.program1;

public class ProductCount {

	public static void main(String[] args) {
		ProductTypeCounter counter = new ProductTypeCounter(Product.class);
		for (Product product : Products.createArray(50)) {
			System.out.print(" [" + product.getClass().getSimpleName() + "] ");
			counter.count(product);
		}
		System.out.println();
		System.out.println(counter);
	}
}