package com.boco.eoms.commons.system.dict.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.jdom.Element;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.model.DictTagHelper;
import com.boco.eoms.commons.system.dict.model.IDictItem;
import com.boco.eoms.commons.system.dict.model.IDictRelation;
import com.boco.eoms.commons.system.dict.model.IDictTagHelper;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.Constants;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.ui.util.JSONUtil;

/**
 * <p>
 * Title:下拉列表关联xml
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-26 10:25:58
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class SelectRelationXML implements IDictTagStrategyHelper {
    private Logger logger = Logger.getLogger(SelectXML.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.commons.system.dict.tags.IDictTagStrategyHelper#doEndTag(com.boco.eoms.commons.system.dict.model.DictTagHelper,
     *      javax.servlet.jsp.PageContext)
     */
    public int doEndTag(IDictTagHelper dictTag, PageContext pageContext)
            throws JspException {
        JspWriter out = pageContext.getOut();
        //取id2name的service
        IDictService service = (IDictService) ApplicationContextHolder
                .getInstance().getBean("DictService");
        List list = null;
        try {
            IDictRelation relation = service
                    .getRelationButItems(Util.constituteDictId(
                            dictTag.getKey(), dictTag.getRelationId()));
            list = service.getDictItems(Util.constituteDictId(relation
                    .getSourceDictKey(), relation.getSourceDictId()));
        } catch (Exception e) {
            // TODO: handle exception
        }
        Element rootElement = new Element("select");
        rootElement.setAttribute("name", dictTag.getSelectId());
        rootElement.setAttribute("id", dictTag.getSelectId());
        if (null != list && !list.isEmpty()) {

            //不需要联动
            //        StringBuffer str = new StringBuffer("<select name='"
            //                + dictTag.getSelectId() + "' id='" + dictTag.getSelectId()
            //                + "'>");

            //若isQuery为true，在select中加入option"全部"
            if (dictTag.getIsQuery() != null
                    && "true".equalsIgnoreCase(dictTag.getIsQuery())) {
                //加入option"全部"
                Element optionElement = new Element("option");
                optionElement.setAttribute("id", Constants.SELECT_QUERY_ALL_ID);
                optionElement.addContent(Constants.SELECT_QUERY_ALL_NAME);
                rootElement.addContent(optionElement);

                //            str.append("<option id='" + Constants.SELECT_QUERY_ALL_ID +
                // "'>"
                //                    + Constants.SELECT_QUERY_ALL_NAME + "</option>");
            }
            // 若不为空则将列表中的option加入
            if (list != null && !list.isEmpty()) {
                //不需要定位在默认id
                if (dictTag.getDefaultId() == null
                        || "".equals(dictTag.getDefaultId())) {
                    for (Iterator it = list.iterator(); it.hasNext();) {
                        IDictItem item = (IDictItem) it.next();
                        //列表中的option加入
                        Element option = new Element("option");
                        option.setAttribute("id", item.getItemId().toString());
                        option.addContent(item.getItemName().toString());
                        rootElement.addContent(option);
                        //列表中的option加入
                        //                    str.append("<option id='" + item.getItemId() + "'>"
                        //                            + item.getItemName() + "</option>");
                    }
                }
                //需要定位在默认id
                else {
                    for (Iterator it = list.iterator(); it.hasNext();) {
                        IDictItem item = (IDictItem) it.next();
                        //若defaultId与itemId相同，则要selected
                        if (dictTag.getDefaultId().equals(item.getItemId())) {
                            //                      列表中的option加入要默认定位到defaultId位置
                            Element option = new Element("option");
                            option.setAttribute("id", item.getItemId()
                                    .toString());
                            option.setAttribute("selected", "selected");
                            option.addContent(item.getItemName().toString());
                            rootElement.addContent(option);

                            //                        str.append("<option id='" + item.getItemId()
                            //                                + "' selected>" + item.getItemName()
                            //                                + "</option>");
                        } else {
                            //列表中的option加入
                            Element option = new Element("option");
                            option.setAttribute("id", item.getItemId()
                                    .toString());
                            option.addContent(item.getItemName().toString());
                            rootElement.addContent(option);
                            //                        str.append("<option id='" + item.getItemId() +
                            // "'>"
                            //                                + item.getItemName() + "</option>");
                        }
                    }
                }
            }
            rootElement.setAttribute("disabled", "true");
        }
        try {
            pageContext.getOut().print(JSONUtil.getStrElement(rootElement));
        } catch (IOException e1) {
            logger.error(e1);
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.commons.system.dict.tags.IDictTagStrategyHelper#doStartTag(com.boco.eoms.commons.system.dict.model.DictTagHelper,
     *      javax.servlet.jsp.PageContext)
     */
    public int doStartTag(DictTagHelper dictTag, PageContext pageContext)
            throws JspException {
        return 0;
    }
}
