/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.complaint.service;

import com.boco.eoms.sheet.complaint.model.ComplaintSel;


/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IComplaintSelectManager {


    public String getPhoneBySheetId(String sheetId);

    public String getSheetIdsByPhone(String customPhone);

    public void saveSelComplaint(ComplaintSel complaintSel);
}