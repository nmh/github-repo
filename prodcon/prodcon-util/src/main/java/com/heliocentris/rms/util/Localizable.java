package com.heliocentris.rms.util;

/**
 * 
 * @organisation Heliocentris GmbH
 * @author Thien
 * @since 13.06.2013
 */
public interface Localizable {

	/**
	 * 
	 * @return a string
	 */
	String getKey();

	/**
	 * 
	 * @return object array
	 */
	Object[] getArguments();

}
