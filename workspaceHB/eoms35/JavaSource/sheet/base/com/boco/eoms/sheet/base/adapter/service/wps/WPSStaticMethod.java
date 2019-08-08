/*
 * Created on 2007-8-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.adapter.service.wps;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

//import com.ibm.bpe.util.Base64;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.util.HibernateConfigurationHelper;
import com.boco.eoms.sheet.base.util.SheetUtils;

/**
 * @author IBM_USER
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class WPSStaticMethod {
    private static final String SOURCE_CLASS = WPSBusinessFlowAdapterServiceImpl.class
            .getName();

    private static final Logger LOGGER = Logger.getLogger(SOURCE_CLASS);

    private static final int _idLength = 16;

    private static final long ID_TIME = 0x0000FFFFFFFFFFFFL;

    private static final long ID_TYPE = 0x00FF000000000000L;

    private static final int ID_TYPE_SHIFT = 6 * 8;

    private static String[] _idPfx = {"_PT", "_AT", "_PI", "_AI", "_IT",
            "_TT", "_MT", "_QT", "_ET", "_GC", "_AL", "_WI", "_EI", "_QI",
            "_FT", "_SI", "_ST", "_CT", "_XT", "_VT", "_UT", "_RI", "_AF",
            "_SN", "_CS", "_TKT", "_TKI", "_ACO", "_EST", "_ESI", "_SVT",
            "_TMT", "_TMI", "_VS", "_PK", "_CO", "_PA", "_EHT", "_EHI", "_SVI"};

    /**
     * create a HashMap representation of the specified DataObject.
     *
     * @param dataObject the original DataObject
     * @return HashMap the HashMap representation of DataObject
     */
    public static HashMap createHashMap(DataObject dataObject) throws Exception {
        String SOURCE_METHOD = "createHashMap";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD,
                new Object[]{dataObject});
        HashMap map = new HashMap();
        /** 1 Go through the properties, and retrieve the values */
        List properties = dataObject.getType().getProperties();
        for (int i = 0; i < properties.size(); i++) {
            Property property = (Property) properties.get(i);
            String name = property.getName();
            /*******************************************************************
             * 1.1 If property not set, go to the next property if
             * (dataObject.get(name) == null) {
             *
             * //continue; }/
             *
             * /** 1.2 If property value is DataObject and many-valued
             */

            if (property.isContainment() && property.isMany()) {
                List dataObjectList = dataObject.getList(name);
                List hashmapList = new ArrayList();
                for (int j = 0; j < dataObjectList.size(); j++) {
                    DataObject childDataObject = (DataObject) dataObject.get(j);
                    HashMap childHashMap = createHashMap(childDataObject);
                    hashmapList.add(childHashMap);
                }
                map.put(name, hashmapList);

            }
            /** 1.3 If property value is DataObject */
            else if (property.isContainment()) {
                DataObject childDataObject = (DataObject) dataObject.get(name);
                HashMap childHashMap = createHashMap(childDataObject);
                map.put(name, childHashMap);

            }
            /** 1.4 If property value is many-valued */
            else if (property.isMany()) {
                List objectList = dataObject.getList(name);
                List hashmapList = new ArrayList();
                for (int j = 0; j < objectList.size(); j++) {
                    Object childObject = dataObject.get(j);
                    hashmapList.add(childObject);
                }
                map.put(name, hashmapList);
            }
            /** 1.5 If property value is simple Java Object */
            else {
                map.put(name, dataObject.get(name));
            }

        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, map);
        return map;
    }

    /**
     * construct a DataObject by walking through the HashMap. For each key of
     * the hashmap, it is mapped to one attribute of the dataobject; And the
     * value corresponding to the key is mapped to the attribute value
     *
     * @param dataObject the dataobject
     * @param map        the dataobject instance represented by hashmap
     */
    public static void createDataObject(DataObject dataObject, HashMap map) {
        String SOURCE_METHOD = "createDataObject";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{dataObject,
                map});
        /**
         * 1. GO through the HashMap, retrieve the key/value and set the
         * DataObject
         */
        Iterator keys = map.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            Object value = map.get(key);
            System.out.println("key=" + key);
            if (value == null) {
                System.out.println("value is null");
            } else {
                System.out.println("value=" + value.toString());
            }
            try {
                /** 1.1 if the value is still a HashMap, set the child BO */
                if (value instanceof HashMap) {
                    DataObject childDataObject = dataObject.createDataObject(key);
                    // DataObject childDataObject =createBusinessObject(key);
                    dataObject.set(key, childDataObject);
                    createDataObject(childDataObject, (HashMap) value);
                }
                /** 1.2 if the value is a list, means that it is a list attribute */
                else if (value instanceof List) {
                    List valueList = (List) value;
                    List valueList1 = dataObject.getList(key);
                    for (int i = 0; i < valueList.size(); i++) {
                        Object value1 = valueList.get(i);
                        /**
                         * 1.2.1 if the value is still a HashMap, means that it is a
                         * child BO
                         */
                        if (value1 instanceof HashMap) {
                            DataObject childDataObject = dataObject
                                    .createDataObject(key);
                            // DataObject childDataObject
                            // =createBusinessObject(key);
                            createDataObject(childDataObject, (HashMap) value1);
                            valueList1.add(childDataObject);
                        }
                        /**
                         * 1.2.2 If the value is a simple datatype, set the
                         * attribute directly
                         */
                        else {
                            valueList1.add(value1);
                        }
                    }
                }
                /** 1.3 If the value is a simple datatype, set the attribute directly */
                else {
                    dataObject.set(key, value);
                }
            } catch (java.lang.IllegalArgumentException err) {
                System.out.println("接口没有定义 " + key + " 参数");
                continue;
            }
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
    }

    public static DataObject createBusinessObject(String BoName) {
        commonj.sdo.DataObject dataObject = null;
//		com.ibm.websphere.sca.ServiceManager serviceManager = new com.ibm.websphere.sca.ServiceManager();
//		com.ibm.websphere.bo.BOFactory factory = (com.ibm.websphere.bo.BOFactory) serviceManager
//				.locateService("com/ibm/websphere/bo/BOFactory");
//		com.ibm.websphere.bo.BOXMLSerializer boSerializer = (com.ibm.websphere.bo.BOXMLSerializer) serviceManager
//				.locateService("com/ibm/websphere/bo/BOXMLSerializer");
//		com.ibm.wsspi.al.ALConfiguration anExtension = com.ibm.wsspi.al.ALConfigFactory
//				.create(
//						"RALL1",
//						com.ibm.wsspi.al.ALConfigFactory.REMOTE_WEBSPHERE_LOCATOR);
//		anExtension.set("server", "http://localhost:9080");
//		anExtension.set("application_name", "PvmApp");
//		try {
//			com.ibm.wsspi.al.ALContext.setContext(anExtension);
//			dataObject = factory.create("http://PvmLib", BoName);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        return dataObject;
    }

    /**
     * 获取对象各属性的取值
     *
     * @param dataObject
     * @return
     * @throws Exception
     */
    public static HashMap getNamesWithValueByMethods(Object dataObject)
            throws Exception {
        HashMap reMap = new HashMap();
        Method[] methods = dataObject.getClass().getMethods();
        for (int i = 0; methods.length > 0 && i < methods.length; i++) {
            Method method = methods[i];
            String name = method.getName();
            if (name.length() > 3 && name.indexOf("get") == 0) {
                String proName = name.substring(name.indexOf("get") + 4, name
                        .length());

                String firstLeter = name.substring(name.indexOf("get") + 3, 4);
                proName = firstLeter.toLowerCase() + proName;
                Object taskObject = null;
                if (method.getParameterTypes().length > 0) {
                    taskObject = method.invoke(dataObject,
                            new Object[]{Locale.CHINA});
                } else {
                    taskObject = method.invoke(dataObject, new Object[]{});
                }
                if (taskObject != null) {
                    String typeName = taskObject.getClass().getName();
                    if (typeName.equals("java.util.GregorianCalendar")) {
                        java.util.GregorianCalendar taskTime = (java.util.GregorianCalendar) taskObject;
                        java.sql.Timestamp time = SheetUtils
                                .tranferDateToDate(taskTime.getTime());
                        reMap.put(proName, time);
                    } else if (typeName.equals("java.util.Date")) {
                        java.util.Date taskTime = (java.util.Date) taskObject;
                        java.sql.Timestamp time = SheetUtils
                                .tranferDateToDate(taskTime);
                        reMap.put(proName, time);
                    } else {
//						String beanValues = StaticMethod
//								.toBaseEncoding(taskObject.toString());
                        reMap.put(proName, taskObject.toString());
                    }
                }
            }
        }
        return reMap;
    }

    /**
     * create a HashMap representation of the specified DataObject.
     *
     * @param dataObject the original DataObject
     * @return HashMap the HashMap representation of DataObject
     */
    public static HashMap getNamesWithValueByDataObject(DataObject dataObject)
            throws Exception {
        HashMap map = new HashMap();
        /** 1 Go through the properties, and retrieve the values */
        List properties = dataObject.getType().getProperties();
        for (int i = 0; i < properties.size(); i++) {
            Property property = (Property) properties.get(i);
            String name = property.getName();
            /** 1.1 If property not set, go to the next property */
            if (dataObject.get(name) == null) {
                continue;
            }
            /** 1.2 If property value is DataObject and many-valued */
            if (property.isContainment() && property.isMany()) {
                List dataObjectList = dataObject.getList(name);
                List hashmapList = new ArrayList();
                for (int j = 0; j < dataObjectList.size(); j++) {
                    DataObject childDataObject = (DataObject) dataObject.get(j);
                    HashMap childHashMap = getNamesWithValueByDataObject(childDataObject);
                    hashmapList.add(childHashMap);
                }
                map.put(name, hashmapList);
            }
            /** 1.3 If property value is DataObject */
            else if (property.isContainment()) {
                DataObject childDataObject = (DataObject) dataObject.get(name);
                HashMap childHashMap = getNamesWithValueByDataObject(childDataObject);
                map.put(name, childHashMap);

            }
            /** 1.4 If property value is many-valued */
            else if (property.isMany()) {
                List objectList = dataObject.getList(name);
                List hashmapList = new ArrayList();
                for (int j = 0; j < objectList.size(); j++) {
                    Object childObject = dataObject.get(j);
                    hashmapList.add(childObject);
                }
                map.put(name, hashmapList);
            }
            /** 1.5 If property value is simple Java Object */
            else {
                Object object = dataObject.get(name);
                String typeName = object.getClass().getName();
                if (typeName.equals("java.util.Date")) {
                    java.util.Date taskTime = (java.util.Date) object;
                    java.sql.Timestamp time = SheetUtils
                            .tranferDateToDate(taskTime);
                    map.put(name, time);
                } else {
                    String beanValues = StaticMethod.toBaseEncoding(object
                            .toString());
                    map.put(name, beanValues);
                }
            }
        }
//		Iterator iterator = map.keySet().iterator();
//		while (iterator.hasNext()) {
//			String key = (String) iterator.next();
//			Object object = map.get(key);
//			if (object == null) {
//				System.out.println("key=" + key + "value is null");
//			} else {
//				System.out.println("key=" + key + "value=" + object.toString());
//			}
//		}
        return map;
    }

    /**
     * 将各种任务信息（流程、业务）放置到task model对象中
     * 此方法已经不用
     *
     * @param task
     * @param methods
     * @param processTemplateMap
     * @param taskBOMap
     * @param taskObjectMap
     * @return
     * @throws Exception
     */
    public static Object setTaskModel(Object task, Method[] methods,
                                      HashMap processTemplateMap, HashMap taskBOMap, HashMap taskObjectMap)
            throws Exception {
        for (int i = 0; methods.length > 0 && i < methods.length; i++) {
            Method method = methods[i];
            String name = method.getName();
            Class returnType = method.getReturnType();
            if (name.length() > 3 && name.indexOf("get") == 0) {
                String proName = name.substring(name.indexOf("get") + 4, name
                        .length());
                String firstLeter = name.substring(name.indexOf("get") + 3, 4);
                proName = firstLeter.toLowerCase() + proName;
                String key = StaticMethod.nullObject2String(WPSQueryHelper
                        .getPropertyValue(WPSQueryHelper.getTaskModel(),
                                proName));
                if (key.equals("")) {
                    Object taskObjectTemp = taskObjectMap.get(proName);
                    String setterMethodName = "set"
                            + name.substring(name.indexOf("get") + 3, name
                            .length());
                    Method setterMethod = task.getClass().getMethod(
                            setterMethodName, new Class[]{returnType});
                    setterMethod.invoke(task, new Object[]{taskObjectTemp});
                } else if (key.indexOf("task.") >= 0) {
                    key = key.substring(key.lastIndexOf(".") + 1, key.length());
                    Object taskBOTemp = taskBOMap.get(key);
                    String setterMethodName = "set"
                            + name.substring(name.indexOf("get") + 3, name
                            .length());
                    Method setterMethod = task.getClass().getMethod(
                            setterMethodName, new Class[]{returnType});
                    setterMethod.invoke(task, new Object[]{taskBOTemp});
                } else if (key.indexOf("process.") >= 0) {
                    key = key.substring(key.lastIndexOf(".") + 1, key.length());
                    Object processTemp = processTemplateMap.get(key);
                    String setterMethodName = "set"
                            + name.substring(name.indexOf("get") + 3, name
                            .length());
                    Method setterMethod = task.getClass().getMethod(
                            setterMethodName, new Class[]{returnType});
                    setterMethod.invoke(task, new Object[]{processTemp});
                }
            }
        }
        return task;
    }

    public static String receiveMessage(String jmsCorrelationId) throws Exception {
        System.out.println("Receive Message... ");

        String receiveMessage = null;
        String selector = "JMSCorrelationID = '" + jmsCorrelationId + "'";
        String qcfName = "jms/eomsJmsFactory";
        String qnameIn = "jms/eomsQueue";
        boolean transacted = false;

        QueueSession qsession = null;
        QueueConnection qconnection = null;
        QueueConnectionFactory qcf = null;
        Queue inQueue = null;

        try {
            InitialContext ctx = new InitialContext();
            qcf = (QueueConnectionFactory) ctx.lookup(qcfName);
            inQueue = (Queue) ctx.lookup(qnameIn);

            // Create a connection
            qconnection = qcf.createQueueConnection();
            qconnection.start();

            // Create a session.
            qsession = qconnection.createQueueSession(transacted, Session.AUTO_ACKNOWLEDGE);
            // Create a QueueSender

            QueueReceiver qReceiver = qsession.createReceiver(inQueue, selector);

            // Create a message to send to the queue...
            TextMessage tmessage = (TextMessage) qReceiver.receive(5000); //以毫秒为单位
            receiveMessage = StaticMethod.nullObject2String(tmessage.getText());

            // Close the connection (close calls will cascade to other objects)
            qReceiver.close();
            qsession.close();
            qconnection.close();
            qconnection = null;

            System.out.println("Message Receive done");
        } catch (JMSException e) {
            System.out.println("Receive JMSException - send message failed with " + e);
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            System.out.println("Receive MDB exception: " + e);
            e.printStackTrace();
            throw e;
        } finally { // Ensure that the Connection always gets closed
            if (qconnection != null) {
                try {
                    qconnection.close();
                } catch (JMSException e) {
                }
            }
        }
        return receiveMessage;
    }


    /**
     * 各种流程ID的转换方法（piid、tkid、aiid等） 将流程数据库中的存储格式转换成API中可以获取的存储格式
     *
     * @param ids
     * @return
     */
    public static String tranferIdByteToString(byte[] ids) throws Exception {
        String id = null;
        // get the id as a byte[] from database.
        byte[] idAsByteArray = ids;
        if (idAsByteArray.length > _idLength)
            throw new RuntimeException("Wrong Byte Array Length for ID");
        if (idAsByteArray.length < _idLength) {
            // this may happen if a DBMS truncates trailing zeros
            // create a new byte array with the right length
            // (assuming the not-set bytes are initialized with zero)
            byte[] byteArray = new byte[_idLength];
            System.arraycopy(idAsByteArray, 0, byteArray, 0,
                    idAsByteArray.length);
            // replace byte array
            idAsByteArray = byteArray;
        }

        // decompose array to long values
        // Attention: the value range of byte is [-128,+127]
        // Simply casting a byte to a long results not in the desired effect.
        // Therefore, for the casted value, first do a &0xFF to get
        // the original value back!
        //
        long id1 = ((long) idAsByteArray[7] & 0xFF)
                + ((((long) idAsByteArray[6]) & 0xFF) << 8)
                + ((((long) idAsByteArray[5]) & 0xFF) << 16)
                + ((((long) idAsByteArray[4]) & 0xFF) << 24)
                + ((((long) idAsByteArray[3]) & 0xFF) << 32)
                + ((((long) idAsByteArray[2]) & 0xFF) << 40)
                + ((((long) idAsByteArray[1]) & 0xFF) << 48)
                + ((((long) idAsByteArray[0]) & 0xFF) << 56);

        long id2 = ((long) idAsByteArray[15] & 0xFF)
                + ((((long) idAsByteArray[14]) & 0xFF) << 8)
                + ((((long) idAsByteArray[13]) & 0xFF) << 16)
                + ((((long) idAsByteArray[12]) & 0xFF) << 24)
                + ((((long) idAsByteArray[11]) & 0xFF) << 32)
                + ((((long) idAsByteArray[10]) & 0xFF) << 40)
                + ((((long) idAsByteArray[9]) & 0xFF) << 48)
                + ((((long) idAsByteArray[8]) & 0xFF) << 56);

        int idType = (int) ((id1 & ID_TYPE) >> ID_TYPE_SHIFT);
        int i1 = (int) (id1 >> 32);
        int i2 = (int) (id1 & 0xFFFFFFFF);
        int i3 = (int) (id2 >> 32);
        int i4 = (int) (id2 & 0xFFFFFFFF);

        StringBuffer idString = new StringBuffer(32);
        idString.append(_idPfx[idType - 1]);
        idString.append(':');
        idString.append(Integer.toHexString(i1));
        idString.append('.');
        idString.append(Integer.toHexString(i2));
        idString.append('.');
        idString.append(Integer.toHexString(i3));
        idString.append('.');
        idString.append(Integer.toHexString(i4));

        id = idString.toString();
        return id;
    }

    /**
     * 各种流程ID的转换方法（piid、tkid、aiid等） 将流程数据库中的存储格式转换成API中可以获取的存储格式
     *
     * @param ids
     * @return
     */
    public static String tranferDBToAction(String ids) throws Exception {
        String id = null;

        // get the id as a byte[] from database.
//		byte[] idAsByteArray = Base64.decode(ids.trim());
//		if (idAsByteArray.length > _idLength)
//			throw new RuntimeException("Wrong Byte Array Length for ID");
//		if (idAsByteArray.length < _idLength) {
//			// this may happen if a DBMS truncates trailing zeros
//			// create a new byte array with the right length
//			// (assuming the not-set bytes are initialized with zero)
//			byte[] byteArray = new byte[_idLength];
//			System.arraycopy(idAsByteArray, 0, byteArray, 0,
//					idAsByteArray.length);
//			// replace byte array
//			idAsByteArray = byteArray;
//		}
//
//		// decompose array to long values
//		// Attention: the value range of byte is [-128,+127]
//		// Simply casting a byte to a long results not in the desired effect.
//		// Therefore, for the casted value, first do a &0xFF to get
//		// the original value back!
//		//
//		long id1 = ((long) idAsByteArray[7] & 0xFF)
//				+ ((((long) idAsByteArray[6]) & 0xFF) << 8)
//				+ ((((long) idAsByteArray[5]) & 0xFF) << 16)
//				+ ((((long) idAsByteArray[4]) & 0xFF) << 24)
//				+ ((((long) idAsByteArray[3]) & 0xFF) << 32)
//				+ ((((long) idAsByteArray[2]) & 0xFF) << 40)
//				+ ((((long) idAsByteArray[1]) & 0xFF) << 48)
//				+ ((((long) idAsByteArray[0]) & 0xFF) << 56);
//
//		long id2 = ((long) idAsByteArray[15] & 0xFF)
//				+ ((((long) idAsByteArray[14]) & 0xFF) << 8)
//				+ ((((long) idAsByteArray[13]) & 0xFF) << 16)
//				+ ((((long) idAsByteArray[12]) & 0xFF) << 24)
//				+ ((((long) idAsByteArray[11]) & 0xFF) << 32)
//				+ ((((long) idAsByteArray[10]) & 0xFF) << 40)
//				+ ((((long) idAsByteArray[9]) & 0xFF) << 48)
//				+ ((((long) idAsByteArray[8]) & 0xFF) << 56);
//
//		int idType = (int) ((id1 & ID_TYPE) >> ID_TYPE_SHIFT);
//		int i1 = (int) (id1 >> 32);
//		int i2 = (int) (id1 & 0xFFFFFFFF);
//		int i3 = (int) (id2 >> 32);
//		int i4 = (int) (id2 & 0xFFFFFFFF);
//
//		StringBuffer idString = new StringBuffer(32);
//		idString.append(_idPfx[idType - 1]);
//		idString.append(':');
//		idString.append(Integer.toHexString(i1));
//		idString.append('.');
//		idString.append(Integer.toHexString(i2));
//		idString.append('.');
//		idString.append(Integer.toHexString(i3));
//		idString.append('.');
//		idString.append(Integer.toHexString(i4));
//
//		id = idString.toString();
        return id;
    }

    /**
     * 各种流程ID的转换方法（piid、tkid、aiid等） 将API中可以获取的存储格式转换成流程数据库中的存储格式
     *
     * @param ids
     * @return
     */
    public static byte[] transferActionToDB(String ids) throws Exception {
        byte[] _idAsByteArray = null;
        if (ids != null && !ids.equals("")) {
            StringTokenizer tok = new StringTokenizer(ids, ":.");

            // 检查id的合法性
            if (tok.hasMoreTokens()) {
                String pfx = tok.nextToken();
                System.out.println("first string=" + pfx);
                boolean bValidPrefix = false;
                // Check for known prefixes
                for (int i = 0; i < _idPfx.length; i++) {
                    if (pfx.equals(_idPfx[i])) {
                        bValidPrefix = true;
                        break;
                    }
                }
                // If prefix is non in list ...
                if (!bValidPrefix) {
                    throw new Exception("this id cannot be a valid ID!");
                }
            } else {
                // No Id separator (. or :) found, cannot be a valid ID
                throw new Exception("this id cannot be a valid ID!");
            }

            // Read four ID hex values, convert the to long values in id[]
            // and finally fill the two long values representing the Id.
            long idKey[] = new long[4];
            for (int i = 0; i < 4; i++) {
                if (!tok.hasMoreTokens()) {
                    throw new Exception("id ");
                }
                try {
                    idKey[i] = hexStringToInt(tok.nextToken());
                } catch (NumberFormatException exc) {
                    throw new Exception("id`s format is wrong!");
                }
            }
            long id1 = (idKey[0] << 32) + idKey[1];
            long id2 = (idKey[2] << 32) + idKey[3];
            // create a new byte array and decompose the
            // two 64 Bit long values
            _idAsByteArray = new byte[_idLength];
            _idAsByteArray[0] = (byte) ((id1 & 0xFF00000000000000L) >> 56);
            _idAsByteArray[1] = (byte) ((id1 & 0xFF000000000000L) >> 48);
            _idAsByteArray[2] = (byte) ((id1 & 0xFF0000000000L) >> 40);
            _idAsByteArray[3] = (byte) ((id1 & 0xFF00000000L) >> 32);
            _idAsByteArray[4] = (byte) ((id1 & 0xFF000000L) >> 24);
            _idAsByteArray[5] = (byte) ((id1 & 0xFF0000L) >> 16);
            _idAsByteArray[6] = (byte) ((id1 & 0xFF00L) >> 8);
            _idAsByteArray[7] = (byte) ((id1 & 0xFFL));
            _idAsByteArray[8] = (byte) ((id2 & 0xFF00000000000000L) >> 56);
            _idAsByteArray[9] = (byte) ((id2 & 0xFF000000000000L) >> 48);
            _idAsByteArray[10] = (byte) ((id2 & 0xFF0000000000L) >> 40);
            _idAsByteArray[11] = (byte) ((id2 & 0xFF00000000L) >> 32);
            _idAsByteArray[12] = (byte) ((id2 & 0xFF000000L) >> 24);
            _idAsByteArray[13] = (byte) ((id2 & 0xFF0000L) >> 16);
            _idAsByteArray[14] = (byte) ((id2 & 0xFF00L) >> 8);
            _idAsByteArray[15] = (byte) ((id2 & 0xFFL));
        }

        return _idAsByteArray;
    }

    /**
     * Read an integer in its hexadecimal string representation and return the
     * integer value. Function Integer.parseInt( xxx, 16 ) would do a similar
     * operation, however, seems to fail when parsing values that result in
     * negative integers.
     *
     * @param strInt The hex representation of an integer value. Usually returned
     *               by a call to <tt>Integer.toHexString()</TT>
     * @return The integer (long) value of the string representation. Will
     * be > 0.
     * @throws NumberFormatException If an error occurs when parsing the hex string.
     */
    private static final long hexStringToInt(String strInt)
            throws NumberFormatException {
        long result = 0;
        for (int i = 0; i < strInt.length(); i++) {
            char c = strInt.charAt(i);
            int val = 0;
            if (c >= '0' && c <= '9') {
                val = c - '0';
            } else if (c >= 'a' && c <= 'f') {
                val = 10 + (c - 'a');
            } else if (c >= 'A' && c <= 'F') {
                val = 10 + (c - 'A');
            } else {
                throw new NumberFormatException(strInt);
            }
            result = (result << 4) + val;
        }
        return result;
    }

    public static void main(String[] args) {
//		try {
//			// String
//			String tkid=Base64.encode(transferActionToDB("_TKI:a01b011b.687cf8a3.f4f66af5.ce00fc70"));
//
//			String idtk = tranferDBToAction("oBsBG2hDOCf09mr1zgAAAg==");
//			System.out.println("idtk=" + tkid+"idtk="+idtk);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
    }
}
