package com.boco.eoms.commons.statistic.base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.SimpleNode;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.statistic.base.config.model.ConditionParam;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.config.model.SummaryDefine;
import com.boco.eoms.commons.statistic.base.excelutil.mgr.impl.DyTableInfo;
import com.boco.eoms.commons.statistic.base.exception.Id2NameStatException;
import com.boco.eoms.commons.statistic.base.mgr.ID2NameManager;
import com.boco.eoms.commons.statistic.base.mgr.IStatManager;
import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;


public class StatUtil {

    private static Logger logger = Logger.getLogger(StatUtil.class);

    public static Object CloneObject(Object object) {
        if (object == null)
            return null;
        Object result = null;
        ByteArrayOutputStream out = null;
        ObjectOutputStream write = null;
        InputStream in = null;
        ObjectInputStream read = null;
        try {
            out = new ByteArrayOutputStream();
            write = new ObjectOutputStream(out);
            write.writeObject(object);

            in = new ByteArrayInputStream(out.toByteArray());
            read = new ObjectInputStream(in);

            result = read.readObject();

            if (result == object) {
                result = null;
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            result = null;
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            result = null;
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (write != null) {
                    write.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (read != null) {
                    read.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * ognl 表达式
     *
     * @param context
     * @param expressionString
     * @param root
     * @return
     * @throws OgnlException
     */
    public static Object getOgnlValue(OgnlContext context, String expressionString, Object root) throws OgnlException {
        SimpleNode expression = (SimpleNode) Ognl.parseExpression(expressionString);

        if (context == null) {
            context = (OgnlContext) Ognl.createDefaultContext(null);
        }

        return Ognl.getValue(expression, context, root);
    }

    public static Object getExpressionResult(Map data, final String expression, Object defaultObj) {
        logger.info("表达式为： " + expression + " 数据Map为：" + data);
        Object ret = null;
        OgnlContext context = new OgnlContext();
//		Map inputMap = new HashMap();
//		inputMap.put("f1", "23");
//		inputMap.put("f2", "24");

        context.putAll(data);

// 		final String expression = "(f1 / f2).(#this>80?80:#this<20?20:#this)";
//		final String expression = "((#this.f1+'D') / f2)";
//		final String expression = "((f1)*100 /f2)";
//		final String expression = "@java.lang.Math@round(new Double(f1)*100 /f2)";
// 		final String expression = "f1";

        Object parseExpression;
        try {

//			System.out.println("表达式为： " + expression);
            parseExpression = Ognl.parseExpression(expression.trim());
            ret = Ognl.getValue(parseExpression, context);
            if (ret == null) {
                if (defaultObj == null)
                    defaultObj = "";
                ret = defaultObj;
            }

            logger.info("ok1 re=" + ret + "," + ret.getClass().getName());
//			System.out.println("ok1 re=" + ret + "," + ret.getClass().getName());

            if (ret.getClass().equals(Float.class)) {

                logger.info("right type.");
//				System.out.println("right type.");
//				ret=new Integer( java.lang.Math.round(((Float)ret).floatValue()));
                ret = ret.toString();
            } else if (ret.getClass().equals(Double.class)) {
                //ret=new Long(java.lang.Math.round(((Double)ret).doubleValue()));
//				ret=new Double(java.lang.Math.round(((Double)ret).doubleValue()));
                java.text.DecimalFormat df = new DecimalFormat("#.####");
                ret = df.format(ret);
                ret = ret.toString();
            }

            if (ret.getClass().equals(Long.class)) {
                ret = ((Long) ret).toString();
            } else if (ret.getClass().equals(Integer.class)) {
                ret = ((Integer) ret).toString();

            } else if (ret.getClass().equals(BigInteger.class)) {

            } else if (ret.getClass().equals(BigInteger.class)) {

            }


            if (ret.getClass().equals(String.class)) {
                //delete start
                //System.out.println("ok re=" + ret + "," + ret.getClass().getName());
                //return ret;
                //delete end

                //mod by lizhenyou 小数点后保留2位
                String retString = String.valueOf(ret);
                retString = retString.replaceAll("NaN", "00.00");

                //无穷大
                if (retString.equalsIgnoreCase("9223372036854775807")) {
                    retString = String.valueOf(defaultObj);
                }
                if (retString.equalsIgnoreCase("9223372036854775807%")) {
                    retString = String.valueOf(defaultObj) + "%";
                }

                int index = retString.indexOf("%");
                if (index != -1) {
                    retString = retString.substring(0, index);
                    DecimalFormat d = (DecimalFormat) NumberFormat.getPercentInstance();
                    if (retString.length() == 4) {
                        d.applyPattern("0.00%");
                    } else {
                        d.applyPattern("00.00%");
                    }
                    retString = d.format(Double.parseDouble(retString) / 100);

                    //为了避免分母为0的情况
                    if ("∞%".equalsIgnoreCase(retString)) {
                        retString = "00.00%";
                    }
                }

                //为了避免分母为0的情况"9223372036854775807".equalsIgnoreCase(retString) ||
                //长度为32位的情况是id2name字符串

//				 if(retString.length()>15 && isNumber(retString))
//				 {
//					 retString = "0";
//				 }

                return retString;//ret;


            } else return defaultObj;// throw new Exception("结构类型不匹配!");

        } catch (OgnlException e) {
            logger.info("Ognl表达式错误");
            e.printStackTrace();
//			return null;
            return defaultObj;
        } catch (IllegalArgumentException e) {
            logger.info("Ognl表达式错误");
            e.printStackTrace();
//			return null;
            return defaultObj;
        } catch (ArithmeticException e) {
            logger.info("Ognl表达式错误 分母为0");
//			e.printStackTrace();
//			return null;
            return defaultObj;
        }
    }

    //判断 retString是否为数字字符串
    public static boolean isNumber(String str) {
        boolean f = true;
        try {
            Double.parseDouble(str);
        } catch (Exception e) {
            f = false;
        }

        return f;
    }

    public static String getMd5(String str) {
        try {
            java.security.MessageDigest md;
            md = java.security.MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return bytes2HexStr(md.digest());
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String bytes2HexStr(byte bytes[]) {
        String rs = "";
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);

            if (hex.length() == 1)
                hex = "0" + hex;

            rs += hex;
        }
        return rs.toUpperCase();
    }

    public static String subString(String str, int s, int e) {
        return str.substring(s, e);
    }

    /**
     * id转name
     *
     * @param id     一般为表中的主键
     * @param beanId 对应dao(表)的beanId
     * @return 返回主键对应的name(自定义)
     */
    public static String id2Name(String id, String beanId) {
        //如果没有系统没有com.boco.eoms.base.util.ApplicationContextHolder的情况需要把InitStaticBaseApplicationContextServlet配置到web.xml中
        if (ApplicationContextHolder.getInstance().getCtx() == null) {
            ApplicationContextHolder.getInstance().setCtx(com.boco.eoms.base.util.ApplicationContextHolder.getInstance().getCtx());
        }

        //ID2NameService id2NameService = ID2NameServiceFactory.getId2nameServiceDB();
        ID2NameManager id2NameService = (ID2NameManager) ApplicationContextHolder
                .getInstance().getBean("statBaseid2nameService");

        return id2NameService.id2Name(id, beanId);
    }

    /**
     * 根据字典把id转为name
     *
     * @param id     一般为表中的主键
     * @param beanId 对应dao(表)的beanId
     * @return 返回主键对应的name(自定义)
     */
    public static String idType2Name(String dictId, String dictType, String beanId) {
        //ID2NameService id2NameService = ID2NameServiceFactory.getId2nameServiceDB();
        ID2NameManager id2NameService = (ID2NameManager) ApplicationContextHolder
                .getInstance().getBean("statBaseid2nameService");

        try {
            return id2NameService.idType2Name(dictId, dictType, beanId);
        } catch (Id2NameStatException e) {
            logger.info("请查看算法配置文件中字典是否设置正确");
            return "";
        }
    }

    //把id 转换为 name 显示
    public static String id2NameDisply(SummaryDefine summaryDefine, String fieldData) {
        //如果dictType不为null，需要使用字典进行转换
        try {
            String showText = "";
            String dictType = summaryDefine.getDictType();
            String id2nameService = summaryDefine.getId2nameService();
            if (dictType != null && !"".equalsIgnoreCase(dictType)) {
                showText = StatUtil.idType2Name(fieldData, dictType, id2nameService);
            } else {
                showText = StatUtil.id2Name(fieldData, id2nameService);
            }

            return showText;
        } catch (Exception e) {
            //不进行id转name
            logger.info("\n id 转 name 失败");
            return fieldData;
        }
    }

    public static String getStatusName(String status) {
        if (status.equals("1")) {
            return "草稿";
        } else if (status.equals("2")) {
            return "运行中";
        } else if (status.equals("3")) {
            return "归档";
        } else if (status.equals("4")) {
            return "撤销";
        } else if (status.equals("5")) {
            return "待归档";
        } else {
            return "未定义" + status;
        }
    }


    /**
     * 得到建立Excel到路径
     *
     * @param excelConfigURL
     * @return
     */
    public static String getEexcelConfigURL(String excelConfigURL) {
        return "classpath:config/" + excelConfigURL;
    }

    /**
     * 建立Excel在磁盘上
     *
     * @param excelConfigURL
     * @param reportName
     * @param listResult
     * @param infoMap
     * @param kpiDefine
     * @throws Exception
     */
    public static String foundExcelToFile(String excelConfigURL, String reportName, List listResult, Map infoMap, KpiDefine kpiDefine, String exclePartURL, IStatManager statManager, String saveTime) throws Exception {
        String excelConfigFilePath = StaticMethod.getFilePathForUrl(excelConfigURL);
        OutputStream fileStream = statManager.exportExcelKpiStream(excelConfigFilePath, reportName, listResult, infoMap, kpiDefine);

        String ExcelFileRelatPath = exclePartURL + /*reportName +*/  saveTime + ".xls";
        String ExcelFilePath = excelConfigFilePath.substring(0, excelConfigFilePath.indexOf(Constants.WEB_INF)) + ExcelFileRelatPath;
        File file = ExcelConverterUtil.writeFile((ByteArrayOutputStream) fileStream, ExcelFilePath);
        logger.info("\n保存Excel到磁盘路径为： " + ExcelFilePath);

        return file.getName();//ExcelFilePath;
    }

    /**
     * 建立Excel在磁盘上
     *
     * @param excelConfigURL
     * @param reportName
     * @param listResult
     * @param infoMap
     * @param kpiDefine
     * @throws Exception
     */
    public static String foundExcelToFile(String excelConfigURL, String reportName, List listResult, Map infoMap, KpiDefine kpiDefine, String exclePartURL, IStatManager statManager, String saveTime, String[] dyColumSelectids, int sheetIndex) throws Exception {
        String excelConfigFilePath = StaticMethod.getFilePathForUrl(excelConfigURL);
        OutputStream fileStream = statManager.exportExcelKpiStream(excelConfigFilePath, reportName, listResult, infoMap, kpiDefine, dyColumSelectids, sheetIndex);

        String ExcelFileRelatPath = exclePartURL + /*reportName +*/  saveTime + ".xls";
        String ExcelFilePath = excelConfigFilePath.substring(0, excelConfigFilePath.indexOf(Constants.WEB_INF)) + ExcelFileRelatPath;
        File file = ExcelConverterUtil.writeFile((ByteArrayOutputStream) fileStream, ExcelFilePath);
        logger.info("\n保存Excel到磁盘路径为： " + ExcelFilePath);

        return file.getName();//ExcelFilePath;
    }

    public static String foundHtmlToFile(String htmlString, String excelConfigURL, String exclePartURL, String saveTime) throws FileNotFoundException {
        String excelConfigFilePath = StaticMethod.getFilePathForUrl(excelConfigURL);
        String HtmlFileRelatPath = exclePartURL + /*reportName +*/  saveTime + ".html";
        String htmlFilePath = excelConfigFilePath.substring(0, excelConfigFilePath.indexOf(Constants.WEB_INF)) + HtmlFileRelatPath;
        File file = ExcelConverterUtil.writeFile(htmlString, htmlFilePath);
        logger.info("\n保存html到磁盘路径为： " + htmlFilePath);
        return file.getName();
    }

    public static Map getMapInfo(Map actionMap, KpiDefine kpiDefine) {
        Map infoMap = new HashMap();

        ConditionParam[] conditionParams = kpiDefine.getConditionParams();
        if (conditionParams != null) {
            for (int iParam = 0; iParam < conditionParams.length; iParam++) {
                ConditionParam condParam = conditionParams[iParam];
                String key = condParam.getPageName();
                Object mapObj = actionMap.get(key);
                //String value = StaticMethod.nullObject2String(mapObj!=null?((String[])mapObj)[0]:"");
                String value = String.valueOf(mapObj);
                infoMap.put(key, value);
            }
        }

        return infoMap;
    }

    /**
     * 转换ParameterMap转换为util到Map
     *
     * @param ParameterMap
     * @return
     */
    public static Map ParameterMapToUtilMap(Map ParameterMap) {
        Map utilMap = new HashMap();
        Iterator iterator = ParameterMap.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            String value = StaticMethod.nullObject2String(ParameterMap.get(key) != null ? ((String[]) ParameterMap.get(key))[0] : "");
            utilMap.put(key, value);
        }

        return utilMap;
    }

    /**
     * 验证url Parameter的合法性
     *
     * @param findForward
     * @param excelConfigURL
     * @throws Exception
     */
    public static void validataParameter(String findForward, String excelConfigURL, String findListForward) throws Exception {
        if (findForward == null || findForward.equalsIgnoreCase("")) {
            //findForward = "statistic";
            throw new Exception("findForward参数不能为空");
        }
        if (excelConfigURL == null || "".equalsIgnoreCase(excelConfigURL)) {
            throw new Exception("excelConfigURL参数不能为空");
        }
        if (findListForward == null || "".equalsIgnoreCase(findListForward)) {
            throw new Exception("findListForward参数不能为空");
        }
    }

    /**
     * 修改request.getParameterMap() 中的Map
     *
     * @param actionMap
     * @return
     * @throws Exception
     */
    public static Map modActionMap(Map actionMap)
            throws Exception {
        String type = String.valueOf(actionMap.get("type"));
        GregorianCalendar bdate = null;
        GregorianCalendar edate = null;

        if ("half".equalsIgnoreCase(type)) {
            int beginyear = Integer.parseInt(String.valueOf(actionMap.get("beginyear")));

            if (actionMap.get("halfselect") != null) {
                if ("half_one".equalsIgnoreCase(String.valueOf(actionMap.get("halfselect")))) {
                    bdate = new GregorianCalendar(beginyear, 0, 1, 0, 0, 0);
                    edate = new GregorianCalendar(beginyear, 5, 1, 23, 59, 59);
                } else if ("half_two".equalsIgnoreCase(String.valueOf(actionMap.get("halfselect")))) {
                    bdate = new GregorianCalendar(beginyear, 6, 1, 0, 0, 0);
                    edate = new GregorianCalendar(beginyear, 11, 1, 23, 59, 59);
                }
            }

            Date[] datee = getMonthStartAndEndDate(edate);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dfbdate = sdf.format(bdate.getTime());
            String dfedate = sdf.format(datee[1]);

            actionMap.put("beginTime", dfbdate);
            actionMap.put("endTime", dfedate);
        }

        if ("year".equalsIgnoreCase(type)) {
            String beginyear = String.valueOf(actionMap.get("beginyear"));
            String beginTime = beginyear + "-01-01 00:00:00";
            String endTime = beginyear + "-12-31 23:59:59";
            actionMap.put("beginTime", beginTime);
            actionMap.put("endTime", endTime);
        }

        if ("day".equalsIgnoreCase(type)) {
            String beginday = String.valueOf(actionMap.get("beginday"));
            String beginTime = beginday + " 00:00:00";
            String endTime = beginday + " 23:59:59";
            actionMap.put("beginTime", beginTime);
            actionMap.put("endTime", endTime);
        }

        if ("week".equalsIgnoreCase(type)) {
            int beginyear = Integer.parseInt(String.valueOf(actionMap.get("beginyear")));
            int beginweek = 1;
            if (actionMap.get("beginweek") != null) {
                beginweek = Integer.parseInt(String.valueOf(actionMap.get("beginweek")));
            }
            String weekstarttime = getYearWeekFirstDay(beginyear, beginweek) + " 00:00:00";
            String weekendtime = getYearWeekEndDay(beginyear, beginweek) + " 23:59:59";

            actionMap.put("beginTime", weekstarttime);
            actionMap.put("endTime", weekendtime);
        }

        if ("month".equalsIgnoreCase(type)) {
            int beginyear = Integer.parseInt(String.valueOf(actionMap.get("beginyear")));
            int beginmonth = 1;
            if (actionMap.get("beginmonth") != null) {
                beginmonth = Integer.parseInt(String.valueOf(actionMap.get("beginmonth")));
            }

            bdate = new GregorianCalendar(beginyear, beginmonth - 1, 1, 0, 0, 0);
            edate = new GregorianCalendar(beginyear, beginmonth - 1, 1, 23, 59, 59);
            Date[] datee = getMonthStartAndEndDate(edate);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dfbdate = sdf.format(bdate.getTime());
            String dfedate = sdf.format(datee[1]);

            actionMap.put("beginTime", dfbdate);
            actionMap.put("endTime", dfedate);
        }
        if ("month1".equalsIgnoreCase(type)) {
            int beginyear = Integer.parseInt(String.valueOf(actionMap.get("beginyear")));
            int beginmonth = 1;
            if (actionMap.get("beginmonth") != null) {
                beginmonth = Integer.parseInt(String.valueOf(actionMap.get("beginmonth")));
            }

            bdate = new GregorianCalendar(beginyear, 0, 1, 0, 0, 0);
            edate = new GregorianCalendar(beginyear, beginmonth - 1, 1, 23, 59, 59);
            Date[] datee = getMonthStartAndEndDate(edate);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dfbdate = sdf.format(bdate.getTime());
            String dfedate = sdf.format(datee[1]);

            actionMap.put("beginTime", dfbdate);
            actionMap.put("endTime", dfedate);
        }

        if ("season".equalsIgnoreCase(type)) {
            int beginyear = Integer.parseInt(String.valueOf(actionMap.get("beginyear")));

            if (actionMap.get("seasonselect") != null) {
                if ("season_one".equalsIgnoreCase(String.valueOf(actionMap.get("seasonselect")))) {
                    bdate = new GregorianCalendar(beginyear, 0, 1, 0, 0, 0);
                    edate = new GregorianCalendar(beginyear, 2, 1, 23, 59, 59);
                } else if ("season_two".equalsIgnoreCase(String.valueOf(actionMap.get("seasonselect")))) {
                    bdate = new GregorianCalendar(beginyear, 3, 1, 0, 0, 0);
                    edate = new GregorianCalendar(beginyear, 5, 1, 23, 59, 59);
                } else if ("season_three".equalsIgnoreCase(String.valueOf(actionMap.get("seasonselect")))) {
                    bdate = new GregorianCalendar(beginyear, 6, 1, 0, 0, 0);
                    edate = new GregorianCalendar(beginyear, 8, 1, 23, 59, 59);
                } else if ("season_four".equalsIgnoreCase(String.valueOf(actionMap.get("seasonselect")))) {
                    bdate = new GregorianCalendar(beginyear, 9, 1, 0, 0, 0);
                    edate = new GregorianCalendar(beginyear, 11, 1, 23, 59, 59);
                }

                Date[] datee = getMonthStartAndEndDate(edate);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dfbdate = sdf.format(bdate.getTime());
                String dfedate = sdf.format(datee[1]);

                actionMap.put("beginTime", dfbdate);
                actionMap.put("endTime", dfedate);
            }
        }

        return actionMap;
    }

    private static String getYearWeekFirstDay(int yearNum, int weekNum)
            throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(1, yearNum);
        cal.set(3, weekNum);
        cal.set(7, 2);
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(cal.get(2) + 1);
        String tempDay = Integer.toString(cal.get(5));
        if (Integer.parseInt(tempMonth) < 10)
            tempMonth = "0" + tempMonth;
        if (Integer.parseInt(tempDay) < 10)
            tempDay = "0" + tempDay;
        String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
        return tempDate;
    }

    private static String getYearWeekEndDay(int yearNum, int weekNum)
            throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(1, yearNum);
        cal.set(3, weekNum + 1);
        cal.set(7, 1);
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(cal.get(2) + 1);
        String tempDay = Integer.toString(cal.get(5));
        if (Integer.parseInt(tempMonth) < 10)
            tempMonth = "0" + tempMonth;
        if (Integer.parseInt(tempDay) < 10)
            tempDay = "0" + tempDay;
        String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
        return tempDate;
    }

    /**
     * 得到当月的第一天和最后一天
     *
     * @param calendar
     * @return
     */
    private static Date[] getMonthStartAndEndDate(Calendar calendar) {
        Date[] dates = new Date[2];
        Date firstDateOfMonth, lastDateOfMonth;
        // 得到当天是这月的第几天
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        // 减去dayOfMonth,得到第一天的日期，因为Calendar用０代表每月的第一天，所以要减一
        calendar.add(Calendar.DAY_OF_MONTH, -(dayOfMonth - 1));
        firstDateOfMonth = calendar.getTime();
        // calendar.getActualMaximum(Calendar.DAY_OF_MONTH)得到这个月有几天
        calendar.add(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
        lastDateOfMonth = calendar.getTime();
        dates[0] = firstDateOfMonth;
        dates[1] = lastDateOfMonth;
        return dates;
    }

    public static Date getMonthEndDate(Calendar calendar) {
        return getMonthStartAndEndDate(calendar)[1];
    }

    public static Date getMonthStartDate(Calendar calendar) {
        return getMonthStartAndEndDate(calendar)[0];
    }


    public static String getDateURL(String RequestURI, String method, String excelConfigURL, String reportIndex, String findListForward, String statID) {
        return /*request.getRequestURI()*/ RequestURI + "?method=showStatisticSheetList" + "&excelConfigURL=" + excelConfigURL + "&findListForward=" + findListForward + "&reportIndex=" + reportIndex + "&statID=" + statID;
    }

    /**
     * 替换String,需要替换的String是repMap中的key，替换的值是repMap中的value
     *
     * @param str
     * @param repMap
     * @param marker
     * @return
     * @throws Exception
     */
    public static String getRepString(String str, Map repMap, String marker) throws Exception {
        //System.out.println("str is: " + str);
        int index1 = str.indexOf(marker);
        while (index1 > 0) {
            int index2 = str.indexOf(marker, index1 + marker.length());
            if (index2 == -1) {
                throw new Exception("没有找到第2个" + marker + ",请查看配置算法文件<query-define>节点是否正确");
            }
            String repString = str.substring(index1 + marker.length(), index2);
            Object tempvalue = repMap.get(repString);
            String valueString = String.valueOf(tempvalue == null ? "" : tempvalue);

            //如果页面中传valueString为""，就去掉sqlString中的这个条件。
            if ("".equalsIgnoreCase(valueString)) {
                logger.info("/n页面" + repString + "赋值为空，所以在sql中去掉" + repString + "条件");

                int andindex = str.lastIndexOf(" and ", index1);
                int orindex = str.lastIndexOf(" or ", index1);
                String and_OR_String = "";

                int index = Math.max(andindex, orindex);

                int len = index2 + +marker.length();
                //取需要替换的字符串后1位如果是'，说明也要替换为""
                if (str.length() >= (index2 + +marker.length() + 1) && "'".equalsIgnoreCase(str.substring(index2 + +marker.length(), index2 + +marker.length() + 1))) {
                    len = len + 1;
                }

                and_OR_String = str.substring(index, len);

                //替换操作符in
                if (and_OR_String.indexOf(" in ") != -1) {
                    int indexlastOp = str.indexOf(")", index);
                    and_OR_String = str.substring(index, indexlastOp + ")".length());
                }

                //由于jdk1.4的原因不能替换in(@user@)
                //如果是用replace进行替换就可以。，不知道为什么，所以先不允许in操作付里面的内同为""
                //throw new Exception(odlString + " 条件中 " + repString + "值不能为空");
                //str = str.replaceAll(and_OR_String,"");
                str = ExcelConverterUtil.replaceAll(str, and_OR_String, "");
            } else {
                str = str.replaceAll(marker + repString + marker, valueString);
                //str = replace(marker+repString+marker,valueString,str);
            }

            index1 = str.indexOf(marker);
        }
        return str;
    }

    public static void printRsList(List listResult) {
        //打印结果集模型
        boolean printListFlg = true;
        if (printListFlg) {
            for (int i = 0; i < listResult.size(); i++) {
                System.out.println("===============================================");
                Map listMap = (Map) listResult.get(i);
                java.util.Iterator iterator = listMap.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = String.valueOf(iterator.next());
                    String value = String.valueOf(listMap.get(key));
                    System.out.println("key : " + key + " , " + "value : " + value);
                }
            }
        }
    }

    /**
     * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     * <p>
     * 纵横软件制作中心雨亦奇2003.08.01
     *
     * @param s 原文件名
     * @return 重新编码后的文件名
     */

    public static String toUtf8String(String s) {

        StringBuffer sb = new StringBuffer();
        int len = s.length();

        for (int i = 0; i < len; i++) {

            char c = s.charAt(i);

            if (c >= 0 && c <= 255) {

                sb.append(c);

            } else {

                byte[] b;

                try {

                    b = Character.toString(c).getBytes("utf-8");

                } catch (Exception ex) {

                    System.out.println(ex);

                    b = null;

                }

                for (int j = 0; j < b.length; j++) {

                    int k = b[j];

                    if (k < 0)
                        k += 256;

                    sb.append("%" + Integer.toHexString(k).

                            toUpperCase());

                }

            }

        }

        return sb.toString();

    }

    public static void setRequestAttribute(HttpServletRequest actionRequest, String name, Object value) {
        if (actionRequest != null) {
            actionRequest.setAttribute(name, value);
        }
    }

    public static String getWEBABSURL() throws FileNotFoundException {
        String webInfoPath = StaticMethod.getFilePathForUrl("classpath:config/statistic/base-config/applicationContext-statistic-base.xml");

        return webInfoPath.substring(0, webInfoPath.indexOf(Constants.WEB_INF));
    }

    public static Map StringToMap(String customDetail) {
        Map map = new HashMap();
        String detail[] = customDetail.split(",");
        for (int i = 0; i < detail.length; i++) {
            String keyvalue[] = detail[i].split("=");
            String key = detail[i].split("=")[0];
            String value = "";
            if (keyvalue.length > 1) {
                value = detail[i].split("=")[1];
            }

            map.put(key, value);
        }

        return map;
    }

    public static void main(String[] args) {
//		String sql = "where t.year&gt;=@fromTime@ and t.year&lt;=@toTime@ and decp=@decp@ and user=@user@ ";
//		Map sqlParams = new HashMap();
//		sqlParams.put("fromTime", "2008-01-19 15:13:44");
//		sqlParams.put("toTime", "2008-05-19 17:19:44");
//		sqlParams.put("decp", "");
//		sqlParams.put("user", "lizhenyou");
//		
//		try {
//			String outString = getRepString(sql,sqlParams,"@");
//			System.out.print(outString);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

        //测试ongl表达式
        String str = "@java.lang.Math@round(new Double(f5)*1000000 /f6) + new String(%)";
        Map map = new HashMap();
        map.put("f5", "1");
        map.put("f6", "0");

        Object obj = getExpressionResult(map, str, new Integer(0));

        System.out.println(obj);

    }

    public static int getYear(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        int r = 0;
        try {
            r = dateFormat.parse(format).getMonth();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return r;
    }


}
