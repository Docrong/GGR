// Source File Name: com.boco.eoms.commons.db.containerpool.DataSourceManager
package com.boco.eoms.commons.db.containerpool;

// java standard library
import java.sql.Connection;
import java.sql.SQLException;

// eoms classes
import com.boco.eoms.commons.db.util.PropertyFile;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * Referenced classes of package com.boco.eoms.commons.db.containerpool:
 * JNDIDataSource, BocoDataSource, WebLogicDataSource, WebsphereDataSource
 * 
 * @author Sandy.wei
 * @version 3.5
 */
public class DataSourceManager {

    // properties
    private static DataSourceManager instance = null;

    private static BocoDataSource dataSource = null;

    private static PropertyFile _objProp = PropertyFile.getInstance();

    /**
     * @see Default Constructor
     */
    private DataSourceManager() {
        try {
            initDataSource();
        }
        catch (Exception e) {
            BocoLog.error(this, e.getMessage());
        }
    }

    /**
     * @see 初始化容器化的数据源
     */
    private static void initDataSource() throws Exception {

        String _strContainerType = _objProp.getProperty("ContainerType");
        if (_strContainerType.equalsIgnoreCase("Tomcat")) {
            initTomcatDataSource();
        }
        else if (_strContainerType.equalsIgnoreCase("WebLogic")) {
            initWeblogicDataSource();
        }
        else if (_strContainerType.equalsIgnoreCase("Websphere")) {
            initWebsphereDataSource();
        }
    }

    /**
     * @see 初始化Tomcat数据源
     */
    private static void initTomcatDataSource() throws Exception {
        String _strJNDIName = _objProp.getProperty("JNDIName");
        dataSource = new JNDIDataSource();
        dataSource.setDatasourceKey(_strJNDIName);

        try {
            ((JNDIDataSource) dataSource).init();
        }
        catch (SQLException e) {
            dataSource = null;
            BocoLog.error(DataSourceManager.class, e.getMessage());
            throw new Exception("Config Error in Tomcat!");
        }
    }

    /**
     * @see 初始化Weblogic数据源
     */
    private static void initWeblogicDataSource() throws Exception {
        String _strJNDIName = _objProp.getProperty("JNDIName");
        String _strProviderURL = _objProp.getProperty("ProviderURL");
        String _strInitContextFactory = _objProp
                .getProperty("InitialContextFactory");

        dataSource = new WebLogicDataSource();
        dataSource.setDatasourceKey(_strJNDIName);
        dataSource.setINITIAL_CONTEXT_FACTORY(_strInitContextFactory);
        dataSource.setPROVIDER_URL(_strProviderURL);

        try {
            ((WebLogicDataSource) dataSource).init();
        }
        catch (SQLException e) {
            dataSource = null;
            BocoLog.error(DataSourceManager.class, e.getMessage());
            throw new Exception("Config Error in Weblogic!");
        }
    }

    /**
     * @see 初始化Websphere数据源
     */
    private static void initWebsphereDataSource() throws Exception {
        String _strJNDIName = _objProp.getProperty("JNDIName");
        String _strProviderURL = _objProp.getProperty("ProviderURL");
        String _strInitContextFactory = _objProp
                .getProperty("InitialContextFactory");

        dataSource = new WebsphereDataSource();
        dataSource.setDatasourceKey(_strJNDIName);
        dataSource.setINITIAL_CONTEXT_FACTORY(_strInitContextFactory);
        dataSource.setPROVIDER_URL(_strProviderURL);

        try {
            ((WebsphereDataSource) dataSource).init();
        }
        catch (SQLException e) {
            dataSource = null;
            BocoLog.error(DataSourceManager.class, e.getMessage());
            throw new Exception("Config Error in Weblogic!");
        }
    }

    /**
     * @see 获取数据源管理器对象，并且控制多线程下的单实例。
     */
    public static synchronized DataSourceManager getInstance() {

        if (instance == null) {
            instance = new DataSourceManager();
        }
        return instance;
    }
    
    /**
     * @see 获取数据库连接
     */
    public synchronized Connection getConnection() throws Exception {
        Connection _objReturn = null;

        if (dataSource == null) {
            throw new Exception("Cannot initialize datasource!");
        }
        else {
            _objReturn = dataSource.getConnection();
        }

        return _objReturn;
    }

}
