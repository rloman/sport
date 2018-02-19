package be.qnh.apps.sport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.qnh.apps.sport.domain.Company;

@Configuration
public class BeansConfig {
   
   @Bean
   public Company company() {
      
      Company qnh = new Company();
      
      return qnh;
   }
}
