-- Table DLL
create table FP_USER_ACTION_LOGS
(
    FP_USER_ACTION_LOGID int primary key,
    FLT_NBR              int                                      null,
    FLT_DT               date                                     null,
    LEG_NBR              int                                      null,
    ITERATION_NBR        int                                      null,
    UID                  varchar(255)                             null,
    ACTN_TYPE            varchar(255)                             null,
    ACTN_DESC            varchar(255)                             null,
    ACTN_TMSTP           timestamp    default (CURRENT_TIMESTAMP) not null,
    SESS_TMSTP           timestamp                                null,
    USR_BRW_IF           varchar(255)                             null,
    FLD_REF              varchar(255)                             null,
    PREV_VALUE           varchar(255)                             null,
    CUR_VALUE            varchar(255)                             null,
    VALUE_TYPE           varchar(255)                             null,
    STATUS               varchar(255) default 'success'           null
);
