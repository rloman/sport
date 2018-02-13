package be.qnh.apps.sport.service;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.qnh.apps.sport.SportRepository;
import be.qnh.apps.sport.domain.Sport;

@Service
@Transactional
public class SportService {
   
   @Autowired
   private SportRepository repo;
   
   @PostConstruct
   public void init() {
      Sport sport1 = new Sport();
      sport1.setName("Voetbal");
      sport1.setMixed(false);

      Sport sport2 = new Sport();
      sport2.setName("Korfbal");
      sport2.setMixed(true);


      Sport sport3 = new Sport();
      sport3.setName("Volleybal");
      sport3.setMixed(false);
      
      this.repo.save(sport1);
      this.repo.save(sport2);
      this.repo.save(sport3);
      
      this.repo.save(Arrays.asList(sport1, sport2, sport3));

   }
   
   public Sport insert(Sport sport) {
      return this.repo.save(sport);
   }
   
   
   public Iterable<Sport> getAll() {
      
      init();
      
      Iterable<Sport> sports = this.repo.findAll();
      
      return sports;
   }
   
   public Sport findById(long id) {
      
      return this.repo.findOne(id);
   }
   
   public void deleteById(long id) {
      this.repo.delete(id);
   }
}
