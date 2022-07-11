/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users
(
    id         uuid        NOT NULL DEFAULT gen_random_uuid(),
    first_name varchar(50),
    last_name  varchar(50),
    email      varchar(50) NOT NULL UNIQUE,
    status     varchar(50) NOT NULL DEFAULT 'ACTIVE',
    CONSTRAINT PK_user PRIMARY KEY (id)
);