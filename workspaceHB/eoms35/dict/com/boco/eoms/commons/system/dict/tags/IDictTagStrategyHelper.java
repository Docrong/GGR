package com.boco.eoms.commons.system.dict.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import com.boco.eoms.commons.system.dict.model.DictTagHelper;
import com.boco.eoms.commons.system.dict.model.IDictTagHelper;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-24 19:45:26
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface IDictTagStrategyHelper {
    /**
     * 标签结束
     * 
     * @param dictTag
     *            标签传递的属性
     * @param pageContext
     *            page上下文
     * @return
     * @throws JspException
     */
    public int doEndTag(IDictTagHelper dictTag, PageContext pageContext)
            throws JspException;

    /**
     * 标签开始
     * 
     * @param dictTag
     *            标签传递的属性
     * @param pageContext
     *            page上下文
     * @return
     * @throws JspException
     */
    public int doStartTag(DictTagHelper dictTag, PageContext pageContext)
            throws JspException;
}
