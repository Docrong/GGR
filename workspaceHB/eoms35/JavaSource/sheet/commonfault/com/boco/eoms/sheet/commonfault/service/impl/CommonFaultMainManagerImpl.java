// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultMainManagerImpl.java

package com.boco.eoms.sheet.commonfault.service.impl;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.*;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultJDBC;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultMainDAO;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultTaskDAO;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.qo.ICommonFaultQo;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;

import java.io.PrintStream;

import com.boco.eoms.sheet.base.util.Constants;

import java.util.List;
import java.util.Map;

public class CommonFaultMainManagerImpl extends MainService implements
        ICommonFaultMainManager {

    public CommonFaultMainManagerImpl() {
    }

    public List showInvokeRelationShipList(String mainId) throws SheetException {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        return iCommonFaultMainDAO.showInvokeRelationShipList(mainId);
    }

    public BaseLink getHasInvokeBaseLink(String mainId) throws SheetException {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        return iCommonFaultMainDAO.getHasInvokeBaseLink(mainId);
    }

    public TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(
            String flowTemplateName) throws SheetException {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        return iCommonFaultMainDAO
                .getTawSystemWorkflowByFlowTemplateName(flowTemplateName);
    }

    public BaseMain getMainByAlarmId(String alarmId) {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        return iCommonFaultMainDAO.getMainByAlarmId(alarmId);
    }

    public BaseMain loadSinglePO(String id) {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        return iCommonFaultMainDAO.loadSinglePO(id, getMainObject());
    }

    public BaseMain getMainBySheetId(String sheetId) {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        return iCommonFaultMainDAO.getMainBySheetId(sheetId, getMainObject());
    }

    public List getMainByLink(Map map) {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        return iCommonFaultMainDAO.getMainByLink(map);
    }

    public List getMainList(Map map) {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        return iCommonFaultMainDAO.getMainList(map);
    }

    public List getListUndoForCheck(Integer curPage, Integer pageSize)
            throws SheetException {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        List list = null;
        try {
            list = iCommonFaultMainDAO.getListUndoForCheck(curPage, pageSize,
                    getMainObject());
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return list;
    }

    public Integer getCountUndoForCheck() throws SheetException {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        Integer count = new Integer(0);
        try {
            count = iCommonFaultMainDAO.getCountUndoForCheck(getMainObject());
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return count;
    }

    public List getListDoneForCheck(Integer curPage, Integer pageSize)
            throws SheetException {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        List list = null;
        try {
            list = iCommonFaultMainDAO.getListDoneForCheck(curPage, pageSize,
                    getMainObject());
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return list;
    }

    public Integer getCountDoneForCheck() throws SheetException {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        Integer count = new Integer(0);
        try {
            count = iCommonFaultMainDAO.getCountDoneForCheck(getMainObject());
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return count;
    }

    public List getHoldedSheetListByTime(String startTime, String endTime) {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        return iCommonFaultMainDAO.getHoldedSheetListByTime(getMainObject(),
                startTime, endTime);
    }

    public List getQueryResult(String hsql[], Map actionForm, Integer curPage,
                               Integer pageSize, int aTotal[], String queryType)
            throws SheetException {
        ICommonFaultQo workSheetQO = (ICommonFaultQo) getWorkSheetQO();
        ICommonFaultMainDAO mainDAO = (ICommonFaultMainDAO) getMainDAO();
        String sql = null;
        if (hsql != null)
            sql = hsql[0];
        if (sql == null || sql.equals("")) {
            hsql[0] = workSheetQO.getClauseSql(actionForm);
            sql = hsql[0];
        }
        List result = mainDAO.getQuerySheetByCondition(sql, curPage, pageSize,
                aTotal, queryType);
        System.out.println(aTotal[0]);
        return result;
    }

    public List getQueryListBySql(String sql) {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        return iCommonFaultMainDAO.getMainListBySql(sql);
    }

    /**
     * 工单归档状态返回(张晓杰添加)
     */
    public boolean isHoldedBySheetId(String id, String sheetId) {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) this
                .getMainDAO();
        Integer integer = null;
        // 通过ID或sheetid得到Status；
        if (id != null) {
            integer = iCommonFaultMainDAO.getStatusById(id);
        } else if (sheetId != null) {
            integer = iCommonFaultMainDAO.getStatusBySheetId(sheetId);
        }

        // 根据Status判断是否已归档
        if (integer.equals(Constants.SHEET_HOLD)
                || integer.equals(new Integer(Constants.ACTION_FORCE_HOLD))
                || integer.equals(new Integer(-100))
                || integer.equals(new Integer(999))) {
            return true;
        }
        return false;
    }

    public CommonFaultMain getCommonFaultMainById(String id) {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) this
                .getMainDAO();
        return iCommonFaultMainDAO.getCommonFaultMainById(id);
    }

    public CommonFaultMain getCommonFaultMainBySheetId(String sheetId) {
        try {
            ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) this
                    .getMainDAO();
            List list = iCommonFaultMainDAO
                    .getCommonFaultMainBySheetId(sheetId);
            for (int i = 0; i < list.size(); i++) {
                CommonFaultMain commonFaultMain = (CommonFaultMain) list.get(i);
                if (commonFaultMain.getStatus().toString().equals("0")) {
                    return commonFaultMain;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public String updateSheetMainTitle(String mainId, String foreStr) {
        try {
            ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) this
                    .getMainDAO();
            CommonFaultMain commonFaultMain = iCommonFaultMainDAO
                    .getCommonFaultMainById(mainId);
            String title = commonFaultMain.getTitle();
            ICommonFaultTaskDAO commonFaultTaskDAOHibernate = (ICommonFaultTaskDAO) ApplicationContextHolder
                    .getInstance().getBean("iCommonFaultTaskDAO");
            String tem = foreStr + title;
            commonFaultTaskDAOHibernate.updateTitleByMainId(mainId, tem);
            return foreStr + title;
        } catch (Exception e) {
            return "";
        }
    }

    private ICommonFaultJDBC mainJDBC;

    public ICommonFaultJDBC getMainJDBC() {
        return mainJDBC;
    }

    public void setMainJDBC(ICommonFaultJDBC mainJDBC) {
        this.mainJDBC = mainJDBC;
    }

    public void updateMainBySql(String sql) {
        mainJDBC.updateSql(sql);
    }

    public Map getCheckList(String condition, Integer curPage, Integer pageSize) {
        ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
        String sql = "";
        if ("".equals(condition) || condition == null) {
            //	sql = " from CommonFaultMain main where main.mainCheckStatus='1'and main.status ='0' and main.mainAlarmSolveDate is null ";
            sql = " from CommonFaultMain main where main.mainCheckStatus='1'and main.status ='0' and main.deleted =0 and main.mainAlarmSolveDate is null AND main.sendTime>SYSDATE-30 ";
        } else {
            StringBuffer sqlCondition = new StringBuffer();
            //	sqlCondition.append("from CommonFaultMain  main where main.mainCheckStatus='1'and main.status ='0' and main.mainAlarmSolveDate is null ").append(condition);
            sqlCondition.append("from CommonFaultMain  main where main.mainCheckStatus='1'and main.status ='0' and main.deleted =0 and main.mainAlarmSolveDate is null AND main.sendTime>SYSDATE-30 ").append(condition);
            sql = sqlCondition.toString();
        }
        System.out.println("checkLIst--------sql---------" + sql);
        return iCommonFaultMainDAO.getMainListBySql(sql, curPage, pageSize);
    }

/*	public String getListByUserId(String userId) {
		// TODO 自动生成方法存根
		return null;
	}*/
	
/*	public String getListByUserId(String userId){
		String result="";	
		if(userId!=null && !"".equals(userId)){
	   ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
          List resultList = iCommonFaultMainDAO.getListByUserId(userId);
          if(resultList.size()==1){
        	  result ="true";
          }else{
        	  result ="false";
          }
		}
         return result;
 }*/

    public Map getQueryListByCondition(String sql, Integer pageIndex, Integer pageSize) {
        return this.getMainDAO().getMainListBySql(sql, pageIndex, pageSize);
    }

//	public String getSecondExcuteHumTaskById(String id) throws SheetException {
//		ICommonFaultMainDAO iCommonFaultMainDAO = (ICommonFaultMainDAO) getMainDAO();
//		return iCommonFaultMainDAO.getSecondExcuteHumTaskById(id);
//	}

}