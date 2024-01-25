package com.juliano.restapispringboot.services;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliano.restapispringboot.data.dto.v1.PersonDTO;
import com.juliano.restapispringboot.data.dto.v2.PersonDTO2;
import com.juliano.restapispringboot.exception.ResourceNotFindException;
import com.juliano.restapispringboot.mapper.custom.PersonMapper;
import com.juliano.restapispringboot.model.Person;
import com.juliano.restapispringboot.repositories.PersonRepository;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;  // -> instancia objeto automaticamente
    @Autowired
    PersonMapper mapper;
    @Autowired
    ModelMapper modelMapper; //de Model Configuration

    public List<PersonDTO> findAll(){
        logger.info("Encontrando todas pessoas...");
        List<Person> persons = this.repository.findAll();
        return persons.stream().map((person) -> modelMapper.map(person, PersonDTO.class))
        .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id){
        
        logger.info("Encontrando uma pessoa...");
        var entity = this.repository.findById(id)
        .orElseThrow(() -> new ResourceNotFindException("Cadastron nao encontrado!"));
        return modelMapper.map(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Criando uma pessoa...");
        var entity = modelMapper.map(person, Person.class); //DOT convert em Person
        var dto = modelMapper.map(repository.save(entity), PersonDTO.class); //Person para DTO
        return dto;
    
    }
    //Usado caso necessite atualizar os parametos da API sem quebrar o sistema
    public PersonDTO2 createV2(PersonDTO2 person){
        logger.info("Criando uma pessoa v2...");
        var entity = mapper.convertDTOToEntity(person);
        
        var vo = mapper.convertEntityToDTO(repository.save(entity));
        return vo;
    }

    public PersonDTO update(PersonDTO person){
        logger.info("Atualizando uma pessoa...");

        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFindException("Sem Registros para este Id") );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        
        var dto = modelMapper.map(repository.save(entity), PersonDTO.class); //Person para DTO
        return dto;
    }

    public void delete(Long id){
        logger.info("Deletando uma pessoa...");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFindException("Sem Registros para este Id") );

        repository.delete(entity);
    }

}