package com.boco.eoms.km.core.crimson.util;

import java.util.ListIterator;

class XPathListIterator implements ListIterator {

	private Object[] objs = null;
	private int index;
	private int max;

	public XPathListIterator(Object[] objs, int max) {
		this.objs = objs;
		this.max = max;
	}

	public void add(Object o) {
	}

	public boolean hasNext() {
		return (this.index < this.max);
	}

	public boolean hasPrevious() {
		return (this.index > 0);
	}

	public Object next() {
		return this.objs[(this.index++)];
	}

	public int nextIndex() {
		return (this.index + 1);
	}

	public Object previous() {
		return this.objs[(this.index--)];
	}

	public int previousIndex() {
		return (this.index - 1);
	}

	public void remove() {
	}

	public void set(Object o) {
	}
}