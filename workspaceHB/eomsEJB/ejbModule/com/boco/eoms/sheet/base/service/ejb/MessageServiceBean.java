package com.boco.eoms.sheet.base.service.ejb;

import java.sql.Connection;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.boco.eoms.sheet.base.sms.WorkSheetSmsServices;
import commonj.sdo.DataObject;

/**
 * Bean implementation class for Enterprise Bean: MessageService
 */
public class MessageServiceBean implements javax.ejb.SessionBean {

	static final long serialVersionUID = 3206093459760846163L;
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	/**
	    * @see 工单派发的时候，进行短信提醒；包括即时提醒、超时前提醒以及超时后提醒
	    *      此方法一般用于普通人工任务生成时调用
	    * @param workflowName 流程名称
	    * @param sheetKey 工单主健ID
	    * @param sheetId  工单流水号
	    * @param title 工单主题
	    * @param receiveType 短信接受者类型
	    * @param receiverId 短信接收者Id
	    * @param acceptLimitTime 接受时限
	    * @param dealLimitTime 处理时限
	    */
	public void sendMsg(DataObject mainObject,String workflowName, String sheetKey,String sheetId, String title,
			            int receiveType,String receiverId,String acceptLimitTime,String dealLimitTime) throws Exception{
	  WorkSheetSmsServices services=new WorkSheetSmsServices();
	  Connection conn = null;
	  try{
		  //获取数据库链接
		  conn=this.getConnection();
		  //派发工单信息
		  services.workSM_T(mainObject,workflowName, sheetKey, sheetId, title, receiveType,
				  receiverId, acceptLimitTime, dealLimitTime);		
		}
	  catch (Exception e){
		 throw new Exception("派发工单短信出错，出错信息为"+e.getMessage()); 
	  }
	}
	
	 /**
	    * @see  此方法用于非流程操作时派发短信，例如：催办、阶段回复、阶段建议、抄送等
	    * @param workflowName 流程名称
	    * @param sheetKey 工单主健ID
	    * @param sheetId  工单流水号
	    * @param title 工单主题
	    * @param receiveType 短信接受者类型
	    * @param receiverId 短信接收者Id
	    */
	public void sendMsgHie(String workflowName,String sheetKey,String sheetId,
			               String title,int receiveType,String receiverId) throws Exception {
		WorkSheetSmsServices services=new WorkSheetSmsServices();
	    Connection conn = null;
		  try{
			  //获取数据库链接
			  conn=this.getConnection();
			  //派发工单信息
			  services.workSM_NON_T(workflowName, sheetKey, sheetId, title, receiveType,
					                receiverId);		
			}
		  catch (Exception e){
			 throw new Exception("派发工单短信出错，出错信息为"+e.getMessage()); 
		  }	
	}
	
	   /**
	    * @see 关闭短信提醒
	    * @param sheetKey   工单主键ID
	    * @param workflowName 流程名称
	    * @param closeMsgType 关闭短信类型
	    */
	   public void closeMsg(String sheetKey,String workflowName,int closeMsgType) throws Exception{
		  WorkSheetSmsServices services=new WorkSheetSmsServices();
		  Connection conn = null;
			  try{
				  //获取数据库链接
				  conn=this.getConnection();
				  //关闭工单信息
				  services.closeMsg(sheetKey, workflowName,closeMsgType);
				}
			  catch (Exception e){
				 throw new Exception("关闭工单短信出错，出错信息为"+e.getMessage()); 
			  } 
	    }
	     
	   private Connection getConnection() throws Exception {
			String jndiName = "jdbc/eoms";
			Connection conn = null;

			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.ibm.websphere.naming.WsnInitialContextFactory");
			InitialContext ic = new InitialContext(env);
			DataSource ds = (DataSource) ic.lookup(jndiName);
			conn = ds.getConnection();
			return conn;
		}
	   /**
	    * @see  add by zhangying 此方法目前只应用与新业务试点工单催办启动正式实施工单
	    * @param workflowName 流程名称
	    * @param sheetKey 工单主健ID
	    * @param sheetId  工单流水号
	    * @param receiveType 短信接受者类型
	    * @param receiverId 短信接收者Id
	    */
	public void sendMsgHie(String workflowName,String sheetKey,String sheetId,
			               int receiveType,String receiverId) throws Exception {
		WorkSheetSmsServices services=new WorkSheetSmsServices();
	    Connection conn = null;
		  try{
			  //获取数据库链接
			  conn=this.getConnection();
			  //派发工单信息
//			  services.workSM_NON_T(workflowName, sheetKey, sheetId, receiveType,
//					                receiverId);		
			}
		  catch (Exception e){
			 throw new Exception("派发工单短信出错，出错信息为"+e.getMessage()); 
		  }	
	}
}
