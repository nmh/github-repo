package com.heliocentris.rms.core;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name=Network.TABLE)
public class Network extends Domain
{
   private static final long serialVersionUID = 3952448630952630515L;
   
   /**
    * Specified the table name of the entity in the backing store.
    */
   public static final String TABLE = "network";

   /**
    * Specified the table name of the relation <code>defaultUnitTypes</code>.
    */
   public static final String DEFAULT_UNIT_TYPES = "network_default_unit_type";
   
   @OneToMany(mappedBy="network", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
   private Set<Region> regions;

   @OneToMany(mappedBy="network", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
   private Set<Subregion> subregions;

   @OneToMany(mappedBy="network", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
   private Set<Site> sites;

   @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
   @JoinTable(name=DEFAULT_UNIT_TYPES, inverseJoinColumns=@JoinColumn(name="unit_types_oid"))
   private Set<UnitType> defaultUnitTypes;

   public Network getNetwork()
   {
      return this;
   }
   
   public Set<Region> getRegions()
   {
      return regions;
   }

   public Set<Subregion> getSubregions()
   {
      return subregions;
   }

   public Set<Site> getSites()
   {
      return sites;
   }

   public Set<UnitType> getDefaultUnitTypes()
   {
      return defaultUnitTypes;
   }

   public boolean addDefaultUnitType(UnitType unitType)
   {
      return !defaultUnitTypes.contains(unitType) && defaultUnitTypes.add(unitType);
   }
   
   public boolean removeDefaultUnitType(UnitType unitType)
   {
      return defaultUnitTypes.contains(unitType) && defaultUnitTypes.remove(unitType);
   }
   
}
