package com.kevin.htmlparsing;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Parse 
{
	
	public void getTotalResults(String query)
	{
		
	}

	public void getpage()
	{
		try
		{
			Document doc = Jsoup.connect("http://www.walmart.com/search/search-ng.do?search_query=digital+cameras&ic=16_0&Find=Find&search_constraint=0").get();
			Elements ele = doc.getElementsByClass("resultMsg");
		
			System.out.println(ele.size());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
