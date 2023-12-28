

UPDATE ACT_CMMN_DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = '192.168.10.1 (192.168.10.1)', LOCKGRANTED = to_timestamp('2020-10-06 14:12:20.971', 'YYYY-MM-DD HH24:MI:SS.FF') WHERE ID = 1 AND LOCKED = 0;

ALTER TABLE ACT_CMMN_RU_CASE_INST ADD LOCK_OWNER_ VARCHAR2(255);

INSERT INTO ACT_CMMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('12', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', SYSTIMESTAMP, 11, '7:e77f0eb21b221f823d6a0e198144cefc', 'addColumn tableName=ACT_CMMN_RU_CASE_INST', '', 'EXECUTED', NULL, NULL, '3.5.3', '1993541264');

ALTER TABLE ACT_CMMN_RU_PLAN_ITEM_INST ADD LAST_UNAVAILABLE_TIME_ TIMESTAMP(3);

ALTER TABLE ACT_CMMN_HI_PLAN_ITEM_INST ADD LAST_UNAVAILABLE_TIME_ TIMESTAMP(3);

INSERT INTO ACT_CMMN_DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('13', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', SYSTIMESTAMP, 12, '7:c5ddabeb0c9fb8db6371c249097d78a3', 'addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_HI_PLAN_ITEM_INST', '', 'EXECUTED', NULL, NULL, '3.5.3', '1993541264');

UPDATE ACT_CMMN_DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

