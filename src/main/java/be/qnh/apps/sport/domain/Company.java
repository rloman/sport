package be.qnh.apps.sport.domain;

import org.springframework.beans.factory.annotation.Value;

public class Company {
   
   @Value("${company.name}")
   private String name;
   
   public String getName() {
      return name;
   }

   
   public void setName(String name) {
      this.name = name;
   }
   

}
