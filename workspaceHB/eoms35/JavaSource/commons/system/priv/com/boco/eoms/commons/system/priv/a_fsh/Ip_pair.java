package com.boco.eoms.commons.system.priv.a_fsh;


public class Ip_pair {

private String name; 
private Ip[] ip = new Ip[]{};

public Ip[] getIp() {
	return ip;
}

public void setIp(Ip[] ip) {
	this.ip = ip;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
}
