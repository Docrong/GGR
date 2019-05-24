package com.boco.eoms.workplan.mgr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.JDOMException;

import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.vo.TawwpAddonsTableVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 9:26:11 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ITawwpAddonsMgr {
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
			String _executeId, String _url) throws Exception;

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
			String _model, String _executeId, String _url) throws Exception;

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
	public TawwpAddonsTableVO viewAddonsTableVO(String _id) throws Exception;

	/**
	 * 业务逻辑：删除附加表
	 * 
	 * @modifyDate 9/13/2005
	 * @param _id
	 *            String 附加表标识
	 * @throws Exception
	 *             操作异常
	 */
	public void removeAddonsTable(String _id) throws Exception;

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
			Vector _v, String _myid) throws Exception;

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
			String _action) throws Exception;

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
	public String getAddonsType(String _url) throws Exception;

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
			String _model, String _myid, String _emtermid) throws Exception;

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
			String _model, String _myid) throws Exception;

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
			Exception;

	/**
	 * 业务逻辑：根据模块名称获得附加表列表
	 * 
	 * @modifyDate 9/13/2005
	 * @param _model
	 *            String 模块表识
	 * @throws TawwpException
	 * @return Hashtable
	 */
	public Hashtable listAddons(String _model) throws TawwpException;

	/**
	 * 业务逻辑：根据作业计划获得附加表列表
	 * 
	 * @throws TawwpException
	 *             操作异常
	 * @return Hashtable 作业计划附加表列表
	 */
	public List listAddonsByWorkPlan() throws TawwpException;

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
			IOException, Exception;
	public String exportAddonsToExcel(String _sourceXML,String outPut)throws TawwpException,
	IOException, Exception;
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
			String _model, String _myid) throws TawwpException;

	/**
	 * 业务逻辑：获得附加表列表
	 * 
	 * @throws TawwpException
	 *             异常
	 * @return List
	 */
	public List listAddons() throws TawwpException;

	public StringBuffer getAddonsHtmlBuffer(String _url, String _userid,
			List _list, String _serialno) throws Exception;
}
