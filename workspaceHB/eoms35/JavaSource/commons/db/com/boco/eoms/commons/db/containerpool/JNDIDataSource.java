// Source File Name: com.boco.eoms.commons.db.containerpool.JNDIDataSource
package com.boco.eoms.commons.db.containerpool;

// java standard library
import java.sql.Connection;
import java.sql.SQLException;

// java extend library
import javax.naming.*;
import javax.sql.DataSource;

// eoms class
import com.boco.eoms.commons.loging.BocoLog;

/**
 * Referenced classes of package com.boco.eoms.commons.db.containerpool:
 * BocoDataSource
 * 
 * @author Sandy.wei
 * @version 3.5
 */
public class JNDIDataSource extends BocoDataSource {

    /**
     * @see Default constructor
     */
    public JNDIDataSource() {
    }

    /**
     * @see 初始化Tomcat数据源
     */
    public void init() throws SQLException {
        if (getDataSource() == null)
            try {
                Context dsCtx = null;
                if (params.size() > 0) {
                    dsCtx = new InitialContext(params);
                }
                else {
                    Context initCtx = new InitialContext();
                    dsCtx = (Context) initCtx.lookup("java:comp/env");
                }
                dataSource = (DataSource) dsCtx.lookup("jdbc/"+datasourceKey);
                if (dataSource == null) {
                    BocoLog.error(this, "datasourceKey invalid, datasource name is: "
                                    + datasourceKey);
                    throw new SQLException("Cannot get datasource from Tomcat!");
                }
            }
            catch (NamingException e) {
                BocoLog.error(this, e.getMessage());
                throw new SQLException("Cannot get datasource from Tomcat!");
            }
    }

    /**
     * @see 从Tomcat数据源中获取数据库连接
     */
    public Connection getConnection() throws SQLException {
        Connection connection = null;
        if (getDataSource() == null) {
            BocoLog.error(this, "Datasource object is null, please check the configure file");
            throw new SQLException(
                    "Datasource object is null, please check the configure file");
        }
        connection = getDataSource().getConnection();
        if (connection == null) {
            BocoLog.error(this, "Connection is null.");
        }

        return connection;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("JNDIDataSource[");
        sb.append(params.toString());
        sb.append("], ");
        sb.append(super.toString());
        return sb.toString();
    }

}
