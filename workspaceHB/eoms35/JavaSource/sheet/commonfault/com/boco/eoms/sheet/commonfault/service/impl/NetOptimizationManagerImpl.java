package com.boco.eoms.sheet.commonfault.service.impl;

import java.sql.SQLException;

import com.boco.eoms.sheet.commonfault.dao.INetOptimizationDAO;
import com.boco.eoms.sheet.commonfault.service.INetOptimizationManger;

public class NetOptimizationManagerImpl implements INetOptimizationManger {

	private INetOptimizationDAO iNetOptimizationDAO;
	
	public void inserNetOpt(String sheetkey) throws SQLException {
		// TODO 自动生成方法存根
		iNetOptimizationDAO.inserNetOpt(sheetkey);
		
	}

	public INetOptimizationDAO getINetOptimizationDAO() {
		return iNetOptimizationDAO;
	}

	public void setINetOptimizationDAO(INetOptimizationDAO netOptimizationDAO) {
		this.iNetOptimizationDAO = netOptimizationDAO;
	}

	
	
}
