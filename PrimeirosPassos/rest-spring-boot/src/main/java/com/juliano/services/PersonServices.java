package com.juliano.services;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.juliano.model.Person;


@Service
public class PersonServices {
    private final AtomicLong counter  = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){
        logger.info("Finding one person...");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Juliano");
        person.setLastName("Amorim");
        person.setAddress("Luis Eduardo Magalhaes BA");
        person.setGender("male");
        
        return person;
    }
    
}
