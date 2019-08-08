package com.boco.eoms.commons.log.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.boco.eoms.base.service.impl.BaseManager;

import com.boco.eoms.commons.log.model.TawCommonLogOperator;
import com.boco.eoms.commons.log.dao.TawCommonLogOperatorDao;

import com.boco.eoms.commons.log.service.TawCommonLogOperatorManager;

public class TawCommonLogOperatorManagerImpl extends BaseManager implements
        TawCommonLogOperatorManager {

    private TawCommonLogOperatorDao tawCommonLogOperatorDao;

    public TawCommonLogOperatorDao getTawCommonLogOperatorDao() {
        return tawCommonLogOperatorDao;
    }

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawCommonLogOperatorDao(
            TawCommonLogOperatorDao tawCommonLogOperatorDao) {
        this.tawCommonLogOperatorDao = tawCommonLogOperatorDao;
    }

    /**
     * @see com.boco.eoms.commons.log.service.TawCommonLogOperatorManager#getTawCommonLogOperators(com.boco.eoms.commons.log.model.TawCommonLogOperator)
     */
    public List getTawCommonLogOperators(
            final TawCommonLogOperator tawCommonLogOperator) {
        return tawCommonLogOperatorDao
                .getTawCommonLogOperators(tawCommonLogOperator);
    }

    /**
     * @see com.boco.eoms.commons.log.service.TawCommonLogOperatorManager#getTawCommonLogOperator(String
     * id)
     */
    public TawCommonLogOperator getTawCommonLogOperator(final String id) {
        return tawCommonLogOperatorDao.getTawCommonLogOperator(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.log.service.TawCommonLogOperatorManager#saveTawCommonLogOperator(TawCommonLogOperator
     * tawCommonLogOperator)
     */
    public void saveTawCommonLogOperator(
            TawCommonLogOperator tawCommonLogOperator) {
        tawCommonLogOperatorDao.saveTawCommonLogOperator(tawCommonLogOperator);
    }

    /**
     * @see com.boco.eoms.commons.log.service.TawCommonLogOperatorManager#removeTawCommonLogOperator(String
     * id)
     */
    public void removeTawCommonLogOperator(final String id) {
        tawCommonLogOperatorDao.removeTawCommonLogOperator(new String(id));
    }

    /**
     * 通过用户ID 模块ID 操作ID 时间段进行日志查询
     *
     * @param userid
     * @param modelid
     * @param operid
     * @param starttime
     * @param endtime
     * @return
     */
    public List getAllBymodelopertiems(HttpServletRequest request, final Integer curPage,
                                       final Integer pageSize, final String userid, final String modelid,
                                       final String operid, final String starttime, final String endtime, final String issucess) {

        List list = new ArrayList();
        List datelist = new ArrayList();
        Map map = null;
        map = tawCommonLogOperatorDao.getAllbyUseridModelidOperids(curPage,
                pageSize, userid,
                modelid, operid, issucess, starttime, endtime);
        list = (List) map.get("result");
        datelist = list;
        request.setAttribute("resultSize", map.get("total"));
        return datelist;
    }

    /**
     * 查询某个用户的所有操作日志
     *
     * @param userid
     * @return
     */
    public List getAllByUserIDs(String userid, String issucess) {
        List list = new ArrayList();

        list = tawCommonLogOperatorDao.getAllByUserIDs(userid, issucess);
        return list;
    }

    /**
     * 查询某个模块的日志信息
     *
     * @param modelid
     * @return
     */
    public List getAllBymodelids(String modelid, String issucess) {
        List list = new ArrayList();

        list = tawCommonLogOperatorDao.getAllBymodelids(modelid, issucess);
        return list;
    }

    /**
     * 查询某个用户 对某个模块的操作情况
     *
     * @param userid
     * @param modelid
     * @return
     */
    public List getAllByuseridandModelids(String userid, String modelid,
                                          String issucess) {
        List list = new ArrayList();

        list = tawCommonLogOperatorDao.getAllByuseridandModelids(userid,
                modelid, issucess);
        return list;
    }

    /**
     * 查询某个用户对某个模块某个业务的操作情况
     *
     * @param userid
     * @param modelid
     * @param operid
     * @return
     */
    public List getAllbyUseridModelidOperids(final Integer curPage,
                                             final Integer pageSize, String userid, String modelid,
                                             String operid, String issucess) {
        List list = new ArrayList();
        String strat = "";
        String end = "";
        Map map = null;
        map = tawCommonLogOperatorDao.getAllbyUseridModelidOperids(curPage,
                pageSize, userid,
                modelid, operid, issucess, strat, end);
        list = (List) map.get("result");
        return list;
    }

    /**
     * 查询某个模块 的某个业务的操作情况
     *
     * @param modelid
     * @param operid
     * @return
     */
    public List getAllbyModelidAndOperids(String modelid, String operid,
                                          String issucess) {
        List list = new ArrayList();

        list = tawCommonLogOperatorDao.getAllbyModelidAndOperids(modelid,
                operid, issucess);
        return list;

    }

    /**
     * 根据userid查询某个用户某个时间段的日志信息
     *
     * @param userid
     * @param starttime
     * @param endtime
     * @return
     */
    public List getAllByUseridAndTimes(String userid, String starttime,
                                       String endtime, String issucess) {
        List list = new ArrayList();
        List datelist = new ArrayList();
        TawCommonLogOperator oper;

        list = tawCommonLogOperatorDao.getAllByUserIDs(userid, issucess);
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {

            oper = (TawCommonLogOperator) iter.next();
            String thattime = oper.getBeginnotetime();
            if (konowDate(thattime, starttime, endtime).booleanValue()) {
                datelist.add(oper);
            }
        }
        return datelist;
    }

    /**
     * 更加模块ID 查询某个模块在某个时间段的日志信息
     *
     * @param modelid
     * @param starttiem
     * @param endtime
     * @return
     */
    public List getAllByModelidAndTimes(String modelid, String starttiem,
                                        String endtime, String issucess) {
        List list = new ArrayList();
        List datelist = new ArrayList();
        TawCommonLogOperator oper;

        list = tawCommonLogOperatorDao.getAllBymodelids(modelid, issucess);
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            oper = new TawCommonLogOperator();
            oper = (TawCommonLogOperator) iter.next();
            String thattime = oper.getBeginnotetime();
            if (konowDate(thattime, starttiem, endtime).booleanValue()) {
                datelist.add(oper);
            }
        }
        return datelist;
    }

    /**
     * 根据用户ID 模块ID 查询某个时间段某个用户对某个模块的日志信息
     *
     * @param userid
     * @param modelid
     * @param starttime
     * @param endtime
     * @return
     */
    public List getAllByUMIDandTimes(String userid, String modelid,
                                     String starttime, String endtime, String issucess) {
        List list = new ArrayList();
        List datelist = new ArrayList();
        TawCommonLogOperator oper;

        list = tawCommonLogOperatorDao.getAllByuseridandModelids(userid,
                modelid, issucess);
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            oper = new TawCommonLogOperator();
            oper = (TawCommonLogOperator) iter.next();
            String thattime = oper.getBeginnotetime();
            if (konowDate(thattime, starttime, endtime).booleanValue()) {
                datelist.add(oper);
            }
        }
        return datelist;
    }

    /**
     * 根据模块ID 操作ID 查询某个时间段某个模块的某个操作的日志信息
     *
     * @param modelid
     * @param operid
     * @param startime
     * @param endtime
     * @return
     */
    public List getAllByMidAndOperidAndTimes(String modelid, String operid,
                                             String startime, String endtime, String issucess) {
        List list = new ArrayList();
        List datelist = new ArrayList();
        TawCommonLogOperator oper;

        if (startime == null) {
            startime = "";
        }
        if (endtime == null) {
            endtime = "";
        }
        list = tawCommonLogOperatorDao.getAllbyModelidAndOperids(modelid,
                operid, issucess);
        if (!startime.equals("") && !endtime.equals("")) {
            for (Iterator iter = list.iterator(); iter.hasNext(); ) {
                oper = new TawCommonLogOperator();
                oper = (TawCommonLogOperator) iter.next();
                String thattime = oper.getBeginnotetime();
                if (konowDate(thattime, startime, endtime).booleanValue()) {
                    datelist.add(oper);
                }
            }
        }
        return datelist;
    }

    private static Boolean konowDate(String thattime, String begintime,
                                     String endtime) {
        boolean flag = false;

        try {

            java.util.Date ttime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse(thattime);
            java.util.Date btime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse(begintime);
            java.util.Date etime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse(endtime);

            long t = ttime.getTime();
            t += 24 * 60 * 60 * 1000;
            t += 60 * 60 * 1000;

            long b = btime.getTime();
            b += 24 * 60 * 60 * 1000;
            b += 60 * 60 * 1000;

            long et = etime.getTime();
            et += 24 * 60 * 60 * 1000;
            et += 60 * 60 * 1000;

            if (t >= b && t <= et) {
                flag = true;
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }

        return new Boolean(flag);
    }

}
