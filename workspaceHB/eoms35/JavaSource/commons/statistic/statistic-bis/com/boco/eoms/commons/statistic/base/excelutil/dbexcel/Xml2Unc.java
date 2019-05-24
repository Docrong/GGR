package com.boco.eoms.commons.statistic.base.excelutil.dbexcel;

import java.sql.Connection;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Title:        
 * Description:  
 * Copyright:    Copyright (c) 2002
 * Company:      UNC
 * @author      zhouj@unc.com.cn
 * @version     1.0
 */
public class Xml2Unc implements Tags {
	private Flopper flopper = null;
	private DomUtter utter = null;
	private Document uncDoc = null;
	private Connection connection = null;

	Xml2Unc() {
		utter = new DomUtter();
		uncDoc = utter.newDocument();
	}

	Xml2Unc(Flopper tFlopper) {
		this();
		flopper = tFlopper;
	}

	public void setConnection(Connection conn) {
		connection = conn;
	}

	public void setFlopper(Flopper tFlopper) {
		flopper = tFlopper;
	}

	private Document getDocument(String uri) {
		Document doc = utter.parseDocument(uri);
		try {
			solveDomTree(doc, -1, false);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		return uncDoc;
	}

	public void createUncXml(String uri) {
		utter.printDOMTree(getDocument(uri));
	}

	private Element table = null, row = null, column = null;

	private void solveDomTree(Node node, int index, boolean data)
		throws Exception {
		int type = node.getNodeType();
		boolean hasCol = false, hasData = false;

		switch (type) {
			// print the document element
			case Node.DOCUMENT_NODE :
				{
					table = uncDoc.createElement(UNC_TABLE);
					table.setAttribute("encoding", "gb2312");
					uncDoc.appendChild(table);
					solveDomTree(
						((Document) node).getDocumentElement(),
						-1,
						false);
					break;
				}

				// print element with attributes
			case Node.ELEMENT_NODE :
				{
					if (node.getNodeName().equals(EXCEL_ROW)) {
						row = uncDoc.createElement(UNC_ROW);
						table.appendChild(row);
						hasCol = true;
					} else if (node.getNodeName().equals(EXCEL_CELL)) {
						if (!node.hasChildNodes()) {
							String text =
								flopper.conversionOp(
									connection,
									null,
									index,
									uncDoc,
									table,
									row);
							//							column = uncDoc.createElement(UNC_COL + index);
							//							column.appendChild(uncDoc.createTextNode(text));
							//							row.appendChild(column);
						}
						//hasCol = true;
					} else if (node.getNodeName().equals(EXCEL_DATA)) {
						column = uncDoc.createElement(UNC_COL + index);
						row.appendChild(column);
						hasData = true;
					}
					NodeList children = node.getChildNodes();
					if (children != null) {
						int len = children.getLength();
						for (int i = 0; i < len; i++)
							if (hasData)
								solveDomTree(children.item(i), index, true);
							else if (hasCol)
								solveDomTree(
									children.item(i),
									(int) (i + 1) / 2,
									false);
							else
								solveDomTree(children.item(i), index, false);
					}

					break;
				}

				// handle entity reference nodes
			case Node.ENTITY_REFERENCE_NODE :
				{
					break;
				}

				// print cdata sections
			case Node.CDATA_SECTION_NODE :
				{
					break;
				}

				// print text
			case Node.TEXT_NODE :
				{
					if (data) {
						String text =
							flopper.conversionOp(
								connection,
								node.getNodeValue().trim(),
								index,
								uncDoc,
								table,
								row);
						column.appendChild(uncDoc.createTextNode(text));
					}
					break;
				}

				// print processing instruction
			case Node.PROCESSING_INSTRUCTION_NODE :
				{
					break;
				}
		}
	}
}
