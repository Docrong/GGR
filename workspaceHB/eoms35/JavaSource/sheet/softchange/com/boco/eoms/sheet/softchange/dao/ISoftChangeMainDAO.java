
package com.boco.eoms.sheet.softchange.dao;

import com.boco.eoms.sheet.base.dao.IMainDAO;

public interface ISoftChangeMainDAO extends IMainDAO {
	public void DeleteEarlyEmptyMain(Object mainObject);
}

