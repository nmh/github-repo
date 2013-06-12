package com.heliocentris.rms.core;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.heliocentris.rms.core.data.Header;


@Entity
public abstract class Component extends Participant
{
   private static final long serialVersionUID = 9012781115064668865L;
   /**
    * Column name of the member <code>operationMode</code> in the database table.
    */
   public static final String OPERATION_MODE = "operation_mode";
   /**
    * Column name of the member <code>severity</code> in the database table.
    */
   public static final String SEVERITY = "severity";
   /**
    * Column name of the member <code>site</code> in the database table.
    */
   public static final String SITE = "site_oid";
   
   public enum Severity { Error, Warning, Info }

   public enum Scope { System, Module, Unit }

   @Column(name=OPERATION_MODE, nullable=true)
   private String operationMode;

   @Column(name=SEVERITY, nullable=false)
   private Severity severity = Severity.Info;
   
   public String getOperationMode()
   {
      return operationMode;
   }

   public void setOperationMode(String operationMode)
   {
      this.operationMode = operationMode;
   }

   public Severity getSeverity()
   {
      return severity;
   }

   public void setSeverity(Severity severity)
   {
      this.severity = severity;
   }

   public abstract Scope getScope();
   
   public abstract Site getSite();

   public abstract void setSite(Site site);

   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(super.toString());
      buffer.deleteCharAt(buffer.length() - 1);
      buffer.append(", severity=").append(severity);      
      buffer.append(", operationMode=").append(operationMode);      
      return buffer.append("]").toString();
   }
 
   public void process(Header header)
   {
      throw new UnsupportedOperationException("not yet implemented");      
   }
   
}
