<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>

<%
  request.setAttribute("userId", request.getParameter("userId")); 
%>

<script type="text/javascript">
    var _tabs;
    Ext.onReady(function(){
        var tabConfig = {
            items:[
                {id:'sheet-info',  text:'基本资料', url:'${app}/kmmanager/kmExpertBasics.do?method=detail&userId=${userId}', isIframe : true},
                {id:'sheet-edu',   text:'教育情况', url:'${app}/kmmanager/kmExpertEdus.do?method=listDetail&userId=${userId}', isIframe : true},
                {id:'sheet-exp',   text:'工作情况', url:'${app}/kmmanager/kmExpertExps.do?method=listDetail&userId=${userId}', isIframe : true},
                {id:'sheet-train', text:'培训管理', url:'${app}/kmmanager/kmExpertTrains.do?method=listDetail&userId=${userId}', isIframe : true},
                {id:'sheet-cet',   text:'证书管理', url:'${app}/kmmanager/kmExpertCets.do?method=listDetail&userId=${userId}', isIframe : true},
                {id:'sheet-com',   text:'技术交流竞赛表彰', url:'${app}/kmmanager/kmExpertComs.do?method=listDetail&userId=${userId}', isIframe : true},
                {id:'sheet-photo', text:'头像照片', url:'${app}/kmmanager/kmExpertPhoto.do?method=detailPhoto&userId=${userId}', isIframe : true}
            ]
        };
        _tabs = new eoms.TabPanel('sheet-detail-page', tabConfig);
    });
</script>

<div id="sheet-detail-page"></div>

<!-- 修改或删除知识 -->
<c:if test="${createUser == sessionScope.sessionform.userid}">
<table>
    <tr>
        <td>
            <input type="button" class="btn" value="修改" onclick="javascript:onSubmitEdit();"/>&nbsp;
            <input type="button" class="btn" value="删除" onclick="javascript:onSubmitDele();"/>&nbsp;
        </td>
    </tr>
</table>
</c:if>

<script type="text/javascript">	
	//修改
	function onSubmitEdit(){
	    var url='${app}/kmmanager/kmExpertBasics.do?method=newExpert&userId=${userId}';
	    window.location.href(url);
    }
    //删除
    function onSubmitDele(){
	    var url='${app}/kmmanager/kmExpertBasics.do?method=remove&userId=${userId}';
	    window.location.href(url);  
    }
</script>

<%@ include file="/common/footer_eoms.jsp"%>