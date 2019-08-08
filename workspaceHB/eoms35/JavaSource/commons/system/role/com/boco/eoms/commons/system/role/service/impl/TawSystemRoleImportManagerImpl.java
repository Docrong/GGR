package com.boco.eoms.commons.system.role.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.file.service.IFMImportFileManager;
import com.boco.eoms.commons.system.role.dao.ITawSystemRoleImportDao;
import com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao;
import com.boco.eoms.commons.system.role.model.TawSystemRoleImport;
import com.boco.eoms.commons.system.role.model.TawSystemRoleImportExcel;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;

public class TawSystemRoleImportManagerImpl extends BaseManager implements
        ITawSystemRoleImportManager {

    /**
     * excel mapping工具
     */
    private IFMImportFileManager fmImportFileManager;

    private ITawSystemRoleImportDao dao;

    /**
     * 子角色dao
     */
    private TawSystemSubRoleDao tawSystemSubRoleDao;

    /**
     * 角色与人员关联dao
     */
    private TawSystemUserRefRoleDao tawSystemUserRefRoleDao;

    /**
     * Set the Dao for communication with the data layer.
     *
     * @param dao
     */
    public void setTawSystemRoleImportDao(ITawSystemRoleImportDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager#getTawSystemRoleImports(com.boco.eoms.commons.system.role.model.TawSystemRoleImport)
     */
    public List getTawSystemRoleImports(
            final TawSystemRoleImport tawSystemRoleImport) {
        return dao.getTawSystemRoleImports(tawSystemRoleImport);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager#getTawSystemRoleImport(String
     * id)
     */
    public TawSystemRoleImport getTawSystemRoleImport(final String id) {
        return dao.getTawSystemRoleImport(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager#saveTawSystemRoleImport(TawSystemRoleImport
     * tawSystemRoleImport)
     */
    public void saveTawSystemRoleImport(TawSystemRoleImport tawSystemRoleImport) {
        dao.saveTawSystemRoleImport(tawSystemRoleImport);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager#removeTawSystemRoleImport(String
     * id)
     */
    public void removeTawSystemRoleImport(final String id) {
        dao.removeTawSystemRoleImport(new String(id));
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager#getTawSystemRoleImports(final
     * Integer curPage, final Integer pageSize)
     */
    public Map getTawSystemRoleImports(final Integer curPage,
                                       final Integer pageSize) {
        return dao.getTawSystemRoleImports(curPage, pageSize, null);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager#getTawSystemRoleImports(final
     * Integer curPage, final Integer pageSize, final String whereStr)
     */
    public Map getTawSystemRoleImports(final Integer curPage,
                                       final Integer pageSize, final String whereStr) {
        return dao.getTawSystemRoleImports(curPage, pageSize, whereStr);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager#getChildList(String
     * parentId)
     */
    public List getChildList(String parentId) {
        return dao.getChildList(parentId);
    }

    /**
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager#xGetChildNodes(String
     * parentId)
     */
    public JSONArray xGetChildNodes(String parentId) {
        JSONArray json = new JSONArray();
        List list = new ArrayList();
        list = this.getChildList(parentId);

        for (Iterator rowIt = list.iterator(); rowIt.hasNext(); ) {
            TawSystemRoleImport obj = (TawSystemRoleImport) rowIt.next();
            JSONObject jitem = new JSONObject();
            jitem.put("id", obj.getId());
            jitem.put("text", obj.getVersion());
            jitem.put("name", obj.getVersion());
            jitem.put("allowChild", true);
            jitem.put("allowDelete", true);
            // if (obj.getLeaf().equals("1")) {
            // jitem.put("leaf", true);
            // }
            json.put(jitem);
        }
        return json;
    }

    /**
     * @param fmImportFileManager the fmImportFileManager to set
     */
    public void setFmImportFileManager(IFMImportFileManager fmImportFileManager) {
        this.fmImportFileManager = fmImportFileManager;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager#mappingRoleExcel(java.lang.String)
     */
    public Map mappingRoleExcel(String excelPath) throws FMException {

        Map map = null;
        map = this.fmImportFileManager
                .impt(
                        StaticMethod.CLASSPATH_FLAG
                                + "com/boco/eoms/commons/system/role/model/TawSystemRoleImportExcel.xml",
                        excelPath);

        return map;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager#importRole(com.boco.eoms.commons.system.role.model.TawSystemRoleImportExcel)
     */
    public void importRole(List list, TawSystemRoleImport tawSystemRoleImport) {
        if (list != null) {
            for (Iterator it = list.iterator(); it.hasNext(); ) {
                TawSystemRoleImportExcel tawSystemRoleImportExcel = (TawSystemRoleImportExcel) it
                        .next();
                // 若此角色没有用户则不保存角色
                if (tawSystemRoleImportExcel.getUserId() == null
                        || "".equals(tawSystemRoleImportExcel.getUserId()
                        .trim())) {
                    continue;
                }
                // 保存子角色
                TawSystemSubRole tawSystemSubRole = new TawSystemSubRole();
                String areaId = getLastWord(tawSystemRoleImportExcel
                        .getAreaId(), "-");
                tawSystemSubRole.setArea(areaId);
                // tawSystemSubRole.setDeptId(areaId);
                tawSystemSubRole.setDeleted(Constants.NOT_DELETED_FLAG);
                tawSystemSubRole.setRoleId(Long.parseLong(getLastWord(
                        tawSystemRoleImportExcel.getRoleId(), "-")));
                tawSystemSubRole.setSubRoleName(tawSystemRoleImportExcel
                        .getSubRoleName());
                tawSystemSubRole.setLimitCount(new Integer(0));
                tawSystemSubRole.setType1(getLastWord(tawSystemRoleImportExcel
                        .getFirstType(), "-"));
                tawSystemSubRole.setType2(getLastWord(tawSystemRoleImportExcel
                        .getSecondType(), "-"));
                tawSystemSubRole.setType3(getLastWord(tawSystemRoleImportExcel
                        .getThirdType(), "-"));
                tawSystemSubRole.setType4(getLastWord(tawSystemRoleImportExcel
                        .getFourthType(), "-"));
                // 版本
                tawSystemSubRole.setVersion(tawSystemRoleImport.getVersion());
                // 写入唯一标识，将来用作判断是否存在角色
                tawSystemSubRole
                        .setLogo(togetherLogo(tawSystemRoleImportExcel));
                this.tawSystemSubRoleDao.saveTawSystemSubRole(tawSystemSubRole);

                String[] userIds = tawSystemRoleImportExcel.getUserId().split(
                        ",");
                for (int i = 0; i < userIds.length; i++) {
                    // 子角色人员关系
                    TawSystemUserRefRole tawSystemUserRefRole = new TawSystemUserRefRole();
                    tawSystemUserRefRole.setUserid(userIds[i]);
                    tawSystemUserRefRole.setRoleid(getLastWord(
                            tawSystemRoleImportExcel.getRoleId(), "-"));

                    // 默认为非虚拟组
                    if (tawSystemRoleImportExcel.getIsGroup() == null
                            || "".equals(tawSystemRoleImportExcel.getIsGroup()
                            .trim())) {
                        tawSystemRoleImportExcel
                                .setIsGroup(RoleConstants.ROLE_IMPORT_NO_GROUP);
                    }
                    // 若为虚拟组
                    if (RoleConstants.ROLE_IMPORT_GROUP
                            .equals(tawSystemRoleImportExcel.getIsGroup()
                                    .toUpperCase())) {
                        // 设置虚拟组类型
                        tawSystemUserRefRole
                                .setRoleType(RoleConstants.ROLETYPE_VIRTUAL);
                        // 若为虚拟组组长
                        if (userIds[i] != null
                                && userIds[i].equals(tawSystemRoleImportExcel
                                .getGroupId())) {
                            // 设置虚拟组组长
                            tawSystemUserRefRole
                                    .setGroupType(RoleConstants.groupType_leader);
                        } else {
                            tawSystemUserRefRole
                                    .setGroupType(RoleConstants.groupType_common);
                        }

                    } else {
                        // 子角色
                        tawSystemUserRefRole
                                .setRoleType(RoleConstants.ROLETYPE_SUBROLE);
                        // 设为普通人员
                        tawSystemUserRefRole
                                .setGroupType(RoleConstants.groupType_common);
                    }

                    // 做导入标记
                    tawSystemUserRefRole.setRemark("import");
                    tawSystemUserRefRole.setSubRoleid(tawSystemSubRole.getId());
                    // 版本
                    tawSystemUserRefRole.setVersion(tawSystemRoleImport
                            .getVersion());

                    this.tawSystemUserRefRoleDao
                            .saveTawSystemUserRefRole(tawSystemUserRefRole);
                }

            }
        }
    }

    //2009-5-13 修改导入方法，当子角色名已经存在，不导入给出提示
    public String importRole1(List list, TawSystemRoleImport tawSystemRoleImport) {
        String existInfo = "";
        int num = 0;
        if (list != null) {
            for (Iterator it = list.iterator(); it.hasNext(); ) {
                TawSystemRoleImportExcel tawSystemRoleImportExcel = (TawSystemRoleImportExcel) it
                        .next();
                // 若此角色没有用户则不保存角色
                if (tawSystemRoleImportExcel.getUserId() == null
                        || "".equals(tawSystemRoleImportExcel.getUserId()
                        .trim())) {
                    continue;
                }
                num++;
                //--------------2009-5-13----------
                String logo = togetherLogo(tawSystemRoleImportExcel);
                TawSystemSubRole subRole = tawSystemSubRoleDao.getTawSystemSubRoleByLogo(logo);//根据logo判断是否存在子角色，若存在则不保存
                //-------------------
                if (subRole == null || subRole.getId() == null) {
                    // 保存子角色
                    TawSystemSubRole tawSystemSubRole = new TawSystemSubRole();
                    String areaId = getLastWord(tawSystemRoleImportExcel
                            .getAreaId(), "-");
                    tawSystemSubRole.setArea(areaId);
                    // tawSystemSubRole.setDeptId(areaId);
                    tawSystemSubRole.setDeleted(Constants.NOT_DELETED_FLAG);
                    tawSystemSubRole.setRoleId(Long.parseLong(getLastWord(
                            tawSystemRoleImportExcel.getRoleId(), "-")));
                    tawSystemSubRole.setSubRoleName(tawSystemRoleImportExcel
                            .getSubRoleName());
                    tawSystemSubRole.setLimitCount(new Integer(0));
                    tawSystemSubRole.setType1(getLastWord(tawSystemRoleImportExcel
                            .getFirstType(), "-"));
                    tawSystemSubRole.setType2(getLastWord(tawSystemRoleImportExcel
                            .getSecondType(), "-"));
                    tawSystemSubRole.setType3(getLastWord(tawSystemRoleImportExcel
                            .getThirdType(), "-"));
                    tawSystemSubRole.setType4(getLastWord(tawSystemRoleImportExcel
                            .getFourthType(), "-"));
                    // 版本
                    tawSystemSubRole.setVersion(tawSystemRoleImport.getVersion());
                    // 写入唯一标识，将来用作判断是否存在角色
                    tawSystemSubRole
                            .setLogo(togetherLogo(tawSystemRoleImportExcel));
                    this.tawSystemSubRoleDao.saveTawSystemSubRole(tawSystemSubRole);

                    String[] userIds = tawSystemRoleImportExcel.getUserId().split(
                            ",");
                    for (int i = 0; i < userIds.length; i++) {
                        // 子角色人员关系
                        TawSystemUserRefRole tawSystemUserRefRole = new TawSystemUserRefRole();
                        tawSystemUserRefRole.setUserid(userIds[i]);
                        tawSystemUserRefRole.setRoleid(getLastWord(
                                tawSystemRoleImportExcel.getRoleId(), "-"));

                        // 默认为非虚拟组
                        if (tawSystemRoleImportExcel.getIsGroup() == null
                                || "".equals(tawSystemRoleImportExcel.getIsGroup()
                                .trim())) {
                            tawSystemRoleImportExcel
                                    .setIsGroup(RoleConstants.ROLE_IMPORT_NO_GROUP);
                        }
                        // 若为虚拟组
                        if (RoleConstants.ROLE_IMPORT_GROUP
                                .equals(tawSystemRoleImportExcel.getIsGroup()
                                        .toUpperCase())) {
                            // 设置虚拟组类型
                            tawSystemUserRefRole
                                    .setRoleType(RoleConstants.ROLETYPE_VIRTUAL);
                            // 若为虚拟组组长
                            if (userIds[i] != null
                                    && userIds[i].equals(tawSystemRoleImportExcel
                                    .getGroupId())) {
                                // 设置虚拟组组长
                                tawSystemUserRefRole
                                        .setGroupType(RoleConstants.groupType_leader);
                            } else {
                                tawSystemUserRefRole
                                        .setGroupType(RoleConstants.groupType_common);
                            }

                        } else {
                            // 子角色
                            tawSystemUserRefRole
                                    .setRoleType(RoleConstants.ROLETYPE_SUBROLE);
                            // 设为普通人员
                            tawSystemUserRefRole
                                    .setGroupType(RoleConstants.groupType_common);
                        }

                        // 做导入标记
                        tawSystemUserRefRole.setRemark("import");
                        tawSystemUserRefRole.setSubRoleid(tawSystemSubRole.getId());
                        // 版本
                        tawSystemUserRefRole.setVersion(tawSystemRoleImport
                                .getVersion());

                        this.tawSystemUserRefRoleDao
                                .saveTawSystemUserRefRole(tawSystemUserRefRole);
                    }

                } else existInfo += "第" + num + "行;";
            }
        }
        if (!existInfo.equals("")) existInfo += "<br>子角色已存在!";
        return existInfo;
    }


    /**
     * 拼接logo字段
     *
     * @param tawSystemRoleImportExcel
     * @return
     */
    private String togetherLogo(
            TawSystemRoleImportExcel tawSystemRoleImportExcel) {
        return tawSystemRoleImportExcel.getAreaId()
                + tawSystemRoleImportExcel.getFirstType()
                + tawSystemRoleImportExcel.getSecondType()
                + tawSystemRoleImportExcel.getThirdType()
                + tawSystemRoleImportExcel.getFourthType()
                + tawSystemRoleImportExcel.getRoleId();
    }

    /**
     * @param tawSystemSubRoleDao the tawSystemSubRoleDao to set
     */
    public void setTawSystemSubRoleDao(TawSystemSubRoleDao tawSystemSubRoleDao) {
        this.tawSystemSubRoleDao = tawSystemSubRoleDao;
    }

    /**
     * @param tawSystemUserRefRoleDao the tawSystemUserRefRoleDao to set
     */
    public void setTawSystemUserRefRoleDao(
            TawSystemUserRefRoleDao tawSystemUserRefRoleDao) {
        this.tawSystemUserRefRoleDao = tawSystemUserRefRoleDao;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager#restoreRole(java.lang.String)
     */
    public void restoreRole(String version) {
        this.tawSystemUserRefRoleDao
                .removeTawSystemUserRefRoleByVersion(version);
        this.tawSystemSubRoleDao.removeTawSystemSubRoleByVersion(version);
    }

    /**
     * 取分隔符后的最后一个单词
     *
     * @param str   要分隔的字符串
     * @param split 分隔符
     * @return 分隔符后的最后一个字符串
     */
    private String getLastWord(String str, String split) {
        if (str != null) {
            String splited[] = str.split(split);
            if (splited != null) {
                return splited[splited.length - 1];
            } else {
                return "";
            }
        }
        return "";
    }
//	public List listFirstLevelAreaByRoleId(long roleId) {
//		return dao.listFirstLevelAreaByRoleId(roleId);
//	}
}
