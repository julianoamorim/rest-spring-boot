package com.juliano.restapispringboot.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliano.restapispringboot.exception.ResourceNotFindException;
import com.juliano.restapispringboot.mapper.DozerMapper;
import com.juliano.restapispringboot.mapper.custom.PersonMapper;
import com.juliano.restapispringboot.model.Person;
import com.juliano.restapispringboot.repositories.PersonRepository;
import com.juliano.restapispringboot.vo.v1.PersonVO;
import com.juliano.restapispringboot.vo.v2.PersonVO2;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;  // -> instancia objeto automaticamente
    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll(){
        logger.info("Encontrando todas pessoas...");
        
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id){
        
        logger.info("Encontrando uma pessoa...");
        
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFindException("Sem Registros para este Id") );
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person){
        logger.info("Criando uma pessoa...");
        var entity = DozerMapper.parseObject(person, Person.class);
        
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    
    }
    //Usado caso necessite atualizar os parametos da API sem quebrar o sistema
    public PersonVO2 createV2(PersonVO2 person){
        logger.info("Criando uma pessoa v2...");
        var entity = mapper.convertVOToEntity(person);
        
        var vo = mapper.convertEntityToVO(repository.save(entity));
        return vo;
    }

    public PersonVO update(PersonVO person){
        logger.info("Atualizando uma pessoa...");

        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFindException("Sem Registros para este Id") );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id){
        logger.info("Deletando uma pessoa...");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFindException("Sem Registros para este Id") );

        repository.delete(entity);
    }

}