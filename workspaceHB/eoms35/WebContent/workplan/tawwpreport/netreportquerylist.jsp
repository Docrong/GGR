<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpNetVO"%>

<%
TawwpNetVO tawwpNetVO = null;
List list = (List)request.getAttribute("netlist");
%>

<table class="listTable">
<caption>网元上报列表</caption>
<% if(list!=null&&list.size()!=0){ %>
<thead>
  <tr>
    <td width="80">
      网元名称
    </td>
    <td width="120">
      网元序列号
    </td>
    <td width="60">
      小专业类型
    </td>
    <td width="60">
      网元类型
    </td>
    <td width="100">
      所属机房
    </td>
    <td width="180">
      所属部门
    </td>
  </tr>
</thead>
<tbody>
  <%
    for(int i=0; i<list.size(); i++){
      tawwpNetVO = (TawwpNetVO)list.get(i);
  %>
  <tr>
    <td>
      <%=tawwpNetVO.getName()%>
    </td>
    <td>
      <%=tawwpNetVO.getSerialNo()%>
    </td>
    <td>
      <%=tawwpNetVO.getNetTypeName()%>
    </td>
    <td>
      <%=tawwpNetVO.getSysTypeName()%>
    </td>
    <td>
      <%=tawwpNetVO.getRoomName()%>
    </td>
    <td>
      <%=tawwpNetVO.getDeptName()%>
    </td>
  </tr>
  <%
    }
  %>
  <tr>
    <td colspan="6">
    <html:link page="/tawwpnet/netreport.do" name="map" >
     提交
    </html:link>
    /
    <html:link page="/tawwpnet/newnetreport.do" name="map" >
     提交新网元
    </html:link>        
    </td>
  </tr>
</tbody>
<% }
  else{
  %>
<tbody>
  <tr>
    <td colspan="6">
      没有任何网元信息
    </td>
  </tr>
</tbody>
  <%
  }
%>
</table>


