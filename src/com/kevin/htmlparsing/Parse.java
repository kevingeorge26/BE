package com.kevin.htmlparsing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;


import com.kevin.item.Item;

/**
 * This class takes in valid URLS to
 * download and parse the html file
 * 
 * @author kevin
 *
 */
public class Parse 
{
	/**
	 * This function is used to handle query 1 of the assignment
	 * 
	 * @param query valid URL to number of products
	 * @return Number of products that matched query
	 */
	public int  getTotalResults(String query)
	{
		try
		{
			Document doc = Jsoup.connect(query).get();
			Elements searchResultDiv = doc.getElementsByClass("resultMsg");
			
			if(searchResultDiv.size() != 1)
			{
				System.out.println("HTML structure was changed");
				return 0;
			}
			
			Elements result = searchResultDiv.get(0).getElementsByClass("BodyXLBoldOrg");
			if(result.size() != 1)
			{
				System.out.println("HTML structure was changed1");
				return 0;
			}
			
			return Integer.valueOf(result.get(0).ownText().split("\\s+")[0]);
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * This function is used to handle query 2 of
	 * the assignment
	 * 
	 * @param query Valid URL
	 * @return
	 */
	public List<Item> getItemsOnPage(String query)
	{
		List<Item> result = new ArrayList<Item>();
		try 
		{
			Document doc = Jsoup.connect(query).get(); //Jsoup.parse(new File("test.html"), "UTF-8");//
			Element mainDiv = doc.getElementById("shelfDiv");
			
			for(Element ele : mainDiv.select("div.item"))
			{
				String name = ele.select("div.prodInfoBox").get(0).select("a.ListItemLink").get(0).select("a.ListItemLink").text();
				String price = ele.select(".camelPrice").text().split(" ")[0];
				
				result.add(new Item(name, price));
			}
			
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
		return result;
	}

	
	
}
