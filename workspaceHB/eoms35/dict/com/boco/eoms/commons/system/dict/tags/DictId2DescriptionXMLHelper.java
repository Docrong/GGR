package com.boco.eoms.commons.system.dict.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.model.DictTagHelper;
import com.boco.eoms.commons.system.dict.model.IDictTagHelper;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.Constants;
import com.boco.eoms.commons.system.dict.util.Util;

/**
 * <p>
 * Title:字典类别id转成描述
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-25 12:41:03
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictId2DescriptionXMLHelper implements IDictTagStrategyHelper {

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.commons.system.dict.tags.IDictTagHelper#doEndTag(com.boco.eoms.commons.system.dict.model.DictTag,
     *      javax.servlet.jsp.PageContext)
     */
    public int doEndTag(IDictTagHelper dictTag, PageContext pageContext)
            throws JspException {
        JspWriter out = pageContext.getOut();
        //取id2name的service
        IDictService service = (IDictService) ApplicationContextHolder
                .getInstance().getBean("DictService");
        try {
            //输出description
            out.print(service.dictId2description(Util.constituteDictId(dictTag
                    .getKey(), dictTag.getDictId())));
        } catch (Exception e) {
            try {
                out.print(Constants.ID_NO_NAME);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.commons.system.dict.tags.IDictTagHelper#doStartTag(com.boco.eoms.commons.system.dict.model.DictTag,
     *      javax.servlet.jsp.PageContext)
     */
    public int doStartTag(DictTagHelper dictTag, PageContext pageContext)
            throws JspException {
        // TODO Auto-generated method stub
        return 0;
    }
}
