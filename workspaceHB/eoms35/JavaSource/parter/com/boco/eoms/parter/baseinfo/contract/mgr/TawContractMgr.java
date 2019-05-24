package com.boco.eoms.parter.baseinfo.contract.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.parter.baseinfo.contract.model.*;

/**
 * <p>
 * Title:代维合同
 * </p>
 * <p>
 * Description:代维合同
 * </p>
 * <p>
 * Wed Apr 01 08:58:31 CST 2009
 * </p>
 * 
 * @author fengshaohong
 * @version 3.5
 * 
 */
 public interface TawContractMgr {
 
	/**
	 *
	 * 取代维合同 列表
	 * @return 返回代维合同列表
	 */
	public List getTawContracts();
    
	/**
	 * 根据主键查询代维合同
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public TawContract getTawContract(final String id);
    
	/**
	 * 保存代维合同
	 * @param tawContract 代维合同
	 */
	public void saveTawContract(TawContract tawContract);
    
	/**
	 * 根据主键删除代维合同
	 * @param id 主键
	 */
	public void removeTawContract(final String id);
    
	/**
	 * 根据条件分页查询代维合同
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回代维合同的分页列表
	 */
	public Map getTawContracts(final Integer curPage, final Integer pageSize,
			final String whereStr);
	public Boolean isunique(final String contractName);
	
	
	
	
	/**
	 * 代维合同付款管理
	 */
	 
	/**
	  * 查询付款信息用于分页显示
	  * @param curPage
	  * @param pageSize
	  * @param whereStr
	  * @return
	  */
	public Map getTawContractPays(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
    /**
	 * 根据主键查询tawcontractpay
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public TawContractPay getTawContractPay(final String id);
    
	 /**
	 * 根据合同id主键查询付款情况列表
	 * @param contractid 主键
	 * @return 返回某contractid的付款情况列表
	 */
	public List getTawContractPayByContractid(final String contractid);
	
    /**
    * 保存tawcontractpay
    * @param tawContractPay tawcontractpay
    */
    public void saveTawContractPay(TawContractPay tawContractPay);
			
}