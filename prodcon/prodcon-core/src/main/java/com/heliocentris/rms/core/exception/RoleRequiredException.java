package com.heliocentris.rms.core.exception;

public class RoleRequiredException extends RmsException
{
   private static final long serialVersionUID = -283942777079671283L;
   private static final String KEY = RoleRequiredException.class.getSimpleName();

   public RoleRequiredException(String role)
   {
      super(KEY, role);
   }
}
