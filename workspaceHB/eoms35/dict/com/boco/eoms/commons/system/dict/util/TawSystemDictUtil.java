package com.boco.eoms.commons.system.dict.util;

import java.io.Serializable;

/**
 *
 *panlong
 *下午05:32:11
 */
public interface TawSystemDictUtil extends Serializable {

	//字典ID按照一定规律子ID跟父ID的length之差为2
	public static final int DICTID_BETWEEN_LENGTH=2;
	
	//如果字典没有数据的情况下设置一个默认值
	public static final String DICTID_DEFULT_VALUE="1";
	
    //如果某个字典ID下没有子节点时需要追加
	public static final String DICTID_NOSON="01";
	
	//判断字典ID是否为设置的最大值
	public static final String DICTID_IF_MAXID="99";
	
	//最大字典ID值
	public static final String DICTID_MAXID="-10";
	
	//初始化默认的变量值
	public static final long DICTID_DEFULT_LONGVAR=0;
	public static final String DICTID_DEFULT_STRINGVAR="";
	public static final int DICTID_DEFULT_INTVAR=0;
	
}

