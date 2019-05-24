create table wf_autotransmitrule(
    id                VARCHAR(50),
    netTypeOne        VARCHAR(50) ,
	  netTypeTwo        VARCHAR(50) ,
	  netTypeThree      VARCHAR(50) ,
	  roleId            VARCHAR(50),
    domainId          VARCHAR(50) ,
	  equipmentFactory  VARCHAR(50) ,
	  stopTime          VARCHAR(50) ,
	  faultResponseLevel  VARCHAR(50) 
    primary key(id)
); 
