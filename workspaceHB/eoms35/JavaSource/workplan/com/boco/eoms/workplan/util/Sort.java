package com.boco.eoms.workplan.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.boco.eoms.workplan.vo.TawwpExecuteContentVO;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;

public class Sort implements Comparator { 
	public Sort() { 

	} 

	public int compare(Object o1, Object o2){ 
		TawwpExecuteContentVO b1=(TawwpExecuteContentVO)o1; 
		TawwpExecuteContentVO b2=(TawwpExecuteContentVO)o2; 
		return b1.getId().compareTo(b2.getId()); 
		}

	

	public static void main(String[] args) { 
	ArrayList array = new ArrayList();
	ArrayList arrays = new ArrayList();
	for(int i=0; i<5;i++){
		TawwpMonthPlanVO tawwpMonthPlanVO =new TawwpMonthPlanVO();
		tawwpMonthPlanVO.setId("b"+i);
		array.add(tawwpMonthPlanVO);
	}
	
//	array.add("000001"); 
//	array.add("000003"); 
//	array.add("000005"); 
//	array.add("000004"); 
//	array.add("000002"); 
	TawwpMonthPlanVO tawwpMonthPlanVO =new TawwpMonthPlanVO();
	TawwpMonthPlanVO tawwpMonthPlanVO1 =new TawwpMonthPlanVO();
	TawwpMonthPlanVO tawwpMonthPlanVO2 =new TawwpMonthPlanVO();
	TawwpMonthPlanVO tawwpMonthPlanVO3 =new TawwpMonthPlanVO();
	tawwpMonthPlanVO.setId("b0");
	tawwpMonthPlanVO1.setId("b5");
	tawwpMonthPlanVO2.setId("b9");
	tawwpMonthPlanVO3.setId("b4");
	array.add(tawwpMonthPlanVO);
	array.add(tawwpMonthPlanVO1);
	array.add(tawwpMonthPlanVO2);
	array.add(tawwpMonthPlanVO3);
//	for(int i=0;i<array.size();i++){
//		TawwpMonthPlanVO tawwpMonthPlanVOs =new TawwpMonthPlanVO();
//		tawwpMonthPlanVOs=(TawwpMonthPlanVO) array.get(i);
//		for(int j=array.size();j<array.size();j--){
//			TawwpMonthPlanVO tawwpMonthPlanVOss =new TawwpMonthPlanVO();
//			if(tawwpMonthPlanVOs.getId().compareTo(tawwpMonthPlanVOss.getId())>0){
//				arrays.add(tawwpMonthPlanVOs);	
//			}else{
//				arrays.add(tawwpMonthPlanVOss);	
//			}
//		}
//	}
	
	Collections.sort(array, new Sort());
	
	for(int i=0;i<array.size();i++){
		TawwpMonthPlanVO tawwpMonthPlanVOs =new TawwpMonthPlanVO();
		tawwpMonthPlanVOs=(TawwpMonthPlanVO) array.get(i);
		System.out.println(tawwpMonthPlanVOs.getId()); 	
	}
	}
	}
//	Collections.sort(array, new Sort(Sort.DOWM));}
//	for(int i=0;i<array.){
//		
//	}

//	for (String str : array) { 
//	System.out.println(str); 
//	} 
//	}

	
