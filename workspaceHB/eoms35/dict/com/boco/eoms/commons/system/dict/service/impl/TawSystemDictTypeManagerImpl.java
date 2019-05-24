package com.boco.eoms.commons.system.dict.service.impl;

// java standard library
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// eoms calsses
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.EOMSAttributes;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.dao.ITawSystemDictJDBC;
import com.boco.eoms.commons.system.dict.dao.ITawSystemDictTypeDao;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.dict.util.TawSystemDictUtil;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.IRoleMgr;

public class TawSystemDictTypeManagerImpl extends BaseManager implements
		ITawSystemDictTypeManager {
	private ITawSystemDictTypeDao dictTypeDao;

	private ITawSystemDictJDBC dictjdbc;

	/**
	 * 角色管理
	 */
	private IRoleMgr roleMgr;
	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setDictTypeDao(ITawSystemDictTypeDao dao) {
		this.dictTypeDao = dao;
	}

	public void setDictjdbc(ITawSystemDictJDBC dictjdbc) {
		this.dictjdbc = dictjdbc;
	}

	public String id2Name(String id) throws DictDAOException
	{
		return dictTypeDao.id2Name(id);
	}
	/*
	 * @see com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager#getTawSystemDictTypes(com.boco.eoms.commons.system.dict.model.TawSystemDictType)
	 */
	public List getTawSystemDictTypes(final TawSystemDictType tawSystemDictType) {
		return dictTypeDao.getTawSystemDictTypes(tawSystemDictType);
	}

	/*
	 * @see com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager#getTawSystemDictType(String
	 *      id)
	 */
	public TawSystemDictType getTawSystemDictType(final String id) {
		return dictTypeDao.getTawSystemDictType(new Integer(id));
	}

	/*
	 * @see com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager#saveTawSystemDictType(TawSystemDictType
	 *      tawSystemDictType)
	 */
	public void saveTawSystemDictType(TawSystemDictType tawSystemDictType) {
		dictTypeDao.saveTawSystemDictType(tawSystemDictType);
	}

	/*
	 * @see com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager#removeTawSystemDictType(String
	 *      id)
	 */
	public void removeTawSystemDictType(final String id) {
		dictTypeDao.removeTawSystemDictType(new Integer(id));
	}

	public String getParentTypeName(String _strCurCode) {
		return dictTypeDao.getParentTypeName(_strCurCode);
	}

	/**
	 * 根据字典id查询字典名字
	 */
	public TawSystemDictType getDictByDictId(String _strDictId) {
		return dictTypeDao.getDictByDictId(_strDictId);
	}
	public List getworkplanDictByDictId() {
		return dictTypeDao.getworkplanDictByDictId();
	}
	/**
	 * 查询下一级信息
	 * 
	 * @param parentdictid
	 * @return
	 */
	public ArrayList getDictSonsByDictid(String parentdictid) {
		ArrayList list = new ArrayList();
		list = dictTypeDao.getDictSonsByDictid(parentdictid);
		return list;
	}

	/**
	 * 根据字id查询字典信息
	 * 
	 * @param dictid
	 * @return
	 */
	public TawSystemDictType getDictTypeByDictid(String dictid) {
		return dictTypeDao.getDictTypeByDictid(dictid);
	}

	/**
	 * 查询最大的字典ID
	 * 
	 * @param pardictid
	 * @param len
	 * @return
	 */
	public String getMaxDictid(String pardictid) {

		// long deptidvar = TawSystemDictUtil.DICTID_DEFULT_LONGVAR;
		// String maxdictid = dictjdbc.getMaxDeptid(pardictid,
		// pardictid.length()
		// + TawSystemDictUtil.DICTID_BETWEEN_LENGTH);
		// //如果是第一个子节点
		// if (maxdictid.equals(TawSystemDictUtil.DICTID_DEFULT_VALUE)) {
		// maxdictid = pardictid + TawSystemDictUtil.DICTID_NOSON;
		// }else {
		// deptidvar = Long.valueOf(maxdictid).longValue();
		// if (maxdictid.compareTo(pardictid
		// + TawSystemDictUtil.DICTID_IF_MAXID) <
		// TawSystemDictUtil.DICTID_DEFULT_INTVAR) {
		// maxdictid = String.valueOf(deptidvar + 1);
		// } else {
		// maxdictid = TawSystemDictUtil.DICTID_MAXID;
		// }
		// }
		// return maxdictid;

		// 修改为循环利用未占用的dictId edit by liqiuye 20080902
		String maxDictId = "";
		String release = StaticMethod
				.null2String(((EOMSAttributes) ApplicationContextHolder
						.getInstance().getBean("eomsAttributes")).getRelease());

		// 获得可用Id顺序，版本发布降序，未发布升序 edit by liqiuye 20080916
		if ("true".equals(release)) {
			maxDictId = dictjdbc.getMaxDeptidDESC(pardictid, pardictid.length()
					+ TawSystemDictUtil.DICTID_BETWEEN_LENGTH);
		} else if ("false".equals(release)) {
			maxDictId = dictjdbc.getMaxDeptid(pardictid, pardictid.length()
					+ TawSystemDictUtil.DICTID_BETWEEN_LENGTH);
		} else {
			maxDictId = dictjdbc.getMaxDeptid(pardictid, pardictid.length()
					+ TawSystemDictUtil.DICTID_BETWEEN_LENGTH);
		}
		return maxDictId;

	}

	/**
	 * 根据字典ID删除记录
	 * 
	 * @param dictid
	 */
	public void removeDictByDictid(String dictid) {
		dictjdbc.removeDictByDictid(dictid);
	}

	/**
	 * 判断是否有相同级别的字典类型
	 * 
	 * @param systype
	 * @return
	 */
	public boolean isHaveSameLevel(String parentdictid, String systype) {
		return dictTypeDao.isHaveSameLevel(parentdictid, systype);
	}

	/**
	 * 更新某字典类型的叶子节点
	 * 
	 * @param dictid
	 * @param leaf
	 */
	public void updateParentDictLeaf(String dictid, String leaf) {
		dictjdbc.updateParentDictLeaf(dictid, leaf);
	}

	/**
	 * 查询code的字典信息
	 * 
	 * @param code
	 * @return
	 */
	public List getDictByCode(String code) {
		return dictTypeDao.getDictByCode(code);
	}

	/**
	 * 根据 parentDictId 和 dictCode获取 TawSystemDictType
	 * 
	 * @param tawSystemDictType
	 * @param tawSystemDictCode
	 * @return String TawSystemDictType
	 * @author liqiuye 2007-11-14
	 */
	public TawSystemDictType getDictByDictType(String dictCode,
			String parentDictId) {

		return dictTypeDao.getDictByDictType(dictCode, parentDictId);
	}

	/**
	 * 根据 dictCode获取 TawSystemDictType
	 * 
	 * @param tawSystemDictCode
	 * @return String TawSystemDictType
	 * @author liqiuye 2007-11-14
	 */
	public TawSystemDictType getDictByDictType(String dictCode) {

		return dictTypeDao.getDictByDictType(dictCode);
	}
	
    public boolean isCodeExist(String dictCode, String dictId) {
    	return dictTypeDao.isCodeExist(dictCode, dictId);
    }
    
    public String getDictIdByDictCode(String dictCode) {
    	return dictTypeDao.getDictIdByDictCode(dictCode);
    }
    
    public String getDictIdByDictCode(String parentId,String dictCode){
    	return dictTypeDao.getDictIdByDictCode(parentId,dictCode);
    }
    
  public List listDict(String dictId, String roleId, String areaId) {
		String dictIds = "";
		//取某地域、角色的所有子角色
		List list = roleMgr.listSubRoleWithType1NotNull(areaId,roleId);
		//拼写dictIds in的字符串
		if (list != null && !list.isEmpty()) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				TawSystemSubRole subrole=(TawSystemSubRole)it.next();
				dictIds+="'"+subrole.getType1()+"',";
			}
			dictIds=StaticMethod.removeLastStr(dictIds, ",");
		}
		else{
			dictIds="''";
		}
		return dictTypeDao.listDictInDictIds(dictId, dictIds);
	}

	/**
	 * @param roleMgr
	 *            the roleMgr to set
	 */
	public void setRoleMgr(IRoleMgr roleMgr) {
		this.roleMgr = roleMgr;
	}
	
	public TawSystemDictType getDictByDictName(String dictName,
			String parentDictId) {

		return dictTypeDao.getDictByDictName(dictName, parentDictId);
	}
}