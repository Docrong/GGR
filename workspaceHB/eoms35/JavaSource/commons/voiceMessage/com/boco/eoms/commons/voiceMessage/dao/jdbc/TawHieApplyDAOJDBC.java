package com.boco.eoms.commons.voiceMessage.dao.jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.voiceMessage.dao.TawHieApplyDAO;
import com.boco.eoms.commons.voiceMessage.model.TawConMem;
import com.boco.eoms.commons.voiceMessage.model.TawConfInfo;
import com.boco.eoms.commons.voiceMessage.webapp.form.TawHieApplyForm;

public class TawHieApplyDAOJDBC extends BaseDaoJdbc implements
TawHieApplyDAO{
	public void addUser(TawHieApplyForm form){

		
		int userId =Integer.parseInt(form.getUserId());
		String userName = form.getUserName();
		String userTel = form.getUserTel();
		int userType = Integer.parseInt(form.getUserType());
		String userCode = form.getUserCode();
		String sql = "insert into T_user_info  values ("
			+ userId
			+ ",'"
			+ userName.trim()
			+ "','"
			+ userTel.trim()
			+ "',"
			+ userType
			+ ",'"
			+ userCode.trim()
			+  "')";
	getJdbcTemplate().execute(sql);
	}
	
	public int addConference(TawHieApplyForm form){

		
		List userDuty = new ArrayList();
		List list = new ArrayList();
		String confName = form.getConfName();
		int confTrunkNo = form.getConfTrunkNo();
		String confOrganizer = form.getConfOrganizer();
		String confBeginTime = form.getConfBeginTime();
		String confEndTime = form.getConfEndTime();
		int confState = form.getConfState();
		int confRecord = form.getConfRecord();
		String confRFile = confOrganizer + confBeginTime;
		int isCallout = form.getIsCallout();

		String insertTime = StaticMethod.getCurrentDateTime();

		int confNo = 0;
		

			String sql = "insert into t_conference_info(conf_name, conf_trunk_no, conf_organiger, " +
					"conf_begintime, conf_endtime, conf_state, conf_record, conf_r_file, is_callout, inputtime) " +
					"values('"+confName+"',"+
					confTrunkNo+",'"+
					confOrganizer+"','"+
					StaticMethod.getTimestamp(confBeginTime)+"','"+
					StaticMethod.getTimestamp(confEndTime)+"',"+
					confState+","+
					confRecord+",'"+
					confRFile+"',"+
					isCallout+",'"+
					insertTime+"')";

			getJdbcTemplate().execute(sql);

			
			String sql1 = "select conf_no from t_conference_info where inputtime = '" + insertTime + "'";
			 list = getJdbcTemplate().queryForList(sql1);
			 Iterator _objIterator = list.iterator();
				while (_objIterator.hasNext()) {
					
					Map _objMap = (Map) _objIterator.next();
					userDuty.add(StaticMethod
							.nullObject2String(_objMap.get("conf_no")));
				}
			confNo = Integer.parseInt(userDuty.get(0).toString());
			// System.out.println("confNo = " + confNo);
			
		return confNo;
	}
	
	public List getAllMems(){
		ArrayList list = new ArrayList();
		
		List listTemp = new ArrayList();
		String	sql = "select * from t_user_info";
			listTemp = getJdbcTemplate().queryForList(sql);
			 Iterator _objIterator = listTemp.iterator();

			
			while (_objIterator.hasNext()) {
				TawConMem tawConMem = new TawConMem();
				Map _objMap = (Map) _objIterator.next();
				
				tawConMem.setUserId(Integer.parseInt(_objMap.get("userid").toString()));
				tawConMem.setUserName(StaticMethod
						.nullObject2String(_objMap.get("userName")));
				tawConMem.setUserTel(StaticMethod
						.nullObject2String(_objMap.get("userTel")));

				switch (Integer.parseInt(_objMap.get("user_type").toString())) {
				case 0:
					tawConMem.setUserType("维护调度员");
					break;
				case 1:
					tawConMem.setUserType("维护工程师");
					break;
				case 2:
					tawConMem.setUserType("维护支持人员");
					break;
				case 3:
					tawConMem.setUserType("协查员");
					break;
				case 4:
					tawConMem.setUserType("协查请求人");
					break;
				case 5:
					tawConMem.setUserType("VIP客户");
					break;
				default:
					break;
			}

				list.add(tawConMem);
			}
		
		return list;
	}
	
	public void addConfMems(int confNo, int[] userIds, String[] userNames, String[] userPhones,
            int[] userTypes, int[] joinModes){

		

			String sql = "insert into t_conference_member_info(conf_no, conf_member_name, conf_member_phone, " +
					"conf_join_state, join_mode, userid) values(";

			for (int i = 0; i < userIds.length; i++) {
			String	sql1=confNo+",'"+userNames[i]+"','"+userPhones[i]+"',"+1+","+joinModes[i]+","+userIds[i]+")";
			
				getJdbcTemplate().execute(sql+sql1);
			}
	}
	public int getConfInfoCount(String con){
		int ret = 0;

		List userDuty = new ArrayList();
		List list = new ArrayList();

			String sql = "select count(userid) userid from t_conference_member_info " + con;
			list = getJdbcTemplate().queryForList(sql);
			 Iterator _objIterator = list.iterator();
				while (_objIterator.hasNext()) {
					
					Map _objMap = (Map) _objIterator.next();
					userDuty.add(StaticMethod
							.nullObject2String(_objMap.get("userid")));
				}
				ret = Integer.parseInt(userDuty.get(0).toString());

			
		return ret;
	}
	
	public List listConfInfo(int offset, int limit, String con) {
		ArrayList list = new ArrayList();
		List listTemp = new ArrayList();
			String sql = "select * from t_conference_info " + con;
			
			listTemp = getJdbcTemplate().queryForList(sql);
			 Iterator _objIterator = listTemp.iterator();
				while (_objIterator.hasNext()) {
					TawConfInfo tawConfInfo = new TawConfInfo();
					Map _objMap = (Map) _objIterator.next();
				
					tawConfInfo.setConfNo(Integer.parseInt(_objMap.get("conf_no").toString()));
					tawConfInfo.setConfName(StaticMethod
							.nullObject2String(_objMap.get("conf_name")));
					tawConfInfo.setConfTrunkNo(Integer.parseInt(_objMap.get("conf_trunk_no").toString()));
					tawConfInfo.setConfOrganizer(StaticMethod
							.nullObject2String(_objMap.get("conf_organiger")));
					tawConfInfo.setConfBeginTime(StaticMethod
							.nullObject2String(_objMap.get("conf_begintime")));
					tawConfInfo.setConfEndTime(StaticMethod
							.nullObject2String(_objMap.get("conf_endtime")));
					
					switch (Integer.parseInt(_objMap.get("conf_state").toString())) {
					case 0:
						tawConfInfo.setConfState("未分配资源");
						break;
					case 1:
						tawConfInfo.setConfState("已分配资源");
						break;
					case 2:
						tawConfInfo.setConfState("已结束");
						break;
					default:
						break;
				}
				switch (Integer.parseInt(_objMap.get("conf_record").toString())) {
					case 0:
						tawConfInfo.setConfRecord("否");
						break;
					case 1:
						tawConfInfo.setConfRecord("是");
						break;
					default:
						break;
				}
				switch (Integer.parseInt(_objMap.get("is_callout").toString())) {
					case 0:
						tawConfInfo.setIsCallout("否");
						break;
					case 1:
						tawConfInfo.setIsCallout("是");
						break;
					default:
						break;
				}
				tawConfInfo.setConfRFile(StaticMethod
						.nullObject2String(_objMap.get("conf_r_file")));

				list.add(tawConfInfo);
				}
			
		
		return list;
	}
	
	public List listConfInfo(TawHieApplyForm form) {
		ArrayList list = new ArrayList();
		List listTemp = new ArrayList();
		int confNo = form.getConfNo();
		//String beginTime = form.getConfBeginTime();
		String con = "conf_no = " + confNo;

		
			String sql = "select * from t_conference_info where " + con;
			//System.out.println("sql = " + sql);
			listTemp = getJdbcTemplate().queryForList(sql);
			 Iterator _objIterator = listTemp.iterator();
				while (_objIterator.hasNext()) {
					TawConfInfo tawConfInfo = new TawConfInfo();
					Map _objMap = (Map) _objIterator.next();
					tawConfInfo.setConfNo(confNo);
					tawConfInfo.setConfName(StaticMethod
							.nullObject2String(_objMap.get("conf_name")));
					tawConfInfo.setConfTrunkNo(Integer.parseInt(_objMap.get("conf_trunk_no").toString()));
					tawConfInfo.setConfOrganizer(StaticMethod
							.nullObject2String(_objMap.get("conf_organiger")));
					tawConfInfo.setConfBeginTime(StaticMethod
							.nullObject2String(_objMap.get("conf_begintime")));
					tawConfInfo.setConfEndTime(StaticMethod
							.nullObject2String(_objMap.get("conf_endtime")));
					switch (Integer.parseInt(_objMap.get("conf_state").toString())) {
					case 0:
						tawConfInfo.setConfState("未分配资源");
						break;
					case 1:
						tawConfInfo.setConfState("已分配资源");
						break;
					case 2:
						tawConfInfo.setConfState("已结束");
						break;
					default:
						break;
				}
				switch (Integer.parseInt(_objMap.get("conf_record").toString())) {
					case 0:
						tawConfInfo.setConfRecord("否");
						break;
					case 1:
						tawConfInfo.setConfRecord("是");
						break;
					default:
						break;
				}
				switch (Integer.parseInt(_objMap.get("is_callout").toString())) {
					case 0:
						tawConfInfo.setIsCallout("否");
						break;
					case 1:
						tawConfInfo.setIsCallout("是");
						break;
					default:
						break;
				}
				tawConfInfo.setConfRFile(StaticMethod
						.nullObject2String(_objMap.get("conf_r_file")));

				list.add(tawConfInfo);
					
					
					
				}

			
		return list;
	}	
	
	public List getConfInfo(TawHieApplyForm form) {
		ArrayList list = new ArrayList();
		List listTemp = new ArrayList();
		int confNo = form.getConfNo();
		String beginTime = form.getConfBeginTime();
		
		String name = form.getConfName();
		
		//String con = "conf_no = " + confNo;

		
			String sql = "select * from t_conference_info where 1=1 ";
			if(beginTime != null&&!beginTime.equals("") ){
				beginTime = beginTime.substring(0, 11);
                sql += " and conf_begintime between '" + beginTime + "00:00:00.00000' " +
			       " and '" + beginTime + "23:59:59.99999'";
              }
              if(confNo != 0){

            	  sql += " and conf_no =" + confNo ;

              }
              if(  name != null&&!name.equals("")){
            	  sql += " and conf_Name like '"+name  +"%'";
                              }
			
			
			
			
			
			//System.out.println("sql = " + sql);
			listTemp = getJdbcTemplate().queryForList(sql);
			 Iterator _objIterator = listTemp.iterator();
				while (_objIterator.hasNext()) {
					TawConfInfo tawConfInfo = new TawConfInfo();
					Map _objMap = (Map) _objIterator.next();
					tawConfInfo.setConfNo(Integer.parseInt(_objMap.get("conf_no").toString()));
					tawConfInfo.setConfName(StaticMethod
							.nullObject2String(_objMap.get("conf_name")));
					tawConfInfo.setConfTrunkNo(Integer.parseInt(_objMap.get("conf_trunk_no").toString()));
					tawConfInfo.setConfOrganizer(StaticMethod
							.nullObject2String(_objMap.get("conf_organiger")));
					tawConfInfo.setConfBeginTime(StaticMethod
							.nullObject2String(_objMap.get("conf_begintime")));
					tawConfInfo.setConfEndTime(StaticMethod
							.nullObject2String(_objMap.get("conf_endtime")));
					switch (Integer.parseInt(_objMap.get("conf_state").toString())) {
					case 0:
						tawConfInfo.setConfState("未分配资源");
						break;
					case 1:
						tawConfInfo.setConfState("已分配资源");
						break;
					case 2:
						tawConfInfo.setConfState("已结束");
						break;
					default:
						break;
				}
				switch (Integer.parseInt(_objMap.get("conf_record").toString())) {
					case 0:
						tawConfInfo.setConfRecord("否");
						break;
					case 1:
						tawConfInfo.setConfRecord("是");
						break;
					default:
						break;
				}
				switch (Integer.parseInt(_objMap.get("is_callout").toString())) {
					case 0:
						tawConfInfo.setIsCallout("否");
						break;
					case 1:
						tawConfInfo.setIsCallout("是");
						break;
					default:
						break;
				}
				tawConfInfo.setConfRFile(StaticMethod
						.nullObject2String(_objMap.get("conf_r_file")));

				list.add(tawConfInfo);
					
					
					
				}

			
		return list;
	}	
	
	public List listConfInfo(int confNo)  {
		ArrayList list = new ArrayList();
		List listTemp = new ArrayList();

		String con = "conf_no = " + confNo;

		
			
			String sql = "select * from t_conference_info where " + con;
			//System.out.println("sql = " + sql);
			listTemp = getJdbcTemplate().queryForList(sql);
			 Iterator _objIterator = listTemp.iterator();
				while (_objIterator.hasNext()) {
					TawConfInfo tawConfInfo = new TawConfInfo();
					Map _objMap = (Map) _objIterator.next();
					tawConfInfo.setConfNo(Integer.parseInt(_objMap.get("conf_no").toString()));
					tawConfInfo.setConfName(StaticMethod
							.nullObject2String(_objMap.get("conf_name")));
					tawConfInfo.setConfTrunkNo(Integer.parseInt(_objMap.get("conf_trunk_no").toString()));
					tawConfInfo.setConfOrganizer(StaticMethod
							.nullObject2String(_objMap.get("conf_organiger")));
					tawConfInfo.setConfBeginTime(StaticMethod
							.nullObject2String(_objMap.get("conf_begintime")));
					tawConfInfo.setConfEndTime(StaticMethod
							.nullObject2String(_objMap.get("conf_endtime")));
					switch (Integer.parseInt(_objMap.get("conf_state").toString())) {
					case 0:
						tawConfInfo.setConfState("未分配资源");
						break;
					case 1:
						tawConfInfo.setConfState("已分配资源");
						break;
					case 2:
						tawConfInfo.setConfState("已结束");
						break;
					default:
						break;
				}
				switch (Integer.parseInt(_objMap.get("conf_record").toString())) {
					case 0:
						tawConfInfo.setConfRecord("否");
						break;
					case 1:
						tawConfInfo.setConfRecord("是");
						break;
					default:
						break;
				}
				switch (Integer.parseInt(_objMap.get("is_callout").toString())) {
					case 0:
						tawConfInfo.setIsCallout("否");
						break;
					case 1:
						tawConfInfo.setIsCallout("是");
						break;
					default:
						break;
				}
				tawConfInfo.setConfRFile(StaticMethod
						.nullObject2String(_objMap.get("conf_r_file")));

				list.add(tawConfInfo);
					
					
					
				}
		return list;
	}
	
	
	public List listMemInfo( int confNo) {
		ArrayList list = new ArrayList();
		List listTemp = new ArrayList();
			String sql = "select * from t_conference_member_info where conf_no = " + confNo;
			//System.out.println("sql = " + sql);
			listTemp = getJdbcTemplate().queryForList(sql);
			 Iterator _objIterator = listTemp.iterator();
				while (_objIterator.hasNext()) {
					
					Map _objMap = (Map) _objIterator.next();
				TawConMem tawConMem = new TawConMem();
				tawConMem.setUserId(Integer.parseInt(_objMap.get("userid").toString()));
				tawConMem.setUserName(StaticMethod
						.nullObject2String(_objMap.get("conf_member_name")));
				tawConMem.setUserTel(StaticMethod
						.nullObject2String(_objMap.get("conf_member_phone")));

				tawConMem.setJoinTime(StaticMethod
						.nullObject2String(_objMap.get("conf_join_time")));
				tawConMem.setExitTime(StaticMethod
						.nullObject2String(_objMap.get("conf_exit_time")));

				switch (Integer.parseInt(_objMap.get("join_mode").toString())) {
				case 0:
					tawConMem.setStrJoinMode("加入");
					break;
				case 1:
					tawConMem.setStrJoinMode("监听");
					break;
				case 2:
					tawConMem.setStrJoinMode("卡拉OK");
					break;
				default:
					break;
				}

				list.add(tawConMem);
			}
			
		return list;
	}
}
