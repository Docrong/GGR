package com.boco.eoms.infmanage.bo;

import java.sql.*;
import javax.sql.*;
import java.io.*;
import com.boco.eoms.common.bo.BO;

import com.boco.eoms.infmanage.controller.*;
import com.boco.eoms.common.util.StaticMethod;

public class TawInfAppuBO
    extends BO
{
    public TawInfAppuBO(com.boco.eoms.db.util.ConnectionPool ds)
    {
        super(ds);
    }

    public String getQueryCondition(TawInfInfoForm form, String sdomIds)
    {
        String condition = "";
        boolean markWhere = true;

        // taw_inf_info a,taw_inf_sort b,taw_rm_user c
        condition =
            " where a.inf_sort_id=b.inf_sort_id and a.inf_up_id=c.userid and a.dept_id=d.deptid";


        // ��¼ID
        /*if (form.getId() != 0)
        {
            if (!markWhere)
            {
                condition = condition + " Where a.id= " +
                    form.getId();
                markWhere = true;
            }
            else
            {
                condition = condition + " AND a.id= " + form.getId();
            }
        }*/

        // ����������
        /*if (!StaticMethod.null2String(form.getInfSortName()).trim().equals(""))
        {
            if (!markWhere)
            {
                condition = condition + " Where b.inf_sort_name LIKE '%" +
                    form.getInfSortName().trim() + "%'";
                markWhere = true;
            }
            else
            {
                condition = condition + " And b.inf_sort_name LIKE '%" +
                    form.getInfSortName().trim() + "%'";
            }
        }*/

        // ���ϱ��
        if (!StaticMethod.null2String(form.getInfInfoId()).trim().equals(
            ""))
        {
            if (!markWhere)
            {
                condition = condition + " Where a.inf_info_id LIKE '%" +
                    form.getInfInfoId().trim() + "%'";
                markWhere = true;
            }
            else
            {
                condition = condition + " AND a.inf_info_id LIKE '%" +
                    form.getInfInfoId().trim() + "%'";
            }
        }

        // �������
        if (!StaticMethod.null2String(form.getInfInfoName()).trim().equals(
            ""))
        {
            if (!markWhere)
            {
                condition = condition + " where a.inf_info_name LIKE '%" +
                    form.getInfInfoName().trim() + "%'";
                markWhere = true;
            }
            else
            {
                condition = condition + " AND a.inf_info_name LIKE '%" +
                    form.getInfInfoName().trim() + "%'";
            }
        }

        // ����ID
        /*if (form.getDeptId() != 0)
        {
            if (!markWhere)
            {
                condition = condition + " where a.dept_id=" + form.getDeptId();
                markWhere = true;
            }
            else
            {
                condition = condition + " AND a.dept_id=" + form.getDeptId();
            }
        }*/

        // �û�IP��ַ
        if (!StaticMethod.null2String(form.getInfUpName()).trim().equals(""))
        {
            if (!markWhere)
            {
                condition = condition + " where c.user_name LIKE '%" +
                    form.getInfUpName().trim() + "%'";
                markWhere = true;
            }
            else
            {
                condition = condition + " AND c.user_name LIKE '%" +
                    form.getInfUpName().trim() + "%'";
            }
        }

        //
        /*if (!sdomIds.equals(""))
        {
            if (!markWhere)
            {
                condition = condition + " where a.dept_id in (" + sdomIds +
                    ") ";
                markWhere = true;
            }
            else
            {
                condition = condition + " AND a.dept_id in (" + sdomIds + ") ";
            }
        }*/

        return condition;
    }
}