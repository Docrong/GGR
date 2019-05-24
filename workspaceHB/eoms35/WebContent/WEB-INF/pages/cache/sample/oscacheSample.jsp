<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/WEB-INF/tlds/oscache.tld" prefix="oscache" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<html:link page="/oscacheSampleAction.do?flush=true">刷新</html:link>
<logic:notEmpty name="flush">
	<%out.println("flush"); %> 
	<cache:flush scope="application" />
</logic:notEmpty>
<li>
最简单的cache标签用法。使用默认的关键字来标识cache内容，超时时间是默认的3600秒
<oscache:cache>
<%=new java.util.Date()%>
</oscache:cache>
</li>
<br>
<br>
<li>用自己指定的字符串标识缓存内容，并且设定作用范围为session。
<oscache:cache key="foobar" scope="session">
<%=new java.util.Date()%>
</oscache:cache> 
</li>
<br>
<br>
<li>
动态设定key值，使用自己指定的time属性设定缓存内容的超时时间，使用动态refresh值决定是否强制内容刷新。因为OSCache使用key值来标识缓存内容，使用相同的key值将会被认为使用相同的的缓存内容，所以使用动态的key值可以自由的根据不同的角色、不同的要求决定使用不同的缓存内容。
<oscache:cache key="foobar" time="30" refresh="false">
<%=new java.util.Date()%>
</oscache:cache> 
</li>

<br>
<br>
<li>
设置time属性为负数使缓存内容永不过期
<oscache:cache time="-1">
<%=new java.util.Date()%>
</oscache:cache>
</li>

<br>
<br>
<li>
使用duration属性设置超期时间
<oscache:cache  duration='PT5M'>
<%=new java.util.Date()%>
</oscache:cache>
</li>
<br>
<br>
<li>
使用mode属性使被缓存的内容不加入给客户的响应中
<oscache:cache  mode='silent'>
<%=new java.util.Date()%>
</oscache:cache>
 
</li>
<p><p>
 <li>
 flush标签
        这个标签用于在运行时刷新缓存。只有运行flush标签后再次访问相关缓存项时才执行刷新。
        属性说明：
 </li>
<table border="1">
	<tr>
		<td>
		scope[all]
		</td>
		<td>
			指定要刷新的范围。可选的值是"application", "session" 和 nul。null（到底是null量还是all呀）值指定刷新所有的缓存（是指使用cache标签的缓存）。			
		</td>
	</tr>
	<tr>
		<td>
		key
		</td>
		<td>
			当指定了scope和key值时，刷新唯一的缓存项。当这个缓存项下次被访问时将被刷新。只指定一个key值而没有指定scope不起作用。
		</td>
	</tr>
	<tr>
		<td>
		group
		</td>
		<td>
			指定一个组时将刷新所有这个组中的缓存项。只指定一个group值而没有指定scope不起作用。
		</td>
	</tr>
	<tr>
		<td>
		pattern
		</td>
		<td>
			任意包含pattern属性指定的值的缓存项都将被刷新。只指定一个pattern值而没有指定scope不起作用。 (注意：OSCache项目组已经不赞成使用pattern这个属性赖刷新缓存，二是鼓励使用具有更好的灵活性和性能的group属性来代替)		
		</td>
	</tr>
	<tr>
		<td>
		language
		</td>
		<td>
			使用ISO-639定义的语言码来发布不同的缓存内容（under an otherwise identical key）。要在一个多语言网站上同一段JSP代码不同用户的参数提供不同的语言时，这个属性会很有用。			
		</td>
	</tr>
	
</table>


<p><p>
<li>
usecached标签
        usecached必须嵌套在cache标签中。
        属性说明：
use
 告诉所在的<cache>标签是否使用已经缓存的内容（缺省为true，使用缓存的内容）。可以使用这个标签来控制缓存。比如使用<frush>标签刷新某个key的缓存，但可以在必要的地方即使这样的强制刷新也仍然使用缓存内容而不刷新。 
 
<oscache:cache>
     <% try { %>
     <%=new java.util.Date()%>
     <% } catch (Exception e) { %>
          <cache:usecached />
     <% } %>
</oscache:cache>
</li>
<p><p>
<li>

addgroup标签
		addgroup:必须嵌套在cache标签中。It allows groups to be dynamically added to a cached block. It is useful when the group(s) a cached block should belong to are unknown until the block is actually rendered. As each group is 'discovered', this tag can be used to add the group to the block's group list.
		group:The name of the group to add the enclosing cache block to.  
 
		
<oscache:cache key="test1">

<oscache:addgroup group="group1" />

<%=new java.util.Date()%>

<oscache:addgroup group="group2" />

<%=new java.util.Date()%>

</oscache:cache>
</li>


 