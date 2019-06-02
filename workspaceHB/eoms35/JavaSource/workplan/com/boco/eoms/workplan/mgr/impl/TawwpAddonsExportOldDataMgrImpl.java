package com.boco.eoms.workplan.mgr.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.util.ConnStatement;
import com.boco.eoms.db.util.RecordSet;
import com.boco.eoms.workplan.mgr.ITawwpAddonsExportOldDataMgr;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpAddonsOldDataVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 10:05:31 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class TawwpAddonsExportOldDataMgrImpl implements
		ITawwpAddonsExportOldDataMgr {
	private String[] elementAttribute = TawwpStaticVariable.elementAttribute;

	private String[] showTypeValue = TawwpStaticVariable.showTypeValue;

	/**
	 * 导出历史模版原型数据到 WEB-INF/originXML/old/[model_id]/[sheet_id].xml
	 * 
	 * @throws IOException
	 * @throws Exception
	 */

	public void addonsExportOldModelBO() throws IOException, Exception {
		// 导入模版
		RecordSet rtSheet = new RecordSet();
		RecordSet rtAttr = new RecordSet();
		RecordSet rtValue = new RecordSet();
		RecordSet rtAttrVO = new RecordSet();

		// 表信息
		String sheetID = "";
		String sheetName = "";
		String sheetModel = "";

		// 获得
		String url = "";
		// 结构设计

		// 获得所有表的信息
		String sql = "select * from taw_sheetname";
		rtSheet.execute(sql);
		int sheetNum = 0; // sheet的数量
		int eleNum = 0;
		sheetNum = rtSheet.getCounts();
		while (rtSheet.next()) {

			sheetName = StaticMethod.null2String(rtSheet
					.getString("sh_cname"));
			sheetID = rtSheet.getString("sheet_id");
			sheetModel = rtSheet.getString("module_id");

			List sheetTable = new ArrayList();
			// List sheetTable=null;
			TawwpAddonsOldDataVO tawwpAddonsOldDataVO = null;
			// 获得taw_sheetattr的数量
			sql = "select count(*) as totle from taw_sheetattr where sheet_id='"
					+ sheetID + "'";
			rtAttr.execute(sql);
			rtAttr.next();
			eleNum = Integer.parseInt(rtAttr.getString("totle"));

			// 获得taw_sheetvalue的数量
			sql = "select count(*) as totle from taw_sheetvalue where sheet_id='"
					+ sheetID + "'";
			rtValue.execute(sql);
			rtValue.next();
			eleNum = eleNum + Integer.parseInt(rtValue.getString("totle"));

			for (int i = 1; i <= eleNum; i++) {

				sql = "select * from taw_sheetattr where sheet_id='" + sheetID
						+ "' and index1='" + i + "'";

				rtAttrVO.execute(sql);
				// 如果存在该记录
				if (rtAttrVO.next()) {
					tawwpAddonsOldDataVO = new TawwpAddonsOldDataVO();
					tawwpAddonsOldDataVO.setAorv("a");
					tawwpAddonsOldDataVO.setShowtype(6);
					tawwpAddonsOldDataVO.setValue(rtAttrVO
							.getString("attr_name"));
					tawwpAddonsOldDataVO.setIsattach(rtAttrVO
							.getString("isattach"));
					tawwpAddonsOldDataVO
							.setIndex1(rtAttrVO.getString("index1"));
					tawwpAddonsOldDataVO.setNewline(rtAttrVO
							.getString("newline"));
					tawwpAddonsOldDataVO.setAlign(rtAttrVO.getString("align"));
					tawwpAddonsOldDataVO
							.setValign(rtAttrVO.getString("valign"));
					tawwpAddonsOldDataVO.setColspan(rtAttrVO
							.getString("colspan"));
					tawwpAddonsOldDataVO.setRowspan(rtAttrVO
							.getString("rowspan"));
					tawwpAddonsOldDataVO.setStarttime(rtAttrVO
							.getString("starttime"));
					tawwpAddonsOldDataVO.setEndtime(rtAttrVO
							.getString("endtime"));

					sheetTable.add(tawwpAddonsOldDataVO);

				} else { // 如果不存在
					sql = "select * from taw_sheetvalue where sheet_id='"
							+ sheetID + "' and index1='" + i + "'";
					rtValue.execute(sql);
					if (rtValue.next()) {

						tawwpAddonsOldDataVO = new TawwpAddonsOldDataVO();
						tawwpAddonsOldDataVO.setAorv("v");
						tawwpAddonsOldDataVO.setShowtype(rtValue
								.getInt("showtype"));
						tawwpAddonsOldDataVO.setValue(rtValue
								.getString("defaultval"));
						tawwpAddonsOldDataVO.setIsattach(rtValue
								.getString("isattach"));
						tawwpAddonsOldDataVO.setIsattach(rtValue
								.getString("isattach"));
						tawwpAddonsOldDataVO.setIndex1(rtValue
								.getString("index1"));
						tawwpAddonsOldDataVO.setNewline(rtValue
								.getString("newline"));
						tawwpAddonsOldDataVO.setAlign(rtValue
								.getString("align"));
						tawwpAddonsOldDataVO.setValign(rtValue
								.getString("valign"));
						tawwpAddonsOldDataVO.setColspan(rtValue
								.getString("colspan"));
						tawwpAddonsOldDataVO.setRowspan(rtValue
								.getString("rowspan"));
						tawwpAddonsOldDataVO.setStarttime(rtValue
								.getString("starttime"));
						tawwpAddonsOldDataVO.setEndtime(rtValue
								.getString("endtime"));
						sheetTable.add(tawwpAddonsOldDataVO);
					}
				}

			}

			// 存数据到模版中地址

			Element titleElement = new Element("title");

			titleElement.setAttribute(new Attribute("value", sheetName));
			titleElement.setAttribute(new Attribute("name", sheetID));
			titleElement.setAttribute(new Attribute("type", "1"));

			Document myDocument = new Document(titleElement);

			Element headElement = new Element("head");
			Element headElesElement = new Element("elements");

			Element bodyElement = new Element("body");
			Element bodyElesElement = new Element("elements");
			Element bodyEmtermElement = new Element("emterm");

			String newline = "1";

			for (int i = 0; i < sheetTable.size(); i++) { // 获取头元素数做循环
				Element bodyEleEmtermElement = new Element("element");

				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[0], ((TawwpAddonsOldDataVO) sheetTable
								.get(i)).getValue()));
				bodyEleEmtermElement
						.setAttribute(new Attribute(
								elementAttribute[1],
								showTypeValue[((TawwpAddonsOldDataVO) sheetTable
										.get(i)).getShowtype()]));

				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[2], ((TawwpAddonsOldDataVO) sheetTable
								.get(i)).getAorv()
								+ i));

				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[3], newline));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[4], ((TawwpAddonsOldDataVO) sheetTable
								.get(i)).getRowspan()));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[5], ((TawwpAddonsOldDataVO) sheetTable
								.get(i)).getColspan()));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[6], ((TawwpAddonsOldDataVO) sheetTable
								.get(i)).getAlign()));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[7], ((TawwpAddonsOldDataVO) sheetTable
								.get(i)).getValign()));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[8], "1"));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[9], "1"));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[10], "null"));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[13], ""));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[14], ""));

				/*
				 * /显示 bodyEleEmtermElement.setAttribute(new
				 * Attribute(elementAttribute[11], "00:00:00"));
				 * bodyEleEmtermElement.setAttribute(new
				 * Attribute(elementAttribute[12], "23:59:59"));
				 */
				// 原表
				if (!((TawwpAddonsOldDataVO) sheetTable.get(i)).getStarttime()
						.equals("")) {
					bodyEleEmtermElement.setAttribute(new Attribute(
							elementAttribute[11],
							((TawwpAddonsOldDataVO) sheetTable.get(i))
									.getStarttime()
									+ ":00"));
				} else {
					bodyEleEmtermElement.setAttribute(new Attribute(
							elementAttribute[11], "00:00:00"));
				}
				if (!((TawwpAddonsOldDataVO) sheetTable.get(i)).getEndtime()
						.equals("")) {
					bodyEleEmtermElement.setAttribute(new Attribute(
							elementAttribute[12],
							((TawwpAddonsOldDataVO) sheetTable.get(i))
									.getEndtime()
									+ ":00"));
				} else {
					bodyEleEmtermElement.setAttribute(new Attribute(
							elementAttribute[12], "23:59:59"));
				}

				newline = ((TawwpAddonsOldDataVO) sheetTable.get(i))
						.getNewline();

				bodyEmtermElement.addContent(bodyEleEmtermElement);
			}
			// 搭构head区域
			headElement.addContent(headElesElement);

			bodyElesElement.addContent(bodyEmtermElement);
			bodyElement.addContent(bodyElesElement);

			myDocument.getRootElement().addContent(headElement);
			myDocument.getRootElement().addContent(bodyElement);

			XMLOutputter outp = new XMLOutputter();

			TawwpUtil.mkDir(TawwpStaticVariable.rootDir
					+ TawwpStaticVariable.rootOriginXMLDir + "old");
			/*
			 * outp.output(myDocument, new FileOutputStream(
			 * TawwpStaticVariable.rootDir +
			 * TawwpStaticVariable.rootOriginXMLDir + "old/" + sheetID +
			 * "_model" + ".xml"));
			 */
			outp.output(myDocument, new FileOutputStream(
					TawwpStaticVariable.rootDir
							+ TawwpStaticVariable.rootOriginXMLDir + "old/"
							+ sheetID + ".xml"));
			url = TawwpStaticVariable.rootOriginXMLDir + "old/" + sheetID
					+ ".xml";
			// System.out.println("新的只读模板:" + sheetID + "_model" + ".xml");

			// 存数据到模版中地址结束
		}
		rtValue.givebackConnection();
		rtAttr.givebackConnection();
		rtSheet.givebackConnection();
		// System.out.println("模版总数sheetNum=" + sheetNum);

	}

	/**
	 * 导出全部都显示（时间）的历史模版数据到 WEB-INF/originXML/old/[model_id]/[sheet_id]_read.xml
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void addonsExportOldModelReadBO() throws IOException, Exception {
		// 导入模版
		RecordSet rtSheet = new RecordSet();
		RecordSet rtAttr = new RecordSet();
		RecordSet rtValue = new RecordSet();
		RecordSet rtAttrVO = new RecordSet();

		// 表信息
		String sheetID = "";
		String sheetName = "";
		String sheetModel = "";

		// 结构设计

		// 获得所有表的信息
		String sql = "select * from taw_sheetname";
		rtSheet.execute(sql);
		int sheetNum = 0; // sheet的数量
		int eleNum = 0;
		sheetNum = rtSheet.getCounts();
		while (rtSheet.next()) {

			sheetName = StaticMethod.null2String(rtSheet
					.getString("sh_cname"));
			sheetID = rtSheet.getString("sheet_id");
			sheetModel = rtSheet.getString("module_id");

			List sheetTable = new ArrayList();
			// List sheetTable=null;
			TawwpAddonsOldDataVO tawwpAddonsOldDataVO = null;
			// 获得taw_sheetattr的数量
			sql = "select count(*) as totle from taw_sheetattr where sheet_id='"
					+ sheetID + "'";
			rtAttr.execute(sql);
			rtAttr.next();
			eleNum = Integer.parseInt(rtAttr.getString("totle"));

			// 获得taw_sheetvalue的数量
			sql = "select count(*) as totle from taw_sheetvalue where sheet_id='"
					+ sheetID + "'";
			rtValue.execute(sql);
			rtValue.next();
			eleNum = eleNum + Integer.parseInt(rtValue.getString("totle"));

			for (int i = 1; i <= eleNum; i++) {

				sql = "select * from taw_sheetattr where sheet_id='" + sheetID
						+ "' and index1='" + i + "'";

				rtAttrVO.execute(sql);
				// 如果存在该记录
				if (rtAttrVO.next()) {
					tawwpAddonsOldDataVO = new TawwpAddonsOldDataVO();
					tawwpAddonsOldDataVO.setAorv("a");
					tawwpAddonsOldDataVO.setShowtype(6);
					tawwpAddonsOldDataVO.setValue(rtAttrVO
							.getString("attr_name"));
					tawwpAddonsOldDataVO.setIsattach(rtAttrVO
							.getString("isattach"));
					tawwpAddonsOldDataVO
							.setIndex1(rtAttrVO.getString("index1"));
					tawwpAddonsOldDataVO.setNewline(rtAttrVO
							.getString("newline"));
					tawwpAddonsOldDataVO.setAlign(rtAttrVO.getString("align"));
					tawwpAddonsOldDataVO
							.setValign(rtAttrVO.getString("valign"));
					tawwpAddonsOldDataVO.setColspan(rtAttrVO
							.getString("colspan"));
					tawwpAddonsOldDataVO.setRowspan(rtAttrVO
							.getString("rowspan"));
					tawwpAddonsOldDataVO.setStarttime(rtAttrVO
							.getString("starttime"));
					tawwpAddonsOldDataVO.setEndtime(rtAttrVO
							.getString("endtime"));

					sheetTable.add(tawwpAddonsOldDataVO);

				} else { // 如果不存在
					sql = "select * from taw_sheetvalue where sheet_id='"
							+ sheetID + "' and index1='" + i + "'";
					rtValue.execute(sql);
					if (rtValue.next()) {

						tawwpAddonsOldDataVO = new TawwpAddonsOldDataVO();
						tawwpAddonsOldDataVO.setAorv("v");
						tawwpAddonsOldDataVO.setShowtype(rtValue
								.getInt("showtype"));
						tawwpAddonsOldDataVO.setValue(rtValue
								.getString("defaultval"));
						tawwpAddonsOldDataVO.setIsattach(rtValue
								.getString("isattach"));
						tawwpAddonsOldDataVO.setIsattach(rtValue
								.getString("isattach"));
						tawwpAddonsOldDataVO.setIndex1(rtValue
								.getString("index1"));
						tawwpAddonsOldDataVO.setNewline(rtValue
								.getString("newline"));
						tawwpAddonsOldDataVO.setAlign(rtValue
								.getString("align"));
						tawwpAddonsOldDataVO.setValign(rtValue
								.getString("valign"));
						tawwpAddonsOldDataVO.setColspan(rtValue
								.getString("colspan"));
						tawwpAddonsOldDataVO.setRowspan(rtValue
								.getString("rowspan"));
						tawwpAddonsOldDataVO.setStarttime(rtValue
								.getString("starttime"));
						tawwpAddonsOldDataVO.setEndtime(rtValue
								.getString("endtime"));
						sheetTable.add(tawwpAddonsOldDataVO);
					}
				}

			}

			// 存数据到模版中地址

			Element titleElement = new Element("title");

			titleElement.setAttribute(new Attribute("value", sheetName));
			titleElement.setAttribute(new Attribute("name", sheetID));
			titleElement.setAttribute(new Attribute("type", "1"));

			Document myDocument = new Document(titleElement);

			Element headElement = new Element("head");
			Element headElesElement = new Element("elements");

			Element bodyElement = new Element("body");
			Element bodyElesElement = new Element("elements");
			Element bodyEmtermElement = new Element("emterm");

			String newline = "1";

			for (int i = 0; i < sheetTable.size(); i++) { // 获取头元素数做循环
				Element bodyEleEmtermElement = new Element("element");

				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[0], ((TawwpAddonsOldDataVO) sheetTable
								.get(i)).getValue()));
				bodyEleEmtermElement
						.setAttribute(new Attribute(
								elementAttribute[1],
								showTypeValue[((TawwpAddonsOldDataVO) sheetTable
										.get(i)).getShowtype()]));

				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[2], ((TawwpAddonsOldDataVO) sheetTable
								.get(i)).getAorv()
								+ i));

				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[3], newline));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[4], ((TawwpAddonsOldDataVO) sheetTable
								.get(i)).getRowspan()));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[5], ((TawwpAddonsOldDataVO) sheetTable
								.get(i)).getColspan()));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[6], ((TawwpAddonsOldDataVO) sheetTable
								.get(i)).getAlign()));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[7], ((TawwpAddonsOldDataVO) sheetTable
								.get(i)).getValign()));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[8], "1"));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[9], "1"));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[10], "null"));
				// 显示
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[11], "00:00:00"));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[12], "23:59:59"));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[13], ""));
				bodyEleEmtermElement.setAttribute(new Attribute(
						elementAttribute[14], ""));

				/*
				 * //原表
				 * if(!((TawwpAddonsOldDataVO)sheetTable.get(i)).getStarttime().equals("")){
				 * bodyEleEmtermElement.setAttribute(new
				 * Attribute(elementAttribute[11], ((TawwpAddonsOldDataVO)
				 * sheetTable.get(i)).getStarttime()+":00")); }else{
				 * bodyEleEmtermElement.setAttribute(new
				 * Attribute(elementAttribute[11], "00:00:00")); }
				 * if(!((TawwpAddonsOldDataVO)sheetTable.get(i)).getEndtime().equals("")){
				 * bodyEleEmtermElement.setAttribute(new
				 * Attribute(elementAttribute[12],
				 * ((TawwpAddonsOldDataVO)sheetTable.get(i)).getEndtime()+":00"));
				 * }else{ bodyEleEmtermElement.setAttribute(new
				 * Attribute(elementAttribute[12], "23:59:59")); }
				 */
				newline = ((TawwpAddonsOldDataVO) sheetTable.get(i))
						.getNewline();

				bodyEmtermElement.addContent(bodyEleEmtermElement);
			}
			// 搭构head区域
			headElement.addContent(headElesElement);

			bodyElesElement.addContent(bodyEmtermElement);
			bodyElement.addContent(bodyElesElement);

			myDocument.getRootElement().addContent(headElement);
			myDocument.getRootElement().addContent(bodyElement);

			XMLOutputter outp = new XMLOutputter();

			TawwpUtil.mkDir(TawwpStaticVariable.rootDir
					+ TawwpStaticVariable.rootOriginXMLDir + "old");
			outp.output(myDocument, new FileOutputStream(
					TawwpStaticVariable.rootDir
							+ TawwpStaticVariable.rootOriginXMLDir + "old/"
							+ sheetID + "_read" + ".xml"));
			// System.out.println("新的全显示模板:" + sheetID + "_read" + ".xml");

			// 存数据到模版中地址结束
		}
		rtValue.givebackConnection();
		rtAttr.givebackConnection();
		rtSheet.givebackConnection();
		// System.out.println("模版总数=" + sheetNum);

	}

	/**
	 * 导出历史数据到WEB-INF/saveXML/old/[model_id]/[sheet_id]/[BOCO_id].xml
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void addonsExportOldDataBO() throws IOException, Exception {
		// 导入模版
		RecordSet rtData = new RecordSet();
		RecordSet rtSheet = new RecordSet();
		RecordSet rtAttr = new RecordSet();
		RecordSet rtValue = new RecordSet();
		RecordSet rtAttrVO = new RecordSet();
		RecordSet rtBOCO = new RecordSet();

		// 表信息
		String sheetID = "";
		String sheetName = "";
		String sheetModel = "";
		String sql = "";
		String dataID = "";
		String boco_id = "";
		// 开始导入
		sql = "select sheet_id from taw_sheetname";
		rtData.execute(sql);
		while (rtData.next()) {
			// 需要导入的信息
			boco_id = rtData.getString("sheet_id");

			// sql = "select * from BOCO_" + boco_id;
			// modified by lijia 2005-12-08 只导出12月度的附加表历史数据
			sql = "select * from BOCO_" + boco_id
					+ " where systime > '2005-12-01 00:00:00'"
					+ " and systime < '2005-12-31 23:59:59'";
			rtBOCO.execute(sql);
			while (rtBOCO.next()) {
				System.gc();
				// System.out.println("开始转化表BOCO_" + boco_id);

				dataID = rtBOCO.getString("id");
				// 获得所有表的信息
				sql = "select * from taw_sheetname where sheet_id=" + boco_id;
				rtSheet.execute(sql);
				int sheetNum = 0; // sheet的数量
				int eleNum = 0;

				while (rtSheet.next()) {

					sheetName = StaticMethod.null2String(rtSheet
							.getString("sh_cname"));
					sheetID = rtSheet.getString("sheet_id");
					sheetModel = rtSheet.getString("module_id");

					List sheetList = new ArrayList();

					TawwpAddonsOldDataVO tawwpAddonsOldDataVO = null;
					// 获得taw_sheetattr的数量
					sql = "select count(*) as totle from taw_sheetattr where sheet_id='"
							+ sheetID + "'";
					rtAttr.execute(sql);
					rtAttr.next();
					eleNum = Integer.parseInt(rtAttr.getString("totle"));

					// 获得taw_sheetvalue的数量
					sql = "select count(*) as totle from taw_sheetvalue where sheet_id='"
							+ sheetID + "'";
					rtValue.execute(sql);
					rtValue.next();
					eleNum = eleNum
							+ Integer.parseInt(rtValue.getString("totle"));
					for (int i = 1; i <= eleNum; i++) {

						sql = "select * from taw_sheetattr where sheet_id='"
								+ sheetID + "' and index1='" + i + "'";

						rtAttrVO.execute(sql);
						// 如果存在该记录
						if (rtAttrVO.next()) {

							tawwpAddonsOldDataVO = new TawwpAddonsOldDataVO();
							tawwpAddonsOldDataVO.setAorv("a");
							tawwpAddonsOldDataVO.setShowtype(6);
							tawwpAddonsOldDataVO.setValue(rtAttrVO
									.getString("attr_name"));
							tawwpAddonsOldDataVO.setIsattach(rtAttrVO
									.getString("isattach"));
							tawwpAddonsOldDataVO.setIndex1(rtAttrVO
									.getString("index1"));
							tawwpAddonsOldDataVO.setNewline(rtAttrVO
									.getString("newline"));
							tawwpAddonsOldDataVO.setAlign(rtAttrVO
									.getString("align"));
							tawwpAddonsOldDataVO.setValign(rtAttrVO
									.getString("valign"));
							tawwpAddonsOldDataVO.setColspan(rtAttrVO
									.getString("colspan"));
							tawwpAddonsOldDataVO.setRowspan(rtAttrVO
									.getString("rowspan"));
							tawwpAddonsOldDataVO.setStarttime(rtAttrVO
									.getString("starttime"));
							tawwpAddonsOldDataVO.setEndtime(rtAttrVO
									.getString("endtime"));

							sheetList.add(tawwpAddonsOldDataVO);

						} else { // 如果不存在
							sql = "select * from taw_sheetvalue where sheet_id='"
									+ sheetID + "' and index1='" + i + "'";
							rtValue.execute(sql);
							if (rtValue.next()) {

								tawwpAddonsOldDataVO = new TawwpAddonsOldDataVO();
								tawwpAddonsOldDataVO.setAorv("v");
								tawwpAddonsOldDataVO.setShowtype(rtValue
										.getInt("showtype"));
								// 获取数据
								tawwpAddonsOldDataVO
										.setValue(rtBOCO.getString(rtValue
												.getString("v_name")));

								tawwpAddonsOldDataVO.setIsattach(rtValue
										.getString("isattach"));
								tawwpAddonsOldDataVO.setIsattach(rtValue
										.getString("isattach"));
								tawwpAddonsOldDataVO.setIndex1(rtValue
										.getString("index1"));
								tawwpAddonsOldDataVO.setNewline(rtValue
										.getString("newline"));
								tawwpAddonsOldDataVO.setAlign(rtValue
										.getString("align"));
								tawwpAddonsOldDataVO.setValign(rtValue
										.getString("valign"));
								tawwpAddonsOldDataVO.setColspan(rtValue
										.getString("colspan"));
								tawwpAddonsOldDataVO.setRowspan(rtValue
										.getString("rowspan"));
								tawwpAddonsOldDataVO.setStarttime(rtValue
										.getString("starttime"));
								tawwpAddonsOldDataVO.setEndtime(rtValue
										.getString("endtime"));
								sheetList.add(tawwpAddonsOldDataVO);
							}
						}
					}
					// 存数据到模版中地址

					Element titleElement = new Element("title");

					titleElement
							.setAttribute(new Attribute("value", sheetName));
					titleElement.setAttribute(new Attribute("name", sheetID));
					titleElement.setAttribute(new Attribute("type", "1"));

					Document myDocument = new Document(titleElement);

					Element headElement = new Element("head");
					Element headElesElement = new Element("elements");

					Element bodyElement = new Element("body");
					Element bodyElesElement = new Element("elements");
					Element bodyEmtermElement = new Element("emterm");

					String newline = "1";

					for (int i = 0; i < sheetList.size(); i++) { // 获取头元素数做循环
						Element bodyEleEmtermElement = new Element("element");

						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[0],
								((TawwpAddonsOldDataVO) sheetList.get(i))
										.getValue()));
						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[1],
								showTypeValue[((TawwpAddonsOldDataVO) sheetList
										.get(i)).getShowtype()]));

						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[2],
								((TawwpAddonsOldDataVO) sheetList.get(i))
										.getAorv()
										+ i));

						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[3], newline));
						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[4],
								((TawwpAddonsOldDataVO) sheetList.get(i))
										.getRowspan()));
						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[5],
								((TawwpAddonsOldDataVO) sheetList.get(i))
										.getColspan()));
						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[6],
								((TawwpAddonsOldDataVO) sheetList.get(i))
										.getAlign()));
						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[7],
								((TawwpAddonsOldDataVO) sheetList.get(i))
										.getValign()));
						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[8], "1"));
						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[9], "1"));
						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[10], "null"));
						// 显示
						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[11], "00:00:00"));
						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[12], "23:59:59"));
						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[13], ""));
						bodyEleEmtermElement.setAttribute(new Attribute(
								elementAttribute[14], ""));

						/*
						 * //原表
						 * if(!((TawwpAddonsOldDataVO)sheetTable.get(i)).getStarttime().equals("")){
						 * bodyEleEmtermElement.setAttribute(new
						 * Attribute(elementAttribute[11],
						 * ((TawwpAddonsOldDataVO)
						 * sheetTable.get(i)).getStarttime()+":00")); }else{
						 * bodyEleEmtermElement.setAttribute(new
						 * Attribute(elementAttribute[11], "00:00:00")); }
						 * if(!((TawwpAddonsOldDataVO)sheetTable.get(i)).getEndtime().equals("")){
						 * bodyEleEmtermElement.setAttribute(new
						 * Attribute(elementAttribute[12],
						 * ((TawwpAddonsOldDataVO)sheetTable.get(i)).getEndtime()+":00"));
						 * }else{ bodyEleEmtermElement.setAttribute(new
						 * Attribute(elementAttribute[12], "23:59:59")); }
						 */
						newline = ((TawwpAddonsOldDataVO) sheetList.get(i))
								.getNewline();

						bodyEmtermElement.addContent(bodyEleEmtermElement);
					}
					// 搭构head区域
					headElement.addContent(headElesElement);

					bodyElesElement.addContent(bodyEmtermElement);
					bodyElement.addContent(bodyElesElement);

					myDocument.getRootElement().addContent(headElement);
					myDocument.getRootElement().addContent(bodyElement);

					XMLOutputter outp = new XMLOutputter();

					TawwpUtil.mkDir(TawwpStaticVariable.rootDir
							+ TawwpStaticVariable.rootSaveXMLDir + "old/"
							+ sheetModel + "/" + sheetID);
					outp.output(myDocument, new FileOutputStream(
							TawwpStaticVariable.rootDir
									+ TawwpStaticVariable.rootSaveXMLDir
									+ "old/" + sheetModel + "/" + sheetID + "/"
									+ dataID + ".xml"));

					// System.out.println("新的数据:" + sheetID + "/" + dataID +
					// ".xml");
					// 存数据到模版中地址结束
					sheetNum++;
				}
				rtValue.givebackConnection();
				rtAttr.givebackConnection();
				rtSheet.givebackConnection();
				// System.out.println("调试信息：数据总数：" + sheetNum);
			}
			// 结束boco_id for
		}

	}

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
	 * StaticMethod.null2String(rtSheet.getString("sh_cname")); sheetID =
	 * rtSheet.getString("sheet_id"); sheetModel =
	 * rtSheet.getString("module_id"); url = "originXML/old/" + sheetModel + "/" +
	 * sheetID + ".xml"; // 插入数据 TawwpAddonsBO tawwpAddonsBO=new
	 * TawwpAddonsBO(); tawwpAddonsBO.addAddonsTable(sheetName, "", sheetModel,
	 * "", url); System.out.println("sheetName="+sheetName);
	 *  } catch (Exception e) { e.printStackTrace(); } } }
	 */

	/**
	 * 将原来的附加表模版信息导入到新的附加表中
	 */
	public void addonsDatatoAddonsTable() throws SQLException {
		// 定义基本的数据

		ConnStatement cstmt = new ConnStatement();
		com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
				.getInstance();
		com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
		PreparedStatement pstmt = null;

		// 导入模版
		RecordSet rtSheet = new RecordSet();

		// 表信息
		String sheetID = "";
		String sheetName = "";
		String sheetModel = "";
		String url = "";
		// 获得
		String sql = "";
		String insertSql = "";
		// 开始导入
		sql = "select * from taw_sheetname";

		rtSheet.execute(sql);
		while (rtSheet.next()) {
			try {
				// 中文名称
				sheetName = StaticMethod.null2String(rtSheet
						.getString("sh_cname"));
				sheetID = rtSheet.getString("sheet_id");
				sheetModel = rtSheet.getString("module_id");
				url = "originXML/old/" + sheetModel + "/" + sheetID + ".xml";
				System.out.println("sheetName:" + sheetName);
				System.out.println("sheetID:" + sheetID);
				// 插入数据
				insertSql = "insert into taw_wp_addonstable (id,model,name,url) values ('"
						+ sheetID
						+ "','"
						+ sheetModel
						+ "','"
						+ sheetName
						+ "','" + url + "')";

				// //////////////// //
				pstmt = conn.prepareStatement(insertSql);
				pstmt.executeUpdate();
				conn.commit();

				// 执行insert
				cstmt.setStatementSql(insertSql);
				cstmt.executeUpdate();
				cstmt.commit();

			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}

		}

	}

}
