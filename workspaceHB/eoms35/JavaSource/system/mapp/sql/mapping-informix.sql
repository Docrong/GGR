create table MAPPINGSTORAGE
(
  ID	VARCHAR2(32) not Null primary key,


	APP_CODE	VARCHAR2(32) not null,

	APP_NAME	VARCHAR2(32) not null,

	NEW_TABLE	VARCHAR2(32) not null,

	CONTEXT	VARCHAR2(32),

	DELETED	VARCHAR2(32) not null,
        BEANID VARCHAR2(32) 

)