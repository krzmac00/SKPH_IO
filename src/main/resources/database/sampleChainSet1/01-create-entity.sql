--liquibase formatted sql
--changeset userName:1
DROP TABLE ENTITY;
CREATE TABLE ENTITY (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);