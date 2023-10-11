--liquibase formatted sql

--changeset madrabit:1
ALTER TABLE users_aud
ALTER COLUMN username DROP NOT NULL;
