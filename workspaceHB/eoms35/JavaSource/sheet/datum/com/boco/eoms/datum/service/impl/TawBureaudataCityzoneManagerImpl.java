/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.datum.service.impl;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.datum.dao.ITawBureaudataApplyDao;
import com.boco.eoms.datum.dao.ITawBureaudataBasicDAO;
import com.boco.eoms.datum.dao.ITawBureaudataCityzoneDao;
import com.boco.eoms.datum.model.TawBureaudataApply;
import com.boco.eoms.datum.model.TawBureaudataBasic;
import com.boco.eoms.datum.model.TawBureaudataCityzone;
import com.boco.eoms.datum.model.TawBureaudataHlr;
import com.boco.eoms.datum.service.ITawBureaudataBasicDAOManager;
import com.boco.eoms.datum.service.ITawBureaudataCityzoneManager;
import com.boco.eoms.datum.vo.TawBureaudataSegmenthlrVO;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;

/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TawBureaudataCityzoneManagerImpl implements
        ITawBureaudataCityzoneManager {
    private ITawBureaudataCityzoneDao bureaudataCityzoneDao;

    private ITawBureaudataApplyDao bureaudataApplyDao;

    public ITawBureaudataCityzoneDao getBureaudataCityzoneDao() {
        return bureaudataCityzoneDao;
    }

    public void setBureaudataCityzoneDao(
            ITawBureaudataCityzoneDao bureaudataCityzoneDao) {
        this.bureaudataCityzoneDao = bureaudataCityzoneDao;
    }

    public Map getAllCityIntoMapKeyCityName() throws HibernateException {
        return bureaudataCityzoneDao.getAllCityIntoMapKeyCityName();
    }

    public Map getAllCityIntoMapKeyZoneNum() throws HibernateException {
        return bureaudataCityzoneDao.getAllCityIntoMapKeyZoneNum();
    }

    public Object findByDept(String deptid) throws HibernateException {
        String hql = "from TawBureaudataCityzone where deptid ='" + deptid
                + "'";
        List list = bureaudataCityzoneDao.loadList(hql);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public Object findById(String id) throws HibernateException {
        String hql = "from TawBureaudataCityzone where cityid ='" + id + "'";
        List list = bureaudataCityzoneDao.loadList(hql);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public List findByAll() throws HibernateException {
        String hql = "from TawBureaudataCityzone ";
        return bureaudataCityzoneDao.loadList(hql);
    }

    public Object findByUser(String userid) throws HibernateException {
        String hql = "from TawBureaudataCityzone where userid ='" + userid
                + "'";
        List list = bureaudataCityzoneDao.loadList(hql);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public void removeObject(Class clazz, Serializable id)
            throws HibernateException {
        bureaudataCityzoneDao.removeObject(clazz, id);

    }

    public void saveorUpdate(Object obj) throws HibernateException {
        bureaudataCityzoneDao.saveOrUpdate(obj);

    }

    public String getTawSystemApplyID(String userid, String type)
            throws HibernateException {
        TawBureaudataCityzone obj = (TawBureaudataCityzone) findByUser(userid);
        String city = obj.getCityname();
        java.text.DateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(new Date());
        String sheettypeid = city + "-HLR����-" + type + "-" + year + "-";
        System.out.println("sheettypeid:" + sheettypeid);
        String sql = "from TawBureaudataApply t where t.city ='" + city
                + "' and t.year='" + year + "' and t.worktype='" + type + "'";
        List list = bureaudataCityzoneDao.loadList(sql);
        if (list != null && list.size() > 0) {
            TawBureaudataApply objapp = (TawBureaudataApply) list.get(0);
            // �������ݿ�
            objapp.setFlowid(""
                    + (StaticMethod.null2int(objapp.getFlowid(), 0) + 1));
            bureaudataApplyDao.saveOrUpdate(objapp);
            // ����������
            String flowid = objapp.getFlowid();
            if (flowid.length() == 1) {
                flowid = "000" + flowid;
            } else if (flowid.length() == 2) {
                flowid = "00" + flowid;
            } else if (flowid.length() == 3) {
                flowid = "0" + flowid;
            }
            sheettypeid += flowid;
            System.out.println("list sheettypeid:" + sheettypeid);
            return sheettypeid;
        } else {
            TawBureaudataApply objapp = new TawBureaudataApply();
            String flowid = "1";
            objapp.setFlowid("2");
            objapp.setCity(city);
            objapp.setWorktype(type);
            objapp.setYear(year + "");
            try {
                if (objapp.getId() == null || objapp.getId().equals("")) {
                    objapp.setId(UUIDHexGenerator.getInstance().getID());
                }
                bureaudataApplyDao.saveOrUpdate(objapp);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (flowid.length() == 1) {
                flowid = "000" + flowid;
            } else if (flowid.length() == 2) {
                flowid = "00" + flowid;
            } else if (flowid.length() == 3) {
                flowid = "0" + flowid;
            }
            sheettypeid += flowid;
            System.out.println("update sheettypeid:" + sheettypeid);
            return sheettypeid;
        }
    }

    public ITawBureaudataApplyDao getBureaudataApplyDao() {
        return bureaudataApplyDao;
    }

    public void setBureaudataApplyDao(ITawBureaudataApplyDao bureaudataApplyDao) {
        this.bureaudataApplyDao = bureaudataApplyDao;
    }
}
