package com.boco.eoms.commons.system.dict.webapp.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.RequestUtils;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.model.IDictItem;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.Util;

/**
 * <p>
 * Title:字典action
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-30 9:42:21
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictAction extends BaseAction {
    /**
     * xml的下拉框字典关联
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward forXML(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //字典关系id
        String relationId = RequestUtils.getStringParameter(request,
                "relationId");
        //字典key值
        String key = RequestUtils.getStringParameter(request, "key");

        //下拉框选择的值
        String value = RequestUtils.getStringParameter(request, "value");
        if (value == null || "".equals(value.trim())) {
            return null;
        }

        IDictService dictService = (IDictService) this.getBean("DictService");
        //取关联结果
        List list = dictService.getRelatedList(value, Util.constituteDictId(
                key, relationId));
        JSONObject j = new JSONObject();
        JSONArray json = new JSONArray();
        if (list != null && !list.isEmpty()) {
            for (Iterator it = list.iterator(); it.hasNext();) {
                IDictItem item = (IDictItem) it.next();
                JSONObject jitem = new JSONObject();
                jitem.put("text", item.getItemName());
                jitem.put("value", item.getItemId());
                json.put(jitem);
            }
        }
        j.put("rows", json);
        j.put("results", list.size());
        //转成json
        response.setContentType("text/xml;charset=UTF-8");
        //将对象写入selectId对映的下拉框
        response.getWriter().print(j.toString());
        return null;
    }
}
