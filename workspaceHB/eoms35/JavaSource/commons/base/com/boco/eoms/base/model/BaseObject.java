package com.boco.eoms.base.model;

import java.io.Serializable;

/**
 * Base class for Model objects. Child objects should implement toString(),
 * equals() and hashCode();
 * 
 * <p>
 * <a href="BaseObject.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public abstract class BaseObject implements Serializable {
	public String toString() {
		return super.toString();
	}

	public abstract boolean equals(Object o);

	public int hashCode() {
		return super.hashCode();
	}
}
