package com.boco.eoms.infmanage.bo;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.*;
import org.apache.struts.util.*;

import com.boco.eoms.common.bo.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.infmanage.model.*;
import com.boco.eoms.infmanage.controller.*;
import com.boco.eoms.infmanage.dao.*;

public class TawInfGroupBO
        extends BO {

    /**
     * @param ds DataSource 数据源（由Struts框架所提供）
     * @see 构造方法
     */
    public TawInfGroupBO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    /**
     * @param offset     int  从该条记录数显示
     * @param length     int  每页的记录个数
     * @param tawAPMform TawMemoForm  包含查询信息的Form
     * @return List类型值。
     * @throws SQLException
     * @see 根据参数查询信息，返回符合条件的信息集合！
     */

    public List getlist(int offset, int length, String con) throws SQLException {
        TawInfGroup taw = null;
        List tawAPMs = null;
        TawInfGroupDAO tawInfGroupDAO = null;

        try {
            tawInfGroupDAO = new TawInfGroupDAO(ds);
            tawAPMs =
                    tawInfGroupDAO.selectByCondition(offset, length, con);
        } catch (SQLException e) {
        } finally {
            taw = null;
            tawInfGroupDAO = null;
        }

        return tawAPMs;
    }

    /**
     * @param tawAPM TawMemoForm  包含查询信息的Form
     * @return String类型值。
     * @throws SQLException
     * @see 将参数查询信息拼成SQL语句中的"WHERE ..."查询条件！
     */

    public String selectByConditions(TawInfGroupForm form, String sdomIds) throws
            SQLException {

        String condition = "";
        Vector vTemp = new Vector();
        int groupId = form.getGroupId();
        String groupName = StaticMethod.null2String(form.getGroupName());
        String userId = StaticMethod.null2String(form.getUserId());

        try {
            condition =
                    " WHERE ";

            if (!sdomIds.equals("")) {
                vTemp.add(" dept_id in (" + sdomIds +
                        ") ");
            }
            if (groupId != 0) {
                vTemp.add(" group_id = " + groupId + "");
            }
            if (!groupName.trim().equals("")) {
                vTemp.add(" group_name like  " + "\'%" + groupName +
                        "%\'  ");
            }
            if (!userId.trim().equals("")) {
                vTemp.add(" user_id like  " + "\'%" + userId +
                        "%\'  ");
            }

            for (int i = 0; i < vTemp.size(); i++) {
                condition += vTemp.get(i).toString() + "   and ";
            }
            condition = condition.substring(0, (condition.length() - 5));

        } catch (Exception e) {
        } finally {
            vTemp = null;
            userId = null;
            groupName = null;
        }
        return condition;
    }
}
