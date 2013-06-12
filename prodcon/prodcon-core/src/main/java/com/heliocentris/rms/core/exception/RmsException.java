package com.heliocentris.rms.core.exception;

import com.heliocentris.rms.util.Localizer;

/**
 * 
 * @author thien
 * 
 */
public class RmsException extends com.heliocentris.rms.util.Exception
{
   private static final long serialVersionUID = 8741917273251591471L;
   private static final String KEY = RmsException.class.getSimpleName();
   protected static Localizer localizer = new Localizer(RmsException.class);

   public RmsException()
   {
      super(KEY);
   }

   public RmsException(Throwable throwable)
   {
      super(KEY, throwable);
   }

   public RmsException(String key, Object... arguments)
   {
      super(key, arguments);
   }

   public RmsException(String key, Throwable throwable, Object... arguments)
   {
      super(key, throwable, arguments);
   }

   public RmsException(String key, Throwable throwable)
   {
      super(key, throwable);
   }

}
