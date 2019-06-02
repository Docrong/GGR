package com.boco.eoms.testcard.bo;

import jxl.Workbook;
import jxl.Sheet;
import jxl.Cell;
import jxl.read.biff.BiffException;
import java.sql.*;
import java.text.*;
import java.io.*;
import java.util.*;
import java.util.Date;
import com.boco.eoms.testcard.model.TawTestcard;
import com.boco.eoms.testcard.dao.TawEventDicDAO;
import com.boco.eoms.testcard.dao.TawTestcardDAO; 

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.struts.upload.*; 

public class TawTestcardLoadBO {

  String filePathName = "";
  String fileName = "";
  String storageid = "";

  public TawTestcardLoadBO(){

  }

  public TawTestcardLoadBO(String pathname) {
    this.filePathName = pathname;
  }


  public TawTestcardLoadBO(String pathname, String name) {
    this.fileName = name;
    this.filePathName = pathname;
  }

  public Workbook getWorkbook() {
    Workbook book = null;
    try {
      //�ļ�·��
      book = Workbook.getWorkbook(new File(filePathName));
    }
    catch (BiffException ex) {
      ex.printStackTrace();
    }

    catch (IOException ex) {
      ex.printStackTrace();
    }
    return book;
  }

  public Sheet getSheet(int i) {
    Sheet sheet = null;
    Workbook book = getWorkbook();
    sheet = book.getSheet(i);
    return sheet;
  }

//从来访台帐中读取数据
  public List getInExcPart() {
    String temp = "";
    ArrayList list = new ArrayList();
    Sheet sheet = getSheet(0);
    int r = sheet.getRows();
    int c = sheet.getColumns();
    for (int i = 2; i < r; i++) {
      TawTestcard tawPart = new TawTestcard();
      Cell[] cell = sheet.getRow(i);
           if (cell.length == 0)
        continue;
      int j=1;
//      tawPart.setVolumenum(this.getCellContent(cell,j++)); //册号
//      tawPart.setPagenum(this.getCellContent(cell,j++)); //页号
      tawPart.setFromCountry(this.getCellContent(cell,j++)); //国家
      tawPart.setFromOpe(this.getCellContent(cell,j++)); //运营商
      tawPart.setFromCrit(this.getCellContent(cell,j++)); //省份
      tawPart.setFromCity(this.getCellContent(cell,j++)); //城市
      tawPart.setIccid(this.getCellContent(cell,j++)); //卡号(ICCID)
      tawPart.setOldNo(this.getCellContent(cell, j++));//单卡编号(OLDNO)
      tawPart.setMsisdn(this.getCellContent(cell,j++)); //MSISDN
      tawPart.setImsi(this.getCellContent(cell,j++)); //IMSI
      temp = this.getCellContent(cell,j++); //卡类型
//      System.out.println(temp);
      if (temp.equals("国际出访卡")) {
        tawPart.setCardType("0");
      }
      else if (temp.equals("国际来访卡")) {
        tawPart.setCardType("1");
      }
      else if (temp.equals("省际来访卡")) {
        tawPart.setCardType("2");
      }
      else if (temp.equals("省际出访卡")) {
        tawPart.setCardType("3");
      }
      else if (temp.equals("本地测试卡")) {
        tawPart.setCardType("4");
      }
      else if (temp.equals("省内来访卡")) {
        tawPart.setCardType("5");
      }
      else if (temp.equals("省内出访卡")) {
        tawPart.setCardType("6");
      }
      temp = this.getCellContent(cell,j++); //可用状态
      if (temp.equals("正常")) {
        tawPart.setState("0");
      }
      
      else if (temp.equals("停机")) {
        tawPart.setState("1");
      }
      else if (temp.equals("遗失")) {
        tawPart.setState("2");
      }
      else if (temp.equals("借出")) {
        tawPart.setState("3");
      }
      else if (temp.equals("使用")) {
        tawPart.setState("4");
      }
      else if (temp.equals("报废")) {
        tawPart.setState("5");
      }
      tawPart.setCardpackage(this.getCellContent(cell,j++));//套餐
//      tawPart.setPosition(this.getCellContent(cell,j++)); //使用地点      
      tawPart.setExes(this.getCellContent(cell,j++)); //费用情况     
      tawPart.setOffer(this.getCellContent(cell, j++));//归属HLR厂商
      tawPart.setMsgcenterno(this.getCellContent(cell, j++));      
      tawPart.setAdder(this.getCellContent(cell,j++)); //入库人
      tawPart.setPosition(this.getCellContent(cell,j++)); //存放位置
      tawPart.setEditState(this.getCellContent(cell,j++));
      list.add(tawPart);
    }
    return list;
  }


  //�ӳ��̨���ж�ȡ���
  public List getOutExcPart() {
//  TawEventDicDAO eventdao = new TawEventDicDAO();
    String temp = "";
    ArrayList list = new ArrayList();
    Sheet sheet = getSheet(0);
    int r = sheet.getRows();
    int c = sheet.getColumns();
    for (int i = 2; i < r; i++) {
      TawTestcard tawPart = new TawTestcard();

      Cell[] cell = sheet.getRow(i);
      if (cell.length == 0)
        continue;
      int j=1;
      tawPart.setFromCountry(this.getCellContent(cell,j++)); //2���
      tawPart.setFromOpe(this.getCellContent(cell,j++)); //3��Ӫ��
      tawPart.setFromCrit(this.getCellContent(cell,j++)); //4ʡ��
      tawPart.setFromCity(this.getCellContent(cell,j++)); //5�������
      tawPart.setIccid(this.getCellContent(cell,j++)); //6����(ICCID)
      tawPart.setOldNo(this.getCellContent(cell, j++));//�������(OLDNO)
      tawPart.setMsisdn(this.getCellContent(cell,j++)); //7MSISDN
      tawPart.setImsi(this.getCellContent(cell,j++)); //8IMSI
      temp = this.getCellContent(cell,j++); //9������   
      System.out.println(temp);
      if (temp.equals("��ʳ�ÿ�")) {
        tawPart.setCardType("0");
      }
      else if (temp.equals("���4�ÿ�")) {
        tawPart.setCardType("1");
      }
      else if (temp.equals("ʡ��4�ÿ�")) {
        tawPart.setCardType("2");
      }
      else if (temp.equals("ʡ�ʳ�ÿ�")) {
        tawPart.setCardType("3");
      }
      else if (temp.equals("���ز��Կ�")) {
        tawPart.setCardType("4");
      }
      else if (temp.equals("ʡ��4�ÿ�")) {
        tawPart.setCardType("5");
      }
      else if (temp.trim().equals("ʡ�ڳ�ÿ�")||temp.trim()=="ʡ�ڳ�ÿ�") {
        tawPart.setCardType("6");
      }
      temp = this.getCellContent(cell,j++); //10����״̬
      if (temp.equals("��")) {
        tawPart.setState("0");
      }
      else if (temp.equals("ͣ��")) {
        tawPart.setState("1");
      }
      else if (temp.equals("��ʧ")) {
        tawPart.setState("2");
      }
      else if (temp.equals("���")) {
        tawPart.setState("3");
      }
      else if (temp.equals("ʹ��")) {
        tawPart.setState("4");
      }
      else if (temp.equals("����")) {
        tawPart.setState("5");
      }

      tawPart.setCardpackage(this.getCellContent(cell,j++)); //11�ײ�
      tawPart.setToCrit(this.getCellContent(cell,j++)); //12ʹ��ʡ��
      tawPart.setToCity(this.getCellContent(cell,j++)); //13ʹ�ó���
      tawPart.setAdder(this.getCellContent(cell,j++)); //14������
      tawPart.setIntime(this.getCellContent(cell,j++)); //15�ĳ�ʱ��
//      tawPart.setOperation(this.getCellContent(cell,j)); //16��ע

      list.add(tawPart);
    }
    return list;
  }

//检查EXCEL文件中的内容,填写是否完整,是否出现填写错误
  //IMSI、使用地点、使用人、备注可为空
  public List checkData(int filetype) {
    List errorList = new ArrayList();
    List PartList = new ArrayList();
    TawTestcardDAO tawTestcardDAO = new TawTestcardDAO();
    try {
      if (filetype == 0) {
        PartList = getInExcPart();
        String ErrorInfo = "";
        for (int i = 0; i < PartList.size(); i++) {
          TawTestcard tp = (TawTestcard) PartList.get(i);
          //检查“册号”
          int rows = i + 3;
//          if (tp.getVolumenum() == null || tp.getVolumenum().trim().equals("")) {
//            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“册号”没有填写";
//            errorList.add(ErrorInfo);
//          }
          //检查“页号”
//          if (tp.getPagenum() == null || tp.getPagenum().trim().equals("")) {
//            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“页号”没有填写";
//            errorList.add(ErrorInfo);
//          }
          //检查“国家”
          if (tp.getFromCountry() == null ||
              tp.getFromCountry().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“国家”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
        	  int length = tawTestcardDAO.ifRight("taw_testcard_tree", "name", tp.getFromCountry());
        	  if(length==0){
        		  ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“国家”名称不对";
                  errorList.add(ErrorInfo);
        	  }
          }
          //检查“省份”
          if (tp.getFromCrit() == null || tp.getFromCrit().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“省份”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
        	  int length = tawTestcardDAO.ifRight("taw_testcard_tree", "name", tp.getFromCrit());
        	  if(length==0){
        		  ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“省份”名称不对";
                  errorList.add(ErrorInfo);
        	  }
          }
          //检查“城市”
          if (tp.getFromCity() == null || tp.getFromCity().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“城市”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
        	  int length = tawTestcardDAO.ifRight("taw_testcard_tree", "name", tp.getFromCity());
        	  if(length==0){
        		  ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“城市”名称不对";
                  errorList.add(ErrorInfo);
        	  }
          }
          //检查“运营商”
          if (tp.getFromOpe() == null || tp.getFromOpe().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“运营商”没有填写";
            errorList.add(ErrorInfo);
          }
//          //检查“IMSI”
//          if (tp.getImsi() == null || tp.getImsi().trim().equals("")) {
//            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“IMSI”没有填写";
//            errorList.add(ErrorInfo);
//          }
          //检查“卡号(ICCID)”
          if (tp.getIccid() == null || tp.getIccid().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“卡号(ICCID)”没有填写";
            errorList.add(ErrorInfo);
          }
          else {
/*            for (int j = 1; j < PartList.size() - i; j++) {
              TawTestcard tp1 = (TawTestcard) PartList.get(j + i);
              int ErrorRow = j + i + 2;
              if (tp.getIccid().trim().equals(tp1.getIccid().trim())) {
                ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“卡号(ICCID)”和第" +
                    ErrorRow + "行的“序列号”重复，请修改";
                errorList.add(ErrorInfo);
              }
            }*/
            int length = tawTestcardDAO.ifIccid("iccid", tp.getIccid());
            if(length!=0){
            	ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“卡号(ICCID)”与原有记录重复，请重新填写";
                errorList.add(ErrorInfo);
            }
            
            
//          if (PartId.get(tp.getIccid().trim())!=null){
//             ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“序列号”与数据库中的数据相同，请修改";
//             errorList.add(ErrorInfo);
//           }
          }
          //检查“MSISDN”
          if (tp.getMsisdn() == null || tp.getMsisdn().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“MSISDN”没有填写";
            errorList.add(ErrorInfo);
          }
          //检查“卡类型”
          if (tp.getCardType() == null || tp.getCardType().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“卡类型”没有填写";
            errorList.add(ErrorInfo);
          }
//          else{
//        	  int length=tawTestcardDAO.ifRight("taw_eventdic", "name", tp.getCardType());
//        	  if(length==0){
//        		  ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“卡类型”填写错误";
//                  errorList.add(ErrorInfo);
//        	  }
//          }
          //检查“套餐”
          if (tp.getCardpackage() == null ||
              tp.getCardpackage().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“套餐”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
        	  int length=tawTestcardDAO.ifRight("taw_eventdic", "name", tp.getCardpackage());
        	  if(length==0){
        		  ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“套餐”填写错误";
                  errorList.add(ErrorInfo);
        	  }
          }
          //检查“状态”
          if (tp.getState() == null || tp.getState().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“状态”没有填写";
            errorList.add(ErrorInfo);
          }
          //检查“费用情况”
          if (tp.getExes() == null || tp.getExes().trim() == "") {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“费用情况”没有填写";
            errorList.add(ErrorInfo);
          }
//          //检查“使用地点”
//          if (tp.getPosition() == null || tp.getPosition().trim() .equals("")) {
//            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“使用地点”没有填写";
//            errorList.add(ErrorInfo);
//          }
//          //检查“领用人”
//          if (tp.getAdder() == null || tp.getAdder().trim() == "") {
//            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“领用人”没有填写";
//            errorList.add(ErrorInfo);
//          }
        }
      }
      else if (filetype == 1) {
        PartList = getOutExcPart();
        String ErrorInfo = "";
        for (int i = 0; i < PartList.size(); i++) {
          TawTestcard tp = (TawTestcard) PartList.get(i);
          //检查“国家”
          int rows = i + 3;
          if (tp.getFromCountry() == null ||
              tp.getFromCountry().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“国家”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
        	  int length = tawTestcardDAO.ifRight("taw_testcard_tree", "name", tp.getFromCountry());
        	  if(length==0){
        		  ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“国家”名称不对";
                  errorList.add(ErrorInfo);
        	  }
          }
          //检查“省份”
          if (tp.getFromCrit() == null || tp.getFromCrit().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“省份”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
        	  int length = tawTestcardDAO.ifRight("taw_testcard_tree", "name", tp.getFromCrit());
        	  if(length==0){
        		  ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“省份”名称不对";
                  errorList.add(ErrorInfo);
        	  }
          }
          //检查“城市”
          if (tp.getFromCity() == null || tp.getFromCity().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“城市”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
        	  int length = tawTestcardDAO.ifRight("taw_testcard_tree", "name", tp.getFromCity());
        	  if(length==0){
        		  ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“城市”名称不对";
                  errorList.add(ErrorInfo);
        	  }
          }
          //检查“运营商”
          if (tp.getFromOpe() == null || tp.getFromOpe().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“运营商”没有填写";
            errorList.add(ErrorInfo);
          }
//          //检查“imsi”
//          if (tp.getImsi() == null || tp.getImsi().trim().equals("")) {
//            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“imsi”没有填写";
//            errorList.add(ErrorInfo);
//          }
          //检查“卡号(ICCID)”
          if (tp.getIccid() == null || tp.getIccid().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“卡号(ICCID)”没有填写";
            errorList.add(ErrorInfo);
          }
          else {
            /*for (int j = 1; j < PartList.size() - i; j++) {
              TawTestcard tp1 = (TawTestcard) PartList.get(j + i);
              int ErrorRow = j + i + 2;
              if (tp.getIccid().trim().equals(tp1.getIccid().trim())) {
                ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“卡号(ICCID)”和第" +
                    ErrorRow + "行的“序列号”重复，请修改";
                errorList.add(ErrorInfo);
              }
            }*/
            
            int length = tawTestcardDAO.ifIccid("iccid", tp.getIccid());
            if(length!=0){
            	ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“卡号(ICCID)”与原有记录重复，请重新填写";
                errorList.add(ErrorInfo);
            }
            
//          if (PartId.get(tp.getIccid().trim())!=null){
//             ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“序列号”与数据库中的数据相同，请修改";
//             errorList.add(ErrorInfo);
//           }
          }
          //检查“MSISDN”
          if (tp.getMsisdn() == null || tp.getMsisdn().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“MSISDN”没有填写";
            errorList.add(ErrorInfo);
          }
          //检查“卡类型”
          if (tp.getCardType() == null || tp.getCardType().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“卡类型”没有填写";
            errorList.add(ErrorInfo);
          }
//          else{
//        	  int length=tawTestcardDAO.ifRight("taw_eventdic", "name", tp.getCardType());
//        	  if(length==0){
//        		  ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“卡类型”填写错误";
//                  errorList.add(ErrorInfo);
//        	  }
//          }
          
          //检查“套餐”
          if (tp.getCardpackage() == null ||
              tp.getCardpackage().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“套餐”没有填写";
            errorList.add(ErrorInfo);
          }
          else{
        	  int length=tawTestcardDAO.ifRight("taw_eventdic", "name", tp.getCardpackage());
        	  if(length==0){
        		  ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“套餐”填写错误";
                  errorList.add(ErrorInfo);
        	  }
          }
          //检查“状态”
          if (tp.getState() == null || tp.getState().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“状态”没有填写";
            errorList.add(ErrorInfo);
          }
          //检查“使用省份”
          if (tp.getToCrit() == null || tp.getToCrit().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“使用省份”没有填写";
            errorList.add(ErrorInfo);
          }
          //检查“使用城市”
          if (tp.getToCity() == null || tp.getToCity().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“使用城市”没有填写";
            errorList.add(ErrorInfo);
          }
          //检查“寄出时间”
          if (tp.getIntime() == null || tp.getIntime().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“寄出时间”没有填写";
            errorList.add(ErrorInfo);
          }
          //检查“接收人”
          if (tp.getAdder() == null || tp.getAdder().trim().equals("")) {
            ErrorInfo = "文件“" + fileName + "”的第" + rows + "行的“接收人”没有填写";
            errorList.add(ErrorInfo);
          }
        }
      }
      else {
        errorList.add("没有选择文件类型");
        return errorList;
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return errorList;
  }

  //���֮�������,�ֳ�}����,��ݿ����Ѿ����ڵĻ�����Serialno��Ϊ��־��,��"UPDATE",�粻����,��"INSERT"
  public boolean importData(int filetype, String leave,String userName) {
    TawEventDicDAO dicDao = new TawEventDicDAO();
    boolean flag = true;
    TawTestcardDAO dao = new TawTestcardDAO();
    List PartList = new ArrayList();
    if (filetype == 0) {
      PartList = getInExcPart();
    }
    else if (filetype == 1) {
      PartList = getOutExcPart();
    }
    else {
      System.out.print("û��ѡ���ļ�����");
      flag = false;
      return flag;
    }
    Date dt = new Date();
    SimpleDateFormat smpDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String sTime = smpDateFormat.format(dt);

    try {
      for (int i = 0; i < PartList.size(); i++) {
        TawTestcard tp = (TawTestcard) PartList.get(i);
        tp.setIntime(sTime);
        if(filetype == 0) {
          tp.setLeave(leave);
        }else{
          tp.setLeave("");
        }
        
        tp.setAdder(userName);
        if(tp.getEditState().equals("1")){
        	dao.update(tp);	
        }
        else{
        	dao.insert(tp);	
        }
        
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }

    return flag;
  }
  public boolean importData(int filetype, String leave,String userName,String type) {
	    TawEventDicDAO dicDao = new TawEventDicDAO();
	    boolean flag = true;
	    TawTestcardDAO dao = new TawTestcardDAO();
	    List PartList = new ArrayList();
	    if (filetype == 0) {
	      PartList = getInExcPart();
	    }
	    else if (filetype == 1) {
	      PartList = getOutExcPart();
	    }
	    else {
	      System.out.print("û��ѡ���ļ�����");
	      flag = false;
	      return flag;
	    }
	    Date dt = new Date();
	    SimpleDateFormat smpDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String sTime = smpDateFormat.format(dt);

	    try {
	      for (int i = 0; i < PartList.size(); i++) {
	        TawTestcard tp = (TawTestcard) PartList.get(i);
	        tp.setIntime(sTime);
	        tp.setAdder(userName);
	        if(filetype == 0) {
	          tp.setLeave(leave);
	        }else{
	          tp.setLeave("");
	        }
	        if(type.equals("1")){
//	        	if(dao.getMsisdnCount(tp.getMsisdn(),)==0){
//	        		dao.insert(tp);	
//	        	}
//	        	
	        }
	        else if(type.equals("2")){
	        	if(tp.getEditState().equals("1")){
		        	dao.update(tp);	
		        }	
	        }
	        else if(type.equals("3")){
	        	dao.delete(tp.getMsisdn());	
	        }
	           
	      }
	    }
	    catch (SQLException ex) {
	      ex.printStackTrace();
	    }

	    return flag;
	  }


  private String getCellContent(Cell[] cell,int i){
    String content="";
    try {
      content = cell[i].getContents().trim();
      System.out.println("cell"+i+"hang");
    }
    catch (Exception ex) {
    	ex.printStackTrace();
      System.out.println("cell"+i+"hang");
    }
    return content;
  }

  public String UpLoadFile(FormFile file,String filePath){
     try{
           InputStream stream=file.getInputStream(); //���ļ�����
           OutputStream bos=new FileOutputStream(filePath+"/"+
                                                 file.getFileName()); //��bһ���ϴ��ļ��������
           int bytesRead=0;
           byte[] buffer=new byte[8192];
           while((bytesRead=stream.read(buffer,0,8192))!=-1){
               bos.write(buffer,0,bytesRead); //���ļ�д�������
           }
           bos.close();
           stream.close();
       }
       catch(Exception e){
           e.printStackTrace();
           return e.toString();
       }
       return "";
   }
  
}
