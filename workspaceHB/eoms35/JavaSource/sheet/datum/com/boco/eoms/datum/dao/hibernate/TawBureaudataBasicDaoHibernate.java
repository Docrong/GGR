/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.datum.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.datum.dao.ITawBureaudataBasicDAO;
import com.boco.eoms.datum.model.TawBureaudataBasic;
import com.boco.eoms.datum.vo.TawBureaudataSegmenthlrVO;
import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;

/**
 * @author TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TawBureaudataBasicDaoHibernate extends BaseSheetDaoHibernate
        implements ITawBureaudataBasicDAO {

    public Object getObject(Class clazz, Serializable id)
            throws HibernateException {

        return super.getObject(clazz, id);
    }

    public void removeObject(Class clazz, Serializable id)
            throws HibernateException {
        super.removeObject(clazz, id);

    }

    public void saveObject(Object o) throws HibernateException {
        super.saveObject(o);
    }

    public void saveOrUpdateAll(List list) throws HibernateException {
        getHibernateTemplate().saveOrUpdateAll(list);
    }

    public List getObjectsByCondtion(final Integer curPage,
                                     final Integer pageSize, int[] aTotal, final String hql,
                                     String counthql, String queryNumber) throws HibernateException {
        aTotal[0] = ((Integer) getHibernateTemplate().find(counthql)
                .listIterator().next()).intValue();
        if (!queryNumber.equals("number")) {
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session)
                        throws HibernateException {
                    Query query = session.createQuery(hql);
                    if (pageSize.intValue() != -1) {
                        // 分页查询条
                        query.setFirstResult(pageSize.intValue()
                                * (curPage.intValue()));
                        query.setMaxResults(pageSize.intValue());
                    }
                    return query.list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }
        return new ArrayList();

    }

    public List loadList(String hql) throws HibernateException {

        return getHibernateTemplate().find(hql);

    }

    public void updateData(List seglist, int belongflag) throws HibernateException {
        // getHibernateTemplate().saveOrUpdate(o);

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        try {
            int size = seglist.size();
            conn = this.getSession().connection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            for (int i = 0; i < size; i++) {
                TawBureaudataSegmenthlrVO segVO = (TawBureaudataSegmenthlrVO) seglist
                        .get(i);
                for (int seg = segVO.getBeginSegment(); seg <= segVO.getEndSegment(); seg++) {
                    String sql = "";
                    if (belongflag == 0) {
                        sql = "update taw_bureaudata_basic set belongflag=0,hlrsignalid='',prehlrsignalid='' where segmentid='"
                                + seg + "'";
                    } else {
                        sql = "update taw_bureaudata_basic set belongflag=1,hlrsignalid =prehlrsignalid  where segmentid='"
                                + seg + "'";
                    }

                    System.out.println("update sql:" + sql);
                    stmt.addBatch(sql);
                }
            }
            stmt.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void saveOrUpdate(List seglist, int belongflag)
            throws HibernateException {
        // getHibernateTemplate().saveOrUpdate(o);

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        try {
            int size = seglist.size();
            conn = this.getSession().connection();
            TawBureaudataSegmenthlrVO obj = (TawBureaudataSegmenthlrVO) seglist.get(0);
            String deletesql = "delete from taw_bureaudatasheet_detail where sheetid=?";
            ps = conn.prepareStatement(deletesql);
            ps.setString(1, obj.getBureaudataSheet());
            int row = ps.executeUpdate();
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            for (int i = 0; i < size; i++) {
                TawBureaudataSegmenthlrVO segVO = (TawBureaudataSegmenthlrVO) seglist
                        .get(i);
                for (int seg = segVO.getBeginSegment(); seg <= segVO
                        .getEndSegment(); seg++) {
                    String sql = "";
                    if (row > 0) {
                        sql = "update taw_bureaudata_basic set belongflag=1,hlrsignalid='"
                                + segVO.getHlrSignalId()
                                + "' where segmentid='"
                                + seg + "'";
                    } else {
                        sql = "update taw_bureaudata_basic set belongflag=1,prehlrsignalid=hlrsignalid,hlrsignalid='"
                                + segVO.getHlrSignalId()
                                + "' where segmentid='"
                                + seg + "'";
                    }

                    System.out.println("update sql:" + sql);
                    stmt.addBatch(sql);

                    String sqld = "";

                    if (belongflag == 2) {
                        sqld = "update taw_bureaudatasheet_detail set segmentid='"
                                + seg
                                + "',hlrsignalid='"
                                + segVO.getHlrSignalId()
                                + "',prehlrsignalid=hlrsignalid where sheetid='"
                                + segVO.getBureaudataSheet() + "'";
                    } else {
                        sqld = "insert into taw_bureaudatasheet_detail(sheetid,segmentid,hlrsignalid,prehlrsignalid,id)"
                                + "values('"
                                + segVO.getBureaudataSheet()
                                + "','"
                                + seg
                                + "','"
                                + segVO.getHlrSignalId()
                                + "','"
                                + segVO.getPrehlrSignalId()
                                + "',taw_bureaudatasheet_detail_sqe.nextVal)";
                    }
                    System.out.println("insert sql:" + sqld);
                    stmt.addBatch(sqld);
                }
            }
            stmt.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    /***************************************************************************
     *
     */
    public Set getAllBureaudataBasic() throws HibernateException {
        Set set = new HashSet();
        String sql = "from TawBureaudataBasic";
        List list = this.getHibernateTemplate().find(sql);
        for (int i = 0; i < list.size(); i++) {
            TawBureaudataBasic obj = (TawBureaudataBasic) list.get(i);
            set.add(obj.getSegmentid());
        }
        return set;
    }

    public List getAllTawBureaudata() throws HibernateException {
        String sql = "select c.areaname, h.hlrname,h.hlrsignalid,b.segmentid, b.newbureauid, b.adjustbureauid,b.bureadatasheetid "
                + " from TawBureaudataBasic b,TawSystemArea c ,TawBureaudataHlr h "
                + "  where  b.zonenum = c.zonenum and b.hlrsignalid = h.hlrsignalid  and b.belongflag = 1 order by b.segmentid";
        return getHibernateTemplate().find(sql);
    }

    public List getCityAdjustBureauDataForExcel(Map headSegMap, String bureauId)
            throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public Map getHLRAdjustBureauDataForExcel(Map headSegMap)
            throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    public Map getHeadsegMapForCityAdjust(String bureauId)
            throws HibernateException {
        Map headSegMap = new LinkedHashMap();
        String sql = "select distinct b.segmentid "
                + "from TawBureaudataBasic b "
                + "where b.adjustbureauid like '%" + bureauId + "%' "
                + "order by segmentid";
        List list = getHibernateTemplate().find(sql);
        if (list != null) {
            int index = 0;
            for (int i = 0; i < list.size(); i++) {
                String key = list.get(i).toString().substring(0, 4);
                headSegMap.put(key, new Integer(index));
                if (headSegMap.get(key) == null) {
                    index++;
                }
            }
        }
        return headSegMap;
    }

    public Map getHeadsegMapForHLRAdjust() throws HibernateException {
        Map headSegMap = new LinkedHashMap();
        Connection comm = null;
        Statement sta = null;
        ResultSet rs = null;
        try {
            String sql = "select distinct Substr(to_char(b.segmentid), 0, 4) headSeg   from taw_bureaudatasheet_detail d  "
                    + "inner join (select m.id, m.title from BureaudataUpdate_Main m where m.id in (select distinct t.sheetkey  from BureaudataUpdate_Task t  where t.taskstatus = '2')) tmp on d.sheetid = tmp.id "
                    + "inner join taw_bureaudata_basic b on b.segmentid = d.segmentid inner join Taw_System_Area c on c.zonenum = b.zonenum"
                    + " left outer join taw_bureaudata_hlr hlr on hlr.hlrsignalid = b.hlrsignalid "
                    + "left outer join taw_bureaudata_hlr prehlr on prehlr.hlrsignalid = d.hlrsignalid where b.belongflag = 1 order by headSeg";
            comm = this.getSession().connection();
            comm.setAutoCommit(false);
            sta = comm.createStatement();

            rs = sta.executeQuery(sql);

            int index = 0;
            while (rs.next()) {
                headSegMap.put(rs.getString("headSeg"), new Integer(index));
                ++index;
            }
            comm.commit();
            comm.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (sta != null) {
                    sta.close();
                }
                if (comm != null) {
                    comm.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return headSegMap;
    }

    public Map getHeadsegMapForNew(String bureauId) throws HibernateException {
        Map headSegMap = new LinkedHashMap();
        String sql = "select distinct b.segmentid "
                + "from TawBureaudataBasic b " + "where b.newbureauid like '%"
                + bureauId + "%' " + "order by segmentid";
        List list = getHibernateTemplate().find(sql);
        if (list != null) {
            int index = 0;
            for (int i = 0; i < list.size(); i++) {
                String key = list.get(i).toString().substring(0, 4);
                headSegMap.put(key, new Integer(index));
                if (headSegMap.get(key) == null) {
                    index++;
                }
            }
        }
        System.out.println("headMap===" + headSegMap.size());
        return headSegMap;
    }

    public List getNewBureauData(String bureauId)
            throws HibernateException {
        List list = new ArrayList();
        String sql = " select b,hlr.hlrname, hlr.hlrid,c.areaname,prec.areaname  "
                + " from TawBureaudataBasic b ,TawBureaudataHlr hlr,TawSystemArea c,TawSystemArea prec"
                + " where b.prezonenum = prec.zonenum and b.zonenum = c.zonenum and hlr.hlrsignalid=b.hlrsignalid"
                + " and b.newbureauid like '%"
                + bureauId
                + "%' "
                + " order by prec.areaid,c.areaid, b.segmentid, b.hlrsignalid";
        List listsql = getHibernateTemplate().find(sql);
        int beg = 0;
        int end = 0;
        try {
            TawBureaudataSegmenthlrVO tmpPreseg = new TawBureaudataSegmenthlrVO();
            for (int i = 0; i < listsql.size(); i++) {
                Object[] obj = (Object[]) listsql.get(i);
                TawBureaudataSegmenthlrVO tmpSeg = new TawBureaudataSegmenthlrVO();
                TawBureaudataBasic base = (TawBureaudataBasic) obj[0];
                int tmp = base.getSegmentid().intValue();
                tmpSeg.setHlrId(StaticMethod.nullObject2String(obj[2], ""));
                tmpSeg.setHlrName(StaticMethod.nullObject2String(obj[1], ""));
                tmpSeg.setCityName(StaticMethod.nullObject2String(obj[3], ""));
                tmpSeg.setPrecityName(StaticMethod
                        .nullObject2String(obj[4], ""));
                tmpSeg.setZoneNum(base.getZonenum().intValue());
                tmpSeg.setPrezoneNum(base.getPrezonenum().intValue());
                tmpSeg.setPrehlrSignalId(base.getPrehlrsignalid());
                tmpSeg.setHlrSignalId(base.getHlrsignalid());
                if (beg == 0) {
                    beg = tmp;
                    end = tmp;
                } else {
                    boolean flag = (tmpSeg.getPrezoneNum() == tmpPreseg
                            .getPrezoneNum());
                    flag = flag
                            && (tmpSeg.getZoneNum() == tmpPreseg.getZoneNum());
                    flag = flag && (tmp == end + 1);
                    flag = flag
                            && (String.valueOf(tmp).substring(0, 4)
                            .equals(String.valueOf(end).substring(0, 4)));
                    flag = flag
                            && (tmpSeg.getHlrSignalId().equals(tmpPreseg
                            .getHlrSignalId()));
                    if (flag) {
                        end = tmp;
                    } else {
                        TawBureaudataSegmenthlrVO seg = new TawBureaudataSegmenthlrVO();
                        MyBeanUtils.copyProperties(seg, tmpPreseg);
                        seg.setBeginSegment(beg);
                        seg.setEndSegment(end);
                        list.add(seg);
                        beg = tmp;
                        end = tmp;
                    }
                }
                MyBeanUtils.copyProperties(tmpPreseg, tmpSeg);

            }
            if (beg != 0) {
                TawBureaudataSegmenthlrVO seg = new TawBureaudataSegmenthlrVO();
                MyBeanUtils.copyProperties(seg, tmpPreseg);
                seg.setBeginSegment(beg);
                seg.setEndSegment(end);
                list.add(seg);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List getCityAdjustBureauData(String bureauId)
            throws HibernateException {
        List list = new ArrayList();
        String sql = " select b,hlr.hlrname, hlr.hlrid,c.areaname,prec.areaname  "
                + " from TawBureaudataBasic b ,TawBureaudataHlr hlr,TawSystemArea c,TawSystemArea prec"
                + " where b.prezonenum = prec.zonenum and b.zonenum = c.zonenum and hlr.hlrsignalid=b.hlrsignalid"
                + " and b.adjustbureauid like '%"
                + bureauId
                + "%' "
                + " order by prec.areaid,c.areaid, b.segmentid, b.hlrsignalid";
        List listsql = getHibernateTemplate().find(sql);
        int beg = 0;
        int end = 0;
        try {
            TawBureaudataSegmenthlrVO tmpPreseg = new TawBureaudataSegmenthlrVO();
            for (int i = 0; i < listsql.size(); i++) {
                Object[] obj = (Object[]) listsql.get(i);
                TawBureaudataSegmenthlrVO tmpSeg = new TawBureaudataSegmenthlrVO();
                TawBureaudataBasic base = (TawBureaudataBasic) obj[0];
                int tmp = base.getSegmentid().intValue();
                tmpSeg.setHlrId(StaticMethod.nullObject2String(obj[2], ""));
                tmpSeg.setHlrName(StaticMethod.nullObject2String(obj[1], ""));
                tmpSeg.setCityName(StaticMethod.nullObject2String(obj[3], ""));
                tmpSeg.setPrecityName(StaticMethod
                        .nullObject2String(obj[4], ""));
                tmpSeg.setZoneNum(base.getZonenum().intValue());
                tmpSeg.setPrezoneNum(base.getPrezonenum().intValue());
                tmpSeg.setPrehlrSignalId(base.getPrehlrsignalid());
                tmpSeg.setHlrSignalId(base.getHlrsignalid());
                if (beg == 0) {
                    beg = tmp;
                    end = tmp;
                } else {
                    boolean flag = (tmpSeg.getPrezoneNum() == tmpPreseg
                            .getPrezoneNum());
                    flag = flag
                            && (tmpSeg.getZoneNum() == tmpPreseg.getZoneNum());
                    flag = flag && (tmp == end + 1);
                    flag = flag
                            && (String.valueOf(tmp).substring(0, 4)
                            .equals(String.valueOf(end).substring(0, 4)));
                    flag = flag
                            && (tmpSeg.getHlrSignalId().equals(tmpPreseg
                            .getHlrSignalId()));
                    if (flag) {
                        end = tmp;
                    } else {
                        TawBureaudataSegmenthlrVO seg = new TawBureaudataSegmenthlrVO();
                        MyBeanUtils.copyProperties(seg, tmpPreseg);
                        seg.setBeginSegment(beg);
                        seg.setEndSegment(end);
                        list.add(seg);
                        beg = tmp;
                        end = tmp;
                    }
                }
                MyBeanUtils.copyProperties(tmpPreseg, tmpSeg);

            }
            if (beg != 0) {
                TawBureaudataSegmenthlrVO seg = new TawBureaudataSegmenthlrVO();
                MyBeanUtils.copyProperties(seg, tmpPreseg);
                seg.setBeginSegment(beg);
                seg.setEndSegment(end);
                list.add(seg);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List getHLRAdjustBureauData() throws HibernateException {
        Connection comm = null;
        Statement sta = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            String sql = "select tmp.title, b.segmentid,b.zonenum,c.areaname,"
                    + "hlr.hlrname,hlr.hlrsignalid,prehlr.hlrname ,prehlr.hlrsignalid from Taw_Bureaudatasheet_Detail d"
                    + ",(select m.id, m.title from BureaudataUpdate_Main m  where m.id in (select distinct t.sheetkey from BureaudataUpdate_Task t where t.taskstatus = '2')) tmp, "
                    + " Taw_Bureaudata_Basic b , Taw_System_Area c  , Taw_Bureaudata_Hlr hlr  ,Taw_Bureaudata_Hlr prehlr "
                    + " where d.sheetid = tmp.id and b.segmentid = d.segmentid and c.zonenum = b.zonenum and hlr.hlrsignalid = b.hlrsignalid and prehlr.hlrsignalid = d.hlrsignalid"
                    + " and b.belongflag = 1 order by tmp.title, d.segmentid";
            comm = this.getSession().connection();
            comm.setAutoCommit(false);
            sta = comm.createStatement();

            rs = sta.executeQuery(sql);

            int beg = 0;
            int end = 0;
            TawBureaudataSegmenthlrVO tmpPreseg = new TawBureaudataSegmenthlrVO();
            while (rs.next()) {
                // for (int i = 0; i < listsql.size(); i++) {
                // Object[] obj = (Object[]) listsql.get(i);
                int tmp = StaticMethod.nullObject2int(rs.getObject(2));
                TawBureaudataSegmenthlrVO tmpSeg = new TawBureaudataSegmenthlrVO();
                tmpSeg.setBureaudataSheet(StaticMethod.nullObject2String(rs
                        .getObject(1), ""));
                tmpSeg.setZoneNum(StaticMethod.nullObject2int(rs.getObject(3)));
                tmpSeg.setCityName(StaticMethod.nullObject2String(rs
                        .getObject(4), ""));
                tmpSeg.setHlrName(StaticMethod.nullObject2String(rs
                        .getObject(5), ""));
                tmpSeg.setHlrSignalId(StaticMethod.nullObject2String(rs
                        .getObject(6), ""));
                tmpSeg.setPrehlrName(StaticMethod.nullObject2String(rs
                        .getObject(7), ""));
                tmpSeg.setPrehlrSignalId(StaticMethod.nullObject2String(rs
                        .getObject(8), ""));

                if (beg == 0) {
                    beg = tmp;
                    end = tmp;
                } else {
                    boolean flag = (tmpSeg.getBureaudataSheet()
                            .equals(tmpPreseg.getBureaudataSheet()));
                    flag = flag
                            && (tmpSeg.getZoneNum() == tmpPreseg.getZoneNum());
                    flag = flag
                            && (tmpSeg.getHlrSignalId().equals(tmpPreseg
                            .getHlrSignalId()));
                    flag = flag
                            && (tmpSeg.getPrehlrSignalId().equals(tmpPreseg
                            .getPrehlrSignalId()));
                    flag = flag && (tmp == end + 1);
                    flag = flag
                            && (String.valueOf(tmp).substring(0, 4)
                            .equals(String.valueOf(end).substring(0, 4)));
                    if (flag) {
                        end = tmp;
                    } else {
                        TawBureaudataSegmenthlrVO seg = new TawBureaudataSegmenthlrVO();
                        MyBeanUtils.copyProperties(seg, tmpPreseg);
                        seg.setBeginSegment(beg);
                        seg.setEndSegment(end);
                        list.add(seg);
                        beg = tmp;
                        end = tmp;
                    }
                }
                MyBeanUtils.copyProperties(tmpPreseg, tmpSeg);

            }
            if (beg != 0) {
                TawBureaudataSegmenthlrVO seg = new TawBureaudataSegmenthlrVO();
                MyBeanUtils.copyProperties(seg, tmpPreseg);
                seg.setBeginSegment(beg);
                seg.setEndSegment(end);
                list.add(seg);
            }
            comm.commit();
            comm.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (sta != null) {
                    sta.close();
                }
                if (comm != null) {
                    comm.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }
}
