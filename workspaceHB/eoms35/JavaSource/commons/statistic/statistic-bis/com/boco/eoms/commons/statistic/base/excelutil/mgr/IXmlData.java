package com.boco.eoms.commons.statistic.base.excelutil.mgr;


public interface IXmlData extends IStatExcel{
	
	/**
	 * 最终需要显示的数据模型(xml) 
	 * 下面包括xml个个节点的具体说明
	 * @return
<?xml version="1.0" encoding="UTF-8"?>
<reports>
		<report>
			<!--表头表尾替换的字符串-->
			<display-info>
				<info name="beginTime" value="2009-1-12"/>
				<info name="endTime" value="2009-1-19"/>
				<info name="custom" value="year..."/>
			</display-info>
			
			<!--数据节点-->
			<datas>
				<data><!-- 报表数据 最终显示数据-->
					<field id="s1" id2name="10040" value="北京1" />
					<field id="s2" id2name="10041" value="上海"/>
					<field id="s3" id2name="10044" value="上22"/>
				 	<field id="f1" value="1" />
				 	<field id="f2" value="2" />
				 	<field id="f3" value="3" />
				 	<field id="f4" value="4" />
				 	<!--0到多个field节点 id是该field唯一标识，value是取值-->
		 		</data>
		 	
			 	<data><!-- 报表数据 最终显示数据-->
					<field id="s1" id2name="10043"  value="南京" />
					<field id="s2" id2name="10044" value="太难海"/>
					<field id="s3" id2name="10045" value="上33g"/>
				 	<field id="f1" value="11"/>
				 	<field id="f2" value="22"/>
				 	<field id="f3" value="33"/>
				 	<field id="f4" value="4"/>
				 	<!--0到多个field节点 id是该field唯一标识，value是取值 ,url为点击详细信息连接-->
		 		</data>
				<!-- 0到多个data节点-->
			</datas>
		</report>
	</reports>
	 */
	String getData();
}
