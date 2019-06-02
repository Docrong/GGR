package com.boco.eoms.sheet.base.flowchar;

import java.util.Comparator;

/**
 * @see 对Node排序
 * @author zhangxb
 * @version 1.0
 * @since 2008-09-09
 */

public class NodeComparator implements Comparator {

	public int compare(Object arg0, Object arg1) {
		// TODO 自动生成方法存根
		Node n0 = (Node) arg0;
		Node n1 = (Node) arg1;
		long code0 = StaticMethod.nullString2long(n0.getCode());
		long code1 = StaticMethod.nullString2long(n1.getCode());

		if (code0 > code1) {
			return 1;
		} else {
			return 0;
		}
	}
}
