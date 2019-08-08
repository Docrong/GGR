package com.boco.eoms.sheet.base.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.service.IMainService;

/**
 * *
 * <p>
 * Title:将工单的表单信息转换成Engine Adapter能够识别的对象
 * </p>
 * <p>
 * Description:将工单的表单信息转换成Engine Adapter能够识别的对象
 * </p>
 * <p>
 * Date:2007-8-3 14:55:03
 * </p>
 *
 * @author 陈元蜀
 * @version 1.0
 */
public class SheetUtils {

    /**
     * @param map
     * @param columnName
     * @return
     * @throws SheetException
     * @deprecated 这个方法现在暂时无用，所以修改均不用对该方法进行修改
     */
    public static HashMap populateActionMapToWpsMap(Map map, String columnName)
            throws SheetException {
        HashMap returnMap = new HashMap();

        if (columnName.indexOf(";") != -1) {
            String[] bo = columnName.split(";");
            for (int i = 0; bo.length > 0 && i < bo.length; i++) {
                String tempBO = bo[i];
                if (tempBO.indexOf(":") != -1) {
                    String boName = tempBO.substring(0, tempBO.indexOf(":"));
                    tempBO = tempBO.substring(tempBO.indexOf(":") + 1, tempBO
                            .length());
                    String[] attributNameWithType = tempBO.split(",");
                    Map tempMap = new HashMap();
                    for (int j = 0; attributNameWithType.length > 0
                            && j < attributNameWithType.length; j++) {
                        String[] attributNames = attributNameWithType[j]
                                .split("@");
                        Object obj = map.get(attributNames[0]);
                        if (obj != null && obj.getClass().isArray()) {
                            obj = ((Object[]) obj)[0];
                        }
                        if (!attributNames[0].equals("id")
                                && !attributNames[0].equals("correlationKey")) {
                            if (attributNames[1].equals("java.util.Date")) {
                                String temp = StaticMethod
                                        .nullObject2String(obj);
                                Date date = stringToDate(temp);
                                tempMap.put(attributNames[0], date);
                            } else if (attributNames[1]
                                    .equals("java.lang.Integer")) {
                                int temp = StaticMethod.nullObject2int(obj, 0);
                                Integer integer = new Integer(temp);
                                tempMap.put(attributNames[0], integer);
                            } else if (attributNames[1]
                                    .equals("java.lang.Long")) {
                                long temp = StaticMethod
                                        .nullObject2Long(obj, 0);
                                Long longs = new Long(temp);
                                tempMap.put(attributNames[0], longs);
                            } else if (attributNames[1]
                                    .equals("java.lang.Float")) {
                                float temp = StaticMethod.nullObject2Long(obj,
                                        0);
                                Float f = new Float(temp);
                                tempMap.put(attributNames[0], f);
                            } else {
                                String temp = StaticMethod
                                        .nullObject2String(obj);
                                tempMap.put(attributNames[0], temp);
                            }
                        } else {
                            try {
                                String temp = StaticMethod
                                        .nullObject2String(obj);
                                if (temp.equals("")) {
                                    tempMap.put(attributNames[0],
                                            UUIDHexGenerator.getInstance()
                                                    .getID());
                                } else {
                                    tempMap.put(attributNames[0], temp);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new SheetException("获取UUID出现异常，请联系管理员!");
                            }
                        }
                    }
                    returnMap.put(boName, tempMap);
                } else {
                    throw new SheetException("参数赋值发生异常，请联系管理员!");
                }
            }

        } else {
            throw new SheetException("Column Map中出现异常,请联系管理员!");
        }
        return returnMap;
    }

    public static HashMap actionMapToEngineMap(Map map, HashMap columnMap)
            throws SheetException {
        HashMap returnMap = new HashMap();
        String columnName = "";
        // add by chenyuanshu 增加对时间的处理 begin
        Object send = map.get("sendTime");
        if (send != null && send.getClass().isArray()) {
            send = ((Object[]) send)[0];
        }
        String sendTime = StaticMethod.nullObject2String(send);
        Object end = map.get("endTime");
        if (end != null && end.getClass().isArray()) {
            end = ((Object[]) end)[0];
        }
        String endTime = StaticMethod.nullObject2String(end);
        Object operate = map.get("operateTime");
        if (operate != null && operate.getClass().isArray()) {
            operate = ((Object[]) operate)[0];
        }
        String operateTime = StaticMethod.nullObject2String(operate);
        String currentTime = StaticMethod.getCurrentDateTime();
        Object objstatus = map.get("status");
        if (objstatus != null && objstatus.getClass().isArray()) {
            objstatus = ((Object[]) objstatus)[0];
        }
        int status = StaticMethod.nullObject2int(objstatus, -100);
        if (sendTime.equals("") && status == Constants.SHEET_RUN.intValue()) {
            map.put("sendTime", currentTime);
        }
        if (endTime.equals("") && status != Constants.SHEET_RUN.intValue()) {
            map.put("endTime", currentTime);
        }
        if (status == Constants.SHEET_RUN.intValue()) {
            if (StaticMethod.nullObject2String(map.get("nodeAcceptLimit")).equals(""))
                map.put("nodeAcceptLimit", map.get("sheetAcceptLimit"));
            if (StaticMethod.nullObject2String(map.get("nodeCompleteLimit")).equals(""))
                map.put("nodeCompleteLimit", map.get("sheetCompleteLimit"));

        }
        if (StaticMethod.nullObject2String(map.get("operaterContact")).equals("")) {
            map.put("operaterContact", map.get("sendContact"));
        }
        map.put("operateTime", currentTime);

        // add by chenyuanshu 增加对时间的处理 end

        /** add action control panlong begin* */
        Object operateObj = map.get("operateType");
        if (operateObj != null && operateObj.getClass().isArray()) {
            operateObj = ((Object[]) operateObj)[0];
        }
        int operateType = StaticMethod.nullObject2int(operateObj, 0);

        //if (operateType > 0 && operateType < 60) {
        // 流程动作 完成任务的操作类型时保存完成时限
        map.put("completetime", currentTime);
        Object taskCompleteLimit = map.get("taskCompleteLimit");
        if (taskCompleteLimit != null && taskCompleteLimit.getClass().isArray()) {
            taskCompleteLimit = ((Object[]) taskCompleteLimit)[0];
        }
        Object taskAcceptLimit = map.get("taskAcceptLimit");
        if (taskAcceptLimit != null && taskAcceptLimit.getClass().isArray()) {
            taskAcceptLimit = ((Object[]) taskAcceptLimit)[0];
        }
        if (taskAcceptLimit != null && taskCompleteLimit == null) {
            taskCompleteLimit = taskAcceptLimit;
            taskAcceptLimit = null;
        }

        //处理是否及时
        if (taskCompleteLimit != null) {

            String taskCompleteLimitTime = StaticMethod
                    .nullObject2String(taskCompleteLimit);//
            Date taskCompTime = StringToDate(taskCompleteLimitTime);

            //由于某些工单界面上赋值反了，所以需要单独判断下
            if (taskAcceptLimit != null && taskCompleteLimit != null) {
                //taskAcceptLimit = ((Object[]) taskAcceptLimit)[0];
                String taskAcceptLimitTime = StaticMethod
                        .nullObject2String(taskAcceptLimit);//
                Date taskAcceptTime = StringToDate(taskAcceptLimitTime);
                if (taskCompTime != null && taskAcceptTime != null && taskCompTime.before(taskAcceptTime)) {
                    taskCompTime = taskAcceptTime;
                } else if (taskAcceptTime != null && taskCompTime == null) {
                    taskCompTime = taskAcceptTime;
                }
            }
            Date curTime = StringToDate(currentTime);

            if (taskCompTime != null && curTime != null) {
                if (curTime.after(taskCompTime)) {
                    map.put("completeFlag", new Integer(
                            Constants.MAKE_COMPLETEFLAG_DEFER));
                } else {
                    map.put("completeFlag", new Integer(
                            Constants.MAKE_COMPLETEFLAG_INTIME));
                }
            }
        }
        //受理是否及时
        if (taskAcceptLimit != null) {

            String taskAcceptLimitTime = StaticMethod
                    .nullObject2String(taskAcceptLimit);//
            Date taskAcceptTime = StringToDate(taskAcceptLimitTime);
            //由于某些工单界面上赋值反了，所以需要单独判断下
            if (taskAcceptLimit != null && taskCompleteLimit != null) {

                String taskCompleteLimitTime = StaticMethod
                        .nullObject2String(taskCompleteLimit);//
                Date taskCompTime = StringToDate(taskCompleteLimitTime);
                if (taskCompTime != null && taskAcceptTime != null && taskAcceptTime.after(taskCompTime)) {
                    taskAcceptTime = taskCompTime;
                } else if (taskAcceptTime != null && taskCompTime == null) {
                    taskCompTime = taskAcceptTime;
                }
            }
            Date curTime = StringToDate(currentTime);
            if (taskAcceptTime != null && curTime != null) {
                if (curTime.after(taskAcceptTime)) {
                    map.put("acceptFlag", new Integer(
                            Constants.MAKE_COMPLETEFLAG_DEFER));
                } else {
                    map.put("acceptFlag", new Integer(
                            Constants.MAKE_COMPLETEFLAG_INTIME));
                }
            }
        }

        /** add action control panlong end* */

        String sendRoleId = StaticMethod.nullObject2String(map
                .get("sendRoleId"));
        String operateRoleId = StaticMethod.nullObject2String(map
                .get("sendRoleId"));
        if (operateRoleId.equals("") && !sendRoleId.equals("")) {
            map.put("operateRoleId", sendRoleId);
        }

        if (columnMap != null) {
            Set set = columnMap.keySet();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                String key = StaticMethod.nullObject2String(it.next());
                Object obj = columnMap.get(key);
                String temp = "";
                if (obj == null) {
                    throw new SheetException("Interface Map中存在为空的对象,请联系管理员!");
                }
                // leo modify
                // if (!obj.getClass().getName().equals("java.lang.String")) {
                if (!(obj instanceof String)) {
                    temp = getModelObjectPropotieNames(columnMap.get(key));

                } else {
                    // leo modify
                    // temp =
                    // StaticMethod.nullObject2String(columnMap.get(key));
                    temp = StaticMethod.nullObject2String(obj);
                    // temp = key + "@"
                    // + StaticMethod.null2String(obj.getClass().getName());

                }
                columnName = columnName + key + ":" + temp + ";";
            }
            // returnMap = populateActionMapToWpsMap(map, columnName);
            returnMap = populateActionMapToWpsMap(map, columnName, columnMap);
            // populateModel2WpsMap(returnMap, columnMap);
        } else {
            throw new SheetException("Interface Map为空,请联系管理员!");
        }
        return returnMap;
    }

    /**
     * ************************************* 转换页面保存字段,当同时保存main和link时,
     * 如果两个表有重名字段,并且从页面传入,需要在此方法中特殊处理!! *************************************
     *
     * @param map
     * @param columnName
     * @param columnMap
     * @return
     * @throws SheetException
     */
    public static HashMap populateActionMapToWpsMap(Map map, String columnName,
                                                    HashMap columnMap) throws SheetException {
        HashMap returnMap = new HashMap();
        String sheetKey = "";
        if (columnName.indexOf(";") != -1) {
            String[] bo = columnName.split(";");
            for (int i = 0; bo.length > 0 && i < bo.length; i++) {
                boolean isNewValue = false;
                String tempBO = bo[i];
                if (tempBO.indexOf(":") != -1) {
                    String boName = tempBO.substring(0, tempBO.indexOf(":"));
                    Object bean = columnMap.get(boName);
                    tempBO = tempBO.substring(tempBO.indexOf(":") + 1, tempBO
                            .length());
                    String[] attributNameWithType = tempBO.split(",");
                    Map tempMap = new HashMap();
                    for (int j = 0; attributNameWithType.length > 0
                            && j < attributNameWithType.length; j++) {
                        String[] attributNames = attributNameWithType[j]
                                .split("@");
                        if (attributNames[0] != null) {
                            attributNames[0] = attributNames[0].trim();
                        }
                        Object obj = null;
                        isNewValue = map.keySet().contains(attributNames[0]);
                        if (isNewValue) {
                            obj = map.get(attributNames[0]);
                        }

                        if (obj != null && obj.getClass().isArray()) {
                            obj = ((Object[]) obj)[0];
                        }
                        String getMethod = "get"
                                + StaticMethod
                                .firstToUpperCase(attributNames[0]);
                        Method getterMethod = null;
                        Object beanValue = null;
                        if (!bean.getClass().getName().equals(
                                "java.lang.String")
                                && bean != null) {
                            try {
                                getterMethod = bean.getClass().getMethod(
                                        getMethod, new Class[]{});
                                beanValue = getterMethod.invoke(bean,
                                        new Object[]{});
                                // tempMap.put(attributNames[0], beanValue);
                            } catch (SecurityException e1) {
                            } catch (NoSuchMethodException e1) {
                            } catch (IllegalArgumentException e2) {
                            } catch (IllegalAccessException e2) {
                            } catch (InvocationTargetException e2) {
                            }
                        }

                        if (attributNames[0].equals("id")) {
                            try {
                                String objId = StaticMethod
                                        .nullObject2String(obj);
                                String beanValueId = StaticMethod
                                        .nullObject2String(beanValue);

                                if (!objId.equals("") && beanValueId.equals("")) {

                                    // 如果是流程间互相调用时，新增的对象也应当是重新赋值id和correlationKey
                                    // add by chenyuanshu 2008-5-14 begin
                                    if (boName.toLowerCase().indexOf("link") != -1
                                            || boName.length() > 4) {
                                        // 如果是link对象，则判断页面是否传过来id值（linkId属性承载）。不能使用界面传递的id，界面传递的id是main对象的id
                                        Object linkObject = map.get("linkId");
                                        if (linkObject != null
                                                && linkObject.getClass()
                                                .isArray()) {
                                            linkObject = ((Object[]) linkObject)[0];
                                        }
                                        String linkId = StaticMethod
                                                .nullObject2String(linkObject);
                                        if (linkId.equals(""))
                                            linkId = UUIDHexGenerator
                                                    .getInstance().getID();
                                        tempMap.put(attributNames[0], linkId);
                                    } else {
                                        tempMap.put(attributNames[0], objId);
                                    }
                                    // tempMap.put(attributNames[0], objId);
                                    // add by chenyuanshu 2008-5-14 end
                                } else if (!beanValueId.equals("")) {
                                    tempMap.put(attributNames[0], beanValueId);
                                } else {
                                    tempMap.put(attributNames[0],
                                            UUIDHexGenerator.getInstance()
                                                    .getID());
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new SheetException("获取UUID出现异常，请联系管理员!");
                            }

                        } else {
                            if (attributNames[0].equals("correlationKey")) {
                                String beanValueId = StaticMethod
                                        .nullObject2String(obj);
                                if (!beanValueId.equals("")) {
                                    tempMap.put(attributNames[0], beanValueId);
                                } else {
                                    try {
                                        tempMap.put(attributNames[0],
                                                UUIDHexGenerator.getInstance()
                                                        .getID());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        throw new SheetException(
                                                "获取UUID出现异常，请联系管理员!");
                                    }
                                }
                            } else if (attributNames[0].equals("sheetId")) {// 填充工单流水号
                                String sheetId = StaticMethod
                                        .nullObject2String(obj);
                                /**批量处理时的特殊处理begin，add by 秦敏**/
                                String beanValueId = StaticMethod
                                        .nullObject2String(beanValue);
                                if (sheetId.equals("") && !beanValueId.equals("")) {
                                    sheetId = beanValueId;
                                }
                                /**批量处理时的特殊处理end，add by 秦敏**/
                                if (sheetId.equals("")) {
                                    String beanId = "";
                                    Object sheetObj = map.get("beanId");

                                    if (sheetObj != null
                                            && sheetObj.getClass().isArray()) {
                                        sheetObj = ((Object[]) sheetObj)[0];
                                        beanId = StaticMethod
                                                .nullObject2String(sheetObj);
                                    } else {
                                        beanId = StaticMethod
                                                .nullObject2String(sheetObj);
                                    }
                                    IMainService mainService = (IMainService) ApplicationContextHolder
                                            .getInstance().getBean(beanId);
                                    sheetId = mainService.getSheetId();
                                }
                                tempMap.put(attributNames[0], sheetId);
                            } else if (attributNames[1]
                                    .equals("java.util.Date")) {
                                if (obj != null) {
                                    String temp = StaticMethod
                                            .nullObject2String(obj);
                                    Date date = stringToDate(temp);
                                    tempMap.put(attributNames[0], date);
                                } else if (beanValue != null) {
                                    tempMap.put(attributNames[0], beanValue);
                                } else {
                                    tempMap.put(attributNames[0],
                                            null);
                                }

                            } else if (attributNames[1]
                                    .equals("java.lang.Integer")) {
                                if (obj != null) {
                                    int temp = StaticMethod.nullObject2int(obj,
                                            0);
                                    Integer integer = new Integer(temp);
                                    tempMap.put(attributNames[0], integer);
                                } else if (beanValue != null) {
                                    tempMap.put(attributNames[0], beanValue);
                                } else {
                                    tempMap.put(attributNames[0],
                                            new Integer(0));
                                }

                            } else if (attributNames[1]
                                    .equals("java.lang.Long")) {
                                long temp = StaticMethod
                                        .nullObject2Long(obj, 0);
                                Long longs = new Long(temp);
                                tempMap.put(attributNames[0], longs);
                            } else if (attributNames[1]
                                    .equals("java.lang.Float")) {
                                if (obj != null) {
                                    float temp = StaticMethod.nullObject2Long(
                                            obj, 0);
                                    Float f = new Float(temp);
                                    tempMap.put(attributNames[0], f);
                                } else if (beanValue != null) {
                                    tempMap.put(attributNames[0], beanValue);
                                } else {
                                    tempMap.put(attributNames[0], new Float(0));
                                }

                            } else {
                                if (obj != null && !obj.equals("")) {
                                    String temp = StaticMethod
                                            .nullObject2String(obj);
                                    tempMap.put(attributNames[0], temp);
                                } else if (beanValue != null) {
                                    tempMap.put(attributNames[0], beanValue);
                                } else {
                                    tempMap.put(attributNames[0], "");
                                }

                            }
                        }
                    }
                    returnMap.put(boName, tempMap);
                } else {
                    throw new SheetException("参数赋值发生异常，请联系管理员!");
                }
            }

        } else {
            throw new SheetException("Column Map中出现异常,请联系管理员!");
        }
        return returnMap;
    }

    public static String getModelObjectPropotieNames(Object obj) {
        String reString = "";
        /**
         * modify by chenyuanshu 2008-11-12 修改获取对象属性的方法，改用hibernate方法 begin

         Method[] superMethods = obj.getClass().getSuperclass()
         .getDeclaredMethods();
         Method[] methods = obj.getClass().getDeclaredMethods();
         String superColumnName = getNamesWithTypeByMethods(superMethods);
         String subColumnName = getNamesWithTypeByMethods(methods);
         if (!subColumnName.equals("")) {
         reString = superColumnName + "," + subColumnName;
         } else {
         reString = superColumnName;
         }
         */
        String pkid = HibernateConfigurationHelper.getPkColumnName(obj.getClass());
        String pro = HibernateConfigurationHelper.getPropNamesWithoutPK(obj.getClass());
        reString = pkid + "," + pro;
        reString = getNamesWithTypeByPropName(obj, reString);
        /**
         * modify by chenyuanshu 2008-11-12 修改获取对象属性的方法，改用hibernate方法 end
         */
        return reString;
    }

    public static String getNamesWithTypeByMethods(Method[] methods) {
        String reString = "";
        for (int i = 0; methods.length > 0 && i < methods.length; i++) {
            Method method = methods[i];
            String name = method.getName();
            String returnType = method.getReturnType().getName();
            if (name.length() > 3 && name.indexOf("get") == 0) {
                String proName = name.substring(name.indexOf("get") + 4, name
                        .length());
                String firstLeter = name.substring(name.indexOf("get") + 3, 4);
                proName = firstLeter.toLowerCase() + proName;

                if (!reString.equals("")) {
                    reString = reString + ",";
                }
                reString = reString + proName + "@" + returnType;
            }
        }
        return reString;

    }

    public static String getNamesWithTypeByPropName(Object obj, String name) {
        String reString = "";
        String[] names = name.split(",");
        for (int i = 0; names != null && i < names.length; i++) {
            String methodName = "get" + StaticMethod.firstToUpperCase(names[i]);
            try {
                String returnType = obj.getClass().getMethod(methodName, null).getReturnType().getName();
                if (!reString.equals("")) {
                    reString = reString + ",";
                }
                reString = reString + names[i] + "@" + returnType;
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return reString;
    }

    public static Date stringToDate(String s) throws SheetException {
        Date date = new Date();
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!s.equals("")) {
                date = df.parse((String) s);
            } else {
                date = null;
            }
        } catch (ParseException e) {
            date = StringToDate(s, "dd/mm/yyyy HH:mm:ss");
        }
        return date;
    }

    public static Date StringToDate(String dateStr, String format)
            throws SheetException {
        Date date = new Date();
        try {
            DateFormat df = new SimpleDateFormat(format);
            if (!dateStr.equals("")) {
                date = df.parse((String) dateStr);
            } else {
                date = null;
            }
        } catch (ParseException e) {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            if (!dateStr.equals("")) {
                try {
                    date = sdf.parse(dateStr);
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {
                date = null;
            }
        }
        return date;
    }

    public static Map serializableParemeterMap(Map attMap)
            throws SheetException {
        Map returnMap = new HashMap();
        try {
            Iterator it = attMap.keySet().iterator();
            String flag = "";
            while (it.hasNext()) {
                String mapKey = (String) it.next();
                String[] keys = mapKey.split("\\.");
                if (keys.length > 2) {
                    throw new SheetException("序列化界面参数出现异常,请联系管理员!");
                } else if (keys.length == 2) {
                    flag = keys[0];
                    if (returnMap.containsKey(flag)) {
                        HashMap containsMap = (HashMap) returnMap.get(flag);
                        containsMap.put(keys[1], attMap.get(mapKey));
                        returnMap.put(flag, containsMap);
                    } else {
                        HashMap tempMap = new HashMap();
                        tempMap.put(keys[1], attMap.get(mapKey));
                        returnMap.put(flag, tempMap);
                    }
                } else {
                    flag = "selfSheet";
                    if (returnMap.containsKey(flag)) {
                        HashMap containsMap = (HashMap) returnMap.get(flag);
                        containsMap.put(mapKey, attMap.get(mapKey));
                        returnMap.put(flag, containsMap);
                    } else {
                        HashMap tempMap = new HashMap();
                        tempMap.put(mapKey, attMap.get(mapKey));
                        returnMap.put(flag, tempMap);
                    }
                }
            }
            if (returnMap.containsKey("method")) {
                returnMap.remove("method");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SheetException("序列化界面参数出现异常,请联系管理员!");
        }
        return returnMap;
    }

    public static void populateModel2WpsMap(HashMap wpsMap,
                                            HashMap interfaceBOMap) {
        Iterator it = interfaceBOMap.keySet().iterator();
        while (it.hasNext()) {
            String mapKey = (String) it.next();
            Object dataObject = interfaceBOMap.get(mapKey);

            String getMethod = "getId";
            try {
                Method getterMethod = dataObject.getClass().getMethod(
                        getMethod, new Class[]{});
                String dataId = StaticMethod.nullObject2String(getterMethod
                        .invoke(dataObject, new Object[]{}), "");
                if (!"".equals(dataId)) {
                    HashMap tempWpsMap = (HashMap) wpsMap.get(mapKey);
                    SheetBeanUtils.populateDataObj2ReqMap(tempWpsMap,
                            dataObject);
                    tempWpsMap.put("id", dataId);
                    wpsMap.put(mapKey, tempWpsMap);
                }
            } catch (SecurityException e) {
                // e.printStackTrace();

            } catch (NoSuchMethodException e) {
                // e.printStackTrace();

            } catch (IllegalArgumentException e1) {
                // e1.printStackTrace();

            } catch (IllegalAccessException e1) {
                // e1.printStackTrace();

            } catch (InvocationTargetException e1) {
                // e1.printStackTrace();

            }

        }
    }

    /**
     * 处理人员解析，获取当前子角色ID、组长ID以及角色类型。add by qinmin
     *
     * @param performer       子角色或者用户ID。
     * @param performerLeader 组长ID。若派单到角色且角色为虚拟组的话，此值为虚拟组组长id，否则为角色ID；若派单到用户，则为用户id
     * @param performerType   处理类型（角色、用户或者部门）
     * @return
     * @throws SheetException
     */
    public static HashMap performerParse(String performer)
            throws SheetException {

        HashMap performerMap = new HashMap();
        String performerNew = "";
        String performerLeader = "";
        String performerType = "";

        try {
            if (!performer.equals("")) {
                JSONArray subRoleJSONList = new JSONArray();
                subRoleJSONList = JSONArray.fromString(performer);
                for (int i = 0; i < subRoleJSONList.length(); i++) {
                    String subRoleId = "";
                    String subRoleType = "";
                    String leaderId = "";
                    JSONObject jsonObj = (JSONObject) subRoleJSONList.get(i);
                    if (jsonObj.has("id"))
                        subRoleId = StaticMethod.nullObject2String(jsonObj
                                .getString("id"));
                    if (jsonObj.has("leaderId"))
                        leaderId = StaticMethod.nullObject2String(jsonObj
                                .getString("leaderId"));
                    if (jsonObj.has("nodeType"))
                        subRoleType = StaticMethod.nullObject2String(jsonObj
                                .getString("nodeType"));

                    performerNew = performerNew + subRoleId + ",";

                    if (!leaderId.equals("")) {
                        performerLeader = performerLeader + leaderId + ",";
                    } else {
                        performerLeader = performerLeader + subRoleId + ",";
                    }

                    performerType = performerType + subRoleType + ",";

                }
                if (!performerNew.equals(""))
                    performerNew = performerNew.substring(0, performerNew
                            .lastIndexOf(","));
                if (!performerLeader.equals(""))
                    performerLeader = performerLeader.substring(0,
                            performerLeader.lastIndexOf(","));
                if (!performerType.equals(""))
                    performerType = performerType.substring(0, performerType
                            .lastIndexOf(","));
            }

            performerMap.put("perfomer", performerNew);
            performerMap.put("performerLeader", performerLeader);
            performerMap.put("performerType", performerType);

        } catch (JSONException e) {
            throw new SheetException("处理人员解析出现异常,请联系管理员!");
        }
        return performerMap;
    }

    /**
     * 时间类型转换
     *
     * @param dateTime
     * @return
     */
    public static Timestamp tranferDateToDate(Date dateTime) {
        java.sql.Timestamp date = null;
        if (dateTime != null) {
            long dateTimeMillion = dateTime.getTime();
            date = new java.sql.Timestamp(dateTimeMillion);
        }
        return date;
    }

    /**
     * String 转换为 Date类型 格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static Date StringToDate(String date) {
        SimpleDateFormat dateformat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date todate = null;

        try {
            if (date != null && !date.equals(""))
                todate = dateformat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return todate;
    }
    /**
     * 效率过于低下，注释之
     * @param subRoleIdPage
     * @return
     * @throws Exception
     */

//	public static Map getUserNameForSubRole(String subRoleIdPage) throws Exception {
//		String userString = "";
//		String leader = "";
//		HashMap returnMap = new HashMap();
//		
//		ITawSystemSubRoleManager subRoleObj = ((ITawSystemSubRoleManager) ApplicationContextHolder
//				.getInstance().getBean("ItawSystemSubRoleManager"));
//		ITawSystemUserRefRoleManager userObj = ((ITawSystemUserRefRoleManager) ApplicationContextHolder
//				.getInstance().getBean("itawSystemUserRefRoleManager"));
//		
//		String[] subRoleIds = subRoleIdPage.trim().split(",");
//		
//		for (int i = 0; i < subRoleIds.length; i ++) {
//			String subRoleId = subRoleIds[i];
//			
//			TawSystemSubRole subobj = subRoleObj.getTawSystemSubRole(subRoleId);
//			if(subobj!=null){
//				//派单到角色
//				List userList = userObj.getUserbyroleid(subRoleId);
//				
//				for (int k = 0; userList != null && k < userList.size(); k++) {
//					TawSystemUser user = (TawSystemUser) userList.get(k);
//					
//					if (StaticMethod.nullObject2String(user.getDeleted()).equals("0") || StaticMethod.nullObject2String(user.getDeleted()).equals("")) {
//						if (!userString.equals("")) {
//							userString += ",";
//						}
//						boolean bool = true;
//						List list = userObj.getUserRefRoleByuserid(user.getUserid());
//						for (int j=0;j<list.size();j++){
//							TawSystemUserRefRole userrefrole =(TawSystemUserRefRole)list.get(j);	
//							if (StaticMethod.nullObject2String(userrefrole.getSubRoleid()).equals(subRoleId) && StaticMethod.nullObject2String(userrefrole.getGroupType()).equals("2")) {
//								userString += user.getUsername()+"(组长)";
//								leader = userrefrole.getUserid();
//								bool = false;
//								break;
//							}
//						}
//						
//						if(bool==true){
//							userString += user.getUsername();
//						}
//					}
//				}
//				returnMap.put("subRoleName", subobj.getSubRoleName());
//				returnMap.put("name", userString);
//				returnMap.put("leaderId", leader);
//			}else{
//				//派单到人
//				returnMap.put("leaderId", subRoleId);
//			}
//			
//		}
//
//		return returnMap;
//	}

    /**
     * 根据子角色id，得到该角色下的组长以及组员
     */
    public static Map getUserNameForSubRole(String subRoleIdPage) throws Exception {
        String userString = "";
        String leader = "";
        HashMap returnMap = new HashMap();

        ITawSystemSubRoleManager subRoleObj = ((ITawSystemSubRoleManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemSubRoleManager"));
        IDownLoadSheetAccessoriesService jdbcManager = (IDownLoadSheetAccessoriesService) ApplicationContextHolder
                .getInstance().getBean("IDownLoadSheetAccessoriesService");

        String[] subRoleIds = subRoleIdPage.trim().split(",");
        TawSystemUserRefRole userrefRole = new TawSystemUserRefRole();

        ID2NameService service = (ID2NameService) ApplicationContextHolder
                .getInstance().getBean("ID2NameGetServiceCatch");

        System.out.println("subRoleIds======" + subRoleIds);

        for (int i = 0; i < subRoleIds.length; i++) {
            String subRoleId = subRoleIds[i];

            TawSystemSubRole subobj = subRoleObj.getTawSystemSubRole(subRoleId);
            if (subobj != null) {
                String userrefroleTable = HibernateConfigurationHelper.getTableName(userrefRole.getClass());
                String useridSql = "select userid,groupType from " + userrefroleTable
                        + " where subroleid='" + subRoleId + "'";

                List userList = jdbcManager.getSheetAccessoriesList(useridSql);

                System.out.println("userList=====>" + userList);

                Iterator _objIterator = userList.iterator();
                while (_objIterator.hasNext()) {
                    List sheetList = new ArrayList();
                    Map _objMap = (Map) _objIterator.next();
                    if (!userString.equals("")) {
                        userString += ",";
                    }
                    String userid = StaticMethod.nullObject2String(_objMap
                            .get("USERID"));
                    System.out.println("userid=====>" + userid);
                    String groupType = StaticMethod.nullObject2String(_objMap
                            .get("GROUPTYPE"));
                    String name = "";
                    if (groupType.equals(RoleConstants.groupType_leader)) {
                        leader = userid;
                        name = service.id2Name(userid, "tawSystemUserDao") + "(" + RoleConstants.TEXT_LEADER + ")";
                        System.out.println("name1=====>" + name);
                    } else {
                        name = service.id2Name(userid, "tawSystemUserDao");
                        System.out.println("name2=====>" + name);
                    }
                    userString = userString + name;
                    System.out.println("name3=====>" + name);
                }

                returnMap.put("subRoleName", subobj.getSubRoleName());
                System.out.println("subRoleName=====>" + subobj.getSubRoleName());
                returnMap.put("name", userString);
                returnMap.put("leaderId", leader);
            } else {
                //派单到人
                returnMap.put("leaderId", subRoleId);
                System.out.println("leaderId=====>" + subRoleId);
            }

        }

        return returnMap;
    }

    /**
     * 得到最匹配的子角色对象
     *
     * @param roleList
     * @param it
     * @return
     */
    public static TawSystemSubRole getMaxFilterSubRole(List roleList, Iterator it) {
        TawSystemSubRole subRole = new TawSystemSubRole();
        HashMap map = new HashMap();
        map.put("count", "0");
        map.put("subrole", null);
        for (int j = 0; roleList != null && j < roleList.size(); j++) {
            int count = 0;
            TawSystemSubRole tempSubRole = (TawSystemSubRole) roleList.get(j);
            String deptId = StaticMethod.nullObject2String(tempSubRole.getDeptId());
            if (!deptId.equals("") && !deptId.toLowerCase().equals("null")) {
                count += 1;
            }
            for (int i = 1; i <= 7; i++) {
                try {
                    String fileterName = "getType" + i;
                    Method getterMethod = tempSubRole.getClass().getMethod(
                            fileterName, new Class[]{});
                    String dataId = StaticMethod.nullObject2String(getterMethod
                            .invoke(tempSubRole, new Object[]{}), "");
                    if (!dataId.equals("")) {
                        count += 1;
                    }
                } catch (IllegalArgumentException e) {
                } catch (IllegalAccessException e) {
                } catch (InvocationTargetException e) {
                } catch (SecurityException e) {
                } catch (NoSuchMethodException e) {
                }
            }
            int tempCount = StaticMethod.nullObject2int(map.get("count"));
            if (count > tempCount || (count == 0 && tempCount == 0)) {
                map.put("count", "" + count + "");
                subRole = tempSubRole;
            }
        }
        return subRole;
    }

    /**
     * 转换页面获取信息的编码格式
     *
     * @param attMap
     * @param str
     * @return
     * @throws SheetException
     */
    public static Map SetEncodingParemeterMap(Map attMap, String str)
            throws SheetException {
        Map returnMap = new HashMap();
        Iterator ite = attMap.keySet().iterator();
        while (ite.hasNext()) {
            String name = (String) ite.next();
            Object value = (Object) attMap.get(name);
            String flag = "selfSheet";
            if (attMap.containsKey(flag)) {
                HashMap containsMap = (HashMap) attMap.get(flag);
                Iterator it = containsMap.keySet().iterator();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    Object mapvalue = (Object) containsMap.get(key);
                    String mapvalues = "";

                    if (mapvalue != null && mapvalue.getClass().isArray()) {
                        try {
                            mapvalues = new String(((String[]) mapvalue)[0].toString().getBytes("ISO-8859-1"), "UTF-8");
                            System.out.println(name + "============" + mapvalues);
                        } catch (UnsupportedEncodingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            mapvalues = new String(mapvalue.toString().getBytes("ISO-8859-1"), "UTF-8");
                            System.out.println(name + "============" + mapvalues);
                        } catch (UnsupportedEncodingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    containsMap.put(key, mapvalues);
                }
                returnMap.put(flag, containsMap);
            }

            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 清空map中的工单基本数据值
     *
     * @param attMap
     * @param str
     * @return
     * @throws SheetException
     */
    public static Map clearCommonSheetData(Map mainMap) throws Exception {
        if (mainMap != null) {
            for (Iterator it = mainMap.keySet().iterator(); it.hasNext(); ) {
                String mapKey = it.next().toString();

                if (mapKey.equals("id")) {
                    mainMap.put("id", "");
                } else if (mapKey.equals("sheetId")) {
                    mainMap.put("sheetId", "");
                } else if (mapKey.equals("sendTime")) {
                    mainMap.put("sendTime", "");
                } else if (mapKey.equals("piid")) {
                    mainMap.put("piid", "");
                } else if (mapKey.equals("correlationKey")) {
                    mainMap.put("correlationKey", "");
                } else if (mapKey.equals("parentCorrelation")) {
                    mainMap.put("parentCorrelation", "");
                } else if (mapKey.equals("sendYear")) {
                    mainMap.put("sendYear", "");
                } else if (mapKey.equals("sendMonth")) {
                    mainMap.put("sendMonth", "");
                } else if (mapKey.equals("sendDay")) {
                    mainMap.put("sendDay", "");
                } else if (mapKey.equals("sendDay")) {
                    mainMap.put("sendDay", "");
                } else {
                    continue;
                }
            }
        } else {
            throw new SheetException("map为空，请联系管理员!");
        }
        return mainMap;

    }


}
