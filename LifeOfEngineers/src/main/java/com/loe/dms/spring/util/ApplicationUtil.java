/*
 * Copyright © Foremost Insurance 2010

 * All Rights Reserved
 *
 * Foremost NSS Application.
 *
 * Created:  Dec 16, 2010 3:08:29 PM
 * Author:   usw4w72
 * Project:  nssEJB
 *
 * $LastChangedBy$ usw4w72
 * $LastChangedRevision$
 * $LastChangedDate$ Dec 15, 2010 11:08:29 AM
 */
package com.loe.dms.spring.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the Utility Class used for data validation and conversion. And
 * setting the default data to the entities.
 * 
 */
public final class ApplicationUtil implements ApplicationConstants {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationUtil.class);

	/**
	 * This method returns true if the input array is null or length is zero.
	 * 
	 * @param array
	 * @return boolean
	 */
	public static boolean isObjectArrayEmpty(Object[] array) {
		if (array == null || array.length == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * This method returns true if the array is not null and length is greater
	 * than zero.
	 * 
	 * @param array
	 * @return boolean
	 */
	public static boolean isObjectArrayNotEmpty(Object[] array) {
		if (array != null && array.length > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * This method returns true if the input list is null or list is empty.
	 * 
	 * @param list
	 * @return boolean.
	 */
	public static boolean isObjectListEmpty(List list) {
		return (list == null || list.isEmpty());
	}

	/**
	 * This method returns true if the input list is not null and list is not
	 * empty.
	 * 
	 * @param list
	 * @return boolean.
	 */
	public static boolean isObjectListNotEmpty(List list) {
		return (list != null && !list.isEmpty());
	}

	/**
	 * This method returns true if the input map is null or empty.
	 * 
	 * @param map
	 * @return boolean
	 */
	public static boolean isObjectMapEmpty(Map map) {
		return (map == null || map.isEmpty());
	}

	/**
	 * This method return true if the two input string values of type number are
	 * same.
	 * 
	 * @param val1
	 * @param val2
	 * @return boolean
	 */
	public static boolean compareStringNumerics(String val1, String val2) {
		int num1 = 0;
		int num2 = 0;
		try {
			num1 = Integer.parseInt(val1);
			num2 = Integer.parseInt(val2);
		} catch (Exception ne) {
			return false;
		}
		if (num1 == num2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method returns null for an empty input string for a numeric field.
	 * 
	 * @return String
	 * @param field
	 */
	public static String convertEmptyStringToNull(String field) {
		if (field == null) {
			return null;
		} else {
			if ((field.trim().length() == 0) || (field.equals(""))) {
				field = null;
			}
		}
		return field;
	}

	/**
	 * This method returns double value for the input string numeric value.
	 * 
	 * @param field
	 * @return double
	 */
	public static double getBigNumeric(String field) {
		if (isBigNumeric(field)) {
			return new Double(field).doubleValue();
		} else {
			return 0.0;
		}
	}

	/**
	 * This method returns the trimmed String value for input string.
	 * 
	 * @param value
	 * @return String
	 */
	public static String getTrimValue(String value) {
		if (value != null)
			return value.trim();
		else
			return "";
	}

	/**
	 * This method returns true if the input string value is of the BigNumeric
	 * value.
	 * 
	 * @return boolean
	 * @param field
	 */
	public static boolean isBigNumeric(String field) {
		if (field == null) {
			return true;
		}
		try {
			new Double(field);
			return true;
		} catch (Exception nfe) {
			return false;
		}
	}

	/**
	 * This method returns the array from the input String and parsed based on
	 * the new line.
	 * 
	 * @param str
	 * @return List<String>
	 */
	public static List<String> parseString(String str) {
		List<String> parsedTokens = new ArrayList<String>();
		try {
			StringTokenizer st = new StringTokenizer(str, "\n");
			List<String> tokens = new ArrayList<String>();
			while (st.hasMoreTokens()) {
				tokenizeString(st.nextToken(), 65, tokens);
			}
			for (int i = 0; i < tokens.size(); i++) {
			}
			parsedTokens = tokens;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return parsedTokens;
	}

	/**
	 * The method breaks the input string into tokens.The method is used by
	 * parseString method.
	 * 
	 * @param str
	 * @param tokenLength
	 * @param tokens
	 * @throws Exception
	 */
	private static void tokenizeString(String str, int tokenLength, List tokens) throws Exception {
		if (str == null) {
			return;
		}
		int length = str.length();
		if (length <= tokenLength) {
			tokens.add(str);
			return;
		}
		StringBuffer strBuf = new StringBuffer(str);
		int startIndex = 0;
		int endIndex = tokenLength;

		while (length > endIndex) {
			String token = strBuf.substring(startIndex, endIndex);
			int lastSpaceIndex = token.lastIndexOf(" ");

			if ((lastSpaceIndex == -1) || endsWithWord(strBuf, endIndex, lastSpaceIndex)) {
				// Dont do any thing the whole string is a word or ends with a
				// word.
				startIndex += tokenLength;
			} else {
				token = token.substring(0, lastSpaceIndex + 1);
				startIndex += (lastSpaceIndex + 1);
			}

			tokens.add(token);
			endIndex = startIndex + tokenLength;
		}
		if (startIndex < length) {
			tokens.add(str.substring(startIndex));
		}
	}

	/**
	 * This method checks for the end Index value and returns true if it
	 * matches.
	 * 
	 * @param strBuf
	 * @param endIndex
	 * @param lastSpaceIndex
	 * @return boolean
	 */
	private static boolean endsWithWord(StringBuffer strBuf, int endIndex, int lastSpaceIndex) {
		return ((lastSpaceIndex == (endIndex - 1)) || (strBuf.charAt(endIndex) == ' '));
	}

	/**
	 * This method sets the Default Data to all the columns of the JPA Entities
	 * and will be invoked from the AbstractBaseentity Constructor
	 * 
	 * @param obj
	 */
	public static void setDefaultValues(Object obj) {
		try {
			Method[] methods = obj.getClass().getMethods();
			for (Method method : methods) {
				try {
					if (isSetter(method)) {
						String paramType = method.getParameterTypes()[0].toString();
						if (paramType.endsWith("String"))
							method.invoke(obj, "");
						else if (paramType.endsWith("BigDecimal"))
							method.invoke(obj, new BigDecimal(0));
						else if (paramType.endsWith("short"))
							method.invoke(obj, new Short((short) 0));
						else if (paramType.endsWith("Date"))
							method.invoke(obj, DEFAULT_DB_DATE);
						else if (paramType.endsWith("Timestamp"))
							method.invoke(obj, DEFAULT_DB_TIMESTAMP);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean compareValues(Object obj1, Object oject2) {
		boolean isDataChanged = false;
		try {
			Method[] methods = obj1.getClass().getMethods();
			Object value1 = null;
			Object value2 = null;
			String returnType = null;
			for (Method method : methods) {
				try {
					if (isGetter(method)) {
						returnType = String.valueOf(method.getReturnType());
						if (returnType != null && !"getCtlCategory".equalsIgnoreCase(method.getName()) && (returnType.endsWith("String") || returnType.endsWith("BigDecimal") || returnType.endsWith("short") || returnType.endsWith("Date"))) {
							value1 = method.invoke(obj1, new Object[0]);
							value2 = method.invoke(oject2, new Object[0]);
							if (value1 != null && value2 != null && !value1.equals(value2)) {
								logger.debug(":::::::::::::::::::::::::::::::::::::::::::::");
								logger.debug("obj1 ::: " + method.getName() + " ::: " + value1 + " ::: obj2 ::: " + method.getName() + " : " + value2);
								logger.debug(":::::::::::::::::::::::::::::::::::::::::::::");
								isDataChanged = true;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDataChanged;
	}

	/**
	 * This method returns true if the input method is of the type setter.
	 * 
	 * @param method
	 * @return boolean
	 */
	public static boolean isSetter(Method method) {
		if (!method.getName().startsWith("set"))
			return false;
		if (method.getParameterTypes().length != 1)
			return false;
		return true;
	}

	/**
	 * This method returns true if the input method is of the type getter.
	 * 
	 * @param method
	 * @return boolean
	 */
	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get"))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		return true;
	}

	/**
	 * This method appends the spaces to the max length specified for the input
	 * string.
	 * 
	 * @param str
	 * @param maxLength
	 * @return String
	 */
	public static String appendSpaces(String str, int maxLength) {
		StringBuffer strBuf = new StringBuffer(str);
		for (int i = str.length(); i < maxLength; i++) {
			strBuf.append(" ");
		}
		return strBuf.toString();
	}

	/**
	 * This method will be invoked only to trim the trailing spaces but still
	 * should retain the leading spaces.
	 * 
	 * @param str
	 * @return
	 */
	public static String trimTrailingSpaceOnly(String str) {
		if (str == null) {
			return "";
		} else {
			str = str.replaceAll("\\s+$", "");
		}
		return str;
	}

}