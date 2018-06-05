package com.ggr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggr.dao.PersonRepository;
import com.ggr.entity.Person;
import com.ggr.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired(required=true)
    private PersonRepository personRepository;

    public Long savePerson() {
        Person person = new Person();
        person.setUsername("XRog");
        person.setPhone("18381005946");
        person.setAddress("chenDu");
        person.setRemark("this is XRog");
        return personRepository.save(person);
    }
}