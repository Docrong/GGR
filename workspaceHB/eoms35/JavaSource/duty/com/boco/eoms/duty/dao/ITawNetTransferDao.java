
package com.boco.eoms.duty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawNetTransfer;

public interface ITawNetTransferDao extends Dao {

	public List getTawNetTransfers();
	 public TawNetTransfer getTawNetTransfer(final String id);
	 public void saveTawNetTransfer(final TawNetTransfer tawNetTransfer);
	 public void removeTawNetTransfer(final String id);
	 public Map getTawNetTransfers(final Integer curPage, final Integer pageSize,final String whereStr);
	 public Map getTawNetTransfers(final Integer curPage, final Integer pageSize);
	 public ArrayList getChildList(String parentId);
	 public List getTawNetTransferByCondition(String condition);
}

