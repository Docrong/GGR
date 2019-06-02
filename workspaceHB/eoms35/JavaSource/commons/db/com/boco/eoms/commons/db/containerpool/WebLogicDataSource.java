// Source File Name: com.boco.eoms.commons.db.containerpool.WebLogicDataSource
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
public class WebLogicDataSource extends BocoDataSource {

    /**
     * @see Default constructor
     */
    public WebLogicDataSource() {
    }

    /**
     * @see 初始化Weblogic数据源
     */
    public void init() throws SQLException {
        if (getDataSource() == null)
            try {
                BocoLog.debug(this, "providr url is " + this.getPROVIDER_URL());
                BocoLog.debug(this, "datasourceKey is " + getDatasourceKey());

                Context ctx = new InitialContext(params);
                dataSource = (DataSource) ctx.lookup(datasourceKey);
                if (dataSource == null) {
                    BocoLog.error(this,
                            "datasourceKey invaid, datasource name is: "
                                    + datasourceKey);
                    throw new SQLException(
                            "Cannot get datasource from Weblogic!");
                }
            }
            catch (NamingException e) {
                BocoLog.error(this, "NamingException is thrown! "
                        + e.getMessage());
                throw new SQLException("Cannot get datasource from Weblogic!");
            }
    }

    /**
     * @see 从Weblogic数据源中获取数据库连接
     */
    public Connection getConnection() throws SQLException {
        Connection connection = null;
        if (getDataSource() == null) {
            BocoLog
                    .error(this,
                            "Datasource object is null, please check the configure file");
            throw new SQLException(
                    "Datasource object is null, please check the configure file");
        }
        connection = (Connection) getDataSource().getConnection();
        if (connection == null) {
            BocoLog.error(this, "Connection is null.");
            throw new SQLException("Connection is null.");
        }
        else {
            return connection;
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("WebLogicDataSource[");
        sb.append(params.toString());
        sb.append("], ");
        sb.append(super.toString());
        return sb.toString();
    }

}
