package com.heliocentris.rms.core.action;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.heliocentris.rms.core.data.Event;

@Entity
@Table(name=EventAction.TABLE)
public class EventAction extends UserAction<Event>
{
   private static final long serialVersionUID = -7083672550726652870L;
   
   /**
    * Column name of the member <code>creationDate</code>.
    */
   public static final String CREATION_DATE = "creation_date";
   
   /**
    * 
    */
   public static final String TABLE = "event_action";
   
   /**
    * 
    */
   public static final String SUBJECT = "event_oid";

   @ManyToOne
   @JoinColumn(name=SUBJECT, nullable=false)
   private Event subject;
   
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name=CREATION_DATE, columnDefinition=DATETIME_COLUMN_DEFINITION, nullable=false)
   private Date creationDate;
   
   public Event getSubject()
   {
      return subject;
   }

   public void setSubject(Event subject)
   {
      this.subject = subject;
   }

   public Date getCreationDate()
   {
      return creationDate;
   }

   public void setCreationDate(Date creationDate)
   {
      this.creationDate = creationDate;
   }
   
}
