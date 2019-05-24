package com.boco.eoms.commons.system.dict.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.system.mappingstorage.ehcache.DictCacheBean;
import com.boco.eoms.system.mappingstorage.mgr.MappingMgr;




/**
 * <p>
 * Title:字典反显标签类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-04-14 16:00:00
 * </p>
 * 
 * @author 史闯科
 * @version 1.0
 * 
 */

public class DictInvertTag extends TagSupport {
	
	
	   /** 应用模块编号*/
	    private String appCode= "";
	    
	    /**
	     * 相关工单sheetkey
	     */
        private String sheetKey="";
        
	   /** 附件在formBean中的属性名*/
	    private String property;
	 
	    /**查询的范围*/
	    private String scope;
	  
	    /**
		 * 字典id，标识某类字典，如：专业、
		 */
		private String dictId;
		
	   
	    /**
		 * 关联字典对应的字典id
		 */
		private String relationId;
	  
	    /**
		 * spring dao中的bean id
		 */
		private String beanId;
	  
	    /**
		 * alt属性
		 */
		private String alt;
		

		public String getAlt() {
			return alt;
		}

		public void setAlt(String alt) {
			this.alt = alt;
		}

		public String getAppCode() {
			return appCode;
		}

		public void setAppCode(String appCode) {
			this.appCode = appCode;
		}

		public String getBeanId() {
			return beanId;
		}

		public void setBeanId(String beanId) {
			this.beanId = beanId;
		}

		public String getDictId() {
			return dictId;
		}

		public void setDictId(String dictId) {
			this.dictId = dictId;
		}

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}

		public String getRelationId() {
			return relationId;
		}

		public void setRelationId(String relationId) {
			this.relationId = relationId;
		}

		public String getScope() {
			return scope;
		}

		public void setScope(String scope) {
			this.scope = scope;
		}

		/**
		 * 做为参数传递给策略类
		 */
		
		private DictInvertTag invert;

		/**
		 * 创建DictInvertTag对象，此对象传递给策略类
		 * 
		 */

		public int doStartTag() throws JspException {		    
		    //// 执行策略类的doStartTag
		      return TagSupport.EVAL_BODY_INCLUDE;//根据返回值看看标签结束后要怎样执行别的代码

		    }	
		public int doEndTag() throws JspException {
			JspWriter out = pageContext.getOut();
	        //取id2name的service
			/*MappingMgr mgr = (MappingMgr) ApplicationContextHolder
	                .getInstance().getBean("mappingMgr");

	        try {
	            //输出name
	            out.print(mgr.dictIdToName(appCode, sheetKey));
	        } catch (IOException e) {

	        }*/
			
			DictCacheBean CacheBean=(DictCacheBean)ApplicationContextHolder.getInstance().getBean("CacheBean");
			try{
				String dictName = CacheBean.getDictData(appCode, sheetKey);
				out.print(dictName);
			}catch(IOException e){
				
			}

	        return SKIP_BODY;
	    }
		
		public void release() {
			   // TODO Auto-generated method stub
			   super.release();
			}


		public DictInvertTag getInvert() {
			return invert;
		}

		public void setInvert(DictInvertTag invert) {
			this.invert = invert;
		}

		public String getSheetKey() {
			return sheetKey;
		}

		public void setSheetKey(String sheetKey) {
			this.sheetKey = sheetKey;
		}
}
