package com.boco.eoms.common.oo;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import com.boco.eoms.common.util.StaticMethod;

public abstract class QueryDataObject
    implements DataObject {

  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String addLikeClause(String poName, String clause, String clauseValue) {
    String str = "";
    if (!StaticMethod.null2String(clauseValue, "").equals("")) {
      str = " and " + poName + "." + clause + "  like '%" + clauseValue + "%' ";
    }
    return str;
  }

  public String addEqualClause(String poName, String clause, String clauseValue) {
    String str = "";
    if (!StaticMethod.null2String(clauseValue, "").equals("")) {
      str = " and " + poName + "." + clause + "  = '" + clauseValue + "' ";
    }
    return str;
  }

  public String addEqualIntClause(String poName, String clause,
                                  String clauseValue) {
    String str = "";
    if (!StaticMethod.null2String(clauseValue, "").equals("")) {
      str = " and " + poName + "." + clause + "  = " + clauseValue + " ";
    }
    return str;
  }

  public String addGreatClause(String poName, String clause, String clauseValue) {
    String str = "";
    if (!StaticMethod.null2String(clauseValue, "").equals("")) {
      str = " and " + poName + "." + clause + "  > " + clauseValue;
    }
    return str;
  }

  public String addLessClause(String poName, String clause, String clauseValue) {
    String str = "";
    if (!StaticMethod.null2String(clauseValue, "").equals("")) {
      str = " and " + poName + "." + clause + "  < " + clauseValue;
    }
    return str;
  }
  public String addLessClause1(String poName, String clause, String clauseValue) {
      String str = "";
      if (!StaticMethod.null2String(clauseValue, "").equals("")) {
        str = " and " + poName + "." + clause + "  < '" + clauseValue+"'";
      }
      return str;
    }

  public String addInClause(String poName, String clause, String clauseValue) {
    String str = "";
    if (!StaticMethod.null2String(clauseValue, "").equals("")) {
      str = " and " + poName + "." + clause + "  in (" + clauseValue +")";
    }
    return str;
  }

}
