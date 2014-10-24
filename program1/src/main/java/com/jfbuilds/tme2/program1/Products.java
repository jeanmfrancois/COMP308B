package com.jfbuilds.tme2.program1;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/******************************************************************
 * COMP308 Java for Programmer, SCIS, Athabasca University Assignment: TME2
 * 
 * @author: Steve Leung
 * @param <T>
 * @date : Oct 21, 2005
 ******************************************************************/
class Product {

	final static String[] OMITTED_FIELDS = { "rootClass", "count" };

	protected static int count = 0;

	protected float price;

	protected int orderNumber;

	private Class<? extends Product> rootClass = Product.class;

	// return the price of a particular product
	float price() {
		return price;
	}

	protected Product() {
		this(4.0F);
	}

	protected Product(float p) {
		price = p;
		orderNumber = 100000 + count++;
	}

	public static Product generate() {
		System.out.println("Creating..");
		return new Product(1.01F);
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
				while (rootClass.isAssignableFrom(curClass)) {
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
		fields = Products.omitFinalFields(fields);
		fields = Products.omitSpecialFields(fields, OMITTED_FIELDS);
		DecimalFormat formatter = new DecimalFormat("$0.00");
		String fieldValues = "";
		try {
			for (Field f : fields) {
				// f.setAccessible(true);
				String fieldInput = "";
				if (f.getName() == "orderNumber") {
					fieldInput += "order number";
					fieldInput += "=";
					fieldInput += f.get(this);
				} else if (f.getName() == "price") {
					fieldInput += "price";
					fieldInput += "=";
					fieldInput += formatter.format(f.get(this));
				} else {
					fieldInput += f.getName();
					fieldInput += "=";
					fieldInput += f.get(this);
				}
				fCollection.add(fieldInput);
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

	final static float MAX_PRICE = 5.00F;

	final static float MIN_PRICE = 1.00F;

	final static float DEFAULT_PRICE = 3.00F;

	final static String[] MANUFACTURES = { "Asus", "Gigabyte", "MSI", "Intel", "Asrock" };

	final static String DEFAULT_MANUFACTURE = MANUFACTURES[3];

	protected String manufacturer;

	public Motherboard() {
		this(DEFAULT_MANUFACTURE, DEFAULT_PRICE);
	}

	public Motherboard(String mfg, float p) {
		super(p);
		manufacturer = mfg;
	}

	public static Motherboard generate() {
		Motherboard product =
				new Motherboard(MANUFACTURES[new Random().nextInt(MANUFACTURES.length)],
						(float) (Math.random() * (MAX_PRICE - MIN_PRICE)) + MIN_PRICE);
		return product;
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

	public static RAM generate() {
		return new RAM();
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

	public static Drive generate() {
		return new Drive();
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

/**
 * Products (description of class)
 * <p>
 * (description of core fields)
 * <p>
 * (description of core methods)
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class Products {

	/**
	 * @param fields
	 * @return
	 */
	public static Field[] omitFinalFields(Field[] fields) {
		ArrayList<Field> feildsArray = new ArrayList<>();
		Field[] newFields;
		for (Field f : fields) {
			if (!Modifier.isFinal(f.getModifiers())) {
				feildsArray.add(f);
			}
		}
		newFields = new Field[feildsArray.size()];
		return feildsArray.toArray(newFields);
	}

	public static Field[] omitSpecialFields(Field[] fields, String[] fieldNames) {
		ArrayList<Field> feildsArray = new ArrayList<>();
		Field[] newFields;
		for (Field f : fields) {
			boolean namePresent = false;
			for (String name : fieldNames) {
				if (f.getName().equals(name))
					namePresent = true;
			}
			if (namePresent != true)
				feildsArray.add(f);
		}
		newFields = new Field[feildsArray.size()];
		return feildsArray.toArray(newFields);
	}

	public static Product randomProduct() {
		return ProductGenerator.randomProduct(Product.class);
	}

	public static Product[] createArray(int size) {
		return ((Product[]) ProductGenerator.createOrder(size, Product.class).toArray());
	}

	public static ArrayList<Product> createList(int size) {
		return ProductGenerator.createOrder(size, Product.class);
	}
}
