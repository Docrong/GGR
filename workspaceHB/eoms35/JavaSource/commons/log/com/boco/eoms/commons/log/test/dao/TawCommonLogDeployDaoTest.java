package com.boco.eoms.commons.log.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;

import com.boco.eoms.commons.log.model.TawCommonLogDeploy;

import com.boco.eoms.commons.log.dao.TawCommonLogDeployDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonLogDeployDaoTest extends BaseDaoTestCase {

	private TawCommonLogDeployDao dao = null;

	private String userid = "admin";

	private String operid = "9988";

	private String opername = "转派";

	private String modelid = "99";

	private String loglevel = "error";

	private String isdebug = "0";

	private String noteremark = "工单转派";

	private String savetype = "0";

	private String beginnotetime = "2007-11-29 20:20:20";

	public void setTawCommonLogDeployDao(TawCommonLogDeployDao dao) {
		this.dao = dao;
	}

	public void testAddTawCommonLogDeploy() throws Exception {
		TawCommonLogDeploy tawCommonLogDeploy = new TawCommonLogDeploy();

		// set required fields

		dao.saveTawCommonLogDeploy(tawCommonLogDeploy);

		// verify a primary key was assigned
		assertNotNull(tawCommonLogDeploy.getId());

		// verify set fields are same after save
	}

	public void testGetTawCommonLogDeploy() throws Exception {
		TawCommonLogDeploy deploy = new TawCommonLogDeploy();
		deploy.setBeginnotetime(beginnotetime);
		deploy.setFilecutsize(new Integer(15));
		deploy.setFilesavepath("D://");
		deploy.setIsdebug(isdebug);
		deploy.setLoglevel(loglevel);
		deploy.setModelid(modelid);
		deploy.setModelname("gongdan");
		deploy.setNoteremark(noteremark);
		deploy.setOperdesc("gongdanzhuangpai");
		deploy.setOperid(operid);
		deploy.setOpername(opername);
		deploy.setSavetype(savetype);
		deploy.setUserid(userid);

		dao.saveTawCommonLogDeploy(deploy);
		TawCommonLogDeploy tlog = new TawCommonLogDeploy();
		tlog = dao.getTawCommonLogDeploy(deploy.getId());

		assertSame(deploy, tlog);
	}

	public void testGetTawCommonLogDeploys() throws Exception {
		TawCommonLogDeploy tawCommonLogDeploy = new TawCommonLogDeploy();

		List results = dao.getTawCommonLogDeploys(tawCommonLogDeploy);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawCommonLogDeploy() throws Exception {
		TawCommonLogDeploy deploy = new TawCommonLogDeploy();
		deploy.setBeginnotetime(beginnotetime);
		deploy.setFilecutsize(new Integer(15));
		deploy.setFilesavepath("D://");
		deploy.setIsdebug(isdebug);
		deploy.setLoglevel(loglevel);
		deploy.setModelid(modelid);
		deploy.setModelname("gongdan");
		deploy.setNoteremark(noteremark);
		deploy.setOperdesc("gongdanzhuangpai");
		deploy.setOperid(operid);
		deploy.setOpername(opername);
		deploy.setSavetype(savetype);
		deploy.setUserid(userid);

		dao.saveTawCommonLogDeploy(deploy);
		TawCommonLogDeploy tawCommonLogDeploy = dao
				.getTawCommonLogDeploy(deploy.getId());

		// update required fields

		dao.saveTawCommonLogDeploy(tawCommonLogDeploy);
		assertSame(deploy, tawCommonLogDeploy);
	}

	public void testRemoveTawCommonLogDeploy() throws Exception {
		TawCommonLogDeploy deploy = new TawCommonLogDeploy();
		deploy.setBeginnotetime(beginnotetime);
		deploy.setFilecutsize(new Integer(15));
		deploy.setFilesavepath("D://");
		deploy.setIsdebug(isdebug);
		deploy.setLoglevel(loglevel);
		deploy.setModelid(modelid);
		deploy.setModelname("gongdan");
		deploy.setNoteremark(noteremark);
		deploy.setOperdesc("gongdanzhuangpai");
		deploy.setOperid(operid);
		deploy.setOpername(opername);
		deploy.setSavetype(savetype);
		deploy.setUserid(userid);

		dao.saveTawCommonLogDeploy(deploy);

		dao.removeTawCommonLogDeploy(deploy.getId());
		try {
			TawCommonLogDeploy deploys = new TawCommonLogDeploy();
			deploys = dao.getTawCommonLogDeploy(deploy.getId());
			assertNull(deploys);
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}

	public void testSaveLog() {
		TawCommonLogDeploy deploy = new TawCommonLogDeploy();
		deploy.setBeginnotetime(beginnotetime);
		deploy.setFilecutsize(new Integer(15));
		deploy.setFilesavepath("D://");
		deploy.setIsdebug(isdebug);
		deploy.setLoglevel(loglevel);
		deploy.setModelid(modelid);
		deploy.setModelname("gongdan");
		deploy.setNoteremark(noteremark);
		deploy.setOperdesc("gongdanzhuangpai");
		deploy.setOperid(operid);
		deploy.setOpername(opername);
		deploy.setSavetype(savetype);
		deploy.setUserid(userid);

		dao.saveTawCommonLogDeploy(deploy);
		TawCommonLogDeploy tlog = new TawCommonLogDeploy();
		tlog = dao.getTawCommonLogDeploy(deploy.getId());
		assertSame(deploy, tlog);

	}
}
