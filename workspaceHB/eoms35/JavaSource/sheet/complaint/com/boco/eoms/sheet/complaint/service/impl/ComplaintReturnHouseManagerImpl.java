/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.complaint.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.complaint.dao.IComplaintMainDAO;
import com.boco.eoms.sheet.complaint.dao.IComplaintReturnHouseDAO;
import com.boco.eoms.sheet.complaint.model.ComplaintReturnHouse;
import com.boco.eoms.sheet.complaint.service.IComplaintMainManager;
import com.boco.eoms.sheet.complaint.service.IComplaintReturnHouseManager;
import com.boco.eoms.sheet.nbproducts.model.NBProducts;

/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComplaintReturnHouseManagerImpl extends BaseManager implements
        IComplaintReturnHouseManager {
    public IComplaintReturnHouseDAO complaintReturnHouseDao;

    public IComplaintReturnHouseDAO getComplaintReturnHouseDao() {
        return complaintReturnHouseDao;
    }

    public void setComplaintReturnHouseDao(
            IComplaintReturnHouseDAO complaintReturnHouseDao) {
        this.complaintReturnHouseDao = complaintReturnHouseDao;
    }

    public void saveReturnHouse(ComplaintReturnHouse complaintreturnhouse) {
        // TODO Auto-generated method stub
        this.complaintReturnHouseDao.saveReturnHorse(complaintreturnhouse);

    }

    public Map getReturnHouse(String userid, final String queryStr,
                              final Integer curPage, final Integer pageSize) {

        return this.complaintReturnHouseDao.getReturnHorse(userid, queryStr, curPage, pageSize);
    }

    public ComplaintReturnHouse getReturnHouseByid(String id) {
        return complaintReturnHouseDao.getReturnHouseByid(id);

    }


    //	private int applyTime;
//	
//	private String deferSwitch;
//	
//	
//	
//	
//	
//	public String getDeferSwitch() {
//		return deferSwitch;
//	}
//	
//	public void setDeferSwitch(String deferSwitch) {
//		this.deferSwitch = deferSwitch;
//	}
//	
//	public int getApplyTime() {
//		return applyTime;
//	}
//	public void setApplyTime(int applyTime) {
//		this.applyTime = applyTime;
//	}
    /**
     * 通过告警号获取工单
     * @param alarmId 告警号
     * @return
     * @throws HibernateException
     */
//	public BaseMain getMainByAlarmId(String alarmId){
//		IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO)this.getMainDAO();
//		return iComplaintMainDAO.getMainByAlarmId(alarmId, this.getMainObject());
//	}
//	public BaseMain loadSinglePO(String id) {
//		IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO)this.getMainDAO();
//		return iComplaintMainDAO.loadSinglePO(id, this.getMainObject());
//    }
//	public List getMainByLink(Map map, Object linkObject){
//		IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO)this.getMainDAO();
//		return iComplaintMainDAO.getMainByLink(map, this.getMainObject(), linkObject);
//	}
//	public List getMainList(Map map){
//		IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO)this.getMainDAO();
//		return iComplaintMainDAO.getMainList(map, this.getMainObject());
//	}
//
//	public List getListUndoForCheck(Integer curPage, Integer pageSize)
//			throws SheetException {
//		IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO)this.getMainDAO();
//		List list = null;
//		try {
//			list = iComplaintMainDAO.getListUndoForCheck(curPage, pageSize, this.getMainObject());
//		} catch (Exception e) {
//			throw new SheetException(e);
//		}
//		return list;
//	}
//
//
//	public Integer getCountUndoForCheck() throws SheetException {
//		IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO)this.getMainDAO();		
//		Integer count = new Integer(0);
//		try {
//			count = iComplaintMainDAO.getCountUndoForCheck(this.getMainObject());
//		} catch (Exception e) {
//			throw new SheetException(e);
//		}
//		return count;
//	}	
//
//	public List getListDoneForCheck(Integer curPage, Integer pageSize)throws SheetException {
//		IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO)this.getMainDAO();
//		List list = null;
//		try {
//			list = iComplaintMainDAO.getListDoneForCheck(curPage, pageSize, this.getMainObject());
//		} catch (Exception e) {
//			throw new SheetException(e);
//		}
//		return list;
//	}
//
//
//	public Integer getCountDoneForCheck() throws SheetException {
//		IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO)this.getMainDAO();		
//		Integer count = new Integer(0);
//		try {
//			count = iComplaintMainDAO.getCountDoneForCheck(this.getMainObject());
//		} catch (Exception e) {
//			throw new SheetException(e);
//		}
//		return count;
//	}	
//	/**
//     * 获取某段时间内需要上报集团的工单
//     */
//    public List getHoldedSheetListByTime(String startTime,String endTime){
//    	IComplaintMainDAO iComplaintMainDAO = (IComplaintMainDAO)this.getMainDAO();		
//    	return iComplaintMainDAO.getHoldedSheetListByTime(this.getMainObject(), startTime, endTime);
//    }


}
