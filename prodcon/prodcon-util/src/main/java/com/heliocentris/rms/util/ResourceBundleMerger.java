package com.heliocentris.rms.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;


/**
 * 
 * @organisation Heliocentris GmbH
 * @author Thien
 * @since 13.06.2013
 */
public class ResourceBundleMerger extends ResourceBundle
{
	protected ResourceBundle child;
	
	public ResourceBundleMerger(ResourceBundle parent, ResourceBundle child)
	{
		if (child == null) throw new NullPointerException();
		
		this.child = child;		
		this.parent = parent;
	}

	@Override
	public Enumeration<String> getKeys()
	{
		Enumeration<String> keys = child.getKeys();
			
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
		Set<String> keySet = child.keySet();
			
		if (parent != null)
		{
			keySet.addAll(parent.keySet());
		}
		return keySet;
	}
	
	@Override
	public Locale getLocale()
	{
		return child.getLocale();
	}
	
	@Override
	protected Object handleGetObject(String key) 
	{
		try
		{
			return child.getObject(key);
		}
		catch (MissingResourceException missingResourceException)
		{
			if (parent != null)
			{
				return parent.getObject(key);
			}
			throw missingResourceException;			
		}
	}
	
	@Override
	public String toString()
	{
		return parent + ", " + child;
	}	
}
