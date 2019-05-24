package com.boco.eoms.extra.supplierkpi.test.service;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateManager;

public class TawSupplierkpiTemplateManagerTest extends ConsoleTestCase {
	private ITawSupplierkpiTemplateManager manager;


	public void testSaveTawSystemDictTypeAndTemplate() {
		TawSupplierkpiDict tawSupplierkpiDict = new TawSupplierkpiDict();
		tawSupplierkpiDict.setDictId("123");
		tawSupplierkpiDict.setDictName("123");
		tawSupplierkpiDict.setLeaf("333");
		tawSupplierkpiDict.setParentDictId("123");
		TawSupplierkpiTemplate tawSupplierkpiTemplate = new TawSupplierkpiTemplate();
		tawSupplierkpiTemplate.setSpecialType(tawSupplierkpiDict.getDictId());

		manager.saveDictAndTemplate(tawSupplierkpiDict,
				tawSupplierkpiTemplate);

		TawSupplierkpiTemplate template = manager
				.getTawSupplierkpiTemplate(tawSupplierkpiTemplate.getId());
		assertNotNull(template);
		assertEquals(tawSupplierkpiDict.getDictId(),template.getSpecialType());
		
	} 
}
