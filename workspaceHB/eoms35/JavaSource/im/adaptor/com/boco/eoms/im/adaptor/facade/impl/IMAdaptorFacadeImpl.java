package com.boco.eoms.im.adaptor.facade.impl;

import java.io.File;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.im.adaptor.exception.IMAdaptorCancelMsgErrorException;
import com.boco.eoms.im.adaptor.exception.IMAdaptorSendFileErrorException;
import com.boco.eoms.im.adaptor.exception.IMAdaptorSendMsgErrorException;
import com.boco.eoms.im.adaptor.facade.IMAdaptorFacade;
import com.boco.eoms.im.adaptor.listener.IMFileListener;
import com.boco.eoms.im.adaptor.listener.IMMsgListener;
import com.boco.eoms.im.adaptor.mgr.IMAdaptorMgr;
import com.boco.eoms.pq.facade.IPQFacade;
import com.boco.eoms.sequence.Job;

public class IMAdaptorFacadeImpl implements IMAdaptorFacade {

	// IMAdaptorMgr imAdaptorMgr=(IMAdaptorMgr)ApplicationContextHolder.
	// getInstance().getBean("imMgr");

	public void cancelMsg(String id) throws IMAdaptorCancelMsgErrorException {
		IMAdaptorMgr imAdaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
				.getInstance().getBean("imMgr");
		imAdaptorMgr.cancelMsg(id);

	}

	public void sendFile(File file, String toOrgIds,
			IMFileListener imFileListener)
			throws IMAdaptorSendFileErrorException {
		IMAdaptorMgr imAdaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
				.getInstance().getBean("imMgr");
		// imAdaptorMgr.sendFile(file, toOrgIds, imFileListener);

		String methodName = "sendFile";
		Class[] paramTypes = new Class[] { java.io.File.class,
				java.lang.String.class, java.lang.String.class };
		Object[] params = new Object[] { file, toOrgIds, imFileListener };
		Job job = new Job(imAdaptorMgr, methodName, paramTypes, params, null);
		IPQFacade ipQFacade = (IPQFacade) ApplicationContextHolder
				.getInstance().getBean("pqFacade");
		String id = ipQFacade.putPQ(job);
		ipQFacade.doPQ(id);

	}

	public void sendFile(File file, String toOrgIds)
			throws IMAdaptorSendFileErrorException {
		IMAdaptorMgr imAdaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
				.getInstance().getBean("imMgr");
		// imAdaptorMgr.sendFile(file, toOrgIds);

		String methodName = "sendFile";
		Class[] paramTypes = new Class[] { java.io.File.class,
				java.lang.String.class };
		Object[] params = new Object[] { file, toOrgIds };
		Job job = new Job(imAdaptorMgr, methodName, paramTypes, params, null);
		IPQFacade ipQFacade = (IPQFacade) ApplicationContextHolder
				.getInstance().getBean("pqFacade");
		String id = ipQFacade.putPQ(job);
		ipQFacade.doPQ(id);

	}

	public void sendFile(String user, String passwd, File file,
			String toOrgIds, IMFileListener imFileListener)
			throws IMAdaptorSendFileErrorException {
		IMAdaptorMgr imAdaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
				.getInstance().getBean("imMgr");
		// imAdaptorMgr.sendFile(user, passwd, file, toOrgIds, imFileListener);

		String methodName = "sendFile";
		Class[] paramTypes = new Class[] { java.lang.String.class,
				java.lang.String.class, java.io.File.class,
				java.lang.String.class, IMFileListener.class };
		Object[] params = new Object[] { user, passwd, file, toOrgIds,
				imFileListener };
		Job job = new Job(imAdaptorMgr, methodName, paramTypes, params, null);
		IPQFacade ipQFacade = (IPQFacade) ApplicationContextHolder
				.getInstance().getBean("pqFacade");
		String id = ipQFacade.putPQ(job);
		ipQFacade.doPQ(id);

	}

	public void sendFile(String user, String passwd, File file, String toOrgIds)
			throws IMAdaptorSendFileErrorException {
		IMAdaptorMgr imAdaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
				.getInstance().getBean("imMgr");
		// imAdaptorMgr.sendFile(user, passwd, file, toOrgIds);

		String methodName = "sendFile";
		Class[] paramTypes = new Class[] { java.lang.String.class,
				java.lang.String.class, java.io.File.class,
				java.lang.String.class, };
		Object[] params = new Object[] { user, passwd, file, toOrgIds };
		Job job = new Job(imAdaptorMgr, methodName, paramTypes, params, null);
		IPQFacade ipQFacade = (IPQFacade) ApplicationContextHolder
				.getInstance().getBean("pqFacade");
		String id = ipQFacade.putPQ(job);
		ipQFacade.doPQ(id);

	}

	public void sendFile(String filePath, String toOrgIds,
			IMFileListener imFileListener)
			throws IMAdaptorSendFileErrorException {
		IMAdaptorMgr imAdaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
				.getInstance().getBean("imMgr");
		// imAdaptorMgr.sendFile(filePath, toOrgIds, imFileListener);

		String methodName = "sendFile";
		Class[] paramTypes = new Class[] { java.lang.String.class,
				java.lang.String.class, IMFileListener.class };
		Object[] params = new Object[] { filePath, toOrgIds, imFileListener };
		Job job = new Job(imAdaptorMgr, methodName, paramTypes, params, null);
		IPQFacade ipQFacade = (IPQFacade) ApplicationContextHolder
				.getInstance().getBean("pqFacade");
		String id = ipQFacade.putPQ(job);
		ipQFacade.doPQ(id);

	}

	public void sendFile(String user, String passwd, String filePath,
			String toOrgIds, IMFileListener imFileListener)
			throws IMAdaptorSendFileErrorException {
		IMAdaptorMgr imAdaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
				.getInstance().getBean("imMgr");
		// imAdaptorMgr.sendFile(user, passwd, filePath, toOrgIds,
		// imFileListener);

		String methodName = "sendFile";
		Class[] paramTypes = new Class[] { java.lang.String.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class, IMFileListener.class };
		Object[] params = new Object[] { user, passwd, filePath, toOrgIds,
				imFileListener };
		Job job = new Job(imAdaptorMgr, methodName, paramTypes, params, null);
		IPQFacade ipQFacade = (IPQFacade) ApplicationContextHolder
				.getInstance().getBean("pqFacade");
		String id = ipQFacade.putPQ(job);
		ipQFacade.doPQ(id);

	}

	public void sendFile(String user, String passwd, String filePath,
			String toOrgIds) throws IMAdaptorSendFileErrorException {
		IMAdaptorMgr imAdaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
				.getInstance().getBean("imMgr");
		// imAdaptorMgr.sendFile(user, passwd, filePath, toOrgIds);

		String methodName = "sendFile";
		Class[] paramTypes = new Class[] { java.lang.String.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class, };
		Object[] params = new Object[] { user, passwd, filePath, toOrgIds };
		Job job = new Job(imAdaptorMgr, methodName, paramTypes, params, null);
		IPQFacade ipQFacade = (IPQFacade) ApplicationContextHolder
				.getInstance().getBean("pqFacade");
		String id = ipQFacade.putPQ(job);
		ipQFacade.doPQ(id);

	}

	public void sendFile(String filePath, String toOrgIds)
			throws IMAdaptorSendFileErrorException {
		IMAdaptorMgr imAdaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
				.getInstance().getBean("imMgr");
		// imAdaptorMgr.sendFile(filePath, toOrgIds);

		String methodName = "sendFile";
		Class[] paramTypes = new Class[] { java.lang.String.class,
				java.lang.String.class };
		Object[] params = new Object[] { filePath, toOrgIds };
		Job job = new Job(imAdaptorMgr, methodName, paramTypes, params, null);
		IPQFacade ipQFacade = (IPQFacade) ApplicationContextHolder
				.getInstance().getBean("pqFacade");
		String id = ipQFacade.putPQ(job);
		ipQFacade.doPQ(id);

	}

	public String sendMsg(String toOrgIds, String body,
			IMMsgListener imMsgListener) throws IMAdaptorSendMsgErrorException {
		IMAdaptorMgr imAdaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
				.getInstance().getBean("imMgr");
		// return imAdaptorMgr.sendMsg(toOrgIds, body, imMsgListener);

		String methodName = "sendMsg";
		Class[] paramTypes = new Class[] { java.lang.String.class,
				java.lang.String.class, IMMsgListener.class };
		Object[] params = new Object[] { toOrgIds, body, imMsgListener };
		Job job = new Job(imAdaptorMgr, methodName, paramTypes, params, null);
		IPQFacade ipQFacade = (IPQFacade) ApplicationContextHolder
				.getInstance().getBean("pqFacade");
		String id = ipQFacade.putPQ(job);
		ipQFacade.doPQ(id);
		return id;
	}

	public String sendMsg(String user, String passwd, String toOrgIds,
			String body, IMMsgListener imMsgListener)
			throws IMAdaptorSendMsgErrorException {
		IMAdaptorMgr imAdaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
				.getInstance().getBean("imMgr");
		// return imAdaptorMgr
		// .sendMsg(user, passwd, toOrgIds, body, imMsgListener);

		String methodName = "sendMsg";
		Class[] paramTypes = new Class[] { java.lang.String.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class, IMMsgListener.class };
		Object[] params = new Object[] { user, passwd, toOrgIds, body,
				imMsgListener };
		Job job = new Job(imAdaptorMgr, methodName, paramTypes, params, null);
		IPQFacade ipQFacade = (IPQFacade) ApplicationContextHolder
				.getInstance().getBean("pqFacade");
		String id = ipQFacade.putPQ(job);
		ipQFacade.doPQ(id);
		return id;
	}

	public String sendMsg(String user, String passwd, String toOrgIds,
			String body) throws IMAdaptorSendMsgErrorException {
		IMAdaptorMgr imAdaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
				.getInstance().getBean("imMgr");
		// return imAdaptorMgr.sendMsg(user, passwd, toOrgIds, body);

		String methodName = "sendMsg";
		Class[] paramTypes = new Class[] { java.lang.String.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class };
		Object[] params = new Object[] { user, passwd, toOrgIds, body };
		Job job = new Job(imAdaptorMgr, methodName, paramTypes, params, null);
		IPQFacade ipQFacade = (IPQFacade) ApplicationContextHolder
				.getInstance().getBean("pqFacade");
		String id = ipQFacade.putPQ(job);
		ipQFacade.doPQ(id);
		return id;
	}

	public String sendMsg(String toOrgIds, String body)
			throws IMAdaptorSendMsgErrorException {
		IMAdaptorMgr imAdaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
				.getInstance().getBean("imMgr");
//		 imAdaptorMgr.sendMsg(toOrgIds, body);
//		 return body;

		String methodName = "sendMsg";
		Class[] paramTypes = new Class[] { java.lang.String.class,
				java.lang.String.class };
		Object[] params = new Object[] { toOrgIds, body };
		Job job = new Job(imAdaptorMgr, methodName, paramTypes, params, null);
		IPQFacade ipQFacade = (IPQFacade) ApplicationContextHolder
				.getInstance().getBean("pqFacade");
		String id = ipQFacade.putPQ(job);
		ipQFacade.doPQ(id);
		return id;

	}

}
