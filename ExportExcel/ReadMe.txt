1 使用jdk1.5，tomcat6，可以通过访问http://localhost:8080/exportexcel地址测试,ExportExcel.zip为源代码

2在tomcat的context.xml中配置数据库连接池
	<Resource  name="jdbcoracle"   auth="Container"  type="javax.sql.DataSource"
	 factory="org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory"
	maxActive="100"
	maxIdle="10"
	maxWait="10000"
	username="eomsvlz"
	password="Boco1234"
	driverClassName="oracle.jdbc.OracleDriver"
	url="jdbc:oracle:thin:@10.25.0.201:1521:rac"/>
	其中username，password，url需要改成自己的数据库地址。
	
3在tomcat的server.xml中增加
<Context path="/exportexcel"   reloadable="true" />

4ExportExcel中的export-util.xml
filePath配置导出文件缓存路径
part配置多少条数据分配一个线程

5在EOMS增加自定义标签，只需要把sql语句传入标签就可实现数据导出，相关文件在《eoms系统中的自定义标签》这个文件夹下
		1exportexcel文件夹放在eoms35\WebContent下面
		2eoms.tld中增加
		<tag>
			<name>exportexcel</name>
			<tag-class>
				com.boco.eoms.base.webapp.taglib.ExportExcelTag
			</tag-class>
	
			<attribute>
				<name>sql</name>
				<required>false</required>
				<rtexprvalue>true</rtexprvalue>
			</attribute>
		</tag>
		3在eoms35\JavaSource\commons\base\com\boco\eoms\base\webapp\taglib\ExportExcelTag.java中增加ExportExcelTag.java
		4在需要导出的页面增加标签
		<eoms:exportexcel sql="SELECT m.ID as ID, m.SHEETID as 工单号, m.TITLE as 标题, m.SHEETACCEPTLIMIT as 处理时限, m.SHEETCOMPLETELIMIT as 受理时限, m.SENDTIME as 派单时间, m.SENDUSERID as 派单人, m.SENDDEPTID as SENDDEPTID, m.SENDROLEID as SENDROLEID, m.SENDCONTACT as SENDCONTACT, m.ENDTIME as ENDTIME, m.ENDRESULT as ENDRESULT, m.STATUS as STATUS, u.id as 用户ID, u.deptname as deptname, u.email as email, u.userid as userid, u.username as username FROM eomsv35.commonfault_main m,eomsv35.taw_system_user u WHERE  m.sendtime>Sysdate-1200 AND m.senduserid=u.userid" />
		其中引号里的为你的sql语句

6如果导出时出现java.sql.SQLException: ORA-01652: 无法通过 128 (在表空间 EOSMTEMP 中) 扩展 temp 段就通过以下方式解决
		1、增大或者新建一个临时表空间，表空间大小为10G ，如果是RAC，需要指定为ASM存储
		create temporary tablespace test_temp   
		tempfile '/opt/oracle/***.dbf'   
		size 10240m  ; 
		或者在增加现有的临时表空间的大小：
		alter database test_temp '/home/oracle/oradata/trade/temp01.dbf' resize 10240m;
		2、将查询用户指向上面创建或者增加了大小的临时表空间
		
		alter user eomsv35 temporary tablespace test_temp;
		
		3、然后在运行下列查询语句看一下，看是否还报错
		
7如果导出数据出现"<"或者">"会出现EXCEL无法打开的问题，在sql中过滤掉上面的字符

8导出sql需是下面格式
SELECT m.ID as ID, m.SHEETID as 工单号, m.TITLE as 标题, m.SHEETACCEPTLIMIT as 处理时限, m.SHEETCOMPLETELIMIT as 受理时限, m.SENDTIME as 派单时间, m.SENDUSERID as 派单人, m.SENDDEPTID as SENDDEPTID, m.SENDROLEID as SENDROLEID, m.SENDCONTACT as SENDCONTACT, m.ENDTIME as ENDTIME, m.ENDRESULT as ENDRESULT, m.STATUS as STATUS, u.id as 用户ID, u.deptname as deptname, u.email as email, u.userid as userid, u.username as username FROM eomsv35.commonfault_main m,eomsv35.taw_system_user u WHERE  m.sendtime>Sysdate-1200 AND m.senduserid=u.userid
m.SHEETID as 工单号  字段名 as 中文名  的格式

