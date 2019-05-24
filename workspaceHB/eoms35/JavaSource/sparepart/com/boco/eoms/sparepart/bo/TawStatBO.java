package com.boco.eoms.sparepart.bo;

import com.boco.eoms.sparepart.dao.TawStatDAO;
import com.boco.eoms.sparepart.dao.TawStatDAO;
import java.util.*;
import com.boco.eoms.sparepart.model.TawStat;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.sparepart.model.TawStorage;
import com.boco.eoms.sparepart.util.StaticPartMethod;
public class TawStatBO {
  public TawStatBO() {
  }

  public List statall() throws Exception {
    int[] stateTypeArr = {11,12,13,14,15,16};
    TawStatDAO statdao = new TawStatDAO();
    List storageidlist = statdao.getStorageIdList();
    List statresult = new ArrayList() ;
    if(storageidlist.isEmpty()){
      throw new Exception("统计仓库为空!");
    }else{
      int[] stattemp= new int[6];
      for (int i = 0; i < storageidlist.size(); i++) {
        String storage = statdao.getStorage(Integer.parseInt(storageidlist.get(i).toString()));
        for (int j = 0; j < stateTypeArr.length; j++) {
          int temp=statdao.getStatNum(stateTypeArr[j],storageidlist.get(i).toString());
          stattemp[j] =temp;
        }
        TawStat st = new TawStat();
        st.setStorageid(StaticMethod.nullObject2int(storageidlist.get(i)));
        st.setStorage(storage);
        st.setUseablenum(stattemp[0]);
        st.setFaultnum(stattemp[1]);
        st.setOutnum(stattemp[2]);
        st.setLoadnum(stattemp[3]);
        st.setRejectnum(stattemp[4]);
        st.setEnlargnum(stattemp[5]);
        int temp=0;
        for (int k=0; k<4;k++){
          temp+=stattemp[k];
        }
        st.setSummation(temp);
        statresult.add(st);
      }
    }
    return statresult;
  }

  public List statstorage(List storageList) throws Exception {
    int[] stateTypeArr = {11,12,13,14,15,16};
    TawStatDAO statdao = new TawStatDAO();
    TawStorage tawStorage = null;
    List statresult = new ArrayList() ;
    if(storageList.isEmpty()){
    	System.out.print("ͳ�Ʋֿ�Ϊ��!");
      //throw new Exception("ͳ�Ʋֿ�Ϊ��!");
    }else{
      int[] stattemp= new int[6];
      for (int i = 0; i < storageList.size(); i++) {
        tawStorage = (TawStorage)storageList.get(i);
        String storageName = tawStorage.getStoragename();
        int storageId = tawStorage.getId();
        for (int j = 0; j < stateTypeArr.length; j++) {
          int temp=statdao.getStatNum(stateTypeArr[j],Integer.toString(storageId));
          stattemp[j] =temp;
        }
        TawStat st = new TawStat();
        st.setStorageid(storageId);
        st.setStorage(storageName);
        st.setUseablenum(stattemp[0]);
        st.setFaultnum(stattemp[1]);
        st.setOutnum(stattemp[2]);
        st.setLoadnum(stattemp[3]);
        st.setRejectnum(stattemp[4]);
        st.setEnlargnum(stattemp[5]);
        int temp=0;
        for (int k=0; k<4;k++){
          temp+=stattemp[k];
        }
        st.setSummation(temp);
        statresult.add(st);
      }
    }
    return statresult;
  }
  /**
   * ����ͳ�Ʒ���,��ָ���������.
   * @param storageList,partType
   * @return
   * @throws Exception
   */
  public List statstorage(List storageList,int partType) throws Exception {
	    String stateIN=StaticPartMethod.stateIN;
	    String stateOUT=StaticPartMethod.stateOUT;
	    String stateNO=StaticPartMethod.stateNO;//������
	    TawStatDAO statdao = new TawStatDAO();
	    TawStorage tawStorage = null;
	    List statresult = new ArrayList() ;
	    if(storageList.isEmpty()){
	    	System.out.print("ͳ�Ʋֿ�Ϊ��!");
	      //throw new Exception("ͳ�Ʋֿ�Ϊ��!");
	    }else{
	      for (int i = 0; i < storageList.size(); i++) {
	        tawStorage = (TawStorage)storageList.get(i);
	        String storageName = tawStorage.getStoragename();
	        int storageId = tawStorage.getId();

	        int in=statdao.getStatNum(stateIN,Integer.toString(storageId),partType);//�ڿ�״̬
	        int out=statdao.getStatNum(stateOUT,Integer.toString(storageId),partType);//�ڿ�״̬
	        int no=statdao.getStatNum(stateNO,Integer.toString(storageId),partType);//�ڿ�״̬
	        TawStat st = new TawStat();
            st.setInnum(in);
            st.setOutnum(out);
            st.setRejectnum(no);//������
            st.setAllnum(in+out+no);
            st.setStorage(storageName);
            st.setStorageid(storageId);
	        statresult.add(st);
	      }
	    }
	    return statresult;
	  }
}
