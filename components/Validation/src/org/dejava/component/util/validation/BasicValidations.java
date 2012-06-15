package org.dejava.component.util.validation;

import java.util.Collection;
import java.util.Date;

import org.dejava.component.util.date.DateHandler;

/**
 * Some basic validations.
 */
public final class BasicValidations {
	
	/**
	 * Private constructor.
	 */
	public BasicValidations() {
	}
	
	/**
	 * Validate if two objects are equal.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param otherObject
	 *            Object that should be equal.
	 * @return If two objects are equal.
	 */
	public static Boolean validateEquals(final Object object, final Object otherObject) {
		// If the object is null.
		if (object == null) {
			// If the other object is null too.
			if (otherObject == null) {
				// Returns true.
				return true;
			}
			// Otherwise.
			else {
				// Returns false.
				return false;
			}
		}
		// If the object is not null.
		else {
			// If the objects are equal.
			if (object.equals(otherObject)) {
				// Returns true.
				return true;
			}
			// If they are not equal.
			else {
				// Returns false.
				return false;
			}
		}
	}
	
	/**
	 * Validate if two objects are not equal.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param otherObject
	 *            Object that should be equal.
	 * @return If two objects are not equal.
	 */
	public static Boolean validateNotEquals(final Object object, final Object otherObject) {
		// Returns the opposite of the not null validation.
		return !validateEquals(object, otherObject);
	}
	
	/**
	 * Validate if an object is not null.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @return If an object is not null.
	 */
	public static Boolean validateNotNull(final Object object) {
		// If the object is null.
		if (object == null) {
			// Returns false;
			return false;
		}
		// If it is not null.
		else {
			// Returns true.
			return true;
		}
	}
	
	/**
	 * Validate if an object is null.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @return If an object is null.
	 */
	public static Boolean validateNull(final Object object) {
		// Returns the opposite of the not null validation.
		return !validateNotNull(object);
	}
	
	/**
	 * Validate the minimum value of an object.
	 * 
	 * @param <AnyComparable>
	 *            Any comparable class.
	 * @param object
	 *            Object to be validated.
	 * @param minimumValue
	 *            Minimum value (included) for the object.
	 * @return If the object has, at least, a minimum value.
	 */
	public static <AnyComparable extends Comparable<AnyComparable>> Boolean validateMinimumValue(
			final AnyComparable object, final AnyComparable minimumValue) {
		// Return if the object has, at least, the minimum value.
		return (object.compareTo(minimumValue) >= 0);
	}
	
	/**
	 * Validate the maximum value of an object.
	 * 
	 * @param <AnyComparable>
	 *            Any comparable class.
	 * @param object
	 *            Object to be validated.
	 * @param maximumValue
	 *            Maximum value (included) for the object.
	 * @return If the object has, at most, a maximum value.
	 */
	public static <AnyComparable extends Comparable<AnyComparable>> Boolean validateMaximumValue(
			final AnyComparable object, final AnyComparable maximumValue) {
		// Return if the object has, at most, the maximum value.
		return (object.compareTo(maximumValue) <= 0);
	}
	
	/**
	 * Validate the minimum value of an object. The minimum value will be evaluated adding the giving amount
	 * (for the given calendar field) to the current date.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param calendarField
	 *            Calendar field to add the amount for the minimum value.
	 * @param amount
	 *            Amount (for the given calendar field) to be added to the current date to evaluate the
	 *            minimum value.
	 * @return If the object has, at least, a minimum value.
	 */
	public static Boolean validateMinimumValue(final Date object, final Integer calendarField,
			final Integer amount) {
		// Validate using the general minimum value validation.
		return validateMinimumValue(object, DateHandler.addToDate(calendarField, amount));
	}
	
	/**
	 * Validate the maximum value of an object. The maximum value will be evaluated adding the giving amount
	 * (for the given calendar field) to the current date.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param calendarField
	 *            Calendar field to add the amount for the maximum value.
	 * @param amount
	 *            Amount (for the given calendar field) to be added to the current date to evaluate the
	 *            maximum value.
	 * @return If the object has, at most, a maximum value.
	 */
	public static Boolean validateMaximumValue(final Date object, final Integer calendarField,
			final Integer amount) {
		// Validate using the general maximum value validation.
		return validateMaximumValue(object, DateHandler.addToDate(calendarField, amount));
	}
	
	/**
	 * Validate if a object is not empty.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @return If a object is not empty.
	 */
	public static Boolean validateNotEmpty(final Collection<?> object) {
		// If the object is empty.
		if (object.isEmpty()) {
			// Returns false.
			return false;
		}
		// Otherwise.
		else {
			// Returns true.
			return true;
		}
	}
	
	/**
	 * Validate if a object is empty.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @return If a object is empty.
	 */
	public static Boolean validateEmpty(final Collection<?> object) {
		// Returns the opposite of the not empty validation.
		return !validateNotEmpty(object);
	}
	
	/**
	 * Validate if an object has the given size.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param size
	 *            Exact size for the object.
	 * @return If an object has the given size.
	 */
	public static Boolean validateExactSize(final Collection<?> object, final Integer size) {
		// Returns if the object has the given size.
		return object.size() == size;
	}
	
	/**
	 * Validate if an object does not have the given size.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param size
	 *            Size that the object must not have.
	 * @return If an object has the given size.
	 */
	public static Boolean validateDifferentSize(final Collection<?> object, final Integer size) {
		// Returns the opposite of the exact size validation.
		return !validateExactSize(object, size);
	}
	
	/**
	 * Validate the minimum size of an object.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param minimumSize
	 *            Minimum size (included) for the object.
	 * @return If the object has, at least, a minimum size.
	 */
	public static Boolean validateMinimumSize(final Collection<?> object, final Integer minimumSize) {
		// Returns if the object has, at least, the minimum size.
		return object.size() >= minimumSize;
	}
	
	/**
	 * Validate the maximum size of an object.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param maximumSize
	 *            Maximum size (included) for the object.
	 * @return If the object has, at most, a maximum size.
	 */
	public static Boolean validateMaximumSize(final Collection<?> object, final Integer maximumSize) {
		// Returns if the object has, at most, the maximum size.
		return object.size() <= maximumSize;
	}
	
	/**
	 * Validate if a object is not empty.
	 * 
	 * @param <AnyObject>
	 *            Any object class.
	 * @param object
	 *            Object to be validated.
	 * @return If a object is not empty.
	 */
	public static <AnyObject extends Object> Boolean validateNotEmpty(final AnyObject[] object) {
		// If the object is empty.
		if (object.length == 0) {
			// Returns false.
			return false;
		}
		// Otherwise.
		else {
			// Returns true.
			return true;
		}
	}
	
	/**
	 * Validate if a object is empty.
	 * 
	 * @param <AnyObject>
	 *            Any object class.
	 * @param object
	 *            Object to be validated.
	 * @return If a object is empty.
	 */
	public static <AnyObject extends Object> Boolean validateEmpty(final AnyObject[] object) {
		// Returns the opposite of the not empty validation.
		return !validateNotEmpty(object);
	}
	
	/**
	 * Validate if an object has the given size.
	 * 
	 * @param <AnyObject>
	 *            Any object class.
	 * @param object
	 *            Object to be validated.
	 * @param size
	 *            Exact size for the object.
	 * @return If an object has the given size.
	 */
	public static <AnyObject extends Object> Boolean validateExactSize(final AnyObject[] object,
			final Integer size) {
		// Returns if the object has the given size.
		return object.length == size;
	}
	
	/**
	 * Validate if an object does not have the given size.
	 * 
	 * @param <AnyObject>
	 *            Any object class.
	 * @param object
	 *            Object to be validated.
	 * @param size
	 *            Size that the object must not have.
	 * @return If an object has the given size.
	 */
	public static <AnyObject extends Object> Boolean validateDifferentSize(final AnyObject[] object,
			final Integer size) {
		// Returns the opposite of the exact size validation.
		return !validateExactSize(object, size);
	}
	
	/**
	 * Validate the minimum size of an object.
	 * 
	 * @param <AnyObject>
	 *            Any object class.
	 * @param object
	 *            Object to be validated.
	 * @param minimumSize
	 *            Minimum size (included) for the object.
	 * @return If the object has, at least, a minimum size.
	 */
	public static <AnyObject extends Object> Boolean validateMinimumSize(final AnyObject[] object,
			final Integer minimumSize) {
		// Returns if the object has, at least, the minimum size.
		return object.length >= minimumSize;
	}
	
	/**
	 * Validate the maximum size of an object.
	 * 
	 * @param <AnyObject>
	 *            Any object class.
	 * @param object
	 *            Object to be validated.
	 * @param maximumSize
	 *            Maximum size (included) for the object.
	 * @return If the object has, at most, a maximum size.
	 */
	public static <AnyObject extends Object> Boolean validateMaximumSize(final AnyObject[] object,
			final Integer maximumSize) {
		// Returns if the object has, at most, the maximum size.
		return object.length <= maximumSize;
	}
	
	/**
	 * Validate if a object is not empty.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @return If a object is not empty.
	 */
	public static Boolean validateNotEmpty(final String object) {
		// If the object is empty.
		if (object.isEmpty()) {
			// Returns false.
			return false;
		}
		// Otherwise.
		else {
			// Returns true.
			return true;
		}
	}
	
	/**
	 * Validate if a object is empty.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @return If a object is empty.
	 */
	public static Boolean validateEmpty(final String object) {
		// Returns the opposite of the not empty validation.
		return !validateNotEmpty(object);
	}
	
	/**
	 * Validate if an object has the given size.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param size
	 *            Exact size for the object.
	 * @return If an object has the given size.
	 */
	public static Boolean validateExactSize(final String object, final Integer size) {
		// Returns if the object has the given size.
		return object.length() == size;
	}
	
	/**
	 * Validate if an object does not have the given size.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param size
	 *            Size that the object must not have.
	 * @return If an object has the given size.
	 */
	public static Boolean validateDifferentSize(final String object, final Integer size) {
		// Returns the opposite of the exact size validation.
		return !validateExactSize(object, size);
	}
	
	/**
	 * Validate the minimum size of an object.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param minimumSize
	 *            Minimum size (included) for the object.
	 * @return If the object has, at least, a minimum size.
	 */
	public static Boolean validateMinimumSize(final String object, final Integer minimumSize) {
		// Returns if the object has, at least, the minimum size.
		return object.length() >= minimumSize;
	}
	
	/**
	 * Validate the maximum size of an object.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param maximumSize
	 *            Maximum size (included) for the object.
	 * @return If the object has, at most, a maximum size.
	 */
	public static Boolean validateMaximumSize(final String object, final Integer maximumSize) {
		// Returns if the object has, at most, the maximum size.
		return object.length() <= maximumSize;
	}
	
	/**
	 * Validates if a text match a regular expression.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param regularExpression
	 *            Regular expression to validate the text.
	 * @return If a text match a regular expression.
	 */
	public static Boolean validateRegExMatch(final String object, final String regularExpression) {
		// Returns if the text match the expression.
		return object.matches(regularExpression);
	}
	
	/**
	 * Validates if a text does not match a regular expression.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param regularExpression
	 *            Regular expression to validate the text.
	 * @return If a text does not match a regular expression.
	 */
	public static Boolean validateNotRegExMatch(final String object, final String regularExpression) {
		// Returns the opposite of the regular expression match validation.
		return !validateRegExMatch(object, regularExpression);
	}
	
	/**
	 * Regular expression to validate e-mail addresses.
	 */
	public static final String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/**
	 * Validates an e-mail address.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @return If the text is a valid e-mail address.
	 */
	public static Boolean validateEmail(final String object) {
		// Validates the object using the e-mail address regular expression.
		return validateRegExMatch(object, EMAIL_REGEX);
	}
	
	/**
	 * Validates a CPF (Brazilian SSID).
	 * 
	 * @param object
	 *            Object to be validated.
	 * @return If the text is a valid CPF.
	 */
	public static Boolean validateCPF(final String object) {
		// Initial sum is 0 (will be used to validate check digits).
		Integer sum = 0;
		// If the object have 11 digits.
		if (object.length() == 11) {
			// For each one of the first 9 digits.
			for (Integer currentDigitIndex = 0; currentDigitIndex < 9; currentDigitIndex++) {
				// Adds the amount for the current digit.
				sum += (10 - currentDigitIndex) * (object.charAt(currentDigitIndex) - '0');
			}
			// The sum is 11 minus its modulo 11 now.
			sum = 11 - (sum % 11);
			// If it is 10.
			if (sum > 9) {
				// Then, it is 0.
				sum = 0;
			}
			// If the first check digit is the one represented by the sum.
			if (sum == (object.charAt(9) - '0')) {
				// Sum is 0 again (time to validate the second check digit).
				sum = 0;
				// For each one of the first 10 digits.
				for (Integer currentDigitIndex = 0; currentDigitIndex < 10; currentDigitIndex++) {
					// Adds the amount for the current digit.
					sum += (11 - currentDigitIndex) * (object.charAt(currentDigitIndex) - '0');
				}
				// The sum is its modulo 11 now.
				sum = 11 - (sum % 11);
				// If it is 10.
				if (sum > 9) {
					// Then, it is 0.
					sum = 0;
				}
				// If the second check digit is the one represented by the sum.
				if (sum == (object.charAt(10) - '0')) {
					// Returns true.
					return true;
				}
			}
		}
		// If the validation could not be done, returns false.
		return false;
	}
	
	/**
	 * Validates a CNPJ (Brazilian company ID).
	 * 
	 * @param object
	 *            Object to be validated.
	 * @return If the text is a valid CNPJ.
	 */
	public static Boolean validateCNPJ(final String object) {
		// Initial sum is 0 (will be used to validate check digits).
		Integer sum = 0;
		// If the object does not have 11 digits.
		if (object.length() == 14) {
			// For each one of the first 12 digits.
			for (Integer currentDigitIndex = 0, auxNumber = 5; currentDigitIndex < 12; currentDigitIndex++) {
				// Adds the amount for the current digit.
				sum += auxNumber-- * (object.charAt(currentDigitIndex) - '0');
				// If the auxiliary number is 1.
				if (auxNumber < 2) {
					// It is 9.
					auxNumber = 9;
				}
			}
			// The sum is 11 minus its modulo 11 now.
			sum = 11 - (sum % 11);
			// If it is 10.
			if (sum > 9) {
				// Then, it is 0.
				sum = 0;
			}
			// If the first check digit is the one represented by the sum.
			if (sum == (object.charAt(12) - '0')) {
				// Sum is 0 again (time to validate the second check digit).
				sum = 0;
				// For each one of the first 13 digits.
				for (Integer currentDigitIndex = 0, auxNumber = 6; currentDigitIndex < 13; currentDigitIndex++) {
					// Adds the amount for the current digit.
					sum += auxNumber-- * (object.charAt(currentDigitIndex) - '0');
					// If the auxiliary number is 1.
					if (auxNumber < 2) {
						// It is 9.
						auxNumber = 9;
					}
				}
				// The sum is 11 minus its modulo 11 now.
				sum = 11 - (sum % 11);
				// If it is 10.
				if (sum > 9) {
					// Then, it is 0.
					sum = 0;
				}
				// If the second check digit is the one represented by the sum.
				if (sum == (object.charAt(13) - '0')) {
					// Returns true.
					return true;
				}
			}
		}
		// If the validation could not be done, returns false.
		return false;
	}
	
	/**
	 * Regular expression for any character.
	 */
	public static final String ANYTHING_REGEX = ".*";
	
	/**
	 * Regular expression for any character that is not a letter or number.
	 */
	public static final String NOT_LETTER_NUMBER = "[^a-zA-Z0-9]";
	
	/**
	 * Validates if a text has the given forbidden word.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param forbiddenWord
	 *            Word that is forbidden in the text.
	 * @param asSequence
	 *            If the forbidden word must be validated as a sequence of characters. If false, the word just
	 *            must not be isolated in the text (between characters that are not letters or numbers).
	 * @return If a text has the given forbidden word.
	 */
	public static Boolean validateForbiddenWord(final String object, String forbiddenWord,
			final Boolean asSequence) {
		// If the forbidden word cannot be found as a sequence of characters.
		if (asSequence) {
			// Adds the regular expression for any character at the beginning and at the end of the word.
			forbiddenWord = ANYTHING_REGEX + forbiddenWord + ANYTHING_REGEX;
		}
		// If the forbidden word cannot be found as an isolated word.
		else {
			// Adds the regular expression to force the forbidden word not to be isolated in the text (between
			// characters that are not letters or numbers).
			forbiddenWord = "((" + ANYTHING_REGEX + ")" + NOT_LETTER_NUMBER + ")?" + forbiddenWord + "("
					+ NOT_LETTER_NUMBER + "(" + ANYTHING_REGEX + "))?";
		}
		// Validate the regular expression for the forbidden word.
		return validateNotRegExMatch(object, forbiddenWord);
	}
	
	/**
	 * Validates if a text has any of the given forbidden words.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param forbiddenWords
	 *            Words that are forbidden in the text.
	 * @param splitRegEx
	 *            Regular expression to split the "forbidden words" string in several words.
	 * @param asSequence
	 *            If the forbidden word must be validated as a sequence of characters. If false, the word just
	 *            must not be isolated in the text (between characters that are not letters or numbers).
	 * @return If a text has any of the given forbidden words.
	 */
	public static Boolean validateForbiddenWords(final String object, final String forbiddenWords,
			final String splitRegEx, final Boolean asSequence) {
		// Gets the forbidden words from the given text.
		final String[] forbiddenWordsArray = forbiddenWords.split(splitRegEx);
		// For each forbidden word.
		for (final String currentForbiddenWord : forbiddenWordsArray) {
			// If the text has the forbidden word.
			if (!validateForbiddenWord(object, currentForbiddenWord.trim(), asSequence)) {
				// Returns false.
				return false;
			}
		}
		// If the text does not contain any of the forbidden words, returns true.
		return true;
	}
}
