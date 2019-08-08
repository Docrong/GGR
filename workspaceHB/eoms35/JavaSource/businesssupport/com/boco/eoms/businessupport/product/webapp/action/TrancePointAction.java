package com.boco.eoms.businessupport.product.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;
import com.boco.eoms.businessupport.product.model.TrancePoint;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.businessupport.product.service.IGprsSpecialLineManager;
import com.boco.eoms.businessupport.product.service.IIPSpecialLineManager;
import com.boco.eoms.businessupport.product.service.ITransferSpecialLineManager;
import com.boco.eoms.businessupport.product.service.TrancePointMgr;
import com.boco.eoms.businessupport.product.util.BusinessupportConstants;
import com.boco.eoms.businessupport.product.webapp.form.TrancePointForm;
import com.boco.eoms.businessupport.util.Constants;

/**
 * <p>
 * Title:业务接入点
 * </p>
 * <p>
 * Description:业务接入点
 * </p>
 * <p>
 * Sun May 16 14:18:55 CST 2010
 * </p>
 *
 * @moudle.getAuthor() lizhigang
 * @moudle.getVersion() 3.5
 */
public final class TrancePointAction extends BaseAction {

    /**
     * 未指定方法时默认调用的方法
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
     * 新增业务接入点
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
        ActionForward af = mapping.findForward("edit");
        return af;
    }

    /**
     * 修改业务接入点
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
        TrancePointMgr businessupportMgr = (TrancePointMgr) getBean("businessupportMgr");

//		PortDesignService ps = new PortDesignService();
//		ps.getPortData("SN-311-100516-30002");
//		
//		ps.getBusinessData("SN-311-100516-30002");

        String id = StaticMethod.null2String(request.getParameter("id"));
        TrancePoint businessupport = businessupportMgr.getBusinessupport(id);
        TrancePointForm trancePointForm = (TrancePointForm) convert(businessupport);
        updateFormBean(mapping, request, trancePointForm);
        return mapping.findForward("edit");
    }

    /**
     * 修改业务接入点
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editPoint(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TrancePointForm trancePointForm = new TrancePointForm();

        String orderSheetId = StaticMethod.null2String(request.getParameter("orderSheetId"));

        IOrderSheetManager mgr = (IOrderSheetManager) ApplicationContextHolder
                .getInstance().getBean("IOrderSheetManager");

        OrderSheet os = mgr.getOrderSheet(orderSheetId);
        String specialType = os.getOrderBuisnessType();
        IOrderSheetManager iOrderSheetManager = (IOrderSheetManager) ApplicationContextHolder
                .getInstance().getBean("IOrderSheetManager");


        Object objectName = "";
        if (specialType.equals(Constants._GPRS_LINE)) {//GPRS专线
            objectName = new GprsSpecialLine();
            List list = iOrderSheetManager.getSpecialLinesByType(orderSheetId, objectName);

            GprsSpecialLine gps = (GprsSpecialLine) list.get(0);

            trancePointForm.setId(gps.getId());
            trancePointForm.setPortInterfaceType(gps.getPortAInterfaceType());
            trancePointForm.setPortEngineRoomName(gps.getZpointComputerHorseName());
            trancePointForm.setSevPointContactPhone(gps.getInterfaceCustomConnPhoneZ());
            trancePointForm.setSevPointContactEmail(gps.getInterfaceCustomConnMailZ());
            trancePointForm.setSevPointContactAdd(gps.getInterfaceCustomConnAddZ());
            trancePointForm.setFiberEquipName(gps.getFiberEquipNameZ());
            trancePointForm.setFiberEquipCode(gps.getFiberEquipCodeZ());
            trancePointForm.setSiteEquipCode(gps.getSiteEquipCodeZ());
            trancePointForm.setSiteName(gps.getSiteNameZ());
            trancePointForm.setAccessSiteIden(gps.getAccessSiteIdenZ());
            trancePointForm.setFiberLength(gps.getFiberLengthZ());
            trancePointForm.setFiberOwner(gps.getFiberOwnerZ());
            trancePointForm.setSevPointContact(gps.getInterfaceCustomConnPersonZ());


        } else if (specialType.equals(Constants._IP_LINE)) {//IP专线
            objectName = new IPSpecialLine();
            List list = iOrderSheetManager.getSpecialLinesByType(orderSheetId, objectName);

            IPSpecialLine ip = (IPSpecialLine) list.get(0);
            trancePointForm.setId(ip.getId());
            trancePointForm.setPortInterfaceType(ip.getPortAInterfaceType());
            trancePointForm.setPortEngineRoomName(ip.getZpointComputerHorseName());
            trancePointForm.setSevPointContactPhone(ip.getInterfaceCustomConnPhoneZ());
            trancePointForm.setSevPointContactEmail(ip.getInterfaceCustomConnMailZ());
            trancePointForm.setSevPointContactAdd(ip.getInterfaceCustomConnAddZ());
            trancePointForm.setFiberEquipName(ip.getFiberEquipNameZ());
            trancePointForm.setFiberEquipCode(ip.getFiberEquipCodeZ());
            trancePointForm.setSiteEquipCode(ip.getSiteEquipCodeZ());
            trancePointForm.setSiteName(ip.getSiteNameZ());
            trancePointForm.setAccessSiteIden(ip.getAccessSiteIdenZ());
            trancePointForm.setFiberLength(ip.getFiberLengthZ());
            trancePointForm.setFiberOwner(ip.getFiberOwnerZ());
            trancePointForm.setSevPointContact(ip.getInterfaceCustomConnPersonZ());


        } else if (specialType.equals(Constants._TRANSFER_LINE)) {//传输专线
            objectName = new TransferSpecialLine();
            List list = iOrderSheetManager.getSpecialLinesByType(orderSheetId, objectName);

            TransferSpecialLine tfs = (TransferSpecialLine) list.get(0);

            trancePointForm.setId(tfs.getId());
            trancePointForm.setPortInterfaceType(tfs.getPortAInterfaceType());
            trancePointForm.setPortEngineRoomName(tfs.getZpointComputerHorseName());
            trancePointForm.setSevPointContactPhone(tfs.getInterfaceCustomConnPhoneZ());
            trancePointForm.setSevPointContactEmail(tfs.getInterfaceCustomConnMailZ());
            trancePointForm.setSevPointContactAdd(tfs.getInterfaceCustomConnAddZ());
            trancePointForm.setFiberEquipName(tfs.getFiberEquipNameZ());
            trancePointForm.setFiberEquipCode(tfs.getFiberEquipCodeZ());
            trancePointForm.setSiteEquipCode(tfs.getSiteEquipCodeZ());
            trancePointForm.setSiteName(tfs.getSiteNameZ());
            trancePointForm.setAccessSiteIden(tfs.getAccessSiteIdenZ());
            trancePointForm.setFiberLength(tfs.getFiberLengthZ());
            trancePointForm.setFiberOwner(tfs.getFiberOwnerZ());
            trancePointForm.setSevPointContact(tfs.getInterfaceCustomConnPersonZ());

        }
        request.setAttribute("specialType", specialType);

//		trancePointForm = (TrancePointForm) convert(businessupport);
        updateFormBean(mapping, request, trancePointForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存业务接入点
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
        String specialType = request.getParameter("specialType");
        IGprsSpecialLineManager iGprsSpecialLineManager = (IGprsSpecialLineManager) getBean("IGprsSpecialLineManager");
        IIPSpecialLineManager iIPSpecialLineManager = (IIPSpecialLineManager) getBean("IIPSpecialLineManager");
        ITransferSpecialLineManager iTransferSpecialLineManager = (ITransferSpecialLineManager) getBean("ITransferSpecialLineManager");
        String id = request.getParameter("id");

        if (specialType.equals(Constants._GPRS_LINE)) {//GPRS专线

            GprsSpecialLine gps = iGprsSpecialLineManager.getGprsSpecialLine(id);

            gps.setPortAInterfaceType(request.getParameter("portInterfaceType"));
            gps.setZpointComputerHorseName(request.getParameter("portEngineRoomName"));
            gps.setInterfaceCustomConnPhoneZ(request.getParameter("sevPointContactPhone"));
            gps.setInterfaceCustomConnMailZ(request.getParameter("sevPointContactEmail"));
            gps.setInterfaceCustomConnAddZ(request.getParameter("sevPointContactAdd"));
            gps.setFiberEquipNameZ(request.getParameter("fiberEquipName"));
            gps.setFiberEquipCodeZ(request.getParameter("fiberEquipCode"));
            gps.setSiteEquipCodeZ(request.getParameter("siteEquipCode"));
            gps.setSiteNameZ(request.getParameter("siteName"));
            gps.setAccessSiteIdenZ(request.getParameter("accessSiteIden"));
            gps.setFiberLengthZ(request.getParameter("fiberLength"));
            gps.setFiberOwnerZ(request.getParameter("fiberOwner"));
            gps.setInterfaceCustomConnPersonZ(request.getParameter("sevPointContact"));

            iGprsSpecialLineManager.saveOrUpdate(gps);

        } else if (specialType.equals(Constants._IP_LINE)) {//IP专线
            IPSpecialLine ip = iIPSpecialLineManager.getIPSpecialLine(id);

            ip.setPortAInterfaceType(request.getParameter("portInterfaceType"));
            ip.setZpointComputerHorseName(request.getParameter("portEngineRoomName"));
            ip.setInterfaceCustomConnPhoneZ(request.getParameter("sevPointContactPhone"));
            ip.setInterfaceCustomConnMailZ(request.getParameter("sevPointContactEmail"));
            ip.setInterfaceCustomConnAddZ(request.getParameter("sevPointContactAdd"));
            ip.setFiberEquipNameZ(request.getParameter("fiberEquipName"));
            ip.setFiberEquipCodeZ(request.getParameter("fiberEquipCode"));
            ip.setSiteEquipCodeZ(request.getParameter("siteEquipCode"));
            ip.setSiteNameZ(request.getParameter("siteName"));
            ip.setAccessSiteIdenZ(request.getParameter("accessSiteIden"));
            ip.setFiberLengthZ(request.getParameter("fiberLength"));
            ip.setFiberOwnerZ(request.getParameter("fiberOwner"));
            ip.setInterfaceCustomConnPersonZ(request.getParameter("sevPointContact"));

            iIPSpecialLineManager.saveOrUpdate(ip);

        } else if (specialType.equals(Constants._TRANSFER_LINE)) {//传输专线
            TransferSpecialLine ts = iTransferSpecialLineManager.getTransferSpecialLine(id);
            ts.setId(id);
            ts.setPortAInterfaceType(request.getParameter("portInterfaceType"));
            ts.setZpointComputerHorseName(request.getParameter("portEngineRoomName"));
            ts.setInterfaceCustomConnPhoneZ(request.getParameter("sevPointContactPhone"));
            ts.setInterfaceCustomConnMailZ(request.getParameter("sevPointContactEmail"));
            ts.setInterfaceCustomConnAddZ(request.getParameter("sevPointContactAdd"));
            ts.setFiberEquipNameZ(request.getParameter("fiberEquipName"));
            ts.setFiberEquipCodeZ(request.getParameter("fiberEquipCode"));
            ts.setSiteEquipCodeZ(request.getParameter("siteEquipCode"));
            ts.setSiteNameZ(request.getParameter("siteName"));
            ts.setAccessSiteIdenZ(request.getParameter("accessSiteIden"));
            ts.setFiberLengthZ(request.getParameter("fiberLength"));
            ts.setFiberOwnerZ(request.getParameter("fiberOwner"));
            ts.setInterfaceCustomConnPersonZ(request.getParameter("sevPointContact"));

            iTransferSpecialLineManager.saveOrUpdate(ts);

        }

        return search(mapping, form, request, response);
    }

    /**
     * 保存业务接入点数据按照不同的专线类型
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward savePoint(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TrancePointMgr businessupportMgr = (TrancePointMgr) getBean("businessupportMgr");
        TrancePointForm trancePointForm = (TrancePointForm) form;
        boolean isNew = (null == trancePointForm.getId() || "".equals(trancePointForm.getId()));
        TrancePoint trancePointForm1 = (TrancePoint) convert(trancePointForm);

        if (isNew) {
            businessupportMgr.saveBusinessupport(trancePointForm1);
        } else {
            TrancePoint trancePoint = businessupportMgr.getBusinessupport(trancePointForm.getId());
            trancePoint.setAccessSiteIden(trancePointForm.getAccessSiteIden());
            trancePoint.setFiberEquipCode(trancePointForm.getFiberEquipCode());
            trancePoint.setSevPointContactPhone(trancePointForm.getSevPointContactPhone());
            trancePoint.setSevPointContactEmail(trancePointForm.getSevPointContactEmail());
            trancePoint.setSevPointContactAdd(trancePointForm.getSevPointContactAdd());
            trancePoint.setFiberEquipName(trancePointForm.getFiberEquipName());
            trancePoint.setSiteEquipCode(trancePointForm.getSiteEquipCode());
            trancePoint.setSiteName(trancePointForm.getSiteName());
            trancePoint.setSevPointContact(trancePointForm.getSevPointContact());
            trancePoint.setRemark(trancePointForm.getRemark());
            trancePoint.setFiberNum(trancePointForm.getFiberNum());
            trancePoint.setFiberLength(trancePointForm.getFiberLength());
            trancePoint.setFiberOwner(trancePointForm.getFiberOwner());

            businessupportMgr.saveBusinessupport(trancePoint);
        }

        return search(mapping, trancePointForm, request, response);
    }

    /**
     * 删除业务接入点
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
        TrancePointMgr businessupportMgr = (TrancePointMgr) getBean("businessupportMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        businessupportMgr.removeBusinessupport(id);
        return search(mapping, form, request, response);
    }

    /**
     * 分页显示业务接入点列表
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
                BusinessupportConstants.BUSINESSUPPORT_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        TrancePointMgr businessupportMgr = (TrancePointMgr) getBean("businessupportMgr");
        Map map = (Map) businessupportMgr.getBusinessupports(pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute(BusinessupportConstants.BUSINESSUPPORT_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }


}