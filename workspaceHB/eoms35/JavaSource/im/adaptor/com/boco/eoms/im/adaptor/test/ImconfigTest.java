package com.boco.eoms.im.adaptor.test;

import junit.framework.TestCase;

import com.boco.eoms.im.adaptor.util.Imconfig;
import com.boco.eoms.im.adaptor.util.Imlocator;

public class ImconfigTest extends TestCase{

	public void testIm() {
		Imconfig imconfig;
		imconfig=Imlocator.ImConfigInstance();
		System.out.println(imconfig.getIp());
		System.out.println(imconfig.getHostname());
		System.out.println(imconfig.getPort());
		System.out.println(imconfig.getResource());

	}

}
