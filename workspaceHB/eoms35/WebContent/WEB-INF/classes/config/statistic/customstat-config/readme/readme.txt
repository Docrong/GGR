定制统计定制的工单都是已经开发完成的及时统计工单，只是在自定义的时间触发及时统计并保存统计的结果。

配置可以定制的工单：
1.statistic-custom-config.xml配置文件中设置可以定制每一类工单
 <report name="commonfault" beanid="statCommonfaultConfigManager"/>
 name:工单类型
 beanid：及时统计工单String注入的beanid，定义及时统计的工单列表，excelMap和quaryMap.
2.可以自定义定制的工单
只需要按照report 节点定义一个工单列表即可，注意：定制的工单一定是已经是开发完成的工单。
3.刷新内存：
在修改statistic-custom-config.xml配置文件后不需要重启服务器就可以执行定制工单的功能。
执行：serverPath/statistic/customstat/stat.do?method=reLoadCustomConfig



statistic/customstat/stat.do?method=seachSatatistFile
statistic/customstat/stSubscriptions.do?method=search
statistic/customstat/stSubscriptions.do?method=newAdd