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
 * @date : Oct 21, 2005 Class is used with minor additional content by
 *       Jean-francois Nepton
 ******************************************************************/
class Product {

	final static String[] OMITTED_FIELDS = { "rootClass", "count" };

	protected static int count = 1;

	protected float price;

	protected int orderNumber;

	private Class<? extends Product> rootClass = Product.class;

	float price() {
		return price;
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

class ComputerPart extends Product {

	public static ComputerPart generate() {
		try {
			throw new UnsupportedOperationException();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

class Motherboard extends ComputerPart {

	final static float MAX_PRICE = 75.00F;

	final static float MIN_PRICE = 50.00F;

	final static float DEFAULT_PRICE = 65.00F;

	final static String[] MANUFACTURES = { "Asus", "Gigabyte", "MSI", "Intel", "ASRock", "Chaintech", "DFI", "EPoX",
			"IBM", "LiteOn", "PNY", "Gumstix" };

	final static String DEFAULT_MANUFACTURE = MANUFACTURES[3];

	protected String manufacturer;

	public Motherboard() {
		this(DEFAULT_MANUFACTURE, DEFAULT_PRICE);
	}

	public Motherboard(String manufacture, float price) {
		this.price = price;
		this.manufacturer = manufacture;
		this.orderNumber = count++;
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

	final static float MAX_PRICE = 120.00F;

	final static float MIN_PRICE = 30.00F;

	final static String[] MANUFACTURES = { "Asus", "Chaintech", "HP", "IBM", "Kingston Technology", "Lenovo", "PNY",
			"Samsung Electronics", "Sony", "Toshiba" };

	final static int[] SIZES = { 1, 2, 4, 8, 16 };

	final static float DEFAULT_PRICE = 60.00F;

	final static String DEFAULT_MANUFACTURE = MANUFACTURES[2];

	final static int DEFAULT_SIZE = SIZES[2];

	protected int size;

	protected String manufacturer;

	public RAM() {
		this(DEFAULT_MANUFACTURE, DEFAULT_SIZE, DEFAULT_PRICE);
	}

	public RAM(String manufacture, int size, float price) {
		this.price = price;
		this.manufacturer = manufacture;
		this.size = size;
		this.orderNumber = count++;
	}

	public static RAM generate() {
		return new RAM(MANUFACTURES[new Random().nextInt(MANUFACTURES.length)],
				SIZES[new Random().nextInt(SIZES.length)], (float) (Math.random() * (MAX_PRICE - MIN_PRICE))
						+ MIN_PRICE);
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public int getSize() {
		return size;
	}
}

class Drive extends ComputerPart {

	final static float MAX_PRICE = 220.00F;

	final static float MIN_PRICE = 60.00F;

	final static String[] TYPES = { "Parallel Advanced Technology Attachment", "Serial ATA",
			"Small Computer System Interface", "Solid State Drive" };

	final static int[] SPEEDS = { 10, 800, 100, 6000 };

	final static float DEFAULT_PRICE = 90.00F;

	final static String DEFAULT_TYPE = TYPES[3];

	final static int DEFAULT_SPEED = SPEEDS[3];

	protected String type;

	protected int speed;

	public Drive() {
		this(DEFAULT_TYPE, DEFAULT_SPEED, DEFAULT_PRICE);
	}

	public Drive(String type, int speed, float price) {
		this.price = price;
		this.type = type;
		this.speed = speed;
		this.orderNumber = count++;
	}

	public static Drive generate() {
		int randomInt = (new Random().nextInt(TYPES.length));
		return new Drive(TYPES[randomInt], SPEEDS[randomInt], (float) (Math.random() * (MAX_PRICE - MIN_PRICE))
				+ MIN_PRICE);
	}

	public String getType() {
		return type;
	}

	public int getSpeed() {
		return speed;
	}
}

class Peripheral extends Product {

	public static Peripheral generate() {
		try {
			throw new UnsupportedOperationException();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

class Printer extends Peripheral {

	final static float MAX_PRICE = 80.00F;

	final static float MIN_PRICE = 40.00F;

	final static String[] MODELS = { "HP", "Epson", "Canon", "Lexmark", "Oki Data", "Xerox", "Citizen", "Panasonic",
			"Samsung", "Apple" };

	final static float DEFAULT_PRICE = 65.00F;

	final static String DEFAULT_MODEL = MODELS[0];

	protected String model;

	public Printer() {
		this(DEFAULT_MODEL, DEFAULT_PRICE);
	}

	public Printer(String model, float price) {
		this.price = price;
		this.model = model;
		this.orderNumber = count++;
	}

	public static Printer generate() {
		return new Printer(MODELS[new Random().nextInt(MODELS.length)],
				(float) (Math.random() * (MAX_PRICE - MIN_PRICE)) + MIN_PRICE);
	}

	public String getModel() {
		return model;
	}
}

class Monitor extends Peripheral {

	final static float MAX_PRICE = 260.00F;

	final static float MIN_PRICE = 100.00F;

	final static String[] MODELS = { "3M", "Acer Inc.", "AG Neovo", "AOC Monitors", "Apple", "Asus", "BenQ", "Compaq",
			"Dell", "HP", "Gateway" };

	final static float DEFAULT_PRICE = 120.00F;

	final static String DEFAULT_MODEL = MODELS[9];

	protected String model;

	public Monitor() {
		this(DEFAULT_MODEL, DEFAULT_PRICE);
	}

	public Monitor(String model, float price) {
		this.price = price;
		this.model = model;
		this.orderNumber = count++;
	}

	public static Monitor generate() {
		return new Monitor(MODELS[new Random().nextInt(MODELS.length)],
				(float) (Math.random() * (MAX_PRICE - MIN_PRICE)) + MIN_PRICE);
	}

	public String getModel() {
		return model;
	}
}

class Service extends Product {

	public static Service generate() {
		try {
			throw new UnsupportedOperationException();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

class AssemblyService extends Service {

	final static float MAX_PRICE = 180.00F;

	final static float MIN_PRICE = 60.00F;

	final static String[] PROVIDERS = { "Toshiba", "IBM", "Acer", "Fujitsu", "Apple", "Lenovo", "Alienware", "Sony",
			"Dell", "NEC", "HP", "Gateway", "Compaq" };

	final static float DEFAULT_PRICE = 95.00F;

	final static String DEFAULT_PROVIDER = PROVIDERS[10];

	protected String provider;

	public AssemblyService() {
		this(DEFAULT_PROVIDER, DEFAULT_PRICE);
	}

	public AssemblyService(String provider, float price) {
		this.price = price;
		this.provider = provider;
		this.orderNumber = count++;
	}

	public static AssemblyService generate() {
		return new AssemblyService(PROVIDERS[new Random().nextInt(PROVIDERS.length)],
				(float) (Math.random() * (MAX_PRICE - MIN_PRICE)) + MIN_PRICE);
	}

	public String getProvider() {
		return provider;
	}
}

class DeliveryService extends Service {

	final static float MAX_PRICE = 115.00F;

	final static float MIN_PRICE = 45.00F;

	final static String[] COURIERS = { "UPS", "FedEx", "Purolator", "Canada Post", "DHL" };

	final static float DEFAULT_PRICE = 75.00F;

	final static String DEFAULT_COURIER = COURIERS[3];

	String courier;

	public DeliveryService() {
		this(DEFAULT_COURIER, DEFAULT_PRICE);
	}

	public DeliveryService(String courier, float price) {
		this.price = price;
		this.courier = courier;
		this.orderNumber = count++;
	}

	public static DeliveryService generate() {
		return new DeliveryService(COURIERS[new Random().nextInt(COURIERS.length)],
				(float) (Math.random() * (MAX_PRICE - MIN_PRICE)) + MIN_PRICE);
	}

	public String getCourier() {
		return courier;
	}
}

// -------------------------------------------------------
class Cheese extends Product {

	public static Cheese generate() {
		try {
			throw new UnsupportedOperationException();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

class Cheddar extends Cheese {

	final static float MAX_PRICE = 7.25F;

	final static float MIN_PRICE = 2.25F;

	final static float DEFAULT_PRICE = 3.75F;

	public Cheddar() {
		this(DEFAULT_PRICE);
	}

	public Cheddar(float price) {
		this.price = price;
		this.orderNumber = count++;
	}

	public static Cheddar generate() {
		return new Cheddar((float) (Math.random() * (MAX_PRICE - MIN_PRICE)) + MIN_PRICE);
	}
}

class Mozzarella extends Cheese {

	final static float MAX_PRICE = 8.00F;

	final static float MIN_PRICE = 3.00F;

	final static float DEFAULT_PRICE = 4.50F;

	public Mozzarella() {
		this(DEFAULT_PRICE);
	}

	public Mozzarella(float price) {
		this.price = price;
		this.orderNumber = count++;
	}

	public static Mozzarella generate() {
		return new Mozzarella((float) (Math.random() * (MAX_PRICE - MIN_PRICE)) + MIN_PRICE);
	}
}

class Fruit extends Product {

	public static Fruit generate() {
		try {
			throw new UnsupportedOperationException();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

class Apple extends Fruit {

	final static float MAX_PRICE = 2.50F;

	final static float MIN_PRICE = .50F;

	final static float DEFAULT_PRICE = 0.95F;

	public Apple() {
		this(DEFAULT_PRICE);
	}

	public Apple(float price) {
		this.price = price;
		this.orderNumber = count++;
	}

	public static Apple generate() {
		return new Apple((float) (Math.random() * (MAX_PRICE - MIN_PRICE)) + MIN_PRICE);
	}
}

class Orange extends Fruit {

	final static float MAX_PRICE = 3.50F;

	final static float MIN_PRICE = .70F;

	final static float DEFAULT_PRICE = 1.05F;

	public Orange() {
		this(DEFAULT_PRICE);
	}

	public Orange(float price) {
		this.price = price;
		this.orderNumber = count++;
	}

	public static Orange generate() {
		return new Orange((float) (Math.random() * (MAX_PRICE - MIN_PRICE)) + MIN_PRICE);
	}
}

/**
 * Products in general to represent different Product types
 * <p>
 * <p>
 * omitFinalFields is used to omit declared final fields when performing the
 * toString method on a Product. omitSpecialFields is used to omit fields which
 * are in collection in static constant OMITTED_FIELDS when performing the
 * toString method on a Product.
 * 
 * @author Jean-francois Nepton
 * @version %I%, %G%
 * @since 1.0
 */
public class Products {

	/**
	 * @param fields
	 *            that will be checked for final modifier
	 * @return array of fields that meet criteria of not being final
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

	/**
	 * @param fields
	 *            that will be checked for inclusion in a string collection
	 *            fieldNames
	 * @param fieldNames
	 *            String array such as OMMITED_FIELDS constant which are used to
	 *            check for exclusion in returned list
	 * @return array of fields that meet criteria of not being in String array
	 *         fieldNames
	 */
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
}
