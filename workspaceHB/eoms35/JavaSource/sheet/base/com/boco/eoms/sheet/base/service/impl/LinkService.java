package com.boco.eoms.sheet.base.service.impl;

import java.lang.reflect.Method;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.sheet.base.dao.ILinkDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-18 16:36:24
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public abstract class LinkService extends BaseManager implements ILinkService {

    private BaseLink linkObject;

    private ILinkDAO linkDAO;

    /**
     * @return Returns the linkDAO.
     */
    public ILinkDAO getLinkDAO() {
        return linkDAO;
    }

    /**
     * @param linkDAO The linkDAO to set.
     */
    public void setLinkDAO(ILinkDAO linkDAO) {
        this.linkDAO = linkDAO;
    }


    /**
     * @return Returns the linkObject.
     */
    public BaseLink getLinkObject() {
        return linkObject;
    }

    /**
     * @param linkObject The linkObject to set.
     */
    public void setLinkObject(BaseLink linkObject) {
        this.linkObject = linkObject;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.service.ILinkService#getSingleLinkPO(java.lang.String)
     */
    public BaseLink getSingleLinkPO(String id) throws Exception {
        ILinkDAO dao = this.getLinkDAO();
        BaseLink link = dao.loadSinglePO(id, this.getLinkObject());
        return link;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.service.ILinkService#getLinksBySql(java.lang.String)
     */
    public List getLinksBySql(String sql) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.service.ILinkService#getLinksByMainId(java.lang.String)
     */
    public List getLinksByMainId(String mainId) throws Exception {
        ILinkDAO dao = getLinkDAO();
        List list = dao.listAllLinkOfWorkSheet(mainId, this.getLinkObject());
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.service.ILinkService#addLink(java.lang.Object)
     */
    public String addLink(Object obj) throws Exception {
        System.out.println("Eoms35 linkService addLink");
        Method getterMethod = obj.getClass().getMethod("getId", new Class[]{});
        Object idObject = getterMethod.invoke(obj, new Object[]{});
        String id = idObject.toString();
        if (id == null || id.equals("")) {
            id = UUIDHexGenerator.getInstance()
                    .getID();
            Method setterMethod = obj.getClass().getMethod("setId", new Class[]{String.class});
            setterMethod.invoke(obj, new Object[]{id});
        }
        linkDAO.saveOrUpdate(obj);
        return id;
    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.sheet.base.service.ILinkService#getDealTemplatesByUserIds(java.lang.String, java.lang.Integer, java.lang.Integer, int[], java.lang.String)
     */
    public List getDealTemplatesByUserIds(String userId, Integer curPage, Integer pageSize, int[] aTotal, String linkName, String codition) throws SheetException {
        return this.linkDAO.getDealTemplatesByUserIds(userId, curPage, pageSize, aTotal, linkName, codition);
    }


    public String getOperateRoleId(String preLinkId) {
        ILinkDAO dao = this.getLinkDAO();
        BaseLink link = dao.loadSinglePO(preLinkId, this.getLinkObject());
        if (link == null)
            return "";
        String str = link.getOperateRoleId();

        return str;

    }

    public String getOperateUserId(String preLinkId) {
        ILinkDAO dao = this.getLinkDAO();
        BaseLink link = dao.loadSinglePO(preLinkId, this.getLinkObject());
        if (link == null)
            return "";
        String str = link.getOperateUserId();

        return str;

    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.sheet.base.service.ILinkService#getLinkOperateByAiid(java.lang.String, java.lang.String)
     */
    public List getLinkOperateByAiid(String aiid, String linkName) {
        return this.linkDAO.getLinkOperateByAiid(aiid, linkName);
    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.sheet.base.service.ILinkService#getLinkOperateByAiid(java.lang.String, java.lang.String)
     */
    public void removeLink(Object obj) {
        this.linkDAO.removeLink(obj);
    }

    /**
     * 清除当前session
     */
    public void clearObjectOfCurrentSession() {
        this.linkDAO.clearObjectOfCurrentSession();
    }

    /**
     * 当有两个相同标识不同实体时执行
     */
    public void mergeObject(Object obj) {
        this.linkDAO.mergeObject(obj);
    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.sheet.base.service.ILinkService#getLinkOperateByAiid(java.lang.String, java.lang.String)
     */
    public List getLinksBycondition(String condition, String linkName) {
        return this.linkDAO.getLinksBycondition(condition, linkName);
    }
}
