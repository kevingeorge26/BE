package com.kevin.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.kevin.htmlparsing.Parse;
import com.kevin.item.Item;

/**
 * This is class creates a valid URL
 * based on the query .
 * 
 * It then calls the appropriate to download
 * and parse the HTML file
 * 
 * @author kevin
 *
 */
public class QueryProcessing 
{
	private  String basicURL = "http://www.walmart.com/search/search-ng.do?Find=Find&search_constraint=0";
	int count = 16;


	public QueryProcessing() {
		loadConfigFile();
	}

/**
 * Loads the config file for the application.
 * Here you can set your preference of number 
 * of itmes per page to 16 or 32.
 * 
 */
	private  void loadConfigFile()
	{
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("config.properties")));
			count = Integer.valueOf(properties.getProperty("item_per_page", "16"));
		} 
		catch (IOException e) {
			System.out.println("Error in loading config file. Going to use default config");	
		}
	}

	/**
	 * This method is used to find number of
	 * items availabe
	 * 
	 * @param searchItem The item to be searched e.g "Digital Cameras"
	 * 
	 */
	public void getTotalResult(String searchItem)
	{
		String countIC = "ic=" + String.valueOf(count)+ "_0";
		String searchItemQ = "search_query=" + searchItem.replaceAll("\\s+","+");

		String url = basicURL+"&" + countIC + "&" + searchItemQ;
		int count = new Parse().getTotalResults(url);

		System.out.println("Number of results found for " + searchItem + " = " +count);
	}

	/**
	 * This method is used get the details of
	 * the searched product on the specified
	 * page
	 * 
	 * @param searchItem The item to be searched e.g "Digital Cameras"
	 * @param pageNumber Specify the page number you want to List
	 */
	public void getItemizedResult(String searchItem , int pageNumber)
	{
		String countIC = "ic=" + String.valueOf(count)+ "_" + String.valueOf(count * (pageNumber-1));
		String searchItemQ = "search_query=" + searchItem.replaceAll("\\s+","+");

		String url = basicURL+"&" + countIC + "&" + searchItemQ;
		List<Item> items = new Parse().getItemsOnPage(url);

		System.out.println(items.size());
		for(Item item : items)
			System.out.println(item.toString());
	}
}
