package com.boco.eoms.partner.baseinfo.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.partner.baseinfo.mgr.AreaDeptTreeMgr;
import com.boco.eoms.partner.baseinfo.mgr.PartnerDeptMgr;
import com.boco.eoms.partner.baseinfo.model.AreaDeptTree;
import com.boco.eoms.partner.baseinfo.model.PartnerDept;
import com.boco.eoms.partner.baseinfo.util.AreaDeptTreeConstants;
import com.boco.eoms.partner.baseinfo.util.PartnerDeptConstants;
import com.boco.eoms.partner.baseinfo.webapp.form.PartnerDeptForm;

/**
 * <p>
 * Title:����
 * </p>
 * <p>
 * Description:����
 * </p>
 * <p>
 * Mon Feb 09 10:57:12 CST 2009
 * </p>
 *
 * @moudle.getAuthor() liujinlong
 * @moudle.getVersion() 3.5
 */
public final class PartnerDeptAction extends BaseAction {

    public static String defaultParentNodeId = "";//当save 或 remove 方法，调用 search方法时，利用这个变量，定位到相应人力信息的树节点下

    /**
     * δָ������ʱĬ�ϵ��õķ���
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }

    /**
     * ������
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));//nodeId 是地市的nodeId
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        AreaDeptTree areaDeptTree = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);//此对象是地市
        String areaId = areaDeptTree.getId();
        String areaName = areaDeptTree.getNodeName();
        request.setAttribute("parentNodeId", nodeId);
        request.setAttribute("areaId", areaId);

        PartnerDeptForm partnerDeptForm = (PartnerDeptForm) form;
        partnerDeptForm.setAreaName(areaName);
        updateFormBean(mapping, request, partnerDeptForm);

        return mapping.findForward("edit");
    }

    /**
     * �޸ĳ���
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartnerDeptMgr partnerDeptMgr = (PartnerDeptMgr) getBean("partnerDeptMgr");
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));

        PartnerDept partnerDept = null;
        if (!id.equals("")) {
            partnerDept = partnerDeptMgr.getPartnerDept(id);
            AreaDeptTree areaDeptTree = areaDeptTreeMgr.getAreaDeptTreeByNodeId(partnerDept.getTreeNodeId());//此对象是地市
            request.setAttribute("parentNodeId", areaDeptTree.getParentNodeId());
        } else if (!nodeId.equals("")) {
            partnerDept = partnerDeptMgr.getPartnerDeptByTreeNodeId(nodeId);
            AreaDeptTree areaDeptTree = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);//此对象是地市
            request.setAttribute("parentNodeId", areaDeptTree.getParentNodeId());
        }
        PartnerDeptForm partnerDeptForm = (PartnerDeptForm) convert(partnerDept);
        updateFormBean(mapping, request, partnerDeptForm);
        return mapping.findForward("edit");
    }

    /**
     * ���泧��
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartnerDeptMgr partnerDeptMgr = (PartnerDeptMgr) getBean("partnerDeptMgr");
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        PartnerDeptForm partnerDeptForm = (PartnerDeptForm) form;
        boolean isNew = (null == partnerDeptForm.getId() || "".equals(partnerDeptForm.getId()));
        PartnerDept partnerDept = (PartnerDept) convert(partnerDeptForm);
        String parentNodeId = request.getParameter("parentNodeId");
        String treeNodeId = partnerDept.getTreeNodeId();

        if (isNew && parentNodeId != null && !parentNodeId.equals("")) {//新增


            //给地市节点的factoryLists字段赋值，为了在合作伙伴统计报表中使用。
            AreaDeptTree area = areaDeptTreeMgr.getAreaDeptTreeByNodeId(parentNodeId);
            String factoryLists = area.getFactoryLists();
            if (factoryLists == null || factoryLists.equals("")) {
                factoryLists = "" + partnerDeptForm.getName();
            } else {
                factoryLists += "," + partnerDeptForm.getName();
            }
            area.setFactoryLists(factoryLists);
            areaDeptTreeMgr.saveAreaDeptTree(area);

            //在树表里存厂商节点   保存合作伙伴单表的同时保存树表里合作伙伴节点
            AreaDeptTree factory = new AreaDeptTree();
            factory.setParentNodeId(parentNodeId);
            factory.setNodeName(partnerDeptForm.getName());
            factory.setNodeType(AreaDeptTreeConstants.NODE_TYPE_FACTORY);


            areaDeptTreeMgr.saveAreaDeptTree(factory);
            partnerDept.setTreeId(factory.getId());
            partnerDept.setTreeNodeId(factory.getNodeId());

        }
        if (treeNodeId != null && !treeNodeId.equals("")) {
            AreaDeptTree factory = areaDeptTreeMgr.getAreaDeptTreeByNodeId(treeNodeId);
            factory.setNodeName(partnerDept.getName());
            areaDeptTreeMgr.saveAreaDeptTree(factory);
        }
        if (treeNodeId != null && !treeNodeId.equals("")) {//treeNodeId存在，表示为修改
            //修改地市节点的factoryLists字段，为了在合作伙伴统计报表中使用。
            AreaDeptTree factory = areaDeptTreeMgr.getAreaDeptTreeByNodeId(treeNodeId);
            if (!factory.getNodeName().equals(partnerDept.getName())) {
                AreaDeptTree area = areaDeptTreeMgr.getAreaDeptTreeByNodeId(parentNodeId);
                String factoryLists = area.getFactoryLists();
                String[] factories = factoryLists.split(",");
                String factoryLists1 = "";
                for (int i = 0; i < factories.length; i++) {
                    if (factories[i].equals(factory.getNodeName())) {
                        factories[i] = partnerDept.getName();
                        break;
                    }
                }
                for (int i = 0; i < factories.length; i++) {
                    if (i < factories.length - 1) {
                        factoryLists1 += (factories[i] + ",");
                    } else factoryLists1 += factories[factories.length - 1];
                }
                area.setFactoryLists(factoryLists1);
                areaDeptTreeMgr.saveAreaDeptTree(area);
            }

            factory.setNodeName(partnerDept.getName());
            areaDeptTreeMgr.saveAreaDeptTree(factory);
        }
        if (isNew) {

            partnerDeptMgr.savePartnerDept(partnerDept);
            if (partnerDept.getTreeNodeId() != null) {
                AreaDeptTree factory = areaDeptTreeMgr.getAreaDeptTree(partnerDept.getTreeId());
                //在树表里存人力信息节点
                AreaDeptTree user = new AreaDeptTree();
                user.setParentNodeId(factory.getNodeId());
                user.setNodeName("人力信息");
                user.setNodeType(AreaDeptTreeConstants.NODE_TYPE_USER);
                areaDeptTreeMgr.saveAreaDeptTree(user);

                //在树表里存仪器仪表节点
                AreaDeptTree instrument = new AreaDeptTree();
                instrument.setParentNodeId(factory.getNodeId());
                instrument.setNodeName("仪器仪表");
                instrument.setNodeType(AreaDeptTreeConstants.NODE_TYPE_INSTRUMENT);
                areaDeptTreeMgr.saveAreaDeptTree(instrument);

                //在树表里存车辆管理节点
                AreaDeptTree car = new AreaDeptTree();
                car.setParentNodeId(factory.getNodeId());
                car.setNodeName("车辆管理");
                car.setNodeType(AreaDeptTreeConstants.NODE_TYPE_CAR);
                areaDeptTreeMgr.saveAreaDeptTree(car);

                //在树表里存油机管理节点
                AreaDeptTree oil = new AreaDeptTree();
                oil.setParentNodeId(factory.getNodeId());
                oil.setNodeName("油机管理");
                oil.setNodeType(AreaDeptTreeConstants.NODE_TYPE_OIL);
                areaDeptTreeMgr.saveAreaDeptTree(oil);
            }

        } else {
            partnerDeptMgr.savePartnerDept(partnerDept);
        }
        this.defaultParentNodeId = parentNodeId;
        request.setAttribute("refreshTree", "1");

        return search(mapping, partnerDeptForm, request, response);
    }

    /**
     * ɾ����
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward remove(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartnerDeptMgr partnerDeptMgr = (PartnerDeptMgr) getBean("partnerDeptMgr");
        AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));

        String parentNodeId = request.getParameter("parentNodeId");
        this.defaultParentNodeId = parentNodeId;

        AreaDeptTree area = areaDeptTreeMgr.getAreaDeptTreeByNodeId(parentNodeId);
        String factoryLists = area.getFactoryLists();
        String[] factories = factoryLists.split(",");
        String factoryLists1 = "";

        if (!id.equals("")) {
            //删除地市节点字段factoryLists关于此厂商的记录,用于合作伙伴统计报表
            PartnerDept partnerDept = partnerDeptMgr.getPartnerDept(id);
            for (int j = 0; j < factories.length; j++) {
                if (factories[j].equals(partnerDept.getName())) {
                    factories[j] = "";
                    break;
                }
            }
            for (int k = 0; k < factories.length; k++) {
                if (!factories[k].equals("") && k < factories.length - 1) {
                    factoryLists1 += (factories[k] + ",");
                } else if (k == factories.length - 1) factoryLists1 += factories[factories.length - 1];
                if (factoryLists1.lastIndexOf(",") == factoryLists1.length() - 1) {
                    factoryLists1 = factoryLists1.substring(0, factoryLists1.length() - 2);
                }
            }
            area.setFactoryLists(factoryLists);
            areaDeptTreeMgr.saveAreaDeptTree(area);
            //---------------------------------------------
            String treeNodeId = partnerDept.getTreeNodeId();
            areaDeptTreeMgr.removeAreaDeptTreeByNodeId(treeNodeId);
            partnerDeptMgr.removePartnerDept(id);
            //对合作伙伴下的相关记录“人力信息、车辆管理、油机管理、仪器仪表”进行处理

            //----------------------
        }

        String[] ids = request.getParameterValues("checkbox11");
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                PartnerDept partnerDept = partnerDeptMgr.getPartnerDept(ids[i]);
                //删除地市节点字段factoryLists关于此厂商的记录,用于合作伙伴统计报表
                for (int j = 0; j < factories.length; j++) {
                    if (factories[j].equals(partnerDept.getName())) {
                        factories[j] = "";
                        break;
                    }
                }
                //--------------------------------
                String treeNodeId = partnerDept.getTreeNodeId();
                areaDeptTreeMgr.removeAreaDeptTreeByNodeId(treeNodeId);
                partnerDeptMgr.removePartnerDept(ids[i]);
                //对合作伙伴下的相关记录“人力信息、车辆管理、油机管理、仪器仪表”进行处理

                //----------------------
            }
            //-----------------删除地市节点字段factoryLists关于此厂商的记录,用于合作伙伴统计报表
            for (int k = 0; k < factories.length; k++) {
                if (!factories[k].equals("") && k < factories.length - 1) {
                    factoryLists1 += (factories[k] + ",");
                }
                if (k == factories.length - 1) {
                    factoryLists1 += factories[factories.length - 1];
                }
            }
            if (factoryLists1.length() > 0 && factoryLists1.lastIndexOf(",") == factoryLists1.length() - 1) {
                System.out.println("4444444444444444 factoryLists1.length()-1 = " + (factoryLists1.length() - 1));
                System.out.println("5555555555555555 factoryLists1.lastIndexOf(,) = " + factoryLists1.lastIndexOf(","));
                factoryLists1 = factoryLists1.substring(0, factoryLists1.length() - 1);
            }
            area.setFactoryLists(factoryLists1);
            areaDeptTreeMgr.saveAreaDeptTree(area);
            //------------------------------
        }

        return search(mapping, form, request, response);
    }

    /**
     * ��ҳ��ʾ�����б�
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                PartnerDeptConstants.PARTNERDEPT_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        PartnerDeptMgr partnerDeptMgr = (PartnerDeptMgr) getBean("partnerDeptMgr");

        String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));//地市的nodeid
        String whereStr = " where 1=1";
        if (!nodeId.equals("")) {
            whereStr += " and treeNodeId in (select nodeId from AreaDeptTree tree where tree.parentNodeId = " + nodeId + ")";
            request.setAttribute("parentNodeId", nodeId);
        } else if (this.defaultParentNodeId != null && !this.defaultParentNodeId.equals("")) {
            whereStr += " and treeNodeId in (select nodeId from AreaDeptTree tree where tree.parentNodeId = " + this.defaultParentNodeId + ")";
            request.setAttribute("parentNodeId", this.defaultParentNodeId);
            this.defaultParentNodeId = "";
        }
        //组装查询条件
        PartnerDeptForm partnerDeptForm = (PartnerDeptForm) form;
        if (request.getParameter("nameSearch") != null && !request.getParameter("nameSearch").equals("")) {
            whereStr += " and name like '%" + request.getParameter("nameSearch") + "%'";
        }
        if (request.getParameter("managerSearch") != null && !request.getParameter("managerSearch").equals("")) {
            whereStr += " and manager like '%" + request.getParameter("managerSearch") + "%'";
        }
        if (request.getParameter("phoneSearch") != null && !request.getParameter("phoneSearch").equals("")) {
            whereStr += " and phone like '%" + request.getParameter("phoneSearch") + "%'";
        }

        Map map = (Map) partnerDeptMgr.getPartnerDepts(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(PartnerDeptConstants.PARTNERDEPT_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("list");
    }
}