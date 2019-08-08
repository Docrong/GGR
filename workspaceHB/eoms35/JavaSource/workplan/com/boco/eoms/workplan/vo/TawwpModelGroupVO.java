package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 作业计划模板组织信息数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

public class TawwpModelGroupVO {

    private String id; //标识
    private String name; //作业计划模板组织名称
    private TawwpModelGroupVO parentModelGroupVO; //父作业计划组织形式
    private Hashtable childModelGroupVO; //子作业计划组织形式
    private List modelExecuteVOList; //作业计划模版执行内容

    private int row = 0;
    private int col = 0;

    public TawwpModelGroupVO() {
    }

    public String getId() {
        if (id == null) {
            id = "";
        }

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Hashtable getChildModelGroupVO() {
        return childModelGroupVO;
    }

    public void setChildModelGroupVO(Hashtable childModelGroupVO) {
        this.childModelGroupVO = childModelGroupVO;
    }

    public List getModelExecuteVOList() {
        return modelExecuteVOList;
    }

    public void setModelExecuteVOList(List modelExecuteVOList) {
        this.modelExecuteVOList = modelExecuteVOList;
    }

    public String getName() {
        if (name == null) {
            name = "";
        }

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TawwpModelGroupVO getParentModelGroupVO() {
        return parentModelGroupVO;
    }

    public void setParentModelGroupVO(TawwpModelGroupVO parentModelGroupVO) {
        this.parentModelGroupVO = parentModelGroupVO;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

}
