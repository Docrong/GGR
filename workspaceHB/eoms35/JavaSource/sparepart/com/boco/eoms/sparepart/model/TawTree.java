package com.boco.eoms.sparepart.model;

/**
 * <p>Title: 备品备件</p>
 * <p>Description: EOMS子系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.7
 */

public class TawTree implements java.io.Serializable{
    public TawTree(){
    }

    private String cname;
    private String ename;
    private String code;
    private String note;
    private String layer;
    private int id;
    private int parentId;
    private int radix;
    private int deleted;

    public String getCname(){
        return cname;
    }

    public void setCname(String cname){
        this.cname=cname;
    }

    public String getEname(){
        return ename;
    }

    public void setEname(String ename){
        this.ename=ename;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code=code;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note=note;
    }

    public String getLayer(){
        return layer;
    }

    public void setLayer(String layer){
        this.layer=layer;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getParentId(){
        return parentId;
    }

    public void setParentId(int parentId){
        this.parentId=parentId;
    }

    public int getRadix(){
        return radix;
    }

    public void setRadix(int radix){
        this.radix=radix;
    }

    public int getDeleted(){
        return deleted;
    }

    public void setDeleted(int deleted){
        this.deleted=deleted;
    }

    public static TawTree load(int _parentId,String _layer,int _radix,
                               String _cname,String _note){
        TawTree tawTree=null;
        try{
            tawTree=new TawTree();
            tawTree.setParentId(_parentId);
            tawTree.setLayer(_layer);
            tawTree.setRadix(_radix);
            tawTree.setCname(_cname);
            tawTree.setNote(_note);
        }
        catch(Exception e){
            e.printStackTrace();
            tawTree=null;
        }
        return tawTree;
    }
}