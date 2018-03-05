package be.qnh.apps.sport.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import be.qnh.apps.sport.domain.Sport;
import be.qnh.apps.sport.service.SportService;

@RestController
@RequestMapping("/api/sports")
public class SportEndpoint {
   
   @Autowired
   private SportService service;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Iterable<Sport>> getAll() {
       
       Iterable <Sport> sports = this.service.getAll();

        if(sports == null) {
            return new ResponseEntity<Iterable<Sport>>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<Iterable<Sport>>(sports, HttpStatus.OK);
        }
    }

    @GetMapping(value="sqlmap")
    public void fooTestForSQLInjectionDemoForSETrack(String name) {
        System.err.println(name);

        Iterable<Sport> result = this.service.findByName(name);

        System.err.println(result);
    }

    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sport> getById(@PathVariable long id) {
       
       Sport result = this.service.findById(id);
       
       return result != null ?  new ResponseEntity<Sport>(result, HttpStatus.OK) : new ResponseEntity<Sport>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Sport> putById(@PathVariable long id, @RequestBody Sport sport) {

       Sport sportAfterUpdating = this.service.update(id, sport);

       if (sportAfterUpdating != null) {
          return new ResponseEntity<Sport>(sportAfterUpdating, HttpStatus.OK);
       }
       else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
    
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("{id}")
    public ResponseEntity<Sport> deleteById(@PathVariable long id) {
       
       this.service.deleteById(id);
       
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Sport> insert(@RequestBody Sport sportNieuw) {

       return new ResponseEntity<Sport>(this.service.insert(sportNieuw), HttpStatus.CREATED);
    }
}