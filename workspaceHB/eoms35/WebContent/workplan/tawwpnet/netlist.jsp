<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpNetVO"%>

<script language="javascript">

Ext.onReady(function(){
	colorRows('list-table');
})

  function onNetAdd()
  {
    location.href="netadd.do";
  }

  function onRemove(netid){
    if (confirm("<bean:message key="netlist.title.ifDelete" />")==true){
      location.href="netremove.do?netid=" + netid;
    }
  }

</script>

<input type="button" value="<bean:message key="netlist.title.add" />" onclick="javascript:onNetAdd();"  Class="button">

<%
TawwpNetVO tawwpNetVO = null;
List list = (List)request.getAttribute("netlist");
%>

<table width="100%" class="listTable" id="list-table">
  <caption><bean:message key="netlist.title.formTitle" /></caption>
  <thead>
  <tr>
    <td width="700" colspan="8">
      <%=(String)request.getAttribute("pagerHeader")%>
    </td>
  </tr>
  <tr>
    <td width="120">
      <bean:message key="netlist.title.formName" />
    </td>
    <td width="120">
      <bean:message key="netlist.title.formNetSerialNo" />
    </td>
    <td width="140">
      <bean:message key="netlist.title.formNetTypeName" />
    </td>
    <td width="200">
      <bean:message key="netlist.title.formRoomName" />
    </td>
    <td width="120">
      <bean:message key="netlist.title.formDeptName" />
    </td>
    <td width="100">
      <bean:message key="netlist.title.formVendor" />
    </td>
    <td width="50">
      <bean:message key="netlist.title.formEdit" />
    </td>
    <td width="50">
      <bean:message key="netlist.title.formRemove" />
    </td>
  </tr>
  </thead>
  <tbody>
  <%
  if(list.size() > 0){
    for(int i=0; i<list.size(); i++){
      tawwpNetVO = (TawwpNetVO)list.get(i);
  %>
  <tr>
    <td width="120">
      <%=tawwpNetVO.getName()%>
    </td>
    <td width="120">
      <%=tawwpNetVO.getSerialNo()%>
    </td>
    <td width="140">
      <%=tawwpNetVO.getNetTypeName()%>
    </td>
    <td width="200">
      <%=tawwpNetVO.getRoomName()%>
    </td>
    <td width="120">
      <%=tawwpNetVO.getDeptName()%>
    </td>
    <td width="60">
      <%=tawwpNetVO.getVendor()%>
    </td>
    <td width="50">
      <a href="netedit.do?netid=<%=tawwpNetVO.getId()%>">
        <img src="${app }/images/icons/edit.gif" width="19" height="19">
      </a>
    </td>
    <td width="50">
      <a href="javascript:onRemove('<%=tawwpNetVO.getId()%>')">
        <img src="${app }/images/icons/icon.gif" width="23" height="30">
      </a>
    </td>
  </tr>
  <%
    }
  }
  else{
  %>
  <tr>
    <td width="700" colspan="8">
      <bean:message key="netlist.title.nolist" />
    </td>
  </tr>
  <%
  }
  %>
  </tbody>
</table>

<%@ include file="/common/footer_eoms.jsp"%>
