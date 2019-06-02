package com.boco.eoms.commons.system.dict.service.bo;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;

/**
 *
 *panlong
 *下午05:07:02
 */
public class TawSystemDictBo {

	private TawSystemDictBo() {
		
	}
    private static TawSystemDictBo instance;
   
	public static TawSystemDictBo getInstance() {
		if( instance==null){
		  instance = new TawSystemDictBo();
		}
		return instance;
	}
	
	
	public static ITawSystemDictTypeManager getManager(){
		ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager)ApplicationContextHolder
		.getInstance().getBean("ItawSystemDictTypeManager");
		return mgr;
	}
	/**
	 * 通过字典ID查询字典名称
	 * @param dictid
	 */
	public String getDictNameByDictid( String dictid ){
		ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager)ApplicationContextHolder
		.getInstance().getBean("ItawSystemDictTypeManager");
		return mgr.getDictByDictId(dictid).getDictName();
	}
	/**
	 * 通过字典ID查询字典编码
	 * @param dictid
	 * @return
	 */
	public String getDictCodeByDictid( String dictid ){
		return getManager().getDictTypeByDictid(dictid).getDictCode();
	}
	/**
	 * 通过CODE查询所有code字典信息
	 * @param code
	 * @return
	 */
	public List getDictByCode( String code ){
		return getManager().getDictByCode(code);
	}
	 /**
     * 根据 parentDictId 和 dictCode获取 TawSystemDictType
     * @param tawSystemDictType
     * @param tawSystemDictCode
     * @return TawSystemDictType
     * @author liqiuye	2007-11-14
     */
    public TawSystemDictType getDictByDictType(String dictCode,String parentDictId){
    	return  getManager().getDictByDictType(dictCode,parentDictId);
    }
    /**
     * 根据   dictCode获取 TawSystemDictType 
     * @param tawSystemDictCode
     * @return TawSystemDictType
     * @author liqiuye	2007-11-14
     */
    public TawSystemDictType getDictByDictType(String dictCode){
    	return  getManager().getDictByDictType(dictCode);
    }
}

