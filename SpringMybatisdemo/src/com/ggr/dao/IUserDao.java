package com.ggr.dao;

import java.util.List;

import com.ggr.model.User;


public interface IUserDao {
	public abstract List<User> selectAll();
}
