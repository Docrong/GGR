/*
 * Created on 2007-8-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.exception.SheetException;

/**
 * <p>
 * Title:提供工单的populate方法
 * </p>
 * <p>
 * Description:之所以不用BeanUtils的方法，是由于诸如java.util.Date这样的属性BeanUtil方法无法进行赋值
 * </p>
 * <p>
 * Date:2007-8-3 14:55:03
 * </p>
 * 
 * @author 陈元蜀
 * @version 1.0
 * 
 */
public class SheetBeanUtils extends BeanUtils {

	public static void populate(Object bean, Map properties)
			throws IllegalAccessException, InvocationTargetException {

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (properties == null)) {
			return;
		}

		// Loop through the property name/value pairs to be set
		Iterator names = properties.keySet().iterator();
		while (names.hasNext()) {

			String name = (String) names.next();

			// Identify the property name and value(s) to be assigned
			if (name == null) {
				continue;
			}
			Object value = properties.get(name);

			try {
				Class clazz = PropertyUtils.getPropertyType(bean, name);

				if (null == clazz) {
					continue;
				}
				String className = clazz.getName();

				if (className.equalsIgnoreCase("java.util.Date")) {
					if (value == null || value.equals("")) {
						continue;
					} else {
						String setMethod = "set"
								+ StaticMethod.firstToUpperCase(name);
						Method setterMethod = bean.getClass().getMethod(
								setMethod, new Class[] { Date.class });

						if (value != null && value.getClass().isArray()) {
							value = ((Object[]) value)[0];
						}
						if (value == null || value instanceof String) {

							String dateString = StaticMethod
									.nullObject2String(value);
							try {
								Date date = SheetUtils.stringToDate(dateString);
								setterMethod
										.invoke(bean, new Object[] { date });
							} catch (SheetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							setterMethod.invoke(bean, new Object[] { value });
						}
					}
				} else {
					setProperty(bean, name, value);
				}
			} catch (NoSuchMethodException e) {
				continue;
			}
		}
	}

	public static void populateDataObj2ReqMap(Map properties, Object bean)
			throws IllegalAccessException, InvocationTargetException {

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (properties == null)) {
			return;
		}

		// Loop through the property name/value pairs to be set
		Iterator names = properties.keySet().iterator();
		while (names.hasNext()) {

			String name = (String) names.next();

			// Identify the property name and value(s) to be assigned
			if (name == null) {
				continue;
			}
			Object value = properties.get(name);

			try {
				Class clazz = PropertyUtils.getPropertyType(bean, name);

				if (null == clazz) {
					continue;
				}
				String getMethod = "get" + StaticMethod.firstToUpperCase(name);
				Method getterMethod = bean.getClass().getMethod(getMethod,
						new Class[] {});
				Object beanValue = getterMethod.invoke(bean, new Object[] {});
				if (value != null) {
					properties.put(name, value);
				} else {
					properties.put(name, beanValue);
				}
			} catch (NoSuchMethodException e) {
				continue;
			}
		}
	}
	/**
	 * EngineMap中的日期转换时格式不正确，需要单独format成yyyy-MM-dd HH:mm:ss格式
	 * 
	 */
	public static void populateEngineMap2Bean(Object bean, Map properties)
		throws IllegalAccessException, InvocationTargetException {
		Iterator names = properties.keySet().iterator();
		while (names.hasNext()) {
			String name = (String) names.next();
			Object value = properties.get(name);
			if (value != null) {
				Class clazz = value.getClass();
				String className = clazz.getName();
				if (className.equalsIgnoreCase("java.util.Date")) {
					DateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String datestr = df.format((Date) value);
					properties.put(name, datestr);
				}
			}
		}
		populateMap2Bean(bean, properties);
	}
	public static void populateMap2Bean(Object bean, Map properties)
			throws IllegalAccessException, InvocationTargetException {

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (properties == null)) {
			return;
		}

		// Loop through the property name/value pairs to be set
		Iterator names = properties.keySet().iterator();
		while (names.hasNext()) {

			String name = (String) names.next();

			// Identify the property name and value(s) to be assigned
			if (name == null) {
				continue;
			}
			Object value = properties.get(name);
			if (value != null && value.getClass().isArray()) {
				value = ((Object[]) value)[0];
			}
			try {
				Class clazz = PropertyUtils.getPropertyType(bean, name);

				if (null == clazz) {
					continue;
				}
				String className = clazz.getName();

				System.out.println(bean.getClass().getName()+"=====name====="+name);
				if (className.equalsIgnoreCase("java.util.Date")) {
					if (value == null || value.equals("")) {
						continue;
					} else {
						String setMethod = "set"
								+ StaticMethod.firstToUpperCase(name);
						Method setterMethod = bean.getClass().getMethod(
								setMethod, new Class[] { Date.class });

						try {
							setterMethod.invoke(bean,new Object[] { 
									(Object) SheetUtils.stringToDate(StaticMethod.nullObject2String(value)) 
									});
						} catch (IllegalArgumentException e1) {
							e1.printStackTrace();
						} catch (SheetException e1) {
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							e1.printStackTrace();
						} catch (InvocationTargetException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					setProperty(bean, name, value);
				}
			} catch (NoSuchMethodException e) {
				continue;
			}
		}
	}

	public static void populateMap2Map(Map bean, Map properties)
			throws IllegalAccessException, InvocationTargetException {

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (properties == null)) {
			return;
		}

		// Loop through the property name/value pairs to be set
		Iterator names = bean.keySet().iterator();
		while (names.hasNext()) {

			String name = (String) names.next();

			// Identify the property name and value(s) to be assigned
			if (name == null) {
				continue;
			}
			Object value = properties.get(name);
			if (null != value) {
				bean.put(name, value);
			}
		}
	}

	public static HashMap bean2Map(Object obj) {

		HashMap map = new HashMap();
		String pkid = HibernateConfigurationHelper.getPkColumnName(obj.getClass());
		String pro = HibernateConfigurationHelper.getPropNamesWithoutPK(obj.getClass());
		String reString = pkid + "," +pro;
		if (!reString.equals("")){
			String[] attNames  = reString.split(",");
			for (int i =0;attNames!=null && i<attNames.length;i++){
				String getMethodName = "get"+StaticMethod.firstToUpperCase(attNames[i]);
				try {
					Method getMethod = obj.getClass().getMethod(getMethodName, null);
				    Object property = getMethod.invoke(obj, null);
				    if(property!=null){
				    	map.put(attNames[i], property);
				    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		/**
		 * modify by chenyuanshu  2009-01-09 15:35:59 begin
		 * 因为工单对象实际存在多层继承关系，所以无法使用以下方法将对象转换成map对象
		 *
		 * 
		 * 		 
		Method[] methodList = obj.getClass().getDeclaredMethods();
		Method[] superMethods = obj.getClass().getSuperclass()
				.getDeclaredMethods();
		try {
			for (int i = 0; i < methodList.length; i++) {
				String method = ((Method) methodList[i]).getName();
				if (method.startsWith("get")) {
					Object cat = methodList[i].invoke(obj, null);

					if (cat != null) {
						String name = ((Method) methodList[i]).getName()
								.substring(4);
						name = ((Method) methodList[i]).getName().substring(3,
								4).toLowerCase()
								+ name;
						map.put(name, cat);
					}
				}
			}

			for (int i = 0; i < superMethods.length; i++) {
				String method = ((Method) superMethods[i]).getName();
				if (method.startsWith("get")) {
					Object cat = superMethods[i].invoke(obj, null);

					if (cat != null) {
						String name = ((Method) superMethods[i]).getName()
								.substring(4);
						name = ((Method) superMethods[i]).getName().substring(
								3, 4).toLowerCase()
								+ name;
						map.put(name, cat);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		*modify by chenyuanshu  2009-01-09 15:35:59 end
		*/
		return map;
	}

	public static HashMap bean2MapWithNull(Object obj) {
		
		HashMap map = new HashMap();
		String pkid = HibernateConfigurationHelper.getPkColumnName(obj.getClass());
		String pro = HibernateConfigurationHelper.getPropNamesWithoutPK(obj.getClass());
		String reString = pkid + "," +pro;
		if (!reString.equals("")){
			String[] attNames  = reString.split(",");
			for (int i =0;attNames!=null && i<attNames.length;i++){
				String getMethodName = "get"+StaticMethod.firstToUpperCase(attNames[i]);
				try {
					Method getMethod = obj.getClass().getMethod(getMethodName, null);
				    Object property = getMethod.invoke(obj, null);
				    map.put(attNames[i], property);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return map;

		/**
		 * 同上类
		 * 		
		HashMap map = new HashMap();
		Method[] methodList = obj.getClass().getDeclaredMethods();
		Method[] superMethods = obj.getClass().getSuperclass()
				.getDeclaredMethods();
		try {
			for (int i = 0; i < methodList.length; i++) {
				String method = ((Method) methodList[i]).getName();
				if (method.startsWith("get")) {
					Object cat = methodList[i].invoke(obj, null);

					String name = ((Method) methodList[i]).getName().substring(
							4);
					name = ((Method) methodList[i]).getName().substring(3, 4)
							.toLowerCase()
							+ name;
					map.put(name, cat);
				}
			}

			for (int i = 0; i < superMethods.length; i++) {
				String method = ((Method) superMethods[i]).getName();
				if (method.startsWith("get")) {
					Object cat = superMethods[i].invoke(obj, null);

					String name = ((Method) superMethods[i]).getName()
							.substring(4);
					name = ((Method) superMethods[i]).getName().substring(3, 4)
							.toLowerCase()
							+ name;
					map.put(name, cat);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
		*/
	}

	public static void populateWithTrim(Object bean, Map properties)
			throws IllegalAccessException, InvocationTargetException {

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (properties == null)) {
			return;
		}

		// Loop through the property name/value pairs to be set
		Iterator names = properties.keySet().iterator();
		while (names.hasNext()) {

			String name = (String) names.next();

			// Identify the property name and value(s) to be assigned
			if (name == null) {
				continue;
			}
			Object value = properties.get(name);

			try {
				Class clazz = PropertyUtils.getPropertyType(bean, name);

				if (null == clazz) {
					continue;
				}
				String className = clazz.getName();

				if (className.equalsIgnoreCase("java.util.Date")) {
					if (value == null || value.equals("")) {
						continue;
					} else {
						String setMethod = "set"
								+ StaticMethod.firstToUpperCase(name);
						Method setterMethod = bean.getClass().getMethod(
								setMethod, new Class[] { Date.class });

						if (value != null && value.getClass().isArray()) {
							value = ((Object[]) value)[0];
						}
						if (value == null || value instanceof String) {

							String dateString = StaticMethod
									.nullObject2String(value);
							try {
								Date date = SheetUtils.stringToDate(dateString);
								setterMethod
										.invoke(bean, new Object[] { date });
							} catch (SheetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							setterMethod.invoke(bean, new Object[] { value });
						}
					}
				} else if (className.equalsIgnoreCase("java.lang.String")) {
					if (value != null) {
						String temp = StaticMethod.nullObject2String(value);
						setProperty(bean, name, temp.trim());
					} else {
						setProperty(bean, name, value);
					}

				} else {
					setProperty(bean, name, value);
				}
			} catch (NoSuchMethodException e) {
				continue;
			}catch(IllegalArgumentException err){
				continue;
			}
		}
	}

	/**
	 * 将数据库对象进行trim处理，这样是为了防止在informix数据库中的char类型字段，导致界面被填充，风格难看
	 * @param dbObject 数据库中查询出来的hibernate对象
	 * @param model    空的hibernate model对象
	 */
	public static void eomsDB2Model(Object dbObject, Object model) {
		try {
			HashMap dbObjectMap = bean2Map(dbObject);
			populateWithTrim(model, dbObjectMap);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
