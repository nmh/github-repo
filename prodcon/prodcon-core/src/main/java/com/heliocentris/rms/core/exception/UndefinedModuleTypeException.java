package com.heliocentris.rms.core.exception;

public class UndefinedModuleTypeException extends RmsException
{
   private static final long serialVersionUID = 7939633270117737942L;
   private static final String KEY = UndefinedModuleTypeException.class.getSimpleName();

   public UndefinedModuleTypeException(short moduleId)
   {
      super(KEY, moduleId);
   }
}
