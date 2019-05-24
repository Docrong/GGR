create table wf_overtimetip(
    id                  VARCHAR(50),
    flowName            VARCHAR(50) ,
	specialty1          VARCHAR(50) ,
	specialty2          VARCHAR(50) ,
	specialty3          VARCHAR(50) ,
	specialty4          VARCHAR(50) ,
	specialty5     		VARCHAR(50) ,
	specialty6			VARCHAR(50) ,
	specialty7			VARCHAR(50) ,
	specialty8			VARCHAR(50) ,
	specialty9			VARCHAR(50) ,
	specialty10			VARCHAR(50) ,
	overtimeLimit		VARCHAR(50) ,
	setTime				DATE ,
	userId				VARCHAR(50) ,
    primary key(id)
); 
insert into wf_overtimetip(id,overtimeLimit,userId) values ('1','100','system');