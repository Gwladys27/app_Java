/**
 * 
 */
package com.pmsi;

import java.text.DateFormat;

/**
 * Date Manager
 */
public abstract class DateManager {
		
	protected DateFormat getDateFormatInstance() {
		return DateFormat.getDateTimeInstance(
					DateFormat.MEDIUM,
			        DateFormat.MEDIUM
				);
	}
}
