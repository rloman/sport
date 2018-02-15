package be.qnh.apps.sport.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import be.qnh.apps.sport.domain.Company;

@Configuration
public class BeansConfig {
   
   @Bean
   @Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
   public Company company() {
      
      Company qnh = new Company();
      
      return qnh;
   }
}
