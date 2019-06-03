package com.hoo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hoo.entity.User;

/**
 * * <b>function:</b>axis WebService传递复杂类型数据 * @author hoojo * @createDate Dec
 * 16, 2010 10:21:06 PM * @file ManagerUser.java * @package com.hoo.service
 * * @project AxisWebService * @blog http://blog.csdn.net/IBM_hoojo * @email
 * hoojo_@126.com * @version 1.0
 */
@SuppressWarnings("deprecation")
public class ManagerUser {
	public User getUserByName(String name) {
		User user = new User();
		user.setId(new Date().getSeconds());
		user.setName(name);
		user.setAddress("china");
		user.setEmail(name + "@hoo.com");
		return user;
	}

	public void setUser(User user) {
		System.out.println(user);
	}

	public List<User> getUsers(int i) {
		List<User> users = new ArrayList<User>();
		for (int j = 0; j <= i; j++) {
			User user = new User();
			user.setId(new Date().getSeconds());
			user.setName("jack#" + j);
			user.setAddress("china");
			user.setEmail("jack" + j + "@hoo.com");
			users.add(user);
		}
		return users;
	}

	public void setUserMap(Map<String, User> maps) {
		System.out.println(maps);
	}

	public User[] getUserArray(int i) {
		User[] users = new User[i];
		for (int j = 0; j < i; j++) {
			User user = new User();
			user.setId(new Date().getSeconds());
			user.setName("jack#" + j);
			user.setAddress("china");
			user.setEmail("jack" + j + "@hoo.com");
			users[j] = user;
		}
		return users;
	}

	public void setUserArray(User[] users) {
		for (User u : users) {
			System.out.println(u);
		}
	}

	public Map<String, User> getUserMap() {
		Map<String, User> users = new HashMap<String, User>();
		User user = new User();
		user.setId(new Date().getSeconds());
		user.setName("jack#");
		user.setAddress("china");
		user.setEmail("jack@hoo.com");
		users.put("A", user);
		user = new User();
		user.setId(new Date().getSeconds());
		user.setName("tom");
		user.setAddress("china");
		user.setEmail("tom@hoo.com");
		users.put("B", user);
		return users;
	}

	public void setUsers(List<User> users) {
		for (User u : users) {
			System.out.println(u);
		}
	}
}
