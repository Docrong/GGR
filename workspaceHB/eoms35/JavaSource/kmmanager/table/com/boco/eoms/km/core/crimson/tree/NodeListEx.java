package com.boco.eoms.km.core.crimson.tree;

import java.io.Serializable;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeListEx implements NodeList, Serializable {

	private static final long serialVersionUID = 1L;
	private int cursor;
	private int max = 10;
	private Object[] nodes = new Object[10];

	public int getLength() {
		return this.cursor;
	}

	public Node item(int index) {
		return ((Node) this.nodes[index]);
	}

	public void append(Node node) {
		this.nodes[this.cursor] = node;
		this.cursor += 1;
		if (this.cursor >= this.max)
			expand();
	}

	public void expand() {
		Object[] oldObjs = this.nodes;
		this.max += 10;
		this.nodes = new Object[this.max];
		System.arraycopy(oldObjs, 0, this.nodes, 0, this.cursor);
	}

}