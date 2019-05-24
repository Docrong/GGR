package com.boco.eoms.partner.baseinfo.util;

/**
 * <p>
 * Title:地域部门树
 * </p>
 * <p>
 * Description:地域部门树
 * </p>
 * <p>
 * Fri Feb 06 11:46:50 CST 2009
 * </p>
 * 
 * @author liujinlong
 * @version 3.5
 * 
 */
public class AreaDeptTreeConstants {
	
	/**
	 * 根结点
	 */
	public final static String TREE_ROOT_ID = "1";
	
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
	/**
	 * 节点类型 省province
	 */
	public final static String NODE_TYPE_PROVINCE = "province";
	/**
	 * 节点类型 地市area
	 */
	public final static String NODE_TYPE_AREA = "area";
	/**
	 * 节点类型 地市factory
	 */
	public final static String NODE_TYPE_FACTORY = "factory";
	/**
	 * 节点类型 人员user
	 */
	public final static String NODE_TYPE_USER = "user";
	/**
	 * 节点类型 仪器仪表instrument
	 */
	public final static String NODE_TYPE_INSTRUMENT = "instrument";
	/**
	 * 节点类型 车辆管理car
	 */
	public final static String NODE_TYPE_CAR = "car";
	/**
	 * 节点类型 油机oil
	 */
	public final static String NODE_TYPE_OIL = "oil";
}