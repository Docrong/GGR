package com.boco.eoms.eva.mgr;

import java.util.ArrayList;

import com.boco.eoms.eva.model.EvaKpi;
import com.boco.eoms.eva.model.EvaKpiVariable;

/**
 * <p>
 * Title:指标变量业务方法类
 * </p>
 * <p>
 * Description:指标变量业务方法类
 * </p>
 * <p>
 * Date:2008-11-26 下午09:49:45
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public interface IEvaKpiVariableMgr {

	/**
	 * 根据主键id查询指标变量
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public EvaKpi getKpiVariable(String id);

	/**
	 * 保存指标指标变量
	 * 
	 * @param EvaKpi
	 *            指标
	 */
	public void saveKpiVariable(EvaKpiVariable kpiVariable);

	/**
	 * 根据指标变量主键id删除指标变量
	 * 
	 * @param id
	 *            主键
	 */
	public void removeKpiVariable(String id);

	/**
	 * 查询某模板订制的所有指标变量
	 * 
	 * @param customId
	 *            模板订制id
	 * @return
	 */
	public ArrayList listVariableOfCustom(String customId);

	/**
	 * 删除某模板订制的所有指标变量
	 * 
	 * @param customId
	 *            模板订制id
	 * @return
	 */
	public void removeVariableOfCustom(String customId);
}
