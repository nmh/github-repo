package com.heliocentris.rms.core.data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.heliocentris.rms.core.Component;

@Entity
@Table(name=CurrentData.TABLE)
public class CurrentData extends Data
{
   private static final long serialVersionUID = 6202653876558208262L;
   
   /**
    * Table name of the member <code>ModuleType</code> in the database table.
    */
   public static final String TABLE = "current_data";
   
   @ManyToOne(fetch=FetchType.LAZY, targetEntity=CurrentHeader.class)
   @JoinColumn(name=HEADER, nullable=false)
   private Header header;
   

   public CurrentData()
   {
   }

   public CurrentData(Component component, int dataTypeId, Boolean value)
   {
      super(component, dataTypeId, value);
   }

   public CurrentData(Component component, int dataTypeId, Byte value)
   {
      super(component, dataTypeId, value);
   }

   public CurrentData(Component component, int dataTypeId, Double value)
   {
      super(component, dataTypeId, value);
   }
   
   public CurrentData(Component component, int dataTypeId, Float value)
   {
      super(component, dataTypeId, value);
   }
   
   public CurrentData(Component component, int dataTypeId, Integer value)
   {
      super(component, dataTypeId, value);
   }

   public CurrentData(Component component, int dataTypeId, Long value)
   {
      super(component, dataTypeId, value);
   }

   public CurrentData(Component component, int dataTypeId, Object value)
   {
      super(component, dataTypeId, value);
   }

   public CurrentData(Component component, int dataTypeId, Short value)
   {
      super(component, dataTypeId, value);
   }

   public CurrentData(Component component, int dataTypeId, String value)
   {
      super(component, dataTypeId, value);
   }

   public CurrentData(Data data)
   {
      super(data);
   }

   public CurrentData(int dataTypeId, Boolean value)
   {
      super(dataTypeId, value);
   }

   public CurrentData(int dataTypeId, Byte value)
   {
      super(dataTypeId, value);
   }

   public CurrentData(int dataTypeId, Double value)
   {
      super(dataTypeId, value);
   }
   
   public CurrentData(int dataTypeId, Float value)
   {
      super(dataTypeId, value);
   }

   public CurrentData(int dataTypeId, Integer value)
   {
      super(dataTypeId, value);
   }

   public CurrentData(int dataTypeId, Long value)
   {
      super(dataTypeId, value);
   }

   public CurrentData(int dataTypeId, Object value)
   {
      super(dataTypeId, value);
   }

   public CurrentData(int dataTypeId, Short value)
   {
      super(dataTypeId, value);
   }

   public CurrentData(int dataTypeId, String value)
   {
      super(dataTypeId, value);
   }

   public Header getHeader()
   {
      return header;
   }

   public void setHeader(Header header)
   {
      this.header = header;
   }
   
}
