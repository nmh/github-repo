package com.heliocentris.rms.core.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.heliocentris.rms.core.Site;
import com.heliocentris.rms.util.AbstractEntity;
import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;

/**
 * 
 * @author thien
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = { Header.SITE, Header.PACKAGE_ID, Header.CREATION_DATE }))
@SequenceGenerator(name = Header.SEQUENCE_NAME, sequenceName = Header.SEQUENCE_NAME, allocationSize = 1, initialValue = 1)
public abstract class Header implements Serializable
{
   /**
    * Column name of the member <code>creationDate</code>.
    */
   public static final String CREATION_DATE = "creation_date";

   /**
    * 
    */
   public static final String DATETIME_COLUMN_DEFINITION = AbstractEntity.DATETIME_COLUMN_DEFINITION;

   /**
    * Column name of the member <code>deliveryDate</code>.
    */
   public static final String DELIVERY_DATE = "delivery_date";

   private static final Logger logger = LoggerFactory.getLogger(Header.class);

   /**
    * Column name of the member <code>oid</code>.
    */
   public static final String OID = "oid";

   /**
    * Column name of the member <code>package_id</code>.
    */
   public static final String PACKAGE_ID = "package_id";

   /**
    * Column name of the member <code>segment</code>.
    */
   public static final String SEGMENT = "segment";

   /**
    * Database sequence name of the entity.
    */
   public static final String SEQUENCE_NAME = "header_oid";

   private static final long serialVersionUID = -7250061893670965661L;

   /**
    * Column name of the member <code>site</code>.
    */
   public static final String SITE = "site_oid";

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = CREATION_DATE, columnDefinition = DATETIME_COLUMN_DEFINITION, nullable = false)
   private Date creationDate;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = DELIVERY_DATE, columnDefinition = DATETIME_COLUMN_DEFINITION, nullable = false)
   private Date deliveryDate;

   @OneToMany(mappedBy = "setHeader", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
   private List<Event> events = new ArrayList<Event>();

   @Id
   @Column(name = OID)
   @GeneratedValue(generator = Header.SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
   private Long oid;

   @Column(name = PACKAGE_ID, nullable = true)
   private Long packageId;

   @Column(name = SEGMENT, nullable = true)
   private short segment;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = SITE, nullable = false)
   private Site site;

   public Header()
   {

   }

   public Header(Header header)
   {
      setOid(header.getOid());
      setSite(header.getSite());
      setSegment(header.getSegment());
      setPackageId(header.getPackageId());
      setCreationDate(header.getCreationDate());
      setDeliveryDate(header.getDeliveryDate());
   }

   public boolean addData(Data data)
   {
      logger.entering(data);

      List<Data> list = getData();
      if (!list.contains(data))
      {
         if (list.add(data))
         {
            data.setHeader(this);
            data.setSegment(getSegment());
            data.setCreationDate(getCreationDate());
            return logger.exiting(true);
         }
      }
      return logger.exiting(false);
   }

   public boolean addEvent(Event event)
   {
      if (!events.contains(event))
      {
         if (events.add(event))
         {
            event.setSetHeader(this);
            return true;
         }
      }
      return false;
   }

   /**
    * Normally <code>creationDate</code> is less or equal
    * <code>deliveryDate</code>.
    * 
    * @return
    */
   public Date getCreationDate()
   {
      return creationDate;
   }

   public abstract List<Data> getData();

   /**
    * Normally <code>deliveryDate</code> is later than<code>creationDate</code>.
    * 
    * @return
    */
   public Date getDeliveryDate()
   {
      return deliveryDate;
   }

   public List<Event> getEvents()
   {
      return events;
   }

   public Long getOid()
   {
      return oid;
   }

   /**
    * The <code>packageId</code> is the indicator, if the <code>Header</code> is
    * the LifeSignal or not.
    * 
    * @return
    */
   public Long getPackageId()
   {
      return packageId;
   }

   public short getSegment()
   {
      return segment;
   }

   public Site getSite()
   {
      return site;
   }

   /**
    * The <code>Header</code> is the <code>Header</code> of the life signal if
    * <code>packageId</code> not null.
    * 
    * @return
    */
   public boolean isLifeSignal()
   {
      return packageId != null;
   }

   public boolean removeData(Data data)
   {
      List<Data> list = getData();
      if (list.contains(data))
      {
         if (list.remove(data))
         {
            data.setHeader(null);
            return true;
         }
      }
      return false;
   }

   public boolean removeEvent(Event event)
   {
      if (events.contains(event))
      {
         if (events.remove(event))
         {
            event.setSetHeader(null);
            return true;
         }
      }
      return false;
   }

   public void setCreationDate()
   {
      setCreationDate(new Date());
   }

   public void setCreationDate(Date creationDate)
   {
      this.creationDate = creationDate;
   }

   public void setDeliveryDate()
   {
      setDeliveryDate(new Date());
   }

   public void setDeliveryDate(Date deliveryDate)
   {
      this.deliveryDate = deliveryDate;
   }

   public void setOid(Long oid)
   {
      this.oid = oid;
   }

   public void setPackageId(Long packageId)
   {
      this.packageId = packageId;
   }

   public void setSegment(short segment)
   {
      this.segment = segment;
   }

   public void setSite(Site site)
   {
      this.site = site;
   }

   @Override
   public String toString()
   {
      StringBuffer buffer = new StringBuffer(getClass().getSimpleName());
      buffer.append("[oid=").append(oid);
      buffer.append(", site=").append(site == null ? null : site.getOid());
      buffer.append(", segment=").append(segment);
      buffer.append(", packageId=").append(packageId);
      buffer.append(", creationDate=").append(creationDate);
      buffer.append(", deliveryDate=").append(deliveryDate);
      return buffer.append("]").toString();
   }

}
