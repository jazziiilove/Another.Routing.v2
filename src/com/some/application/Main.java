/* 
 * Programmer: Baran Topal                   																			*
 * Project name: Another.Routing.v2          																			*
 * Folder name: src        																								*
 * Package name: com.some.application  																					*
 * File name: Main.java                     																	    	*
 *                                           																			*      
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *	                                                                                         							*
 *  LICENSE: This source file is subject to have the protection of GNU General Public License.             				*
 *	You can distribute the code freely but storing this license information. 											*
 *	Contact Baran Topal if you have any questions. barantopal@barantopal.com 										    *
 *	                                                                                         							*
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.some.application;

import java.util.Scanner;

import com.some.entitywork.Route;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Route r = new Route();		    	

		System.out.println("Enter the characters to be searched: ");
		Scanner scanIn = new Scanner(System.in);

		// user input
		String sWhatever = scanIn.nextLine();
		scanIn.close();

		// preprocess the user input and pass it to read function
		r.read(r.preprocess(sWhatever));

		// cheapest deal
		r.displayCheapest();
	}
}
