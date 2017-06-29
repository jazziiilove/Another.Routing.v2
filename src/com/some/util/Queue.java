/* 
 * Programmer: Baran Topal                   																			*
 * Project name: Another.Routing.v2          																		    *
 * Folder name: src        																								*
 * Package name: com.some.util  																						*
 * File name: Queue.java                     																	        *
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

import com.some.entitywork.Route;

public interface Queue
{
	/**
	 * Insert a new item into the queue.
	 * @param x the item to insert.
	 */
	void  enqueue( Route x );

	/**
	 * Get the least recently inserted item in the queue.
	 * Does not alter the queue.
	 * @return the least recently inserted item in the queue.
	 * @exception UnderflowException if the queue is empty.
	 */
	Route getFront( );

	/**
	 * Return and remove the least recently inserted item
	 * from the queue.
	 * @return the least recently inserted item in the queue.
	 * @exception UnderflowException if the queue is empty.
	 */
	Route dequeue( );

	/**
	 * Test if the queue is logically empty.
	 * @return true if empty, false otherwise.
	 */
	boolean isEmpty( );

	/**
	 * Make the queue logically empty.
	 */
	void makeEmpty( );
}