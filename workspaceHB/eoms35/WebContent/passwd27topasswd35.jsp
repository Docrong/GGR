<%@ page
        import="java.sql.Connection, java.sql.DriverManager,java.sql.ResultSet,java.sql.SQLException,java.sql.Statement,org.acegisecurity.providers.encoding.Md5PasswordEncoder" %>


<%

    Statement stmt27 = null;
    Connection conn35 = null;
    Statement stmt35 = null;
    Connection conn27 = null;

    try {
        // ??????????oracle??
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
            out.println(rs27.getString(1));
            out.println(rs27.getString(2));
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
        // ?????????????????mydb()???????????
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


%>