package com.kevin.item;

/**
 * Class to hold item name and price
 * @author kevin
 *
 */
public class Item
{
	String name,price;
	

	public Item(String name,String price) {
		this.name = name;
		this.price = price;
		if(price.compareTo("") == 0)
			this.price="Price not available";
		
		
	}


	@Override
	public String toString() {
		return "Item name=" + name + ", price=" + price + " ";
	}
	
	
}
