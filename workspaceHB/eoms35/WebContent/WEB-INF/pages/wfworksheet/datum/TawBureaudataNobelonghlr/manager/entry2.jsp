<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.lang.String" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod,com.boco.eoms.datum.vo.impl.TawBureaudataNobelonghlrVO" %>
<%
    /**
     * Modified By Matao 2007-11-09
     * 规范了本页面中标签书写
     * 解决了不能保存的问题
     */
%>
<html:html>
    <head>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
        <title><bean:message key="label.edit"/>地市未确定HLR归属的号段</title>
    </head>
    <body>
    <html:form method="post" action="/TawBureaudataNobelonghlr/save2">
        <%
            TawBureaudataNobelonghlrVO vo = new TawBureaudataNobelonghlrVO();
            vo = (TawBureaudataNobelonghlrVO) request.getAttribute("VO");
        %>
        <div align="center">
            <center>
                <br>
                <table border="0" width="95%" cellspacing="0">
                    <tr class="tr_show">
                        <td width="100%" class="table_title" align="center">
                            <bean:message key="label.edit"/>地市未确定HLR归属的号段
                        </td>
                    </tr>
                </table>
                <logic:present name="VO" scope="request">
                    <html:hidden name="VO" property="rowId"/>
                    <html:hidden name="VO" property="id"/>
                    <logic:present name="savedURL" scope="request">
                        <input type="hidden" name="savedURL" value='<%=(String)request.getAttribute("savedURL")%>'/>
                    </logic:present>
                    <table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
                        <tr class="tr_show">
                            <td class="clsfth">地市编号</td>
                            <td><eoms:CityzoneDict cityId="<%=vo.getCityId()%>"/></td>
                        </tr>
                        <tr class="tr_show">
                            <td width="15%" class="clsfth">起始号段（万号）</td>
                            <td width="200"><html:text property="beginSegment" size="20" name="VO"/></td>
                        </tr>
                        <tr class="tr_show">
                            <td width="15%" class="clsfth">终止号段（万号）</td>
                            <td width="200"><html:text property="endSegment" size="20" name="VO"/></td>
                        </tr>
                        <tr class="tr_show">
                            <td class="clsfth">是否已确定归属</td>
                            <td>
                                <select name="belongFlag">
                                    <option value="<%=vo.getBelongFlag()%>"><%=vo.getBelongFlagName()%>
                                    </option>
                                    <%
                                        String flag = "";
                                        String flagname = "";
                                        if (vo.getBelongFlag().equals("N")) {
                                            flag = "Y";
                                            flagname = "已归属";
                                        } else {
                                            flag = "N";
                                            flagname = "未归属";
                                        }
                                    %>
                                    <option value="<%=flag%>"><%=flagname%>
                                    </option>
                                </select>

                            </td>
                        </tr>
                        <tr class="tr_show">
                            <td width="15%" class="clsfth">局数据编号</td>
                            <td width="200"><html:text property="bureauNo" size="20" name="VO"/></td>
                        </tr>
                        <tr class="tr_show">
                            <td width="15%" class="clsfth">备注</td>
                            <td width="200"><html:text property="note" size="20" name="VO"/></td>
                        </tr>
                    </table>
                </logic:present>
                <table border="0" width="95%" cellspacing="0">
                    <tr>
                        <td width="100%" height="32" align="right">
                            <html:submit styleClass="clsbtn2">
                                <bean:message key="label.save"/>
                            </html:submit>
                            &nbsp;
                            <html:reset styleClass="clsbtn2">
                                <bean:message key="label.reset"/>
                            </html:reset>&nbsp;&nbsp;
                        </td>
                    </tr>
                </table>
            </center>
        </div>
    </html:form>
    <logic:messagesPresent>
        <html:messages id="error">
            <script type="text/javascript">
                <!--
                alert("<bean:write name="error"/>");
                -->
            </script>
        </html:messages>
    </logic:messagesPresent>
    </body>
</html:html>
