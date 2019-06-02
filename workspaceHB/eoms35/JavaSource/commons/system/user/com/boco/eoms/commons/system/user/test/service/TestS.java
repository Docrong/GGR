/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.user.test.service;

import java.util.List;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;

/**
 * @author panlong
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TestS extends ConsoleTestCase {

	// public void testCreate(){
	// Hashtable hash = new Hashtable();
	// hash.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
	// hash.put(Context.PROVIDER_URL,"iiop://localhost:2809");
	//
	// // hash.put("org.omg.CORBA.ORB", "com.ibm.rmi.iiop.ORB");
	// // hash.put("org.omg.CORBA.ORBSingletonClass",
	// "com.ibm.rmi.corba.ORBSingleton");
	// // hash.put("javax.rmi.CORBA.UtilClass",
	// "com.ibm.rmi.javax.rmi.CORBA.Util");
	// // hash.put("javax.rmi.CORBA.StubClass",
	// "com.ibm.rmi.javax.rmi.CORBA.StubDelegateImpl");
	// // hash.put("javax.rmi.CORBA.PortableRemoteObjectClass",
	// "com.ibm.rmi.javax.rmi.PortableRemoteObject");
	// // hash.put("java.naming.factory.url.pkgs", "com.ibm.ws.naming");
	// InitialContext ic;
	// try {
	// ic = new InitialContext(hash);
	// // Object result =
	// ic.lookup("ejb/com/boco/eoms/ejb/TawSystemUserEjbHome");
	// TawSystemUserEJBHome home =
	// (TawSystemUserEJBHome)ic.lookup("ejb/com/boco/eoms/system/ejb/TawSystemUserEJBHome");
	// // Convert the lookup result to the proper type
	//			
	// TawSystemUserEJB dao = home.create();
	// TawSystemUser user = new TawSystemUser();
	// user = dao.getUserInfoByUserID();
	// System.out.println(user.getUserid() + "&&&&&&&&&&&&&&&&&&&&&");
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//		
	// }
	//	

	public void testV() {

		List list = (List) TawSystemUserRoleBo.getInstance()
				.getUserByRoleidAndDeptid("13", "1");
		if (list != null && list.size() > 0) {
			System.out.println(list.size() + "************");
		}

	}
}
