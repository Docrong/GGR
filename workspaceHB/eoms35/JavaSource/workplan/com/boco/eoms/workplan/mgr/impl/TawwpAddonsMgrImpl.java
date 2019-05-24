package com.boco.eoms.workplan.mgr.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.workplan.dao.ITawwpAddonsTableDao;
import com.boco.eoms.workplan.mgr.ITawwpAddonsMgr;
import com.boco.eoms.workplan.model.TawwpAddonsTable;
import com.boco.eoms.workplan.util.CodeContentComsInfo;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpStaticVariable;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpAddonsElementVO;
import com.boco.eoms.workplan.vo.TawwpAddonsTableVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 9:36:16 AM
 * </p>
 * 
 * @author eoms
 * @version 3.5.1
 * 
 */
public class TawwpAddonsMgrImpl implements ITawwpAddonsMgr {

	private ITawwpAddonsTableDao tawwpAddonsTableDao;

	/**
	 * @param tawwpAddonsTableDao
	 *            the tawwpAddonsTableDao to set
	 */
	public void setTawwpAddonsTableDao(ITawwpAddonsTableDao tawwpAddonsTableDao) {
		this.tawwpAddonsTableDao = tawwpAddonsTableDao;
	}

	/**
	 * 业务逻辑：附加表的增加
	 * 
	 * @param _name
	 *            String 附加表名称
	 * @param _text
	 *            String 备注
	 * @param _model
	 *            String 模块
	 * @param _executeId
	 *            String 执行计划标识
	 * @param _url
	 *            String 地址
	 * @throws Exception
	 *             操作异常
	 */
	public void addAddonsTable(String _name, String _text, String _model,
			String _executeId, String _url) throws Exception {
		TawwpAddonsTableVO tawwpAddonsTableVO = null;
		TawwpAddonsTable tawwpAddonsTable = null;
		try {
			tawwpAddonsTable = new TawwpAddonsTable(_name, _text, _model,
					_executeId, _url);

			// 保存年度作业计划
			tawwpAddonsTableDao.saveAddonsTable(tawwpAddonsTable);
			tawwpAddonsTableVO = new TawwpAddonsTableVO();
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpAddonsTableVO,
					tawwpAddonsTable);
			TawwpStaticVariable.ADDONS_INF.put(tawwpAddonsTableVO.getId(),
					tawwpAddonsTableVO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("附加表保存出现异常");
		}

	}

	/**
	 * 业务逻辑：编辑附加表信息
	 * 
	 * @modifyDate 9/13/2005
	 * @param _id
	 *            String 附加表标识
	 * @param _name
	 *            String 附加表名称
	 * @param _text
	 *            String 备注
	 * @param _model
	 *            String 模块
	 * @param _url
	 *            String 地址
	 * @param _executeId
	 *            String 执行计划标识
	 * @throws Exception
	 *             操作异常
	 */
	public void editAddonsTable(String _id, String _name, String _text,
			String _model, String _executeId, String _url) throws Exception {
		TawwpAddonsTable tawwpAddonsTable = null;
		TawwpAddonsTableVO tawwpAddonsTableVO = null;
		try {

			tawwpAddonsTable = tawwpAddonsTableDao.loadAddonsTable(_id);
			if (tawwpAddonsTable != null) {
				// 封装附加表对象
				tawwpAddonsTable.setModel(_model);
				tawwpAddonsTable.setName(_name);
				tawwpAddonsTable.setText(_text);
				tawwpAddonsTable.setExecuteId(_executeId);
				tawwpAddonsTable.setUrl(_url);
				// 保存附加表
				tawwpAddonsTableDao.saveAddonsTable(tawwpAddonsTable);
				tawwpAddonsTableVO = new TawwpAddonsTableVO();
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpAddonsTableVO,
						tawwpAddonsTable); // 转换数据
				TawwpStaticVariable.ADDONS_INF.put(tawwpAddonsTableVO.getId(),
						tawwpAddonsTableVO);
			} else {
				throw new Exception("附加表不存在");
			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new Exception("附加表编辑出现异常");

		}
	}

	/**
	 * 业务逻辑：浏览附加表
	 * 
	 * @modifyDate 9/13/2005
	 * @param _id
	 *            String 附加表标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpAddonsTableVO
	 */
	public TawwpAddonsTableVO viewAddonsTableVO(String _id) throws Exception {

		TawwpAddonsTable tawwpAddonsTable = null;
		TawwpAddonsTableVO tawwpAddonsTableVO = null;

		try {

			tawwpAddonsTable = tawwpAddonsTableDao.loadAddonsTable(_id);
			if (tawwpAddonsTable != null) {
				// 不为空
				tawwpAddonsTableVO = new TawwpAddonsTableVO(); // 初始化附加表VO类
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpAddonsTableVO,
						tawwpAddonsTable); // 转换数据
				// 处理null,如果为空则转换为""
				tawwpAddonsTableVO.setText(StaticMethod
						.null2String(tawwpAddonsTableVO.getText()));
			} else {
				throw new TawwpException("附加表不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("附加表浏览出现异常");
		}
		return tawwpAddonsTableVO;
	}

	/**
	 * 业务逻辑：删除附加表
	 * 
	 * @modifyDate 9/13/2005
	 * @param _id
	 *            String 附加表标识
	 * @throws Exception
	 *             操作异常
	 */
	public void removeAddonsTable(String _id) throws Exception {
		TawwpAddonsTable tawwpAddonsTable = null;

		TawwpAddonsTableVO tawwpAddonsTableVO = null;
		try {
			tawwpAddonsTable = tawwpAddonsTableDao.loadAddonsTable(_id);
			tawwpAddonsTableVO = new TawwpAddonsTableVO();
			MyBeanUtils.copyPropertiesFromDBToPage(tawwpAddonsTableVO,
					tawwpAddonsTable); // 转换数据

			if (tawwpAddonsTableVO != null) {
				tawwpAddonsTableDao.deleteAddonsTable(tawwpAddonsTable);
				tawwpAddonsTableVO = new TawwpAddonsTableVO();
				TawwpStaticVariable.ADDONS_INF.remove(tawwpAddonsTableVO);
			} else {
				throw new Exception("附加表不存在");
			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new Exception("附加表删除出现异常");

		}
	}

	/**
	 * 业务逻辑：保存附加表XML
	 * 
	 * @modifyDate 9/20/2005
	 * @param _action
	 *            String 增加还是修改（是否已存在url文件）
	 * @param _model
	 *            String 所属模块标识
	 * @param _url
	 *            String XML的地址
	 * @param _v
	 *            Vector 存储了变量的Vector
	 * @param _myid
	 *            String 调用者生成的唯一标识
	 * @throws Exception
	 *             操作异常
	 * @return String 存储的相对地址
	 */
	public String saveAddons(String _action, String _model, String _url,
			Vector _v, String _myid) throws Exception {
		String rootSaveXMLDir = TawwpStaticVariable.rootSaveXMLDir; // 硬盘实际目录
		String rootDir = TawwpStaticVariable.rootDir; // web目录
		try {
			// 从文件构造一个Document
			Document _doc = getSAXBDoc(_url);
			// 得到根元素
			Element root = _doc.getRootElement();
			// 获得elements集合
			Element body = root.getChild("body").getChild("elements");
			// emterms
			List emtermsList = body.getChildren();
			int index = 0;
			for (int e = 0; e < emtermsList.size(); e++) {
				Element emterm = (Element) emtermsList.get(e);

				List emtermLists = emterm.getChildren();
				for (int i = 0; i < emtermLists.size(); i++) {
					TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
					// 获得单独的元素
					Element element = (Element) emtermLists.get(i);
					eleForm = eleForm.getElement(element);
					element.setAttribute(new Attribute("value",
					// informix
							(String) _v.get(index)));
					index++;
				}
			}

			XMLOutputter outp = new XMLOutputter();

			// 如果是增加就创建目录
			if (_action.equals("add")) {
				TawwpUtil.mkDir(rootDir + rootSaveXMLDir + _model);
			}
			FileOutputStream fos = new FileOutputStream(rootDir
					+ rootSaveXMLDir + _model + "/" + _myid + ".xml");
			outp.output(_doc, fos);
			fos.close();

			String newUrl = rootSaveXMLDir + _model + "/" + _myid + ".xml";
			return newUrl;

		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new Exception("附加表XML保存出现异常");
		}
	}

	/**
	 * 业务逻辑：获得附加表的表格的StringBuffer
	 * 
	 * @modifyDate 9/13/2005
	 * @param _url
	 *            String XML的地址
	 * @param _userName
	 *            String 用户名称
	 * @param _action
	 *            String 动作
	 * @throws Exception
	 * @return StringBuffer
	 */
	public StringBuffer getAddonsHtmlBuffer(String _url, String _userName,
			String _action) throws Exception {
		CodeContentComsInfo codeContent;
		StringBuffer optionBuffer = new StringBuffer("");

		StringBuffer StrBuffer = new StringBuffer("");
		try {
			// 从文件构造一个Document
			Document _doc = getSAXBDoc(_url);

			// 得到根元素
			Element root = _doc.getRootElement();
			Element element = null;

			// head部分
			// 得到head区域子元素
			Element headElement = root.getChild("head").getChild("elements");
			// 定义List
			List headList = headElement.getChildren();
			for (int h = 0; h < headList.size(); h++) {
				// 逐个获得element元素
				element = (Element) headList.get(h);
				// 得到封装element中元素的vo
				TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
				eleForm = eleForm.getElement(element);
				// 获得表格头部
				if (eleForm.getNewLine().equals("1")) {
					StrBuffer
							.append("</tr><tr class=\"tr_show\" onmouseover=\"current(this)\" id=\"0\">\r\n");
				}
				StrBuffer.append("<td class=\"td_label\" align=\""
						+ eleForm.getAlign() + "\" valign=\""
						+ eleForm.getValign() + "\" ROWSPAN=\""
						+ eleForm.getRows() + "\" COLSPAN=\""
						+ eleForm.getCols() + "\">\r\n");
				StrBuffer.append(eleForm.getValue());
				StrBuffer.append("</td>\r\n");
			}

			// body部分
			// 获得body区域子元素
			Element bodyElement = root.getChild("body").getChild("elements");
			// 定义List
			List bodyList = null;
			List emtermLists = null;
			// 定义最小值
			int order = 0;

			bodyList = bodyElement.getChildren();

			for (int e = 0; e < bodyList.size(); e++) {
				// 逐个获得emterm元素
				Element emterm = (Element) bodyList.get(e);
				emtermLists = emterm.getChildren();
				if (emtermLists.size() > 0) {
					order++;
				}
				for (int i = 0; i < emtermLists.size(); i++) {

					// 得到封装element中元素的vo
					TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
					// 逐个获得element元素
					element = (Element) emtermLists.get(i);
					eleForm = eleForm.getElement(element);
					// 验证时间
					SimpleDateFormat formatter = new SimpleDateFormat(
							"HH:mm:ss");
					Date curtime = new java.util.Date();
					String startTime = eleForm.getStartTime();
					String endTime = eleForm.getEndTime();
					try {
						Date now = formatter.parse(formatter.format(curtime));
						Date endT = formatter.parse(endTime);
						Date startT = formatter.parse(startTime);

						// 如果跨天且现在时间比起始时间大，就把结束时间+1天
						if (startT.getTime() > endT.getTime()
								&& now.getTime() > startT.getTime()) {
							endT
									.setTime(endT.getTime()
											+ (1000 * 60 * 60 * 24));
							// 如果跨天且现在时间比起始时间小，就把起始时间-1天
						} else if (startT.getTime() > endT.getTime()
								&& now.getTime() < startT.getTime()) {
							startT.setTime(startT.getTime()
									- (1000 * 60 * 60 * 24));
						}

						// 在规定时间之间或时间为空
						if (_action.equals("read")
								|| ((now.getTime() - startT.getTime()) >= 0 && (now
										.getTime() - endT.getTime()) <= 0)
								|| (endTime.equals("") && startTime.equals(""))) {
							// 如果不是第一个emterm,则可以删除该循环
							if (eleForm.getNewLine().equals("1")) {
								StrBuffer
										.append("</tr><tr onmouseover=\"current(this)\" id=\""
												+ e
												+ "\" class=\"tr_show\">\r\n");
							} else {
								if (eleForm.getNewLine().equals("1")) {
									StrBuffer
											.append("</tr><tr class=\"tr_show\">\r\n");
								}
							}

							StrBuffer.append("<td align=\""
									+ eleForm.getAlign() + "\" valign=\""
									+ eleForm.getValign() + "\" rowspan=\""
									+ eleForm.getRows() + "\" colspan=\""
									+ eleForm.getCols() + "\">\r\n");
							if (eleForm.getShowType().equals("text")) { // 显示文字
								StrBuffer.append(eleForm.getValue()
										+ "<input type=\"hidden\" name=\""
										+ eleForm.getName() + "_" + e
										+ "\" value=\"" + eleForm.getValue()
										+ "\">\r\n");

							} else if (eleForm.getShowType().equals("order")) {
								StrBuffer.append(eleForm.getValue()
										+ "<input type=\"hidden\" name=\""
										+ eleForm.getName() + "_" + e
										+ "\" value=\"" + order + "\">\r\n");

							} else if (eleForm.getShowType().equals("timer")) { // 自动填写时间
								StrBuffer.append(TawwpUtil.getCurrentDateTime()
										+ "<input type=\"hidden\" name=\""
										+ eleForm.getName() + "_" + e
										+ "\" dataType=\""
										+ eleForm.getValidateType()
										+ "\" value=\""
										+ TawwpUtil.getCurrentDateTime()
										+ "\">\r\n");
							} else if (eleForm.getShowType().equals("executer")) { // 自动填写执行人
								StrBuffer
										.append(_userName
												+ "<input type=\"hidden\" name=\""
												+ eleForm.getName() + "_" + e
												+ "\" dataType=\""
												+ eleForm.getValidateType()
												+ "\" value=\"" + _userName
												+ "\">\r\n");
							} else if (eleForm.getShowType().equals("file")) { // 附件
								String[] value = eleForm.getValue().split(",");
								int numU = 0;
								for (int c = 0; c < value.length; c++) {
									if (!value[c].equals("")) {
										numU++;
									}
								}
								StrBuffer
										.append("<input type=\"button\" size=\"2\" name=\""
												+ eleForm.getName()
												+ "_f"
												+ e
												+ "\" value=\""
												+ numU
												+ "\" Onclick=\"javascript:onFile("
												+ eleForm.getName()
												+ "_"
												+ e
												+ ","
												+ eleForm.getName()
												+ "_f"
												+ e
												+ ");\">个附件"
												+ "<input type=\"hidden\" name=\""
												+ eleForm.getName()
												+ "_"
												+ e
												+ "\" dataType=\""
												+ eleForm.getValidateType()
												+ "\" value=\""
												+ eleForm.getValue()
												+ "\">\r\n");

							} else if (eleForm.getShowType().equals("select")) { // 选择框
								StrBuffer.append("<select name=\""
										+ eleForm.getName() + "_" + e
										+ "\" dataType=\""
										+ eleForm.getValidateType()
										+ "\" >\r\n");
								// 获得字典数据
								codeContent = new CodeContentComsInfo(eleForm
										.getDicId());
								while (codeContent.next()) {
									optionBuffer.append("<option value='"
											+ codeContent.getCodeName() + "'>"
											+ codeContent.getCodeName()
											+ "</option>");
								}
								optionBuffer
										.append("<option value=''>无</option>");
								StrBuffer.append(optionBuffer);
								optionBuffer = new StringBuffer("");
								StrBuffer.append("</select>");
								StrBuffer
										.append("<script language=\"javascript\">\r\n"
												+ "document.addonssaveform."
												+ eleForm.getName()
												+ "_"
												+ e
												+ ".value = '"
												+ eleForm.getValue()
												+ "';"
												+ "</script>\r\n");
							} else { // 显示input
								StrBuffer.append("<input size=\"10\" name=\""
										+ eleForm.getName() + "_" + e
										+ "\" dataType=\""
										+ eleForm.getValidateType()
										+ "\" value=\"" + eleForm.getValue()
										+ "\">\r\n");
							}
							StrBuffer.append("</td>\r\n");
						} else { // 不在显示时间内
							// 获得表格主体部分
							/*
							 * if (eleForm.getShowType().equals("text")) {
							 * //显示文字 StrBuffer.append("<input type=\"hidden\"
							 * name=\"" + eleForm.getName() + "_" + e + "\"
							 * value=\"" + eleForm.getValue() + "\">\r\n"); }
							 * else if (eleForm.getShowType().equals("timer")) {
							 * //自动填写时间 StrBuffer.append("<input
							 * type=\"hidden\" name=\"" + eleForm.getName() +
							 * "_" + e + "\" value=\"" + eleForm.getValue() +
							 * "\">\r\n"); } else if
							 * (eleForm.getShowType().equals("executer")) {
							 * //自动执行人 StrBuffer.append("<input type=\"hidden\"
							 * name=\"" + eleForm.getName() + "_" + e + "\"
							 * value=\"" + eleForm.getValue() + "\">\r\n"); }
							 * else if (eleForm.getShowType().equals("file")) {
							 * //附件 StrBuffer.append("<input type=\"hidden\"
							 * name=\"" + eleForm.getName() + "_" + e + "\"
							 * dataType=\"" + eleForm.getValidateType() + "\"
							 * value=\"" + eleForm.getValue() + "\">\r\n"); }
							 * else { //显示input }
							 */
							if (eleForm.getShowType().equals("text")) { // 显示文字
								StrBuffer
										.append("<input type=\"hidden\" name=\""
												+ eleForm.getName()
												+ "_"
												+ e
												+ "\" value=\""
												+ eleForm.getValue()
												+ "\">\r\n");
							} else if (eleForm.getShowType().equals("order")) {
								StrBuffer
										.append("<input type=\"hidden\" name=\""
												+ eleForm.getName()
												+ "_"
												+ e
												+ "\" value=\""
												+ order
												+ "\">\r\n");

							}

							StrBuffer.append("<input type=\"hidden\" name=\""
									+ eleForm.getName() + "_" + e
									+ "\" value=\"" + eleForm.getValue()
									+ "\">\r\n");
							// }
						}
					} catch (Exception ec) {
						// 捕获异常
						ec.printStackTrace();
						throw new Exception("时间格式化出错");

					}
				}

			}
			return StrBuffer;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new Exception("附加表的表格的StringBuffer获取出现异常");
		}

	}

	/**
	 * 业务逻辑：获得表格的类型
	 * 
	 * @modifyDate 9/13/2005
	 * @param _url
	 *            String XML的地址
	 * @throws Exception
	 *             异常
	 * @return String 返回类型 表格的类型（1：普通表格，2：需要自增的特殊表格）
	 */
	public String getAddonsType(String _url) throws Exception {
		String type = "";
		try {
			// 从文件构造一个Document
			Document _doc = getSAXBDoc(_url);

			// 得到根元素
			Element root = _doc.getRootElement();
			type = root.getAttributeValue("type");
			return type;
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new Exception("附加表的类型获取出现异常");

		}
	}

	/**
	 * 删除某一行的元素
	 * 
	 * @param _action
	 *            String
	 * @param _url
	 *            String
	 * @param _model
	 *            String
	 * @param _myid
	 *            String
	 * @param _emtermid
	 *            String
	 * @throws Exception
	 * @return String
	 */
	public String addAddonsDelEmterm(String _action, String _url,
			String _model, String _myid, String _emtermid) throws Exception {
		String rootDir = TawwpStaticVariable.rootDir; // 硬盘实际目录
		try {
			// 从文件构造一个Document
			Document _doc = getSAXBDoc(_url);

			// 根节点
			Element element = _doc.getRootElement();
			Element body = element.getChild("body").getChild("elements"); // 获得elements集合

			// 如果是需要删除的emterm则删除该emterm
			Element delEmterm = _doc.getRootElement().getChild("body")
					.getChild("elements");
			List delEmtermList = delEmterm.getChildren();
			delEmtermList.remove(Integer.parseInt(_emtermid) - 1);

			// 创建新的emterm
			// Element elementEmterm = new Element("emterm");
			// 获得原有的emterm空元素结构
			// emterms
			java.util.List emtermsList = body.getChildren();
			Element emterm = (Element) emtermsList.get(0);
			java.util.List emtermLists = emterm.getChildren();
			// 最多包含多少行
			int maxRow = 0;
			// int emtermlistsize = emtermsList.size();

			// 获得基本的元素-每个循环包含几个行
			for (int i = 0; i < emtermLists.size(); i++) {
				TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
				// 获得单独的元素
				Element sElement = (Element) emtermLists.get(i);
				eleForm = eleForm.getElement(sElement);

				// 计算一个循环里面有多少个行
				if (Integer.parseInt(eleForm.getNewLine()) == 1) {
					maxRow++;
				}
			}

			// 修改第一行中循环元素的Y值原有的值-每个循环含有的行数
			for (int i = 0; i < emtermLists.size(); i++) {
				TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
				// 获得单独的元素
				Element sElement = (Element) emtermLists.get(i);
				eleForm = eleForm.getElement(sElement);
				if (eleForm.getCycle().equals("1")) {
					sElement.setAttribute(new Attribute("rows", (Integer
							.parseInt(eleForm.getRows()) - maxRow)
							+ ""));
				}
			}

			// int newLineTag = 0;
			// y需要改变的数量
			int changeRows = 0;

			List bodyList = body.getChildren();
			// 修改所有的y值
			for (int e = 0; e < bodyList.size(); e++) {
				// 逐个获得emterm元素
				emterm = (Element) bodyList.get(e);
				emtermLists = emterm.getChildren();

				if (e == (Integer.parseInt(_emtermid) - 1)) {
					changeRows = -maxRow;
				}
				// 如果changeRows修改了则将下面的数据全部修改
				if (changeRows < 0) {
					for (int i = 0; i < emtermLists.size(); i++) {

						// 得到封装element中元素的vo
						TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
						// 逐个获得element元素
						element = (Element) emtermLists.get(i);
						eleForm = eleForm.getElement(element);
						if (eleForm.getShowType().equals("order")) {
							element.setAttribute("value", (e + 1) + "");
						}
						element.setAttribute("y", (Integer.parseInt(eleForm
								.getY()) + changeRows)
								+ "");
					}
				} else {
					for (int i = 0; i < emtermLists.size(); i++) {
						// 得到封装element中元素的vo
						TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
						// 逐个获得element元素
						element = (Element) emtermLists.get(i);
						eleForm = eleForm.getElement(element);
						if (eleForm.getShowType().equals("order")) {
							element.setAttribute("value", (e + 1) + "");
						}
					}
				}
			}

			// 修改特殊表格跨行的y值以及rows
			// 勾画xml文件

			XMLOutputter outp = new XMLOutputter();

			// 本来是存在的
			FileOutputStream fos = new FileOutputStream(rootDir + _url);
			outp.output(_doc, fos);
			fos.close();

			return _url;

		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new Exception("特殊表2的横向元素表格元素删除出现异常");
		}
	}

	/**
	 * 业务逻辑:增加特殊表2的横向元素
	 * 
	 * @modifyDate 11/16/2005 by 翟耀纲
	 * @param _action
	 *            String 增加还是修改（是否已存在url文件）
	 * @param _url
	 *            String XML的地址
	 * @param _model
	 *            String 所属模块
	 * @param _myid
	 *            String 调用者生成的唯一标识
	 * @throws Exception
	 *             异常
	 * @return String
	 */
	public String addAddonsT2Element(String _action, String _url,
			String _model, String _myid) throws Exception {
		String rootDir = TawwpStaticVariable.rootDir; // 硬盘实际目录
		String rootSaveXMLDir = TawwpStaticVariable.rootSaveXMLDir; // web目录
		try {
			// 从文件构造一个Document
			Document _doc = getSAXBDoc(_url);

			// 根节点
			Element element = _doc.getRootElement();
			Element body = element.getChild("body").getChild("elements"); // 获得elements集合
			// 创建新的emterm
			Element elementEmterm = new Element("emterm");
			// 获得原有的emterm空元素结构
			// emterms
			java.util.List emtermsList = body.getChildren();
			Element emterm = (Element) emtermsList.get(0);
			java.util.List emtermLists = emterm.getChildren();
			// 最多包含多少行
			int maxRow = 0;

			// 获得基本的元素-每个循环包含几个行
			for (int i = 0; i < emtermLists.size(); i++) {
				TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
				// 获得单独的元素
				Element sElement = (Element) emtermLists.get(i);
				eleForm = eleForm.getElement(sElement);

				// 计算一个循环里面有多少个行
				if (Integer.parseInt(eleForm.getNewLine()) == 1) {
					maxRow = maxRow + 1;
				}
			}

			// 修改第一行中循环元素的rows值
			for (int i = 0; i < emtermLists.size(); i++) {
				TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
				// 获得单独的元素
				Element sElement = (Element) emtermLists.get(i);
				eleForm = eleForm.getElement(sElement);
				if (eleForm.getCycle().equals("1")) {
					sElement.setAttribute(new Attribute("rows", (Integer
							.parseInt(eleForm.getRows()) + maxRow)
							+ ""));
				}

			}
			int newLineTag = 0;
			int myY = emtermsList.size();
			// 增加新的循环元素
			for (int i = 0; i < emtermLists.size(); i++) {
				TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();

				Element elementEm = (Element) emtermLists.get(i);
				eleForm = eleForm.getElement(elementEm);

				Element _element = new Element("element");

				if (!eleForm.getCycle().equals("1")) { // 如果这个元素不是循环元素则增加
					_element.setAttribute(new Attribute("name", eleForm
							.getName()
							+ emtermsList.size()));
					_element.setAttribute(new Attribute("validateType", eleForm
							.getValidateType()));
					_element.setAttribute(new Attribute("rows", eleForm
							.getRows()));
					_element.setAttribute(new Attribute("cols", eleForm
							.getCols()));
					_element.setAttribute(new Attribute("showType", eleForm
							.getShowType()));
					if (newLineTag == 1) {
						_element.setAttribute(new Attribute("newLine", "1"));
					} else {
						_element.setAttribute(new Attribute("newLine", eleForm
								.getNewLine()));
					}
					_element.setAttribute(new Attribute("align", eleForm
							.getAlign()));
					_element.setAttribute(new Attribute("valign", eleForm
							.getAlign()));
					_element.setAttribute(new Attribute("startTime", eleForm
							.getStartTime()));
					_element.setAttribute(new Attribute("endTime", eleForm
							.getEndTime()));
					_element.setAttribute(new Attribute("dicId", eleForm
							.getDicId()));
					_element.setAttribute(new Attribute("cycle", eleForm
							.getCycle()));
					_element.setAttribute(new Attribute("x", eleForm.getX()));
					_element.setAttribute(new Attribute("y", (Integer
							.parseInt(eleForm.getY()) + maxRow * myY)
							+ ""));
					_element.setAttribute(new Attribute("value", ""));
					if (eleForm.getShowType().equals("order")) { // 增加新的元素
						_element.setAttribute(new Attribute("value",
								(emtermsList.size() + 1) + ""));
					}
					if (eleForm.getShowType().equals("text")) {
						_element.setAttribute(new Attribute("value", eleForm
								.getValue()));
					}

					elementEmterm.addContent(_element);
					newLineTag = 0;
				} else {
					newLineTag = 1;
				}

			}
			_doc.getRootElement().getChild("body").getChild("elements")
					.addContent(elementEmterm);
			// 勾画xml文件

			XMLOutputter outp = new XMLOutputter();

			// 如果是增加就创建目录
			if (_action.equals("add")) {
				TawwpUtil.mkDir(rootDir + rootSaveXMLDir + _model);
				String newUrl = rootSaveXMLDir + _model + "/" + _myid + ".xml";
				FileOutputStream fos = new FileOutputStream(rootDir + newUrl);
				outp.output(_doc, fos);
				fos.close();
				return newUrl;
			} else {
				FileOutputStream fos = new FileOutputStream(rootDir + _url);
				outp.output(_doc, fos);
				fos.close();

				return _url;
			}

		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new Exception("特殊表2的横向元素表格元素增加出现异常");
		}
	}

	/**
	 * 业务逻辑：获得SAXBuilder构造好的Document
	 * 
	 * @modifyDate 9/13/2005
	 * @param _url
	 *            String 地址
	 * @throws FileNotFoundException
	 *             没有找到文件
	 * @throws JDOMException
	 *             JDOM异常
	 * @return Document
	 * @throws FileNotFoundException
	 * @throws Exception
	 * @return Document
	 */

	public Document getSAXBDoc(String _url) throws FileNotFoundException,
			Exception {
		try {
			// 实际目录
			String rootDir = TawwpStaticVariable.rootDir;
			SAXBuilder sb = new SAXBuilder();

			// 从文件构造一个Document
			Document _doc = sb.build(new FileInputStream(rootDir + _url));
			if (_doc != null) {
				return _doc;
			} else {
				throw new FileNotFoundException("XML文件读取出现异常");
			}
		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
			throw new Exception("Document构造出现异常");
		}
	}

	/**
	 * 业务逻辑：根据模块名称获得附加表列表
	 * 
	 * @modifyDate 9/13/2005
	 * @param _model
	 *            String 模块表识
	 * @throws TawwpException
	 * @return Hashtable
	 */
	public Hashtable listAddons(String _model) throws TawwpException {
		Hashtable addonsTableHashtable = new Hashtable(); // 附加表hashtable
		List list = null;

		TawwpAddonsTable tawwpAddonsTable = null;
		TawwpAddonsTableVO tawwpAddonsTableVO = null;

		try {
			// 获取所有附加表的集合
			list = tawwpAddonsTableDao.listAddonsTable(_model);
			// 循环对每个附加表进行分类处理
			for (int i = 0; i < list.size(); i++) {
				tawwpAddonsTable = (TawwpAddonsTable) list.get(i); // 获取一个附加表
				tawwpAddonsTableVO = new TawwpAddonsTableVO();
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpAddonsTableVO,
						tawwpAddonsTable); // 将附加表信息导入到附加表显示类中
				addonsTableHashtable.put(tawwpAddonsTableVO.getId(),
						tawwpAddonsTableVO);

			}
			return addonsTableHashtable;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("附加表信息查询出现异常");
		} finally {
			list = null;
		}

	}

	/**
	 * 业务逻辑：根据作业计划获得附加表列表
	 * 
	 * @throws TawwpException
	 *             操作异常
	 * @return Hashtable 作业计划附加表列表
	 */
	public List listAddonsByWorkPlan() throws TawwpException {
		List list = null;
		List newList = new ArrayList();

		TawwpAddonsTable tawwpAddonsTable = null;
		TawwpAddonsTableVO tawwpAddonsTableVO = null;

		try {
			// 获取所有附加表的集合
			list = tawwpAddonsTableDao.listAddonsTable(String
					.valueOf(TawwpStaticVariable.GZJHDICTFLAG));
			// 循环对每个附加表进行分类处理
			for (int i = 0; i < list.size(); i++) {
				tawwpAddonsTable = (TawwpAddonsTable) list.get(i); // 获取一个附加表
				tawwpAddonsTableVO = new TawwpAddonsTableVO();
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpAddonsTableVO,
						tawwpAddonsTable); // 将附加表信息导入到附加表显示类中
				tawwpAddonsTableVO.setText(StaticMethod
						.null2String(tawwpAddonsTableVO.getText()));
				newList.add(tawwpAddonsTableVO);

			}
			return newList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("附加表信息查询出现异常");
		} finally {
			list = null;
		}
	}

	/**
	 * 业务逻辑：由附加表导出excel文件
	 * 
	 * @param _sourceXML
	 *            String
	 * @throws TawwpException
	 * @throws IOException
	 * @throws Exception
	 * @return String
	 */
	public String exportAddonsToExcel(String _sourceXML) throws TawwpException,
			IOException, Exception {

		String wwwDir = TawwpStaticVariable.wwwDir; // web目录
		String newDir = TawwpUtil.getCurrentDateTime("yyyy_MM_dd");

		TawwpUtil.mkDir(wwwDir + TawwpStaticVariable.tmpDownDir + newDir);
		String exportExcelUrl = "/" + TawwpStaticVariable.tmpDownDir + newDir
				+ "/" + TawwpUtil.getFileName(_sourceXML) + ".xls";

		String exportExcel = wwwDir + exportExcelUrl;

		// 生成的Excel地址
		FileOutputStream fos = new FileOutputStream(exportExcel);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		// String xmlType = getAddonsType(_sourceXML);
		wb.setSheetName(0, "附加表", HSSFWorkbook.ENCODING_UTF_16);

		HSSFRow row = sheet.createRow((short) 0); // 建立新行
		HSSFCell cell = null; // 建立新cell
		HSSFCellStyle cellStyle = wb.createCellStyle();
		; // 样式
		cellStyle.setWrapText(true);
		cellStyle.setVerticalAlignment((short) 1); // 设置垂直居中
		cellStyle.setAlignment((short) 2); // 设置水平居中

		// 写信息－start
		try {
			// 从文件构造一个Document
			Document _doc = getSAXBDoc(_sourceXML);
			// 得到根元素
			Element root = _doc.getRootElement();
			Element element = null;
			// head部分
			// 得到head区域子元素
			Element head = root.getChild("head").getChild("elements");
			// 定义List
			List headList = head.getChildren();
			for (int h = 0; h < headList.size(); h++) {
				// 逐个获得element元素
				element = (Element) headList.get(h);
				// 得到封装element中元素的vo
				TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
				eleForm = eleForm.getElement(element);
				// 定义单元格
				row = sheet.createRow((short) Integer.parseInt(eleForm.getY())); // 建立新行
				cell = row.createCell((short) Integer.parseInt(eleForm.getX())); // 建立新cell
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(eleForm.getValue());
				cell.setCellStyle(cellStyle);
				sheet.addMergedRegion(new Region((short) Integer
						.parseInt(eleForm.getY()), (short) Integer
						.parseInt(eleForm.getX()), (short) (Integer
						.parseInt(eleForm.getY())
						+ Integer.parseInt(eleForm.getRows()) - 1),

				(short) (Integer.parseInt(eleForm.getX())
						+ Integer.parseInt(eleForm.getCols()) - 1)));

			}
			// body部分
			// 获得elements集合
			Element body = root.getChild("body").getChild("elements");
			// 定义List
			List emtermsList = body.getChildren();
			for (int e = 0; e < emtermsList.size(); e++) {
				Element emterm = (Element) emtermsList.get(e);
				java.util.List emtermLists = emterm.getChildren();
				for (int i = 0; i < emtermLists.size(); i++) {
					TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
					// 获得单独的元素
					element = (Element) emtermLists.get(i);
					eleForm = eleForm.getElement(element);
					// 定义单元格
					row = sheet.createRow((short) Integer.parseInt(eleForm
							.getY())); // 建立新行
					cell = row.createCell((short) Integer.parseInt(eleForm
							.getX())); // 建立新cell
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(eleForm.getValue());
					cell.setCellStyle(cellStyle);
					sheet.addMergedRegion(new Region((short) Integer
							.parseInt(eleForm.getY()), (short) Integer
							.parseInt(eleForm.getX()), (short) (Integer
							.parseInt(eleForm.getY())
							+ Integer.parseInt(eleForm.getRows()) - 1),

					(short) (Integer.parseInt(eleForm.getX())
							+ Integer.parseInt(eleForm.getCols()) - 1)));
				}
			}
			// 写信息－end
			wb.write(fos);
			fos.close();
			return exportExcelUrl;

		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("附加表导出Excel文件出现异常！");
		}
	}

	public String exportAddonsToExcel(String _sourceXML,String outPut) throws TawwpException,
			IOException, Exception {

		String wwwDir = TawwpStaticVariable.wwwDir; // web目录
		//String newDir = TawwpUtil.getCurrentDateTime("yyyy_MM_dd");

		TawwpUtil.mkDir(wwwDir + outPut);
		String exportExcelUrl = "/" +outPut
				+ "/" + TawwpUtil.getFileName(_sourceXML)  ;
		exportExcelUrl=exportExcelUrl.replace(".xml", ".xls");
		String exportExcel = wwwDir + exportExcelUrl;

//		生成的Excel地址
	    FileOutputStream fos = new FileOutputStream(exportExcel);
	    HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet();
	    String xmlType = getAddonsType(_sourceXML);
	    wb.setSheetName(0, "附加表", HSSFWorkbook.ENCODING_UTF_16);

	    HSSFRow row = sheet.createRow( (short) 0); //建立新行
	    HSSFCell cell = null; //建立新cell
	    HSSFCellStyle cellStyle = wb.createCellStyle(); ; //样式
	    cellStyle.setWrapText(true);
	    cellStyle.setVerticalAlignment( (short) 1); //设置垂直居中
	    cellStyle.setAlignment( (short) 2); //设置水平居中

	    //写信息－start
	    try {
	      //从文件构造一个Document
	      Document _doc = getSAXBDoc(_sourceXML);
	      //得到根元素
	      Element root = _doc.getRootElement();
	      Element element = null;
	      //head部分
	      //得到head区域子元素
	      Element head = root.getChild("head").getChild("elements");
	      //定义List
	      List headList = head.getChildren();
	      for (int h = 0; h < headList.size(); h++) {
	        //逐个获得element元素
	        element = (Element) headList.get(h);
	        //得到封装element中元素的vo
	        TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
	        eleForm = eleForm.getElement(element);
	        //定义单元格
	        row = sheet.createRow( (short) Integer.parseInt(eleForm.getY())); //建立新行
	        cell = row.createCell( (short) Integer.parseInt(eleForm.getX())); //建立新cell
	        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	        cell.setCellValue(eleForm.getValue());
	        cell.setCellStyle(cellStyle);
	        sheet.addMergedRegion(new Region( (short) Integer.parseInt(eleForm.getY()),
	                                         (short) Integer.parseInt(eleForm.getX()),
	                                         (short) (Integer.parseInt(eleForm.getY()) +
	                                                  Integer.parseInt(eleForm.
	            getRows()) - 1),

	                                         (short) (Integer.parseInt(eleForm.getX()) +
	                                                  Integer.parseInt(eleForm.
	            getCols()) - 1)));

	      }
	      //body部分
	      //获得elements集合
	      Element body = root.getChild("body").getChild("elements");
	      //定义List
	      List emtermsList = body.getChildren();
	      for (int e = 0; e < emtermsList.size(); e++) {
	        Element emterm = (Element) emtermsList.get(e);
	        java.util.List emtermLists = emterm.getChildren();
	        for (int i = 0; i < emtermLists.size(); i++) {
	          TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
	          //获得单独的元素
	          element = (Element) emtermLists.get(i);
	          eleForm = eleForm.getElement(element);
	          //定义单元格
	          row = sheet.createRow( (short) Integer.parseInt(eleForm.getY())); //建立新行
	          cell = row.createCell( (short) Integer.parseInt(eleForm.getX())); //建立新cell
	          cell.setEncoding(HSSFCell.ENCODING_UTF_16);
	          cell.setCellValue(eleForm.getValue());
	          cell.setCellStyle(cellStyle);
	          sheet.addMergedRegion(new Region( (short) Integer.parseInt(eleForm.
	              getY()),
	                                           (short) Integer.parseInt(eleForm.
	              getX()),
	                                           (short) (Integer.parseInt(eleForm.
	              getY()) +
	              Integer.parseInt(eleForm.
	                               getRows()) - 1),

	                                           (short) (Integer.parseInt(eleForm.
	              getX()) +
	              Integer.parseInt(eleForm.
	                               getCols()) - 1)));
	        }
	      }
	      //写信息－end
	      wb.write(fos);
	      fos.close();
	      return exportExcel;

	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      throw new TawwpException("附加表导出Excel文件出现异常！");
	    }
			 
	}

	/**
	 * 业务逻辑：从excel导入附加表
	 * 
	 * @param _sourceExcel
	 *            String 用户上传的excel服务器地址
	 * @param _addonsXML
	 *            String 附加表模板
	 * @param _model
	 *            String 所属模块
	 * @param _myid
	 *            String 标记
	 * @throws TawwpException
	 *             异常
	 * @return String xml的存储地址
	 */
	public String importAddonsFromExcel(String _sourceExcel, String _addonsXML,
			String _model, String _myid) throws TawwpException {
		String rootSaveXMLDir = TawwpStaticVariable.rootSaveXMLDir; // 硬盘实际目录
		String rootDir = TawwpStaticVariable.rootDir; // web目录
		String _importXMLUrl = "";

		int hasData = 1;
		// 每行中最大的x值
		int maxX = 0;
		int maxY = 0;

		try {
			POIFSFileSystem fs = null;
			HSSFWorkbook wb = null;

			fs = new POIFSFileSystem(new FileInputStream(_sourceExcel));
			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = sheet.getRow(0);
			HSSFCell cell = null;
			// 获得源doc
			Document _doc = getSAXBDoc(_addonsXML);

			// 得到根元素
			Element root = _doc.getRootElement();
			// 获得elements集合
			Element body = root.getChild("body").getChild("elements");
			// emterms
			List emtermsList = body.getChildren();
			// 附加表的类型
			String addonsType = getAddonsType(_addonsXML);

			int index = 0;
			int maxRow = 0; // 一个循环里的行
			int emtermCycle = 0; // emterm增加的个数
			int newLineTag = 0;

			// 第一个数据按照数据一个一个的填入
			Element emterm = (Element) emtermsList.get(0);
			java.util.List emtermLists = emterm.getChildren();
			for (int i = 0; i < emtermLists.size(); i++) {
				TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
				// 获得单独的元素
				Element element = (Element) emtermLists.get(i);
				eleForm = eleForm.getElement(element);
				row = sheet.getRow(Integer.parseInt(eleForm.getY()));
				cell = row.getCell((short) Short.parseShort(eleForm.getX()));
				// 计算一个循环里面有多少个行
				if (Integer.parseInt(eleForm.getNewLine()) == 1) {
					maxRow = maxRow + 1;
				}
				// 根据类型不同（Numeric、String）分别获得值
				if (cell.getCellType() == 1) {
					element.setAttribute(new Attribute("value", cell
							.getStringCellValue()));
				} else if (cell.getCellType() == 0) {
					element.setAttribute(new Attribute("value", ""
							+ cell.getNumericCellValue()));
				}
				index++;
				if (maxX < Integer.parseInt(eleForm.getX())) {
					maxX = Integer.parseInt(eleForm.getX());
				}
				if (maxY < Integer.parseInt(eleForm.getY())) {
					maxY = Integer.parseInt(eleForm.getY());

				}
			}

			// 如果附加表的格式是其他
			if (!addonsType.equals("1")) {
				// 如果数据
				while (hasData == 1) {
					emtermCycle = emtermCycle + 1;
					// 通过一个emterm的循环获得是否增加一个新的emterm
					Element elementEmterm = new Element("emterm");
					for (int ic = 0; ic < emtermLists.size(); ic++) {
						TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
						Element elementEm = (Element) emtermLists.get(ic);
						eleForm = eleForm.getElement(elementEm);
						row = sheet.getRow(Integer.parseInt(eleForm.getY())
								+ emtermCycle * maxRow);

						// 如果该行存在
						if (row != null) {
							cell = row.getCell((short) Integer.parseInt(eleForm
									.getX()));

							if (cell != null) { // 如果表格不为空
								hasData = 2;
								Element _element = new Element("element");
								// 如果元素Cycle是包含所有循环，则新行没有该元素
								if (!eleForm.getCycle().equals("1")) {
									_element.setAttribute(new Attribute("name",
											eleForm.getName()));
									_element.setAttribute(new Attribute(
											"validateType", eleForm
													.getValidateType()));
									_element.setAttribute(new Attribute("rows",
											eleForm.getRows()));
									_element.setAttribute(new Attribute("cols",
											eleForm.getCols()));
									_element.setAttribute(new Attribute(
											"showType", eleForm.getShowType()));
									if (newLineTag == 1) {
										_element.setAttribute(new Attribute(
												"newLine", "1"));
									} else {
										_element
												.setAttribute(new Attribute(
														"newLine", eleForm
																.getNewLine()));
									}

									_element.setAttribute(new Attribute(
											"align", eleForm.getAlign()));
									_element.setAttribute(new Attribute(
											"valign", eleForm.getAlign()));
									_element.setAttribute(new Attribute("x",
											eleForm.getX()));
									// 将Y累加，获得真实行
									_element
											.setAttribute(new Attribute(
													"y",
													(Integer.parseInt(eleForm
															.getY()) + emtermCycle
															* maxRow)
															+ ""));
									_element
											.setAttribute(new Attribute(
													"startTime", eleForm
															.getStartTime()));
									_element.setAttribute(new Attribute(
											"endTime", eleForm.getEndTime()));
									_element.setAttribute(new Attribute(
											"dicId", eleForm.getDicId()));

									// 写出值
									if (cell.getCellType() == 1) {
										_element.setAttribute(new Attribute(
												"value", cell
														.getStringCellValue()));
									} else if (cell.getCellType() == 0) {
										_element
												.setAttribute(new Attribute(
														"value",
														""
																+ cell
																		.getNumericCellValue()));
									}

									if (eleForm.getShowType().equals("order")) { // 增加新的元素
										// 重新排列order数据
										_element
												.setAttribute(new Attribute(
														"value",
														(emtermCycle + 1) + ""));
									} else if (eleForm.getShowType().equals(
											"text")) { // 如果本身是text则覆盖原有的excel上的数据
										_element.setAttribute(new Attribute(
												"value", eleForm.getValue()));
									}
									elementEmterm.addContent(_element);
									newLineTag = 0;
								} else {
									newLineTag = 1;
								}

							}
						} else {
							hasData = 0;
						}
					}
					// 如果该行没有元素则hasData==1,如果有元素hasData==2,改变值为了重新进入循环
					if (hasData == 2) {
						hasData = 1;
						_doc.getRootElement().getChild("body").getChild(
								"elements").addContent(elementEmterm);
					} else {
						hasData = 0;
					}

				}
			}
			// 修改第一行中循环元素的rows值
			for (int i = 0; i < emtermLists.size(); i++) {
				TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
				// 获得单独的元素
				Element sElement = (Element) emtermLists.get(i);
				eleForm = eleForm.getElement(sElement);
				if (eleForm.getCycle().equals("1")) {
					sElement.setAttribute(new Attribute("rows",
							(Integer.parseInt(eleForm.getRows()) + emtermCycle
									* maxRow)
									+ ""));
				}
			}

			XMLOutputter outp = new XMLOutputter();

			// 创建文件夹
			TawwpUtil.mkDir(rootDir + rootSaveXMLDir + _model);
			if (_myid.equals("")) {
				// 导入的附加表相对地址
				_importXMLUrl = rootSaveXMLDir + _model + "/"
						+ TawwpUtil.getCurrentDateTime("yyyyMMddhhmmss_")
						+ TawwpUtil.getFileName(_addonsXML);
			} else {
				// 导入的附加表相对地址
				_importXMLUrl = rootSaveXMLDir + _model + "/" + _myid + ".xml";

			}
			// 输出
			FileOutputStream fos = new FileOutputStream(rootDir + _importXMLUrl);
			outp.output(_doc, fos);
			fos.close();

			return _importXMLUrl;
		} catch (Exception sqle) {
			sqle.printStackTrace();
			throw new TawwpException("从Excel导入附加表文件出现异常！");
		}
		// TODO 2.7移3.5注释掉
		// } finally {
		// return null;
		// }

	}

	/**
	 * 业务逻辑：获得附加表列表
	 * 
	 * @throws TawwpException
	 *             异常
	 * @return List
	 */
	public List listAddons() throws TawwpException {

		TawwpAddonsTable tawwpAddonsTable = new TawwpAddonsTable();

		TawwpAddonsTableVO tawwpAddonsTableVO = new TawwpAddonsTableVO();

		List list = null;
		List newList = null;
		try {
			list = tawwpAddonsTableDao.listAddonsTable(); // 查询附加表list

			// 循环处理放置附加表到附加表显示类中
			for (int i = 0; i < list.size(); i++) {
				tawwpAddonsTable = (TawwpAddonsTable) list.get(i);
				tawwpAddonsTableVO = new TawwpAddonsTableVO();
				MyBeanUtils.copyPropertiesFromDBToPage(tawwpAddonsTableVO,
						tawwpAddonsTable);

				newList.add(tawwpAddonsTableVO);
			}
			return newList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TawwpException("附加表查询出现异常");
		}
	}

	public StringBuffer getAddonsHtmlBuffer(String _url,
			String _userName, List _list,String _netSerialNo)
			throws Exception {
	    String rootSaveXMLDir = TawwpStaticVariable.rootSaveXMLDir; //硬盘实际目录
	    String rootDir = TawwpStaticVariable.rootDir;//web目录
		CodeContentComsInfo codeContent;
		StringBuffer optionBuffer = new StringBuffer("");
		StringBuffer StrBuffer = new StringBuffer("");
		String cols="";

		try {
			// 从文件构造一个Document
			Document _doc = getSAXBDoc(_url);

			// 得到根元素
			Element root = _doc.getRootElement();
			Element element = null;

			// head部分
			// 得到head区域子元素
			Element headElement = root.getChild("head").getChild("elements");
			// 定义List
			List headList = headElement.getChildren();
			for (int h = 0; h < headList.size(); h++) {
				// 逐个获得element元素
				element = (Element) headList.get(h);
				// 得到封装element中元素的vo
				TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
				eleForm = eleForm.getElement(element);
				cols=eleForm.getCols();
				// 获得表格头部
				if (eleForm.getNewLine().equals("1")) {
					StrBuffer
							.append("</tr><tr class=\"tr_show\" onmouseover=\"current(this)\" id=\"0\">\r\n");
				}
				StrBuffer.append("<td class=\"td_label\" align=\""
						+ eleForm.getAlign() + "\" valign=\""
						+ eleForm.getValign() + "\" ROWSPAN=\""
						+ eleForm.getRows() + "\" COLSPAN=\""
						+ eleForm.getCols() + "\">\r\n");
				StrBuffer.append(eleForm.getValue());
				StrBuffer.append("</td>\r\n");
			}

			for (int w=0;w<_list.size();w++){
				String sourcexml = rootSaveXMLDir + "50" + "/" +_list.get(w)+ ".xml";
	         	String strFileName = rootDir + sourcexml;
	         	File file = new File(strFileName);
	         	if(!file.exists()) {
	         		StrBuffer.append("</tr><tr class=\"tr_show\">\r\n");
	         		StrBuffer.append("<td class=\"td_label\" align=\"center\" COLSPAN=\""
	         				+ cols +"\">数据源文件:"
	         				+ sourcexml +"不存在");
	         		StrBuffer.append("</td></tr><tr class=\"tr_show\" onmouseover=\"current(this)\" id=\"0\">\r\n");
	         	}else{
				Document _doc2 = getSAXBDoc(sourcexml);
				Element root2 = _doc2.getRootElement();
			// body部分
			// 获得body区域子元素
			Element bodyElement = root2.getChild("body").getChild("elements");
			// 定义List
			List bodyList = null;
			List emtermLists = null;
			// 定义最小值
			int order = 0;

			bodyList = bodyElement.getChildren();

			for (int e = 0; e < bodyList.size(); e++) {
				// 逐个获得emterm元素
				Element emterm = (Element) bodyList.get(e);
				emtermLists = emterm.getChildren();
				if (emtermLists.size() > 0) {
					order++;
				}
				for (int i = 0; i < emtermLists.size(); i++) {

						// 得到封装element中元素的vo
						TawwpAddonsElementVO eleForm = new TawwpAddonsElementVO();
						// 逐个获得element元素
						element = (Element) emtermLists.get(i);
						eleForm = eleForm.getElement(element);
						// 验证时间
						SimpleDateFormat formatter = new SimpleDateFormat(
								"HH:mm:ss");
						Date curtime = new java.util.Date();
						String startTime = eleForm.getStartTime();
						String endTime = eleForm.getEndTime();
						try {
							Date now = formatter.parse(formatter.format(curtime));
							Date endT = formatter.parse(endTime);
							Date startT = formatter.parse(startTime);

							// 如果跨天且现在时间比起始时间大，就把结束时间+1天
							if (startT.getTime() > endT.getTime()
									&& now.getTime() > startT.getTime()) {
								endT
										.setTime(endT.getTime()
												+ (1000 * 60 * 60 * 24));
								// 如果跨天且现在时间比起始时间小，就把起始时间-1天
							} else if (startT.getTime() > endT.getTime()
									&& now.getTime() < startT.getTime()) {
								startT.setTime(startT.getTime()
										- (1000 * 60 * 60 * 24));
							}

							// 在规定时间之间或时间为空
							if (((now.getTime() - startT.getTime()) >= 0 && (now
											.getTime() - endT.getTime()) <= 0)
									|| (endTime.equals("") && startTime.equals(""))) {
								// 如果不是第一个emterm,则可以删除该循环
								if (eleForm.getNewLine().equals("1")) {
									StrBuffer
											.append("</tr><tr onmouseover=\"current(this)\" id=\""
													+ e
													+ "\" class=\"tr_show\">\r\n");
								} else {
									if (eleForm.getNewLine().equals("1")) {
										StrBuffer
												.append("</tr><tr class=\"tr_show\">\r\n");
									}
								}

								StrBuffer.append("<td align=\""
										+ eleForm.getAlign() + "\" valign=\""
										+ eleForm.getValign() + "\" rowspan=\""
										+ eleForm.getRows() + "\" colspan=\""
										+ eleForm.getCols() + "\">\r\n");
								if (eleForm.getShowType().equals("text")) { // 显示文字
									StrBuffer.append(eleForm.getValue()
											+ "<input type=\"hidden\" name=\""
											+ eleForm.getName() + "_" + e
											+ "\" value=\"" + eleForm.getValue()
											+ "\">\r\n");

								} else if (eleForm.getShowType().equals("order")) {
									StrBuffer.append(eleForm.getValue()
											+ "<input type=\"hidden\" name=\""
											+ eleForm.getName() + "_" + e
											+ "\" value=\"" + order + "\">\r\n");

								} else if (eleForm.getShowType().equals("timer")) { // 自动填写时间
									if (eleForm.getValue().equals("")) {
										StrBuffer.append(TawwpUtil
												.getCurrentDateTime()
												+ "<input type=\"hidden\" name=\""
												+ eleForm.getName()
												+ "_"
												+ e
												+ "\" dataType=\""
												+ eleForm.getValidateType()
												+ "\" value=\""
												+ TawwpUtil.getCurrentDateTime()
												+ "\">\r\n");
									} else {
										StrBuffer.append(eleForm.getValue()
												+ "<input type=\"hidden\" name=\""
												+ eleForm.getName() + "_" + e
												+ "\" dataType=\""
												+ eleForm.getValidateType()
												+ "\" value=\""
												+ eleForm.getValue() + "\">\r\n");
									}

								} else if (eleForm.getShowType().equals("executer")) { // 自动填写执行人
									if (eleForm.getValue().equals(""))
										StrBuffer.append(_userName
												+ "<input type=\"hidden\" name=\""
												+ eleForm.getName() + "_" + e
												+ "\" dataType=\""
												+ eleForm.getValidateType()
												+ "\" value=\"" + _userName
												+ "\">\r\n");
									else
										StrBuffer.append(eleForm.getValue()
												+ "<input type=\"hidden\" name=\""
												+ eleForm.getName() + "_" + e
												+ "\" dataType=\""
												+ eleForm.getValidateType()
												+ "\" value=\""
												+ eleForm.getValue() + "\">\r\n");

								} else if (eleForm.getShowType().equals("file")) { // 附件
									String[] value = eleForm.getValue().split(",");
									int numU = 0;
									for (int c = 0; c < value.length; c++) {
										if (!value[c].equals("")) {
											numU++;
										}
									}
									StrBuffer
											.append("<input type=\"button\" size=\"2\" name=\""
													+ eleForm.getName()
													+ "_f"
													+ e
													+ "\" value=\""
													+ numU
													+ "\" Onclick=\"javascript:onFile("
													+ eleForm.getName()
													+ "_"
													+ e
													+ ","
													+ eleForm.getName()
													+ "_f"
													+ e
													+ ");\">个附件"
													+ "<input type=\"hidden\" name=\""
													+ eleForm.getName()
													+ "_"
													+ e
													+ "\" dataType=\""
													+ eleForm.getValidateType()
													+ "\" value=\""
													+ eleForm.getValue()
													+ "\">\r\n");

								} else if (eleForm.getShowType().equals("select")) { // 选择框
									StrBuffer.append("<select name=\""
											+ eleForm.getName() + "_" + e
											+ "\" dataType=\""
											+ eleForm.getValidateType()
											+ "\" >\r\n");
									// 获得字典数据
									codeContent = new CodeContentComsInfo(eleForm
											.getDicId());
									while (codeContent.next()) {
										optionBuffer.append("<option value='"
												+ codeContent.getCodeName() + "'>"
												+ codeContent.getCodeName()
												+ "</option>");
									}
									optionBuffer
											.append("<option value=''>无</option>");
									StrBuffer.append(optionBuffer);
									optionBuffer = new StringBuffer("");
									StrBuffer.append("</select>");
									StrBuffer
											.append("<script language=\"javascript\">\r\n"
													+ "document.addonssaveform."
													+ eleForm.getName()
													+ "_"
													+ e
													+ ".value = '"
													+ eleForm.getValue()
													+ "';"
													+ "</script>\r\n");
								} else { // 显示input
									StrBuffer.append("<input size=\"10\" name=\""
											+ eleForm.getName() + "_" + e
											+ "\" dataType=\""
											+ eleForm.getValidateType()
											+ "\" value=\"" + eleForm.getValue()
											+ "\">\r\n");
								}
								StrBuffer.append("</td>\r\n");
							} else { //不在显示时间内

								if (eleForm.getShowType().equals("text")) { //显示文字
									StrBuffer
											.append("<input type=\"hidden\" name=\""
													+ eleForm.getName()
													+ "_"
													+ e
													+ "\" value=\""
													+ eleForm.getValue()
													+ "\">\r\n");
								} else if (eleForm.getShowType().equals("order")) {
									StrBuffer
											.append("<input type=\"hidden\" name=\""
													+ eleForm.getName()
													+ "_"
													+ e
													+ "\" value=\""
													+ order
													+ "\">\r\n");
								}
								StrBuffer.append("<input type=\"hidden\" name=\""
										+ eleForm.getName() + "_" + e
										+ "\" value=\"" + eleForm.getValue()
										+ "\">\r\n");
								//}
							}
						} catch (Exception ec) {
							//捕获异常
							ec.printStackTrace();
							throw new Exception("时间格式化出错");

						}	
					}	
				}
			}
		}
			return StrBuffer;
		} catch (Exception e) {
			//捕获异常
			e.printStackTrace();
			throw new Exception("附加表的表格的StringBuffer获取出现异常");
		}

	}
}
