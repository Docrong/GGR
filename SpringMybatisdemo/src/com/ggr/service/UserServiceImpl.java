package com.ggr.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ggr.dao.IUserDao;
import com.ggr.model.User;




@Service("userService")
public class UserServiceImpl implements IUserService {
	private IUserDao userDao;
	
	
	public IUserDao getUserDao() {
		return userDao;
	}

    @Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}

}
