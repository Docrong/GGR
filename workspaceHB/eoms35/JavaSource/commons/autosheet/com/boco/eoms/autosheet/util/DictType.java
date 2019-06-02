package com.boco.eoms.autosheet.util;

import java.util.*;
import com.boco.eoms.db.util.*;
import com.boco.eoms.common.util.*;
/*****************************************************************
 将所有的typeid,typename from taw_codetype中取出来放入hashtable,
 并通过getCodeTypeInfo让其驻留在内存里，并给出取数据的方法。
 *****************************************************************/
public class DictType{

  private ArrayList typeids = null;
  private ArrayList typenames=null;
  private RecordSet rt = null;
  private StaticObject staticobj = null;

  private int current_index = -1;
  private int array_size = 0;

  private DictType(){
    try{
      staticobj = StaticObject.getInstance();
      current_index=-1;
      getCodeTypeInfo() ;
      array_size = typeids.size();
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  private static DictType instance=new DictType();
  public static DictType getInstance(){
    return instance;
  }
  public boolean reset(){
    this.current_index=-1;
    return true;
  }

  private void getCodeTypeInfo() throws Exception{
    if(staticobj.getObject("CodeTypeInfo") == null)
      setCodeTypeInfo();
    typeids = (ArrayList)(staticobj.getRecordFromObj("CodeTypeInfo", "typeids"));
    typenames=(ArrayList)(staticobj.getRecordFromObj("CodeTypeInfo","typenames"));

  }
  private void setCodeTypeInfo() throws Exception{
    if(typeids != null)
      typeids.clear();
    else
      typeids = new ArrayList();
    if(typenames!=null)
      typenames.clear();
    else
      typenames=new ArrayList();

    rt = new RecordSet() ;
    try{
      rt.execute("select distinct dict_type,dict_typelabel from taw_ws_dict_type where deleted<>1") ;
      while(rt.next()){
        typeids.add(StaticMethod.null2String(rt.getString(1)));
        typenames.add((rt.getString(2)));
      }
      rt.givebackConnection();
    }
    catch(Exception e) {
     // writeLog(e) ;
      throw e ;
    }

    staticobj.putRecordToObj("CodeTypeInfo", "typeids", typeids);
    staticobj.putRecordToObj("CodeTypeInfo","typenames",typenames);

  }

  public int getCodeTypeCounts()
  {
          return 	array_size;
  }

  public boolean next(){

    if((current_index+1) < array_size){
      current_index++;
      return true;
    }
    else{
      current_index = -1;
      return false;
    }
  }
  /**************************************
   根据参数定位第n条记录,参数为正指从前往后数,参数为负,表示从后往前数,
   @param index 将记录定位到记录的第index行
   @return boolean
   */
  public boolean absolute(int index){
    int line=index;
    if(line>0&&line>=this.getCodeTypeCounts()){
      line=this.getCodeTypeCounts();
    }
    if(line<0&&(-line)>this.getCodeTypeCounts()){
      line=1;
    }else if(line<0){
      line=this.getCodeTypeCounts()+line+1;
    }
    current_index=line-1;
    return true;
  }


  public String getTypeID(String CodeTypeName){
    current_index=typenames.indexOf(CodeTypeName);
    String id="";
    try{
      id=(String)typeids.get(current_index);
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      return id;
    }
  }

  public String getTypeID(){
    return (String)(typeids.get(current_index));
  }
  public String getTypeName(String id){
      current_index=typeids.indexOf(id);
      String name="";
      try{
        name=StaticMethod.null2String((String)typenames.get(current_index));
      }catch(Exception e){
        LogMan log=LogMan.getInstance();
        log.writeLog(e.toString());
      }
      return name;
  }
  public String getTypeName(){
    return StaticMethod.fromBaseEncoding((String)typenames.get(current_index));
  }

  public void removeOSCache(){
    staticobj.removeObject("CodeTypeInfo");

  }
}
