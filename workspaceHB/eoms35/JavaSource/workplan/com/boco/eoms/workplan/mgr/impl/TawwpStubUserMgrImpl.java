package com.boco.eoms.workplan.mgr.impl;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.workplan.dao.ITawwpStubUserDao;
import com.boco.eoms.workplan.dao.TawwpUtilDAO;
import com.boco.eoms.workplan.mgr.ITawwpStubUserMgr;
import com.boco.eoms.workplan.model.TawwpStubUser;
import com.boco.eoms.workplan.util.TawwpException;
import com.boco.eoms.workplan.util.TawwpUtil;
import com.boco.eoms.workplan.vo.TawwpStubUserVO;


public class TawwpStubUserMgrImpl implements ITawwpStubUserMgr {

    private ITawwpStubUserDao tawwpStubUserDao;

    /**
     * 业务逻辑：制定代理人员（SET-DEPUTY-001）
     *
     * @param _cruser    String 创建人
     * @param _stubuser  String 代理人
     * @param _startDate String 开始时间
     * @param _endDate   String 结束时间
     * @param _checkUser String 审批人
     * @param _content   String 代理内容
     * @throws TawwpException 异常信息
     */
    public void addStubUser(String _cruser, String _stubuser, String _startDate,
                            String _endDate, String _checkUser, String _content) throws
            TawwpException {


        TawwpStubUser tawwpStubUser = null;

        try {
            tawwpStubUser = new TawwpStubUser(_cruser, _stubuser, _startDate,
                    _endDate, _content, _checkUser, "0"); //封装代理数据对象

            tawwpStubUserDao.saveStubUser(tawwpStubUser); //保存代理
            try {
                TawwpUtil.sendSMS(_checkUser, "作业计划申请代理人《"
                        + StaticMethod
                        .null2String(tawwpStubUser.getStubuser())
                        + "》需要您审批", tawwpStubUser.getId());
                // 发送短消息
                TawwpUtil.sendMail(_checkUser, "作业计划申请代理人《"
                        + StaticMethod
                        .null2String(tawwpStubUser.getStubuser())
                        + "》需要您审批", tawwpStubUser.getId());
            } catch (Exception e) {
                System.out.print("消息发送异常" + e);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划代理保存出现异常");
        }
    }

    /**
     * 业务逻辑：制定代理人员（EDIT-DEPUTY-002）
     *
     * @param _id        String 代理标识
     * @param _stubuser  String 代理人
     * @param _startDate String 开始时间
     * @param _endDate   String 结束时间
     * @param _checkUser String 审批人
     * @param _content   String 代理内容
     * @throws TawwpException 异常信息
     */
    public void editStubUser(String _id, String _stubuser, String _startDate,
                             String _endDate, String _checkUser,
                             String _content) throws TawwpException {
        TawwpStubUser tawwpStubUser = null;

        try {
            tawwpStubUser = tawwpStubUserDao.loadStubUser(_id); //获取作业计划代理对象

            //如果代理状态为“新建”或“驳回”
            if (tawwpStubUser.getState().equals("0") ||
                    tawwpStubUser.getState().equals("3")) {
                tawwpStubUser.setStubuser(_stubuser);
                tawwpStubUser.setStartDate(_startDate);
                tawwpStubUser.setEndDate(_endDate);
                tawwpStubUser.setCheckuser(_checkUser);
                tawwpStubUser.setContent(_content);

                tawwpStubUserDao.updateStubUser(tawwpStubUser); //保存作业计划模板
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划代理修改出现异常");
        }
    }

    /**
     * 内部逻辑：修改代理状态
     *
     * @param _id    String 代理标识
     * @param _state String 状态
     * @throws TawwpException 异常信息
     */
    private void checkStubUser(String _id, String _state) throws TawwpException {
        TawwpStubUser tawwpStubUser = null;
        try {
            tawwpStubUser = tawwpStubUserDao.loadStubUser(_id); //获取作业计划代理对象
            tawwpStubUser.setState(_state);
            tawwpStubUserDao.updateStubUser(tawwpStubUser); //更新作业计划代理状态
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划代理状态修改出现异常");
        }
    }

    /**
     * 业务逻辑：提交代理信息（REFER-DEPUTY-001）
     *
     * @param _id String 代理标识
     * @throws TawwpException 异常信息
     */
    public void referStubUser(String _id) throws TawwpException {
        this.checkStubUser(_id, "2");
    }

    /**
     * 业务逻辑：通过代理信息（PASS-DEPUTY-001）
     *
     * @param _id String 代理标识
     * @throws TawwpException 异常信息
     */
    public void passStubUser(String _id) throws TawwpException {
        this.checkStubUser(_id, "1");
    }

    /**
     * 业务逻辑：驳回代理信息（REJECT-DEPUTY-001）
     *
     * @param _id String 代理标识
     * @throws TawwpException 异常信息
     */
    public void rejectStubUser(String _id) throws TawwpException {
        this.checkStubUser(_id, "3");
    }

    /**
     * 业务逻辑：删除代理信息（ANNUL-DEPUTY-001）
     *
     * @param _id String 代理标识
     * @throws TawwpException 异常信息
     */
    public void annulStubUser(String _id) throws TawwpException {
        this.checkStubUser(_id, "4");
    }

    /**
     * 业务逻辑：删除代理信息（DELETE-DEPUTY-001）
     *
     * @param _id String 代理标识
     * @throws TawwpException 异常信息
     */
    public void removeStubUser(String _id) throws TawwpException {
        TawwpStubUser tawwpStubUser = null;

        try {
            tawwpStubUser = tawwpStubUserDao.loadStubUser(_id); //获取作业计划代理对象
            if (tawwpStubUser != null) {
                tawwpStubUserDao.deleteStubUser(tawwpStubUser); //删除作业计划代理
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划代理删除出现异常");
        }
    }

    /**
     * 业务逻辑：查询待审核代理信息（CHECK-DEPUTY-001）
     *
     * @param _checkuser String 审批人
     * @return List 待审核代理信息
     * @throws TawwpException 异常信息
     */
    public List listStubUserByCheckuser(String _checkuser) throws TawwpException {

        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
        TawwpStubUser tawwpStubUser = null;
        TawwpStubUserVO tawwpStubUserVO = null;
        List list = null;
        List newList = new ArrayList();
        try {
            list = tawwpStubUserDao.loadStubUserByCheckUser(_checkuser); //获取待审批作业计划代理对象列表

            //循环进行数据处理
            for (int i = 0; i < list.size(); i++) {
                tawwpStubUser = (TawwpStubUser) list.get(i);
                tawwpStubUserVO = new TawwpStubUserVO();
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpStubUserVO, tawwpStubUser);
                tawwpStubUserVO.setCruserName(tawwpUtilDAO.getUserName(tawwpStubUserVO.
                        getCruser()));
                tawwpStubUserVO.setStubuserName(tawwpUtilDAO.getUserName(
                        tawwpStubUserVO.getStubuser()));
                tawwpStubUserVO.setCheckuserName(tawwpUtilDAO.getUserName(
                        tawwpStubUserVO.getCheckuser()));
                tawwpStubUserVO.setStateName(TawwpStubUserVO.getStateTypeName(
                        tawwpStubUserVO.getState()));
                newList.add(tawwpStubUserVO);
            }

            return newList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划代理查询出现异常");
        }
    }

    /**
     * 业务逻辑：查询用户的代理信息（CRUSER-DEPUTY-001）
     *
     * @param _cruser String 创建人
     * @return List 创建人的代理信息
     * @throws TawwpException 异常信息
     */
    public List listStubUserByCruser(String _cruser) throws TawwpException {

        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
        TawwpStubUser tawwpStubUser = null;
        TawwpStubUserVO tawwpStubUserVO = null;
        List list = null;
        List newList = new ArrayList();
        try {
            list = tawwpStubUserDao.loadStubUserByCrUser(_cruser, TawwpUtil.getCurrentDateTime()); //获取新建作业计划代理对象列表

            //循环进行数据处理
            for (int i = 0; i < list.size(); i++) {
                tawwpStubUser = (TawwpStubUser) list.get(i);
                tawwpStubUserVO = new TawwpStubUserVO();
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpStubUserVO, tawwpStubUser);
                tawwpStubUserVO.setCruserName(tawwpUtilDAO.getUserName(tawwpStubUserVO.
                        getCruser()));
                tawwpStubUserVO.setStubuserName(tawwpUtilDAO.getUserName(
                        tawwpStubUserVO.getStubuser()));
                tawwpStubUserVO.setCheckuserName(tawwpUtilDAO.getUserName(
                        tawwpStubUserVO.getCheckuser()));
                tawwpStubUserVO.setStateName(TawwpStubUserVO.getStateTypeName(
                        tawwpStubUserVO.getState()));
                newList.add(tawwpStubUserVO);
            }

            return newList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划代理查询出现异常");
        }
    }

    /**
     * 业务逻辑：查询用户的代理信息（CRUSER-DEPUTY-001）
     *
     * @param _stubuser String 代理人
     * @return List 需要代理的信息
     * @throws TawwpException 异常信息
     */
    public List listStubUserByStubuser(String _stubuser) throws TawwpException {

        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
        TawwpStubUser tawwpStubUser = null;
        TawwpStubUserVO tawwpStubUserVO = null;
        List list = null;
        List newList = new ArrayList();
        try {
            list = tawwpStubUserDao.loadStubUserByStubUser(_stubuser,
                    TawwpUtil.getCurrentDateTime()); //获取需要代理人代理的作业计划代理对象列表

            //循环进行数据处理
            for (int i = 0; i < list.size(); i++) {
                tawwpStubUser = (TawwpStubUser) list.get(i);
                tawwpStubUserVO = new TawwpStubUserVO();
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpStubUserVO, tawwpStubUser);
                tawwpStubUserVO.setCruserName(tawwpUtilDAO.getUserName(tawwpStubUserVO.
                        getCruser()));
                tawwpStubUserVO.setStubuserName(tawwpUtilDAO.getUserName(
                        tawwpStubUserVO.getStubuser()));
                tawwpStubUserVO.setCheckuserName(tawwpUtilDAO.getUserName(
                        tawwpStubUserVO.getCheckuser()));
                tawwpStubUserVO.setStateName(TawwpStubUserVO.getStateTypeName(
                        tawwpStubUserVO.getState()));
                newList.add(tawwpStubUserVO);
            }

            return newList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划代理查询出现异常");
        }
    }


    /**
     * add by gongyufeng for serach
     * 业务逻辑：查询用户的代理信息（CRUSER-DEPUTY-001）
     *
     * @param _stubuser String 代理人
     * @return List 需要代理的信息
     * @throws TawwpException 异常信息
     */
    public List listStubUserByStubuser(String _stubuser, String stubtime, String checkuser, String state) throws TawwpException {

        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
        TawwpStubUser tawwpStubUser = null;
        TawwpStubUserVO tawwpStubUserVO = null;
        List list = null;
        List newList = new ArrayList();

        try {

            list = tawwpStubUserDao.loadStubUserByStubUser(_stubuser, stubtime, checkuser, state); //获取需要代理人代理的作业计划代理对象列表

            //循环进行数据处理
            for (int i = 0; i < list.size(); i++) {
                tawwpStubUser = (TawwpStubUser) list.get(i);
                tawwpStubUserVO = new TawwpStubUserVO();
                MyBeanUtils.copyPropertiesFromDBToPage(tawwpStubUserVO, tawwpStubUser);
                tawwpStubUserVO.setCruserName(tawwpUtilDAO.getUserName(tawwpStubUserVO.
                        getCruser()));
                tawwpStubUserVO.setStubuserName(tawwpUtilDAO.getUserName(
                        tawwpStubUserVO.getStubuser()));
                tawwpStubUserVO.setCheckuserName(tawwpUtilDAO.getUserName(
                        tawwpStubUserVO.getCheckuser()));
                tawwpStubUserVO.setStateName(TawwpStubUserVO.getStateTypeName(
                        tawwpStubUserVO.getState()));
                newList.add(tawwpStubUserVO);
            }

            return newList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("作业计划代理查询出现异常");
        }
    }

    /**
     * 业务逻辑：显示代理信息编辑页面（EDIT-DEPUTY-001）
     *
     * @param _stubuserId String 代理信息编号
     * @return TawwpStubUserVO 代理信息VO对象
     * @throws TawwpException 异常信息
     */
    public TawwpStubUserVO editView(String _stubuserId) throws TawwpException {

        TawwpUtilDAO tawwpUtilDAO = new TawwpUtilDAO();
        TawwpStubUser tawwpStubUser = null;
        TawwpStubUserVO tawwpStubUserVO = null;

        try {
            tawwpStubUser = tawwpStubUserDao.loadStubUser(_stubuserId); //获取代理信息对象

            tawwpStubUserVO = new TawwpStubUserVO();
            MyBeanUtils.copyPropertiesFromDBToPage(tawwpStubUserVO, tawwpStubUser);
            tawwpStubUserVO.setCruserName(tawwpUtilDAO.getUserName(tawwpStubUserVO.
                    getCruser()));
            tawwpStubUserVO.setStubuserName(tawwpUtilDAO.getUserName(
                    tawwpStubUserVO.getStubuser()));
            tawwpStubUserVO.setCheckuserName(tawwpUtilDAO.getUserName(
                    tawwpStubUserVO.getCheckuser()));
            tawwpStubUserVO.setStateName(TawwpStubUserVO.getStateTypeName(
                    tawwpStubUserVO.getState()));

            return tawwpStubUserVO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TawwpException("代理信息编辑页面显示出现异常");
        }
    }

    public void setTawwpStubUserDao(ITawwpStubUserDao tawwpStubUserDao) {
        this.tawwpStubUserDao = tawwpStubUserDao;
    }
}
