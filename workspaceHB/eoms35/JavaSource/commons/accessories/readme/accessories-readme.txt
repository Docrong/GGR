附件管理组件使用方法：
 
附件管理组件的功能描述：
  1、附件管理信息的配置：通过页面化的方式，用户可以配置各个应用模块的附件管理信息。
包括：附件类型限制、附件大小限制、附件存放路径等信息。
  2、附件的上传和下载：提供标签化的调用方法，实现文件的上传和下载。

说明：为了保持与eoms以往版本的统一性，附件上传和下载的taglib自定义标签与以往版本一样，
      不改变taglib标签的属性。
      
附件管理组件的使用：
   
   请参考com.boco.eoms.commons.accessories.sample  

  1、附件管理信息的配置。
     1）附件管理信息是以xml文件形式保存在系统中的，该xml配置文件的相对保存路径是在
"applicationContext-accessories.xml"中已经配置好的，如下所示。若要改变其保存路径，则
需要修改"applicationContext-accessories.xml"中的对应值。
 <bean id="ItawCommonsAccessoriesConfigManager" class="com.boco.eoms.commons.accessories.service.impl.TawCommonsAccessoriesConfigManagerImpl" autowire="byName">
    <property name="configFilePath">
        <value>classpath:com/boco/eoms/commons/accessories/config/accessoriesConfigInfo.xml</value>
    </property>
</bean>
     2）配置好附件配置信息文件保存路径后，通过附件配置信息页面配置各个应用模块的附件管理信息。
         有关应用模块附件信息的配置方法，请参考附件组件使用手册。
                 
  2、附件的上传和下载。
       我们提供两个struts自定义标签来实现文件的上传和下载，用户在需要完成文件上传和下载的页面
上调用这两个标签就可以实现文件的上传和下载功能。
       1）文件的上传：
         <eoms:attachment idList="" idField="accessories" appCode="1001"/>
        其中：idList－－附件ID号，一般初始值为空。
            idField－－未用，但是为了与以往版本保持一致，保留。
            appCode－－应用模块的ID号，对应与taw_system_appliaction表中的app_id。
       2）文件的下载：
          <eoms:attachment name="BASEWORKSHEET" property="accessories" 
           scope="request" idField="accessories" appCode="wfworksheet" 
           viewFlag="Y"/> 
          其中：name－－formbean名称
              property－－附件在formbean中的属性名
              scope－－formbean存放的范围
              idField－－未用，但是为了与以往版本保持一致，保留。
              appCode－－应用模块的ID号，对应与taw_system_appliaction表中的app_id。
              viewFlag－－其值为"Y"，表明此标签是用于查看附件信息并下载。
         