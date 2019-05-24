<%@ page contentType="text/html; charset=gb2312"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<html:form method="post" action="/TawRmRecord/save">
<html:hidden property="strutsAction" value="1"/>

<input type="hidden" name="user" value="">
<TABLE cellSpacing="0" cellPadding="0" width="520" border="0">
<TR>
	<TD noWrap align="middle" colSpan="3" height="15"><font color="crimson"><span id="LabelInfo"></span></font></TD>
</TR>
</TABLE>
<TABLE cellSpacing="0" borderColorDark="#ffffff" cellPadding="0" width="560" align="center" bgColor="#f3f3f3" borderColorLight="#66ccff" border="1">

<TR>
	<TD noWrap rowSpan="5">&nbsp;<bean:message key="TawRmRecord.baseinfo"/></TD>
	<TD noWrap><bean:message key="TawRmSysteminfo.roomName"/></TD>
	<TD colSpan="3">
	<input name="RoomName" id="RoomName" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" maxlength="150" size="20" value="\u00CA\u00A1\u00CD\u00F8\u00B9\u00DC\u00D6\u00D0\u00D0\u00C4\u00BB¨²¡¤\u00BF" />
	<input name="RoomID" id="RoomID" type="hidden" value="1" />
        <input name="roomId" id="roomId" type="hidden" value="1" />
        </TD>
</TR>

<TR>
	<TD>
            <bean:message key="TawRmRecord.hander"/>
        </TD>
	<TD colSpan="3">
	<input name="hander" id="hander" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58" value="<logic:iterate id="tawRmRecord" name="TAWRMRECORDS" type="com.boco.eoms.duty.model.TawRmRecord"> <bean:write name="tawRmRecord" property="hander" scope="page"/> </logic:iterate> " />

        </TD>
</TR>
<TR>
	<TD><bean:message key="TawRmRecord.dutyman"/></TD>
	<TD colSpan="3">
	<input name="dutyman" id="dutyman" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58" value="<logic:iterate id="tawRmRecord" name="TAWRMRECORD_DUTYMAN" type="com.boco.eoms.duty.model.TawRmRecord"><bean:write name="tawRmRecord" property="dutyman" scope="page"/> </logic:iterate>" />
</TR>
<TR>
	<TD><bean:message key="TawRmRecord.receiver"/></TD>
	<TD colSpan="3">
	<input name="receiver" id="receiver" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58" value="<logic:iterate id="tawRmRecord" name="TAWRMRECORD_RECEIVER" type="com.boco.eoms.duty.model.TawRmRecord"><bean:write name="tawRmRecord" property="receiver" scope="page"/> </logic:iterate>" />
        </TD>
</TR>

<TR>
	<TD><bean:message key="TawRmRecord.receivertime"/></TD>
	<TD>
        <input name="starttime" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" size="18" value="<eoms:TextTime />" />
	</TD>
	<TD><bean:message key="TawRmRecord.flag"/></TD>
	<TD><Span id="spanFlag"><Font Color=Green> </font></Span>
            <input name="Flag" id="Flag" type="hidden" value="0" />
            <input name="flag" id="flag" type="hidden" value="0" />
        </TD>
</TR>
<TR>
	<TD noWrap align="middle" rowSpan="3"><bean:message key="TawRmRecord.circumstance"/></TD>
	<TD><bean:message key="TawRmRecord.weather"/></TD>
	<TD><html:text property="weather" size="20"/>**
	</TD>
	<TD><bean:message key="TawRmRecord.clean"/></TD>
	<TD><html:text property="clean" size="10"/>**
	</TD>
</TR>
<TR>
	<TD><bean:message key="TawRmRecord.temperature"/></TD>
	<TD><select name="temperature" id="temperature">
	<option value="0">0</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
	<option value="13">13</option>
	<option value="14">14</option>
	<option value="15">15</option>
	<option value="16">16</option>
	<option value="17">17</option>
	<option value="18">18</option>
	<option value="19">19</option>
	<option selected="selected" value="20">20</option>
	<option value="21">21</option>
	<option value="22">22</option>
	<option value="23">23</option>
	<option value="24">24</option>
	<option value="25">25</option>
	<option value="26">26</option>
	<option value="27">27</option>
	<option value="28">28</option>
	<option value="29">29</option>
	<option value="30">30</option>
	<option value="31">31</option>
	<option value="32">32</option>
	<option value="33">33</option>
	<option value="34">34</option>
	<option value="35">35</option>
	<option value="36">36</option>
	<option value="37">37</option>
	<option value="38">38</option>
	<option value="39">39</option>
	<option value="40">40</option>
	<option value="41">41</option>
	<option value="42">42</option>
	<option value="43">43</option>
	<option value="44">44</option>
	<option value="45">45</option>
	<option value="46">46</option>
	<option value="47">47</option>
	<option value="48">48</option>
	<option value="49">49</option>
	<option value="50">50</option>
</select>&nbsp;\u2103</TD>
        <TD noWrap><bean:message key="TawRmRecord.clean1"/></TD>
        <TD><html:text property="clean1" size="10"/>**
        </TD>
</TR>
<TR>
        <TD><bean:message key="TawRmRecord.wet"/></TD>
        <TD><select name="wet" id="wet">
	<option value="0">0</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
	<option value="13">13</option>
	<option value="14">14</option>
	<option value="15">15</option>
	<option value="16">16</option>
	<option value="17">17</option>
	<option value="18">18</option>
	<option value="19">19</option>
	<option value="20">20</option>
	<option value="21">21</option>
	<option value="22">22</option>
	<option value="23">23</option>
	<option value="24">24</option>
	<option value="25">25</option>
	<option value="26">26</option>
	<option value="27">27</option>
	<option value="28">28</option>
	<option value="29">29</option>
	<option value="30">30</option>
	<option value="31">31</option>
	<option value="32">32</option>
	<option value="33">33</option>
	<option value="34">34</option>
	<option value="35">35</option>
	<option value="36">36</option>
	<option value="37">37</option>
	<option value="38">38</option>
	<option value="39">39</option>
	<option value="40">40</option>
	<option value="41">41</option>
	<option value="42">42</option>
	<option value="43">43</option>
	<option value="44">44</option>
	<option value="45">45</option>
	<option value="46">46</option>
	<option value="47">47</option>
	<option value="48">48</option>
	<option value="49">49</option>
	<option selected="selected" value="50">50</option>
	<option value="51">51</option>
	<option value="52">52</option>
	<option value="53">53</option>
	<option value="54">54</option>
	<option value="55">55</option>
	<option value="56">56</option>
	<option value="57">57</option>
	<option value="58">58</option>
	<option value="59">59</option>
	<option value="60">60</option>
	<option value="61">61</option>
	<option value="62">62</option>
	<option value="63">63</option>
	<option value="64">64</option>
	<option value="65">65</option>
	<option value="66">66</option>
	<option value="67">67</option>
	<option value="68">68</option>
	<option value="69">69</option>
	<option value="70">70</option>
	<option value="71">71</option>
	<option value="72">72</option>
	<option value="73">73</option>
	<option value="74">74</option>
	<option value="75">75</option>
	<option value="76">76</option>
	<option value="77">77</option>
	<option value="78">78</option>
	<option value="79">79</option>
	<option value="80">80</option>
</select>&nbsp;%</TD>
        <TD><bean:message key="TawRmRecord.conditioner"/></TD>
        <TD><html:text property="conditioner" size="10"/>**</TD>
</TR>
<TR>
        <TD align="middle"><bean:message key="TawRmRecord.accessories"/></TD>
        <TD colSpan="4"><Span id="spanFile"></Span><BR>
                <input name="Uploadfile" id="Uploadfile" type="file" style="WIDTH: 391px; HEIGHT: 22px" size="46" />&nbsp;&nbsp;&nbsp;&nbsp;
                <input language="javascript" onclick="{if (typeof(Page_ClientValidate) != 'function' ||  Page_ClientValidate()) __doPostBack('btnUpLoad','')} " name="btnUpLoad" id="btnUpLoad" type="button" value="\u00C9\u00CF\u00B4\u00AB" style="WIDTH: 40px; HEIGHT: 22px" />
        </TD>
</TR>
<TR>
        <TD align="middle">\u4E2A\u4EBA\u65E5\u5FD7</TD>
        <TD colSpan="4">&nbsp;<Span id="spanSubReocrd" runat="server"></Span></TD>
</TR>
<TR>
        <TD align="middle">\u5DE5\u4F5C\u65E5\u5FD7<BR>
                <BR>
                <input type="button" id="btnGetSub" runat="server" Value="\u5408\u6210\u65E5\u5FD7" style="WIDTH:52px;" size="3"></TD>
        <TD colspan="4" align="left" Nowrap>
                <textarea rows="6" cols="64" id="Dutyrecord" runat="server" NAME="Dutyrecord"></textarea>
                <asp:CustomValidator id="Customvalidator1" runat="server" ErrorMessage="<BR>\u5DE5\u4F5C\u65E5\u5FD7\u4E0D\u8981\u8D85\u8FC72000\u5B57\uFF01" Display="Dynamic" ClientValidationFunction="CheckDutyRecord" ControlToValidate="Dutyrecord"></asp:CustomValidator></TD>
</TR>
<TR>
        <TD align="middle">\u5907&nbsp;&nbsp;&nbsp;&nbsp;\u6CE8</TD>
        <TD colspan="4"><textarea rows="4" cols="64" id="Notes" runat="server" NAME="Notes"></textarea>
                <asp:CustomValidator id="Customvalidator2" runat="server" ErrorMessage="<BR>\u5907\u6CE8\u4E0D\u8981\u8D85\u8FC7120\u5B57\uFF01" Display="Dynamic" ClientValidationFunction="CheckNotes" ControlToValidate="Notes"></asp:CustomValidator></TD>
</TR>
<TR>
        <TD align="middle" colspan="5"><INPUT id="btnGo" type="button" value="\u4E0B \u4E00 \u6B65" name="btnGo" runat="server"></TD>
</TR>
</TABLE>
</html:form>


