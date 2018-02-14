package be.qnh.apps.sport.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class AbstractEntity implements Serializable{
   
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private long id;

   
   public long getId() {
      return id;
   }

}
