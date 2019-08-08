package com.boco.eoms.sparepart.bo;

import java.util.List;
import java.sql.SQLException;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.dao.TawClassMsgDAO;
import com.boco.eoms.sparepart.controller.TawClassMsgForm;
import com.boco.eoms.sparepart.dao.TawCommonDAO;
import com.boco.eoms.sparepart.model.TawClassMsg;
import com.boco.eoms.sparepart.controller.TawSparepartForm;
import com.boco.eoms.sparepart.util.TawClassMsgTree;
import com.boco.eoms.sparepart.util.*;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 *
 * @author HAO
 * @version 2.0
 */

public class TawClassMsgBO extends BO {
    public TawClassMsgBO(ConnectionPool ds) {
        super(ds);
    }

    public TawClassMsgBO(ConnectionPool ds, String str) {
        super(ds, str);
    }

    /**
     * @return
     * @see 用于显示一级类型信息
     */
    public List getType() {
        String sql = " where parent_id=1";
        List list = null;
        TawClassMsgDAO dao = new TawClassMsgDAO(ds);
        list = dao.getClassMsg(sql);
        return list;
    }

    /**
     * @param form
     * @return
     * @see 根据id返回指定的类型对象。
     */
    public TawClassMsg getEditClass(TawClassMsgForm form) {
        TawClassMsg tawClassMsg = new TawClassMsg();
        String sql = " where id='" + form.getId() + "'";
        List list = null;
        TawClassMsgDAO dao = new TawClassMsgDAO(ds);
        list = dao.getClassMsg(sql);
        tawClassMsg = (TawClassMsg) list.get(0);
        //在树形维护中，不维护备件名称
        int parentId = tawClassMsg.getParentId();
        //专业id
        if (parentId == 41 || parentId == 42 || parentId == 43) {
            tawClassMsg.setDeleted(3);
        } else {
            tawClassMsg.setDeleted(0);
        }
        return tawClassMsg;
    }

    /**
     * @param form
     * @return
     * @see 2.0两级类型维护，如果写入了cname就insert一条类型信息。
     * 当parentId不唯空就显示它的子类型信息。
     */
    public List getMsg(String parentId) {
        List list = null;
        TawClassMsgDAO dao = new TawClassMsgDAO(ds);
        String sql = " where parent_id=" + parentId;

        list = dao.getClassMsg(sql);

        return list;
    }

    public List getCnameMsg(String cname, String parentId, String note) {
        List list = null;

        TawClassMsgDAO dao = new TawClassMsgDAO(ds);
        /**
         * @see树性结构注释掉 if(! cname.equals ( " ")){
        try{
        dao.insertClassMsg(cname,note,parentId);
        }
        catch(SQLException ex1){
        ex1.printStackTrace();
        }
        }
         */
        //if(cname.equals("")||cname==null){
        String sql = " where parent_id=" + parentId + " and cname like '%" + cname +
                "%'";
        if (note != null) {
            sql = " where parent_id=" + parentId + " and (cname like '%" + cname +
                    "%' or note like '%" + note + "%')";
        }

        list = dao.getClassMsg(sql);

        return list;
    }

    /**
     * @param cname
     * @return
     * @see 校验类型名称是否存在重名。
     */

    public boolean checkClassName(String cname) {
        boolean flag = true;
        TawCommonDAO dao = new TawCommonDAO(ds);

        flag = dao.checkName("taw_sp_classmsg", "cname", cname);
        return flag;
    }

    /**
     * @param form
     * @return
     * @see 将form传递给DAO，删除数据。3
     */

    public String deleteClass(String id) {
        TawClassMsgDAO dao = new TawClassMsgDAO(ds);
        dao.deleteClassMsg(id);

        return StaticPart.DELETE_CLASSMSG;
    }

    /**
     * @param form
     * @return
     * @see 将form传递给DAO，修改数据。
     */

    public String updateClass(TawClassMsgForm form) {
        TawClassMsgDAO dao = new TawClassMsgDAO(ds);

        dao.updateClassMsg(form);

        return StaticPart.UPDATE_CLASSMSG;
    }

    /**
     * @param form
     * @return
     * @see 将form传递给DAO，插入数据。不能重复
     */
    public String insertClass(String cname, String note, String parentId) throws
            SQLException {
        TawCommonDAO tawCommonDAO = new TawCommonDAO(ds);

        TawClassMsgDAO dao = new TawClassMsgDAO(ds);

        if (tawCommonDAO.checkName("taw_sp_classmsg", "cname", cname)) {
            dao.insertClassMsg(cname, note, parentId, "0");
            return StaticPart.ADD_CLASSMSG;
        } else {
            return StaticPart.CLASS_NAME_HAS;
        }

    }

    public String insertClass(String cname, String note, String parentId,
                              String deleted) throws
            SQLException {
        TawCommonDAO tawCommonDAO = new TawCommonDAO(ds);

        TawClassMsgDAO dao = new TawClassMsgDAO(ds);

        if (tawCommonDAO.checkName("taw_sp_classmsg", "cname", cname)) {
            dao.insertClassMsg(cname, note, parentId, deleted);
            return StaticPart.ADD_CLASSMSG;
        } else {
            return StaticPart.CLASS_NAME_HAS;
        }
    }

    /**
     * @param id
     * @return
     * @version 选择已经选中的类型
     */
    public TawClassMsg getTypeObject(String id) {
        String sql = " where id=" + id;
        List list = null;
        TawClassMsgDAO dao = new TawClassMsgDAO(ds);

        list = dao.getClassMsg(sql);

        TawClassMsg object = (TawClassMsg) list.get(0);
        return object;
    }

    public String getClassNecodeId(String department, String necode) {
        String id = "";
        TawClassMsgDAO tawClassMsgDAO = new TawClassMsgDAO(ds);
        id = tawClassMsgDAO.getTwoClassMsgId(department, necode);
        int i = id.length();
        int s = id.indexOf("_");
        String ss = id.substring(0, s);
        String ms = id.substring(s + 1, i);

        return ms;
    }

    public String getNameSql(TawClassMsgForm form) {
        String necode = getClassNecodeId(form.getDepartment(),
                form.getNecode());
        form.setId(necode);
        String condition = " where parent_id=" + necode;
        if (!form.getObjectname().equals("")) {
            condition = condition + " and cname like '" + form.getObjectname() + "'";
        }
        if (!form.getNote().equals("")) {
            condition = condition + " and note like '" + form.getNote() + "'";
        }
        return condition;
    }

    public boolean checkSpName() {
        boolean flag = false;
        TawClassMsgTree dao = new TawClassMsgTree();
        try {
            String str = dao.dong("2");
        } catch (Exception ex) {
            ex.printStackTrace();
            flag = true;
        }
        return flag;
    }

}
