package com.boco.eoms.message.dao.hibernate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.dom4j.DocumentException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.message.dao.ISchedulerDao;
import com.boco.eoms.message.mgr.impl.VoiceMonitorManagerImpl;
import com.boco.eoms.message.model.MmsContent;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.util.SendConfig;
import com.cmcc.mm7.vasp.common.MMConstants;
import com.cmcc.mm7.vasp.common.MMContent;
import com.cmcc.mm7.vasp.conf.MM7Config;
import com.cmcc.mm7.vasp.message.MM7RSRes;
import com.cmcc.mm7.vasp.message.MM7SubmitReq;
import com.cmcc.mm7.vasp.service.MM7Sender;
import com.huawei.smproxy.CMPPSMProxy30;
import com.huawei.smproxy.comm.cmpp30.message.CMPP30SubmitMessage;
import com.huawei.smproxy.util.Args;
import com.huawei.smproxy.util.Cfg;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-7-21 下午03:40:02
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public class SchedulerDaoHibernate_GanSu extends BaseDaoHibernate implements
		ISchedulerDao {
	private static Args args = null ;
	private static CMPPSMProxy30 smp = null ;
	public boolean mmsMonitorScheduler(String mobiles, String subject, List contentList) {
		int code = 0;
		boolean status = false;
		String[] mobileArr = null;	    
	    //发送初始化发送地址，服务地址等信息
	    String mm7c = "";
	    String connc = "";
		try {
			mm7c = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/message/config/mm7Config.xml");
			connc = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/message/config/ConnConfig.xml");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		MM7Config mm7Config = new MM7Config(mm7c);
		mm7Config. setConnConfigName(connc);
		MM7Sender mm7Sender = null;
		try {
			mm7Sender = new MM7Sender(mm7Config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    MM7SubmitReq submit = new MM7SubmitReq();
	    try {
			SendConfig sendConfig = new SendConfig();
			sendConfig = SendConfig.getSendConfig("classpath:com/boco/eoms/message/config/SendConfig.xml");
			submit.setTransactionID(sendConfig.transactionID);//设置MM7_submit.REQ/MM7_submit.RES对的标识，必备
		    submit.setVASID(sendConfig.VASID);//设置SP代码，必备
		    submit.setVASPID(sendConfig.VASPID);//设置服务代码，必备
		    submit.setSenderAddress(sendConfig.SenderAddress);//"MM始发方的地址 必备
		    submit.setServiceCode(sendConfig.serviceCode);//设置业务代码，必备
		    submit.setChargedPartyID("1");//设置付费方的手机号码，必备
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	    
	    submit.setChargedParty((byte) 1);//VASP所提交MM的付费方  设置VASP所提交MM的付费方，例如，发送方、接收方、发送方和接收方或两方均不付费，可选，0：Sender、1：Recipients、2：Both、3：Neither、4：ThirdParty
		submit.setDeliveryReport(true); //设置是否需要发送报告的请求（boolean值）,可选
		
		//彩信报的名称
	    submit.setSubject(subject);
	    //接收人手机号：15829550058,13915002000 可以设置发送多个人
	    if(mobiles != null && !mobiles.equals("")) {
	    	mobileArr = mobiles.split(",");
	    	for(int i=0; i<mobileArr.length; i++) {
	    		submit.addTo(mobileArr[i]);
	    	}
	    }	    
	    
	    //建立彩信内容
	    MMContent content = new MMContent();
	    content.setContentType(MMConstants.ContentType. MULTIPART_MIXED);
	    	    
	    //加入彩信说明文字
	    	    
	    for(int i=0;i<contentList.size();i++)
	    {
	    	MmsContent mmsContent = (MmsContent)contentList.get(i);
	    	String contentUrl = mmsContent.getContent().trim();
	    	String contentType = mmsContent.getContentType();
	    	
	    	if(contentType != null && !contentType.equalsIgnoreCase("")) {
	    		if(contentType.equals(MsgConstants.MMS_TYPE_TEXT)) {
	    			MMContent sub = MMContent.createFromString(contentUrl);
				    sub.setContentID(i+".txt");
				    sub.setContentType(MMConstants.ContentType.TEXT);
				    content.addSubContent(sub);
	    		} else if(contentType.equals(MsgConstants.MMS_TYPE_GIF)) {
	    			MMContent sub = MMContent.createFromFile(contentUrl);
				    sub.setContentID(i+".gif");
				    sub.setContentType(MMConstants.ContentType.GIF);
				    content.addSubContent(sub);
	    		} else if(contentType.equals(MsgConstants.MMS_TYPE_JPEG)) {
	    			MMContent sub = MMContent.createFromFile(contentUrl);
				    sub.setContentID(i+".jpeg");
				    sub.setContentType(MMConstants.ContentType.JPEG);
				    content.addSubContent(sub);
	    		}
	    	}
	    }
	    submit.setContent(content);
	    
	    //发送彩信
	    if(mm7Sender != null)
	    {
	    	MM7RSRes res = mm7Sender.send(submit);
			//发送时间和状态
			int statusCode = res.getStatusCode();
			String statusText = res.getStatusText();
			System.out.println("statusCode=" + statusCode + ";statusText=" + statusText);
			code = statusCode;
	    }
	    if(code == 1000) {
	    	status = true;
	    }
		return status;
	}

	public boolean smsMonitorScheduler(String mobile, String message) {
		boolean returnStr=false ;
		String[] tel = new String[1];
		tel[0] = mobile;	
	   //int retStatus = 0;
	    if (smp == null) {
            connection();
          }	
		synchronized(smp){try 
		   {
		       byte[]messageUCS2;
		       messageUCS2 = message.getBytes("UnicodeBigUnmarked");
		       BocoLog.debug(this, message + " -(UCS2)编码: " + bytesToHexStr(messageUCS2));
		       int messageUCS2Len = messageUCS2.length;
		       //长短信长度
		       int maxMessageLen = 140;
		   
		       if(messageUCS2Len<=maxMessageLen){
		    	   byte[] msg_Content = new byte[140];
		    	    msg_Content = message.getBytes();
		    	   CMPP30SubmitMessage cm = new CMPP30SubmitMessage(
		    			      1, 
		    			      1, 
		    			      1, 
		    			      88, 
		    			      "good news", 
		    			      0, 
		    			      "", 
		    			      1, 
		    			      0, 
		    			      0, 
		    			      15, 
		    			      "927066", 
		    			      "01", 
		    			      "999", 
		    			      null, 
		    			      null, 
		    			      "106584320", 
		    			      tel, 
		    			      0, 
		    			      msg_Content, 
		    			      "linkid=0123456789012");
		    			    try {
					    	  
		    			      smp.send(cm);
		    			      returnStr = true;
		    			    }
		    			    catch (IOException e) {
		    			    	returnStr = false;
		    			    	e.printStackTrace();
		    			    }
		       }
		       if (messageUCS2Len > maxMessageLen) 
		       {
		           //长短信发送		           
		           int messageUCS2Count = messageUCS2Len / (maxMessageLen - 6) + 1;
		           //长短信分为多少条发送
		           byte[]tp_udhiHead = new byte[6];
		           tp_udhiHead[0] = 0x05;
		           tp_udhiHead[1] = 0x00;
		           tp_udhiHead[2] = 0x03;
		           tp_udhiHead[3] = 0x0A;
		           tp_udhiHead[4] = (byte)messageUCS2Count;
		           tp_udhiHead[5] = 0x01;
		           //默认为第一条
		           
		            
		           for (int i = 0; i < messageUCS2Count; i ++ ) 
		           {
		               tp_udhiHead[5] = (byte)(i + 1);
		               byte[]msgContent;
		               if (i != messageUCS2Count - 1) {
		                   //不为最后一条
		                   msgContent = SchedulerDaoHibernate_GanSu.byteAdd(tp_udhiHead, messageUCS2, i * (maxMessageLen - 6), (i + 1) * (maxMessageLen - 6));
		               } else {
		                   msgContent = SchedulerDaoHibernate_GanSu.byteAdd(tp_udhiHead, messageUCS2, i * (maxMessageLen - 6), messageUCS2Len);
		               }
		               CMPP30SubmitMessage cm = new CMPP30SubmitMessage(
			            	      1, 
			            	      1, 
			            	      1, 
			            	      88, 
			            	      "good news", 
			            	      0, 
			            	      "", 
			            	      1, 
			            	      0, 
			            	      0x01, 
			            	      0x08, 
			            	      "927066", 
			            	      "01", 
			            	      "999", 
			            	      null, 
			            	      null, 
			            	      "106584320", 
			            	      tel, 
			            	      0, 
			            	      msgContent, 
			            	      "linkid=0123456789012");
		               BocoLog.debug(this, "submit start");
		               BocoLog.debug(this, "正在发送第" + tp_udhiHead[5] + "条长短信");
		               BocoLog.debug(this, "UCS2:" + SchedulerDaoHibernate_GanSu.bytesToHexStr(msgContent));
		               BocoLog.debug(this, "总长度：" + msgContent.length);
		               smp.send(cm);		               
		           }
		           //for end
		           returnStr = true ;
		       }
		       //if end		       
		   } catch(Exception e) {
		       returnStr = false;
		       e.printStackTrace();
		   }
		}
		return returnStr;
	}

	public boolean voiceMonitorScheduler(String t_no, String t_alloc_time, String t_finish_time, String t_content, String t_tel_num, String t_tel_num2, String dispatch_tel) {
		DataSource db = (DataSource)ApplicationContextHolder.getInstance().getBean("voiceSource");
		Statement stmt = null;
		Connection conn = null;
		boolean status = true;
		try {			
			conn = db.getConnection();
			stmt = conn.createStatement();
			String sql = "insert into t_info (t_no,t_alloc_time,t_finish_time,t_content,t_tel_num,t_tel_num2,dispatch_tel,t_allocer_no, t_dealer_no) values ('"+ t_no + "','" + t_alloc_time + "','" + t_finish_time + "','" + t_content + "','" + t_tel_num + "','" + t_tel_num2 + "','" + dispatch_tel + "',0,0)";
			
			stmt.execute(sql);
			
		} catch (Exception e) {
			status = false;			
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				status = false;
				BocoLog.error(VoiceMonitorManagerImpl.class, e.getMessage());
			}
		}
		return status;
	}
	public  void connection(){
		try {
			args = new Cfg("SMProxy.xml",false).getArgs("CMPPConnect");
			smp = new CMPPSMProxy30(args);			  
		} catch(Exception ex){		     
		    ex.printStackTrace();		       
		}
	}
	   /** *//**
	 * 功能：
	 *     将字节转换为16进制码（在此只是为了调试输出，此函数没有实际意义）
	 * @param b  
	 * @return 转化后的16进制码
	 * @Author: eric(eric_cheung709@hotmail.com)
	    * created in 2007/04/28 16:33:06
	 */
	   private static String bytesToHexStr(byte[]b) 
	   {
	       if (b == null)return "";
	       StringBuffer strBuffer = new StringBuffer(b.length * 3);
	       for (int i = 0; i < b.length; i ++ ) 
	       {
	           strBuffer.append(Integer.toHexString(b[i] & 0xff));
	           strBuffer.append(" ");
	       }
	       return strBuffer.toString();
	   }
	   /** *//** */ 
	   /** *//***
	    * * 功能：
	    *     将src里的字节与add里的从start开始到end（不包括第end个位置)的字节串连在一起返回
	    * @param src
	    * @param add
	    * @param start    add 开始位置
	    * @param end      add 的结束位置(不包括end位置)
	    * @return 也即实现类似String类型的src+add.subString(start,end)功能
	    */
	      public static byte[]byteAdd(byte[]src, byte[]add, int start, int end) 
	      {
	          byte[]dst = new byte[src.length + end - start];
	          for (int i = 0; i < src.length; i ++ ) 
	          {
	              dst[i] = src[i];
	          }
	          for (int i = 0; i < end - start; i ++ ) 
	          {
	              dst[src.length + i] = add[start + i];
	          }
	          return dst;
	      }
}