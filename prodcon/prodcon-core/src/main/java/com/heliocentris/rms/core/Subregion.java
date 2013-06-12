package com.heliocentris.rms.core;

import java.util.Set;

import javax.persistence.AssociationOverride;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.heliocentris.rms.core.util.Area;


/**
 * 
 * @author thien
 *
 */
@Entity
@Table(name=Subregion.TABLE)
public class Subregion extends Domain
{
   private static final long serialVersionUID = 7465383486612182995L;

   /**
    * Specified the table name of the entity in the backing store.
    */
   public static final String TABLE = "subregion";
   /**
    * Column name of member <code>network</code>.
    */
   public static final String NETWORK = "network_oid";
   /**
    * Column name of member <code>region</code>.
    */
   public static final String REGION = "region_oid";

   /**
    * Table name of member <code>area</code>.
    */
   public static final String AREA = "subregion_area";
   /**
    * 
    */
   public static final String SUBREGION = "subregion_oid";
   
   
   @ManyToOne(optional=false)
   @JoinColumn(name=NETWORK)
   private Network network;

   @ManyToOne(optional=false)
   @JoinColumn(name=REGION)
   private Region region;

   @OneToMany(mappedBy="subregion", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
   private Set<Site> sites;

   @Embedded
   @AssociationOverride(name="positions", joinTable=@JoinTable(name=AREA, joinColumns=@JoinColumn(name=SUBREGION)))
   Area area;
   

   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.Domain#getSites()
    */
   public Set<Site> getSites()
   {
      return sites;
   }

   /**
    * The <code>Subregion</code> is always belong to the <code>Network</code>. 
    * @return
    */
   public Network getNetwork()
   {
      return network;
   }

   public void setNetwork(Network network)
   {
      this.network = network;
   }

   /**
    * The <code>Subregion</code> is always belong to the <code>Region</code>. 
    * @return
    */
   public Region getRegion()
   {
      return region;
   }

   public void setRegion(Region region)
   {
      this.region = region;
   }
   
}
