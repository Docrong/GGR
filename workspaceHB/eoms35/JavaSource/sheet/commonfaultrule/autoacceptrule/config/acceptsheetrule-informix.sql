create table ACCEPT_SHEET_RULE
(
    ID               VARCHAR(32)  not Null primary key,


    FAULTAREA        VARCHAR(100),

    NETSORTONE       VARCHAR(100) not null,

    NETSORTTWO       VARCHAR(100),

    NETSORTTHREE     VARCHAR(100),

    EQUIPMENTFACTORY VARCHAR(100),

    DEALHUMAN        VARCHAR(100) not null,

    SUBROLE          VARCHAR(100) not null

)