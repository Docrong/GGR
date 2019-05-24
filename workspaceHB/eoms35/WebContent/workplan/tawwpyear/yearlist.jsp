<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Hashtable"%>
<%@ page import ="java.util.Enumeration"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpYearPlanVO"%>
<%@ page import ="com.boco.eoms.workplan.util.TawwpStaticVariable"%>

<%@ page import ="com.boco.eoms.workplan.util.TawwpUtil"%>
<script language="javascript">
Ext.onReady(function(){
	colorRows('list-table');
})

  function onAdd()
  {
    location.href="yearadd.do";
  }

  function onAddNoModel()
  {
    location.href="yearaddnomodel.do";
  }
  function onRemove(yearplanid){
      if (confirm("<bean:message key="yearlist.title.ifDelete" />")==true){
        document.yearplanform.yearplanid.value=yearplanid;
        document.yearplanform.action="yearremove.do";
        document.yearplanform.submit();
      }
  }
  function changeYear(){
      document.yearplanform.action="yearlist.do";
      document.yearplanform.submit();
  }
</script>

<%
String yearFlag="";
if(request.getAttribute("yearFlag")!=null){
	yearFlag=(String)request.getAttribute("yearFlag");
}else{
	yearFlag=TawwpUtil.getCurrentDateTime("yyyy");
}
List yearPlanList = (List)request.getAttribute("yearplanlist"); //获取年度作业计划集合结构
TawwpYearPlanVO tawwpYearPlanVO = null;
%>
<form name="yearplanform">
  <input type="hidden" name="yearplanid">

          <table width="100%" class="listTable" id="list">
            <caption><bean:message key="yearlist.title.formTitle" />
            <eoms:dict key="dict-workplan" dictId="yearFlag" isQuery="false" defaultId="<%=yearFlag%>"  onchange="changeYear()" selectId="yearFlag" beanId="selectXML" />
      
            
            </caption>
            <thead>
            <tr>
              <td><bean:message key="yearlist.title.formPlanName" /></td>
              <td><bean:message key="yearlist.title.formSystem" /></td>
              <td><bean:message key="yearlist.title.formNetType" /></td>
              <td><bean:message key="yearlist.title.formCreateTime" /></td>
              <td><bean:message key="yearlist.title.formCreater" /></td>
              <td><bean:message key="yearlist.title.formState" /></td>
              <td><bean:message key="yearlist.title.formRemove" /></td>
              <td><bean:message key="yearlist.title.formDetail" /></td>
            </tr>
            </thead>
            <tbody>
            <%
            if(yearPlanList.size() > 0){
              for(int i=0; i<yearPlanList.size(); i++){
                tawwpYearPlanVO=(TawwpYearPlanVO)yearPlanList.get(i);
            %>
              <tr>
                <td height="20"><%=tawwpYearPlanVO.getName()%></td>
                <td><%=tawwpYearPlanVO.getSysTypeName()%></td>
                <td><%=tawwpYearPlanVO.getNetTypeName()%></td>
                <td><%=tawwpYearPlanVO.getCrtime()%></td>
                <td><%=tawwpYearPlanVO.getCruserName()%></td>
                <td><%=tawwpYearPlanVO.getStateName()%></td>
                <td> 
                  <% 
                    if("0".equals(tawwpYearPlanVO.getState())
                       ||"2".equals(tawwpYearPlanVO.getState())){
                  %> 
                  
                  <a href="javascript:onRemove('<%=tawwpYearPlanVO.getId()%>')">
                    <img src="${app }/images/icons/icon.gif" width="22" height="30">
                  </a>
                  <%
                    }
                  %>
                </td>
                <td>
                  <a href="itemlist.do?yearplanid=<%=tawwpYearPlanVO.getId()%>">
                    <img src="${app }/images/icons/icon1.gif" width="18" height="18">
                  </a>
                </td>
              </tr>
            <%
              }
            }
            else{
            %>
            <tr>
              <td height="25" colspan="8">
                <bean:message key="yearlist.title.nolist" />
              </td>
            </tr>
            <%
            }
            %>
          </tbody>
          </table>
          <br>
          <input type="button" Class="button" value="<bean:message key="yearlist.title.addFromModel" />" onclick="onAdd();">
          <input type="button" Class="button" value="<bean:message key="yearlist.title.addFromDept" />" onclick="onAddNoModel();">
          </form>
<%@ include file="/common/footer_eoms.jsp"%>
