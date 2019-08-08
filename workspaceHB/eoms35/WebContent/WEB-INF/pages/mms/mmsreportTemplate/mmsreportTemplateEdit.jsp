<%@ page language="java"
         import="java.util.*,com.boco.eoms.commons.mms.mmsreporttemplate.model.MmsreportTemplate,java.util.List,com.boco.eoms.commons.mms.base.config.*,com.boco.eoms.base.util.StaticMethod"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    List sheetList = (List) request.getAttribute("sheetList");
    MmsreportTemplate mmsreportTemplate = (MmsreportTemplate) request.getAttribute("mmsreportTemplate");
    String statReportIds = mmsreportTemplate.getStatReportId();
%>
<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'mmsreportTemplateForm'});
    });

</script>

<html:form action="/mmsreportTemplates.do?method=save" styleId="mmsreportTemplateForm" method="post">

    <fmt:bundle basename="config/applicationResources-mms">

        <table class="formTable">
            <caption>
                <div class="header center">定制彩信报</div>
            </caption>

            <tr class="tr_show">


                </select>
                <td class="label" width="2%" nowrap="nowrap" align="center">
                    <c:if test="${mmsreportTemplateForm.executeCycle=='dailyReport'}">
                        <input type="radio" name="executeCycle" value="dailyReport" checked>
                    </c:if>
                    <c:if test="${mmsreportTemplateForm.executeCycle != 'dailyReport'}">
                        <input type="radio" name="executeCycle" value="dailyReport">
                    </c:if>
                </td>
                <td noWrap width="13%">
                    日报统计
                </td>
                <td noWrap width="85%">
                    统计时间段：从昨天0点到昨天23点59分，报表呈现的时间是今日
                </td>

            </tr>

            <tr class="tr_show">
                <td class="label" width="2%" nowrap="nowrap" align="center">
                    <c:if test="${mmsreportTemplateForm.executeCycle=='weekReport'}">
                        <input type="radio" name="executeCycle" value="weekReport" checked>
                    </c:if>
                    <c:if test="${mmsreportTemplateForm.executeCycle!='weekReport'}">
                        <input type="radio" name="executeCycle" value="weekReport">
                    </c:if>
                </td>
                <td noWrap width="13%">
                    周报统计
                </td>
                <td width="85%">
                    统计选择起始时间:
                    <select name="reportCreatDate" style="width: 2.0cm;">
                        <% for (int i = 1; i < 8; i++) {
                            String select = "";
                            if (mmsreportTemplate.getExecuteCycle().equals("weekReport") && mmsreportTemplate.getReportCreatDate().equals("" + i)) {
                                select = "Selected";
                            }
                        %>
                        <option value="<%=i%>" <%=select %>>周<%= i%>
                        </option>
                        <%}%>
                    </select>
                    以此为周期
                </td>
            </tr>


            <tr class="tr_show">
                <td class="label" width="2%" nowrap="nowrap" align="center">
                    <c:if test="${mmsreportTemplateForm.executeCycle=='monthReport'}">
                        <input type="radio" name="executeCycle" value="monthReport" checked>
                    </c:if>
                    <c:if test="${mmsreportTemplateForm.executeCycle!='monthReport'}">
                        <input type="radio" name="executeCycle" value="monthReport">
                    </c:if>
                </td>
                <td width="13%">
                    月报统计
                </td>
                <td width="85%">
                    统计选择起始时间:
                    <select name="reportMonthCreatDate" style="width: 2.0cm;">
                        <% for (int i = 1; i < 31; i++) {
                            String select = "";
                            if (mmsreportTemplate.getExecuteCycle().equals("monthReport") && mmsreportTemplate.getReportCreatDate().equals("" + i)) {
                                select = "Selected";
                            }
                        %>
                        <option value="<%=i%>" <%=select %>><%= i%>号</option>
                        <%}%>
                    </select>
                    以此为周期
                </td>
            </tr>

            <tr>
                <td class="label">
                </td>

                <td>
                    彩信报表名称：
                </td>
                <td class="content">
                    <html:text property="mmsName" styleId="mmsName"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${mmsreportTemplateForm.mmsName}"/>
                </td>
            </tr>

            <!--
	<tr>
		<td class="label">
		</td>
		<td>
			报表显示类型：
		</td>
		<td class="content">
			<select name="reportDisplayType">
			<c:if test="${mmsreportTemplateForm.reportDisplayType=='stat'}">
              <option value="stat" checked>表格</option>
			</c:if>
			<c:if test="${mmsreportTemplateForm.reportDisplayType!='stat'}">
              <option value="stat">表格</option>
			</c:if>
			<c:if test="${mmsreportTemplateForm.reportDisplayType=='column'}">
              <option value="column" checked>柱图</option>
			</c:if>
			<c:if test="${mmsreportTemplateForm.reportDisplayType!='column'}">
              <option value="column" >柱图</option>
			</c:if>
			<c:if test="${mmsreportTemplateForm.reportDisplayType=='line'}">
              <option value="line" checked>线图</option>
			</c:if>
			<c:if test="${mmsreportTemplateForm.reportDisplayType!='line'}">
              <option value="line" >线图</option>
			</c:if>
			<c:if test="${mmsreportTemplateForm.reportDisplayType=='pie'}">
              <option value="pie" checked>饼图</option>
			</c:if>
			<c:if test="${mmsreportTemplateForm.reportDisplayType!='pie'}">
              <option value="pie" >饼图</option>
			</c:if>			
			<c:if test="${mmsreportTemplateForm.reportDisplayType=='columnline'}">
              <option value="columnline" checked>线柱结合图</option>
			</c:if>
			<c:if test="${mmsreportTemplateForm.reportDisplayType!='columnline'}">
              <option value="columnline" >线柱结合图</option>
			</c:if>
			</select>
		</td>
	</tr>
	<tr>
		<td class="label">
		</td>
	
		<td>
			报表图片格式：
		</td>
		<td class="content">
			<select name="pictureFormat">
			<c:if test="${mmsreportTemplateForm.pictureFormat=='gif'}">
              <option value="gif" checked>gif</option>
			</c:if>
			<c:if test="${mmsreportTemplateForm.pictureFormat!='gif'}">
              <option value="gif" >gif</option>
			</c:if>
			<c:if test="${mmsreportTemplateForm.pictureFormat=='jpg'}">
              <option value="jpg" checked>jpg</option>
			</c:if>
			<c:if test="${mmsreportTemplateForm.pictureFormat!='jpg'}">
              <option value="jpg" >jpg</option>
			</c:if>
			<c:if test="${mmsreportTemplateForm.pictureFormat=='png'}">
              <option value="png" checked>png</option>
			</c:if>
			<c:if test="${mmsreportTemplateForm.pictureFormat!='png'}">
              <option value="png" >png</option>
			</c:if>
			</select>
		</td>
	</tr>
	 -->

            <tr>
                <td class="label">
                </td>
                <td>
                    选择报表：
                </td>
                <td>
                    <%
                        Sheet sheet = null;
                        for (int i = 0; i < sheetList.size(); i++) {
                            sheet = (Sheet) sheetList.get(i);
                            String id = sheet.getId();
                            String checked = "";
                            if (statReportIds.contains(id)) {
                                checked = "checked";
                            }
                            String name = sheet.getName();
                    %>
                    <input name="statReportIds" id="statReportIds" type="checkbox" value=<%=id%> <%=checked %>/>
                    <%=name %>
                    <%}%>
                </td>
            </tr>

            <tr>
                <td class="label">
                </td>

                <td>
                    彩信报说明：
                </td>
                <td class="content">
                    <html:textarea property="mmsReportDesc" styleId="mmsReportDesc" rows="6" cols="100"
                                   styleClass="text medium"
                                   alt="allowBlank:true,vtext:''" value="${mmsreportTemplateForm.mmsReportDesc}"/>
                </td>
            </tr>

        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>

            </td>
        </tr>
    </table>
    <html:hidden property="id" value="${mmsreportTemplateForm.id}"/>

</html:form>

<%@ include file="/common/footer_eoms.jsp" %>