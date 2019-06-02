<%@ page contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="com.boco.eoms.common.util.*,java.util.*,java.io.*,com.boco.eoms.common.tree.RelativeDrop"%>
<html:html>
<head>
<title>
���Է�������
</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script language="javascript" src="<%=request.getContextPath()%>/css/checkform.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/css/table_calendar.js"></script>

<script language="javascript">
var User = new Array;
<%
  RelativeDrop rel = new RelativeDrop();
  String users = rel.strRelativeDrop("");
  out.println(users);
%>

  function selectDept(){
        var form = document.forms[0];
        var deptId = form.deptLabel.value;
        var sel_sprlen = form.deptLabel.options.length-1;
        var j=0;
        for(j=sel_sprlen;j>=0;j--)
        {
             form.userLabel.options[j]=null;
        }

         var m=0;
         form.userLabel.options[m]=new Option("","");
         if (deptId != "0")
         {
            var i;
            if (User[deptId]){
            k=User[deptId].length;

            for (i=0;i*2<k;i++)
            {
             var tempoption=new Option(User[deptId][i*2],User[deptId][i*2+1]);
             form.userLabel.options[m++]=tempoption;
            }
            }
         }
  }

  function select(){
    var form = document.forms[0];
    var valueString = "";
    valueString  = form.specialtySel[form.specialtySel.selectedIndex].text;
    valueString = valueString + ">" + form.manufacturerSel[form.manufacturerSel.selectedIndex].text;
    valueString = valueString + ">" + form.equipIdSel[form.equipIdSel.selectedIndex].text;
    valueString = valueString + ">" + form.expertSel[form.expertSel.selectedIndex].text
                              + ":" + form.hardSel[form.hardSel.selectedIndex].text
                              + ":" + form.secondarySel[form.secondarySel.selectedIndex].text
                              + ":" + form.primarySel[form.primarySel.selectedIndex].text;
    //alert(valueString);
    form.Value.value = valueString;
  }

  function doselect(){
    var form = document.forms[0];
    var flag = false;
    if ( form.Value.value != null && form.Value.value != "" ){
      for(var i = 0 ; i < form.studySel.options.length ; i++){
        if (form.studySel.options[i].text == form.Value.value)
          flag = true;
      }
      if( !flag ){
        var text = new Option(form.Value.value);
        var SelString = form.specialtySel.value
                  + ">" + form.manufacturerSel.value
                  + ">" + form.equipIdSel.value
                  + ">" + form.expertSel.value
                  + ":" + form.hardSel.value
                  + ":" + form.secondarySel.value
                  + ":" + form.primarySel.value;
        text.value = SelString;
        form.studySel.options[form.studySel.options.length]=text;
      }
      else
        alert("��ѡ���ظ�");
    }
  }

  function deleteSel(){
    var form = document.forms[0];
    form.studySel.options[form.studySel.selectedIndex] = null;
  }

  function doUserSelect(){
      var form = document.forms[0];
      var flag = false;
      var userSel = form.userLabel.options[form.userLabel.selectedIndex].text;
      for(var i = 0 ; i < form.testerSel.options.length ; i++){
        if (form.testerSel.options[i].text == userSel)
          flag = true;
      }
      if( !flag ){
        var text = new Option(userSel);
        text.value = form.userLabel.value;
        form.testerSel.options[form.testerSel.options.length]=text;
      }
      else
        alert("��ѡ���ظ�");

  }

  function deleteUserSel(){
    var form = document.forms[0];
    form.testerSel.options[form.testerSel.selectedIndex] = null;
  }

  function confirm(){
    var form = document.forms[0];
    if (form.studySel.options.length == 0){
      alert("ѡ������Ϊ��");
      return false;
    }
    //��������typeSelΪ 8>2>3>1:1:1:1;4>3>2>1:2:2:1 ����ʽ
    for(var i = 0; i < form.studySel.options.length; i++){
      if (i == 0)
        form.typeSel.value = form.studySel[i].value;
      else
        form.typeSel.value = form.typeSel.value + ";" + form.studySel[i].value;
    }
    //��������testersΪ ";" �ָ�����ʽ
    for(var i = 0; i < form.testerSel.options.length; i++){
      if (i == 0)
        form.testers.value = form.testerSel[i].value;
      else
        form.testers.value = form.testers.value + ";" + form.testerSel[i].value;
    }

    if (!checkLength(form.testers,1,2000)) return false;
    if (!checkLength(form.typeSel,1,1000)) return false;
    if (!checkLength(form.starttime,1,20)) return false;
    if (!checkLength(form.endtime,1,20)) return false;
    if (!checkLength(form.title,1,255)) return false;
  }
</script>

</head>

<eoms:DictType typeName="Specialty"/>
<eoms:DictType typeName="Manufacturer"/>
<eoms:DictType typeName="EquipId"/>

<body >
<html:form action="/configSubmit">
<SCRIPT language=javascript>
<!--
//������������������ʾ
var outObject;
var old_dd = null;
writeCalender();
bltSetDay(bltTheYear,bltTheMonth);
//-->
</SCRIPT>
<input type="hidden" name="Value" >
<html:hidden property="typeSel" title="��ѡ���"/>
<html:hidden property="testers" title="��ѡ��Ա"/>
<table cellpadding="0" cellspacing="0" width="95%" border="0">
  <tr>
    <td width="100%" align="center" class="table_title">
      ���Է�������
    </td>
  </tr>
</table>
<table cellpadding="1" cellspacing="1" width="95%" border="0" class="table_show">
  <tr class="tr_show">
    <td class="td_label">
      ����<font color="red">*</font>
    </td>
    <td>
      <html:text property="title" style="width:400" title="����"/>
    </td>
  </tr>
  <tr class="tr_show">
    <td align="center" rowspan="2" class="td_label">
      <p>
        רҵ
        <html:select property="specialtySel" style="width:120" value="1" onclick="select();">
          <html:options collection="Specialty"  property="value" labelProperty="label"/>
        </html:select>
      </p>
      <p>
        �豸
        <html:select property="manufacturerSel" style="width:120" value="6" onclick="select();">
          <html:options collection="Manufacturer"  property="value" labelProperty="label"/>
        </html:select>
      </p>
      <p>
        ����
        <html:select property="equipIdSel" style="width:120" value="1" onclick="select();">
          <html:options collection="EquipId"  property="value" labelProperty="label"/>
        </html:select>
      </p>
      <p>
        ר��(������)
        <html:select property="expertSel" style="width:120" value="25" onclick="select();">
          <html:options collection="NUMLABLIST"  property="value" labelProperty="label"/>
        </html:select>
      </p>
      <p>
        �߼�(������)
        <html:select property="hardSel" style="width:120" value="25" onclick="select();">
          <html:options collection="NUMLABLIST"  property="value" labelProperty="label"/>
        </html:select>
      </p>
      <p>
        �м�(������)
        <html:select property="secondarySel" style="width:120" value="25" onclick="select();">
          <html:options collection="NUMLABLIST"  property="value" labelProperty="label"/>
        </html:select>
      </p>
      <p>
        ����(������)
        <html:select property="primarySel" style="width:120" value="25" onclick="select();">
          <html:options collection="NUMLABLIST"  property="value" labelProperty="label"/>
        </html:select>
      </p>
    </td>
    <td>
      <p>��ѡ���:<font color="red">*</font></p>
      <html:select size="5" property="studySel" style="width:400" value="0" ondblclick="deleteSel()" title="��ѡ���">
      </html:select>
    </td>
  </tr>
  <tr class="tr_show">
    <td >
     <html:button value="ѡ��" property="add" onclick="doselect()"/>
     <html:button value="ɾ��" property="delete" onclick="deleteSel()"/>
    </td>
  </tr>
  <!--tr class="tr_show">
    <td class="td_label">
      ���׶ȱ���<font color="red">*</font>
    </td>
    <td>
      <html:text property="proportion" style="width:200" value="����:�м�:�߼�:ר��" title="���׶ȱ���"/>
    </td>
  </tr-->
  <tr class="tr_show">
    <td class="td_label">
      ��ʼʱ��<font color="red">*</font>
    </td>
    <td>
      <html:text property="starttime" style="width:200"  onfocus="setday(this)" readonly="true" title="��ʼʱ��"/>
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label">
      ����ʱ��<font color="red">*</font>
    </td>
    <td>
      <html:text property="endtime" style="width:200"  onfocus="setday(this)" readonly="true" title="����ʱ��"/>
    </td>
  </tr>
  <tr class="tr_show">
    <td class="td_label" rowspan="2">
      <p>
        ������Աѡ��
      </p>
      <p>
        <html:select size="1" property="deptLabel" value="1" style="width:200" onchange="selectDept()">
        <html:options collection="DEPTLABLIST" property="value" labelProperty="label"/>
        </html:select>
      </p>
      <p>
        <html:select size="9" property="userLabel" value="0" style="width:200" ondblclick="doUserSelect()">
        </html:select>
      </p>
    </td>
    <td>
      <p>��ѡ��Ա:<font color="red">*</font></p>
      <html:select size="8" property="testerSel" style="width:400" value="0" ondblclick="deleteUserSel()" title="��ѡ��Ա">
      </html:select>
    </td>
  </tr>
  <tr class="tr_show">
    <td>
      <html:button value="ѡ��" property="add" onclick="doUserSelect()"/>
      <html:button value="ɾ��" property="delete" onclick="deleteUserSel()"/>
    </td>
  </tr>
  <tr class="tr_show">
    <td colspan="2" align="right">
      <html:submit value="����" onclick="return confirm()"/>
    </td>
  </tr>
</table>
</html:form>
</body>
</html:html>
