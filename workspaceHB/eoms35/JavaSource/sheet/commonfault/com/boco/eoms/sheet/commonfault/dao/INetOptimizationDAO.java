package com.boco.eoms.sheet.commonfault.dao;

import java.sql.SQLException;

import com.boco.eoms.base.dao.Dao;

public interface INetOptimizationDAO {

    public void inserNetOpt(String sheetkey) throws SQLException;
}
