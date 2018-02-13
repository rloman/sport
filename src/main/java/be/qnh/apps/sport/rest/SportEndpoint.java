package be.qnh.apps.sport.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sport> getById(@PathVariable long id) {
       
       Sport result = this.service.findById(id);
       
       return result != null ?  new ResponseEntity<Sport>(result, HttpStatus.OK) : new ResponseEntity<Sport>(HttpStatus.NOT_FOUND);
    }
}