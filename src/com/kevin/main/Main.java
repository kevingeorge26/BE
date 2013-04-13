package com.kevin.main;

import com.kevin.htmlparsing.Parse;

/**
 * 
 * @author kevin
 *
 */
public class Main {


	public static void main(String[] args) 
	{
		String searchItem;
		int pageNumber = 1;

		if( args.length != 1 && args.length != 2 )
		{
			printUsageOption();
		}

		searchItem = args[0];

		if( args.length == 2)
		{
			try{
				pageNumber = Integer.valueOf(args[1]);
				if(pageNumber<1)
					throw new NumberFormatException("Page number cannot be negative or zero.");


			}
			catch(NumberFormatException e)
			{
				printNumberFormat();
			}
		}

		if( args.length == 2)
			new QueryProcessing().getItemizedResult(searchItem, pageNumber);
		else
			new QueryProcessing().getTotalResult(searchItem);


	}


	private static void printUsageOption()
	{
		System.out.println(" Wrong Input, Please try \n java -jar Assignment.jar <keyword> or " +
				"java -jar Assignment.jar <keyword> <page number>\n" +
				"\t<keyword> = Item you want to search\n" +
				"\t<page number> = Page number in Integer  (Numbers greater 0)");

		System.exit(1);
	}

	private static void printNumberFormat()
	{
		System.out.println("valid <page number> cannot have negative or zero");
		System.exit(1);
	}

}
