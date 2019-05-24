/**
 * 删除菜单方案异常。当该菜单方案已经被分配给用户或角色时，若执行删除操作即发生
 */
package com.boco.eoms.commons.system.priv.exception;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class DelMenuDenyException extends MenuBaseException {

	public DelMenuDenyException() {
		this("Fail to execute delete menu operation.");
	}

	public DelMenuDenyException(String errorMessage) {
		super(errorMessage);
	}

}
