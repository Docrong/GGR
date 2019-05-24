package com.boco.eoms.duty.mgr.impl;

import java.text.*;
import java.util.*;

import com.boco.eoms.duty.model.AttemperSub;
import com.boco.eoms.duty.model.Attemper;
import com.boco.eoms.duty.mgr.AttemperMgr;
import com.boco.eoms.duty.mgr.AttemperSubMgr;
import com.boco.eoms.duty.webapp.form.AttemperForm;
import com.boco.eoms.duty.dao.AttemperDao;
import com.boco.eoms.duty.dao.AttemperSubDao;
import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.duty.util.CommonTools;

/**
 * <p>
 * Title:网调信息
 * </p>
 * <p>
 * Description:网调信息管理
 * </p>
 * <p>
 * Thu Apr 02 14:11:04 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class AttemperMgrImpl implements AttemperMgr {
 
	private AttemperDao  attemperDao;
	private AttemperSubDao attemperSubDao;
 	
	public AttemperDao getAttemperDao() {
		return this.attemperDao;
	}
 	
	public void setAttemperDao(AttemperDao attemperDao) {
		this.attemperDao = attemperDao;
	}
 	
    public List getAttempers() {
    	return attemperDao.getAttempers();
    }
    
    public Attemper getAttemper(final String id) {
    	Attemper attemper = attemperDao.getAttemper(id);
    	attemper.setNetDeptName(CommonTools.getTypeName(CommonTools.parseTypeList(attemper.getNetDeptList())));
		attemper.setSubNum(Integer.parseInt(attemperSubDao.getNum(" " +
				"AND attemperSub.attemperId='" + attemper.getId()+"'"
				+ " AND attemperSub.status in (0,1,2) ")));
		attemper.setIfSubFinish(Integer.parseInt(attemperSubDao.getNum(
				" AND attemperSub.attemperId='" + attemper.getId()+"'"
				+ " AND attemperSub.status in (0,1) ")));
    	return attemper;
    }
    
    public void saveAttemper(Attemper attemper) {
    	attemperDao.saveAttemper(attemper);
    }
    
    public void removeAttemper(final String id) {
    	attemperDao.removeAttemper(id);
    }
    
    public Map getAttempers(final Integer curPage, final Integer pageSize,
			final String whereStr) {
    	Map attemperMap = attemperDao.getAttempers(curPage, pageSize, whereStr);
    	Attemper attemper = null;
    	List list = (List)attemperMap.get("result");
    	List attemperList = new ArrayList();
    	for(int i=0;i<list.size();i++) {
    		attemper = (Attemper)list.get(i);
    		attemper.setNetDeptName(CommonTools.getTypeName(CommonTools.parseTypeList(attemper.getNetDeptList())));
    		attemper.setSubNum(Integer.parseInt(attemperSubDao.getNum(" " +
    				"AND attemperSub.attemperId='" + attemper.getId()+"'"
    				+ " AND attemperSub.status in (0,1,2) ")));
    		attemper.setIfSubFinish(Integer.parseInt(attemperSubDao.getNum(
    				" AND attemperSub.attemperId='" + attemper.getId()+"'"
    				+ " AND attemperSub.status in (0,1) ")));
    		attemperList.add(attemper);
    	}
    	attemperMap.put("result", attemperList);
		return attemperMap;
	}
    
    /**
     * 获取网调和子过程信息
     */
    public Map getAttemperAndSubs(final Integer curPage, final Integer pageSize,
			final String whereStr) throws Exception{
    	Map map = attemperDao.getAttemperAndSubs(curPage, pageSize, whereStr);
    	List list = (List)map.get("result");
    	List attempers = new ArrayList();

		
    	for(int i=0;i<list.size();i++) {
    		Object[] o = (Object[])list.get(i);

    	    Attemper attemper = (Attemper)org.apache.commons.beanutils.BeanUtils.cloneBean((Attemper)o[0]);
    	    
    	    AttemperSub attemperSub = (AttemperSub)org.apache.commons.beanutils.BeanUtils.cloneBean((AttemperSub)o[1]);

    		// 9 为传输专业，状态为1表示未结束,ifContrastReport为0表示还没生成对比表
    		if(attemper.getSpeciality().equals("9")&&attemperSub.getStatus().equals("1")
    				&&StaticMethod.nullObject2String(attemperSub.getIfContrastReport()).equals("")){
    			// 4为本地网,5为时钟、时间同步网
    			if(!attemper.getSubSpeciality().equals("4")&&!attemper.getSubSpeciality().equals("5")) {
    				attemper.setIfContrast(true);
    			}
    		}
			
    		attemperSub.setPersistTimes(Integer.toString(CommonTools.getResumeTimeSlot(attemperSub.getIntendBeginTime(),attemperSub.getIntendEndTime())));
    		attemper.setNetDeptName(CommonTools.getTypeName(CommonTools.parseTypeList(attemper.getNetDeptList())));
			attemper.setAttemperSub(attemperSub);
			
    		attempers.add(attemper);
     	}

    	map.put("result", attempers);
		return map;
	}
    
    /**
	 * 获取网调信息编号
	 * @return String
	 */
	public String getSheetId(){
		String sheetId = "";
		String regionId = StaticMethod.getNodeName("STRREGIONCODE");
		int sheetType = 25;
		int xyz = 0;
		try {
			String strYYMMDD = StaticMethod.getYYMMDD();
			sheetId = strYYMMDD;

			xyz = StaticMethod.nullObject2int(attemperDao.getSheetId(sheetId)) + 1;

			String strXYZ = String.valueOf(100000 + xyz);
			sheetId = regionId + "-" + sheetId + "-" + strXYZ.substring(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheetId;
	}
	
	
	/**
	 * 根据Form中数据，获取查询条件
	 * @param AttemperForm
	 * @return 返回 condition,字符串
	 */
	public String getSearchCondition(AttemperForm attemperForm){
		//拼SQL条件字串
		String strQueryCondition = " ";
		if (attemperForm.getDays() != 0) { // 显示最近几天的网调信息
			attemperForm.setFromBeginTime(CommonTools.getTimeString(-attemperForm.getDays()));
			attemperForm.setToBeginTime(StaticMethod.getCurrentDateTime());
		}
		
		if (attemperForm.getFromBeginTime() != null && !attemperForm.getFromBeginTime().equals("")) { // 开始时间查询条件
			strQueryCondition += " AND attemper.beginTime >= '" + attemperForm.getFromBeginTime()+ "'";
		}

		if (attemperForm.getToBeginTime() != null && !attemperForm.getToBeginTime().equals("")) { // 结束时间查询条件
			strQueryCondition += " AND attemper.beginTime <= '" + attemperForm.getToBeginTime()+ "'";
		}
		
		if (attemperForm.getCruser() != null && !attemperForm.getCruser().equals("")) { // 记录人查询条件
			strQueryCondition += " AND attemper.cruser like('%" + attemperForm.getCruser()+ "%')";
		}

		if (attemperForm.getNetNames() != null && !attemperForm.getNetNames().equals("")) { // 相关网元查询条件
			strQueryCondition += " AND attemper.netNames like('%" + attemperForm.getNetNames()+ "%')";
		}

		if (attemperForm.getRecordUser() != null && !attemperForm.getRecordUser().equals("")) { // 记录人查询条件
			strQueryCondition += " AND attemper.recordUser like('%"+ attemperForm.getRecordUser() + "%')";
		}
		
		if (attemperForm.getStatus()!=null&&!attemperForm.getStatus().equals("-1")&&!attemperForm.getStatus().equals("")) { // 状态查询条件
			strQueryCondition += " AND attemper.status in (" + attemperForm.getStatus() + ") ";
		}

		if (attemperForm.getSpeciality()!=null&&!attemperForm.getSpeciality().equals("-1")&&!attemperForm.getSpeciality().equals("")) { // 专业名称查询条件
			strQueryCondition += " AND attemper.speciality =" + attemperForm.getSpeciality();
		}

		if (attemperForm.getNetDeptList()!=null&&!attemperForm.getNetDeptList().equals("-1")&&!attemperForm.getNetDeptList().equals("")) { // 设备所属部门查询条件
			strQueryCondition += " AND attemper.netDeptList like '%#"+ attemperForm.getNetDeptList() + "#%'";
		}

		if (attemperForm.getRecordDept()!=null&&!attemperForm.getRecordDept().equals("-1")&&!attemperForm.getRecordDept().equals("")) { // 发起部门查询条件
			strQueryCondition += " AND attemper.recordDept =" + attemperForm.getRecordDept();
		}

		if (attemperForm.getFromInsertTime() != null &&!attemperForm.getFromInsertTime().equals("")) { // 创建时间查询条件
			strQueryCondition += " AND attemper.crtime >= '"+ attemperForm.getFromInsertTime() + "'";
		}

		if (attemperForm.getToInsertTime() != null && !attemperForm.getToInsertTime().equals("")) { // 创建时间查询条件
			strQueryCondition += " AND attemper.crtime <= '" + attemperForm.getToInsertTime()+ "'";
		}
		
		if (attemperForm.getSheetId() != null && !attemperForm.getSheetId().equals("")) { // 网调编号查询条件
			strQueryCondition += " AND attemper.sheetId like('%" + attemperForm.getSheetId()+ "%')";
		}

		if (attemperForm.getPlanSheetId() != null && !attemperForm.getPlanSheetId().equals("")) { // EOMS工单号查询条件
			strQueryCondition += " AND attemper.planSheet_id like('%"+ attemperForm.getPlanSheetId() + "%')";
		}

		if (attemperForm.getTitle() != null && !attemperForm.getTitle().equals("")) { // EOMS工单号查询条件
			strQueryCondition += " AND attemper.title like('%" + attemperForm.getTitle()+ "%')";
		}

		if (attemperForm.getSubContent() != null && !attemperForm.getSubContent().equals("")) { // 子过程内容查询条件
			strQueryCondition += " AND attemperSub.content like('%"+ attemperForm.getSubContent() + "%')";
		}

		return strQueryCondition;
	}

	public AttemperSubDao getAttemperSubDao() {
		return attemperSubDao;
	}

	public void setAttemperSubDao(AttemperSubDao attemperSubDao) {
		this.attemperSubDao = attemperSubDao;
	}
	
}