package com.boco.eoms.commons.system.area.model.util;

import java.io.Serializable;

/**
 *
 *panlong
 *下午04:51:19
 */
public interface TawSystemAreaUtil extends Serializable {

//	初始化默认的变量值
	public static final int AREA_DEFAULT_INTVAL=0;
	public static final int AREA_DEFAULT_INTHVAL=1;
	public static final long AREA_DEFAULT_LONGVAL=0;
	public static final String AREA_DEFAULT_STRVAL="";
	public static final String AREA_DEFAULT_STRONE="1";
	public static final String AREA_DEFAULT_LEAFONE="1";
	public static final String AREA_DEFAULT_LEAFZERO="0";
	public static final String AREA_DEFAULT_PARENTVAL="-1";
	public static final String AREA_DEFAULT_CODE="";
	public static final Integer AREA_DEFAULT_ORDERCODEVAL=new Integer(1);
	
//	如果某个字典ID下没有子节点时需要追加
	public static final String AREAID_NOSON="01";
	
//	地域ID按照一定规律子ID跟父ID的length之差为2
	public static final int AREAID_BETWEEN_LENGTH=2;
	//判断地域ID是否为设置的最大值
	public static final String AREAID_IF_MAXID="99";
	
//	最大地域ID值
	public static final String AREAID_MAXID="-10";
	
	
}

