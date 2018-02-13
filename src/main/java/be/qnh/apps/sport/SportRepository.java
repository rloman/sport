package be.qnh.apps.sport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.qnh.apps.sport.domain.Sport;

@Repository
public interface SportRepository extends CrudRepository<Sport, Long> {
   
   Iterable<Sport> findByName(String name);

   Iterable<Sport> findByNameOrderByMixedAsc(String name);
   
   Iterable<Sport> findByMixed(boolean mixed);

   Iterable<Sport> findAllByOrderByNameAsc();
   
}
