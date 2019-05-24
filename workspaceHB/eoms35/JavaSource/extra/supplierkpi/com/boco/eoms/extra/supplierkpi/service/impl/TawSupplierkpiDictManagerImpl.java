package com.boco.eoms.extra.supplierkpi.service.impl;

import java.util.ArrayList;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiDictDao;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiDictManager;
import com.boco.eoms.extra.supplierkpi.util.TawSupplierkpiDictUtil;

public class TawSupplierkpiDictManagerImpl extends BaseManager implements
		ITawSupplierkpiDictManager {

	private TawSupplierkpiDictDao tawSupplierkpiDictDao;

	public TawSupplierkpiDictDao getTawSupplierkpiDictDao() {
		return tawSupplierkpiDictDao;
	}

	public void setTawSupplierkpiDictDao(
			TawSupplierkpiDictDao tawSupplierkpiDictDao) {
		this.tawSupplierkpiDictDao = tawSupplierkpiDictDao;
	}

	public void delDictByDictId(String dictId) {
		tawSupplierkpiDictDao.delDictByDictId(dictId);
	}

	public TawSupplierkpiDict getDictByDictId(String dictId) {
		return tawSupplierkpiDictDao.getDictByDictId(dictId);
	}

	public ArrayList getDictSonsByDictId(String parentDictId) {
		ArrayList list = new ArrayList();
		list = tawSupplierkpiDictDao.getDictSonsByDictId(parentDictId);
		return list;
	}

	public String getMaxDictid(String parentDictId) {
		// long deptidvar = TawSupplierkpiDictUtil.DICTID_DEFULT_LONGVAR;
		// String maxDictId = tawSupplierkpiDictDao.getMaxDictId(parentDictId,
		// parentDictId.length()
		// + TawSupplierkpiDictUtil.DICTID_BETWEEN_LENGTH);
		// //如果是第一个子节点
		// if (maxDictId.equals(TawSupplierkpiDictUtil.DICTID_DEFULT_VALUE)) {
		// maxDictId = parentDictId + TawSupplierkpiDictUtil.DICTID_NOSON;
		// } else {
		// deptidvar = Long.valueOf(maxDictId).longValue();
		// if (maxDictId.compareTo(maxDictId
		// + TawSupplierkpiDictUtil.DICTID_IF_MAXID) <
		// TawSupplierkpiDictUtil.DICTID_DEFULT_INTVAR) {
		// maxDictId = String.valueOf(deptidvar + 1);
		// } else {
		// maxDictId = TawSupplierkpiDictUtil.DICTID_MAXID;
		// }
		// }
		// return maxDictId;
		return tawSupplierkpiDictDao.getMaxDictId(parentDictId, parentDictId
				.length()
				+ TawSupplierkpiDictUtil.DICTID_BETWEEN_LENGTH);
	}

	public boolean isHaveSameLevel(String parentDictId) {
		return tawSupplierkpiDictDao.isHaveSameLevel(parentDictId);
	}

	public void saveTawSupplierkpiDict(TawSupplierkpiDict tawSupplierkpiDict) {
		tawSupplierkpiDictDao.saveTawSupplierkpiDict(tawSupplierkpiDict);
	}

	public void updateParentDictLeaf(String dictid, String leaf) {
		tawSupplierkpiDictDao.updateParentDictLeaf(dictid, leaf);
	}

	public String id2Name(String dictId) {
		return tawSupplierkpiDictDao.id2Name(dictId);
	}
}
