package com.boco.eoms.knowledge.service;

import java.util.Map;

public interface IKnowledgeService {
	public String getMainFromLink(Map map) throws Exception;
	public String getLinks(String sheetKey) throws Exception;
}
