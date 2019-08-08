/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultAutoDAO;
import com.boco.eoms.sheet.commonfault.model.CommonFaultAuto;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultAutoManager;

/**
 * @author wangjianhua
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommonFaultAutoManagerImpl implements ICommonFaultAutoManager {

    private ICommonFaultAutoDAO autoDAO;


    public ICommonFaultAutoDAO getAutoDAO() {
        return autoDAO;
    }

    public void setAutoDAO(ICommonFaultAutoDAO autoDAO) {
        this.autoDAO = autoDAO;
    }

    public Object getObject(Class clazz, Serializable id) throws HibernateException {
        return autoDAO.getObject(clazz, id);
    }

    public List getObjectsByCondtion(Integer curPage, Integer pageSize, int[] aTotal, Map condition, String queryNumber) throws HibernateException {

        String hql = " from CommonFaultAuto ";
        String countHql = "select count(*) from CommonFaultAuto ";
        String where = (String) condition.get("where");
        String order = (condition.get("order") == null ? "" : (String) condition.get("order"));

        hql = hql + where + order;
        countHql = countHql + where;
        return autoDAO.getObjectsByCondtion(curPage, pageSize, aTotal, hql, countHql, queryNumber);
    }

    public void removeObject(Class clazz, Serializable id) throws HibernateException {
        autoDAO.removeObject(clazz, id);

    }

    public void saveObject(Object o) throws HibernateException {
        autoDAO.saveObject(o);

    }

    public CommonFaultAuto getMaxFilter(Map filterMap, String ruleType, String autoType) throws HibernateException {
        BocoLog.debug(this.getClass(), "进入规则匹配，规则类型为：" + ruleType + ",自动匹配类型：" + autoType);
        CommonFaultAuto commonfaultAuto = new CommonFaultAuto();

        String NetSortOne = "";
        Object mainNetSortOneObj = filterMap.get("mainNetSortOne");
        if (mainNetSortOneObj instanceof String[]) {
            NetSortOne = ((String[]) mainNetSortOneObj)[0];
        } else {
            NetSortOne = StaticMethod.nullObject2String(mainNetSortOneObj);
        }

        String NetSortTwo = "";
        Object mainNetSortTwoObj = filterMap.get("mainNetSortTwo");
        if (mainNetSortTwoObj instanceof String[]) {
            NetSortTwo = ((String[]) mainNetSortTwoObj)[0];
        } else {
            NetSortTwo = StaticMethod.nullObject2String(mainNetSortTwoObj);
        }

        String NetSortThree = "";
        Object mainNetSortThreeObj = filterMap.get("mainNetSortThree");
        if (mainNetSortThreeObj instanceof String[]) {
            NetSortThree = ((String[]) mainNetSortThreeObj)[0];
        } else {
            NetSortThree = StaticMethod.nullObject2String(mainNetSortThreeObj);
        }

        //故障地点
        Object toDeptIdObj = filterMap.get("toDeptId");
        String faultSite = "";
        if (toDeptIdObj instanceof String[]) {
            faultSite = ((String[]) toDeptIdObj)[0];
        } else {
            faultSite = StaticMethod.nullObject2String(toDeptIdObj);
        }
        ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
        faultSite = service.id2Name(faultSite, "tawSystemAreaDao");


        String hql = " from CommonFaultAuto  where complaintType1 = '" + NetSortOne + "' and complaintType2 = '" + NetSortTwo + "'"
                + " and complaintType3 ='" + NetSortThree + "'" + " and faultSite like '%" + faultSite + "%'"
                + " and ruleType = '" + ruleType + "'"
                + " and (sheetType = '" + autoType + "' or sheetType = 'all')"
                + " and colseSwitch = 'yes'";

        if (ruleType.equals("autoHold")) {
            String title = StaticMethod.nullObject2String(filterMap.get("title"));
            hql = hql + " and (title like '%" + title + "%' or title is null or title = '')";
        }

        Object obj = autoDAO.getMaxFilter(hql);
        if (obj != null) {
            commonfaultAuto = (CommonFaultAuto) obj;
        }

        return commonfaultAuto;
    }

    public List getSteps() throws HibernateException {
        String hql = "select distinct commonFaultDesc from CommonFaultAuto where sheetType='auto' and colseSwitch = 'yes'";
        return autoDAO.getSteps(hql);

    }

    public List getStepsbycondition(String remark1, String commonFaultDesc) throws HibernateException {
        String hql = "select  remark1,commonFaultDesc,remark2  from CommonFaultAuto " +
                "where sheetType='auto' and colseSwitch = 'yes' and remark1='" + remark1 + "'and commonFaultDesc='" + commonFaultDesc + "'";
        return autoDAO.getSteps(hql);
    }

}
