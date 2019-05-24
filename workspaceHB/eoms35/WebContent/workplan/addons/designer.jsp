<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.workplan.util.*"%>

<%
/**
 * <p>Title: 附加表设计器(1.01)</p>
 * <p>Description: excel附加表增加</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author  
 * @version 3.5
 */
%>

<script>
lastobj=null;
function current(obj)
{
	if(lastobj!=null)
        {
		lastobj.style.background="#E2F1FC";
	}
	obj.style.background="#A2B1CC";
	lastobj=obj;
}
function GoBack()
{
  window.history.back()
}
</script>

	<%
        //字典option
          CodeTypeComsInfo codetype=CodeTypeComsInfo.getInstance();
          String dicOption="";
          codetype.reset();
          while(codetype.next()){
            dicOption=dicOption+"<option value="+codetype.getTypeID()+">"+codetype.getTypeName()+"</option>";
          }
	int headElementAmount=1;
	int bodyElementAmount=1;
	if(request.getParameter("headelementamount")!=null){
	try{
		headElementAmount=Integer.parseInt(request.getParameter("headelementamount"));
		bodyElementAmount=Integer.parseInt(request.getParameter("bodyelementamount"));
	}catch (Exception e) {

	}
	}



	%>

<table>
  <caption><bean:message key="designer.title.formTitle" /></caption>
</table>
  <!--修改元素个数表格：开始-->
  <FORM method="post" action="designadd.do" onSubmit="return Validator.Validate(this,2)">
	<bean:message key="designer.title.labTableHead" />
		<input name=headelementamount value="<%=headElementAmount%>" dataType="Integer" class="text">
	<bean:message key="designer.title.labElement" />
    <br>
    <br>
	<bean:message key="designer.title.labTableBody" />
		<input name=bodyelementamount value="<%=bodyElementAmount%>" dataType="Integer" class="text">
	<bean:message key="designer.title.labElement" />
    <br>
    <br>
	<input type="submit" value="<bean:message key="designer.title.btnModifyElmt" />" class="submit">
 </form>
 <br>
 <br>
<!--修改元素个数表格：结束-->
<!--建立XML各项变量表格：开始-->
<%--<table width="300" align=center style="margin:0pt 0pt 2pt 0pt">--%>
<%--  <tr>--%>
<%--    <td width="300" align="center" valign="middle" class="table_title">--%>
<%--      添加附加表--%>
<%--    </td>--%>
<%--  </tr>--%>
<%--</table>--%>
	 <form method="post"  action="designsave.do" onSubmit="return Validator.Validate(this,3)">
		<table border=0 cellspacing="1" cellpadding="1" class="listTable">
            <input type="hidden" name="headelementamount" value="<%=headElementAmount%>">
			<input type="hidden" name="bodyelementamount" value="<%=bodyElementAmount%>">
                  <!--附加表以及XML文件基本属性表格：开始-->
			<tr>
				<td COLSPAN="3" class="label">
					<bean:message key="designer.title.formAName" />
				</td>
				<td COLSPAN="14">
					<input name="name" value="" dataType="EnglishAndInteger" class="text">
				</td>
			</tr>
			<tr>
				<td COLSPAN="3" class="label">
					<bean:message key="designer.title.formAChinese" />
				</td>
				<td COLSPAN="14">
					<input name="value" value="" dataType="Chinese" class="text">
				</td>
			</tr>
            <tr>
				<td COLSPAN="3" class="label">
					<bean:message key="designer.title.formAModel" />
				</td>
				<td COLSPAN="14">
					<select name="model" class="select">
                        <option value="50"><bean:message key="designer.title.selWorkplan" /></option>
                    </select>
				</td>
			</tr>
            <tr>
				<td COLSPAN="3" class="label">
					<bean:message key="designer.title.formARemark" />
				</td>
				<td COLSPAN="14">
					<textarea cols="50" name="text" value="" class="textarea max"></textarea>
				</td>
			</tr>
			<tr>
				<td COLSPAN="3" class="label">
					<bean:message key="designer.title.formAType" />
				</td>
				<td COLSPAN="14">
					<select name="type" class="select">
					<option value="1">1</option>
					<option value="2">2</option>
					</select>
					<bean:message key="designer.title.labRemark" />
				</td>
		</tr>
                <!--附加表以及XML文件基本属性表格：结束-->
                <!--HEAD部分的元素表格：开始-->
		<tr>
			<td class="label" COLSPAN="17" align=left>
			<bean:message key="designer.title.labHead" /><%=headElementAmount%><bean:message key="designer.title.labElement" />
			</td>
		</tr>
		<tr align="center">
            <td>id</td>
			<td><bean:message key="designer.title.formDisplay" /></td>
            <td></td>
            <td></td>
			<td width="50"><bean:message key="designer.title.formName" /></td>
			<td><bean:message key="designer.title.formValue" /></td>
			<td><bean:message key="designer.title.formBr" /></td>
			<td>rows</td>
			<td>cols</td>
			<td><bean:message key="designer.title.formAlign" /></td>
			<td><bean:message key="designer.title.formValign" /></td>
			<td>X</td>
			<td>Y</td>
			<td><bean:message key="designer.title.formValidate" /></td>
			<td><bean:message key="designer.title.formStartDate" /></td>
			<td><bean:message key="designer.title.formEndDate" /></td>
            <td>id</td>
		</tr>
		<%

		for(int i=0;i<headElementAmount;i++){
		out.println("<tr onmouseover=\"current(this)\">");
		out.println("<td valign=\"top\">"+(i+1)+"</td>");
		out.println("<td valign=\"top\"><select name=\"showType"+i+"\">"+
                                "<option value=\"text\">text</option>"+
                                "<option value=\"input\">input</option>"+
                                "<option value=\"order\">order</option>"+
                                "<option value=\"timer\">timer</option>"+
                                "<option value=\"executer\">executer</option>"+
                                "<option value=\"file\">file</option>"+
                                "<option value=\"select\">select</option>"+
                                "</select></td>");
              out.println("<td valign=\"top\"><input type=\"hidden\" name=\"cycle"+i+"\" value=\"0\"></td>");
               out.println("<td valign=\"top\"><input type=\"hidden\" name=\"dicId"+i+"\" value=\"0\"></td>");


		out.println("<td valign=\"top\">h"+i+"<input type=\"hidden\" name=\"name"+i+"\" value=\"b"+i+"\"></td>");
		out.println("<td valign=\"top\"><input size=\"10\" name=\"value"+i+"\"></td>");
		out.println("<td valign=\"top\"><select  name=\"newLine"+i+"\">"+
                                "<option value=\"0\">0</option>"+
                                "<option value=\"1\">1</option>"+
                                "</select></td>");
		out.println("<td valign=\"top\"><input size=\"3\" name=\"rows"+i+"\" value=\"1\" dataType=\"Integer\"></td>");
		out.println("<td valign=\"top\"><input size=\"3\" name=\"cols"+i+"\" value=\"1\" dataType=\"Integer\"></td>");
		out.println("<td valign=\"top\"><select name=\"align"+i+"\">"+
                                "<option value=\"center\">center</option>"+
                                "<option value=\"left\">left</option>"+
                                "<option value=\"right\">right</option>"+
                                "</select></td>");
		out.println("<td valign=\"top\"><select name=\"valign"+i+"\">"+
                                "<option value=\"center\">center</option>"+
                                "<option value=\"top\">top</option>"+
                                "<option value=\"bottom\">bottom</option>"+
                                "</select></td>");
		out.println("<td valign=\"top\"><input size=\"3\" name=\"x"+i+"\" value=\"1\" dataType=\"Integer\"></td>");
		out.println("<td valign=\"top\"><input size=\"3\" name=\"y"+i+"\" value=\"1\" dataType=\"Integer\"></td>");
		out.println("<td valign=\"top\"><select name=\"validateType"+i+"\">"+
                                "<option value=\"null\">null</option>"+
                                "<option value=\"Chinese\">Chinese</option>"+
                                "<option value=\"Require\">Require</option>"+
                                "<option value=\"English\">English</option>"+
                                "<option value=\"Number\">Number</option>"+
                                "<option value=\"Integer\">Integer</option>"+
                                "<option value=\"Email\">Email</option>"+
                                "<option value=\"IdCard\">IdCard</option>"+
                                "<option value=\"Date\">Date</option>"+
                                "<option value=\"EnglishAndInteger\">EnglishAndInteger</option>"+
                                "</select></td>");
                out.println("<td valign=\"top\"><input size=\"9\" name=\"startTime"+i+"\" value=\"00:00:00\"></td>");
                out.println("<td valign=\"top\"><input size=\"9\" name=\"endTime"+i+"\" value=\"23:59:59\"></td>");
                out.println("<td valign=\"top\">"+(i+1)+"</td>");
                out.println("</tr>");
		}
		%>
                <!--HEAD部分的元素表格：结束-->
                <!--BODY部分的元素表格：开始-->
		<tr>
			<td class="label" COLSPAN="17" align=left>
			<bean:message key="designer.title.labBody" /><%=bodyElementAmount%><bean:message key="designer.title.labElement" />
			</td>
		</tr>
		<tr align="center">
            <td>id</td>
			<td><bean:message key="designer.title.formDisplay" /></td>
            <td><bean:message key="designer.title.formLoop" /></td>
            <td><bean:message key="designer.title.formDict" /></td>
			<td><bean:message key="designer.title.formName" /></td>
			<td><bean:message key="designer.title.formValue" /></td>
			<td><bean:message key="designer.title.formBr" /></td>
			<td>rows</td>
			<td>cols</td>
			<td><bean:message key="designer.title.formAlign" /></td>
			<td><bean:message key="designer.title.formValign" /></td>
			<td>X</td>
			<td>Y</td>
			<td><bean:message key="designer.title.formValidate" /></td>
            <td><bean:message key="designer.title.formStartDate" /></td>
            <td><bean:message key="designer.title.formEndDate" /></td>
            <td>id</td>
		</tr>
		<%
		//body元素
		for(int e=0;e<bodyElementAmount;e++){
		out.println("<tr class=\"tr_show\" onmouseover=\"current(this)\">");
                out.println("<td valign=\"top\">"+(e+1)+"</td>");
		out.println("<td valign=\"top\"><select name=\"showType_"+e+"\">"+
                                    "<option value=\"text\">text</option>"+
                                    "<option value=\"input\">input</option>"+
                                    "<option value=\"order\">order</option>"+
                                	"<option value=\"timer\">timer</option>"+
                                	"<option value=\"executer\">executer</option>"+
                                        "<option value=\"file\">file</option>"+
                                        "<option value=\"select\">select</option>"+
                                    "</select></td>");
                out.println("<td valign=\"top\"><select name=\"cycle_"+e+"\">"+
                                "<option value=\"0\" selected>0</option>"+
                                "<option value=\"1\" >1</option>"+
                                "</select></td>");
                out.println("<td valign=\"top\"><select name=\"dicId_"+e+"\">"+
               			dicOption+
                                "<option value=\"0\" >无</option>"+
                                "</select></td>");
		out.println("<td valign=\"top\">b"+(e+1)+"<input type=\"hidden\"name=\"name_"+e+"\" value=\"b"+(e+1)+"\"></td>");
		out.println("<td valign=\"top\"><input size=\"10\" name=\"value_"+e+"\"></td>");
		out.println("<td valign=\"top\"><select  name=\"newLine_"+e+"\">"+
                                "<option value=\"0\">0</option>"+
                                "<option value=\"1\">1</option>"+
                                      "</select></td>");
		out.println("<td valign=\"top\"><input size=\"3\" name=\"rows_"+e+"\" value=\"1\" dataType=\"Integer\"></td>");
		out.println("<td valign=\"top\"><input size=\"3\" name=\"cols_"+e+"\" value=\"1\" dataType=\"Integer\"></td>");
		out.println("<td valign=\"top\"><select name=\"align_"+e+"\">"+
                		"<option value=\"center\">center</option>"+
                                "<option value=\"left\">left</option>"+
                                "<option value=\"right\">right</option>"+
                                      "</select></td>");
		out.println("<td valign=\"top\"><select name=\"valign_"+e+"\">"+
                                "<option value=\"center\">center</option>"+
                                "<option value=\"top\">top</option>"+
                                "<option value=\"bottom\">bottom</option>"+
                                      "</select></td>");
		out.println("<td valign=\"top\"><input size=\"3\" name=\"x_"+e+"\" value=\"1\" dataType=\"Integer\"></td>");
		out.println("<td valign=\"top\"><input size=\"3\" name=\"y_"+e+"\" value=\"1\" dataType=\"Integer\"></td>");
		out.println("<td valign=\"top\"><select name=\"validateType_"+e+"\">"+
                                "<option value=\"null\">null</option>"+
                                "<option value=\"Chinese\">Chinese</option>"+
                                "<option value=\"Require\">Require</option>"+
                                "<option value=\"English\">English</option>"+
                                "<option value=\"Number\">Number</option>"+
                                "<option value=\"Integer\">Integer</option>"+
                                "<option value=\"Email\">Email</option>"+
                                "<option value=\"IdCard\">IdCard</option>"+
                                "<option value=\"Date\">Date</option>"+
                                "<option value=\"EnglishAndInteger\">EnglishAndInteger</option>"+
                                "</select></td>");
                 out.println("<td valign=\"top\"><input size=\"9\" name=\"startTime_"+e+"\" value=\"00:00:00\"></td>");
                out.println("<td valign=\"top\"><input size=\"9\" name=\"endTime_"+e+"\" value=\"23:59:59\"></td>");
                out.println("<td valign=\"top\">"+(e+1)+"</td>");
                out.println("</tr>");
		}
		%>
		<tr>
		<td COLSPAN="17">
		<input type="hidden" name="action" value="add">
		<input type="submit" value="<bean:message key="designer.title.btnSubmit" />" class="submit">
		<input type="button" value="<bean:message key="designer.title.btnBack" />" name="B2" Onclick="GoBack();" class="button">
		</td>
		</tr>
                <!--BODY部分的元素表格：结束-->
	 </table>
	</form>
<!--建立XML各项变量表格：结束-->
<%@ include file="/common/footer_eoms.jsp"%>
