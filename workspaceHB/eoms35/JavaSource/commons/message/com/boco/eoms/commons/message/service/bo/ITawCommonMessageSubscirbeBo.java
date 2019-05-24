package com.boco.eoms.commons.message.service.bo;

import java.util.List;

import com.boco.eoms.commons.message.model.TawCommonMessageSubscribe;

public interface ITawCommonMessageSubscirbeBo {

	/**
	 * 查找所有的定制服务
	 * 
	 * @return
	 */

	public List getSubscribes();

	/**
	 * 根据ID查找定制的服务
	 * 
	 * @param id
	 * @return
	 */
	public TawCommonMessageSubscribe getSubscribe(String id);

	/**
	 * 根据modelid operid 查找消息服务
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public List getMessageScByModelidAndOperids(String modelid, String operid);

	/**
	 * 根据用户ID查找定制的服务类型
	 * 
	 * @param userid
	 * @return
	 */
	public List getSubscByUserids(String userid);

	/**
	 * 根据ID 删除定制的服务
	 * 
	 * @param id
	 */
	public void removeSubscBy(String id);

	/**
	 * 新增服务订阅
	 * 
	 * @param subsc
	 */
	public void saveSubsc(TawCommonMessageSubscribe subsc);

	/**
	 * 修改定制的服务
	 * 
	 * @param beforesubsc
	 * @param aftersubsc
	 */
	public void saveAndUpdateSubsc(TawCommonMessageSubscribe beforesubsc,
			TawCommonMessageSubscribe aftersubsc);

}
