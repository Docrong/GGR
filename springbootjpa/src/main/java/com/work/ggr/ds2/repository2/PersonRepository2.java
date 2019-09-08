package com.work.ggr.ds2.repository2;

import com.work.ggr.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("personRepository2")
public interface PersonRepository2 extends JpaRepository<Person,Integer> {
}
