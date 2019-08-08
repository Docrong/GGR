package com.boco.gr.dao;

import java.util.List;
import java.util.Map;

import com.boco.gr.model.TawRiskDocument;


public interface RiskControlDao {
    public void saveTawRiskDocument(TawRiskDocument t);//����-�����ĵ�

    public Map getTawRiskDocumentList(Map maptj);
}
