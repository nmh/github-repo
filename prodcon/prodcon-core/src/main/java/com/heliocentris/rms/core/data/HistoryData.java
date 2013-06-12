package com.heliocentris.rms.core.data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.heliocentris.rms.core.Component;


@Entity
@Table(name=HistoryData.TABLE)
public class HistoryData extends Data 
{
   private static final long serialVersionUID = 877069633527272349L;
   
   /**
    * Table name of the entity <code>HistoryData</code> in the database table.
    */
   public static final String TABLE = "history_data";
   
   
   @ManyToOne(fetch=FetchType.LAZY, targetEntity=HistoryHeader.class)
   @JoinColumn(name=HEADER, nullable=false)
   private Header header;
   
   
   public HistoryData()
   {
   }
   
   public HistoryData(Data data)
   {
      super(data);
   }

   public HistoryData(int dataTypeId, Object value)
   {
      super(dataTypeId, value);
   }
   
   public HistoryData(int dataTypeId, Boolean value)
   {
      super(dataTypeId, value);
   }
   
   public HistoryData(int dataTypeId, Byte value)
   {
      super(dataTypeId, value);
   }
   
   public HistoryData(int dataTypeId, Double value)
   {
      super(dataTypeId, value);
   }

   public HistoryData(int dataTypeId, Float value)
   {
      super(dataTypeId, value);
   }

   public HistoryData(int dataTypeId, Integer value)
   {
      super(dataTypeId, value);
   }

   public HistoryData(int dataTypeId, Long value)
   {
      super(dataTypeId, value);
   }

   public HistoryData(int dataTypeId, Short value)
   {
      super(dataTypeId, value);
   }

   public HistoryData(int dataTypeId, String value)
   {
      super(dataTypeId, value);
   }

   public HistoryData(Component component, int dataTypeId, Boolean value)
   {
      super(component, dataTypeId, value);
   }

   public HistoryData(Component component, int dataTypeId, Byte value)
   {
      super(component, dataTypeId, value);
   }

   public HistoryData(Component component, int dataTypeId, Double value)
   {
      super(component, dataTypeId, value);
   }
   
   public HistoryData(Component component, int dataTypeId, Float value)
   {
      super(component, dataTypeId, value);
   }
   
   public HistoryData(Component component, int dataTypeId, Integer value)
   {
      super(component, dataTypeId, value);
   }

   public HistoryData(Component component, int dataTypeId, Long value)
   {
      super(component, dataTypeId, value);
   }

   public HistoryData(Component component, int dataTypeId, Object value)
   {
      super(component, dataTypeId, value);
   }

   public HistoryData(Component component, int dataTypeId, Short value)
   {
      super(component, dataTypeId, value);
   }

   public HistoryData(Component component, int dataTypeId, String value)
   {
      super(component, dataTypeId, value);
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
