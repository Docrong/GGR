package com.boco.eoms.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

/**
 * <p>
 * Title:eoms2.7中的密码导入到eoms3.5中
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jul 1, 2008 8:55:06 PM
 * </p>
 *
 * @author 曲静波
 * @version 3.5.1
 */
public class Passwd27ToPasswd35 {

    public static void main(String args[]) {
        // 3.5期
        // eoms35_gz:taw_system_user
        // (id,deleted,deptid,email,fax,mobile,phone, userid, username,
        // password)
        // 2.7期
        // eoms27:taw_rm_user
        // id,deleted,dept_id,email,fax,mobile,phone,user_id,user_name,password
        Statement stmt27 = null;
        Connection conn35 = null;
        Statement stmt35 = null;
        Connection conn27 = null;

        try {
            // 注册数据库驱动程序为oracle驱动
            Class.forName("com.informix.jdbc.IfxDriver");
            conn27 = DriverManager
                    .getConnection(
                            "jdbc:informix-sqli://10.194.2.232:8002/eoms27:INFORMIXSERVER=eomsdb;NEWCODESET=gbk,8859-1,819",
                            "informix", "informix");
            stmt27 = conn27.createStatement();
            ResultSet rs27 = stmt27
                    .executeQuery("select user_id,password from taw_rm_user");
            conn35 = DriverManager
                    .getConnection(
                            "jdbc:informix-sqli://10.194.2.232:8002/eoms35_gz:INFORMIXSERVER=eomsdb;NEWCODESET=gbk,8859-1,819",
                            "informix", "informix");

            stmt35 = conn35.createStatement();
            conn35.setAutoCommit(false);

            while (rs27.next()) {
                System.out.println(rs27.getString(1));
                System.out.println(rs27.getString(2));
                String passwd = new Md5PasswordEncoder().encodePassword(rs27
                        .getString(2), new String());
                stmt35
                        .executeUpdate("update taw_system_user set password='"
                                + passwd + "' where userid='"
                                + rs27.getString(1) + "'");

            }
            conn35.commit();

        } catch (Exception e) {
            try {
                conn35.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            // 这样写是为了方便调试程序，出错打印mydb()就知道在什么地方出错了
            e.printStackTrace();
        } finally {
            try {
                stmt27.close();
                stmt35.close();
                conn27.close();
                conn35.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}
