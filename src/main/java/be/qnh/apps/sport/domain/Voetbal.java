package be.qnh.apps.sport.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("voetbal")
public class Voetbal extends Sport {

   private static final long serialVersionUID = -7497543841532775415L;

}
