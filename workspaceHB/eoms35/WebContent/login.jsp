
<jsp:directive.page import="org.acegisecurity.ui.AbstractProcessingFilter"/>
<jsp:directive.page import="org.acegisecurity.LockedException"/>
<jsp:directive.page import="org.acegisecurity.DisabledException"/>
<jsp:directive.page import="org.acegisecurity.BadCredentialsException"/>
<%@ include file="/common/taglibs.jsp"%>
 
<%@ page import ="com.boco.eoms.base.util.UtilMgrLocator"%>
<%@ page import ="com.boco.eoms.base.Constants;"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
 

<logic:notEmpty name="edu.yale.its.tp.cas.client.filter.user" scope="session">
	<% response.sendRedirect(request.getContextPath()+"/index.do?method=saveSession");%>
	<jsp:forward page="<c:url value='/index.do?method=saveSession'/>" />
</logic:notEmpty>

<head>
  <title><fmt:message key="webapp.name" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <script type="text/javascript" charset="utf-8" src="${app}/scripts/local/zh_CN.js"></script>  
  <script type="text/javascript" charset="utf-8" src="${app}/scripts/base/eoms.js"></script>
  <script type="text/javascript">eoms.appPath = "${app}";</script>
  <link rel="stylesheet" type="text/css" media="all" href="${app}/styles/${theme}/theme.css" />
  <script type="text/javascript" src="${app}/scripts/util/Cookie.js"></script> 
  <style type="text/css">
	body#login{
		background:#fff url(${app}/styles/gansu/bg.gif) repeat-x center 83px;
		overflow:hidden;
	}
	#login #page{width:900px !important;}
	#login #main{
		background: url(${app}/styles/gansu/login-logo.gif) no-repeat;
		padding-top:88px;
	}
	#login .login-wrap{
		height:331px;
		background: url(${app}/styles/gansu/login-bg.jpg) no-repeat;
	}
	#login form{
		padding-left:373px;
		padding-top:135px;
	}
	#login ul{
		float:left;
		padding:0;margin:0;
	}
	.input-btn{float:left;}
	.input-btn input{width:100px;height:60px;cursor:pointer;border:0;background:transparent;}
	#username, #password{
		height:20px;
		width:100px;
		margin:0px 0px 10px;
		border:0;
		background:#FDF6BF;
		-moz-border-radius:3px;
	}
	#login #footer{
		text-align:center;
		font-size:12px;
		margin-top:100px;
		border:none;
	}
	#login .errors{
		padding:0 10px;
		width:120px;
	}
  </style>
</head>

<body id="login" scroll="no">
<div id="page">
  <div id="content" class="clearfix">
    <div id="main">
  <div class="login-wrap">
  <%
  	String loginType = UtilMgrLocator.getEOMSAttributes().getLoginType();
  	String acegiStr = Constants.LOGIN_ACEGI;
  	String eomsStr = Constants.LOGIN_EOMS;
  	String loginerrObj = request.getAttribute(Constants.EOMS_SECURITY_EXCEPTION_KEY)==null?"":(String)request.getAttribute(Constants.EOMS_SECURITY_EXCEPTION_KEY);
  	System.out.println(loginerrObj);
  	System.out.println("hb1:111==="+loginType);
  	if (!(loginerrObj.equals(Constants.EOMS_SECURITY_EXCEPTION_KEY)&&loginerrObj.equals("")))
  		request.setAttribute("loginerror",loginerrObj);
  	System.out.println("hb1:222==="+request.getAttribute("loginerror"));
  	request.setAttribute("loginType",loginType);
  	request.setAttribute("acegiStr",acegiStr);
  	request.setAttribute("eomsStr",eomsStr);
  	System.out.println("hb1:333===loginType="+loginType+" acegiStr="+acegiStr+" eomsStr="+eomsStr) ; 
   %>

  <c:choose>
	  <c:when test="${loginType==acegiStr}">
	  <form method="post" id="loginForm" action="<c:url value="/j_security_check"/>" class="fm-v clearfix" onsubmit="saveUsername(this);">
	  </c:when>
	  <c:when test="${loginType==eomsStr}">

	  <form method="post" id="loginForm" action="<c:url value='/index.do?method=saveSession'/>" class="fm-v clearfix" onsubmit="saveUsername(this);">
	  <input type="hidden" valuue="${loginType }" name="loginType" id="loginType" />
	  </c:when>
  </c:choose>
    

  
        <ul>
        
          <li>
            <input type="text" class="required text" name="j_username" id="j_username" tabindex="1" />
          </li>

          <li>
            <input type="password" class="required text" name="j_password" id="j_password" tabindex="2" />
          </li>
          <c:choose>
	  		<c:when test="${loginType==acegiStr}">
        		<c:if test="${param.error != null}">
		          <li class="errors">
		            <img src="<c:url value="/images/icons/warning.gif"/>" alt="<fmt:message key="icon.warning"/>" class="icon" />
		            <%
		                Exception exceptionAcegi = (Exception)session.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY);
		                if(exceptionAcegi instanceof BadCredentialsException){
		            %>
		            	 <fmt:message key="errors.password.mismatch"/>
		            <%
		                }
		            	else if(exceptionAcegi instanceof LockedException){
		            %>
		            		<fmt:message key="errors.account.locked"/>
		            <%
		            	}else if(exceptionAcegi instanceof DisabledException){
		            %>
		            		<fmt:message key="errors.account.expired"/>
		            <%
		            	}
		             %>
		       
		          <!--${sessionScope.ACEGI_SECURITY_LAST_EXCEPTION.message}-->
		          </li>
        		</c:if>
        	</c:when>
        	<c:when test="${loginType==eomsStr}">
	  			<c:if test="${loginerror != ''}">
		          <li class="errors">
		            <img src="<c:url value="/images/icons/warning.gif"/>" alt="<fmt:message key="icon.warning"/>" class="icon" />
		            <%
		                String exceptionEoms = (String)request.getAttribute("loginerror");
		                if(exceptionEoms.equals("BadCredentialsException")){
		            %>
		            	 <fmt:message key="errors.password.mismatch"/>
		            <%
		                }
		            	else if(exceptionEoms.equals("LockedException")){
		            %>
		            		<fmt:message key="errors.account.locked"/>
		            <%
		            	}else if(exceptionEoms.equals("DisabledException")){
		            %>
		            		<fmt:message key="errors.account.expired"/>
		            <%
		            	}
		             %>
		       
		          <!--${sessionScope.ACEGI_SECURITY_LAST_EXCEPTION.message}-->
		          </li>
        		</c:if>
	 		</c:when>
        </c:choose>
          
         
        </ul>
            <div class="input-btn"><input type="submit" class="btn" name="login" title="<fmt:message key='button.login'/>" value=" " tabindex="3" /></div>
          </form>
	</div>
	<div class="clear"></div>
      <div id="footer">
       <br/><br/>
       </div>
    </div>
  </div>
</div>

<script type="text/javascript">  
	var c = eoms.util.Cookie;
	if (c.get("username") != null) {
		$("j_username").value = c.get("username");
		$("j_password").focus();
    } else {
        $("j_username").focus();
    }
    
    function saveUsername(frm) {
		c.set("username",frm.j_username.value,30);
    }
  function openOnlineUserWindow() {
  var url = '${app}/loginUserid.jsp';
  window.open(url, '', 'channelMode, menubar=no, toolbar=no, location=no, directories=no, status=yes, resizable=yes, scrollbars=yes, width=600, height=400, fullscreen=no');
}
</script>
<%@ include file="/common/footer_eoms.jsp"%>