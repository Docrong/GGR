package com.boco.eoms.duty.mgr.impl;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dict.util.Constants;
import com.boco.eoms.duty.model.Attemper;
import com.boco.eoms.duty.model.AttemperContrast;
import com.boco.eoms.duty.model.AttemperSub;
import com.boco.eoms.duty.mgr.AttemperContrastMgr;
import com.boco.eoms.duty.webapp.form.AttemperContrastForm;
import com.boco.eoms.duty.dao.AttemperContrastDao;
import com.boco.eoms.duty.util.CommonTools;

/**
 * <p>
 * Title:网调对比表
 * </p>
 * <p>
 * Description:网调对比表
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class AttemperContrastMgrImpl implements AttemperContrastMgr {
 
	private AttemperContrastDao  attemperContrastDao;
 	
	public AttemperContrastDao getAttemperContrastDao() {
		return this.attemperContrastDao;
	}
 	
	public void setAttemperContrastDao(AttemperContrastDao attemperContrastDao) {
		this.attemperContrastDao = attemperContrastDao;
	}
 	
    public List getAttemperContrasts() {
    	return attemperContrastDao.getAttemperContrasts();
    }
    
    public AttemperContrast getAttemperContrast(final String id) {
    	return attemperContrastDao.getAttemperContrast(id);
    }
    
    public void saveAttemperContrast(AttemperContrast attemperContrast) {
    	attemperContrastDao.saveAttemperContrast(attemperContrast);
    }
    
    public void removeAttemperContrast(final String id) {
    	attemperContrastDao.removeAttemperContrast(id);
    }
    
    public Map getAttemperContrasts(final Integer curPage, final Integer pageSize,
			final String whereStr) throws Exception{
    	
    	Map map =attemperContrastDao.getAttemperContrasts(curPage, pageSize, whereStr);
    	List list = (List)map.get("result");
    	List attemperContrasts = new ArrayList();
		
    	for(int i=0;i<list.size();i++) {
    		Object[] o = (Object[])list.get(i);

    		AttemperContrast attemperContrast = (AttemperContrast)org.apache.commons.beanutils.BeanUtils.cloneBean((AttemperContrast)o[0]);
    	    Attemper attemper = (Attemper)org.apache.commons.beanutils.BeanUtils.cloneBean((Attemper)o[1]);    	    
    	    AttemperSub attemperSub = (AttemperSub)org.apache.commons.beanutils.BeanUtils.cloneBean((AttemperSub)o[2]);
			
    	    attemperContrast.setCableClass(CommonTools.getTypeNameFromXML(CommonTools.parseTypeList(attemperContrast.getCableClass())));
    	    attemper.setAttemperSub(attemperSub);
    	    attemperContrast.setAttemper(attemper);
    	    attemperContrasts.add(attemperContrast);
     	}

    	map.put("result", attemperContrasts);
		return map;
	}
    
    /**
	 * 根据Form中数据，获取查询条件
	 * @param AttemperContrastForm
	 * @return 返回 condition,字符串
	 */
	public String getSearchCondition(AttemperContrastForm attemperContrastForm){
		//拼SQL条件字串
		String strQueryCondition = " ";
		
		if (attemperContrastForm.getDays() != 0) { // 显示最近几天的网调对比表信息
			attemperContrastForm.setFromBeginTime(CommonTools.getTimeString(-attemperContrastForm.getDays()));
			attemperContrastForm.setToBeginTime(StaticMethod.getCurrentDateTime());
		}
		
		if (attemperContrastForm.getFromBeginTime() != null && !attemperContrastForm.getFromBeginTime().equals("")) { // 开始时间查询条件
			strQueryCondition += " AND attemperContrast.beginTime >= '" + attemperContrastForm.getFromBeginTime()+ "'";
		}

		if (attemperContrastForm.getToBeginTime() != null && !attemperContrastForm.getToBeginTime().equals("")) { // 结束时间查询条件
			strQueryCondition += " AND attemperContrast.beginTime <= '" + attemperContrastForm.getToBeginTime()+ "'";
		}
		
		if (attemperContrastForm.getRemark() != null && !attemperContrastForm.getRemark().equals("")) { // 备注查询条件
		   strQueryCondition += " AND attemperContrast.remark like('%" + attemperContrastForm.getRemark() + "%')";
		}
		if (attemperContrastForm.getAdjustReason() != null && !attemperContrastForm.getAdjustReason().equals("")) { // 割接原因查询条件
			strQueryCondition += " AND attemperContrast.adjustReason like('%" + attemperContrastForm.getAdjustReason() + "%')";
		}
	    if (attemperContrastForm.getFinishResult() != null && !attemperContrastForm.getFinishResult().equals("")) { // 完成情况查询条件\
	    	strQueryCondition += " AND attemperContrast.finishResult like('%" + attemperContrastForm.getFinishResult() + "%')";
	    }
	    if (attemperContrastForm.getNetNameB() != null && !attemperContrastForm.getNetNameB().equals("")) { // B端网元名称查询条件
	    	strQueryCondition += " AND attemperContrast.netNameB like('%" + attemperContrastForm.getNetNameB() + "%')";
	    }

		if (attemperContrastForm.getNetNameA() != null && !attemperContrastForm.getNetNameA().equals("")) { // A端网元名称查询条件
		    strQueryCondition += " AND attemperContrast.netNameA like('%" + attemperContrastForm.getNetNameA() + "%')";
		}

		if (attemperContrastForm.getProtectCable() != null && !attemperContrastForm.getProtectCable().equals("")) { // 保护光缆查询条件
		    strQueryCondition += " AND attemperContrast.protectCable like('%" + attemperContrastForm.getProtectCable() + "%')";
		}
		if (attemperContrastForm.getMainCable() != null && !attemperContrastForm.getMainCable().equals("")) { // 主用光缆查询条件
			strQueryCondition += " AND attemperContrast.mainCable like('%" + attemperContrastForm.getMainCable() + "%')";
		}
		if (attemperContrastForm.getSubRing() != null && !attemperContrastForm.getSubRing().equals("")) { // 子环查询条件
		   strQueryCondition += " AND attemperContrast.subRing like('% " + attemperContrastForm.getSubRing() + "%') ";
		}
		if (attemperContrastForm.getAttemperId() != null && !attemperContrastForm.getAttemperId().equals("")) { // 网调ID号查询条件
			strQueryCondition += " AND attemperContrast.attemperId like('% " + attemperContrastForm.getAttemperId() + "%') ";
		}
		if (attemperContrastForm.getSubAttemperId() != null && !attemperContrastForm.getSubAttemperId().equals("")) { // 子过程ID号查询条件
			strQueryCondition += " AND attemperContrast.subAttemperId like('% " + attemperContrastForm.getSubAttemperId() + "%') ";
		}
		if (attemperContrastForm.getSubCompany()!=null&&!attemperContrastForm.getSubCompany().equals("-1")&&!attemperContrastForm.getSubCompany().equals("")) { // 责任分公司查询条件
			strQueryCondition += " AND attemperContrast.subCompany in (" + attemperContrastForm.getSubCompany() + ") ";
		}
		if (attemperContrastForm.getFriendCompany()!=null&&!attemperContrastForm.getFriendCompany().equals("-1")&&!attemperContrastForm.getFriendCompany().equals("")) { // 代维公司查询条件
			strQueryCondition += " AND attemperContrast.friendCompany in (" + attemperContrastForm.getFriendCompany() + ") ";
		}
		if (attemperContrastForm.getCableClass()!=null&&!attemperContrastForm.getCableClass().equals("-1")&&!attemperContrastForm.getFriendCompany().equals("")) { // 光缆级别查询条件
			strQueryCondition += " AND attemperContrast.cableClass like('%# " + attemperContrastForm.getSubAttemperId() + "#%') ";
		}
		if (attemperContrastForm.getIfNormalSwitch()!=null&&!attemperContrastForm.getIfNormalSwitch().equals("-1")&&!attemperContrastForm.getIfNormalSwitch().equals("")) {  // SDH是否正常倒换查询条件
			strQueryCondition += " AND attemperContrast.ifNormalSwitch = " + attemperContrastForm.getIfNormalSwitch() + " ";
		}
		if (attemperContrastForm.getIsAppEffect()!=null&&!attemperContrastForm.getIsAppEffect().equals("-1")&&!attemperContrastForm.getIsAppEffect().equals("")) {  // 是否影响业务查询条件
			strQueryCondition += " AND attemperContrast.isAppEffect = " + attemperContrastForm.getIsAppEffect() + " ";
		}
		if (attemperContrastForm.getDeleted()!=null&&!attemperContrastForm.getDeleted().equals("-1")&&!attemperContrastForm.getDeleted().equals("")) {  // 是否删除查询条件
			strQueryCondition += " AND attemperContrast.deleted = " + attemperContrastForm.getDeleted() + " ";
		}

		return strQueryCondition;
	}
}