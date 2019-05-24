package com.boco.eoms.commons.system.user.test.service;

import java.util.List;

import junit.framework.TestCase;

import com.boco.eoms.commons.system.user.exception.TawSystemUserException;
import com.boco.eoms.commons.system.user.model.TawSystemUser;

import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;

public class TawSystemUserRoleTest extends TestCase {
	TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();

	// public void testGetrole() {
	//
	// try {
	//
	// List list = rolebo.getRoleidByuserid("test");
	//		
	// // System.out.println("查询用户ID为 test 的角色");
	// assertTrue(list.size() > 0 );
	// } catch (TawSystemUserException e) {
	// e.printStackTrace();
	// }
	// }

	public void testGetuserid() {

		List list = rolebo.getUserBycptidAndDegid("11", "1");

		// System.out.println("查询机房ID为 11 的所有管理员");
		// System.out.println(list.size());
		assertTrue(list.size() > 0);
	}

	public void testGetcptids() {

		List list = rolebo.getUserbyCptids("11");

		// System.out.println("查询用户ID为 11 的所有用户信息");
		// System.out.println(list.size());
		assertTrue(list.size() > 0);
	}

	public void testgetUserbyDeptidandDegids() {

		List list = rolebo.getUserbyDeptidandDegids("22", "1");
		// BocoLog.debug(this, "查询部门ID 为 22 的管理员");
		// System.out.println(list.size());
		assertTrue(list.size() > 0);
	}

	public void testgetUserbydeptidanson() {

		List list = rolebo.getUserbydeptidanson("22,33");

		// System.out.println("查询部门为 22 和 33 的所有用户信息");
		// System.out.println(list.size());
		assertTrue(list.size() > 0);
	}

	public void testgetUserBydeptids() {

		List list = rolebo.getUserBydeptids("22");

		// System.out.println("查询部门ID为 22 的所有用户信息");
		// System.out.println(list.size());
		assertTrue(list.size() > 0);
	}

	public void testgetUserbyOperuserids() {

		List list = rolebo.getUserbyOperuserids("admin");

		// System.out.println(list.size()+ " 查询 admin 创建的所有用户信息");
		assertTrue(list.size() > 0);
	}

	public void testgetUserbysexs() {

		List list = rolebo.getUserbysexs("0");

		// System.out.println("查询部门的所有男人 "+list.size());
		assertTrue(list.size() > 0);
	}

	public void getUserbyUserdegrees() {

		List list = rolebo.getUserbyUserdegrees("1");

		// System.out.println("查询所有的管理员"+list.size());
		assertTrue(list.size() > 0);
	}

	// public void getUseridbyroleid() throws TawSystemUserException {
	//
	// List list = rolebo.getUseridbyroleid("23");
	// //BocoLog.debug(this, "查询角色ID 为 23 的所有用户ID");
	// //System.out.println(list.size());
	// assertTrue(list.size() > 0);
	// }

	public void getUsersbyroleids() throws TawSystemUserException {

		List list = rolebo.getUsersbyroleids("23");
		// BocoLog.debug(this, "查询角色ID为 23 的所有用户信息");
		// System.out.println(list.size());
		assertTrue(list.size() > 0);
	}

	// public void testgetUsersbyroleids() throws TawSystemUserException {
	//
	// boolean flag = rolebo.getRoleidByuserid("23", "test");
	// // BocoLog.debug(this, "判断角色为 23 的用户 test 是否存在");
	//
	// assertTrue(flag);
	// }

	// public void testgetUserbyroleidanduserids() throws TawSystemUserException
	// {
	//
	// TawSystemUser user = rolebo.getUserbyroleidanduserids(
	// "23", "test");
	// //BocoLog.debug(this, "查询角色为 23 的 用户 test 的信息 ");
	// assertNotNull(user);
	// }

}
