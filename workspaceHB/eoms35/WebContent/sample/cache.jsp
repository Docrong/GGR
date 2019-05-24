<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.*,
				 java.text.*"
%>
<%!	// global variables
	static final DecimalFormat mbFormat = new DecimalFormat("#0.00");
	static final DecimalFormat percentFormat = new DecimalFormat("#0.0");
	static final int NUM_BLOCKS = 50;
%>

<p>

<font size="-1"><b>Java VM （Java虚拟机）内存</b></font>
<ul>

<%


    // The java runtime
	Runtime runtime = Runtime.getRuntime();

    double freeMemory = (double)runtime.freeMemory()/(1024*1024);
	double totalMemory = (double)runtime.totalMemory()/(1024*1024);
	double usedMemory = totalMemory - freeMemory;
	double percentFree = ((double)freeMemory/(double)totalMemory)*100.0;
    int free = 100-(int)Math.round(percentFree);
%>
	<table border=0>
	<tr><td><font size="-1">已用内存:</font></td>
        <td><font size="-1"><%= mbFormat.format(usedMemory) %> MB</font></td>
    </tr>
	<tr><td><font size="-1">内存总量:</font></td>
        <td><font size="-1"><%= mbFormat.format(totalMemory) %> MB</font></td>
    </tr>
	</table>
	<br>
    <table border=0><td>
    <table bgcolor="#000000" cellpadding="1" cellspacing="0" border="0" width="200" align=left>
    <td>
    <table bgcolor="#000000" cellpadding="1" cellspacing="1" border="0" width="100%">
<%    for (int i=0; i<NUM_BLOCKS; i++) {
        if ((i*(100/NUM_BLOCKS)) < free) {
    %>
    	<td bgcolor="#00ff00" width="<%= (100/NUM_BLOCKS) %>%"><img src="images/img/blank.gif" width="1" height="15" border="0"></td>
<%		} else { %>
    	<td bgcolor="#006600" width="<%= (100/NUM_BLOCKS) %>%"><img src="images/img/blank.gif" width="1" height="15" border="0"></td>
<%		}
    }
%>
    </table>
    </td>
    </table></td><td>
        <font size="-1">
        &nbsp;<b><%= percentFormat.format(percentFree) %>% 空闲</b>
        </font>
    </td></table>
</ul>

<%	// Destroy the runtime reference
	runtime = null;
        System.gc();
%>
