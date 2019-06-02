<%@ include file="/common/taglibs.jsp"%>
<tr>
    <td>
        <select name="${param.leftId}" multiple="multiple"
            onDblClick="moveSelectedOptions(this,$('${param.rightId}'),true)"
            id="${param.leftId}" size="5">
    <c:if test="${leftList != null}">
        <c:forEach var="list" items="${leftList}" varStatus="status">
            <option value="${list.value}">
                <c:out value="${list.label}" escapeXml="false" />
            </option>
        </c:forEach>
    </c:if>
        </select>
    </td>
    <td class="moveOptions">
        <button name="moveRight" id="moveRight${param.listCount}" type="button" 
            onclick="moveSelectedOptions($('${param.leftId}'),$('${param.rightId}'),true)">
            &gt;&gt;</button><br />
        <button name="moveAllRight" id="moveAllRight${param.listCount}" type="button"
            onclick="moveAllOptions($('${param.leftId}'),$('${param.rightId}'),true)">
            All &gt;&gt;</button><br />
        <button name="moveLeft" id="moveLeft${param.listCount}" type="button"
            onclick="moveSelectedOptions($('${param.rightId}'),$('${param.leftId}'),true)">
            &lt;&lt;</button><br />
        <button name="moveAllLeft" id="moveAllLeft${param.listCount}" type="button"
            onclick="moveAllOptions($('${param.rightId}'),$('${param.leftId}'),true)">
            All &lt;&lt;</button>
    </td>
    <td>
        <select name="${param.rightId}" multiple="multiple"
            id="${param.rightId}" size="5">
    <c:if test="${rightList != null}">
        <c:forEach var="list" items="${rightList}" varStatus="status">
            <option value="${list.value}">
                <c:out value="${list.label}" escapeXml="false"/>
            </option>
        </c:forEach>
    </c:if>
        </select>
    </td>
</tr>
