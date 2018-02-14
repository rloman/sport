package be.qnh.apps.sport.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import be.qnh.apps.sport.SportApplication;
import be.qnh.apps.sport.domain.Sport;


@RunWith(SpringRunner.class)
@ActiveProfiles("integrationtest")
@SpringBootTest(classes=SportApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT)
public class SportServiceTest {
   
   @Autowired
   private SportService sportService;
   
   @Test
   public void testCreate() {
      
      Sport nieuwe = new Sport();
      nieuwe.setMixed(true);
      nieuwe.setName("Schaken");
      
      Sport sportCreated = this.sportService.insert(nieuwe);
      
      Assert.assertTrue(sportCreated.getId() != 0);
   }
}