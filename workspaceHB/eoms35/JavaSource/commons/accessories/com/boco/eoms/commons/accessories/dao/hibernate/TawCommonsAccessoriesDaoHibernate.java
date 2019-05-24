package com.boco.eoms.commons.accessories.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesDao;
import com.boco.eoms.commons.loging.BocoLog;

import org.springframework.orm.ObjectRetrievalFailureException;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:附件管理DAO类实现类
 * </p>
 * <p>Apr 10, 2007 10:58:30 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class TawCommonsAccessoriesDaoHibernate extends BaseDaoHibernate
		implements TawCommonsAccessoriesDao {

	/**
	 * @see com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesDao#getTawCommonsAccessoriess(com.boco.eoms.commons.accessories.model.TawCommonsAccessories)
	 */
	public List getTawCommonsAccessoriess( ) {
		return getHibernateTemplate().find("from TawCommonsAccessories");

	}

	/**
	 * @see com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesDao#getTawCommonsAccessories(String
	 *      id)
	 */
	public TawCommonsAccessories getTawCommonsAccessories(final String id) {
		TawCommonsAccessories tawCommonsAccessories = (TawCommonsAccessories) getHibernateTemplate()
				.get(TawCommonsAccessories.class, id);
		if (tawCommonsAccessories == null) {
			BocoLog.warn(this,"uh oh, tawCommonLogDeploy with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(
					TawCommonsAccessories.class, id);
		}

		return tawCommonsAccessories;
	}

	/**
	 * @see com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesDao#saveTawCommonsAccessories(TawCommonsAccessories
	 *      tawCommonsAccessories)
	 */
	public void saveTawCommonsAccessories(
			final TawCommonsAccessories tawCommonsAccessories) {
		if ((tawCommonsAccessories.getId() == null)
				|| (tawCommonsAccessories.getId().equals("")))
			getHibernateTemplate().save(tawCommonsAccessories);
		else
			getHibernateTemplate().saveOrUpdate(tawCommonsAccessories);
	}

	/**
	 * @see com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesDao#removeTawCommonsAccessories(String
	 *      id)
	 */
	public void removeTawCommonsAccessories(final String id) {
		getHibernateTemplate().delete(getTawCommonsAccessories(id));
	}

	/**
	 * 根据文件名称查询文件信息
	 * @author 秦敏
	 * @param accesspriesFileNames 文件名称
	 * @return
	 */
	public List getAllFileByName(String accesspriesFileNames) {
		if(accesspriesFileNames==null || "".equals(accesspriesFileNames.trim())){
			accesspriesFileNames="''";
		}
		String sql = "from TawCommonsAccessories as tawCommonsAccessories "
				+ "where  tawCommonsAccessories.accessoriesName in ("
				+ accesspriesFileNames + ")";
		List list = getHibernateTemplate().find(sql);
		return list;
	}

	/**
	 * 根据实际文件的编号查询文件信息（附件上传接口用）
	 * @author 赵东亮
	 * @param accesspriesFileName 文件的编号（类似20081214092334）
	 * @return
	 */
	public List getFileByName(String accesspriesFileName) {
		if(accesspriesFileName==null || "".equals(accesspriesFileName.trim())){
			accesspriesFileName="''";
		}
		String sql = "from TawCommonsAccessories as tawCommonsAccessories "
				+ "where  tawCommonsAccessories.accessoriesName like '%"
				+ accesspriesFileName + "%'";
		List list = getHibernateTemplate().find(sql);
		return list;
	}

}
