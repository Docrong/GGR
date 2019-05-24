package com.boco.eoms.commons.statistic.base.anychart.bo;

import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.VisitorSupport;

import com.boco.eoms.commons.statistic.base.anychart.util.ReportTool;
import com.boco.eoms.commons.statistic.base.config.graphic.FieldDefine;
import com.boco.eoms.commons.statistic.base.config.graphic.GraphicReport;
import com.boco.eoms.commons.statistic.base.util.Constants;

public class Dom4jVisitor extends VisitorSupport {

	private double lineMaxValue = 0;
	
	private double columnMaxValue = 0;
	
	private int line_lines_step = 1;
	
	private int column_lines_step = 1;
	
	//workspace 的宽度
	private int width = 0;
	
	/**
	 * 默认的坐标轴最大值
	 */
	private static final double DEFAULTMAXVALUE = 10;
	
	/**
	 * 坐标轴增加比例
	 */
	private static final double SCALE = 1.25;
	
	/**
	 * 在显示字段前的字符
	 */
	private String prefix = "";
	
	/**
	 * 在显示字段后的字符（例如15% ，可以配置postfix="%"）
	 */
	private String postfix = "";
	
	/**
	 * 默认的workspace width
	 */
	private static final int DEFAULTWIDTHVALUE = 800;
	
//	private static final int DEFAULTWIDTHSCALE = 80;
	
	/**
	 * 遍历Document的所有节点
	 */
	public void visit(Element element)
	{
		super.visit(element);
		String name = element.getName();
		//grid节点是设置坐标轴的属性
		if("grid".equalsIgnoreCase(name))
		{
			Attribute attributechart = element.attribute("chart");
			String attributeValue = attributechart.getValue();
			Element values = element.element("values");
			if("2DColumn".equalsIgnoreCase(attributeValue))
			{
				values.attribute("lines_step").setValue(String.valueOf(column_lines_step));
				if(values.attribute("end") != null)
				{
					values.attribute("end").setValue(String.valueOf(columnMaxValue));
				}
			}
			else if("2DLine".equalsIgnoreCase(attributeValue))
			{
				values.attribute("lines_step").setValue(String.valueOf(line_lines_step));
				if(values.attribute("end") != null)
				{
					values.attribute("end").setValue(String.valueOf(lineMaxValue));
				}
			}
		}
		if("chart".equalsIgnoreCase(name))
		{
			System.err.println(element.getName());
			
			Attribute attributeType = element.attribute("type");
			Attribute attributemaximum_value = element.attribute("maximum_value");
			if(attributemaximum_value != null)
			{
				String attributeValue = attributeType.getValue();
				if("2DColumn".equalsIgnoreCase(attributeValue))
				{
					attributemaximum_value.setValue(String.valueOf(columnMaxValue));
				}
				else if("2DLine".equalsIgnoreCase(attributeValue))
				{
					attributemaximum_value.setValue(String.valueOf(lineMaxValue));
				}
			}
		}
		
		if("workspace".equalsIgnoreCase(name))
		{
			Attribute attributeType = element.attribute("width");
			if(attributeType != null)
			{
				attributeType.setValue(String.valueOf(width));
			}
			
			
			Element chart_area = element.element("chart_area");
			Attribute attributechart_area = chart_area.attribute("width");
			if(attributechart_area != null)
			{
				attributechart_area.setValue(String.valueOf(width));
			}
			
		}
	}
	
	/**
	 * 遍历Document的所有属性
	 */
	public void visit(Attribute attr)
	{
		super.visit(attr);
		//System.out.println(attr.getName());
	}
	
	/**
	 * 处理图形报表的样式(现在主要是坐标轴的maxValue)
	 * @param resultList
	 * @param gf
	 * @param doc
	 */
	public void acceptModal(List resultList,GraphicReport gf)
	{
		double lmv = getMaxValueInList(resultList, gf,Constants.LINE) * SCALE;
		double cmv = getMaxValueInList(resultList, gf,Constants.COLUMN)* SCALE;
		lineMaxValue = lmv>DEFAULTMAXVALUE?lmv:DEFAULTMAXVALUE;
		columnMaxValue = cmv>DEFAULTMAXVALUE?cmv:DEFAULTMAXVALUE;
		
		line_lines_step = (int)(lineMaxValue/5);
		column_lines_step = (int)(columnMaxValue/5);
		
		int w = resultList.size() * gf.getFieldDefines().length * 20;
		width = w>DEFAULTWIDTHVALUE?w:DEFAULTWIDTHVALUE;
	}
	
	/**
	 * 得到result中显示最大的value值，设置为坐标轴的最大值
	 * @param resultList
	 * @param gf
	 * @return
	 */
	private double getMaxValueInList(List resultList,GraphicReport gf,String type)
	{
		FieldDefine[] FieldDefines = gf.getFieldDefines();
		double maxValue = 0;
		for(int i=0;i<resultList.size();i++)
		{
			Map reMap =(Map) resultList.get(i);
			for(int j=0;j<FieldDefines.length;j++)
			{
				if(type.equalsIgnoreCase(FieldDefines[j].getType()))
				{
					String id = FieldDefines[j].getId();
					double value = Double.parseDouble(String.valueOf(reMap.get(id)));
					if(maxValue<value)
					{
						maxValue = value;
					}
				}
			}
		}
		return maxValue;
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = "commonfault_T_resolve_byuser-columnline20081105033912.xml";
		String path = "E:\\work\\EOMSV3.5R1B2\\source\\EOMS\\eoms35\\WebContent\\statisticfile\\swfs\\file\\" + name;
		
		Document doc = ReportTool.readDocument(path);
		
		List chartList = doc.selectNodes("root/type/chart");
		for(int i=0;i<chartList.size();i++)
		{
			Node chart = (Node)chartList.get(i);
			chart.accept(new Dom4jVisitor());
		}
//		Node workspace = doc.selectSingleNode("root/type/workspace");
		
//		workspace.accept(new Dom4jVisitor());
//		doc.accept(new Dom4jVisitor());
	}
}
