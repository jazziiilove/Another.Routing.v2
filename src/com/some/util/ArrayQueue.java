/* 
 * Programmer: Baran Topal                   																			*
 * Project name: Another.Routing.v2          																			*
 * Folder name: src        																								*
 * Package name: com.some.util  																						*
 * File name: ArrrayQueue.java                     																	    *
 *                                           																			*      
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *	                                                                                         							*
 *  LICENSE: This source file is subject to have the protection of GNU General Public License.             				*
 *	You can distribute the code freely but storing this license information. 											*
 *	Contact Baran Topal if you have any questions. barantopal@barantopal.com 										    *
 *	                                                                                         							*
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.some.util;

import java.util.ArrayList;
import java.util.List;

import com.some.entitywork.Route;

public class ArrayQueue implements Queue
{
	/**
	 * Construct the queue.
	 */

	public ArrayQueue( )
	{
		theArray = new Route[ DEFAULT_CAPACITY ];
		makeEmpty( );
	}

	/**
	 * Test if the queue is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty( )
	{
		return currentSize == 0;
	}

	/**
	 * Make the queue logically empty.
	 */
	public void makeEmpty( )
	{
		currentSize = 0;
		front = 0;
		back = -1;        
	}

	/**
	 * Return and remove the least recently inserted item
	 * from the queue.
	 * @return the least recently inserted item in the queue.
	 * @throws UnderflowException if the queue is empty.
	 */
	public Route dequeue( )
	{
		if( isEmpty( ) )
			throw new UnderflowException( "ArrayQueue dequeue" );
		currentSize--;

		Route returnValue = theArray[ front ];
		front = increment( front );
		return returnValue;
	}

	/**
	 * Get the least recently inserted item in the queue.
	 * Does not alter the queue.
	 * @return the least recently inserted item in the queue.
	 * @throws UnderflowException if the queue is empty.
	 */
	public Route getFront( )
	{
		if( isEmpty( ) )
			throw new UnderflowException( "ArrayQueue getFront" );
		return theArray[ front ];
	}

	/**
	 * Insert a new item into the queue.
	 * @param x the item to insert.
	 */
	public void enqueue( Route a )
	{
		if( currentSize == theArray.length )
			doubleQueue( );
		back = increment( back );
		theArray[ back ] = a;
		currentSize++;
	}

	/**
	 * Internal method to increment with wraparound.
	 * @param x any index in theArray's range.
	 * @return x+1, or 0 if x is at the end of theArray.
	 */
	private int increment( int x )
	{
		if( ++x == theArray.length )
			x = 0;
		return x;
	}

	/**
	 * Internal method to expand theArray.
	 */
	private void doubleQueue( )
	{
		Route [ ] newArray;

		newArray = new Route[ theArray.length * 2 ];

		// copy elements that are logically in the queue
		for( int i = 0; i < currentSize; i++, front = increment( front ) )
			newArray[ i ] = theArray[ front ];

		theArray = newArray;
		front = 0;
		back = currentSize - 1;
	}

	private Route [ ] theArray;
	private int        currentSize;
	private int        front;
	private int        back;

	public Route[] getTheArray()
	{
		return theArray;
	}

	// remove null values in the array
	public void removeNullValues()
	{
		List<Route> list = new ArrayList<Route>();

		for(Route s : theArray) {
			if(s != null) {
				list.add(s);
			}
		}
		theArray = list.toArray(new Route[list.size()]);
	}
	public void sort()
	{			
		// check for empty or null array
		if (theArray ==null || theArray.length==0){
			return;
		}	     
		quicksort(0, theArray.length - 1);
	}

	// recursive quicksort
	private void quicksort(int low, int high) {
		int i = low, j = high;
		// get the pivot element from the middle of the list
		int pivot = theArray[low + (high-low)/2].getPrefix();

		// divide into two lists
		while (i <= j) {
			// if the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (theArray[i].getPrefix() < pivot) {
				i++;
			}
			// if the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (theArray[j].getPrefix() > pivot) {
				j--;
			}

			// if we have found a values in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the
			// values.
			// as we are done we can increase i and j
			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}
		}
		// recursion
		if (low < j)
			quicksort(low, j);
		if (i < high)
			quicksort(i, high);
	}

	private void exchange(int i, int j) {
		Route temp = theArray[i];
		theArray[i] = theArray[j];
		theArray[j] = temp;
	}

	private static final int DEFAULT_CAPACITY = 20;        
}
class UnderflowException extends RuntimeException
{
	/**
	 * Construct this exception object.
	 * @param message the error message.
	 */
	public UnderflowException( String message )
	{
		super( message );
	}
}