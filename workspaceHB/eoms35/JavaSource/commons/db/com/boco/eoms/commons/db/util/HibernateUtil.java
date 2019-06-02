package com.boco.eoms.commons.db.util;

// java standard library
import java.sql.Connection;

// hibernate library
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;

// eoms library
import com.boco.eoms.commons.db.bocopool.ConnectionPool;
import com.boco.eoms.commons.db.bocopool.BocoConnection;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class HibernateUtil {

    private static final ThreadLocal sessionThread = new ThreadLocal();

    private static final ThreadLocal transactionThread = new ThreadLocal();

    private static ConnectionPool ds = ConnectionPool.getInstance();

    private static SessionFactory sf = null;

    static {
        try {
            Configuration config = new Configuration().configure();
            sf = config.buildSessionFactory();
        }
        catch (HibernateException e) {
            try {
                String _strCfgFile = "com/boco/eoms/commons/db/config/hibernate.cfg.xml";
                Configuration config = new Configuration()
                        .configure(_strCfgFile);
                sf = config.buildSessionFactory();
            }
            catch (HibernateException ex) {
                throw new RuntimeException(
                        "Exception building SessionFactory: " + ex.getMessage(),
                        ex);
            }

        }
    }

    /**
     * @see 非等待方式获取默认连接池中的数据库连接
     * @return BocoConnection, 可用数据库连接对象或null
     */
    public static BocoConnection getConnection() {
        BocoConnection conn = ds.getConnection();
        BocoLog.debug(HibernateUtil.class, "Current connection is: ["
                + conn.toString() + "]");

        return conn;
    }

    /**
     * @see 固定等待时间方式获取默认数据库连接池中的数据库连接
     * @param int,
     *            等待时间，以分钟计数
     * @return BocoConnection, 可用数据库连接对象或null
     */
    public static BocoConnection getConnection(int time) {
        BocoConnection conn = ds.getConnection(time);
        return conn;
    }

    /**
     * @see 非等待方式获取指定连接池中的数据库连接
     * @param String,
     *            指定的数据库连接池
     * @return BocoConnection, 可用数据库连接对象或null
     */
    public static BocoConnection getConnection(String _strPoolName) {
        BocoConnection conn = ds.getConnection(_strPoolName);
        return conn;
    }

    /**
     * @see 固定等待时间方式获取指定连接池中数据库连接
     * @param String,
     *            指定连接池名称
     * @param int,
     *            等待时间，以分钟计数
     * @return BocoConnection, 可用数据库连接或null
     */
    public static BocoConnection getConnection(String _strPoolName, int time) {
        BocoConnection conn = ds.getConnection(_strPoolName, time);
        return conn;
    }

    /**
     * @see 根据指定的数据库连接，获取Hibernate的Session对象
     * @param Connection,
     *            数据库连接对象
     * @return Session,
     */
    public static Session currentSession(Connection _objConn)
            throws HibernateException {
        return currentSession(_objConn, false);
    }

    /**
     * @see 根据指定的数据库连接及是否支持长时间使用特性，获取Hibernate的Session对象
     * @param Connection,
     *            数据库连接对象
     * @param boolean,
     *            是否支持长时间占用
     * @return Session,
     */
    public static Session currentSession(Connection _objConn,
            boolean flagUseLongActive) throws HibernateException {
        Session s = (Session) sessionThread.get();

        if (s == null) {
            ((BocoConnection) _objConn)
                    .setFlagUseLongActiveTime(flagUseLongActive);
            s = sf.openSession(_objConn);
            sessionThread.set(s);
        }

        return s;
    }

    /**
     * 获取当前线程使用的Session， 如果有则返回，无则返回null
     * 
     * @return Session
     * @throws HibernateException
     */
    public static Session getCurrentSession() throws HibernateException {
        return (Session) sessionThread.get();
    }

    /**
     * 启动或者加入当前Session的事务
     * 
     * @return Transaction
     * @throws HibernateException
     */
    public static Transaction currentTransaction(Connection _objConn)
            throws HibernateException {
        Transaction tx = (Transaction) transactionThread.get();
        if (tx == null) {
            tx = currentSession(_objConn).beginTransaction();
            transactionThread.set(tx);
        }
        return tx;
    }

    /**
     * 提交当前Session的事务
     * 
     * @throws HibernateException
     */
    public static void commitTransaction() throws HibernateException {
        Transaction tx = (Transaction) transactionThread.get();
        transactionThread.set(null);
        if (tx != null) tx.commit();
    }

    /**
     * 回滚当前Session的事务
     * 
     * @throws HibernateException
     */
    public static void rollbackTransaction() throws HibernateException {
        Transaction tx = (Transaction) transactionThread.get();
        transactionThread.set(null);
        if (tx != null) tx.rollback();
    }

    /**
     * 关闭当前线程使用的Session
     * 
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session s = (Session) sessionThread.get();
        if (s != null) {
            BocoConnection conn = (BocoConnection) s.connection();
            if (conn != null) {
                conn.close();
            }
            s.clear();
            s.close();
        }
        sessionThread.set(null);
    }

    /**
     * @return the ds
     */
    public static ConnectionPool getDs() {
        return ds;
    }

    /**
     * @param ds
     *            the ds to set
     */
    public static void setDs(ConnectionPool ds) {
        HibernateUtil.ds = ds;
    }

}
