<%@ page language="java" pageEncoding="UTF-8" %>

<jsp:directive.page import="com.boco.eoms.workplan.model.TawwpExecuteContent"/>

<jsp:directive.page import="com.boco.eoms.common.util.StaticMethod"/>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>

<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>

<%@ page import ="java.util.List"%>

<%@ page import ="java.util.ArrayList""text/css">

<body topmargin=0 marginheight=0 leftmargin=0 marginwidth=0 >



<!--  正文开始  -->



<form name="monthplan">



  <table width="700" align=center style="margin:0pt 0pt 2pt 0pt">

    <tr>

      <td width="700" align="center" valign="middle" class="table_title">

       作业计划内容查询结果

      </td>

    </tr>

  </table>



  <table width="700" border="0" align="center" cellpadding="1"  cellspacing="1" class="table_show">

    <tr class="td_label">

      <td width="300">

        执行内容名称

      </td>

      <td width="100">

        执行内容

      </td>

      <td width="100">

        开始时间

      </td>

      <td width="150">

        结束时间

      </td>

      

    </tr>

<%	ArrayList list=(ArrayList)request.getAttribute("list");

	if(list!=null){

		for(int i=0;i<list.size();i++){

		 TawwpExecuteContent content=(TawwpExecuteContent)list.get(i);

 %>

    <tr class="tr_show">

      <td width="300" class="clsthd2">

					<a

						href="../tawwpaddons/addonsread.do?action=edit&window=new&myid=<%=content.getFormDataId()%>&model=50&addonsid=<%=content.getId()%>&reaction=/tawwpexecute/redirection.jsp"

						target="blank"> <%=content.getName()%>

					</a>

				</td>

      <td width="100" class="clsthd2"align="middle">

     	<%=StaticMethod.null2String(content.getContent())%>

      </td>

      <td width="100" class="clsthd2" align="middle">

      <%=content.getStartDate()%>

       </td>

      <td width="150" class="clsthd2" align="middle">

        <%=content.getEndDate()%>

      </td>

       

    </tr>

 <%	}

   }

   

  %>  

  </table>



</form>



<!--  正文结束  -->



</body>