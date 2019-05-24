/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.complaint.service.impl;

import com.boco.eoms.sheet.complaint.dao.IComplaintSelectDAO;
import com.boco.eoms.sheet.complaint.model.ComplaintSel;
import com.boco.eoms.sheet.complaint.service.IComplaintSelectManager;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComplaintSelectManagerImpl implements IComplaintSelectManager {
	
	private IComplaintSelectDAO complaintSelectDAO;

	public IComplaintSelectDAO getComplaintSelectDAO() {
		return complaintSelectDAO;
	}

	public void setComplaintSelectDAO(IComplaintSelectDAO complaintSelectDAO) {
		this.complaintSelectDAO = complaintSelectDAO;
	}
	
	public String getPhoneBySheetId(String sheetId) {
		// TODO Auto-generated method stub
		return complaintSelectDAO.getPhoneBySheetId(sheetId);
	}

	public String getSheetIdsByPhone(String customPhone) {
		// TODO Auto-generated method stub
		return complaintSelectDAO.getSheetIdsByPhone(customPhone);
	}

	public void saveSelComplaint(ComplaintSel complaintSel) {
		// TODO Auto-generated method stub
		complaintSelectDAO.saveSelComplaint(complaintSel);
	}
}
