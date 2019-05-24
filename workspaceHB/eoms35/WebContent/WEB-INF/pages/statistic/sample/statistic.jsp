<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%@page import="com.boco.eoms.commons.statistic.base.config.excel.*"%>
<script type="text/javascript">

   Ext.onReady(function(){
	v = new eoms.form.Validation({form:'theform'});  

    var	treeAction='${app}/xtree.do?method=subroleFromDept&systemId=15';
    roleTree =new xbox({
	   btnId:'trees',dlgId:'hello-dlg1',
	   treeDataUrl:treeAction,treeRootId:'-1',treeRootText:'<bean:message bundle="sheet" key="sheet.selectRole"/>',treeChkMode:'',treeChkType:'subrole',
	   showChkFldId:'showd',saveChkFldId:'operateRoleId'
    });


   });

</script> 

<%
	Excel excelConfig =(Excel) request.getAttribute("excelConfig");
	if (excelConfig == null) throw new Exception("读取配置统计文件失败!");
%>

<html:form action="/stat.do?method=performStatistic" styleId="theform">
 <table class="formTable" >
   <caption></caption>
   <tr>
     <td noWrap class="label">${eoms:a2u("选择报表")}</td>
     <td width="80%">

            	<select name="reportName">
            	<%
								for(int i=0; i<excelConfig.getSheets().length;i++){
									Sheet sheet = excelConfig.getSheets()[i];
							%>		
                 <option value="<%=sheet.getSheetName() %>"><%=sheet.getSheetName()%></option> 
									
									
							<%}%>
              </select>       		
              </td>
            </tr>


            <tr>
              <td noWrap class="label"><bean:message bundle="statistic" key="statistic.sendtime" />
              </td>
              <td width="80%">
              	<bean:message bundle="statistic" key="statistic.begin" />
	            	<input type="text" name="beginTime" id="beginTime"  readonly="readonly" onclick="popUpCalendar(this, this);" class="text"/>
	            	<bean:message bundle="statistic" key="statistic.end" />
	            	<input type="text" name="endTime" id="endTime" alt="allowBlank:false,vtext:'${eoms:a2u("处理时限不能早于回复时限")}'" readonly="readonly" onclick="popUpCalendar(this, this);" class="text"/>
              </td>
            </tr>

						<tr>
							<td nowrap class="label">
								<bean:message bundle="statistic" key="statistic.sendrole"/>
							</td>
							
							<td nowrap>
								<input type="button" class="btn" name="trees" id="trees" value="<bean:message bundle="statistic" key="button.select"/>">
								<input type="text" name="showd" id="showd"> 
								<input type="hidden" name="operateRoleId" id="operateRoleId" />
							</td>
						</tr>         
          </table>

  <br/>	
  <!-- buttons -->

     <html:submit styleClass="btn" property="method.save" styleId="method.save">
				<bean:message bundle="statistic" key="button.done"/>
     </html:submit>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
