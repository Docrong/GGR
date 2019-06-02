package com.boco.eoms.workbench.commission.mgr;

import java.util.List;

import net.sf.json.JSONArray;

import com.boco.eoms.workbench.commission.model.TawWorkbenchCommissionPreset;

/**
 * <p>
 * Title:代理预设定管理接口类
 * </p>
 * <p>
 * Description:代理预设定管理接口类
 * </p>
 * <p>
 * Date:2008-7-24 下午03:31:32
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public interface ITawWorkbenchCommissionPresetMgr {

	/**
	 * 保存预设定信息
	 * 
	 * @param commissionPreset
	 *            预设定信息
	 */
	public void saveCommissionPrese(
			TawWorkbenchCommissionPreset commissionPreset);

	/**
	 * 根据ID查询代理预设定信息
	 * 
	 * @param id
	 *            代理预设定ID
	 * @return TawWorkbenchCommissionPreset 代理预设定
	 */
	public TawWorkbenchCommissionPreset getCommissionPreset(String id);

	/**
	 * 根据被代理人ID和模块ID查询预设定信息
	 * 
	 * @param userId
	 * @param moduleId
	 * @return
	 */
	public List listCommissionPresets(String userId, String moduleId);
	
	/**
	 * 根据被代理人ID和模块ID查询预设定信息(JSON)
	 * 
	 * @param userId
	 * @param moduleId
	 * @return
	 */
	public JSONArray listCommissionPresetsJSON(String userId, String moduleId);
	
	/**
	 * 根据被代理人ID和模块ID删除预设定信息
	 * 
	 * @param userId
	 * @param moduleId
	 * @return
	 */
	public void delCommissionPresets(String userId, String moduleId);

}
