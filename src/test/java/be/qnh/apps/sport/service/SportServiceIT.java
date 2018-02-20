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
@SpringBootTest(classes = SportApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SportServiceIT {

   @Autowired
   private SportService sportService;

   @Test
   public void testCrud() {

      Sport nieuwe = new Sport();
      nieuwe.setMixed(true);
      nieuwe.setName("Schaken");

      Sport sportCreated = this.sportService.insert(nieuwe);

      Assert.assertTrue(sportCreated.getId() != 0);

      long id = sportCreated.getId();

      Sport opgehaald = this.sportService.findById(id);

      Assert.assertEquals(nieuwe.getName(), opgehaald.getName());

      opgehaald.setName("Schaken en Dammen");

      Sport opgehaaldNaSaven = this.sportService.update(id, opgehaald);

      Assert.assertEquals("Schaken en Dammen", opgehaaldNaSaven.getName());

      this.sportService.deleteById(id);

      Assert.assertNull(this.sportService.findById(id));
   }

   @Test
   public void testDeletingNonExisting() {

      Sport nieuwe = new Sport();
      nieuwe.setMixed(true);
      nieuwe.setName("Schaken");

      Sport sportCreated = this.sportService.insert(nieuwe);

      Assert.assertTrue(sportCreated.getId() != 0);

      long id = sportCreated.getId();

      Sport opgehaald = this.sportService.findById(id);

      Assert.assertEquals(nieuwe.getName(), opgehaald.getName());

      opgehaald.setName("Schaken en Dammen");

      Sport opgehaaldNaSaven = this.sportService.update(id, opgehaald);

      Assert.assertEquals("Schaken en Dammen", opgehaaldNaSaven.getName());

      Assert.assertTrue(this.sportService.deleteById(id));

      Assert.assertNull(this.sportService.findById(id));

      Assert.assertFalse(this.sportService.deleteById(id));
   }

   @Test
   public void testDeleteAllUsingAtQueryAnnotation() {
      for (int i = 0; i < 3; i++) {
         Sport nieuwe = new Sport();
         nieuwe.setMixed(true);
         nieuwe.setName("Schaken");

         this.sportService.insert(nieuwe);
      }
      
     Assert.assertEquals(3, this.sportService.count());
     
     this.sportService.deleteAllSports();
     
     Assert.assertEquals(0, this.sportService.count());
   }
   
   @Test
   public void testSetAllSportsToVoetbal() {

      for (int i = 0; i < 3; i++) {
         Sport nieuwe = new Sport();
         nieuwe.setMixed(true);
         nieuwe.setName("Schaken");

         this.sportService.insert(nieuwe);
      }
      
     Assert.assertEquals(3, this.sportService.count());
     
     this.sportService.setAllSportsToVoetbal();
     
     this.sportService.getAll().forEach(s -> {
        Assert.assertEquals("voetbal", s.getName());
     });
     
     Assert.assertEquals(3, this.sportService.count());
   }
}