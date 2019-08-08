package com.boco.eoms.sheet.base.sms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.EOMSAttributes;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.message.mgr.ISmsSheetContentManager;
import com.boco.eoms.message.model.SmsSheetContent;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.Sequence;
import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetStaticMethod;
import commonj.sdo.DataObject;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.service.ID2NameService;

public class WorkSheetSmsServices {

    public WorkSheetSmsServices() {
    }

    private static final String CONFIG_FILEPATH = "classpath:config/worksheet-sms-service-info.xml";//短信服务信息配置文件


    /**
     * @param workflowName    流程名称
     * @param sheetKey        工单主健ID
     * @param sheetId         工单流水号
     * @param title           工单主题
     * @param receiveType     短信接受者类型
     * @param receiverId      短信接收者Id
     * @param acceptLimitTime 接受时限
     * @param dealLimitTime   处理时限
     * @see 工单派发的时候，进行短信提醒；包括即时提醒、超时前提醒以及超时后提醒
     * 此方法一般用于普通人工任务生成时调用
     * 修改此方法，将短信派发放置到队列中。 modify by 秦敏 20090910
     */
/*   public void workSM_T(DataObject mainObject,String workflowName,String sheetKey,String sheetId,String title,int receiveType,
		                 String receiverId,String acceptLimitTime,String dealLimitTime) throws Exception {	   
     try {
   
    	  String sequenceOpen = StaticMethod.null2String(((EOMSAttributes) ApplicationContextHolder
					.getInstance().getBean("eomsAttributes")).getSequenceOpen());
    	  WorkSheetSmsUtils  smsUtils=new WorkSheetSmsUtils();
	     if ("true".equals(sequenceOpen)) {
		 // 初始化队列
	     ISequenceFacade sequenceFacade = SequenceLocator.getSequenceFacade();
	     Sequence sendmsgSequence = null;
	     sendmsgSequence = sequenceFacade.getSequence("sendMsg");
	     // 把mgr撇队列里	     
		 sequenceFacade.put(smsUtils, "sendMsg", 
			new Class[] {commonj.sdo.DataObject.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.Integer.class,java.lang.String.class,java.lang.String.class,java.lang.String.class},
			new Object[] {mainObject,workflowName, sheetKey, sheetId, title, new Integer(receiveType), receiverId, acceptLimitTime, dealLimitTime}, null,
			sendmsgSequence);
		 sendmsgSequence.setChanged();
		 sequenceFacade.doJob(sendmsgSequence); 
	  }else{
		  smsUtils.sendMsg(mainObject,workflowName, sheetKey, sheetId, title, new Integer(receiveType), receiverId, acceptLimitTime, dealLimitTime); 	  
	   }
    }
     catch (Exception e){
    	 throw new Exception("send message exception,error info is"+e.getMessage()); 
     }
    
   }   
*/
    public void workSM_T(DataObject mainObject, String workflowName, String sheetKey, String sheetId, String title, int receiveType,
                         String receiverId, String acceptLimitTime, String dealLimitTime) throws Exception {
        try {
            MsgServiceImpl msgService = new MsgServiceImpl();

//		   解析短信服务信息文件，获取工单中文名称，以及短信服务ID
            String nodeCnName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_PROCESS_CN_NAME;
//		   短信立即发送
            String nodeInstantName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_SERVICE_INSTANT;
//		   短信提前发送
            String nodePreOvertimeAccpetName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_SERVICE_PREOVERTIME_ACCEPT;
            String nodePreOvertimeDealName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_SERVICE_PREOVERTIME_DEAL;
//		   短信超时发送
            String nodePostOvertimeAcceptName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_SERVICE_POSTOVERTIME_ACCPT;
            String nodePostOvertimeDealName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_SERVICE_POSTOVERTIME_DEAL;

            String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
            String processCnName = SheetStaticMethod.getNodeName(filePath, nodeCnName);
            String instantServiceId = SheetStaticMethod.getNodeName(filePath, nodeInstantName);
            String preOvertimeAcceptServiceId = SheetStaticMethod.getNodeName(filePath, nodePreOvertimeAccpetName);
            String preOvertimeDealServiceId = SheetStaticMethod.getNodeName(filePath, nodePreOvertimeDealName);
            String postOvertimeAcceptServiceId = SheetStaticMethod.getNodeName(filePath, nodePostOvertimeAcceptName);
            String postOvertimeDealServiceId = SheetStaticMethod.getNodeName(filePath, nodePostOvertimeDealName);

//		   拼接短信接受者
            String receivers = "";
            if (receiveType == Constants.SMS_RECEIVE_TYPE_USER) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_USER + "," + receiverId + "#";
            } else if (receiveType == Constants.SMS_RECEIVE_TYPE_DEPT) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_DEPT + "," + receiverId + "#";
            } else if (receiveType == Constants.SMS_RECEIVE_TYPE_ROLE) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_ROLE + "," + receiverId + "#";
            }
//		   add by hanlili --start
            ISmsSheetContentManager isscm = (ISmsSheetContentManager) ApplicationContextHolder.getInstance().getBean("ISmsSheetContentManager");
//		   --end
            if (!receivers.equals(""))
                receivers = receivers.substring(0, receivers.lastIndexOf("#"));

            System.out.println("receivers=" + receivers);

//		   派发通知（即时提醒）
            if (!instantServiceId.equals("")) {
//			   获得当前时间 
                Date currentTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sendTime = formatter.format(currentTime);
//			   拼写发送信息
                String sendContent = "提醒您收取" + processCnName + ":" + sheetId + ",主题名:" + title;

//			   start add by mym 2010/04/21 投诉工单派发督办工单短信提醒
                //add by hanlili --start
                List list = isscm.getRecordByFlowNameAndType(workflowName, "2");
                if (list.size() > 0) {
                    SmsSheetContent ssc = (SmsSheetContent) list.get(0);
                    sendContent = returnContent(mainObject, ssc.getContent());
//					   msgService.sendMsg(instantServiceId, sendContent, sheetKey, receivers, sendTime);
                } else {
                    BocoLog.info(this, "流程名为：" + workflowName + "的工单，即时提醒服务id为：" + instantServiceId + "的服务未配置发送内容，故不发送");
                }
                //--end


            }

//		   接单超时前提醒  (短信受理提前提醒)
            if (!preOvertimeAcceptServiceId.equals("") && !acceptLimitTime.equals("")) {
                List list = isscm.getRecordByFlowNameAndType(workflowName, "0");
                if (list.size() > 0) {
                    SmsSheetContent ssc = (SmsSheetContent) list.get(0);
                    String sendContent = returnContent(mainObject, ssc.getContent());
                    //String sendContent= "提醒您," + processCnName +":"+sheetId + "即将于"+acceptLimitTime+"超时，请及时接收！" ;
                    msgService.sendMsg(preOvertimeAcceptServiceId, sendContent,
                            sheetKey, receivers, acceptLimitTime);
                } else {
                    BocoLog.info(this, "流程名为：" + workflowName + "的工单，短信受理提前提醒服务id为：" + preOvertimeAcceptServiceId + "的服务未配置发送内容，故不发送");
                }

            }
//		   处理超时提醒	  (短信处理提前提醒)
            else if (!preOvertimeDealServiceId.equals("") && !dealLimitTime.equals("")) {
                List list = isscm.getRecordByFlowNameAndType(workflowName, "1");
                if (list.size() > 0) {
                    SmsSheetContent ssc = (SmsSheetContent) list.get(0);
                    String sendContent = returnContent(mainObject, ssc.getContent());
                    // String sendContent= "提醒您," + processCnName +":"+sheetId + "即将于"+acceptLimitTime+"超时，请及时处理！" ;
                    msgService.sendMsg(preOvertimeDealServiceId, sendContent,
                            sheetKey, receivers, acceptLimitTime);
                } else {
                    BocoLog.info(this, "流程名为：" + workflowName + "的工单，处理超时提醒服务id为：" + preOvertimeDealServiceId + "的服务未配置发送内容，故不发送");
                }

            }

//		   接单超时后提醒  (短信超时提醒)
            if (!postOvertimeAcceptServiceId.equals("") && !acceptLimitTime.equals("")) {
                List list = isscm.getRecordByFlowNameAndType(workflowName, "3");
                if (list.size() > 0) {
                    SmsSheetContent ssc = (SmsSheetContent) list.get(0);
                    String sendContent = returnContent(mainObject, ssc.getContent());
                    //String sendContent= "提醒您," + processCnName +":"+sheetId + "已经超时，请及时接收！" ;
                    msgService.sendMsg(postOvertimeAcceptServiceId, sendContent,
                            sheetKey, receivers, acceptLimitTime);
                } else {
                    BocoLog.info(this, "流程名为：" + workflowName + "的工单，短信超时提醒服务id为：" + postOvertimeAcceptServiceId + "的服务未配置发送内容，故不发送");
                }
            } else if (!postOvertimeDealServiceId.equals("") && !dealLimitTime.equals("")) {
//			   处理即将超时提醒	(短信回复超时提醒)
                List list = isscm.getRecordByFlowNameAndType(workflowName, "4");
                if (list.size() > 0) {
                    SmsSheetContent ssc = (SmsSheetContent) list.get(0);
                    //String sendContent= "提醒您," + processCnName +":"+sheetId + "已经超时，请及时处理！" ;
                    String sendContent = returnContent(mainObject, ssc.getContent());
                    msgService.sendMsg(postOvertimeDealServiceId, sendContent,
                            sheetKey, receivers, acceptLimitTime);
                } else {
                    BocoLog.info(this, "流程名为：" + workflowName + "的工单，短信回复超时提醒服务id为：" + postOvertimeDealServiceId + "的服务未配置发送内容，故不发送");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("send message exception,error info is" + e.getMessage());
        }

    }


    /**
     * 将传入的map中的值 替换content中类似于[title]的值
     */
    public static String returnContent(DataObject main, String content) {
        String[] cont = content.split("\\[");
        String[] contents = new String[cont.length - 1];
        for (int i = 1; i < cont.length; i++) {
            contents[i - 1] = (cont[i].split("\\]"))[0];

        }
        for (int i = 0; i < contents.length; i++) {

            if (contents[i].indexOf("d|") != -1) {
                String contDict = contents[i];
                contDict = contDict.replace("d|", "");
                String contDictEn = main.getString(contDict);
                ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
                String contentCn = service.id2Name(contDictEn, "ItawSystemDictTypeDao");
                System.out.println("-----------------插入短信表----------字点值：contDictEn：" + contDictEn + "contentCn:" + contentCn);
                content = returnCont(contentCn, content);
            } else {
                content = returnCont(main.getString(contents[i]), content);
            }
        }
        System.out.println("转换后的 content==" + content);
        return content;
    }


    /**
     * add by hanlili
     * 将value中的值，替换content中类似于[title]的字段
     */
    public static String returnCont(String value, String content) {
        int schar = content.indexOf("[");
        String subCont = content.substring(0, schar);
        int echar = content.indexOf("]");
        subCont = subCont + value;
        if (-1 != echar) {
            content = content.substring(content.indexOf("]") + 1, content.length());
            content = subCont + content;
        }
        return content;
    }

    /**
     * @param workflowName 流程名称
     * @param sheetKey     工单主健ID
     * @param sheetId      工单流水号
     * @param title        工单主题
     * @param receiveType  短信接受者类型
     * @param receiverId   短信接收者Id
     * @see 此方法用于非流程操作时派发短信，例如：催办、阶段回复、阶段建议、抄送等
     */
    public void workSM_NON_T(String workflowName, String sheetKey, String sheetId, String title, int receiveType,
                             String receiverId) throws Exception {
        try {
            MsgServiceImpl msgService = new MsgServiceImpl();

            //解析短信服务信息文件，获取工单中文名称，以及短信服务ID
            String nodeCnName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_PROCESS_CN_NAME;
            String nodeInstantName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_SERVICE_INSTANT;
            String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
            String processCnName = SheetStaticMethod.getNodeName(filePath, nodeCnName);
            String instantServiceId = SheetStaticMethod.getNodeName(filePath, nodeInstantName);
            //拼接短信接受者
            String receivers = "";
            if (receiveType == Constants.SMS_RECEIVE_TYPE_USER) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_USER + "," + receiverId + "#";
            } else if (receiveType == Constants.SMS_RECEIVE_TYPE_DEPT) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_DEPT + "," + receiverId + "#";
            } else if (receiveType == Constants.SMS_RECEIVE_TYPE_ROLE) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_ROLE + "," + receiverId + "#";
            }

            if (!receivers.equals(""))
                receivers = receivers.substring(0, receivers.lastIndexOf("#"));

            System.out.println("receivers=" + receivers);

            //派发通知（即时提醒）
            if (!instantServiceId.equals("")) {
                //获得当前时间
                Date currentTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                String sendTime = formatter.format(currentTime);

                //拼写发送信息
                String sendContent = "请你及时处理" + processCnName + ":" + sheetId + "。";
                msgService.sendMsg(instantServiceId, sendContent,
                        sheetKey, receivers, sendTime);

            }
        } catch (Exception e) {
            throw new Exception("send message exception,error info is" + e.getMessage());
        }
    }

    /**
     * @param sheetKey     工单主键ID
     * @param workflowName 流程名称
     * @param closeMsgType 关闭短信类型
     * @see 关闭短信提醒
     */
    public void closeMsg(String sheetKey, String workflowName, int closeMsgType) throws Exception {
        MsgServiceImpl msgService = new MsgServiceImpl();
        try {
            //解析短信服务信息文件，获取工单中文名称，以及短信服务ID

            String nodeInstantName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_SERVICE_INSTANT;
            String nodePreOvertimeAccpetName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_SERVICE_PREOVERTIME_ACCEPT;
            String nodePreOvertimeDealName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_SERVICE_PREOVERTIME_DEAL;
            String nodePostOvertimeAcceptName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_SERVICE_POSTOVERTIME_ACCPT;
            String nodePostOvertimeDealName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_SERVICE_POSTOVERTIME_DEAL;

            String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
            String instantServiceId = SheetStaticMethod.getNodeName(filePath, nodeInstantName);
            String preOvertimeAcceptServiceId = SheetStaticMethod.getNodeName(filePath, nodePreOvertimeAccpetName);
            String preOvertimeDealServiceId = SheetStaticMethod.getNodeName(filePath, nodePreOvertimeDealName);
            String postOvertimeAcceptServiceId = SheetStaticMethod.getNodeName(filePath, nodePostOvertimeAcceptName);
            String postOvertimeDealServiceId = SheetStaticMethod.getNodeName(filePath, nodePostOvertimeDealName);

            switch (closeMsgType) {
                case Constants.SMS_TYPE_INSTANT: {
                    if (!instantServiceId.equals("")) msgService.closeMsg(instantServiceId, sheetKey);
                    break;
                }
                case Constants.SMS_TYPE_ACCEPT: {
                    if (!preOvertimeAcceptServiceId.equals(""))
                        msgService.closeMsg(preOvertimeAcceptServiceId, sheetKey);
                    if (!postOvertimeAcceptServiceId.equals(""))
                        msgService.closeMsg(postOvertimeAcceptServiceId, sheetKey);
                    break;
                }
                case Constants.SMS_TYPE_DEAL: {
                    if (!preOvertimeDealServiceId.equals("")) msgService.closeMsg(preOvertimeDealServiceId, sheetKey);
                    if (!postOvertimeDealServiceId.equals("")) msgService.closeMsg(postOvertimeDealServiceId, sheetKey);
                    break;
                }
                default: {
                    if (!instantServiceId.equals("")) msgService.closeMsg(instantServiceId, sheetKey);
                    if (!preOvertimeAcceptServiceId.equals(""))
                        msgService.closeMsg(preOvertimeAcceptServiceId, sheetKey);
                    if (!preOvertimeDealServiceId.equals("")) msgService.closeMsg(preOvertimeDealServiceId, sheetKey);
                    if (!postOvertimeAcceptServiceId.equals(""))
                        msgService.closeMsg(postOvertimeAcceptServiceId, sheetKey);
                    if (!postOvertimeDealServiceId.equals("")) msgService.closeMsg(postOvertimeDealServiceId, sheetKey);
                    break;
                }
            }


        } catch (Exception e) {
            throw new Exception("close instant message exception,error info is" + e.getMessage());
        }
    }

    /**
     * @param workflowName 流程名称
     * @param sheetKey     工单主健ID
     * @param sheetId      工单流水号
     * @param title        工单主题
     * @param receiveType  短信接受者类型
     * @param receiverId   短信接收者Id
     * @see 此方法用于非流程操作时派发短信，例如：催办、阶段回复、阶段建议、抄送等
     */
    public void workSM_NON_T(String workflowName, String sheetKey, String sheetId, int receiveType,
                             String receiverId) throws Exception {
        try {
            MsgServiceImpl msgService = new MsgServiceImpl();

            //解析短信服务信息文件，获取工单中文名称，以及短信服务ID
            //下面是解析你调用是import com.boco.eoms.common.properties.XMLProperties.XMLPropertyFile；
            String nodeCnName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_PROCESS_CN_NAME;
            String nodeInstantName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_SERVICE_INSTANT;
            String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
            String processCnName = SheetStaticMethod.getNodeName(filePath, nodeCnName);
            String instantServiceId = SheetStaticMethod.getNodeName(filePath, nodeInstantName);
            //拼接短信接受者
            String receivers = "";
            if (receiveType == Constants.SMS_RECEIVE_TYPE_USER) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_USER + "," + receiverId + "#";
            } else if (receiveType == Constants.SMS_RECEIVE_TYPE_DEPT) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_DEPT + "," + receiverId + "#";
            } else if (receiveType == Constants.SMS_RECEIVE_TYPE_ROLE) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_ROLE + "," + receiverId + "#";
            }

            if (!receivers.equals(""))
                receivers = receivers.substring(0, receivers.lastIndexOf("#"));

            System.out.println("receivers=" + receivers);

            //派发通知（即时提醒）
            if (!instantServiceId.equals("")) {
                //获得当前时间
                Date currentTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                String sendTime = formatter.format(currentTime);

                //拼写发送信息
                String sendContent = "您启动的新业务试点工单" + sheetId + "调用的新产品编号尚没有启动相关的正式实施工单，请您核查!";
                System.out.println("sendContent=====" + sendContent);
                msgService.sendMsg(instantServiceId, sendContent,
                        sheetKey, receivers, sendTime);

            }
        } catch (Exception e) {
            throw new Exception("send message exception,error info is" + e.getMessage());
        }
    }

    /**
     * @param workflowName 流程名称
     * @param sheetKey     工单主健ID
     * @param sheetId      工单流水号
     * @param title        工单主题
     * @param receiveType  短信接受者类型
     * @param receiverId   短信接收者Id
     * @author yyk
     * 此方法用于新业务试点工单短信提醒（资源欲载(总共3个月)的第二个月结束时进行短信提醒）
     */
    public void workSM_NON_T_new(String workflowName, String sheetKey, String sheetId, int receiveType,
                                 String receiverId, String content) throws Exception {
        try {
            MsgServiceImpl msgService = new MsgServiceImpl();

            //解析短信服务信息文件，获取工单中文名称，以及短信服务ID
            //下面是解析你调用是import com.boco.eoms.common.properties.XMLProperties.XMLPropertyFile；
            String nodeCnName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_PROCESS_CN_NAME;
            String nodeInstantName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_SERVICE_INSTANT;
            String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
            String processCnName = SheetStaticMethod.getNodeName(filePath, nodeCnName);
            String instantServiceId = SheetStaticMethod.getNodeName(filePath, nodeInstantName);
            //拼接短信接受者
            String receivers = "";
            if (receiveType == Constants.SMS_RECEIVE_TYPE_USER) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_USER + "," + receiverId + "#";
            } else if (receiveType == Constants.SMS_RECEIVE_TYPE_DEPT) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_DEPT + "," + receiverId + "#";
            } else if (receiveType == Constants.SMS_RECEIVE_TYPE_ROLE) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_ROLE + "," + receiverId + "#";
            }

            if (!receivers.equals(""))
                receivers = receivers.substring(0, receivers.lastIndexOf("#"));

            //System.out.println("receivers==="+receivers);
            //System.out.println("instantServiceId==="+instantServiceId);
            //派发通知（即时提醒）
            if (!instantServiceId.equals("")) {
                //获得当前时间
                Date currentTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                String sendTime = formatter.format(currentTime);

                //拼写发送信息
                //String sendContent="您启动的新业务试点工单" + sheetId + "调用的新产品编号尚没有启动相关的正式实施工单，请您核查!";
                String sendContent = "包含这个" + content + "编号的新业务试点工单将要到期，工单主题:" + sheetId;
                //System.out.println("sendContent====="+sendContent);
                msgService.sendMsg(instantServiceId, sendContent, sheetKey, receivers, sendTime);

            }
        } catch (Exception e) {
            throw new Exception("send message exception,error info is" + e.getMessage());
        }
    }
}
