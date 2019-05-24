/*
 * Created on 2007-8-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.dept.test;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawSystemDeptUpdateDB extends ConsoleTestCase {

	/*
	public void testUpdateDB(){
		
		TawSystemDept dept=new TawSystemDept();
		TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
		try {
			List deptlist = (ArrayList) deptbo.getTawSystemDepts(dept);
			
			int i=0;
			for(Iterator rowIt = deptlist.iterator(); rowIt.hasNext();){
				i=i+1;
				TawSystemDept depts=new TawSystemDept();
				
				depts = (TawSystemDept)rowIt.next();
				String id=String.valueOf(depts.getId());
				
				 List deptinfolist = TawSystemDeptBo.getInstance().getNextLevecDepts(depts.getDeptId(),"0");
				 if( deptinfolist == null ){
				 	for( int z=0;z<10;z++)
				 System.out.println("********************:>"+deptinfolist.size()+"<:*******************");	
				 }
				if( id.length()==1){
					
					depts.setOrdercode(new Integer(0));
				    if( deptinfolist.size() > 0 ){
				    	depts.setLeaf("0");
				    }else{
				    	depts.setLeaf("1");
				    }
				}else if( id.length()==3){
					depts.setOrdercode(new Integer(1));
				    if( deptinfolist.size() > 0 ){
				    	depts.setLeaf("0");
				    }else{
				    	depts.setLeaf("1");
				    }
				}else if( id.length()==5){
					depts.setOrdercode(new Integer(2));
				    if( deptinfolist.size() > 0 ){
				    	depts.setLeaf("0");
				    }else{
				    	depts.setLeaf("1");
				    }
				}else if( id.length()==7){
					depts.setOrdercode(new Integer(3));
				    if( deptinfolist.size() > 0 ){
				    	depts.setLeaf("0");
				    }else{
				    	depts.setLeaf("1");
				    }
				}else if( id.length()==9){
					depts.setOrdercode(new Integer(1));
				    if( deptinfolist.size() > 0 ){
				    	depts.setLeaf("0");
				    }else{
				    	depts.setLeaf("1");
				    }
				}else{
					System.out.println("********************"+depts.getDeptId()+"*******************");
				}
				depts.setOperuserid("admin");
				deptbo.saveTawSystemDept(depts);
				
			}
			System.out.println("@@@@@@@@@@@@@@@@@@@"+i+"@@@@@@@@@@@@@@@@@@@@@@@@");
  	deptlist = (ArrayList) TawSystemDeptBo.getNextLevecDepts("1", "0");
		} catch (TawSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		for(Iterator rowIt = deptlist.iterator(); rowIt.hasNext();){
			TawSystemDept depts=new TawSystemDept();
			
			depts = (TawSystemDept)rowIt.next();
			System.out.println(depts.getDeptId());
			TawSystemDept deptss=new TawSystemDept();
			deptss.setOrdercode(new Integer(1));
		}
	}
*/
	public void testId2Name(){
		
		ID2NameService  id2service = (ID2NameService)ApplicationContextHolder
		.getInstance().getBean("id2nameService");
		String deptname = id2service.id2Name("1", "tawSystemDeptDao");
		System.out.println(deptname);
		System.out.println("&&&&&&&&&&&&&&&&&&&");
		
	}
}
