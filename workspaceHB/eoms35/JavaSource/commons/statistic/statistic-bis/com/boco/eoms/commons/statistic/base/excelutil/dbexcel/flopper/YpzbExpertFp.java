package com.boco.eoms.commons.statistic.base.excelutil.dbexcel.flopper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Title:        
 * Description:  F:\work4\9-23\expert.xml F:\work4\9-23\_expert2.xml com.uncnet.dbexcel.flopper.YpzbExpertFp oracle.jdbc.driver.OracleDriver jdbc:oracle:thin:@127.0.0.1:1521:oracle scott tiger F:\work4\9-23\data\diploma.txt F:\work4\9-23\data\hospital.txt
 * Copyright:    Copyright (c) 2002
 * Company:      UNC
 * @author      zhouj@unc.com.cn
 * @version     1.0
 */
public class YpzbExpertFp extends DictFlopper {
	private String diploma = null;
	private String hospital = null;
	private String etype = null;
	private String trank = null;

	private ResultSet rs = null;
	private Statement stmt = null;

	private PreparedStatement pstmt = null;
	private String sqlscript =
		"insert into PLYPZB_EXPERT (ID, HOSPITALID, NAME, FNAME, LNAME, BIRTHDAY, DIPLOMA, ETYPE, TRANK, ARANK) values (?,?,?,?,?,?,?,?,?,?)";

	private int row_num = 0;
	private int col_num = 0;
	private static int COLUMNS = 10;
	//private static int CMT_ROW = 5;

	private String out_sql = null;
	private String[] ps_in = null; //new String[COLUMNS + 1];
	private boolean isToCmt = false;

	private Calendar calendar = Calendar.getInstance();
	private int yyyy = calendar.get(Calendar.YEAR);
	/**
	 * @see com.uncnet.dbexcel.Flopper#conversionOp(Connection connection, String, int, Document, Element, Element)
	 */
	public String conversionOp(
		Connection connection,
		String arg,
		int index,
		Document doc,
		Element table,
		Element row) {
		String text = null;
		if (arg == null)
			arg = "";
		try {
			//if (connection == null) {
			//			if (connection == null)
			//				connection = getConnection();
			if (connection == null)
				return "No Connection";
			stmt = connection.createStatement();
			if (col_num++ % COLUMNS == 0)
				ps_in = new String[COLUMNS + 1];

			switch (index) {
				case 1 :
					{
						text = findForHospital(hospital, arg, stmt);
						ps_in[0] = row_num + 100 + "";
						ps_in[1] = text;
						//						Element mc = doc.createElement("A");
						//						mc.appendChild(doc.createTextNode("test-" + arg));
						//						row.appendChild(mc);
						break;
					}
				case 2 :
					{
						ps_in[2] = arg;
						ps_in[3] = arg.substring(1);
						ps_in[4] = arg.substring(0, 1);
						break;
					}
				case 3 :
					{
						int age = 0;
						try {
							age = Integer.parseInt(arg);
						} catch (NumberFormatException nfe) {
							age = 0;
						}

						if (age == 0)
							ps_in[5] = "";
						else
							ps_in[5] = (yyyy - age - 1) + "";
					}
				case 4 :
					{
						text = findForDict(diploma, arg, stmt, 3);
						ps_in[6] = text;
						break;
					}
				case 5 :
					{
						text = findForDict(null, arg, stmt, 4);
						ps_in[7] = text;
						break;
					}
				case 7 :
					{
						text = findForDict(null, arg, stmt, 5);
						ps_in[8] = text;
						break;
					}
				case 8 :
					{
						ps_in[9] = arg;
					}
				default :
					{
						text = arg;
					}
			}
			if (col_num % COLUMNS == 0)
				isToCmt = true;
			else
				isToCmt = false;
			if (isToCmt) {
				pstmt = connection.prepareStatement(sqlscript);
				connection.setAutoCommit(false);
				//sqlscript += ");\n";
				row_num++;
				boolean isToExe = true;
				int hospital_id = 0,
					expert_id = 0,
					diploma = 0,
					etype = 0,
					trank = 0,
					year = 0;
				try {
					year = Integer.parseInt(ps_in[5]);
				} catch (NumberFormatException nfe) {
					year = 0;
				}
				try {
					hospital_id = Integer.parseInt(ps_in[1]);
					diploma = Integer.parseInt(ps_in[6]);
					etype = Integer.parseInt(ps_in[7]);
					trank = Integer.parseInt(ps_in[8]);
					expert_id = Integer.parseInt(ps_in[0]);
				} catch (NumberFormatException nfe) {
					isToExe = false;
				}
				if (year != 0) {
					calendar.set(Calendar.YEAR, year);
					calendar.set(Calendar.MONTH, 0);
					calendar.set(Calendar.DAY_OF_MONTH, 1);
				}
				pstmt.setInt(1, expert_id);
				pstmt.setInt(2, hospital_id);
				pstmt.setString(3, ps_in[2]);
				pstmt.setString(4, ps_in[3]);
				pstmt.setString(5, ps_in[4]);
				if (year != 0)
					pstmt.setDate(6, new Date(calendar.getTimeInMillis()));
				else
					pstmt.setDate(6, null);
				pstmt.setInt(7, diploma);
				pstmt.setInt(8, etype);
				pstmt.setInt(9, trank);
				pstmt.setString(10, ps_in[9]);

				for (int i = 0; i < COLUMNS; i++) {
					//					if (i == 1)
					//						pstmt.setInt(2, id);
					//					else
					//						pstmt.setString(i + 1, ps_in[i]);
					System.err.print(ps_in[i] + " ");
				}
				System.err.println();
				//ps_in = new String[COLUMNS + 1];
				if (isToExe) {
					pstmt.execute();
					connection.commit();
				} else
					System.err.print("Encountering null.");
				connection.setAutoCommit(true);
				if (pstmt != null)
					pstmt.close();

			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (isToCmt && pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		if (text == null) {
			table.removeChild(row);
		}
		return text;
	}

	private String findForHospital(String uri, String find, Statement stmt)
		throws SQLException {

		String text = null;
		String what = findForWhat(uri, find);
		if (what == null)
			what = find;
		//return "";
		String query =
			"select ID from PLYPZB_HOSPITAL where NAME = '" + what + "'";

		//System.err.println(stmt.closed);
		rs = stmt.executeQuery(query);
		if (rs.next())
			text = rs.getString(1);
		else
			text = "";
		return text;
	}

	private String findForDict(
		String uri,
		String find,
		Statement stmt,
		int type)
		throws SQLException {
		String text = null;
		String what = findForWhat(uri, find);
		if (what == null)
			what = find;
		//return "";
		String query =
			"select ID from PLYPZB_DICT where DICT_TYPE = "
				+ type
				+ " and DESCRIBE = '"
				+ what
				+ "'";

		//System.err.println(stmt.closed);
		rs = stmt.executeQuery(query);
		if (rs.next())
			text = rs.getString(1);
		else
			text = "";
		return text;
	}

	public void setParameter(String[] para) {
		int len = 4;
		String dbLink[] = new String[len];
		System.arraycopy(para, 0, dbLink, 0, len);
		super.setParameter(dbLink);
		diploma = para[len];
		hospital = para[len + 1];
		//etype = para[len + 1];
		//trank = para[len + 2];
		//System.out.println(diploma);
	}

	private long begin_time, end_time;

	public void beginConvert() {
		begin_time = System.currentTimeMillis();
	}
	public void endOfConvert() {
		end_time = System.currentTimeMillis();
		System.out.println("Parse time used:" + (end_time - begin_time) + "ms");
	}

	public void afterConnected(Connection conn) {
		String sql = "delete from PLYPZB_EXPERT";
		try {
			stmt = conn.createStatement();
//			stmt.execute(sql);
			System.err.println(sql + "...");
		} catch (SQLException e) {
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
	public void beforeClosed(Connection conn) {
	}
}
