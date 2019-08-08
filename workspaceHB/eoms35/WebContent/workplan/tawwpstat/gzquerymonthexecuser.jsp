<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="com.boco.eoms.common.tree.WKTree" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>
<%@ page import="com.boco.eoms.common.log.BocoLog" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.boco.eoms.workplan.model.TawwpMonthExecuteUser" %>

<table class="listTable">
    <caption>
        <bean:message
                key="gzquerymonthexecuser.title.labDateStart"/> <%=StaticMethod.null2String(request.getAttribute("startDategz").toString())%>
        <bean:message
                key="gzquerymonthexecuser.title.labDateTo"/> <%=StaticMethod.null2String(request.getAttribute("endDategz").toString())%>
    </caption>
    <thead>
    <tr>
        <td width="5%"><bean:message key="gzquerymonthexecuser.title.formNO"/></td>
        <td width="10%"><bean:message key="gzquerymonthexecuser.title.formPlanName"/></td>
        <td width="10%"><bean:message key="gzquerymonthexecuser.title.formPlanType"/></td>
        <td width="10%"><bean:message key="gzquerymonthexecuser.title.formStartDate"/></td>
        <td width="10%"><bean:message key="gzquerymonthexecuser.title.formEndDate"/></td>
        <td width="10%"><bean:message key="gzquerymonthexecuser.title.formExecuteUser"/></td>
        <td width="8%"><bean:message key="gzquerymonthexecuser.title.formAllCounts"/></td>
        <td width="10%"><bean:message key="gzquerymonthexecuser.title.formInTimeCount"/></td>
        <td width="10%"><bean:message key="gzquerymonthexecuser.title.formOutTimeCount"/></td>
        <td width="8%"><bean:message key="gzquerymonthexecuser.title.formUnexecutedCounts"/></td>
        <td width="9%"><bean:message key="gzquerymonthexecuser.title.formNO"/></td>
    </tr>
    </thead>
    <tbody>

    <%
        //要用到的变量 (局部变量在具体的方法里面)
        String monthPlanId = "", startDategz = "", endDategz = "";
//           int j=0;
        //从提交页面获取参数
        monthPlanId = StaticMethod.null2String(request.getAttribute("monthPlanId").toString());
        startDategz = StaticMethod.null2String(request.getAttribute("startDategz").toString());
        endDategz = StaticMethod.null2String(request.getAttribute("endDategz").toString());
//           System.out.println("startDategz1"+startDategz);
//           System.out.println("monthPlanId is "+monthPlanId);


//           System.out.println("monthexecuteuser size is "+monthexecuteuser.size());
    %>
    <% if (request.getAttribute("monthExecuteuserId") != null && request.getAttribute("monthExecuteUser") != null) {
        System.out.println("作业计划月度按人统计详细显示报表 add by denden（贵州本地）");
        List monthExecuteUser = (List) request.getAttribute("monthExecuteUser");
        List monthExecuteuserId = (List) request.getAttribute("monthExecuteuserId");
        for (int i = 0; i < monthExecuteUser.size(); i++) {
            List monthexecuteuserdetail = (List) monthExecuteuserId.get(i);

            int j = 0, k = 0, l = 0;
            j = Integer.parseInt(monthexecuteuserdetail.get(4).toString());
            k = Integer.parseInt(monthexecuteuserdetail.get(5).toString());
            l = Integer.parseInt(monthexecuteuserdetail.get(6).toString());

    %>
    <tr>
        <td width="5%"><%=i + 1%>
        </td>
        <td width="10%"><%= monthexecuteuserdetail.get(3)%>
        </td>
        <td width="10%"><%=monthexecuteuserdetail.get(1) %>
        </td>
        <td width="10%"><%=startDategz.substring(0, 10)%>
        </td>
        <td width="10%"><%=endDategz.substring(0, 10)%>
        </td>
        <td width="10%"><%=monthexecuteuserdetail.get(0) %>
        </td>
        <td width="8%"><%=j%>
        </td>
        <td width="10%"><%=l%>
        </td>
        <td width="10%"><%=k - l%>
        </td>
        <td width="8%"><%=j - k%>
        </td>
        <td width="9%">
            <a href="../tawwpexecute/executeviewstat.do?monthplanid=<%=monthexecuteuserdetail.get(2).toString()%>&userid=<%=monthexecuteuserdetail.get(0).toString()%>&startDategz=<%=startDategz%>&endDategz=<%=endDategz%>">
                <bean:message key="gzquerymonthexecuser.title.formView"/>
            </a>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
<%@ include file="/common/footer_eoms.jsp" %>
