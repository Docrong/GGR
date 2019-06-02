缓存组件使用方法：

缓存组件主要包括：
1、oscache oscache主要用作jsp上的缓存，缓存jsp上的某一部分。
2、ehcache 主要作method及hibernate二级缓存。

描述：cache管理，利用spring ioc实现castor的文件配置，实现与平台解藕并供其他组件调用，使用spring aop方式对ehcache进行封装，实现配置分组缓存，进行ehcache统计信息，缓存组件、对象刷新。并对其进行监听，使组件缓存添加缓存，读取缓存，刷新缓存等得到监听。支持配置刷新方法，即：A方法需要缓存，但A方法添加数据即要更新缓存，实现这种需求只需要配置几行。oscache对jsp进行缓存。


1、oscache 配置及使用,更详细的使用请参考官方网站

当你要缓存*.jsp时，需要加入filter，本示例未加入filter
1)web.xml中新增filter

	<filter>
		<filter-name>cacheFilter</filter-name>
		<filter-class>
			com.opensymphony.oscache.web.filter.CacheFilter
		</filter-class>
		<init-param>
			<param-name>time</param-name>
			<param-value>600</param-value>
		</init-param>
		<init-param>
			<param-name>scope</param-name>
			<param-value>session</param-value>
		</init-param>
	</filter>

	<filter-mapping>
		<filter-name>cacheFilter</filter-name>
		<url-pattern>*.jsp</url-pattern>
	</filter-mapping>

2)web.xml中新增taglib

	<taglib>
		<taglib-uri>/WEB-INF/tlds/oscache.tld</taglib-uri>
		<taglib-location>/WEB-INF/tlds/oscache.tld</taglib-location>
	</taglib>

3)classpath 下新增oscache.properties配置文件

4)将oscache.jar放到lib下。

5)jsp按如下方式写，sample页面请见：http://localhost:8080/eoms/cache/oscacheSampleAction.html
	


访问jsp(web-inf/pages/cache/sample/oscacheSample.jsp)如下：

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/WEB-INF/tlds/oscache.tld" prefix="oscache" %>


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
<cache:flush scope="session" />
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




2、ehcache 主要作method及hibernate二级缓存。

1)组件注册
将cache.jar copy到web-inf/lib下，及config/applicationContext-ehcache.xml注册到config/applicationContext-all.xml中。若系统未使用spring，需在web.xml中新增如下：




	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
			classpath:config/applicationContext-all.xml,/WEB-INF/security.xml
		</param-value>
	</context-param>

	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

2)在classpath下的ehcache.xml中注册一组件缓存

	
 	<!--缺省缓存配置。CacheManager 会把这些配置应用到程序中。
		
		
		下列属性是 defaultCache 必须的：
		
		maxInMemory           - 设定内存中创建对象的最大值。
		
		eternal                        - 设置元素（译注：内存中对象）是否永久驻留。如果是，将忽略超
		
		时限制且元素永不消亡。
		
		timeToIdleSeconds  - 设置某个元素消亡前的停顿时间。
		
		也就是在一个元素消亡之前，两次访问时间的最大时间间隔值。
		
		这只能在元素不是永久驻留时有效（译注：如果对象永恒不灭，则
		
		设置该属性也无用）。
		
		如果该值是 0 就意味着元素可以停顿无穷长的时间。
		
		timeToLiveSeconds - 为元素设置消亡前的生存时间。
		
		也就是一个元素从构建到消亡的最大时间间隔值。
		
		这只能在元素不是永久驻留时有效。
		
		overflowToDisk        - 设置当内存中缓存达到 maxInMemory 限制时元素是否可写到磁盘
		上。
		
	-->

	<cache name="com.boco.eoms.DEMO_CACHE" maxElementsInMemory="10001"
		eternal="true" timeToIdleSeconds="600" timeToLiveSeconds="600"
		overflowToDisk="true">
		<cacheEventListenerFactory
			class="com.boco.eoms.cache.event.EHCacheEventListenerFactory"
			properties="key=123,key1=321" />
	</cache>

	cache name为组件名称，配置根据组件要求配置，若需要监控缓存的添加，删除操作等，需实现Listener配置.若需要配置集群需另配置Listener
	以上述配置为例,cacheName为com.boco.eoms.DEMO_CACHE




3)在config包下新建组件缓存配置如：config/applicationContext-ehcache-demo.xml


	新建config/applicationContext-ehcache-组件名.xml

	在config/applicationContext-ehcache.xml中注册缓存组件,配置如下(在注释处新增）：

	<bean id="ApplicationCacheMgr"
		class="com.boco.eoms.commons.cache.application.ApplicationCacheMgr">
		<property name="caches">
			<list>
				<!-- 在这里添加模块名称，与ehcache.xml中的cacheName相同 -->
				<value>com.boco.eoms.DEMO_CACHE</value>
				<!--新增在这，组件名就是ehcache.xml中配置的cacheName-->
				<value>组件名</value>
			</list>
		</property>

		<property name="cacheManager">
			<ref local="appCacheManager" />
		</property>
	</bean>

	<!-- demo组件 -->
	<import resource="classpath:config/applicationContext-ehcache-demo.xml" />
	<!-- 刚刚新增的applicationContext-ehcache-组件名.xml-->
	<import resource="classpath:config/applicationContext-ehcache-组件名.xml" />



4)配置刚刚新增config/applicationContext-ehcache-组件名.xml，如下配置（根据注释配置）

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN"
    "http://www.springframework.org/dtd/spring-beans.dtd">
<beans>
	<!-- beanId 为“组件名cache”-->
	<bean id="demoCache"
		class="org.springframework.cache.ehcache.EhCacheFactoryBean">
		<property name="cacheManager">
			<ref bean="appCacheManager" />
		</property>		
		<property name="cacheName">
			<!--组件名，在ehcache.xml中配置的cacheName-->
			<value>com.boco.eoms.DEMO_CACHE</value>
		</property>
		<!-- 若ehcache.xml没有chacheName相关配置，则使用下面配置 -->
		<property name="maxElementsInMemory">
			<value>100001</value>
		</property>
		<property name="overflowToDisk">
			<value>true</value>
		</property>
		<property name="eternal">
			<value>true</value>
		</property>
		<property name="timeToLive">
			<value>600</value>
		</property>
		<property name="timeToIdle">
			<value>600</value>
		</property>
		<property name="diskPersistent">
			<value>false</value>
		</property>
		<property name="diskExpiryThreadIntervalSeconds">
			<value>120</value>
		</property>

	</bean>
	<!--beanId为“组件名CacheInterceptor”-->
	<bean id="demoCacheInterceptor"
		class="com.boco.eoms.commons.cache.support.MethodCacheInterceptor">
		<property name="cache">
			<!--刚刚配置的bean “组件名Cache” -->
			<ref local="demoCache" />
		</property>
	</bean>
	<!-- beanId为“组件名CachePointCut"-->
	<bean id="demoCachePointCut"
		class="org.springframework.aop.support.RegexpMethodPointcutAdvisor">
		<property name="advice">
			<!--刚刚配置的bean “组件名CacheInterceptor”-->
			<ref local="demoCacheInterceptor" />
		</property>
		<property name="patterns">
			<list>
				<!--
					<value>.*methodOne</value>
					<value>.*methodTwo</value>
				-->
				<!--要缓存的方法名称-->
				<value>.*getDate</value>
				<value>.*getRandom</value>
			</list>
		</property>
	</bean>

	<!-- 组件名demoBaseCacheProxy -->
	<bean id="demoBaseCacheProxy"
		class="org.springframework.aop.framework.ProxyFactoryBean"
		abstract="true">
		<property name="interceptorNames">
			<list>
				<!-- 上面配的 beanId为“组件名CachePointCut"-->
				<value>demoCachePointCut</value>
				<!-- 事务控制，不需改动 -->
				<value>txInterceptor</value>
			</list>
		</property>
	</bean>


	<!-- 要缓存的类及demoCachePointCut的com.boco.eoms.commons.cache.sample.DemoBean的getDate、getRandom方法-->
	<bean id="DemoBean" parent="demoBaseCacheProxy">
		<property name="target">
			<bean class="com.boco.eoms.commons.cache.sample.DemoBean" />
		</property>
	</bean>


	<!-- 自动更新缓存配置，即如下配置指：addDate方法执行后，将刷新组件中com.boco.eoms.commons.cache.sample.DemoBean.getDate key值，
		即再调用com.boco.eoms.commons.cache.sample.DemoBean.getDate方法将被更新-->
	<bean id="demoFlushCachePointCut"
		class="org.springframework.aop.support.RegexpMethodPointcutAdvisor">
		<property name="advice">
			<ref local="demoFlushCacheInterceptor" />
		</property>
		<property name="patterns">
			<list>
				<!--
					<value>.*methodOne</value>
					<value>.*methodTwo</value>
				-->
				<!--addDate方法执行将更新com.boco.eoms.commons.cache.sample.DemoBean.getDate方法的缓存-->
				<value>.*addDate</value>
			</list>
		</property>
	</bean>
	<!--组件名FlushCacheInterceptor-->
	<bean id="demoFlushCacheInterceptor"
		class="com.boco.eoms.commons.cache.support.MethodFlushCacheInterceptor">
		<property name="cache">
			<ref local="demoCache" />
		</property>
		<property name="method">
			<props>
				<!--配置当com.boco.eoms.commons.cache.sample.DemoBean.addDate方法执行，
				将更新缓存(cacheKey)com.boco.eoms.commons.cache.sample.DemoBean.getDate
				cacheKey为刚刚缓存配的DemoBean中的target值com.boco.eoms.commons.cache.sample.DemoBean
				加上方法名称getDate即得cacheKey=com.boco.eoms.commons.cache.sample.DemoBean.getDate-->
				
				<prop
					key="com.boco.eoms.commons.cache.sample.DemoBean.addDate">
					com.boco.eoms.commons.cache.sample.DemoBean.getDate
				</prop>
			</props>
		</property>
	</bean>
	<!--组件名BaseFlushCacheProxy-->
	<bean id="demoBaseFlushCacheProxy"
		class="org.springframework.aop.framework.ProxyFactoryBean"
		abstract="true">
		<property name="interceptorNames">
			<!-- 上面配的 组件名FlushCacheInterceptor-->
			<list>
				<value>demoFlushCachePointCut</value>
				<!-- 事务控制，不需改动 -->
				<value>txInterceptor</value>
			</list>
		</property>
	</bean>
	<!-- 调用DemoFlushBean的addDate方法时，即更新缓存cacheKey=com.boco.eoms.commons.cache.sample.DemoBean.getDate-->
	<bean id="DemoFlushBean" parent="demoBaseFlushCacheProxy">
		<property name="target">
			<bean class="com.boco.eoms.commons.cache.sample.DemoBean" />
		</property>
	</bean>
</beans>

在调用时，可参看测试用例DemoBeanTest.java

5)将web相关东西copy

将web.xml中新增struts-config-cache.xml加入，如：

		<init-param>
			<param-name>config/cache</param-name>
			<param-value>/WEB-INF/struts-config-cache.xml</param-value>
		</init-param>

		加入到servlet Action中。

将cache文件夹copy至web-inf/pages/下。

6)访问http://localhost:8080/eoms/cache/applicationCacheMgrAction.html?method=listCache进行cache管理

7)调用ApplicationContextHolder.getInstance().getBean("ApplicationCacheMgr")调用管理方法。

	<bean id="ApplicationCacheMgr"
		class="com.boco.eoms.commons.cache.application.ApplicationCacheMgr">
		<property name="caches">
			<list>
				<!-- 在这里添加模块名称，与ehcache.xml中的cacheName相同 -->
				<value>com.boco.eoms.DEMO_CACHE</value>
			</list>
		</property>

		<property name="cacheManager">
			<ref local="appCacheManager" />
		</property>
	</bean>


OK,头大了吧~
	












