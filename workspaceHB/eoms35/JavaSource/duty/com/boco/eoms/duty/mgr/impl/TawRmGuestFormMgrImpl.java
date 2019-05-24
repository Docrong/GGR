package com.boco.eoms.duty.mgr.impl;

import java.util.List;
import java.util.Map;
import com.boco.eoms.duty.dao.ITawRmGuestFormDAO;
import com.boco.eoms.duty.mgr.TawRmGuestFormMgr;
import com.boco.eoms.duty.model.TawRmGuestform;
import com.boco.eoms.duty.webapp.form.TawRmGuestFormForm;


/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 28, 2009
 * </p>
 * 
 * @Author panyunfu
 * @Version 3.5
 *
 */
public class TawRmGuestFormMgrImpl implements TawRmGuestFormMgr {

	private ITawRmGuestFormDAO tawRmGuestFormDAO;
	
	
	public ITawRmGuestFormDAO getTawRmGuestFormDAO() {
		return tawRmGuestFormDAO;
	}

	
	public void setTawRmGuestFormDAO(ITawRmGuestFormDAO tawRmGuestFormDAO) {
		this.tawRmGuestFormDAO = tawRmGuestFormDAO;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmGuestFormMgr#deleteById(java.lang.String)
	 */
	public void deleteById(String id) {
		tawRmGuestFormDAO.deleteById(id);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmGuestFormMgr#getTawRmGuestform(java.lang.String)
	 */
	public TawRmGuestform getTawRmGuestform(String id) {
		return tawRmGuestFormDAO.getTawRmGuestform(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmGuestFormMgr#getUnChecklist(int)
	 */
	public List getUnChecklist(int flag) {
		return tawRmGuestFormDAO.getUnChecklist(flag);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmGuestFormMgr#getUnChecklist(int)
	 */
	public List list(final String condtion) {
		return tawRmGuestFormDAO.list(condtion);
	}

	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmGuestFormMgr#save(com.boco.eoms.duty.model.TawRmGuestform)
	 */
	public void save(TawRmGuestform tawRmGuestform) {
		tawRmGuestFormDAO.save(tawRmGuestform);
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmGuestFormMgr#getTawRmGuestForm(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public Map getTawRmGuestForm(final Integer curPage, final Integer pageSize,
				final String whereStr){
		return tawRmGuestFormDAO.getTawRmGuestForm(curPage, pageSize, whereStr);
	}
		

	/*
	 * (non-Javadoc)
	 * @see com.boco.eoms.duty.mgr.TawRmGuestFormMgr#getCount(java.lang.String)
	 */
	public String getCount(final String conditionStr){
		return tawRmGuestFormDAO.getCount(conditionStr);
	}
	
	
	public String getSearchCondition(TawRmGuestFormForm form){
		//拼SQL条件字串
	    String strQueryCondition = " ";
	    if(form.getId()>0) { // 机房
	      strQueryCondition += " AND obj.id= " + form.getId();
	    }
	    
	    if (form.getCruser() != null && !form.getCruser().equals("")) { // 录入人
		     strQueryCondition += " AND obj.cruser like('%" + form.getCruser() + "%')";
		}
	    
	    if (form.getDeptId() != null && !form.getDeptId().equals("")) { // 录入人部门
		     strQueryCondition += " AND obj.deptId like('%" + form.getDeptId() + "%')";
		}
	    
	    if (form.getDeptName() != null && !form.getDeptName().equals("")) { // 录入人部门名称
		     strQueryCondition += " AND obj.deptName like('%" + form.getDeptName() + "%')";
		}
	    
	    if(form.getRoomId() !=0){
	    	strQueryCondition += " AND obj.roomId= " + form.getRoomId();
		}

	    if (form.getGuestname() != null && !form.getGuestname().equals("")) { // 访客姓名
	     strQueryCondition += " AND obj.guestname like('%" + form.getGuestname() + "%')";
	   }

	   if (form.getCompany() != null && !form.getCompany().equals("")) { // 访客单位
	     strQueryCondition += " AND obj.company like('%" + form.getCompany() + "%')";
	   }

	   if (form.getDutyman() != null && !form.getDutyman().equals("")) { // 登记人
	     strQueryCondition += " AND obj.dutyman like('%" + form.getDutyman() + "%')";
	   }

	   if (form.getDepartment() != null && !form.getDepartment().equals("")) { // 批准部门
	     strQueryCondition += " AND obj.dutyman like('%" + form.getDepartment() + "%')";
	   }

	   if (form.getSender() != null && !form.getSender().equals("")) { // 批准人
	     strQueryCondition += " AND obj.sender like('%" + form.getSender() + "%')";
	   }

	   if (form.getPurpose() != null && !form.getPurpose().equals("")) { // 工作内容
	     strQueryCondition += " AND obj.purpose like('%" + form.getPurpose() + "%')";
	   }

	   if (form.getConcerned() != null && !form.getConcerned().equals("")) { // 相关网元
	     strQueryCondition += " AND obj.concerned like('%" + form.getConcerned() + "%')";
	   }

	   if(form.getFlag()!=-1) { // 是否确认
	     strQueryCondition += " AND obj.flag = " + form.getFlag() ;
	   }

	   if (form.getFromStarttime() != null &&!form.getFromStarttime().equals("")) { // 开始时间,起点
	     strQueryCondition += " AND obj.starttime >= '" + form.getFromStarttime() +"'";
	   }

	   if (form.getToStarttime() != null &&!form.getToStarttime().equals("")) { // 开始时间,终点
	     strQueryCondition += " AND obj.starttime <= '" + form.getToStarttime() +"'";
	   }

	   if (form.getFromEndtime() != null &&!form.getFromEndtime().equals("")) { // 结束时间,起点
	     strQueryCondition += " AND obj.endtime >= '" + form.getFromEndtime() +"'";
	   }

	   if (form.getToEndtime() != null &&!form.getToEndtime().equals("")) { // 结束时间,终点
	     strQueryCondition += " AND obj.endtime <= '" + form.getToEndtime() +"'";
	   }
	    return strQueryCondition + " ";
	}
}
