package com.ggr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggr.entity.Person;
import com.ggr.mapping.PersonMapper;
import com.ggr.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public int insert(Person person) {
        return personMapper.insert(person);
    }

    public List findAll() {
        return personMapper.findAll();
    }

    public void insertAnother(Person person) {
        personMapper.insertAnother(person);
    }
}