
package com.boco.eoms.duty.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.model.TawNetTransferSub;

public interface ITawNetTransferSubDao extends Dao {

	public List getTawNetTransferSubs();
	 public TawNetTransferSub getTawNetTransferSub(final String id);
	 public void saveTawNetTransferSub(final TawNetTransferSub tawNetTransferSub);
	 public void removeTawNetTransferSub(final String id);
	 public Map getTawNetTransferSubs(final Integer curPage, final Integer pageSize,final String whereStr);
	 public Map getTawNetTransferSubs(final Integer curPage, final Integer pageSize);
	 public ArrayList getChildList(String parentId);
	 public List getTawNetTransferSubByCondition(String condition);
	 public List getTawNetTransferSubByMainId(String mainId);
}

