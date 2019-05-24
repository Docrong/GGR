package com.boco.eoms.km.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.boco.eoms.km.core.dataservice.clause.CriteriaNode;
import com.boco.eoms.km.core.dataservice.clause.FieldNode;
import com.boco.eoms.km.core.dataservice.database.RelationalDB;
import com.boco.eoms.km.core.dataservice.map.EntityMap;
import com.boco.eoms.km.core.dataservice.map.FieldMap;
import com.boco.eoms.km.core.util.Function;
import com.boco.eoms.km.core.util.Operate;

public class ElementTranslator {

	private boolean isDefaultCriteria = false;
	private int defaultFunc = Function.NOT_FUNCTION;

	private int defaultOpe = Operate.EQUAL; // 设置默认的查询条件为"="，值为2
	private boolean isDefaultField;

	private String cond;

	private XMLTranslator xmlTrans = new XMLTranslator();
	private RelationalDB dataBase = null;
	private EntityMap entityMap = null;
	private Element element = null;

	// 用来存储转换后的 SELECT 字段
	private List fieldNodeList = new ArrayList();
	// 用来存储转换后的 WHERE 条件
	private List criteriaNodeList = new ArrayList();
	
	public ElementTranslator(EntityMap entityMap, Element aElement)
			throws Exception {
		this.dataBase = RelationalDB.getInstance();
		this.entityMap = entityMap;
		this.element = aElement;
		searchEntityMap(); // 未实现
		fetchHeader();
	}

	public ElementTranslator(RelationalDB aDataBase, EntityMap entityMap,
			Element aElement) throws Exception {
		this.dataBase = aDataBase;
		this.entityMap = entityMap;
		this.element = aElement;
		searchEntityMap(); // 未实现
		fetchHeader(); // 未实现
	}

	private void searchEntityMap() throws Exception {
	}

	void fetchHeader() throws Exception {
		String fieldValue = this.xmlTrans.getNodeAttr(this.element, "field");
		if (fieldValue == null) {
			this.isDefaultField = false;
		} else {
			this.isDefaultField = true;
			this.defaultFunc = Function.convertStringToInt(fieldValue);
		}

		String criteriaValue = this.xmlTrans.getNodeAttr(this.element,
				"criteria");
		if (criteriaValue == null) {
			this.isDefaultCriteria = false;
		} else {
			this.isDefaultCriteria = true;
			this.defaultOpe = Operate.convertStringToInt(criteriaValue);
		}
	}

	protected void parseFind() throws Exception {
		this.isDefaultField = true;
		// 设置默认的查询条件为"="，值为2
		this.defaultOpe = Operate.EQUAL;
		NodeList nodeList = this.element.getChildNodes();
		int length = nodeList.getLength();
		for (int i = 0; i < length; ++i) {
			Node node = nodeList.item(i);
			FieldMap fieldMap = searchFieldMap(node);
			if (fieldMap != null) {
				FieldNode fieldNode = buildFieldNode((Element) node, fieldMap,
						false);
				if ((fieldNode != null)
						&& (!(this.fieldNodeList.contains(fieldNode))))
					this.fieldNodeList.add(fieldNode);
				CriteriaNode criteriaNode = buildCriteriaNode((Element) node,
						fieldMap);
				if (criteriaNode != null)
					this.criteriaNodeList.add(criteriaNode);
			}
		}
		parseCond();
	}

	/**
	 * 分析查询条件
	 */
	public void parseExpandAll() throws Exception {
		// 设置为默认
		this.isDefaultField = true;
		// 设置默认的查询条件为"="，值为2
		this.defaultOpe = Operate.EQUAL;
		// 要转换的节点
		NodeList nodeList = this.element.getChildNodes();
		int length = nodeList.getLength();
		// 开始进行转换
		for (int i = 0; i < length; ++i) {
			Node node = nodeList.item(i);
			// 根据 Node 的名称在 EntityMap 中的 FieldMapHash 中查找对应的 FieldMap。
			FieldMap fieldMap = searchFieldMap(node);
			if (fieldMap != null) {
				FieldNode fieldNode = buildFieldNode((Element) node, fieldMap, false);
				// 如果 fieldNode 不为 null，并且 fieldNodeList 列表不包含指定的 fieldNode 元素。
				if (fieldNode != null && !this.fieldNodeList.contains(fieldNode))
					this.fieldNodeList.add(fieldNode);
				// 创建查询条件
				CriteriaNode criteriaNode = buildCriteriaNode((Element) node, fieldMap);
				if (criteriaNode != null)
					this.criteriaNodeList.add(criteriaNode);
			}
		}
		buildAllFieldNode(this.entityMap); // 补充其它字段
		parseCond(); // 未实现
	}

	public List parseInsert() throws Exception {
		this.isDefaultField = true;
		this.defaultFunc = Function.SET;
		NodeList nodeList = this.element.getChildNodes();
		int length = nodeList.getLength();
		for (int i = 0; i < length; ++i) {
			Node node = nodeList.item(i);
			FieldMap fieldMap = searchFieldMap(node);
			if (fieldMap != null) {
				FieldNode fieldNode = buildFieldNode((Element) node, fieldMap, false);
				// 如果 fieldNode 不为 null，并且 fieldNodeList 列表不包含指定的 fieldNode 元素。
				if (fieldNode != null && !this.fieldNodeList.contains(fieldNode))
					this.fieldNodeList.add(fieldNode);
			}
		}
		return this.fieldNodeList;
	}

	/**
	 * 
	 * @param aElement
	 *            要转换的字段
	 * @param aFieldMap
	 *            节点的基本定义
	 * @param isIgnoreNull
	 *            是否忽略null值
	 * @return
	 * @throws Exception
	 */
	private FieldNode buildFieldNode(Element aElement, FieldMap aFieldMap,
			boolean isIgnoreNull) throws Exception {
		return buildFieldNode(aElement, aFieldMap, false, isIgnoreNull);
	}

	/**
	 * 
	 * @param aElement
	 * @param aFieldMap
	 *            节点的基本定义
	 * @param isFind
	 * @param isIgnoreNull
	 * @return FieldNode
	 * @throws Exception
	 */
	private FieldNode buildFieldNode(Element aElement, FieldMap aFieldMap,
			boolean isFind, boolean isIgnoreNull) throws Exception {
		// 取操作名称，查询节点的 field 属性的值
		String funcName = this.xmlTrans.getNodeAttr(aElement, "field");
		if ((!(this.isDefaultField)) && (funcName == null)) {
			return null;
		}
		// 将函数名称转换成函数编号
		int func = this.defaultFunc;
		if (funcName != null) {
			func = Function.convertStringToInt(funcName);
		}
		// 没有找到对应的操作编号
		if (func == -1) {
			throw new Exception(funcName);
		}
		if (func == Function.INVALIDATE) {
			return null;
		}
		// 如果有函数运算取字段的值
		String attrValue = null;
		if ((func != Function.NOT_FUNCTION) || (!(isFind))) {
			attrValue = this.xmlTrans.getElementValue(aElement);
		}
		if (attrValue == null && aFieldMap.getColumnMap().getColumnType() == 12) {
			attrValue = "";
		} else if (isIgnoreNull && (attrValue == null || attrValue.trim().equals(""))) {
			return null;
		}
		// 取字段别名，查询节点的 alias 属性的值
		String alias = this.xmlTrans.getNodeAttr(aElement, "alias");

		// 创建新的插入条件
		FieldNode fieldNode = new FieldNode(this.dataBase, aFieldMap, func, attrValue, alias);

		// 创建查询条件的精度
		String pattern = this.xmlTrans.getNodeAttr(aElement, "pattern");
		fieldNode.setPattern(pattern);

		return fieldNode;
	}

	protected void buildAllFieldNode(EntityMap anEntityMap) {
		Iterator iterator = anEntityMap.fetchFieldList().iterator();
		while (iterator.hasNext()) {
			FieldMap fieldMap = (FieldMap) iterator.next();
			boolean isCreate = true;
			for (int i = 0; i < this.fieldNodeList.size(); ++i) {
				FieldNode node = (FieldNode)this.fieldNodeList.get(i);
				if (node.getFieldMap().getColumnName().equals(
						fieldMap.getColumnName())) {
					isCreate = false;
					break;
				}
			}
			if (isCreate) {
				FieldNode fieldNode = new FieldNode(this.dataBase, fieldMap,
						Function.NOT_FUNCTION, null);
				this.fieldNodeList.add(fieldNode);
			}
		}
	}

	protected void parseCond() {
		if (this.element == null)
			return;
		this.cond = this.xmlTrans
				.getFieldValueFromEntity(this.element, "where");
	}

	/**
	 * 根据 Node 的名称在 EntityMap 中的 FieldMapHash 中查找对应的 FieldMap。
	 * 
	 * @param aNode
	 * @return FieldMap
	 */
	private FieldMap searchFieldMap(Node aNode) {
		// 判断是否是一个 Element
		if (aNode instanceof Element) {
			// 取节点的 fieldName 属性的值
			String attrName = this.xmlTrans.getNodeAttr(aNode, "fieldName");
			// 如果节点没有定义 fieldName 属性，那么就去节点的名称
			if (attrName == null)
				attrName = aNode.getNodeName();
			// 在 EntityMap 中查找 node 的定义
			FieldMap fieldMap = this.entityMap.fetchFieldMap(attrName);
			return fieldMap;
		}
		return null;
	}

	/**
	 * 
	 * @param aElement
	 *            输入的查询条件节点
	 * @param aFieldMap
	 *            节点的基本定义
	 * @return CriteriaNode
	 * @throws Exception
	 */
	private CriteriaNode buildCriteriaNode(Element aElement, FieldMap aFieldMap)
			throws Exception {
		// 取操作名称，查询节点的 criteria 属性的值
		String opeName = this.xmlTrans.getNodeAttr(aElement, "criteria");
		if (!(this.isDefaultCriteria) && opeName == null) {
			return null;
		}
		// 将操作名称转换成操作编号
		int ope = this.defaultOpe;
		if (opeName != null) {
			ope = Operate.convertStringToInt(opeName);
		}
		// 没有找到对应的操作编号
		if (ope == Operate.NOT_FIND || ope == Operate.NOP) {
			return null;
		}
		// 如果操作 是 NOTNULL 或 NULL
		if ((ope == Operate.NOT_NULL) || (ope == Operate.NULL)) {
			return new CriteriaNode(this.dataBase, aFieldMap, ope, null);
		}
		// 取查询条件的值
		String criteriaValue = this.xmlTrans.getNodeAttr(aElement,
				"criteriaValue");
		if (criteriaValue == null) {
			criteriaValue = this.xmlTrans.getElementValue(aElement);
		}
		if ((criteriaValue == null) || (criteriaValue.equals(""))) {
			return null;
		}
		// 创建新的查询条件
		CriteriaNode criteriaNode = new CriteriaNode(this.dataBase, aFieldMap,
				ope, criteriaValue);
		// 创建查询条件的精度
		String pattarn = this.xmlTrans.getNodeAttr(aElement, "criteriaPattern");
		criteriaNode.setPattern(pattarn);

		return criteriaNode;
	}

	public List getCriteriaNodeList() {
		return criteriaNodeList;
	}

	public List getFieldNodeList() {
		return fieldNodeList;
	}

	public String getOrderByString() {
		Element orderEle = this.xmlTrans.getLowElementByName(this.element, "orderby");

	    if (orderEle == null){
	      return "";
	    }
	    List colNames = new ArrayList();
	    List ascList = new ArrayList();

	    int i = 1;
	    Element tmp = this.xmlTrans.getLowElementByName(orderEle, "col" + i);
	    while (tmp != null) {
	    	String fieldName = this.xmlTrans.getElementValue(tmp);
	    	FieldMap fieldMap = this.entityMap.fetchFieldMap(fieldName);
	    	if (fieldMap != null) {
	    		colNames.add(fieldMap.getColumnName());
	    		String attrOrder = this.xmlTrans.getNodeAttr(tmp, "order");
	    		if (attrOrder != null){
	    			if ( attrOrder.toUpperCase().equals(this.dataBase.getClauseStringAscend())  || 
	    				 attrOrder.toUpperCase().equals(this.dataBase.getClauseStringDescend()) ){
	    				ascList.add(attrOrder);
	    			} else {
	    				ascList.add(this.dataBase.getClauseStringDescend());
	    			}
	    		} else {
	    			ascList.add(this.dataBase.getClauseStringDescend());
	    		}
	    	} else if ("true".equalsIgnoreCase(tmp.getAttribute("clause"))) {
	    		colNames.add("");
	    		ascList.add(this.xmlTrans.getElementValue(tmp));
	    	}
	    	++i;
	    	tmp = this.xmlTrans.getLowElementByName(orderEle, "col" + i);
	    }

	    StringBuffer sql = new StringBuffer(" ");
	    sql.append(this.dataBase.getClauseStringOrderBy());
	    int length = colNames.size();
	    if (length == 0)
	      return "";
	    for (i = 0; i < length; ++i) {
	      sql.append(' ');
	      sql.append((String)colNames.get(i));
	      sql.append(' ');
	      sql.append((String)ascList.get(i));
	      sql.append(' ');
	      if (i != length - 1)
	        sql.append(',');
	    }
	    return sql.toString();
	}
	
	public void parseUpdate(boolean isIgnoreNull) throws Exception {
		NodeList nodeList = this.element.getChildNodes();
		int length = nodeList.getLength();
		for (int i = 0; i < length; ++i) {
			Node node = nodeList.item(i);
			FieldMap fieldMap = searchFieldMap(node);
			if (fieldMap != null) {
				FieldNode fieldNode = buildFieldNode((Element) node, fieldMap, isIgnoreNull);
				if (fieldNode != null)
					this.fieldNodeList.add(fieldNode);
				CriteriaNode criteriaNode = buildCriteriaNode((Element) node, fieldMap);
				if (criteriaNode != null)
					this.criteriaNodeList.add(criteriaNode);
			}
		}
	}

	public List getKeyCriteriaNodeList(boolean ignoredKey) {
		List list = new ArrayList();

		HashMap keyHashMap = this.entityMap.fetchKeyFieldMap();

		NodeList nodeList = this.element.getChildNodes();
		int length = nodeList.getLength();

		for (int i = 0; i < length; ++i) {
			Node field = nodeList.item(i);
			if (!(field instanceof Element)) {
				return null;
			}
			String fieldName = field.getNodeName();
			FieldMap fieldMap = (FieldMap)keyHashMap.get(fieldName);
			if (fieldMap != null) {
				String criteriaValue = this.xmlTrans.getElementValue((Element) field);
				if (ignoredKey) {
					((Element) field).setAttribute("field", "0");
				}
				if (criteriaValue == null || criteriaValue.trim().equals("")) {
					return null;
				}
				// 创建新的查询条件
				CriteriaNode criteriaNode = new CriteriaNode(this.dataBase, fieldMap, Operate.EQUAL, criteriaValue);
				list.add(criteriaNode);
			}
		}
		if (list.isEmpty())
			return null;
		if (list.size() != keyHashMap.size())
			return null;
		return list;
	}
}
