package com.boco.eoms.extra.supplierkpi.report.statistic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.extra.supplierkpi.report.statistic.entity.StatisticEntity;

/**
 * provide methods to generate xml file
 * 
 * @author lzp
 * 
 */
public class CreateXml {
	private static void write(Document document,String fileName) {
		Log log = LogFactory.getLog(CreateXml.class);
		try {
			OutputFormat format=OutputFormat.createPrettyPrint();
			XMLWriter writer=new XMLWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName)),"UTF-8"),format);
			writer.write(document);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			log.error("Can't find the target file!");
			log.error(e);
		}
	}
	private static Document getDocument(String fileName){
		Log log = LogFactory.getLog(CreateXml.class);
		Document document = null;
		SAXReader saxBuilder = null;
		try {
			saxBuilder = new SAXReader();
			document = saxBuilder.read(new InputStreamReader(
					new FileInputStream(fileName),"utf-8"));
		} catch (IOException e) {
			log.error(e);
		} catch (DocumentException e) {
			log.error("Can't get the Document");
			log.error(e);
		}
		return document;
	}
	private static Element getElement(Element rootElement,String elementName){
		Element element=rootElement.element(elementName);
		if (element!=null){
			rootElement.remove(element);
		}
		element=rootElement.addElement(elementName);
		return element;
	}
	/**
	 * 
	 * @param list
	 * @param templateName 模版文件名称及路径
	 * @param destName 生成文件名称及路径
	 */
	public static void createXml(List list, String templateName,String destName){
		DecimalFormat to = new DecimalFormat("0.00");
		
//		System.out.println("FileName is: " + fileName);
//		String templateFolder = templateName.substring(0, templateName.lastIndexOf("/"));
//		System.out.println("The folder is: " + folder);
//		String templateFile=templateName.substring(templateName.lastIndexOf("/")+1,
//				templateName.length());
//		String destFolder= destName.substring(0,destName.lastIndexOf("/"));
//		String dataFile = destName.substring(destName.lastIndexOf("/") + 1,
//				destName.length());
		
//		System.out.println("Data file is: " + dataFile);
//		String inputFileStream =templateFolder + "\\" + templateFile;
//		System.out.println("File: " + fileStream);
		Document document =getDocument(templateName);
		Element root = document.getRootElement();
		Element data = root.addElement("data");
		Element block = data.addElement("block");	
		block.addAttribute("color", "0xDC4343");
		block.addAttribute("border_color", "0xDC4343");
		
		//将3D饼图值转换为百分比值
		float total = 0;
		if (templateName.indexOf("3D-Pie") != -1) {
			for (int j = 0; j < list.size(); j++) {
				StatisticEntity obj = (StatisticEntity) list.get(j);
				total = total + obj.getValue();
			}
		}
		for (int i = 0; i < list.size(); i++) {
			StatisticEntity obj = (StatisticEntity) list.get(i);
			Element set = block.addElement("set");
			String name=obj.getName();			
				name=StaticMethod.null2String(name);
			set.addAttribute("name", name);
			set.addAttribute("argument",obj.getName());
			if (templateName.indexOf("3D-Pie") != -1) {
				set.addAttribute("value", to.format((obj.getValue()/total)*100));
			}
			else {
				set.addAttribute("value", String.valueOf(obj.getValue()));
			}
			String s2 = "宋体";
			set.addAttribute("font", s2);
			String color = "0xffffff";
			switch (i % 16) {
			case 0:
				color = "0x6495ed";
				break;
			case 1:
				color = "0xEE82EE";
				break;
			case 2:
				color = "0xDAA520";
				break;
			case 3:
				color = "0xFF8c00";
				break;
			case 4:
				color = "0xFFEBCD";
				break;
			case 5:
				color = "0xFF00FF";
				break;
			case 6:
				color = "0x9370db";
				break;
			case 7:
				color = "0x00fa9a";
				break;
			case 8:
				color = "0xff4500";
				break;
			case 9:
				color = "0xffd700";
				break;
			case 10:
				color = "0xffff00";
				break;
			case 11:
				color = "0xABA38D";
				break;
			case 12:
				color = "0xC9A971";
				break;
			case 13:
				color = "0xDBAD60";
				break;
			case 14:
				color = "0xEFB14D";
				break;
			case 15:
				color = "0x8b4513";
				break;
			default:
				color = "0xffffff";
				break;
			}
			set.addAttribute("color",color);
			if(templateName.indexOf("2D-Pie") != -1){
				Element background=set.addElement("background");
				background.addAttribute("type", "gradient");
				Element colors=background.addElement("colors");
				Element color1=colors.addElement("color");
				color1.addText("0xFFFFFF");
				Element color2=colors.addElement("color");
				color2.addText(color);
			}
		}
		write(document,destName);
	}
	public static void createXmlMulti(Map blockMap,String templateName,String destName){
		Document document =getDocument(templateName);		
		Element root=document.getRootElement();
		Element type=root.element("type");
		createLegend(type,"");
		Element data=root.addElement("data");
		Set keySet=blockMap.keySet();
		int index=0;
		for(Iterator iterator=keySet.iterator();iterator.hasNext();){
			String color="";
			String blockName=(String)iterator.next();
			Element block=data.addElement("block");
			block.addAttribute("name", blockName);
			switch(index){
			case 0:
				color = "0x6495ed";
				break;
			case 1:
				color = "0xEE82EE";
				break;
			case 2:
				color = "0xDAA520";
				break;
			case 3:
				color = "0xFF8c00";
				break;
			case 4:
				color = "0xFFEBCD";
				break;
			case 5:
				color = "0xFF00FF";
				break;
			case 6:
				color = "0x9370db";
				break;
			case 7:
				color = "0x00fa9a";
				break;
			case 8:
				color = "0xff4500";
				break;
			case 9:
				color = "0xffd700";
				break;
			case 10:
				color = "0xffff00";
				break;
			case 11:
				color = "0xABA38D";
				break;
			case 12:
				color = "0xC9A971";
				break;
			case 13:
				color = "0xDBAD60";
				break;
			case 14:
				color = "0xEFB14D";
				break;
			case 15:
				color = "0x8b4513";
				break;
			default:
				color = "0xffffff";
				break;
			}
			block.addAttribute("color", color);
			block.addAttribute("border_attribute", color);
			List dataList=(List)blockMap.get(blockName);
			for(int i=0;i<dataList.size();i++){
				StatisticEntity obj=(StatisticEntity)dataList.get(i);
				Element set=block.addElement("set");
				String name= obj.getName();
				name=StaticMethod.null2String(obj.getName());
				set.addAttribute("name",name);
				set.addAttribute("argument",obj.getName());
				set.addAttribute("value", String.valueOf(obj.getValue()));
				String s2="宋体";
				set.addAttribute("font", s2);
				String dotColor="0xC9A971";
				set.addAttribute("color", dotColor);
			}
			index++;
		}
		write(document,destName);
	}
/*
 * 		<workspace>
			<chart_area width="300" height="100" deep="0">
				<border enabled="yes" color="0xB54001"/>
			</chart_area>
			<base_area enabled="no"/>
			<name text="boco"/>
			<grid>
				<values>
					<lines color="0xB9B9B8"/>
					<captions>
						<font size="12" type="Tahoma"/>
					</captions>
					<ticks>
						<major_ticks>
							<lines length="5" color="0xFBF6EF" type="outside"/>
						</major_ticks>
					</ticks>
				</values>
				<arguments lines_step="1">
					<lines color="0xB9B9B8"/>
					<captions enabled="no">
						<font size="12" type="Tahoma"/>
					</captions>
					<ticks>
						<major_ticks type="outside">
							<lines length="5" color="0xFBF6EF" type="outside"/>
						</major_ticks>
					</ticks>
				</arguments>
			</grid>

			<x_axis smart="no"/>
			<y_axis smart="yes"/>
		</workspace>
 */
	private static void createWorkspace (Element type){
		Element workspace=type.addElement("workspace");
		Element chart_area=workspace.addElement("char_area");
		chart_area.addAttribute("width", "300");
		chart_area.addAttribute("height", "100");
		chart_area.addAttribute("deep", "0");
		Element border=chart_area.addElement("border");
		border.addAttribute("enabled", "yes");
		border.addAttribute("color", "0xB54001");
		Element base=workspace.addElement("base_area");
		base.addAttribute("enabled", "no");
		Element name=workspace.addElement("name");
		name.addAttribute("text", "  ");
		Element grid=workspace.addElement("grid");
		Element values=grid.addElement("values");
		Element lines=values.addElement("lines");
		lines.addAttribute("color", "0xB9B9B8");
		Element captions=values.addElement("captions");
		Element font=captions.addElement("font");
		font.addAttribute("size", "12");
		font.addAttribute("type", "Tahoma");
		Element ticks=values.addElement("ticks");
		Element major_ticks=ticks.addElement("lines");
		lines.addAttribute("length","5");
		lines.addAttribute("color", "0xFBF6EF");
		lines.addAttribute("type","outside");	
		Element arguments=grid.addElement("arguments");
		arguments.addAttribute("lines_step", "1");
		lines=arguments.addElement("lines");
		lines.addAttribute("length","5");
		lines.addAttribute("color", "0xFBF6EF");
		lines.addAttribute("type", "outside");
		Element x_axis=workspace.addElement("x_axis");
		x_axis.addAttribute("smart","no" );
		Element y_axis=workspace.addElement("y_axis");
		y_axis.addAttribute("smart","yes" );
	}
	private static void createChart(Element type){
		Element chart=type.addElement("chart");
		chart.addAttribute("type", "3DColumn");
		chart.addAttribute("maxmium_value", "35000");
		Element values=chart.addElement("values");
		values.addAttribute("decimal_places", "0");
		values.addAttribute("thousand_separator", ",");
		values.addAttribute("prefix", "");
		Element animation=chart.addElement("animation");
		animation.addAttribute("enabled", "yes");
		Element column_chart=chart.addElement("column_chart");
		column_chart.addAttribute("left_space", "50");
		column_chart.addAttribute("right_space","50");
		column_chart.addAttribute("up_space", "10");
		column_chart.addAttribute("down_space", "10");
	}
	/**
	 * 在报表中显示不同颜色的线或柱分别代表是什么统计数据
	 * @param type
	 */
//<legend enabled="yes" x="350" y="50">
//	<values enabled="no"/>
//	<header names="Times"/>
//	<names width="150"/>
//	<scroller enabled="no"/>
//	<border enabled="no"/>
//	<background enabled="no"/>
//</legend>
	private static void createLegend(Element type,String name){
		
		Element legend=getElement(type,"legend");
		legend.addAttribute("enabled", "yes");
		legend.addAttribute("x", "330");
		legend.addAttribute("y","20");
		Element values=legend.addElement("values");
		values.addAttribute("enabled", "no");
		Element header=legend.addElement("header");
		header.addAttribute("names", StaticMethod.null2String(name));
		Element names=legend.addElement("names");
		names.addAttribute("width", "150");
		names.addAttribute("enabled", "false");
		Element scroller=legend.addElement("scroller");		
		scroller.addAttribute("enabled", "no");		
		Element border=legend.addElement("border");
		border.addAttribute("enabled", "no");
		Element background=legend.addElement("background");
		background.addAttribute("enabled", "no");
		
	}
	/**
	 * 设置报表纵轴最大值，最小值
	 * @param float maxmium,float minimum
	 */
	private static void setMaxAndMin(float max,float min,Element type){
		Element chart=type.element("chart");
		Float max_f = new Float(max);
		Float min_f = new Float(min);
		if (max_f == null) {
			chart.addAttribute("maximum_value",new Float(100).toString());
		}
		else {
			chart.addAttribute("maximum_value",new Float(max).toString());
		}
		if (min_f == null) {
			chart.addAttribute("minimum_value", new Float(0).toString());
		}
		else {
			chart.addAttribute("minimum_value", new Float(min).toString());
		}	
	}
	/**
	 * 设置报表表头
	 * @param String title
	 */
	private static void setTitle(Element type,String title){
		Element workspace=type.element("workspace");
		Element name=workspace.element("name");
		if(name==null){
			name=workspace.addElement("name");
		}
		name.addAttribute("text",StaticMethod.null2String(title));
	}
	/**
	 * 设置数据单位
	 * @param String units
	 */
	private static void setUnits(Element type,String unit){
		Element chart=type.element("chart");
		Element values=chart.element("values");
		if(values==null){
			values=chart.addElement("values");
		}
		values.addAttribute("postfix", StaticMethod.null2String(unit));
	}
	/**
	 * 设置报表显示范围
	 * @param width,height,x_axis,y_axis
	 */
	private static void setArea(Element type,Map area){
		Element workspace=type.element("workspace");
		Element chart_area=workspace.element("chart_area");
		if(area.containsKey("width"))
			chart_area.addAttribute("width", area.get("width").toString());
		if(area.containsKey("height"))
			chart_area.addAttribute("height", area.get("height").toString());
		if(area.containsKey("x_axis"))
			chart_area.addAttribute("x", area.get("x_axis").toString());
		if(area.containsKey("y_axis"))
			chart_area.addAttribute("y", area.get("y_axis").toString());
	}
	/**
	 * 设置柱图报表每根数据柱的间隔
	 * @param int column_space
	 */
	private static void setSpace(Element type,String column_space){
		Element chart=type.element("chart");
		Element column_chart=chart.element("column_chart");
		column_chart.addAttribute("column_space", column_space);
	}
	/**
	 * 设置小数点显示位数
	 * @param int decimal_places 
	 */
	private static void setDecimal(Element type,String decimal_places){
		Element chart=type.element("chart");
		Element values=chart.element("values");
		values.addAttribute("decimal_places", decimal_places);
	}
	public static void setLegend(String fileName,String lineName){
		Document document=getDocument(fileName);
		Element root=document.getRootElement();
		Element type=root.element("type");
		createLegend(type,lineName);
		write(document,fileName);
	}
	public static void setTitle(String fileName,String title){
		if(title.equals("false")){
			title="  ";
		}
		Document document=getDocument(fileName);
		Element root=document.getRootElement();
		Element type=root.element("type");
		setTitle(type,title);
		write(document,fileName);
	}
	public static void SetUnits(String fileName,String unit){
		if(unit.equals("false")){
			return ;
		}
		Document document=getDocument(fileName);
		Element root=document.getRootElement();
		Element type=root.element("type");
		setUnits(type,unit);
		write(document,fileName);
	}
	public static void SetMaxAndMin(String fileName,Map maps){
		float max=((Float)maps.get("Max")).floatValue();
		float min=((Float)maps.get("Min")).floatValue();
		Document document=getDocument(fileName);
		Element root=document.getRootElement();
		Element type=root.element("type");
		setMaxAndMin(max,min,type);
		write(document,fileName);
	}
	public static void SetArea(String fileName,Map area){
		Document document=getDocument(fileName);
		Element root=document.getRootElement();
		Element type=root.element("type");
		setArea(type,area);
		write(document,fileName);
	}
	public static void setSpace(String fileName,String column_space){
		Document document=getDocument(fileName);
		Element root=document.getRootElement();
		Element type=root.element("type");
		setSpace(type,column_space);
		write(document,fileName);
	}
	public static void setDecimal(String fileName,String decimal_places){
		Document document=getDocument(fileName);
		Element root=document.getRootElement();
		Element type=root.element("type");
		setDecimal(type,decimal_places);
		write(document,fileName);
	}
}