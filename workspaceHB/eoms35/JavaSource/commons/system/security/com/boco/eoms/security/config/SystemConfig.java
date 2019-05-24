/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-18         created
 */

package com.boco.eoms.security.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.security.util.ServiceAttributes;
import com.boco.eoms.security.util.ServiceMgrLocator;

/**
 * <p>
 * Title: BOCO Authentication and Authorization System
 * </p>
 * <p>
 * Description: Load system configuration into memory
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003 boco Co.,Ltd
 * </p>
 * <p>
 * Company: BOCO
 * </p>
 * 
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class SystemConfig {

	/* set default value of LDAP server */
	public String userDNPrefix = "CN=";
	// public String userDNSuffix =
	// "OU=��λ��,OU=nms_users,DC=testnms,DC=fj,DC=cm";
	public String userDNSuffix = "OU=nms_users,DC=NMS,DC=FJ,DC=CM";
	public String groupCtxDN = "OU=depts,DC=NMS,DC=FJ,DC=CM";
	public String rolesCtxDN = "ou=roles,DC=NMS,DC=FJ,DC=CM";
	public String permissionCtxDN = "ou=permissions,DC=NMS,DC=FJ,DC=CM";
	public String rangeCtxDN = "ou=dataranges,DC=NMS,DC=FJ,DC=CM";

	/* system DAO class definition */
	public String userDAOClass = "com.boco.eoms.security.service.dao.ldap.UserLdapDAO";
	public String departmentDAOClass = "com.boco.eoms.security.service.dao.ldap.DepartmentLdapDAO";
	public String regionDAOClass = "com.boco.eoms.security.service.dao.ldap.RegionLdapDAO";
	public String roleDAOClass = "com.boco.eoms.security.service.dao.ldap.RoleLdapDAO";
	public String permissionDAOClass = "com.boco.eoms.security.service.dao.ldap.PermissionLdapDAO";
	public String rangeDAOClass = "com.boco.eoms.security.service.dao.ldap.RangeLdapDAO";

	public String daoFactory = "com.boco.eoms.security.services.factory.LdapDaoFactory";

	/**
	 * Server information.
	 */
	public String factory = "com.sun.jndi.ldap.LdapCtxFactory";
	// public String url = StaticMethod.getNodeName("ADURL");
	public String url = ServiceMgrLocator.getServiceAttributes().getAdurl();
	/**
	 * The username and password of the administrator of LDAP server
	 */
	// public String adminUID = StaticMethod.getNodeName("ADUID");
	public String adminUID = ServiceMgrLocator.getServiceAttributes()
			.getAduid();
	// public String adminPWD = StaticMethod.getNodeName("ADPWD");
	public String adminPWD = ServiceMgrLocator.getServiceAttributes()
			.getAdpwd();

	/* instance of SystemConfig class */
	private static SystemConfig instance = new SystemConfig();

	/**
	 * @return instance of class "SystemConfig"
	 */
	public static SystemConfig getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return new SystemConfig();
		}
	}

	/**
	 * Construct of SystemConfig class
	 */
	private SystemConfig() {

		/*
		 * Properties prop = new Properties(); File confFile=new
		 * File("conf/env.conf"); if (!confFile.exists()) { confFile=new
		 * File("../conf/env.conf"); if (!confFile.exists()) {
		 * System.out.println("can not found config file 'conf/env.conf'.");
		 * return; } } try { prop.load(new FileInputStream(confFile));
		 * 
		 * factory = (String) prop.getProperty("facotry"); url = (String)
		 * prop.getProperty("url");
		 * 
		 * userDNPrefix = (String) prop.getProperty("userDNPrefix");
		 * userDNSuffix = (String) prop.getProperty("userDNSuffix"); groupCtxDN =
		 * (String) prop.getProperty("groupCtxDN"); rolesCtxDN = (String)
		 * prop.getProperty("rolesCtxDN"); permissionCtxDN = (String)
		 * prop.getProperty("permissionCtxDN"); rangeCtxDN = (String)
		 * prop.getProperty("rangeCtxDN");
		 * 
		 * userDAOClass = (String) prop.getProperty("userDAOClass");
		 * departmentDAOClass = (String) prop.getProperty("departmentDAOClass");
		 * regionDAOClass = (String) prop.getProperty("regionDAOClass");
		 * roleDAOClass = (String) prop.getProperty("roleDAOClass");
		 * permissionDAOClass = (String) prop.getProperty("permissionDAOClass");
		 * rangeDAOClass = (String) prop.getProperty("rangeDAOClass");
		 * 
		 * daoFactory = (String) prop.getProperty("daoFactory");
		 * 
		 * adminUID = (String) prop.getProperty("adminUID"); adminPWD = (String)
		 * prop.getProperty("adminPWD");
		 * 
		 * System.out.println("System Config=" + prop); } catch
		 * (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
		 * e) { e.printStackTrace(); }
		 */
	}

	/* Only for self-testing */
	public static void main(String[] args) {

		SystemConfig systemConfig1 = new SystemConfig();
	}

}
