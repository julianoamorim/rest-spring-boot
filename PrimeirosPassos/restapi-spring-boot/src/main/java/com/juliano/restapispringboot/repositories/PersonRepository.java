package com.juliano.restapispringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juliano.restapispringboot.model.Person;

//Realiza as operacoes de CRUD
@Repository                                             //Objeto, Id
public interface PersonRepository extends JpaRepository<Person, Long>{}
