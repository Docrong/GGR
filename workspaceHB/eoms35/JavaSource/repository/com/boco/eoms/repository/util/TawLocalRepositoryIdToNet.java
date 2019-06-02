package com.boco.eoms.repository.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.decorator.TableDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.repository.mgr.TawLocalRepositoryMgr;
import com.boco.eoms.repository.model.TawLocalRepository;

/**
 * 根据本地网版本库id,获取网元名称
 * @author jinli  
 * 2009-11-17
 *
 */
public class TawLocalRepositoryIdToNet extends TableDecorator {
	
	public String getRepositoryId(){
		String name ="";
		Map map = (HashMap) getCurrentRowObject();
		if(map!=null){
			String repositoryId = (String) map.get("repositoryId");	
			TawLocalRepositoryMgr repositoryMgr = (TawLocalRepositoryMgr)ApplicationContextHolder.getInstance().getBean("tawLocalRepositoryMgr");
			TawLocalRepository tawLocalRepository = repositoryMgr.getTawLocalRepository(repositoryId);
			if(tawLocalRepository!=null){
				name = tawLocalRepository.getNet();
			}
		}
		return name;
	}
	
	public String getPatchs(){
		Map map = (HashMap) getCurrentRowObject();
		String name = "";
		if(map!=null){
			String sheetkey = (String) map.get("sheetkeys");
			String patch = (String) map.get("patchs");
//			System.out.println(sheetkey+","+patch);
			if(sheetkey!=null&&!"".equals(sheetkey)&&patch!=null&&!"".equals(patch)){
				String[] sheetkeys = sheetkey.split(",");
				String[] patchs = patch.split(",");
				for (int i = 0; i < patchs.length; i++) {
					System.out.println(patchs.length+","+sheetkeys.length);
					if(sheetkeys.length>i){
						name += "<a href='../sheet/softchange/softchange.do?method=showMainDetailPage&sheetKey="+sheetkeys[i]+"' >"+patchs[i]+"</a>";
						if((patchs.length-1)>i){
							name+=",";
						}
					}else{
						name=patch;
					}
				}
			}else{
				name=patch;
			}
		}
		return name;
	}
//	public Object decorate(Object columnValue, PageContext pageContext, MediaTypeEnum mediaTypeEnum) throws DecoratorException {
//		String name ="";
//		if(columnValue!=null){
//			String repositoryId = columnValue.toString();	
//			TawLocalRepositoryMgr repositoryMgr = (TawLocalRepositoryMgr)ApplicationContextHolder.getInstance().getBean("tawLocalRepositoryMgr");
//			TawLocalRepository tawLocalRepository = repositoryMgr.getTawLocalRepository(repositoryId);
//			if(tawLocalRepository!=null){
//				name = tawLocalRepository.getNet();
//			}
//		}
//		return name;
//	}
}