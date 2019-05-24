package com.boco.eoms.commons.statistic.base.util;

/**
 * @author lizhenyou
 *
 *	常量类
 */
public class Constants {	
	/**
	 * 
	 */
	public static final String $DISENABLE$ = "$disenable$";
	/**
	 * 默认行高
	 */
	public static final int DEFAULT_HEIGHT_INPOINT = 10;
	
	/**
	 * 默认列宽
	 */
	public static final int DEFAULT_COL_WIDTH = 2304 ;
	
	/**
	 * 配置信息 table
	 */
	public static final String CONFIGTABLE = "table";
	
	/**
	 * 配置信息 width
	 */
	public static final String CONFIGWIDTH = "width";
	
	/**
	 * 配置信息 head_start
	 */
	public static final String CONFIGHEAD_START = "head_start";
	
	/**
	 * 配置信息 head_end
	 */
	public static final String CONFIGHEAD_END = "head_end";
	
	/**
	 * 配置信息 body_start
	 */
	public static final String CONFIGBODY_START = "body_start";
	
	/**
	 * 配置信息 body_end
	 */
	public static final String CONFIGBODY_END = "body_end";
	
	/**
	 * 配置信息 foot_start
	 */
	public static final String CONFIGFOOT_START = "foot_start";
	
	/**
	 * 配置信息 foot_end
	 */
	public static final String CONFIGFOOT_END = "foot_end";
	
	/**
	 * $info$标签
	 */
	public static final String $INFO$ = "$info$";
	
	/**
	 * $total$标签
	 */
	public static final String $TOTAL$ = "$total$";
	
	/**
	 * sumarrayunite标签
	 */
	public static final String SUMARRAYUNITE = "sumarrayunite";
	
	/**
	 * $summary$标签
	 */
	public static final String $SUMMARY$ = "$summary$";
	
	/**
	 * $data$标签
	 */
	public static final String $DATA$ = "$data$";
	
	/**
	 * statcolum-x标签
	 */
	public static final String $STATCOLUM_X$ = "statcolum-x";
	
	/**
	 * $sn$标签
	 */
	public static final String $SN$ = "$sn$";
	
	/**
	 * $colum$标签
	 */
	public static final String $COLUM$ = "$colum$";
	
	/**
	 * $dictionaryid$字典名字
	 */
	public static final String DICTIONARYID = "$dictionaryid$";
	
	/**
	 * $dictionarydatekey$ 字典中的key，根据key取得相应的value
	 */
	public static final String DICTIONARYDATEKEY = "$dictionarydatekey$";
	
	/**
	 * 最后的时间
	 */
	public static final String LASTTIME = "lasttime";
	
	/**
	 * 最后的时间数据库的ID
	 */
	public static final int LASTTIMEID = 9;
	
	/**
	 * WEB-INF名称
	 */
	public static final String WEB_INF = "WEB-INF";
	
	public static final String CLASSPATH = "classpath:config/statistic/base-config/applicationContext-statistic-base.xml";
	
	/**
	 * column,line,pie,columnline
	 */
	public static final String COLUMN = "column";
	public static final String LINE = "line";
	public static final String PIE = "pie";
	public static final String COLUMNLINE = "columnline";
	
	public final static  String STAT = "stat";
	
	public final static  String PERCENT= "Percent";
	
	public final static  String[] G_TYPE= {COLUMN,LINE,COLUMNLINE};
	
	/**
	 * 导出Excel存放的临时路径
	 */
	public static final String EXPORTEXCELFILEPATH = "statisticfile/file/";
	
	/**
	 * 定制年统计文件夹
	 */
	public static final String CUSTOMSTATISTICYEARFILE = "statisticfile/customstatisticfile/yearfile/";
	
	
	/**
	 * 定制季度统计文件夹
	 */
	public static final String CUSTOMSTATISTICSEASONFILE = "statisticfile/customstatisticfile/seasonfile/";
	
	/**
	 * 定制月统计文件夹
	 */
	public static final String CUSTOMSTATISTICMONTHFILE = "statisticfile/customstatisticfile/monthfile/";
	
	/**
	 * 定制周统计文件夹
	 */
	public static final String CUSTOMSTATISTICWEEKFILE = "statisticfile/customstatisticfile/weekfile/";
	
	/**
	 * 定制日统计文件夹
	 */
	public static final String CUSTOMSTATISTICDAILYFILE = "statisticfile/customstatisticfile/dailyfile/";
	
	/**
	 * 定制自定义统计
	 */
	public static final String CUSTOMSTATISTICCUSTOMFILE = "statisticfile/customstatisticfile/customfile/";
	
	/**
	 * graphics图形报表文件目录
	 */
	public static final String GRAPHICSFILEPATH = "statisticfile/swfs/file/";
	
	/**
	 * 算法配置文件名称
	 */
	public static final String QUARYFILENAME =  "queryfilename";
	
	/**
	 * 动态列信息
	 */
	public static final String DYCOLUM =  "dycolum";
	
	/**
	 * 可以显示的最大行数
	 */
	public static final String SHOWMAXROW =  "showmaxrow";
	
	/**
	 * 订制信息DB表名称
	 */
	public static final String EST_CUSTOMSTATISTICINFO =  "est_customsatatisticinfo";
	
	/**
	 * 订制统计结果文件DB表名称
	 */
	public static final String EST_SATATISTICFILEINFO =  "est_satatisticfileinfo";
	
    /**
     * 当查找不到对应id名称时显示
     */
    public final static String ID_NO_NAME = "";
    
    /**
     * 是否应该加单引号（在组成sql中 例如 where id = 12345a 转换为 where id = '12345a' ）
     */
    public final static String MARKFLG = "true";
    
    //=======================增加com.boco.eoms.base.Constants下的常量=================================
    
	/** The name of the configuration hashmap stored in application scope. */
	public static final String CONFIG = "appConfig";
	
	/**
	 * 超级管理员
	 */
	public static final String ADMINISTRATOR = "admin";
	
	
	  /**
	   * 下拉列表查询全部的ID
	   */
	  public final static String SELECT_QUERY_ALL_ID = "-1";

	  /**
	   * 下拉列表查询全部的name
	   */
	  public final static String SELECT_QUERY_ALL_NAME = "全部";

	  /**
	   * 在关联关系dict-relation.xml配置中，目的关联的多个id,以逗号隔开
	   */
	  public final static String DESTINATION_ITEM_IDS_SPLIT = ",";
	  
      /**
	   * xml字典中dictId中的分隔符，一般为key&dictId中的&
	   */
	  public final static String DICT_ID_SPLIT_CHAR = "#";
    
}
