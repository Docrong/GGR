<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><bean:message key="cacheList.title"/></title>
<script language="JavaScript">
<!--
	function flush(cacheName,cacheKey){
		window.location="<html:rewrite page='/applicationCacheMgrAction.do?method=flushObject&cacheName='/>"+cacheName+"&cacheKey="+cacheKey;
	}
//-->
</script>

<table border="1">
<tr>
	<td>key</td>
	<td>version</td>
	<td>hitCount</td>
	<td>CreationTime</td>
	<td>LastAccessTime</td>
	<td>SerializedSize</td>
	<td>operation</td>
</tr>
<c:if test="${not empty cacheMap}">
<c:forEach var="item" items="${cacheMap}" >
  	<tr>
        <td><c:out value="${item.value.key}"/></td>
        <td><c:out value="${item.value.version}"/></td>
        <td><c:out value="${item.value.hitCount}"/></td>
        <td><c:out value="${item.value.creationTime}"/></td>
        <td>${item.value.lastAccessTime}
    	</td>
        <td><c:out value="${item.value.serializedSize}"/></td>        
        <td><input type="button" value="<bean:message key='cacheList.button.flush'/>"  onclick="flush('${cacheName}','${item.value.key}');" /></td>
    </tr>
</c:forEach>
</c:if>

</table>

<html:link page="/applicationCacheMgrAction.do?method=listCache">return list</html:link><br>
<html:link page="/applicationCacheMgrAction.do?method=cleanStatAll">clear all</html:link><br>
<html:link page="/applicationCacheMgrAction.do?method=flushAll">flush all</html:link><br>
<html:link page="/applicationCacheMgrAction.do?method=addCache">add cache</html:link>
<%@ include file="/common/footer_eoms.jsp"%>
