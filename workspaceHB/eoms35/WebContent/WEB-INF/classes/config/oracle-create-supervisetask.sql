create table Supervise_task_rule
(
    ID                    VARCHAR2(200) not null,
    deleted               VARCHAR2(200),
    major                 varchar2(200),
    city                  VARCHAR2(200),
    country               VARCHAR2(200),
    listedRegulationType  VARCHAR2(200),
    listedRegulationCycle VARCHAR2(200),
    superviseType         varchar2(200),
    acceptOverTime        varchar2(200),
    dealOverTime          varchar2(200),
    mainNetSortOne        varchar2(200),
    mainNetSortTwo        varchar2(200),
    mainNetSortThree      varchar2(200),
    noticeUser1           varchar2(200),
    noticeUser2           varchar2(200),
    noticeUser3           varchar2(200),
    noticeUser4           varchar2(200),
    noticeUserphone1      varchar2(200),
    noticeUserphone2      varchar2(200),
    noticeUserphone3      varchar2(200),
    noticeUserphone4      varchar2(200),
    noticeUsername1       varchar2(200),
    noticeUsername2       varchar2(200),
    noticeUsername3       varchar2(200),
    noticeUsername4       varchar2(200),
    createTime            varchar2(200),
    updateTime            varchar2(200),
    acceptOverTime1       varchar2(200),
    acceptOverTime2       varchar2(200),
    acceptOverTime3       varchar2(200),
    acceptOverTime4       varchar2(200),
    dealOverTime1         varchar2(200),
    dealOverTime2         varchar2(200),
    dealOverTime3         varchar2(200),
    dealOverTime4         varchar2(200)
);
alter table Supervise_Task_rule
    add primary key (id);

create table Supervise_task_Record
(
    id                    varchar2(200),
    deleted               varchar2(200),
    startTime             varchar2(200),
    endTime               varchar2(200),
    city                  varchar2(200),
    country               varchar2(200),
    sheetId               varchar2(200),
    listedRegulationType  varchar2(200),
    listedRegulationCycle varchar2(200),
    mainNetSortOne        varchar2(200),
    mainNetSortTwo        varchar2(200),
    mainNetSortThree      varchar2(200),
    noticeUser1           varchar2(200),
    noticeUser2           varchar2(200),
    noticeUser3           varchar2(200),
    noticeUser4           varchar2(200),
    noticeUserphone1      varchar2(200),
    noticeUserphone2      varchar2(200),
    noticeUserphone3      varchar2(200),
    noticeUserphone4      varchar2(200),
    noticeUsername1       varchar2(200),
    noticeUsername2       varchar2(200),
    noticeUsername3       varchar2(200),
    noticeUsername4       varchar2(200),
    supervisetaskResult   varchar2(200),
    supervisetaskRule     varchar2(200),
    createTime            varchar2(200),
    content               varchar2(2000),
    reason                varchar2(200),
    superviseType         varchar2(200)

);
alter table Supervise_Task_record
    add primary key (id);

