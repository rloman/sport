package be.qnh.apps.sport.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("volleybal")
public class Volleybal extends Sport {

   private static final long serialVersionUID = 929609169009951850L;

}
