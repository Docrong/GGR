package com.boco.eoms.infmanage.bo;

import java.util.*;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.infmanage.controller.*;
import com.boco.eoms.infmanage.dao.*;
import com.boco.eoms.common.util.StaticMethod;

public class ProjectBO
        extends BO {
    public static String querysql = "";
    public static String sizesql = "";

    public ProjectBO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    /**
     * 子任务列表
     *
     * @param offset int
     * @param length int
     * @return List
     */
    public List getTasklist(int offset, int length, int parent_id) {
        String sql =
                "select a.*,b.user_name,c.project_name,c.project_send_user,c.check_project_group  from taw_project_taskinfo a," +
                        " taw_rm_user b,taw_project_info c where a.task_executor =b.user_id and c.project_id=a.parent_id and a.task_audit_flag=0 and  parent_id = " +
                        parent_id + " order by a.task_number ";
        ProjectDAO projectDAO = new ProjectDAO(ds);
        ArrayList list = (ArrayList) projectDAO.getTasklist(sql, offset, length);
        return list;
    }

    /**
     * 子任务列表
     *
     * @param offset int
     * @param length int
     * @return List
     */
    public List getQueryTasklist(int offset, int length, int parent_id) {
        String sql =
                "select a.*,b.user_name,c.project_name,c.project_send_user,c.check_project_group   from taw_project_taskinfo a," +
                        " taw_rm_user b,taw_project_info c where a.task_executor =b.user_id and c.project_id=a.parent_id and  parent_id = " +
                        parent_id + " order by a.task_number ";
        ProjectDAO projectDAO = new ProjectDAO(ds);
        ArrayList list = (ArrayList) projectDAO.getTasklist(sql, offset, length);
        return list;
    }


    public List getMyTaskList(int offset, int length, String user_id) {
        String sql =
                "select a.*,b.user_name,c.project_name,c.project_send_user,c.check_project_group  from taw_project_taskinfo a," +
                        " taw_rm_user b ,taw_project_info c where a.task_executor =b.user_id and c.project_id=a.parent_id and a.task_audit_flag=0 and  a.task_executor ='" +
                        user_id + "' order by a.task_exec_time ";
        ProjectDAO projectDAO = new ProjectDAO(ds);
        ArrayList list = (ArrayList) projectDAO.getTasklist(sql, offset, length);
        return list;
    }

    public List getMyTaskList1(int offset, int length, String user_id, String userName, int task_audit_flag, int local) {
        String sql =
                "select a.*,b.user_name,c.project_name,c.project_send_user,c.check_project_group  from taw_project_taskinfo a," +
                        " taw_rm_user b ,taw_project_info c where a.task_executor =b.user_id " +
                        "  and c.project_id=a.parent_id  and ((','||c.check_project_group||',') like '%," +
                        StaticMethod.dbNStrRev(userName.trim()) +
                        ",%' or c.project_executor ='" + user_id + "' ) and a.task_sign=100  and a.task_audit_flag=" + task_audit_flag + "  order by a.task_exec_time ";


        if (local == 1) {
            sql =
                    "select a.*,b.user_name,c.project_name ,c.project_send_user,c.check_project_group  from taw_project_taskinfo a," +
                            " taw_rm_user b ,taw_project_info c where a.task_executor =b.user_id " +
                            "  and c.project_id=a.parent_id  and  a.task_send_user ='" +
                            StaticMethod.dbNStrRev(userName.trim()) +
                            "'  and a.task_audit_flag=" + task_audit_flag + "  order by a.task_exec_time ";

        }
        ProjectDAO projectDAO = new ProjectDAO(ds);
        ArrayList list = (ArrayList) projectDAO.getTasklist(sql, offset, length);
        return list;
    }

    /**
     * @param offset int
     * @param length int
     * @return List
     */
    public List getList(int offset, int length) {
        String sql = "select a.*,b.user_name from taw_project_info a," +
                " taw_rm_user b where a.project_executor =b.user_id and a.pro_audit_flag=0 order by a.indata_time desc";
        ProjectDAO projectDAO = new ProjectDAO(ds);
        ArrayList list = (ArrayList) projectDAO.getList(sql, offset, length);
        return list;
    }

    public List getMyList(int offset, int length, String user_id) {
        String sql = "select a.*,b.user_name from taw_project_info a," +
                " taw_rm_user b where a.project_executor ='" +
                user_id + "' and  a.project_executor =b.user_id  and a.pro_audit_flag=0 order by a.indata_time desc";
        ProjectDAO projectDAO = new ProjectDAO(ds);
        ArrayList list = (ArrayList) projectDAO.getList(sql, offset, length);
        return list;
    }

    public List getMyList2(int offset, int length, String user_id, String userName,
                           int pro_audit_flag, int local) {
        String sql = "";

        if (local == 1)       //////子任务分配
        {
            sql = "select a.*,b.user_name from taw_project_info a," +
                    " taw_rm_user b where a.project_executor ='" +
                    user_id + "' and  a.project_executor =b.user_id   and a.pro_audit_flag=" +
                    pro_audit_flag + " order   by a.indata_time desc";
        } else if (local == 2) {  /////项目跟踪
            sql = "select a.*,b.user_name from taw_project_info a," +
                    " taw_rm_user b where (a.project_send_user ='" +
                    StaticMethod.dbNStrRev(userName.trim()) +
                    "'  or a.project_executor ='" + user_id + "' ) and  a.project_executor =b.user_id   and a.pro_audit_flag=" +
                    pro_audit_flag + " order   by a.indata_time desc";
        } else if (local == 0)  ///////项目考核
        {
            sql = "select a.*,b.user_name from taw_project_info a," +
                    " taw_rm_user b where (','||a.check_project_group||',') like '%," +
                    StaticMethod.dbNStrRev(userName.trim()) +
                    ",%' and  a.project_executor =b.user_id  and a.project_sign=100 and a.pro_audit_flag=" +
                    pro_audit_flag + " order   by a.indata_time desc";
        }

        ProjectDAO projectDAO = new ProjectDAO(ds);
        ArrayList list = (ArrayList) projectDAO.getList(sql, offset, length);
        return list;
    }

    public List getTrackList(int offset, int length, int pro_id, int task_id) {
        String sql = "";
        int flag = 0; /////////////// 区别项目和子 任务的跟踪列表
        if (pro_id != 0) {
            sql = "select a.*,c.user_name from taw_project_track a,taw_project_info b,taw_rm_user c" +
                    " where b.project_executor =c.user_id and a.parent_id=b.project_id " +
                    " and a.parent_id=" + pro_id +
                    " and  b.pro_audit_flag=0  and a.flag=1 order by  a.track_pro_sign ";

        }
        if (task_id != 0) {
            sql = " select a.*,b.task_desc,c.user_name from taw_project_track a, " +
                    "taw_project_taskinfo b,taw_rm_user c where  b.task_executor =c.user_id" +
                    " and a.task_id=" + task_id +
                    " and a.task_id =b.id  and b.task_audit_flag=0 order by  a.track_task_sign ";
            flag = 1;
        }
        ProjectDAO projectDAO = new ProjectDAO(ds);
        ArrayList list = (ArrayList) projectDAO.getTrackList(sql, offset, length,
                flag);
        return list;
    }

    public List getQueryTrackList(int offset, int length, int pro_id, int task_id) {
        String sql = "";
        int flag = 0; /////////////// 区别项目和子 任务的跟踪列表
        if (pro_id != 0) {
            sql = "select a.*,c.user_name from taw_project_track a,taw_project_info b,taw_rm_user c" +
                    " where b.project_executor =c.user_id and a.parent_id=b.project_id " +
                    " and a.parent_id=" + pro_id +
                    " and  b.pro_audit_flag=0  and a.flag=1 order by  a.track_pro_sign ";

        }
        if (task_id != 0) {
            sql = " select a.*,b.task_desc,c.user_name from taw_project_track a, " +
                    "taw_project_taskinfo b,taw_rm_user c where  b.task_executor =c.user_id" +
                    " and a.task_id=" + task_id +
                    " and a.task_id =b.id   order by  a.track_task_sign ";
            flag = 1;
        }
        ProjectDAO projectDAO = new ProjectDAO(ds);
        ArrayList list = (ArrayList) projectDAO.getTrackList(sql, offset, length,
                flag);
        return list;
    }


    public List getQueryList(ProjectForm projectForm, int offset, int length,
                             int flag, String user_id) {
        String sql = "select a.*,b.user_name from taw_project_info a," +
                " taw_rm_user b where 1=1  ";
        if (!StaticMethod.null2String(projectForm.getProject_name().trim()).equals(
                "") && projectForm.getProject_name() != null)
            sql += "and project_name like '%" + projectForm.getProject_name().trim() +
                    "%'";
        if (projectForm.getProject_code() != 0)
            sql += " and project_code=" + projectForm.getProject_code(); ///项目类别
        if (!StaticMethod.null2String(projectForm.getProject_exec_time()).trim().
                equals(""))
            sql += " and project_exec_time >='" +
                    projectForm.getProject_exec_time().trim() + "'"; ///项目开始时间
        if (!StaticMethod.null2String(projectForm.getProject_comp_time()).trim().
                equals(""))
            sql += " and project_comp_time <='" +
                    projectForm.getProject_comp_time().trim() + "'"; ///项目完成时间
        if (!StaticMethod.null2String(projectForm.getProject_desc().trim()).equals(
                "") && projectForm.getProject_desc() != null)
            sql += "and project_desc like '%" + projectForm.getProject_desc().trim() +
                    "%'";

        if (flag == 1) { ///子任务标志
            sql += "and  project_executor ='" + user_id.trim() + "'";
        } else {
            if (!StaticMethod.null2String(projectForm.getDept_id()).trim().equals(""))
                sql += " and a.dept_id = '" + projectForm.getDept_id().trim() + "'";
            if (!StaticMethod.null2String(projectForm.getProject_executor()).trim().
                    equals(""))
                sql += " and project_executor = '" +
                        projectForm.getProject_executor().trim() + "'";
        }
        //////////////进入进度上下限
        sql += "  and a.project_sign <=" + projectForm.getUp() +
                " and a.project_sign >=" + projectForm.getDown() +
                " and a.project_executor =b.user_id  order by a.indata_time desc";

        ProjectBO.querysql = sql;

        ProjectDAO projectDAO = new ProjectDAO(ds);
        ArrayList list = (ArrayList) projectDAO.getList(ProjectBO.querysql, offset,
                length);
        return list;
    }

    public int getSize() {
        String sql = "select count(*) from taw_project_info";
        ProjectDAO projectDAO = new ProjectDAO(ds);
        return projectDAO.getSize(sql);
    }

}
