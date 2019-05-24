package com.boco.eoms.interfaces.log.qo;

import com.boco.eoms.common.oo.QueryDataObject;

public class InterfaceLogQO extends QueryDataObject{
  private String sheetId;
  private String poName = "interfaceLog";
  private String formName =
      " select distinct interfaceLog from InterfaceLog as interfaceLog where ";
  private String clauseSql = "";
  private String orderSql = " order by interfaceLog.sheetId desc ";

  public InterfaceLogQO() {
  }
  public String getClauseSql() {
    int str_index = clauseSql.indexOf("and ");
    return this.formName + clauseSql.substring(str_index + 4) + orderSql;
  }
  public void setSheetId(String sheetId) {
    this.clauseSql += addLikeClause(poName, "sheetId", sheetId);
    this.sheetId = sheetId;
  }


}
