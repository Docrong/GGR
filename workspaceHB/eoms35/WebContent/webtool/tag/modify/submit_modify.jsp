<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.List,
                 com.boco.eoms.mobilewap.webtool.bo.WebToolBO,
                 com.boco.eoms.mobilewap.tag.imp.SubmitTag,
                 com.boco.eoms.mobilewap.tag.vo.PostFieldVO" %>
<%@ page autoFlush="true" %>
<%
SubmitTag tag = (SubmitTag)request.getAttribute("submit");
String wapNodeId=tag.getCardId().substring(tag.getCardId().indexOf("_")+1,tag.getCardId().length());
String app = request.getContextPath(); 
WebToolBO webtool = new WebToolBO(request);
List arrayList = webtool.getNodeVariableList();
int n=0;
 %>
<script language="javascript">

var k = 3;

/**
 * �ر�
 */
function bt(){
    window.close();
}

/**
 * �ύcheck
 */
function checkInput(){
   // �ύ��ַ����Ϊ��
   var addressValue = form1.hrefCardId.value;
   if(addressValue == null || addressValue.length==0) {
      alert("�ύ��ַ����Ϊ��!");
      document.form1.hrefCardId.focus();
      return false;
   }
    // �������Ʋ���Ϊ��
    if(form1.postValue!=null){
        if(form1.postValue.value){
            if(form1.postName.value=="" || form1.postName.value==null) {
                alert("�������Ʋ���Ϊ��!");
                form1.postName.focus();
                return false;
            }
        }else{
            var postNames = form1.postName;
            for(i=0;i<postNames.length;i++){
                if(postNames[i].value=="" || postNames[i].value==null){
                    alert("�������Ʋ���Ϊ��!");
                    postNames[i].focus();
                    return false;
                }
            }
        }
    }
}

/**
 * ���Ӳ���
 */
function addPara(a,value) {
    if(a>k) {
        k=a;
    }
    var idVal = "str" + k;
    Para.innerHTML = Para.innerHTML+'<span id ="'+idVal+'"><table class="form_table"><tr>'
        +'<td>��������<br/><input name="postName" type="text" size="15" maxlength="20" value="'+value+'"  class="smallInput"><font color="#FF0000">*</font></td>'
        +'<td>����ֵ<br/><input name="postValue" type="text"  size="15" value="'+"$("+value+")"+'" class="smallInput"></td>'
        +'<td class="nobg">'
        +'<input name="del" type="button" value="ɾ��" onClick="delOpt('+idVal+')" class="submit">'
        +'</td>'
        +'</tr></table><span>';
    flags.innerHTML ='<input type="hidden" name="i_flag" value="'+k+'">';
    k++;
}
/**
 * ɾ��ָ������
 */
function delOpt(str) {
    if(confirm("��ȷʵҪɾ��������?")){
        str.innerHTML ='';
    }
}
/**
 * ѡ��ť
 */
function btt1(con){
    var retValue = window.showModalDialog("WapParamAllPop.jsp",
        "","help:0;resizable:0;status=0;scrollbars=0;dialogWidth=25;dialogHeight=30;center=true");

    if(retValue == null || retValue == ''){
        return;
    }
    con.value = con.value + retValue;
}
</script>
<html>
<head>
<title>����ԱWAP���ù���</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
</head>
<body>
<form name="form1" action="<%=app %>/webtool/tag" onSubmit="return checkInput()">
  <input name="act" type="hidden" value="modify"	>
  <input name="tagType" type="hidden" value="submit"	>
  <input name="tagKey" type="hidden" value="<%=request.getParameter("tagKey") %>">
  <input name="cardId" type="hidden" value="<%=request.getParameter("cardId") %>">
  <input name="class_str" type="hidden" value="com.boco.eoms.mobilewap.tag.imp.SubmitTag">
  
  <table width="100%">
	 <tr>
	   <td class="title">�޸��ύ��Ϣ</td>
	   <td align="right"></td>
	 </tr>
</table>

			<table class="form_table">
				<tr>
					<th>�ύ��ַ��</th>
					<td>
						<input id="hrefCardId" name="hrefCardId" type="text" value="<%=tag.getHrefCardId() %>"  size="20" ><font color="#FF0000">*</font>
						<input type="button" value="ѡ���ύҳ��" onclick="window.open('<%=app %>/webtool/common/getCardList.jsp?wapNodeId=<%=wapNodeId %>','','height=400,width=340,status=no,toolbar=no,menubar=no,location=no')">	    
					</td>
				</tr>
				<tr>
					<th>ѡ���ύ������</th>
					<td>
						<select name="postFileValue">
							<%for(int i=0;i<arrayList.size();i++){%>
								<option value=<%=arrayList.get(i)%> ><%=arrayList.get(i)%> </option>
							<%}%>
						</select>
						<input type="button" name="btadd" value="����" onclick="javascript:addPara(form1.i_flag.value,form1.postFileValue.value)">
					
			<span id ="Para">
			<%if(tag.getPostField()!=null) 
			     for(int j=0; j<tag.getPostField().size(); j++){
			     PostFieldVO pfVO = (PostFieldVO)tag.getPostField().get(j);
			     String idVal = "str" + j;
			 %>
			    <span id =<%=idVal%>>
			        <table class="form_table">
			            <tr>
			                <td>��������<br/>
						      <input name="postName" type="text" size="15" maxlength="20" value="<%=pfVO.getName()%>" class="smallInput"><font color="#FF0000">*</font>
					        </td>
			                <td>����ֵ<br/>
			                    <input name="postValue" type="text"  size="15" value="<%=pfVO.getValue()%>" class="smallInput">
					        </td>
			                <td class="nobg">
			                     <input name="del" type="button" value="ɾ��" onClick="delOpt(<%=idVal%>)" class="submit">
			                </td>
			            </tr>
			        </table>
			    </span>
	         <%}%>			
			</span>
			<span id ="flags">
             <input type="hidden" name="i_flag" value="<%=n%>">
            </span>
					
					</td>
				</tr>
			</table>
  <table width="100%">
    <tr><td align="right"><input type="submit" class="inputsubmit" value="�ύ"></td></tr>
  </table>
</form>
</body>
</html>
