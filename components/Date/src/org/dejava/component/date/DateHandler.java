package org.dejava.component.date;

import java.util.Calendar;
import java.util.Date;

/**
 * Helps handling dates.
 */
public final class DateHandler {
	
	/**
	 * Adds time to a given date.	 * 
	 * @param date
	 *            Date to add time.
	 * @param calendarField
	 *            Calendar field to add the time.
	 * @param amount
	 *            Amount of time (for the given calendar field) to add. If the amount is negative, it is
	 *            subtracted from the date.
	 * @return The given time with the amount added.
	 */
	public static Date addToDate(final Date date, final Integer calendarField, final Integer amount) {
		// Gets the instance of the calendar.
		final Calendar calendar = Calendar.getInstance();
		// Sets the date of the calendar.
		calendar.setTime(date);
		// Adds the desired amount of time to the date.
		calendar.add(calendarField, amount);
		// Returns the new date.
		return calendar.getTime();
	}
	
	/**
	 * Adds time to a given date.
	 * 
	 * @param calendarField
	 *            Calendar field to add the time.
	 * @param amount
	 *            Amount of time (for the given calendar field) to add. If the amount is negative, it is
	 *            subtracted from the date.
	 * @return The given time with the amount added.
	 */
	public static Date addToDate(final Integer calendarField, final Integer amount) {
		// Gets the instance of the Gregorian calendar.
		final Calendar calendar = Calendar.getInstance();
		// Adds the desired amount of time to the date.
		calendar.add(calendarField, amount);
		// Returns the new date.
		return calendar.getTime();
	}
	
	/**
	 * Private constructor.
	 */
	private DateHandler() {
	}
}
