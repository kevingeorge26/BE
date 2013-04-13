package com.kevin.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class QueryProcessing 
{
	private  String basicURL = "http://www.walmart.com/search/search-ng.do?Find=Find&search_constraint=0";
	String count = "ic=16_0";
	
	
	public QueryProcessing() {
		loadConfigFile();
	}
	

	private  void loadConfigFile()
	{
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("config.properties")));
			if(properties.getProperty("item_per_page")!= null)
				count = properties.getProperty("item_per_page");
		} 
		catch (IOException e) {
		System.out.println("Error in loading config file. Going to use default config");	
		}
	}
	
	public void getTotalResult(String searchItem)
	{
		searchItem = "search_query=" + searchItem.replaceAll("\\s+","+");
		String url = basicURL+"&" + count + "&" + searchItem;
		System.out.println(url);
	}
}
