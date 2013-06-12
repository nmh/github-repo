package com.heliocentris.rms.core.action;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.heliocentris.rms.core.Participant;

@Entity
@Table(name=ParticipantAction.TABLE)
public class ParticipantAction extends UserAction<Participant>
{
   private static final long serialVersionUID = 3643399658405508174L;
   

   /**
    * Column name of the member <code>creationDate</code>.
    */
   public static final String CREATION_DATE = "creation_date";

   /**
    * 
    */
   public static final String TABLE = "participant_action";

   /**
    * 
    */
   public static final String SUBJECT = "participant_oid";
   
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name=CREATION_DATE, columnDefinition=DATETIME_COLUMN_DEFINITION, nullable=false)
   private Date creationDate;
   
   @ManyToOne
   @JoinColumn(name=SUBJECT, nullable=false)
   private Participant subject;
   
   public Participant getSubject()
   {
      return subject;
   }

   public void setSubject(Participant subject)
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