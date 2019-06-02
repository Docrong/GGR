package com.boco.eoms.commons.statistic.base.anychart.anychartinterface;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.boco.eoms.commons.statistic.base.anychart.bo.Dom4jVisitor;
import com.boco.eoms.commons.statistic.base.anychart.bo.XmlSet;
import com.boco.eoms.commons.statistic.base.anychart.util.ReportTool;
import com.boco.eoms.commons.statistic.base.config.graphic.GraphicReport;

public class AnyChartDrawPic {

	//===================================================Report Interface ==================================================================//
	public static Document DrawPic(List result,GraphicReport gf){
		
		String templatePath = gf.getConfigPath();
		
		Document doc = ReportTool.readDocument(templatePath);
		
		//add by lizhenyou 增加样式处理，处理图形报表的坐标抽
		Dom4jVisitor d4v = new Dom4jVisitor();
		d4v.acceptModal(result, gf);
		doc.accept(d4v);
		
//		System.out.println("==================");
//		String docString = doc.asXML();
//		System.out.println(docString);
//		System.out.println("==================");
		//add end
		
		Element root = doc.getRootElement();
		Element data = null;
		if(root.element("data")==null){
			data = root.addElement("data");
		}else{
			data = root.element("data");
		}
		
		String reporttype = gf.getType();
		
		//按接口规则,转换现有统计报表数据,写入DOC-DATA节点,根据配置中的TYPE选择不同的draw method
		if(reporttype.equals("line")){//线
			
			XmlSet.drawLine(data,result,gf);
			
		}else if(reporttype.equals("column")){//柱
			
			XmlSet.drawColumn(data,result,gf);
			
		}else if(reporttype.equals("pie")){//饼
			
			XmlSet.drawPie(data,result,gf);
			
		}else{//线柱结合
			XmlSet.drawColumnLine(data,result,gf);
		}
		return doc;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
