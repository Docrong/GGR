package com.boco.eoms.commons.db.bocopool;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Vector;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.db.util.PropertyFile;
import com.boco.eoms.commons.loging.BocoLog;

public class RecordSet {

	/**
	 * default constructor
	 */
	public RecordSet() {
		array = new Vector();
		bSuccess = true;
		// loadProperties();
	}

	/**
	 * initialize array
	 */
	private void init() {
		curpos = -1;
		flag = 1;
		msg = "数据库异常";
		array.clear();
	}

	/**
	 * 解析参数
	 */
	private void parseArgument(String s) {
		int i = 0;
		int j = 0;
		if (s.trim().equals("")) {
			args = new String[0];
			return;
		}
		for (j = 0; j < s.length(); j++) {
			if (s.charAt(j) == separator) {
				i++;

			}
		}
		args = new String[i + 1];
		j = 0;
		i = 0;
		while ((j = s.indexOf(separator)) != -1) {
			args[i++] = s.substring(0, j);
			s = s.substring(j + 1);
		}
		args[i] = s;
	}

	public boolean executeProc(String poolname, String s, String s1) {

		init();
		if (!getConnection(poolname)) {
			return false;
		}
		try {
			parseArgument(s1);
			int i = args.length;
			String s2 = "";
			if (databaseType.equals("informix")) {
				s2 = "execute procedure " + s + "(";
				for (int j = 0; j < i - 1; j++) {
					s2 = s2 + "?,";
				}
				s2 = s2 + "?)";
			} else {
				s2 = "{call " + s + "(";
				for (int j = 0; j < i + 1; j++) {
					s2 = s2 + "?,";

				}
				if (databaseType.equals("oracle")) {
					s2 = s2 + "?,";
				}
				s2 = s2 + "?)}";
			}
			conn.setAutoCommit(true);
			CallableStatement callablestatement = conn.prepareCall(s2);
			for (int k = 0; k < i; k++) {
				callablestatement.setString(k + 1, args[k]);
			}
			if (!databaseType.equals("informix")) {
				callablestatement.registerOutParameter(i + 1, Types.NUMERIC);
				callablestatement.registerOutParameter(i + 2, Types.VARCHAR);
				if (databaseType.equals("oracle")) {
					callablestatement.registerOutParameter(i + 3, -10);
				}
			}
			long l1 = System.currentTimeMillis();
			callablestatement.execute();
			long l3 = System.currentTimeMillis();
			if (logExecuteTime) {
				log(getLogFileName("execute"), s + "|" + (l3 - l1) + "\r\n");
				/*
				 * flag=callablestatement.getInt(i+1); msg =
				 * callablestatement.getString(i + 2);
				 */
			}
			if (true) { // flag == 0)
				try {
					ResultSet resultset = null;
					if (databaseType.equals("informix")) {
						resultset = callablestatement.getResultSet();
					} else {
						if (databaseType.equals("oracle")) {
							resultset = (ResultSet) callablestatement
									.getObject(i + 3);
						} else {
							while ((resultset = callablestatement
									.getResultSet()) == null) {
								if (!callablestatement.getMoreResults()
										&& callablestatement.getUpdateCount() == -1) {
									break;
								}
							}
						}
					}
					if (resultset != null) {
						parseResultSet(resultset);
						resultset.close();
					}
				} catch (SQLException sqlexception) {
					sqlexception.printStackTrace();
				}
			}
			callablestatement.close();
			conn.setAutoCommit(false);
			bSuccess = true;
		} catch (Exception exception) {
			bSuccess = false;
			String s3 = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"))
					.format(new java.util.Date())
					+ "\r\n"
					+ s
					+ "\r\n"
					+ s1
					+ "\r\n"
					+ exception.toString()
					+ "\r\n";
			if (logErrorMsg) {
				log(getLogFileName("err"), s3);
			}
			exception.printStackTrace();
		} finally {
			try {
				givebackConnection();
			} catch (Exception exception2) {
			}
		}
		return bSuccess;
	}

	public boolean executeSql(String poolname, String s) {
		init();
		if (!getConnection(poolname)) {
			return false;
		}
		try {
			conn.setAutoCommit(true);
			// print("execute sql: " + s);
			Statement statement = conn.createStatement();
			statement.execute(s);
			ResultSet resultset = statement.getResultSet();
			parseResultSet(resultset);

			// print("rowcount=" + j);
			resultset.close();
			statement.close();
			conn.setAutoCommit(false);
			bSuccess = true;
			msg = "执行成功";
			flag = 0;
		} catch (Exception exception) {
			bSuccess = false;
			msg = exception.getMessage();
			flag = -1;
			if (logErrorMsg) {
				log(getLogFileName("err"), exception.toString());
			}
			exception.printStackTrace();
		} finally {
			try {
				givebackConnection();
			} catch (Exception exception2) {
			}
		}
		return bSuccess;
	}

	public boolean executeProc(String s, String s1) {
		return executeProc("", s, s1);
	}

	public boolean executeSql(String s) {
		return executeSql("", s);
	}

	public boolean execute(String s, String s1) {
		return executeProc("", s, s1);
	}

	public boolean execute(String s) {
		return executeSql("", s);
	}

	private void parseResultSet(ResultSet resultset) throws Exception {
		ResultSetMetaData resultsetmetadata = resultset.getMetaData();
		int i1 = resultsetmetadata.getColumnCount();
		columnName = new String[i1];
		columnType = new int[i1];
		for (int i2 = 0; i2 < i1; i2++) {
			columnName[i2] = resultsetmetadata.getColumnName(i2 + 1);
			columnType[i2] = resultsetmetadata.getColumnType(i2 + 1);
		}
		int j1;
		for (j1 = 0; resultset.next(); j1++) {
			Object as[] = new Object[i1];
			for (int k1 = 1; k1 <= i1; k1++) {
				as[k1 - 1] = resultset.getObject(k1);
			}
			array.add(as);
		}
	}

	/**
	 * 建立数据库连接
	 */
	private boolean getConnection(String poolname) {
		// try {
		// if (connectionType == 1) {
		// Class.forName(jdbcDriverName);
		// conn = DriverManager.getConnection(jdbcUrl);
		// } else if (connectionType == 2) {
		// Context env = (Context) new InitialContext()
		// .lookup("java:comp/env");
		// DataSource pool = (DataSource) env.lookup("jdbc/boco");
		// conn = pool.getConnection();
		// } else {
		// pool = ConnectionPool.getInstance();
		// if (poolname == "") {
		// conn = pool.getConnection();
		// } else {
		// conn = pool.getConnection(poolname);
		// }
		// }
		// return true;
		// } catch (Exception exception) {
		// exception.printStackTrace();
		// }
		// return false;
		conn = com.boco.eoms.db.util.ConnectionPool.getInstance()
				.getConnection();
		return true;
	}

	/**
	 * 关闭数据库连接
	 */
	public void givebackConnection() throws SQLException {
		if (conn != null) {
			// pool.returnConnection(conn);
			conn.close();
		}
	}

	/**
	 * 取得结果集行数
	 */
	public int getCounts() {
		return array.size();
	}

	/**
	 * 取得结果集列数
	 */
	public int getColCounts() {
		return array.isEmpty() ? 0 : ((String[]) array.get(0)).length;
	}

	public String getString(int columnIndex) {
		columnIndex--;
		String s = "";
		if (!array.isEmpty() && curpos >= 0 && curpos < array.size()) {
			Object as[] = (Object[]) array.get(curpos);
			if (columnIndex >= 0 && columnIndex < as.length) {
				try {
					if (columnType[columnIndex] == Types.CLOB) {
						Clob clob = null;
						try {
							clob = (Clob) as[columnIndex];
						} catch (Exception se) {
						}
						if (clob != null) {
							s = clob.getSubString(1L, (int) clob.length());
						}
					} else {
						s = as[columnIndex].toString();
					}
				} catch (Exception e) {
				}
				if (s == null) {
					s = "";
				}
			}
		}
		String reStr = "";
		reStr = s == null ? "" : s;
		reStr = reStr.trim();
		if (connectionType != 3) {
			String charset = prop.getProperty("BocoNMCharset");

			String CHARSET_PAGE = prop.getProperty(
					"database.format.Charset.Page_Charset").toString();
			reStr = s == null ? "" : s;
			reStr = reStr.trim();
			if (CHARSET_PAGE.equals(charset)) {
				return reStr;
			}
			try {
				reStr = new String(reStr.getBytes(charset), CHARSET_PAGE);
			} catch (Exception e) {
				BocoLog.error(this, "转化数据库字符错误: " + e.getMessage());
			}
		}
		return reStr;
	}

	public String getString(String columnname) {

		return getString(getColumnIndex(columnname));
	}

	public boolean getBoolean(int columnIndex) {
		columnIndex--;
		boolean bool = false;
		if (!array.isEmpty() && curpos >= 0 && curpos <= array.size()) {
			Object as[] = (Object[]) array.get(curpos);
			try {
				bool = ((Boolean) as[columnIndex]).booleanValue();
			} catch (Exception e) {
				throw new ClassCastException();
			}
		}
		return bool;
	}

	public boolean getBoolean(String columnName) {
		return getBoolean(getColumnIndex(columnName));
	}

	public int getInt(int columnIndex) {
		columnIndex--;
		int integer = 0;
		if (!array.isEmpty() && curpos >= 0 && curpos < array.size()) {
			Object as[] = (Object[]) array.get(curpos);
			if (columnIndex >= 0 && columnIndex < as.length) {
				try {
					if (columnType[columnIndex] == Types.BIGINT
							|| columnType[columnIndex] == Types.BIT
							|| columnType[columnIndex] == Types.CHAR
							|| columnType[columnIndex] == Types.INTEGER
							|| columnType[columnIndex] == Types.SMALLINT
							|| columnType[columnIndex] == Types.TINYINT
							|| columnType[columnIndex] == Types.VARCHAR
							|| columnType[columnIndex] == 2) {
						integer = Integer.parseInt(as[columnIndex].toString());
					} else {
						throw new java.lang.ClassCastException();
					}
				} catch (Exception e) {
					throw new ClassCastException();
				}
			}
		}
		return integer;
	}

	public int getInt(String columnname) {
		return getInt(getColumnIndex(columnname));
	}

	public InputStream getInputStream(int columnIndex) {
		BufferedInputStream bis = null;
		if (!array.isEmpty() && curpos >= 0 && curpos <= array.size()) {
			Object as[] = (Object[]) array.get(curpos);
			if (columnType[columnIndex] == Types.LONGVARCHAR) {
				byte inputByte[] = new byte[as[columnIndex].toString().length()];
				inputByte = as[columnIndex].toString().getBytes();

				bis = new BufferedInputStream(new ByteArrayInputStream(
						inputByte));
			}
		} else {
			throw new ClassCastException();
		}
		return bis;
	}

	public InputStream getInputStream(String columnName) {
		return getInputStream(getColumnIndex(columnName));
	}

	public float getFloat(int columnIndex) {
		columnIndex--;
		float f = 0.0f;
		if (!array.isEmpty() && curpos >= 0 && curpos <= array.size()) {
			Object as[] = (Object[]) array.get(curpos);
			try {
				f = Float.parseFloat(as[columnIndex].toString());
			} catch (ClassCastException cce) {
				throw new ClassCastException();
			}
		}
		return f;
	}

	public float getFloat(String columnName) {
		return getFloat(this.getColumnIndex(columnName));
	}

	public double getDouble(int columnIndex) {
		columnIndex--;
		double d = 0.0;
		if (!array.isEmpty() && curpos >= 0 && curpos <= array.size()) {
			Object as[] = (Object[]) array.get(curpos);
			try {
				d = Double.parseDouble(as[columnIndex].toString());
			} catch (ClassCastException cce) {
				throw new ClassCastException();
			}
		}
		return d;
	}

	public double getDouble(String columnName) {
		return getDouble(getColumnIndex(columnName));
	}

	public java.util.Date getDate(int columnIndex) {
		columnIndex--;
		java.util.Date date = null;
		if (!array.isEmpty() && curpos >= 0 && curpos <= array.size()) {
			Object as[] = (Object[]) array.get(curpos);
			try {
				date = (java.util.Date) as[columnIndex];
			} catch (Exception e) {
				throw new ClassCastException();
			}
		}
		return date;
	}

	public java.util.Date getDate(String columnName) {
		return getDate(getColumnIndex(columnName));
	}

	public InputStream getBinaryStream(int columnIndex) {
		columnIndex--;
		InputStream is = null;
		if (!array.isEmpty() && curpos >= 0 && curpos <= array.size()) {
			Object[] as = (Object[]) array.get(curpos);
			try {
				is = ((Blob) as[columnIndex]).getBinaryStream();
			} catch (Exception e) {
				throw new ClassCastException();
			}
		}
		return is;
	}

	public InputStream getBinaryStream(String columnName) {
		return getBinaryStream(getColumnIndex(columnName));
	}

	public int getFlag() {
		return flag;
	}

	public String getMsg() {
		return msg;
	}

	public boolean first() {
		if (array.isEmpty()) {
			return false;
		} else {
			curpos = 0;
			return true;
		}
	}

	public boolean last() {
		if (array.isEmpty()) {
			return false;
		} else {
			curpos = array.size() - 1;
			return true;
		}
	}

	public boolean next() {
		if (array.isEmpty()) {
			return false;
		}
		if (curpos < array.size() - 1) {
			curpos++;
			return true;
		} else {
			return false;
		}
	}

	public boolean previous() {
		if (array.isEmpty()) {
			return false;
		}
		if (curpos > 0 && curpos < array.size()) {
			curpos--;
			return true;
		} else {
			return false;
		}
	}

	public boolean absolute(int i) {
		if (array.isEmpty()) {
			return false;
		}
		if (i >= 0 && i < array.size()) {
			curpos = i;
			return true;
		} else {
			return false;
		}
	}

	private int getColumnIndex(String columnname) {
		for (int i1 = 0; i1 < columnName.length; i1++) {
			if (columnName[i1].equalsIgnoreCase(columnname)) {
				return i1 + 1;
			}
		}
		return -1;
	}

	/**
	 * 获取数据库连接信息
	 */
	private void loadProperties() {
		if (needReloadProperty) {
			PropertyFile prop = PropertyFile.getInstance();
			logExecuteTime = (prop.getProperty("LogExecuteTime", "1")
					.equals("1")) ? true : false;
			logErrorMsg = (prop.getProperty("LogErrorMsg", "1").equals("1")) ? true
					: false;
			printMsg = Boolean.getBoolean(prop.getProperty("PrintMsg", "true"));
			logPath = prop.getProperty("LogPath");
			separator = (char) StaticMethod.getIntValue(prop
					.getProperty("Separator"), 2);
			connectionType = StaticMethod.getIntValue(prop.getProperty(
					"ConnectionType", "1"));
			databaseType = prop.getProperty("DatabaseType");
			jdbcDriverName = prop.getProperty("DriverClassName");
			jdbcUrl = prop.getProperty("url");
			needReloadProperty = Boolean.getBoolean(prop.getProperty(
					"NeedReloadProperty", "true"));
		}
	}

	private synchronized void log(String s, String s1) {
		try {
			File file = new File(s);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter filewriter = new FileWriter(s, true);
			filewriter.write(s1);
			filewriter.close();
		} catch (Exception exception) {
			BocoLog.error(this, "RecordSet: " + exception.getMessage());
		}
	}

	private String getLogFileName(String s) {
		String s1 = "";
		if (s.equals("err")) {
			s1 = "RsErr";
		} else if (s.equals("execute")) {
			s1 = "RsExe";
		}
		s1 = logPath + File.separator + s1;
		s1 = s1
				+ (new SimpleDateFormat("yyyyMMdd"))
						.format(new java.util.Date()) + ".log";
		return s1;
	}

	private int curpos;

	private int flag;

	private String msg;

	private static PropertyFile prop = PropertyFile.getInstance();

	private static boolean logExecuteTime = (prop.getProperty("LogExecuteTime",
			"1").equals("1")) ? true : false;

	private static boolean logErrorMsg = (prop.getProperty("LogErrorMsg", "1")
			.equals("1")) ? true : false;

	private static boolean printMsg = (prop.getProperty("PrintMsg", "1")
			.equals("1")) ? true : false;

	private static String logPath = prop.getProperty("LogPath");

	private static char separator = (char) StaticMethod.getIntValue(prop
			.getProperty("Separator"), 2);

	private static int connectionType = StaticMethod.getIntValue(prop
			.getProperty("ConnectionType", "1"));

	private static String databaseType = prop.getProperty("DatabaseType");

	private static String jdbcDriverName = prop.getProperty("DriverClassName");

	private static String jdbcUrl = prop.getProperty("url");

	private static boolean needReloadProperty = Boolean.getBoolean(prop
			.getProperty("NeedReloadProperty", "true"));

	private Vector array;

	private String columnName[];

	private int columnType[];

	private String args[];

	private Connection conn;

	private ConnectionPool pool;

	private boolean bSuccess;

}
