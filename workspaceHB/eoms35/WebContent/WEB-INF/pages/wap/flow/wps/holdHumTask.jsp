<%@page import="java.util.Date,com.boco.eoms.base.util.StaticMethod" pageEncoding="UTF-8"%>
<%@ include file="/wap/common/taglibs.jsp"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
<title>工单处理</title>
</head>
<body >
<jsp:include page="../../head.jsp" flush="true"/>
<table border="0" cellspacing="0" cellpadding="0" class="sub_table_bg">
	 <tr>
    <td class="title_bg_01"><img src="${app}/wap/images/s_ico_01.gif" /> 工单处理</td>
  </tr>
<form action="${app}/wap/WPSAction.do?method=performDeal" method="POST">
  <input name="url" type="hidden" value='<%=(String) request.getAttribute("url")%>'/>
  <input name="hiddenStr" type="hidden" value='<%=(String) request.getAttribute("hiddenStr")%>' />
  <tr>
    <td class="list_text_03">
    	<table  width="100%" border="0" cellpadding="1" cellspacing="1" class="alterColor" style="table-layout:fixed;word-break:break-all">
	      <tr>
	        <td class="tt_head_bg_1"><img src="images/tu03.gif"/>故障处理响应级别:</td>
	      </tr>
	      <tr>
	        <td class="tt_head_bg_2">
	        	<select name="linkFaultResponseLevel">
	        		<option value="101030401">一级处理</option>
	        		<option value="101030402">二级处理</option>
	        	</select>
	        </td>
	      </tr>
	      
      <tr>
        <td class="tt_head_bg_1"><img src="images/tu03.gif"/>是否重大故障:</td>
      </tr>
      <tr>
        <td class="tt_head_bg_2">
        	<select name="linkIfGreatFault">
        		<option value="1030101">是</option>
        		<option value="1030102">否</option>
        	</select>
        </td>
      </tr>
      
      <tr>
        <td class="tt_head_bg_1"><img src="images/tu03.gif"/>归档满意度:</td>
      </tr>
      <tr>
        <td class="tt_head_bg_2">
        	<select name="holdStatisfied">
        		<option value="1030301">满意</option>
        		<option value="1030302">很满意</option>
        		<option value="1030303">很不满意</option>
        		<option value="1030304">一般</option>
        	</select>
        </td>
      </tr>
      
      <tr>
        <td class="tt_head_bg_1"><img src="images/tu03.gif"/>是否影响业务:</td>
      </tr>
      <tr>
        <td class="tt_head_bg_2">
        	<select name="mainIfAffectOperation">
        		<option value="1030101">是</option>
        		<option value="1030102">否</option>
        	</select>
        </td>
      </tr>
      
      <tr>
        <td class=" tt_head_bg_1"><img src="images/tu04.gif"/>归档意见:</td>
      </tr>
      <tr>
        <td class=" tt_head_bg_2"><textarea name="endResult"></textarea></td>
      </tr>
    </table> 
   </td>
  </tr>
 
 
	   <tr>
	    <td class="btn_bg">
	    	 <table>
			  		<tr>
			    	<td><input name="holdHumTask" type="submit" class="btn_02" value="提交" /></td>  	
					  </tr>
					</table>
			</td>
		</tr>

  </form>
</table>
</body>
</html>
