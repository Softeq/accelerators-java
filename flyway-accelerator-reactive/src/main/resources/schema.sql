/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

CREATE TABLE users
(
    id         uuid        NOT NULL,
    first_name varchar(50),
    last_name  varchar(50),
    email      varchar(50) NOT NULL UNIQUE,
    status     varchar(50) NOT NULL DEFAULT 'ACTIVE',
    CONSTRAINT PK_user PRIMARY KEY (id)
);

CREATE TABLE assessment
(
    id              uuid NOT NULL,
    target_user_id  uuid,
    assessment_date timestamp,
    CONSTRAINT PK_assessment PRIMARY KEY (id)
);

ALTER TABLE assessment
    ADD CONSTRAINT FK_assessment_user FOREIGN KEY (target_user_id) REFERENCES users (id);

CREATE TABLE feedback
(
    id            uuid NOT NULL,
    user_id       uuid,
    assessment_id uuid,
    feedback_date timestamp,
    score         decimal(5, 2),
    comment       varchar(1024),
    CONSTRAINT PK_feedback PRIMARY KEY (id)
);

ALTER TABLE feedback
    ADD CONSTRAINT FK_feedback_users FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE feedback
    ADD CONSTRAINT FK_feedback_assessment FOREIGN KEY (assessment_id) REFERENCES assessment (id);