package com.boco.eoms.workbench.memo.util;
import com.boco.eoms.workbench.memo.vo.TawWorkbenchMemoVO;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemo;
/*
 * 
 * 
 * 
 * 
 */
public class Copy {
	public static void copy(TawWorkbenchMemo tawWorkbenchMemo,TawWorkbenchMemoVO tawWorkbenchMemoVO){
		tawWorkbenchMemoVO.setContent(tawWorkbenchMemo.getContent());
		tawWorkbenchMemoVO.setCreattime(tawWorkbenchMemo.getCreattime());
		tawWorkbenchMemoVO.setLevel(tawWorkbenchMemo.getLevel());
		tawWorkbenchMemoVO.setId(tawWorkbenchMemo.getId());
		tawWorkbenchMemoVO.setReciever(tawWorkbenchMemo.getReciever());
		tawWorkbenchMemoVO.setSendflag(tawWorkbenchMemo.getSendflag());
		tawWorkbenchMemoVO.setSendmanner(tawWorkbenchMemo.getSendmanner());
		tawWorkbenchMemoVO.setSendtime(tawWorkbenchMemo.getSendtime());
		tawWorkbenchMemoVO.setTitle(tawWorkbenchMemo.getTitle());
		tawWorkbenchMemoVO.setUserid(tawWorkbenchMemo.getUserid());
		tawWorkbenchMemoVO.setLevelName(tawWorkbenchMemo.getLevel());
		tawWorkbenchMemoVO.setSendflagName(tawWorkbenchMemo.getSendflag());
		tawWorkbenchMemoVO.setSendMannerName(tawWorkbenchMemo.getSendmanner());
	}
}
