package com.juliano.restapispringboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.restapispringboot.model.Person;
import com.juliano.restapispringboot.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
    
    @Autowired
    @Lazy
    private PersonServices service;
    //private PersonServices service = new PersonServices() -> Instacia automaticamente

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll(){
                
            return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable(value = "id") String id) throws Exception{
                
            return service.findById(id);
    }

    @RequestMapping(
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE, //JSON na entrada
    produces = MediaType.APPLICATION_JSON_VALUE) //JSON na saida
    public Person create(@RequestBody Person person) throws Exception{
                
            return service.create(person);
    }

    @RequestMapping(
    method = RequestMethod.PUT,
    consumes = MediaType.APPLICATION_JSON_VALUE, //JSON na entrada
    produces = MediaType.APPLICATION_JSON_VALUE) //JSON na saida
    public Person update(@RequestBody Person person) throws Exception{
                
            return service.update(person);
    }

    @RequestMapping(
    value = "/{id}",
    method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") String id) throws Exception{
                
            service.delete(id);;
    }

    private Double convertToDouble(String strNumber) {
        if(strNumber == null) return 0D;
        String number = strNumber.replaceAll(",", "."); //converte , em .
        if(isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String strNumber) {
        if(strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+"); //verifica se esta entre 0-9 e se e + ou -
    }
    
}

