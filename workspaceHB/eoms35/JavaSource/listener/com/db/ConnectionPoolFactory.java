package com.db;

/**
 * 创建连接池的工厂类
 *
 * @author Administrator
 */
public class ConnectionPoolFactory {

    /**
     * 取得连接池类的一个实例
     *
     * @param name 连接池类的名字（必须处于connectionPool包下）
     * @return 连接池类的一个实例
     */
    public static MySQLConnection getConnectionPool(String name) {
        try {
            return (MySQLConnection) Class.forName(name).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
