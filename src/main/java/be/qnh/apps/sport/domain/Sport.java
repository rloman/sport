package be.qnh.apps.sport.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("sport")
public class Sport extends AbstractEntity {

   private static final long serialVersionUID = -5297029710836360582L;

   private String name;

    private boolean mixed;

    
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

   @Override
   public String toString() {
      return "Sport [id=" +getId() + ", name=" + getName() + "]";
   }
}
