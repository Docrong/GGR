package com.boco.eoms.db.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import javax.sql.DataSource;

import com.boco.eoms.base.util.ApplicationContextHolder;

/**
 * 
 * <p>
 * Title:eoms2.7鏉╃偞甯村Ч锟?
 * </p>
 * <p>
 * Description: 鐏忓枣鍙炬穱顔芥暭娑撶dapter阌涘苯鐤勭拹銊ㄧ殶阎⑩暋oms3.5阎ㄥ嚋onnectionPool(鏉╃偞甯村Ч锟?
 * </p>
 * <p>
 * Date:Mar 14, 2008 10:51:20 AM
 * </p>
 * 
 * @author 閺囨煡娼ゅ▔锟?
 * @version 1.0
 * 
 */
public class ConnectionPool {
	static private ConnectionPool instance; // 鍞竴瀹为敓鏂ゆ嫹

	// // adpater阌涘矁镄熼凄钰ms35娑擃厾娈庆onnectionPool阌涘奔濞囬凄銊ユ倱娑掳拷閺佺増宓佸┃锟?
	// private static com.boco.eoms.commons.db.bocopool.ConnectionPool adapter;

	static private int clients;

	static private String defaultpoolname;

	//private LogMan log = LogMan.getInstance("conn");

	// private Prop prop = Prop.getInstance() ;
	private Vector drivers = new Vector();

	private Hashtable pools = new Hashtable();

//	private PropertyFile prop = PropertyFile.getInstance();

	/**
	 * 阌熸枻鎷烽敓鏂ゆ嫹鍞竴瀹为敓鏂ゆ嫹.阌熸枻鎷烽敓鏂ゆ嫹鍫戦敓鎻紮鎷疯敋阌熸枻鎷蜂箞链旈敓鏂ゆ嫹阌燂拷,阌熸触鍒涙枻鎷峰疄阌熸枻鎷?
	 * 
	 * @return ConnectionPool 鍞竴瀹为敓鏂ゆ嫹
	 */

	static synchronized public ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
			// adapter = com.boco.eoms.commons.db.bocopool.ConnectionPool
			// .getInstance();
		}
		clients++;
		return instance;
	}

	/**
	 * 阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷烽敓鏂ゆ嫹绉侀敓鏂ゆ嫹阌熺殕鍑ゆ嫹姝㈤敓鏂ゆ嫹阌熸枻鎷烽敓鏂ゆ嫹锜犲搅阌熸枻鎷烽敓鏂ゆ嫹阌熺粸纰夋嫹阌燂拷
	 */
	private ConnectionPool() {
		// init();
	}

	/**
	 * 阌熸枻鎷穕阌熸帴璁规嫹阌熻杩斿洖闱╂嫹阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷锋寚阌熸枻鎷烽敓鏂ゆ嫹l阌熸帴绛规嫹
	 * 
	 * @param name
	 *            阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷烽敓渚ョ》鎷烽敓鍙鎷烽敓鏂ゆ嫹阌熺担阌熸帴绛规嫹阌熸枻鎷烽敓鏂ゆ嫹
	 * @param con
	 *            l阌熸帴璁规嫹阌熸枻鎷?
	 */

	public void returnConnection(BocoConnection con) {
		// String name = defaultpoolname;
		// DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		// if (pool != null)
		// pool.freeConnection(con);
		// adapter.returnConnection(con);
	}

	public void returnConnection(String name, BocoConnection con) {
		// adapter.returnConnection(name, con);
	}

	/**
	 * 阌熸枻鎷烽敓鎻紮鎷烽敓鏂ゆ嫹阌熺煫纰夋嫹(阌熸枻鎷烽敓鍙鎷?l阌熸枻鎷?阌熸枻鎷烽敓鐭紮鎷烽攲阌熸枻鎷烽敓绲ｉ敓鏂ゆ嫹,阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷穕阌熸枻鎷烽敓鏂ゆ嫹灏忛敓鏂ゆ嫹阌熸枻鎷烽敓绲ｉ敓鏂ゆ嫹阌熸枻鎷?
	 * 阌熸枻鎷烽敓鏂ゆ嫹,阌熸触鍒涙枻鎷烽敓鏂ゆ嫹阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷穕阌熸枻鎷?
	 * 
	 * @param name
	 *            阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷烽敓渚ョ》鎷烽敓鍙鎷烽敓鏂ゆ嫹阌熺担阌熸帴绛规嫹阌熸枻鎷烽敓鏂ゆ嫹
	 * @return Connection 阌熸枻鎷烽敓鏂ゆ嫹l阌熸帴浼欐嫹null
	 */

	public BocoConnection getConnection() {
		DataSource ds = (DataSource) ApplicationContextHolder
				.getInstance().getBean("dataSource");
		try {

			return new BocoConnection(ds.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// return adapter.getConnection();
	}

	public Connection getConnection(String name) {
		return null;
	}

	public com.boco.eoms.commons.db.bocopool.DBConnectionPool getPool(
			String name) {
		return null;
	}

	/**
	 * 阌熸枻鎷烽敓鎻紮鎷烽敓鏂ゆ嫹阌熸枻鎷穕阌熸枻鎷?阌熸枻鎷锋病阌熷彨鍖℃嫹阌熸枻鎷穕阌熸枻鎷?阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷穕阌熸枻鎷烽敓鏂ゆ嫹灏忛敓鏂ゆ嫹阌熸枻鎷烽敓绲ｉ敓鏂ゆ嫹阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷?
	 * 阌熸触鍒涙枻鎷烽敓鏂ゆ嫹阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷穕阌熸枻鎷?阌熸枻鎷烽敓鏂ゆ嫹,阌熸枻鎷锋寚阌熸枻鎷烽敓鏂ゆ嫹镞堕敓鏂ゆ嫹阌熻妭绛夎揪鎷烽敓鏂ゆ嫹阌熸枻鎷烽敓绔鎷烽敓閰靛嚖鎷穕阌熸枻鎷?
	 * 
	 * @param name
	 *            l阌熸帴绛规嫹阌熸枻鎷烽敓鏂ゆ嫹
	 * @param time
	 *            阌熺殕鐚存嫹阌熸枻鎷烽鐗″嵈阌熺粸鎲嬫嫹阌燂拷
	 * @return Connection 阌熸枻鎷烽敓鏂ゆ嫹l阌熸帴浼欐嫹null
	 */

	public BocoConnection getConnection(String name, int time) {
		return null;
	}

	/**
	 * 阌熸埅鎲嬫嫹阌熸枻鎷烽敓鏂ゆ嫹l阌熸枻鎷?阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷锋敞阌熸枻鎷?
	 */

	public synchronized void closeAllConnection() {

	}

	/**
	 * 阌熸枻鎷烽敓琛楅潻鎷烽敓鏂ゆ嫹阌熸枻鎷锋簮阌熸枻鎷烽敓绲ｉ敓鎺ョ鎷峰疄阌熸枻鎷?
	 * 
	 * @param props
	 *            l阌熸帴绛规嫹阌熸枻鎷烽敓鏂ゆ嫹
	 */
	/*
	private void createPools() {
		String poolnames = prop.getProperty("PoolNames", "Boco");
		System.out.println(poolnames);
		StringTokenizer st = new StringTokenizer(poolnames);
		while (st.hasMoreElements()) {// 阌熸枻鎷穊阌熸枻鎷峰悓阌熸枻鎷疯彉阌熸枻鎷穕阌熸帴绛规嫹
			String poolName = st.nextToken().trim();
			String url = prop.getProperty(poolName + "Url"); // t阌熸帴纰夋嫹url
			String user = prop.getProperty(poolName + "User"); // t阌熸帴纰夋嫹阌熺煫浼欐嫹阌熸枻鎷?
			String password = prop.getProperty(poolName + "Password"); // t阌熸帴纰夋嫹阌熸枻鎷烽敓鏂ゆ嫹
			String charset = prop.getProperty(poolName + "Charset"); // t阌熸帴纰夋嫹阌熻鍑ゆ嫹
			int maxconn = StaticMethod.getIntValue(prop.getProperty(poolName
					+ "Maxconn"), 10); // 阌熸枻鎷烽敓鏂ゆ嫹t阌熸枻鎷烽敓鏂ゆ嫹
			int minconn = StaticMethod.getIntValue(prop.getProperty(poolName
					+ "Minconn"), 5); // 阌熸枻鎷峰皬阌熸枻鎷穞阌熸枻鎷烽敓鏂ゆ嫹
			int maxusecount = StaticMethod.getIntValue(prop
					.getProperty(poolName + "Maxusecount"), 20); // t阌熸帴纰夋嫹阌熸枻鎷烽敓缁炵櫢鎷蜂箞阌熸枻鎷烽敓锟?
			int maxidletime = StaticMethod.getIntValue(prop
					.getProperty(poolName + "Maxidletime"), 30); // t阌熸帴纰夋嫹阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷锋椂阌熸枻鎷?阌熸枻鎷?
			int maxalivetime = StaticMethod.getIntValue(prop
					.getProperty(poolName + "Maxalivetime"), 3); // t阌熸枻鎷烽敓鏂ゆ嫹涓€阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷烽敓鍙鎷烽敓鏂ゆ嫹阌熸枻鎷烽敓鏂ゆ嫹镞堕敓鏂ゆ嫹(阌熸枻鎷?

			DBConnectionPool pool = new DBConnectionPool(poolName, url, user,
					password, charset, maxconn, minconn, maxusecount,
					maxidletime, maxalivetime);
			pools.put(poolName, pool);
			BocoLog.info(this, 1, "阌熸枻鎷穊阌熸枻鎷疯彉阌熺担阌熸帴绛规嫹:" + url);
		}
	}
	*/
	/**
	 * 阌熸枻鎷峰彇阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷锋矙阌熺粸纭锋嫹阌燂拷
	 */
	/*
	private void init() {
		defaultpoolname = prop.getProperty("DefaultPoolName");
		loadDrivers();
		createPools();
	}
	*/

	/**
	 * 瑁呴敓鎴尨鎷锋敞阌熸枻鎷烽敓鏂ゆ嫹阌熸枻鎷稪DBC阌熸枻鎷烽敓鏂ゆ嫹阌燂拷
	 * 
	 * @param props
	 *            阌熸枻鎷烽敓鏂ゆ嫹
	 */
	/*
	private void loadDrivers() {
		String driverClasses = prop.getProperty("DriverClasses");
		StringTokenizer st = new StringTokenizer(driverClasses);
		while (st.hasMoreElements()) {
			String driverClassName = st.nextToken().trim();
			System.out.println(driverClassName);
			try {
				Driver driver = (Driver) Class.forName(driverClassName)
						.newInstance();
				DriverManager.registerDriver(driver);
				drivers.addElement(driver);
			} catch (Exception e) {
				log(e, "阌熺潾鍑ゆ嫹娉ㄩ敓鏂ゆ嫹JDBC阌熸枻鎷烽敓鏂ゆ嫹阌燂拷: " + driverClassName + e);
			}
		}
	}

	*/
	/**
	 * 阌熸枻鎷烽敓渚ユ唻鎷烽敓鏂ゆ嫹鎭啓阌熸枻鎷烽敓鏂ゆ嫹蹇楅敓渚ョ》鎷?
	 */
	private void log(String msg) {
		//log.writeLog(msg);
	}

	/**
	 * 阌熸枻鎷烽敓渚ユ唻鎷烽敓鏂ゆ嫹鎭敓鏂ゆ嫹阌熷众甯稿啓阌熸枻鎷烽敓鏂ゆ嫹蹇楅敓渚ョ》鎷?
	 */
	private void log(Throwable e, String msg) {
//		log.writeLog("ConnectionPool.class", e);
//		log.writeLog(e);
	}

	/*
	 * private String getPropValue(String filename , String key) { return
	 * prop.getPropValue(filename , key) ; }
	 */
}
