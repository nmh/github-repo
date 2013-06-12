package com.heliocentris.rms.util;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;



/**
 * 
 * @author Nguyen, Tuan-Thien
 * @version $Revision$
 * @since JDK 1.5.0
 */
public class ResourceBundle 
{
	private static final Logger logger = LoggerFactory.getLogger(ResourceBundle.class);
	
	/**
	 * Delimiters for use in array
	 */
	public static final String DELIMITERS = " []{}<>()";
	
	/**
	 * The parent key for the parent resource bundle
	 */
	public static final String PARENT_KEY = "resourceBundle.parent";

	private ResourceBundle parent;
	
	private String name;
	private static Locale locale = Locale.getDefault();
	
	/**
	 * 
	 * @param classes
	 * @return
	 */
	public static ResourceBundle getBundle(Class<?>... classes)
	{
		return getBundle(Locale.getDefault(), null, classes);
	}

	/**
	 * 
	 * @param parent
	 * @param classes
	 * @return
	 */
	public static ResourceBundle getBundle(ResourceBundle parent, Class<?>... classes)
	{
		return getBundle(Locale.getDefault(), parent, classes);
	}
	
	/**
	 * 
	 * @param parent
	 * @param classes
	 * @return
	 */
	public static ResourceBundle getBundle(Locale locale, ResourceBundle parent, Class<?>... classes)
	{
		ResourceBundle bundle = parent;
		for (Class<?> aClass : classes)
		{
			bundle = getBundle(locale, bundle, aClass.getName());
		}
		return bundle;
	}

	/**
	 * 
	 * @param names
	 * @return
	 */
	public static ResourceBundle getBundle(String... names)
	{
		return getBundle(Locale.getDefault(), null, names);
	}

	/**
	 * 
	 * @param names
	 * @return
	 */
	public static ResourceBundle getBundle(Locale locale, String... names)
	{
		return getBundle(locale, null, names);
	}

	/**
	 * 
	 * @param parent
	 * @param names
	 * @return
	 */
	public static ResourceBundle getBundle(ResourceBundle parent, String... names)
	{
		return getBundle(Locale.getDefault(), null, names);
	}
	
	/**
	 * 
	 * @param parent
	 * @param names
	 * @return
	 */
	public static ResourceBundle getBundle(Locale locale, ResourceBundle parent, String... names)
	{
		ResourceBundle bundle = parent;
		for (String name : names)
		{
			bundle = new ResourceBundle(locale, bundle, name);
		}
		return bundle;
	}
	
	protected ResourceBundle(Locale locale, ResourceBundle parent, String name)
	{
			logger.entering(locale, parent, name);
			
			java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(name, locale);
			
			if (parent == null)
			{
				try
				{
	                String parentName = bundle.getString(PARENT_KEY);
					if (parentName != null && !parentName.isEmpty())
					{
					    parent = new ResourceBundle(locale, null, parentName);
					}
				}
				catch (MissingResourceException missingResourceException) 
				{
					logger.info("No parent bundle specified in the child bundle");
				}
			}
			
			this.name = name;
			this.parent = parent;
			//this.locale = bundle.getLocale();
			logger.exiting();
	}

	/**
	 * Provide compatibility to <code>java.util.ResourceBundle</code>.
	 * 
	 * @return java.util.ResourceBundle
	 */
	public java.util.ResourceBundle getBundle()
	{
		return getBundle(locale);
	}

	/**
	 * Provide compatibility to <code>java.util.ResourceBundle</code>.
	 * 
	 * @param locale
	 * @return java.util.ResourceBundle
	 */
	public java.util.ResourceBundle getBundle(Locale locale)
	{
		   java.util.ResourceBundle bundle = null;
			logger.entering(locale);

			bundle = java.util.ResourceBundle.getBundle(name, locale);
			if (parent != null)
			{
				bundle = new Merger(parent.getBundle(locale));
			}
			return logger.exiting(bundle);
	}

	public static void clearCache()
	{
		java.util.ResourceBundle.clearCache();
	}
	
	/**
	 * 
	 * @return
	 */
	public Enumeration<String> getKeys()
	{
		Enumeration<String> keys = java.util.ResourceBundle.getBundle(name, locale).getKeys();
		
		if (parent != null)
		{
			TreeSet<String> set = new TreeSet<String>();
			set.addAll(Collections.list(keys));
			set.addAll(Collections.list(parent.getKeys()));
			keys = Collections.enumeration(set);
		}
		return keys;
	}

	public Set<String> keySet()
	{
		Set<String> keySet = java.util.ResourceBundle.getBundle(name, locale).keySet();
		
		if (parent != null)
		{
			keySet.addAll(parent.keySet());
		}
		return keySet;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * 
	 * @return
	 */
	public Locale getLocale()
	{
		return locale;
	}

//	/**
//	 * Set the location for the specific resource bundle.
//	 * It should not affect the locale of the same resource. 
//	 * This is the multi-threading capacity of the resource bunde.
//	 * 
//	 * @param locale
//	 */
//	public void setLocale(Locale locale)
//	{
//		try
//		{
//            // Locale.setDefault(locale); // not the best solution, cause not multi-threading
//
//            this.locale = java.util.ResourceBundle.getBundle(name, locale).getLocale();
//
//            if (parent != null)
//			{
//				parent.setLocale(locale);
//			}
//		}
//		finally
//		{
//			logger.exiting();
//		}
//	}

	public static void setLocale(Locale locale)
	{
		ResourceBundle.locale = locale;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Object getObject(String key)
	{
		logger.entering(key);
		return logger.exiting(handleGetObject(key));
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public char getChar(String key)
	{
		char value = Character.UNASSIGNED;
		logger.entering(key);
		value = getString(key).charAt(0);
		return logger.exiting(value);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public char getChar(String key, char value)
	{
		try
		{
			logger.entering(key, value);
			value = getString(key).charAt(0);
		}
		catch (MissingResourceException missingResourceException)
		{
		}
		return logger.exiting(value);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key)
	{
		logger.entering(key);
      return logger.exiting((String)getObject(key));
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String getString(String key, String value)
	{
		try
		{
			logger.entering(key, value);
         value = getString(key);
		}
		catch (MissingResourceException missingResourceException)
		{
		}
		return logger.exiting(value);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String[] getStringArray(String key)
	{
		String [] values = new String[] {};
		logger.entering(key);
			
		ArrayList<String> arrayList = new ArrayList<String>();
			
		try
		{
			StringTokenizer tokenizer = new StringTokenizer(getString(key), DELIMITERS);
			while (tokenizer.hasMoreTokens())
			{
				arrayList.add(tokenizer.nextToken());
			}
		}
		catch (MissingResourceException exception)
		{
			for (int i = 0; i < Integer.MAX_VALUE; i++)
			{
				try
				{
					arrayList.add(getString(key + "[" + i + "]"));
				}
				catch (MissingResourceException missingResourceException)
				{
					if (i == 0) throw missingResourceException;
					break;
				}
			}
		}
		return logger.exiting(values = arrayList.toArray(values));
	}

	/**
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public String[] getStringArray(String key, String... values)
	{
		try
		{
			logger.entering(key);
			values = getStringArray(key);
		}
		catch (MissingResourceException missingResourceException)
		{
		}
		return logger.exiting(values);
	}
	
	/**
	 * 
	 * @param key
	 * @return true or false
	 */
	public boolean getBoolean(String key)
	{
		logger.entering(key);
		return logger.exiting(Boolean.parseBoolean(getString(key)));
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean getBoolean(String key, boolean value)
	{
		try
		{
			logger.entering(key, value);
	      value = Boolean.parseBoolean(getString(key));
		}
		catch (MissingResourceException missingResourceException)
		{
		}
		return logger.exiting(value);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(String key)
	{
      logger.entering(key);
 	   return logger.exiting(Integer.parseInt(getString(key)));
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public int getInt(String key, int value)
	{
		try
		{
			logger.entering(key, value);
			value = Integer.parseInt(getString(key));
		}
		catch (MissingResourceException missingResourceException)
		{
		}
		return logger.exiting(value);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public long getLong(String key)
	{
		logger.entering(key);
		return logger.exiting(Long.parseLong(getString(key)));
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public long getLong(String key, long value)
	{
		try
		{
			logger.entering(key, value);
			value = Long.parseLong(getString(key));
		}
		catch (MissingResourceException missingResourceException)
		{
		}
		return logger.exiting(value);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public float getFloat(String key)
	{
			logger.entering(key);
			return logger.exiting(Float.parseFloat(getString(key)));
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return a float
	 */
	public float getFloat(String key, float value)
	{
		try
		{
			logger.entering(key, value);
			value = Float.parseFloat(getString(key));
		}
		catch (MissingResourceException missingResourceException)
		{
		}
		return logger.exiting(value);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public double getDouble(String key)
	{
			logger.entering(key);
			return logger.exiting(Double.parseDouble(getString(key)));
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public double getDouble(String key, double value)
	{
		try
		{
			logger.entering(key, value);
			value = Double.parseDouble(getString(key));
		}
		catch (MissingResourceException missingResourceException)
		{
		}
		return logger.exiting(value);
	}
	
	/**
	 * 
	 * @param key
	 * @return Locale
	 */
	public Locale getLocale(String key)
	{
		logger.entering(key);
		return logger.exiting(new Locale(getString(key)));
	}
	
    /**
     * 
     * @param key
     * @return Color
     */
    public Color getColor(String key)
    {
         logger.entering(key);
         
         StringTokenizer tokenizer = new StringTokenizer(getString(key), DELIMITERS);

         int r = Integer.parseInt(tokenizer.nextToken());
         int g = Integer.parseInt(tokenizer.nextToken());
         int b = Integer.parseInt(tokenizer.nextToken());
         
         return logger.exiting(new Color(r, g, b));
    }

    /**
     * 
     * @param key
     * @return Cursor
     */
    public Cursor getCursor(String key)
    {
        try
        {
            logger.entering(key);
            return logger.exiting(Cursor.getSystemCustomCursor(getString(key)));
        }
        catch (AWTException awtException)
        {
            throw new MissingResourceException(awtException.getLocalizedMessage(), name, key);
        }
    }
    
    /**
     * 
     * @param key
     * @return Dimension
     */
    public Dimension getDimension(String key)
    {
         logger.entering(key);
         
         StringTokenizer tokenizer = new StringTokenizer(getString(key), DELIMITERS);

         int width  = Integer.parseInt(tokenizer.nextToken());
         int height = Integer.parseInt(tokenizer.nextToken());
         
         return logger.exiting(new Dimension(width, height));
    }

    /**
     * 
     * @param key
     * @return Font
     */
    public Font getFont(String key)
    {
       logger.entering(key);
       return logger.exiting(Font.decode(getString(key)));
    }

    /**
     * 
     * @param key
     * @return Image
     */
    public Image getImage(String key)
    {
       logger.entering(key);
       return logger.exiting(Toolkit.getDefaultToolkit().getImage(getString(key)));
    }

    /**
     * 
     * @param key
     * @return Insets
     */
    public Insets getInsets(String key)
    {
       logger.entering(key);
      
       StringTokenizer tokenizer = new StringTokenizer(getString(key), DELIMITERS);
      
       int bottom = Integer.parseInt(tokenizer.nextToken());
       int left   = Integer.parseInt(tokenizer.nextToken());
       int right  = Integer.parseInt(tokenizer.nextToken());
       int top    = Integer.parseInt(tokenizer.nextToken());
      
       return logger.exiting(new Insets(bottom, left, right, top));
    }

    /**
     * 
     * @param key
     * @return Point
     */
    public Point getPoint(String key)
    {
       logger.entering(key);
      
       StringTokenizer tokenizer = new StringTokenizer(getString(key), DELIMITERS);
      
       int x = Integer.parseInt(tokenizer.nextToken());
       int y = Integer.parseInt(tokenizer.nextToken());
      
       return logger.exiting(new Point(x, y));
    }
    
    /**
     * 
     * @param key
     * @return Rectangle
     */
    public Rectangle getRectangle(String key)
    {
        logger.entering(key);
         
        StringTokenizer tokenizer = new StringTokenizer(getString(key), DELIMITERS);
         
        int x = Integer.parseInt(tokenizer.nextToken());
        int y = Integer.parseInt(tokenizer.nextToken());
         
        int width  = Integer.parseInt(tokenizer.nextToken());
        int height = Integer.parseInt(tokenizer.nextToken());
         
        return logger.exiting(new Rectangle(x, y, width, height));
    }
    
    /**
     * 
     * @param key
     * @return ImageIcon
     */
    public ImageIcon getImageIcon(String key)
    {
        logger.entering(key);
        return logger.exiting(new ImageIcon(getString(key)));
    }
    
    /**
     * 
     * @param key
     * @return KeyStroke
     */
    public KeyStroke getKeyStroke(String key)
    {
       logger.entering(key);
       return logger.exiting(KeyStroke.getKeyStroke(getString(key)));
    }

    
    protected Object handleGetObject(String key) 
    {
    	try
    	{
    		return java.util.ResourceBundle.getBundle(name, locale).getObject(key);
    	}
    	catch (MissingResourceException missingResourceException)
    	{
    		if (parent != null)
    		{
    			return parent.handleGetObject(key);
    		}
    		throw logger.throwing(missingResourceException);    		
    	}
    }

	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		if (parent == null)
		{
			buffer.append(super.toString()).append("[");
		}
		else
		{
			buffer.append(parent).deleteCharAt(buffer.length() - 1).append(",");
		}
		buffer.append(name);
		
		return buffer.append("]").toString();
	}
	
	/**
	 * 
	 * @author thien
	 *
	 */
	public class Merger extends java.util.ResourceBundle
	{
		public Merger(java.util.ResourceBundle parent)
		{
			if (parent == null) throw new NullPointerException();
			
			this.parent = parent;			
		}

		@Override
		public Enumeration<String> getKeys()
		{
			Enumeration<String> keys = java.util.ResourceBundle.getBundle(name, locale).getKeys();
				
			if (parent != null)
			{
				TreeSet<String> set = new TreeSet<String>();
				set.addAll(Collections.list(keys));
				set.addAll(Collections.list(parent.getKeys()));
				keys = Collections.enumeration(set);
			}
			return keys;
		}

		@Override
		public Set<String> keySet()
		{
			Set<String> keySet = java.util.ResourceBundle.getBundle(name, locale).keySet();
				
			if (parent != null)
			{
				keySet.addAll(parent.keySet());
			}
			return keySet;
		}
		
		@Override
		public Locale getLocale()
		{
			return java.util.ResourceBundle.getBundle(name, locale).getLocale();
		}
		
		@Override
		protected Object handleGetObject(String key) 
		{
			try
			{
				return java.util.ResourceBundle.getBundle(name, locale).getObject(key);
			}
			catch (MissingResourceException missingResourceException)
			{
				if (parent == null)
				{
					throw missingResourceException;
				}
				return parent.getObject(key);
			}
		}
		
		@Override
		public String toString()
		{
			return parent + ", " + java.util.ResourceBundle.getBundle(name, locale);
		}	
	}
}
