package com.boco.eoms.km.table.util;

/**
 * <p>
 * Title:模块信息表
 * </p>
 * <p>
 * Description:模块信息表
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 * 
 * @author 吕卫华
 * @version 1.0
 * 
 */
public class KmTableGeneralConstants {
	
	/**
	 * list key
	 */
	public final static String KMTABLEGENERAL_LIST = "kmTableGeneralList";
	
	/**
	 * 根结点
	 */
	public final static String TREE_ROOT_ID = "1";
	
	/**
	 * 模型使用的字典根节点编号
	 * 注意：105 是平台分配给“知识管理”模块使用的根节点号段
	 * 注意：10501 是“知识管理-模型定义”模块使用的字典号段
	 */
	public final static String DICT_TREE_ROOT_ID = "-1";

	public final static String FILE_TREE_ROOT_ID = "1";

	/**
	 * 节点ID按照规则子ID跟父ID的length之差为2
	 */
	public static final int NODEID_BETWEEN_LENGTH = 2;
	
	/**
	 * 如果节点没有数据的情况下设置一个默认值
	 */
	public static final String NODEID_DEFAULT_VALUE = "1";
	
	/**
	 * 如果某个节点ID下没有子节点时需要追加
	 */
	public static final String NODEID_NOSON = "01";
	
	/**
	 * 判断节点ID是否为设置的最大值
	 */
	public static final String NODEID_IF_MAXID = "99";
	
	/**
	 * 无名称节点
	 */
	public static final String NODE_NONAME = "未知名称";
	
	/**
	 * 叶节点
	 */
	public final static String NODE_LEAF = "1";

	/**
	 * 非叶节点
	 */
	public final static String NODE_NOTLEAF = "0";
	
	public final static String[] COLTYPE= {
		"VARCHAR2","VARCHAR2","DATE",
		"VARCHAR2","VARCHAR2","VARCHAR2"
	};
}