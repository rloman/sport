package be.qnh.apps.sport.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import be.qnh.apps.sport.domain.Company;
import be.qnh.apps.sport.domain.Sport;
import be.qnh.apps.sport.persistence.SportRepository;

@Service
public class SportService {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(SportService.class);
   
   @Autowired
   private SportRepository repo;
   
   @Autowired
   private Company company;
   
   @Scheduled(cron="*/15 * * * * *")
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
      
      LOGGER.info("Add Sport [{}] and [{}] and [{}]", sport1, sport2, sport3 );
      
      this.repo.save(Arrays.asList(sport1, sport2, sport3));
   }
   
   @Transactional
   public Sport insert(Sport sport) {
      Sport result = this.repo.save(sport);
      
      return result;
   }
   
   @Transactional
   public Sport update(long id, Sport sportIn) {
      
      Sport victim = this.repo.findOne(id);
      
      if(victim != null) {
         victim.setName(sportIn.getName());
         victim.setMixed(sportIn.isMixed());
         
         return this.repo.save(victim);
      }
      else {
         return null;
      }
      
   }
   
   public Iterable<Sport> getAll() {
      
      Iterable<Sport> sports = this.repo.findAll();
      
      return sports;
   }
   
   public Sport findById(long id) {
      
      Sport sport = this.repo.findOne(id);
      
      System.err.println("Getting sport for company "+company.getName());
      
      return sport;
   }
   
   @Transactional
   public boolean deleteById(long id) {
      if(this.repo.exists(id)) {
         try {
            this.repo.delete(id);
            
            return true;
         }
         // in case of removing between this 1 ms
         catch(EmptyResultDataAccessException emptyException){
            LOGGER.warn("Entity removed between checking if existing and removing, no worries but this should not happen every day :-)");
         }
      }
      return false;
   }
   
   public long count() {
      return this.repo.count();
   }
   
   @Transactional
   public void deleteAllSports() {
      this.repo.deleteAllSports();
   }

   @Transactional
   public void setAllSportsToVoetbal() {
      this.repo.setAllSportsToVoetbal();
      
   }  
}