package com.boco.eoms.commons.system.priv.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivOperationManager;

public class TawSystemPrivOperationManagerImpl extends BaseManager implements
        ITawSystemPrivOperationManager {
    private TawSystemPrivOperationDao dao;

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSystemPrivOperationDao(TawSystemPrivOperationDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivOperationManager#getTawSystemPrivOperations(com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation)
     */
    public List getTawSystemPrivOperations(
            final TawSystemPrivOperation tawSystemPrivOperation) {
        return dao.getTawSystemPrivOperations(tawSystemPrivOperation);
    }

    /**
     * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivOperationManager#getTawSystemPrivOperation(String
     * id)
     */
    public TawSystemPrivOperation getTawSystemPrivOperation(final String id) {
        return dao.getTawSystemPrivOperation(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivOperationManager#saveTawSystemPrivOperation(TawSystemPrivOperation
     * tawSystemPrivOperation)
     */
    public void saveTawSystemPrivOperation(
            TawSystemPrivOperation tawSystemPrivOperation) {
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);
    }

    /**
     * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivOperationManager#removeTawSystemPrivOperation(String
     * id)
     */
    public void removeTawSystemPrivOperation(final String id) {
        dao.removeTawSystemPrivOperation(new String(id));
    }

    /**
     *
     */
    public Map getTawSystemPrivOperations(final Integer curPage,
                                          final Integer pageSize) {
        return dao.getTawSystemPrivOperations(curPage, pageSize, null);
    }

    public Map getTawSystemPrivOperations(final Integer curPage,
                                          final Integer pageSize, final String whereStr) {
        return dao.getTawSystemPrivOperations(curPage, pageSize, whereStr);
    }

    /**
     * 得到某一模块下的所有对象（包括子模块与功能项,包括该模块）
     */
    public List getAllSubObjects(String code) {
        return dao.getAllSubObjects(code);
    }

    /**
     * 得到所有的对象(根模块code=10,包括根模块)
     */
    public List getAllObjects() {
        return dao.getAllSubObjects("10");
    }

    /**
     * 得到某一模块下的所有子模块 （包括子模块的子模块，,包括该模块）
     */
    public List getAllSubModules(String code) {
        return dao.getAllSubModules(code);
    }

    /**
     * 得到某一模块下的所有功能项（包括子模块的功能项）
     */

    public List getAllSubOperations(String code) {
        return dao.getAllSubOperations(code);
    }

    /**
     * 得到某一模块下的对象（仅仅是有关该模块的）
     */
    public List getObjects(String code) {
        return dao.getObjects(code);
    }

    /**
     * 得到某一模块下的功能项（仅仅是有关该模块的）
     */
    public List getOperations(String code) {
        return dao.getOperations(code);
    }

    /**
     * 得到某一模块下的子模块(仅仅是有关该模块的)
     */

    public List getModules(String code, String deleted) {
        return dao.getModules(code, deleted);
    }

    /**
     * 得到某一模块的直接父模块
     *
     * @param childObjectCode
     * @return
     */
    public TawSystemPrivOperation getFatherModule(String childObjectCode) {
        return dao.getFatherModule(childObjectCode);
    }

    /**
     * 得到某一模块的所有父模块集合
     */

    public List getAllFatherModules(String code) {
        return dao.getAllFatherModules(code);
    }

    /**
     * 根据ID判断是否是模块（String id）
     */

    public boolean isModule(String id) {
        TawSystemPrivOperation tawSystemPrivOperation = null;
        String isApp = null;
        // tawSystemPrivOperation=dao.getTawSystemPrivOperation(new String(id));
        tawSystemPrivOperation = dao.getTawSystemPrivOpt(id);
        isApp = tawSystemPrivOperation.getIsApp();

        if ("0".equals(isApp)) // 1代表模块，0代表是功能项
            return false;
        return true;
    }

    /**
     * 得到该模块下下一个可以设置的CODE值
     *
     * @param fatherId
     * @return
     */
    public String getCodeValueForModify(String fatherId) {
        //
        if ("-1".equals(fatherId)) {
            int _iMaxCode = Integer.parseInt(dao.getMaxCodeValue());
            return "" + (_iMaxCode + 1);
        }

        //
        String maxCodeValue = dao.getMaxCodeValue(fatherId);
        if (maxCodeValue != null && !"".equals(maxCodeValue)) {
            long codeValue = Long.parseLong(maxCodeValue);
            //int codeValue = new Integer(maxCodeValue).intValue();
            return (codeValue + 1) + "";
        } else {
            if (!fatherId.equals("0")) {
                return fatherId + "01";
            } else {
                return "10";
            }
        }
    }

    public TawSystemPrivOperation getTawSystemPrivOpt(String code) {
        return dao.getTawSystemPrivOpt(code);
    }

    public String getParentModuleName(String _strParentId) {
        return dao.getParentModuleName(_strParentId);
    }

    public List getDirectSubModules(String _strParentId) {
        return dao.getDirectSubModules(_strParentId);
    }

    public void removeTawSystemPrivOperationByCode(String _strCode) {
        dao.removeTawSystemPrivOperationByCode(_strCode);
    }

    //2009.03.24加，删除节点，顺带删除所有子节点
    public void removeAllNodeByCode(final String _strCode) {
        dao.removeAllNodeByCode(_strCode);
    }

    /**
     * 根据CODE取得对应的NAME
     *
     * @param code
     * @return
     */
    public TawSystemPrivOperation getPrivOperationbyCode(String code) {
        return dao.getPrivOperationbyCode(code);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.priv.service.ITawSystemPrivOperationManager#getTawSystemPrivOerationsByParentCode(java.lang.String)
     */
    public List getTawSystemPrivOerationsByParentCode(String parentCode) {
        return dao.getTawSystemPrivOerationsByParentCode(parentCode);
    }

    /**
     * 得到某一模块下的所有未删除未隐藏的对象（包括子模块与功能项,包括该模块）
     */
    public List getAllEnableSubObjects(String code) {
        return dao.getAllEnableSubObjects(code);
    }

}
