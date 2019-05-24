/**
 * 
 */
package com.boco.eoms.duty.bo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.bo.BO;
import com.boco.eoms.common.dao.SaveSessionBeanDAO;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.duty.dao.TawRmAssignSubDAO;
import com.boco.eoms.duty.dao.TawRmAssignworkDAO;
import com.boco.eoms.duty.util.DutyMgrLocator;

/**
 * @author FengShaoHong
 * 
 */
public class TawModelAssignXLSBO extends BO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7269711308173150435L;

	/**
	 * 将xls中的值班存入数据库
	 */
	public boolean xlsToDate(String url, int roomId) {
		try {
			
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(url));
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow row = null;
			int cols = 0;
			int workid = 0;
			String allDutyDate = "";
			String dutydate = "";
			String starttime_defined = "";
			String endtime_defined = "";
			String dutymaster = "";
			TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);
			TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
			TawSystemUserDao tawSystemUserDao=(TawSystemUserDao)ApplicationContextHolder.getInstance().getBean("tawSystemUserDao");
			int temp = 0;
			for (Iterator rit = sheet.rowIterator(); rit.hasNext();) {
				if (temp == 0) {
					rit.next();
					temp = 1;
				} else {
					row = (HSSFRow) rit.next();
					cols = row.getPhysicalNumberOfCells();
			//	dutydate = StaticMethod.date2String(row.getCell(0).getDateCellValue());	
					dutydate = row.getCell(0).getStringCellValue();
					if("".equals(dutydate)){break;}
					starttime_defined =row.getCell(1).getStringCellValue();
					endtime_defined = row.getCell(2).getStringCellValue();
					dutymaster = row.getCell(3).getStringCellValue();
					List list1 = tawSystemUserDao.getUsersByName(dutymaster);
					dutymaster =((TawSystemUser) list1.get(0)).getUserid();
							if (!"".equals(dutydate)) {
						if (allDutyDate.equals(dutydate)) {
							workid++;
						} else {
							workid = 0;
							allDutyDate = dutydate;
							tawRmAssignSubDAO
							.delete_room_date(roomId, dutydate);
							 tawRmAssignworkDAO.delete_room_date(roomId,
									 dutydate);
							 
						}
						
						 tawRmAssignworkDAO.insert(roomId, dutydate,
						 workid,dutymaster, starttime_defined,
						 endtime_defined);

						int workserial = tawRmAssignworkDAO.get_id(roomId,
								dutydate, workid);
						for (int j = 4; j < cols; j++) {
							String dutyman = row.getCell(j)
									.getStringCellValue();
							List list2 = tawSystemUserDao.getUsersByName(dutyman);
							dutyman = ((TawSystemUser) list2.get(0)).getUserid();
							tawRmAssignSubDAO.insert(workserial, dutyman);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public String exportUrl(String roomId, String timeForm, String timeTo) {
		Vector vectorQueryResult = new Vector();
		String url = "";
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = null;
		try {
			String timeTag = StaticMethod.getCurrentDateTime("yyyyMMddHH");
			String uploadPath = DutyMgrLocator.getAttributes()
					.getDutyRootPath()
					+ "/";

			String sysTemPaht = StaticMethod.getWebPath();

			url = sysTemPaht + "/" + uploadPath + timeTag + ".xls";
			tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(
					Integer.parseInt(roomId)), 0);
			String roomName = tawApparatusroom.getRoomname();
			// 建立输出流
			FileOutputStream fos = new FileOutputStream(url);
			// 建立一个新的工作表
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();

			HSSFRow row = null; // 建立新行
			HSSFCell cell = null; // 建立新cell
			HSSFCellStyle cellStyle = wb.createCellStyle();
			; // 样式
			cellStyle.setWrapText(true);
			cellStyle.setVerticalAlignment((short) 1); // 设置垂直居中
			cellStyle.setAlignment((short) 2); // 设置水平居中

			// 写信息－start
			// TawRmRecordDAO tawRmRecordDAO = new TawRmRecordDAO(ds);
			int y = 0;
			// TawRmRecord tawRmRecord = null;

			// List tawRmRecords = null;

			/** ********************配置常量单元格信息 start************************ */
			// 常量值
			String[] valueOfLine = { "值班日期", "开始时间", "结束时间", "值班长", "值班人员","值班人员" };
			// 对应的行
			int[] rowOfLine = { 0, 0, 0, 0, 0, 0 };
			// 对应的列
			int[] colOfLine = { 0, 1, 2, 3, 4, 5 };
			// xy
			int[][] xyOfLine = { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 },
					{ 1, 1 }, { 1, 1 } };

			vectorQueryResult = this.getQueryResultVector(Integer
					.parseInt(roomId), timeForm, timeTo);

			wb.setSheetName(0, roomName, HSSFWorkbook.ENCODING_UTF_16);

			// 定义常量单元格
			for (int i = 0; i < valueOfLine.length; i++) {
				row = sheet.createRow((short) rowOfLine[i]); // 建立新行
				cell = row.createCell((short) colOfLine[i]); // 建立新cell
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(valueOfLine[i]);

				sheet.addMergedRegion(new Region((short) rowOfLine[i],
						(short) (colOfLine[i]), (short) rowOfLine[i], (short) (colOfLine[i])));

			}
			Vector getDutydate = (Vector) vectorQueryResult.elementAt(0);
			Vector getStarttime = (Vector) vectorQueryResult.elementAt(1);
			Vector getEndtime = (Vector) vectorQueryResult.elementAt(2);
			Vector getDutymaster = (Vector) vectorQueryResult.elementAt(3);
			Vector getDutyman = (Vector) vectorQueryResult.elementAt(4);

			y = 1;
			// 循环处理执行内容
			for (int i = 0; i < getDutydate.size(); i++) {// tawRmRecords.size();

				// tawRmRecord = (TawRmRecord) tawRmRecords.get(i);

				// 新的行
				row = sheet.createRow((short) (i + 1));
				sheet.addMergedRegion(new Region((short) ((i) + y), (short) 0,
						(short) ((i) + y), (short) 0));
				// HSSFCellStyle style = wb.createCellStyle();
				// style.setAlignment(HSSFCellStyle.ALIGN_CENTER );
				cell = row.createCell((short) 0);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(getDutydate.elementAt(i) + "");
	
				sheet.addMergedRegion(new Region((short) ((i) + y), (short) 1,
						(short) ((i) + y), (short) 1));
				cell = row.createCell((short) 1);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(getStarttime.elementAt(i) + "");

				sheet.addMergedRegion(new Region((short) ((i) + y), (short) 2,
						(short) ((i) + y), (short) 2));
				cell = row.createCell((short) 2);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(getEndtime.elementAt(i) + "");

				sheet.addMergedRegion(new Region((short) ((i) + y), (short) 3,
						(short) ((i) + y), (short) 3));
				cell = row.createCell((short) 3);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(getDutymaster.elementAt(i) + "");
				
				Vector man = StaticMethod.getVector(String.valueOf(
						getDutyman.elementAt(i)).trim(), " ");
                for(int c=0;c<man.size();c++)
                {
				sheet.addMergedRegion(new Region((short) ((i) + y), (short) (4+c),
						(short) ((i) + y), (short) (4+c)));
				cell = row.createCell((short) (4+c));

				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(man.get(c) + "");
                }
				y++;

			}

			wb.write(fos);
			fos.close(); // 关闭输出流

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	public Vector getQueryResultVector(int roomId, String time_from,
			String time_to) throws SQLException {
		Vector getVector = new Vector();
		Vector getDutydate = new Vector();
		Vector getStarttime = new Vector();
		Vector getEndtime = new Vector();
		Vector getDutymaster = new Vector();
		Vector getDutyman = new Vector();
		String strDutydate = "";
		Vector vectDutydate = new Vector();
		TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			HashMap map = this.getSubDutyMan(roomId, time_from, time_to);
			SaveSessionBeanDAO saveSessionBeanDAO = new SaveSessionBeanDAO(ds);
			saveSessionBeanDAO.alterDateFormat();
			conn = ds.getConnection();
			String sql = "";
			sql = "select a.id,a.dutydate,a.starttime_defined,a.endtime_defined,b.username from taw_rm_assignwork a,taw_system_user b WHERE a.room_id = ? and a.dutydate >= ? and a.dutydate <= ? and b.userid = a.dutymaster order by a.starttime_defined";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			pstmt.setString(2, time_from);
			pstmt.setString(3, time_to);
			rs = pstmt.executeQuery();
			// null
			sql = "";
			while (rs.next()) {
				strDutydate = rs.getString(2).trim();
				vectDutydate = StaticMethod.getVector(strDutydate, " ");
				strDutydate = String.valueOf(vectDutydate.elementAt(0)).trim();
				getDutydate.add(strDutydate);
				getStarttime.add(StaticMethod.StringReplace(rs.getString(3)
						.trim(), ".0", ""));
				getEndtime.add(StaticMethod.StringReplace(rs.getString(4)
						.trim(), ".0", ""));
				// edit by wangheqi 2.7to3.5
				// TawSystemUserRoleBo userbo =
				// TawSystemUserRoleBo.getInstance();
				// TawSystemUser tawRmUser = userbo.getUserByuserid(StaticMethod
				// .dbNull2String(rs.getString(5)).trim());
				// edit end
				// TawRmUserDAO tawRmUserDAO = new TawRmUserDAO(ds);
				getDutymaster.add(StaticMethod.null2String(rs.getString(5))
						.trim());
				getDutyman.add(map.get(rs.getString(1)));
			}
			map = null;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			rs.close();
			pstmt.close();
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
		getVector.add(getDutydate);
		getVector.add(getStarttime);
		getVector.add(getEndtime);
		getVector.add(getDutymaster);
		getVector.add(getDutyman);

		// null
		tawRmAssignSubDAO = null;
		// tawRmUserDAO=null;
		rs = null;
		getDutydate = null;
		getStarttime = null;
		getEndtime = null;
		getDutymaster = null;
		getDutyman = null;
		strDutydate = null;
		vectDutydate = null;
		return getVector;
	}

	// 将值班人员的数据一次性取出，根据班次的ID拼写值班人员名称的字符串，放到MAP中，减少获取值班人员信息时对taw_rm_assign_sub表的查询次数
	public HashMap getSubDutyMan(int roomId, String time_from, String time_to) throws SQLException {
		HashMap subDutyMain = new HashMap();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String temp = "";
		String DutymanStr = "";
		try {
			conn = ds.getConnection();
			String sql = "select a.workserial,a.dutyman,b.username from taw_rm_assign_sub a,taw_system_user b where b.userid=a.dutyman and a.workserial in (select id from taw_rm_assignwork WHERE room_id = ? and dutydate >= ? and dutydate <= ?) order by a.workserial";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			pstmt.setString(2, time_from);
			pstmt.setString(3, time_to);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = StaticMethod.null2String(rs.getString(1));
				if (temp.equals("") || id.equals(temp)) {
					DutymanStr = DutymanStr + " " + rs.getString(3).trim();
				} else {
					subDutyMain.put(temp, DutymanStr);
					DutymanStr = rs.getString(3).trim();
				}
				temp = StaticMethod.null2String(rs.getString(1));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			rs.close();
			pstmt.close();
		} finally {
			conn.close();
		}
		subDutyMain.put(temp, DutymanStr);
		return subDutyMain;
	}
}
