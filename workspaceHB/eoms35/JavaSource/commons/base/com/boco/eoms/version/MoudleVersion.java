package com.boco.eoms.version;

import java.io.Serializable;
import java.util.Date;

/**
 * 模块的版本
 * 
 * @author leo
 * 
 * create table version ( id varchar(32) primary key, name varchar(100),
 * principal varchar(100), patch varchar(100), patch_desc varchar(255), on_date
 * varchar(32), patch_principal varchar(32), svn varchar(255), problem
 * varchar(255) )
 * 
 */
public class MoudleVersion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1349423721387315836L;
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 模块名称
	 */
	private String name;
	/**
	 * 模块负责人
	 */
	private String principal;

	/**
	 * 模块补丁号,6245patch_作业计划（SVN编号patch|bug_模块）;“模块补丁号”中的“SVN编号”为研发人员解决这个问题最后一次提交代码入库时SVN产生的编号。
	 */
	private String patch;

	/**
	 * 补丁描述
	 */
	private String patchDesc;

	/**
	 * 补丁时间2009.4.9(补丁提交的时间)
	 */
	private String onDate;

	/**
	 * 补丁负责人
	 */
	private String patchPrincipal;

	/**
	 * https://10.0.2.1/eoms/dev/branches/platform/0903/0903_bug (处理问题的SVN路径)
	 */
	private String svn;
	/**
	 * 问题版本号
	 */
	private String problem;

	/**
	 * 第一次补丁时间
	 */
	private Date patchFirstDate;

	/**
	 * 主版本 or 模块版本
	 */
	private String type;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the principal
	 */
	public String getPrincipal() {
		return principal;
	}

	/**
	 * @param principal
	 *            the principal to set
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	/**
	 * @return the patch
	 */
	public String getPatch() {
		return patch;
	}

	/**
	 * @param patch
	 *            the patch to set
	 */
	public void setPatch(String patch) {
		this.patch = patch;
	}

	/**
	 * @return the patchDesc
	 */
	public String getPatchDesc() {
		return patchDesc;
	}

	/**
	 * @param patchDesc
	 *            the patchDesc to set
	 */
	public void setPatchDesc(String patchDesc) {
		this.patchDesc = patchDesc;
	}

	/**
	 * @return the onDate
	 */
	public String getOnDate() {
		return onDate;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param onDate
	 *            the onDate to set
	 */
	public void setOnDate(String onDate) {
		this.onDate = onDate;
	}

	/**
	 * @return the patchPrincipal
	 */
	public String getPatchPrincipal() {
		return patchPrincipal;
	}

	/**
	 * @param patchPrincipal
	 *            the patchPrincipal to set
	 */
	public void setPatchPrincipal(String patchPrincipal) {
		this.patchPrincipal = patchPrincipal;
	}

	/**
	 * @return the problem
	 */
	public String getProblem() {
		return problem;
	}

	/**
	 * @param problem
	 *            the problem to set
	 */
	public void setProblem(String problem) {
		this.problem = problem;
	}

	/**
	 * @return the svn
	 */
	public String getSvn() {
		return svn;
	}

	/**
	 * @param svn
	 *            the svn to set
	 */
	public void setSvn(String svn) {
		this.svn = svn;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the patchFirstDate
	 */
	public Date getPatchFirstDate() {
		return patchFirstDate;
	}

	/**
	 * @param patchFirstDate
	 *            the patchFirstDate to set
	 */
	public void setPatchFirstDate(Date patchFirstDate) {
		this.patchFirstDate = patchFirstDate;
	}

}
