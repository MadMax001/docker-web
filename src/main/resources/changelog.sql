-- liquibase formatted sql

-- changeset id:initial_db
-- preconditions onFail:HALT
CREATE SCHEMA IF NOT EXISTS docker_test;
CREATE SEQUENCE IF NOT EXISTS docker_test.visit_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE docker_test.visit (
  id BIGINT NOT NULL,
   ips VARCHAR(255),
   created TIMESTAMP,
   CONSTRAINT pk_visit PRIMARY KEY (id)
);