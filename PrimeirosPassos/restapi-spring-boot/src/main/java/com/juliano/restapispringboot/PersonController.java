package com.juliano.restapispringboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.restapispringboot.data.dto.v1.PersonDTO;
import com.juliano.restapispringboot.data.dto.v2.PersonDTO2;
import com.juliano.restapispringboot.services.PersonServices;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
    
    @Autowired
    @Lazy
    private PersonServices service; // -> instancia objeto automaticamente

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PersonDTO> findAll(){
                
            return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonDTO findById(@PathVariable(value = "id") Long id) throws Exception{
                
            return service.findById(id);
    }

    @PostMapping(
    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, //JSON na entrada
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) //JSON na saida
    public PersonDTO create(@RequestBody PersonDTO person){
                
            return service.create(person);
    }

    //Usado caso necessite atualizar os parametos da API sem quebrar o sistema
    @PostMapping(
    value = "v2",
    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, //JSON na entrada
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) //JSON na saida
    public PersonDTO2 createV2(@RequestBody PersonDTO2 person){
                
            return service.createV2(person);
    }

    @PutMapping(
    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, //JSON na entrada
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) //JSON na saida
    public PersonDTO update(@RequestBody PersonDTO person) throws Exception{
                
            return service.update(person);
    }

    @DeleteMapping(
    value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception{
                
            service.delete(id);
            return ResponseEntity.noContent().build(); //Retorna o status-code da operacao
    }


    private boolean isNumeric(String strNumber) {
        if(strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+"); //verifica se esta entre 0-9 e se e + ou -
    }
    
}

