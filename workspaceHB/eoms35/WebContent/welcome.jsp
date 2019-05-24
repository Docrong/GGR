<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<div class="welcome">

  <div class="title">
    <div class="clear"></div>
    <div class="content">
         电子运维平台系统是中国移动运行维护部门从自身的运行维护工作模式和特点出发，利用现有的网管平台并结合中国移动信息系统运行维护工作规范，构建的一套满足中国移动维护工作需要的集中化、流程化、电子化、自动化、智能化的系统，用于中国移动集团及各省级分公司运维人员完成运维日常维护工作，并将工作进行规范性记录，定期上报中国移动集团运维管理部门，形成合理、高效、规范的电子化处理流程。

    </div>
    <div class="foot"></div>
  </div>
  
  <div class="module">
    <div class="img">
    
    <img src="${app}/styles/default/images/wel-flow-img.gif"/></div>
    <div class="detail"><img src="${app}/styles/default/images/wel-flow.gif"/><br/>
中国移动网络运维流程体系是参考了TMF提出的eTOM模型，根据中国移动网络运维部门的职责和定位，针对主要核心任务提出了三大类十六个流程，包括业务实现类的新业务开通、变更管理 、网络配置，运行维护类的告警监控、故障管理、性能管理、作业计划、网络优化、集团客户服务保障，以及支撑类的知识管理、IT需求管理、流程管理、供应商服务管理、应急与事件管理、安全管理、资产管理等，覆盖了中国移动日常网络运维管理和生产的大部分工作。
　　</div>
  </div>
  
  <div class="module">
    <div class="img">
    
    <img src="${app}/styles/default/images/wel-plat-img.gif"/></div>
    <div class="detail"><img src="${app}/styles/default/images/wel-plat.gif"/><br/>
     系统提供组织管理功能，主要为用户提供了字典管理，权限管理，机房管理，角色管理，用户管理，部门管理，菜单管理等功能。用户可以使用各项功能对电子运维系统基础数据进行编辑维护。这些基础数据对运维系统起到支撑作用，因此面向用户是系统维护人员。
     </div>
  </div>
  
  <div class="module">
    <div class="img"><img src="${app}/styles/default/images/wel-wkpl-img.gif"/></div>
    <div class="detail"><img src="${app}/styles/default/images/wel-wkpl.gif"/><br/>
     作业计划管理主要是面向作业计划的制定、发布与监控。由于这些流程中的许多部分是值班管理人员没有权限进行操作的，而且此项功能事关公司全局事务，因此，独立出来单独处理。另外，该项功能将在进一步的强化中与企业计划管理系统关联或重复，可以通过接口系统进行数据的交互与同步。事实上，该项功能实现之后，企业计划管理中与生产相关的部分功能可以直接通过该系统实现。
    </div>
  </div>
  
  <div class="module">
    <div class="img"><img src="${app}/styles/default/images/wel-duty-img.gif"/></div>
    <div class="detail"><img src="${app}/styles/default/images/wel-duty.gif"/><br/>
      值班管理功能模块：实现统一的机房电子化值班管理，将值班日志、交接班记录、维护作业记录以及由各项维护规程和管理办法中确定的周期性维护作业计划等任务集成至该系统中，使得当班人员能够准确、高效的完成各项维护任务。排班可选择早班、中班、小夜班、大夜班；早班、中班、夜班方式；白班、夜班方式。一天不会超过4个班。最后要求统计各人各班次排班数。 </div>
  </div>
  
  <div class="module">
    <div class="img"><img src="${app}/styles/default/images/wel-info-img.gif"/></div>
    <div class="detail"><img src="${app}/styles/default/images/wel-info.gif"/><br/>
      系统提供信息发布功能，主要为用户提供了公告撰写、发布、阅读、查找历史记录、打印等功能。授权用户可以使用这项功能，快速实现信息的发布。信息发布功能应根据信息重要等级的不同判断为弹出窗口显示或信息栏滚动显示等，同时公告发布应具备权限控制及发布审核功能。 </div>
  </div>
  
</div>

<%@ include file="/common/footer_eoms.jsp"%>
