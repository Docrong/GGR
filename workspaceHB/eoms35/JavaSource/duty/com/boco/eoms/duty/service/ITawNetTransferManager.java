
package com.boco.eoms.duty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.duty.model.TawNetTransfer;

public interface ITawNetTransferManager extends Manager {
	public List getTawNetTransfers();
	 public TawNetTransfer getTawNetTransfer(final String id);
	 public void saveTawNetTransfer(final TawNetTransfer tawNetTransfer);
	 public void removeTawNetTransfer(final String id);
	 public Map getTawNetTransfers(final Integer curPage, final Integer pageSize,final String whereStr);
	 public Map getTawNetTransfers(final Integer curPage, final Integer pageSize);
	 public ArrayList getChildList(String parentId);
	 public List getTawNetTransferByCondition(String condition);
}

