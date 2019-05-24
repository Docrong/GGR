package com.boco.eoms.duty.bo;

import com.boco.eoms.common.bo.BO;
 
import com.boco.eoms.duty.controller.TawRmCycleTableForm;
import com.boco.eoms.duty.dao.TawRmCycleTableDAO;
 
import com.boco.eoms.duty.model.TawRmCycleTable;

public class TawRmCycleTableBO extends BO{
	public TawRmCycleTableBO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}
	public void insert(TawRmCycleTableForm tawRmCycleTableForm) {
		TawRmCycleTableDAO tawRmCycleTableDAO = null;
		TawRmCycleTable tawRmCycleTable = null;
		try {
			tawRmCycleTableDAO = new TawRmCycleTableDAO(ds);
			tawRmCycleTable = new TawRmCycleTable();
			tawRmCycleTable.setModel(tawRmCycleTableForm.getModel());
			tawRmCycleTable.setCreatTime(tawRmCycleTableForm.getCreatTime());
			tawRmCycleTable.setCreatUser(tawRmCycleTableForm.getCreatUser());
			tawRmCycleTable.setName(tawRmCycleTableForm.getName());
			tawRmCycleTable.setRemark(tawRmCycleTableForm.getRemark());
			tawRmCycleTable.setRoomId(tawRmCycleTableForm.getRoomId());
			tawRmCycleTable.setUrl(tawRmCycleTableForm.getUrl());
			
			tawRmCycleTableDAO.inset(tawRmCycleTable);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
