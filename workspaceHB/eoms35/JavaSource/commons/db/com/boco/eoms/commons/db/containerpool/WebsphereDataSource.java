// Source File Name: com.boco.eoms.commons.db.containerpool.WebsphereDataSource
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
 * DataSource
 * 
 * @author Sandy.wei
 * @version 3.5
 */
public class WebsphereDataSource extends BocoDataSource {

    /**
     * @see Default constructor
     */
    public WebsphereDataSource() {
    }

    /**
     * @see 初始化Websphere数据源
     */
    public void init() throws SQLException {
        if (getDataSource() == null)
            try {
                BocoLog.debug(this, "providr url is " + this.getPROVIDER_URL());
                BocoLog.debug(this, "datasourceKey is " + getDatasourceKey());

                Context ctx = new InitialContext(params);
                dataSource = (DataSource) ctx.lookup(datasourceKey);
                if (dataSource == null) {
                    BocoLog.error(this, "datasourceKey invalid, datasource name is: "
                            + datasourceKey);
                    throw new SQLException("Cannot get datasource from Websphere!");
                }
            }
            catch (NamingException e) {
                BocoLog.debug(this, e.getMessage());
                throw new SQLException("Cannot get datasource from Websphere!");
            }
    }

    /**
     * @see 从Websphere数据源中获取数据库连接
     */
    public Connection getConnection() throws SQLException {
        Connection connection = null;
        if (getDataSource() == null)
            throw new SQLException(
                    "Datasource object is null, please check the configure file");
        connection = getDataSource().getConnection();
        if (connection == null)
            throw new SQLException("Connection is null.");
        else
            return connection;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("WebsphereDataSource[");
        sb.append(params.toString());
        sb.append("], ");
        sb.append(super.toString());
        return sb.toString();
    }

}
