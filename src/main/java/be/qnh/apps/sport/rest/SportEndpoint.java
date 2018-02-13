package be.qnh.apps.sport.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value="list", method= RequestMethod.GET)
    public ResponseEntity<Iterable<Sport>> getAll() {
       
       Iterable <Sport> sports = this.service.getAll();

        if(sports == null) {
            return new ResponseEntity<Iterable<Sport>>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<Iterable<Sport>>(sports, HttpStatus.OK);
        }

    }

}
