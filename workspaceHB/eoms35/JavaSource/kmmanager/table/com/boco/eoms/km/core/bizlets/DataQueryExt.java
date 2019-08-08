package com.boco.eoms.km.core.bizlets;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.boco.eoms.km.core.crimson.util.XmlUtil;
import com.boco.eoms.km.core.dataservice.map.FieldMap;
import com.boco.eoms.km.core.util.Operate;

public class DataQueryExt {

    public static Element transformCriteriaNode(Element queryCond)
            throws Exception {
        Document document = XmlUtil.newDocument();
        Element newQuery = document.createElement(queryCond.getNodeName());

        transformCriteriaNode2(queryCond, newQuery);
        newQuery.setAttribute("criteria", "=");
        return newQuery;
    }

    private static void transformCriteriaNode2(Element queryCond, Element newQueryCond) throws Exception {

        // 判断是否有实体名称
        if (queryCond.hasAttribute("entityName")) {
            String entityName = queryCond.getAttribute("entityName");
            newQueryCond.setAttribute("entityName", entityName);
        }
        // 判断是否有别名
        if (queryCond.hasAttribute("alias")) {
            String alias = queryCond.getAttribute("alias");
            newQueryCond.setAttribute("alias", alias);
        }

        String inElementName = queryCond.getNodeName();
        Document newQueryDoc = newQueryCond.getOwnerDocument();
        Element element3 = newQueryDoc.createElement("orderby");
        boolean orderFlag = false;

        // 迭代页面录入的查询条件
        NodeList queryCondChild = queryCond.getChildNodes();

        for (int j = 0; j < queryCondChild.getLength(); j++) {
            Node queryNode = queryCondChild.item(j);

            if (queryNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            // 取查询节点的名称
            String queryNodeName = queryNode.getNodeName();

            // 首先判断输入的查询NODE是否是我们定义的特殊NODE
            if (queryNodeName.equalsIgnoreCase("_order")) {
                NodeList nodelist2 = queryNode.getChildNodes();

                for (int k = 0; k < nodelist2.getLength(); k++) {
                    Node node2 = nodelist2.item(k);
                    String s4 = node2.getNodeName().toLowerCase();
                    if (s4.startsWith("col")) {
                        int i1 = Integer.parseInt(s4.substring(3));
                        String s6 = trim(BNXmlUtil.getNodeValue(node2, "field"));
                        if (!s6.equals("")) {
                            String s8 = trim(BNXmlUtil.getNodeValue(node2, "asc"));
                            if (s8.equalsIgnoreCase("DESC")) {
                                Element element6 = newQueryDoc.createElement("col" + String.valueOf(i1));
                                element6.setAttribute("order", "DESC");
                                XmlUtil.setNodeValue(element6, s6);
                                element3.appendChild(element6);
                            } else {
                                Element element7 = newQueryDoc.createElement("col" + String.valueOf(i1));
                                XmlUtil.setNodeValue(element7, s6);
                                element3.appendChild(element7);
                            }
                            orderFlag = true;
                        }
                    }
                }
            } else if (queryNodeName.equals("_distinct")) {
                continue;

            } else if (queryNodeName.equals("_where")) {
                String queryNodeValue = trim(XmlUtil.getNodeValue(queryNode));
                if (!queryNodeValue.equals("")) {
                    Element where = newQueryDoc.createElement("where");
                    XmlUtil.setNodeValue(where, queryNodeValue);
                    newQueryCond.appendChild(where);
                }
                continue;
            }

            Element queryElement = (Element) queryNode;
            NodeList criteriaList = queryElement.getElementsByTagName("criteria");

            FieldCriteria fieldCriteria = null;
            for (int i = 0; i < criteriaList.getLength(); i++) {
                Node oldCriteria = criteriaList.item(i);
                FieldCriteria newCriteria = processFieldCriteria(inElementName, queryNodeName, oldCriteria);
                if (newCriteria == null)
                    continue;
                if (fieldCriteria == null) {
                    fieldCriteria = newCriteria;
                    continue;
                }
                String criteria = fieldCriteria.getCriteria();
                String value = fieldCriteria.getValue();
                if (criteria.equals("") && value.equals("")) {
                    fieldCriteria = newCriteria;
                    continue;
                }
                String s5 = fieldCriteria.getMatchString();
                String s7 = newCriteria.getMatchString();
                if (s7 != null && s5 != null) {
                    fieldCriteria.setCriteria("match");
                    fieldCriteria.setValue(s5 + "||" + s7);
                }
            }
            if (fieldCriteria == null) {
                continue;
            }

            Element newQueryNode = newQueryDoc.createElement(queryNodeName);
            if (!fieldCriteria.getField().equals("")) {
                String field = fieldCriteria.getField();
                newQueryNode.setAttribute("field", field);
            }
            if (!fieldCriteria.getCriteria().equals("")) {
                String criteria = fieldCriteria.getCriteria();
                newQueryNode.setAttribute("criteria", criteria);
            }
            if (!fieldCriteria.getValue().equals("")) {
                String value = fieldCriteria.getValue();
                XmlUtil.setNodeValue(newQueryNode, value);
            }
            if (!fieldCriteria.getPattern().equals("")) {
                String pattern = fieldCriteria.getPattern();
                newQueryNode.setAttribute("pattern", pattern);
            }
            if (!fieldCriteria.getCriteriaPattern().equals("")) {
                String criteriaPattern = fieldCriteria.getCriteriaPattern();
                newQueryNode.setAttribute("criteriaPattern", criteriaPattern);
            }
            if (queryElement.hasAttribute("fieldName")) {
                String fieldName = queryElement.getAttribute("fieldName");
                newQueryNode.setAttribute("fieldName", fieldName);
            }
            newQueryCond.appendChild(newQueryNode);
        }

        if (orderFlag) {
            newQueryCond.appendChild(element3);
        }
    }

    public static Element transformCriteriaNode(Element tableInfo,
                                                Element queryCond) throws Exception {
        return transformCriteriaNode(tableInfo, queryCond, queryCond
                .getNodeName());
    }

    private static Element transformCriteriaNode(Element tableInfo,
                                                 Element queryCond, String nodeName) throws Exception {
        Document document = tableInfo.getOwnerDocument();
        Element newQuery = document.createElement(nodeName);
        transformCriteriaNode(tableInfo, queryCond, newQuery);
        return newQuery;
    }

    /**
     * 将页面输入的查询条件转换成EOMS查询条件
     *
     * @param queryCond 页面录入的查询条件
     * @param newQuery  转换后的EOMS查询条件
     * @param tableInfo 页面录入的查询结果列表排序条件
     * @throws Exception
     */
    private static void transformCriteriaNode(Element tableInfo,
                                              Element queryCond, Element newQuery) throws Exception {
        NodeList inElementChild = queryCond.getChildNodes();
        String inElementName = queryCond.getNodeName();

        Document listDocument = tableInfo.getOwnerDocument();

        // 以文档顺序返回具有给定标记名称的所有后代 Elements 的 NodeList。
        NodeList orderNodeList = tableInfo.getElementsByTagName("orderby");

        // 从 listElem 的子节点列表中移除 node 所指示的子节点，并将其返回。
        for (int i = orderNodeList.getLength(); i > 0; i--) {
            Node node = orderNodeList.item(i - 1);
            tableInfo.removeChild(node);
        }

        // 创建指定类型的元素。
        Element newOrderElem = listDocument.createElement("orderby");
        boolean flag = false;

        // 返回此节点（如果它是一个元素）是否具有任何属性
        if (queryCond.hasAttribute("entityName")) { // 判断是否有实体名称
            newQuery.setAttribute("entityName", queryCond
                    .getAttribute("entityName"));
        }

        if (queryCond.hasAttribute("alias")) { // 判断是否有别名
            newQuery.setAttribute("alias", queryCond.getAttribute("alias"));
        }

        // 迭代页面录入的查询条件
        label0:
        for (int j = 0; j < inElementChild.getLength(); j++) {
            Node inWhereNode = inElementChild.item(j);
            if (inWhereNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            String inWhereNodeName = inWhereNode.getNodeName();

            if (inWhereNodeName.equals("_order")) {
                NodeList orderWhereNodeList = inWhereNode.getChildNodes();
                int k = 0;
                do {
                    if (k >= orderWhereNodeList.getLength())
                        continue label0;
                    Node orderNode = orderWhereNodeList.item(k);
                    String orderNodeName = orderNode.getNodeName()
                            .toLowerCase();
                    if (orderNodeName.startsWith("col")) {
                        int i1 = Integer.parseInt(orderNodeName.substring(3));
                        String fieldValue = trim(BNXmlUtil.getNodeValue(
                                orderNode, "field"));
                        if (!fieldValue.equals("")) {
                            String ascValue = trim(BNXmlUtil.getNodeValue(
                                    orderNode, "asc"));
                            if (ascValue.equalsIgnoreCase("DESC")) {
                                Element newOrderNode = listDocument
                                        .createElement("col"
                                                + String.valueOf(i1));
                                newOrderNode.setAttribute("order", "DESC");
                                XmlUtil.setNodeValue(newOrderNode, fieldValue);
                                newOrderElem.appendChild(newOrderNode);
                            } else {
                                Element newOrderNode = listDocument
                                        .createElement("col"
                                                + String.valueOf(i1));
                                XmlUtil.setNodeValue(newOrderNode, fieldValue);
                                newOrderElem.appendChild(newOrderNode);
                            }
                            flag = true;
                        }
                    }
                    k++;
                } while (true);
            } else if (inWhereNodeName.equals("_distinct")) {
                String s2 = trim(XmlUtil.getNodeValue(inWhereNode));
                if (!s2.equals("")) {
                    tableInfo.setAttribute("distinct", s2);
                }
                continue;
            } else if (inWhereNodeName.equals("_where")) {
                String s3 = trim(XmlUtil.getNodeValue(inWhereNode));
                if (!s3.equals("")) {
                    Element element4 = listDocument.createElement("where");
                    XmlUtil.setNodeValue(element4, s3);
                    newQuery.appendChild(element4);
                }
                continue;
            }

            // 以文档顺序返回具有给定标记名称的所有后代 Elements 的 NodeList。
            NodeList inWhereNodeCriteria = ((Element) inWhereNode)
                    .getElementsByTagName("criteria");
            FieldCriteria fieldCriteria = null;
            for (int l = 0; l < inWhereNodeCriteria.getLength(); l++) {
                Node criteria = inWhereNodeCriteria.item(l);
                FieldCriteria fieldcriteria1 = processFieldCriteria(
                        inElementName, inWhereNodeName, criteria);
                if (fieldcriteria1 == null)
                    continue;
                if (fieldCriteria == null) {
                    fieldCriteria = fieldcriteria1;
                    continue;
                }
                if (fieldCriteria.getCriteria().equals("")
                        && fieldCriteria.getValue().equals("")) {
                    fieldCriteria = fieldcriteria1;
                    continue;
                }
                String s5 = fieldCriteria.getMatchString();
                String s7 = fieldcriteria1.getMatchString();
                if (s7 != null && s5 != null) {
                    fieldCriteria.setCriteria("match");
                    fieldCriteria.setValue(s5 + "||" + s7);
                }
            }
            if (fieldCriteria == null) {
                continue;
            }

            Element newWhereElem = listDocument.createElement(inWhereNodeName);
            if (!fieldCriteria.getField().equals(""))
                newWhereElem.setAttribute("field", fieldCriteria.getField());
            if (!fieldCriteria.getCriteria().equals(""))
                newWhereElem.setAttribute("criteria", fieldCriteria
                        .getCriteria());
            if (!fieldCriteria.getValue().equals(""))
                XmlUtil.setNodeValue(newWhereElem, fieldCriteria.getValue());
            if (!fieldCriteria.getPattern().equals(""))
                newWhereElem
                        .setAttribute("pattern", fieldCriteria.getPattern());
            if (!fieldCriteria.getCriteriaPattern().equals(""))
                newWhereElem.setAttribute("criteriaPattern", fieldCriteria
                        .getCriteriaPattern());
            if (((Element) inWhereNode).hasAttribute("fieldName"))
                newWhereElem.setAttribute("fieldName", ((Element) inWhereNode)
                        .getAttribute("fieldName"));

            // 将节点 newWhereElem 添加到此节点的子节点列表的末尾。如果 newWhereElem 已经存在于树中，则首先移除它。
            newQuery.appendChild(newWhereElem);
        }

        if (flag) {
            // 将节点 newWhereElem 添加到此节点的子节点列表的末尾。如果 newWhereElem 已经存在于树中，则首先移除它。
            tableInfo.appendChild(newOrderElem);
        }
    }

    private static FieldCriteria processFieldCriteria(String s, String s1,
                                                      Node criteria) throws Exception {
        if (criteria.getNodeType() != Node.ELEMENT_NODE) {
            return null;
        }

        // 查询字段模型定义
        FieldMap fieldMap = null;// MapFactory.fetchField(s, s1);

        byte byte0 = 0;
        if (fieldMap != null) {
            if (fieldMap.getColumnMap().getColumnTypeName().equalsIgnoreCase(
                    "Date")) {
                byte0 = 1;
            } else if (fieldMap.getColumnMap().getColumnTypeName()
                    .equalsIgnoreCase("TimeStamp")) {
                byte0 = 3;
            }
        }

        String operatorValue = "";
        String valueValue = "";
        String minValue = "";
        String maxValue = "";
        String patternValue = "";
        String criteriaPatternValue = "";
        String fieldValue = "";
        String likeRuleValue = "";
        String yearValue = "";
        String quarterValue = "";
        String monthValue = "";
        String dateRuleValue = "";
        boolean flag = false;

        ArrayList valueArraylist = new ArrayList();

        NodeList nodelist = criteria.getChildNodes();
        for (int i = 0; i < nodelist.getLength(); i++) {
            Node subCriteria = nodelist.item(i);
            if (subCriteria.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            String subCriteriaName = subCriteria.getNodeName();
            if (subCriteriaName.equalsIgnoreCase("operator")) {
                operatorValue = trim(XmlUtil.getNodeValue(subCriteria));
                continue;
            } else if (subCriteriaName.equalsIgnoreCase("value")) {
                String value = trim(XmlUtil.getNodeValue(subCriteria));
                if (value.equals(""))
                    continue;
                if (valueValue.equals("")) {
                    valueValue = value;
                    valueArraylist.add(value);
                } else {
                    valueArraylist.add(value);
                    flag = true;
                }
                continue;
            } else if (subCriteriaName.equalsIgnoreCase("likeRule")) {
                likeRuleValue = trim(XmlUtil.getNodeValue(subCriteria));
                continue;
            } else if (subCriteriaName.equalsIgnoreCase("min")) {
                minValue = trim(XmlUtil.getNodeValue(subCriteria));
                continue;
            } else if (subCriteriaName.equalsIgnoreCase("max")) {
                maxValue = trim(XmlUtil.getNodeValue(subCriteria));
                continue;
            } else if (subCriteriaName.equalsIgnoreCase("pattern")) {
                patternValue = trim(XmlUtil.getNodeValue(subCriteria));
                continue;
            } else if (subCriteriaName.equalsIgnoreCase("criteriaPattern")) {
                criteriaPatternValue = trim(XmlUtil.getNodeValue(subCriteria));
                continue;
            } else if (subCriteriaName.equalsIgnoreCase("field")) {
                fieldValue = trim(XmlUtil.getNodeValue(subCriteria));
                continue;
            } else if (subCriteriaName.equalsIgnoreCase("year")) {
                yearValue = trim(XmlUtil.getNodeValue(subCriteria));
                continue;
            } else if (subCriteriaName.equalsIgnoreCase("quarter")) {
                quarterValue = trim(XmlUtil.getNodeValue(subCriteria));
                continue;
            } else if (subCriteriaName.equalsIgnoreCase("month")) {
                monthValue = trim(XmlUtil.getNodeValue(subCriteria));
                continue;
            } else if (subCriteriaName.equalsIgnoreCase("dateRule")) {
                dateRuleValue = trim(XmlUtil.getNodeValue(subCriteria));
            }
        }

        if (flag) {
            Iterator iterator = valueArraylist.iterator();
            StringBuffer stringbuffer = new StringBuffer("(=");
            stringbuffer.append((String) iterator.next());
            for (; iterator.hasNext(); stringbuffer.append(iterator.next())) {
                stringbuffer.append("||=");
            }
            stringbuffer.append(")");
            return new FieldCriteria(s1, "match", stringbuffer.toString(), patternValue, criteriaPatternValue, fieldValue);
        }

        // 将操作名称转换为操作ID
        int operateId = Operate.convertStringToInt(operatorValue);

        if (operateId == Operate.BETWEEN) { // 9
            if (minValue.equals("") && maxValue.equals(""))
                if (byte0 == 1 || byte0 == 3) {
                    valueValue = processDateTime(byte0, valueValue);
                    if (valueValue == "" || valueValue.indexOf(":") == -1)
                        return new FieldCriteria(s1, "", valueValue,
                                patternValue, "", fieldValue);
                    else
                        return new FieldCriteria(s1, operatorValue, valueValue,
                                patternValue, "", fieldValue);
                } else {
                    return new FieldCriteria(s1, "", valueValue, patternValue,
                            "", fieldValue);
                }
            if (minValue.equals(""))
                return new FieldCriteria(s1, "<=", maxValue, patternValue,
                        criteriaPatternValue, fieldValue);
            if (maxValue.equals(""))
                return new FieldCriteria(s1, ">=", minValue, patternValue,
                        criteriaPatternValue, fieldValue);
            else
                return new FieldCriteria(s1, operatorValue, new String(minValue
                        + "|" + maxValue), patternValue, criteriaPatternValue,
                        fieldValue);
        } else if (operateId == Operate.LIKE) { // 8
            if (valueValue.equals(""))
                return new FieldCriteria(s1, "", "", patternValue, "",
                        fieldValue);
            valueValue = processSpecial(valueValue);
            if (likeRuleValue.equals("")
                    || likeRuleValue.equalsIgnoreCase("center"))
                valueValue = "*" + valueValue + "*";
            else if (likeRuleValue.equalsIgnoreCase("begin"))
                valueValue = valueValue + "*";
            else if (likeRuleValue.equalsIgnoreCase("end"))
                valueValue = "*" + valueValue;
            else
                valueValue = "*" + valueValue + "*";
            return new FieldCriteria(s1, operatorValue, valueValue,
                    patternValue, "", fieldValue);
        }

        String s14 = "";
        if (!dateRuleValue.equals("")) {
            if (dateRuleValue.equals("year") && !yearValue.equals("")
                    && yearValue.length() == 4)
                s14 = processDateTime(byte0, yearValue, "", dateRuleValue);
            if (dateRuleValue.equals("quarter") && !quarterValue.equals("")
                    && quarterValue.length() == 1)
                s14 = processDateTime(byte0, yearValue, quarterValue,
                        dateRuleValue);
            if (dateRuleValue.equals("month") && !monthValue.equals("")
                    && monthValue.length() <= 2)
                s14 = processDateTime(byte0, yearValue, monthValue,
                        dateRuleValue);
            if (s14.equals("") || s14.indexOf(":") == -1)
                return new FieldCriteria(s1, "", valueValue, patternValue, "",
                        fieldValue);
            else
                return new FieldCriteria(s1, "between", s14, patternValue, "",
                        fieldValue);
        }

        if (operatorValue.equals("") && dateRuleValue.equals("")) {
            return new FieldCriteria(s1, "", valueValue, patternValue,
                    criteriaPatternValue, fieldValue);
        }

        switch (operateId) {
            case Operate.NOT_NULL: // 1
            case Operate.NULL: // 11
                return new FieldCriteria(s1, operatorValue, "", patternValue,
                        "", fieldValue);
            case Operate.MATCH: // 10
                if (!valueValue.equals(""))
                    return new FieldCriteria(s1, operatorValue, valueValue,
                            patternValue, criteriaPatternValue, fieldValue);
                else
                    return new FieldCriteria(s1, "", "", patternValue, "",
                            fieldValue);
            case Operate.EQUAL: // 2
            case Operate.NOT_EQUAL: // 3
            case Operate.LESSER: // 4
            case Operate.LESSER_EQUAL: // 5
            case Operate.GREATER: // 6
            case Operate.GREATER_EQUAL: // 7
                if (valueValue.equals(""))
                    return new FieldCriteria(s1, "", "", patternValue, "",
                            fieldValue);
                else
                    return new FieldCriteria(s1, operatorValue, valueValue,
                            patternValue, criteriaPatternValue, fieldValue);
            case Operate.LIKE: // 8
            case Operate.BETWEEN: // 9
            default:
                return new FieldCriteria(s1, "", "", patternValue, "",
                        fieldValue);
        }
    }

    private static String processSpecial(String s) {
        String s1 = "";
        String s2 = "";
        String s4 = "";
        for (int i = 0; i < s.length(); ) {
            String s3 = "" + s.charAt(i);
            if (i + 1 < s.length())
                s4 = "" + s.charAt(i + 1);
            if ("'".equals(s3)) {
                if (s3.equals(s4)) {
                    s1 = s1 + "''";
                    i += 2;
                } else {
                    s1 = s1 + "''";
                    i++;
                }
            } else {
                s1 = s1 + s3;
                i++;
            }
        }

        return s1;
    }

    private static String processDateTime(int i, String s, String s1, String s2)
            throws Exception {
        if (s.equals("") || s1.equals("") && !s2.equals("year"))
            return "";
        String s3 = "";
        if (i == 1)
            if (s2.equals("quarter"))
                switch (Integer.parseInt(s1)) {
                    case 1: // '\001'
                        s3 = s + "0101:" + s + "0331";
                        break;

                    case 2: // '\002'
                        s3 = s + "0401:" + s + "0630";
                        break;

                    case 3: // '\003'
                        s3 = s + "0701:" + s + "0930";
                        break;

                    case 4: // '\004'
                        s3 = s + "1001:" + s + "1231";
                        break;

                    default:
                        return "";
                }
            else if (s2.equals("month")) {
                if (Integer.parseInt(s1) < 1 || Integer.parseInt(s1) > 12)
                    return "";
                String s4 = getMonthLastDay(Integer.parseInt(s), Integer
                        .parseInt(s1));
                if (s1.length() == 1)
                    s3 = s + "0" + s1 + "01:" + s + "0" + s1 + s4;
                else
                    s3 = s + s1 + "01:" + s + s1 + s4;
            } else {
                s3 = s + "0101:" + s + "1231";
            }
        if (i == 3)
            if (s2.equals("quarter"))
                switch (Integer.parseInt(s1)) {
                    case 1: // '\001'
                        s3 = s + "0101000000:" + s + "0331235959";
                        break;

                    case 2: // '\002'
                        s3 = s + "0401000000:" + s + "0630235959";
                        break;

                    case 3: // '\003'
                        s3 = s + "0701000000:" + s + "0930235959";
                        break;

                    case 4: // '\004'
                        s3 = s + "1001000000:" + s + "1231235959";
                        break;

                    default:
                        return "";
                }
            else if (s2.equals("month")) {
                if (Integer.parseInt(s1) < 1 || Integer.parseInt(s1) > 12)
                    return "";
                String s5 = getMonthLastDay(Integer.parseInt(s), Integer
                        .parseInt(s1));
                if (s1.length() == 1)
                    s3 = s + "0" + s1 + "01000000:" + s + "0" + s1 + s5
                            + "235959";
                else
                    s3 = s + s1 + "01000000:" + s + s1 + s5 + "235959";
            } else {
                s3 = s + "0101000000:" + s + "1231235959";
            }
        return s3;
    }

    private static String processDateTime(int i, String s) throws Exception {
        if (s.equals(""))
            return "";
        String s1 = processTimeString(s);
        if (i == 1) {
            int j = s1.length();
            switch (j) {
                case 4: // '\004'
                    return s1 + "0101:" + s1 + "1231";

                case 5: // '\005'
                    s1 = appendZero(s1);
                    return s1 + "01:" + s1 + getMonthLastDay(s1);

                case 6: // '\006'
                    return s1 + "01:" + s1 + getMonthLastDay(s1);

                case 7: // '\007'
                    return appendZero(s1);
            }
            if (j < 4)
                throw new Exception(
                        "com.primeton.eos.bizlets.database.DataQueryExt.processDateTime() dateValue="
                                + s + " which length is " + j
                                + ", can not be parsed!");
            if (j > 8)
                s1 = s1.substring(0, 8);
            return s1;
        }
        if (i == 3) {
            int k = s1.length();
            switch (k) {
                case 4: // '\004'
                    return s1 + "0101000000:" + s1 + "1231235959";

                case 5: // '\005'
                    s1 = appendZero(s1);
                    return s1 + "01:" + s1 + getMonthLastDay(s1);

                case 6: // '\006'
                    String s2 = getMonthLastDay(s1);
                    return s1 + "01000000:" + s1 + s2 + "235959";

                case 8: // '\b'
                    return s1 + "000000:" + s1 + "235959";

                case 10: // '\n'
                    return s1 + "0000:" + s1 + "5959";

                case 12: // '\f'
                    return s1 + "00:" + s1 + "59";

                case 13: // '\r'
                    s1 = appendZero(s1);
                    break;
            }
            if (k < 4 || k == 7 || k == 9 || k == 11)
                throw new Exception(
                        "com.primeton.eos.bizlets.database.DataQueryExt.processDateTime() timeValue="
                                + s + " which length is " + k
                                + ", can not be parsed!");
            if (k > 14)
                s1 = s1.substring(0, 14);
            return s1;
        } else {
            return "";
        }
    }

    private static String getMonthLastDay(int i, int j) {
        byte byte0 = 0;
        if (j == 1 || j == 3 || j == 5 || j == 7 || j == 8 || j == 10
                || j == 12) {
            byte0 = 31;
        }
        if (j == 4 || j == 6 || j == 9 || j == 11) {
            byte0 = 30;
        } else if (j == 2) {
            byte0 = ((byte) (i % 4 != 0 || i % 100 == 0 && i % 400 != 0 ? 28
                    : 29));
        }
        return (new Integer(byte0)).toString();
    }

    private static String appendZero(String s) {
        String s1 = s.substring(0, s.length() - 1);
        String s2 = s.substring(s.length() - 1);
        return s1 + "0" + s2;
    }

    private static String getMonthLastDay(String s) throws Exception {
        if (s == null || s.equals("")) {
            return "";
        }
        // boolean flag = false;
        int i = Integer.parseInt(s.substring(0, 4));
        int j = Integer.parseInt(s.substring(4, 6));
        if (i < 1000) {
            throw new Exception("getMonthLastDay parse " + s + " error year!");
        }
        if (j < 1 || j > 12) {
            throw new Exception("getMonthLastDay parse " + s + " error month!");
        } else {
            return getMonthLastDay(i, j);
        }
    }

    private static String processTimeString(String s) {
        if (s == null) {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer("");
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                stringbuffer.append(c);
            }
        }
        return stringbuffer.toString();
    }

    private static String trim(String s) {
        if (s == null) {
            return "";
        } else {
            return s.trim();
        }
    }

    public static void main(String args[]) throws Exception {
        System.setProperty("EOS_HOME", "D:/primeton/eosserver");
        Document localDocument = XmlUtil
                .parseString("<?xml version=\"1.0\" encoding=\"GB2312\"?><root><data><list><orderby/><orderby/></list><CUST entityName=\"Cust\"><LOGINTIME fieldName='loginTime'><criteria><operator>between</operator><value>1974</value></criteria></LOGINTIME></CUST></data></root>");
        Node localNode = XmlUtil.findNode(localDocument, "/root/data/CUST");
        Element localElement1 = (Element) XmlUtil.findNode(localDocument,
                "/root/data/list");
        Element localElement2 = localDocument.createElement("CUST");
        System.out.println("转换前，结果list========>\n"
                + XmlUtil.node2String(localElement1) + "\n");
        System.out.println("输入的查询节点Cust========>\n"
                + XmlUtil.node2String(localNode) + "\n");
        localElement2 = transformCriteriaNode((Element) localNode,
                localElement1);
        System.out.println("转换后，结果list========>\n"
                + XmlUtil.node2String(localElement1) + "\n");
        System.out.println("转换后，查询节点Cust========>\n"
                + XmlUtil.node2String(localElement2) + "\n");
    }
}
