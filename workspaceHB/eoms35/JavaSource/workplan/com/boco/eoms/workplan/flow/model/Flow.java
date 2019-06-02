package com.boco.eoms.workplan.flow.model;

/**
 * <p>Title: 流程信息类</p>
 * <p>Description:定义一个流程信息，包括各步骤信息以及流程的名称等 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: boco</p>
 * @author not attributable
 * @version 1.0
 */

import java.util.List;
import java.util.Hashtable;

import org.jdom.Element;


public class Flow {
  private int serial; //流程序列号
  private String name; //流程名称
  private Hashtable stepHashtable; //步骤集合

  public Flow() {
  }

  public Flow(int _serial, String _name, Hashtable _stepHashtable) {
    this.serial = _serial;
    this.name = _name;
    this.stepHashtable = _stepHashtable;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSerial() {
    return serial;
  }

  public void setSerial(int serial) {
    this.serial = serial;
  }

  public Hashtable getStepHashtabler() {
    return stepHashtable;
  }

  public void setStepHashtable(Hashtable stepHashtable) {
    this.stepHashtable = stepHashtable;
  }

  /**
   * 获取第一步骤信息
   * @return Step 步骤信息
   */
  public Step getFristStep() {
    Step step = null;

    //如果当前步骤不是最后一个步骤
    if (stepHashtable.size() > 0) {
      step = (Step) stepHashtable.get("0");
    }
    return step;
  }

  public Step getNextStep(int _currentSetp) {
    Step step = null;
    int nexStep = ++_currentSetp;

    //如果当前步骤不是最后一个步骤
    if (stepHashtable.size() > nexStep) {
      step = (Step) stepHashtable.get(String.valueOf(nexStep));
    }
    return step;
  }


  /**
   * 根据xml文件属性信息，生成flow流程对象
   * @param _element Element xml文件属性信息
   * @return Flow flow流程对象
   */
  public static Flow importXML(Element _element) {
    //流程信息
    int serial = Integer.parseInt(_element.getAttributeValue("serial"));
    String name = _element.getAttributeValue("name");
    Hashtable hashtable = new Hashtable();

    Flow flow = null;
    Step step = null;

    Element stepElement = null;
    List list = _element.getChildren();

    String stepSerial;
    String stepName;
    int stepFlag;
    String stepDeptId;
    String stepCheckuser;
    String stepRoles;
    int stepType;

    //循环处理步骤信息
    for (int i = 0; i < list.size(); i++) {
      stepElement = (Element) list.get(i);
      stepSerial = stepElement.getAttributeValue("serial");
      stepName = stepElement.getAttributeValue("name");
      stepFlag = Integer.parseInt(stepElement.getAttributeValue("flag"));
      stepDeptId = stepElement.getAttributeValue("deptid");
      stepCheckuser = stepElement.getAttributeValue("checkuser");
      stepRoles = stepElement.getAttributeValue("roles");
      stepType = Integer.parseInt(stepElement.getAttributeValue("type"));

      step = new Step(stepSerial, stepName, stepFlag, stepDeptId,
                      stepCheckuser, stepRoles,stepType);
      hashtable.put(stepSerial,step);
    }

    //生成流程对象
    flow = new Flow(serial,name,hashtable);

    return flow;
  }
}
