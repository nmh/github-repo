package com.heliocentris.rms.util;

import java.io.Serializable;

import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;

/**
 * The <code>Exception</code> is the basic class for all exceptions.
 * 
 * @author thien
 * 
 */
public class Exception extends RuntimeException implements Localizable, Serializable
{
   private static final long serialVersionUID = -1723821116798094189L;
   private static final Logger logger = LoggerFactory.getLogger(Exception.class);

   private static final Object[] EMPTY_ARGS = new Object[] {};
   private static final String KEY = Exception.class.getSimpleName();

   protected static Localizer localizer = new Localizer(Exception.class);

   private Object[] arguments = EMPTY_ARGS;

   /**
	 * 
	 */
   public Exception()
   {
      super(KEY);
   }

   /**
    * Construct Exception with caused throwable.
    * 
    * @param throwable
    */
   public Exception(Throwable throwable)
   {
      super(KEY, throwable);
   }

   /**
    * Protected constructor for inheritance of the Exception.
    * 
    * @param key
    * @param throwable
    */
   protected Exception(String key, Throwable throwable)
   {
      super(key, throwable);
   }

   /**
    * Protected constructor for inheritance of the Exception.
    * 
    * @param key
    * @param throwable
    */
   protected Exception(String key, Object... arguments)
   {
      super(key);
      try
      {
         logger.entering(key, arguments);

         if (arguments != null)
         {
            this.arguments = arguments;
         }
      } finally
      {
         logger.exiting();
      }
   }

   protected Exception(String key, Throwable throwable, Object... arguments)
   {
      super(key, throwable);
      logger.entering(key, arguments);
      if (arguments != null)
      {
         this.arguments = arguments;
      }
      logger.exiting();
   }

   public String getKey()
   {
      return getMessage();
   }

   public Object[] getArguments()
   {
      return arguments;
   }

   @Override
   public String getLocalizedMessage()
   {
      return getLocalizer().getMessage(this);
   }

   public Localizer getLocalizer()
   {
      return Localizer.getLocalizer(getClass());
   }
}
