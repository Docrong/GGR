package com.boco.eoms.sheet.supervisetask.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ParameterMode;

import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.StringUtils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.commontask.model.CommonTaskMain;
import com.boco.eoms.sheet.listedregulation.model.ListedRegulationMain;
import com.boco.eoms.sheet.listedregulation.model.ListedRegulationTask;
//import com.boco.eoms.sheet.listedregulation.model.ListedRegulationTask;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.supervisetask.dao.SuperviseTaskDao;
import com.boco.eoms.sheet.supervisetask.model.SuperviseTaskRecord;
import com.boco.eoms.sheet.supervisetask.model.SuperviseTaskRule;
import com.boco.eoms.sheet.supervisetask.service.SuperviseTaskManager;

public class SuperviseTaskManagerImpl implements SuperviseTaskManager {

	private SuperviseTaskDao ISuperviseTaskDAO;
	

	public SuperviseTaskDao getISuperviseTaskDAO() {
		return ISuperviseTaskDAO;
	}

	public void setISuperviseTaskDAO(SuperviseTaskDao superviseTaskDAO) {
		ISuperviseTaskDAO = superviseTaskDAO;
	}

	public void savesupervisetaskRecord(SuperviseTaskRecord t) {
		ISuperviseTaskDAO.savesupervisetaskRecord(t);
		
	}

	public void savesupervisetaskRule(SuperviseTaskRule t) {
		ISuperviseTaskDAO.savesupervisetaskRule(t);
		
	}

	public Map supervisetaskRecordList(Integer curPage, Integer pageSize, Map maptj) {
		return ISuperviseTaskDAO.supervisetaskRecordList(curPage, pageSize, maptj);
	}

	public Map supervisetaskRuleList(Integer curPage, Integer pageSize, Map maptj) {
		return ISuperviseTaskDAO.supervisetaskRuleList(curPage, pageSize, maptj);
	}

	public Map supervisetaskRuleList2(Integer curPage, Integer pageSize, Map maptj) {
		return ISuperviseTaskDAO.supervisetaskRuleList(curPage, pageSize, maptj);
	}
	public SuperviseTaskRecord getSuperviseTaskRecordById(String id) {
		return ISuperviseTaskDAO.getSuperviseTaskRecordById(id);
	}

	public SuperviseTaskRule getSuperviseTaskRuleById(String id) {
		return ISuperviseTaskDAO.getSuperviseTaskRuleById(id);
	}
	
	public ListedRegulationMain getListedRegulationMainById( String id){
		return ISuperviseTaskDAO.getListedRegulationMainById(id);
	}
		
	public SuperviseTaskRecord getSuperviseTaskRecordBySheetId(String sheetId) {
		return ISuperviseTaskDAO.getSuperviseTaskRecordBySheetId(sheetId);
	}
	public ListedRegulationTask getListedRegulationTaskById( String id){
		return ISuperviseTaskDAO.getListedRegulationTaskById(id);
		
	}
	
	public String exportRecordExcel( HttpServletRequest request,
			HttpServletResponse response,List result) throws Exception{
		
			List ulist=result;
		
			System.out.println("导出Excel大小:" + ulist.size());
			SuperviseTaskRecord xlsnew = new SuperviseTaskRecord();// 表头
			xlsnew.setSheetId("工单ID");
			xlsnew.setCity("地市");
			xlsnew.setCountry("区县");
			xlsnew.setNoticeUser1("督办对象");
			xlsnew.setContent("督办内容");
			xlsnew.setSuperviseType("督办方式");
			xlsnew.setSupervisetaskRule("督办规则");
			xlsnew.setReason("督办原因");
			
			ulist.add(0, xlsnew);

			request.setCharacterEncoding("UTF-8");
			OutputStream os = response.getOutputStream(); // 取得输出流
			try {
				response.reset(); // 清空输出流
				response.setHeader("Content-disposition",
						"attachment;filename=" + new String("eoms.xls"));
				response.setContentType("application/msexcel;charset=UTF-8");// 定义输出类型

				int row = 0; // 从第二行开始写

				String path = request
						.getRealPath("/WEB-INF/pages/wfworksheet/supervisetask/exportExcel.xls");
				Workbook wb = Workbook.getWorkbook(new File(path));

				WritableWorkbook wwb = Workbook.createWorkbook(os, wb);
				WritableSheet ws = wwb.getSheet(0);
				System.out.println("sheet页的名称：" + ws.getName());

				if (ulist != null && ulist.size() > 0) {
					for (int i = 0; i < ulist.size(); i++) {
						// SimpleDateFormat sdf = new
						// SimpleDateFormat("yyyy-MM-dd hh:mi:ss");

						SuperviseTaskRecord mss = (SuperviseTaskRecord) ulist.get(i);


						int col = 0; // 从第一列开始写
						
						ws.addCell(new Label(col++,row,mss.getSheetId()));
						ws.addCell(new Label(col++,row,mss.getCity()));
						ws.addCell(new Label(col++,row,mss.getCountry()));
						ws.addCell(new Label(col++,row,mss.getNoticeUsername1()));
						ws.addCell(new Label(col++,row,mss.getContent()));
						ws.addCell(new Label(col++,row,mss.getSuperviseType()));
						ws.addCell(new Label(col++,row,mss.getSupervisetaskRule()));
						ws.addCell(new Label(col++,row,mss.getReason()));
						row++;
						
					}
				}

				wwb.write();
				wwb.close();
				wb.close();
				os.close();
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try{
					os.close();
				}catch(Exception e2){
					
				}
			}
		
		return null;
	}

	public Map getUsers(List userList){
		String users = "";
		if (userList != null && userList.size() > 0) {
			for (int i = 0; i < userList.size(); i++) {
				Map map = (Map) userList.get(i);
				if (map.get("userids") != null) {
					String user = (String) map.get("userids");
					if (user != null && !user.equals("")) {
						String[] arr = user.split(",");
						for (int j = 0; j < arr.length; j++) {
							users += arr[j] + ",";
						}
					}
				}
			}
			if (users.endsWith(",")) {// 最后一个为逗号则去除
				users = users.substring(0, users.lastIndexOf(","));
			}
		}
		Map map = new HashMap();
		map.put("users", users);
		return map;
	}
	
	public Map getUsersNamePhone(String[]usersarr) throws Exception{
		IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		String []usersPhonearr=new String[usersarr.length];
		String []usersNamearr=new String[usersarr.length];
		String usersPhone="";
		String usersName="";
		
		String queryUserInfo="select mobile,username from taw_system_user where deleted='0' ";
		if(usersarr!=null&&usersarr.length>0){
			for(int i=0;i<usersarr.length;i++){
				List list=service.getSheetAccessoriesList(queryUserInfo+" and userid='"+usersarr[i]+"'");
				if(list!=null&&list.size()>0){
					Map map=(Map) list.get(0);
					String username=(String) map.get("username");
					String mobile=(String) map.get("mobile");
					usersPhonearr[i]=mobile;
					usersNamearr[i]=username;
					usersPhone+=mobile+",";
					usersName+=username+",";
				}
			}
			if(!usersPhone.equals("")&&usersPhone.endsWith(",")){
				usersPhone=usersPhone.substring(0, usersPhone.lastIndexOf(","));
			}
			if(!usersName.equals("")&&usersName.endsWith(",")){
				usersName=usersName.substring(0, usersName.lastIndexOf(","));
			}
		}
		Map map=new HashMap();
		map.put("usersPhonearr", usersPhonearr);
		map.put("usersNamearr", usersNamearr);
		map.put("usersPhone", usersPhone);
		map.put("usersName", usersName);
		return map;
	}
	
	/*
	 *usersarr:用户userid
	 *usersPhonearr:用户userphone
	 *main:工单SheetId
	 *rule:通知方式 
	 *dealdate:发送日期
	 *content:发送内容
	 */
	public void sendSMSIVR(String[]usersarr,String[]usersPhonearr,ListedRegulationMain main,SuperviseTaskRule rule,Date dealdate,String content2) throws Exception{
//	public void sendSMSIVR(String[]usersarr,String[]usersPhonearr,CommonTaskMain main,SuperviseTaskRule rule,Date dealdate,String content2) throws Exception{
		IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for(int i=0;i<usersarr.length;i++){
			if (usersarr[i] != null && !usersarr[i].equals("")
					&& usersPhonearr[i] != null
					&& !usersPhonearr[i].equals("")) {

				if (rule.getSuperviseType().contains("SMS")) {
					String id=UUIDHexGenerator.getInstance().getID();
					String insertSql = "insert into sms_monitor values ('"
							+ id + "','"
							+ main.getSheetId() + "',null,'"
							+ main.getSheetId() + "','" + usersarr[i] + "','"
							+ usersPhonearr[i] + "',to_date('" + sdf.format(dealdate)
							+ "','yyyy-mm-dd hh24:mi:ss'),'" + content2 + "',"
							+ "'true','false','0') ";
					Map smsRuleMap=new HashMap();
					String title="SuperviseTaskRecord";
					String username="";
					String sql2= "insert into sms_record values ('"+id+"','"+StaticMethod.nullObject2String(main.getId())+"',"+
					"to_date('"+sdf.format(new Date())+"','yyyy-mm-dd hh24:mi:ss'),'"+StaticMethod.nullObject2String(rule.getCity())+"',"+
					"'"+StaticMethod.nullObject2String(rule.getCountry())+"','"+StaticMethod.nullObject2String(smsRuleMap.get("mainEquipmentFactory"))+"',"+
					"'"+StaticMethod.nullObject2String(rule.getMainNetSortOne())+"','"+StaticMethod.nullObject2String(rule.getMainNetSortTwo())+"',"+
					"'"+StaticMethod.nullObject2String(rule.getMainNetSortThree())+"','"+StaticMethod.nullObject2String(smsRuleMap.get("mainAlarmGenre"))+"',"+
					"'"+StaticMethod.nullObject2String(smsRuleMap.get("mainAlarmName"))+"','"+StaticMethod.nullObject2String(smsRuleMap.get("startTime"))+"',"+
					"'"+StaticMethod.nullObject2String(smsRuleMap.get("endTime"))+"','"+StaticMethod.nullObject2String(smsRuleMap.get("mainFaultResponseLevel"))+"',"+
					0 + ",'"+usersarr[i]+"',"+
					"'"+username+"','"+usersPhonearr[i]+"',"+
					"'"+main.getSheetId()+"','"+title+"',"+
					"'"+content2+"',to_date('"+sdf.format(dealdate)+"','yyyy-mm-dd hh24:mi:ss'),0,"+
					StaticMethod.nullObject2int(smsRuleMap.get("startHour")) + "," +
					StaticMethod.nullObject2int(smsRuleMap.get("startMin")) + "," +
					StaticMethod.nullObject2int(smsRuleMap.get("endHour")) + "," +
					StaticMethod.nullObject2int(smsRuleMap.get("endMin")) + 
					") ";
					try {
						service.updateTasks(insertSql);
						service.updateTasks(sql2);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (rule.getSuperviseType().contains("IVR")) {
					String insertSql = "insert into complaint_sms(id,CUSTOMERCONTACT,COMPLAINTDESC,CREATETIME,IFLOAD,sheetid,sendtime,TASKSERVICEID,TASKSERVICETYPE,HMFlag)values ('"
							+ UUIDHexGenerator.getInstance().getID() //id
							+ "','"
							+ usersPhonearr[i] //CUSTOMERCONTACT
							+ "','"
							+ content2 //COMPLAINTDESC
							+ "',to_date('"
							+ StaticMethod.getCurrentDateTime() //CREATETIME
							+ "','yyyy-MM-dd hh24:mi:ss'),'0','" //IFLOAD
							+ main.getSheetId() //sheetid
							+ "',to_date('"
							+ sdf.format(dealdate) //sendtime
							+ "','yyyy-MM-dd hh24:mi:ss'),'"
							+ main.getSheetId() //TASKSERVICEID
							+ "','" + 0 //TASKSERVICETYPE
							+ "','" + "Y" //HMFlag
							+ "')";
					BocoLog.info(this, "插入短信语音sql1" + insertSql);
					try {
						service.updateTasks(insertSql);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	public void supervisetaskRecordAddSaveDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		System.out.println("method is supervisetaskRecordAddSaveDeal");
		String id=StaticMethod.null2String(request.getParameter("id"));
		ListedRegulationMain main=this.getListedRegulationMainById(id);
//		CommonTaskMain main=this.getCommonTaskById(id);
		//对复发挂牌 两次确认;空则为不督办
		
//		String city=main.getMainCity();
//		String country=main.getMainCountry();
//		String listedRegulationType=main.getMainListedType();
//		String listedRegulationCycle=main.getMainListedCyc();
//		String majorCn=main.getMainProfessional();//main中专业是中文,需要转义
//		String translateSql="select dictid from taw_system_dicttype where dictid like '1010107%' and dictname ='"+majorCn+"'";
//		String major="";
//		List majorList=service.getSheetAccessoriesList(translateSql);
//		if(majorList!=null&&majorList.size()>0){
//			try {
//				
//				Map map=(Map) majorList.get(0);
//				major=(String) map.get("dictid");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println(major);
		
		
//		String mainNetSortOne=main.getMainNetSortOne();
//		String mainNetSortTwo=main.getMainNetSortTwo();
//		String mainNetSortThree=main.getMainNetSortThree();
//		
		Map maptj=new HashMap();
////		maptj.put("major", major);
//		maptj.put("city", city);
//		maptj.put("country", country);
//		maptj.put("mainNetSortOne", mainNetSortOne);
//		maptj.put("mainNetSortTwo", mainNetSortTwo);
//		maptj.put("mainNetSortThree", mainNetSortThree);
//		maptj.put("listedRegulationType", listedRegulationType);
//		maptj.put("listedRegulationCycle", listedRegulationCycle);
		
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
			.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
						: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		Map result=this.supervisetaskRuleList2(pageIndex, pageSize, maptj);
		List resultList=(List) result.get("result");
		System.out.println(resultList);
		if(resultList!=null&&resultList.size()>0){//匹配相应的督办规则
			SuperviseTaskRule rule=(SuperviseTaskRule) resultList.get(0);//只匹配第一条规则,前面有唯一约束
			
			SuperviseTaskRecord record=new SuperviseTaskRecord();
//			record.setCity(city);
//			record.setCountry(country);
//			record.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//			record.setDeleted("0");
//			record.setSheetId(main.getSheetId());
//			record.setListedRegulationCycle(listedRegulationCycle);
//			record.setListedRegulationType(listedRegulationType);
//			record.setMainNetSortOne(mainNetSortOne);
//			record.setMainNetSortTwo(mainNetSortTwo);
//			record.setMainNetSortThree(mainNetSortThree);
			record.setSupervisetaskRule(rule.getMajor()+"--"+rule.getCity()+"-"+rule.getCountry());
			record.setSupervisetaskResult("未完成");
			record.setSuperviseType(rule.getSuperviseType());//督办方式
			record.setSheetId(main.getSheetId());
			
			String content1="您有一张挂牌整治工单复发挂牌已处理超时，工单号"+main.getSheetId()+"，请及时处理。";
			String content2="您的下属有一张挂牌整治工单复发挂牌已处理超时，工单号"+main.getSheetId()+"，请督办。";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar c=Calendar.getInstance();
			c.setTime(main.getSheetAcceptLimit());
			c.add(Calendar.DATE, Integer.parseInt(rule.getAcceptOverTime1()));//获取接收时限
			Date acceptdate1=c.getTime();//受理时限+天数;发送短信时间
			
			c.setTime(main.getSheetAcceptLimit());
			c.add(Calendar.DATE, Integer.parseInt(rule.getAcceptOverTime2()));
			Date acceptdate2=c.getTime();
			
			c.setTime(main.getSheetAcceptLimit());
			c.add(Calendar.DATE, Integer.parseInt(rule.getAcceptOverTime3()));
			Date acceptdate3=c.getTime();
			
			c.setTime(main.getSheetAcceptLimit());
			c.add(Calendar.DATE, Integer.parseInt(rule.getAcceptOverTime4()));
			Date acceptdate4=c.getTime();
			
			c.setTime(main.getSheetCompleteLimit());//获取受理时限
			c.add(Calendar.DATE, Integer.parseInt(rule.getDealOverTime1()));
			Date dealdate1=c.getTime();
			
			c.setTime(main.getSheetCompleteLimit());//获取受理时限
			c.add(Calendar.DATE, Integer.parseInt(rule.getDealOverTime2()));
			Date dealdate2=c.getTime();
			
			c.setTime(main.getSheetCompleteLimit());//获取受理时限
			c.add(Calendar.DATE, Integer.parseInt(rule.getDealOverTime3()));
			Date dealdate3=c.getTime();
			
			c.setTime(main.getSheetCompleteLimit());//获取受理时限
			c.add(Calendar.DATE, Integer.parseInt(rule.getDealOverTime4()));
			Date dealdate4=c.getTime();
			
			String dept1ids=rule.getNoticeUser1();//获取到的是SuperviseTaskRule中的NoticeUser1 字段,可能为aa,bb,cc
			String dept2ids=rule.getNoticeUser2();
			String dept3ids=rule.getNoticeUser3();
			String dept4ids=rule.getNoticeUser4();
			
			String querySql1="select post.userids from sms_rule_userrefpost post,  " +
					"		(select regexp_substr('"+dept1ids+"'," +
					"		'[^,]+',1,b.lv)ids from dual,(select level lv from dual connect by level < 10)b   ) c where c.ids=post.id";
			String querySql2="select post.userids from sms_rule_userrefpost post,  " +
			"		(select regexp_substr('"+dept2ids+"'," +
			"		'[^,]+',1,b.lv)ids from dual,(select level lv from dual connect by level < 10)b   ) c where c.ids=post.id";
			String querySql3="select post.userids from sms_rule_userrefpost post,  " +
			"		(select regexp_substr('"+dept3ids+"'," +
			"		'[^,]+',1,b.lv)ids from dual,(select level lv from dual connect by level < 10)b   ) c where c.ids=post.id";
			String querySql4="select post.userids from sms_rule_userrefpost post,  " +
			"		(select regexp_substr('"+dept4ids+"'," +
			"		'[^,]+',1,b.lv)ids from dual,(select level lv from dual connect by level < 10)b   ) c where c.ids=post.id";
			List userList1=service.getSheetAccessoriesList(querySql1);
			List userList2=service.getSheetAccessoriesList(querySql2);
			List userList3=service.getSheetAccessoriesList(querySql3);
			List userList4=service.getSheetAccessoriesList(querySql4);
			
			System.out.println("usersList1--"+userList1);
			System.out.println("usersList2--"+userList2);
			System.out.println("userslist3--"+userList3);
			System.out.println("usersList4--"+userList4);
			/*
			 * 查询出来的结果为userids的列表,然后去tawSystemUser表中查询mobile
			 */
			String users1=(String) this.getUsers(userList1).get("users");
			String users2=(String) this.getUsers(userList2).get("users");
			String users3=(String) this.getUsers(userList3).get("users");
			String users4=(String) this.getUsers(userList4).get("users");
			System.out.println("users1--"+users1);
			System.out.println("users2--"+users2);
			System.out.println("users3--"+users3);
			System.out.println("users4--"+users4);
			
			//得到userids1-4,以字符串形式进行分割
			record.setNoticeUser1(users1);
			record.setNoticeUser2(users2);
			record.setNoticeUser3(users3);
			record.setNoticeUser4(users4);
			//根据userid查找电话,首先分割字符串
			String []users1arr=users1.split(",");
			String []users2arr=users2.split(",");
			String []users3arr=users3.split(",");
			String []users4arr=users4.split(",");
			//每人一个电话,名字
			String []usersPhone1arr=(String[]) this.getUsersNamePhone(users1arr).get("usersPhonearr");//通知角色1电话
			String []usersPhone2arr=(String[]) this.getUsersNamePhone(users2arr).get("usersPhonearr");
			String []usersPhone3arr=(String[]) this.getUsersNamePhone(users3arr).get("usersPhonearr");
			String []usersPhone4arr=(String[]) this.getUsersNamePhone(users4arr).get("usersPhonearr");
			String []usersName1arr=(String[]) this.getUsersNamePhone(users1arr).get("usersPhonearr");
			String []usersName2arr=(String[]) this.getUsersNamePhone(users2arr).get("usersPhonearr");
			String []usersName3arr=(String[]) this.getUsersNamePhone(users3arr).get("usersPhonearr");
			String []usersName4arr=(String[]) this.getUsersNamePhone(users4arr).get("usersPhonearr");
			
			//存储到数据库
			String users1Phone=(String) this.getUsersNamePhone(users1arr).get("usersPhone");
			String users2Phone=(String) this.getUsersNamePhone(users2arr).get("usersPhone");
			String users3Phone=(String) this.getUsersNamePhone(users3arr).get("usersPhone");
			String users4Phone=(String) this.getUsersNamePhone(users4arr).get("usersPhone");
			String users1Name=(String) this.getUsersNamePhone(users1arr).get("usersName");
			String users2Name=(String) this.getUsersNamePhone(users2arr).get("usersName");;
			String users3Name=(String) this.getUsersNamePhone(users3arr).get("usersName");
			String users4Name=(String) this.getUsersNamePhone(users4arr).get("usersName");
			
			System.out.println("***usersPhone***");
			System.out.println(users1Phone);
			System.out.println(users2Phone);
			System.out.println(users3Phone);
			System.out.println(users4Phone);
			System.out.println("***usersName***");
			System.out.println(users1Name);
			System.out.println(users2Name);
			System.out.println(users3Name);
			System.out.println(users4Name);
			
			record.setNoticeUsername1(users1Name);
			record.setNoticeUsername2(users2Name);
			record.setNoticeUsername3(users3Name);
			record.setNoticeUsername4(users4Name);
			record.setNoticeUserphone1(users1Phone);
			record.setNoticeUserphone2(users2Phone);
			record.setNoticeUserphone3(users3Phone);
			record.setNoticeUserphone4(users4Phone);
			
			
			this.savesupervisetaskRecord(record);
//			当前处理角色
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
			.getSession().getAttribute("sessionform");
			String nowUser=sessionform.getUserid();
			String nowUserphone= sessionform.getContactMobile();
			this.sendSMSIVR(new String []{nowUser}, new String []{nowUserphone}, main, rule,acceptdate1, content1);
			
			//督办角色
			this.sendSMSIVR(users1arr, usersPhone1arr, main, rule,dealdate1, content2);
			this.sendSMSIVR(users2arr, usersPhone2arr, main, rule,dealdate2, content2);
			this.sendSMSIVR(users3arr, usersPhone3arr, main, rule,dealdate3, content2);
			this.sendSMSIVR(users4arr, usersPhone4arr, main, rule,dealdate4, content2);
			if(false){//工单接单通知
				this.sendSMSIVR(users1arr, usersPhone1arr, main, rule,dealdate1, content1);
				this.sendSMSIVR(users2arr, usersPhone2arr, main, rule,dealdate2, content1);
				this.sendSMSIVR(users3arr, usersPhone3arr, main, rule,dealdate3, content1);
				this.sendSMSIVR(users4arr, usersPhone4arr, main, rule,dealdate4, content1);
			}
		}
	}
	
	
//	public void supervisetaskRecordAddSaveAccept(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)throws Exception{
	public void supervisetaskRecordAddSaveAccept(Map tempmap) throws Exception{
		IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		System.out.println("method is supervisetaskRecordAddSaveAccept");
//		String id=StaticMethod.null2String(request.getParameter("id"));
		String sheetId=(String) tempmap.get("sheetId");
		System.out.println("sheetId:--"+sheetId);
		ListedRegulationMain main=ISuperviseTaskDAO.getListedRegulationMainBySheetId(sheetId);
		if(main==null){
			main=new ListedRegulationMain();
			System.out.println("没有找到工单");
			return;
		}
		//对复发挂牌 两次确认;空则为不督办
		if(main.getMainIfReListed()==null||main.getMainIfReListed().equals("")){
			
		}
		
		String city=main.getMainCity();
		String country=main.getMainCountry();
		String listedRegulationType=main.getMainListedType();
		String listedRegulationCycle=main.getMainListedCyc();
		String majorCn=main.getMainProfessional();//main中专业是中文,需要转义
		String translateSql="select dictid from taw_system_dicttype where dictid like '1010107%' and dictname ='"+majorCn+"'";
		String major="";
		List majorList=service.getSheetAccessoriesList(translateSql);
		if(majorList!=null&&majorList.size()>0){
			try {
				
				Map map=(Map) majorList.get(0);
				major=(String) map.get("dictid");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(major);
		
		
		String mainNetSortOne=main.getMainNetSortOne();
		String mainNetSortTwo=main.getMainNetSortTwo();
		String mainNetSortThree=main.getMainNetSortThree();
		
		Map maptj=new HashMap();
//		maptj.put("major", major);
		maptj.put("city", city);
		maptj.put("country", country);
		maptj.put("mainNetSortOne", mainNetSortOne);
		maptj.put("mainNetSortTwo", mainNetSortTwo);
		maptj.put("mainNetSortThree", mainNetSortThree);
		maptj.put("listedRegulationType", listedRegulationType);
		maptj.put("listedRegulationCycle", listedRegulationCycle);
		
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
//		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
//			.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
//		final Integer pageIndex = new Integer(GenericValidator
//				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
//						: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
//		Map result=this.supervisetaskRuleList2(pageIndex, pageSize, maptj);
		Map result=this.supervisetaskRuleList2(new Integer(1), new Integer(-1), maptj);
		List resultList=(List) result.get("result");
		System.out.println(resultList);
		if(resultList!=null&&resultList.size()>0){//匹配相应的督办规则
			SuperviseTaskRule rule=(SuperviseTaskRule) resultList.get(0);//只匹配第一条规则,前面有唯一约束
			
			SuperviseTaskRecord record=new SuperviseTaskRecord();
			record.setCity(city);
			record.setCountry(country);
			record.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			record.setDeleted("0");
			record.setSheetId(main.getSheetId());
			record.setListedRegulationCycle(listedRegulationCycle);
			record.setListedRegulationType(listedRegulationType);
			record.setMainNetSortOne(mainNetSortOne);
			record.setMainNetSortTwo(mainNetSortTwo);
			record.setMainNetSortThree(mainNetSortThree);
//			record.setNoticeUser1(rule.getNoticeUser1());//用户
//			record.setNoticeUser2(rule.getNoticeUser2());
//			record.setNoticeUser3(rule.getNoticeUser3());
//			record.setNoticeUser4(rule.getNoticeUser4());
//			record.setNoticeUserphone1(rule.getNoticeUserphone1());//电话
//			record.setNoticeUserphone2(rule.getNoticeUserphone2());
//			record.setNoticeUserphone3(rule.getNoticeUserphone3());
//			record.setNoticeUserphone4(rule.getNoticeUserphone4());
			record.setSupervisetaskRule(rule.getMajor()+"--"+rule.getCity()+"-"+rule.getCountry());
			record.setSupervisetaskResult("未完成");
			record.setSuperviseType(rule.getSuperviseType());//督办方式
			record.setSheetId(main.getSheetId());
			
			String content1="您有一张挂牌整治工单复发挂牌超时未受理，工单号"+main.getSheetId()+"，请及时处理。";
			String content2="您的下属有一张挂牌整治工单复发挂牌超时未受理，工单号"+main.getSheetId()+"，请督办。";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar c=Calendar.getInstance();
			c.setTime(main.getSheetAcceptLimit());
			c.add(Calendar.DATE, Integer.parseInt(rule.getAcceptOverTime1()));//获取接收时限
			Date acceptdate1=c.getTime();//受理时限+天数;发送短信时间
			
			c.setTime(main.getSheetAcceptLimit());
			c.add(Calendar.DATE, Integer.parseInt(rule.getAcceptOverTime2()));
			Date acceptdate2=c.getTime();
			
			c.setTime(main.getSheetAcceptLimit());
			c.add(Calendar.DATE, Integer.parseInt(rule.getAcceptOverTime3()));
			Date acceptdate3=c.getTime();
			
			c.setTime(main.getSheetAcceptLimit());
			c.add(Calendar.DATE, Integer.parseInt(rule.getAcceptOverTime4()));
			Date acceptdate4=c.getTime();
			
			c.setTime(main.getSheetCompleteLimit());//获取受理时限
			c.add(Calendar.DATE, Integer.parseInt(rule.getDealOverTime1()));
			Date dealdate1=c.getTime();
			
			c.setTime(main.getSheetCompleteLimit());//获取受理时限
			c.add(Calendar.DATE, Integer.parseInt(rule.getDealOverTime2()));
			Date dealdate2=c.getTime();
			
			c.setTime(main.getSheetCompleteLimit());//获取受理时限
			c.add(Calendar.DATE, Integer.parseInt(rule.getDealOverTime3()));
			Date dealdate3=c.getTime();
			
			c.setTime(main.getSheetCompleteLimit());//获取受理时限
			c.add(Calendar.DATE, Integer.parseInt(rule.getDealOverTime4()));
			Date dealdate4=c.getTime();
			
			String dept1ids=rule.getNoticeUser1();//获取到的是SuperviseTaskRule中的NoticeUser1 字段,可能为aa,bb,cc
			String dept2ids=rule.getNoticeUser2();
			String dept3ids=rule.getNoticeUser3();
			String dept4ids=rule.getNoticeUser4();
			
			String querySql1="select post.userids from sms_rule_userrefpost post,  " +
					"		(select regexp_substr('"+dept1ids+"'," +
					"		'[^,]+',1,b.lv)ids from dual,(select level lv from dual connect by level < 10)b   ) c where c.ids=post.id";
			String querySql2="select post.userids from sms_rule_userrefpost post,  " +
			"		(select regexp_substr('"+dept2ids+"'," +
			"		'[^,]+',1,b.lv)ids from dual,(select level lv from dual connect by level < 10)b   ) c where c.ids=post.id";
			String querySql3="select post.userids from sms_rule_userrefpost post,  " +
			"		(select regexp_substr('"+dept3ids+"'," +
			"		'[^,]+',1,b.lv)ids from dual,(select level lv from dual connect by level < 10)b   ) c where c.ids=post.id";
			String querySql4="select post.userids from sms_rule_userrefpost post,  " +
			"		(select regexp_substr('"+dept4ids+"'," +
			"		'[^,]+',1,b.lv)ids from dual,(select level lv from dual connect by level < 10)b   ) c where c.ids=post.id";
			List userList1=service.getSheetAccessoriesList(querySql1);
			List userList2=service.getSheetAccessoriesList(querySql2);
			List userList3=service.getSheetAccessoriesList(querySql3);
			List userList4=service.getSheetAccessoriesList(querySql4);
			
			System.out.println("usersList1--"+userList1);
			System.out.println("usersList2--"+userList2);
			System.out.println("userslist3--"+userList3);
			System.out.println("usersList4--"+userList4);
			/*
			 * 查询出来的结果为userids的列表,然后去tawSystemUser表中查询mobile
			 */
			String users1=(String) this.getUsers(userList1).get("users");
			String users2=(String) this.getUsers(userList2).get("users");
			String users3=(String) this.getUsers(userList3).get("users");
			String users4=(String) this.getUsers(userList4).get("users");
			System.out.println("users1--"+users1);
			System.out.println("users2--"+users2);
			System.out.println("users3--"+users3);
			System.out.println("users4--"+users4);
			
			//得到userids1-4,以字符串形式进行分割
			record.setNoticeUser1(users1);
			record.setNoticeUser2(users2);
			record.setNoticeUser3(users3);
			record.setNoticeUser4(users4);
			//根据userid查找电话,首先分割字符串
			String []users1arr=users1.split(",");
			String []users2arr=users2.split(",");
			String []users3arr=users3.split(",");
			String []users4arr=users4.split(",");
			//每人一个电话,名字
			String []usersPhone1arr=(String[]) this.getUsersNamePhone(users1arr).get("usersPhonearr");//通知角色1电话
			String []usersPhone2arr=(String[]) this.getUsersNamePhone(users2arr).get("usersPhonearr");
			String []usersPhone3arr=(String[]) this.getUsersNamePhone(users3arr).get("usersPhonearr");
			String []usersPhone4arr=(String[]) this.getUsersNamePhone(users4arr).get("usersPhonearr");
			String []usersName1arr=(String[]) this.getUsersNamePhone(users1arr).get("usersPhonearr");
			String []usersName2arr=(String[]) this.getUsersNamePhone(users2arr).get("usersPhonearr");
			String []usersName3arr=(String[]) this.getUsersNamePhone(users3arr).get("usersPhonearr");
			String []usersName4arr=(String[]) this.getUsersNamePhone(users4arr).get("usersPhonearr");
			
			//存储到数据库
			String users1Phone=(String) this.getUsersNamePhone(users1arr).get("usersPhone");
			String users2Phone=(String) this.getUsersNamePhone(users2arr).get("usersPhone");
			String users3Phone=(String) this.getUsersNamePhone(users3arr).get("usersPhone");
			String users4Phone=(String) this.getUsersNamePhone(users4arr).get("usersPhone");
			String users1Name=(String) this.getUsersNamePhone(users1arr).get("usersName");
			String users2Name=(String) this.getUsersNamePhone(users2arr).get("usersName");;
			String users3Name=(String) this.getUsersNamePhone(users3arr).get("usersName");
			String users4Name=(String) this.getUsersNamePhone(users4arr).get("usersName");
			
			System.out.println("***usersPhone***");
			System.out.println(users1Phone);
			System.out.println(users2Phone);
			System.out.println(users3Phone);
			System.out.println(users4Phone);
			System.out.println("***usersName***");
			System.out.println(users1Name);
			System.out.println(users2Name);
			System.out.println(users3Name);
			System.out.println(users4Name);
			
			record.setNoticeUsername1(users1Name);
			record.setNoticeUsername2(users2Name);
			record.setNoticeUsername3(users3Name);
			record.setNoticeUsername4(users4Name);
			record.setNoticeUserphone1(users1Phone);
			record.setNoticeUserphone2(users2Phone);
			record.setNoticeUserphone3(users3Phone);
			record.setNoticeUserphone4(users4Phone);
			
			
			this.savesupervisetaskRecord(record);
			//当前处理角色
//			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
//			.getSession().getAttribute("sessionform");
//			String nowUser=sessionform.getUserid();
//			String nowUserphone= sessionform.getContactMobile();
			String nowUser=(String) tempmap.get("userid");
			String nowUserphone=(String) tempmap.get("operaterContact");
			if(nowUser==null||nowUser.equals("")){
				System.out.println("从Session获取到的UserId为NULL");
			}
			this.sendSMSIVR(new String []{nowUser}, new String []{nowUserphone}, main, rule,acceptdate1, content1);
			//督办角色
			this.sendSMSIVR(users1arr, usersPhone1arr, main, rule,acceptdate1, content2);
			this.sendSMSIVR(users2arr, usersPhone2arr, main, rule,acceptdate2, content2);
			this.sendSMSIVR(users3arr, usersPhone3arr, main, rule,acceptdate3, content2);
			this.sendSMSIVR(users4arr, usersPhone4arr, main, rule,acceptdate4, content2);
			
		}
	}
	/*
	 * (non-Javadoc)
	 * 受理督办完成
	 */
	public void supervisetaskRecordDoneAccept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map mainMap=(Map) request.getAttribute("mainMap");
		String id=(String) mainMap.get("id");
		
//		ListedRegulationMain main=this.getListedRegulationMainById(id);
		CommonTaskMain main=this.getCommonTaskById(id);
		SuperviseTaskRecord t=this.getSuperviseTaskRecordBySheetId(main.getSheetId());
		t.setSupervisetaskResult("已受理未完成");
		this.savesupervisetaskRecord(t);
		
		IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService) ApplicationContextHolder
				.getInstance().getBean("IDownLoadSheetAccessoriesService");
		String sql = "delete from sms_monitor where service_id='"
				+ main.getSheetId() + "'";
		String sql2 = "delete from complaint_sms where sheetid='"
				+ main.getSheetId() + "'";
		try {
			service.executeProcedure(sql);
			service.executeProcedure(sql2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * (non-Javadoc)
	 * 处理督办完成,督办任务结束
	 */
	public void supervisetaskRecordDone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map mainMap=(Map) request.getAttribute("mainMap");
		String id=(String) mainMap.get("id");
		
//		ListedRegulationMain main=this.getListedRegulationMainById(id);
		CommonTaskMain main=this.getCommonTaskById(id);
		SuperviseTaskRecord t=this.getSuperviseTaskRecordBySheetId(main.getSheetId());
		t.setSupervisetaskResult("已完成");
		this.savesupervisetaskRecord(t);
		
		IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService)ApplicationContextHolder
													.getInstance().getBean("IDownLoadSheetAccessoriesService");
		String sql="delete from sms_monitor where service_id='"+main.getSheetId()+"'";
		String sql2="delete from complaint_sms where sheetid='"+main.getSheetId()+"'";
		try{
			service.executeProcedure(sql);
			service.executeProcedure(sql2);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public CommonTaskMain getCommonTaskById(String id){
		return ISuperviseTaskDAO.getCommonTaskMainById(id);
	}
	
	public void newBulletin1(String str){
		System.out.println("SuperviseTaskManagerImpl,newBulletin1方法开始");
		try {
			String endpoint = XmlManage.getFile("/config/supervisetaskMainAdd-util.xml").getProperty("webservice.url");
		  	System.out.println("SuperviseTaskManagerImpl,webservice接口,服务端地址===="+endpoint);
		  	URL url=new URL(endpoint);
		  	
		  	String targetNamespace=XmlManage.getFile("/config/supervisetaskMainAdd-util.xml").getProperty("webservice.targetNamespace");
		  	String operationName = "gain";  
		  	
		  	Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			
//			参数1：wsdl文件中的targetNamespace，参数2：  WSDL里面描述的接口名称(要调用的方法)
			call.setOperationName(new javax.xml.namespace.QName(targetNamespace, operationName));
			
			
			call.addParameter(str, XMLType.XSD_STRING, ParameterMode.IN);
			
//			 设置被调用方法的返回值类型
			call.setReturnType(XMLType.XSD_STRING);
			try {
				String result=(String) call.invoke(new Object[]{str});
				
			} catch (Exception e) {
				System.out.println("SupervisetaskError");
				e.printStackTrace();
			}
			
//			 接口方法的参数名, 参数类型,参数模式  IN(输入), OUT(输出) or INOUT(输入输出)
			
		} catch (Exception e) {
			System.out.println("SuperviseTaskManagerImpl,webservice接口，公告中调用客服支撑报错！");
			e.printStackTrace();
		}
		System.out.println("SuperviseTaskManagerImpl,webservice接口，公告中调用客服支撑完毕！");

	}

	public String querySheetStatusTest(String sheetid){
		System.out.println("SuperviseTaskManagerImpl,newBulletin1方法开始");
		try {
			String endpoint = XmlManage.getFile("/config/supervisetaskMainAdd-util2.xml").getProperty("webservice.url");
		  	System.out.println("SuperviseTaskManagerImpl,webservice接口,服务端地址===="+endpoint);
		  	URL url=new URL(endpoint);
		  	
		  	String targetNamespace=XmlManage.getFile("/config/supervisetaskMainAdd-util2.xml").getProperty("webservice.targetNamespace");
		  	String operationName = "querySheetStatus";  
		  	
		  	Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			
//			参数1：wsdl文件中的targetNamespace，参数2：  WSDL里面描述的接口名称(要调用的方法)
			call.setOperationName(new javax.xml.namespace.QName(targetNamespace, operationName));
			
			
			call.addParameter(sheetid, XMLType.XSD_STRING, ParameterMode.IN);
			
//			 设置被调用方法的返回值类型
			call.setReturnType(XMLType.XSD_STRING);
			try {
				String result=(String) call.invoke(new Object[]{sheetid});
				System.out.println("result:"+result);
				
			} catch (Exception e) {
				System.out.println("SupervisetaskError");
				e.printStackTrace();
			}
			
//			 接口方法的参数名, 参数类型,参数模式  IN(输入), OUT(输出) or INOUT(输入输出)
			
		} catch (Exception e) {
			System.out.println("SuperviseTaskManagerImpl,webservice接口，公告中调用客服支撑报错！");
			e.printStackTrace();
		}
		System.out.println("SuperviseTaskManagerImpl,webservice接口，公告中调用客服支撑完毕！");
		return "";
	}
	
	public String querySheetStatus(String sheetid){
		JSONObject json=new JSONObject();
		String status="";
		try {
			
		sheetid="JX-051-190513-24958";
		sheetid="JX-666-190311-13611";
		String ss=this.querySheetStatusTest(sheetid);
		IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		String sql="select status from COMMONFAULT_MAIN where sheetid='"+sheetid+"' ";
		String sql2="select status from listedregulation_main where sheetid='"+sheetid+"'";
		List mainlist=service.getSheetAccessoriesList(sql);
		System.out.println("Supervisetaskmainlist1:"+mainlist);
		if(mainlist!=null&&mainlist.size()>0){
			Map map=(Map) mainlist.get(0);
			status=String.valueOf(map.get("status")) ;
		}else{
			mainlist=service.getSheetAccessoriesList(sql2);
			System.out.println("Supervisetaskmainlist1:"+mainlist);
			if(mainlist!=null&&mainlist.size()>0){
				Map map=(Map) mainlist.get(0);
				status=String.valueOf( map.get("status"));
			}
		}
		System.out.println("工单状态是:"+status);
		json.put("status", status);
		json.put("error", "");
		System.out.println(json);
		return json.toString();
		} catch (Exception e) {
			json=new JSONObject();
			json.put("status", "");
			json.put("error", e.fillInStackTrace());
			System.out.println("SuperviseTaskErrorQuery");
			e.printStackTrace();
		}
		return json.toString();
	}
}
