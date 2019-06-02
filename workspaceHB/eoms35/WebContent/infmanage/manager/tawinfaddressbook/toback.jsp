<%
   String mail = (String)request.getAttribute("mail");
%>
<body >
<form>

<script language="javascript">

    var mail;
    mail = "<%=mail%>";
    window.location.href='mailto:'+mail;
    window.close();

</script>

</form>
</body>