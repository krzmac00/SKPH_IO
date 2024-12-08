--liquibase formatted sql
--changeset userName:1
insert into ENTITY(name) values( 'Entity 1');
insert into ENTITY(name) values( 'Entity 2');
insert into ENTITY(name) values( 'Entity 3');