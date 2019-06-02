package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 作业计划模板组织管理信息数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.util.Hashtable;
import java.util.Enumeration;
import com.boco.eoms.workplan.vo.TawwpModelGroupVO;

public class TawwpModelGroupManageVO {

  private int allRow = 0;
  private int allCol = 0;
  private Hashtable allHashtable;

  public TawwpModelGroupManageVO() {
    allHashtable = new Hashtable();
  }

  public Hashtable getAllHashtable() {
    return allHashtable;
  }

  public void setAllHashtable(Hashtable allHashtable) {
    this.allHashtable = allHashtable;
  }

  public int getAllCol() {
    return allCol;
  }

  public void setAllCol(int allCol) {
    this.allCol = allCol;
  }

  public int getAllRow() {
    return allRow;
  }

  public void setAllRow(int allRow) {
    this.allRow = allRow;
  }

  public String printGroupTree(TawwpModelGroupVO _tawwpModelGroupVO,
                               String _modelPlanId, int _level) {
    StringBuffer str = null;
    Hashtable hashtable = null;
    Enumeration enumeration = null;
    TawwpModelGroupVO tawwpModelGroupVO = null;
    String treeStr = "";
    String no = "";

    if (_tawwpModelGroupVO != null) {
      hashtable = _tawwpModelGroupVO.getChildModelGroupVO();

      if (hashtable != null && hashtable.size() > 0) {
        enumeration = hashtable.keys();
        str = new StringBuffer();
        str.append("<span id='id_item_" + _tawwpModelGroupVO.getId() +
                     "' style='display:inline'>");
        while (enumeration.hasMoreElements()) {
          no = (String) enumeration.nextElement();
          tawwpModelGroupVO = (TawwpModelGroupVO) hashtable.get(no);

          str.append("<br>");
          str.append("<img src='../images/tree-v.gif' align=TEXTTOP>");

          for (int i = 0; i < _level; i++) {
            str.append(
              "<img src='../images/tree-v.gif' align=TEXTTOP border='0'>");
          }

          if(tawwpModelGroupVO.getChildModelGroupVO() != null && tawwpModelGroupVO.getChildModelGroupVO().size() > 0){
            str.append("<a class='blue' href=\"javascript:expandChildren('" +
                       tawwpModelGroupVO.getId() + "');\">");
            str.append(
                "<img Id='id_img1_" + _tawwpModelGroupVO.getId() +
                "' src='../images/tree2.gif' align=TEXTTOP border='0'>");
            str.append(
                "<img Id='id_img2_" + _tawwpModelGroupVO.getId() +
                "' src='../images/icon-group2.gif' align=TEXTTOP border='0'>");
            str.append("</a>");
          }
          else{
            str.append(
                "<img src='../images/tree-last.gif' align=TEXTTOP border='0'>");

            str.append("<a class='blue' href=\"javascript:expandChildren('" +
                       tawwpModelGroupVO.getId() + "');\">");
            str.append(
                "<img Id='id_img2_" + _tawwpModelGroupVO.getId() +
                "' src='../images/icon-group2.gif' align=TEXTTOP border='0'>");
            str.append("</a>");
          }
          str.append(
              "<a id='id_groupHref_" + tawwpModelGroupVO.getId() + "' class='blue' href='grouplist.do?modelplanid=" +
              _modelPlanId + "&modelgroupid=" + tawwpModelGroupVO.getId() +
              "'>");
          str.append(tawwpModelGroupVO.getName());
          str.append("</a>");
          str.append(this.printGroupTree(tawwpModelGroupVO, _modelPlanId,
                                         _level + 1));

        }
        str.append("</span>");
      }
    }
    if (str != null) {
      treeStr = str.toString();
    }

    return treeStr;
  }

}
