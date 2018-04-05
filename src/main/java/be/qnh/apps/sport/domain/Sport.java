package be.qnh.apps.sport.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;
import java.util.TreeSet;

@Entity
@DiscriminatorValue("sport")
public class Sport extends AbstractEntity implements Comparable<Sport> {

   private static final long serialVersionUID = -5297029710836360582L;

   public Sport() {

   }

   public Sport(long id) {
       super(id);
   }

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


   public static void main() {

        Sport s = new Sport();

        Set<Sport> verz = new TreeSet<>();

   }

    @Override
    public int compareTo(Sport o) {
        return 0;
    }
}
