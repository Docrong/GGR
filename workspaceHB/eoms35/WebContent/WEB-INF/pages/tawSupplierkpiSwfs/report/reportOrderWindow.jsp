<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%String graphType = request.getAttribute("graphType").toString();
  String jsp = "";
  String graphName = "";
  if ("3D-Column.xml".equals(graphType)) {
    jsp = "3D-Column.jsp";
    graphName = "柱状图";
  }
  else if ("3D-Pie.xml".equals(graphType)) {
    jsp = "3D-Pie.jsp";
    graphName = "饼状图";
  }
%>

<iframe width=580 height=350 frameborder=1 align="center" src="<%=request.getContextPath()%>/reportSwfs/report/<%=jsp%>">
</iframe>
<form>
<div align="center">
<input type="button" name="refresh" value="${eoms:a2u('刷新')}" class="btn" onclick="window.location.reload()" />
<input type="button" name="close" value="${eoms:a2u('关闭')}" class="btn" onclick="window.close()" />
</div>
</form>
<%@ include file="/common/footer_eoms.jsp"%>