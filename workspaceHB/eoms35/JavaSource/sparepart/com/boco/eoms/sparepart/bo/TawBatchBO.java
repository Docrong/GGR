package com.boco.eoms.sparepart.bo;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.util.ConnectionPool;
import jxl.Workbook;
import jxl.Sheet;
import jxl.Cell;
import jxl.read.biff.BiffException;

import java.io.*;
import java.util.*;
import java.util.Date;
import com.boco.eoms.sparepart.model.*;
import com.boco.eoms.sparepart.dao.*;
import com.boco.eoms.common.fileupload.SmartUpload;

import java.sql.*;
import java.text.*;

/**
 * <p>Title: ��Ʒ����</p>
 * <p>Description: EOMS��ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.7
 */
 
public class TawBatchBO extends BO{

    String filePathName="";
    String fileName="";
    String storageid="";

    public TawBatchBO(String pathname,String storageid,String name){
        this.fileName=name;
        this.filePathName=pathname;
        this.storageid=storageid;
    }

    public TawBatchBO(ConnectionPool ds){
        super(ds);
    }

    public TawBatchBO(ConnectionPool ds,String str){
        super(ds,str);
    }

    public Workbook getWorkbook(){
        Workbook book=null;
        try{
            //�ļ�·��
            book=Workbook.getWorkbook(new File(filePathName));
        }
        catch(BiffException ex){
            ex.printStackTrace();
        }

        catch(IOException ex){
            ex.printStackTrace();
        }
        return book;
    }

    public Sheet getSheet(int i){
        Sheet sheet=null;
        Workbook book=getWorkbook();
        sheet=book.getSheet(i);
        return sheet;
    }

    //��EXCEL�ļ��ж�ȡ�����Сרҵ����Ԫ���ͣ��������ͣ������ͺţ�������ƣ��������кţ����쳧�̣�������,ʱ��ȱ�������
    public List getExcPart(){
        ArrayList list=new ArrayList();
        Sheet sheet=getSheet(0);
        int r=sheet.getRows();
        int c=sheet.getColumns();
        for(int i=1;i<r;i++){
            TawPart tawPart=new TawPart();
            Cell[] cell=sheet.getRow(i);

            tawPart.setNettype(cell[0].getContents().trim());//��רҵ
            tawPart.setSubdept(cell[1].getContents().trim());//Сרҵ            
            tawPart.setNecode(cell[2].getContents().trim());//��Ԫ����
            tawPart.setObjecttype(cell[3].getContents().trim());//��������---��Ϊ���
            //tawPart.setObjectname(cell[4].getContents().trim());//�������---��ʱȡ���.
            tawPart.setObjtype(cell[4].getContents().trim());//�豸�ͺ�---��ǰ
            tawPart.setSupplier(cell[5].getContents().trim());//�����̣�
            tawPart.setFixe(cell[6].getContents().trim());//�豸����--��Ϊ����� ����ǰ�t�
            tawPart.setVersion(cell[7].getContents().trim());//�����汾�ţ�
            tawPart.setManagecode(cell[8].getContents().trim());//������Ϣ���룺(managecode) add M
            tawPart.setSerialno(cell[9].getContents().trim());//�������к�  
            //tawPart.setOperator(cell[9].getContents().trim());//������--��ʱȡ���
            tawPart.setState(cell[10].getContents().trim());//����״̬
            tawPart.setProform(cell[11].getContents().trim());//��������
            tawPart.setWarrantyName(cell[12].getContents().trim());//�Ƿ��� (warrantyName) add M
            tawPart.setStopproductName(cell[13].getContents().trim());//�Ƿ�ͣ�� (stopproductName) add M
            tawPart.setContract(cell[14].getContents().trim());//�����/��ͬ (contract) add
            tawPart.setUnits(cell[15].getContents().trim());//��λ (units) add--�������ֶ�
            tawPart.setMoney(cell[16].getContents().trim());//������� (money) add
            tawPart.setPosition(cell[17].getContents().trim());//�������λ��
            tawPart.setEtime(cell[18].getContents().trim());//���ʱ��     
            tawPart.setStorage(cell[19].getContents().trim());//����ֿ�
            tawPart.setDescribe(cell[20].getContents().trim());//��ע--> �޸�Ϊ "��Ҫ��������"
//            tawPart.setHwversion(cell[10].getContents().trim());
            //tawPart.setProposer(cell[20].getContents().trim());//���������----��ʱȡ��
            tawPart.setCompany(cell[21].getContents().trim());//����˾    
            tawPart.setRepair_endtime(cell[22].getContents().trim());//���޵���ʱ��
            tawPart.setRepairtime(cell[23].getContents().trim());//����ʱ��
            tawPart.setNote(cell[24].getContents().trim());//��ע
            tawPart.setPartclass(cell[25].getContents().trim());//�������
            if(tawPart.getPartclass().trim().equals("��ά����")){
            	tawPart.setParttype(0);
            }else if(tawPart.getPartclass().trim().equals("��Ʒ����")){
            	tawPart.setParttype(1);
            }else if(tawPart.getPartclass().trim().equals("�����Ǳ�")){
            	tawPart.setParttype(2);
            }
            list.add(tawPart);
        }
        return list;
    }

    //���EXCEL�ļ��е�����,��д�Ƿ�����,�Ƿ������д����
    //检查EXCEL文件中的内容,填写是否完整,是否出现填写错误
    public List checkData(){
        List errorList = new ArrayList();
        List PartList = getExcPart();
        TawPartDAO dao = new TawPartDAO();
        String ErrorInfo = "";
        HashMap supplier=null;
        HashMap state=null;
        HashMap proform=null;
        HashMap storage=null;
        HashMap type=null;
        HashMap PartIds = null;
        HashMap PartIdm = null;
        HashMap company=null;
        HashMap fixe=null;
        int n=0;
        try{
          supplier=dao.getCname(6);
          state=dao.getCname(10);   //获取备件库中状态 . 
          proform=dao.getCname(440); //获取备件性能状态 正常 不正常 dww 070308 变更为 完好 修复可用 已报废
          storage=dao.getStorage();
          company=dao.getCname(450);   //获取物资所属公司 . 
          fixe=dao.getCname(460);   //获取设备厂商 . 
          type=dao.getType();
          PartIds = dao.getPartId();//获取序列号的map
          PartIdm = dao.getPartId_formanagecode();//获取资产条形码的map
        }
        catch(Exception e){
          e.printStackTrace();
        }
        for(int i=0;i<PartList.size();i++){
          TawPart tp = (TawPart) PartList.get(i);

          //检查“所属大专业”
          int rows = i+2;
          
              TawTreeDao dao_temp=new TawTreeDao();
              String sumId=dao_temp.getTreeThreeId(StaticMethod.dbNStrRev(tp.getNettype()),StaticMethod.dbNStrRev(tp.getSubdept()),
                      StaticMethod.dbNStrRev(tp.getNecode()),
                      StaticMethod.dbNStrRev(tp.getObjecttype()));
              if(sumId.equals("")){
            	  ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“大小专业关联关系”没有正确";
            	  errorList.add(ErrorInfo);              
              }          
          if (tp.getNettype().trim() == null || tp.getNettype().trim() == ""){
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“所属大专业”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
            if(type.get(tp.getNettype().trim()) == null){
              ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“所属大专业”填写错误，数据库中没有该“所属大专业”，请检查";
              errorList.add(ErrorInfo);
            }
          }
          //检查“所属小专业”
          if (tp.getSubdept().trim() == null || tp.getSubdept().trim() == ""){
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“所属小专业”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
            if(type.get(tp.getSubdept().trim()) == null){
              ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“所属小专业”填写错误，数据库中没有该“所属专业”，请检查";
              errorList.add(ErrorInfo);
            }
          } 
          
          //检查“网元类型”
          if (tp.getNecode().trim() == null || tp.getNecode().trim() == ""){
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“设备类型”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
            if(type.get(tp.getNecode().trim()) == null){
              ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“网元类型”填写错误，数据库中没有该“设备类型”，请检查";
              errorList.add(ErrorInfo);
            }
          }
          //检查“备件类型”---修改为名称
          if (tp.getObjecttype().trim() == null || tp.getObjecttype().trim() == ""){
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“名称”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
            if(type.get(tp.getObjecttype().trim()) == null){
              ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“名称”填写错误，数据库中没有该“名称”，请检查";
              errorList.add(ErrorInfo);
            }
          }
          //检查“设备型号”
          if(tp.getObjtype().trim()==null||tp.getObjtype().trim()==""){
        	  ErrorInfo="文件“"+fileName+"”的第"+rows+"行的“规格型号”没有填写";
        	  errorList.add(ErrorInfo);
          }
          //检查“供货厂商”
          if (tp.getSupplier().trim() == null || tp.getSupplier().trim() == ""){
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“供货厂商”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
            if(supplier.get(tp.getSupplier().trim()) == null){
              ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“供货厂商”填写错误，数据库中没有该“供货厂商”，请检查";
              errorList.add(ErrorInfo);
            }
          }
          //检查“设备厂商”--修改为生产厂商
          if(tp.getFixe().trim()==null||tp.getFixe().trim()==""){
        	  ErrorInfo="文件“"+fileName+"”的第"+rows+"行的“生产厂商”没有填写";
        	  errorList.add(ErrorInfo);
          }else{
              if(fixe.get(tp.getFixe().trim()) == null){
                  ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“生产厂商”填写错误，数据库中没有该“设备厂商”，请检查";
                  errorList.add(ErrorInfo);
                }
              }
//          //检查“备件名称”--暂时取消
//          if (tp.getObjectname().trim() == null || tp.getObjectname().trim() == ""){
//            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“备件名称”没有填写";
//            errorList.add(ErrorInfo);
//          }
          //检查“备件信息编码”资产条形码
          if (tp.getManagecode().trim() == null || tp.getManagecode().trim() == ""){
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“资产条形码”没有填写";
            errorList.add(ErrorInfo);
          } else{
              for(int j=i+1;j<PartList.size();j++){
                  TawPart tp1 = (TawPart) PartList.get(j);
                  int ErrorRow = j;
                  if (tp.getManagecode().trim().equals(tp1.getManagecode().trim())){
                    ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“资产条形码”和第" + ErrorRow + "行的“资产条形码”重复，请修改";
                    errorList.add(ErrorInfo);
                  }
                }
                if (PartIdm.get(tp.getManagecode().trim())!=null){
                 ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“资产条形码”与数据库中的数据相同，请修改";
                 errorList.add(ErrorInfo);
               }
             }
          //检查“序列号”
          if (tp.getSerialno().trim() == null || tp.getSerialno().trim() == ""){
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“序列号”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
            for(int j=i+1;j<PartList.size();j++){
              TawPart tp1 = (TawPart) PartList.get(j);
              int ErrorRow = j;
              if (tp.getSerialno().trim().equals(tp1.getSerialno().trim())){
                ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“序列号”和第" + ErrorRow + "行的“序列号”重复，请修改";
                errorList.add(ErrorInfo);
              }
            }
            if (PartIds.get(tp.getSerialno().trim())!=null){
             ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“序列号”与数据库中的数据相同，请修改";
             errorList.add(ErrorInfo);
           }
         }
          //检查“备件版本号”
          if (tp.getVersion().trim() == null || tp.getVersion().trim() == ""){
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“备件版本号”没有填写";
            errorList.add(ErrorInfo);
          }
          //检查“状态”
          if (tp.getState().trim() == null || tp.getState().trim() == ""){
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“状态”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
            if(state.get(tp.getState().trim()) == null){
              ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“状态”填写错误，数据库中没有该“状态”，请检查";
              errorList.add(ErrorInfo);
            }
          }
          //检查“备件性能”
          if (tp.getProform().trim() == null || tp.getProform().trim() == ""){
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“备件性能”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
            if(proform.get(tp.getProform().trim()) == null){
              ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“备件性能”填写错误，数据库中没有该“备件性能”，请检查";
              errorList.add(ErrorInfo);
            }
          }
//        检查“是否保修”
//        检查“是否停产”
//          //检查“保修期”
//          if (tp.getHwversion().trim() == null || tp.getHwversion().trim() == ""){
//            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“保修期”没有填写";
//            errorList.add(ErrorInfo);
//          }
          //检查“入库时间”
          if (tp.getEtime().trim() == null || tp.getEtime().trim() == ""){
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“入库时间”没有填写";
            errorList.add(ErrorInfo);
          }
//          //检查“经手人”--暂时取消
//          if (tp.getOperator().trim() == null || tp.getOperator().trim() == ""){
//            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“经手人”没有填写";
//            errorList.add(ErrorInfo);
//          }
          //检查“具体存放位置”
          if (tp.getPosition().trim() == null || tp.getPosition().trim() == ""){
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“具体存放位置”没有填写";
            errorList.add(ErrorInfo);
          }
//          //检查“入库申请人”
//          if(tp.getProposer().trim()==null||tp.getProposer().trim()==""){
//        	  ErrorInfo="文件“"+fileName+"”的第"+rows+"行的“入库申请人”没有填写";
//        	  errorList.add(ErrorInfo);
//          }
          //检查仓库
          if (tp.getStorage().trim() == null || tp.getStorage().trim() == ""){
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“所属仓库”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
            if(storage.get(tp.getStorage().trim()) == null){
              ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“所属仓库”填写错误，数据库中没有该“仓库”，请检查";
              errorList.add(ErrorInfo);
            }
          }
          //检查“物资所属公司”
          if(tp.getCompany().trim()==null||tp.getCompany().trim()==""){
        	  ErrorInfo="文件“"+fileName+"”的第"+rows+"行的“物资所属公司”没有填写";
        	  errorList.add(ErrorInfo);
          }else{
              if(company.get(tp.getCompany().trim()) == null){
                  ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“物资所属公司”填写错误，数据库中没有该“物资所属公司”，请检查";
                  errorList.add(ErrorInfo);
                }
              }
          //检查“物资类别”
          if(tp.getPartclass().trim()==null||tp.getPartclass().trim()==""){
        	  ErrorInfo="文件“"+fileName+"”的第"+rows+"行的“物资类别”没有填写";
        	  errorList.add(ErrorInfo);
          }else{
              if(tp.getPartclass().trim().equals("运维物资")){
            	    tp.setPartclass("0");
                }else{
                    if(tp.getPartclass().trim().equals("备品备件")){
                  	    tp.setPartclass("1");
                      }else{
                          if(tp.getPartclass().trim().equals("仪器仪表")){
                        	    tp.setPartclass("2");
                            }else{
                                ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“物资类别”填写错误，请填写正确的物资类别,如:'运维物资','备品备件','仪器仪表'，请检查";
                                errorList.add(ErrorInfo);
                            }
                      }
                }
              }          
//          //检查“保修到期时间”
//          if(tp.getRepair_endtime().trim()==null||tp.getRepair_endtime().trim()==""){
//        	  ErrorInfo="文件“"+fileName+"”的第"+rows+"行的“保修到期时间”没有填写";
//        	  errorList.add(ErrorInfo);
//          }
//          //检查“保修时间”
//          if(tp.getRepairtime().trim()==null||tp.getRepairtime().trim()==""){
//        	  ErrorInfo="文件“"+fileName+"”的第"+rows+"行的“保修时间”没有填写";
//        	  errorList.add(ErrorInfo);
//          }
        }
        return errorList;
    }

    //���֮�������,�ֳ�}����,��ݿ����Ѿ����ڵĻ�����Serialno��Ϊ��־��,��"UPDATE",�粻����,��"INSERT"
    public boolean importData(){
        boolean flag = true;
        TawPartDAO dao = new TawPartDAO();
        List PartList = getExcPart();
        List updateList = new ArrayList();
        List insertList = new ArrayList();
        TawPart tawPart = new TawPart();

        Date dt = new Date();
        SimpleDateFormat smpDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sTime = smpDateFormat.format(dt);

        for(int i=0;i<PartList.size();i++){
          TawPart tp = (TawPart) PartList.get(i);
          insertList.add(tp);
        }
        try{
          int bb[] = dao.insert(insertList);
        }
        catch(SQLException ex){
          ex.printStackTrace();
          flag = false;
        }
        return flag;
    }
/**
    public String insertPart(){
        String msg="";
        List list=getExcPart();
        TawPartDAO dao=new TawPartDAO();
        try{
            int[] aa=dao.insert(list);
            msg="ok";
        }
        catch(SQLException ex){
            msg="no";
            ex.printStackTrace();
        }
        return msg;
    }
**/
}
