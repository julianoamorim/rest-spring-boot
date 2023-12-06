package com.juliano.restapispringboot.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.juliano.restapispringboot.model.Person;
import com.juliano.restapispringboot.vo.v2.PersonVO2;

@Service
public class PersonMapper {

    public PersonVO2 convertEntityToVO(Person person){
        PersonVO2 vo = new PersonVO2();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthday(new Date());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        return vo;
    }

    //transformando VO en Entity(p o banco de dados)
    public Person convertVOToEntity(PersonVO2 person){
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
        //entity.setBirthday(new Date());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        return entity;
    }

}
