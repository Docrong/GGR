package com.boco.eoms.infmanage.dao;

import java.sql.*;
import java.util.*;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.infmanage.model.*;
import com.boco.eoms.infmanage.controller.*;
import com.boco.eoms.common.util.StaticMethod;

public class ProjectDAO  extends DAO{
  public ProjectDAO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }
  public void update(Project project)throws SQLException
  {
      com.boco.eoms.db.util.BocoConnection conn = null;
      conn = ds.getConnection();
      conn.setAutoCommit(true);
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      String sql = "";
      sql = "update taw_project_info set project_name=?,"+
           "project_code=?,project_level=?,project_state=?,"+
           "project_instancy=?,project_exec_time=?,project_comp_time=?,"+
           "dept_id=?,dept_name=?,project_executor=?,project_desc=?,check_project_group=? where project_id=?";

      try
      {
          pstmt = conn.prepareStatement(sql);
          System.out.println(sql);

          pstmt.setString(1, project.getProject_name());
          pstmt.setInt(2, project.getProject_code());
          pstmt.setString(3,project.getProject_level());
          pstmt.setString(4, project.getProject_state());
          pstmt.setString(5, project.getProject_instancy());
          pstmt.setString(6, project.getProject_exec_time());
          pstmt.setString(7, project.getProject_comp_time());
          pstmt.setString(8,project.getDept_id());
          pstmt.setString(9,project.getDept_name());
          pstmt.setString(10,project.getProject_executor());
          pstmt.setString(11,project.getProject_desc());
          pstmt.setString(12,project.getCheck_project_group());
          pstmt.setInt(13,project.getProject_id());
          pstmt.executeUpdate();


      }
      catch (Exception sqle)
      {
        //  conn.rollback();
       //   sqle.printStackTrace();
      }
      finally
      {   close(pstmt);
          conn.setAutoCommit(false);
          close(conn);
      }
  }

  public void updateProCheck(Project project)throws SQLException
{
    com.boco.eoms.db.util.BocoConnection conn = null;
    conn = ds.getConnection();
    conn.setAutoCommit(true);
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String sql = "";
    sql = "update taw_project_info set pro_audit_flag=?,"+
         " pro_check_content=?,pro_audit=?  where project_id=?";

    try
    {
        pstmt = conn.prepareStatement(sql);
        System.out.println(sql);

        pstmt.setInt(1, 1);
        pstmt.setString(2, project.getPro_check_content());
        pstmt.setString(3,project.getPro_audit());
        pstmt.setInt(4, project.getProject_id());
        pstmt.executeUpdate();


    }
    catch (Exception sqle)
    {
      //  conn.rollback();
     //   sqle.printStackTrace();
    }
    finally
    {   close(pstmt);
        conn.setAutoCommit(false);
        close(conn);
    }
}
  public void updateTaskCheck(Project project)throws SQLException
{
    com.boco.eoms.db.util.BocoConnection conn = null;
    conn = ds.getConnection();
    conn.setAutoCommit(true);
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String sql = "";
    sql = "update taw_project_taskinfo set task_audit_flag=?,"+
         " task_check_content=?,task_audit=?  where id=?";

    try
    {
        pstmt = conn.prepareStatement(sql);
        System.out.println(sql);

        pstmt.setInt(1, 1);
        pstmt.setString(2, project.getTask_check_content());
        pstmt.setString(3,project.getTask_audit());
        pstmt.setInt(4, project.getId());
        pstmt.executeUpdate();


    }
    catch (Exception sqle)
    {
      //  conn.rollback();
     //   sqle.printStackTrace();
    }
    finally
    {   close(pstmt);
        conn.setAutoCommit(false);
        close(conn);
    }
}


  public void del(Project project)throws SQLException
   {
       com.boco.eoms.db.util.BocoConnection conn = null;
       conn = ds.getConnection();
       conn.setAutoCommit(true);
       PreparedStatement pstmt = null;
       ResultSet rs = null;

       String sql = "";
       sql = "delete from taw_project_info "
           + " where project_id=?";

       try
       {
           pstmt = conn.prepareStatement(sql);
           System.out.println(sql);
           pstmt.setInt(1, project.getProject_id());
           pstmt.executeUpdate();


       }
       catch (Exception sqle)
       {
         //  conn.rollback();
        //   sqle.printStackTrace();
       }
       finally
       {   close(pstmt);
           conn.setAutoCommit(false);
           close(conn);
       }
   }

   public void delTask(Project project)throws SQLException
 {
     com.boco.eoms.db.util.BocoConnection conn = null;
     conn = ds.getConnection();
     conn.setAutoCommit(true);
     PreparedStatement pstmt = null;
     ResultSet rs = null;

     String sql = "";
     sql = "delete from taw_project_taskinfo "
         + " where id=?";

     try
     {
         pstmt = conn.prepareStatement(sql);
         System.out.println(sql);
         pstmt.setInt(1, project.getId());
         pstmt.executeUpdate();
       }
     catch (Exception sqle)
     {
       //  conn.rollback();
      //   sqle.printStackTrace();
     }

     finally
     {   close(pstmt);
         conn.setAutoCommit(false);
         close(conn);
     }
     delTrack(project.getId());//////////////////////删除历史信息
     String parent_id = project.getParent_id();
     int[] pro = queryProjectInfo(parent_id);
     int project_scale = pro[0]-project.getTask_scale();
     int number=pro[1]-1;
     updateProject(parent_id,project_scale,number);

 }


  /**
   * 在项目信息表插入记录
   * @param
   * @throws SQLException
   */
  public void insert(Project project) throws SQLException
  {
      com.boco.eoms.db.util.BocoConnection conn = null;
      conn = ds.getConnection();
      conn.setAutoCommit(true);
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      String sql = "";
      String indata_time=StaticMethod.getCurrentDateTime();
      sql = "insert into taw_project_info"+
          "(project_code,project_level,project_instancy,"+
           "project_state,project_send_user,project_exec_time,"+
            "project_comp_time,pro_audit_flag,dept_id,dept_name,"+
            "project_executor,project_name,project_desc,project_sign,indata_time,project_scale,project_task_flag,check_project_group)"
           + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

      try
      {
          pstmt = conn.prepareStatement(sql);
          System.out.println(sql);

          pstmt.setInt(1, project.getProject_code());
          pstmt.setString(2, project.getProject_level());
          pstmt.setString(3, project.getProject_instancy());
          pstmt.setString(4,project.getProject_state());
          pstmt.setString(5,project.getProject_send_user());
          pstmt.setString(6, project.getProject_exec_time());
          pstmt.setString(7, project.getProject_comp_time());
          pstmt.setInt(8,0);
          pstmt.setString(9, project.getDept_id());
          pstmt.setString(10,project.getDept_name());
          pstmt.setString(11, project.getProject_executor());
          pstmt.setString(12, project.getProject_name());
          pstmt.setString(13, project.getProject_desc());
          pstmt.setInt(14, 0);
          pstmt.setString(15,indata_time);
          pstmt.setInt(16,0);
          pstmt.setInt(17,0);
          pstmt.setString(18,project.getCheck_project_group());
          pstmt.executeUpdate();

      }
      catch (Exception sqle)
      {
        //  conn.rollback();
       //   sqle.printStackTrace();
      }
      finally
      {
          close(pstmt);
          conn.setAutoCommit(false);
          close(conn);
      }

      int project_id = queryProjectId(indata_time);
      insertTrack(0,project_id,project.getProject_exec_time(),0,0,"",1);/////////插入历史信息
  }


  public Project getById(int pro_code,int pro_id){
    Project project = new Project();
    com.boco.eoms.db.util.BocoConnection conn = null;
    conn = ds.getConnection();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select a.*,b.user_name from taw_project_info a,"+
        "taw_rm_user b where a.project_id=? "+
        " and a.project_executor =b.user_id    order by a.project_id ";
         try
         {
           pstmt = conn.prepareStatement(sql);
           System.out.println(sql);
           pstmt.setInt(1, pro_id);
           rs = pstmt.executeQuery();
             while (rs.next())
             {

                        String sql1="select dict_name from taw_ws_dict  where dict_id=? and dict_type=?";
                        PreparedStatement pstmt1 = null;
                        ResultSet rs1 = null;
                        String code_name = "";
                        try{
                          pstmt1 = conn.prepareStatement(sql1);
                          System.out.println(sql1);
                          pstmt1.setInt(1, rs.getInt("project_code"));
                          pstmt1.setInt(2, pro_code);
                          rs1 = pstmt1.executeQuery();

                          if (rs1.next()) {
                            code_name = rs1.getString("dict_name");
                          }
                        }
                        catch (SQLException e) {
                          // e.printStackTrace();
                        }
                        finally {
                          close(rs1);
                          close(pstmt1);
                           close(conn);
                         }
                        project.setProject_code(rs.getInt("project_code"));
                        project.setProject_id(rs.getInt("project_id"));
                        project.setProject_code_name(StaticMethod.dbNull2String(code_name));//
                        project.setProject_level(StaticMethod.dbNull2String(rs.getString("project_level")));
                        project.setProject_instancy(StaticMethod.dbNull2String(rs.getString("project_instancy")));
                        project.setProject_state(StaticMethod.dbNull2String(rs.getString("project_state")));
                        project.setProject_send_user(StaticMethod.dbNull2String(rs.getString("project_send_user")));
                        project.setProject_exec_time(StaticMethod.dbNull2String(rs.getString("project_exec_time")).substring(0,19));
                        project.setProject_comp_time(StaticMethod.dbNull2String(rs.getString("project_comp_time")).substring(0,19));
                        project.setProject_plan(StaticMethod.dbNull2String(rs.getString("project_plan")));
                        project.setDept_id(StaticMethod.dbNull2String(rs.getString("dept_id")));
                        project.setDept_name(StaticMethod.dbNull2String(rs.getString("dept_name")));
                        project.setProject_executor(StaticMethod.dbNull2String(rs.getString("project_executor")));
                        project.setProject_executor_name(StaticMethod.dbNull2String(rs.getString("user_name")));
                        project.setProject_name(StaticMethod.dbNull2String(rs.getString("project_name")));
                        project.setProject_desc(StaticMethod.dbNull2String(rs.getString("project_desc")));
                        project.setProject_sign(rs.getInt("project_sign"));
                        project.setProject_scale(rs.getInt("project_scale"));
                        project.setProject_task_flag(rs.getInt("project_task_flag"));
                        project.setPro_audit(StaticMethod.dbNull2String(rs.getString("pro_audit")));
                        project.setPro_check_content(StaticMethod.dbNull2String(rs.getString("pro_check_content")));
                        project.setCheck_project_group(StaticMethod.dbNull2String(rs.getString("check_project_group")));
             }
         }
         catch (SQLException e)
         {
            // e.printStackTrace();
         }
         finally
         {
             close(rs);
             close(pstmt);
             close(conn);
         }
         return project;
  }

  public List getList(String condition,int offset, int length){
       ArrayList list=new ArrayList();
       com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            pstmt = conn.prepareStatement(condition);
            System.out.println(condition);
            rs = pstmt.executeQuery();
            if (offset > 0)
            {
                int i = 0;
                while (i++ < offset)
                {
                    rs.next();
                }
            }

            int recCount = 0;
            while ( (recCount++ < length) && rs.next())
            {
                Project project = new Project();
                project.setProject_id(rs.getInt("project_id"));
                project.setProject_code(rs.getInt("project_code"));
                project.setProject_level(StaticMethod.dbNull2String(rs.getString("project_level")));
                project.setProject_instancy(StaticMethod.dbNull2String(rs.getString("project_instancy")));
                project.setProject_state(StaticMethod.dbNull2String(rs.getString("project_state")));
                project.setProject_send_user(StaticMethod.dbNull2String(rs.getString("project_send_user")));
                project.setProject_exec_time(StaticMethod.dbNull2String(rs.getString("project_exec_time")).substring(0,10));
                project.setProject_comp_time(StaticMethod.dbNull2String(rs.getString("project_comp_time")).substring(0,10));
                project.setProject_plan(StaticMethod.dbNull2String(rs.getString("project_plan")));
                project.setDept_id(StaticMethod.dbNull2String(rs.getString("dept_id")));
                project.setDept_name(StaticMethod.dbNull2String(rs.getString("dept_name")));
                project.setProject_executor_name(StaticMethod.dbNull2String(rs.getString("user_name")));
                project.setProject_executor(StaticMethod.dbNull2String(rs.getString("project_executor")));
                project.setProject_name(StaticMethod.dbNull2String(rs.getString("project_name")));
                project.setProject_desc(StaticMethod.dbNull2String(rs.getString("project_desc")));
                project.setProject_sign(rs.getInt("project_sign"));
                project.setProject_scale(rs.getInt("project_scale"));
                project.setProject_task_flag(rs.getInt("project_task_flag"));
                project.setPro_audit(StaticMethod.dbNull2String(rs.getString("pro_audit")));
                project.setCheck_project_group(StaticMethod.dbNull2String(rs.getString("check_project_group")));
                list.add(project);
            }
        }
        catch (SQLException e)
        {
           // e.printStackTrace();
        }
        finally
        {
            close(rs);
            close(pstmt);
            close(conn);
        }
        return list;
  }

  public int getSize(String condition){
        int size=0;
        com.boco.eoms.db.util.BocoConnection conn = null;
         conn = ds.getConnection();
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         try
         {
             pstmt = conn.prepareStatement(condition);
             System.out.println(condition);
             rs = pstmt.executeQuery();
             rs.next();
            size = rs.getInt(1);

           }
         catch (SQLException e)
         {
            // e.printStackTrace();
         }
         finally
         {
           if (rs != null)
          {
              rs = null;
          }
          if (pstmt != null)
          {
              pstmt = null;
          }
          if (conn != null)
          {
              conn = null;
          }

         }
         return size;
   }
   /**
    * 在项目信息表插入记录
    * @param
    * @throws SQLException
    */
   public void inserttask(Project project) throws SQLException
   {
       com.boco.eoms.db.util.BocoConnection conn = null;
       conn = ds.getConnection();
       conn.setAutoCommit(true);
       PreparedStatement pstmt = null;
       ResultSet rs = null;

       String sql = "";

       int number=queryTaskNumber(project.getParent_id())+1;

       String task_indata_time=StaticMethod.getCurrentDateTime();
       sql = "insert  into taw_project_taskinfo"+
           "(parent_id,task_number,task_name,"+
            "task_send_user,task_exec_time,"+
             "task_comp_time,task_dept_id,task_dept_name,"+
             "task_executor,task_scale,task_remark,task_sign,task_indata_time,task_audit_flag)"
            + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

       try
       {
           pstmt = conn.prepareStatement(sql);
           System.out.println(sql);
           pstmt.setString(1,project.getParent_id());
           pstmt.setInt(2, number);
           pstmt.setString(3,project.getTask_name());
           pstmt.setString(4,project.getTask_send_user());
           pstmt.setString(5,project.getTask_exec_time());
           pstmt.setString(6, project.getTask_comp_time());
           pstmt.setString(7, project.getTask_dept_id());
           pstmt.setString(8, project.getTask_dept_name());
           pstmt.setString(9, project.getTask_executor());
           pstmt.setInt(10,project.getTask_scale());
           pstmt.setString(11, project.getTask_remark());
           pstmt.setInt(12, 0);
           pstmt.setString(13, task_indata_time);
           pstmt.setInt(14, 0);
           pstmt.executeUpdate();

       }
       catch (Exception sqle)
       {
         //  conn.rollback();
        //   sqle.printStackTrace();
       }
       finally
       {
           close(pstmt);
           conn.setAutoCommit(false);
           close(conn);
       }
       int[] pro_temp= queryProjectInfo(project.getParent_id());
       int scale= pro_temp[0]+project.getTask_scale();
       updateProject(project.getParent_id(),scale,number);

       int task_id = queryTaskId(task_indata_time);
       insertTrack(task_id,Integer.parseInt(project.getParent_id()), task_indata_time,0,0,"",0);/////////插入子任务历史信息

   }
   public List getTasklist(String condition,int offset, int length){
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
         conn = ds.getConnection();
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         try
         {
             pstmt = conn.prepareStatement(condition);
             System.out.println(condition);
             rs = pstmt.executeQuery();
             if (offset > 0)
             {
                 int i = 0;
                 while (i++ < offset)
                 {
                     rs.next();
                 }
             }

             int recCount = 0;
             while ( (recCount++ < length) && rs.next())
             {
                 Project project = new Project();
                 project.setId(rs.getInt("Id"));
                 project.setProject_send_user(StaticMethod.dbNull2String(rs.getString("project_send_user")));
                 project.setParent_id(rs.getString("parent_id"));
                 project.setTask_name(StaticMethod.dbNull2String(rs.getString("task_name")));
                 project.setTask_number(rs.getInt("task_number"));
                 project.setTask_send_user(StaticMethod.dbNull2String(rs.getString("task_send_user")));
                 project.setTask_exec_time(StaticMethod.dbNull2String(rs.getString("task_exec_time")).substring(0,10));
                 project.setTask_comp_time(StaticMethod.dbNull2String(rs.getString("task_comp_time")).substring(0,10));
                 project.setTask_dept_id(StaticMethod.dbNull2String(rs.getString("task_dept_id")));
                 project.setTask_dept_name(StaticMethod.dbNull2String(rs.getString("task_dept_name")));
                 project.setTask_executor_name(StaticMethod.dbNull2String(rs.getString("user_name")));
                 project.setTask_executor(StaticMethod.dbNull2String(rs.getString("task_executor")));
                 project.setTask_remark(StaticMethod.dbNull2String(rs.getString("task_remark")));
                 project.setTask_sign(rs.getInt("task_sign"));
                 project.setTask_scale(rs.getInt("task_scale"));
                 project.setProject_name(StaticMethod.dbNull2String(rs.getString("project_name")));
                 project.setTask_audit(StaticMethod.dbNull2String(rs.getString("task_audit")));
                 project.setCheck_project_group(StaticMethod.dbNull2String(rs.getString("check_project_group")));
                 list.add(project);
             }
         }
         catch (SQLException e)
         {
            // e.printStackTrace();
         }
         finally
         {
             close(rs);
             close(pstmt);
             close(conn);
         }
         return list;
   }
   public Project getTaskById(int id){
       Project project = new Project();
       com.boco.eoms.db.util.BocoConnection conn = null;
       conn = ds.getConnection();
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       String sql = "select a.*,b.user_name,c.project_name,c.project_scale,c.project_exec_time,c.project_comp_time from taw_project_taskinfo a,"+
           "taw_rm_user b,taw_project_info c where a.task_executor =b.user_id "+
           " and c.project_id=a.parent_id  and a.id=?";
            try
            {
              pstmt = conn.prepareStatement(sql);
              System.out.println(sql);
              pstmt.setInt(1, id);
              rs = pstmt.executeQuery();
                while (rs.next())
                {
                  project.setId(rs.getInt("Id"));
                  project.setParent_id(rs.getString("parent_id"));
                  project.setTask_name(StaticMethod.dbNull2String(rs.getString(
                      "task_name")));
                  project.setTask_number(rs.getInt("task_number"));
                  project.setTask_send_user(StaticMethod.dbNull2String(rs.getString(
                      "task_send_user")));
                  project.setTask_exec_time(StaticMethod.dbNull2String(rs.getString(
                      "task_exec_time")).substring(0, 19));
                  project.setTask_comp_time(StaticMethod.dbNull2String(rs.getString(
                      "task_comp_time")).substring(0, 19));
                  project.setTask_dept_id(StaticMethod.dbNull2String(rs.getString(
                      "task_dept_id")));
                  project.setTask_dept_name(StaticMethod.dbNull2String(rs.getString(
                      "task_dept_name")));
                  project.setTask_executor_name(StaticMethod.dbNull2String(rs.getString(
                      "user_name")));
                  project.setTask_executor(StaticMethod.dbNull2String(rs.getString(
                      "task_executor")));
                  project.setTask_remark(StaticMethod.dbNull2String(rs.getString(
                      "task_remark")));
                  project.setTask_desc(StaticMethod.dbNull2String(rs.getString(
                      "task_desc")));
                  project.setTask_sign(rs.getInt("task_sign"));
                  project.setTask_scale(rs.getInt("task_scale"));
                  project.setTask_audit(StaticMethod.dbNull2String(rs.getString("task_audit")));
                  project.setTask_check_content(StaticMethod.dbNull2String(rs.getString("task_check_content")));
                  project.setProject_name(StaticMethod.dbNull2String(rs.getString("project_name")));
                  project.setProject_exec_time(StaticMethod.dbNull2String(rs.getString("project_exec_time")));
                  project.setProject_comp_time(StaticMethod.dbNull2String(rs.getString("project_comp_time")));
                  project.setProject_scale((rs.getInt("project_scale")));

                }
            }
            catch (SQLException e)
            {
               // e.printStackTrace();
            }
            finally
            {
                close(rs);
                close(pstmt);
                close(conn);
            }
            return project;
     }
     public void updateTask(Project project)throws SQLException
      {
          com.boco.eoms.db.util.BocoConnection conn = null;
          conn = ds.getConnection();
          conn.setAutoCommit(true);
          PreparedStatement pstmt = null;
          ResultSet rs = null;

          String sql = "";
          int[] task_scale_old= queryTaskInfo(project.getId());

          sql = "update taw_project_taskinfo set task_name=?,"+
               "task_exec_time=?,task_comp_time=?,task_dept_id=?,"+
               "task_dept_name=?,task_executor=?,task_scale=?,task_remark=? where id=?";

          try
          {
              pstmt = conn.prepareStatement(sql);
              System.out.println(sql);

              pstmt.setString(1, project.getTask_name());
              pstmt.setString(2, project.getTask_exec_time());
              pstmt.setString(3, project.getTask_comp_time());
              pstmt.setString(4,project.getTask_dept_id());
              pstmt.setString(5,project.getTask_dept_name());
              pstmt.setString(6,project.getTask_executor());
              pstmt.setInt(7,project.getTask_scale());
              pstmt.setString(8,project.getTask_remark());
              pstmt.setInt(9,project.getId());
              pstmt.executeUpdate();
          }
          catch (Exception sqle)
          {
            //  conn.rollback();
           //   sqle.printStackTrace();
          }
          finally
          {   close(pstmt);
              conn.setAutoCommit(false);
              close(conn);
          }
       int[] pro_temp = queryProjectInfo(project.getParent_id());       ///////原来项目的scale
       int project_scale= pro_temp[0]+ (project.getTask_scale()-task_scale_old[0]);/////需要插入项目的scale
       updateProject(project.getParent_id(),project_scale,-1);
      }

      public void updateTaskSign(Project project)throws SQLException
 {
     com.boco.eoms.db.util.BocoConnection conn = null;
     conn = ds.getConnection();
     conn.setAutoCommit(true);
     PreparedStatement pstmt = null;
     ResultSet rs = null;
      int [] task_sign =queryTaskInfo(project.getId());

     String sql = "";
       sql = "update taw_project_taskinfo set task_sign=?,task_desc=? where id=?";

     try
     {
         pstmt = conn.prepareStatement(sql);
         System.out.println(sql);

         pstmt.setInt(1,project.getTask_sign());
         pstmt.setString(2,project.getTask_desc());
         pstmt.setInt(3,project.getId());
         pstmt.executeUpdate();
     }
     catch (Exception sqle)
     {
       //  conn.rollback();
      //   sqle.printStackTrace();
     }
     finally
     {   close(pstmt);
         conn.setAutoCommit(false);
         close(conn);
     }
     int [] temp = queryProjectInfo(project.getParent_id());
     int project_sign= temp[2]+(project.getTask_sign()-task_sign[1])*(project.getTask_scale())/100;
     updateProjectSign(project.getParent_id(),project_sign);

     //插入历史信息
     insertTrack(project.getId(),Integer.parseInt(project.getParent_id()),
              StaticMethod.getCurrentDateTime(),project_sign,project.getTask_sign(),project.getTask_desc(),1);
 }

  private  int[] queryProjectInfo(String parent_id)throws SQLException{
    com.boco.eoms.db.util.BocoConnection conn = null;
       conn = ds.getConnection();
       conn.setAutoCommit(true);
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       String sql = "";
       int[] pro_temp = new int[3];

       try
       {
         sql= "select project_scale,project_task_flag,project_sign from taw_project_info where project_id="+
             Integer.parseInt(parent_id);
         pstmt = conn.prepareStatement(sql);
         System.out.println(sql);
         rs = pstmt.executeQuery();
         rs.next();
         pro_temp[0]=rs.getInt(1);
         pro_temp[1]=rs.getInt(2);
         pro_temp[2]=rs.getInt(3);

      }
      catch (Exception sqle)
      {

      }
      finally
      {
          close(pstmt);
          conn.setAutoCommit(false);
          close(conn);
      }
      return pro_temp;

  }

  private  int queryProjectId(String exec_time)throws SQLException{
  com.boco.eoms.db.util.BocoConnection conn = null;
     conn = ds.getConnection();
     conn.setAutoCommit(true);
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     String sql = "";
     int  project_id=0;
    try
     {
       sql= "select project_id from taw_project_info where indata_time='"+
           exec_time+"'";
       pstmt = conn.prepareStatement(sql);
       System.out.println(sql);
       rs = pstmt.executeQuery();
       rs.next();
       project_id = rs.getInt("project_id");

    }
    catch (Exception sqle)
    {

    }
    finally
    {
        close(pstmt);
        conn.setAutoCommit(false);
        close(conn);
    }
    return project_id;

}
  private  int queryTaskId(String exec_time)throws SQLException{
com.boco.eoms.db.util.BocoConnection conn = null;
   conn = ds.getConnection();
   conn.setAutoCommit(true);
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   String sql = "";
   int  task_id=0;
  try
   {
     sql= "select id from taw_project_taskinfo where task_indata_time='"+
         exec_time+"'";
     pstmt = conn.prepareStatement(sql);
     System.out.println(sql);
     rs = pstmt.executeQuery();
     rs.next();
     task_id = rs.getInt("id");

  }
  catch (Exception sqle)
  {

  }
  finally
  {
      close(pstmt);
      conn.setAutoCommit(false);
      close(conn);
  }
  return task_id;

}


  private void updateProject(String parent_id,int scale,int task_number)throws SQLException{

    com.boco.eoms.db.util.BocoConnection conn = null;
       conn = ds.getConnection();
       conn.setAutoCommit(true);
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       String sql = "";

    try
       {
         if (task_number == -1) {
           sql = "update taw_project_info set project_scale=? where project_id=?";
           pstmt = conn.prepareStatement(sql);
           System.out.println(sql);
           pstmt.setInt(1, scale);
           pstmt.setInt(2, Integer.parseInt(parent_id));

         }
         else {
           sql = "update taw_project_info set project_scale=?,project_task_flag=? where project_id=?";
           pstmt = conn.prepareStatement(sql);
           System.out.println(sql);
           pstmt.setInt(1, scale);
           pstmt.setInt(2, task_number);
            pstmt.setInt(3, Integer.parseInt(parent_id));
         }
          pstmt.executeUpdate();
        }
      catch (Exception sqle)
      {
        //  conn.rollback();
       //   sqle.printStackTrace();
      }
      finally
      {
          close(pstmt);
          conn.setAutoCommit(false);
          close(conn);
      }

  }
  private void updateProjectSign(String project_id,int project_sign)throws SQLException{

   com.boco.eoms.db.util.BocoConnection conn = null;
      conn = ds.getConnection();
      conn.setAutoCommit(true);
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "";

   try
      {

          sql = "update taw_project_info set project_sign=? where project_id=?";
          pstmt = conn.prepareStatement(sql);
          System.out.println(sql);
          pstmt.setInt(1, project_sign);
          pstmt.setInt(2, Integer.parseInt(project_id));

         pstmt.executeUpdate();
       }
     catch (Exception sqle)
     {
       //  conn.rollback();
      //   sqle.printStackTrace();
     }
     finally
     {
         close(pstmt);
         conn.setAutoCommit(false);
         close(conn);
     }

 }


  private  int[] queryTaskInfo(int id)throws SQLException{
     com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        int [] task_info= new int[2];
         try
        {
          sql= "select task_scale,task_sign from taw_project_taskinfo where id="+
              id;
          pstmt = conn.prepareStatement(sql);
          System.out.println(sql);
          rs = pstmt.executeQuery();
          rs.next();
         task_info[0]=rs.getInt(1);
         task_info[1]=rs.getInt(2);
       }
       catch (Exception sqle)
       {

       }
       finally
       {
           close(pstmt);
           conn.setAutoCommit(false);
           close(conn);
       }
       return task_info;

   }
   private  int queryTaskNumber(String parent_id)throws SQLException{
      com.boco.eoms.db.util.BocoConnection conn = null;
         conn = ds.getConnection();
         conn.setAutoCommit(true);
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         String sql = "";
         int task_number=1;
          try
         {
           sql= "select max(task_number) from taw_project_taskinfo where parent_id="+
               parent_id;
           pstmt = conn.prepareStatement(sql);
           System.out.println(sql);
           rs = pstmt.executeQuery();
           rs.next();
          task_number=rs.getInt(1);
        }
        catch (Exception sqle)
        {

        }
        finally
        {
            close(pstmt);
            conn.setAutoCommit(false);
            close(conn);
        }
        return task_number;

    }
    /**
     * 在项目信息表插入记录
     * @param
     * @throws SQLException
     */
    private void insertTrack(int task_id,int parent_id,String track_exec_time,
                             int pro_sign,int task_sign,String track_info,int flag ) throws SQLException
    {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "";
        sql = "insert  into taw_project_track" +
            "(task_id,parent_id,track_exec_time,"+
            " track_pro_sign,track_task_sign,track_info,flag)"
             + " VALUES (?,?,?,?,?,?,?)";

        try
        {
            pstmt = conn.prepareStatement(sql);
            System.out.println(sql);
            pstmt.setInt(1,task_id);
            pstmt.setInt(2,parent_id);
            pstmt.setString(3,track_exec_time);
            pstmt.setInt(4,pro_sign);
            pstmt.setInt(5,task_sign);
            pstmt.setString(6,track_info);
            pstmt.setInt(7,flag);
            pstmt.executeUpdate();

        }
        catch (Exception sqle)
        {
          //  conn.rollback();
         //   sqle.printStackTrace();
        }
        finally
        {
            close(pstmt);
            conn.setAutoCommit(false);
            close(conn);
        }
    }


    public List getTrackList(String condition,int offset, int length,int flag){
          ArrayList list=new ArrayList();
          com.boco.eoms.db.util.BocoConnection conn = null;
           conn = ds.getConnection();
           PreparedStatement pstmt = null;
           ResultSet rs = null;
           try
           {
               pstmt = conn.prepareStatement(condition);
               System.out.println(condition);
               rs = pstmt.executeQuery();
               if (offset > 0)
               {
                   int i = 0;
                   while (i++ < offset)
                   {
                       rs.next();
                   }
               }

               int recCount = 0;
               while ( (recCount++ < length) && rs.next())
               {
                   Project project = new Project();

                   project.setTrack_exec_time(StaticMethod.dbNull2String(rs.getString("track_exec_time")));
                   project.setProject_sign(rs.getInt("track_pro_sign"));
                   project.setTask_sign(rs.getInt("track_task_sign"));
                    if(flag==1){
                 //  project.setTask_desc(StaticMethod.dbNull2String(rs.getString("task_desc")));

                   project.setTask_executor_name(StaticMethod.dbNull2String(rs.getString("user_name")));
                    }
                   else{
                   project.setProject_executor_name(StaticMethod.dbNull2String(rs.getString("user_name")));
                   }
                   project.setTrack_info(StaticMethod.dbNull2String(rs.getString("track_info")));
                   list.add(project);
               }
           }
           catch (SQLException e)
           {
              // e.printStackTrace();
           }
           finally
           {
               close(rs);
               close(pstmt);
               close(conn);
           }
           return list;

     }


     private void delTrack(int task_id)throws SQLException
   {
       com.boco.eoms.db.util.BocoConnection conn = null;
       conn = ds.getConnection();
       conn.setAutoCommit(true);
       PreparedStatement pstmt = null;
       ResultSet rs = null;

       String sql = "";
       sql = "delete from taw_project_track "
           + " where task_id=?";

       try
       {
           pstmt = conn.prepareStatement(sql);
           System.out.println(sql);
           pstmt.setInt(1, task_id);
           pstmt.executeUpdate();
         }
       catch (Exception sqle)
       {
         //  conn.rollback();
        //   sqle.printStackTrace();
       }

       finally
       {   close(pstmt);
           conn.setAutoCommit(false);
           close(conn);
       }
   }
}
