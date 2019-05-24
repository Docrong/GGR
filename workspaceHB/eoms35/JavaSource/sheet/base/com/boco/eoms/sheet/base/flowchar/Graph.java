package com.boco.eoms.sheet.base.flowchar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
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

	private int x = 6; // 初始矩形结点左上角的横坐标
	private int y = 6; // 初始矩形结点左上角的纵坐标
	private static Position rectDefine = new Position(110, 50); // 矩形的长宽
	private static Position spaceDefine = new Position(110, 50); // 矩形之间的间距	
	private int maxY = 0;
	
	public Graph(String linkServiceName, String description, String dictSheet){
		this.linkServiceName = linkServiceName;
		this.description = description;
		this.dictSheet = dictSheet;
	}

	public String draw(List allAiTaskList, List allTiTaskList) throws Exception {
		String allVml = "";

		int taskNodeListSize = allAiTaskList.size();
	
		for (int index = 0; index < taskNodeListSize; index++) {
			// 第一个节点
			ITask iTask = (ITask) allAiTaskList.get(index);

			// 第一个节点
			Node firstNode = new Node();
			firstNode.setCode("101");
			firstNode.setId(iTask.getId());
			firstNode.setName(iTask.getTaskDisplayName());
			firstNode.setStatus(StaticMethod.getIntValue(iTask.getTaskStatus()));
			firstNode.setParentId("");
			firstNode.setParentLink("");

			// 所有处理过的node节点
			ArrayList allNodeList = new ArrayList();
			allNodeList.add(firstNode);

			// [0]记录顶层编号；[1]...开始记录每层有多少个节点
			ArrayList allFloorList = new ArrayList();
			allFloorList.add("1"); // 第一层
			allFloorList.add("1"); // 第一层节点个数

			getAllNode(allNodeList, allFloorList, 2, allNodeList, allTiTaskList);

			allVml = allVml + getVml(allNodeList, allFloorList);
			
			x = 6;
			y = maxY;
		}

		return allVml;
	}

	/**
	 * @param allNodeList
	 *            所有处理过的node节点
	 * @param allFloorList
	 *            [0]记录顶层编号；[1]...开始记录每层有多少个节点
	 * @param floorNumber
	 *            当前在处理的层号
	 * @param nodeIterator
	 *            上一层的node节点列表
	 * @param taskIterator
	 *            所有未处理的task节点
	 */
	public void getAllNode(List allNodeList, List allFloorList,
			int floorNumber, List nodeIterator, List taskIterator) {
		List newNodeIterator = new ArrayList(); // 新处理的node
		List newTaskIterator = new ArrayList(); // 剩余未处理的task
		newTaskIterator.addAll(taskIterator);
		
		int nodeIteratorSize = nodeIterator.size();
		int taskIteratorSize = taskIterator.size();

		for (int i = 0; i < nodeIteratorSize; i++) {
			Node parNode = (Node) nodeIterator.get(i);
			int codeIndex = 0;	
			
			for (int j = 0; j < taskIteratorSize; j++) {
				ITask task = (ITask) taskIterator.get(j);			
				if (parNode.getId().equals(task.getParentTaskId())) {
					String code = getMyFloorCode(parNode.getCode(), codeIndex++); // 得到我的层次编号

					Node node = new Node();
					node.setCode(code);
					node.setId(task.getId());
					node.setCurrentLink(task.getCurrentLinkId());
					
					if(task.getOperateType().equals("user")){
						ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
						String name = service.id2Name(task.getTaskOwner(), "tawSystemUserDao");
						node.setName(task.getTaskDisplayName()+"<br><br>"+name);
					}
					else if(task.getOperateType().equals("subrole")){
						ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
						String name = service.id2Name(task.getTaskOwner(), "tawSystemRoleDao");
						node.setName(task.getTaskDisplayName()+"<br><br>"+name);
					}
					else if(task.getOperateType().equals("dept")){
						ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
						String name = service.id2Name(task.getTaskOwner(), "tawSystemDeptDao");
						node.setName(task.getTaskDisplayName()+"<br><br>"+name);
					}

					node.setParentId(task.getParentTaskId());
					node.setParentLink(task.getPreLinkId());					
					
					parNode.setSubSize(parNode.getSubSize() + 1);

					// 加入到节点列表
					allNodeList.add(node);

					// 更新最大楼层编号，并更新我所在的楼层所拥有的Node个数
					addEntityNumber(allFloorList, floorNumber);

					newNodeIterator.add(node);
					newTaskIterator.remove(task);
				} 
			}
		}

		if (newNodeIterator.size() > 0) {
			getAllNode(allNodeList, allFloorList, floorNumber + 1, newNodeIterator, newTaskIterator);
		}
	}

	// 得到层级编码
	private String getMyFloorCode(String parFloorCode, int number) {
		int int_id = 1000 + number;
		String str = String.valueOf(int_id);
		str = parFloorCode + str.substring(1);
		return str;
	}

	// floorSizeList [0]记录顶层编号；[1]...开始记录每层有多少个节点
	// floorNumber 当前在处理的层号
	private void addEntityNumber(List floorSizeList, int floorNumber) {
		int maxFloorNumber = StaticMethod.nullObject2int(floorSizeList.get(0));
		if (floorNumber > maxFloorNumber) {
			floorSizeList.set(0, String.valueOf(floorNumber));// 记录最大层号
			floorSizeList.add("0");// 增加一层
		}

		int floorSize = StaticMethod.nullObject2int(floorSizeList.get(floorNumber)) + 1;
		floorSizeList.set(floorNumber, String.valueOf(floorSize)); // 更新所处楼层的节点个数
	}

	public String getVml(ArrayList nodeList, ArrayList floorList) throws Exception {
		// 按照 code（房间编号）对 nodeList 排序
		Comparator comp = new NodeComparator();
		Collections.sort(nodeList, comp);

		// ---------------开始生成Node图形---------------
		List vmlObjList = new ArrayList(); // 图形对象LIST

		int roomNum = 0; // 房间号
		int maxFloorNum = StaticMethod.nullObject2int(floorList.get(0)); // 最大层数
		HashMap rectMap = new HashMap(); // 矩形对象MAP

		for (int i = 1; i <= maxFloorNum; i++) {// 循环楼层
			int roomSize = StaticMethod.nullObject2int(floorList.get(i)); // 楼层房间个数

			for (int j = 0; j < roomSize; j++) {// 循环房间
				Node tempNode = (Node) nodeList.get(roomNum + j);
				
				Position position = new Position(x, y); // 设置矩形的坐标
				Rect rect = new Rect(rectDefine, spaceDefine, position, tempNode.getCode()); // 生成矩形节点

				rect.setId(String.valueOf(i));

				// 设置：背景色：如果节点处理完成了，那么就显示渐变的蓝色				
				 if (tempNode.getStatus() == 5) {
				   rect.setRectFillcolor("#006699");// 设置矩形的背景色
				   rect.setFillColor2("#5aaeef");// 设置矩形的背景色 }
				 }

				// 设置：文字区域的内容
				rect.setTextboxContent(tempNode.getName());

				// 设置：超链接
				//rect.setLink("javascript:popup('detailNode.do?id=" + tempNode.getId() + "');");

				vmlObjList.add(rect);// 放入图形对象LIST
				rectMap.put(tempNode.getCode(), rect);// 放入矩形对象MAP

				// 如果当前节点有子节点，下一个行矩形的纵坐标
				if (tempNode.getSubSize() > 1) {
					y = position.getY() + (rectDefine.getY() + spaceDefine.getY()) * tempNode.getSubSize();
				} 
				else {
					y = position.getY()	+ (rectDefine.getY() + spaceDefine.getY());
				}

				if(y > maxY){
					maxY = y;
				}
			}

			// 下一列，第一个矩形的坐标
			x = x + rectDefine.getX() + spaceDefine.getX();
			y = 10;

			roomNum = roomNum + roomSize;
		}

		ILinkService linkService = (ILinkService)ApplicationContextHolder.getInstance().getBean(linkServiceName);
		
		// 根据图形节点，从最下层的节点生成线
		for (int i = nodeList.size() - 1; i >= 0; i--) {
			Node tempNode = (Node) nodeList.get(i);
			String code = tempNode.getCode();

			if (code.length() > 3) {
				String parentCode = code.substring(0, code.length() - 3);
				Rect fromRect = (Rect) rectMap.get(parentCode);
				Rect toRect = (Rect) rectMap.get(code);

				PolyLine line = new PolyLine(fromRect, toRect);

				line.setId(String.valueOf(i));

				// 设置：文字区域的内容
				String preLink = tempNode.getParentLink();
				BaseLink sendLink = linkService.getSingleLinkPO(preLink);								
				String sendOperName = (String) DictMgrLocator.getDictService().itemId2name(Util.constituteDictId(dictSheet,description),String.valueOf(sendLink.getOperateType()));
				line.setTextContent(i + sendOperName);
				
				// 设置：直线父节点的箭头
				 if (tempNode.getStatus() == 5) {
					 line.setStrokeStartarrow("classic");
					 String curLink = tempNode.getCurrentLink();
					 BaseLink backLink = linkService.getSingleLinkPO(curLink);
					 String backOperName = (String) DictMgrLocator.getDictService().itemId2name(Util.constituteDictId(dictSheet,description),String.valueOf(backLink.getOperateType()));
					 line.setTextContent(i + sendOperName + " <--> " + backOperName);					 
				 }
				
				// 设置：超链接
				//line.setLink("javascript:popup('detailNode.do?id=" + tempNode.getId() + "');");

				vmlObjList.add(line);
			}
		}

		String strVml = "";
		for (int i = 0; i < vmlObjList.size(); i++) {
			VmlObject vo = (VmlObject) vmlObjList.get(i);
			String vml = vo.getVml();
			strVml += vml;
		}

		return strVml;
	}
}