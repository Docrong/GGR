package com.work.ggr.ds1.repository;

import com.work.ggr.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author : gr
 * @date : 2019/9/6 16:18
 */
@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person,Integer> {

    Person findById(String id);
    List<Person> findByUsernameAndAddress(String username, String address);
}
