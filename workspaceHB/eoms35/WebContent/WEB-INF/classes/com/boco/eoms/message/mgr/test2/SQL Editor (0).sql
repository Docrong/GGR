select * from sms_apply
select service_id,deleted from sms_apply 
select * from sms_service where id="8a03beb41ee40191011eec88bbd00536"
select count( *)  from sms_apply where  service_id="8a03be3e21f67ba10121f70d83b90007"
serviceid="8a03be3e21f67ba10121f70d83b90007"
select * from IM_MONITOR
select count(*) from IM_MONITOR
select * from sms_service where id="8a03beb41ee40191011eec88bbd00536"


create table IM_MONITOR
(
  ID	VARCHAR(32) not Null primary key,


	APPLY_ID	VARCHAR2(32),

	BUIZ_ID	VARCHAR2(200),

	SERVICE_ID	VARCHAR2(32) not null,

	RECEIVER_ID VARCHAR2(200),
	IS_SEND_IMEDIAT	VARCHAR2(10),

	REGETDATA	VARCHAR2(10),

	CONTENT	VARCHAR2(200),

	FILEPATH	VARCHAR2(200),

	DISPATCH_TIME	VARCHAR2(100),

	DELETED	VARCHAR2(5),
	to_Org_Ids varchar2(200) not null

)
select * from IM_MONITOR
select * from sms_apply --个性化定制

drop table IM_MONITOR;



