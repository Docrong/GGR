package com.boco.eoms.sparepart.bo;

import java.util.*;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.model.TawTree;
import com.boco.eoms.sparepart.dao.*;
import com.boco.eoms.sparepart.controller.*;
import com.boco.eoms.sparepart.util.*;
import com.boco.eoms.common.util.StaticMethod;

/**
 * <p>Title: ��Ʒ����</p>
 * <p>Description: EOMS��ϵͳ</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.7
 */

public class TawTreeBO extends BO{

    public TawTreeBO(){
    }

    public TawTreeBO(ConnectionPool ds){
        super(ds);
    }

    public TawTreeBO(ConnectionPool ds,String str){
        super(ds,str);
    }

    /**
     * @see
     * @param id
     * @param cname
     * @param note
     * @return
     */
    public int insert(int id,String cname,String note){
        TawTreeDao dao=new TawTreeDao(ds);
        TawTree tawTree=null;
        int layers=0;
        //�����Ը�idΪ��������Ϣ�������ţ�Ĭ��0
        int radix=dao.getMaxRadix(id);
        if(id!=0){
            tawTree=dao.getParentMsg(id); //����Ϣ
        }
        if(tawTree!=null){
            String layer=tawTree.getLayer()+"_"+tawTree.getRadix();
            tawTree=TawTree.load(tawTree.getId(),layer,radix,cname,note);
            if (checkClassName(cname,tawTree.getParentId()) == true){
              dao.insert(tawTree);
              //��ӵ��ǵڼ������ݣ����㷵��1����㷵��2
              layers = getLayer(layer);
            }
            else{
              layers = -1;
            }
        }
        else{
            tawTree=TawTree.load(0,"0",radix,cname,note);
            if (checkClassName(cname,tawTree.getParentId()) == true){
              dao.insert(tawTree);
              //Ĭ�Ϸ���0
            }
            else{
              layers = -1;
            }
        }
        tawTree=null;
        dao=null;
        return layers;
    }

    /**
     * @see ��� form����Ϣ���ظ�id�Ͳ���layer
     * @param form
     */
    public void getParentOrLayer(TawTreeForm form){
        if(form.getId()!=0){
            form.setParentId(form.getId());
            form.setLayer("2");
        }
        else if(form.getRoot()!=null&&form.getSubType()!=null){
            TawTreeDao dao=new TawTreeDao(ds);
            TawTree tawTree=dao.getParentMsg(form.getSubType(),form.getRoot());
             if(tawTree!=null){
	            form.setParentId(tawTree.getId());
	            form.setId(tawTree.getId());
             }
            form.setLayer("4");
            dao=null;
        }
        else if(form.getSubType()!=null){
       	 TawTreeDao dao=new TawTreeDao(ds);
            TawTree tawTree=dao.getParentMsg(form.getDept(),form.getSubType());
             if(tawTree!=null){
	            form.setParentId(tawTree.getId());
	            form.setId(tawTree.getId());
             }
            form.setLayer("3");
            dao=null;
       }
        else{
            form.setParentId(0);
            form.setLayer("1");
        }

    }

    /**
     * @see ��ݸ� id �������еı����������Ե���Ϣ�б�
     * @param parentId �� id
     * @return �����������Ե���Ϣ�б�
     */
    public List getView(int parentId,String type){
        TawTreeDao dao=new TawTreeDao(ds);
        List tree=dao.getTree(parentId,type);
        dao=null;
        return tree;
    }

    /**
     * @see ��� layer����,���ر������Ե��ַ�
     * @param layer ����
     * @return  �Ѿ�ƴ�õ��ַ���js������ɶ�̬��-��
     */
    public String getMyTreeStr(int layer){
        String str="";
        TawTreeDao dao=new TawTreeDao(ds);
         
        com.boco.eoms.sparepart.util.myTreeMap myTree=dao.getStrTree(layer);
        Set mySet=myTree.myHashMap.entrySet();
        Iterator i=mySet.iterator();
        while(i.hasNext()){
            Map.Entry me=(Map.Entry)i.next();
            Object ok=me.getKey();
            Object ov=me.getValue();
            str=str+"dsy.add('"+ok+"',["+ov+"]);";
        }
        dao=null;
        return str;
    }

    /**
     * @see ���id����һ���������Ϣ
     * @param Id Ψһ
     * @return ����������Ϣ
     */
    public TawTree getTree(int Id){
        TawTreeDao dao=new TawTreeDao(ds);
        TawTree tree=dao.getParentMsg(Id);
        dao=null;
        return tree;
    }

    /**
     * @see ���layer�����ȷ������
     * @param layer  �����
     * @return  ����
     */
    public int getLayer(String layer){
        String[] layers=layer.split("_");
        return layers.length-1;
    }

    /**
     * @see ���idȷ�� ��Ϣ���ڵĲ���
     * @param id Ψһ
     * @return ����
     */
    public int getLayer(int id){
        if(id!=0){
            TawTree tree=getTree(id);
            return getLayer(tree.getLayer());
        }
        else{
            return 0;
        }
    }

    /**
     * @see �ɸ�idȷ����༭����Ϣ���ڵĲ���
     * @param id ��id�����
     * @return ����
     */
    public int getParentToLayer(int id){
        if(id!=0){
            return getLayer(id)+1;
        }
        else{
            return 0;
        }
    }

    /**
     * @see ���id update ��ص�������Ϣ
     * @param id  Ψһ
     * @param cname  �����������
     * @param note ������ע
     */
    public void updateTree(int id,String cname,String note){
        TawTreeDao dao=new TawTreeDao(ds);
        dao.update(id,cname,note);
        dao=null;
    }

    /**
     * @see ���id ɾ���������Ϣ
     * @param id Ψһ
     */
    public void dropTree(int id){
        TawTreeDao dao=new TawTreeDao(ds);
        dao.drop(id);
        dao=null;
    }

    //����Ƿ�����
    public boolean checkClassName(String cname,int parentId){
        boolean flag=true;
        TawCommonDAO dao=new TawCommonDAO(ds);
        flag=dao.checkName("taw_sp_tree","cname",cname,"parent_id",parentId);
        return flag;
    }
    //ȡ�ñ�����רҵ���ͺ����֮��Ĺ�j��.1����4���Ĺ�jxml�ļ���
    public String getTreeNodesForXml(int parentId){
    	String xml="";
        TawTreeDao dao=new TawTreeDao(ds);
        TawTree tree=new TawTree();
        List treeList=null;
        List cnameList=null;
        treeList=dao.getTree(parentId);
        for(int i=0;i<treeList.size();i++){
        	tree=(TawTree)treeList.get(i);
        	xml=xml+"<TreeNode Value=\""+tree.getCname()+"\">\n";
        	cnameList=dao.getTreeForThree(tree.getId());
            for(int j=0;j<cnameList.size();j++){
            	String cname=StaticMethod.nullObject2String(cnameList.get(j));
            	xml=xml+"<TreeNode Value=\""+cname+"\"></TreeNode>\n";        	
            }
            xml=xml+"</TreeNode>\n";
        	
        }
    	return xml;
    }    
}
