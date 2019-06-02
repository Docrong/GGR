<%@ include file="/common/taglibs.jsp"%>
<c:choose>
      <c:when test="${param.id=='1'}">
        <c:redirect url="/duty/tawRmPlanContent.do?method=planSearch&folderPath=1"/>
      </c:when>
      <c:when test="${param.id=='2'}">
        <c:redirect url="/duty/tawRmWorkorderRecord.do?method=workOrderSearch&folderPath=2"/>
      </c:when>
      <c:when test="${param.id=='3'}">
        <c:redirect url="/workbench/memo/tawWorkbenchMemoNodes.do?method=memoSearch&folderPath=3"/>
      </c:when>
      <c:when test="${param.id=='4'}">
        <c:redirect url="/duty/tawRmDispatchRecord.do?method=dispatchRecordSearch&folderPath=4"/>
      </c:when>
      <c:when test="${param.id=='5'}">
        <c:redirect url="/duty/tawRmVisitRecord.do?method=visitRecordSearch&folderPath=5"/>
      </c:when>
      <c:when test="${param.id=='6'}">
        <c:redirect url="/duty/tawRmLoanRecord.do?method=loanRecordSearch&folderPath=6"/>
      </c:when>
      <c:when test="${param.id=='7'}">
        <c:redirect url="/duty/tawRmReliefRecord.do?method=reliefRecordSearch&folderPath=7"/>
      </c:when>
      <c:when test="${param.id=='8'}">
        <c:redirect url="/duty/tawRmLogUnite.do?method=logUniteSearch&folderPath=8"/>
      </c:when>
</c:choose>