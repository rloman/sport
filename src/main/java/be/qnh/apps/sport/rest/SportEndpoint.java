package be.qnh.apps.sport.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Sport>> getAll() {

        return new ResponseEntity<Iterable<Sport>>(this.service.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sport> getById(@PathVariable long id) {

        Sport result = this.service.findById(id);

        return result != null ? new ResponseEntity<Sport>(result, HttpStatus.OK) : new ResponseEntity<Sport>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Sport> putById(@PathVariable long id, @RequestBody Sport sport) {

        Sport sportAfterUpdating = this.service.update(id, sport);

        if (sportAfterUpdating != null) {
            return new ResponseEntity<Sport>(sportAfterUpdating, HttpStatus.OK);
        } else {
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