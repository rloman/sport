package be.qnh.apps.sport.domain;

public class Sport extends AbstractEntity {

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
      return "Sport [id=" + id + ", name=" + name + "]";
   }
}
