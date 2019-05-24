package com.boco.eoms.workbench.commission.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.workbench.commission.model.TawWorkbenchCommissionPreset;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-7-25 上午09:08:50
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public interface ITawWorkbenchCommissionPresetDao extends Dao {

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
	 * 根据被代理人ID和模块ID删除预设定信息
	 * 
	 * @param userId
	 * @param moduleId
	 * @return
	 */
	public void delCommissionPresets(String userId, String moduleId);

}
