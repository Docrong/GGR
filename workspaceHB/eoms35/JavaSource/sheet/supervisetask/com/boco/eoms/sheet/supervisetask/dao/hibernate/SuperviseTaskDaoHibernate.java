package com.boco.eoms.sheet.supervisetask.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.commontask.model.CommonTaskMain;
import com.boco.eoms.sheet.listedregulation.model.ListedRegulationMain;
import com.boco.eoms.sheet.listedregulation.model.ListedRegulationTask;
import com.boco.eoms.sheet.supervisetask.dao.SuperviseTaskDao;
import com.boco.eoms.sheet.supervisetask.model.SuperviseTaskMainDuty;
import com.boco.eoms.sheet.supervisetask.model.SuperviseTaskRecord;
import com.boco.eoms.sheet.supervisetask.model.SuperviseTaskRule;

public class SuperviseTaskDaoHibernate extends MainDAO implements SuperviseTaskDao {

    public Map supervisetaskRuleList(final Integer curPage, final Integer pageSize, final Map maptj) {
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                String queryStr = " from SuperviseTaskRule t where t.deleted='0' ";
                String whereStr = "";

                String major = (String) maptj.get("major");
                String city = (String) maptj.get("city");
                String country = (String) maptj.get("country");
                String mainNetSortOne = (String) maptj.get("mainNetSortOne");
                String mainNetSortTwo = (String) maptj.get("mainNetSortTwo");
                String mainNetSortThree = (String) maptj.get("mainNetSortThree");
                String listedRegulationType = (String) maptj.get("listedRegulationType");
                String listedRegulationCycle = (String) maptj.get("listedRegulationCycle");

                if (major != null && !major.equals("")) {
                    whereStr += " and t.major='" + major + "' ";
                }
                if (city != null && !city.equals("")) {
                    whereStr += " and t.city='" + city + "' ";
                }
                if (country != null && !country.equals("")) {
                    whereStr += " and t.country like '%" + country + "%' ";
                }
                if (mainNetSortOne != null && !mainNetSortOne.equals("")) {
                    whereStr += " and t.mainNetSortOne='" + mainNetSortOne + "' ";
                }
                if (mainNetSortTwo != null && !mainNetSortTwo.equals("")) {
                    whereStr += " and t.mainNetSortTwo='" + mainNetSortTwo + "' ";
                }
                if (mainNetSortThree != null && !mainNetSortThree.equals("")) {
                    whereStr += " and t.mainNetSortThree='" + mainNetSortThree + "' ";
                }
                if (listedRegulationType != null && !listedRegulationType.equals("")) {
                    whereStr += " and t.listedRegulationType like '%" + listedRegulationType + "%' ";
                }
                if (listedRegulationCycle != null && !listedRegulationCycle.equals("")) {
                    whereStr += " and t.listedRegulationCycle like '%" + listedRegulationCycle + "%' ";
                }

                String hql = queryStr + whereStr;
                Query query = session.createQuery(hql.toString());
                //设置归档标记
//				query.setInteger("status", Constants.SHEET_HOLD.intValue());
//				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);

                //分页查询条
                if (pageSize.intValue() != -1) {
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));

                    query.setMaxResults(pageSize.intValue());
                }
                Query query2 = session.createQuery(hql.toString());
                int total = query2.list().size();
                Map map = new HashMap();
                map.put("result", query.list());
                map.put("total", new Integer(total));

                return map;
            }

        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public void savesupervisetaskRule(SuperviseTaskRule t) {
        if ((t.getId() == null) || (t.getId().equals(""))) {
            getHibernateTemplate().save(t);
        } else
            getHibernateTemplate().saveOrUpdate(t);
    }

    public void savesupervisetaskRecord(SuperviseTaskRecord t) {
        if ((t.getId() == null) || (t.getId().equals(""))) {
            getHibernateTemplate().save(t);
        } else
            getHibernateTemplate().saveOrUpdate(t);
    }


    public Map supervisetaskRecordList(final Integer curPage, final Integer pageSize, final Map maptj) {
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                String queryStr = " from SuperviseTaskRecord t where t.deleted='0' ";
                String whereStr = "";

                String startTime = (String) maptj.get("startTime");
                String endTime = (String) maptj.get("endTime");
                String major = (String) maptj.get("major");
                String mainNetSortOne = (String) maptj.get("mainNetSortOne");
                String mainNetSortTwo = (String) maptj.get("mainNetSortTwo");
                String mainNetSortThree = (String) maptj.get("mainNetSortThree");
                String city = (String) maptj.get("city");
                String listedRegulationType = (String) maptj.get("listedRegulationType");
                String listedRegulationCycle = (String) maptj.get("listedRegulationCycle");
                String phone = (String) maptj.get("phone");


                if (startTime != null && !startTime.equals("")) {
//					whereStr+="and to_date(t.starttime,'yyyy-MM-dd hh:mm:ss')>to_date(startTime,'yyyy-MM-dd hh:mm:ss')";
                }
                if (endTime != null && !endTime.equals("")) {
//					whereStr+="and to_date(t.endtime,'yyyy-MM-dd hh:mm:ss')<to_date(endTime,'yyyy-MM-dd hh:mm:ss')";
                }

                if (major != null && !major.equals("")) {
                    whereStr += " and t.major='" + major + "' ";
                }
                if (city != null && !city.equals("")) {
                    whereStr += " and t.city='" + city + "' ";
                }
                if (mainNetSortOne != null && !mainNetSortOne.equals("")) {
                    whereStr += " and t.mainNetSortOne='" + mainNetSortOne + "' ";
                }
                if (mainNetSortTwo != null && !mainNetSortTwo.equals("")) {
                    whereStr += " and t.mainNetSortTwo='" + mainNetSortTwo + "' ";
                }
                if (mainNetSortThree != null && !mainNetSortThree.equals("")) {
                    whereStr += " and t.mainNetSortThree='" + mainNetSortThree + "' ";
                }
                if (listedRegulationType != null && !listedRegulationType.equals("")) {
                    whereStr += " and t.listedRegulationType='" + listedRegulationType + "' ";
                }
                if (listedRegulationCycle != null && !listedRegulationCycle.equals("")) {
                    whereStr += " and t.listedRegulationCycle='" + listedRegulationCycle + "' ";
                }
                if (phone != null && !phone.equals("")) {
                    whereStr += " and (instr(t.noticeUserphone1,'" + phone + "')>0 or instr(t.noticeUserphone2,'" + phone + "')>0 or instr(t.noticeUserphone3,'" + phone + "')>0 or instr(t.noticeUserphone4,'" + phone + "')>0)";
                }

                String hql = queryStr + whereStr;
                Query query = session.createQuery(hql.toString());
                //设置归档标记
//				query.setInteger("status", Constants.SHEET_HOLD.intValue());
//				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);

                //分页查询条
                if (pageSize.intValue() != -1) {
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));

                    query.setMaxResults(pageSize.intValue());
                }
                Query query2 = session.createQuery(hql.toString());
                int total = query2.list().size();
                Map map = new HashMap();
//				return query.list();
                map.put("result", query.list());
                map.put("total", new Integer(total));
                return map;
            }

        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public SuperviseTaskRule getSuperviseTaskRuleById(final String id) {
        SuperviseTaskRule t = null;
        if (id != null && !"".equals(id)) {
            String queryStr = "from SuperviseTaskRule t where t.id='" + id + "'";
            List list = (List) getHibernateTemplate().find(queryStr);
            if (list != null && list.size() > 0) {
                t = (SuperviseTaskRule) list.get(0);
            }
        }
        return t;
    }

    public SuperviseTaskRecord getSuperviseTaskRecordById(final String id) {
        SuperviseTaskRecord t = null;
        if (id != null && !"".equals(id)) {
            String queryStr = "from SuperviseTaskRecord t where t.id='" + id + "'";
            List list = (List) getHibernateTemplate().find(queryStr);
            if (list != null && list.size() > 0) {
                t = (SuperviseTaskRecord) list.get(0);
            }
        }
        return t;
    }

    public SuperviseTaskRecord getSuperviseTaskRecordBySheetId(final String id) {
        SuperviseTaskRecord t = null;
        if (id != null && !"".equals(id)) {
            String queryStr = "from SuperviseTaskRecord t where t.sheetId='" + id + "'";
            List list = (List) getHibernateTemplate().find(queryStr);
            if (list != null && list.size() > 0) {
                t = (SuperviseTaskRecord) list.get(0);
            }
        }
        return t;
    }

    public ListedRegulationMain getListedRegulationMainById(final String id) {
        ListedRegulationMain t = null;
        if (id != null && !"".equals(id)) {
            String queryStr = "from ListedRegulationMain t where t.id='" + id + "'";
            List list = (List) getHibernateTemplate().find(queryStr);
            if (list != null && list.size() > 0) {
                t = (ListedRegulationMain) list.get(0);
            }
        }
        return t;
    }

    public ListedRegulationMain getListedRegulationMainBySheetId(final String sheetId) {
        ListedRegulationMain t = null;
        if (sheetId != null && !"".equals(sheetId)) {
            String queryStr = "from ListedRegulationMain t where t.sheetId='" + sheetId + "'";
            List list = (List) getHibernateTemplate().find(queryStr);
            if (list != null && list.size() > 0) {
                t = (ListedRegulationMain) list.get(0);
            }
        }
        return t;
    }

    public CommonTaskMain getCommonTaskMainById(final String sheetId) {
        CommonTaskMain t = null;
        if (sheetId != null && !"".equals(sheetId)) {
            String queryStr = "from CommonTaskMain t where t.id='"
                    + sheetId + "'";
            List list = (List) getHibernateTemplate().find(queryStr);
            if (list != null && list.size() > 0) {
                t = (CommonTaskMain) list.get(0);
            }
        }
        return t;
    }

    public ListedRegulationTask getListedRegulationTaskById(final String sheetId) {
        ListedRegulationTask t = null;
        if (sheetId != null && !"".equals(sheetId)) {
            String queryStr = "from ListedRegulationTask t where t.sheetId='" + sheetId + "'";
            List list = (List) getHibernateTemplate().find(queryStr);
            if (list != null && list.size() > 0) {
                t = (ListedRegulationTask) list.get(0);
            }
        }
        return t;
    }

    public Map supervisetaskRuleList2(final Integer curPage, final Integer pageSize, final Map maptj) {
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                String queryStr = " from SuperviseTaskRule t where t.deleted='0' ";
                String whereStr = "";

                String major = (String) maptj.get("major");
                String city = (String) maptj.get("city");
                String country = (String) maptj.get("country");
                String mainNetSortOne = (String) maptj.get("mainNetSortOne");
                String mainNetSortTwo = (String) maptj.get("mainNetSortTwo");
                String mainNetSortThree = (String) maptj.get("mainNetSortThree");
                String listedRegulationType = (String) maptj.get("listedRegulationType");
                String listedRegulationCycle = (String) maptj.get("listedRegulationCycle");

                if (major != null && !major.equals("")) {
                    whereStr += " and t.major='" + major + "' ";
                }
                if (city != null && !city.equals("")) {
                    whereStr += " and t.city='" + city + "' ";
                }
                if (country != null && !country.equals("")) {
                    whereStr += " and t.country like '%" + country + "%' ";
                }
                if (mainNetSortOne != null && !mainNetSortOne.equals("")) {
                    whereStr += " and t.mainNetSortOne='" + mainNetSortOne + "' ";
                }
                if (mainNetSortTwo != null && !mainNetSortTwo.equals("")) {
                    whereStr += " and t.mainNetSortTwo='" + mainNetSortTwo + "' ";
                }
                if (mainNetSortThree != null && !mainNetSortThree.equals("")) {
                    whereStr += " and t.mainNetSortThree='" + mainNetSortThree + "' ";
                }
                if (listedRegulationType != null && !listedRegulationType.equals("")) {
                    whereStr += " and t.listedRegulationType like '%" + listedRegulationType + "%' ";
                }
                if (listedRegulationCycle != null && !listedRegulationCycle.equals("")) {
                    whereStr += " and t.listedRegulationCycle like '%" + listedRegulationCycle + "%' ";
                }

                String hql = queryStr + whereStr;
                Query query = session.createQuery(hql.toString());
                //设置归档标记
//				query.setInteger("status", Constants.SHEET_HOLD.intValue());
//				query.setInteger("status1", Constants.ACTION_FORCE_HOLD);

                //分页查询条
                if (pageSize.intValue() != -1) {
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));

                    query.setMaxResults(pageSize.intValue());
                }
                Query query2 = session.createQuery(hql.toString());
                int total = query2.list().size();
                Map map = new HashMap();
                map.put("result", query.list());
                map.put("total", new Integer(total));
                List result = query.list();
                if (result.size() == 0) {
                    whereStr = " ";
                    if (major != null && !major.equals("")) {
                        whereStr += " and (t.major='" + major + "' or t.major is null) ";
                    }
                    if (city != null && !city.equals("")) {
                        whereStr += " and t.city='" + city + "' ";
                    }
                    if (country != null && !country.equals("")) {
                        whereStr += " and (t.country like '%" + country + "%' or t.country is null) ";
                    }
                    if (mainNetSortOne != null && !mainNetSortOne.equals("")) {
                        whereStr += " and t.mainNetSortOne='" + mainNetSortOne + "' ";
                    }
                    if (mainNetSortTwo != null && !mainNetSortTwo.equals("")) {
                        whereStr += " and (t.mainNetSortTwo='" + mainNetSortTwo + "' or t.mainNetSortTwo is null) ";
                    }
                    if (mainNetSortThree != null && !mainNetSortThree.equals("")) {
                        whereStr += " and (t.mainNetSortThree='" + mainNetSortThree + "' or t.mainNetSortThree is null) ";
                    }
                    if (listedRegulationType != null && !listedRegulationType.equals("")) {
                        whereStr += " and t.listedRegulationType like '%" + listedRegulationType + "%' ";
                    }
                    if (listedRegulationCycle != null && !listedRegulationCycle.equals("")) {
                        whereStr += " and t.listedRegulationCycle like '%" + listedRegulationCycle + "%' ";
                    }
                    whereStr += " order by t.mainNetSortTwo,t.mainNetSortThree ";
                    hql = queryStr + whereStr;
                    query = session.createQuery(hql.toString());
                    if (pageSize.intValue() != -1) {
                        query.setFirstResult(pageSize.intValue()
                                * (curPage.intValue()));

                        query.setMaxResults(pageSize.intValue());
                    }

                    map = new HashMap();
                    map.put("result", query.list());
                    map.put("total", new Integer(total));
                }


                return map;
            }

        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public Map getBoardDetail2(final Integer curPage, final Integer pageSize, final Map maptj) throws Exception {
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session) {
                IDownLoadSheetAccessoriesService service = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
                String status = (String) maptj.get("status");
                String sql = "";
                if (status.equals("0")) {
                    sql = "SELECT DISTINCT m.sheetid,m.title from  ( " +
                            " SELECT A .noticeuserid, A .noticemobile,A .*FROM " +
                            " sms_record A WHERE A .dispatchtime - SYSDATE <= 1 / 24 " +
                            " AND A .ifload = 0 AND NOTICEUSERID = 'xiongchiliang') m";

                } else if (status.equals("1")) {
                    String commonfaultsql = "SELECT DISTINCT m.SHEETID FROM( " +
                            " select a.noticeuserid,a.noticemobile,a.*  from " +
                            " sms_record a where a.ifload = 0 and exists " +
                            "(select 1 from commonfault_main b where b.sheetid = a.sheetid and b.status = 0))m";
                    String listedregulationsql = "SELECT DISTINCT m.SHEETID FROM( " +
                            " select a.noticeuserid,a.noticemobile,a.*  from " +
                            " sms_record a where a.ifload = 0 and exists " +
                            " (select 1 from commonfault_main b where b.sheetid = a.sheetid and b.status = 0))m";
                    sql = commonfaultsql + " union all(" + listedregulationsql + ")";
                } else if (status.equals("2")) {
                    String sql3commonfault = "SELECT distinct m.sheetid  from( " +
                            " select a.noticeuserid,a.noticemobile,a.* from sms_record a where a.ifload = 1 and " +
                            " exists (select 1 from sms_record b where b.sheetid = a.sheetid " +
                            " and b.dispatchtime >a.dispatchtime and b.ifload = 1) " +
                            " and exists (select 1 from commonfault_main b where b.sheetid = a.sheetid and b.status = 0))m";
                    String sql3listedregulation = "SELECT distinct m.sheetid  from( " +
                            " select a.noticeuserid,a.noticemobile,a.* from sms_record a where a.ifload = 1 and " +
                            " exists (select 1 from sms_record b where b.sheetid = a.sheetid " +
                            " and b.dispatchtime >a.dispatchtime and b.ifload = 1) " +
                            " and exists (select 1 from listedregulation_main b where b.sheetid = a.sheetid and b.status = 0))m";
                    sql = sql3commonfault + " union all(" + sql3listedregulation + ")";
                }
                Query query = session.createSQLQuery(sql);
                if (pageSize.intValue() != -1) {
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));

                    query.setMaxResults(pageSize.intValue());
                }
                List list = query.list();
                Query query2 = session.createSQLQuery(sql);
                int total = query2.list().size();
                Map map = new HashMap();
                map.put("result", list);
                map.put("total", new Integer(total));
                return map;
            }

        };

        return (Map) getHibernateTemplate().execute(callback);
    }

    public Map BoardCountList(final Integer curPage, final Integer pageSize, final Map maptj) throws Exception {
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                String userid = (String) maptj.get("userid");
                String querysql = "SELECT DISTINCT S.sheetid,'commonfault' type_,m.sendtime from \n" +
                        "SMS_RECORD s,COMMONFAULT_MAIN m where s.ifload=0 and s.sheetid=m.sheetid\n" +
                        "union all(\n" +
                        "select DISTINCT s.sheetid,'listedregulation' type_,l.sendtime from  \n" +
                        "SMS_RECORD s,LISTEDREGULATION_MAIN l where  s.sheetid=l.sheetid)";

                String sql = querysql;

                Query query = session.createSQLQuery(sql);
                if (pageSize.intValue() != -1) {
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));

                    query.setMaxResults(pageSize.intValue());
                }
                List list = query.list();
                Query query2 = session.createSQLQuery(sql);
                int total = query2.list().size();
                Map map = new HashMap();
                map.put("result", list);
                map.put("total", new Integer(total));
                return map;
            }

        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public Map BoardCountPersonList(final Integer curPage, final Integer pageSize, final Map maptj) throws Exception {
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                String userid = (String) maptj.get("userid");
                String querysql = "";

                String sql = querysql;

                Query query = session.createSQLQuery(sql);
                if (pageSize.intValue() != -1) {
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));

                    query.setMaxResults(pageSize.intValue());
                }
                List list = query.list();
                Query query2 = session.createSQLQuery(sql);
                int total = query2.list().size();
                Map map = new HashMap();
                map.put("result", list);
                map.put("total", new Integer(total));
                return map;
            }

        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public void supervisetaskMainDutySave(SuperviseTaskMainDuty t) {
        if ((t.getId() == null) || (t.getId().equals(""))) {
            getHibernateTemplate().save(t);
        } else
            getHibernateTemplate().saveOrUpdate(t);

    }
}
