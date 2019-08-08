package com.boco.eoms.commons.statistic.base.mgr.impl;

import com.boco.eoms.commons.statistic.base.exception.Id2NameDAOException;
import com.boco.eoms.commons.statistic.base.exception.Id2NameStatException;
import com.boco.eoms.commons.statistic.base.mgr.IStatID2Name;
import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.commons.statistic.base.util.Constants;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;

/**
 * @author lizhenyou
 */
public class StatID2NameV35Impl implements IStatID2Name {

    private String ID2NameV35BeanId;
    //= "tawSystemUserDao";

    public String id2Name(String id) throws Id2NameStatException {

        ID2NameDAO dao = (ID2NameDAO) ApplicationContextHolder.getInstance().getBean(ID2NameV35BeanId);

        String reString = Constants.ID_NO_NAME;
        try {
            reString = dao.id2Name(id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return reString;

    }

    public String idType2Name(String id, String type)
            throws Id2NameDAOException {
        return null;
    }

    public String getID2NameV35BeanId() {
        return ID2NameV35BeanId;
    }

    public void setID2NameV35BeanId(String nameV35BeanId) {
        ID2NameV35BeanId = nameV35BeanId;
    }


}
