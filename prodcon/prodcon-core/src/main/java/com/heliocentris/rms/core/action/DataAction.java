package com.heliocentris.rms.core.action;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.heliocentris.rms.core.data.HistoryData;

@Entity
@Table(name=DataAction.TABLE)
public class DataAction extends UserAction<HistoryData>
{
   private static final long serialVersionUID = -7494284473042339862L;

   /**
    * 
    */
   public static final String TABLE = "data_action";
   
   /**
    * 
    */
   public static final String SUBJECT = "history_data_oid";
   

   @ManyToOne
   @JoinColumn(name=SUBJECT, nullable=false)
   private HistoryData subject;
   
   @Override
   public Date getCreationDate()
   {
      return getSubject().getCreationDate();
   }

   @Override
   public HistoryData getSubject()
   {
      return subject;
   }

   @Override
   public void setSubject(HistoryData subject)
   {
      this.subject = subject;
   }

}
