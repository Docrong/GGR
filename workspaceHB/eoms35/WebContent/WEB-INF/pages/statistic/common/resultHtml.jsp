<%@page language="java" import="com.boco.eoms.commons.statistic.base.config.graphic.* ,
                                com.boco.eoms.commons.statistic.base.util.GraphicsReportUtil" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ include file="/common/extlibs.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
    })

</script>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

    String excelFilePath = (String) request.getAttribute("excelFilePath");
    String excelFileName = (String) request.getAttribute("excelFileName");

    String reportId = (String) request.getAttribute("id");

//out.println("excelFilePath" + excelFilePath);
//out.println("excelFileName" + excelFileName);

%>

<div id="sheet-list">
    <div class="list-title"></div>
    <div class="list">

        <%
            String HtmlString = (String) request.getAttribute("html");

            out.println(HtmlString);

        %>

        <%
            if (reportId != null && !reportId.equalsIgnoreCase("")) {
        %>

        <html:form action="/stat.do?method=updateSatatistFileInfo" styleId="theform">

            审核：
            <select name="checked">
                <option value="Y">通过</option>
                <option value="N">不通过</option>
            </select>

            阅读状态：
            <select name="readedState">
                <option value="Y">已阅读</option>
                <option value="N">未阅读</option>
            </select>

            <input type="hidden" name="id" value="<%=reportId%>">
            <input type="submit" name="submit" value="提交"/>
        </html:form>

        <%
            }
        %>


        <form method="post" action="${app}/statisticfile/download.jsp">

            <input type="hidden" name="excelFilePath" value="<%=excelFilePath %>">
            <input type="hidden" name="excelFileName" value="<%=excelFileName %>">

            <input type="submit" name="fileNamesubmit" value="<bean:message bundle="statistic" key="button.export"/>">
            <!-- onclick="window.open('about:hello','_blank','left=0,top=0')" -->
        </form>

        <!-- 图形报表 按钮 -->
        <%
            String graphicswitch = (String) request.getAttribute("graphicswitch");
            GraphicReport graphicReport = (GraphicReport) request.getAttribute("graphicReport");

            //out.println("graphicReport is " + graphicReport);
            //out.println("Graphicswitch is " + graphicswitch);
            if (graphicReport != null && "true".equalsIgnoreCase(graphicswitch)) {
        %>
        <html:form action="/stat.do?method=showGraphicsStatisticPage" styleId="theform">

        <select name="graphicReportType">
                    <%
				 	String[] selects = graphicReport.getSelectType().split(",");
					for(int i=0;i<selects.length;i++)
					{
						String name = selects[i];
						String value = GraphicsReportUtil.graphicsId2Name(name);
				 %>
            <option value="<%=name %>"><%=value%>
            </option>
                    <%
					}
				 %>

            <!-- cache的id -->
            <input type="hidden" name="statEhCacheID" value="<%=(String)request.getAttribute("statEhCacheID") %>">

            <input type="submit" name="fileNamesubmit" value="显示图形报表"/>

            </html:form>
                <%
	 	}
	  %>

    </div>
</div>

<%@ include file="/common/footer_eoms.jsp" %>