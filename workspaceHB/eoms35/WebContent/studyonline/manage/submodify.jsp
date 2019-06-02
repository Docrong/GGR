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
题目信息修改
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script language="javascript" src="<%=request.getContextPath()%>/css/checkform.js"></script>

<script language="JavaScript" >
</script>
<eoms:DictType typeName="Specialty"/>
<eoms:DictType typeName="Manufacturer"/>
<eoms:DictType typeName="EquipId"/>

</head>
<body bgcolor="#ffffff">
<html:form action="/modifySubmit">
<html:hidden name="OnlineWarehouse" property="subId"/>
</html:hidden>

<table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
    <td width="100%" align="center" class="table_title">
      题目修改
    </td>
  </tr>
</table>
<table cellpadding="1" cellspacing="1" width="95%" align="center" border="0" class="table_show">
  <tr class="tr_show">
    <td class="td_label" width="10%" align="center">
      标题
    </td>
    <td width="90%">
       <html:textarea name="OnlineWarehouse" rows="4" style="width:100%" property="title"/>
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label" width="10%" align="center">
      选项
    </td>
    <td width="90%">
       <html:textarea name="OnlineWarehouse" rows="4" style="width:100%" property="options"/>
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label" width="10%" align="center">
      答案
    </td>
    <td width="90%">
       <html:text name="OnlineWarehouse" style="width:150" property="result"/>
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label" width="10%" align="center">
      难度
    </td>
    <td width="90%">
       <html:select property="difficulty" name="OnlineWarehouse" style="width:150">
         <html:options collection="RANK" property="value" labelProperty="label"/>
       </html:select>
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label" width="10%" align="center">
      发布类型
    </td>
    <td width="90%">
       <html:select property="issueType" name="OnlineWarehouse" style="width:150">
         <html:options collection="ISSUETYPE" property="value" labelProperty="label"/>
       </html:select>
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label" width="10%" align="center">
      分值
    </td>
    <td width="90%">
       <html:text name="OnlineWarehouse" style="width:150" property="value"/>
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label" width="10%" align="center">
      专业
    </td>
    <td width="90%">
       <html:select property="specialty" name="OnlineWarehouse" style="width:150">
         <html:options collection="Specialty" property="value" labelProperty="label"/>
       </html:select>
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label" width="10%" align="center">
      厂家
    </td>
    <td width="90%">
       <html:select property="manufacturer" name="OnlineWarehouse" style="width:150">
         <html:options collection="Manufacturer" property="value" labelProperty="label"/>
       </html:select>
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label" width="10%" align="center">
      设备
    </td>
    <td width="90%">
       <html:select property="equipment" name="OnlineWarehouse" style="width:150">
         <html:options collection="EquipId" property="value" labelProperty="label"/>
       </html:select>
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label" width="10%" align="center">
      备注
    </td>
    <td width="90%">
      <html:textarea name="OnlineWarehouse" rows="4" style="width:100%" property="comment"/>
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label" width="10%" align="center">
      图片
    </td>
    <td width="90%">
      <bean:define id="fileName" name="OnlineWarehouse" property="image" type="java.lang.String"/>
      <bean:define id="subId" name="OnlineWarehouse" property="subId" type="java.lang.String"/>
        <IFRAME ID=IFrame1  FRAMEBORDER=0 width='100%' height=80 SCROLLING=yes SRC="manage/imageupload.jsp?subId=<%=subId%>&fileName=<%=fileName%>"></IFRAME>
    </td>
  </tr>
  <tr class="tr_show">
    <td align="right" colspan="2">
      <html:submit value="提交"/>
      <!--input type="button" value="返回" onclick="history.back()"-->
    </td>
  </tr>
</table>
</html:form>
</body>
</html>
