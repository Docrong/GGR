package com.boco.eoms.workplan.mgr.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.workplan.dao.ITawwpMonthPlanDao;
import com.boco.eoms.workplan.dao.ITawwpYearCheckDao;
import com.boco.eoms.workplan.dao.ITawwpYearPlanDao;
import com.boco.eoms.workplan.mgr.ITawwpMonthMgr;
import com.boco.eoms.workplan.mgr.ITawwpWorkplanReportMgr;
import com.boco.eoms.workplan.mgr.ITawwpYearPlanMgr;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.model.TawwpYearPlan;


public class TawwpWorkplanReportMgrImpl implements ITawwpWorkplanReportMgr{
	
	private ITawwpYearPlanDao tawwpYearPlanDao;
	private ITawwpMonthPlanDao tawwpMonthPlanDao;	
	private ITawwpYearPlanMgr tawwpYearPlanMgr;
	private ITawwpMonthMgr tawwpMonthMgr;
	
	public String reportAllPlan(int type,String time,String yearFlag) {
		    String errorInf = null;
		    List list = null;
		    Map hashMap=new HashMap();
		    list = new ArrayList();
		    if (type == 1) {
		      hashMap.put("yearFlag",time);
		      hashMap.put("deleted","0");
		      try {
		        list = tawwpYearPlanDao.searchYearPlan(hashMap);
		      }
		      catch (Exception ex) {
		        ex.printStackTrace();
		        errorInf = ex.toString();
		      }
		      for (int i = 0; i < list.size(); i++) {
		        TawwpYearPlan tawwpYearPlan = (TawwpYearPlan) list.get(i);
		        if (null != tawwpYearPlan.getUnicomType() &&
		            !tawwpYearPlan.getUnicomType().equals("") &&
		            tawwpYearPlan.getState().equals("1") &&
		            (tawwpYearPlan.getReportFlag() == null ||
		             tawwpYearPlan.getReportFlag().equals("2"))) {
		          try {
		            tawwpYearPlanMgr.crYearReport(tawwpYearPlan.getId());
		          }
		          catch (Exception ex2) {
		        	  ex2.printStackTrace();
		          }
		        }
		      }
		    }
		    if (type == 2) {
		      try {
		        hashMap.put("yearFlag",yearFlag);
		        hashMap.put("monthFlag",time);
		        hashMap.put("deleted","0");
		        list = tawwpMonthPlanDao.searchMonthPlan(hashMap);
		      }
		      catch (Exception ex1) {
		        ex1.printStackTrace();
		        errorInf = ex1.toString();
		      }

		      for (int i = 0; i < list.size(); i++) {
		        TawwpMonthPlan tawwpMonthPlan = (TawwpMonthPlan) list.get(i);
		        if (null != tawwpMonthPlan.getUnicomType() &&
		            !tawwpMonthPlan.getUnicomType().equals("") &&
		            tawwpMonthPlan.getConstituteState().equals("1") &&
		            (tawwpMonthPlan.getReportFlag() == null ||
		             !tawwpMonthPlan.getReportFlag().equals("1"))) {
		          try {
		            tawwpMonthMgr.crMonthReport(tawwpMonthPlan.getId());
		          }
		          catch (Exception ex3) {
		          }
		        }
		      }
		    }
		    return errorInf;

		  }
	
	
	  public ITawwpMonthMgr getTawwpMonthMgr() {
			return tawwpMonthMgr;
		}


		public void setTawwpMonthMgr(ITawwpMonthMgr tawwpMonthMgr) {
			this.tawwpMonthMgr = tawwpMonthMgr;
		}


		public ITawwpMonthPlanDao getTawwpMonthPlanDao() {
			return tawwpMonthPlanDao;
		}


		public void setTawwpMonthPlanDao(ITawwpMonthPlanDao tawwpMonthPlanDao) {
			this.tawwpMonthPlanDao = tawwpMonthPlanDao;
		}


		public ITawwpYearPlanDao getTawwpYearPlanDao() {
			return tawwpYearPlanDao;
		}


		public void setTawwpYearPlanDao(ITawwpYearPlanDao tawwpYearPlanDao) {
			this.tawwpYearPlanDao = tawwpYearPlanDao;
		}


		public ITawwpYearPlanMgr getTawwpYearPlanMgr() {
			return tawwpYearPlanMgr;
		}


		public void setTawwpYearPlanMgr(ITawwpYearPlanMgr tawwpYearPlanMgr) {
			this.tawwpYearPlanMgr = tawwpYearPlanMgr;
		}

}
