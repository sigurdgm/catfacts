-- TABLE
CREATE TABLE T_CATFACTS
(
  ID            NUMBER        NOT NULL,
  FACT          VARCHAR2(255) NOT NULL,
  LENGTH        NUMBER        NULL,
  CREATED_DATE  TIMESTAMP     NOT NULL,

  CONSTRAINT T_CATFACTS_PK PRIMARY KEY (ID)
);

-- SEQUENCE
CREATE SEQUENCE  T_CATFACTS_SEQ
  START WITH 10
  INCREMENT BY 50
  MINVALUE 1
  MAXVALUE 5000000;

-- INSERT
INSERT into T_CATFACTS (ID, FACT, LENGTH, CREATED_DATE) VALUES
(1, 'CATS GO MEOW', 12, sysdate);