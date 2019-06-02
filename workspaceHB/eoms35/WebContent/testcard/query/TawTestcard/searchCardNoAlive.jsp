<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<%
	String id = StaticMethod.nullObject2String(request.getAttribute("formId"));
	String leaveid = StaticMethod.nullObject2String(request.getAttribute("leaveid"));
%>    
<script language="JavaScript">
function check(){
	var leaveid = document.forms[0].leaveid.value;
	if (leaveid == '') {
		alert("请选择存放公司！");
		return false;
	}
	return true;
}
</script>
    <html:form  method="post" action="/TawTestcardApply/searchcarddo"  styleId="tawTestcardApplyForm">
      <table  class="formTable">
       <tr>
              <td noWrap width="100"  class= "label">
                   电话号码
              </td>
              <td width="380">
                     <html:text styleClass="clstext" property="phoneNumber" size="20" value=""/>
              </td>
    </tr>
	<tr>
			<td class="label">
					存放公司
			</td>
			<td colspan="3">
				<eoms:comboBox name="leaveid" id="a1" sub="a2" initDicId="10401"/>
			</td>
	</tr>
	<html:hidden property="id" value="<%=id%>"/>
    <tr>
         <td colspan="2" >
           <html:submit styleClass="button" onclick="return check();">
		   查询
		   </html:submit>
      		<html:reset styleClass="button">
       		重置
      		</html:reset>
      </td>
	</tr>
			</table>
</html:form>
 <script type="text/javascript">
window.onload = function(){
	document.forms[0].leaveid.value = <%=leaveid%>;
}
</script>
      
<%@ include file="/common/footer_eoms.jsp"%>
