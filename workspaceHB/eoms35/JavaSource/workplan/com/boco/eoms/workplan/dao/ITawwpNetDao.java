package com.boco.eoms.workplan.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.workplan.model.TawwpNet;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 1:07:44 AM
 * </p>
 * 
 * @author 鏇查潤娉�
 * @version 3.5.1
 * 
 */
public interface ITawwpNetDao {
	/**
	 * 淇濆瓨缃戝厓
	 * 
	 * @param _tawwpNet
	 *            TawwpNet 缃戝厓绫�
	 * @throws Exception
	 *             鎿嶄綔寮傚父
	 */
	public void saveNet(TawwpNet _tawwpNet);

	/**
	 * 鍒犻櫎缃戝厓
	 * 
	 * @param _tawwpNet
	 *            TawwpNet 缃戝厓绫�
	 * @throws Exception
	 *             鎿嶄綔寮傚父
	 */
	public void deleteNet(TawwpNet _tawwpNet);

	/**
	 * 淇敼缃戝厓
	 * 
	 * @param _tawwpNet
	 *            TawwpNet 缃戝厓绫�
	 * @throws Exception
	 *             鎿嶄綔寮傚父
	 */
	public void updateNet(TawwpNet _tawwpNet);

	/**
	 * 鏌ヨ缃戝厓淇℃伅
	 * 
	 * @param _id
	 *            String 缃戝厓鏍囪瘑
	 * @throws Exception
	 *             鎿嶄綔寮傚父
	 * @return TawwpNet 缃戝厓绫�
	 */
	public TawwpNet loadNet(String _id);

	/**
	 * 鏌ヨ鎵�鏈夌綉鍏冧俊鎭�
	 * 
	 * @throws Exception
	 *             鎿嶄綔寮傚父
	 * @return List 缃戝厓绫诲垪琛�
	 */
	public List ListNet() throws Exception;

	/**
	 * 鏌ヨ鎵�鏈夌綉鍏冧俊鎭�
	 * 
	 * @param _netIdList
	 *            String 缃戝厓鏍囪瘑瀛楃涓�
	 * @throws Exception
	 *             鎿嶄綔寮傚父
	 * @return List 缃戝厓绫诲垪琛�
	 */
	public List ListNet(String _netIdList);

	/**
	 * 鑾峰彇鎵�鏈夌綉鍏冧俊鎭紝鎸夌綉鍏冪被鍨嬨�侀儴闂ㄨ繘琛屾煡璇�
	 * 
	 * @param _netTypeId
	 *            String 缃戝厓鏍囪瘑
	 * @param _deptId
	 *            String 閮ㄩ棬
	 * @throws Exception
	 *             鎿嶄綔寮傚父
	 * @return List 缃戝厓绫诲垪琛�
	 */
	public List ListNetByNetDetp(String _netTypeId, String _deptId);

	/**
	 * 鏌ヨ鎵�鏈夌綉鍏冧俊鎭紝鍒嗛〉
	 * 
	 * @param _pagePra
	 *            int[] 鍒嗛〉淇℃伅
	 * @throws Exception
	 *             鎿嶄綔寮傚父
	 * @return List 缃戝厓绫诲垪琛�
	 */
	public List ListNet(int[] _pagePra);

	/**
	 * 缁勫悎鏌ヨ缃戝厓淇℃伅
	 * 
	 * @param _map
	 *            Map 鏌ヨ鏉′欢
	 * @throws Exception
	 *             鎿嶄綔寮傚父
	 * @return List 缃戝厓淇℃伅鍒楄〃
	 */
	public List searchNet(Map _map);
	
	public List searchNet(Map _map,String flag);
	
	public List searchNetpage(Map _map,int[] pagePra,String sql) throws Exception;

	/**
	 * 鑾峰彇缃戝厓鍚嶇О瀛楃涓�
	 * 
	 * @param _netIdList
	 *            String 缃戝厓缂栧彿瀛楃涓�
	 * @throws Exception
	 *             鎿嶄綔寮傚父
	 * @return String 缃戝厓鍚嶇О瀛楃涓�
	 */
	public String getNetNameList(String _netIdList);

	/**
	 * 鑾峰彇缃戝厓鐨勭敓浜у巶鍟�
	 * 
	 * @throws Exception
	 *             鎿嶄綔寮傚父
	 * @return List 鍘傚晢淇℃伅鍒楄〃
	 */
	public List listVerdor();

	public String loadNetBySerial(String _netSerial);
	public List searchWorkplanNet();

	public List ListNetByDept(String _deptId);

	public List ListNetNoExecute(String time);

	public List listNetType(Map _map) throws Exception;
	public List ListNetByYearReport() throws Exception;
	public List ListNetNewUpdate() throws Exception;
	public List searchListNet(String _id) throws Exception;
	
	public List listidbymynettypie(String mynettypeid) throws Exception;
}
