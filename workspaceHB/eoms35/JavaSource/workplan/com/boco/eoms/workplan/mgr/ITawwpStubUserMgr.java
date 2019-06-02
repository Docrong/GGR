package com.boco.eoms.workplan.mgr;

 
import java.util.List;

 
import com.boco.eoms.workplan.util.TawwpException;
 
import com.boco.eoms.workplan.vo.TawwpStubUserVO;

public interface ITawwpStubUserMgr {
	 
	  /**
	   * 业务逻辑：制定代理人员（SET-DEPUTY-001）
	   * @param _cruser String 创建人
	   * @param _stubuser String 代理人
	   * @param _startDate String 开始时间
	   * @param _endDate String 结束时间
	   * @param _checkUser String 审批人
	   * @param _content String 代理内容
	   * @throws TawwpException 异常信息
	   */
	  public void addStubUser(String _cruser, String _stubuser, String _startDate,
	                          String _endDate, String _checkUser, String _content) throws
	      TawwpException ;

	  /**
	   * 业务逻辑：制定代理人员（EDIT-DEPUTY-002）
	   * @param _id String 代理标识
	   * @param _stubuser String 代理人
	   * @param _startDate String 开始时间
	   * @param _endDate String 结束时间
	   * @param _checkUser String 审批人
	   * @param _content String 代理内容
	   * @throws TawwpException 异常信息
	   */
	  public void editStubUser(String _id, String _stubuser, String _startDate,
	                           String _endDate, String _checkUser,
	                           String _content) throws TawwpException;

	 

	  /**
	   * 业务逻辑：提交代理信息（REFER-DEPUTY-001）
	   * @param _id String 代理标识
	   * @throws TawwpException 异常信息
	   */
	  public void referStubUser(String _id) throws TawwpException;

	  /**
	   * 业务逻辑：通过代理信息（PASS-DEPUTY-001）
	   * @param _id String 代理标识
	   * @throws TawwpException 异常信息
	   */
	  public void passStubUser(String _id) throws TawwpException ;

	  /**
	   * 业务逻辑：驳回代理信息（REJECT-DEPUTY-001）
	   * @param _id String 代理标识
	   * @throws TawwpException 异常信息
	   */
	  public void rejectStubUser(String _id) throws TawwpException ;

	  /**
	   * 业务逻辑：删除代理信息（ANNUL-DEPUTY-001）
	   * @param _id String 代理标识
	   * @throws TawwpException 异常信息
	   */
	  public void annulStubUser(String _id) throws TawwpException ;
	  /**
	   * 业务逻辑：删除代理信息（DELETE-DEPUTY-001）
	   * @param _id String 代理标识
	   * @throws TawwpException 异常信息
	   */
	  public void removeStubUser(String _id) throws TawwpException ;

	  /**
	   * 业务逻辑：查询待审核代理信息（CHECK-DEPUTY-001）
	   * @param _checkuser String 审批人
	   * @throws TawwpException 异常信息
	   * @return List 待审核代理信息
	   */
	  public List listStubUserByCheckuser(String _checkuser) throws TawwpException ;
	  /**
	   * 业务逻辑：查询用户的代理信息（CRUSER-DEPUTY-001）
	   * @param _cruser String 创建人
	   * @throws TawwpException 异常信息
	   * @return List 创建人的代理信息
	   */
	  public List listStubUserByCruser(String _cruser) throws TawwpException ;

	  /**
	   * 业务逻辑：查询用户的代理信息（CRUSER-DEPUTY-001）
	   * @param _stubuser String 代理人
	   * @throws TawwpException 异常信息
	   * @return List 需要代理的信息
	   */
	  public List listStubUserByStubuser(String _stubuser) throws TawwpException ;
	  
	  /**
	   * 业务逻辑：查询用户的代理信息（CRUSER-DEPUTY-001）
	   * @param _stubuser String 代理人
	   * @throws TawwpException 异常信息
	   * @return List 需要代理的信息
	   */
	  public List listStubUserByStubuser(String _stubuser,String stubtime,String checkuser,String state) throws TawwpException ;

	  /**
	   * 业务逻辑：显示代理信息编辑页面（EDIT-DEPUTY-001）
	   * @param _stubuserId String 代理信息编号
	   * @throws TawwpException 异常信息
	   * @return TawwpStubUserVO 代理信息VO对象
	   */
	  public TawwpStubUserVO editView(String _stubuserId) throws TawwpException ;
	  
}
