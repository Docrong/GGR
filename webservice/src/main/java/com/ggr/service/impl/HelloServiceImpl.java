package com.ggr.service.impl;

import com.ggr.service.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String info) {
		return "HELLO--"+info;
	}

}
