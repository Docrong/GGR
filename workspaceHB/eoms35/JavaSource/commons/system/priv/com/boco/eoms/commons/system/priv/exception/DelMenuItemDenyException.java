/**
 * 删除菜单项异常。当该菜单项所属方案已经被分配给用户或角色时，若执行删除操作即发生
 */
package com.boco.eoms.commons.system.priv.exception;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class DelMenuItemDenyException extends MenuBaseException {

	public DelMenuItemDenyException() {
		this("Fail to execute delete menu item operation.");
	}

	public DelMenuItemDenyException(String errorMessage) {
		super(errorMessage);
	}

}
