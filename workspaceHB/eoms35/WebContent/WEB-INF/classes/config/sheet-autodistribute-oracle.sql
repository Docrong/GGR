create table wf_autodistribute
(
    id       VARCHAR(50),
    flowName VARCHAR(50),
    autoType VARCHAR(50),
    roleId   VARCHAR(50),
    primary key (id)
);
create table wf_threshold
(
    id        VARCHAR(50),
    roleId    VARCHAR(50),
    subRoleId VARCHAR(50),
    userId    VARCHAR(50),
    threshold VARCHAR(50),
    primary key (id)
);
insert into wf_autodistribute(id, flowName, autoType, roleId)
values ('CommonFaultMainFlowProcess', '故障处理流程', '101010602', '191');
