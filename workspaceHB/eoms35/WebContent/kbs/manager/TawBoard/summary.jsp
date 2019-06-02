<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.boco.eoms.common.util.*"%>
<script language="javascript">
<!--
function del_confirm()
{
  return confirm("\u60A8\u786E\u5B9E\u8981\u5220\u9664\u8FD9\u4E2A\u4E13\u9898\u5417\uFF1F");
}
-->
</script>
<%
String infoType=String.valueOf(request.getParameter("infoType"));
%>

<table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
    <td width="100%" class="table_title">
      <bean:message key="label.board"/><bean:message key="label.list"/>
    </td>
  </tr>
</table>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
      <tr class="td_label">
      <td width="30%"><bean:message key="TawBoard.boardName"/></td>
      <td width="20%"><bean:message key="TawBoard.deptId"/></td>
      <td width="20%"><bean:message key="TawBoard.createTime"/></td>
      <td width="10%">资料数</td>
      <td width="10%">修改</td>
      <td width="10%">删除</td>
     </tr>
<%
int layer=0;
int current_parent=0;
int last_parent=0;
int current = 0;
java.util.HashMap layerMap = new java.util.HashMap();
java.util.HashMap map = new java.util.HashMap();
%>
              <logic:iterate id="tawBoard" name="TAWBOARDS" type="com.boco.eoms.kbs.model.TawBoard">
<tr class="tr_show">
<%
if (tawBoard.getDeleted()==1)
    out.println("<td bgcolor=\"#e5edf8\" style=\"color: #FF0000\">");
else
    out.println("<td bgcolor=\"#e5edf8\">");

current_parent=tawBoard.getParentId();
current = tawBoard.getBoardId();
if (current_parent!=last_parent)
{
  layer=StaticMethod.nullObject2int(layerMap.get(String.valueOf(current_parent)))+1;
}
layerMap.put(String.valueOf(current),String.valueOf(layer));

for(int i=0;i<layer;i++)
  out.println("&nbsp;&nbsp;");
%>
<!--                    <a href="view.do?boardId=<bean:write name="tawBoard" property="boardId" scope="page"/>&infoType=<bean:write name="tawBoard" property="infoType" scope="page"/>"/>-->
                    <bean:write name="tawBoard" property="boardName" scope="page"/>
<!--                   </a>-->
    </td>


    <td>
                    <bean:write name="tawBoard" property="deptName" scope="page"/>
    </td>

     <td nowrap>
                    <bean:write name="tawBoard" property="createTime" scope="page"/>
    </td>


     <td>
                    <bean:write name="tawBoard" property="articleNum" scope="page"/>
    </td>

<%
map.put("boardId", String.valueOf(tawBoard.getBoardId()));
map.put("infoType", infoType);
map.put("deleted",String.valueOf(tawBoard.getDeleted()));
pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
%>
    <td>
<%
if (tawBoard.getParentId()!=0)
{%>
        <html:link page="/TawBoard/edit.do" name="map" scope="page" >
        <img title="<bean:message key="label.edit"/>" src="<%=request.getContextPath()%>/images/bottom/an_bj.gif" border="0">
        </html:link>
<%}%>
</td>
<td>
<%
if (tawBoard.getParentId()!=0)
{%>
        <a href="../TawBoard/trash.do?boardId=<%=tawBoard.getBoardId()%>&deleted=<%=tawBoard.getDeleted()%>&infoType=<%=infoType%>" onclick='return del_confirm();' title="<bean:message key="label.remove"/>">
        <img src="<%=request.getContextPath()%>/images/bottom/an_sc.gif" border="0">
        </a>
<%}%>
    </td>
</tr>
<%
last_parent=current_parent;
%>
              </logic:iterate>

</table>
<%
layerMap.clear();
layerMap = null;
map.clear();
map = null;
%>
<table width=100%>
<tr>
	<td align="right" height="32">
	    <input type="button" Class="clsbtn2" value="<bean:message key="label.add"/>" onclick="window.location='add.do?infoType=<%=infoType%>'">
            <input type="button" Class="clsbtn2" value="<bean:message key="label.cancel"/>" onclick="history.back(-1);">
	</td>
</tr>
</table>
<script language="javascript">
  //parent.leftFrame.location="../../template/boardtree.jsp?infoType=<%=infoType%>&reload=true"
</script>
