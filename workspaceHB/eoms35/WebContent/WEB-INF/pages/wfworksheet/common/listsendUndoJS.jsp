<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function openQuery(handler){
	var el = Ext.get('listQueryObject');
	if(el.isVisible()){
		el.slideOut('t',{useDisplay:true});
		handler.innerHTML = "打开快速查询";
	}
	else{
		el.slideIn();
		handler.innerHTML = "关闭快速查询";
	}
}
</script>

<div style="border:1px solid #98c0f4;padding:5px;width:98%;" class="x-layout-panel-hd">
工具栏： 
  <img src="${app}/images/icons/search.gif" align="absmiddle" style="cursor:pointer"/>
  <span id="openQuery"  style="cursor:pointer" onclick="openQuery(this);">打开快速查询</span>
  <c:if test="${'performListQuery' eq param.method}">
    | <a href="./${module}.do?method=showListsendundo">返回列表</a>
  </c:if>
</div>

<div id="listQueryObject" style="display:none;border:1px solid #98c0f4;border-top:0;padding:5px;background-color:#eff6ff;width:98%">
<jsp:include page="/WEB-INF/pages/wfworksheet/${module}/listUndoQuery.jsp"/>
</div>
