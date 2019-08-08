package com.ggr.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.ggr.entity.Person;

@Mapper
public interface PersonMapper {

    int deleteByPrimaryKey(Long id);

    @Insert("insert into Person (username,address)values(#{username},#{address})")
    int insert(Person record);

    void insertAnother(Person person);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);

    List findAll();
}
