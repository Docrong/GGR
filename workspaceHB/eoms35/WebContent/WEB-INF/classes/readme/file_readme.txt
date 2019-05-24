文件管理使用说明：

文件管理支持excel的导入及对excel,csv的样式定制导出，文件管理是不对数据库进行操作的，

导入就是将按照excel要求导入的数据进行xml配置，给出存储数据的pojo，
配置fieldName，这时文件管理会帮你组织数据，数据格式为一个map，其中map的key以页码保存，
key所对应的value就是该页码所对应的数据，这里是个list，list中存放的是pojo数据。根据xml配置，
文件管理会将pojo对象，set/getFildName帮你返回map,结果返回给你，你想怎么做都可以了。

导出就是将由开发人员传入map,map的格式为key为页码,所对应的value为本配置pojo的list。
文件管理会根据开发人员配置的xml样式生成excel,csv。

请参考com.boco.eoms.commons.file.sample

1、首先说下excel的导入，先贴个com.boco.eoms.commons.file.sample.FMImportSample.xml

2、在FMImportSample.xml中对应的FMImportSample.java
<?xml version="1.0" encoding="UTF-8"?>
<!--DTD验证-->
<!-- <!DOCTYPE document SYSTEM "E:\projects\boco\eoms3.5\01-Project\EOMS-V3.5\source0.1\web\WEB-INF\classes\com\boco\eoms\commons\file\config\FMImportMapping.dtd">-->
<sheets>
	<!-- 第一页 以"0"起始-->
	<sheet num="0">
		<!-- 第一页的类型，即map.put("第几页",new List<第几页的类型>); -->
		<className>com.boco.eoms.commons.file.sample.FMImportSample</className>
		<column>
			<!-- 从开始行开始读死 以"0"起始 -->
			<startRow>2</startRow>
			<!-- 结束行，当结束行小于开始行时，将开发人员传来的excel中的记录全部导入 -->
			<endRow>-1</endRow>
			<headerMapping>
				<!-- 字段1 col为例数，以"0"起超-->
				<header col="1">
					<!-- 显示名称 -->
					<headerName>备注</headerName>
					<!-- 字段名称 与com.boco.eoms.commons.file.sample.FMImportSample的get/set对应-->
					<fieldName>memo</fieldName>
					<!-- 字段类型 -->
					<type>String</type>
					<!-- 字段长度 -->
					<length>50</length>
					<!-- 是否可以为空 ，当写入不可以为空时，会以这个值进行判断结束行，若为空则认为到行尾-->
					<isNull>true</isNull>
				</header>
				<!-- 第二列，以"0"开始，其实就是第三列-->
				<header col="2">
					<headerName>名称</headerName>
					<fieldName>name</fieldName>
					<type>String</type>
					<length>50</length>
					<isNull>false</isNull>
				</header>
			</headerMapping>
		</column>
		<!--第一页的另一段需要读入的数据,文件管理会自地动将
	    <column>
			<startRow>16</startRow>
			<endRow>20</endRow>
			<headerMapping>
				<header col="2">
					<headerName>备注</headerName>
					<fieldName>memo</fieldName>
					<type>String</type>
					<length>50</length>
					<isNull>true</isNull>
				</header>
				<header col="3">
					<headerName>名称</headerName>
					<fieldName>name</fieldName>
					<type>String</type>
					<length>50</length>
					<isNull>true</isNull>
				</header>
			</headerMapping>
		</column>
	</sheet>


	<!--第二页-->
	<sheet num="1">
		<className>com.boco.eoms.commons.file.sample.FMImportSample</className>
		<column>
			<startRow>2</startRow>
			<endRow>13</endRow>
			<headerMapping>
				<header col="1">
					<headerName>备注</headerName>
					<fieldName>memo</fieldName>
					<type>String</type>
					<length>50</length>
					<isNull>true</isNull>
				</header>				
				<header col="2">
					<headerName>名称</headerName>
					<fieldName>name</fieldName>
					<type>String</type>
					<length>50</length>
					<isNull>true</isNull>
				</header>				
			</headerMapping>
		</column>
	</sheet>
</sheets>

给出com.boco.eoms.commons.file.sample.FMImportSample.java所配的pojo看下


public class FMImportSample {

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 名称
	 */
	private String name;

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

只有两个字段memo,name，与xml配置的fieldName相匹配


对应的再看下com.boco.eoms.commons.file.sample.FMImportSample.xls，这是个excel文件，没办法贴出。

那怎样调用呢，请看下com.boco.eoms.commons.file.test.service.FMImportExcelFileManagerImplTest.java

javadoc+单元测试=最好的文档啦~

贴出片断


public void testImpt() {
		IFMImportFileManager ifm = (IFMImportFileManager) this
				.getBean("FMImportExcelFileManagerImpl");
		Map map = null;
		try {
			map = ifm
					.impt(
							StaticMethod.CLASSPATH_FLAG
									+ "com/boco/eoms/commons/file/sample/FMImportSample.xml",
							StaticMethod.CLASSPATH_FLAG
									+ "com/boco/eoms/commons/file/sample/FMImportSample.xls");
		} catch (FMException e) {
			logger.error(e);
			fail();
		}
		assertNotNull(map);
		// 取第一页
		List page1 = (List) map.get(new Integer(0));
		assertNotNull(page1);
		for (int i = 0; i < page1.size(); i++) {
			FMImportSample fm = (FMImportSample) page1.get(i);
			assertNotNull(fm);
		}

		// 取第二页
		List page2 = (List) map.get(new Integer(1));
		assertNotNull(page2);

		for (int j = 0; j < page2.size(); j++) {
			FMImportSample fm = (FMImportSample) page2.get(j);
			assertNotNull(fm);
		}
	}


OK,数据你取到了，根据业务组织起来就看你的啦~


好，再说下excel,csv的导出

还是先看下com.boco.eoms.commmons.file.sample.FMExportSample.xml吧

<?xml version="1.0" encoding="UTF-8"?>
<!-- DTD 验证 -->
<!-- <!DOCTYPE document SYSTEM "E:\projects\boco\eoms3.5\01-Project\EOMS-V3.5\source0.1\web\WEB-INF\classes\com\boco\eoms\commons\file\config\FMExportMapping.dtd"> -->
<pages>
	<!-- 第1页 以"0"开始，对应sheet ,className为类型，在map中传入以key为页码，new List<com.boco.eoms.TawRmUser>为类型 ,name将写入导出excel的sheeName，-->
	<page num="0" className="com.boco.eoms.TawRmUser" name="页码友一友">
		<!--标题配置-->		
		<titles>
			<!--从第一行（0起始）开始到第二行，15列到19列合并单元格，并在合并单元格中写入宋体，粗体，斜体，30字号，毅色为红色，文本内存为"${area1}区地${year1}年"-->
			<title startRow="1" endRow="2" startCol="15" endCol="19">
				<font family="宋体" weight="bold" style="italic" size="30"
					color="red" text="${area1}区地${year1}年" />
			</title>
			<!--与上一行解释一样-->
			<title startRow="7" endRow="11" startCol="20" endCol="40">
				<font family="宋体" weight="bold" style="italic" size="30" color="red" text="欢迎，欢迎"/>
			</title>
		</titles>
		
		<headers startCol="4" endCol="43" startRow="1" endRow="5">
		<!-- 导出数据 从第4列开始，43列结束，开始1行，结果5行，导入pojo中为getMemo()的数据写入第4列-->
			<header col="4">
				<headerName>备注</headerName>
				<fieldName>memo</fieldName>
				<type>String</type>
				<length>50</length>
				<isNull>true</isNull>
			</header>

			<header col="5">
				<headerName>姓名</headerName>
				<fieldName>name</fieldName>
				<type>String</type>
				<length>50</length>
				<isNull>true</isNull>
			</header>
		</headers>


		<headers startCol="0" endCol="9" startRow="6" endRow="10">
			<header col="4">
				<headerName>备注</headerName>
				<fieldName>memo</fieldName>
				<type>String</type>
				<length>50</length>
				<isNull>true</isNull>
			</header>

			<header col="5">
				<headerName>姓名</headerName>
				<fieldName>name</fieldName>
				<type>String</type>
				<length>50</length>
				<isNull>true</isNull>
			</header>
		</headers>
	</page>

	<!--第二页,以零开始-->
	<page num="1" className="com.boco.eoms.TawRmUser" name="页码2">
		<titles>
			<title startRow="1" endRow="2" startCol="15" endCol="19">
				<font family="宋体" weight="bold" style="italic" size="12"
					color="red" text="${area1}地区${year1}年" />
			</title>
			<title startRow="7" endRow="11" startCol="20" endCol="40">
				<font family="宋体" weight="bold" style="italic" size="12"
					color="red" text="欢迎，欢迎" />
			</title>
		</titles>

		<headers startCol="4" endCol="43" startRow="1" endRow="5">
			<header col="4">
				<headerName>备注</headerName>
				<fieldName>memo</fieldName>
				<type>String</type>
				<length>50</length>
				<isNull>true</isNull>
			</header>

			<header col="5">
				<headerName>姓名</headerName>
				<fieldName>name</fieldName>
				<type>String</type>
				<length>50</length>
				<isNull>true</isNull>
			</header>
		</headers>
	</page>
</pages>


调用呢，参考
export导出
com.boco.eoms.commons.file.test.service.FMExportExcelFileManagerImplTest.java
csv的导出与export导出类似
com.boco.eoms.commons.file.test.service.FMExportCSVFileManagerImplTest.java

贴两段代码

FMExportExcelFileManagerImplTest.java(excel导出)
public void testExport() {
		List list0 = new ArrayList();
		List list1 = new ArrayList();
		for (int i = 0; i < 11; i++) {

			FMExportSample fme = new FMExportSample();
			fme.setMemo("memo" + i);
			fme.setName("name" + i);
			list0.add(fme);
			list1.add(fme);
		}
		IFMExportFileManager efm = (IFMExportFileManager) this
				.getBean("FMExportExcelFileManagerImpl");
		// 模拟数据
		Map<Integer, List> map = new HashMap();
		map.put(new Integer(0), list0);
		map.put(new Integer(1), list1);
		try {
			efm
					.export(
							map,
							StaticMethod.CLASSPATH_FLAG
									+ "com/boco/eoms/commons/file/sample/FMExportSample.xml",
							StaticMethod.CLASSPATH_FLAG
									+ "com/boco/eoms/commons/file/sample/FMExportSample.xls");

		} catch (FMException e) {
			logger.error(e);
			fail();
		}
	}

FMExportCSVFileManagerImplTest.java(csv导出)

public void testExport() {
		List list0 = new ArrayList();
		List list1 = new ArrayList();
		for (int i = 0; i < 11; i++) {

			FMExportSample fme = new FMExportSample();
			fme.setMemo("memo" + i);
			fme.setName("name" + i);
			list0.add(fme);
			list1.add(fme);
		}
		IFMExportFileManager efm = (IFMExportFileManager) this
				.getBean("FMExportCSVFileManagerImpl");
		// 模拟数据
		Map<Integer, List> map = new HashMap();
		map.put(new Integer(0), list0);
		map.put(new Integer(1), list1);
		try {
			efm
					.export(
							map,
							StaticMethod.CLASSPATH_FLAG
									+ "com/boco/eoms/commons/file/sample/FMExportSample.xml",
							StaticMethod.CLASSPATH_FLAG
									+ "com/boco/eoms/commons/file/sample/FMExportSample.csv");

		} catch (FMException e) {
			logger.error(e);
			fail();
		}
	}

运行两个测试用例，在classpath找到com.boco.eoms.commons.file.sample包下，会看到导出的csv,excel两个文件。

classpath下找，不是source下呀~


OK，数据也导出来啦。

再说说基于文件管理扩展

开发人员可以基于文件管理很快的开发出新的导入导出，例：要开发pdf、word的导入导出

这里只需要新增一个类继承
导入基类
com.boco.eoms.commons.file.service.FMImportFileManagerAdpter.java
实现下面方法
public Map<Integer, List> impt(String xmlPath, String filePath)
			throws FMException
			
其中xmlPath为要传入的xmlPath要对应的castor o/r mapping ，写到这才发现封装的有些问题，
xmlPath这里可以直接传给配置文件的对象的。不过这里也算更灵活吧，在export中调用fileconfig
取出对应的o/r mapping对象（对应配置文件的对象），做你想做的操作吧。
filePath为导入文件的路径

导出基类
com.boco.eoms.commons.file.service.FMExportFileManagerAdpter.java

实现下面方法
public void export(Map<Integer, List> map, String xmlPath, String filePath)
			throws FMException ;

map是要导出的数据，
其中xmlPath为要传入的xmlPath要对应的castor o/r mapping ，写到这才发现封装的有些问题，
xmlPath这里可以直接传给配置文件的对象的。不过这里也算更灵活吧，在export中调用fileconfig
取出对应的o/r mapping对象（对应配置文件的对象），做你想做的操作吧。
filePath为生成文件的路径


扩展之后，在你的spring配置文件里

	<bean id="FMImportExcelFileManagerImpl"
		class="com.boco.eoms.commons.file.service.impl.FMImportExcelFileManagerImpl">
		<property name="fmParseXmlManager">
			<ref bean="ParseXmlManagerImpl" />
		</property>
	</bean>
	
	加入如下，现在你只需像FMExportCSVFileManagerImpl.java这样调用就OK了。
	
		IFMExportFileManager efm = (IFMExportFileManager) this
				.getBean("FMExportCSVFileManagerImpl");
		// 模拟数据
		Map<Integer, List> map = new HashMap();
		map.put(new Integer(0), list0);
		map.put(new Integer(1), list1);
		try {
			efm
					.export(
							map,
							StaticMethod.CLASSPATH_FLAG
									+ "com/boco/eoms/commons/file/sample/FMExportSample.xml",
							StaticMethod.CLASSPATH_FLAG
									+ "com/boco/eoms/commons/file/sample/FMExportSample.csv");

		} catch (FMException e) {
			logger.error(e);
			fail();
		}
		
		
总结：其实扩展时如果导入、导出配置文件若使用文件管理的配置文件，这时在其基础上扩展是相当简单的，若新增castor配置文件有点痛苦。
