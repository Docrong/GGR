package com.boco.eoms.ejb.bo;

import com.boco.eoms.ejb.service.MessageObjectImpl;
import commonj.sdo.DataObject;

public class MessageObjectBO {

    private static MessageObjectBO objectBO = null;


    private MessageObjectBO() {

    }

    public static MessageObjectBO getInstance() {
        if (objectBO == null) {
            objectBO = init();
        }
        return objectBO;
    }

    private static MessageObjectBO init() {
        objectBO = new MessageObjectBO();
        return objectBO;
    }

    public void sendMsg(DataObject mainObject, String workflowName, String sheetKey, String sheetId, String title,
                        int receiveType, String receiverId, String acceptLimitTime, String dealLimitTime) throws Exception {
        MessageObjectImpl object = new MessageObjectImpl();
        object.sendMsg(mainObject, workflowName, sheetKey, sheetId, title, receiveType,
                receiverId, acceptLimitTime, dealLimitTime);
    }

    public void sendMsgHie(String workflowName, String sheetKey, String sheetId,
                           String title, int receiveType, String receiverId) throws Exception {
        MessageObjectImpl object = new MessageObjectImpl();
        object.sendMsgHie(workflowName, sheetKey, sheetId, title, receiveType, receiverId);
    }

    public void closeMsg(String sheetKey, String workflowName, int closeMsgType) throws Exception {
        MessageObjectImpl object = new MessageObjectImpl();
        object.closeMsg(sheetKey, workflowName, closeMsgType);
    }

    //zhangying 添加 试点工单催办启动实施工单
    public void sendMsgHie(String workflowName, String sheetKey, String sheetId,
                           int receiveType, String receiverId) throws Exception {
        MessageObjectImpl object = new MessageObjectImpl();
        object.sendMsgHie(workflowName, sheetKey, sheetId, receiveType, receiverId);
    }

}
