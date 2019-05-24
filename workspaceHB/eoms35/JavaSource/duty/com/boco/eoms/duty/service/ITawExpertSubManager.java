
package com.boco.eoms.duty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.duty.model.TawExpertSub;

public interface ITawExpertSubManager extends Manager {
	public List getTawExpertSubs();
	 public TawExpertSub getTawExpertSub(final String id);
	 public void saveTawExpertSub(final TawExpertSub tawExpertSub);
	 public void removeTawExpertSub(final String id);
	 public Map getTawExpertSubs(final Integer curPage, final Integer pageSize,final String whereStr);
	 public Map getTawExpertSubs(final Integer curPage, final Integer pageSize);
	 public ArrayList getChildList(String parentId);
	 public List getTawExpertSubByCondition(String condition);
	 public List getTawExpertSubByMainId(String mainId);
}

