package com.boco.eoms.eva.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.eva.model.EvaTemplate;

/**
 * 
 * <p>
 * Title:考核模板Dao接口
 * </p>
 * <p>
 * Description:考核模板Dao接口
 * </p>
 * <p>
 * Date:2008-12-8 上午09:50:27
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public interface IEvaTemplateDao extends Dao {

	/**
	 * 根据主键id查询模板
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public EvaTemplate getTemplate(String id);

	/**
	 * 保存模板
	 * 
	 * @param template
	 *            模板
	 */
	public void saveTemplate(EvaTemplate template);

	/**
	 * 删除模板
	 * 
	 * @param template
	 *            模板
	 */
	public void removeTemplate(EvaTemplate template);

	/**
	 * 根据模板Id返回模板名称
	 * 
	 * @param id
	 *            模板Id
	 * @return
	 */
	public String id2Name(final String id);

}
