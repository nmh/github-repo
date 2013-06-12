package com.heliocentris.rms.core.action;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

import com.heliocentris.rms.util.AbstractEntity;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = Action.SEQUENCE_NAME, sequenceName = Action.SEQUENCE_NAME, allocationSize = 1, initialValue = 1)
public abstract class Action<T> implements Serializable
{
   private static final long serialVersionUID = 6205335747361220295L;
   
   /**
    * 
    */
   public static final String DATETIME_COLUMN_DEFINITION = AbstractEntity.DATETIME_COLUMN_DEFINITION;
   
   public static final String SEQUENCE_NAME = "action_oid";
   /**
    * Column name of the member <code>oid</code> in the database table.
    */
   public static final String OID = "oid";

   /**
    * Column name of the member <code>subject</code> in the database table.
    */
   public static final String SUBJECT = "subject";

   /**
    * Column name of the member <code>name</code> in the database table.
    */
   public static final String NAME = "name";

   /**
    * Column name of the member <code>comment</code> in the database table.
    */
   public static final String COMMENT = "comment_";
   
   @Id
   @GeneratedValue(generator=SEQUENCE_NAME, strategy=GenerationType.SEQUENCE)
   @Column(name=OID)
   private Long oid;

   @Column(name=NAME, nullable=true)
   private String name;
   
   @Column(name=COMMENT, nullable=true)
   private String comment;

   public abstract Date getCreationDate();

   public abstract T getSubject();

   public abstract void setSubject(T subject);

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getComment()
   {
      return comment;
   }

   public void setComment(String comment)
   {
      this.comment = comment;
   }
}
