package com.boco.eoms.common.oo;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

public interface DataObject extends java.io.Serializable {
    public abstract String getId();

    public abstract void setId(String id);
}