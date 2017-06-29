/* 
 * Programmer: Baran Topal                   																			*
 * Project name: Another.Routing.v2          																			*
 * Folder name: src        																								*
 * Package name: com.some.entitywork																					*
 * File name: Route.java                     																	        *
 *                                           																			*      
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *	                                                                                         							*
 *  LICENSE: This source file is subject to have the protection of GNU General Public License.             				*
 *	You can distribute the code freely but storing this license information. 											*
 *	Contact Baran Topal if you have any questions. barantopal@barantopal.com 										    *
 *	                                                                                         							*
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.some.entitywork;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.some.util.ArrayQueue;

public class Route {

	// ArrayQueue instance to put the inputs
	private ArrayQueue queue;

	// ArrayQueue instance to put the matched values
	private ArrayQueue founds;

	// cheapest deal ArrayList
	private ArrayList <Route> cheapList = new ArrayList<Route>();

	private int prefix;
	private float price;
	private char letter;		

	public int getPrefix()
	{
		return prefix;
	}

	// default ctor
	public Route()
	{ }

	// non-default ctor
	public Route(char letter, int prefix, float price)
	{
		this.letter = letter;
		this.prefix = prefix;
		this.price = price;
	}

	// preprocess the user input, replace each alpha character with empty value
	public String preprocess(String userInput)
	{		
		String regx = "+-";
		char[] ca = regx.toCharArray();
		for (char c : ca) 
		{
			userInput = userInput.replace(""+c, "");
		}
		return userInput;
	}

	// read the file
	public void read(String cleanUserInput)
	{

		try{
			// open the file that is the first command line parameter
			FileInputStream fstream = new FileInputStream("List.txt");

			// get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine = br.readLine();			
			while(strLine != null)
			{
				if(strLine.startsWith("O"))
				{
					// setup
					founds = new ArrayQueue();
					queue = new ArrayQueue();		
					letter = strLine.charAt(strLine.length() - 2);
					strLine = br.readLine();
				}
				else
				{									
					while(strLine != null && !strLine.startsWith("O"))
					{
						process(strLine, queue);						
						strLine = br.readLine();
					}

					// remove the trailing null values in array
					queue.removeNullValues();

					// sort the queue
					queue.sort();

					for(int i = 1; i < cleanUserInput.length(); i++)
					{
						String chunks = cleanUserInput.substring(0, i);
						int index = binarySearch(chunks, queue.getTheArray());
						if(index != -1)
						{
							founds.enqueue(queue.getTheArray()[index]);
						}
					}
					founds.removeNullValues();
					display();						
				}
			}
			// close the input stream
			in.close();			
		}catch (Exception e){
			// catch exception if any
			System.out.println("Error: " + e.getMessage());
		}
	}

	// tokenize the numerical lines with delimiter blank
	private void process(String line, ArrayQueue queue)
	{
		StringTokenizer stringTokenizer = new StringTokenizer(line);
		prefix = (Integer.parseInt(stringTokenizer.nextToken()));
		price = (Float.valueOf(stringTokenizer.nextToken()));		
		queue.enqueue(new Route(letter, prefix, price));
	}

	// binary search upon the string chunks
	public int binarySearch(String chunks, Route []r) 
	{		
		int high = r.length -1, low = 0, mid;				
		int inputChunks = Integer.parseInt(chunks);
		while (low <= high) 
		{
			mid = (low + high) / 2;
			if (r[mid].prefix > inputChunks) {
				high = mid - 1;
			} else if (r[mid].prefix < inputChunks) {
				low = mid + 1;
			} else {
				//found index
				return mid;
			}		
		}
		return -1;
	}

	// display the all outputs
	public void display()
	{
		if(founds.isEmpty())		
			System.out.println("Operator: " + letter + " has no corresponding prefix.");
		else
		{
			founds.sort();
			Route result = founds.getTheArray()[founds.getTheArray().length -1];
			cheapList.add(result);
			System.out.println("Operator: " + result .letter + " has the prefix: " + result.prefix + " and has the price: " + result.price);		
		}			
	}

	// display the cheapest operator and its contents
	public void displayCheapest()
	{
		Route routeCheap = cheapList.get(0);
		float cheapest = routeCheap.price;
		int index = 0;
		for(int i = 1; i < cheapList.size(); i++)
		{
			Route temp = cheapList.get(i);
			if(temp.price < cheapest)
			{
				cheapest = temp.price;
				index = i;
			}
		}
		System.out.println("Cheapest deal is as follows:"); 
		System.out.println("Operator " + cheapList.get(index).letter + ": has the prefix: " + cheapList.get(index).prefix + " and has the price: " + cheapList.get(index).price);
	}
}
