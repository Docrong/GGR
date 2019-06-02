/**
 * 
 */
package com.boco.eoms.commons.db.test;

// Junit library
import junit.framework.TestCase;

// eoms class
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.db.bocopool.BocoConnection;
import com.boco.eoms.commons.db.bocopool.EomsDataSource;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class EomsDataSourceTest extends TestCase {

    EomsDataSource _objDataSource = new EomsDataSource();

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
 
        _objDataSource.setUser("eoms35");
        _objDataSource.setPassword("eoms35");
        _objDataSource.setUrl("jdbc:oracle:thin:@10.0.6.6:1521:eoms");
        _objDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        _objDataSource.setMaxConn(10);
        _objDataSource.setMinConn(5);
        _objDataSource.setMaxAliveTime(3);
        _objDataSource.setMaxIdleTime(30);
        _objDataSource.setMaxWaitTime(-1);
        _objDataSource.setMaxUseCounts(20);
        _objDataSource.setCharset("ZHS16GBK");
        _objDataSource.setDefaultAutoCommit(true);
        
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetConnection() throws Exception {
        BocoConnection _objConn = (BocoConnection)_objDataSource.getConnection();
        assertNotNull(_objConn);
        BocoLog.info(this, "获取数据库连接成功！");
        assertTrue(_objConn.isUse());
        BocoLog.info(this, "数据库连接正在被使用！");
        _objConn.close();
        assertFalse(_objConn.isUse());
        BocoLog.info(this, "数据库连接成功关闭！");
    }

}
