package com.boco.eoms.workbench.memo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.message.util.MsgMgrLocator;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContact;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemo;
import com.boco.eoms.workbench.memo.dao.TawWorkbenchMemoDao;
import com.boco.eoms.workbench.memo.service.ITawWorkbenchMemoManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.workbench.memo.util.MemoAttributeLocator;

public class TawWorkbenchMemoManagerImpl extends BaseManager implements
		ITawWorkbenchMemoManager {
	private TawWorkbenchMemoDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawWorkbenchMemoDao(TawWorkbenchMemoDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.workbench.service.ITawWorkbenchMemoManager#getTawWorkbenchMemos(com.boco.eoms.commons.workbench.model.TawWorkbenchMemo)
	 */
	public List getTawWorkbenchMemos(final TawWorkbenchMemo tawWorkbenchMemo) {
		return dao.getTawWorkbenchMemos(tawWorkbenchMemo);
	}

	/**
	 * @see com.boco.eoms.commons.workbench.service.ITawWorkbenchMemoManager#getTawWorkbenchMemo(String
	 *      id)
	 */
	public TawWorkbenchMemo getTawWorkbenchMemo(final String id) {
		return dao.getTawWorkbenchMemo(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.workbench.service.ITawWorkbenchMemoManager#saveTawWorkbenchMemo(TawWorkbenchMemo
	 *      tawWorkbenchMemo)
	 */
	public void saveTawWorkbenchMemo(TawWorkbenchMemo tawWorkbenchMemo) {

		dao.saveTawWorkbenchMemo(tawWorkbenchMemo);
	}

	/**
	 * @see com.boco.eoms.commons.workbench.service.ITawWorkbenchMemoManager#removeTawWorkbenchMemo(String
	 *      id)
	 */
	public void removeTawWorkbenchMemo(final String id) {
		dao.removeTawWorkbenchMemo(new String(id));
	}

	/**
	 * 
	 */
	public Map getTawWorkbenchMemos(final Integer curPage,
			final Integer pageSize) {
		return dao.getTawWorkbenchMemos(curPage, pageSize, null);
	}

	public Map getTawWorkbenchMemos(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getTawWorkbenchMemos(curPage, pageSize, whereStr);
	}

	/**
	 * 查询某用户所有便笺 Parameters: userId - 用户Id Returns: List 便笺列表
	 * 
	 */
	public java.util.List listTawWorkbenchMemo(final String userId) {
		List list = new ArrayList();
		return list;
	}

	/**
	 * 根据用户Id和所属象限查询便笺 Parameters: userId - 用户Id quadrant -
	 * 便笺所属象限，即重要紧急的级别（1：重要紧急，2：重要非紧急，3：紧急非重要，4：非重要非紧急） Returns: List 便笺列表
	 */
	public java.util.List listTawWorkbenchMemo(final String userId,
			final int quadrant) {
		List list = new ArrayList();
		return list;
	}

	/**
	 * 发送便笺 Parameters: tawWorkbenchMemo - 便笺信息 recievers - 接收者 sendManner -
	 * 发送方式（短信、Email等） Returns: 发送操作结果（true：发送成功，false：发送失败）
	 */
	public boolean sendMemo(
			com.boco.eoms.workbench.memo.model.TawWorkbenchMemo tawWorkbenchMemo,
			java.lang.String recievers, java.lang.String sendManner,java.lang.String userid) {
		boolean bool = true;
		ITawWorkbenchContactManager mgr = (ITawWorkbenchContactManager) ApplicationContextHolder
				.getInstance().getBean("ItawWorkbenchContactManager");
		TawWorkbenchContact tawWorkbenchContact = new TawWorkbenchContact();
		StringBuffer tele = new StringBuffer();
		String teleNO = "";
		String mailStr = "";
		 StringBuffer email = new StringBuffer();
		int level = Integer.parseInt(String.valueOf(tawWorkbenchMemo.getLevel()));
		String levelName = "";
		switch (level) {
		case 1:
			levelName = "重要紧急";
			break;
		case 2:
			levelName = "重要不紧急";
			break;
		case 3:
			levelName = "紧急不重要";
			break;
		case 4:
			levelName = "不重要不紧急";
			break;
		 
		}
		
		try{
		if (recievers != null) {
			String[] reciever_id = recievers.split(",");
			for (int i = 0; i < reciever_id.length; i++) {

				tawWorkbenchContact = mgr
						.getTawWorkbenchContact(reciever_id[i]);
				if (tawWorkbenchContact.getTele().indexOf(",") > 0) {
					String[] telStr = tawWorkbenchContact.getTele().split(",");
					
					for (int j = 0; j < telStr.length; j++) {
						tele.append(telStr[j]);

						tele.append(",");
					}
				} else {
					tele.append(tawWorkbenchContact.getTele());
					tele.append(",");
				}
				email.append(tawWorkbenchContact.getEmail());
				email.append(",");
				teleNO = tele.toString().substring(0, tele.length() - 1);
			}
			mailStr = email.toString().substring(0, email.length() - 1);
		}
		// 服务id
		String serverId = MemoAttributeLocator.getMemoAttributes()
				.getMemoServerID();
		// 取当前时间
		String creattime = StaticMethod.getLocalString();
		// 拼内容字符串
		String content = "您好！您的同事"+userid+"给你发送了一个标题为:" + tawWorkbenchMemo.getTitle() + "，紧急程度为"+levelName+"的便签，内容为:"
				+ tawWorkbenchMemo.getContent() + "。" + creattime;
		// 业务id
		String MemoId = tawWorkbenchMemo.getId();
		System.out.println(content);
		MsgServiceImpl   msgService = new MsgServiceImpl();
		if (sendManner.equals("1")) { // 短信发送
			  msgService.sendMsg4Mobiles(serverId, content,
					MemoId, teleNO, creattime);//sendMsg4Mobile
			
			//String tel = mgr.getTeleByUserId(sendManner);// 根据用户名得到这个用户的tel
			// 调用接口派发短信

		}
		if (sendManner.equals("2")) { // email
			//TODO
//			msgService.sendEmail4Org(serverId, content,
//					MemoId, mailStr, creattime);

		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * 保存便笺发送记录 Parameters: tawWorkbenchMemoLog - 便笺发送记录 Returns:
	 * 保存发送记录结果（true：保存成功，false：保存失败）
	 */
	public boolean saveTawWorkbenchMemoLog(
			com.boco.eoms.workbench.memo.model.TawWorkbenchMemoSendLog tawWorkbenchMemoSendLog) {
		boolean bool = false;
		return bool;

	}

	/**
	 * 查询某用户已发送便笺 Parameters: userId - 用户Id sendFlag - 发送标志（0：未发送，1：已发送）
	 * Returns: List 已发送便笺列表
	 */
	public java.util.List listTawWorkbenchMemoSend(java.lang.String userId,
			int sendFlag) {
		List list = new ArrayList();
		return list;
	} 
	public String saveTawWorkbenchMemoReturnId(TawWorkbenchMemo tawWorkbenchMemo){
		return dao.saveTawWorkbenchMemoReturnId(tawWorkbenchMemo);
		
	}
	/*
	 *  
	 */
	public boolean ifSystemUser(String user){
		return dao.ifSystemUser(user);
	}
}
