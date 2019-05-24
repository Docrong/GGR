package com.boco.eoms.sheet.base.flowchar.xmltree;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.task.ITask;

public class Graph {
	private String linkServiceName = "";

	private String description = "";

	private String dictSheet = "";

	public Graph(String linkServiceName, String description, String dictSheet) {
		this.linkServiceName = linkServiceName;
		this.description = description;
		this.dictSheet = dictSheet;
	}

	public String[] draw(List allAiTaskList, List allTiTaskList)
			throws Exception {

		int taskNodeListSize = allAiTaskList.size();
		String[] result = new String[taskNodeListSize];

		ILinkService linkService = (ILinkService) ApplicationContextHolder
				.getInstance().getBean(linkServiceName);

		for (int index = 0; index < taskNodeListSize; index++) {
			ITask iTask = (ITask) allAiTaskList.get(index);

			XMLModel xModel = new XMLModel();
			xModel.setId(iTask.getId());
			xModel.setStatus(iTask.getTaskStatus());
			ID2NameService service = (ID2NameService) ApplicationContextHolder
					.getInstance().getBean("ID2NameGetServiceCatch");
			String name = service.id2Name(iTask.getTaskOwner(),
					"tawSystemUserDao");
			if (name.equals("")) {
				name = service
						.id2Name(iTask.getTaskOwner(), "tawSystemRoleDao");
			}
			if (name.equals("")) {
				name = service
						.id2Name(iTask.getTaskOwner(), "tawSystemDeptDao");
			}
			xModel.setName(iTask.getTaskDisplayName() + " " + name);

			String linkId = iTask.getCurrentLinkId();
			if (linkId != null) {
				BaseLink baseLink = linkService.getSingleLinkPO(linkId);
				if (baseLink != null) {
					String operName = (String) DictMgrLocator.getDictService()
							.itemId2name(
									Util.constituteDictId(dictSheet,
											description),
									String.valueOf(baseLink.getOperateType()));
					if (Integer.parseInt(iTask.getTaskStatus()) == 5) { // 已处理
						xModel.setOperate(operName + "--已回复");
					} else {
						xModel.setOperate(operName + "--");
					}
				}
			}

			xModel.setParentId("");
			xModel.setParentLink("");

			ArrayList modelIterator = new ArrayList();
			modelIterator.add(xModel);

			getAllNode(modelIterator, allTiTaskList);

			result[index] = xModel.getXML();

			// System.out.println();
			// System.out.println(result[index]);
			// System.out.println();
		}

		return result;
	}

	public void getAllNode(List modelIterator, List taskIterator)
			throws Exception {

		List newModelIterator = new ArrayList();

		List newTaskIterator = new ArrayList();
		newTaskIterator.addAll(taskIterator);

		int modelIteratorSize = modelIterator.size();
		int taskIteratorSize = taskIterator.size();

		ILinkService linkService = (ILinkService) ApplicationContextHolder
				.getInstance().getBean(linkServiceName);

		for (int i = 0; i < modelIteratorSize; i++) {
			XMLModel parModel = (XMLModel) modelIterator.get(i);

			for (int j = 0; j < taskIteratorSize; j++) {
				ITask iTask = (ITask) taskIterator.get(j);

				if (parModel.getId().equals(iTask.getParentTaskId())) {
					XMLModel newXmlModel = new XMLModel();
					newXmlModel.setId(iTask.getId());
					newXmlModel.setStatus(iTask.getTaskStatus());

					if (iTask.getOperateType().equals("user")) {
						ID2NameService service = (ID2NameService) ApplicationContextHolder
								.getInstance()
								.getBean("ID2NameGetServiceCatch");
						String name = service.id2Name(iTask.getTaskOwner(),
								"tawSystemUserDao");
						newXmlModel.setName(iTask.getTaskDisplayName() + " "
								+ name);
					} else if (iTask.getOperateType().equals("subrole")) {
						ID2NameService service = (ID2NameService) ApplicationContextHolder
								.getInstance()
								.getBean("ID2NameGetServiceCatch");
						String name = service.id2Name(iTask.getTaskOwner(),
								"tawSystemRoleDao");
						newXmlModel.setName(iTask.getTaskDisplayName() + " "
								+ name);
					} else if (iTask.getOperateType().equals("dept")) {
						ID2NameService service = (ID2NameService) ApplicationContextHolder
								.getInstance()
								.getBean("ID2NameGetServiceCatch");
						String name = service.id2Name(iTask.getTaskOwner(),
								"tawSystemDeptDao");
						newXmlModel.setName(iTask.getTaskDisplayName() + " "
								+ name);
					}

					String linkId = iTask.getCurrentLinkId();
					if (linkId != null) {
						BaseLink baseLink = linkService.getSingleLinkPO(linkId);
						if (baseLink != null) {
							String operName = (String) DictMgrLocator
									.getDictService().itemId2name(
											Util.constituteDictId(dictSheet,
													description),
											String.valueOf(baseLink
													.getOperateType()));
							if (Integer.parseInt(iTask.getTaskStatus()) == 5) { // 已处理
								newXmlModel.setOperate(operName + "--已回复");
							} else {
								newXmlModel.setOperate(operName + "--");
							}
						}
					}

					newXmlModel.setCurrentLink(iTask.getCurrentLinkId());
					newXmlModel.setParentId(iTask.getParentTaskId());
					newXmlModel.setParentLink(iTask.getPreLinkId());

					parModel.setSubSize(parModel.getSubSize() + 1);
					parModel.addSub(newXmlModel);

					newModelIterator.add(newXmlModel);
					newTaskIterator.remove(iTask);
				}
			}
		}

		if (newModelIterator.size() > 0) {
			getAllNode(newModelIterator, newTaskIterator);
		}
	}
}