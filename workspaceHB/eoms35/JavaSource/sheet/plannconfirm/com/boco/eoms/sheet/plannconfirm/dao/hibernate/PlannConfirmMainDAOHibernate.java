package com.boco.eoms.sheet.plannconfirm.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.plannconfirm.dao.IPlannConfirmMainDAO;

/**
 * <p>
 * Title:规划站址确认申请流程
 * </p>
 * <p>
 * Description:规划站址确认申请流程
 * </p>
 * <p>
 * Thu Jun 06 17:13:18 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class PlannConfirmMainDAOHibernate extends MainDAO implements IPlannConfirmMainDAO {


    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
     */
    public String getMainName() {
        return "PlannConfirmMain";
    }

    public List getNumber(final String sendTimeStartDate, final String sendTimeEndDate, final String queryType) throws HibernateException {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "";
                if ("territorialBranch".equals(queryType)) {
                    queryStr = "select t1.territorialBranch,decode(t1.count,'',0,t1.count),decode(t2.count,'',0,t2.count),decode(t3.count,'',0,t3.count),decode(t4.count,'',0,t4.count),decode(t1.count,'',0,t1.count)-decode(t2.count,'',0,t2.count) from " +
                            "(select m.territorialBranch territorialBranch,count(*) count from PlannConfirm_main m where m.sendtime between to_date('" + sendTimeStartDate + "','yyyy-mm-dd hh24:mi:ss') and to_date('" + sendTimeEndDate + "','yyyy-mm-dd hh24:mi:ss') group by m.territorialBranch) t1 " +
                            "full outer join " +
                            "(select m.territorialBranch territorialBranch,count(*) count from PlannConfirm_main m ,PlannConfirm_link l where m.sendtime between to_date('" + sendTimeStartDate + "','yyyy-mm-dd hh24:mi:ss') and to_date('" + sendTimeEndDate + "','yyyy-mm-dd hh24:mi:ss') and l.operatetype = '103' and m.id = l.mainid group by m.territorialBranch) t2 " +
                            "on t1.territorialBranch = t2.territorialBranch " +
                            "full outer join " +
                            "(select m.territorialBranch territorialBranch,count(*) count from PlannConfirm_main m ,PlannConfirm_link l where m.sendtime between to_date('" + sendTimeStartDate + "','yyyy-mm-dd hh24:mi:ss') and to_date('" + sendTimeEndDate + "','yyyy-mm-dd hh24:mi:ss') and (l.validationResult = '101200701' or l.validationResult = '101200702') and l.operatetype = '103' and m.id = l.mainid group by m.territorialBranch) t3 " +
                            "on t1.territorialBranch = t3.territorialBranch " +
                            "full outer join " +
                            "(select m.territorialBranch territorialBranch,count(*) count from PlannConfirm_main m ,PlannConfirm_link l where m.sendtime between to_date('" + sendTimeStartDate + "','yyyy-mm-dd hh24:mi:ss') and to_date('" + sendTimeEndDate + "','yyyy-mm-dd hh24:mi:ss') and l.validationResult = '101200703' and l.operatetype = '103' and m.id = l.mainid group by m.territorialBranch) t4 " +
                            "on t1.territorialBranch = t4.territorialBranch";
                } else {
                    queryStr = "select cast('全网' as varchar(4)) as territorialBranch, t1.count1,t2.count2,t3.count3,t4.count4,t1.count1-t2.count2 from " +
                            "(select count(*) count1 from PlannConfirm_main m where m.sendtime between to_date('" + sendTimeStartDate + "','yyyy-mm-dd hh24:mi:ss') and to_date('" + sendTimeEndDate + "','yyyy-mm-dd hh24:mi:ss')) t1," +
                            "(select count(*) count2 from PlannConfirm_main m ,PlannConfirm_link l where m.sendtime between to_date('" + sendTimeStartDate + "','yyyy-mm-dd hh24:mi:ss') and to_date('" + sendTimeEndDate + "','yyyy-mm-dd hh24:mi:ss') and l.operatetype = '103' and m.id = l.mainid) t2," +
                            "(select count(*) count3 from PlannConfirm_main m ,PlannConfirm_link l where m.sendtime between to_date('" + sendTimeStartDate + "','yyyy-mm-dd hh24:mi:ss') and to_date('" + sendTimeEndDate + "','yyyy-mm-dd hh24:mi:ss') and (l.validationResult = '101200701' or l.validationResult = '101200702') and l.operatetype = '103' and m.id = l.mainid) t3," +
                            "(select count(*) count4 from PlannConfirm_main m ,PlannConfirm_link l where m.sendtime between to_date('" + sendTimeStartDate + "','yyyy-mm-dd hh24:mi:ss') and to_date('" + sendTimeEndDate + "','yyyy-mm-dd hh24:mi:ss') and l.validationResult = '101200703' and l.operatetype = '103' and m.id = l.mainid) t4";
                    //queryStr = "select '' territorialBranch, t1.count1,t2.count2,t3.count3,t4.count4,t1.count1-t2.count2 from "+
//"(select count(*) count1 from PlannConfirm_main m where m.sendtime between to_date('2013-6-3 17:45:19','yyyy-mm-dd hh24:mi:ss') and to_date('2013-6-14 17:45:19','yyyy-mm-dd hh24:mi:ss')) t1,"+
//"(select count(*) count2 from PlannConfirm_main m ,PlannConfirm_link l where m.sendtime between to_date('2013-6-3 17:45:19','yyyy-mm-dd hh24:mi:ss') and to_date('2013-6-14 17:45:19','yyyy-mm-dd hh24:mi:ss') and l.operatetype = '103' and m.id = l.mainid) t2,"+
//"(select count(*) count3 from PlannConfirm_main m ,PlannConfirm_link l where m.sendtime between to_date('2013-6-3 17:45:19','yyyy-mm-dd hh24:mi:ss') and to_date('2013-6-14 17:45:19','yyyy-mm-dd hh24:mi:ss') and l.validationResult = '1012301' and l.operatetype = '103' and m.id = l.mainid) t3,"+
//"(select count(*) count4 from PlannConfirm_main m ,PlannConfirm_link l where m.sendtime between to_date('2013-6-3 17:45:19','yyyy-mm-dd hh24:mi:ss') and to_date('2013-6-14 17:45:19','yyyy-mm-dd hh24:mi:ss') and l.validationResult = '1012302' and l.operatetype = '103' and m.id = l.mainid) t4";
                }
                Query query = session.createSQLQuery(queryStr);
                ArrayList queryList = new ArrayList();
                String[] elementKey = new String[]{"territorialBranch", "amount", "checkAmount", "throughAmount", "rejectAmount", "notcheckAmount"};
                ArrayList taskList = new ArrayList();
                queryList = (ArrayList) query.list();
                System.out.println(queryList.size());
                for (int i = 0; i < queryList.size(); i++) {
                    Object[] tmpObjArr = (Object[]) queryList.get(i);
                    Map tmptaskMap = new HashMap();
                    for (int j = 0; j < elementKey.length; j++) {
                        tmptaskMap.put(elementKey[j], tmpObjArr[j]);
                    }
                    taskList.add(tmptaskMap);
                }
                return taskList;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public Map getDetail(final Integer curPage, final Integer pageSize, final String sendTimeStartDate, final String sendTimeEndDate) throws HibernateException {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "";
                String queryStrCount = "";
                queryStr = "select m.sheetId,m.title,m.status,m.sendUserId,m.sendDeptId,m.sendContact,to_char(m.sendTime,'YYYY-MM-DD hh24:mi:ss') as sendTime,m.networkTypeTwo,m.planningNumber,m.territorialBranch,m.stationSite,m.sceneType,m.longitude,m.latitude,m.buildingType,m.buildingNumber,m.antennaHeight,m.platformType,m.requirement,m.highRequirement,m.existenceBarrier,m.otherCircumstance,l.validationResult,l.percentAgreement,l.groupDiscussion,l.planningAzimuth,l.electronicAngle,l.mechanicalAngle,l.planningConfiguration,l.particularCase,l.checkPeople from " +
                        "PlannConfirm_main m left outer join PlannConfirm_link l on m.id = l.mainid and m.sendtime between to_date('" + sendTimeStartDate + "','yyyy-mm-dd hh24:mi:ss') and to_date('" + sendTimeEndDate + "','yyyy-mm-dd hh24:mi:ss') and l.operatetype = '103'";
                queryStrCount = "select count(*) from PlannConfirmMain m where m.sendTime between to_date('" + sendTimeStartDate + "','yyyy-mm-dd hh24:mi:ss') and to_date('" + sendTimeEndDate + "','yyyy-mm-dd hh24:mi:ss')";
                int total = ((Integer) session.createQuery(queryStrCount).iterate().next()).intValue();
                Query query = session.createSQLQuery(queryStr);
                ArrayList queryList = new ArrayList();
                String[] elementKey = new String[]{"sheetId", "title", "status", "sendUserId", "sendDeptId", "sendContact", "sendTime", "networkTypeTwo", "planningNumber", "territorialBranch", "stationSite", "sceneType", "longitude", "latitude", "buildingType", "buildingNumber", "antennaHeight", "platformType", "requirement", "highRequirement", "existenceBarrier", "otherCircumstance", "validationResult", "percentAgreement", "groupDiscussion", "planningAzimuth", "electronicAngle", "mechanicalAngle", "planningConfiguration", "particularCase", "checkPeople"};
                ArrayList taskList = new ArrayList();
                query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
                System.out.println(curPage.intValue());
                query.setMaxResults(pageSize.intValue());
                queryList = (ArrayList) query.list();
                System.out.println(queryList.size());
                for (int i = 0; i < queryList.size(); i++) {
                    Object[] tmpObjArr = (Object[]) queryList.get(i);
                    Map tmptaskMap = new HashMap();
                    tmptaskMap.put("number", String.valueOf(pageSize.intValue() * (curPage.intValue()) + i + 1));
                    for (int j = 0; j < elementKey.length; j++) {
                        tmptaskMap.put(elementKey[j], tmpObjArr[j]);
                    }
                    taskList.add(tmptaskMap);
                }
                HashMap map = new HashMap();
                map.put("total", new Integer(total));
                map.put("result", taskList);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

}