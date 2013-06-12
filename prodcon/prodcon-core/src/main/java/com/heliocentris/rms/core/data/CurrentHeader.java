package com.heliocentris.rms.core.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author thien
 *
 */
@Entity
@Table(name=CurrentHeader.TABLE)
public class CurrentHeader extends Header
{
   /**
    * 
    */
   private static final long serialVersionUID = -1982988321292904852L;

   /**
    * Table name of the entity.
    */
   public static final String TABLE = "current_header";
   
   @OneToMany(mappedBy="header", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY, targetEntity=CurrentData.class)
   private List<Data> data = new ArrayList<Data>(); 

   public CurrentHeader()
   {
      
   }  
   
   /**
    * Clone the give header and all his properties
    * 
    * @param header
    */
   public CurrentHeader(Header header)
   {
      super(header);
      
      for (Data data : header.getData())
      {
         addData(new CurrentData(data));
      }
      for (Event event : header.getEvents())
      {
         addEvent(event);
      }
   }
   
   public List<Data> getData()
   {
      return data;
   }
}
 