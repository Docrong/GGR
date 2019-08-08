package com.boco.eoms.extra.supplierkpi.report.statistic.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.extra.supplierkpi.report.common.Parameter;
import com.boco.eoms.extra.supplierkpi.report.common.Report;
import com.boco.eoms.extra.supplierkpi.report.statistic.entity.StatisticEntity;


public class GenXml {
    private static Logger logger = Logger.getLogger(GenXml.class);
    /**
     * 获取统计及信息的方法，这是一个演示方法，具体的条件可由用户传入，fileName为前台提供的将要生成的xml统计报表
     * 展示文件。
     * @author lzp
     * @param sourceName
     * @param fileName
     */
//	public static void genPortletObjectRef(Map reports){
//		Report report=(Report)reports.get("3");
//		String driver=report.getDbInfo().getClassPath();
//		String url=report.getDbInfo().getUrl();
//		String username=report.getDbInfo().getUser();
//		String password=report.getDbInfo().getPassword();
//		Parameter[] parameters=report.getReportInfo().getParameters();
//		Parameter parameter=parameters[0];
//		String fileName=parameter.getValue();
//		String path=report.getReportInfo().getReportPath();
//		path=org.light.portal.Servlet.path+path;
//		System.out.println("realPath:"+path+fileName);
//		Connection conn=new Conn().getConnection(driver, url, username, password);
//		ResultSet rs=null;
//		PreparedStatement ps=null;
//		List returnList=new ArrayList();
//		try{
//			String sql=report.getReportInfo().getSql();
//			ps=conn.prepareStatement(sql);
//			rs=ps.executeQuery();
//			while(rs.next()){
//				String name=rs.getString("icon");
//				name=name==null?"":name.substring(name.lastIndexOf("/")+1,name.lastIndexOf(".")-1);
//				StatisticEntity obj=new StatisticEntity();
//				obj.setName(name);
//				obj.setValue(rs.getInt("mount"));
//				returnList.add(obj);
//			}
////			System.out.println("list size is: "+returnList.size());
//			CreateXml.create(returnList, path+fileName);
//		}catch(SQLException exp){
//			System.out.println("Found a SQLException!");
//			exp.printStackTrace();
//		}finally{
//			Conn.close(rs);
//			Conn.close(ps);
//			Conn.close(conn);			
//		}
//			
//	}

    /**
     * 设置单位
     *
     * @param parameter[] parameters
     * @return unit/false
     */
    private static String getUnit(Parameter[] parameters) {
        String unit = "false";
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.getName().equals("unit")) {
                unit = parameter.getValue();
                return unit;
            }
        }
        return unit;
    }

    /**
     * 设置表头
     *
     * @param parameter[] parameters
     * @return title/false;
     */
    private static String getTitle(Parameter[] parameters) {
        String title = "false";
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.getName().equals("title")) {
                title = parameter.getValue();
                return title;
            }

        }
        return title;
    }

    /**
     * 设置显示区域
     *
     * @param parameter[] parameters
     * @return HashMap:area
     */
    private static Map getArea(Parameter[] parameters) {
        Map area = new HashMap();
        //表宽
        int width = 300;
        //表高
        int height = 300;
        //表起始顶点横坐标
        int x_axis = 0;
        //表起始顶点纵坐标
        int y_axis = 0;
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.getName().equals("width"))
                width = new Integer(parameter.getValue()).intValue();
            if (parameter.getName().equals("height"))
                height = new Integer(parameter.getValue()).intValue();
            if (parameter.getName().equals("x_axis"))
                x_axis = new Integer(parameter.getValue()).intValue();
            if (parameter.getName().equals("y_axis"))
                y_axis = new Integer(parameter.getValue()).intValue();
        }
        area.put("width", new Integer(width));
        area.put("height", new Integer(height));
        area.put("x_axis", new Integer(x_axis));
        area.put("y_axis", new Integer(y_axis));
        return area;
    }

    /**
     * 设置柱状图柱之间的间隔
     *
     * @param parameter[] parameters;
     * @return string column_space
     */
    private static String getSpace(Parameter[] parameters) {
        String column_space = "0";
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.getName().equals("column_space"))
                column_space = parameter.getValue();
        }
        return column_space;
    }

    /**
     * 设置小数点显示位数
     *
     * @param parameter[] parameters;
     * @return string decimal_place
     */
    private static String getDecimal(Parameter[] parameters) {
        String decimal_place = "0";
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.getName().equals("decimal_place")) {
                decimal_place = StaticMethod.null2String("0", parameter.getValue());
                return decimal_place;
            }


        }
        return decimal_place;
    }

    /***** 以下为应用测试函数*****/
    public static void genPortletObjectRef(List fileList, String realPath) {
        System.out.println("开始生成文件！");
        for (int i = 0; i < fileList.size(); i++) {
            System.out.println(i);
            Report report = (Report) fileList.get(i);
            String driver = report.getDbInfo().getClassPath();
            String url = report.getDbInfo().getUrl();
            String username = report.getDbInfo().getUser();
            String password = report.getDbInfo().getPassword();
//			System.out.println("DBInfo:\ndriver:"+driver+"  url:"+url+"\nusername:"+username+"  password:"+password);
            Parameter[] parameters = report.getReportInfo().getParameters();
//			Parameter parameter=parameters[0];
            String fileName = parameters[0].getValue();
            String templateName = parameters[parameters.length - 2].getValue();
            String sourcePath = report.getReportInfo().getReportPath();
            sourcePath = realPath + sourcePath;
            String destPath = report.getReportInfo().getDestPath();
            destPath = realPath + destPath;
//			System.out.println("realPath:"+path+fileName);
            Connection conn = new Conn().getConnection(driver, url, username, password);
            if (conn == null) {
                continue;
            }
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                String sql = report.getReportInfo().getSql();
                System.out.println(sql);
                float max = 0;
                float min = 0;
                Map maps = new HashMap();
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (parameters[1].getValue().equals("false")) {
                    List returnList = new ArrayList();
                    while (rs.next()) {
                        StatisticEntity obj = new StatisticEntity();
                        String name = rs.getString(parameters[2].getValue());

                        //以下这条语句仅适用于测试例子，在实际情况运行时需要根据情况修改
                        if (driver.indexOf("informix") != -1)
                            name = new String((rs.getString(parameters[2].getValue())).getBytes("ISO8859-1"), "gbk");
//						name=name==null?"":name.substring(name.lastIndexOf("/")+1,name.lastIndexOf(".")-1);
//						name=UtilMethod.null2String(name);
                        float value = rs.getInt(parameters[3].getValue());
                        if (value > max) {
                            max = value;
                        }
                        if (value < min) {
                            min = value;
                        }
                        obj.setName(name);
                        obj.setValue(value);
                        returnList.add(obj);
                    }
                    maps.put("Max", new Float(max));
                    maps.put("Min", new Float(min));
                    String title = getTitle(parameters);
                    String unit = getUnit(parameters);
                    String decimal_places = getDecimal(parameters);
                    Map area = getArea(parameters);
                    logger.info("Generate Xml file:  " + fileName + "  start!");
                    CreateXml.createXml(returnList, sourcePath + templateName, destPath + fileName);
                    CreateXml.SetUnits(destPath + fileName, unit);
                    CreateXml.SetMaxAndMin(destPath + fileName, maps);
                    CreateXml.setTitle(destPath + fileName, title);
                    CreateXml.SetArea(destPath + fileName, area);
                    CreateXml.setDecimal(destPath + fileName, decimal_places);
                    if (parameters[parameters.length - 2].getValue().indexOf("Column") != -1) {
                        String column_space = getSpace(parameters);
                        CreateXml.setSpace(destPath + fileName, column_space);
                    }
                    logger.info("Generate Xml file:  " + fileName + "  end!");

                } else {

                    Map blockMap = new HashMap();
                    String lineName = "";
                    while (rs.next()) {
                        StatisticEntity obj = new StatisticEntity();
                        String name = rs.getString(parameters[2].getValue());

                        if (driver.indexOf("informix") != -1)
                            name = new String(rs.getString(parameters[2].getValue()).getBytes("ISO8859-1"), "UTF-8");
                        float value = rs.getInt(parameters[3].getValue());
                        if (value > max) {
                            max = value;
                        }
                        if (value < min) {
                            min = value;
                        }
                        obj.setName(name);
                        obj.setValue(value);
                        lineName = rs.getString(parameters[4].getValue());
                        if (blockMap.containsKey(lineName)) {
                            List list = (List) blockMap.get(lineName);
                            list.add(obj);
                        } else {
                            List list = new ArrayList();
                            list.add(obj);
                            blockMap.put(lineName, list);
                        }
                    }
                    CreateXml.createXmlMulti(blockMap, sourcePath + templateName, destPath + fileName);
                    CreateXml.setLegend(destPath + fileName, parameters[4].getValue());
                    maps.put("Max", new Float(max));
                    maps.put("Min", new Float(min));
                    String title = getTitle(parameters);
                    String unit = getUnit(parameters);
                    String decimal_places = getDecimal(parameters);
                    Map area = getArea(parameters);
                    CreateXml.SetUnits(destPath + fileName, unit);
                    CreateXml.SetMaxAndMin(destPath + fileName, maps);
                    CreateXml.setTitle(destPath + fileName, title);
                    CreateXml.SetArea(destPath + fileName, area);
                    CreateXml.setDecimal(destPath + fileName, decimal_places);
                }

            } catch (Exception exp) {
                System.out.println("Catch a Exception!");
                exp.printStackTrace();

            } finally {
                Conn.close(rs);
                Conn.close(ps);
                Conn.close(conn);
            }
        }
        System.out.println("生成文件结束！");
    }

    public static void genNopStatisticXml(List fileList, String realPath) {
        logger.info("开始生成文件！");
        for (int i = 0; i < fileList.size(); i++) {
            Report report = (Report) fileList.get(i);
            String driver = report.getDbInfo().getClassPath();
            String url = report.getDbInfo().getUrl();
            String username = report.getDbInfo().getUser();
            String password = report.getDbInfo().getPassword();
            Parameter[] parameters = report.getReportInfo().getParameters();
            String fileName = parameters[0].getValue();
            String templateName = parameters[parameters.length - 2].getValue();
            String sourcePath = report.getReportInfo().getReportPath();
            sourcePath = realPath + sourcePath;
            String destPath = report.getReportInfo().getDestPath();
            destPath = realPath + destPath;
            Connection conn = new Conn().getConnection(driver, url, username, password);
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                String sql = report.getReportInfo().getSql();
                float max = 0;
                float min = 0;
                Map maps = new HashMap();
                logger.info(sql);
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (parameters[1].getValue().equals("false")) {
                    List returnList = new ArrayList();
                    while (rs.next()) {
                        StatisticEntity obj = new StatisticEntity();
                        String name = rs.getString(parameters[2].getValue());
                        name = StaticMethod.null2String(name);
                        float value = rs.getFloat(parameters[3].getValue());
                        if (value > max) {
                            max = value;
                        }
                        if (value < min) {
                            min = value;
                        }
                        obj.setName(name);
                        obj.setValue(value);
                        returnList.add(obj);
                    }
                    maps.put("Max", new Float(max));
                    maps.put("Min", new Float(min));
                    String title = getTitle(parameters);
                    String unit = getUnit(parameters);
                    String decimal_places = getDecimal(parameters);
                    Map area = getArea(parameters);
                    logger.info("Generate Xml file:  " + fileName + "  start!");
                    CreateXml.createXml(returnList, sourcePath + templateName, destPath + fileName);
                    CreateXml.SetUnits(destPath + fileName, unit);
                    CreateXml.SetMaxAndMin(destPath + fileName, maps);
                    CreateXml.setTitle(destPath + fileName, title);
                    CreateXml.SetArea(destPath + fileName, area);
                    CreateXml.setDecimal(destPath + fileName, decimal_places);
                    if (parameters[parameters.length - 2].getValue().indexOf("Column") != -1) {
                        String column_space = getSpace(parameters);
                        CreateXml.setSpace(destPath + fileName, column_space);
                    }
                    logger.info("Generate Xml file:  " + fileName + "  end!");

                } else {

                    Map blockMap = new HashMap();
                    String lineName = "";
                    while (rs.next()) {
                        StatisticEntity obj = new StatisticEntity();
                        String name = rs.getString(parameters[2].getValue());
                        name = StaticMethod.null2String(name);
                        int value = rs.getInt(parameters[3].getValue());
                        obj.setName(name);
                        obj.setValue(value);
                        lineName = rs.getString(parameters[4].getValue());
                        if (blockMap.containsKey(lineName)) {
                            List list = (List) blockMap.get(lineName);
                            list.add(obj);
                        } else {
                            List list = new ArrayList();
                            list.add(obj);
                            blockMap.put(lineName, list);
                        }
                        CreateXml.createXmlMulti(blockMap, sourcePath + templateName, destPath + fileName);
                    }
                }

            } catch (Exception exp) {
                System.out.println("Catch a Exception!");
                exp.printStackTrace();
            } finally {
                Conn.close(rs);
                Conn.close(ps);
                Conn.close(conn);
                continue;
            }
        }
        logger.info("生成文件结束！");
    }
}