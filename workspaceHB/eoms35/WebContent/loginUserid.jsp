<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page
	import="com.boco.eoms.commons.system.user.service.ITawSystemUserManager,com.boco.eoms.base.util.ApplicationContextHolder,com.boco.eoms.commons.system.user.model.TawSystemUser,com.boco.eoms.message.service.impl.MsgServiceImpl"%>
<%@ page import="com.boco.eoms.base.util.StaticMethod,java.util.*;"%>
<title>title</title>
<script type="text/javascript"
	src="${app}/scripts/layout/AppSimpleTree.js"></script>
<%
	String userid = "";
	TawSystemUser tawSystemUser = null;
	String flag = "";
	String exit = "";
	String sRand = request.getParameter("sRand");
	String cardStr = "";
	System.out.println("---------" + cardStr);
	MsgServiceImpl msgService = new MsgServiceImpl();
	Random random = new Random();
	String password = "";
	String flagstr="";
	String bool ="";
%>
<form method="post" action="${app}/loginUserid.jsp" styleId="userForm">
	<table class="listTable" id="list-table">
		<%
			String login = request.getParameter("login");
			if (login != null) {

				userid = request.getParameter("userid").trim();

				ITawSystemUserManager mgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance()
				.getBean("ItawSystemUserSaveManagerFlush");
				tawSystemUser = mgr.getUserByuserid(userid);
				 
				if (tawSystemUser.getUserid() == null) {
					exit = "0";
				} else {
					exit = "1";
				}
				 
				cardStr = request.getParameter("card");
				if (cardStr == null) {
					String rand = "";
					for (int i = 0; i < 4; i++) {
				   rand = rand + String.valueOf(random.nextInt(10));
				   System.out.println(rand);

					}
					sRand = rand;
					 
				
				if (exit.equals("1")) {

					String creattime = StaticMethod.getLocalString();
					String serverId = "1";
					String content = "身份验证码为" + sRand;
					msgService.sendMsg4Mobiles(serverId, new String(content.getBytes("ISO-8859-1"), "UTF-8"), "-1",
					tawSystemUser.getMobile(), creattime);
					exit="2";
				}
				}
				password = request.getParameter("password");
				if(password==null){
				if (cardStr != null) {
					if (!sRand.equals(cardStr)) {
				flag = "0";
				 
					} else {
				flag = "1";
				 exit="";
				
					}
				}
				
				}
				if(password!=null){
				boolean boole = mgr.checkPasswd(password);
				if (boole) {
					bool = "2";
				    flagstr = mgr.updatePassword(tawSystemUser,password);
				    flag = "1";
				    exit="";
				    cardStr="";
				} else {
					bool = "3";
					flag = "1";
					exit="";
					cardStr="";
				}
					}
				
			}
		%>
		<tr>

			<td class="label">
				${eoms:a2u("用户名")}
				<input type='text' name="userid" size='20' value='<%=userid%>'>
			</td>

			<%
			if (exit.equals("0")) {
			%>
			<font color="red">${eoms:a2u("用户不存在")}</font>
			<%
			} else if (exit.equals("2")) {
			%>
		 
			<input type="hidden" name="sRand" value=<%=sRand%>>
			 
			<td class="label">
				${eoms:a2u("验证码")}
				<input type='text' name="card" size='20'>
			</td>

			<%
			}
			%>

			<%
			if (flag.equals("0")) {
			%>
			<font color="red">${eoms:a2u("输入的验证码不正确")}</font>
			<%
			} else if (flag.equals("1")) {
			%>
			
			
			<td class="label">
				${eoms:a2u("输入密码（大写字母,小写字母,数字组成,大于6为）")}
				<input type="hidden" name="card" value="">
				<input type='text' name="password" size='20' >
			</td>


			<%
			} if (bool.equals("2")) {
			%>
			<font color="red">${eoms:a2u("密码修改成功")}<%=password%></font>
			<%
			}else if (bool.equals("3")) { 
			%>
			<font color="red">${eoms:a2u("输入的密码格式错误，请输入大写字母加小写字母和数字组成的密码")}<%=password%></font>
			<%
			}
			%>
			<td class="label">
				<%
				if (bool.equals("2")) {
				%>



				<input type="button" name="login" class="button"
					value=" ${eoms:a2u(" 关闭")}" Onclick="window.close()">

				<%
				}
				%>
				<input type="submit" name="login" class="button"
					value=" ${eoms:a2u("提交")}" >

			</td>
		</tr>
	</table>
	<form>
		<%@ include file="/common/footer_eoms.jsp"%>