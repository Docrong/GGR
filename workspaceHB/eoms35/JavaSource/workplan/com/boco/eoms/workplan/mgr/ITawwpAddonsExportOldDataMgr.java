package com.boco.eoms.workplan.mgr;

import java.io.IOException;
import java.sql.SQLException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 10:03:53 AM
 * </p>
 * 
 * @author eoms
 * @version 3.5.1
 * 
 */
public interface ITawwpAddonsExportOldDataMgr {

	/**
	 * 导出历史模版原型数据到 WEB-INF/originXML/old/[model_id]/[sheet_id].xml
	 * 
	 * @throws IOException
	 * @throws Exception
	 */

	public void addonsExportOldModelBO() throws IOException, Exception;

	/**
	 * 导出全部都显示（时间）的历史模版数据到 WEB-INF/originXML/old/[model_id]/[sheet_id]_read.xml
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void addonsExportOldModelReadBO() throws IOException, Exception;

	/**
	 * 导出历史数据到WEB-INF/saveXML/old/[model_id]/[sheet_id]/[BOCO_id].xml
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void addonsExportOldDataBO() throws IOException, Exception;

	/**
	 * 将原来的附加表模版信息导入到新的附加表中
	 */
	/*
	 * public void addonsDatatoAddonsTable() throws SQLException { //定义基本的数据
	 * 
	 * ConnStatement cstmt = new ConnStatement();
	 * 
	 * //导入模版 RecordSet rtSheet = new RecordSet();
	 * 
	 * //表信息 String sheetID = ""; String sheetName = ""; String sheetModel = "";
	 * String url = ""; //获得 String sql = ""; String insertSql = ""; //开始导入 sql =
	 * "select sheet_id from taw_sheetname";
	 * 
	 * rtSheet.execute(sql); while (rtSheet.next()) { try { //中文名称 sheetName =
	 * StaticMethod.dbNull2String(rtSheet.getString("sh_cname")); sheetID =
	 * rtSheet.getString("sheet_id"); sheetModel =
	 * rtSheet.getString("module_id"); url = "originXML/old/" + sheetModel + "/" +
	 * sheetID + ".xml"; // 插入数据 TawwpAddonsBO tawwpAddonsBO=new
	 * TawwpAddonsBO(); tawwpAddonsBO.addAddonsTable(sheetName, "", sheetModel,
	 * "", url); System.out.println("sheetName="+sheetName); } catch (Exception
	 * e) { e.printStackTrace(); } } }
	 */

	/**
	 * 将原来的附加表模版信息导入到新的附加表中
	 */
	public void addonsDatatoAddonsTable() throws SQLException;

}
