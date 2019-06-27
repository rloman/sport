package be.qnh.apps.sport.domain;

import org.apache.tomcat.util.net.AbstractEndpoint;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@DiscriminatorColumn(name="type")
@MappedSuperclass
public abstract class AbstractEntity implements Serializable{
   
   private static final long serialVersionUID = 1497251056695744100L;
   
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private long id;

   public AbstractEntity() {

   }

   public AbstractEntity(long id) {
      this.id = id;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      AbstractEntity that = (AbstractEntity) o;

      return id == that.id;
   }

   @Override
   public int hashCode() {
      return (int) (id ^ (id >>> 32));
   }

   public long getId() {
      return id;
   }
}