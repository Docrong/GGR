package com.boco.eoms.sparepart.util;

/**
 * <p>Title: ��Ʒ����</p>
 * <p>Description: interface �ṩ��Ʒ������ϵͳ��ȫ�ֱ�</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.7
 */

public interface StaticPart{

    public static final String APP_PART_OK="新备件入库成功！";
    public static final String ORDER_OVER="备件操作已经完成！";
    public static final String UPDATE_OVER="备件修改成功！";
    public static final String NO_DATA="没有相应数据！";
    public static final String PART_DELETE_OK="备件删除成功！";
    public static final String NO_PART_NAME="该备件名称不存在！";
    public static final String REMOVE_OVER="备件转库成功！";

    public static final String CLASS_NAME_HAS="该类型已经存在！";
    public static final String DELETE_CLASSMSG="该类型已经删除！";
    public static final String UPDATE_CLASSMSG="类型修改成功！";
    public static final String ADD_CLASSMSG="类型增加成功！";

    public static final String SET_PART_OK="库存设置成功！";
    public static final String SET_ORDER_OK="单据设置成功！";
    public static final String PART_MANY="库存过多！";
    public static final String PART_ABSENT="库存过少！";

    public static final String CHOOSED_OK="选择仓库成功，以下操作将针对本仓库进行！";
    public static final String STOTAGE_NAME_HAS="仓库名称已经存在！";
    public static final String STOTAGE_CREATE_OK="仓库创建成功！";
    public static final String STOTAGE_DELETE_OK="仓库删除成功！";
    public static final String STOTAGE_UPDATE_OK="仓库修改成功！";

    public static final String No_CLASS_NAME="这里不能添加备件名称！";

    public static final String NO_NAME="该备件名称不存在！";
    public static final String BACK_PART_OK="备件退库成功！";
    public static final String BACK_PART_NO="备件退库成功！";
    public static final String[] ORDER_NAME={
          "","借出单","领用单","维修出库单","待修单","报废单","转库单","扩容单"};
    /**
     * @see 备品备件树型结构数据
     */

    public static final String NO_NETROOT="没有网元类型，请先添加网元类型！";
    public static final String NO_DEPT="没有大专业类型，请先添加大专业类型！";
    public static final String NO_SUB_DEPT="没有小专业类型，请先添加小专业类型！";
    
    public static final String[] UPDATE_TREE_OK={"大专业类型编辑成功！",
          "小专业类型编辑成功！","网元类型编辑成功！","备件类型编辑成功！"};
    public static final String[] ADD_TREE={"添加大专业类型",
          "添加小专业类型","添加网元类型","添加备件类型"};
    public static final String[] CREATE_TREE_OK={"大专业类型添加成功！",
          "小专业类型添加成功！","网元类型添加成功！","备件类型添加成功！"};

    public static final String[] DROP_TREE_OK={"大专业类型删除成功！",
          "小专业类型删除成功！","网元类型删除成功！","备件类型删除成功！"};

    public static final String[] treeMsg={"大专业类型信息",
          "小专业类型信息","网元类型信息","备件类型信息"};
    public static final String APP_PART_FAILURE="添加备件失败！";
}
