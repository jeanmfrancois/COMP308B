package com.jfbuilds.tme2.program2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

/******************************************************************
 * COMP308 Java for Programmer, SCIS, Athabasca University Assignment: TME2
 * 
 * @author: Steve Leung
 * @date : Oct 21, 2005
 ******************************************************************/
abstract class Product {

	protected static int count = 0;

	protected float price;

	protected int orderNumber;

	private Class<? extends Product> rootClass = Product.class;

	// return the price of a particular product
	abstract float price();

	protected Product() {
		this(4.0F);
	}

	protected Product(float p) {
		price = p;
		orderNumber = 100000 + count++;
	}

	@Override
	public String toString() {
		Class c = this.getClass();
		Field[] fields = c.getDeclaredFields();
		ArrayList<String> fCollection = new ArrayList<>();
		String className = c.getSimpleName();
		String fieldValues = "";
		String output = "";
		Class curClass = getClass();
		try {
			// check to make sure it is a subclass of root class
			if (rootClass.isAssignableFrom(this.getClass())) {
				// System.out.println("Item  " + c.getSimpleName() +
				// " is a candidate of the subclass:"
				// + rootClass.getSimpleName());
				while (rootClass.isAssignableFrom(curClass)) {
					// System.out.println("setting some fields " + curClass);
					fieldValues += getFieldValues(curClass, fCollection);
					curClass = curClass.getSuperclass();
				}
			} else {
				System.out.println("This item is not a candiate for reflecting field values");
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fieldValues += "";
		Collections.reverse(fCollection);
		for (int i = 1; i < fCollection.size(); i++) {
			output += " " + fCollection.get(i);
			output += ", ";
		}
		output += fCollection.get(0);
		return className + output;
	}

	public String getFieldValues(Class c, ArrayList<String> fCollection) {
		Field[] fields = c.getDeclaredFields();
		String fieldValues = "";
		try {
			for (Field f : fields) {
				// f.setAccessible(true);
				if (f.getName() != "rootClass" && f.getName() != "count") {
					String fieldInput = "";
					if (f.getName() == "orderNumber") {
						fieldInput += "order number";
						fieldInput += "=";
						fieldInput += f.get(this);
					} else if (f.getName() == "price") {
						fieldInput += "price";
						fieldInput += "=";
						fieldInput += "$" + f.get(this);
					} else {
						fieldInput += f.getName();
						fieldInput += "=";
						fieldInput += f.get(this);
					}
					fCollection.add(fieldInput);
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Access has not been set");
			e.printStackTrace();
		}
		return fieldValues;
	}
}

// ------------------------------------------------------------
class ComputerPart extends Product {

	public ComputerPart() {
		super(6.3F);
	}

	public ComputerPart(float p) {
		price = p;
	}

	@Override
	public float price() {
		return price;
	}
}

class Motherboard extends ComputerPart {

	protected String manufacturer;

	public Motherboard() {
		super(2.8F);
		manufacturer = "HP";
	}

	public Motherboard(String mfg, float p) {
		super(p);
		manufacturer = mfg;
	}

	public String getManufacturer() {
		return manufacturer;
	}
}

class RAM extends ComputerPart {

	protected int size;

	protected String manufacturer;

	public RAM() {
		super(5.78F);
		size = 2;
		manufacturer = "HP";
	}

	public RAM(String mfg, int size, float p) {
		super(p);
		this.manufacturer = mfg;
		this.size = size;
	}

	public String getManufacturer() {
		return manufacturer;
	}
}

class Drive extends ComputerPart {

	protected String type;

	protected int speed;

	public Drive() {
		super(10.5F);
		this.type = "SSD";
		this.speed = 600;
	}

	public Drive(String type, int speed, float p) {
		super(p);
		this.type = type;
		this.speed = speed;
	}

	public String getType() {
		return type;
	}

	public int getSpeed() {
		return speed;
	}
}

class Peripheral extends Product {

	public Peripheral() {
		super(3.6F);
	}

	public Peripheral(float p) {
		price = p;
	}

	@Override
	public float price() {
		return price;
	}
}

class Printer extends Peripheral {

	protected String model;

	public Printer() {
		super(5.29F);
		this.model = "Epson";
	}

	public Printer(String model, float p) {
		super(p);
		this.model = model;
	}

	public String getModel() {
		return model;
	}
}

class Monitor extends Peripheral {

	protected String model;

	public Monitor() {
		super(1.89F);
		this.model = "Acer";
	}

	public Monitor(String model, float p) {
		super(p);
		this.model = model;
	}

	public String getModel() {
		return model;
	}
}

class Service extends Product {

	public Service() {
		super(19.99F);
	}

	public Service(float p) {
		price = p;
	}

	@Override
	public float price() {
		return price;
	}
}

class AssemblyService extends Service {

	String provider;

	public AssemblyService() {
		super(92.24F);
		provider = "Geek Squad";
	}

	public AssemblyService(String pv, float p) {
		super(p);
		provider = pv;
	}

	public String getProvider() {
		return provider;
	}
}

class DeliveryService extends Service {

	String courier;

	public DeliveryService() {
		super(12.78F);
		courier = "UPS";
	}

	public DeliveryService(String c, float p) {
		super(p);
		courier = c;
	}

	public String getCourier() {
		return courier;
	}
}

// -------------------------------------------------------
class Cheese extends Product {

	public Cheese() {
		super(2.43F);
	}

	public Cheese(float p) {
		price = p;
	}

	@Override
	public float price() {
		return price;
	}
}

class Cheddar extends Cheese {

	public Cheddar() {
		super(1.14F);
	}

	public Cheddar(float p) {
		super(p);
	}
}

class Mozzarella extends Cheese {

	public Mozzarella() {
		super(8.24F);
	}

	public Mozzarella(float p) {
		super(p);
	}
}

class Fruit extends Product {

	public Fruit() {
		super(4.38F);
	}

	public Fruit(float p) {
		price = p;
	}

	@Override
	public float price() {
		return price;
	}
}

class Apple extends Fruit {

	public Apple() {
		super(2.08F);
	}

	public Apple(float p) {
		super(p);
	}
}

class Orange extends Fruit {

	public Orange() {
		super(1.02F);
	}

	public Orange(float p) {
		super(p);
	}
}

public class Products {
	// public static final ProductCreator creator = new LiteralProductCreator();
	//
	// public static Product randomProduct() {
	// return creator.randomProduct();
	// }
	//
	// public static Product[] createArray(int size) {
	// return creator.createArray(size);
	// }
	//
	// public static ArrayList<Product> arrayList(int size) {
	// return creator.arrayList(size);
	// }
}
