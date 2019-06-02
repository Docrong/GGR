<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="WEB-INF/classes/config/cssHorizontalMenu.vm" permissions="rolesAdapter">
<ul id="primary-nav" class="menuList">
    <li class="pad">&nbsp;</li>
    <c:if test="${empty pageContext.request.remoteUser}"><li><a href="<c:url value="/login.jsp"/>" class="current"><fmt:message key="login.title"/></a></li></c:if>
    <menu:displayMenu name="MainMenu"/>
    <menu:displayMenu name="UserMenu"/>
    <menu:displayMenu name="FileUpload"/>
    <menu:displayMenu name="AdminMenu"/>
    <menu:displayMenu name="Logout"/>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <!--Dept-START-->
    <menu:displayMenu name="DeptMenu"/>
    <!--Dept-END-->
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <!--TawPrefixTable-START-->
    <menu:displayMenu name="TawPrefixTableMenu"/>
    <!--TawPrefixTable-END-->
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <!--TawDemoTable-START-->
    <menu:displayMenu name="TawDemoTableMenu"/>
    <!--TawDemoTable-END-->
    
    
    
    
    
    
    
    
    
    
    
    <!--AppfuseSample-START-->
    <menu:displayMenu name="AppfuseSampleMenu"/>
    <!--AppfuseSample-END-->
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <!--TestTable-START-->
    <menu:displayMenu name="TestTableMenu"/>
    <!--TestTable-END-->
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <!--TawDemoMytable-START-->
    <menu:displayMenu name="TawDemoMytableMenu"/>
    <!--TawDemoMytable-END-->
    
    
    
    
    
    <!--TawCommonMessageServiceType-START-->
    <menu:displayMenu name="TawCommonMessageServiceTypeMenu"/>
    <!--TawCommonMessageServiceType-END-->
    <!--TawCommonMessageOpertype-START-->
    <menu:displayMenu name="TawCommonMessageOpertypeMenu"/>
    <!--TawCommonMessageOpertype-END-->
    <!--TawCommonMessageModelType-START-->
    <menu:displayMenu name="TawCommonMessageModelTypeMenu"/>
    <!--TawCommonMessageModelType-END-->
    <!--TawCommonMessageAddService-START-->
    <menu:displayMenu name="TawCommonMessageAddServiceMenu"/>
    <!--TawCommonMessageAddService-END-->
    <!--TawCommonMessageSubscribe-START-->
    <menu:displayMenu name="TawCommonMessageSubscribeMenu"/>
    <!--TawCommonMessageSubscribe-END-->
    <!--TawCommonMessageMonitor-START-->
    <menu:displayMenu name="TawCommonMessageMonitorMenu"/>
    <!--TawCommonMessageMonitor-END-->
    <!--TawCommonMessageMonitorRef-START-->
    <menu:displayMenu name="TawCommonMessageMonitorRefMenu"/>
    <!--TawCommonMessageMonitorRef-END-->
    
    <!--TawSystemUserRefRole-START-->
    <menu:displayMenu name="TawSystemUserRefRoleMenu"/>
    <!--TawSystemUserRefRole-END-->
    <!--TawSystemUser-START-->
    <menu:displayMenu name="TawSystemUserMenu"/>
    <!--TawSystemUser-END-->
    
    <!--TawSystemDept-START-->
    <menu:displayMenu name="TawSystemDeptMenu"/>
    <!--TawSystemDept-END-->
    <!--TawSystemDictType-START-->
    <menu:displayMenu name="TawSystemDictTypeMenu"/>
    <!--TawSystemDictType-END-->
    <!--TawSystemDictItem-START-->
    <menu:displayMenu name="TawSystemDictItemMenu"/>
    <!--TawSystemDictItem-END-->
    <!--PortalUnifyUser-START-->
    <menu:displayMenu name="PortalUnifyUserMenu"/>
    <!--PortalUnifyUser-END-->
</ul>




















































































































































































</menu:useMenuDisplayer>

<script type="text/javascript">
/*<![CDATA[*/
var navItems = document.getElementById("primary-nav").getElementsByTagName("li");

for (var i=0; i<navItems.length; i++) {
    if(navItems[i].className == "menubar") {
        navItems[i].onmouseover=function() { this.className += " over"; }
        navItems[i].onmouseout=function() { this.className = "menubar"; }
    }
}
/*]]>*/
</script>
