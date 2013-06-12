package com.heliocentris.rms.core.data;

import java.util.ArrayList;
import java.util.List;

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
@Table(name=HistoryHeader.TABLE)
public class HistoryHeader extends Header
{
   /**
    * 
    */
   private static final long serialVersionUID = -1982988321292904852L;
   
   /**
    * Table name of the entity.
    */
   public static final String TABLE = "history_header";
   
   @OneToMany(mappedBy="header", orphanRemoval=true, fetch=FetchType.LAZY, targetEntity=HistoryData.class)
   private List<Data> data = new ArrayList<Data>(); 
   
   
   public HistoryHeader()
   {
      
   }

   public HistoryHeader(Header header)
   {
      super(header);
   }
   
   public List<Data> getData()
   {
      return data;
   }
   
}
