--liquibase formatted sql

--changeset madrabit:1
ALTER TABLE users
ADD COLUMN image varchar(64);

--changeset madrabit:2
ALTER  TABLE users_aud
ADD COLUMN image varchar(64);
