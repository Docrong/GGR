package com.boco.eoms.workplan.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.vo.TawwpNetVO;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 2:47:34 PM
 * </p>
 * 
 * @author 鏇查潤娉�
 * @version 3.5.1
 * 
 */
public interface ITawwpNetMgr {
	/**
	 * 涓氬姟閫昏緫锛氬垪琛ㄧ綉鍏冧俊鎭紙LIST-NET-ELEMENT-001锛�
	 * 
	 * @param pagePra
	 *            int[] 鍒嗛〉淇℃伅
	 * @throws TawwpException
	 *             寮傚父淇℃伅
	 * @return List 缃戝厓鍒楄〃
	 */
	public List listNet(int[] pagePra) throws TawwpException;

	/**
	 * 涓氬姟閫昏緫锛氬鍔犵綉鍏冿紙ADD-NET-ELEMENT-001锛�
	 * 
	 * @param _name
	 *            String 缃戝厓鍚嶇О
	 * @param _deptId
	 *            String 鎵�灞為儴闂�
	 * @param _roomId
	 *            String 鎵�鍦ㄦ満鎴�
	 * @param _sysTypeId
	 *            String 绯荤粺绫诲埆
	 * @param _netTypeId
	 *            String 缃戝厓绫诲瀷
	 * @param _serialNo
	 *            String 搴忓垪鍙�
	 * @param _vendor
	 *            String 鍘傚晢
	 * @param vendorSel 
	 * @throws TawwpException
	 *             寮傚父淇℃伅
	 */
	public void addNet(String _name, String _deptId, String _roomId,
			String _sysTypeId, String _netTypeId, String _mynetTypeId, String _serialNo,
			String _vendor) throws TawwpException;

	/**
	 * 涓氬姟閫昏緫锛氬鍔犵綉鍏冮�氳繃缃戝厓鎺ュ彛璇诲彇鏁版嵁锛圓DD-NET-ELEMENT-002锛�
	 * 
	 * @param _name
	 *            String 缃戝厓鍚嶇О
	 * @param _serialNo
	 *            String 搴忓垪鍙�
	 * @param _vendor
	 *            String 鍘傚晢
	 * @throws TawwpException
	 *             寮傚父淇℃伅
	 */
	public void addNetByInterface(String _name, String _serialNo, String _vendor)
			throws TawwpException;

	/**
	 * 涓氬姟閫昏緫锛氫慨鏀圭綉鍏冿紙EDIT-NET-ELEMENT-001锛�
	 * 
	 * @param _id
	 *            String 缃戝厓鏍囪瘑
	 * @param _name
	 *            String 缃戝厓鍚嶇О
	 * @throws TawwpException
	 *             寮傚父淇℃伅
	 */
	public void editNet(String _id, String _name) throws TawwpException;

	public void editNet(String _id, String _name,String serialNo) throws TawwpException;
	/**
	 * 涓氬姟閫昏緫锛氬垹闄ょ綉鍏冿紙DELETE-NET-ELEMENT-001锛�
	 * 
	 * @param _id
	 *            String
	 * @throws TawwpException
	 */
	public void removeNet(String _id) throws TawwpException;

	/**
	 * 涓氬姟閫昏緫锛氭祻瑙堢綉鍏冧俊鎭紙BROWSE-NET-ELEMENT-001锛�
	 * 
	 * @param _id
	 *            String 缃戝厓淇℃伅鏍囪瘑
	 * @throws TawwpException
	 *             寮傚父淇℃伅
	 * @return TawwpNetVO 缃戝厓淇℃伅
	 */
	public TawwpNetVO viewNet(String _id) throws TawwpException;

	/**
	 * 涓氬姟閫昏緫锛氭煡璇㈢綉鍏冧俊鎭紙QUERY-NET-ELEMENT-001锛�
	 * 
	 * @param _mapQuery
	 *            Map 鏌ヨ鏉′欢
	 * @throws TawwpException
	 *             寮傚父淇℃伅
	 * @return List 缃戝厓鍒楄〃
	 */
	public List searchNetPage(Map _mapQuery,int[] pagePra,String sql) throws TawwpException;
	
	public List searchNet(Map _mapQuery) throws TawwpException;
	public List searchWorkplanNet() throws TawwpException;

	/**
	 * 鑾峰彇缃戝厓淇℃伅锛屾寜缃戝厓绫诲瀷鍜岄儴闂ㄣ��
	 * 
	 * @param _netTypeId
	 *            String 缃戝厓绫诲瀷鏍囪瘑
	 * @param _deptId
	 *            String 閮ㄩ棬
	 * @throws TawwpException
	 *             寮傚父淇℃伅
	 * @return List 缃戝厓鍒楄〃
	 */
	public List listNetByTypeDept(String _netTypeId, String _deptId)
			throws TawwpException;

	/**
	 * 鑾峰彇缃戝厓淇℃伅锛屾寜缃戝厓绫诲瀷鍜岄儴闂ㄣ��
	 * 
	 * @param _netTypeId
	 *            String 缃戝厓绫诲瀷鏍囪瘑
	 * @param _deptId
	 *            String 閮ㄩ棬
	 * @param flag
	 *            鏄惁鏀惧紑鏈堝害浣滀笟璁″垝鍒跺畾鍚庝慨鏀圭綉鍏冪殑鍔熻兘
	 * @throws TawwpException
	 *             寮傚父淇℃伅
	 * @return List 缃戝厓鍒楄〃
	 */
	public List listNetByTypeDept(String _netTypeId, String _deptId, String flag)
			throws TawwpException;
	
	public List searchNetNoExecute(String _startTime) throws TawwpException;

	public String netXml(Map _mapQuery);

	public String newNetXml(Map _mapQuery);
	/**
	 * 鐢熸垚涓婃姤缃戝厓淇℃伅
	 * @param fileName
	 *            String銆�鏂囦欢鍚�
	 * @throws Exception 
	 * @throws Exception
	 */
	public void netReport(String _fileName) throws Exception;
	public void newNetReport(String _fileName) throws Exception;
	public void netReportOK(String _reportType) throws Exception;
	
	/**
	 * 根据序列号查询id号
	 * @param _netSerial
	 * @return
	 */
	public String loadNetBySerial(String _netSerial);
	
	



}
