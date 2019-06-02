
package com.boco.eoms.duty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.boco.eoms.base.service.Manager;
import com.boco.eoms.duty.model.TawExpertInfo;

public interface ITawExpertInfoManager extends Manager {
	public List getTawExpertInfos();
	 public TawExpertInfo getTawExpertInfo(final String id);
	 public void saveTawExpertInfo(final TawExpertInfo tawExpertInfo);
	 public void removeTawExpertInfo(final String id);
	 public Map getTawExpertInfos(final Integer curPage, final Integer pageSize,final String whereStr);
	 public Map getTawExpertInfos(final Integer curPage, final Integer pageSize);
	 public ArrayList getChildList(String parentId);
	 public List getTawExpertInfosByCondition(String condition);
}

