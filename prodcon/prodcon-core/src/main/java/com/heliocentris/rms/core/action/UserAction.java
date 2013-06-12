package com.heliocentris.rms.core.action;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class UserAction<T> extends Action<T>
{
   private static final long serialVersionUID = -2254310201701912277L;

   public static final String ACTOR = "actor";
   
   @Column(name=ACTOR, nullable=false)
   private String actor;

   public String getActor()
   {
      return actor;
   }

   public void setActor(String actor)
   {
      this.actor = actor;
   }
}
