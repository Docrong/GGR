package com.boco.eoms.km.expert.mgr;

import com.boco.eoms.km.expert.model.KmExpertPhoto;

/**
 * <p>
 * Title:知识库专家问答
 * </p>
 * <p>
 * Description:知识库专家问答
 * </p>
 * <p>
 * 2009-07-27
 * </p>
 * 
 * @author daizhigang
 * @version 1.0
 * 
 */

public interface KmExpertPhotoMgr {
	
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