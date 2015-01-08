drop table if exists PERSISTENT_LOGINS;

create table PERSISTENT_LOGINS  (  
   USERNAME             VARCHAR(64),  
   SERIES               VARCHAR(64)  not null,  
   TOKEN                VARCHAR(64),  
   LAST_USED            TIMESTAMP,  
   constraint PK_PERSISTENT_LOGINS primary key (SERIES)  
); 


drop table if exists SYS_AUTHORITIES;


create table SYS_AUTHORITIES  (  
   AUTHORITY_ID         VARCHAR(100)  not null,  
   AUTHORITY_MARK       VARCHAR(100),  
   AUTHORITY_NAME       VARCHAR(100) not null,  
   AUTHORITY_DESC       VARCHAR(200),  
   MESSAGE              VARCHAR(100),  
   ENABLE               INT(1),  
   ISSYS                INT(1),  
   MODULE_ID            VARCHAR(100),  
   constraint PK_SYS_AUTHORITIES primary key (AUTHORITY_ID)  
); 

drop table if exists SYS_AUTHORITIES_RESOURCES;


create table SYS_AUTHORITIES_RESOURCES  (  
   ID                   VARCHAR(100)                   not null,  
   RESOURCE_ID          VARCHAR(100)                   not null,  
   AUTHORITY_ID         VARCHAR(100)                   not null,  
   constraint PK_SYS_AUTHORITIES_RESOURCES primary key (ID)  
); 

drop table if exists SYS_MODULES;

create table SYS_MODULES  (  
   MODULE_ID            VARCHAR(100)                   not null,  
   MODULE_NAME          VARCHAR(100)                   not null,  
   MODULE_DESC          VARCHAR(200),  
   MODULE_TYPE          VARCHAR(100),  
   PARENT               VARCHAR(100),  
   MODULE_URL           VARCHAR(100),  
   I_LEVEL              INT(1),  
   LEAF                 INT(1),  
   APPLICATION          VARCHAR(100),  
   CONTROLLER           VARCHAR(100),  
   ENABLE               INT(1),  
   PRIORITY             INT(1),  
   constraint PK_SYS_MODULES primary key (MODULE_ID)  
);

drop table if exists SYS_RESOURCES;


create table SYS_RESOURCES  (  
   RESOURCE_ID          VARCHAR(100)                   not null,  
   RESOURCE_TYPE        VARCHAR(100),  
   RESOURCE_NAME        VARCHAR(100),  
   RESOURCE_DESC        VARCHAR(200),  
   RESOURCE_PATH        VARCHAR(200),  
   PRIORITY             VARCHAR(100),  
   ENABLE               INT(1),  
   ISSYS                INT(1),  
   MODULE_ID            VARCHAR(100),  
   constraint PK_SYS_RESOURCES primary key (RESOURCE_ID)  
); 

drop table if exists SYS_ROLES;


create table SYS_ROLES  (  
   ROLE_ID              VARCHAR(100)                   not null,  
   ROLE_NAME            VARCHAR(100),  
   ROLE_DESC            VARCHAR(200),  
   ENABLE               INT(1),  
   ISSYS                INT(1),  
   MODULE_ID            VARCHAR(100),  
   constraint PK_SYS_ROLES primary key (ROLE_ID)  
);


drop table if exists SYS_ROLES_AUTHORITIES;  

create table SYS_ROLES_AUTHORITIES  (  
   ID                   VARCHAR(100)                   not null,  
   AUTHORITY_ID         VARCHAR(100)                   not null,  
   ROLE_ID              VARCHAR(100)                   not null,  
   constraint PK_SYS_ROLES_AUTHORITIES primary key (ID)  
); 

drop table if exists SYS_ROLES_MOUDLES;  


create table SYS_ROLES_MOUDLES  (  
   ID                   VARCHAR(100)                   not null,  
   MODULE_ID            VARCHAR(100)                   not null,  
   ROLE_ID              VARCHAR(100)                   not null,  
   constraint PK_SYS_ROLES_MOUDLES primary key (ID)  
); 

drop table if exists SYS_USERS;  

create table SYS_USERS  (  
   USER_ID              VARCHAR(100)                   not null,  
   USERNAME             VARCHAR(100)                   not null,  
   NAME                 VARCHAR(100),  
   PASSWORD             VARCHAR(100)                   not null,  
   DT_CREATE            TIMESTAMP                not null default  CURRENT_TIMESTAMP,  
   LAST_LOGIN           DATE,  
   DEADLINE             DATE,  
   LOGIN_IP             VARCHAR(100),  
   V_QZJGID             VARCHAR(100),  
   V_QZJGMC             VARCHAR(100),  
   DEP_ID               VARCHAR(100),  
   DEP_NAME             VARCHAR(100),  
   ENABLED              INT(1),  
   ACCOUNT_NON_EXPIRED  INT(1),  
   ACCOUNT_NON_LOCKED   INT(1),  
   CREDENTIALS_NON_EXPIRED INT(1),  
   constraint PK_SYS_USERS primary key (USER_ID)  
); 

drop table if exists SYS_USERS_ROLES;  

create table SYS_USERS_ROLES  (  
   ID                   VARCHAR(100)                   not null,  
   ROLE_ID              VARCHAR(100)                   not null,  
   USER_ID              VARCHAR(100)                   not null,  
   constraint PK_SYS_USERS_ROLES primary key (ID)  
); 

alter table SYS_AUTHORITIES_RESOURCES  
   add constraint FK_SYS_AUTH_REFERENCE_SYS_AUTH foreign key (AUTHORITY_ID)  
      references SYS_AUTHORITIES (AUTHORITY_ID);  
  
alter table SYS_AUTHORITIES_RESOURCES  
   add constraint FK_SYS_AUTH_REFERENCE_SYS_RESO foreign key (RESOURCE_ID)  
      references SYS_RESOURCES (RESOURCE_ID);  
  
alter table SYS_RESOURCES  
   add constraint FK_SYS_RESO_REFERENCE_SYS_MODU foreign key (MODULE_ID)  
      references SYS_MODULES (MODULE_ID);  
  
alter table SYS_ROLES_AUTHORITIES  
   add constraint FK_SYS_ROLE_REFERENCE_SYS_ROLE foreign key (ROLE_ID)  
      references SYS_ROLES (ROLE_ID);  
  
alter table SYS_ROLES_AUTHORITIES  
   add constraint FK_SYS_ROLE_REFERENCE_SYS_AUTH foreign key (AUTHORITY_ID)  
      references SYS_AUTHORITIES (AUTHORITY_ID);  
  
alter table SYS_ROLES_MOUDLES  
   add constraint FK_SYS_ROLE_REFERENCE_SYS_MODU foreign key (MODULE_ID)  
      references SYS_MODULES (MODULE_ID);  
  
alter table SYS_ROLES_MOUDLES  
   add constraint FK_S_ROLE_REFERENCE_SYS_ROLE foreign key (ROLE_ID)  
      references SYS_ROLES (ROLE_ID);  
  
alter table SYS_USERS_ROLES  
   add constraint FK_SYS_USER_REFERENCE_SYS_USER foreign key (USER_ID)  
      references SYS_USERS (USER_ID);  
  
alter table SYS_USERS_ROLES  
   add constraint FK_SYS_USER_REFERENCE_SYS_ROLE foreign key (ROLE_ID)  
      references SYS_ROLES (ROLE_ID);  

