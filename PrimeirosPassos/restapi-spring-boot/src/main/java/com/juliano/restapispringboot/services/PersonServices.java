package com.juliano.restapispringboot.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliano.restapispringboot.exception.ResourceNotFindException;
import com.juliano.restapispringboot.repositories.PersonRepository;
import com.juliano.restapispringboot.vo.v1.PersonVO;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;  // -> instancia objeto automaticamente

    public List<PersonVO> findAll(){
        logger.info("Encontrando todas pessoas...");
        
        return repository.findAll();
    }

    public PersonVO findById(Long id){
        
        logger.info("Encontrando uma pessoa...");
        
        return repository.findById(id).orElseThrow(() -> new ResourceNotFindException("Sem Registros para este Id") );
    }

    public PersonVO create(PersonVO person){
        logger.info("Criando uma pessoa...");
        

        return repository.save(person);
    }

    public PersonVO update(PersonVO person){
        logger.info("Atualizando uma pessoa...");

        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFindException("Sem Registros para este Id") );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Deletando uma pessoa...");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFindException("Sem Registros para este Id") );

        repository.delete(entity);
    }

}