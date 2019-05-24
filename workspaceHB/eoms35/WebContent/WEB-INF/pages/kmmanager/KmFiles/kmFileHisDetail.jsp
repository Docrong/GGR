<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="com.boco.eoms.km.file.model.KmFileHis"%>
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<title><fmt:message key="kmFile.title" /></title>
<content tag="heading"><b>

</content>
  <table class="formTable">
    <caption>
      <fmt:message key="kmFile.fileName" />:
      ${kmFileHis.fileName }
    </caption>
    <tr>
      <td class="label">
        <fmt:message key="kmFile.nodeId" />:
      </td>
      <td class="content">
       <eoms:id2nameDB id="${kmFileHis.nodeId}" beanId="kmFileTreeDao" />        
      </td>
    </tr>    
    <tr>
      <td class="label">
        <fmt:message key="kmFile.userId" />
      </td>
      <td class="content max">
        <eoms:id2nameDB beanId="tawSystemUserDao" id="${kmFileHis.userId}"/>
      </td>
    </tr>
        <tr>
      <td class="label">
        <fmt:message key="kmFile.deptId" />
      </td>
      <td class="content max">
        <eoms:id2nameDB beanId="tawSystemDeptDao" id="${kmFileHis.deptId}"/>
      </td>
    </tr>
        <tr>
      <td class="label">
        <fmt:message key="kmFile.phone" />
      </td>
      <td class="content max">
        ${kmFileHis.phone }
      </td>
    </tr>
    <tr>
      <td class="label">
        <fmt:message key="kmFile.uploadTime" />
      </td>
      <td class="content max">
        ${kmFileHis.uploadTime }
      </td>
    </tr>
    <tr>
		<td class="label">
			状态	
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="contentStatus" itemId="${kmFileHis.state}" beanId="id2nameXML" />
	</td>
	</tr>
	    <tr>
		<td class="label">
			版本号	
		</td>
		<td class="content">
			 ${kmFileHis.version }.0
	</td>
	</tr>
    <tr>
      <td class="label">
        <fmt:message key="kmFile.fileSize" />
      </td>
      <td class="content">
        ${kmFileHis.fileSize}&nbsp;KB
      </td>
    </tr>
    <tr>
      <td class="label">
        <fmt:message key="kmFile.fileGrade" />
      </td>
      <td class="content">
        <eoms:dict key="dict-kmmanager" dictId="fileGrade" itemId="${kmFileHis.fileGrade}" beanId="id2nameXML" />
      </td>
    </tr>    
    <tr>
      <td class="label">
        <fmt:message key="kmFile.clickCount" />
      </td>
      <td class="content">
        ${kmFileHis.clickCount}
      </td>
    </tr>  
    <tr>
      <td class="label">
        <fmt:message key="kmFile.keywords" />
      </td>
      <td class="content">
        ${kmFileHis.keywords}
      </td>
    </tr>   
    <tr>
      <td class="label">
        <fmt:message key="kmFile.fileAbstract" />
      </td>
      <td class="content">
        ${kmFileHis.fileAbstract}
      </td>
    </tr>
    <tr>
      <td class="label">
      	<fmt:message key="kmFile.download" />
      </td>
      <td class="content">
        <a href="${app}/kmmanager/files.do?method=downloadHis&id=${kmFileHis.hisId}&nodeId=${KM_FILETREE_NODEID}" style="text-decoration:none">${kmFileHis.fileName}</a>
      </td>
    </tr>
  </table>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp"%>
