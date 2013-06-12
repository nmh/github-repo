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


@Entity
@Table(name=Region.TABLE)
public class Region extends Domain
{
   private static final long serialVersionUID = -772343194731125462L;
   
   /**
    * 
    */
   public static final String NETWORK = "network_oid";

   /**
    * Specified the table name of the entity in the backing store.
    */
   public static final String TABLE = "region";
   
   /**
    * Table name of member <code>area</code>.
    */
   public static final String AREA = "region_area";
   public static final String REGION = "region_oid";
   
   @ManyToOne(optional=false)
   @JoinColumn(name=NETWORK)
   private Network network;

   @OneToMany(mappedBy="region", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
   private Set<Site> sites;

   @OneToMany(mappedBy="region", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
   private Set<Subregion> subregions;
   
   @Embedded
   @AssociationOverride(name="positions", joinTable=@JoinTable(name=AREA, joinColumns=@JoinColumn(name=REGION)))
   Area area;
   
   public Area getArea()
   {
      return area;
   }

   public void setArea(Area area)
   {
      this.area = area;
   }

   /**
    * The <code>Region</code> is always belong to the <code>Network</code>. 
    * @return
    */
   public Network getNetwork()
   {
      return network;
   }
   
   /* (non-Javadoc)
    * @see com.heliocentris.rms.core.Domain#getSites()
    */
   public Set<Site> getSites()
   {
      return sites;
   }

   public Set<Subregion> getSubregions()
   {
      return subregions;
   }

   public void setNetwork(Network network)
   {
      this.network = network;
   }
   
}
