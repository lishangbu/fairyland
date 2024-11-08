-- 账户表
DROP TABLE IF EXISTS ACCOUNT;
CREATE TABLE ACCOUNT
(
    ID                      BIGINT,
    ENABLED                 BOOLEAN,
    ACCOUNT_NON_EXPIRED     BOOLEAN,
    CREDENTIALS_NON_EXPIRED BOOLEAN,
    ACCOUNT_NON_LOCKED      BOOLEAN,
    USERNAME                CHARACTER VARYING(30),
    PASSWORD                CHARACTER VARYING(100),
    EMAIL                   CHARACTER VARYING(200),
    MOBILE                  CHARACTER VARYING(11)
);

