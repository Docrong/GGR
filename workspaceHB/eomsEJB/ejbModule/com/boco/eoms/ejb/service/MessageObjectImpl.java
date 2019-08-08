package com.boco.eoms.ejb.service;

import javax.naming.InitialContext;

import com.boco.eoms.sheet.base.service.ejb.MessageService;
import com.boco.eoms.sheet.base.service.ejb.MessageServiceHome;
import commonj.sdo.DataObject;

public class MessageObjectImpl {
    private MessageService messageService;

    public MessageObjectImpl() {
    }

    /**
     * @return Returns the saveDataService.
     */

    public MessageService getMessageService() throws Exception {
        InitialContext initialContext = new InitialContext();
        MessageServiceHome home
                = (MessageServiceHome) initialContext.lookup("ejb/com/boco/eoms/sheet/base/service/ejb/MessageServiceHome");
        messageService = home.create();
        //"ejb/com/boco/eoms/sheet/base/service/ejb/SaveDataServiceHome";
        return messageService;
    }


    public void sendMsg(DataObject mainObject, String workflowName, String sheetKey, String sheetId, String title,
                        int receiveType, String receiverId, String acceptLimitTime, String dealLimitTime) throws Exception {
        this.getMessageService().sendMsg(mainObject, workflowName, sheetKey, sheetId, title,
                receiveType, receiverId, acceptLimitTime, dealLimitTime);
    }


    public void sendMsgHie(String workflowName, String sheetKey, String sheetId,
                           String title, int receiveType, String receiverId) throws Exception {
        this.getMessageService().sendMsgHie(workflowName, sheetKey, sheetId, title, receiveType, receiverId);
    }

    public void closeMsg(String sheetKey, String workflowName, int closeMsgType) throws Exception {
        this.getMessageService().closeMsg(sheetKey, workflowName, closeMsgType);
    }

    //zhangying 添加 试点工单催办启动实施工单
    public void sendMsgHie(String workflowName, String sheetKey, String sheetId,
                           int receiveType, String receiverId) throws Exception {
        this.getMessageService().sendMsgHie(workflowName, sheetKey, sheetId, receiveType, receiverId);
    }

}
