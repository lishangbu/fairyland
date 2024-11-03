-- 账户表
DROP TABLE  IF EXISTS "ACCOUNT";
CREATE TABLE IF NOT EXISTS "ACCOUNT"
(
    ID                      bigint,
    ENABLED                 boolean,
    ACCOUNT_NON_EXPIRED     boolean,
    CREDENTIALS_NON_EXPIRED boolean,
    ACCOUNT_NON_LOCKED      boolean,
    USERNAME                varchar(30),
    PASSWORD                varchar(100),
    EMAIL                   varchar(200),
    MOBILE                  varchar(11)
);

