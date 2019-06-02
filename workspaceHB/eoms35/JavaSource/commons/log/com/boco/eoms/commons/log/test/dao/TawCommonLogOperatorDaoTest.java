package com.boco.eoms.commons.log.test.dao;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.log.model.TawCommonLogOperator;
import com.boco.eoms.commons.log.dao.TawCommonLogOperatorDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonLogOperatorDaoTest extends BaseDaoTestCase {

	private TawCommonLogOperatorDao dao = null;

	private String userid = "admin";

	private String operid = "9988";

	private String opername = "转派";

	private String modelid = "99";

	private String modelname = "工单";

	private String operremark = "工单转派";

	private String remoteip = "192.168.0.1";

	private String issucess = "error";

	private String beginnotetime = "2007-11-29 20:20:20";

	private String notemessage = "派单报错";

	private String currentday = "26";

	private String currentmonth = "3";

	private String operatetime = "2007-3-26 16:13:20";

	private String bzremark = "工单的转派日志";

	private String url = "D://log//";

	public void setTawCommonLogOperatorDao(TawCommonLogOperatorDao dao) {
		this.dao = dao;
	}

	public void testAddTawCommonLogOperator() throws Exception {
		TawCommonLogOperator oper = new TawCommonLogOperator();

		oper.setBeginnotetime(beginnotetime);
		oper.setBzremark(bzremark);
		oper.setCurrentday(currentday);
		oper.setCurrentmonth(currentmonth);
		oper.setIssucess(issucess);
		oper.setModelid(modelid);
		oper.setModelname(modelname);
		oper.setNotemessage(notemessage);
		oper.setOperatetime(operatetime);
		oper.setOperid(operid);
		oper.setOpername(opername);
		oper.setOperremark(operremark);
		oper.setRemoteip(remoteip);
		oper.setUrl(url);
		oper.setUserid(userid);
		// set required fields

		dao.saveTawCommonLogOperator(oper);

		// verify a primary key was assigned
		assertSame(issucess, oper.getIssucess());
		assertSame(modelname, oper.getModelname());

		// verify set fields are same after save
	}

	public void testGetTawCommonLogOperator() throws Exception {
		TawCommonLogOperator oper = new TawCommonLogOperator();
		oper.setBeginnotetime(beginnotetime);
		oper.setBzremark(bzremark);
		oper.setCurrentday(currentday);
		oper.setCurrentmonth(currentmonth);
		oper.setIssucess(issucess);
		oper.setModelid(modelid);
		oper.setModelname(modelname);
		oper.setNotemessage(notemessage);
		oper.setOperatetime(operatetime);
		oper.setOperid(operid);
		oper.setOpername(opername);
		oper.setOperremark(operremark);
		oper.setRemoteip(remoteip);
		oper.setUrl(url);
		oper.setUserid(userid);
		dao.saveObject(oper);
		TawCommonLogOperator logoper = dao
				.getTawCommonLogOperator(oper.getId());

		assertSame(oper, logoper);
	}

	public void testGetTawCommonLogOperators() throws Exception {
		TawCommonLogOperator oper = new TawCommonLogOperator();

		oper.setBeginnotetime(beginnotetime);
		oper.setBzremark(bzremark);
		oper.setCurrentday(currentday);
		oper.setCurrentmonth(currentmonth);
		oper.setIssucess(issucess);
		oper.setModelid(modelid);
		oper.setModelname(modelname);
		oper.setNotemessage(notemessage);
		oper.setOperatetime(operatetime);
		oper.setOperid(operid);
		oper.setOpername(opername);
		oper.setOperremark(operremark);
		oper.setRemoteip(remoteip);
		oper.setUrl(url);
		oper.setUserid(userid);

		dao.saveTawCommonLogOperator(oper);

		List results = dao.getTawCommonLogOperators(oper);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawCommonLogOperator() throws Exception {
		TawCommonLogOperator oper = new TawCommonLogOperator();

		oper.setBeginnotetime(beginnotetime);
		oper.setBzremark(bzremark);
		oper.setCurrentday(currentday);
		oper.setCurrentmonth(currentmonth);
		oper.setIssucess(issucess);
		oper.setModelid(modelid);
		oper.setModelname(modelname);
		oper.setNotemessage(notemessage);
		oper.setOperatetime(operatetime);
		oper.setOperid(operid);
		oper.setOpername(opername);
		oper.setOperremark(operremark);
		oper.setRemoteip(remoteip);
		oper.setUrl(url);
		oper.setUserid(userid);

		dao.saveTawCommonLogOperator(oper);
		TawCommonLogOperator logoper = new TawCommonLogOperator();
		logoper = dao.getTawCommonLogOperator(oper.getId());
		dao.saveTawCommonLogOperator(logoper);

		assertSame(oper, logoper);

	}

	public void testRemoveTawCommonLogOperator() throws Exception {
		TawCommonLogOperator oper = new TawCommonLogOperator();

		oper.setBeginnotetime(beginnotetime);
		oper.setBzremark(bzremark);
		oper.setCurrentday(currentday);
		oper.setCurrentmonth(currentmonth);
		oper.setIssucess(issucess);
		oper.setModelid(modelid);
		oper.setModelname(modelname);
		oper.setNotemessage(notemessage);
		oper.setOperatetime(operatetime);
		oper.setOperid(operid);
		oper.setOpername(opername);
		oper.setOperremark(operremark);
		oper.setRemoteip(remoteip);
		oper.setUrl(url);
		oper.setUserid(userid);

		dao.saveTawCommonLogOperator(oper);
		String removeId = oper.getId();
		dao.removeTawCommonLogOperator(removeId);
		try {
			dao.getTawCommonLogOperator(removeId);
			fail("tawCommonLogOperator found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}

	public void testgetAllByUserIDs() {
		TawCommonLogOperator oper = new TawCommonLogOperator();

		oper.setBeginnotetime(beginnotetime);
		oper.setBzremark(bzremark);
		oper.setCurrentday(currentday);
		oper.setCurrentmonth(currentmonth);
		oper.setIssucess(issucess);
		oper.setModelid(modelid);
		oper.setModelname(modelname);
		oper.setNotemessage(notemessage);
		oper.setOperatetime(operatetime);
		oper.setOperid(operid);
		oper.setOpername(opername);
		oper.setOperremark(operremark);
		oper.setRemoteip(remoteip);
		oper.setUrl(url);
		oper.setUserid(userid);

		dao.saveTawCommonLogOperator(oper);
		List listres = new ArrayList();

		listres = dao.getAllByUserIDs(oper.getUserid(), "error");

		assertTrue(listres.size() > 0);
	}

}
