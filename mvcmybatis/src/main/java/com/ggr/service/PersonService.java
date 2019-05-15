package com.ggr.service;

import java.util.List;

import com.ggr.entity.Person;

public interface PersonService {

    int insert(Person person);
    List findAll();
    void insertAnother(Person person);
}
