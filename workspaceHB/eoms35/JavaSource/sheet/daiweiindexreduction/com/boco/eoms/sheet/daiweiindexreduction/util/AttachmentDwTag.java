package com.boco.eoms.sheet.daiweiindexreduction.util;

import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts.taglib.TagUtils;

import com.boco.eoms.commons.loging.BocoLog;

import javax.servlet.jsp.*;

/**
 * <p>Title:附件管理标签类
 * </p>
 * <p>Description:
 * </p>
 * <p>Apr 22, 2007 7:07:22 PM</p>
 *
 * @author 秦敏  修改 自定义标签上传解析 by wmm 20170815
 * @version 1.0
 */
public class AttachmentDwTag extends BodyTagSupport {

    /**
     * 应用模块编号
     */
    private String appCode = "";
    /**
     * 文件ID
     */
    private String idList = "";
    /**
     * 查看标志
     */
    private String viewFlag = "";

    /**
     * formBean名字
     */
    private String name;
    /**
     * 附件在formBean中的属性名
     */
    private String property;
    /**
     * 查询的范围
     */
    private String scope;

    private String path;

    private String attachmentIds;
    private String idField;

    private String alt;

    private String startsWith;

    private String sheetFlag;

    public int doStartTag() {
        javax.servlet.ServletRequest request = pageContext.getRequest();
        path = ((javax.servlet.http.HttpServletRequest) request).getContextPath();
        //获取附件ID
        if (request.getParameter(this.idList) != null) {
            this.attachmentIds = request.getParameter(this.idList);
        } else {
            this.attachmentIds = "";
        }
        try {
            //根据给定的formbean、属性名得到附件ID值
            if (name != null && property != null && scope != null) {
                this.attachmentIds = (String) TagUtils.getInstance().lookup(this.pageContext,
                        name, property, scope);
            }
        } catch (Exception e) {
            BocoLog.error(this, e.toString());
        }
        return EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspTagException { // 修改 path

        try {
            String html = "";
            if (!viewFlag.equals("Y")) {
                html = "<iframe id=\"UIFrame1-" + idField +
                        "\" name=\"UIFrame1-" + idField + "\" class=\"uploadframe\" frameborder=\"0\" "
                        + "scrolling=\"auto\" src=\"" + path + "/accessories/daiweiindexreductionfiles/uploadDw.jsp?appId=" + appCode + "&sheetFlag=" + sheetFlag
                        + "&filelist=" + attachmentIds + "&idField=" + idField + "\" style=\"height:80%;width:100%\"></iframe><input type=\"hidden\" "
                        + "name=\"" + idField + "\" id=\"" + idField + "\" value=\"" + attachmentIds + "\"";
                if (alt != null && !alt.equals("")) {
                    html = html + " alt=\"blankText:'请上传附件。',iframeId:'IFrame1-" + idField + "'," + alt + "\"/>";
                } else {
                    html = html + " />";
                }

            } else {
//        html = "<iframe id=\"VIFrame1-" +idField+
//        		"\" name=\"VIFrame1-"+idField+"\" class=\"uploadframe\" frameborder=\"0\" "
//            + "scrolling=\"auto\" src=\"" + path + "/accessories/pages/view.jsp?appId=" + appCode
//            + "&filelist=" + attachmentIds + "&idField="+idField+"\" style=\"height:100%;width:100%\"></iframe><input type=\"hidden\" "
//            + "name=\"" + idField +"\" id=\""+idField+ "\" value=\""+attachmentIds+"\"/>";

                html = "<iframe id=\"VIFrame1-" + idField +
                        "\" name=\"VIFrame1-" + idField + "\" class=\"uploadframe\" frameborder=\"0\" "
                        + "scrolling=\"auto\" src=\"" + path + "/accessories/pages/view.jsp?appId=" + appCode
                        + "&filelist=" + attachmentIds + "&idField=" + idField + "\" style=\"height:80%;width:100%\"></iframe>";
            }
            System.out.println("---html---" + html);
            pageContext.getOut().write(html);
        } catch (Exception e) {
        }
        return EVAL_PAGE;
    }

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }

    public String getViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(String viewFlag) {
        this.viewFlag = viewFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    /**
     * @return Returns the alt.
     */
    public String getAlt() {
        return alt;
    }

    /**
     * @param alt The alt to set.
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }

    public String getSheetFlag() {
        return sheetFlag;
    }

    public void setSheetFlag(String sheetFlag) {
        this.sheetFlag = sheetFlag;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}