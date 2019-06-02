<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<title><bean:message key="cacheList.title"/></title>
<script language="JavaScript">
<!--
	function detail(cacheName){
		window.location="<html:rewrite page='/applicationCacheMgrAction.do?method=detailCache&cacheName='/>"+cacheName;
	}
	function flush(cacheName){
		window.location="<html:rewrite page='/applicationCacheMgrAction.do?method=flushCache&cacheName='/>"+cacheName;
	}
	function clearStat(cacheName){
		window.location="<html:rewrite page='/applicationCacheMgrAction.do?method=cleanStat&cacheName='/>"+cacheName;
	}
//-->
</script>
<table border="1">
<c:if test="${not empty list}">
<tr>
<td><bean:message key="cacheList.label.cacheName"/></td>
<td><bean:message key="cacheList.label.cacheInMemorySize"/></td>
<td><bean:message key="cacheList.label.size"/></td>
<td>cacheHits</td>
<td>onDiskHits</td>
<td>inMemoryHits</td>
<td>cacheMisses</td>
<td>statisticsSize</td>
<td>objectCount</td>
<td>maxElementsInMemory</td>
<td>diskExpiryThreadIntervalSeconds</td>
<td>diskStoreSize</td>
<td>diskStoreHitCount</td>
<td>memoryStoreHitCount</td>
<td>overflowToDisk</td>
<td>timeToIdleSeconds</td>
<td>timeToLiveSeconds</td>
<td>eternal</td>
<td colspan="3"><bean:message key="cacheList.label.operation"/></td>
</tr>
<c:forEach var="item" items="${list}" >
  	<tr>
        <td><c:out value="${item.cacheName}"/></td>
        <td><c:out value="${item.cacheInMemorySize}"/></td>
        <td><c:out value="${item.size}"/></td>
        <td><c:out value="${item.cacheHits}"/></td>
        <td><c:out value="${item.onDiskHits}"/></td>
        <td><c:out value="${item.inMemoryHits}"/></td>
        <td><c:out value="${item.cacheMisses}"/></td>
        <td><c:out value="${item.statisticsSize}"/></td>
        <td><c:out value="${item.objectCount}"/></td>
        <td><c:out value="${item.maxElementsInMemory}"/></td>
        <td><c:out value="${item.diskExpiryThreadIntervalSeconds}"/></td>
        <td><c:out value="${item.diskStoreSize}"/></td>
        <td><c:out value="${item.diskStoreHitCount}"/></td>
        <td><c:out value="${item.memoryStoreHitCount}"/></td>
        <td><c:out value="${item.overflowToDisk}"/></td>
        <td><c:out value="${item.timeToIdleSeconds}"/></td>
        <td><c:out value="${item.timeToLiveSeconds}"/></td>
        <td><c:out value="${item.eternal}"/></td>
        <td><input type="button" value="<bean:message key='cacheList.button.flush' />" onclick="flush('${item.cacheName}');"/></td>
        <td><input type="button" value="<bean:message key='cacheList.button.detail' />" onclick="detail('${item.cacheName}');" /></td>
        <td><input type="button" value="<bean:message key='cacheList.button.cleanStat'/>"  onclick="clearStat('${item.cacheName}');" /></td>
    </tr>
</c:forEach>
</c:if>

</table>
<html:link page="/applicationCacheMgrAction.do?method=flushAll">flush all</html:link><br>
<html:link page="/applicationCacheMgrAction.do?method=cleanStatAll">clear stat all</html:link><br>
<%@ include file="/common/footer_eoms.jsp"%>
