/*
 * Created on 2007-8-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util;

import org.hibernate.Hibernate;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;
import org.hibernate.util.PropertiesHelper;
import org.hibernate.id.AbstractUUIDGenerator;
import java.io.Serializable;
import java.util.Properties;
/**
  * <p>
 * Title:产生UUID
 * </p>
 * <p>
 * Description:由于数据存储的方法在引擎中完成，当引擎中采用jdbc方式保存数据时，就会需要我们手动进行赋值
 * 目前该方法还用于给correlationKey赋值
 * </p>
 * <p>
 * Date:2007-8-3 14:55:03
 * </p>
 * @author 陈元蜀 
 * @version 1.0
 */
public class UUIDHexGenerator extends AbstractUUIDGenerator implements Configurable{
	  private String sep = "";
	  private static UUIDHexGenerator hexGenerator = null;
	  private static Properties props = new Properties();
	  private static IdentifierGenerator gen = new UUIDHexGenerator();

	  public static UUIDHexGenerator getInstance() {
	    if (hexGenerator == null) {
	      hexGenerator = new UUIDHexGenerator();
	    }
	    return hexGenerator;
	  }

	  private UUIDHexGenerator() {
	    try {
	      props.setProperty("seperator", "");
	      ( (Configurable) gen).configure(Hibernate.STRING, props, null);
	    }
	    catch (Exception e) {

	    }
	  }


	  protected String format(int intval) {
	    String formatted = Integer.toHexString(intval);
	    StringBuffer buf = new StringBuffer("00000000");
	    buf.replace(8 - formatted.length(), 8, formatted);
	    return buf.toString();
	  }

	  protected String format(short shortval) {
	    String formatted = Integer.toHexString(shortval);
	    StringBuffer buf = new StringBuffer("0000");
	    buf.replace(4 - formatted.length(), 4, formatted);
	    return buf.toString();
	  }

	  public Serializable generate(SessionImplementor cache, Object obj) {
	    return new StringBuffer(36)
	        .append(format(getIP())).append(sep)
	        .append(format(getJVM())).append(sep)
	        .append(format(getHiTime())).append(sep)
	        .append(format(getLoTime())).append(sep)
	        .append(format(getCount()))
	        .toString();
	  }

	  public String getID() throws Exception{
	    return (String) gen.generate(null, null);
	  }

	  public static void main(String[] args) throws Exception {
	    Properties props = new Properties();
	    props.setProperty("seperator", "");
	    IdentifierGenerator gen = new UUIDHexGenerator();
	    ( (Configurable) gen).configure(Hibernate.STRING, props, null);
	    IdentifierGenerator gen2 = new UUIDHexGenerator();
	    ( (Configurable) gen2).configure(Hibernate.STRING, props, null);

	    for (int i = 0; i < 10; i++) {
	      String id = (String) gen.generate(null, null);
	      System.out.println(id + ": " + id.length());
	      String id2 = (String) gen2.generate(null, null);
	      System.out.println(id2 + ": " + id2.length());
	    }
	  }

	  public void configure(Type type, Properties params, Dialect d) {
	    sep = PropertiesHelper.getString("seperator", params,"");
	  }

}
