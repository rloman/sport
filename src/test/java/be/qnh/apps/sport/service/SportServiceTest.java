package be.qnh.apps.sport.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import be.qnh.apps.sport.domain.Company;
import be.qnh.apps.sport.domain.Sport;
import be.qnh.apps.sport.persistence.SportRepository;

@RunWith(MockitoJUnitRunner.class)
public class SportServiceTest {
   
   
   @InjectMocks
   private SportService sportService;
   
   @Mock
   private SportRepository repo;
   
   @Mock
   private Company company;
   
   @Test
   public void testFindById() {
      
      Sport resultFromRepo = new Sport();
      resultFromRepo.setMixed(true);
      resultFromRepo.setName("Curling");
      
      
      // instruct the mock of the repo what to do
      Mockito.when(this.repo.findOne(3L)).thenReturn(resultFromRepo);
      
      // mocking done
      Sport resultFromService = this.sportService.findById(3);
      
      Assert.assertEquals("Curling", resultFromService.getName());
      Assert.assertTrue(resultFromService.isMixed());
      
      Mockito.verify(this.repo).findOne(3L);
      Mockito.verify(this.repo, Mockito.times(3)).findOne(3L); // the same but explicit one call
   }
}
