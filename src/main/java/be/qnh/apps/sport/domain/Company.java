package be.qnh.apps.sport.domain;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;

public class Company implements Serializable{
   
   private static final long serialVersionUID = -3414266657804730362L;
   
   @Value("${company.name}")
   private String name;
   
   public String getName() {
      return name;
   }

   
   public void setName(String name) {
      this.name = name;
   }
   

}
