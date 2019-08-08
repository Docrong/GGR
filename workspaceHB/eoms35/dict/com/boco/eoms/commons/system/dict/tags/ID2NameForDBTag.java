package com.boco.eoms.commons.system.dict.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;

/**
 * <p>
 * Title:数据库的id2name
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-19 11:11:13
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public class ID2NameForDBTag extends TagSupport {

    /**
     *
     */
    private static final long serialVersionUID = -6089139100246144693L;

    /**
     * spring dao中的bean id
     */
    private String beanId;

    /**
     * 要转成name的id
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the beanId
     */
    public String getBeanId() {
        return beanId;
    }

    /**
     * @param beanId the beanId to set
     */
    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        //取id2name的service
        ID2NameService service = (ID2NameService) ApplicationContextHolder
                .getInstance().getBean("ID2NameGetServiceCatch");

        try {
            //输出name
            out.print(service.id2Name(id, beanId));
        } catch (IOException e) {

        }

        return SKIP_BODY;
    }

    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

}
