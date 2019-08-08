package com.boco.eoms.partdata.mgr.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.partdata.model.TawpartMscid;
import com.boco.eoms.partdata.mgr.TawpartMscidMgr;
import com.boco.eoms.partdata.dao.TawpartMscidDao;

/**
 * <p>
 * Title:MSCID码号管理
 * </p>
 * <p>
 * Description:MSCID码号管理
 * </p>
 * <p>
 * Mon Jul 05 09:11:48 CST 2010
 * </p>
 *
 * @author fengshaohong
 * @version 3.6
 */
public class TawpartMscidMgrImpl implements TawpartMscidMgr {

    private TawpartMscidDao tawpartMscidDao;

    public TawpartMscidDao getTawpartMscidDao() {
        return this.tawpartMscidDao;
    }

    public void setTawpartMscidDao(TawpartMscidDao tawpartMscidDao) {
        this.tawpartMscidDao = tawpartMscidDao;
    }

    public List getTawpartMscids() {
        return tawpartMscidDao.getTawpartMscids();
    }

    public TawpartMscid getTawpartMscid(final String id) {
        return tawpartMscidDao.getTawpartMscid(id);
    }

    public void saveTawpartMscid(TawpartMscid tawpartMscid) {

        boolean isNew = (null == tawpartMscid.getId() || ""
                .equals(tawpartMscid.getId()));

        if (!isNew) {
            tawpartMscidDao.saveTawpartMscid(tawpartMscid);
        } else {
            String m0 = tawpartMscid.getNumberM0();
            String m1 = tawpartMscid.getNumberM1();
            String m2 = tawpartMscid.getNumberM2();
            String headNumber = tawpartMscid.getHeadNumber();
            List tawpartMscidList = tawpartMscidDao
                    .getTawpartMscidsbyHeadnumber(headNumber);
            Map mscidMap = new HashMap();
            for (int i = 0; i < tawpartMscidList.size(); i++) {
                if (tawpartMscidList.get(i) != null
                        && !mscidMap.containsKey(((TawpartMscid) tawpartMscidList
                        .get(i)).getNumberFree()))
                    ;
                mscidMap.put(((TawpartMscid) tawpartMscidList.get(i))
                        .getNumberFree(), (TawpartMscid) tawpartMscidList.get(i));
            }
            try {
                String[] arrm0 = getArrforString(m0, ",");
                String[] arrm1 = getArrforString(m1, ",");
                String[] arrm2 = getArrforString(m2, ",");
                for (int i = 0; i < arrm0.length; i++) {
                    for (int k = 0; k < arrm1.length; k++) {
                        for (int j = 0; j < arrm2.length; j++) {
                            TawpartMscid tawpartMscidtemp = tawpartMscid
                                    .cloneTawpartMscid();
                            tawpartMscidtemp.setNumberM0(arrm0[i]);
                            tawpartMscidtemp.setNumberM1(arrm1[k]);
                            tawpartMscidtemp.setNumberM2(arrm2[j]);
                            tawpartMscidtemp.setNumberFree(tawpartMscidtemp
                                    .getHeadNumber()
                                    + tawpartMscidtemp.getNumberM0()
                                    + tawpartMscidtemp.getNumberM1()
                                    + tawpartMscidtemp.getNumberM2());
                            if (!mscidMap.containsKey(tawpartMscidtemp
                                    .getHeadNumber()
                                    + tawpartMscidtemp.getNumberM0()
                                    + tawpartMscidtemp.getNumberM1()
                                    + tawpartMscidtemp.getNumberM2())) {
                                tawpartMscidDao.saveTawpartMscid(tawpartMscidtemp);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveOneTawpartMscid(TawpartMscid tawpartMscid) {
        try {

            tawpartMscidDao.saveTawpartMscid(tawpartMscid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTawpartMscid(TawpartMscid tawpartMscid) {

        try {
            tawpartMscidDao.saveTawpartMscid(tawpartMscid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] getArrforString(String content, String expression) {
        String[] arr = null;
        if (",".equals(expression)) {
            if (content.indexOf(expression) > 0) {
                arr = content.split(expression);
                return arr;
            } else {
                arr = new String[1];
                arr[0] = content;
            }

        } else if ("-".equals(expression)) {
            if (content.indexOf(expression) > 0) {
                arr = content.split(expression);
                int start = Integer.parseInt(arr[0]);
                int end = Integer.parseInt(arr[1]);
                String[] arrtemp = new String[end - start + 1];
                int j = 0;
                for (int i = start; i <= end; i++) {
                    arrtemp[j] = i + "";
                    j++;
                }
                return arrtemp;
            } else {
                arr = new String[1];
                arr[0] = content;
            }
        }
        return arr;

    }

    public void removeTawpartMscid(final String id) {
        tawpartMscidDao.removeTawpartMscid(id);
    }

    public Map getTawpartMscids(final Integer curPage, final Integer pageSize,
                                final String whereStr) {
        return tawpartMscidDao.getTawpartMscids(curPage, pageSize, whereStr);
    }

    public List getM0byHeadNumber(final String headNumber) {
        return tawpartMscidDao.getM0byHeadNumber(headNumber);
    }

    public List getM1byHeadNumber(final String headNumber) {
        return tawpartMscidDao.getM1byHeadNumber(headNumber);
    }

    public List getM2byHeadNumber(final String headNumber) {
        return tawpartMscidDao.getM2byHeadNumber(headNumber);
    }

    public List getTawpartMscidsbyHeadnumber(final String headNumber) {
        return tawpartMscidDao.getTawpartMscidsbyHeadnumber(headNumber);
    }

    public List getIDbyHeadNumber(final String headNumber) {
        return tawpartMscidDao.getIDbyHeadnumber(headNumber);
    }

    public void removeTawpartMscid(final String headNumber,
                                   final String numberM0, final String numberM1, final String numberM2) {
        tawpartMscidDao.removeTawpartMscid(headNumber, numberM0, numberM1,
                numberM2);
    }
}