create table ACCEPT_SHEET_RULE
(
    ID               VARCHAR2(32)  not Null primary key,


    FAULTAREA        VARCHAR2(100),

    NETSORTONE       VARCHAR2(100) not null,

    NETSORTTWO       VARCHAR2(100),

    NETSORTTHREE     VARCHAR2(100),

    EQUIPMENTFACTORY VARCHAR2(100),

    DEALHUMAN        VARCHAR2(100) not null,

    SUBROLE          VARCHAR2(100) not null

)