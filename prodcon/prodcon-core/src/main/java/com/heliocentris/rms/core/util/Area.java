package com.heliocentris.rms.core.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.OrderColumn;

@Embeddable
public class Area implements Serializable
{
   private static final long serialVersionUID = -4014353290234104561L;
   
   public static final String TABLE = "area";
   
   @OrderColumn(name="ordinal")
   @ElementCollection(fetch=FetchType.LAZY)
   @JoinTable(name=TABLE)
   private List<Position> positions = new ArrayList<Position>();

   public Area()
   {
      addPosition(new Position(-1, -1));
      addPosition(new Position(-1, +1));
      addPosition(new Position(+1, +1));
      addPosition(new Position(+1, -1));
   }
   
   /**
    * The <code>Position</code>s list for the <code>Area</code>. 
    * @return
    */
   public List<Position> getPositions()
   {
      return positions;
   }

   public boolean addPosition(Position position)
   {
      return !positions.contains(position) && positions.add(position);
   }

   public boolean removePosition(Position position)
   {
      return positions.contains(position) && positions.remove(position);
   }
   
   /**
    * Determine if the specified <code>Position</code> intersect with the <code>Area</code>.
    * @param position the specified <code>Position</code>.
    * @return true or false indicate intersect or not.
    */
   public boolean intersect(Position position)
   {
      throw new UnsupportedOperationException("not yet implemented");
   }
}
