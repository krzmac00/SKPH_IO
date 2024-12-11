-- --liquibase formatted sql
-- --changeset userName:1
-- DROP TABLE entity;
-- CREATE TABLE entity (
--     id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
--     name VARCHAR(255) NOT NULL
-- );
--
--
--
-- CREATE TABLE address (
--                          id SERIAL PRIMARY KEY,  -- Kolumna id jako SERIAL
--                          address VARCHAR(255) NOT NULL  -- Kolumna address, typu VARCHAR
-- );
--
--
-- CREATE TABLE requester (
--                            id SERIAL PRIMARY KEY,
--                            firstName VARCHAR(255) NOT NULL,
--                            lastName VARCHAR(255) NOT NULL
-- );
--
-- CREATE TABLE resource
-- (
--     id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
--     name   VARCHAR(255),
--     amount INTEGER                                 NOT NULL,
--     CONSTRAINT pk_resource PRIMARY KEY (id)
-- );
--
-- CREATE TABLE clothes
-- (
--     id   BIGINT NOT NULL,
--     size VARCHAR(255),
--     sex  VARCHAR(255),
--     CONSTRAINT pk_clothes PRIMARY KEY (id)
-- );
--
-- ALTER TABLE clothes
--     ADD CONSTRAINT FK_CLOTHES_ON_ID FOREIGN KEY (id) REFERENCES resource (id);
--
-- CREATE TABLE food
-- (
--     id           BIGINT  NOT NULL,
--     temperature  VARCHAR(255),
--     allergyFree BOOLEAN NOT NULL,
--     CONSTRAINT pk_food PRIMARY KEY (id)
-- );
--
-- ALTER TABLE food
--     ADD CONSTRAINT FK_FOOD_ON_ID FOREIGN KEY (id) REFERENCES resource (id);
--
-- CREATE TABLE other
-- (
--     id          BIGINT NOT NULL,
--     description VARCHAR(255),
--     CONSTRAINT pk_other PRIMARY KEY (id)
-- );
--
-- ALTER TABLE other
--     ADD CONSTRAINT FK_OTHER_ON_ID FOREIGN KEY (id) REFERENCES resource (id);
--
-- CREATE TABLE shelter
-- (
--     id           BIGINT  NOT NULL,
--     withAnimals BOOLEAN NOT NULL,
--     CONSTRAINT pk_shelter PRIMARY KEY (id)
-- );
--
-- ALTER TABLE shelter
--     ADD CONSTRAINT FK_SHELTER_ON_ID FOREIGN KEY (id) REFERENCES resource (id);
--
-- CREATE TABLE days_list
-- (
--     task_id   BIGINT       NOT NULL,
--     days_list VARCHAR(255) NOT NULL
-- );
--
-- CREATE TABLE task
-- (
--     id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
--     resource     BIGINT                                  NOT NULL,
--     accomplished BOOLEAN                                 NOT NULL,
--     CONSTRAINT pk_task PRIMARY KEY (id)
-- );
--
-- ALTER TABLE task
--     ADD CONSTRAINT uc_task_resource UNIQUE (resource);
--
-- ALTER TABLE task
--     ADD CONSTRAINT FK_TASK_ON_RESOURCE FOREIGN KEY (resource) REFERENCES resource (id);
--
-- ALTER TABLE days_list
--     ADD CONSTRAINT fk_dayslist_on_task FOREIGN KEY (task_id) REFERENCES task (id);
--
-- ALTER TABLE days_list
--     DROP CONSTRAINT fk_dayslist_on_task;
--
-- CREATE TABLE request
-- (
--     id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
--     accomplished BOOLEAN                                 NOT NULL,
--     startDate   date                                    NOT NULL,
--     endDate     date,
--     CONSTRAINT pk_request PRIMARY KEY (id)
-- );
--
-- CREATE TABLE request_resource_list
-- (
--     request_id       BIGINT NOT NULL,
--     resource_list_id BIGINT NOT NULL
-- );
--
-- CREATE TABLE request_task_list
-- (
--     request_id   BIGINT NOT NULL,
--     task_list_id BIGINT NOT NULL
-- );
--
-- ALTER TABLE request_resource_list
--     ADD CONSTRAINT uc_request_resource_list_resourcelist UNIQUE (resource_list_id);
--
-- ALTER TABLE request_task_list
--     ADD CONSTRAINT uc_request_task_list_tasklist UNIQUE (task_list_id);
--
-- ALTER TABLE request_resource_list
--     ADD CONSTRAINT fk_reqreslis_on_request FOREIGN KEY (request_id) REFERENCES request (id);
--
-- ALTER TABLE request_resource_list
--     ADD CONSTRAINT fk_reqreslis_on_resource FOREIGN KEY (resource_list_id) REFERENCES resource (id);
--
-- ALTER TABLE request_task_list
--     ADD CONSTRAINT fk_reqtaslis_on_request FOREIGN KEY (request_id) REFERENCES request (id);
--
-- ALTER TABLE request_task_list
--     ADD CONSTRAINT fk_reqtaslis_on_task FOREIGN KEY (task_list_id) REFERENCES task (id);
--
-- ALTER TABLE request
--     ADD COLUMN address_id BIGINT,
--     ADD CONSTRAINT fk_request_address FOREIGN KEY (address_id) REFERENCES address (id);
--
-- ALTER TABLE request
--     ADD COLUMN requester_id BIGINT,
--     ADD CONSTRAINT fk_request_requester FOREIGN KEY (requester_id) REFERENCES requester (id);

--
-- -- DROP TABLE address CASCADE;
-- -- DROP TABLE clothes CASCADE;
-- -- DROP TABLE days_list CASCADE;
-- -- DROP TABLE food CASCADE;
-- -- DROP TABLE other CASCADE;
-- -- DROP TABLE request CASCADE;
-- -- DROP TABLE request_resource_list CASCADE;
-- -- DROP TABLE request_task_list CASCADE;
-- -- DROP TABLE requester CASCADE;
-- -- DROP TABLE resource CASCADE;
-- -- DROP TABLE shelter CASCADE;
-- -- DROP TABLE task CASCADE;
-- DROP TABLE databasechangelog CASCADE;
-- DROP TABLE databasechangeloglock CASCADE;