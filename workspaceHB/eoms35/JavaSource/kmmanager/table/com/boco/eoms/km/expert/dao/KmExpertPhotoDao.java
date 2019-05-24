package com.boco.eoms.km.expert.dao;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.km.expert.model.KmExpertPhoto;

/**
 * <p>
 * Title:基本信息
 * </p>
 * <p>
 * Description:专家基本信息
 * </p>
 * <p>
 * Mon Jun 15 19:14:24 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public interface KmExpertPhotoDao extends Dao {

	/**
	 * 根据用户ID查询头像信息
	 * @param userId 用户ID
	 * @return 返回某用户的头像信息
	 */
	public KmExpertPhoto getKmExpertPhotoByUserId(final String userId);

	/**
	 *
	 * 保存头像信息
	 * @param kmExpertBasic 头像信息
	 * 
	 */
	public void saveKmExpertPhoto(KmExpertPhoto kmExpertPhoto);

}