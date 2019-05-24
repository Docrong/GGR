package com.boco.eoms.commons.statistic.base.anychart.bo;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import com.boco.eoms.commons.statistic.base.anychart.bean.BlockBean;
import com.boco.eoms.commons.statistic.base.anychart.bean.ReportBean;
import com.boco.eoms.commons.statistic.base.anychart.bean.SetBean;
import com.boco.eoms.commons.statistic.base.anychart.dao.XmlDao;
import com.boco.eoms.commons.statistic.base.anychart.util.ConnectionFactory;
import com.boco.eoms.commons.statistic.base.anychart.util.ReportTool;
import com.boco.eoms.commons.statistic.base.anychart.util.TimeTool;
import com.boco.eoms.commons.statistic.base.config.graphic.FieldDefine;
import com.boco.eoms.commons.statistic.base.config.graphic.GraphicReport;
import com.boco.eoms.commons.statistic.base.config.graphic.SummaryDefine;
import com.boco.eoms.commons.statistic.base.util.StatUtil;
/**
 * 属性设置方法
 * @author tomilee
 */
public class XmlSet {
	
	/**
	 * 解析BLOCKS，得到数据
	 * @param doc
	 * @param report
	 */
	public static void CreateData(Document doc,ReportBean report,HttpServletRequest request){
		Element root = doc.getRootElement();
		Element data = null;
		if(root.element("data")==null){
			data = root.addElement("data");
		}else{
			data = root.element("data");
		}
		List blocks = report.getBlocks();
		for(int i=0;i<blocks.size();i++){
			BlockBean bb = (BlockBean)blocks.get(i);
			
			String type = bb.getType();
			String cycle = bb.getCycle();
					
			String starttime = (String)request.getParameter("starttime");
			String endtime = (String)request.getParameter("endtime");
			String cycletype = (String)request.getParameter("cycletype");
			if(cycletype == null || "".equals(cycletype)){
				cycletype = "month";
			}
			List times = TimeTool.getCycle(starttime, endtime, cycletype);
			//无周期，统一日期格式为yyyy/MM/dd
			starttime = starttime.replaceAll("-", "/");
			endtime = TimeTool.getNextDay(endtime);
			
			if("yes".equals(bb.getReference())){						
				if("line".equals(type)){
					//有周期，单类或单指标线图
					List sqls = bb.getSqls();
					List results = createLineBlocks(sqls,times,cycletype);
					//构建BLOCK节点[block name 赋值可能要修改]
					drawLineBlocks(data,bb,results);
				}else if("multiline".equals(type)){
					//无周期、多类多指标线图
					List sqlss = bb.getSqlss();
					List results = createMultiBlocks(sqlss,starttime,endtime);
					//构建BLOCK节点[block name 赋值可能要修改]
					drawLineBlocks(data,bb,results);
				}else if("singlecolumn".equals(type)){					
					//注意周期判读?
					if("yes".equals(cycle)){
						List sqls = bb.getSqls();											
						List results = createSingleBlock(sqls,times,cycletype);	
						drawSingleColumnBlocks(data,bb,results);
					}else{
						List sqls = bb.getSqls();
						List results = createSingleBlock(starttime,endtime,sqls);
						drawSingleColumnBlocks(data,bb,results);
					}					
				}else if("multicolumn".equals(type)){
					//X坐标-时间周期
					List sqls = bb.getSqls();
					List results = createMultiBlockY(sqls,times,cycletype);
					drawMultiColumnBlocks(data,bb,results);
				}else if("pie".equals(type)){
					//饼图,周期单指标、分周期多指标
					List sqls = bb.getSqls();
					List results = new ArrayList();
					if("yes".equals(cycle)){
						//sql.size() = 1,SQL：1
						results = createLineBlocks(sqls,times,cycletype);
					}else{
						//sql.size() = N,SQL:N
						results = createSingleBlock(starttime,endtime,sqls);
					}
					//总和
					float sum = 0;
					for(int j=0;j<results.size();j++){
						List lst = (List)results.get(j);
						for(int k=0;k<lst.size();k++){
							SetBean setbean = (SetBean)lst.get(k);
							sum = sum + Float.parseFloat(setbean.getValue());
						}						
					}
					//建立新的数据集合SETBEANS
					List rs = new ArrayList();
					for(int j=0;j<results.size();j++){
						List lst = (List)results.get(j);
						for(int k=0;k<lst.size();k++){
							SetBean setbean = (SetBean)lst.get(k);
							float point = Float.parseFloat(setbean.getValue());
							//计算百分比
							DecimalFormat df = new DecimalFormat("0.00");
							String resultvalue = df.format(point/sum*100);
							setbean.setPiecount(setbean.getValue());
							setbean.setValue(resultvalue);
							rs.add(setbean);
						}						
					}
					drawSinglePieBlock(data,bb,rs);
				}else{
					//多柱:多类多指标,一个时间段 list - list - sql
					List sqlss = bb.getSqlss();
					List results = createMultiBlocks(sqlss,starttime,endtime);
					drawMultiColumnBlocks(data,bb,results);
				}				
			}else{//REFERENCE:NO-Line，其他暂不考虑
				
				List sqls = bb.getSqls();
				List sqlss = bb.getSqlss();
				
				List monthers = createMotherBlock(sqls,starttime,endtime);//决定X轴的点
				List children = createMultiBlocks(sqlss,starttime,endtime);//LIST - BLOCK[N] - SETBEAN[N]	决定Lines		
					
				List results = new ArrayList();
				
				if(monthers.size()==1){
					SetBean monther = (SetBean)monthers.get(0);
					String monthervalue = monther.getValue();				
					for(int j=0;j<children.size();j++){
						List temp = new ArrayList();
						List block = (List)children.get(j);
						for(int k=0;k<block.size();k++){
							SetBean child = (SetBean)block.get(k);
							String childrenvalue = child.getValue();
							//计算百分比
							float a = Float.parseFloat(childrenvalue);
							float b = Float.parseFloat(monthervalue);
							DecimalFormat df = new DecimalFormat("0.000");
							String resultvalue = df.format(a/b*100);							
							child.setValue(resultvalue);
							temp.add(child);
						}
						results.add(temp);
					}					
					drawLineBlocks(data,bb,results);
				}else{				
					/**look out 
					 * block map: 
					 * one block:mather[0]/children[0][0],block:mather[1]/children[0][1];
					 * two block:mather[0]/children[1][0],block:mather[1]/children[1][1];
					 * 修正循环，children - mothers
					 */
					for(int k=0;k<children.size();k++){
						List block = (List)children.get(k);
						List temp = new ArrayList();
						for(int j=0;j<block.size();j++){
							SetBean tmpchild = new SetBean();
							
							SetBean mother = (SetBean)monthers.get(j);
							String monthervalue = mother.getValue();
							SetBean child = (SetBean)block.get(j);
							String childvalue = child.getValue();
							
							//计算百分比
							float a = Float.parseFloat(childvalue);
							float b = Float.parseFloat(monthervalue);
							DecimalFormat df = new DecimalFormat("0.000");
							String resultvalue = df.format(a/b*100);							
							
							tmpchild.setValue(resultvalue);
							tmpchild.setName(child.getName());
							tmpchild.setArgument(child.getArgument());
							
							temp.add(tmpchild);
						}
						results.add(temp);
					}
					drawLineBlocks(data,bb,results);
									
				}
			}
		}
		
		//图例手工构建——2DLine-Column,按一定规则
		if(!report.isAuto()){
			List block = data.elements();
			//计数定色彩
			int linenumber = 1;
			int columnnumber = 1;
			boolean isset = false;
			
			for(int i =0 ; i < block.size(); i++){
				
				Element blockelement = (Element)block.get(i);
				String chart_type = blockelement.attribute("chart_type").getValue();
				
				if("2DLine".equals(chart_type)){//one block one color					
					List lines = blockelement.elements("set");
					//设置颜色方法
					String color = ReportTool.getLineColor(linenumber);
					//String color = ReportTool.getColumnColor(linenumber);					
					String name = blockelement.attributeValue("name");
					for(int j=0;j<lines.size();j++){
						//设置颜色方法
						Element lineset = (Element)lines.get(j);
						lineset.addAttribute("color", color);
						if("".equals(name)){//IF BLOCKBEAN'S NAME IS NULL,THEN SET SAMPLENAME WITH SETBEAN'S NAME
							name = lineset.attributeValue("argument");
						}else{
							name = name.replaceAll("@", lineset.attributeValue("argument"));
						}
					}
					linenumber++;
					//添加HASHMAP-List图例
					HashMap hmp = new HashMap();
					hmp.put("color", color);
					hmp.put("sample", name);
					report.setLinesample(hmp);
					
					if(blockelement.attribute("color")==null){
						blockelement.addAttribute("color", color);
					}else{
						blockelement.attribute("color").setValue(color);
					}
					//SET BLOCK NAME
					blockelement.attribute("name").setValue("");
					
				}else if("2DColumn".equals(chart_type)){//one set one color		
					List columns = blockelement.elements("set");				
					for(int j=0;j<columns.size();j++){						
						//设置颜色方法
						String color = ReportTool.getColumnColor(columnnumber);
						//设置颜色方法
						Element columnset = (Element)columns.get(j);
						columnset.addAttribute("color", color);
						columnnumber++;
						if(!isset){
							//添加HASHMAP-List图例
							HashMap hmp = new HashMap();
							String name = columnset.attributeValue("name");
							hmp.put("color", color);
							hmp.put("sample", name);
							report.setColumnsample(hmp);
						}
						//去除SETNAME属性
						columnset.remove(columnset.attribute("name"));
					}
					isset = true;
				}
				columnnumber = 1;
			}
		}
		
	}
	
	//========================================DRAW BLOCKS================================================//
	/**
	 * Pie BLOCK:1
	 */
	private static void drawSinglePieBlock(Element data, BlockBean bb, List rs) {
		Element blockelement = data.addElement("block");
		for(int i=0;i<rs.size();i++){
			SetBean setbean = (SetBean)rs.get(i);
			String color = ReportTool.getPieColor(i);
			
			Element set = blockelement.addElement("set");
			set.addAttribute("value", setbean.getValue());
			set.addAttribute("name", setbean.getName());
			set.addAttribute("url","javascript:alert( \'Count :" + setbean.getPiecount() + "\')");
			//set.addAttribute("color",color);
			
			Element background = set.addElement("background");
			background.addAttribute("type", "gradient");
			Element colors = background.addElement("colors");
			Element color1 = colors.addElement("color");
			color1.addText("0xFFFFFF");
			Element color2 = colors.addElement("color");
			
			color2.addText(color);
			color2.addAttribute("color", color);
		}
	}

	/**
	 * 构建Line BLOCK节点[2.block name 赋值可能要修改]
	 * @param data
	 * @param bb
	 * @param results
	 */
	private static void drawLineBlocks(Element data,BlockBean bb,List results){
		for(int k=0;k<results.size();k++){
			List blst = (List)results.get(k);
			Element blockelement = data.addElement("block");
			ReportTool.addAttribute(blockelement,bb.getAttibute());
			
			for(int j=0;j<blst.size();j++){
				SetBean setbean = (SetBean)blst.get(j);
				Element set = blockelement.addElement("set");
				set.addAttribute("value", setbean.getValue());
				set.addAttribute("argument", setbean.getArgument());
				set.addAttribute("name", setbean.getName());
				
				Attribute blockname;
				//判断BLOCK节点NAME属性
				if(blockelement.attribute("name")!=null){
					blockname = blockelement.attribute("name");
				}else{
					blockelement.addAttribute("name", "");
					blockname = blockelement.attribute("name");
				}
				String nametype = blockname.getValue();
				if("ARGUMENT".equals(nametype)){
					blockname.setValue(setbean.getArgument());
				}else if("NAME".equals(nametype)){
					blockname.setValue(setbean.getName());
				}else{
					blockname.setValue(nametype);
				}
			}
		}
	}
	
	/**
	 * 构建SingleColumn BLOCK节点[block name 赋值可能要修改]
	 * @param data
	 * @param bb
	 * @param results
	 */
	private static void drawSingleColumnBlocks(Element data,BlockBean bb,List results){
		//周期分割_外循环1内循环N,类别分割_外循环N内循环1
		for(int k=0;k<results.size();k++){
			List blst = (List)results.get(k);			
			for(int j=0;j<blst.size();j++){
				Element blockelement = data.addElement("block");
				ReportTool.addAttribute(blockelement,bb.getAttibute());
				SetBean setbean = (SetBean)blst.get(j);
				Element set = blockelement.addElement("set");
				set.addAttribute("value", setbean.getValue());
				set.addAttribute("argument", setbean.getArgument());
				
				Attribute blockname;
				//判断BLOCK节点NAME属性
				if(blockelement.attribute("name")!=null){
					blockname = blockelement.attribute("name");
					blockname.setValue(setbean.getName());
				}else{
					blockelement.addAttribute("name", setbean.getName());
				}					
			}
		}
	}
	
	/**
	 * 构建MultiColumn-X-Y BLOCK节点[1.测试 2.block name 赋值可能要修改]
	 * @param data
	 * @param bb
	 * @param results
	 */
	private static void drawMultiColumnBlocks(Element data,BlockBean bb,List results){
		//都循环N次
		for(int k=0;k<results.size();k++){
			List blst = (List)results.get(k);
			Element blockelement = data.addElement("block");
			for(int j=0;j<blst.size();j++){				
				ReportTool.addAttribute(blockelement,bb.getAttibute());
				SetBean setbean = (SetBean)blst.get(j);
				Element set = blockelement.addElement("set");
				set.addAttribute("value", setbean.getValue());				
				set.addAttribute("argument", setbean.getArgument());				
				
				//判断BLOCK节点NAME属性
				if(blockelement.attribute("name")!=null){
					Attribute blockname = blockelement.attribute("name");
					blockname.setValue(setbean.getName());
					set.addAttribute("name", setbean.getArgument());
				}else{
					blockelement.addAttribute("name", setbean.getName());
					set.addAttribute("name", setbean.getArgument());
				}
				
			}
		}
	}
	
	//========================================GET BLOCKS================================================//
	/**
	 * 周期循环,按类区分生,多周期单线 - 每周期循环一次，得到一个BLOCK
	 * @param sqls
	 * @param times
	 * @param nav
	 * @param cycletype
	 * @return
	 */
	private static List createLineBlocks(List sqls,List times,String cycletype) {
		List blocks = new ArrayList();
		Connection cn = null;		
		try{
			cn = ConnectionFactory.getConnection();
			for(int i=0;i<sqls.size();i++){
				List block = new ArrayList();
				HashMap nav = (HashMap)sqls.get(i);
				for(int j=0;j<times.size();j++){
					HashMap hmp = (HashMap)times.get(j);
					//配置时候必须保证每循环一次只有一个唯一值
					SetBean set = XmlDao.getlineSet(hmp, nav,cycletype,cn);
					block.add(set);
				}
				blocks.add(block);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(cn);			
		}
		return blocks;
	}
	
	/**
	 * 无周期，按类区分,多指标单线
	 * @param sqlss
	 * @param startime
	 * @param endtime
	 * @return
	 */
	private static List createMultiBlocks(List sqlss,String starttime,String endtime){
		List blocks = new ArrayList();
		Connection cn = null;		
		try{
			cn = ConnectionFactory.getConnection();
			for(int i=0;i<sqlss.size();i++){
				List block = new ArrayList();
				List sqls = (List)sqlss.get(i);
				for(int k=0;k<sqls.size();k++){
					HashMap nav = (HashMap)sqls.get(k);
					SetBean set = XmlDao.getMultiSet(starttime,endtime,nav,cn);
					block.add(set);
				}
				blocks.add(block);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(cn);
		}
		return blocks;
	}
	
	
	/**
	 * 周期YES，按周期生成单一指标多个单柱，一周期一柱
	 * @param sqls 单条SQL
	 * @param times
	 * @param sb
	 * @param cycletype
	 * @return
	 */
	private static List createSingleBlock(List sqls, List times,String cycletype) {
		//sqls:只含有一条SQL语句，为通用方便，集中放置于BLOCKBEAN的SQLS，
		List blocks = new ArrayList();
		Connection cn = null;		
		try{
			cn = ConnectionFactory.getConnection();
			for(int i=0; i<sqls.size();i++){
				List sets = new ArrayList();
				HashMap nav = (HashMap)sqls.get(i);
				for(int j=0;j<times.size();j++){
					HashMap hmp = (HashMap)times.get(j);
					//配置时候必须保证每循环一次只有一个唯一值
					SetBean set = XmlDao.getSingleColumnSet(hmp, nav,cycletype,cn);
					sets.add(set);
				}
				blocks.add(sets);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(cn);			
		}
		return blocks;
	}
	
	/**
	 * 一类\指标 多周期柱图
	 * @param sqls
	 * @param times
	 * @param sb
	 * @param cycletype
	 * @return
	 */
	private static List createMultiBlockY(List sqls, List times,String cycletype) {
		List blocks = new ArrayList();
		Connection cn = null;		
		try{
			cn = ConnectionFactory.getConnection();
			for(int i=0; i<sqls.size();i++){
				List sets = new ArrayList();
				HashMap nav = (HashMap)sqls.get(i);
				for(int j=0;j<times.size();j++){
					HashMap hmp = (HashMap)times.get(j);
					//配置时候必须保证每循环一次只有一个唯一值
					SetBean set = XmlDao.getMultiColumnSet(hmp, nav, cycletype,cn);
					sets.add(set);
				}
				blocks.add(sets);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(cn);
		}
		return blocks;
	}
	
	/**
	 * 无周期，按类单类生成单一时间段多个指标单柱
	 * @param starttime
	 * @param endtime
	 * @param sqls 多条SQL
	 * @param sb
	 * @return
	 */
	private static List createSingleBlock(String starttime,String endtime,List sqls){
		List blocks = new ArrayList();
		Connection cn = null;		
		try{
			cn = ConnectionFactory.getConnection();
			for(int i=0; i<sqls.size();i++){
				List sets = new ArrayList();
				HashMap nav = (HashMap)sqls.get(i);
				//配置时候必须保证每循环一次只有一个唯一值
				SetBean set = XmlDao.getSingleColumnSet(starttime,endtime, nav,cn);
				sets.add(set);
				blocks.add(sets);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(cn);			
		}
		return blocks;
	}
	
	/**
	 * 获取分母值 - 某一类总值
	 * @param sqls
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	private static List createMotherBlock(List sqls,String starttime,String endtime){
		List blocks = new ArrayList();
		Connection cn = null;		
		try{
			cn = ConnectionFactory.getConnection();
			for(int i=0;i<sqls.size();i++){			
				HashMap nav = (HashMap)sqls.get(i);
				SetBean set = XmlDao.getMultiSet(starttime,endtime,nav,cn);
				blocks.add(set);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.closeConnection(cn);			
		}
		return blocks;
	}
	
	//========================================DRAW MAP ================================================//
	public static void drawLine(Element data,List results,GraphicReport gf){		
		List summarykeys = getSummarykeys(gf);
		List fieldkeys = getDefinefieldkeys(gf);
		
		List blocks = new ArrayList();
		for(int i = 0;i<results.size();i++){
			List setbeans = new ArrayList();
			Map map = (Map)results.get(i);
			
			String summaryvalue = "";//block name
			for(int j = 0;j<summarykeys.size();j++){
				SummaryDefine summarydefine = (SummaryDefine)summarykeys.get(j);
				String summaryid = (String)map.get(summarydefine.getId());
				String dictType = summarydefine.getDictType();
				
				String summaryName = "";
				if(dictType != null /*|| !"".equalsIgnoreCase(dictType)*/)
				{
					//3.0字典转name
					summaryName = StatUtil.idType2Name(summaryid, dictType,summarydefine.getId2nameService());
				}
				else
				{
					//3.5字典转names
					summaryName = StatUtil.id2Name(summaryid, summarydefine.getId2nameService());
				}
				
				summaryvalue = summaryvalue + summaryName + " ";
			}
			for(int j = 0;j<fieldkeys.size();j++){
				SetBean setbean = new SetBean();
				FieldDefine fd = (FieldDefine)fieldkeys.get(j);
				String fieldvalue = (String)map.get((String)fd.getId());
				String argument = fd.getName();
				//arguement = trans(fieldkeys.get(j));
				setbean.setName(summaryvalue);
				setbean.setArgument(argument);
				setbean.setValue(fieldvalue);
				setbeans.add(setbean);
			}
			blocks.add(setbeans);
		}
		
		//draw picture - blocks
		for(int k=0;k<blocks.size();k++){
			List blst = (List)blocks.get(k);
			Element blockelement = data.addElement("block");			
			for(int j=0;j<blst.size();j++){
				SetBean setbean = (SetBean)blst.get(j);
				Element set = blockelement.addElement("set");
				set.addAttribute("value", setbean.getValue());
				set.addAttribute("name", setbean.getArgument());
				if(blockelement.attribute("name")==null){					
					blockelement.addAttribute("name", setbean.getName());
				}
			}
		}
		
	}
	
	public static void drawColumn(Element data,List results,GraphicReport gf){
		List summarykeys = getSummarykeys(gf);
		List fieldkeys = getDefinefieldkeys(gf);
		List blocks = new ArrayList();
		for(int i = 0;i<results.size();i++){
			List setbeans = new ArrayList();
			Map map = (Map)results.get(i);
			
			String summaryvalue = "";//block name
			for(int j = 0;j<summarykeys.size();j++){
				SummaryDefine summarydefine = (SummaryDefine)summarykeys.get(j);
				String summaryid = (String)map.get(summarydefine.getId());
				String dictType = summarydefine.getDictType();
				
				String summaryName = "";
				String id2names = summarydefine.getId2nameService();
				if(id2names != null)
				{
					if(dictType != null /*|| !"".equalsIgnoreCase(dictType)*/)
					{
						//3.0字典转name
						summaryName = StatUtil.idType2Name(summaryid, dictType,summarydefine.getId2nameService());
					}
					else
					{
						//3.5字典转names
						summaryName = StatUtil.id2Name(summaryid, summarydefine.getId2nameService());
					}
				}
				else
				{
					summaryName = summaryid;
				}
				
				
				summaryvalue = summaryvalue + summaryName + " ";
			}
			for(int j = 0;j<fieldkeys.size();j++){
				SetBean setbean = new SetBean();
				FieldDefine fd = (FieldDefine)fieldkeys.get(j);
				String fieldvalue = (String)map.get((String)fd.getId());
				String argument = fd.getName();
				//arguement = trans(fieldkeys.get(j));
				setbean.setName(summaryvalue);
				setbean.setArgument(argument);
				setbean.setValue(fieldvalue);
				setbeans.add(setbean);
			}
			blocks.add(setbeans);
		}
		//draw column
		for(int k=0;k<blocks.size();k++){
			List blst = (List)blocks.get(k);
			Element blockelement = data.addElement("block");
			for(int j=0;j<blst.size();j++){
				SetBean setbean = (SetBean)blst.get(j);
				Element set = blockelement.addElement("set");
				set.addAttribute("value", setbean.getValue());				
				//set.addAttribute("argument", setbean.getArgument());
				set.addAttribute("name", setbean.getArgument());
				if(blockelement.attribute("name")==null){					
					blockelement.addAttribute("name", setbean.getName());
				}
				//blockelement.addAttribute("name", setbean.getName());				
			}
		}
		
	}
	
	public static void drawPie(Element data,List results,GraphicReport gf){
		List summarykeys = getSummarykeys(gf);
		List fieldkeys = getDefinefieldkeys(gf);
		float sum = 0;//总值
		List blocks = new ArrayList();
		for(int i = 0;i<results.size();i++){
			List setbeans = new ArrayList();
			Map map = (Map)results.get(i);
			
			String summaryvalue = "";//block name
			for(int j = 0;j<summarykeys.size();j++){
				SummaryDefine summarydefine = (SummaryDefine)summarykeys.get(j);
				String summaryid = (String)map.get(summarydefine.getId());
				String dictType = summarydefine.getDictType();
				
				String summaryName = "";
				if(dictType != null /*|| !"".equalsIgnoreCase(dictType)*/)
				{
					//3.0字典转name
					summaryName = StatUtil.idType2Name(summaryid, dictType,summarydefine.getId2nameService());
				}
				else
				{
					//3.5字典转names
					summaryName = StatUtil.id2Name(summaryid, summarydefine.getId2nameService());
				}
				
				summaryvalue = summaryvalue + summaryName + " ";
			}
			for(int j = 0;j<fieldkeys.size();j++){
				SetBean setbean = new SetBean();
				FieldDefine fd = (FieldDefine)fieldkeys.get(j);
				String fieldvalue = (String)map.get((String)fd.getId());
				String argument = fd.getName();
				//String fieldvalue = (String)map.get((String)fieldkeys.get(j));
				sum = sum + Float.parseFloat(fieldvalue);
				//String argument = "";
				//arguement = trans(fieldkeys.get(j));
				if(results.size() > 1){
					setbean.setName(summaryvalue);
				}else{
					setbean.setName(argument);
				}
				setbean.setValue(fieldvalue);
				setbeans.add(setbean);
			}
			blocks.add(setbeans);
		}
		
		Element blockelement = data.addElement("block");
		for(int j=0;j<blocks.size();j++){
			List rs = (List)blocks.get(j);
			for(int i=0;i<rs.size();i++){
				SetBean setbean = (SetBean)rs.get(i);
				String color = ReportTool.getPieColor(i);
				
				Element set = blockelement.addElement("set");
				
				//计算百分比
				float a = Float.parseFloat(setbean.getValue());
				DecimalFormat df = new DecimalFormat("0.00");				
				String resultvalue = df.format(a/sum*100);
				
				set.addAttribute("value",resultvalue);
				set.addAttribute("name", setbean.getName());
				set.addAttribute("url","javascript:alert( \'Count :" + setbean.getValue() + "\')");
				
				Element background = set.addElement("background");
				background.addAttribute("type", "gradient");
				Element colors = background.addElement("colors");
				Element color1 = colors.addElement("color");
				color1.addText("0xFFFFFF");
				Element color2 = colors.addElement("color");
				
				color2.addText(color);
				color2.addAttribute("color", color);
			}
		}
	}
	
	/**
	 * 线柱结合
	 * @param data
	 * @param results
	 * @param gf
	 */
	public static void drawColumnLine(Element data,List results,GraphicReport gf){
		List summarykeys = getSummarykeys(gf);
		List fieldkeys = getDefinefieldkeys(gf);
		List columnblocks = new ArrayList();
		List lineblocks = new ArrayList();//要做2次处理
		
		//数据分离（COLUMN/LINE）
		for(int i = 0;i<results.size();i++){
			List setbeans = new ArrayList();//colunmblock
			List linetemp = new ArrayList();//lineblock
			Map map = (Map)results.get(i);		
			String summaryvalue = "";//block name
			for(int j = 0;j<summarykeys.size();j++){
				SummaryDefine summarydefine = (SummaryDefine)summarykeys.get(j);
				String summaryid = (String)map.get(summarydefine.getId());
				String dictType = summarydefine.getDictType();
				
				String summaryName = "";
				if(dictType != null  /* || !"".equalsIgnoreCase(dictType)*/)
				{
					//3.0字典转name
					summaryName = StatUtil.idType2Name(summaryid, dictType,summarydefine.getId2nameService());
				}
				else
				{
					//3.5字典转names
					summaryName = StatUtil.id2Name(summaryid, summarydefine.getId2nameService());
				}
				
				summaryvalue = summaryvalue + summaryName + " ";
			}
			for(int j = 0;j<fieldkeys.size();j++){
				SetBean setbean = new SetBean();
				FieldDefine fd = (FieldDefine)fieldkeys.get(j);
				String fieldvalue = (String)map.get((String)fd.getId());
				String argument = fd.getName();				
				setbean.setName(summaryvalue);
				setbean.setArgument(argument);
				setbean.setValue(fieldvalue);
				if("column".equals(fd.getType())){
					setbeans.add(setbean);
				}else{
					Map temp = new HashMap();
					setbean.setFdid(fd.getId());
					temp.put(fd.getId(), setbean);
					linetemp.add(temp);
					//linetemp.add(setbean);
				}
			}
			columnblocks.add(setbeans);
			if(linetemp.size()>=1){
				lineblocks.add(linetemp);
			}
		}
		
		//lineblock2次处理
		List linefd = new ArrayList();
		if(lineblocks.size()>=1){
			List linetemp = (List)lineblocks.get(0);
			for(int j=0;j<linetemp.size();j++){
				Map temp = (HashMap) linetemp.get(j);
				Set tempset =  temp.keySet();
				Iterator iter = tempset.iterator();
				while(iter.hasNext()){
					String fdid = (String)iter.next();
					linefd.add(fdid);
				}
			}
		}
		
		//draw line
		for(int i=0;i<linefd.size();i++){
			String fdid = (String)linefd.get(i);
			Element blockelement = data.addElement("block");
			blockelement.addAttribute("chart_type", "2DLine");
			for(int j=0;j<lineblocks.size();j++){
				List linetemp = (List)lineblocks.get(j);
				for(int k=0;k<linetemp.size();k++){
					Map temp = (HashMap) linetemp.get(k);
					if(temp.get(fdid)!=null){
						SetBean setbean = (SetBean)temp.get(fdid);
						Element set = blockelement.addElement("set");
						set.addAttribute("value", setbean.getValue());
						set.addAttribute("name", setbean.getName());
						if(blockelement.attribute("name")==null){					
							blockelement.addAttribute("name", setbean.getArgument());
						}
					}
				}
			}
		}
		
		
		//draw column
		for(int k=0;k<columnblocks.size();k++){
			List blst = (List)columnblocks.get(k);
			Element blockelement = data.addElement("block");
			blockelement.addAttribute("chart_type", "2DColumn");
			for(int j=0;j<blst.size();j++){
				SetBean setbean = (SetBean)blst.get(j);
				Element set = blockelement.addElement("set");
				set.addAttribute("value", setbean.getValue());
				set.addAttribute("name", setbean.getArgument());
				if(blockelement.attribute("name")==null){					
					blockelement.addAttribute("name", setbean.getName());
				}		
			}
		}
	}
	
	/**
	 * 获取线图图例
	 * @param doc
	 * @return
	 */
	public static List getLineSimple(Document doc){
		List linesample = new ArrayList();
		Element root = doc.getRootElement();
		Element data = root.element("data");
		List blocks = data.elements();
		int linenumber = 1;
		for(int i=0;i<blocks.size();i++){
			Element blockelement = (Element)blocks.get(i);
			String chart_type = blockelement.attribute("chart_type").getValue();
			
			if("2DLine".equals(chart_type)){//one block one color					
				List lines = blockelement.elements("set");
				//设置颜色方法
				String color = ReportTool.getLineColor(linenumber);				
				String name = blockelement.attributeValue("name");
				for(int j=0;j<lines.size();j++){
					//设置颜色方法
					Element lineset = (Element)lines.get(j);
					lineset.addAttribute("color", color);
				}				
				if(blockelement.attribute("color")==null){
					blockelement.addAttribute("color", color);
				}else{
					blockelement.attribute("color").setValue(color);
				}				
				//添加HASHMAP-List图例
				HashMap hmp = new HashMap();
				hmp.put("color", color);
				hmp.put("sample", name);
				linesample.add(hmp);
				linenumber++;
				//SET BLOCK NAME
				blockelement.attribute("name").setValue("");			
			}
		}
		return linesample;
	}
	
	/**
	 * 获取柱图图例
	 * @param doc
	 * @return
	 */
	public static List getColumnSimple(Document doc){
		List columnsample = new ArrayList();
		Element root = doc.getRootElement();
		Element data = root.element("data");
		List blocks = data.elements();
		int columnnumber = 1;
		boolean isset = false;
		for(int i=0;i<blocks.size();i++){
			Element blockelement = (Element)blocks.get(i);
			String chart_type = blockelement.attribute("chart_type").getValue();			
			if("2DColumn".equals(chart_type)){//one set one color		
				List columns = blockelement.elements("set");				
				for(int j=0;j<columns.size();j++){						
					//设置颜色方法
					String color = ReportTool.getColumnColor(columnnumber);
					//设置颜色方法
					Element columnset = (Element)columns.get(j);
					columnset.addAttribute("color", color);
					columnnumber++;
					if(!isset){
						//添加HASHMAP-List图例
						HashMap hmp = new HashMap();
						String name = columnset.attributeValue("name");
						hmp.put("color", color);
						hmp.put("sample", name);
						columnsample.add(hmp);
					}
					//去除SETNAME属性
//					if(columnset.attribute("name")!=null)
//					columnset.remove(columnset.attribute("name"));
					columnset.attribute("name").setValue("");
				}
				isset = true;
			}
			columnnumber = 1;
		}
		return columnsample;
	}
	
	/**
	 * @param gf
	 * @return
	 */
	private static List getSummarykeys(GraphicReport gf){
		SummaryDefine[] summays = gf.getSummaryDefines();	
		List summarykeys = new ArrayList();	
		for(int i=0;i<summays.length;i++){
			summarykeys.add(summays[i]);
		}	
		return summarykeys;
	}
	
	/**
	 * @param gf
	 * @return
	 */
	private static List getDefinefieldkeys(GraphicReport gf){
		FieldDefine[] querys = gf.getFieldDefines();
		List fieldkeys = new ArrayList();
		for(int i=0;i<querys.length;i++){
			FieldDefine querydefine = querys[i];
			//String key = querydefine.getId();
			fieldkeys.add(querydefine);
		}
		return fieldkeys;
	}
}
