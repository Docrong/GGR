package com.boco.eoms.sheet.base.flowchar.bias;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.flowchar.Node;
import com.boco.eoms.sheet.base.flowchar.NodeComparator;
import com.boco.eoms.sheet.base.flowchar.Position;
import com.boco.eoms.sheet.base.flowchar.VmlObject;

public class Graph {
	private String linkServiceName = "";
	private String description = "";
	private String dictSheet = "";
	private int floorSize = 0;
	int HORSPACE = 10;
	int VERSPACE = 180;
	
	public Graph(String linkServiceName, String description, String dictSheet) {
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
			firstNode.setStatus(Integer.parseInt(iTask.getTaskStatus()));			
			if(iTask.getOperateType().equals("user")){
				ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
				String name = service.id2Name(iTask.getTaskOwner(), "tawSystemUserDao");
				firstNode.setName(iTask.getTaskDisplayName()+"<br>"+name);
			}
			else if(iTask.getOperateType().equals("subrole")){
				ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
				String name = service.id2Name(iTask.getTaskOwner(), "tawSystemRoleDao");
				firstNode.setName(iTask.getTaskDisplayName()+"<br>"+name);
			}
			else if(iTask.getOperateType().equals("dept")){
				ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
				String name = service.id2Name(iTask.getTaskOwner(), "tawSystemDeptDao");
				firstNode.setName(iTask.getTaskDisplayName()+"<br>"+name);
			}
			
			firstNode.setParentId("");
			firstNode.setParentLink("");

			// 所有处理过的node节点
			ArrayList allNodeList = new ArrayList();
			allNodeList.add(firstNode);

			// [0]记录顶层编号；[1]...开始记录每层有多少个节点
			ArrayList allFloorList = new ArrayList();
			allFloorList.add("1"); // 共有几层
			allFloorList.add("1"); // 第一层节点个数

			getAllNode(allNodeList, allFloorList, 2, allNodeList, allTiTaskList);
			
			String vml = getVml(allNodeList, allFloorList);
			//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			//System.out.println(vml);

			vml += "<v:line style='Z-INDEX: 1001;  LEFT: 0px; TOP: " + (floorSize * VERSPACE + 120) + "px;POSITION: absolute;' from = '0px,0px' to = '1000px,0px' strokecolor = '#006699' strokeweight = '1pt'>" 
				 + "<v:Stroke startarrow = 'oval' endarrow = 'oval'></v:Stroke>"
				 + "</v:line>";
			
			allVml = allVml + vml;
		}
		

		allVml += "<v:roundrect style='visibility:hidden; LEFT: 0px; TOP: "
			+ (floorSize + 1) * VERSPACE
			+ "px;POSITION: absolute;'></v:roundrect>";		

		return allVml;
	}

	/**
	 * @param allNodeList  所有处理过的node节点
	 * @param allFloorList [0]记录顶层编号；[1]...开始记录每层有多少个节点
	 * @param floorNumber  当前在处理的层号
	 * @param nodeIterator 上一层的node节点列表
	 * @param taskIterator 所有未处理的task节点
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
					node.setStatus(Integer.parseInt(task.getTaskStatus()));
					
					if(task.getOperateType().equals("user")){
						ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
						String name = service.id2Name(task.getTaskOwner(), "tawSystemUserDao");
						node.setName(task.getTaskDisplayName()+"<br>"+name);
					}
					else if(task.getOperateType().equals("subrole")){
						ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
						String name = service.id2Name(task.getTaskOwner(), "tawSystemRoleDao");
						node.setName(task.getTaskDisplayName()+"<br>"+name);
					}
					else if(task.getOperateType().equals("dept")){
						ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
						String name = service.id2Name(task.getTaskOwner(), "tawSystemDeptDao");
						node.setName(task.getTaskDisplayName()+"<br>"+name);
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
			getAllNode(allNodeList, allFloorList, floorNumber + 1,
					newNodeIterator, newTaskIterator);
		}
	}

	public String getVml(ArrayList nodeList, ArrayList floorList) throws Exception {
		
		List lstAllVmlObjs = new ArrayList(); // 图形对象LIST
		HashMap rectMap = new HashMap(); // 矩形对象MAP

		// 按照 code（房间编号）对 nodeList 排序
		Comparator comp = new NodeComparator();
		Collections.sort(nodeList, comp);

		System.out.println("---------- 打印层次信息 ----------");
		for (int i = 0; i < floorList.size(); i++) {
			System.out.println("第  " + i + " 行矩形个数为 " + floorList.get(i));
		}
		for (int i = 0; i < nodeList.size(); i++) {
			Node node = (Node) nodeList.get(i);
			System.out.println("层次编码:" + node.getCode() + " 他的工作项是" + node.getId());
		}

		// ---------------开始生成Node图形---------------		
		int nodeNumber = 0; // 房间号
		int maxFloorNum = nullObject2int(floorList.get(0)); //最大层数
		int[] levelWidth = new int[maxFloorNum + 1];
		for (int i = 0; i <= maxFloorNum; i++) {
			levelWidth[i] = 0;
		}

		for (int i = 1; i <= maxFloorNum; i++) {// 循环层数
			int nodeSize = nullObject2int(floorList.get(i)); //每层节点个数

			for (int j = 0; j < nodeSize; j++) {// 循环房间
				// i:row行 j:col列
				Node tempNode = (Node) nodeList.get(nodeNumber + j);
				System.out.println("房间号:" + tempNode.getCode() + "房间名称:" + tempNode.getName() + "子房间个数" + tempNode.getSubSize());

				int width = levelWidth[i];
				Position position = new Position(width, (i+floorSize) * VERSPACE); // 设置矩形的坐标
				Rect rect = new Rect(position); // 生成矩形节点
				rect.setContent(tempNode.getName());
				rect.setStatus(tempNode.getStatus());// 已回复
				if(tempNode.getStatus() == 5){ //表示已经处理回复过的
					rect.setColorBegin("#3366CC");
					rect.setColorEnd("#3399FF");					
				}

				 
				levelWidth[i] = levelWidth[i] + rect.getWidth() + HORSPACE;
				lstAllVmlObjs.add(rect);// 放入图形对象LIST
				rectMap.put(tempNode.getCode(), rect);// 放入矩形对象MAP
			}

			nodeNumber = nodeNumber + nodeSize;
		}
		
		floorSize = floorSize + (maxFloorNum);
		
		ILinkService linkService = (ILinkService)ApplicationContextHolder.getInstance().getBean(linkServiceName);
		// 根据图形节点，从最下层的节点生成线
		for (int i = nodeList.size() - 1; i >= 0; i--) {
			Node tempNode = (Node) nodeList.get(i);
			String code = tempNode.getCode();

			if (code.length() > 3) {
				String parentCode = code.substring(0, code.length() - 3);
				Rect fromRect = (Rect) rectMap.get(parentCode);
				Rect toRect = (Rect) rectMap.get(code);

				String preLink = tempNode.getParentLink();
				BaseLink sendLink = linkService.getSingleLinkPO(preLink);								
				String sendOperName = (String) DictMgrLocator.getDictService().itemId2name(Util.constituteDictId(dictSheet,description),String.valueOf(sendLink.getOperateType()));
				
				Line line = new Line(fromRect, toRect);
				line.setContent(sendOperName + "-->" );
				line.setFlagStartArrow("false");

				 if (tempNode.getStatus() == 5) {
					 line.setFlagStartArrow("true");
					 String curLink = tempNode.getCurrentLink();
					 BaseLink backLink = linkService.getSingleLinkPO(curLink);
					 String backOperName = (String) DictMgrLocator.getDictService().itemId2name(Util.constituteDictId(dictSheet,description),String.valueOf(backLink.getOperateType()));
					 line.setContent(sendOperName + "<->" + backOperName);
				 }
				 
				lstAllVmlObjs.add(line);
			}
		}

		String strVml = "";
		for (int i = 0; i < lstAllVmlObjs.size(); i++) {
			VmlObject vo = (VmlObject) lstAllVmlObjs.get(i);
			String vml = vo.getVml();
			strVml += vml;
		}
		
		return strVml;
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
		int maxFloorNumber = nullObject2int(floorSizeList.get(0));
		if (floorNumber > maxFloorNumber) {
			floorSizeList.set(0, String.valueOf(floorNumber));// 记录最大层号
			floorSizeList.add("0");// 增加一层
		}
		int floorSize = nullObject2int(floorSizeList.get(floorNumber)) + 1;
		floorSizeList.set(floorNumber, String.valueOf(floorSize)); // 更新所处楼层的节点个数
	}

	private int nullObject2int(Object s) {
		String str = "";
		int i = 0;
		try {
			str = s.toString();
			i = Integer.parseInt(str);
		} catch (Exception e) {
			i = 0;
		}
		return i;
	}
}