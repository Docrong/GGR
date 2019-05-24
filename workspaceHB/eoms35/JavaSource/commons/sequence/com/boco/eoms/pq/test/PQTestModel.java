package com.boco.eoms.pq.test;

import java.io.Serializable;

public class PQTestModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9024656616558460081L;

	public PQTestModel() {
		super();
	}

	public void out(String arg0, String arg1) {
		System.out.println("***" + arg0 + "," + arg1);

	}
}
