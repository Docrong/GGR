package com.boco.eoms.operuser.util;

/**
 * <p>
 * Title:operuser
 * </p>
 * <p>
 * Description:operuser
 * </p>
 * <p>
 * Tue Mar 31 09:42:13 CST 2009
 * </p>
 * 
 * @author xiang
 * @version 35
 * 
 */
public class OperuserConstants {
	
	/**
	 * 根结点
	 */
	public final static String TREE_ROOT_ID = "-1";
	
	/**
	 * 节点ID按照规则子ID跟父ID的length之差为2
	 */
	public static final int NODEID_BETWEEN_LENGTH = 2;
	
	/**
	 * 如果节点没有数据的情况下设置一个默认值
	 */
	public static final String NODEID_DEFAULT_VALUE = "-1";
	
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
}