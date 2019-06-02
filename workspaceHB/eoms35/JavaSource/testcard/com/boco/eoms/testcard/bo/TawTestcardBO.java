package com.boco.eoms.testcard.bo;

import java.lang.*;
import java.util.*;
import jxl.*;
import jxl.write.*;
import java.io.*;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.sparepart.util.myTreeMap;
import com.boco.eoms.testcard.controller.TawTestcardForm;
import com.boco.eoms.testcard.dao.TawTestcardDAO;
import com.boco.eoms.testcard.model.TawTestcard;
//import com.boco.eoms.common.util.myTreeMap;
import com.boco.eoms.testcard.util.MyTreeMap;

public class TawTestcardBO {
	public TawTestcardBO() {
	}

	/**
	 * 将模版信息导出成来访台帐Excel
	 * 
	 * @param _modelId
	 *            String 模版标识
	 * @return String 存储地址
	 */
	public String exportModelIn(String _Leave) {
		TawTestcardDAO dao = new TawTestcardDAO();
		int item = 18;// 表头长度
		String[] head = {
				"序号",
				// "册号",
				// "页号",
				"国家", "运营商", "省份", "城市", "卡号(ICCID)", "单卡编号", "MSISDN", "IMSI",
				"卡类型", "可用状态", "套餐", "费用情况", "归属HLR厂商", "归属HLR GT", "入库人",
				"存放位置", "备注", "修改状态" };
		String mapPath = TawwpStaticVariable.wwwDir
				+ "testcard/tempfiledownload";
		System.out.print(mapPath);
		Random aa = new Random();
		long realtime = aa.nextLong();
		String excelfilename = "excel" + realtime + _Leave + ".xls";
		WritableWorkbook excelworkbook;
		WritableSheet excelsheet;
		WritableCellFormat cellFormat;
		Label label;

		try {
			// 建立excel的题头，基本不用变化
			excelworkbook = Workbook.createWorkbook(new File(mapPath + "/"
					+ excelfilename));
			excelsheet = excelworkbook.createSheet("Sheet", 0);
			WritableFont arial12pt = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD);
			WritableCellFormat integerFormat = new WritableCellFormat (NumberFormats.TEXT); 
			cellFormat = new WritableCellFormat(arial12pt);
			cellFormat.setWrap(true);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormat.setBackground(Colour.LIGHT_TURQUOISE);
			label = new Label(0, 0, "测试卡", cellFormat);
			excelsheet.addCell(label);
			excelsheet.mergeCells(0, 0, item, 0);
			for (int i = 0; i < head.length; i++) {
				label = new Label(i, 1, head[i]);
				excelsheet.addCell(label);
			}
			List list = dao.loadInTestcard(_Leave);

			// 以下是根据excel列的不同，自行增加
			int excelRow = 2;
			for (int i = 0; i < list.size(); i++) {
				TawTestcard card = (TawTestcard) list.get(i);
				String tempstr = "";
				label = new Label(0, excelRow, String.valueOf(i + 1));
				excelsheet.addCell(label);
				// tempstr =
				// StaticMethod.nullObject2String(card.getVolumenum());
				// label = new Label(1, excelRow, tempstr);
				// excelsheet.addCell(label);
				// tempstr = StaticMethod.nullObject2String(card.getPagenum());
				// label = new Label(2, excelRow, tempstr);
				// excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getFromCountry());
				label = new Label(1, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getFromOpe());
				label = new Label(2, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getFromCrit());
				label = new Label(3, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getFromCity());
				label = new Label(4, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getIccid());
				label = new Label(5, excelRow, tempstr,integerFormat);
				excelsheet.addCell(label);

				tempstr = StaticMethod.nullObject2String(card.getOldNo());
				label = new Label(6, excelRow, tempstr,integerFormat);
				excelsheet.addCell(label);

				tempstr = StaticMethod.nullObject2String(card.getMsisdn());
				label = new Label(7, excelRow, tempstr,integerFormat);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getImsi());
				label = new Label(8, excelRow, tempstr,integerFormat);
				excelsheet.addCell(label);
				if (card.getCardType() == "" || card.getCardType() == null) {
					tempstr = StaticMethod
							.nullObject2String(card.getCardType());
				} else if (card.getCardType().equals("0")) {
					tempstr = "国内出访卡";
				} else if (card.getCardType().equals("1")) {
					tempstr = "国际来访卡";
				} else if (card.getCardType().equals("2")) {
					tempstr = "省际来访卡";
				} else if (card.getCardType().equals("3")) {
					tempstr = "省际出访卡";
				} else if (card.getCardType().equals("4")) {
					tempstr = "本地测试卡";
				} else if (card.getCardType().equals("5")) {
					tempstr = "省内来访卡";
				} else if (card.getCardType().equals("6")) {
					tempstr = "省内出访卡";
				} else {
					tempstr = String.valueOf(card.getCardType());
				}
				label = new Label(9, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getCardpackage());
				label = new Label(11, excelRow, tempstr);
				excelsheet.addCell(label);
				if (card.getState() == "" || card.getState() == null) {
					tempstr = StaticMethod.nullObject2String(card.getState());
				} else if (card.getState().equals("0")) {
					tempstr = "正常";
				} else if (card.getState().equals("1")) {
					tempstr = "停机";
				} else if (card.getState().equals("2")) {
					tempstr = "遗失";
				} else if (card.getState().equals("3")) {
					tempstr = "借出";
				} else if (card.getState().equals("4")) {
					tempstr = "使用";
				} else {
					tempstr = String.valueOf(card.getState());
				}
				label = new Label(10, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getExes());
				label = new Label(12, excelRow, tempstr);
				excelsheet.addCell(label);

				tempstr = StaticMethod.nullObject2String(card.getOffer());
				label = new Label(13, excelRow, tempstr);
				excelsheet.addCell(label);

				tempstr = StaticMethod.nullObject2String(card.getMsgcenterno());
				label = new Label(14, excelRow, tempstr);
				excelsheet.addCell(label);

				tempstr = StaticMethod.nullObject2String(card.getAdder());
				label = new Label(15, excelRow, tempstr);
				excelsheet.addCell(label);

				tempstr = StaticMethod.nullObject2String(card.getPosition());
				label = new Label(16, excelRow, tempstr);
				excelsheet.addCell(label);

				tempstr = StaticMethod.nullObject2String(card.getOperation());
				label = new Label(17, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = "0";
				label = new Label(18, excelRow, tempstr);
				excelsheet.addCell(label);
				// BocoLog.debug(this,1854,rs.getString(j));
				excelRow++;
			}

			// 关闭excel，基本不用变化
			excelworkbook.write();
			excelworkbook.close();
		} catch (Exception e) {
			System.out
					.println("create excel file " + excelfilename + " error!");
			e.printStackTrace();
			excelfilename = "index.jsp";
		}
		return excelfilename;
	}

	/**
	 * 将模版信息导出成出访台帐Excel
	 * 
	 * @param _modelId
	 *            String 模版标识
	 * @return String 存储地址
	 */
	public String exportModelOut(String _Leave) {
		TawTestcardDAO dao = new TawTestcardDAO();
		int item = 15;
		String[] head = { "序号", "国家", "运营商", "归属省份", "归属城市", "卡号(ICCID)",
				"MSISDN", "IMSI", "卡类型", "可用状态", "套餐", "使用省份", "使用城市", "接收人",
				"寄出时间", "备注" };
		String mapPath = TawwpStaticVariable.wwwDir
				+ "testcard/tempfiledownload";
		System.out.print(mapPath);
		Random aa = new Random();
		long realtime = aa.nextLong();
		String excelfilename = "excel" + realtime + _Leave + ".xls";
		WritableWorkbook excelworkbook;
		WritableSheet excelsheet;
		WritableCellFormat cellFormat;
		Label label;

		try {
			// 建立excel的题头，基本不用变化
			excelworkbook = Workbook.createWorkbook(new File(mapPath + "/"
					+ excelfilename));
			excelsheet = excelworkbook.createSheet("Sheet", 0);
			WritableFont arial12pt = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD);
			cellFormat = new WritableCellFormat(arial12pt);
			cellFormat.setWrap(true);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormat.setBackground(Colour.LIGHT_TURQUOISE);
			label = new Label(0, 0, "测试卡台帐", cellFormat);
			excelsheet.addCell(label);
			excelsheet.mergeCells(0, 0, item, 0);
			for (int i = 0; i < head.length; i++) {
				label = new Label(i, 1, head[i]);
				excelsheet.addCell(label);
			}
			List list = dao.loadOutTestcard(_Leave);

			// 以下是根据excel列的不同，自行增加
			int excelRow = 2;
			for (int i = 0; i < list.size(); i++) {
				TawTestcard card = (TawTestcard) list.get(i);
				String tempstr = "";
				label = new Label(0, excelRow, String.valueOf(i + 1));
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getFromCountry());
				label = new Label(1, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getFromOpe());
				label = new Label(2, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getFromCrit());
				label = new Label(3, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getFromCity());
				label = new Label(4, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getIccid());
				label = new Label(5, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getMsisdn());
				label = new Label(6, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getImsi());
				label = new Label(7, excelRow, tempstr);
				excelsheet.addCell(label);
				if (card.getCardType() == "" || card.getCardType() == null) {
					tempstr = StaticMethod
							.nullObject2String(card.getCardType());
				} else if (card.getCardType().equals("0")) {
					tempstr = "国内出访卡";
				} else if (card.getCardType().equals("1")) {
					tempstr = "国际来访卡";
				} else if (card.getCardType().equals("2")) {
					tempstr = "省际来访卡";
				} else if (card.getCardType().equals("3")) {
					tempstr = "省际出访卡";
				} else if (card.getCardType().equals("4")) {
					tempstr = "本地测试卡";
				} else if (card.getCardType().equals("5")) {
					tempstr = "省内来访卡";
				} else if (card.getCardType().equals("6")) {
					tempstr = "省内出访卡";
				} else {
					tempstr = String.valueOf(card.getCardType());
				}
				label = new Label(8, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getCardpackage());
				label = new Label(10, excelRow, tempstr);
				excelsheet.addCell(label);
				if (card.getState() == "" || card.getState() == null) {
					tempstr = StaticMethod.nullObject2String(card.getState());
				} else if (card.getState().equals("0")) {
					tempstr = "正常";
				} else if (card.getState().equals("1")) {
					tempstr = "停机";
				} else if (card.getState().equals("2")) {
					tempstr = "遗失";
				} else if (card.getState().equals("3")) {
					tempstr = "借出";
				} else if (card.getState().equals("4")) {
					tempstr = "使用";
				} else {
					tempstr = String.valueOf(card.getState());
				}
				label = new Label(9, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getToCrit());
				label = new Label(11, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getToCity());
				label = new Label(12, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getAdder());
				label = new Label(13, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getIntime());
				label = new Label(14, excelRow, tempstr);
				excelsheet.addCell(label);
				tempstr = StaticMethod.nullObject2String(card.getOperation());
				label = new Label(15, excelRow, tempstr);
				excelsheet.addCell(label);
				// BocoLog.debug(this,1854,rs.getString(j));
				excelRow++;
			}

			// 关闭excel，基本不用变化
			excelworkbook.write();
			excelworkbook.close();
		} catch (Exception e) {
			System.out
					.println("create excel file " + excelfilename + " error!");
			e.printStackTrace();
			excelfilename = "index.jsp";
		}
		return excelfilename;
	}

	/**
	 * @see 返回城市树图字符串
	 * @return 已经拼好的字符串，供js解析，生成动态下拉框
	 */
	public String getMyTreeStr() {
		String str = "";
		TawTestcardDAO dao = new TawTestcardDAO();
		MyTreeMap myTree = dao.getStrTree();
		Set mySet = myTree.myHashMap.entrySet();
		Iterator i = mySet.iterator();
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			Object ok = me.getKey();
			Object ov = me.getValue();
			str = str + "dsy.add('" + ok + "',[" + ov + "]);";
		}
		dao = null;
		return StaticMethod.null2String(str);
	}

	/**
	 * @see 返回拜访地城市树图字符串
	 * @return 已经拼好的字符串，供js解析，生成动态下拉框
	 */
	public String getToTreeStr() {
		String str = "";
		TawTestcardDAO dao = new TawTestcardDAO();
		MyTreeMap myTree = dao.getStrTree();
		Set mySet = myTree.myHashMap.entrySet();
		Iterator i = mySet.iterator();
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			Object ok = me.getKey();
			Object ov = me.getValue();
			str = str + "todsy.add('" + ok + "',[" + ov + "]);";
		}
		dao = null;
		return StaticMethod.null2String(str);
	}

	public String getMsisdn(String iccid) {

		String msisdn = "";

		TawTestcardDAO dao = new TawTestcardDAO();
		msisdn = dao.getMsisdn(iccid);
		return msisdn;
	}

	public String getLeave(String iccid) {

		String leave = "";

		TawTestcardDAO dao = new TawTestcardDAO();
		leave = dao.getMsisdn(iccid);
		return leave;
	}

	// 向测试卡主表信息中
	public void isertPhoneNumber(TawTestcardForm form) {
		TawTestcardDAO dao = new TawTestcardDAO();
		try {
			List list = this.getPhoneNumbers(form);
			for (int i = 0; i < list.size(); i++) {
				TawTestcard card = (TawTestcard) list.get(i);
				// 保存存放公司信息
				card.setLeave(form.getLeave());
				dao.insertNotAlive(card);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List getPhoneNumbers(TawTestcardForm form) {
		List list = new ArrayList();
		// 向LIST中增加起始号码信息
		TawTestcard cardBegin = new TawTestcard();
		String beginNumber = form.getBeginNumberPartOne()
				+ form.getBeginNumberPartTow() + form.getBeginNumberPartThree();
		cardBegin.setPhoneNumber(beginNumber);
		cardBegin.setState("-1");
		cardBegin.setIsAlive("0");
		list.add(cardBegin);
		// 向LIST中增加终止号码信息
		TawTestcard cardEnd = new TawTestcard();
		String endNumber = form.getEndNumberPartOne()
				+ form.getEndNumberPartTow() + form.getEndNumberPartThree();
		cardEnd.setPhoneNumber(endNumber);
		cardEnd.setState("-1");
		cardEnd.setIsAlive("0");
		list.add(cardEnd);
		if (form.getCheckBeginNumberPartOne() != null) {
			List tempOne = this.getNumbers(form.getBeginNumberPartOne(), form
					.getEndNumberPartOne());
			for (int i = 0; i < tempOne.size(); i++) {
				TawTestcard card = new TawTestcard();
				String number = this.getFillZero(StaticMethod
						.nullObject2int(tempOne.get(i)))
						+ form.getBeginNumberPartTow()
						+ form.getBeginNumberPartThree();
				card.setPhoneNumber(number);
				card.setState("-1");
				card.setIsAlive("0");
				list.add(card);
			}
		} else if (form.getCheckBeginNumberPartTow() != null) {
			List tempTow = this.getNumbers(form.getBeginNumberPartTow(), form
					.getEndNumberPartTow());
			for (int i = 0; i < tempTow.size(); i++) {
				TawTestcard card = new TawTestcard();
				String number = form.getBeginNumberPartOne()
						+ this.getFillZero(StaticMethod.nullObject2int(tempTow
								.get(i))) + form.getBeginNumberPartThree();
				card.setPhoneNumber(number);
				card.setState("-1");
				card.setIsAlive("0");
				list.add(card);
			}
		} else if (form.getCheckBeginNumberPartThree() != null) {
			List tempThree = this.getNumbers(form.getBeginNumberPartThree(),
					form.getEndNumberPartThree());
			for (int i = 0; i < tempThree.size(); i++) {
				TawTestcard card = new TawTestcard();
				String number = form.getBeginNumberPartOne()
						+ form.getBeginNumberPartTow()
						+ this.getFillZero(StaticMethod
								.nullObject2int(tempThree.get(i)));
				card.setPhoneNumber(number);
				card.setState("-1");
				card.setIsAlive("0");
				list.add(card);
			}
		}
		return list;
	}

	//
	public List getNumbers(String beginNumber, String endNumber) {
		List list = new ArrayList();
		int tempBeginNumber = Integer.parseInt(beginNumber);
		int tempEndNumber = Integer.parseInt(endNumber);
		int limit = tempEndNumber - tempBeginNumber;
		for (int i = 1; i < limit; i++) {
			tempBeginNumber = tempBeginNumber + 1;
			list.add(Integer.toString(tempBeginNumber));
		}
		return list;
	}

	public String getFillZero(int i) {
		String str = "";
		if (i < 10 && i >= 0) {
			str = "000" + i;
		} else if (i < 100 && i >= 10) {
			str = "00" + i;
		} else if (i < 1000 && i >= 100) {
			str = "0" + i;
		} else {
			str = Integer.toString(i);
		}
		return str;
	}
}
