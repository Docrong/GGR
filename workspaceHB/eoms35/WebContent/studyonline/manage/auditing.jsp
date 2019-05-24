<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,java.io.*"%>
<html>
<head>
<title>
��Ŀ����
</title>

<script language="javascript">
  function selectSpecialty(){
    document.all.specialtySel.value = "true";
  }

  function reject(){
    document.forms[0].action = "reject.do";
  }

  function auditingDO(){
    document.forms[0].action = "auditingDO.do";
  }

  function checkform(){
    var form = document.forms[0];
    if( form.comment.value == "" && form.action != "auditingDO.do" ){
      alert("������д�������");
      return false;
    }

      for (var i = 0; i < form.elements.length; i++){
        var obj = form.elements[i];
        if ( obj.type == 'checkbox' ){
          if ( obj.checked ){
              form.checkSel.value = form.checkSel.value + ",'" + obj.name + "'";
            }
        }
      }
      form.checkSel.value = form.checkSel.value.substring(1);
    var msg = "";
    if( form.action == "reject.do" )
       msg = "����";
    else
       msg = "����ͨ��";
    if( form.checkSel.value == "" ){
      alert(msg + "����δѡ��");
      return false;
    }
    if( !confirm( "�Ƿ�ȷ��"+ msg +"����" ) )
      return false;
  }
</script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<eoms:DictType typeName="Specialty"/>
<%int i = 1;%>

</head>
<body bgcolor="#ffffff">
<html:form action="/auditingDO" onsubmit="return checkform()">
<html:hidden property="checkSel" value=""/>
<html:hidden property="specialtySel" value=""/>
<table cellpadding="1" cellspacing="1" width="95%" align="center" border="0" class="table_show">
  <tr>
    <td width="6%" class="td_label" align="center">
    </td>
    <td width="6%" class="td_label" align="center">
      ���
    </td>
    <td class="td_label" align="center" width="17%">
      �����
    </td>
    <td class="td_label" align="center" width="23%">
      ���ʱ��
    </td>
    <td class="td_label" align="center" width="10%">
      רҵ����
    </td>
    <td class="td_label" align="center" width="10%">
      �鿴
    </td>
    <td class="td_label" align="center" width="28%">
      �������ɼ�¼
    </td>
  </tr>
</table>
<center>
<div id="divTable" style="position: relative; align: center; top: 0px;width:  95%; height:  75%; z-index: 1; overflow: auto; overflow-x: hidden">
  <table cellpadding="1" cellspacing="1" width="100%" border="0" class="table_show">
  <logic:iterate id="onlineInfo" name="INFOLIST" type="com.boco.eoms.studyonline.model.OnlineInfo">
    <tr class="tr_show" align="center">
      <td width="6%">
        <input type="checkbox" name="<%=onlineInfo.getImportId()%>">
      </td>
      <td width="6%">
        <%=i++%>
      </td>
      <td width="17%">
        <bean:write name="onlineInfo" property="importUser"/>
      </td>
      <td width="23%">
        <%=StaticMethod.getTimestampString(onlineInfo.getImportTime())%>
      </td>
      <td width="10%">
        <bean:write name="onlineInfo" property="specialtyName"/>
      </td>
<%
  String url = "auditingDetail.do";
  java.util.HashMap map = new java.util.HashMap();
  map.put( "importId" , onlineInfo.getImportId() );
  pageContext.setAttribute( "map" , map );
%>
      <td width="10%">
        <html:link href="<%=url%>" name="map">
          <img src="<%=request.getContextPath()%>/images/bottom/an_xs.gif" border="0">
        </html:link>
      </td>
      <td width="28%">
        <bean:write name="onlineInfo" property="comment"/>
      </td>
    </tr>
  </logic:iterate>
  </table>
</div>
<br>
<table cellpadding="1" cellspacing="1" width="80%" border="0" class="table_show">
  <tr class="td_label" align="center">
    <td width="20%">
      <p>�������</p>
      (125����)
    <td width="80%">
      <input type="textarea" name="comment" style="height:50;width:100%"/>
    </td>
  </tr>
  <tr class="tr_show" align="center">
    <td colspan="2">
      <html:submit value="���ͨ��" onclick="return auditingDO()"/>
      <html:submit value="����" onclick="return reject()"/>
    </td>
  </tr>
</table>
</center>
</html:form>
</body>
</html>
