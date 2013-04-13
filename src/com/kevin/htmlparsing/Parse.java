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
		int count = -1;
		try
		{
			Document doc = Jsoup.connect(query).get();
			Elements ele = doc.select("div.weFound.BodyXLBold span.BodyXLBoldOrg");
			if(ele.isEmpty())
			{
				System.out.println("HTML format for walmart has been changed");
			
			}
			else
			{
				count =  Integer.valueOf(ele.text().split("\\s+")[0]);
			}			
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return count;
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
			Elements elements = doc.select("div#shelfDiv div.item");
			
			for(Element ele : elements)
			{
				String name = ele.select("div.prodInfoBox a.ListItemLink").text();
				String price = ele.select(".camelPrice").text().split(" ")[0];
				
				result.add(new Item(name, price));
			}
			
		}
		
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();			
		}
		
		
		return result;
	}

	
	
}
