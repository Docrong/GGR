package com.boco.eoms.commons.mms.msssubscribe.mgr.impl;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.mms.base.util.MMSConstants;
import com.boco.eoms.commons.mms.mmsreport.model.Mmsreport;
import com.boco.eoms.commons.mms.mmsreporttemplate.mgr.MmsreportTemplateMgr;
import com.boco.eoms.commons.mms.mmsreporttemplate.model.MmsreportTemplate;
import com.boco.eoms.commons.mms.msssubscribe.dao.MsssubscribeDao;
import com.boco.eoms.commons.mms.msssubscribe.mgr.MsssubscribeMgr;
import com.boco.eoms.commons.mms.msssubscribe.model.Msssubscribe;
import com.boco.eoms.commons.mms.statreport.mgr.StatreportMgr;
import com.boco.eoms.commons.mms.statreport.model.Statreport;
import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;
import com.cmcc.mm7.vasp.common.MMConstants;
import com.cmcc.mm7.vasp.common.MMContent;
import com.cmcc.mm7.vasp.conf.MM7Config;
import com.cmcc.mm7.vasp.message.MM7RSRes;
import com.cmcc.mm7.vasp.message.MM7SubmitReq;
import com.cmcc.mm7.vasp.service.MM7Sender;

/**
 * <p>
 * Title:彩信报订阅
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Fri Feb 20 11:32:20 CST 2009
 * </p>
 * 
 * @author 李志刚
 * @version 3.5
 * 
 */
public class MsssubscribeMgrImpl implements MsssubscribeMgr {
 
	public Msssubscribe getMmsreport(Mmsreport mmsreoprt) {
		String mmsreport_template_id = mmsreoprt.getMmsreport_template_id();
		return msssubscribeDao.getMsssubscribeForMmsreportTemplateId(mmsreport_template_id);
	}

	public String sendMmsreport(Msssubscribe msssubscribe,Mmsreport mmsreport) throws Exception {
		String status = mmsreport.getSendStatus();
		if(msssubscribe == null)
		{
			return status;
		}
		
		//实现发送彩信报代码
		System.out.println("订阅号 id ：" + msssubscribe.getId());
		
		// 接收人可以有多个接收人 中间用"#"隔开
		String SaveUser = "";
		if( msssubscribe.getReceivePerson()!=null&& !msssubscribe.getReceivePerson().equals("")){
		    String[] users = msssubscribe.getReceivePerson().split(",");    
		    for(int i =0;i<users.length;i++){
		    	SaveUser = SaveUser + "1,"+users[i]+"#";
		    }
		    SaveUser.substring(0, SaveUser.length()-1);    
		}
		
		//发送时间
		String SaveTime = msssubscribe.getReceiveTime();
		
		// 唯一标识
		String buizId = msssubscribe.getId();
		
		//MSG 建立发送内容
//		MmsreportTemplateMgr mmsreportTemplateMgr = (MmsreportTemplateMgr)ApplicationContextHolder.getInstance().getBean("mmsreportTemplateMgr");
//	    MmsreportTemplate  mmsreportTemplate  = mmsreportTemplateMgr.getMmsreportTemplate(msssubscribe.getMmsreport_templateId());
	    StatreportMgr statreportMgr = (StatreportMgr)ApplicationContextHolder.getInstance().getBean("statreportMgr");
	    List statlist = statreportMgr.getStatreportForMmsreportId(mmsreport.getId());
	    
	    //发送初始化发送地址，服务地址等信息
	    String mm7c = StaticMethod.getFilePathForUrl("classpath:config/mm7Config.xml");
		String connc = StaticMethod.getFilePathForUrl("classpath:config/ConnConfig.xml");
		MM7Config mm7Config = new MM7Config(mm7c);
		mm7Config. setConnConfigName(connc);
		MM7Sender mm7Sender = null;
		try {
			mm7Sender = new MM7Sender(mm7Config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    MM7SubmitReq submit = new MM7SubmitReq();
	    submit.setTransactionID("1000000");//设置MM7_submit.REQ/MM7_submit.RES对的标识，必备
	    submit.setVASID("10658218");//设置SP代码，必备
	    submit.setVASPID("826211");//设置服务代码，必备
	    submit.setSenderAddress("10658218000");//"MM始发方的地址 必备
	    submit.setServiceCode("000");//设置业务代码，必备
	    submit.setChargedPartyID("1");//设置付费方的手机号码，必备
	    submit.setChargedParty((byte) 1);//VASP所提交MM的付费方  设置VASP所提交MM的付费方，例如，发送方、接收方、发送方和接收方或两方均不付费，可选，0：Sender、1：Recipients、2：Both、3：Neither、4：ThirdParty
		submit.setDeliveryReport(true); //设置是否需要发送报告的请求（boolean值）,可选
		
		//彩信报的名称
	    submit.setSubject(msssubscribe.getMmreportName());
	    //接收人手机号：15829550058,13915002000 可以设置发送多个人
	    submit.addTo("15829550058");
//	    submit.addTo("15011321994");
	    
	    //建立彩信内容
	    MMContent content = new MMContent();
	    content.setContentType(MMConstants.ContentType. MULTIPART_MIXED);
	    
	    //彩信报首页图片
	    String titlePath = StaticMethod.getFilePathForUrl(MMSConstants.FIST_PAGE_GIF);
	    MMContent subfistPage = MMContent.createFromFile(titlePath);
	    subfistPage.setContentID("title"+".gif");
	    subfistPage.setContentType(MMConstants.ContentType.GIF);
	    content.addSubContent(subfistPage);
	    
	    //加入彩信说明文字
	    MmsreportTemplateMgr mmsreportTemplateMgr = (MmsreportTemplateMgr)ApplicationContextHolder.getInstance().getBean("mmsreportTemplateMgr");
	    MmsreportTemplate  mmsreportTemplate  = mmsreportTemplateMgr.getMmsreportTemplate(msssubscribe.getMmsreport_templateId());
		byte[] titleTemp = mmsreportTemplate.getMmsReportDesc().getBytes();
		InputStream input = new ByteArrayInputStream(titleTemp);
		MMContent titleText = MMContent.createFromStream(input);
		titleText.setContentID("title.txt");
		titleText.setContentType(MMConstants.ContentType.TEXT);
		content.addSubContent(titleText);
	    
	    for(int i=0;i<statlist.size();i++)
	    {
	    	Statreport statreport = (Statreport)statlist.get(i);
	    	String picUrl = statreport.getPicID();
	    	String footInfo = statreport.getFootInfo();
	    	
	    	if(picUrl != null && !picUrl.equalsIgnoreCase(""))
	    	{
	    		MMContent sub1 = MMContent.createFromFile(MMSConstants.MMSREPORT_FILE_RELATIVEPATH + picUrl);
			    sub1.setContentID(i+".gif");
			    sub1.setContentType(MMConstants.ContentType.GIF);
			    content.addSubContent(sub1);
			    System.out.println(i+".gif");
	    	}
	    	
	    	if(footInfo != null && !footInfo.equalsIgnoreCase(""))
	    	{
	    		MMContent sub2 = MMContent.createFromString(footInfo);
				sub2.setContentID(i+".txt");
				sub2.setContentType(MMConstants.ContentType. TEXT);
				content.addSubContent(sub2);
				System.out.println(i+".txt");
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
			status = statusCode + " : " + statusText;
	    }
	    
		return status;
	}
	
	/**
	 * 邮件发送，根据服务订阅信息发送邮件
	 * 
	 * @param serviceId
	 *            消息系统中订阅的服务id
	 * @param subject 
	 * 			  邮件主题
	 * @param content
	 *            邮件内容
	 * @param buizId
	 *            具体调用消息服务的业务流水号，如：作业计划id（主键），主要为取消消息发送
	 * @param addresser
	 *            邮件的发件人 email格式 
	 * @param orgIds 
	 * 			  发送人员或者部门的串 格式：1,admin#1,sunshengtai#2,254 （其中1代表人员  2代表部门 254代表部门id）             
	 * @param dispatchTime 
	 * 			  业务发送时间点
	 * @param accessoriesUrl
	 * 			  附件url地址
	 * @return success,fail（成功与否）
	 */
    public String sendEmail(String serviceId, String subject, String content, String buizId, String addresser, String orgIds, String dispatchTime, String accessoriesUrl)
    {
    	return "发送彩信";
    }

	private MsssubscribeDao  msssubscribeDao;
 	
	public MsssubscribeDao getMsssubscribeDao() {
		return this.msssubscribeDao;
	}
 	
	public void setMsssubscribeDao(MsssubscribeDao msssubscribeDao) {
		this.msssubscribeDao = msssubscribeDao;
	}
 	
    public List getMsssubscribes() {
    	return msssubscribeDao.getMsssubscribes();
    }
    
    public Msssubscribe getMsssubscribe(final String id) {
    	return msssubscribeDao.getMsssubscribe(id);
    }
    
    public void saveMsssubscribe(Msssubscribe msssubscribe) {
    	msssubscribeDao.saveMsssubscribe(msssubscribe);
    }
    
    public void removeMsssubscribe(final String id) {
    	msssubscribeDao.removeMsssubscribe(id);
    }
    
    public Map getMsssubscribes(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return msssubscribeDao.getMsssubscribes(curPage, pageSize, whereStr);
	}
    
    public static void main(String[] args) throws FileNotFoundException
    {
    	String fistPagePath = StaticMethod.getFilePathForUrl(MMSConstants.FIST_PAGE_GIF);
    	System.out.print(fistPagePath);
    }
	
}