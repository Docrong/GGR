package com.boco.eoms.commons.statistic.base.mgr.impl;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.statistic.base.exception.Id2NameStatException;
import com.boco.eoms.commons.statistic.base.mgr.ID2NameManager;
import com.boco.eoms.commons.statistic.base.mgr.IStatID2Name;
import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.commons.statistic.base.util.Constants;

/**
 * 通过id转换为需要的名称
 * 
 * @author lizhenyou
 *
 */
public class ID2NameManagerImpl implements ID2NameManager {
	protected Logger logger = Logger.getLogger(this.getClass());
	 
	public String id2Name(String id, String beanId) {
		 
		logger.info("\n id2Name转换的beanId 为 ：" + beanId +  " id 为 " + id);
		String name = null;
		try {
			// 通过beanid取bean
			IStatID2Name dao = (IStatID2Name) ApplicationContextHolder
					.getInstance().getBean(beanId);
			
			// 转换后的name
			name = dao.id2Name(id);
		} catch (Exception e) {
			// 取id2name失败后的name默认值
			//name = Util.idNoName();
			logger.error("\n取id2name失败请查看beanid:["+beanId+"]是否正确");
			e.printStackTrace();
			name = Constants.ID_NO_NAME;
		}
		
		if (name == null || "".equals(name)) {
			//name = Util.idNoName();
			logger.error("\n没有找到相对应的name" + "请查看beanid:["+beanId+"]是否正确或数据库中没有该id");
			name = Constants.ID_NO_NAME;
		}
		logger.info("\n id2Name转换的name为 " + name);
		return name;
	}

	/**
	 * 根据字典取name
	 */
	public String idType2Name(String id, String type, String beanId)
			throws Id2NameStatException {
		
		String name = null;
		try {
			// 通过beanid取bean
			IStatID2Name dao = (IStatID2Name) ApplicationContextHolder
					.getInstance().getBean(beanId);
			
			// 转换后的name
			name = dao.idType2Name(id, type);
		} catch (Exception e) {
			// 取id2name失败后的name默认值
			//name = Util.idNoName();
			logger.error("\n取id2name失败请查看beanid:["+beanId+"]是否正确");
			name = Constants.ID_NO_NAME;
		}
		if (name == null || "".equals(name)) {
			//name = Util.idNoName();
			logger.error("\n没有找到相对应的name" + "请查看beanid:["+beanId+"]是否正确或数据库中没有该id");
			name = Constants.ID_NO_NAME;
		}
		return name;
	}
}
