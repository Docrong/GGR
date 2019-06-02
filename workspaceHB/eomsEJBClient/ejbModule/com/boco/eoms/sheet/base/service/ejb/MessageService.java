package com.boco.eoms.sheet.base.service.ejb;

import commonj.sdo.DataObject;

/**
 * Remote interface for Enterprise Bean: MessageService
 */
public interface MessageService extends javax.ejb.EJBObject {

	public void sendMsg(DataObject mainObject,String workflowName, String sheetKey,String sheetId, String title,
            int receiveType,String receiverId,String acceptLimitTime,String dealLimitTime) throws Exception;
    
    public void sendMsgHie(String workflowName,String sheetKey,String sheetId,
		               String title,int receiveType,String receiverId) throws Exception;
    public void sendMsgHie(String workflowName,String sheetKey,String sheetId,
                       int receiveType,String receiverId) throws Exception;
	public void closeMsg(String sheetKey,String workflowName,int closeMsgType) throws Exception;		             
}
