package be.qnh.apps.sport.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sport implements Serializable {

   private static final long serialVersionUID = 6484517974939161707L;

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private long id;
   
    private String name;

    private boolean mixed;

 public long getId() {
      return id;
   }
    
    public boolean isMixed() {
        return mixed;
    }

    public void setMixed(boolean mixed) {
        this.mixed = mixed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
   
  
}
